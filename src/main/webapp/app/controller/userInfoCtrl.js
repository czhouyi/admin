/**
 * index.controller.userInfoInfo by hupeng
 */

Ext.define('index.controller.userInfoCtrl', {
	extend : 'Ext.app.Controller',

	refs : [{
				ref : 'userInfoView',
				selector : 'userInfoView'
			}],

	stores : ['userInfoStore'],

	init : function() {
		this.control({
					'userInfoView' : {
						show : 'onUserInfoShow'
					},
					'userInfoView textfield' : {
						keypress : 'queryUserInfo'
					}
				})
	},

	onUserInfoShow : function() {
		this.getStore('userInfoStore').reload();
	},

	queryUserInfo : function(o, e) {
		var grid = this.getUserInfoView();
		index.util.operator.query(o, e, grid);
	}

});