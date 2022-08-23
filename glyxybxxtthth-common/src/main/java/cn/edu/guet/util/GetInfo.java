package cn.edu.guet.util;


import cn.yiban.open.Authorize;
import cn.yiban.open.common.User;
import cn.yiban.util.AESDecoder;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 与易班进行授权，并获取登录用户的隐私信息（token）和普通信息（用户名，头像）和真实信息（真实姓名）
 * 存入session中
 */
public class GetInfo {
	private String appkey;//appID
	private String appsecret;//appSecret
	private String redirect_uri;//回调地址
	private User u = null;
	
	/**
	 * 初始化
	 * @param appkey
	 * @param appsecret
	 * @param redirect_uri
	 */
	public GetInfo(String appkey,String appsecret,String redirect_uri)
	{
		this.appkey = appkey;
		this.appsecret = appsecret;
		this.redirect_uri = redirect_uri;
	}
	
	/**
	 * 授权获得token
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private int init(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Authorize au = new Authorize(appkey, appsecret);
		String verify_request = request.getParameter("verify_request");
		if(verify_request != null && !verify_request.isEmpty())
		{
			try {
				String code = AESDecoder.dec(verify_request, appsecret, appkey);
				JSONObject codejson = JSONObject.fromObject(code);
				JSONObject codeacjson = (JSONObject)codejson.get("visit_oauth");
				String access_token = codeacjson.getString("access_token");
				request.getSession().setAttribute("access_token", access_token);
				u = new User(access_token);
				if(u==null)
					return 0;
				else 
					return 1;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		else
		{
			String url = au.forwardurl(redirect_uri, "grade", Authorize.DISPLAY_TAG_T.WEB);
			response.sendRedirect(url);
			return 2;
		}
	}
	
	/**
	 * 获得普通信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getMe(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		int b = this.init(request, response);
		if(b==0)
		{
			response.sendRedirect("/WEB-INF/jsp/index.jsp");
			return;
		}else if(b==2){
			return;
		}
		HttpSession session = request.getSession();
		
		String me = u.me();
		JSONObject mejson = JSONObject.fromObject(me);
		JSONObject info = (JSONObject)mejson.get("info");
		String status = mejson.getString("status");
		if("error".equals(status))
		{
			this.printError(info,request);
			return;
		}
		Me m = new Me();
		m.setId(info.getString("yb_userid"));
		m.setName(info.getString("yb_username"));
		m.setExp(info.getString("yb_exp"));
		m.setHead(info.getString("yb_userhead"));
		m.setMoney(info.getString("yb_money"));
		m.setNike(info.getString("yb_usernick"));
		m.setSchool(info.getString("yb_schoolname"));
		m.setSex(info.getString("yb_sex"));
		session.setAttribute("me", m);
		return;
	}
	
	/**
	 * 获得真实信息
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void getRealMe(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		int b = this.init(request, response);
		if(b==0)
		{
			response.sendRedirect("/WEB-INF/jsp/index.jsp");
			return;
		}else if(b==2){
			return;
		}
		HttpSession session = request.getSession();
		String realme = u.realme();
		JSONObject mejson = JSONObject.fromObject(realme);
		// 获取状态
		String status = mejson.getString("status");
		// 个人信息存在 key为info里
		JSONObject info = (JSONObject)mejson.get("info");
		if("error".equals(status))
		{
			this.printError(info,request);
			return;
		}
		RealMe rm = new RealMe();
		Me m = new Me();
		m.setId(info.getString("yb_userid"));
		m.setName(info.getString("yb_username"));
		m.setExp(info.getString("yb_exp"));
		m.setHead(info.getString("yb_userhead"));
		m.setMoney(info.getString("yb_money"));
		m.setNike(info.getString("yb_usernick"));
		m.setSchool(info.getString("yb_schoolname"));
		m.setSex(info.getString("yb_sex"));
		// 上方代码就是getMe方法的内容，获取用户的基本信息
		// 下方代码就是多出一个实体类RealMe，封装了Me，又多出了yb_identity等真实信息，存入session
		rm.setM(m);
		rm.setIdentity(info.getString("yb_identity"));
		rm.setRealName(info.getString("yb_realname"));
		rm.setSid(info.getString("yb_studentid"));
		session.setAttribute("realme", rm);
		return;
	}
	
	/**
	 * 输出报错信息
	 */
	public void printError(JSONObject info,HttpServletRequest request)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("错误编码");
		buffer.append(info.getString("code"));
		buffer.append("**<br>**");
		buffer.append("中文信息");
		buffer.append(info.getString("msgCN"));
		String message = buffer.toString();
		request.getSession().setAttribute("message", message);
	}
}
