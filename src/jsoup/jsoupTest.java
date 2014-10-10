package jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class jsoupTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "http://95598.js.sgcc.com.cn/95598/powercolumn/getSRegionOutage.action?sRegionOutage.startTime=2014-09-26&sRegionOutage.stopDate=2014-05-14&sRegionOutage.areaType=region&sRegionOutage.poweroffArea=320102&sRegionOutage.cityName=南京市玄武区&sRegionOutage.facilityArea=&sRegionOutage.scope=";
		int page = parserPageNum(string);
		String str1 = "http://95598.js.sgcc.com.cn/95598/powercolumn/getSRegionOutage.action?";
		String str2 = "sRegionOutage.startTime=2014-05-08&sRegionOutage.stopDate=2014-05-14&sRegionOutage.areaType=region&sRegionOutage.poweroffArea=320102&sRegionOutage.cityName=南京市玄武区&sRegionOutage.facilityArea=&sRegionOutage.scope=";
		List<String> infoList = new ArrayList<>();
		for (int i = 1; i <= page; i++) {
			List<String> list = new ArrayList<>();
			String url;
			if (i == 1) {
				url = string;
				list = parserInfo(url);
			} else {
				url = str1 + "page=" + i + "&" + str2;
				list = parserInfo(url);
			}
			for (String string2 : list) {
				infoList.add(string2);
			}
		}

		for (String string2 : infoList) {
			System.out.println(string2);
		}

		// try {
		//
		// String str1 =
		// "http://95598.js.sgcc.com.cn/95598/powercolumn/getSRegionOutage.action?";
		// String str2 =
		// "sRegionOutage.startTime=2014-05-08&sRegionOutage.stopDate=2014-05-14&sRegionOutage.areaType=city&sRegionOutage.poweroffArea=320400&sRegionOutage.cityName=%E5%B8%B8%E5%B7%9E&sRegionOutage.facilityArea=&sRegionOutage.scope=";
		// int spanNum = Integer.parseInt(elements.get(0).text());
		// System.out.println("总页数:"+elements.get(0).text());
		// Elements links1 = null ;
		// if(spanNum>1){
		// for(int i=2;i<spanNum+1;i++){
		// String url = str1+"page="+i+"&"+str2;
		// System.out.println("地址："+url);
		// Document document1 = Jsoup.connect(string).timeout(5000).get();
		// Element element1 = document.getElementById("table_list");
		// links1 = element1.getElementsByTag("td");
		// }
		// }
		// for (Element element2 : links1) {
		// System.out.println(element2.text());
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static int parserPageNum(String url) {
		Document document = null;
		Elements elements = null;
		try {
			document = Jsoup.connect(url).timeout(5000).get();
//					.data("sRegionOutage.startTime", "2014-05-14")
//					.data("sRegionOutage.stopDate", "2014-05-16")
//					.data("sRegionOutage.areaType", "region")
//					.data("sRegionOutage.poweroffArea", "320102")
//					.data("sRegionOutage.cityName", "南京市玄武区")
//					.data("sRegionOutage.facilityArea", "")
//					.data("sRegionOutage.scope", "").get();
			elements = document.getElementsByClass("spanNum");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.parseInt(elements.get(0).text());
	}

	public static List<String> parserInfo(String url) {
		Document document = null;
		Element element = null;
		Elements elements = null;
		List<String> infoList = new ArrayList<>();
		try {
			document = Jsoup.connect(url).timeout(5000).get();
			element = document.getElementById("table_list");
			elements = element.getElementsByTag("td");
			for (Element string : elements) {
				infoList.add(string.text());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infoList;

	}
}
