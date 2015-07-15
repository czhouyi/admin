package com.chinadaas.gsinfo.admin.util;

/**
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: smtp邮件服务器校验类<br>
 * date: 2014年9月1日 下午2:54:27<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class SmtpAuth extends javax.mail.Authenticator {
	private String username, password;

	public SmtpAuth(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
		return new javax.mail.PasswordAuthentication(username, password);
	}
}
