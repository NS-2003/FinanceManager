package main;

import java.util.Scanner;

public class Login extends Sql {
	public Login(String port, String databaseName, String userName, String password) {
		super(port, databaseName, userName, password);
	}

	public int mainLogin() {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("\n---------------------[Login]---------------------\n");
			System.out.print("Enter Email_Id : ");
			String email = in.next();
			System.out.print("Enter Password : ");
			String password = in.next();
			int status = checkLogin(email, password);
			if (status != 0)
				return status;
			else {
				System.out.println("\nInvalid Email / Password");
				System.out.println("(1) Wanna Retry ? ");
				System.out.println("(2) Need to SignUp ? ");
				System.out.print("Enter option :  ");
				String option = in.next();
				if (option.equals("1"))
					continue;
				else if (option.equals("2"))
					return 0;

			}
		}
		in.close();
		return 0;
	}

	public int checkLogin(String email, String password) {
		try {
			resultSet = statement.executeQuery("select user_id , password from users where email = '" + email + "' ; ");

			while (resultSet.next()) {
				String pass = resultSet.getString("password");
				if (pass.equals(password)) {
					return resultSet.getInt("user_id");
				}
			}

		} catch (Exception ex) {
//			System.out.println(ex);
			return 0;
		}
		return 0;
	}
}
