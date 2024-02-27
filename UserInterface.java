import java.util.Scanner;

public class UserInterface {
	
	static String op;
	static String Code;
	
	public static void InterfaceAddProduct(LinkedList<Product> cart, 
	        ArrayList<Product> productList, Scanner x) {
	    // This method allows the user to add a product to their shopping cart.
	    
	    // Display text prompting the user to add a product
	    System.out.println(Texttoadd());
	    System.out.print("\nEnter Product Id: ");
	    
	    // Read the product ID from the user
	    int pid = CheckOut.ReadId(productList, x);
	    
	    if (pid == -1) {
	        System.out.println("Product not found.");
	    }
	    
	    System.out.print("Enter Product quantity: ");
	    
	    // Read the quantity from the user
	    int q = CheckOut.ReadQuantity(x);
	    
	    System.out.println("\n------------------------------------------------"
	            + "----------------------------------------------------------"
	            + "-----------------------------------------------------------"
	            + "----------------------------");
	    
	    // Add the selected product to the shopping cart
	    CartManagement.addToCart(cart, productList, pid, q);
	    
	    // Display the updated shopping cart
	    CartManagement.displayCart(cart);
	}

		
	public static void InterfaceRemoveProduct(LinkedList<Product> cart, 
	        ArrayList<Product> productList, Scanner x) {
	    // This method provides an interface for the user to remove a product from their shopping cart.
	    
	    // Display text prompting the user to remove a product
	    System.out.println(RemoveText());
	    System.out.print("\nEnter Product Id: ");
	    
	    // Read the product ID from the user
	    int idc = CheckOut.ReadId(productList, x);
	    
	    // Check if the selected product is in the user's cart
	    boolean CheckCart = CartManagement.isProductInCart(cart, idc);

	    if (CheckCart) {
	        System.out.print("Enter Product quantity: ");
	        
	        // Read the quantity from the user
	        int qc = CheckOut.ReadQuantity(x);
	        
	        System.out.println("\n------------------------------------------------"
	                + "----------------------------------------------------------"
	                + "-----------------------------------------------------------"
	                + "----------------------------");
	        
	        // Remove the selected product from the shopping cart
	        CartManagement.removeItem(cart, idc, qc);
	    } else {
	        System.out.println("\nDon't have this product in your cart");
	        System.out.println("\n------------------------------------------------"
	                + "----------------------------------------------------------"
	                + "-----------------------------------------------------------"
	                + "----------------------------");
	    }

	    // Display the updated shopping cart
	    CartManagement.displayCart(cart);
	}

	
	public static void InterfacePayment(LinkedList<Product> cart, 
	        ArrayList<Product> productList, Scanner y, boolean check) {
	    // This method provides an interface for the user to make a payment for the items in their shopping cart.
	    
	    do {
	        // Display the current contents of the shopping cart
	        CartManagement.displayCart(cart);
	        System.out.print("\nMake a payment for this order? (y/n): ");
	        op = y.nextLine().toLowerCase();
	        
	        System.out.println("\n------------------------------------------------"
	                + "----------------------------------------------------------"
	                + "-----------------------------------------------------------"
	                + "----------------------------");
	    } while (!op.equals("y") && !op.equals("n"));

	    if (op.equals("y")) {
	        do {
	            System.out.print("\nDo you have a Discount Code? (y/n): ");
	            op = y.nextLine().toLowerCase();
	            
	            System.out.println("\n------------------------------------------------"
	                    + "----------------------------------------------------------"
	                    + "-----------------------------------------------------------"
	                    + "----------------------------");
	        } while (!op.equals("y") && !op.equals("n"));

	        if (op.equals("y")) {
	            System.out.print("\nEnter your promotion code: ");
	            Code = y.nextLine().toUpperCase();
	            
	            System.out.println("\n------------------------------------------------"
	                    + "----------------------------------------------------------"
	                    + "-----------------------------------------------------------"
	                    + "----------------------------");
	            
	            // Perform checkout with the provided discount code
	            CheckOut.checkout(cart, productList, Code);
	        } else if (op.equals("n")) {
	            // Perform checkout without a discount code
	            CheckOut.checkout(cart, productList);
	        }
	        check = true; // Indicate that the payment was successful
	    } else if (op.equals("n")) {
	        check = false; // Indicate that the payment was not made
	    }
	}
	
	
	// All method below is the methode that return String word format

