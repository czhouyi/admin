package com.chinadaas.gsinfo.admin.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.chinadaas.gsinfo.admin.service.UserService;
import com.chinadaas.gsinfo.admin.util.Constants;
import com.chinadaas.gsinfo.admin.util.StringUtil;
import com.chinadaas.gsinfo.admin.vo.User;

/**
 * 登录模块action
 * projectName: gsinfo30-admin<br>
 * copyright: Copyright (c) 2014<br>
 * company: 北京中数智汇科技有限公司<br>
 *
 * desc: 登录模块<br>
 * date: 2014年7月8日 下午5:32:43<br>
 * @author 开发者真实姓名[裔传洲]
 */
public class LogAction extends BaseAction {
	
	private static final Logger logger = LoggerFactory.getLogger(LogAction.class);
	
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 登录方法
	 */
	public String execute() throws IOException{
		
		String loginUser = StringUtil.getString(getSession().getAttribute(Constants.PARAM_LOGINUSER));
		String loginTime = StringUtil.getString(getSession().getAttribute(Constants.PARAM_LOGINTIME));
		if( loginUser == null || loginUser.length() <= 0 ||
			loginTime == null || loginTime.length() <= 0) {
			return "failed";
		}

		return SUCCESS;
	}
	
	/**
	 * desc: 登录方法<br>
	 * date: 2014年8月26日 上午10:16:22<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String login() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String userName = request.getParameter(Constants.PARAM_LOGINUSER);
		String password = request.getParameter(Constants.PARAM_LOGINPWD);
		
		boolean userExist = userService.existUser(userName);
		if(!userExist) {
			logger.info("用户[username="+userName+"]登录失败:用户不存在");
			response(false, "用户不存在", Constants.JSON_CODE_200);
			return NONE;
		}
		
		User user = userService.get("from User where user_name='"+userName+"'");
		
		boolean passAuth = userService.passAuth(user.getId(), password);
		if(!passAuth) {
			logger.info("用户[id="+user.getId()+", username="+userName+"]登录失败:密码不正确");
			response(false, "密码不正确", Constants.JSON_CODE_200);
			return NONE;
		}
		
		request.getSession().setAttribute(Constants.PARAM_LOGINUSERID, user.getId());
		request.getSession().setAttribute(Constants.PARAM_LOGINUSER, userName);
		request.getSession().setAttribute(Constants.PARAM_LOGINTIME, new Date().getTime());
		logger.info("用户[id="+user.getId()+", username="+userName+"]登录成功");
		
		response(true, Constants.JSON_OK, Constants.JSON_CODE_200);
		return NONE;
	}
	
	/**
	 * desc: 修改密码<br>
	 * date: 2014年8月26日 上午10:16:03<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String changePassword() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String userid = StringUtil.getString(getSession().getAttribute(Constants.PARAM_LOGINUSERID));
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		
		if(StringUtil.isNull(userid)) {
			response(false, "用户未登录", Constants.JSON_CODE_200);
			return NONE;
		}
		
		boolean passAuth = userService.passAuth(userid, oldPassword);
		
		if(passAuth) {
			userService.changePassword(userid, newPassword);
			response(true, Constants.JSON_OK, Constants.JSON_CODE_200);
			return NONE;
		} else {
			response(false, "密码不正确", Constants.JSON_CODE_200);
			return NONE;
		}
	}
	
	/**
	 * desc: 获取登录信息<br>
	 * date: 2014年8月26日 下午2:04:03<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String getLoginInfo() {
		String user = StringUtil.getString(getSession().getAttribute(Constants.PARAM_LOGINUSER));
		
		if(user == null || user.length() <= 0) {
			response(false, "", Constants.JSON_CODE_200);
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			response(true, "", Constants.JSON_CODE_200, map);
		}
		
		return NONE;
	}
	
	/**
	 * 校验登录
	 * desc: 校验登录<br>
	 * date: 2014年7月8日 下午4:01:29<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String checkLogin() {
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String loginUser = request.getParameter(Constants.PARAM_LOGINUSER);
		String loginPwd = request.getParameter(Constants.PARAM_LOGINPWD);
		String vCode = request.getParameter(Constants.PARAM_VCODE);
		String validateCode = StringUtil.getString(getSession().getAttribute(Constants.VALIDATE_CODE));
		
		if(loginUser == null || loginUser.length() <= 0
				|| loginPwd == null || loginPwd.length() <= 0
				|| vCode == null || vCode.length() <= 0
				|| validateCode == null || validateCode.length() <= 0){
			response(true, "ERROR", "输入信息不完整");
			return null;
		}

		if (!vCode.equalsIgnoreCase(validateCode)) {
			response(true, "ERROR", "校验码输入不正确");
			return null;
		}
		
		response(true, "OK", "");
		
		getSession().setAttribute(Constants.PARAM_LOGINUSER, loginUser);
		getSession().setAttribute(Constants.PARAM_LOGINTIME, new Date().getTime());
		return null;
	}
	
	/**
	 * 生成登录校验码
	 * desc: 生成登录校验码<br>
	 * date: 2014年7月8日 下午4:12:54<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String genCode() {
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 生成随机类
		Random random = new Random();

		// 设定背景色
		g.setColor(getRandomColor(200, 250));
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 画边框
		// g.setColor(new Color());
		// g.drawRect(0,0,width-1,height-1);

		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandomColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 14 * i + 5, 16);
		}

		// 将认证码存入session
		getSession().setAttribute(Constants.VALIDATE_CODE, sRand);

		// 图象生效
		g.dispose();

		HttpServletResponse response = getResponse();
		try {
			OutputStream out = response.getOutputStream();
			// 输出图象到页面
			ImageIO.write(image, "JPEG", response.getOutputStream());
			out.flush();// 清空缓冲区
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 登出方法
	 * desc: 登出方法<br>
	 * date: 2014年7月8日 下午5:33:11<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @return
	 */
	public String logout(){
		getSession().invalidate();
		return SUCCESS;
	}
	
	/**
	 * 给定范围获得随机颜色
	 * desc: 给定范围获得随机颜色<br>
	 * date: 2014年7月8日 下午3:15:42<br>
	 * @author 开发者真实姓名[裔传洲]
	 * @param fc
	 * @param bc
	 * @return
	 */
	private Color getRandomColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
