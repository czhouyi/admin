/**
 * index.model.userColumnInfo by hupeng
 */

Ext.define("index.model.userColumnInfoModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'USER_NAME',
						type : 'String'
					}, {
						name : 'ID_USER',
						type : 'String'
					}, {
						name : 'UKEY_NO',
						type : 'String'
					}, {
						name : 'ID_PRODUCT_ORDER',
						type : 'String'
					}, {
						name : 'P_TPL',
						type : 'String'
					}, {
						name : 'PRODUCT_TPL_NAME',
						type : 'String'
					}, {
						name : 'COLUMN_GROUP_NAME',
						type : 'String'
					}, {
						name : 'ID_COLUMN',
						type : 'String'
					}, {
						name : 'COL_NAME',
						type : 'String'
					}, {
						name : 'COL_DESC',
						type : 'String'
					}, {
						name : 'GRANT_TYP',
						type : 'String'
					}, {
						name : 'REVOKE_TIME',
						type : 'String'
					}]
		})