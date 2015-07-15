/**
 * 
 */
Ext.define("index.model.indexInfoModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'INFO_TYPE',
						type : 'String'
					}, {
						name : 'TITLE',
						type : 'String'
					}, {
						name : 'INFO_CONTENT',
						type : 'String'
					}, {
						name : 'STATUS',
						type : 'String'
					}, {
						name : 'START_TIME',
						type : 'Date'
					}, {
						name : 'END_TIME',
						type : 'Date'
					}]
		});