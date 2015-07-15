/**
 * index.controller.userProductInfoInfo by hupeng
 */

Ext.define('index.controller.userProductInfoCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'userProductInfoView',
				selector : 'userProductInfoView'
			}],

	stores : ['userProductInfoStore'],

	init : function() {
		this.control({
					'userProductInfoView' : {
						show : 'onUserProductInfoShow'
					},
					'userProductInfoView textfield' : {
						keypress : 'queryUserProductInfo'
					}
				})
	},

	onUserProductInfoShow : function() {
		this.getStore('userProductInfoStore').reload();
	},

	queryUserProductInfo : function(o, e) {
		var grid = this.getUserProductInfoView();
		index.util.operator.query(o, e, grid);
	}

});