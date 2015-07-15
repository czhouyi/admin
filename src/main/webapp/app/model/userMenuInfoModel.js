/**
 * index.model.userMenuInfo by hupeng
 */

Ext.define("index.model.userMenuInfoModel", {
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
						name : 'ID_ROLE',
						type : 'String'
					}, {
						name : 'ROLE_NAME',
						type : 'String'
					}, {
						name : 'CNT_MENU',
						type : 'String'
					}, {
						name : 'CNT_P_TPL',
						type : 'String'
					}]
		})