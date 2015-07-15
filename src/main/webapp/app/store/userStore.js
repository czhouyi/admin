/**
0 * index.store.user by hupeng
 */

Ext.define('index.store.userStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.userModel',
	model: 'index.model.userModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_user.action',
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