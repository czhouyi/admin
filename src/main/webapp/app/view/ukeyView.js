/**
 * index.view.ukey by hupeng
 */

var ukeyTypeStores = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						"value" : "1",
						"name" : "正式-收工本费"
					}, {
						"value" : "2",
						"name" : "正式-免工本费"
					}, {
						"value" : "3",
						"name" : "正式-专线虚拟"
					}, {
						"value" : "4",
						"name" : "试用-专线虚拟"
					}, {
						"value" : "5",
						"name" : "试用-外网虚拟"
					}, {
						"value" : "6",
						"name" : "自服务-虚拟"
					}]
		});

var adminStores = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						"value" : "0",
						"name" : "不是"
					}, {
						"value" : "1",
						"name" : "是"
					}]
		});
var orgAdminStores = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						"value" : "0",
						"name" : "不是"
					}, {
						"value" : "1",
						"name" : "是"
					}]
		});

var stateStores = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						"value" : "0",
						"name" : "失效"
					}, {
						"value" : "1",
						"name" : "有效"
					}]
		});

var aUkeyFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_ukey.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : 'UID',
						name : 'ID',
						allowBlank : false
					}, {
						fieldLabel : 'CA',
						name : 'PUBLIC_KEY',
						allowBlank : false
					}, {
						fieldLabel : 'Ukey编号',
						name : 'UKEY_NO',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : 'Ukey类别',
						anchor : '100%',
						name : 'IS_FREE',
						store : ukeyTypeStores,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '是否客户管理员',
						name : 'IS_ADMIN',
						editable : false,
						store : adminStores,
						displayField : 'name',
						validateBlank : true,
						emptyText : '请选择',
						valueField : 'value',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '是否机构管理员',
						name : 'IS_ADMIN_ORG',
						editable : false,
						store : orgAdminStores,
						displayField : 'name',
						validateBlank : true,
						emptyText : '请选择',
						valueField : 'value',
						allowBlank : false
					}, {
						xtype : 'textarea',
						fieldLabel : '绑定IP',
						name : 'BAND_IP',
						allowBlank : false
					}]

		});

var aUkeyW = Ext.create('Ext.window.Window', {
			items : aUkeyFP,
			modal : true,
			close : function() {
				this.hide();
				aUkeyFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '读取Ukey信息',
				handler : function() {
					var uid = getUID();
					if (uid) {
						var cert = getCert(1);
						aUkeyFP.getForm().findField('ID').setValue(uid);
						aUkeyFP.getForm().findField('PUBLIC_KEY')
								.setValue(cert);
					} else {
						Ext.Msg.alert('Failed', '请插入Ukey');
					}
				}
			}, {
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aUkeyFP.getForm();
					index.util.operator.submitForm(form, aUkeyW, Ext
									.widget('ukeyView').getStore());
				}
			}]
		});

var uUkeyFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_ukey.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : 'UID',
						name : 'ID',
						readOnly : true
					}, {
						fieldLabel : 'Ukey编号',
						name : 'UKEY_NO',
						readOnly : true
					}, {
						xtype : 'combo',
						fieldLabel : 'Ukey状态',
						anchor : '100%',
						name : 'STATE',
						store : stateStores,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}, {
						xtype : 'textarea',
						fieldLabel : '绑定IP',
						name : 'BAND_IP',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '是否客户管理员',
						name : 'IS_ADMIN',
						editable : false,
						store : adminStores,
						displayField : 'name',
						validateBlank : true,
						emptyText : '请选择',
						valueField : 'value',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '是否机构管理员',
						name : 'IS_ADMIN_ORG',
						editable : false,
						store : orgAdminStores,
						displayField : 'name',
						validateBlank : true,
						emptyText : '请选择',
						valueField : 'value',
						allowBlank : false
					}]

		});

var uUkeyW = Ext.create('Ext.window.Window', {
			items : uUkeyFP,
			modal : true,
			close : function() {
				this.hide();
				uUkeyFP.getForm().reset();
			},
			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uUkeyFP.getForm();
					index.util.operator.submitForm(form, uUkeyW, Ext
									.widget('ukeyView').getStore());
				}
			}]
		});
		
var mUkeyFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'assign_ukey.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},
			defaultType : 'textarea',
			items : [{
				fieldLabel : 'Ukey',
				name : 'UKEY',
				allowBlank : false
			}, {
				fieldLabel : '绑定IP',
				name : 'BANDIP',
				allowBlank : false
			}]
});
		
var mUkeyW = Ext.create('Ext.window.Window', {
			items : mUkeyFP,
			modal : true,
			close : function() {
				this.hide();
				mUkeyFP.getForm().reset();
			},
				// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = mUkeyFP.getForm();
					index.util.operator.submitForm(form, mUkeyW, Ext
									.widget('ukeyView').getStore());
				}
			}]
});

var render = Ext.create('index.util.DataRender', {});

var columns = [{
			header : 'UID',
			dataIndex : 'ID'
		}, {
			header : 'Ukey编号',
			dataIndex : 'UKEY_NO'
		}, {
			header : 'CA',
			dataIndex : 'PUBLIC_KEY'
		}, {
			header : '绑定IP',
			dataIndex : 'BAND_IP'
		}, {
			header : '用户名称',
			dataIndex : 'USER_NAME'
		}, {
			header : 'Ukey类别',
			dataIndex : 'IS_FREE',
			renderer : render.ukeyTypeRender
		}, {
			header : 'Ukey是否有效',
			dataIndex : 'STATE',
			renderer : render.booleanRender
		}, {
			header : '是否机构管理员',
			dataIndex : 'IS_ADMIN_ORG',
			renderer : render.booleanRender
		}, {
			header : '是否客户管理员',
			dataIndex : 'IS_ADMIN',
			renderer : render.booleanRender
		}, {
			header : '创建时间',
			dataIndex : 'CREATE_TIME',
			renderer : render.dateRender
		}];

Ext.define('index.view.ukeyView', {
			extend : 'Ext.grid.GridPanel',
			title : 'Ukey管理',
			alias : 'widget.ukeyView',
			columns : columns,
			store : 'ukeyStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'ukeyStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						defaultType : 'button',
						items : [{
									text : '新增Ukey',
									action : 'add'
								},{
									text : '批量修改IP',
									action : 'modify'
								}, {
									text : '删除Ukey',
									hidden : true,
									action : 'delete'
								}, {
									xtype : 'textfield',
									emptyText : 'Ukey编号',
									enableKeyEvents : true,
									name : 'ukeyNo'
								}]
					}]
		});
