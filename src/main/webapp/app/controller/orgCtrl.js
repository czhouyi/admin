/**
 * index.controller.org by hupeng
 */

Ext.define('index.controller.orgCtrl', {
			extend : 'Ext.app.Controller',

			refs : [{
						ref : 'orgView',
						selector : 'orgView'
					}],

			stores : ['orgStore'],

			init : function() {
				this.control({
							'orgView' : {
								show : 'onOrgShow'
							},
							'orgView button[action=add]' : {
								click : 'addOrg'
							},
							'orgView button[action=delete]' : {
								click : 'deleteOrg'
							},
							'orgView' : {
								celldblclick : 'updateOrg'
							},
							'orgView textfield' : {
								keypress : 'queryOrg'
							}
						})
			},

			// 显示的时候reload 加载最新数据
			onOrgShow : function() {
				this.getStore('orgStore').reload();
			},

			// 添加操作
			addOrg : function() {
				aOrgW.show();
			},

			// 删除操作
			deleteOrg : function() {
				var grid = this.getOrgView();
				index.util.operator.deleteRecord(grid, 'delete_org.action');
			},

			// 更新操作
			updateOrg : function() {
				var grid = this.getOrgView();
				var store = grid.getStore();
				var selected = grid.getSelectionModel().getSelection()[0];
				if (selected) {
					uOrgFP.getForm().loadRecord(selected);
					uOrgW.show();
				}
			},

			queryOrg : function(o, e) {
				var grid = this.getOrgView();
				index.util.operator.query(o, e, grid);
			}
		});