/**
 * index.view.productOrder by hupeng
 */

var customerFullNameStore = Ext.create('Ext.data.Store', {
			fields : ['ID', 'FULL_NAME'],
			proxy : {
				type : 'ajax',
				url : 'select_customer.action',
				reader : {
					type : 'json',
					root : 'result',
					totalProperty : 'totalCount',
					idProperty : 'ID'
				},
				actionMethods:{
            		create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
				}
			}
		});

var productTplStore = Ext.create('Ext.data.Store', {
			fields : ['TPLCODE', 'PRODUCT_TPL_NAME'],
			proxy : {
				type : 'ajax',
				url : 'select_productTpl.action',
				reader : {
					type : 'json',
					root : 'result',
					totalProperty : 'totalCount',
					idProperty : 'TPLCODE'
				},
				actionMethods:{
            		create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
				}
			}
		});

var aProductOrderFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_productOrder.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			
			// The fields
			defaultType : 'textfield',
			items : [{
						xtype : 'combo',
						fieldLabel : '产品模板',
						name : 'TPLCODE',
						allowBlank : false,
						validateBlank : true,
						emptyText : '请选择',
						store : productTplStore,
						triggerAction : 'all',
						typeAhead: true,
						displayField : 'PRODUCT_TPL_NAME',
						valueField : 'TPLCODE'
					}, {
						fieldLabel : 'Ukey编号',
						allowBlank : false,
						name : 'UKEYNO'
					}, {
						fieldLabel : '查询数量',
						allowBlank : false,
						name : 'ORDERLIMIT'
					}
//						{
//						xtype : 'combo',
//						fieldLabel : '客户名称',
//						name : 'ID_CUSTOMER',
//						allowBlank : false,
//						validateBlank : true,
//						emptyText : '请选择',
//						store : customerFullNameStore,
//						triggerAction : 'all',
//						typeAhead: true,
//						displayField : 'FULL_NAME',
//						valueField : 'ID'
//					}
					]

		});

var aProductOrderW = Ext.create('Ext.window.Window', {
			items : aProductOrderFP,
			modal : true,
			close : function() {
				this.hide();
				aProductOrderFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aProductOrderFP.getForm();
					index.util.operator.submitForm(form, aProductOrderW, Ext
									.widget('productOrderView').getStore());
				}
			}]
		});

var uProductOrderFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_productOrder.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '产品模板编号',
						name : 'POID',
						readOnly : true
					}, {
						fieldLabel : '用户编号',
						name : 'USERID',
						readOnly : true
					}, {
						fieldLabel : '订单查询量',
						allowBlank : false,
						name : 'ORDERCNT',
						readOnly : true
					}, {
						fieldLabel : '订单限制量',
						allowBlank : false,
						name : 'ORDERLIMIT'
					}]

		});

var uProductOrderW = Ext.create('Ext.window.Window', {
			items : uProductOrderFP,
			modal : true,
			close : function() {
				this.hide();
				uProductOrderFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uProductOrderFP.getForm();
					index.util.operator.submitForm(form, uProductOrderW, Ext
									.widget('productOrderView').getStore());
				}
			}]
		});

Ext.define('columnTreeModel', {
			extend : 'Ext.data.Model',
			fields : [{
						name : 'cgcode',
						type : 'String'
					}, {
						name : 'colid',
						type : 'String'
					}, {
						name : 'cgname',
						type : 'String'
					}, {
						name : 'colname',
						type : 'String'
					}, {
						name : 'func_code',
						type : 'String'
					}, {
						name : 'default_use_typ',
						type : 'String'
					}, {
						name : 'grant_typ',
						type : 'String'
					}, {
						name : 'revoke_time',
						type : 'Date'
					}, {
						name : 'checked',
						type : 'Boolean'
					}]
		});

var columnGroupTreeStore = Ext.create('Ext.data.Store', {
			model : columnTreeModel,
			proxy : {
				type : 'ajax',
				url : 'tree_column.action',
				reader : {
					type : 'json',
					root : 'result'
				},
				actionMethods:{
            		create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
				}
			}
		});

