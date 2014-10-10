package HtmlParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.html.HTMLDocument;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;
import org.htmlparser.visitors.TextExtractingVisitor;

public class GetURL {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL("http://95598.js.sgcc.com.cn/95598/powercolumn/getSRegionOutage.action?sRegionOutage.startTime=2014-05-08&sRegionOutage.stopDate=2014-05-14&sRegionOutage.areaType=city&sRegionOutage.poweroffArea=320400&sRegionOutage.cityName=%E5%B8%B8%E5%B7%9E&sRegionOutage.facilityArea=&sRegionOutage.scope=");
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
			System.out.println(sb);
			//get table
			
			
			
			Parser parser = new Parser(urlConnection);
			
//			TextExtractingVisitor visitor = new TextExtractingVisitor();
//			parser.visitAllNodesWith(visitor);
//			String string = visitor.getExtractedText();
//			System.out.println(string);

//			parser.setEncoding("UTF-8");
//			HtmlPage htmlPage = new HtmlPage(parser);
//			parser.visitAllNodesWith(htmlPage);
//			NodeList nodeList = htmlPage.getBody();
//			System.out.println(nodeList);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
