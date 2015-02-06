package com.aiwo.server.pojo;

public class Us {

	private int usId;
	private String usContent;
	private String usTime;
	private String usTitle;
	private String usImage;

	public String getUsImage() {
		return usImage;
	}

	public void setUsImage(String usImage) {
		this.usImage = usImage;
	}

	public Us(int usId, String usContent, String usTime, String usTitle,
			String usImage) {
		super();
		this.usId = usId;
		this.usContent = usContent;
		this.usTime = usTime;
		this.usTitle = usTitle;
		this.usImage = usImage;
	}

	public int getUsId() {
		return usId;
	}

	public void setUsId(int usId) {
		this.usId = usId;
	}

	public String getUsContent() {
		return usContent;
	}

	public void setUsContent(String usContent) {
		this.usContent = usContent;
	}

	public String getUsTime() {
		return usTime;
	}

	public void setUsTime(String usTime) {
		this.usTime = usTime;
	}

	public String getUsTitle() {
		return usTitle;
	}

	public void setUsTitle(String usTitle) {
		this.usTitle = usTitle;
	}

	public Us() {
		super();
	}

}
