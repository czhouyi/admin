/**
 * index.view.userInfo by hupeng
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
			header : 'CA证书内容',
			dataIndex : 'PUBLIC_KEY'
		}, {
			header : '所属机构ID',
			dataIndex : 'ID_ORG'
		}, {
			header : '所属客户ID',
			dataIndex : 'ID_CUSTOMER'
		}, {
			header : '所属客户',
			dataIndex : 'CUSTOMER_NAME'
		}, {
			header : 'UKEY绑定IP',
			dataIndex : 'KEY_IP'
		}, {
			header : '客户通用IP',
			dataIndex : 'CUSTOMER_IP'
		}, {
			header : '是否已注册',
			dataIndex : 'IS_REG'
		}, {
			header : '用户状态',
			dataIndex : 'USER_STAT'
		}, {
			header : 'KEY状态',
			dataIndex : 'KEY_STAT'
		}, {
			header : '机构状态',
			dataIndex : 'ORG_STAT'
		}, {
			header : '客户状态',
			dataIndex : 'CUSTOMER_STAT'
		}];

Ext.define('index.view.userInfoView', {
			extend : 'Ext.grid.GridPanel',
			title : '用户信息大全',
			alias : 'widget.userInfoView',
			columns : columns,
			store : 'userInfoStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'userInfoStore',
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