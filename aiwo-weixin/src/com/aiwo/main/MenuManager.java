package com.aiwo.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aiwo.menu.Button;
import com.aiwo.menu.ClickButton;
import com.aiwo.menu.ComplexButton;
import com.aiwo.menu.Menu;
import com.aiwo.menu.ViewButton;
import com.aiwo.pojo.Token;
import com.aiwo.util.CommonUtil;
import com.aiwo.util.MenuUtil;

/**
 * 菜单管理器类
 * 
 * 
 * @author Liuxp
 * @date 2015年1月4日 10:24:40
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 定义菜单结构
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private static Menu getMenu() {

		ViewButton btn11 = new ViewButton();
		btn11.setName("查看设备");
		btn11.setType("view");
		btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa6309997e9c15c83&redirect_uri="+CommonUtil.urlEncodeUTF8("http://www.59so.com/oauthServlet?state=1")+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

		ViewButton btn12 = new ViewButton();
		btn12.setName("微商城");
		btn12.setType("view");
		btn12.setUrl("http://www.59so.com/mall.jsp");

		/**************************************/
		ViewButton btn15 = new ViewButton();
		btn15.setName("关于我们");
		btn15.setType("view");
		btn15.setUrl("http://www.59so.com/a.jsp");

		ViewButton btn16 = new ViewButton();
		btn16.setName("管理设备");
		btn16.setType("view");
		btn16.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxa6309997e9c15c83&redirect_uri="+CommonUtil.urlEncodeUTF8("http://www.59so.com/oauthServlet?state=2")+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect");

		ClickButton btn17 = new ClickButton();
		btn17.setName("技术支持");
		btn17.setType("click");
		btn17.setKey("contact");

		
		
		
		ClickButton btn18 = new ClickButton();
		btn18.setName("最新资讯");
		btn18.setType("click");
		btn18.setKey("oschina");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("爱窝");
		mainBtn1.setSub_button(new Button[] { btn18, btn16, btn17, btn15 });

		// ComplexButton mainBtn2 = new ComplexButton();
		// mainBtn2.setName("产品介绍");
		// mainBtn2.setSub_button(new Button[] { btn12 });
		//
		// ComplexButton mainBtn3 = new ComplexButton();
		// mainBtn3.setName("联系我们");
		// mainBtn3.setSub_button(new Button[] { btn13 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { btn11, btn12, mainBtn1 });

		return menu;
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = "wxa6309997e9c15c83";
		// 第三方用户唯一凭证密钥
		String appSecret = "259b1a189b3b8375970c57951ae80617";

		// 调用接口获取凭证
		Token token = CommonUtil.getToken(appId, appSecret);

		if (null != token) {
			// 创建菜单
//			boolean result = MenuUtil.createMenu(getMenu(),
//					token.getAccessToken());
			 boolean result = MenuUtil.deleteMenu(token.getAccessToken());
			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败！");
		}
	}
}
