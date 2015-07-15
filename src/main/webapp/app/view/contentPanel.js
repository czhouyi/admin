

Ext.define('index.view.contentPanel', {
    extend: 'Ext.Panel',
    alias: 'widget.contentPanel',
    id : 'contentPanel',
    autoScroll: true,
    autoHeight: true,
    autoWidth:  true,
    layout: 'fit',
    activeItem: 0,
    border: false,
    items: {
        id: 'start-panel',
        title: '首页',
        bodyStyle: 'padding:25px',
        contentEl: 'start-div' // pull existing content from the page
    }
});