package MainApplication;
import java.io.IOException;
import java.util.Scanner;
import User_Mangement.UserMangement;
import Product_Mangement.ProductMangement;

public class MainApplication {
	public static void main(String [] args) throws IOException {
		Scanner S = new Scanner (System.in);
		boolean Canikeeprunnningtheprogram = true;
		

		System.out.println("...>>> WELCOME TO SHOP MANEGEMENT SYSTEM <<<...");
		System.out.println("\n");
		System.out.println("Enter the Login_Name :");
		String Login_Name = S.nextLine();
		System.out.println("Enter the Password");
		String Password = S.nextLine();
		
		if(!UserMangement.LoginProcess(Login_Name ,Password)) {
			System.out.println("Login Failed Program closed");
			return;
		}
		while(Canikeeprunnningtheprogram) {
			System.out.println("...>>> WELCOME TO SHOP MANEGEMENT SYSTEM <<<...");
			System.out.println("1.User Mangement");
			System.out.println("2.Shop Mangement");
			System.out.println("3.Quit");
	
			int OptionSelectByUser = S.nextInt();

			if (OptionSelectByUser == 1) {
				UserMangement.User_Mangement();

			} else if (OptionSelectByUser == 2) {
				ProductMangement.ProductMangement();

			} else if (OptionSelectByUser == 3) {
				System.out.println("!...Program Closed...!");
				Canikeeprunnningtheprogram = false;
			}
		}
	}

}
