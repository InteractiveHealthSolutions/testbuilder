package com.ihs.springhibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	@NotNull
	@OneToMany
	@JoinTable(name = "scheme_category", joinColumns = @JoinColumn(name = "scheme_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<CategoryType> categoryType = new ArrayList<CategoryType>();


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


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CategoryType> getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(List<CategoryType> categoryType) {
		this.categoryType = categoryType;
	}
	
	
	
}
