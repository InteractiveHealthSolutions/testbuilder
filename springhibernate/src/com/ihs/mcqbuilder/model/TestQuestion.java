package com.ihs.mcqbuilder.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="test_question")
public class TestQuestion {

	@Id
	@GeneratedValue
	private Integer id;
	
	private Integer question_id;
	@ManyToOne(fetch =  FetchType.LAZY)
	@JoinColumn(name = "question_id", insertable = false, updatable = false)
	@ForeignKey(name = "fk_test_with_questions_question")
	private Question question;
	
	private Integer test_id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "test_id", insertable = false, updatable = false)
	@ForeignKey(name = "fk_test_with_questions_test1")
	private Test test;
	
	@Column(name = "creation_ts", updatable = false, nullable = false)
	private Date creationTS;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(Integer question_id) {
		this.question_id = question_id;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Integer getTest_id() {
		return test_id;
	}

	public void setTest_id(Integer test_id) {
		this.test_id = test_id;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Date getCreationTS() {
		return creationTS;
	}

	public void setCreationTS(Date creationTS) {
		this.creationTS = creationTS;
	}
}
