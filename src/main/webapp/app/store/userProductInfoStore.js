/**
 * index.store.userProductInfo by hupeng
 */

Ext.define('index.store.userProductInfoStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.userProductInfoModel',
	model: 'index.model.userProductInfoModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'view_userProductInfo.action',
		actionMethods:{
            create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
		},
		reader: {
			type: 'json',
			root: 'result',
			totalProperty: 'totalCount'
		}
	},
	remoteSort: true
});