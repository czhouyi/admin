/**
 * index.controller.userMenuInfoInfo by hupeng
 */

Ext.define('index.controller.userMenuInfoCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'userMenuInfoView',
				selector : 'userMenuInfoView'
			}],

	stores : ['userMenuInfoStore'],

	init : function() {
		this.control({
					'userMenuInfoView' : {
						show : 'onUserMenuInfoShow'
					},
					'userMenuInfoView textfield' : {
						keypress : 'queryUserMenuInfo'
					}
				})
	},

	onUserMenuInfoShow : function() {
		this.getStore('userMenuInfoStore').reload();
	},

	queryUserMenuInfo : function(o, e) {
		var grid = this.getUserMenuInfoView();
		index.util.operator.query(o, e, grid);
	}

});