/**
0 * index.store.role by hupeng
 */

Ext.define('index.store.roleStore', {
	extend: 'Ext.data.Store',
	requires: 'index.model.roleModel',
	model: 'index.model.roleModel',
	pageSize: 20,
	proxy: {
		type: 'ajax',
		url: 'list_role.action',
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