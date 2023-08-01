package main;

import java.util.Scanner;

public class Select extends Sql {

	public Select(String port, String databaseName, String userName, String password) {
		super(port, databaseName, userName, password);
	}

	public void mainSelect(int id) {
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("\n==================================================");
			System.out.println("\t\tSelect");
			System.out.println("==================================================");
			System.out.println("(1) Expense");
			System.out.println("(2) Income");
			System.out.println("(3) HomePage");
			System.out.print("Enter option: ");
			String option = input.next();
			Sql.LoadingMessage();

			if (option.equals("1")) {
				AllExpense(id);
				continue;
			} else if (option.equals("2")) {
				AllIncome(id);
				continue;
			} else if (option.equals("3")) {
//				break ;
				loop = false;
			} else {
				System.out.print("Invalid Option");
			}
		}
//		input.close();
		return;
	}

	public void AllExpense(int id) {
		System.out.println("\n---------------------[Expense]---------------------\n");
		System.out.println("Amount\t\tDate\t\tDescription");
		System.out.println("\n---------------------------------------------------\n");
		try {
			String query = "select amount as Amount, expense_date as Date, expense_desc as Description from expense where user_id = ? ;";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// System.out.print(9);
				System.out.println(
						resultSet.getBigDecimal(1) + "\t\t" + resultSet.getString(2) + "\t\t" + resultSet.getString(3));
			}
			System.out.println("\n---------------------[End]-----------------------\n");
			return;

		} catch (Exception ex) {
//			System.out.println(ex);
			return;
		}
	}

	public void AllIncome(int id) {
		System.out.println("\n---------------------[Income]---------------------\n");
		System.out.println("Amount\t\tDate\t\tDescription");
		System.out.println("\n---------------------------------------------------\n");
		try {
			String query = "select amount as Amount, income_date as Date, income_desc as Description from income where user_id = ? ;";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// System.out.print(9);
				System.out.println(
						resultSet.getBigDecimal(1) + "\t\t" + resultSet.getDate(2) + "\t\t" + resultSet.getString(3));
			}
			System.out.println("\n-----------------------[End]-----------------------\n");
			return;

		} catch (Exception ex) {
//			System.out.println(ex);
			return;
		}
	}

}
