/**
 * index.view.customer by hupeng
 */

var contractTypeStores = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : '0',
						'name' : '试用协议'
					}, {
						'value' : '1',
						'name' : '正式合同'
					}, {
						'value' : '2',
						'name' : '自用'
					}]
		});

var states = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						"value" : "0",
						"name" : "正常"
					}, {
						"value" : "1",
						"name" : "人工停用"
					}, {
						"value" : "2",
						"name" : "系统停用"
					}]
		});

var stateSales = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						"value" : "0",
						"name" : "正式执行中"
					}, {
						"value" : "1",
						"name" : "决定合同签署"
					}, {
						"value" : "2",
						"name" : "提交商务方案"
					}, {
						"value" : "3",
						"name" : "进入采集阶段"
					}, {
						"value" : "4",
						"name" : "使用阶段"
					}]
		});

var aCustomerFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_customer.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '客户名称',
						name : 'FULL_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '客户状态',
						anchor : '100%',
						name : 'STATE',
						store : states,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '商务销售状态',
						name : 'STATE_SALE',
						editable : false,
						store : stateSales,
						displayField : 'name',
						validateBlank : true,
						emptyText : '请选择',
						valueField : 'value',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '合同类别',
						name : 'CONTRACT_TYPE',
						store : contractTypeStores,
						editable : false,
						displayField : 'name',
						validateBlank : true,
						emptyText : '请选择',
						valueField : 'value',
						allowBlank : false,
						listeners : {
							change : function(t, newValue, oldValue) {
								var $effect_date = Ext
										.getCmp('ADD_CONTRACT_EFFECT_DATE');
								if (newValue == 1) {
									$effect_date.allowBlank = false;
								} else {
									$effect_date.allowBlank = true;
								}
								uCustomerFP.doLayout();
							}
						}
					}, {
						fieldLabel : '计费开始日期',
						id : 'ADD_CONTRACT_EFFECT_DATE',
						name : 'EFFECT_DATE',
						xtype : 'datefield',
						format : 'Y-m-d',
						minValue : '2012-01-01'
					}, {
						fieldLabel : '合同有效期',
						name : 'LOSE_DATE',
						xtype : 'datefield',
						format : 'Y-m-d',
						minValue : '2012-01-01'
					}]

		});

var aCustomerW = Ext.create('Ext.window.Window', {
			items : aCustomerFP,
			modal : true,
			close : function() {
				this.hide();
				aCustomerFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aCustomerFP.getForm();
					index.util.operator.submitForm(form, aCustomerW, Ext
									.widget('customerView').getStore());
				}
			}]
		});

var uCustomerFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_customer.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '客户编号',
						name : 'ID',
						readOnly : true
					}, {
						fieldLabel : '客户名称',
						name : 'FULL_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '客户状态',
						anchor : '100%',
						name : 'STATE',
						store : states,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}, {
						fieldLabel : '客户ip地址',
						name : 'IP',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '合同类别',
						name : 'CONTRACT_TYPE',
						store : contractTypeStores,
						editable : false,
						displayField : 'name',
						validateBlank : true,
						emptyText : '请选择',
						valueField : 'value',
						allowBlank : false,
						listeners : {
							change : function(t, newValue, oldValue) {
								var $effect_date = Ext
										.getCmp('UPDATE_CONTRACT_EFFECT_DATE');
								if (newValue == 1) {
									$effect_date.allowBlank = false;
								} else {
									$effect_date.allowBlank = true;
								}
								uCustomerFP.doLayout();
							}
						}
					}, {
						fieldLabel : '计费开始日期',
						id : 'UPDATE_CONTRACT_EFFECT_DATE',
						name : 'EFFECT_DATE',
						xtype : 'datefield',
						format : 'Y-m-d',
						minValue : '2012-01-01'
					}, {
						fieldLabel : '合同有效期',
						name : 'LOSE_DATE',
						xtype : 'datefield',
						format : 'Y-m-d',
						minValue : '2012-01-01'
					}]

		});

var uCustomerW = Ext.create('Ext.window.Window', {
			items : uCustomerFP,
			modal : true,
			close : function() {
				this.hide();
				uCustomerFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uCustomerFP.getForm();
					index.util.operator.submitForm(form, uCustomerW, Ext
									.widget('customerView').getStore());
				}
			}]
		});

var render = Ext.create('index.util.DataRender', {});

var columns = [{
			header : '客户编号',
			dataIndex : 'ID'
		}, {
			header : '客户全称',
			dataIndex : 'FULL_NAME'
		}, {
			header : '客户状态',
			dataIndex : 'STATE',
			renderer : render.customerStatusRender
		}, {
			header : 'IP',
			dataIndex : 'IP'
		}, {
			header : '创建时间',
			dataIndex : 'CREATE_TIME',
			renderer : render.dateRender
		}, {
			header : '合同类型',
			dataIndex : 'CONTRACT_TYPE',
			renderer : render.contractTypeRender
		}, {
			header : '计费开始日期',
			dataIndex : 'EFFECT_DATE',
			renderer : render.dateRender
		}, {
			header : '合同有效期',
			dataIndex : 'LOSE_DATE',
			renderer : render.dateRender
		}];

Ext.define('index.view.customerView', {
			extend : 'Ext.grid.GridPanel',
			title : '客户管理',
			alias : 'widget.customerView',
			columns : columns,
			store : 'customerStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'customerStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						defaultType : 'button',
						items : [{
									text : '新增客户',
									action : 'add'
								}, {
									text : '删除客户',
									hidden : true,
									action : 'delete'
								}, {
									xtype : 'textfield',
									emptyText : '客户正式名称查询',
									name : 'customerName',
									enableKeyEvents : true
								}]
					}]
		});
