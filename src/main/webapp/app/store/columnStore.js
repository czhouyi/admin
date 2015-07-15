/**
0 * index.store.column by hupeng
 */

Ext.define('index.store.columnStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.columnModel',
	model: 'index.model.columnModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_column.action',
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