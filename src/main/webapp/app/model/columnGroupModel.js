/**
 * 
 */
Ext.define("index.model.columnGroupModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'COLUMN_GROUP_NAME',
						type : 'String'
					}, {
						name : 'FUNC_CODE',
						type : 'String'
					}]
		});