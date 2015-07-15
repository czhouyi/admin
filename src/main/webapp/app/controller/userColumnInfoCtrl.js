/**
 * index.controller.userColumnInfoInfo by hupeng
 */

Ext.define('index.controller.userColumnInfoCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'userColumnInfoView',
				selector : 'userColumnInfoView'
			}],

	stores : ['userColumnInfoStore'],

	init : function() {
		this.control({
					'userColumnInfoView' : {
						show : 'onUserColumnInfoShow'
					},
					'userColumnInfoView textfield' : {
						keypress : 'queryUserColumnInfo'
					}
				})
	},

	onUserColumnInfoShow : function() {
		this.getStore('userColumnInfoStore').reload();
	},

	queryUserColumnInfo : function(o, e) {
		var grid = this.getUserColumnInfoView();
		index.util.operator.query(o, e, grid);
	}

});