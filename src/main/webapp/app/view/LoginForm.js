Ext.define('index.view.LoginForm', { 
        extend: 'Ext.form.Panel',
        alias: 'widget.loginForm',
        defaultType: 'textfield',
        labelWidth: 60,
        border: false, 
        bodyPadding: '10,10,4',
        defaults: {
            border: false,
            msgTarget: 'side',
            blankText: ''
	        },
	    waitMsgTarget : true,
        items: [
            { 
                fieldLabel: '用户名:', 
                name: 'login_userName', 
                height: 48, 
                width: 320, 
                msgTarget: 'qtip',
                //border: 0,
                labelAlign: 'top',
                value: Ext.util.Cookies.get("username"),
                allowBlank: false
                //inputType: 'email' 
            },
            { 
                fieldLabel: '密码:', 
                name: 'login_password', 
                height: 48, 
                width: 320, 
                msgTarget: 'qtip',
                minLength: 0,
                labelAlign: 'top',
                enableKeyEvents: true,
                value: Ext.util.Cookies.get("password"),
                inputType: 'password' 
            }
        ],
        buttons: [
            { text: '登录', action: 'login' }, 
            { text: '取消', action: 'cancel'}
         ]
});

