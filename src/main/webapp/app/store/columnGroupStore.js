/**
 * 
 *//**
 * 
 *//**
0 * index.store.columnGroup by hupeng
 */

Ext.define('index.store.columnGroupStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.columnGroupModel',
	model: 'index.model.columnGroupModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_columnGroup.action',
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

