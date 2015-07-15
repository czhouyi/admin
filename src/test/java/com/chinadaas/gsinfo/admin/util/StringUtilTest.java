package com.chinadaas.gsinfo.admin.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * projectName: gsinfo30-admin<br>
 * desc: StringUtil工具类测试用例<br>
 * date: 2014年8月26日 上午9:37:58<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class StringUtilTest {

	@Test
	public void testGetMD5String() {
		String str = "test";
		String md5 = StringUtil.getMD5String(str);
		
		assertTrue(md5.equals(StringUtil.getMD5String("test")));
		assertFalse(md5.equals(StringUtil.getMD5String("test1")));
		assertTrue(StringUtil.isNull(StringUtil.getMD5String("")));
		assertTrue(StringUtil.isNull(StringUtil.getMD5String(null)));
	}
	
	@Test
	public void testCapitalFirst() {
		assertTrue("Admin".equals(StringUtil.capitalFirst("admin")));
	}
	
	@Test
	public void testIsNull() {
		assertTrue(StringUtil.isNull(""));
		assertTrue(StringUtil.isNull("null"));
		assertTrue(StringUtil.isNull(null));
	}

}

