/**
 * index.controller.customer by hupeng
 */

Ext.define('index.controller.customerCtrl', {
			extend : 'Ext.app.Controller',

			refs : [{
						ref : 'customerView',
						selector : 'customerView'
					}],

			stores : ['customerStore'],


			init : function() {
				this.control({
							'customerView' : {
								show : 'onCustomerShow'
							},
							'customerView button[action=add]' : {
								click : 'addCustomer'
							},
							'customerView button[action=delete]' : {
								click : 'deleteCustomer'
							},
							'customerView' : {
								celldblclick : 'updateCustomer'
							},
							'customerView textfield' : {
								keypress : 'queryCustomer'
							}
						})
			},

			// 显示的时候reload 加载最新数据
			onCustomerShow : function() {
				this.getStore('customerStore').reload();
			},

			// 添加操作
			addCustomer : function() {
				aCustomerW.show();
			},

			// 删除操作
			deleteCustomer : function() {
				var grid = this.getCustomerView();
				index.util.operator
						.deleteRecord(grid, 'delete_customer.action');
			},

			// 更新操作
			updateCustomer : function() {
				var grid = this.getCustomerView();
				var store = grid.getStore();
				var selected = grid.getSelectionModel().getSelection()[0];
				if (selected) {
					uCustomerFP.getForm().loadRecord(selected);
					uCustomerW.show();
				}
			},

			queryCustomer : function(o, e) {
				var grid = this.getCustomerView();
				index.util.operator.query(o, e, grid);
			}
		});