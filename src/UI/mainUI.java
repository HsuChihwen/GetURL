package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import HtmlParser.ParserHtml;
import bean.SRegionOutage;
import test.Chooser;

public class mainUI{
	
	/**
	 * 从95588中查询停电信息
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainUI();
	}

	public mainUI(){
		init();
	}
	
	public String[] province = {"南京市","无锡市","徐州市","常州市","苏州市","南通市","连云港市","淮安市","盐城市","扬州市","镇江市","泰州市","宿迁市"};
	
	private JFrame jFrame = new JFrame();
	private JPanel jPanel = new JPanel();
	private JLabel jLabel1 = new JLabel("开始时间:");
	private JLabel jLabel2 = new JLabel("结束时间:");
	private JLabel jLabel3 = new JLabel("停电区县:"); 
	private JLabel jLabel4 = new JLabel("停电区域:");
	private JLabel jLabel5 = new JLabel("停电范围:");
	private JLabel jLabel6 = new JLabel("*");
	private JLabel jLabel7 = new JLabel("*");
	private JLabel jLabel8 = new JLabel("*");
	private JTextField startTimeField = new JTextField();	//开始时间
	private JTextField stopTimeField = new JTextField();	//结束时间
	private JComboBox<String> areaTypeComboBoxProvince = new JComboBox<String>(province);	//停电区县
	private JComboBox<String> areaTypeComboboxCity = new JComboBox<String>();
	private JTextField facilityAreaField = new JTextField();//停电区域
	private JTextField scopeField = new JTextField();//停电范围
	private JButton jButton = new JButton("查询");
	private Chooser chooser = Chooser.getInstance("yyyy-MM-dd");
	private Chooser chooser2 = Chooser.getInstance("yyyy-MM-dd");
	private JTextArea jTextArea = new JTextArea();
	private JScrollPane jScrollPane = new JScrollPane(jTextArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	public SRegionOutage sRegionOutage = new SRegionOutage();
	public Logger logger = Logger.getLogger(mainUI.class);
	
	public void init(){
		jFrame.setBounds(400, 200, 800, 600);
		jFrame.add(jPanel);
		jPanel.setLayout(null);
		jFrame.setTitle("江苏省停电信息查询--by xzw");
		
		//开始时间
		jPanel.add(jLabel1);
		jLabel1.setBounds(40, 10, 100, 30);
		chooser.register(startTimeField);
		jPanel.add(startTimeField);
		startTimeField.setBounds(160,10,200,30);
		jPanel.add(jLabel6);
		jLabel6.setBounds(400, 10, 30, 30);
		jLabel6.setFont(new Font("serif", Font.PLAIN, 30));
		jLabel6.setForeground(Color.RED);
		
		//结束时间
		jPanel.add(jLabel2);
		jLabel2.setBounds(40, 50, 100, 30);
		chooser2.register(stopTimeField);
		jPanel.add(stopTimeField);
		stopTimeField.setBounds(160, 50, 200, 30);
		jPanel.add(jLabel7);
		jLabel7.setBounds(400, 50, 30, 30);
		jLabel7.setFont(new Font("serif", Font.PLAIN, 30));
		jLabel7.setForeground(Color.RED);
		
		//停电区县
		//市
		jPanel.add(jLabel3);
		jLabel3.setBounds(40, 90, 100, 30);
		jPanel.add(areaTypeComboBoxProvince);
		areaTypeComboBoxProvince.setBounds(160, 90, 100, 30);
		
		//区
		jPanel.add(areaTypeComboboxCity);
		areaTypeComboboxCity.setBounds(300, 90, 100, 30);
		
		jPanel.add(jLabel8);
		jLabel8.setBounds(420, 90, 30, 30);
		jLabel8.setFont(new Font("serif", Font.PLAIN, 30));
		jLabel8.setForeground(Color.RED);
		
		//停电区域
		jPanel.add(jLabel4);
		jLabel4.setBounds(40,130,100,30);
		jPanel.add(facilityAreaField);
		facilityAreaField.setBounds(160, 130, 200, 30);
		
		//停电范围
		jPanel.add(jLabel5);
		jLabel5.setBounds(40, 170, 100, 30);
		jPanel.add(scopeField);
		scopeField.setBounds(160, 170, 200, 30);
		
		//按钮
		jPanel.add(jButton);
		jButton.setBounds(380, 210, 100, 40);
		
		//内容显示
		jScrollPane.setBounds(10, 280, 760, 255);
		jPanel.add(jScrollPane);
		
		RegMouseListener regMouseListener = new RegMouseListener();
		jButton.addMouseListener(regMouseListener);
		
		RegActionListener regActionListener = new RegActionListener();
		areaTypeComboBoxProvince.addActionListener(regActionListener);
		
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * 注册鼠标点击事件
	 * @author xzw
	 *
	 */
	class RegMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			Object object = e.getSource();
			if(object == jButton){
				logger.info("鼠标点击\"查询\"按钮");
				Map<String, String> govCodeMap = new HashMap<>();
				govCodeMap = getProvince("./conf/Province.properties");
				//开始时间
				String startTime = startTimeField.getText();
				//停止时间
				String stopTime = stopTimeField.getText();
				//停电区域
				String facilityArea = facilityAreaField.getText();
				//停电市
				String province = (String) areaTypeComboBoxProvince.getSelectedItem();
				//停电区
				String city = (String) areaTypeComboboxCity.getSelectedItem();
				//停电范围
				String scope = scopeField.getText();
				if(startTime==null||startTime.equals("")){
					JOptionPane.showMessageDialog(null, "开始时间不能为空！");
					logger.info("开始时间不能为空！");
				}
				if(stopTime==null||stopTime.equals("")){
					JOptionPane.showMessageDialog(null, "结束时间不能为空！");
					logger.info("结束时间不能为空！");
				}
				if((province==null||province.equals(""))&&(city==null||city.equals(""))){
					JOptionPane.showMessageDialog(null, "请选择所在区域！");
					logger.info("请选择所在区域！");
				}
				
				if(province!=null&&!province.equals("")){
					if(city!=null&&!city.equals("")){
						sRegionOutage.setAreaType("region");
						sRegionOutage.setPoweroffArea(govCodeMap.get(city.trim()).trim());
					}else{
						city="";
						sRegionOutage.setPoweroffArea(govCodeMap.get(province.trim()).trim());
						sRegionOutage.setAreaType("city");
					}
				}
				sRegionOutage.setStartTime(startTime);
				sRegionOutage.setStopTime(stopTime);
				sRegionOutage.setCityName(province+city);
//				sRegionOutage.setPoweroffArea(govCodeMap.get(province));
				sRegionOutage.setFacilityArea(facilityArea);
				sRegionOutage.setScope(scope);
				System.out.println(sRegionOutage);
				ParserHtml parserHtml = new ParserHtml();
				List<String> infoList = parserHtml.parserHtml(sRegionOutage);
				jTextArea.setText(null);;
				if(infoList.isEmpty()){
					jTextArea.append("未查找到任何停电信息！");
				}else{
					for (String string : infoList) {
						jTextArea.append(string+"\n");
					}
				}
				
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	/**
	 * 注册ActionListener监听事件
	 * @author xzw
	 *
	 */
	class RegActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == areaTypeComboBoxProvince){
				int index = areaTypeComboBoxProvince.getSelectedIndex();
				Map<String, String> infoMap = getProvince("./conf/City.properties");
				String string = province[index];
				String str[] = infoMap.get(string).split(",");
				areaTypeComboboxCity.removeAllItems();
				for(int i=0;i<str.length;i++){
					areaTypeComboboxCity.addItem(str[i]);
				}
			}
		}
		
	}
	
	
	/**
	 * 读取properties中的全部信息存入MAP中
	 * @author xzw
	 * @return
	 */
	public Map<String, String> getProvince(String filePath){
		Properties properties = new Properties();
		InputStream inputStream = null;
		Map<String, String> proMap = new HashMap<String, String>();
		try {
			inputStream = new BufferedInputStream(new FileInputStream(filePath));
			properties.load(inputStream);
			Enumeration enumeration = properties.propertyNames();
			while(enumeration.hasMoreElements()){
				String key = (String) enumeration.nextElement();
				String value = properties.getProperty(key);
				proMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("未找到路径为："+filePath+"属性文件");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.info("在读取"+filePath+"文件时发生\n"+e.getMessage()+"\n异常");
		}
		
		return proMap;
	}
	
	
}
