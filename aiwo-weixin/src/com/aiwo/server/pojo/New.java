package com.aiwo.server.pojo;

public class New {

	private int newId;
	private String newTime;
	private String newTitle;
	private String newContent;
	private String newImage;

	public int getNewId() {
		return newId;
	}

	public void setNewId(int newId) {
		this.newId = newId;
	}

	public String getNewTime() {
		return newTime;
	}

	public void setNewTime(String newTime) {
		this.newTime = newTime;
	}

	public String getNewTitle() {
		return newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

	public String getNewContent() {
		return newContent;
	}

	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}

	public String getNewImage() {
		return newImage;
	}

	public void setNewImage(String newImage) {
		this.newImage = newImage;
	}

	public New(int newId, String newTime, String newTitle, String newContent,
			String newImage) {
		super();
		this.newId = newId;
		this.newTime = newTime;
		this.newTitle = newTitle;
		this.newContent = newContent;
		this.newImage = newImage;
	}

	public New() {
		super();
	}

}
