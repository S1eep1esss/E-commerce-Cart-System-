import java.util.Scanner;

public class CheckOut {
	
	public static void checkout(LinkedList<Product> cart, ArrayList<Product> stock, String discountCode) {
		// This method processes the checkout for a user's shopping cart, calculates the total cost and applies discounts
		
		double total = 0.00;
		
	    // Display a receipt header
	    System.out.println(UserInterface.Receipt());
	    System.out.println("----------------------------------------------------------------------------------------------");
	    System.out.printf("||%24s||%15s||%15s||%30s||%n", "Product Name", "Price", "Quantity", "Total Cost");
	    System.out.println("----------------------------------------------------------------------------------------------");

	    // Iterate through the items in the cart
	    for (Node<Product> i = cart.head; i != null; i = i.getNext()) {
	        Product cp = i.getData(); // Current product in the cart
	        int qc = cp.getStockCount(); // Quantity in the cart
	        Product pl = getProductById(stock, cp.getid()); // Product in stock with the same ID
	        int lc = pl.getStockCount(); // Available stock quantity for the product

	        if (pl != null) {
	            String name = pl.getName();
	            double price = pl.getPrice();

	            if (qc > lc) {
	                System.out.println("product name: " + name + " is not enough for your order\n");
	            } else {
	                double pcost = price * qc;
	                total += pcost; // Update the total cost

	                if (pl.getStockCount() >= qc) {
	                    ProductManagement.ReduceProduct(stock, pl.getid(), qc);
	                }
	                CartManagement.removeItem(cart, pl.getid(), qc);

	                // Display the product details in the receipt
	                System.out.printf("||%24s||%15.2f||%15d||%30.2f||%n", name, price, qc, pcost);
	                System.out.println("----------------------------------------------------------------------------------------------");

	            }
	        }
	    }

	    // Display the subtotal
	    System.out.printf("||SUBTOTAL: %80.2f||%n", total);

	    // Apply discounts and calculate the discounted total
	    double discountAmount = applyDiscount(total, discountCode);
	    double discountedTotal = total - discountAmount;

	    // Display the total cost after applying discounts
	    System.out.printf("||Total Cost: %78.2f||%n", discountedTotal);
	    System.out.println("----------------------------------------------------------------------------------------------");
	    System.out.println("\n------------------------------------------------"
	            + "----------------------------------------------------------"
	            + "-----------------------------------------------------------"
	            + "----------------------------");
	}
	
	public static void checkout(LinkedList<Product> cart, ArrayList<Product> stock) {
		// This method processes the checkout for a user's shopping cart, calculates the total cost without discount code
		
		 double total = 0.00;

		    // Display a receipt header
		    System.out.println(UserInterface.Receipt());
		    System.out.println("----------------------------------------------------------------------------------------------");
		    System.out.printf("||%24s||%15s||%15s||%30s||%n", "Product Name", "Price", "Quantity", "Total Cost");
		    System.out.println("----------------------------------------------------------------------------------------------");

		    // Iterate through the items in the cart
		    for (Node<Product> i = cart.head; i != null; i = i.getNext()) {
		        Product cp = i.getData(); // Current product in the cart
		        int qc = cp.getStockCount(); // Quantity in the cart
		        Product pl = getProductById(stock, cp.getid()); // Product in stock with the same ID
		        int lc = pl.getStockCount(); // Available stock quantity for the product

		        if (pl != null) {
		            String name = pl.getName();
		            double price = pl.getPrice();

		            if (qc > lc) {
		                // Display that the product is not available in sufficient quantity
		                System.out.printf("||%24s||%15.2f||%15s||%30.2f||%n", name, price, "not enough", 0.00);
		                System.out.println("----------------------------------------------------------------------------------------------");
		                CartManagement.removeItem(cart, pl.getid(), qc);
		            } else {
		                double pcost = price * qc;
		                total += pcost; // Update the total cost

		                if (pl.getStockCount() >= qc) {
		                    ProductManagement.ReduceProduct(stock, pl.getid(), qc);
		                }
		                CartManagement.removeItem(cart, pl.getid(), qc);

		                // Display the product details in the receipt
		                System.out.printf("||%24s||%15.2f||%15d||%30.2f||%n", name, price, qc, pcost);
		                System.out.println("----------------------------------------------------------------------------------------------");

		            }
		        }
		    }

		    // Display the subtotal and inform the user that no discount is applied
		    System.out.printf("||SUBTOTAL: %80.2f||%n", total);
		    System.out.printf("||No discount applied. %69.2f||%n", -0.00);

		    // Display the total cost
		    System.out.printf("||Total Cost: %78.2f||%n", total);
		    System.out.println("----------------------------------------------------------------------------------------------");
		    System.out.println("\n------------------------------------------------"
		            + "----------------------------------------------------------"
		            + "-----------------------------------------------------------"
		            + "----------------------------");
	}
	
