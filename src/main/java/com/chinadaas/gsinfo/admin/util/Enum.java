package com.chinadaas.gsinfo.admin.util;

/**
 * projectName: gsinfo30-admin<br>
 * desc: 系统使用到的枚举<br>
 * date: 2014年8月27日 上午10:52:19<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class Enum {
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: Ukey状态<br>
	 * date: 2014年8月27日 上午10:56:50<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class UkeyState {
		// 1 正常 0 禁用
		public static final String ENABLE 	= "1";
		public static final String DISABLE 	= "0";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: Ukey 类型<br>
	 * date: 2014年8月27日 上午10:57:13<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class UkeyType {
		// 1 正式-收工本费 2 正式-免工本费 3 正式-专线虚拟 4 试用-专线虚拟 5 试用-外网虚拟 6 自服务-虚拟
		public static final String NORMAL 		= "1";
		public static final String FREE 		= "2";
		public static final String NOR_WS_VIR	= "3";
		public static final String TRIAL_WS_VIR = "4";
		public static final String TRIAL_VIR	= "5";
		public static final String SELF_VIR		= "6";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: 用户状态<br>
	 * date: 2014年8月27日 上午11:02:37<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class UserState {
		// 0禁用 1正常 2 正在审核 3 信息不完整请重新修正
		public static final String DISABLE 	= "0";
		public static final String ENABLE	= "1";
		public static final String AUDITING	= "2";
		public static final String REJECT	= "3";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: 是否新用户注册<br>
	 * date: 2014年8月27日 上午11:05:37<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class REGSTATE {
		// 0 老用户 1 新用户--web只服务新用户
		public static final String OLD 	= "0";
		public static final String NEW	= "1";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: 客户状态<br>
	 * date: 2014年8月27日 上午11:02:37<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class CustomerState {
		// 0 正常 1 人工停用 2 系统停用
		public static final String ENABLE 		= "0";
		public static final String MANDISABLE 	= "1";
		public static final String SYSDISABLE	= "2";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: 商务销售状态<br>
	 * date: 2014年8月27日 上午11:02:37<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class SaleState {
		// 0 正式-执行中 1 决定合同签署 2 提交商务方案 3 进入采集阶段 4 试用阶段
		public static final String NORMAL 	= "0";
		public static final String CONTRACT = "1";
		public static final String SCHEME	= "2";
		public static final String COLLECT	= "3";
		public static final String TRIAL	= "4";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: 机构状态<br>
	 * date: 2014年8月27日 上午10:56:50<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class OrgState {
		// 1 正常 0 禁用
		public static final String ENABLE 	= "1";
		public static final String DISABLE 	= "0";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: 机构类型<br>
	 * date: 2014年8月27日 上午10:56:50<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class OrgType {
		// 0 自用 1 代理商 2 外部客户
		public static final String HELPSELF	= "0";
		public static final String AGENT 	= "1";
		public static final String CUSTOMER	= "2";
	}
	
	/**
	 * projectName: gsinfo30-admin<br>
	 * copyright: Copyright (c) 2014<br>
	 * company: 北京中数智汇科技有限公司<br>
	 *
	 * desc: 产品状态<br>
	 * date: 2014年8月27日 上午10:56:50<br>
	 * @author 开发者真实姓名[裔传洲]
	 */
	public static class ProductState {
		// 0试运行上线 1 试运行下线 2正式上线 3正式下线
		public static final String TRIAL_ON		= "0";
		public static final String TRIAL_OFF 	= "1";
		public static final String ON 			= "2";
		public static final String OFF 			= "3";
	}

}

