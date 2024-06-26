package com.poscodx.jblog.vo;

public class BlogVo {
	private String id;
	private String title;
	private String logo;
	
	public BlogVo() {
	}
	
	public BlogVo(String id, String title, String logo) {
		this.id = id;
		this.title = title;
		this.logo = logo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", title=" + title + ", logo=" + logo + "]";
	}
	
	
}
