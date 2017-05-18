package com.bm.intercomscreening.domain;

public class Customer {

	public Customer(){}
			
	public Customer(Double latitude, Integer user_id, String name, Double longitude){
		this.latitude = latitude;
		this.user_id = user_id;
		this.name = name;
		this.longitude = longitude;
	}
	
	private Double latitude;
	private Integer user_id;
	private String name;
	private Double longitude;
	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
		
}
