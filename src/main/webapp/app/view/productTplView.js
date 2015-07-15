/**
 * index.view.productTpl by hupeng
 */

var aProductTplFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'save_productTpl.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '产品模板名称',
						name : 'PRODUCT_TPL_NAME',
						allowBlank : false
					}]

		});

var aProductTplW = Ext.create('Ext.window.Window', {
			items : aProductTplFP,
			modal : true,
			close : function() {
				this.hide();
				aProductTplFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = aProductTplFP.getForm();
					index.util.operator.submitForm(form, aProductTplW, Ext
									.widget('productTplView').getStore());

				}
			}]
		});

var uProductTplFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'update_productTpl.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '产品模板编号',
						name : 'ID',
						allowBlank : false,
						readOnly : true
					}, {
						fieldLabel : '产品模板名称',
						name : 'PRODUCT_TPL_NAME',
						allowBlank : false
					}]

		});

var uProductTplW = Ext.create('Ext.window.Window', {
			items : uProductTplFP,
			modal : true,
			close : function() {
				this.hide();
				uProductTplFP.getForm().reset();
			},

			// Reset and Submit buttons
			buttons : [{
				text : '提交',
				formBind : true,
				handler : function() {
					var form = uProductTplFP.getForm();
					index.util.operator.submitForm(form, uProductTplW, Ext
									.widget('productTplView').getStore());
				}
			}]
		});

var columnTreeStore = Ext.create('Ext.data.TreeStore', {
			proxy : {
				type : 'ajax',
				url : 'tree_columnGroup.action',
				reader : {
					type : 'json',
					root : 'result'
				},
				actionMethods : {
					create : 'POST',
					read : 'POST',
					update : 'POST',
					destroy : 'POST'
				}
			},
			autoLoad : false,
			root : {
				expanded : true,
				id : '-1'
			}
		});

var columnTreePanel = Ext.create('Ext.tree.Panel', {
			width : 250,
			height : 300,
			autoScroll : true,
			store : columnTreeStore,
			rootVisible : false
		});

var arrangeCGW = Ext.create('Ext.window.Window', {
			items : columnTreePanel,
			modal : true,
			title : '字段组别选择',
			close : function() {
				this.hide();
			},

			// Reset and Submit buttons
			buttons : [{
						text : '提交',
						formBind : true
					}]
		});

var render = Ext.create('index.util.DataRender', {});

var columns = [{
			header : '产品模板编号',
			dataIndex : 'ID'
		}, {
			header : '产品模板名称',
			dataIndex : 'PRODUCT_TPL_NAME'
		}, {
			header : '创建时间',
			dataIndex : 'CREATE_TIME',
			renderer : render.dateRender
		}];

Ext.define('index.view.productTplView', {
			extend : 'Ext.grid.GridPanel',
			title : '产品模板',
			alias : 'widget.productTplView',
			columns : columns,
			store : 'productTplStore',
			emptyText : '找不到相关数据',
			dockedItems : [{
						xtype : 'pagingtoolbar',
						store : 'productTplStore',
						dock : 'bottom',
						displayInfo : true
					}, {
						xtype : 'toolbar',
						dock : 'top',
						defaultType : 'button',
						items : [{
									text : '新增模板',
									action : 'add'
								}, {
									text : '删除模板',
									hidden : true,
									action : 'delete'
								}, {
									text : '分配字段组别',
									action : 'arrange'
								}]
					}]
		});
