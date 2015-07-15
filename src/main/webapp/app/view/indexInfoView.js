/**
 * index.view.indexInfo by hupeng
 */

var statusStore = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : '0',
						'name' : '隐藏'
					}, {
						'value' : '1',
						'name' : '显示'
					}]

		});

var typeStore = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : '1',
						'name' : '产品推广'
					}, {
						'value' : '2',
						'name' : '公告'
					}, {
						'value' : '3',
						'name' : '系统介绍'
					}, {
						'value' : '4',
						'name' : '客服电话'
					}]

		});

var aIndexInfoFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_indexInfo.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '类型',
						name : 'INFO_TYPE',
						xtype : 'combo',
						validateBlank : true,
						emptyText : '请选择',
						store : typeStore,
						triggerAction : 'all',
						typeAhead : true,
						displayField : 'name',
						valueField : 'value',
						editable : false,
						allowBlank : false
					}, {
						fieldLabel : '标题',
						name : 'TITLE',
						allowBlank : false
					}, {
						fieldLabel : '状态',
						name : 'STATUS',
						xtype : 'combo',
						validateBlank : true,
						emptyText : '请选择',
						store : statusStore,
						editable : false,
						triggerAction : 'all',
						typeAhead : true,
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}
//					,{
//						
//						fieldLabel : '内容',
//						name : 'INFO_CONTENT',
//					  	xtype: 'extKindEditor',
//					  	height : 400
//					}
					]

		});

var aIndexInfoW = Ext.create('Ext.window.Window', {
			items : aIndexInfoFP,
			modal : true,
			close : function() {
				this.hide();
				aIndexInfoFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aIndexInfoFP.getForm();
					index.util.operator.submitForm(form, aIndexInfoW, Ext
									.widget('indexInfoView').getStore());

				}
			}]
		});

var uIndexInfoFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 800,
			// The form will submit an AJAX request to this URL when submitted
			url : 'update_indexInfo.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '首页信息编号',
						name : 'ID',
						allowBlank : false,
						readOnly : true
					}, {
						fieldLabel : '类型',
						name : 'INFO_TYPE',
						xtype : 'combo',
						validateBlank : true,
						emptyText : '请选择',
						store : typeStore,
						triggerAction : 'all',
						typeAhead : true,
						editable : false,
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}, {
						fieldLabel : '标题',
						name : 'TITLE',
						allowBlank : false
					}, {
						fieldLabel : '状态',
						name : 'STATUS',
						xtype : 'combo',
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						store : statusStore,
						triggerAction : 'all',
						typeAhead : true,
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}, {
						fieldLabel : '内容',
						name : 'INFO_CONTENT',
					  	xtype: 'extKindEditor',
					  	height : 400
					}]

		});

var uIndexInfoW = Ext.create('Ext.window.Window', {
			items : uIndexInfoFP,
			modal : true,
			close : function() {
				this.hide();
				uIndexInfoFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uIndexInfoFP.getForm();
					index.util.operator.submitForm(form, uIndexInfoW, Ext
									.widget('indexInfoView').getStore());
				}
			}]
		});

var render = Ext.create('index.util.DataRender', {});

var columns = [{
			header : '编号',
			dataIndex : 'ID'
		}, {
			header : '类型',
			dataIndex : 'INFO_TYPE',
			renderer : function(value) {
				if (value == '1') {
					return '产品推广';
				} else if (value == '2') {
					return '公告';
				} else if (value == '3') {
					return '系统介绍';
				} else if (value == '4') {
					return '客服电话';
				}
			}
		}, {
			header : '标题',
			dataIndex : 'TITLE'
		}, {
			header : '状态',
			dataIndex : 'STATUS',
			renderer : function(value) {
				if (value == '0') {
					return '隐藏';
				} else if (value == '1') {
					return '显示';
				}
			}
		}, {
			header : '开始时间',
			dataIndex : 'START_TIME',
			renderer : render.dateRender
		}, {
			header : '结束时间',
			dataIndex : 'END_TIME',
			renderer : render.dateRender
		}, {
			header : '内容',
			dataIndex : 'INFO_CONTENT',
			hidden : true
		}];

Ext.define('index.view.indexInfoView', {
			extend : 'Ext.grid.GridPanel',
			title : '产品模板',
			alias : 'widget.indexInfoView',
			columns : columns,
			store : 'indexInfoStore',
			emptyText : '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'indexInfoStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						defaultType : 'button',
						items : [{
									text : '新增首页内容',
									action : 'add'
								}, {
									text : '删除模板',
									// hidden : true,
									action : 'delete'
								}]
					}]
		});
