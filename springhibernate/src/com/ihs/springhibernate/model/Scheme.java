package com.ihs.springhibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="scheme")
public class Scheme  {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "total_questions")
	private String totalQuestions;
	
	
	@NotEmpty
	@OneToMany
	@JoinTable(name = "scheme_category", joinColumns = @JoinColumn(name = "scheme_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private CategoryType categoryType = new CategoryType();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(String totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}
	
	
	
}
