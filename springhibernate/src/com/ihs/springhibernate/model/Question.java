package com.ihs.springhibernate.model;

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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.ihs.springhibernate.audittrailfields.IAuditTrailFields;

@Entity
@Table(name = "question")
public class Question implements IAuditTrailFields
{
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min=1)
	@Column(name = "title")
	private String title;
	
	@Size(min=1)
	@Column(name = "description")
	private String description;

//	@NotNull
//	@OneToOne
//	@JoinColumn(name = "answer_type_id")
//	private AnswerType answerType = new AnswerType();
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "question_type_id")
	private QuestionType questionType = new QuestionType();

	// It contains MCQ related options
	//@NotEmpty
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<QuestionData> questionDataList = new ArrayList<QuestionData>();

	@NotNull
	@Column(name = "max_marks")
	private Integer maxMarks;

	@Column(name = "comments_for_test_maker")
	private String commentsForMaker;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_ts", updatable = false, nullable = false)
	private Date creationTS; // = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edited_ts")
	private Date lastEditedTS;

	@NotNull
	@Column(name = "creator_id")
	private Integer creatorId;

	@Column(name = "last_editor_id")
	private Integer lastEditorId;

	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}	
	
	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	
//	public AnswerType getAnswerType()
//	{
//		return answerType;
//	}
//
//	public void setAnswerType(AnswerType answerType)
//	{
//		this.answerType = answerType;
//	}

	public QuestionType getQuestionType()
	{
		return questionType;
	}

	public void setQuestionType(QuestionType questionType)
	{
		this.questionType = questionType;
	}
	
	public List<QuestionData> getQuestionDataList()
	{
		return questionDataList;
	}

	public void setQuestionDataList(List<QuestionData> questionDataList)
	{
		this.questionDataList = questionDataList;
	}

	public Integer getCreatorId()
	{
		return creatorId;
	}

	public void setCreatorId(Integer creatorId)
	{
		this.creatorId = creatorId;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public Integer getMaxMarks()
	{
		return maxMarks;
	}

	public void setMaxMarks(Integer maxMarks)
	{
		this.maxMarks = maxMarks;
	}

	public String getCommentsForMaker()
	{
		return commentsForMaker;
	}

	public void setCommentsForMaker(String commentsForMaker)
	{
		this.commentsForMaker = commentsForMaker;
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

	public Integer getCraetorId()
	{
		return creatorId;
	}

	public void setCraetorId(Integer craetorId)
	{
		this.creatorId = craetorId;
	}

	public Integer getLastEditorId()
	{
		return lastEditorId;
	}

	public void setLastEditorId(Integer lastEditorId)
	{
		this.lastEditorId = lastEditorId;
	}

	@Override
	public boolean equals(Object v)
	{
		boolean retVal = false;

		if (v instanceof Question)
		{
			Question question = (Question) v;
			retVal = question.id.equals(this.id);
		}

		return retVal;
	}

	@Override
	public int hashCode()
	{
		int hash = 7;
		hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}
}