	public static int ReadId(ArrayList<Product> productlist,Scanner a) {
		boolean valid = false;
		int id = -1;
		
		while(!valid) {
			
			if(a.hasNextInt()) { // checks if the input is an integer
				id = a.nextInt();
				for(int i=0;i<productlist.size();i++) {
					if(id == productlist.get(i).getid()) { //checks if input is an id of product
						valid = true;
						break;		
					}
				}
				if(!valid) {
					System.out.println("Don't have this produuct in stock");
					System.out.print("Enter Product Id: ");
				}
			}else { // if input is not integer input again
				a.next();
				System.out.print("Enter Product Id: ");
			}
		}
		
		return id;
	}
	
	public static int ReadId(Scanner a) {
		boolean valid = false;
		int id = -1;
		
		while(!valid) {
			
			if(a.hasNextInt()) { // checks if the input is an integer
				id = a.nextInt();
				valid = true;
			}else { // if input is not integer input again
				a.next();
				System.out.print("Enter Product Id: ");
			}
		}
		
		return id;
	}
	
	public static int ReadQuantity(Scanner a) {
		int q = -1;
        boolean valid = false;
		
        while(!valid) {
        	String x = a.next(); // Read the next input as a string
        	try { // Try to parse the string as an integer
                q = Integer.parseInt(x);
                if(q>0) { // // check If q is positive,
                	valid = true;
                }else {
                	System.out.print("Enter Product quantity: ");
                	valid =false;
                }
                
            } catch (NumberFormatException e) { // Prompt the user to enter a valid integer
                System.out.print("Enter Product quantity: ");
            }
        }
		return q;
	}
	
	public static int ReadPrice(Scanner a) {
		int p = -1;
        boolean valid = false;
		
        while(!valid) {
        	String x = a.next(); // Read the next input as a string
        	try { // Try to parse the string as an integer
                p = Integer.parseInt(x);
                if(p>0) { // // check If p is positive,
                	valid = true;
                }else {
                	System.out.print("Enter product price: ");
                	valid =false;
                }
                
            } catch (NumberFormatException e) { // Prompt the user to enter a valid integer
                System.out.print("Enter product price: ");
            }
        }
		return p;
	}
	
	public static double applyDiscount(double total, String discountCode) {
	    double discountAmount = 0.0;

	    if (discountCode.equalsIgnoreCase("ICTLOVEDRINK")) { // Apply a 10% discount
	        discountAmount = 0.10 * total;
	        System.out.printf("||Discount Applied (%s): %57.2f||%n", discountCode, -discountAmount);
	    }else if(discountCode.equalsIgnoreCase("ICTLOVEDRINK50")) {// Apply a 50% discount if total cost more than 5000
	    	if(total >= 5000) {
	    		discountAmount = 0.50 * total;
	    		System.out.printf("||Discount Applied (%s): %55.2f||%n", discountCode, -discountAmount);
	    	}
	    	else { // if price less than 5000 don't apply any discount
	    		System.out.printf("||No discount applied. %69.2f||%n",-0.00);
	    	}
	    }else if(discountCode.equalsIgnoreCase("9arm")) { // Apply a 50% discount
	    	discountAmount = 1 * total;
    		System.out.printf("||Discount Applied (%s): %65.2f||%n", discountCode, -discountAmount);
	    }else {
	    	System.out.printf("||No discount applied. %70.2f||%n",-0.00);
	    }

	    return discountAmount;
	}
	
	public static Product getProductById(ArrayList<Product> productList, int productId) {
		//This method searches for a product with the given productId in the provided productList.
		
		for (int i = 0; i < productList.size(); i++) {
	        Product product = productList.get(i);
	        if (product.getid() == productId) {
	            return product; // Return the product if the ID matches
	        }
	    }
	    
	    return null; // Return null if no product with the specified ID is found
	}
}
