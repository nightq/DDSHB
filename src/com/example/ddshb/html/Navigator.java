package com.example.ddshb.html;

public class Navigator {

	public Navigator(String titleContent, String titleUrl) {
		super();
		this.titleContent = titleContent;
		this.titleUrl = titleUrl;
	}
	private String titleContent;
	private String titleUrl;
	public String getTitleContent() {
		return titleContent;
	}
	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}
	public String getTitleUrl() {
		return titleUrl;
	}
	public void setTitleUrl(String titleUrl) {
		this.titleUrl = titleUrl;
	}
	
}
