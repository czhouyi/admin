/**
 * index.view.userMenuInfo by hupeng
 */

var columns = [{
			header : '用户名称',
			dataIndex : 'USER_NAME'
		}, {
			header : '用户ID',
			dataIndex : 'ID_USER'
		}, {
			header : 'CA证书编号U_ID',
			dataIndex : 'U_ID'
		}, {
			header : 'UKEY物理编号',
			dataIndex : 'UKEY_NO'
		}, {
			header : '所分配角色ID',
			dataIndex : 'ID_ROLE'
		}, {
			header : '角色描述',
			dataIndex : 'ROLE_NAME'
		}, {
			header : '角色可看菜单数',
			dataIndex : 'CNT_MENU'
		}, {
			header : '角色可看产品数',
			dataIndex : 'CNT_P_TPL'
		}];

Ext.define('index.view.userMenuInfoView', {
			extend : 'Ext.grid.GridPanel',
			title : '用户菜单信息',
			alias : 'widget.userMenuInfoView',
			columns : columns,
			store : 'userMenuInfoStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'userMenuInfoStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [{
									xtype : 'textfield',
									emptyText : '用户名',
									enableKeyEvents : true,
									name : 'userName'
								}, {
									xtype : 'textfield',
									emptyText : 'Ukey编号',
									enableKeyEvents : true,
									name : 'ukeyNo'
								}]
					}]
		});