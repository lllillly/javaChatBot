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

				// ����ڿ��� ������ �޾ƾ� ��.
				System.out.println("�˻��� Ű���带 �Է��ϼ��� >> ");
				Scanner sc = new Scanner(System.in);

				// ����ڰ� "���ٵ�"��� �Է����� ���. -> ���� key���ٰ� ����� �Է°��� ����.
				String key = sc.nextLine();

				key = "%" + key + "%";

				String query = "";
				query += " select ";
				query += "	answer_value ";
				query += "from answer ";
				query += "where answer_value like '" + key + "'";

				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					System.out.println("�亯 : " + rs.getString("answer_value"));
				}

			}

		} catch (Exception e) {
			System.out.println("DB ���� ����");
		}

	}

}
