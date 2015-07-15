package com.chinadaas.gsinfo.admin.service;

import com.chinadaas.gsinfo.admin.vo.User;

/**
 * 系统用户service接口类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: TODO<br>
 * date: 2014年7月8日 下午2:48:32<br>
 * @author 开发者真实姓名[裔传洲]
 */
public interface UserService extends IService<User>{
	
	/**
	 * desc: 判断用户名是否存在<br>
	 * date: 2014年8月25日 下午2:15:11<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param userName
	 * @return
	 */
	public boolean existUser(String userName);
	
	/**
	 * desc: 判断密码是否正确<br>
	 * date: 2014年8月25日 下午2:15:28<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param userid
	 * @param password
	 * @return
	 */
	public boolean passAuth(String userid, String password);
	
	/**
	 * desc: 修改密码<br>
	 * date: 2014年8月25日 下午2:15:32<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param userid
	 * @param newpassword
	 */
	public void changePassword(String userid, String newpassword);
	
}
