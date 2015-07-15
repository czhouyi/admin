package com.chinadaas.gsinfo.admin.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.chinadaas.gsinfo.admin.vo.User;

/**
 * projectName: gsinfo30-admin<br>
 * desc: PropertyUtil工具类测试用例<br>
 * date: 2014年8月26日 上午11:25:00<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class PropertyUtilTest {

	@Test
	public void testGet() {
		User user = new User();
		user.setUser_name("abc");
		try {
			assertTrue(user.getUser_name().equals(PropertyUtil.get(user, "user_name")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSet() {
		User user = new User();
		String name = "username";
		try {
			PropertyUtil.set(user, "user_name", name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(name.equals(user.getUser_name()));
	}

}

