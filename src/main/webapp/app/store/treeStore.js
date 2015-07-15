/**
 * @class MyNamespace.indexStore
 * @extends extendsClass
 * Description
 */
var menuTree = [{
	text: 'UKey管理',
	expanded: true,
	children: [{
		text: 'UKey',
		id: 'ukey',
		leaf: true
	}]
}, {
	text: '用户管理',
	expanded: true,
	children: [{
		text: '用户',
		id: 'user',
		leaf: true
	}, {
		text: '机构',
		id: 'org',
		leaf: true
	}, {
		text: '客户',
		id: 'customer',
		leaf: true
	}]
}, {
	text: '产品管理',
	expanded: true,
	children: [{
		text: '产品模板',
		id: 'productTpl',
		leaf: true
	}, {
		text: '产品实例分配',
		id: 'productOrder',
		leaf: true
	}, {
		text: '字段组别',
		id: 'columnGroup',
		leaf: true
	}, {
		text: '字段',
		id: 'column',
		leaf: true
	}]
}, 
//	{
//	text: '菜单管理',
//	expanded: true,
//	children: [{
//		text: '菜单',
//		id: 'menu',
//		leaf: true
//	}, {
//		text: '菜单角色',
//		id: 'role',
//		leaf: true
//	}]
//}, 
//	{
//	text: '权限管理',
//	expanded: true,
//	children: [{
//		text: '分配菜单角色',
//		id: 'alloc_role',
//		leaf: true
//	}, {
//		text: '分配ukey',
//		id: 'alloc_ukey',
//		leaf: true
//	}, {
//		text: '分配产品',
//		id: 'alloc_product',
//		leaf: true
//	}]
//}, 
	{
	text: '系统管理',
	expanded: true,
	children: [{
		text: '首页信息',
		id: 'indexInfo',
		leaf: true
	}
//	, {
//		text: '运行配置',
//		id: 'runtimeCfg',
//		leaf: true
//	}, {
//		text: '页签分类',
//		id: 'category',
//		leaf: true
//	}
	]
}, {
	text: '查询',
	expanded: true,
	children: [{
		text: '用户信息一览查询',
		id: 'userInfo',
		leaf: true
	}, {
		text: '用户产品信息查询',
		id: 'userProductInfo',
		leaf: true
	}, {
		text: '用户菜单信息查询',
		id: 'userMenuInfo',
		leaf: true
	}, {
		text: '用户字段信息查询',
		id: 'userColumnInfo',
		leaf: true
	}]
}];


Ext.define('index.store.treeStore', {
	extend: 'Ext.data.TreeStore',
	//		alias: 'widge.treeStore',
	root: {
		expanded: true,
		children: menuTree
	}
});