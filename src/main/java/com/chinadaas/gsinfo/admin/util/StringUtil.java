package com.chinadaas.gsinfo.admin.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

/**
 * 字符串工具类
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 字符串工具类<br>
 * date: 2014年7月8日 下午5:33:39<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class StringUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
        '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	/**
	 * 对字符串进行MD5加密
	 * desc: 对字符串进行MD5加密<br>
	 * date: 2014年7月8日 下午5:33:59<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param str
	 * @return
	 */
	public static String getMD5String(String str) {
		if (str == null || "".equals(str.trim())) {
			return "";
		}

        try {
            MessageDigest messageDigest = MessageDigest
                .getInstance("MD5");

            messageDigest.update(str.getBytes("utf-8"));


            final byte[] digest = messageDigest.digest();

            return getFormattedText(digest);
        } catch (final NoSuchAlgorithmException e) {
            throw new SecurityException(e);
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
	}
	
	private static String getFormattedText(final byte[] bytes) {
        final StringBuilder buf = new StringBuilder(bytes.length * 2);

        for (int j = 0; j < bytes.length; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }
	
	/**
	 * 获取10位随机字符串
	 * desc: 获取10位随机字符串<br>
	 * date: 2014年7月8日 下午5:34:21<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public static String getRandomString() {
		String result = "";
		Random r = new Random();
		int i = 0;
		int c;
		while (i < 10) {
			c = r.nextInt(122);
			if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90)
					|| (c >= 97 && c <= 122)) {
				result = result + (char) c;
				i++;
			}
		}
		return result;
	}
	
	/**
	 * 获取对象字符串，主要用于处理空值情况
	 * desc: 获取对象字符串<br>
	 * date: 2014年7月8日 下午5:34:52<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param obj
	 * @return
	 */
	public static String getString(Object obj) {
		return obj == null ? null : obj.toString();
	}
	
	/**
	 * desc: 获取request.getParameterMap()中的值<br>
	 * date: 2014年11月14日 下午1:55:45<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param param
	 * @param key
	 * @return
	 */
	public static String getParam(Map param, String key) {
		if(param == null) {
			return null;
		}
		String[] values = (String[])param.get(key);
		if (values != null) {
			return (values.length > 0 ? values[0] : null);
		}
		return null;
	}
	
	/**
	 * 字符串首字母大写
	 * desc: 字符串首字母大写<br>
	 * date: 2014年7月8日 下午5:35:23<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param str
	 * @return
	 */
	public static String capitalFirst(String str) {
		StringBuffer sb = new StringBuffer(str);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	
	/**
	 * 判断字符串是否为空
	 * desc: 判断字符串是否为空<br>
	 * date: 2014年7月8日 下午5:35:38<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null || str.length() <= 0 || "null".equals(str);
	}
	
	/**
	 * 将列表转换为json列表对象
	 * desc: 将列表转换为json列表对象<br>
	 * date: 2014年7月8日 下午5:35:53<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param list
	 * @return
	 */
	public static JSONArray toJson(List list) {
		if (list == null) {
			return new JSONArray();
		}
		JSONArray arr = new JSONArray();
		arr.addAll(list);
		return arr;
	}
	
	/**
	 * desc: 将Map对象转化成json对象<br>
	 * date: 2014年8月26日 下午1:51:08<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param map
	 * @return
	 */
	public static JSONObject toJson(Map map) {
		if (map == null) {
			return new JSONObject();
		}
		JSONObject json = new JSONObject();
		json.putAll(map);
		return json;
	}
	
	/**
	 * desc: 防止中文乱码<br>
	 * date: 2014年8月18日 下午12:20:59<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param str
	 * @return
	 */
	public static String decodeString(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(getMD5String("1"));
		Map l = null;
		toJson(l);
	}

}
