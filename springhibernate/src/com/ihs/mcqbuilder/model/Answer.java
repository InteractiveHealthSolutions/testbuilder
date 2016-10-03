package com.ihs.mcqbuilder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.ihs.mcqbuilder.audittrailfields.IAuditTrailFields;

@Entity
@Table(name = "answer")
public class Answer implements IAuditTrailFields
{
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "test_id")
	private Test test;
	
//	@NotNull
//	@Column(name = "test_id")
//	private Integer testId;

	 @NotNull
	 @OneToOne
	 @JoinColumn(name = "question_id")
	 private Question question = new Question();
	
//	@NotNull
//	@Column(name = "question_id")
//	private Integer questionId;

	// It will contain paragraph answer and MCQ related answer
	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AnswerData> answerDataList = new ArrayList<AnswerData>();

	@NotNull
	@Column(name = "creator_id")
	private Integer creatorId;

	@Column(name = "last_editor_id")
	private Integer lastEditorId;

	@Column(name = "checked")
	private Boolean checked;

	@Column(name = "marks_obtained")
	private Integer marksObtained;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_ts", updatable = false, nullable = false)
	private Date creationTS;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edited_ts")
	private Date lastEditedTS;

//	public Integer getTestId()
//	{
//		return test;
//	}
//
//	public void setTestId(Integer testId)
//	{
//		this.test = testId;
//	}

//	public Integer getQuestionId()
//	{
//		return questionId;
//	}
//
//	public void setQuestionId(Integer questionId)
//	{
//		this.questionId = questionId;
//	}

	public Test getTest()
	{
		return test;
	}

	public void setTest(Test test)
	{
		this.test = test;
	}

	public List<AnswerData> getAnswerDataList()
	{
		return answerDataList;
	}

	public void setAnswerDataList(List<AnswerData> answerDataList)
	{
		this.answerDataList = answerDataList;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	 public Question getQuestion()
	 {
	 return question;
	 }
	
	 public void setQuestion(Question question)
	 {
	 this.question = question;
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

	public Boolean getChecked()
	{
		return checked;
	}

	public void setChecked(Boolean checked)
	{
		this.checked = checked;
	}

	public Integer getMarksObtained()
	{
		return marksObtained;
	}

	public void setMarksObtained(Integer marksObtained)
	{
		this.marksObtained = marksObtained;
	}

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

}
