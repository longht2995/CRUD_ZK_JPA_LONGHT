package greenglobal.longht.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="product")
public class Post implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="title",nullable=false)
	private String title;
	@Column(name="content",nullable=false)
	private String content;
	@Column(name="image")
	private byte[] image;
	@Temporal(TemporalType.TIMESTAMP)
	
	private java.util.Date date;
	@ManyToOne(fetch=FetchType.LAZY)
	private Category category;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public java.util.Date getDate() {
		return date;
	}
	@PrePersist
	public void setDate() {
		this.date = new Date();
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Post(int id, String title, String content, byte[] image, Date date, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.date = date;
		this.category = category;
	}
	public Post() {
		
	}
}
