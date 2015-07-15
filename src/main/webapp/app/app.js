/**
 * index.js by hupeng
 */

Ext.Loader.setConfig({
			enabled : true
		});

Ext.application({

			requires : ['index.util.DataRender', 'index.util.operator', 'Ext.ux.grid.FiltersFeature'],

			name : 'index',

			autoCreateViewport : true,

			controllers : ['mainCtrl', 'roleCtrl', 'userCtrl', 'customerCtrl',
					'ukeyCtrl', 'orgCtrl', 'productTplCtrl', 'columnGroupCtrl',
					'columnCtrl', 'productOrderCtrl', 'userInfoCtrl',
					'userProductInfoCtrl', 'userMenuInfoCtrl',
					'userColumnInfoCtrl', 'indexInfoCtrl'],

			stores : ['treeStore'],

			views : ['extKindEditor', 'roleView', 'userView', 'customerView', 'ukeyView',
					'orgView', 'productTplView', 'columnGroupView',
					'columnView', 'productOrderView', 'toolBarPanel', 'statusBar',
					'userInfoView', 'userProductInfoView', 'userMenuInfoView',
					'userColumnInfoView', 'indexInfoView' ]
		});