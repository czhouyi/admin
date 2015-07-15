Ext.define('index.view.statusBar', {
    extend: 'Ext.ux.statusbar.StatusBar',
	id: 'statusbar',
	alias: 'widget.mystatusbar',
	border: 0,
	bodyBorder: false,
	frame: false,
	text: '©2014 ChinaDaaS',
	initComponent:
		function() {
			var clock = Ext.create('Ext.toolbar.TextItem', {text: Ext.Date.format(new Date(), 'Y-m-d g:i:s A')});		
			var msg = Ext.create('Ext.toolbar.TextItem', {text: ''});
			Ext.apply(this, {
				items: [msg, clock, ' '],
				listeners: {
					render: {
						fn: function(){
							Ext.fly(msg.getEl().parent()).addCls('x-status-text-panel').createChild({cls:'spacer'});
							Ext.fly(clock.getEl().parent()).addCls('x-status-text-panel').createChild({cls:'spacer'});
						 	Ext.TaskManager.start({
								run: function(){
									Ext.fly(clock.getEl()).update(Ext.Date.format(new Date(), 'Y-m-d g:i:s A'));
								},
								interval: 1000
						 	});
						},
						delay: 100
					}
				}
			});
			this.callParent();
		}
});
