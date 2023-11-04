package Product_Mangement;


import java.io.IOException;
import java.sql.ResultSet;
import java.util.Scanner;

import dbutils.DBUtils;

public class ProductMangement {

	public static void ProductMangement() throws IOException {
		Scanner s = new Scanner(System.in);
		boolean CanIKeepRunningTheProgram = true;
		while (CanIKeepRunningTheProgram == true) {
			System.out.println(">>>>>>..ProductMangement..<<<<<<");
			System.out.println("1.AddProduct");
			System.out.println("2.EditProduct");
			System.out.println("3.DeleteProduct");
			System.out.println("4.SearchProduct");
			System.out.println("5.Quit");

			int OptionSelectbyUser = s.nextInt();

			if (OptionSelectbyUser == ProductOptions.AddProduct) {
				AddProduct();
			} else if (OptionSelectbyUser == ProductOptions.EditProduct) {
				System.out.println("Enter the Product Name to edit :");
				s.nextLine();
				String PEdit = s.nextLine();
				EditProduct(PEdit);
			} else if (OptionSelectbyUser == ProductOptions.DeleteProduct) {
				System.out.println("Enter the Product Name to Delete");
				s.nextLine();
				String PDelete = s.nextLine();
				DeleteProduct(PDelete);
			} else if (OptionSelectbyUser == ProductOptions.SearchProduct) {
				System.out.println("Enter the Product Name ");
				s.nextLine();
				String PSearch = s.nextLine();
				SearchProduct(PSearch);
			} else if (OptionSelectbyUser == ProductOptions.Quit) {

				System.out.println("!...Program closed...!");
				CanIKeepRunningTheProgram = false;
			}
		}
	}

	public static void AddProduct() {
		Scanner s = new Scanner(System.in);
		Product PO = new Product();
		System.out.println("Enter the ProductName ");
		PO.ProductName = s.nextLine();
		System.out.println("Enter the ProductID ");
		PO.ProductID = s.nextLine();
		System.out.println("Enter the ProductPrice");
		PO.ProductPrice = s.nextLine();
		System.out.println("Enter the ProductQuantity");
		PO.ProductQuantity = s.nextLine();
		System.out.println("Enter the ProductCategory");
		PO.ProductCategory = s.nextLine();

		System.out.println("\n");
		String Query = "insert into Product (Product_Name, ProductID , ProductPrice , ProductQuantity , ProductCategory  )values ('"
				+ PO.ProductName + "','" + PO.ProductID + "','" + PO.ProductPrice + "','" + PO.ProductQuantity + "' , '"
				+ PO.ProductCategory + "');";
		DBUtils.ExecuteQuery(Query);

	}

	public static void EditProduct(String PEdit) {
		String Query = "select * from Product where Product_Name = '" + PEdit + "'";

		ResultSet rs = DBUtils.executeQueryGetResult(Query);
		try {
			while (rs.next()) { // For-Each
				if (rs.getString("Product_Name").equalsIgnoreCase(PEdit)) {
					Product P = new Product();
					Scanner s = new Scanner(System.in);
					System.out.println("Enter the ProductName ");
					P.ProductName = s.nextLine();
					System.out.println("Enter the ProductID ");
					P.ProductID = s.nextLine();
					System.out.println("Enter the ProductPrice");
					P.ProductPrice = s.nextLine();
					System.out.println("Enter the ProductQuantity");
					P.ProductQuantity = s.nextLine();
					System.out.println("Enter the ProductCategory");
					P.ProductCategory = s.nextLine();
					System.out.println("Information Updated..");

					String updateQuery = "update Product set "
							+ "Product_Name='" + P.ProductName + "', ProductID = '" + P.ProductID + "', "
									+ "ProductPrice='" + P.ProductPrice + "', ProductQuantity='" + P.ProductQuantity + "', "
											+ "ProductCategory='" + P.ProductCategory + "' where Product_Name='" + PEdit + "' ";
					DBUtils.ExecuteQuery(updateQuery);

					System.out.println("Product information updated.");

					return;
				}
			}
		} catch (Exception e) {
			System.out.println("User not found.");
		}

	}

	public static void DeleteProduct(String PDelete) {
		String Query = "Delete from Product where Product_Name = '" + PDelete + "'";
		DBUtils.ExecuteQuery(Query);
		System.out.println(PDelete + " has been deleted");
	}

	public static void SearchProduct(String PSearch) {
		String Query = "Select * from Product where Product_Name ='" + PSearch + "'";
		ResultSet rs = DBUtils.executeQueryGetResult(Query);
		try {
			while (rs.next()) { // For-Each
				if (rs.getString("Product_Name").equalsIgnoreCase(PSearch)) {
					System.out.println("ProductID :" + rs.getString("ProductID"));
					System.out.println("ProductName :" + rs.getString("Product_Name"));
					System.out.println("ProductPrice :" + rs.getString("ProductPrice"));
					System.out.println("ProductQuantity :" + rs.getString("ProductQuantity"));
					System.out.println("ProductCategory :" + rs.getString("ProductCategory"));
				}
			}
		} catch (Exception e) {
			System.out.println("User not found.");
		}

	}

}