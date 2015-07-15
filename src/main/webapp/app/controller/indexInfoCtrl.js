/**
 * index.controller.indexInfo by hupeng
 */

Ext.define('index.controller.indexInfoCtrl', {
			extend : 'Ext.app.Controller',

			refs : [{
						ref : 'indexInfoView',
						selector : 'indexInfoView'
					}],

			stores : ['indexInfoStore'],

			init : function() {
				this.control({
							'indexInfoView' : {
								show : 'onIndexInfoShow'
							},
							'indexInfoView button[action=add]' : {
								click : 'addIndexInfo'
							},
							'indexInfoView button[action=delete]' : {
								click : 'deleteIndexInfo'
							},
							'indexInfoView' : {
								celldblclick : 'updateIndexInfo'
							},
							'indexInfoView textfield' : {
								keypress : 'queryIndexInfo'
							}
						})
			},

			// 显示的时候reload 加载最新数据
			onIndexInfoShow : function() {
				this.getStore('indexInfoStore').reload();
			},

			// 添加操作
			addIndexInfo : function() {
				aIndexInfoW.show();
//				aIndexInfoW.hide();
				aIndexInfoW.show();
			},

			// 删除操作
			deleteIndexInfo : function() {
				var grid = this.getIndexInfoView();
				index.util.operator.deleteRecord(grid, 'delete_indexInfo.action');
			},

			// 更新操作
			updateIndexInfo : function() {
				var grid = this.getIndexInfoView();
				var store = grid.getStore();
				var selected = grid.getSelectionModel().getSelection()[0];
				if (selected) {
					uIndexInfoW.show();
//					uIndexInfoW.hide();
					uIndexInfoFP.getForm().loadRecord(selected);
					uIndexInfoW.show();
				}
			},

			editorHtml : function() {
				var grid = this.getIndexInfoView();
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
										url : 'assign_indexInfo.action',
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

			queryIndexInfo : function(o, e) {
				var grid = this.getIndexInfoView();
				index.util.operator.query(o, e, grid);
			}
		});