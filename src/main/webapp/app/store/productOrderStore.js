/**
 * 
 *//**
 * 
 *//**
0 * index.store.productOrder by hupeng
 */

Ext.define('index.store.productOrderStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.productOrderModel',
	model: 'index.model.productOrderModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_productOrder.action',
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

