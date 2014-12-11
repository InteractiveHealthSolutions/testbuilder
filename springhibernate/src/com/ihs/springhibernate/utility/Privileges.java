package com.ihs.springhibernate.utility;

public enum Privileges
{
	ADMIN(1), TEST_MAKER(2); // INACTIVE("I"), DELETED("D");

	private final int roleId;

	Privileges(int roleId)
	{
		this.roleId = roleId;
	}

	public int getRoleId()
	{
		return this.roleId;
	}

	// public String getRoleId()
	// {
	// return this.roleId;
	// }

}
