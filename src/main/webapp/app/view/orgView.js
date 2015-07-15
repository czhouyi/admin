/**
 * index.view.org by hupeng
 */

var customerFullNameStore = Ext.create('Ext.data.Store', {
			fields : ['ID', 'FULL_NAME'],
			autoLoad : true,
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

var orgTypeStores = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : '0',
						'name' : '自用'
					}, {
						'value' : '1',
						'name' : '代理商'
					}, {
						'value' : '2',
						'name' : '外部客户'
					}]
		});

var aOrgFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_org.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						xtype : 'combo',
						fieldLabel : '客户编号',
						name : 'ID_CUSTOMER',
						allowBlank : false,
						validateBlank : true,
						emptyText : '请选择',
						store : customerFullNameStore,
						triggerAction : 'all',
						typeAhead: true,
						displayField : 'FULL_NAME',
						valueField : 'ID'
					}, {
						fieldLabel : '机构简称',
						name : 'BRIEF_NAME',
						allowBlank : false
					}, {
						fieldLabel : '机构全称',
						name : 'FULL_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '机构类型',
						anchor : '100%',
						name : 'ORG_TYPE',
						store : orgTypeStores,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}]

		});

var aOrgW = Ext.create('Ext.window.Window', {
			items : aOrgFP,
			modal : true,
			close : function() {
				this.hide();
				aOrgFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aOrgFP.getForm();
					index.util.operator.submitForm(form, aOrgW, Ext
									.widget('orgView').getStore());
				}
			}]
		});

var uOrgFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_org.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						name : 'ID',
						hidden : true
					}, {
						xtype : 'combo',
						fieldLabel : '客户编号',
						name : 'ID_CUSTOMER',
						allowBlank : false,
						validateBlank : true,
						emptyText : '请选择',
						store : customerFullNameStore,
						triggerAction : 'all',
						typeAhead: true,
						displayField : 'FULL_NAME',
						valueField : 'ID'
					}, {
						fieldLabel : '机构简称',
						name : 'BRIEF_NAME',
						allowBlank : false
					}, {
						fieldLabel : '机构全称',
						name : 'FULL_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '机构类型',
						anchor : '100%',
						name : 'ORG_TYPE',
						store : orgTypeStores,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}]

		});

var uOrgW = Ext.create('Ext.window.Window', {
			items : uOrgFP,
			modal : true,
			close : function() {
				this.hide();
				uOrgFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uOrgFP.getForm();
					index.util.operator.submitForm(form, uOrgW, Ext
									.widget('orgView').getStore());
				}
			}]
		});

var render = Ext.create('index.util.DataRender', {});

var columns = [{
			header : '机构编号',
			dataIndex : 'ID'
		}, {
			header : '客户编号',
			dataIndex : 'ID_CUSTOMER'
		}, {
			header : '机构简称',
			dataIndex : 'BRIEF_NAME'
		}, {
			header : '机构全称',
			dataIndex : 'FULL_NAME'
		}, {
			header : '机构类型',
			dataIndex : 'ORG_TYPE',
			renderer : render.orgTypeRender
		}, {
			header : '创建时间',
			dataIndex : 'CREATE_TIME',
			renderer : render.dateRender
		}];

Ext.define('index.view.orgView', {
			extend : 'Ext.grid.GridPanel',
			title : '机构管理',
			alias : 'widget.orgView',
			columns : columns,
			store : 'orgStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'orgStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						defaultType : 'button',
						items : [{
									text : '新增机构',
									action : 'add'
								}, {
									text : '删除机构',
									hidden : true,
									action : 'delete'
								}, {
									xtype : 'textfield',
									emptyText : '机构名称',
									name : 'orgName',
									enableKeyEvents : true
								}, {
									xtype : 'textfield',
									emptyText : '客户正式名称查询',
									name : 'customerName',
									enableKeyEvents : true
								}]
					}]
		});
