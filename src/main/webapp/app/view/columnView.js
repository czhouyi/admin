/**
 * index.view.column by hupeng
 */

var columnStateStore = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : '1',
						'name' : '批量和单条'
					}, {
						'value' : '2',
						'name' : '仅单条和word下载'
					}, {
						'value' : '3',
						'name' : '仅批量Excel下载'
					}, {
						'value' : '-1',
						'name' : '全禁用'
					}]

		});

var defaultUseStore = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : '1',
						'name' : '仅中数内部开放'
					}, {
						'value' : '2',
						'name' : '所有用户免费开放'
					}, {
						'value' : '3',
						'name' : '收费开放'
					}, {
						'value' : '4',
						'name' : '不开放'
					}]

		});

var columnGroupStore = Ext.create('Ext.data.Store', {
			fields : ['CODE_COLUMN_GROUP', 'COLUMN_GROUP_NAME'],
			autoLoad : true,
			proxy : {
				type : 'ajax',
				url : 'select_columnGroup.action',
				reader : {
					type : 'json',
					root : 'result',
					totalProperty : 'totalCount',
					idProperty : 'CODE_COLUMN_GROUP'
				},
				actionMethods : {
					create : 'POST',
					read : 'POST',
					update : 'POST',
					destroy : 'POST'
				}
			}

		});

var aColumnFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_column.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						xtype : 'combo',
						fieldLabel : '所属组别名称',
						name : 'CODE_COLUMN_GROUP',
						validateBlank : true,
						emptyText : '请选择',
						store : columnGroupStore,
						typeAhead : true,
						typeAheadDelay : 0,
						triggerAction : 'all',
						displayField : 'COLUMN_GROUP_NAME',
						valueField : 'CODE_COLUMN_GROUP',
						editable : false,
						allowBlank : false
					}, {
						fieldLabel : '字段名称',
						name : 'COL_NAME',
						allowBlank : false
					}, {
						fieldLabel : '描述',
						name : 'COL_DESC',
						allowBlank : false
					}, {
						xtype : 'numberfield',
						step : 1,
						maxValue : 99999,
						decimalPrecision : 0,
						minValue : 0,
						fieldLabel : '排序序号',
						name : 'ORDER_NUM',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '字段状态',
						name : 'STATE',
						validateBlank : true,
						emptyText : '请选择',
						typeAhead : true,
						typeAheadDelay : 0,
						triggerAction : 'all',
						store : columnStateStore,
						displayField : 'name',
						valueField : 'value',
						editable : false,
						allowBlank : false
					}, {
						fieldLabel : '专线XML节点转换字段名',
						name : 'NODE_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '默认开放类型',
						name : 'DEFAULT_USE_TYPE',
						validateBlank : true,
						emptyText : '请选择',
						typeAhead : true,
						typeAheadDelay : 0,
						triggerAction : 'all',
						store : defaultUseStore,
						displayField : 'name',
						valueField : 'value',
						editable : false,
						allowBlank : false
					}]

		});

var aColumnW = Ext.create('Ext.window.Window', {
			items : aColumnFP,
			modal : true,
			close : function() {
				this.hide();
				aColumnFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aColumnFP.getForm();
					index.util.operator.submitForm(form, aColumnW, Ext
									.widget('columnView').getStore());
				}
			}]
		});

var uColumnFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_column.action',

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
						fieldLabel : '所属组别名称',
						name : 'COLUMN_GROUP_NAME',
						validateBlank : true,
						emptyText : '请选择',
						store : columnGroupStore,
						typeAhead : true,
						typeAheadDelay : 0,
						triggerAction : 'all',
						displayField : 'COLUMN_GROUP_NAME',
						valueField : 'CODE_COLUMN_GROUP',
						editable : false,
						allowBlank : false
					}, {
						fieldLabel : '字段名称',
						name : 'COL_NAME',
						allowBlank : false
					}, {
						fieldLabel : '描述',
						name : 'COL_DESC',
						allowBlank : false
					}, {
						xtype : 'numberfield',
						step : 1,
						maxValue : 99999,
						decimalPrecision : 0,
						minValue : 0,
						fieldLabel : '排序序号',
						name : 'ORDER_NUM',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '字段状态',
						name : 'STATE',
						validateBlank : true,
						emptyText : '请选择',
						typeAhead : true,
						typeAheadDelay : 0,
						triggerAction : 'all',
						store : columnStateStore,
						displayField : 'name',
						valueField : 'value',
						editable : false,
						allowBlank : false
					}, {
						fieldLabel : '专线XML节点转换字段名',
						name : 'NODE_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '默认开放类型',
						name : 'DEFAULT_USE_TYPE',
						validateBlank : true,
						emptyText : '请选择',
						typeAhead : true,
						typeAheadDelay : 0,
						triggerAction : 'all',
						store : defaultUseStore,
						displayField : 'name',
						valueField : 'value',
						editable : false,
						allowBlank : false
					}]

		});

var uColumnW = Ext.create('Ext.window.Window', {
			items : uColumnFP,
			modal : true,
			close : function() {
				this.hide();
				uColumnFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uColumnFP.getForm();
					index.util.operator.submitForm(form, uColumnW, Ext
									.widget('columnView').getStore());
				}
			}]
		});

var render = Ext.create('index.util.DataRender', {});

var columns = [{
			header : '字段编号',
			dataIndex : 'ID'
		}, {
			header : '所属组别名称',
			dataIndex : 'COLUMN_GROUP_NAME'
		}, {
			header : '名称',
			dataIndex : 'COL_NAME'
		}, {
			header : '描述',
			dataIndex : 'COL_DESC'
		}, {
			header : '排序序号',
			dataIndex : 'ORDER_NUM'
		}, {
			header : '字段状态',
			dataIndex : 'STATE',
			renderer : render.columnStatusRender
		}, {
			header : '变更时间',
			dataIndex : 'UDT',
			renderer : render.dateRender
		}, {
			header : '专线XML节点转换字段名',
			dataIndex : 'NODE_NAME'
		}, {
			header : '默认开放类型',
			dataIndex : 'DEFAULT_USE_TYPE',
			renderer : render.columnTypeRender
		}];

Ext.define('index.view.columnView', {
			extend : 'Ext.grid.GridPanel',
			title : '字段管理',
			alias : 'widget.columnView',
			columns : columns,
			store : 'columnStore',
			emptyText : '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'columnStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						items : [{
									xtype : 'button',
									text : '新增字段',
									action : 'add'
								}, {
									xtype : 'button',
									text : '删除字段',
									hidden : true,
									action : 'delete'
								}, {
									xtype : 'textfield',
									emptyText : '字段组别名称',
									enableKeyEvents : true,
									name : 'columnGroup'
								}]
					}]
		});