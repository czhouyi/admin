/**
 * index.view.columnGroup by hupeng
 */


var funcCodeStores = Ext.create('Ext.data.Store', {
			fields : ['value', 'name'],
			data : [{
						'value' : 'enterprise',
						'name' : '按企业查询'
					}, {
						'value' : 'person',
						'name' : '按人员查询'
					}, {
						'value' : 'thirdds',
						'name' : '第三方数据源'
					}, {
						'value' : 'dishonesty',
						'name' : '失信人'
					}]
		});

var aColumnGroupFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_columnGroup.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '字段组别编号',
						name : 'ID',
						allowBlank : false
					}, {
						fieldLabel : '字段组别名称',
						name : 'COLUMN_GROUP_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '所属功能类别',
						anchor : '100%',
						name : 'FUNC_CODE',
						store : funcCodeStores,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}]

		});

var aColumnGroupW = Ext.create('Ext.window.Window', {
			items : aColumnGroupFP,
			modal : true,
			close : function() {
				this.hide();
				aColumnGroupFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aColumnGroupFP.getForm();
					index.util.operator.submitForm(form, aColumnGroupW, Ext
									.widget('columnGroupView').getStore());

				}
			}]
		});

var uColumnGroupFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_columnGroup.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '字段组别编号',
						name : 'ID',
						readOnly : true,
						allowBlank : false
					}, {
						fieldLabel : '字段组别名称',
						name : 'COLUMN_GROUP_NAME',
						allowBlank : false
					}, {
						xtype : 'combo',
						fieldLabel : '所属功能类别',
						anchor : '100%',
						name : 'FUNC_CODE',
						store : funcCodeStores,
						editable : false,
						validateBlank : true,
						emptyText : '请选择',
						displayField : 'name',
						valueField : 'value',
						allowBlank : false
					}]

		});

var uColumnGroupW = Ext.create('Ext.window.Window', {
			items : uColumnGroupFP,
			modal : true,
			close : function() {
				this.hide();
				uColumnGroupFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uColumnGroupFP.getForm();
					index.util.operator.submitForm(form, uColumnGroupW, Ext
									.widget('columnGroupView').getStore());
				}
			}]
		});

var columns = [{
			header : '字段组别编号',
			dataIndex : 'ID'
		}, {
			header : '字段组别名称',
			dataIndex : 'COLUMN_GROUP_NAME'
		}, {
			header : '所属功能类别',
			dataIndex : 'FUNC_CODE',
			renderer : function(value) {
				if (value == 'enterprise') {
					return '按企业查询';
				} else if (value == 'person') {
					return '按人员查询';
				} else if (value == 'thirdds') {
					return '第三方数据源';
				} else if (value == 'dishonesty') {
					return '失信人';
				}
			}
		}];

Ext.define('index.view.columnGroupView', {
			extend : 'Ext.grid.GridPanel',
			title : '字段组别',
			alias : 'widget.columnGroupView',
			columns : columns,
			store : 'columnGroupStore',
			emptyText: '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'columnGroupStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						defaultType : 'button',
						items : [{
									text : '新增字段组别',
									action : 'add'
								}, {
									text : '删除字段组别',
									hidden : true,
									action : 'delete'
								}]
					}]
		});
