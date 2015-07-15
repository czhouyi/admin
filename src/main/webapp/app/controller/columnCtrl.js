/**
 * index.controller.column by hupeng
 */

Ext.define('index.controller.columnCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'columnView',
				selector : 'columnView'
			}],

	stores : ['columnStore'],

	init : function() {
		this.control({
					'columnView' : {
						show : 'onColumnShow'
					},
					'columnView button[action=add]' : {
						click : 'addColumn'
					},
					'columnView button[action=delete]' : {
						click : 'deleteColumn'
					},
					'columnView' : {
						celldblclick : 'updateColumn'
					},
					'columnView textfield' : {
						keypress : 'queryColumn'
					}
				})
	},

	onColumnShow : function() {
		this.getStore('columnStore').reload();
	},

	addColumn : function() {
		aColumnW.show();
	},

	deleteColumn : function() {
		var grid = this.getColumnView();
		index.util.operator.deleteRecord(grid, 'delete_column.action');
	},

	updateColumn : function() {
		var grid = this.getColumnView();
		var store = grid.getStore();
		var selected = grid.getSelectionModel().getSelection()[0];
		if (selected) {
			uColumnFP.getForm().loadRecord(selected);
			uColumnW.show();
		}
	},

	queryColumn : function(o, e) {
		var grid = this.getColumnView();
		index.util.operator.query(o, e, grid);
	}

});