package com.ihs.springhibernate.utility;

public enum QuestionTypes
{
	PARAGRAPH(1),  MULTIPLE_CHOICE(2), SINGLE_CHOICE(3) ; // DELETED("D");

	private final int typeId;

	QuestionTypes(int typeId)
	{
		this.typeId = typeId;
	}

	public int getTypeId()
	{
		return this.typeId;
	}
}
