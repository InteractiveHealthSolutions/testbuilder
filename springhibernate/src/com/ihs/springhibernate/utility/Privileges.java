package com.ihs.springhibernate.utility;

/**
 * Their Ids are set according to table of "privilege" in DB
 */
public enum Privileges
{	
	ADMIN(1), TEST_MAKER(2), TEST_CHECKER(3),
	
	/**	
	 * Person who can take test
	 */
	TEST_TAKER(4); // INACTIVE("I"), DELETED("D");

	private final int roleId;

	Privileges(int roleId)
	{
		this.roleId = roleId;
	}

	public int getRoleId()
	{
		return this.roleId;
	}
}
