/**
 * index.controller.ukey by hupeng
 */

Ext.define('index.controller.ukeyCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'ukeyView',
				selector : 'ukeyView'
			}],

	stores : ['ukeyStore'],

	init : function() {
		this.control({
					'ukeyView' : {
						show : 'onUkeyShow'
					},
					'ukeyView button[action=add]' : {
						click : 'addUkey'
					},
					'ukeyView button[action=modify]' : {
						click : 'batchModifyIP'
					},
					'ukeyView button[action=delete]' : {
						click : 'deleteUkey'
					},
					'ukeyView' : {
						celldblclick : 'updateUkey'
					},
					'ukeyView textfield' : {
						keypress : 'queryUkey'
					}
				})
	},
	
	batchModifyIP : function() {
		mUkeyW.show();
	},
	
	//显示的时候reload 加载最新数据
	onUkeyShow : function() {
		this.getStore('ukeyStore').reload();
	},

	//添加操作
	addUkey : function() {
		aUkeyW.show();
	},

	//删除操作
	deleteUkey : function() {
		var grid = this.getUkeyView();
		index.util.operator.deleteRecord(grid, 'delete_ukey.action');
	},

	//更新操作
	updateUkey : function() {
		var grid = this.getUkeyView();
		var store = grid.getStore();
		var selected = grid.getSelectionModel().getSelection()[0];
		if (selected) {
			uUkeyFP.getForm().loadRecord(selected);
			uUkeyW.show();
		}
	},

	queryUkey : function(o, e) {
		var grid = this.getUkeyView();
		index.util.operator.query(o, e, grid);
	}
});