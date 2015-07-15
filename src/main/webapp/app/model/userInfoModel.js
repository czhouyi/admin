/**
 * index.model.userInfo by hupeng
 */
 
Ext.define("index.model.userInfoModel", {
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
						name : 'PUBLIC_KEY',
						type : 'String'
					}, {
						name : 'ID_ORG',
						type : 'String'
					}, {
						name : 'ID_CUSTOMER',
						type : 'String'
					}, {
						name : 'CUSTOMER_NAME',
						type : 'String'
					}, {
						name : 'KEY_IP',
						type : 'String'
					}, {
						name : 'CUSTOMER_IP',
						type : 'String'
					}, {
						name : 'IS_REG',
						type : 'String'
					}, {
						name : 'USER_STAT',
						type : 'String'
					}, {
						name : 'KEY_STAT',
						type : 'String'
					}, {
						name : 'ORG_STAT',
						type : 'String'
					}, {
						name : 'CUSTOMER_STAT',
						type : 'String'
					}]
		})