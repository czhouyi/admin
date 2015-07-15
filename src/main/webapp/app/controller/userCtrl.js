/**
 * index.controller.user by hupeng
 */

Ext.define('index.controller.userCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'userView',
				selector : 'userView'
			}],

	stores : ['userStore'],

	init : function() {
		this.control({
					'userView' : {
						show : 'onUserShow'
					},
					'userView button[action=add]' : {
						click : 'addUser'
					},
					'userView button[action=delete]' : {
						click : 'deleteUser'
					},
					'userView button[action=assign]' : {
						click : 'assignUser'
					},
					'userView' : {
						celldblclick : 'updateUser'
					},
					'userView textfield' : {
						keypress : 'queryUser'
					}
				})
	},

	onUserShow : function() {
		this.getStore('userStore').reload();
	},

	addUser : function() {
		aUserW.show();
	},

	deleteUser : function() {
		var grid = this.getUserView();
		index.util.operator.deleteRecord(grid, 'delete_user.action');
	},

	updateUser : function() {
		var grid = this.getUserView();
		var store = grid.getStore();
		var selected = grid.getSelectionModel().getSelection()[0];
		if (selected) {
			uUserFP.getForm().loadRecord(selected);
			uUserW.show();
		}
	},

	queryUser : function(o, e) {
		var grid = this.getUserView();
		index.util.operator.query(o, e, grid);
	},

	//分配产品实例
	assignUser : function() {

	}

});