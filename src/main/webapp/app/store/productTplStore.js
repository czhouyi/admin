/**
 * 
 *//**
 * 
 *//**
0 * index.store.productTpl by hupeng
 */

Ext.define('index.store.productTplStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.productTplModel',
	model: 'index.model.productTplModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_productTpl.action',
		actionMethods:{
            create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
		},
		reader: {
			type: 'json',
			root: 'result',
			totalProperty: 'totalCount',
			idProperty: 'id'
		}
	},
	remoteSort: true
});

