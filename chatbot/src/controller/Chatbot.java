package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Chatbot {
	public static void main(String[] args) {

		String host = "jdbc:mysql://localhost:3306/chatbot";
		String userName = "root";
		String userPass = "java1234";
		// String dbName = "Chatbot";

		Connection conn = null;
		Statement stmt = null;

		try {

			while (true) {

				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(host, userName, userPass);
				stmt = conn.createStatement();

				// 사용자에게 질문을 받아야 함.
				System.out.println("검색할 키워드를 입력하세요 >> ");
				Scanner sc = new Scanner(System.in);

				// 사용자가 "에뛰드"라고 입력했을 경우. -> 변수 key에다가 사용자 입력값을 저장.
				String key = sc.nextLine();

				key = "%" + key + "%";

				String query = "";
				query += " select ";
				query += "	answer_value ";
				query += "from answer ";
				query += "where answer_value like '" + key + "'";

				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					System.out.println("답변 : " + rs.getString("answer_value"));
				}

			}

		} catch (Exception e) {
			System.out.println("DB 접속 에러");
		}

	}

}
