package com.increff.employee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.sql.ResultSet;

public class employeetest {
	@Test
	public void sayHello() throws Exception {
		Employeeapi api=new Employeeapi();
		api.delete();
		api.insert();
		ResultSet rs=api.select();
		int i=0;
		while(rs.next())
		{
			i++;
		}
		assertEquals(3,i);
	}

}
