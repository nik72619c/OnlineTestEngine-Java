package com.nikhil.testengine.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nikhil.testengine.queryconstants.QueryConstants;
import com.nikhil.testengine.util.CommonDAO;

public class UserDAO implements CommonDAO{
	
	public UserDTO loginUser(String userid,String password) throws ClassNotFoundException, SQLException {
		Connection connection=null;
		ArrayList<RightDTO> rights=null;
		ResultSet resultset=null;
		PreparedStatement preparedstmt=null;
		UserDTO userDTO = null;
		try {
		connection= CommonDAO.setupConnection();
		
		preparedstmt=connection.prepareStatement(QueryConstants.LOGIN_SQL);
		preparedstmt.setString(1, userid);
		preparedstmt.setString(2, password);
		 resultset=preparedstmt.executeQuery();
		
		while(resultset.next()) {
			
			if(userDTO==null) {
				
				userDTO= new UserDTO();
				userDTO.setUserid(resultset.getString("userid"));
				userDTO.setRolename(resultset.getString("rolename"));
				rights= new ArrayList<>();
				userDTO.setRights(rights);
			}
			
			RightDTO right= new RightDTO();
			right.setName(resultset.getString("rightname"));
			right.setScreenname(resultset.getString("screenname"));
			rights.add(right);
			System.out.println("userDTO filled from the lpgin query is :-"+userDTO.getUserid());
			
		}
		

		return userDTO;
	}
		
		finally {
		
		if(resultset!=null) {
			
			resultset.close();
		}
		
		if(preparedstmt!=null) {
			
			preparedstmt.close();
		}
		
		if(connection!=null) {
			
			connection.close();
		}
		
	}
	}
	
	public String registerUser(RegisterClass object) throws SQLException, ClassNotFoundException {
		
		Connection connection=null;
		
		PreparedStatement preparedstmt=null;

		try {
			connection= CommonDAO.setupConnection();
			
			preparedstmt=connection.prepareStatement(QueryConstants.REGISTER_SQL);
			preparedstmt.setString(1, object.username);
			preparedstmt.setString(2, object.password);
			
			//more fields are to be added here in the future with username and password
			 int RecordsCount=preparedstmt.executeUpdate();
			 
			 return RecordsCount>0?"U Registered Successfully !":"Could Not Register";
			
		}
			
			finally {
			
	
			if(preparedstmt!=null) {
				
				preparedstmt.close();
			}
			
			if(connection!=null) {
				
				connection.close();
			}
			
		}
		
	}

	public UserDAO() {
		// TODO Auto-generated constructor stub
	}

}
