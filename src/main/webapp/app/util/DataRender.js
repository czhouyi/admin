Ext.define('index.util.DataRender', {
	dateRender: function(value, meta, record, rowIndex, columnIndex, store) {
        return Ext.Date.format(value,'Y-m-d');
    },
    booleanRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        if (value == 0) {
        	result = '否';
		} else if (value == 1) {
			result = '是';
		}
        return result;
    },
    userStatusRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        if (value == 0) {
        	result = '禁用';
		} else if (value == 1) {
			result = '正常';
		} else if (value == 2) {
			result = '正在审核';
		} else if (value == 3) {
			result = '信息不完整请重新修正';
		}
        return result;
    },
    regStatusRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        if (value == 0) {
			result = '新用户';
		} else if (value == 1) {
			result = '老用户';
		}
        return result;
    },
    ukeyTypeRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        if (value == 1) {
        	result = '正式-收工本费';
		} else if (value == 2) {
			result = '正式-免工本费';
		} else if (value == 3) {
			result = '正式-专线虚拟';
		} else if (value == 4) {
			result = '试用-专线虚拟';
		} else if (value == 5) {
			result = '试用-外网虚拟';
		} else if (value == 6) {
			result = '自服务-虚拟';
		}
        return result;
    },
    orgTypeRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        if (value == 0) {
        	result = '自用';
		} else if (value == 1) {
			result = '代理商';
		} else if (value == 2) {
			result = '外部客户';
		}
        return result;
    },
    contractTypeRender: function(value, meta, record, rowIndex, columnIndex, store) {
    	var result = '';
    	if (value == 0) {
    		result = '试用协议';
		} else if (value == 1) {
			result = '正式合同';
		} else if (value == 2) {
			result = '自用';
		}
    	return result;
    },
    customerStatusRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        if (value == 0) {
        	result = '正常';
		} else if (value == 1) {
			result = '人工停用';
		} else if (value == 2) {
			result = '系统停用';
		}
        return result;
    },
    columnStatusRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        switch(value)
        {
            case '1':
                result = '批量和单条';
                break;
            case '2':
                result = '仅单条和word下载';
                break;
            case '3':
                result = '仅批量Excel下载';
                break;
            case '-1':
                result = '全禁用';
                break;
            default:
                result = '';
           
        }
        return result;
    },
    columnTypeRender: function(value, meta, record, rowIndex, columnIndex, store) {
        var result = '';
        switch(value)
        {
            case '1':
                result = '仅中数内部开放';
                break;
            case '2':
                result = '所有用户免费开放';
                break;
            case '3':
                result = '收费开放';
                break;
            case '4':
                result = '不开放';
                break;
            default:
                result = '';
           
        }
        return result;
    }


});
