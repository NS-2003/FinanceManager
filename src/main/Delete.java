package main;

import java.util.Scanner;

public class Delete extends Sql {

	public Delete() {
		// TODO Auto-generated constructor stub
	}

	public Delete(String port, String databaseName, String userName, String password) {
		super(port, databaseName, userName, password);
	}

	public void mainDelete(int id) {
		Scanner input = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("\n==================================================");
			System.out.println("\tDelete");
			System.out.println("==================================================");
			System.out.println("(1) Delete Expense");
			System.out.println("(2) Delete Income");
			System.out.println("(3) Homepage");
			System.out.print("Enter option: ");
			String option = input.next();
			Sql.LoadingMessage();
			if (option.equals("1")) {
				AllValue(id , "Expense");
				continue;
			} else if (option.equals("2")) {
				AllValue(id);
				continue;
			} else if (option.equals("3")) {
				return;
			}
		}
	}

	public void AllValue(int id , String type) {
		Scanner input = new Scanner(System.in);
		System.out.println("\n---------------------[Expense]---------------------\n");
		System.out.println("Id\t\tAmount\t\tDate\t\tDescription");
		System.out.println("\n---------------------------------------------------\n");
		try {
			String query = "select expense_id as id , amount as Amount, expense_date as Date, expense_desc as Description from expense where user_id = ? ;";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// System.out.print(9);
				System.out.println(resultSet.getInt(1) + "\t\t" + resultSet.getBigDecimal(2) + "\t\t"
						+ resultSet.getString(3) + "\t\t" + resultSet.getString(4));
			}
			System.out.println("\n---------------------[End]-----------------------\n");

			System.out.print("Enter Id of Expense need to be Delete : ");
			int val = Integer.parseInt(input.next());
			DeleteVal(val, "expense", id);
			return;

		} catch (Exception ex) {
//			System.out.println(ex);
			return;
		}
	}

	public void AllValue(int id) {
		Scanner input = new Scanner(System.in);
		System.out.println("\n---------------------[Income]---------------------\n");
		System.out.println("Id\t\tAmount\t\tDate\t\tDescription");
		System.out.println("\n---------------------------------------------------\n");
		try {
			String query = "select income_id as id , amount as Amount, income_date as Date, income_desc as Description from income where user_id = ? ;";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				// System.out.print(9);
				System.out.println(resultSet.getInt(1) + "\t\t" + resultSet.getBigDecimal(2) + "\t\t"
						+ resultSet.getString(3) + "\t\t" + resultSet.getString(4));
			}
			System.out.println("\n---------------------[End]-----------------------\n");

			System.out.print("Enter Id of Income need to be Delete : ");
			int val = Integer.parseInt(input.next());
			DeleteVal(val, "income", id);
			return;

		} catch (Exception ex) {
//			System.out.println(ex);
			return;
		}
	}

	public void DeleteVal(int id, String type, int user) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter your password for security : ");
			String password = input.next();
			String query = "delete x from " + type + " x join users u on x.user_id = u.user_id  where x." + type
					+ "_id = " + id + " && u.user_id = " + user + " && u.password = " + password + " ;";
			int val = statement.executeUpdate(query);
//			System.out.println( val ) ;
			if (val != 0)
				System.out.println("Succesfully Deleted");
			else
				System.out.println("Select valid Id");
		} catch (Exception ex) {
			System.out.println(ex);
			return;
		}
	}
}
