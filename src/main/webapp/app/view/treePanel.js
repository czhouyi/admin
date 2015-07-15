/**
 * @class MyNamespace.indexView
 * @extends extendsClass
 * Description
 */
Ext.define('index.view.treePanel', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.treePanel',
    title: '菜单',
    region: 'north',
    split: true,
    minSize: 150,
    frameHeader: true,
    rootVisible: false,
    collapsible: true,
    animCollapse: true,
    autoScroll: false,
    border: 0,
    store: 'treeStore'
    
});