//package wxtools.demo.services;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import cn.com.webxml.ArrayOfString;
//import cn.com.webxml.EnglishChinese;
//import cn.com.webxml.EnglishChineseSoap;
//
//public class EnglishChineseService {
//	private Map<String, List<String>> ECListDataMap;
//	
//	private EnglishChinese e = null;
//	private EnglishChineseSoap es= null;
//	private static EnglishChineseService instance = null;
//	
//	public EnglishChineseService() {
//		e = new EnglishChinese();
//		es = e.getEnglishChineseSoap();
//		ECListDataMap = new HashMap<String, List<String>>();
//	}
//	
//	public static EnglishChineseService getInstance() {
//		if(instance == null) {
//			synchronized (EnglishChineseService.class) {
//				EnglishChineseService temp = instance;
//				if(temp == null) {
//					temp = new EnglishChineseService();
//					instance = temp;
//				}
//			}
//		}
//		
//		return instance;
//	}
//	
//	public List<String> getList(String wordKey) {
//		List<String> result = null;
//		if(ECListDataMap.containsKey(wordKey)){
//			result = ECListDataMap.get(wordKey);
//		} else {
//			ArrayOfString as = es.translatorString(wordKey);
//			result = as.getString();
//			for(int i=0; i < result.size(); i++) {
//				System.out.println(i + " : " + result.get(i));
//			}
//			
//			ECListDataMap.put(wordKey, result);
//		}
//		
//		return result;
//	}
//}
