/**
 * @class MyNamespace.viewport
 * @extends extendsClass
 * Description
 */
Ext.define('index.view.Viewport', {
	extend : 'Ext.container.Viewport',

	requires : ['index.view.treePanel', 'index.view.contentPanel'],

	layout : 'border',
	title : 'GsInfo后台管理系统',
	items : [{
				region : 'north',
				xtype : 'toolBarPanel'
			}, {
				layout : 'border',
				id : 'tree',
				region : 'west',
				border : false,
				split : true,
				margins : '0 0 1 2',
				width : 200,
				minSize : 100,
				maxSize : 400,
				height : '100%',
				xtype : 'treePanel'

			}, {
				region : 'center',
				xtype : 'contentPanel'
			}, {
				region : 'south',
				xtype : 'mystatusbar'
			}],
	renderTo : Ext.getBody()
});