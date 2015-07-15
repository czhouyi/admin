/**
 * 
 *//**
 * 
 *//**
0 * index.store.indexInfo by hupeng
 */

Ext.define('index.store.indexInfoStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.indexInfoModel',
	model: 'index.model.indexInfoModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_indexInfo.action',
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

