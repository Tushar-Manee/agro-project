package com.app.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "sub_category")
@NoArgsConstructor
@Getter
@Setter
public class SubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subcatg_id") 
	private int subCatgId;
	

	@Column(length = 20,name = "subcatg_name")
	private String subCatgName;
	
	@Column(length = 400)
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@Column(name = "total_stock")
	@NotNull(message = "stock should be zero or more")
	private double totalStock;
	
	@Lob
	@Column(name="subcatg_image")
	private byte[] subCatgImage;
	
	@OneToMany(mappedBy = "subcatg", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Product> products=new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public SubCategory(int subCatgId, String subCatgName, String description, double totalStock) {
		this.subCatgId = subCatgId;
		this.subCatgName = subCatgName;
		this.description = description;
		this.totalStock = totalStock;
	}

}
