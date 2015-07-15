/**
 * index.view.userColumnInfo by hupeng
 */

var columns = [{
			header : '用户名称',
			dataIndex : 'USER_NAME'
		}, {
			header : '用户ID',
			dataIndex : 'ID_USER'
		}, {
			header : 'UKEY物理编号',
			dataIndex : 'UKEY_NO'
		}, {
			header : '产品实例ID',
			dataIndex : 'ID_PRODUCT_ORDER'
		}, {
			header : '产品模板编号',
			dataIndex : 'P_TPL'
		}, {
			header : '产品名称',
			dataIndex : 'PRODUCT_TPL_NAME'
		}, {
			header : '字段组别名',
			dataIndex : 'COLUMN_GROUP_NAME'
		}, {
			header : '字段ID',
			dataIndex : 'ID_COLUMN'
		}, {
			header : '字段名',
			dataIndex : 'COL_NAME'
		}, {
			header : '字段描述',
			dataIndex : 'COL_DESC'
		}, {
			header : '字段类型',
			dataIndex : 'GRANT_TYP'
		}, {
			header : '回收时间',
			dataIndex : 'REVOKE_TIME'
		}];

Ext.define('index.view.userColumnInfoView', {
			extend : 'Ext.grid.GridPanel',
			title : '用户可查看字段',
			alias : 'widget.userColumnInfoView',
			columns : columns,
			store : 'userColumnInfoStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'userColumnInfoStore',
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