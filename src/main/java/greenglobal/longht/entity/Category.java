package greenglobal.longht.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class Category implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	@OneToMany(mappedBy="category",cascade=CascadeType.ALL)
	private List<Post> product;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Post> getProduct() {
		return product;
	}
	public void setProduct(List<Post> product) {
		this.product = product;
	}
	public Category(int id, String name, List<Post> product) {
		this.id = id;
		this.name = name;
		this.product = product;
	}
	public Category() {
		
	}
	
}
