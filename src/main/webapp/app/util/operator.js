/**
 * 
 */
Ext.define('index.util.operator', {
	singleton : true,
	submitForm : function(form, win, store) {
		if (form.isValid()) {
			Ext.Msg.confirm('系统提示', '确定要提交吗？', function(btn) {
						if (btn == 'yes') {
							form.submit({
										success : function(form, action) {
											Ext.Msg.alert('提示', '操作成功');
											win.close();
											store.reload();
										},
										failure : function(form, action) {
											Ext.Msg.alert('错误', "操作失败");
										}
									});
						}
					});
		}
	},

	query : function(o, e, grid) {
		if (e.getKey() == Ext.EventObject.ENTER) {
			var store = grid.getStore();
			var allTextField = grid.query('textfield');
			var paramsArr = new Array();
			for (var i = 0; i < allTextField.length; i++) {
				var obj = allTextField[i];
				if (obj.name != 'inputItem') {
					paramsArr
							.push(obj.getName() + ': "' + obj.getValue() + '"');
				}
			}
			var params = eval('({' + paramsArr.join(',') + '})');
			// console.log(params);
			store.proxy.extraParams = {};
			Ext.apply(store.proxy.extraParams, params);
			store.loadPage(1);
		}
	},

	deleteRecord : function(grid, url) {
		var store = grid.getStore();
		var selected = grid.getSelectionModel().getSelection()[0];
		if (selected) {
			Ext.Msg.confirm('系统提示', '确定要删除吗？', function(btn) {
						if (btn == 'yes') {
							Ext.Ajax.request({
										url : url,
										params : {
											id : selected.get('ID')
										},
										success : function(response) {
											var info = JSON
													.parse(response.responseText);
											if (info.success) {
												Ext.Msg.alert('提示', '删除成功');
												grid.getStore().reload();
												grid.getView().refresh();
											} else {
												Ext.Msg.alert('错误', info.msg);
											}
										},
										failure : function(response) {
											var text = response.responseText;
											Ext.Msg.alert('错误', text);
										}
									});
						}

					}, this);
		} else {
			Ext.Msg.alert('提示', '请选择一条记录');
			return;
		};
	}
});