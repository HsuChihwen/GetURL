package HtmlParser;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.IsEqualFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

public class GetURL1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(
					"http://95598.js.sgcc.com.cn/95598/powercolumn/getSRegionOutage.action?sRegionOutage.startTime=2014-05-08&sRegionOutage.stopDate=2014-05-14&sRegionOutage.areaType=city&sRegionOutage.poweroffArea=320400&sRegionOutage.cityName=%E5%B8%B8%E5%B7%9E&sRegionOutage.facilityArea=&sRegionOutage.scope=");
			URLConnection urlConnection = url.openConnection();
			urlConnection.setDoOutput(true);
			urlConnection.connect();
			String temp;
			final StringBuffer sb = new StringBuffer();
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), "UTF-8"));
			while ((temp = in.readLine()) != null) {
				sb.append("\n");
				sb.append(temp);
			}
			in.close();
			

			Parser parser = new Parser(urlConnection);
			NodeFilter trFilter = new TagNameFilter("td");
			ArrayList<String> stringList = new ArrayList<>();
			for (NodeIterator tableIterator = parser.extractAllNodesThatMatch(trFilter).elements(); tableIterator.hasMoreNodes();) {
				Node node = tableIterator.nextNode();
				String string = node.toPlainTextString().replace(" ", "");
				System.out.println("getPlainText:"+ node.toPlainTextString().replaceAll(" ", "").trim());
//				stringList.add(string);
			}
			ArrayList<Map<String, String>> infoArrayListMaps = new ArrayList<Map<String,String>>();
			for(int i=2;i<stringList.size();i++){
				StringBuffer stringBuffer = new StringBuffer();
				String string = stringList.get(i).replace(" ", "").trim();
				if(string!=null&&!string.equals(" ")){
					String str[] = string.trim().split("\r");
					for(int j=0;j<str.length;j++){
						String str1 = str[j].trim();
						if(str1!=null&&!str1.equals("")){
							stringBuffer.append(str1+"!");
						}
					}
					String temp1 = stringBuffer.toString();
					String[] newStrings = temp1.split("!");
					Map<String, String> map = new HashMap<String, String>();
					map.put("序号", newStrings[0]);
					map.put("停电区县", newStrings[1]);
					map.put("停电范围", newStrings[2]);
					map.put("停电开始时间", newStrings[3]);
					map.put("停电结束时间", newStrings[4]);
					map.put("停电线路", newStrings[5]);
					infoArrayListMaps.add(map);
				}
				
			}
			
			
			for (Map<String, String> map : infoArrayListMaps) {
				System.out.println("序号："+map.get("序号"));
				System.out.println("停电区县："+map.get("停电区县"));
				System.out.println("停电范围："+map.get("停电范围"));
				System.out.println("停电开始时间："+map.get("停电开始时间"));
				System.out.println("停电结束时间："+map.get("停电结束时间"));
				System.out.println("停电线路："+map.get("停电线路"));
				System.out.println("--------------------------------------------");
				
			}
			
//			NodeFilter spanFilter = new TagNameFilter("span");
//			for(NodeIterator nodeIterator = parser.extractAllNodesThatMatch(spanFilter).elements();nodeIterator.hasMoreNodes();){
//				Node node = nodeIterator.nextNode();
////				String string = node.toPlainTextString().replace(" ", "");
//				System.out.println("getPlainText:"+ node.toPlainTextString().replaceAll(" ", ""));
//			}
			
//			HtmlPage htmlPage = new HtmlPage(parser);
//			NodeList bodyNodeList = htmlPage.getBody();
//			
//			for (int i=0;i<bodyNodeList.size();i++) {
//				Node bodyNode = bodyNodeList.extractAllNodesThatMatch(trFilter).elementAt(i);
//				System.out.println(bodyNode.toPlainTextString());
//			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
