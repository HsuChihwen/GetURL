package bean;

public class SRegionOutage {
	
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 停止时间
	 */
	private String stopTime;
	/**
	 * 停电区县 city region
	 */
	private String areaType;
	/**
	 * 区域行政代码
	 */
	private String poweroffArea;
	private String cityName;
	/**
	 * 停电区域
	 */
	private String facilityArea;
	/**
	 * 停电范围
	 */
	private String scope;
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getStopTime() {
		return stopTime;
	}
	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}
	public String getAreaType() {
		return areaType;
	}
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	public String getPoweroffArea() {
		return poweroffArea;
	}
	public void setPoweroffArea(String poweroffArea) {
		this.poweroffArea = poweroffArea;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getFacilityArea() {
		return facilityArea;
	}
	public void setFacilityArea(String facilityArea) {
		this.facilityArea = facilityArea;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "SRegionOutage [startTime=" + startTime + ", stopTime="
				+ stopTime + ", areaType=" + areaType + ", poweroffArea="
				+ poweroffArea + ", cityName=" + cityName + ", facilityArea="
				+ facilityArea + ", scope=" + scope + "]";
	}
	
	
}
