package com.aiwo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.aiwo.message.req.TextMessage;
import com.aiwo.message.resp.Article;
import com.aiwo.message.resp.NewsMessage;
import com.aiwo.server.connect.Config;
import com.aiwo.server.pojo.New;
import com.aiwo.server.service.NewService;
import com.aiwo.server.service.WeixinService;
import com.aiwo.util.CommonUtil;
import com.aiwo.util.MessageUtil;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class CoreService {

	private static WeixinService wx = new WeixinService();

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return xml
	 */
	public static String processRequest(HttpServletRequest request) {
		// xml格式的消息数据
		String respXml = null;
		try {
			// 调用parseXml方法解析请求消息
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号
			String fromUserName = requestMap.get("FromUserName");
			// 开发者微信号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			
			// 事件推送s
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					String httpUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
					httpUrl = httpUrl.replace("ACCESS_TOKEN", CommonUtil
							.getToken(Config.APPID, Config.APPSECRET)
							.getAccessToken());
					httpUrl = httpUrl.replace("OPENID", fromUserName);
					JSONObject jsonObject = CommonUtil.httpsRequest(httpUrl,
							"GET", null);
					System.out.println(jsonObject);
					boolean flg = wx.scan(jsonObject);
					textMessage.setContent("您好，欢迎关注爱窝智慧家庭，智能家居，爱窝家庭！！");
//					if (flg) {

//						textMessage.setContent("您好，欢迎关注爱窝智慧家庭，智能家居，爱窝家庭！！");

//					} else {
//						textMessage
//								.setContent("您好，欢迎关注爱窝智慧家庭，智能家居，爱窝家庭！！您的微信未绑定手机app请<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa6309997e9c15c83&redirect_uri="
//										+ CommonUtil
//												.urlEncodeUTF8("http://www.59so.com/oauthServlet?state=1")
//										+ "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\">绑定</a>");
//					}

					// 将消息对象转换成xml
					respXml = MessageUtil.messageToXml(textMessage);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 保留方法 看项目具体铺展开是否需要删除取消关注的用户
					// wx.unscan(fromUserName) ;
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建菜单时的key值对应
					String eventKey = requestMap.get("EventKey");
					// 根据key值判断用户点击的按钮
					if (eventKey.equals("oschina")) {

						List<New> newList = new NewService().queryAll("1");

						List<Article> articleList = new ArrayList<Article>();

						for (int i = 0; i < newList.size(); i++) {
							Article article = new Article();
							article.setTitle(newList.get(i).getNewTitle());
							article.setDescription("");
							article.setPicUrl(Config.SITE
									+ newList.get(i).getNewImage());
							article.setUrl(Config.SITE + "/b.jsp?newId="
									+ newList.get(i).getNewId());
							articleList.add(article);
							
						}

						 NewsMessage newsMessage = new NewsMessage();
						 newsMessage.setToUserName(fromUserName);
						 newsMessage.setFromUserName(toUserName);
						 newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						 newsMessage.setArticleCount(articleList.size());
						 newsMessage.setArticles(articleList);
						 respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("product")) {
						Article article = new Article();
						article.setTitle("产品介绍");
						article.setDescription("环境温度、湿度，光照强度监测；\n空气污染强度监测；天燃气监测；\n用心以智慧家庭环境守护者为切入点满足家居环境环境监测；\n特别为婴幼儿、老人提供一个安全健康的家庭环境；\n配合空气净化器、空调、智能灯具，手机APP,实现家居环境智能可调可控。");
						article.setPicUrl("http://www.59so.com/img/aiwo-4.png");
						article.setUrl("");
						List<Article> articleList = new ArrayList<Article>();
						articleList.add(article);
						// 创建图文消息
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(new Date().getTime());
						newsMessage
								.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						respXml = MessageUtil.messageToXml(newsMessage);
					} else if (eventKey.equals("contact")) {
						textMessage
								.setContent("联系我们：\n TEL：010-57131103 62136195 \n网址：\nhttp://www.micronode.cn\nhttp://www.ltm8000.cn");
						respXml = MessageUtil.messageToXml(textMessage);
					}
				}
			}
			// 当用户发消息时
			else {
				textMessage.setContent("请通过菜单进行交互！");
				respXml = MessageUtil.messageToXml(textMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respXml;
	}
}
