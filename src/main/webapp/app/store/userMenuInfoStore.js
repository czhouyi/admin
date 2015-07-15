/**
 * index.store.userProductInfo by hupeng
 */

Ext.define('index.store.userMenuInfoStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.userMenuInfoModel',
	model: 'index.model.userMenuInfoModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'view_userMenuInfo.action',
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