	public static String TitleName() {
		return  " __          __         _                                       _                                               \r\n"
				+ " \\ \\        / /        | |                                     | |                                              \r\n"
				+ "  \\ \\  /\\  / /    ___  | |   ___    ___    _ __ ___     ___    | |_    ___                                      \r\n"
				+ "   \\ \\/  \\/ /    / _ \\ | |  / __|  / _ \\  | '_ ` _ \\   / _ \\   | __|  / _ \\                                     \r\n"
				+ "    \\  /\\  /    |  __/ | | | (__  | (_) | | | | | | | |  __/   | |_  | (_) |                                    \r\n"
				+ "     \\/  \\/      \\___| |_|  \\___|  \\___/  |_| |_| |_|  \\___|    \\__|  \\___/                                     \r\n"
				+ "                                                                                                                \r\n"
				+ "                                                                                                                \r\n"
				+ "  _____    _____   _______     _____            __                       _                                  _   \r\n"
				+ " |_   _|  / ____| |__   __|   |  __ \\          / _|                     | |                                | |  \r\n"
				+ "   | |   | |         | |      | |__) |   ___  | |_   _ __    ___   ___  | |__    _ __ ___     ___   _ __   | |_ \r\n"
				+ "   | |   | |         | |      |  _  /   / _ \\ |  _| | '__|  / _ \\ / __| | '_ \\  | '_ ` _ \\   / _ \\ | '_ \\  | __|\r\n"
				+ "  _| |_  | |____     | |      | | \\ \\  |  __/ | |   | |    |  __/ \\__ \\ | | | | | | | | | | |  __/ | | | | | |_ \r\n"
				+ " |_____|  \\_____|    |_|      |_|  \\_\\  \\___| |_|   |_|     \\___| |___/ |_| |_| |_| |_| |_|  \\___| |_| |_|  \\__|\r\n"
				+ "                                                                                                                \r\n"
				+ "                                                                                                                \r\n"
				+ "   _____   _             _   _                                                                                  \r\n"
				+ "  / ____| | |           | | | |                                                                                 \r\n"
				+ " | (___   | |_    __ _  | | | |                                                                                 \r\n"
				+ "  \\___ \\  | __|  / _` | | | | |                                                                                 \r\n"
				+ "  ____) | | |_  | (_| | | | | |                                                                                 \r\n"
				+ " |_____/   \\__|  \\__,_| |_| |_|                                                                                 \r\n"
				+ "                                 ";
		
	}
	
	public static String Thank() {
		return "\r\n"
				+ "  _______   _                       _                                   __                                 \r\n"
				+ " |__   __| | |                     | |                                 / _|                                \r\n"
				+ "    | |    | |__     __ _   _ __   | | __    _   _    ___    _   _    | |_    ___    _ __                  \r\n"
				+ "    | |    | '_ \\   / _` | | '_ \\  | |/ /   | | | |  / _ \\  | | | |   |  _|  / _ \\  | '__|                 \r\n"
				+ "    | |    | | | | | (_| | | | | | |   <    | |_| | | (_) | | |_| |   | |   | (_) | | |                    \r\n"
				+ "    |_|    |_| |_|  \\__,_| |_| |_| |_|\\_\\    \\__, |  \\___/   \\__,_|   |_|    \\___/  |_|                    \r\n"
				+ "                                              __/ |                                                        \r\n"
				+ "                                             |___/                                                         \r\n"
				+ "                _                                                                          _               \r\n"
				+ "               (_)                                                                        (_)              \r\n"
				+ "  _   _   ___   _   _ __     __ _      ___    _   _   _ __     ___    ___   _ __  __   __  _    ___    ___ \r\n"
				+ " | | | | / __| | | | '_ \\   / _` |    / _ \\  | | | | | '__|   / __|  / _ \\ | '__| \\ \\ / / | |  / __|  / _ \\\r\n"
				+ " | |_| | \\__ \\ | | | | | | | (_| |   | (_) | | |_| | | |      \\__ \\ |  __/ | |     \\ V /  | | | (__  |  __/\r\n"
				+ "  \\__,_| |___/ |_| |_| |_|  \\__, |    \\___/   \\__,_| |_|      |___/  \\___| |_|      \\_/   |_|  \\___|  \\___|\r\n"
				+ "                             __/ |                                                                         \r\n"
				+ "                            |___/                                                                          \r\n"
				+ "";
	}
	
