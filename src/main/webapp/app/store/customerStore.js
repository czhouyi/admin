/**
 * 
 *//**
0 * index.store.customer by hupeng
 */

Ext.define('index.store.customerStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.customerModel',
	model: 'index.model.customerModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_customer.action',
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

