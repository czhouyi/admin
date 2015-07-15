/**
 * index.store.userInfo by hupeng
 */

Ext.define('index.store.userInfoStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.userInfoModel',
	model: 'index.model.userInfoModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'view_userInfo.action',
		actionMethods:{
            create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
		},
		reader: {
			type: 'json',
			root: 'result',
			totalProperty: 'totalCount',
			idProperty: 'ID_USER'
		}
	},
	remoteSort: true
});