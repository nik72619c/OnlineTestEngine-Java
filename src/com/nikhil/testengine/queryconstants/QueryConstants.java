package com.nikhil.testengine.queryconstants;

public interface QueryConstants {

	String LOGIN_SQL="SELECT USER_MST.userid,ROLE_MST.name as rolename, RIGHT_MST.name as rightname, "
			+ "RIGHT_MST.screenname from USER_MST, ROLE_MST, RIGHT_MST,USER_ROLE_MAPPING, ROLE_RIGHT_MAPPING "
			+ "where USER_MST.uid=USER_ROLE_MAPPING.uid AND ROLE_MST.roleid=USER_ROLE_MAPPING.roleid AND "
			+ "ROLE_MST.roleid=ROLE_RIGHT_MAPPING.roleid AND RIGHT_MST.id=ROLE_RIGHT_MAPPING.id AND "
			+ "USER_MST.userid=? AND USER_MST.password=?";
	String REGISTER_SQL="insert into USER_MST (userid,password) values(?,?)";
	String QUESTION_UPLOAD_SQL = "insert into question (quid, name,"
			+ "ans1,ans2,ans3,ans4,rans,score) values(?,?,?,?,?,?,?,?)";

	String QUESTION_FETCH_SQL = "select quid, name, ans1, ans2, ans3, ans4, rans, score from question";
			
}

