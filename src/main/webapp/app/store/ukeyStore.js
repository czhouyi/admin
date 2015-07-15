/**
 * 
 *//**
0 * index.store.ukey by hupeng
 */

Ext.define('index.store.ukeyStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.ukeyModel',
	model: 'index.model.ukeyModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_ukey.action',
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

