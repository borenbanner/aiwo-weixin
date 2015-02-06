package com.aiwo.server.pojo;

public class Product {

	private int proId;
	private String proName;
	private String proPrice;
	private String proDetail;
	private String proImage;
	private String proTime ;
	

	public Product(int proId, String proName, String proPrice,
			String proDetail, String proImage, String proTime) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.proPrice = proPrice;
		this.proDetail = proDetail;
		this.proImage = proImage;
		this.proTime = proTime;
	}

	public String getProTime() {
		return proTime;
	}

	public void setProTime(String proTime) {
		this.proTime = proTime;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProPrice() {
		return proPrice;
	}

	public void setProPrice(String proPrice) {
		this.proPrice = proPrice;
	}

	public String getProDetail() {
		return proDetail;
	}

	public void setProDetail(String proDetail) {
		this.proDetail = proDetail;
	}

	public String getProImage() {
		return proImage;
	}

	public void setProImage(String proImage) {
		this.proImage = proImage;
	}

	
	public Product() {
		super();
	}

}
