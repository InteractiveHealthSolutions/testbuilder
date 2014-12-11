package com.ihs.springhibernate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ihs.springhibernate.audittrailfields.IAuditTrailFields;

@Entity
@Table(name = "question_data")
public class QuestionData implements Comparable<QuestionData>, IAuditTrailFields
{
	@Id
	@GeneratedValue
	private Integer id;

//	@NotNull
//	@OneToOne
//	@JoinColumn(name = "question_type_id")
//	private QuestionType questionType = new QuestionType();

	@NotEmpty
	@Column(name = "data")
	private String data;

	@Column(name = "image_data")
	private byte[] imageData;

	@Transient
	private CommonsMultipartFile file;

	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	@Column(name = "creation_ts", updatable = false, nullable = false)
	private Date creationTS; // = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edited_ts")
	private Date lastEditedTS;

	@NotNull
	@Column(name = "_index")
	private Integer index;

	@PrePersist
	protected void onCreate()
	{
		this.creationTS = new Date();
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public CommonsMultipartFile getFile()
	{
		return file;
	}

	public void setFile(CommonsMultipartFile file)
	{
		this.file = file;
		this.imageData = file.getBytes();
		this.data = file.getOriginalFilename();
	}

//	public QuestionType getQuestionType()
//	{
//		return questionType;
//	}
//
//	public void setQuestionType(QuestionType questionType)
//	{
//		this.questionType = questionType;
//	}

	public String getData()
	{
		return data;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public byte[] getImageData()
	{
		return imageData;
	}

	public void setImageData(byte[] imagedata)
	{
		this.imageData = imagedata;
	}

	public Date getCreationTS()
	{
		return creationTS;
	}

	public void setCreationTS(Date creationTS)
	{
		this.creationTS = creationTS;
	}

	public Integer getIndex()
	{
		return index;
	}

	public void setIndex(Integer index)
	{
		this.index = index;
	}

	@Override
	public int compareTo(QuestionData questionData)
	{
		return this.getIndex() > questionData.getIndex() ? 1 : (this.getIndex() < questionData.getIndex() ? -1 : 0);
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

	@Override
	public boolean equals(Object v)
	{
		boolean retVal = false;

		if (v instanceof QuestionData)
		{
			QuestionData ptr = (QuestionData) v;
			//retVal = ptr.id == this.id;
			retVal = ptr.id.equals(this.id);
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

	// Collections.sort(_uniqueVoteList, Collections.reverseOrder());
}
