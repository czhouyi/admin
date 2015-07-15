/**
 * index.controller.columnGroup by hupeng
 */

Ext.define('index.controller.columnGroupCtrl', {
			extend : 'Ext.app.Controller',

			refs : [{
						ref : 'columnGroupView',
						selector : 'columnGroupView'
					}],

			stores : ['columnGroupStore'],

			init : function() {
				this.control({
							'columnGroupView' : {
								show : 'onColumnGroupShow'
							},
							'columnGroupView button[action=add]' : {
								click : 'addColumnGroup'
							},
							'columnGroupView button[action=delete]' : {
								click : 'deleteColumnGroup'
							},
							'columnGroupView' : {
								celldblclick : 'updateColumnGroup'
							},
							'columnGroupView textfield' : {
								keypress : 'queryColumnGroup'
							}
						})
			},

			// 显示的时候reload 加载最新数据
			onColumnGroupShow : function() {
				this.getStore('columnGroupStore').reload();
			},

			// 添加操作
			addColumnGroup : function() {
				aColumnGroupW.show();
			},

			// 删除操作
			deleteColumnGroup : function() {
				var grid = this.getColumnGroupView();
				index.util.operator.deleteRecord(grid,
						'delete_columnGroup.action');
			},

			// 更新操作
			updateColumnGroup : function() {
				var grid = this.getColumnGroupView();
				var store = grid.getStore();
				var selected = grid.getSelectionModel().getSelection()[0];
				if (selected) {
					uColumnGroupFP.getForm().loadRecord(selected);
					uColumnGroupW.show();
				}
			},

			queryColumnGroup : function(o, e) {
				var grid = this.getColumnGroupView();
				index.util.operator.query(o, e, grid);
			}
		});