var cGcolumns = [{
			header : '字段组别名称',
			dataIndex : 'cgname',
			filter : {
				type : 'string'
			},
			flex : 1
		}, {
			header : '字段名称',
			dataIndex : 'colname',
			flex : 1
		}, {
			header : '列分类',
			dataIndex : 'func_code',
			flex : 1,
			renderer : function(value) {
				if (value == 'enterprise') {
					return '按企业查询';
				} if (value == 'person') {
					return '按人员查询'; 
				}
			}
		}, {
			header : '默认类型',
			dataIndex : 'default_use_typ',
			flex : 1,
			renderer : function(value) {
				if (value == 1) {
					return '仅中数内部开发';
				} else if (value == 2) {
					return '所有用户免费开放';
				} else if (value == 3) {
					return '收费开放';
				} else if (value == 4) {
					return '不开放';
				}
			}
		}, {
			header : '字段类型',
			dataIndex : 'grant_typ',
			flex : 1,
			renderer : function(value) {
				if (value == 1) {
					return '正式配置';
				} else if (value == 2) {
					return '新增——NEW';
				} else if (value == 3) {
					return '新增——试用';
				} else if (value == '' || value == null) {
					value = 1;
					return '正式配置';
				}
			},
			editor : new Ext.form.field.ComboBox({
						typeAhead : true,
						triggerAction : 'all',
						emptyText : '请选择',
						valueField : 'value',
						displayField : 'name',
						store : Ext.create('Ext.data.Store', {
									fields : ['value', 'name'],
									data : [{
												'value' : '1',
												'name' : '正式配置'
											}, {
												'value' : '2',
												'name' : '新增——NEW'
											}, {
												'value' : '3',
												'name' : '新增——试用'
											}]
								})
					})
		}, {
			header : '回收时间',
			dataIndex : 'revoke_time',
			flex : 1,
			renderer : function(value) {
				if (value == '' || value == null) {
					value = new Date(new Date().getTime() + 14*24*60*60*1000);
				}
				return Ext.Date.format(value, 'Y-m-d');
			},
			editor : {
				xtype : 'datefield',
				format : 'Y-m-d',
				emptyText : '请选择',
				minValue : '2012-01-01'
			}
		}, {
			xtype : 'checkcolumn',
			header : '',
			dataIndex : 'checked',
			width : 90,
			stopSelection : false
		}];

var filters = {
	ftype : 'filters',
	// encode and local configuration options defined previously for easier reuse
	local : true, // defaults to false (remote filtering)

	// Filters are most naturally placed in the column definition, but can also be
	// added here.
	filters : [{
				type : 'boolean',
				dataIndex : 'visible'
			}]
};

var columnGroupGrid = Ext.create('Ext.grid.GridPanel', {
			title : '字段选项',
			height : 400,
			autoScroll : true,
			region : 'center',
			store : columnGroupTreeStore,
			features: [filters],
			columns : cGcolumns,
			flex : 1,
			selModel : {
				selType : 'cellmodel'
			},
			plugins : [Ext.create('Ext.grid.plugin.CellEditing', {
						clicksToEdit : 1
					})],
			emptyText: '找不到相关数据',
			dockedItems : {
				xtype : 'toolbar',
				dock : 'top',
				items : ['->',{
					xtype : 'button',
					action : 'selectAll',
					text : '全选',
					handler : function() {
						var storeTmp = columnGroupGrid.getView().getStore();
						for (var i = 0; i < storeTmp.getCount(); i++) {
							storeTmp.getAt(i).set("checked", true);		
						}
					}
				},{
					xtype : 'button',
					text : '反选',
					action : 'deselectAll',
					handler : function() {
						var nodes = columnGroupGrid.getView().getStore();
						for (var i = 0; i < nodes.getCount(); i++) {
							nodes.getAt(i).set("checked", false);		
						}
					}
				}]
			}
		})

var roleFormPanel = Ext.create('Ext.form.Panel', {
	title : '菜单选项',
	region : 'north',
	height : 80,
	layout:'column' 
});
		
var assignProductOrderW = Ext.create('Ext.window.Window', {
			items : [
				roleFormPanel,
				columnGroupGrid
				],
			modal : true,
			layout : 'border',
			height : 600,
			width : 900,
			close : function() {
				this.hide();
			},
			buttons : [{
						text : '提交',
						action : 'submit',
						formBind : true
					}]
		});

var columns = [{
			header : '产品名称',
			dataIndex : 'PRODUCT_TPL_NAME'
		}, {
			header : 'Ukey编号',
			dataIndex : 'UKEYNO'
		}, {
			header : '用户名称',
			dataIndex : 'USER_NAME'
		}, {
			header : '用户编号',
			dataIndex : 'USERID'
		}, {
			header : '产品实例编号',
			dataIndex : 'POID'
		}, {
			header : '产品模板编号',
			dataIndex : 'TPLCODE'
		}, {
			header : "订单限制量",
			dataIndex : "ORDERLIMIT"
		}, {
			header : "订单查询量",
			dataIndex : "ORDERCNT"
		}];

Ext.define('index.view.productOrderView', {
			extend : 'Ext.grid.GridPanel',
			title : '产品实例',
			alias : 'widget.productOrderView',
			columns : columns,
			store : 'productOrderStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'productOrderStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						defaultType : 'button',
						items : [{
									text : '新增产品实例',
									action : 'add'
								}, {
									text : '删除实例',
									action : 'delete',
									hidden : true
								}, {
									text : '分配角色与字段',
									action : 'assign'
								}, {
									xtype : 'textfield',
									emptyText : '用户名称',
									enableKeyEvents : true,
									name : 'userName'
								}, {
									xtype : 'textfield',
									emptyText : 'ukey编号',
									enableKeyEvents : true,
									name : 'ukeyNo'
								}]
					}]
		});
