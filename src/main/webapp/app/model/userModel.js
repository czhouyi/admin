Ext.define("index.model.userModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'USER_NAME',
						type : 'String'
					}, {
						name : 'STATE',
						type : 'String'
					}, {
						name : 'MOBILE',
						type : 'String'
					}, {
						name : 'EMAIL',
						type : 'String'
					}, {
						name : 'ADDRESS_INFO',
						type : 'String'
					}, {
						name : 'CREATE_TIME',
						type : 'Date'
					}, {
						name : 'CUSTOMER_FULL_NAME',
						type : 'String'
					}, {
						name : 'UKEY_NO',
						type : 'String'
					}, {
						name : 'IS_ADMIN',
						type : 'String'
					}, {
						name : 'IS_ADMIN_ORG',
						type : 'String'
					}, {
						name : 'BAND_IP',
						type : 'String'
					}, {
						name : 'ORG_FULL_NAME',
						type : 'String'
					}, {
						name : 'IS_NEW_REG',
						type : 'String'
					}, {
						name : 'ID_ORG',
						type : 'String'
					}, {
						name : 'ID_CUSTOMER',
						type : 'String'
					}]
		})