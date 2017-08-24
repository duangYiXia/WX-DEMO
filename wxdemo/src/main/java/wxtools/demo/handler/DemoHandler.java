package wxtools.demo.handler;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.WxXmlOutNewsMessage.Item;
import com.soecode.wxtools.exception.WxErrorException;
//import wxtools.demo.services.EnglishChineseService;
import wxtools.demo.services.WeatherService;

/**
 * 示例：DemoHandler
 * 目的：返回用户 “恭喜你中奖了”
 * @author antgan
 * @date 2016/12/15
 *
 */
public class DemoHandler implements WxMessageHandler{

	@Override
	public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService)
			throws WxErrorException {
		WxXmlOutMessage result = null ;
		String keyword = wxMessage.getContent();
		String contextStr = "";
		if(keyword.contains("你好") || keyword.contains("您好")) {
			contextStr = "你好";
			result = getXmlOutMsg(wxMessage, contextStr);
		} else if(keyword.contains("天气")) {
			String cityName = keyword.split("天气")[0];
			System.out.println("cityName=" + cityName);
			List<String> data = WeatherService.getInstance().getWeatherListData(cityName);
			if(data.size() > 30) {
				Item item = new Item();
				item.setTitle(data.get(0));
				item.setDescription(data.get(4) + "\n" 
						+ data.get(5) + "\n"
						+ data.get(6));
				Item item0 = new Item();
				item0.setTitle(data.get(7) + "\n"
						+ data.get(8));
				item0.setPicUrl(data.get(10));
				Item item1 = new Item();
				item1.setTitle(data.get(12) + "\n"
						+ data.get(13));
				item1.setPicUrl(data.get(15));
				Item item2 = new Item();
				item2.setTitle(data.get(17) + "\n"
						+ data.get(18));
				item2.setPicUrl(data.get(20));
				Item item3 = new Item();
				item3.setTitle(data.get(22) + "\n" +data.get(23));
				item3.setPicUrl(data.get(25));
				
				result = WxXmlOutMessage.NEWS()
						.addArticle(item)
						.addArticle(item0)
						.addArticle(item1)
						.addArticle(item2)
						.addArticle(item3)
						.toUser(wxMessage.getFromUserName())
						.fromUser(wxMessage.getToUserName()).build();
			} else {
				Item item = new Item();
				item.setTitle(cityName);
				item.setPicUrl("找不到该城市的天气数据！");
				item.setUrl("http://www.webxml.com.cn/");
				
				result = WxXmlOutMessage.NEWS()
						.addArticle(item)
						.toUser(wxMessage.getFromUserName())
						.fromUser(wxMessage.getToUserName()).build();
			}
		} /*else if(keyword.contains("#")) {//包含# 表示翻译(输入单词以#结尾)
			String transKey = keyword.split("#")[0];
			List<String> transData = EnglishChineseService.getInstance().getList(transKey);
			
			Item item = new Item();
			item.setTitle(transData.get(0) + "\n"
					+ transData.get(1) + "\n"
					+ transData.get(3) + "\n"
					+ transData.get(4));
			
			result = WxXmlOutMessage.NEWS()
					.addArticle(item)
					.toUser(wxMessage.getFromUserName())
					.fromUser(wxMessage.getToUserName()).build();
		}*/ else {
			contextStr = "不明白您的意思，请您回复【城市】+【天气】，如：北京天气，查询城市天气信息+'\n'"
					+ "或者输入单词翻译(英汉双译)，格式【单词】+【#】,如：world#";
			result = getXmlOutMsg(wxMessage, contextStr);
		}
     
		return result;
	}
	
	private WxXmlOutMessage getXmlOutMsg(WxXmlMessage wxMessage, String context) {
		String sendMsg = "";
		try {
			sendMsg = new String(context.getBytes("UTF-8"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content(sendMsg)
				.toUser(wxMessage.getFromUserName())
				.fromUser(wxMessage.getToUserName()).build();
		return xmlOutMsg;
	}
}
