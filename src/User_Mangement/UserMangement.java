package User_Mangement;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dbutils.DBUtils;

public class UserMangement {
	
	public static void User_Mangement() throws IOException {
		Scanner S = new Scanner(System.in);
		boolean CanIkeepRunningtheprogram = true;
		while (CanIkeepRunningtheprogram) {
			System.out.println("....Welcome to UserMangement....");
			System.out.println("1.Add User");
			System.out.println("2.Edit User");
			System.out.println("3.Delete User");
			System.out.println("4.Search User");
			System.out.println("5.Exit\n");

			int OptionselectbyUser = S.nextInt();
			if (OptionselectbyUser == UserOptions.Add) {
				AddUser();
			} else if (OptionselectbyUser == UserOptions.Edit) {
				System.out.println("Enter the UserName to Edit");
				S.nextLine();
				String EU = S.nextLine();
				EditUser(EU);
			} else if (OptionselectbyUser == UserOptions.Delete) {
				System.out.println("Enter the UserName to Detele");
				S.nextLine();
				String DU = S.nextLine();
				DeleteUser(DU);
			} else if (OptionselectbyUser == UserOptions.Search) {
				System.out.println("Enter the UserName to Search");
				S.nextLine();
				String SU = S.nextLine();
				SearchUser(SU);
			} else if (OptionselectbyUser == UserOptions.Exit) {

				System.out.println("!...Program Closed...!");
				CanIkeepRunningtheprogram = false;
			}
		}
	}

	public static void AddUser() {
		Scanner S = new Scanner(System.in);
		User U = new User();
		System.out.println("Enter the UseName :");
		U.UserName = S.nextLine();
		System.out.println("Enter the LoginName :");
		U.Login = S.nextLine();
		System.out.println("Enter the Password :");
		U.Password = S.nextLine();
		System.out.println("Enter the ConfirmPassword :");
		U.ConfirmPassword = S.nextLine();
		System.out.println("Enter the UserRole :");
		U.UserRole = S.nextLine();

		System.out.println("UserName : " + U.UserName);
		System.out.println("Login : " + U.Login);
		System.out.println("Password : " + U.Password);
		System.out.println("UserRole" + U.UserRole);

		String Query = "insert into User (User_Name ,Login_Name ,Password ,User_Role  )values ('" + U.UserName + "','"
				+ U.Login + "','" + U.Password + "','" + U.UserRole + "');";
		DBUtils.ExecuteQuery(Query);
	}

	public static void EditUser(String EditUser) {
		String Query = "select * from User where User_name = '"+EditUser+"'";

		ResultSet rs = DBUtils.executeQueryGetResult(Query);
		try {
			while (rs.next()) { // For-Each
				if (rs.getString("User_Name").equalsIgnoreCase(EditUser)) {
					Scanner scanner = new Scanner(System.in);
					User user = new User();
					
					System.out.println("Editing user: " + EditUser);
					System.out.print("New  User Name: ");
					user.UserName = scanner.nextLine();

					System.out.print("New Login Name: ");
					user.Login = scanner.nextLine();

					System.out.print("New Password: ");
					user.Password = scanner.nextLine();

					System.out.print("New Confirm Password: ");
					user.ConfirmPassword = scanner.nextLine();

					System.out.print("New User Role: ");
					user.UserRole = scanner.nextLine();
					
					String updateQuery = "update user set"
							+ " User_Name ='"+user.UserName+"', Login_Name = '" + user.Login + "', "
									+ " Password='" + user.Password + "', User_Role='" + user.UserRole + "' where User_Name='" + EditUser + "'";
					
					DBUtils.ExecuteQuery(updateQuery);

					System.out.println("User information updated.");

					return;
				}
			}
		} catch (Exception e) {
			System.out.println("User not found.");
		}


		}
	

	public static void DeleteUser(String DeleteUser) {
		String Query = "Delete from user where User_Name = '" + DeleteUser + "' ";
		DBUtils.ExecuteQuery(Query);
		System.out.println(DeleteUser + " has been deleted");
	}

	public static void SearchUser(String SearchUser) {
		String Query = "Select * from user where User_Name = '" + SearchUser + "'";
		ResultSet rs =DBUtils.executeQueryGetResult(Query);
		try {
			while (rs.next()) { // For-Each
				if (rs.getString("User_Name").equalsIgnoreCase(SearchUser)) {
					System.out.println("User Name: " + rs.getString("User_name"));
					System.out.println("Login Name: " + rs.getString("Login_Name"));
					System.out.println("Password: " + rs.getString("Password"));
					System.out.println("User Role: " + rs.getString("User_Role"));
					// break;
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("User not found.");
		}

		
		
	}

	public static boolean LoginProcess(String LoginName, String Password) {
		String Query = "Select * from user where Login_Name='"+LoginName+"' and Password='"+Password+"'";
		ResultSet rs = DBUtils.executeQueryGetResult(Query);
		try {
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
}

