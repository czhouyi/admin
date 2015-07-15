/**
 * index.model.userProductInfo by hupeng
 */

Ext.define("index.model.userProductInfoModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'USER_NAME',
						type : 'String'
					}, {
						name : 'ID_USER',
						type : 'String'
					}, {
						name : 'U_ID',
						type : 'String'
					}, {
						name : 'UKEY_NO',
						type : 'String'
					}, {
						name : 'ID_PRODUCT_ORDER',
						type : 'String'
					}, {
						name : 'CODE_PRODUCT_TPL',
						type : 'String'
					}, {
						name : 'PRODUCT_TPL_NAME',
						type : 'String'
					}, {
						name : 'CNT_GRP',
						type : 'String'
					}, {
						name : 'CNT_COL',
						type : 'String'
					}, {
						name : 'LIMIT_CNT',
						type : 'String'
					}, {
						name : 'ORDER_CNT',
						type : 'String'
					}]
		})