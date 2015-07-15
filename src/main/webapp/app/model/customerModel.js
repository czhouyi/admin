Ext.define("index.model.customerModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'FULL_NAME',
						type : 'String'
					}, {
						name : 'STATE',
						type : 'String'
					}, {
						name : 'IP',
						type : 'String'
					}, {
						name : 'CREATE_TIME',
						type : 'Date'
					}, {
						name : 'CONTRACT_TYPE',
						type : 'String'
					}, {
						name : 'EFFECT_DATE',
						type : 'Date'
					}, {
						name : 'LOSE_DATE',
						type : 'Date'
					}]
		});