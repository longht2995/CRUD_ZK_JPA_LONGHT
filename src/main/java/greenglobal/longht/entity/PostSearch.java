package greenglobal.longht.entity;

import java.util.Date;

public class PostSearch {
	private String key;
	private Category category;
	private java.util.Date datestart;
	private java.util.Date dateend;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public PostSearch(String key, Category category, Date datestart, Date dateend) {
		super();
		this.key = key;
		this.category = category;
		this.datestart = datestart;
		this.dateend = dateend;
	}
	public java.util.Date getDatestart() {
		return datestart;
	}
	public void setDatestart(java.util.Date datestart) {
		this.datestart = datestart;
	}
	public java.util.Date getDateend() {
		return dateend;
	}
	public void setDateend(java.util.Date dateend) {
		this.dateend = dateend;
	}
	public PostSearch() {
		
	}
	
}
