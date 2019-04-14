package com.jsst.cloud.sql;

import java.util.ArrayList;
import java.util.List;

import com.jsst.cloud.utils.SqlConfig;

public class UserSqlProvider {

	public String getUserList() {
		return "SELECT * FROM cs_au_user;";
	}

	public String getUserInfo(String userCode, String password) {
		String sqlStr = SqlConfig.getResString("MDP_QUERY_USER");
		List<String> list=new ArrayList<>();
		list.add(userCode);
		list.add(password);
		System.out.println(list);
		for(String obj:list) {
			System.out.println(obj);
			sqlStr.replace("?", obj);
		}
		return sqlStr;
	}

	public static void main(String[] args) {
		UserSqlProvider usr = new UserSqlProvider();
		System.out.println(usr.getUserInfo("13632842844", "o111111"));
	}
}