	public static String Texttoadd() {
		return "\r\n"
				+ "              _     _   _____               _            _     _           _____           _   \r\n"
				+ "     /\\      | |   | | |  __ \\             | |          | |   | |         / ____|         | |  \r\n"
				+ "    /  \\   __| | __| | | |__) | __ ___   __| |_   _  ___| |_  | |_ ___   | |     __ _ _ __| |_ \r\n"
				+ "   / /\\ \\ / _` |/ _` | |  ___/ '__/ _ \\ / _` | | | |/ __| __| | __/ _ \\  | |    / _` | '__| __|\r\n"
				+ "  / ____ \\ (_| | (_| | | |   | | | (_) | (_| | |_| | (__| |_  | || (_) | | |___| (_| | |  | |_ \r\n"
				+ " /_/    \\_\\__,_|\\__,_| |_|   |_|  \\___/ \\__,_|\\__,_|\\___|\\__|  \\__\\___/   \\_____\\__,_|_|   \\__|\r\n";
	}
	
	public static String CartText() {
		return "\r\n"
				+ "   _____           _   \r\n"
				+ "  / ____|         | |  \r\n"
				+ " | |     __ _ _ __| |_ \r\n"
				+ " | |    / _` | '__| __|\r\n"
				+ " | |___| (_| | |  | |_ \r\n"
				+ "  \\_____\\__,_|_|   \\__|\r\n";
	}
	
	public static String Receipt() {
		return "\r\n"
				+ " __     __                _____               _       _   \r\n"
				+ " \\ \\   / /               |  __ \\             (_)     | |  \r\n"
				+ "  \\ \\_/ /__  _   _ _ __  | |__) |___  ___ ___ _ _ __ | |_ \r\n"
				+ "   \\   / _ \\| | | | '__| |  _  // _ \\/ __/ _ \\ | '_ \\| __|\r\n"
				+ "    | | (_) | |_| | |    | | \\ \\  __/ (_|  __/ | |_) | |_ \r\n"
				+ "    |_|\\___/ \\__,_|_|    |_|  \\_\\___|\\___\\___|_| .__/ \\__|\r\n"
				+ "                                               | |        \r\n"
				+ "                                               |_|        \r\n"
				+ "";
	}
	
	public static String RemoveText() {
		return "\r\n"
				+ "______                                _____ _                  ______                     _____            _   \r\n"
				+ "| ___ \\                              |_   _| |                 |  ___|                   /  __ \\          | |  \r\n"
				+ "| |_/ /___ _ __ ___   _____   _____    | | | |_ ___ _ __ ___   | |_ _ __ ___  _ __ ___   | /  \\/ __ _ _ __| |_ \r\n"
				+ "|    // _ \\ '_ ` _ \\ / _ \\ \\ / / _ \\   | | | __/ _ \\ '_ ` _ \\  |  _| '__/ _ \\| '_ ` _ \\  | |    / _` | '__| __|\r\n"
				+ "| |\\ \\  __/ | | | | | (_) \\ V /  __/  _| |_| ||  __/ | | | | | | | | | | (_) | | | | | | | \\__/\\ (_| | |  | |_ \r\n"
				+ "\\_| \\_\\___|_| |_| |_|\\___/ \\_/ \\___|  \\___/ \\__\\___|_| |_| |_| \\_| |_|  \\___/|_| |_| |_|  \\____/\\__,_|_|   \\__|\r\n"
;
	}
	
}
