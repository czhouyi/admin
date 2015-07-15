/**
 * 
 */
Ext.define("index.model.productTplModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'ID',
						type : 'String'
					}, {
						name : 'PRODUCT_TPL_NAME',
						type : 'String'
					}, {
						name : 'CREATE_TIME',
						type : 'Date'
					}]
		});