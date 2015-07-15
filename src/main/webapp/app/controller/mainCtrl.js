/**
 * indexController.js by hupeng
 */

// Assign the changeLayout function to be called on tree node click.
/**
 * @class index.Controller.indexController
 * @extends extendsClass Description
 */
var detailEl;

Ext.define('index.controller.mainCtrl', {
			extend : 'Ext.app.Controller',
			requires : ['index.view.*'],
			refs : [{
						ref : 'treePanel',
						selector : 'treePanel'
					}, {
						ref : 'contentPanel',
						selector : 'contentPanel'
					}],

			init : function() {
				this.control({
							'treePanel' : {
								itemclick : 'onItemClick'
							}
						});
			},

			onItemClick : function(view, record) {
				var id = record.get('id');
				if (id) {
					id = 'index.view.' + id + "View";
					var contentPanel = this.getContentPanel();
					var newPanel = Ext.create(id);
					var newStore = newPanel.getStore();
					newStore.proxy.extraParams = {};
					newStore.loadPage(1);
					contentPanel.removeAll(true);
					contentPanel.add(newPanel);
				}
			}
		});
