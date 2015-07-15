/**
 * 
 *//**
 * 
 *//**
0 * index.store.org by hupeng
 */

Ext.define('index.store.orgStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.orgModel',
	model: 'index.model.orgModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_org.action',
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

