/**
 * index.view.role by hupeng
 */

var columns = [{
			header : '编号',
			dataIndex : 'id'
		}, {
			header : '角色名',
			dataIndex : 'role_name'
		}];

Ext.define('index.view.roleView', {
			extend : 'Ext.grid.GridPanel',
			title : '菜单角色',
			alias : 'widget.roleView',
			columns : columns,
			store : 'roleStore',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'roleStore',
						dock : 'bottom',
						displayInfo : true
					}]
		});