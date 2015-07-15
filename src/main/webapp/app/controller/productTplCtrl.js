/**
 * index.controller.productTpl by hupeng
 */

Ext.define('index.controller.productTplCtrl', {
			extend : 'Ext.app.Controller',

			refs : [{
						ref : 'productTplView',
						selector : 'productTplView'
					}],

			stores : ['productTplStore'],

			init : function() {
				this.control({
							'productTplView' : {
								show : 'onProductTplShow'
							},
							'productTplView button[action=add]' : {
								click : 'addProductTpl'
							},
							'productTplView button[action=delete]' : {
								click : 'deleteProductTpl'
							},
							'productTplView button[action=arrange]' : {
								click : 'arrangeProductTpl'
							},
							'productTplView' : {
								celldblclick : 'updateProductTpl'
							},
							'productTplView textfield' : {
								keypress : 'queryProductTpl'
							}
						})
			},

			// 显示的时候reload 加载最新数据
			onProductTplShow : function() {
				this.getStore('productTplStore').reload();
			},

			// 添加操作
			addProductTpl : function() {
				aProductTplW.show();
			},

			// 删除操作
			deleteProductTpl : function() {
				var grid = this.getProductTplView();
				index.util.operator.deleteRecord(grid, 'delete_productTpl.action');
			},

			// 更新操作
			updateProductTpl : function() {
				var grid = this.getProductTplView();
				var store = grid.getStore();
				var selected = grid.getSelectionModel().getSelection()[0];
				if (selected) {
					uProductTplFP.getForm().loadRecord(selected);
					uProductTplW.show();
				}
			},

			arrangeProductTpl : function() {
				var grid = this.getProductTplView();
				var store = grid.getStore();
				var selected = grid.getSelectionModel().getSelection()[0];
				if (selected) {
					var selectedId = selected.get('ID');
					columnTreeStore.load({
								params : {
									tplCode : selectedId
								}
							});
					arrangeCGW.show();
				}
				arrangeCGW.down('button').setHandler( function() {
							var checkedNodes = columnTreePanel.getChecked();
							var code = [];
							for (var i = 0; i < checkedNodes.length; i++) {
								code.push(checkedNodes[i].internalId);
							}
							columnGroupCode = code.join(',');
							Ext.Ajax.request({
										url : 'assign_productTpl.action',
										params : {
											tplCode : selectedId,
											assignResult : columnGroupCode
										},
										success : function(response) {
											Ext.Msg.alert('提示','分配成功');
											arrangeCGW.close();
											store.reload();
										},
										failure : function(response) {
											Ext.Msg.alert('错误','分配失败');
										}
									});
						});
			},

			queryProductTpl : function(o, e) {
				var grid = this.getProductTplView();
				index.util.operator.query(o, e, grid);
			}
		});