/**
 * index.view.userProductInfo by hupeng
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
			header : '使用产品实例ID',
			dataIndex : 'ID_PRODUCT_ORDER'
		}, {
			header : '产品模版编号',
			dataIndex : 'CODE_PRODUCT_TPL'
		}, {
			header : '产品名称',
			dataIndex : 'PRODUCT_TPL_NAME'
		}, {
			header : '可查看组别数',
			dataIndex : 'CNT_GRP'
		}, {
			header : '可查看字段数',
			dataIndex : 'CNT_COL'
		}, {
			header : '该产品限制条数',
			dataIndex : 'LIMIT_CNT'
		}, {
			header : '已查条数',
			dataIndex : 'ORDER_CNT'
		}];

Ext.define('index.view.userProductInfoView', {
			extend : 'Ext.grid.GridPanel',
			title : '用户可用产品信息',
			alias : 'widget.userProductInfoView',
			columns : columns,
			store : 'userProductInfoStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'userProductInfoStore',
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