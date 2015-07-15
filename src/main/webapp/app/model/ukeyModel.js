/**
 * 
 */
Ext.define("index.model.ukeyModel", {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'UKEY_NO',
						type : 'String'
					}, {
						name : 'PIN',
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
						name : 'PUBLIC_KEY',
						type : 'String'
					}, {
						name : 'CREATE_TIME',
						type : 'Date'
					}, {
						name : 'USER_NAME',
						type : 'String'
					}, {
						name : 'IS_FREE',
						type : 'String'
					}, {
						name : 'STATE',
						type : 'String'
					}]
		});