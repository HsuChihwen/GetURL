package HtmlParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.SRegionOutage;

/**
 * 解析HTML
 * @author xzw
 * 
 */
public class ParserHtml {

	private String urlString = "http://95598.js.sgcc.com.cn/95598/powercolumn/getSRegionOutage.action?";

	public List<String> parserHtml(SRegionOutage sRegionOutage) {
		String url = getUrl(sRegionOutage);
		System.out.println(urlString + url);
		boolean flag = isMorePage(urlString+url);
		List<String> infoList = new ArrayList<>();
		if(flag){
			int pageNum = parserPageNum(urlString + url);
			for (int i = 1; i <= pageNum; i++) {
				List<String> list = new ArrayList<>();
				if (i == 1) {
					list = parserInfo(urlString + url);
				} else {
					String temp = urlString + "page=" + i + "&" + url;
					list = parserInfo(temp);
				}
				for (String string2 : list) {
					infoList.add(string2);
				}
			}
		}
		return infoList;
	}

	/**
	 * 拼接URL
	 * 
	 * @param sRegionOutage
	 * @return
	 */
	private String getUrl(SRegionOutage sRegionOutage) {
		String string = "";
		string = "sRegionOutage.startTime=" + sRegionOutage.getStartTime()
				+ "&sRegionOutage.stopDate=" + sRegionOutage.getStopTime()
				+ "&sRegionOutage.areaType=" + sRegionOutage.getAreaType()
				+ "&sRegionOutage.poweroffArea="
				+ sRegionOutage.getPoweroffArea() + "&sRegionOutage.cityName="
				+ sRegionOutage.getCityName() + "&sRegionOutage.facilityArea="
				+ sRegionOutage.getFacilityArea() + "&sRegionOutage.scope="
				+ sRegionOutage.getScope();

		return string;
	}

	/**
	 * 解析总页数
	 * 
	 * @param url
	 * @return
	 */
	public int parserPageNum(String url) {
		Document document = null;
		Elements elements = null;
		try {
			document = Jsoup.connect(url).timeout(5000).post();
			elements = document.getElementsByClass("spanNum");
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		return Integer.parseInt(elements.get(0).text());
	}

	/**
	 * 解析网页内容
	 * 
	 * @param url
	 * @return
	 */
	public List<String> parserInfo(String url) {
		Document document = null;
		Element element = null;
		Elements elements = null;
		List<String> infoList = new ArrayList<>();
		try {
			document = Jsoup.connect(url).timeout(5000).post();
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
	
	/**
	 * 当前解析的HTML中是否有内容 false--没有 true--有
	 * @param url
	 * @return
	 */
	public boolean isMorePage(String url){
		List<String> infoList = parserInfo(url);
		int length = infoList.size();
		if(length==0||length==6||length==7){
			return false;
		}
		return true;
	}
}
