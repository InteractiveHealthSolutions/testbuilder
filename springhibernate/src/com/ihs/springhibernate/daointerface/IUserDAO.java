package com.ihs.springhibernate.daointerface;

import java.util.List;

import com.ihs.springhibernate.model.User;

public interface IUserDAO
{
	public void save(User user);

	public List<User> getAllUser();

	public Boolean authenticate(User user);

	// public List<User> getSpecificUserList();
	//
	// public User getSpecificUser();
	//
	// public void updateUser(User user)
}
