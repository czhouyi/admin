/**
 * index.controller.productOrder by hupeng
 */

var showWindow = function(tplCode) {
	
}

Ext.define('index.controller.productOrderCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'productOrderView',
				selector : 'productOrderView'
			}],

	stores : ['productOrderStore'],

	init : function() {
		this.control({
					'productOrderView' : {
						show : 'onProductOrderShow'
					},
					'productOrderView button[action=add]' : {
						click : 'addProductOrder'
					},
					'productOrderView button[action=delete]' : {
						click : 'deleteProductOrder'
					},
					'productOrderView button[action=assign]' : {
						click : 'assignProductOrder'
					},
					'productOrderView' : {
						celldblclick : 'updateProductOrder'
					},
					'productOrderView textfield' : {
						keypress : 'queryProductOrder'
					}
				})
	},

	// 显示的时候reload 加载最新数据
	onProductOrderShow : function() {
		this.getStore('productOrderStore').reload();
	},

	// 添加操作
	addProductOrder : function() {
		aProductOrderW.show();
	},

	// 删除操作
	deleteProductOrder : function() {
		var grid = this.getProductOrderView();
				var store = grid.getStore();
		var selected = grid.getSelectionModel().getSelection()[0];
		if (selected) {
			Ext.Msg.confirm('系统提示', '确定要删除吗？', function(btn) {
						if (btn == 'yes') {
							Ext.Ajax.request({
										url : 'delete_productOrder.action',
										params : {
											POID : selected.get('POID'),
											USERID : selected.get('USERID')
										},
										success : function(response) {
											var text = response.responseText;
											Ext.Msg.alert('提示', '删除成功');
											grid.getStore().reload();
											grid.getView().refresh();
										},
										failure : function(response) {
											var text = response.responseText;
											Ext.Msg.alert('错误', text);
										}
									});
						}

					}, this);
		} else {
			Ext.Msg.alert('提示', '请选择一条记录');
			return;
		}
	},

	// 更新操作
	updateProductOrder : function() {
		var grid = this.getProductOrderView();
		var store = grid.getStore();
		var selected = grid.getSelectionModel().getSelection()[0];
		if (selected) {
			uProductOrderFP.getForm().loadRecord(selected);
			uProductOrderW.show();
		}
	},

	assignProductOrder : function() {
		var grid = this.getProductOrderView();
		var selected = grid.getSelectionModel().getSelection()[0];
		if (selected) {
			var selectedId = selected.get('POID');
			var userId = selected.get('USERID');
			var tplCodeId = selected.get('TPLCODE');
			columnGroupTreeStore.load({
						params : {
							tplCode : tplCodeId,
							userid : userId
						}
					});
			Ext.Ajax.request({
				url : 'select_role.action',
				params : {
					tplCode : tplCodeId,
					idUser : userId
				},
				success : function(response) {
					var result = Ext.decode(response.responseText).result;
					roleFormPanel.removeAll(true);
					for (var i=0; i<result.length; i++) {
						var r = result[i];
						if (r.CHECKED=='TRUE') {
							var check = true;
						} else check = false;
						roleFormPanel.add({xtype:'checkbox', boxLabel:r.RN, checked:check, id:r.RID, height:50});
					}
					assignProductOrderW.show();
				},
				failure : function(response) {
					Ext.Msg.alert('错误','系统错误');
				}
			});
		} else {
			Ext.Msg.alert('提示','请选择一条记录');
			return;
		};
		assignProductOrderW.down('button[action=submit]').setHandler( function() {
					var checkId = [];
					roleFormPanel.items.each(function(item, index, length){
						if (item.checked) {
							checkId.push(item.id);
						}
					});
					var roleId = checkId.join(',');
					var store = columnGroupGrid.getStore();
					//清理过滤条件
					store.clearFilter();
					var code = [];
					for (var i = 0; i < store.getCount(); i++) {
						var record = store.getAt(i);
						if (record.get('checked')) {
							var tmp = [];
							tmp.push(record.get('colid'));
							tmp.push(record.get('grant_typ'));
							tmp.push(Ext.Date.format(record.get('revoke_time'),'Y-m-d'));
							code.push(tmp.join(','));
						}
					}
					columnGroupCode = code.join(';');
					Ext.Ajax.request({
								url : 'assign_productOrder.action',
								params : {
									poid : selectedId,
									assignResult : columnGroupCode,
									role : roleId,
									idUser : userId,
									tplCode : tplCodeId
								},
								success : function(response) {
									Ext.Msg.alert('提示','分配成功');
									assignProductOrderW.close();
								},
								failure : function(response) {
									Ext.Msg.alert('错误','分配失败');
								}
							});
				});
	},

	queryProductOrder : function(o, e) {
		var grid = this.getProductOrderView();
		index.util.operator.query(o, e, grid);
	}
});