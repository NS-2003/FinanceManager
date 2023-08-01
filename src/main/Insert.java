package main;

import java.sql.Date;
import java.util.Scanner;

public class Insert extends Sql {

	public Insert() {
		// TODO Auto-generated constructor stub
	}

	public Insert(String port, String databaseName, String userName, String password) {
		super(port, databaseName, userName, password);
	}

	public void mainInsert(int id) {
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("\n==================================================");
			System.out.println("\tInsert");
			System.out.println("==================================================");
			System.out.println("(1) Add Expense");
			System.out.println("(2) Add Income");
			System.out.println("(3) Homepage");
			System.out.print("Enter option: ");
			String option = input.next();
			Sql.LoadingMessage();

			if (option.equals("1")) {
				System.out.println("\n---------------------[Expense]---------------------\n");
				System.out.print("Enter Amount : ");
				double amount = Double.parseDouble(input.next());
				System.out.print("Enter Date(dd/mm/yyyy) : ");
				String date = input.next();
				System.out.print("Enter Description : ");
				String desc = input.next();
				boolean status = insertExpense(id, amount, date, desc);
				if (status) {
					System.out.println("Expense Added ");
					continue;
				} else {
					System.out.println("Invalid Input");
					continue;
				}
			} else if (option.equals("2")) {
				System.out.println("\n---------------------[Income]---------------------\n");
				System.out.print("Enter Amount : ");
				double amount = Double.parseDouble(input.next());
				System.out.print("Enter Date(dd/mm/yyyy) : ");
				String date = input.next();
				System.out.print("Enter Description : ");
				String desc = input.next();
				boolean status = insertIncome(id, amount, date, desc);
				if (status) {
					System.out.println("Income Added ");
					continue;
				} else {
					System.out.println("Invalid Input");
					continue;
				}
			} else if (option.equals("3")) {
				return;
			} else {
				System.out.println("Invalid Option ");
				continue;
			}

		}
		input.close();
		return;
	}

	public boolean insertExpense(int id, double amount, String date, String desc) {
		try {
			String query = "INSERT INTO expense (user_id, amount, expense_date , expense_desc) VALUES (?,?,?,?)";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setDouble(2, amount);
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, desc);
			int val = preparedStatement.executeUpdate();
//			System.out.println("Rows affected : " + val);
			return true;

		} catch (Exception ex) {
//			System.out.println(ex);
			return false;
		}
	}

	public boolean insertIncome(int id, double amount, String date, String desc) {
		try {
			String query = "INSERT INTO income (user_id, amount, income_date , income_desc) VALUES (?,?,?,?)";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setDouble(2, amount);
			preparedStatement.setString(3, date);
			preparedStatement.setString(4, desc);
			int val = preparedStatement.executeUpdate();
//			System.out.println("Rows affected : " + val);
			return true;

		} catch (Exception ex) {
//			System.out.println(ex);
			return false;
		}
	}

}
