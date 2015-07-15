Ext.define('index.controller.LoginController', {
    extend: 'Ext.app.Controller',
    views: ['index.view.LoginForm'],
    init: function() {
        console.log('Login Controller init');
        this.control({
            'loginForm button[action=login]': {
                click: this.login
            },
            'loginForm button[action=cancel]': {
            	click: this.cancel
        	},
        	'loginForm textfield[name=login_password]': {
        		keypress: this.keylogin
        	}
        });
    },
    redirectToIndex: function() {
    	location.reload();
    },
    showLoginError: function(msg) {
          Ext.Msg.alert('Error', msg);
    },
    onSuccess: function(form, action) {
        result = action.result;
        success = result['success'];
        if (success) {
        	//var cookie = new Ext.state.CookieProvider();
        	Ext.util.Cookies.set('username', form.findField('login_userName').getValue());
        	Ext.util.Cookies.set('password', form.findField('login_password').getValue());
        	//Ext.state.Manager.setProvider(cookie);
            this.redirectToIndex();
        } else {
            msg = result['msg'];
            this.showLoginError(msg);
        }
    },
    onFailure: function(form, action) {
        if (action.failureType === Ext.form.action.Action.CONNECT_FAILURE) {
            Ext.Msg.alert('错误',
                '状态:'+action.response.status+': '+
                action.response.statusText);
        }
        if (action.failureType === Ext.form.action.Action.SERVER_INVALID){
            console.log(action.response.status);
            console.log(action.result);
            Ext.Msg.alert('错误', action.result.msg);
        }
    },
    login: function(button) {
        var form = button.up('loginForm').getForm();
        if (form.isValid()) {
            form.submit({
                clientValidation: true,
                dataType: 'json',
                method: 'post',
                scope: this,
                success: this.onSuccess, 
                failure: this.onFailure, 
                url: 'doLogin',
                waitMsg: '正在检查您的账户，请耐心等待...'
            });
        }
    },
    keylogin:function(b, e, eOpts){
    	if(e.getKey() == Ext.EventObject.ENTER){
    		var form = b.up('loginForm').getForm();
            if (form.isValid()) {
                form.submit({
                    clientValidation: true,
                    dataType: 'json',
                    method: 'post',
                    scope: this,
                    success: this.onSuccess, 
                    failure: this.onFailure, 
                    url: 'doLogin',
                    waitMsg: '正在检查您的账户，请耐心等待...'
                });
            }
    	}
    },
    cancel: function(button) {
        var form = button.up('loginForm').getForm();
        form.reset();
    }
});

