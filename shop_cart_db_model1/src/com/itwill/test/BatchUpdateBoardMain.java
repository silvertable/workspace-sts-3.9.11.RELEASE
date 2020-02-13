﻿package com.itwill.test;
import java.sql.*;
public class BatchUpdateBoardMain 
{
	public static void main(String[] args) 
	{
		Connection con=null;
		Statement stmt=null;
		try{
		 con=ConnectionFactory.getConnection();
		 stmt=con.createStatement(
			 ResultSet.TYPE_SCROLL_SENSITIVE,
			 ResultSet.CONCUR_UPDATABLE);
		 con.setAutoCommit(false);

		 for(int i=1;i<=563;i++){
			stmt.addBatch(		
					"INSERT INTO board1 (boardno, title, writer, content, groupno, step)"
					+ " VALUES ("
					+ "board1_sequence.nextval,"
					+ "'게시판타이틀'||board1_sequence.currval ,"
					+ "'김경호'||board1_sequence.currval,"
					+ "'content'||board1_sequence.currval,"
					+ "board1_sequence.currval,"
					+ "1)"
			);
			
		 }
		 int[] updateCounts = stmt.executeBatch();
		 System.out.println("query 수:"+updateCounts.length);
		 con.commit();
		 System.out.println("success commit!!!!");
		}catch(SQLException e){
			e.printStackTrace();
			try{
				con.rollback();
				System.out.println("rollback !!!");
			}catch(SQLException e1){
				System.out.println("rollback fail!!!");
			}
		}finally{
			try{
				if(con!=null){
					con.close();
				}
			}catch(SQLException e){
				System.out.println("close 시 에러발생");
			}
		}

	}
}