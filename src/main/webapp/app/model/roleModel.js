/**
 * index.model.user by hupeng
 */

Ext.define("index.model.roleModel", {
	extend: 'Ext.data.Model',
	fields: [{
		name: 'id',
		type: 'String'
	}, {
		name: 'role_name',
		type: 'String'
	}, {
		name: 'id_customer',
		type: 'String'
	}, {
		name: 'role_type',
		type: 'String'
	}, {
		name: 'desc_info',
		type: 'String'
	}, {
		name: 'state',
		type: 'String'
	}, {
		name: 'udt',
		type: 'Date'
	}]
})