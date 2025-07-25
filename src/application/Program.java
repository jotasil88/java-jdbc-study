package application;

import db.DB;

public class Program {

	public static void main(String[] args) {
		System.out.println("inicio");
		DB.getConnection();
		DB.closeConnection();
		System.out.println("fim");
	}
}
