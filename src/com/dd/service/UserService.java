package com.dd.service;

import com.dd.utils.SqlHelper;

import java.util.ArrayList;

public class UserService {
	public ArrayList<Object[]> getUsers() {
		String sql = "select * from students";
		SqlHelper sqlHelper = new SqlHelper();
		return sqlHelper.executeQuery(sql, null);
	}
}
