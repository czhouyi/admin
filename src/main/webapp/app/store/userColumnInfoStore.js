/**
 * index.store.userColumnInfo by hupeng
 */

Ext.define('index.store.userColumnInfoStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.userColumnInfoModel',
	model: 'index.model.userColumnInfoModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'view_userColumnInfo.action',
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