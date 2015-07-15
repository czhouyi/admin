/**
 * 
 */
Ext.define("index.model.productOrderModel", {
			extend : 'Ext.data.Model',

			fields : [{
						name : 'PRODUCT_TPL_NAME',
						type : 'String'
					}, {
						name : 'UKEYNO',
						type : 'String'
					},{
						name : 'USER_NAME',
						type : 'String'
					}, {
						name : 'USERID',
						type : 'String'
					}, {
						name : 'POID',
						type : 'String'
					}, {
						name : 'TPLCODE',
						type : 'String'
					}, {
						name : 'ORDERLIMIT',
						type : 'String'
					}, {
						name : 'ORDERCNT',
						type : 'String'
					}]
		});