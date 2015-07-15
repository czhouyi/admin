/**
 * index.view.user by hupeng
 */



var newRegStore = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : '1',
						'name' : '老用户'
					}, {
						'value' : '0',
						'name' : '新用户'
					}]

		});

var orgFullNameStore = Ext.create('Ext.data.Store', {
			fields : ['ID', 'FULL_NAME'],
			autoLoad : true,
			proxy : {
				type : 'ajax',
				url : 'select_org.action',
				reader : {
					type : 'json',
					root : 'result',
					totalProperty : 'totalCount',
					idProperty : 'id'
				},
				actionMethods:{
            		create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
				}
			}

		});

var ukeyNoStore = Ext.create('Ext.data.Store', {
			fields : ['ID', 'UKEY_NO'],
			proxy : {
				type : 'ajax',
				url : 'select_ukey.action',
				reader : {
					type : 'json',
					root : 'result',
					totalProperty : 'totalCount',
					idProperty : 'id'
				},
				actionMethods:{
            		create: 'POST', read: 'POST', update: 'POST', destroy: 'POST'
				}
			}

		});

var aUserFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_user.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '用户名',
						name : 'USER_NAME'
					}, {
						xtype : 'combo',
						fieldLabel : '机构名称',
						name : 'ID_ORG',
						validateBlank : true,
						emptyText : '请选择',
						store : orgFullNameStore,
						triggerAction : 'all',
						typeAhead: true,
						displayField : 'FULL_NAME',
						valueField : 'ID',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : 'Ukey编号',
						name : 'UKEY_ID',
						validateBlank : true,
						emptyText : '请选择',
						triggerAction : 'all',
						typeAhead: true,
						store : ukeyNoStore,
						displayField : 'UKEY_NO',
						valueField : 'ID',
						allowBlank : false
					}, {
						fieldLabel : '手机',
						name : 'MOBILE'
					}, {
						fieldLabel : '地址',
						name : 'ADDRESS_INFO'
					}, {
						fieldLabel : '邮箱',
						vtype : 'email',
						name : 'EMAIL'
					}]

		});

var aUserW = Ext.create('Ext.window.Window', {
			items : aUserFP,
			modal : true,
			close : function() {
				this.hide();
				aUserFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aUserFP.getForm();
					index.util.operator.submitForm(form, aUserW, Ext
									.widget('userView').getStore());
				}
			}]
		});

var uUserFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_user.action',

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
						fieldLabel : '用户名',
						name : 'USER_NAME'
					}, {
						xtype : 'combo',
						fieldLabel : '机构名称',
						name : 'ID_ORG',
						validateBlank : true,
						emptyText : '请选择',
						store : orgFullNameStore,
						typeAhead : true,
						typeAheadDelay : 0,
						triggerAction : 'all',
						displayField : 'FULL_NAME',
						valueField : 'ID',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '注册状态',
						anchor : '100%',
						name : 'IS_NEW_REG',
						store : newRegStore,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}, {
						fieldLabel : '绑定IP',
						name : 'BAND_IP'
					}]

		});

var uUserW = Ext.create('Ext.window.Window', {
			items : uUserFP,
			modal : true,
			close : function() {
				this.hide();
				uUserFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uUserFP.getForm();
					index.util.operator.submitForm(form, uUserW, Ext
									.widget('userView').getStore());

				}
			}]
		});

var assignUserTreeStore = Ext.create('Ext.data.TreeStore', {

});

var assignUserTreePanel = Ext.create('Ext.tree.Panel', {
			width : 250,
			height : 300,
			autoScroll : true,
			store : assignUserTreeStore,
			rootVisible : false
		});

var assignUserW = Ext.create('Ext.window.Window', {
			items : assignUserTreePanel,
			title : '产品实例选择',
			close : function() {
				this.hide();
			},

			// Reset and Submit buttons
			buttons : [{
						text : '提交',
						formBind : true,
						id : 'btn_assignUser'
					}]
		});

var render = Ext.create('index.util.DataRender', {});

var columns = [{
			header : '用户编号',
			dataIndex : 'ID'
		}, {
			header : '客户全称',
			dataIndex : 'CUSTOMER_FULL_NAME'
		}, {
			header : '机构全称',
			dataIndex : 'ORG_FULL_NAME'
		}, {
			header : '用户名',
			dataIndex : 'USER_NAME'
		}, {
			header : 'Ukey编号',
			dataIndex : 'UKEY_NO'
		}, {
			header : '用户状态',
			dataIndex : 'STATE',
			renderer : render.userStatusRender
		}, {
			header : '绑定IP',
			dataIndex : 'BAND_IP'
		}, {
			header : '注册状态',
			dataIndex : 'IS_NEW_REG',
			renderer : render.regStatusRender
		}, {
			header : '手机号码',
			dataIndex : 'MOBILE'
		}, {
			header : '电子邮件',
			dataIndex : 'EMAIL'
		}, {
			header : '地址',
			dataIndex : 'ADDRESS_INFO'
		}, {
			header : '创建时间',
			dataIndex : 'CREATE_TIME',
			renderer : render.dateRender
		}];

Ext.define('index.view.userView', {
			extend : 'Ext.grid.GridPanel',
			title : '用户管理',
			alias : 'widget.userView',
			columns : columns,
			store : 'userStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'userStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [{
									xtype : 'button',
									text : '新增用户',
									action : 'add'
								}, {
									xtype : 'button',
									text : '删除用户',
									hidden : true,
									action : 'delete'
								}, {
									xtype : 'button',
									text : '分配产品实例',
									action : 'assign',
									hidden : true
								}, {
									xtype : 'textfield',
									emptyText : '用户名',
									enableKeyEvents : true,
									name : 'username'
								}, {
									xtype : 'textfield',
									emptyText : '客户名称',
									enableKeyEvents : true,
									name : 'customerName'
								}, {
									xtype : 'textfield',
									emptyText : 'Ukey编号',
									enableKeyEvents : true,
									name : 'ukeyNo'
								}]
					}]
		});