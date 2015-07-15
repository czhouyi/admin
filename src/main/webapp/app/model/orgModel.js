/**
 * 
 */
Ext.define("index.model.orgModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'ID_CUSTOMER',
						type : 'String'
					}, {
						name : 'BRIEF_NAME',
						type : 'String'
					}, {
						name : 'FULL_NAME',
						type : 'String'
					}, {
						name : 'ORG_TYPE',
						type : 'String'
					}, {
						name : 'CREATE_TIME',
						type : 'Date'
					}]
		});