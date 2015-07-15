/**
 * index.controller.role by hupeng
 */
 
 Ext.define('index.controller.roleCtrl', {
 	extend : 'Ext.app.Controller',
 	
 	refs : [{
 		ref : 'roleView',
 		selector : 'roleView'
 	}],
 	
 	stores : ['roleStore'],
 	
 	init : function() {
 		this.control( {
 			'roleView' : {
 				show : 'onRoleShow'
 			}
 		})
 	},
 	
 	onRoleShow : function() {
 		this.getStore('roleStore').reload();
 	}
 });