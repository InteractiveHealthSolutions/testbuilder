package com.ihs.springhibernate.model;

import java.util.ArrayList;
import java.util.Date;
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

import com.ihs.springhibernate.audittrailfields.IAuditTrailFields;

@Entity
@Table(name = "test")
public class Test implements IAuditTrailFields
{
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "comments")
	private String comments;

	@NotEmpty
	@OneToMany
	@JoinTable(name = "test_question", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questionList = new ArrayList<Question>();
	
//	@NotEmpty
//	@OneToMany
//	@JoinTable(name = "test_question", joinColumns = @JoinColumn(name = "test_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
//	List<List<Question>> questionCollection = new ArrayList<List<Question>>();

	// @Column(name = "test_maker_id")
	// private Integer testMakerId;

	@Column(name = "creation_ts", updatable = false, nullable = false)
	private Date creationTS; // = new Date();

	@Column(name = "last_edited_ts")
	private Date lastEditedTS; // = new Date();

	@Column(name = "creator_id")
	private Integer creatorId;

	@Column(name = "last_editor_id")
	private Integer lastEditorId;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name= "scheme_id")
	private Scheme scheme = new Scheme();

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public List<Question> getQuestionList()
	{
		return questionList;
	}

	public void setQuestionList(List<Question> questionList)
	{
		this.questionList = questionList;
	}

	// public Integer getTestMakerId()
	// {
	// return testMakerId;
	// }
	//
	// public void setTestMakerId(Integer testMakerId)
	// {
	// this.testMakerId = testMakerId;
	// }
	
	@Override
	public Date getCreationTS()
	{
		return creationTS;
	}

	@Override
	public void setCreationTS(Date creationTS)
	{
		this.creationTS = creationTS;
	}

	@Override
	public Date getLastEditedTS()
	{
		return lastEditedTS;
	}

	@Override
	public void setLastEditedTS(Date lastEditedTS)
	{
		this.lastEditedTS = lastEditedTS;
	}

	public Integer getCreatorId()
	{
		return creatorId;
	}

	public void setCreatorId(Integer creatorId)
	{
		this.creatorId = creatorId;
	}

	public Integer getLastEditorId()
	{
		return lastEditorId;
	}

	public void setLastEditorId(Integer lastEditorId)
	{
		this.lastEditorId = lastEditorId;
	}

	public Scheme getScheme() {
		return scheme;
	}

	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}

//	public List<List<Question>> getQuestionCollection() {
//		return questionCollection;
//	}
//
//	public void setQuestionCollection(List<List<Question>> questionCollection) {
//		this.questionCollection = questionCollection;
//	}
}
