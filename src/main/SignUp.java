package main;

import java.util.Scanner;

public class SignUp extends Sql {
	public SignUp(String port, String databaseName, String userName, String password) {
		super(port, databaseName, userName, password);
	}

	public void mainSignUp() {
		Scanner in = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("\n---------------------[SignUp]---------------------\n");
			System.out.print("Enter UserName : ");
			String name = in.next();
			System.out.print("Enter Email_Id : ");
			String email = in.next();
			System.out.print("Enter Password : ");
			String pass1 = in.next();
			System.out.print("Confirm Password : ");
			String pass2 = in.next();
			if (pass1.equals(pass2)) {
				boolean status = insertValues(name, email, pass1);
				if (status) {
					System.out.println("Sucessfully registered Login to enter");
					return;
				} else {
					System.out.println("Email already exists");
					System.out.println("(1) Wanna Retry ? ");
					System.out.println("(2) Need to Login ? ");
					System.out.print("Enter option :  ");
					String option = in.next();
					if (option.equals("1"))
						continue;
					else if (option.equals("2"))
						return;
				}

			} else {
				System.out.println("Passwords doesn't match");
				continue;
			}
//			if( status )
//				return true ;
//			else
//			{
//				System.out.println( "\nInvalid Email / Password" ) ;
//				System.out.println( "(1) Wanna Retry ? " ) ;
//				System.out.println( "(2) Need to SignUp ? " ) ;
//				System.out.print( "Enter option :  " ) ;
//				String option = in.next() ;
//				if( option.equals("1"))
//					continue ;
//				else if( option.equals("2"))
//					return false ;
//				
//			}
		}
		in.close();
		return;
	}

	public boolean insertValues(String username, String email, String password) {
		try {
			String query = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			int val = preparedStatement.executeUpdate();
//			System.out.println("Rows affected : " + val);
			return true;

		} catch (Exception ex) {
//			System.out.println(ex);
			return false;
		}
	}
}
