Ext.QuickTips.init();
Ext.application({
    name: 'index',
    
    controllers: [
    'LoginController'
    ],

    launch: function() {
       this.showLoginForm();
    }, 
    constructor: function(config) {
        this.callParent(arguments);
        this.initConfig(config);
    },
    showLoginForm: function() {
        var form = Ext.create('index.view.LoginForm', {});
        var win = Ext.create('Ext.window.Window', {
            title: '后台管理登录',
            layout: 'fit',
            plain: true,
            items: form,
            closable: false,
            style: 
            {
            padding: "10 10 10 10",
                //width: 500,
                //height:300,
                //minWidth: 300,
                //minHeight: 200,
            }
        });
        win.show();     
    }
});

