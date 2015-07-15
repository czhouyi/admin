/**
 * 
 */

Ext.onReady(function() {
	Ext.apply(Ext.form.field.VTypes, {
		    password: function(val, field) {
            if (field.initialPassField) {
                var pwd = field.up('form').down('#' + field.initialPassField);
                return (val == pwd.getValue());
            }
            return true;
        },
        passwordText: '密码不一致'
	});
});

function submitForm(form, win) {
	if (form.isValid()) {
		form.submit({
					success : function(form, action) {
						Ext.Msg.alert('提示', action.result.msg);
						win.close();
					},
					failure : function(form, action) {
						Ext.Msg.alert('错误', action.result.msg);
					}
				});
	}
}


var changePswFP = Ext.create('Ext.form.Panel', {
			bodyPadding : 5,
			width : 350,

			// The form will submit an AJAX request to this URL when submitted
			url : 'changePassword.action',

			// Fields will be arranged vertically, stretched to full width
			layout : 'anchor',
			defaults : {
				anchor : '100%',
				inputType: 'password'
			},

			// The fields
			defaultType : 'textfield',
			items : [{
						fieldLabel : '旧密码',
						name : 'oldPassword'
					}, {
						fieldLabel : '新密码',
						name : 'newPassword',
						allowBlank : false,
						itemId: 'pass'
					}, {
						fieldLabel : '重复密码',
						name : 'rePassword',
						vtype: 'password',
						initialPassField: 'pass',
						allowBlank : false
					}]

		});

var changePswWin = Ext.create('Ext.window.Window', {
			items : changePswFP,
			close : function() {
				this.hide();
				changePswFP.getForm().reset();
			},
			title : '用户名:'+ Ext.get('login_userName').getValue(),
			draggable : false,
			// Reset and Submit buttons
			buttons : [{
						text : '提交',
						formBind : true,
						handler : function() {
							var form = changePswFP.getForm();
							submitForm(form, changePswWin);
						}
					}]
		});
 
Ext.define('index.view.toolBarPanel', {
			extend : 'Ext.toolbar.Toolbar',
			alias : 'widget.toolBarPanel',
			height: 60,
			floatable: false,
		    minWidth: 175,
		    frameHeader: false,
		    collapsible: false,
		    bodyBorder: false,
			border: 0,
			style: {
				background: 'url(resources/images/header_bg.gif) repeat-x', 
				padding: '10px'
			},
			html : '<div style="color:white; text-shadow: 5px 5px 5px #0A1C7E;"><font color="white" size="6"> GSInfo后台管理系统</font></div>',
			items : ['->', {
				text : '当前用户 : ' + Ext.get('login_userName').getValue()
			}, {
				text : '修改密码',
				handler : function() {
					changePswWin.show();
				}
			}, {
				text : '注销',
				handler : function() {
					Ext.Msg.confirm('系统提示', '确定要退出后台管理系统?', function(btn) {
						if (btn == 'yes') {
							window.location.href = 'logout.action'; // 'https://yichuanzhou:8443/cas/logout';
						}
					}, this);
				}
			}]

		});