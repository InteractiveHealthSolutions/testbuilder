package com.ihs.mcqbuilder.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.ihs.mcqbuilder.audittrailfields.IAuditTrailFields;
import com.ihsinformatics.util.MDHashUtil;

@Entity
@Table(name = "user")
public class User implements IAuditTrailFields
{
	@Id
	@GeneratedValue
	private Integer id;

	@NotEmpty
	private String name;

	@NotEmpty
	private String password;

	@NotEmpty
	private String login_Id;
	// private Integer role_id;

	@OneToOne
	@JoinColumn(name = "role_id")
	private Role role = new Role();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "creation_ts")
	private Date creationTS = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_edited_ts")
	private Date lastEditedTS;

	@NotNull
	@Column(name = "creator_id")
	private Integer creatorId;

	@Column(name = "last_editor_id")
	private Integer lastEditorId;

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

	public String getLogin_Id()
	{
		return login_Id;
	}

	public void setLogin_Id(String login_Id)
	{
		this.login_Id = login_Id;
	}

	public Date getCreationTS()
	{
		return creationTS;
	}

	public void setCreationTS(Date creationTS)
	{
		this.creationTS = creationTS;
	}

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

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
   	  String hashPass = MDHashUtil.getHashString(password);
		this.password = hashPass;
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

	public void setCreatorId(Integer craetorId)
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

}
