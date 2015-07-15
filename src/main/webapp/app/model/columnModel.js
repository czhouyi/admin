Ext.define("index.model.columnModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'CODE_COLUMN_GROUP',
						type : 'String'
					}, {
						name : 'COLUMN_GROUP_NAME',
						type : 'String'
					}, {
						name : 'COL_NAME',
						type : 'String'
					}, {
						name : 'COL_DESC',
						type : 'String'
					}, {
						name : 'ORDER_NUM',
						type : 'String'
					}, {
						name : 'STATE',
						type : 'String'
					}, {
						name : 'UDT',
						type : 'Date'
					}, {
						name : 'NODE_NAME',
						type : 'String'
					}, {
						name : 'DEFAULT_USE_TYPE',
						type : 'String'
					}]
		})