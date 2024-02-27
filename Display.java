import java.util.Scanner;
import java.io.*;

public class Display {
	public static void main(String[] args) {
		
		// Initialize Scanner objects for user input
		Scanner x = new Scanner(System.in);
		Scanner y = new Scanner(System.in);
		Scanner z = new Scanner(System.in);

		// Initialize boolean flags for program control
		boolean check = false;      // Flag to track payment status
		boolean checkadd = false;   // Flag to control the Buy Product sub-menu loop
		boolean exit = false;       // Flag to exit the main program for user loop
		boolean admincheck = false; // Flag to check admin mode
		boolean Systemexit = false; // Flag to close program
		
		String FileName = "ProductStock.csv";

		// Create an instance of the ProductManagement class to manage products
		ProductManagement a = new ProductManagement();

		// Create an ArrayList to store product data and populate it with sample data
		ArrayList<Product> productList = a.Product_Item();

		// Create a linked list to represent the shopping cart
		LinkedList<Product> cart = new LinkedList();
		
		do {
			
			// Display three options admin, user, exit
		    System.out.println("\nEnter <1> : Exit \n"
		        + "Enter <2> : Admin \n"
		        + "Enter <3> : User\n");
		    
		    String option;
		    
		    do {
		        // Prompt the user to choose an option and read their input
		        System.out.print("Choose option: ");
		        option = z.nextLine();
		    } while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
		    
		    switch(option) {
		    case "1":
		    	// If the user chooses to exit, set the exit flag to true
		    	Systemexit = true;
		    	break;
		    case "2":
		    	System.out.print("Enter Password to Access admin: ");
		    	String Pass = z.nextLine();
		    	
		    	if(Pass.equals("JavaIsPain")) {
		    		do {
			    		// Display three options add, remove, exit stock
					    System.out.println("\nEnter <1> : Back \n"
					        + "Enter <2> : Add product to stock \n"
					        + "Enter <3> : Remove product from stock\n");

					    do {
					        // Prompt the user to choose an option and read their input
					        System.out.print("Choose option: ");
					        option = z.nextLine();
					    } while (!option.equals("1") && !option.equals("2") && !option.equals("3"));
					    
					    switch(option){
					    	case "1": // back to main manu
					    		admincheck = true;
					    		break;
					    	case "2": // option for add new product or increase quantity of product
					    		System.out.print("Enter Product Id: ");
					    		int Sid = CheckOut.ReadId(x);
					    		
					    		ProductManagement.AddStock(productList, Sid);
					    		
					    		ProductManagement.DisplayProduct(productList);
					    		
					    		admincheck = false;
					    		break;
					    	case "3": // Reduce quantity of product or remove product
					    		System.out.print("Enter Product Id: ");
					    		int did = CheckOut.ReadId(productList, x);
					    		System.out.print("Enter Product quantity: ");
					    		int dq = CheckOut.ReadQuantity(x);
					    		
					    		ProductManagement.ReduceProduct(productList, did, dq);
					    		
					    		ProductManagement.DisplayProduct(productList);
					    		
					    		admincheck = false;
					    		break;
					    		
					    }
					    
			    	}while(!admincheck);
		    		
		    	}else {
		    		
		    		System.out.print("Wrong Password press 2 and try again\n");
		    		
		    	}
		    	break;
		    case "3":
		    	
		    	// Display the program's title
				System.out.println(UserInterface.TitleName());

				do {
				    // Display the main menu with three options
				    System.out.println("\nEnter <1> : Exit \n"
				        + "Enter <2> : Product List \n"
				        + "Enter <3> : Buy product\n");
				    
				    do {
				        // Prompt the user to choose an option and read their input
				        System.out.print("Choose option: ");
				        option = z.nextLine();
				    } while (!option.equals("1") && !option.equals("2") && !option.equals("3"));

				    // Display a separator line
				    System.out.println("\n--------------------------------------------------"
				            + "-------------------------------------------------------------"
				            + "--------------------------------------------------------------"
				            + "--------------------");

				    switch (option) {
				        case "1":
				            // If the user chooses to exit, set the exit flag to true
				            exit = true;
				            break;
				        case "2":
				            // If the user chooses to view the product list, display it
				            System.out.println("\nList of available Product Now\n");
				            ProductManagement.DisplayProduct(productList);
				            
				            // Display a separator line
				            System.out.println("\n--------------------------------------------------"
				                    + "-------------------------------------------------------------"
				                    + "--------------------------------------------------------------"
				                    + "--------------------");
				            exit = false;
				            break;
				        case "3":
				            // If the user chooses to buy a product, enter the Buy Product sub-menu loop
				            do {
				                // Display the Buy Product sub-menu with five options
				                System.out.println("\nEnter <1> : Back \n"
				                        + "Enter <2> : Show cart \n"
				                        + "Enter <3> : Add product to cart\n"
				                        + "Enter <4> : Remove product from cart\n"
				                        + "Enter <5> : Make a payment\n");

				                do {
				                    // Prompt the user to choose a sub-option and read their input
				                    System.out.print("Choose option: ");
				                    option = z.nextLine();
				                } while (!option.equals("1") && !option.equals("2") && !option.equals("3") 
				                        && !option.equals("4") && !option.equals("5"));

				                // Display a separator line
				                System.out.println("\n------------------------------------------------"
				                        + "----------------------------------------------------------"
				                        + "-----------------------------------------------------------"
				                        + "----------------------------");

				                switch (option) {
				                    case "1":
				                        // If the user chooses to go back, clear the shopping cart and set checkadd to true
				                        cart.clear();
				                        checkadd = true;
				                        break;
				                    case "2":
				                        // If the user chooses to view the cart, display its contents
				                        CartManagement.displayCart(cart);
				                        checkadd = false;
				                        break;
				                    case "3":
				                        // If the user chooses to add a product to the cart, call the InterfaceAddProduct method
				                        UserInterface.InterfaceAddProduct(cart, productList, x);
				                        checkadd = false;
				                        break;
				                    case "4":
				                        // If the user chooses to remove a product from the cart, call the InterfaceRemoveProduct method
				                        UserInterface.InterfaceRemoveProduct(cart, productList, x);
				                        checkadd = false;
				                        break;
				                    case "5":
				                        // If the user chooses to make a payment, call the InterfacePayment method
				                        UserInterface.InterfacePayment(cart, productList, y, checkadd);
				                        break;
				                }

				            } while (!checkadd);  // Continue the Buy Product sub-menu loop until checkadd is true
				            
				            exit = false;
				            break;
				    }

				} while (!exit);  // Continue the main program loop until exit is true

				// Display a thank-you message
				System.out.println(UserInterface.Thank());
				Systemexit = true;
		    	break;
		    }
		    
		}while(!Systemexit);
		
		ProductManagement.SaveProductsStock(productList,FileName); // Save latest stock

	}
}
