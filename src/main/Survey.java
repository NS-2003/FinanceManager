package main;

public class Survey extends Sql {

	public Survey() {
		// TODO Auto-generated constructor stub
	}

	public Survey(String port, String databaseName, String userName, String password) {
		super(port, databaseName, userName, password);
	}

	public void mainSurvey(int id) {
		int sExpense = sumExpense(id);
		int sIncome = sumIncome(id);
		System.out.println("\n---------------------[Survey]---------------------\n");
		System.out.println("Expense : " + sExpense);
		System.out.println("Income  : " + sIncome);

		if (sExpense > sIncome) {
			System.out.println("Your expense is higher than your income");
		} else if (sExpense < sIncome) {
			System.out.println("Good , your income is higher than your expense");
		} else if (sExpense == sIncome) {
			System.out.println("You just barely managed expense with your income");
		}
		int div = gcd(sExpense, sIncome);
//		System.out.println("GCD : " + div );
		System.out.println("The ratio of Expense and Income : " + sExpense / div + " : " + sIncome / div);

		return;
	}

	public int sumExpense(int id) {
		try {
			String query = "select amount as Amount from expense where user_id = ? ;";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			int sExpense = 0;
			while (resultSet.next()) {
				sExpense += resultSet.getInt(1);
			}
			return sExpense;

		} catch (Exception ex) {
//			System.out.println(ex);
			return 0;
		}
	}

	public int sumIncome(int id) {
		try {
			String query = "select amount as Amount from income where user_id = ? ;";
			preparedStatement = connect.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			int sIncome = 0;
			while (resultSet.next()) {
				sIncome += resultSet.getInt(1);
			}
			return sIncome;

		} catch (Exception ex) {
//			System.out.println(ex);
			return 0;
		}
	}

	public int gcd(int a, int b) {
		// Everything divides 0
		if (a == 0 && b == 0)
			return 1;
		if (a == 0)
			return b;
		if (b == 0)
			return a;

		// Base case
		if (a == b)
			return a;

		// a is greater
		if (a > b)
			return gcd(a - b, b);
		return gcd(a, b - a);
	}
}
