
public class CartManagement {
	
	public static void addToCart(LinkedList<Product> cart, 
			ArrayList<Product> productList, int id , int quantity) { // Add item to cart
	    boolean x = false;

	    // Loop through the products in the cart
	    for(int i=0; i < cart.size(); i++) {
	        Node<Product> cNode = cart.get(i);
	        Product cp = cNode.getData();

	        // Check if the ID of the item in the cart matches the input ID
	        if(cp.getid() == id) { 
	            // If the ID matches, only increase the quantity
	            cp.setStockCount(cp.getStockCount() + quantity);
	            x = true;
	            break;
	        }
	    }

	    // If the product with the given ID is not already in the cart
	    if(!x) { 
	        for(int i = 0; i < productList.size(); i++) {
	            if(productList.get(i).getid() == id) {
	                // Create a new Product object based on the selected product
	                Product goods = new Product(productList.get(i).getid(), productList.get(i).getName(), 
	                        productList.get(i).getDescription(), productList.get(i).getPrice(), quantity);
	                
	                cart.append(goods); // Add the new Product object to the cart
	            }
	        }
	    }
	}


    public static void removeItem(LinkedList<Product> cart, int id, int quantity) {
        Node<Product> c = cart.head;
        Node<Product> p = null;

        while (c != null) {
            Product product = c.getData();

            if (product.getid() == id) { // Check if the current product's ID matches the provided ID
                int cq = product.getStockCount(); // Get the current quantity of the product in the cart

                if (quantity <= cq) { // If the input quantity is less than or equal to the quantity in the cart
                    product.setStockCount(cq - quantity); // Reduce the cart quantity

                    if (product.getStockCount() == 0) { // If the cart quantity reaches zero
                        if (p == null) {
                            cart.head = c.getNext(); // Remove the product from the cart (head case)
                        } else {
                            p.setNext(c.getNext()); // Remove the product from the cart (non-head case)
                        }
                    }
                } else {
                    System.out.println("\nQuantity in cart is less than input"); // Notify the user of insufficient quantity
                }

                break; // Exit the loop since the product has been found and processed
            }

            p = c;
            c = c.getNext(); // Move to the next product in the cart
        }
    }
    
    public static boolean isProductInCart(LinkedList<Product> cart, int id) {
        Node<Product> c = cart.head;

        // Loop through the items in the cart
        while (c != null) {
            Product product = c.getData();
            if (product != null && product.getid() == id) {
                // If a product with the given ID is found in the cart, return true
                return true;
            }
            c = c.getNext();
        }

        // If no product with the given ID is found in the cart, return false
        return false;
    }

    public static void displayCart(LinkedList<Product> cart) {
        Node<Product> c = cart.head;

        // Check if the cart is empty
        if (c == null) {
            System.out.println("\nDon't have any product in cart");
            System.out.println("\n------------------------------------------------"
                    + "----------------------------------------------------------"
                    + "-----------------------------------------------------------"
                    + "----------------------------");
        } else {
            // Print the header of the cart table
            System.out.println(UserInterface.CartText());
            System.out.println("----------------------------------------------------------------------");
            System.out.printf("||%10s||%22s||%12s||%16s||%n", "ID", "Product Name", "Price", "Quantity");
            System.out.println("----------------------------------------------------------------------");

            // Loop through the items in the cart and print each item
            while (c != null) {
                Product product = c.getData();
                if (product != null) {
                    System.out.printf("||%10d||%22s||%12.2f||%16d||%n",
                            product.getid(),
                            product.getName(),
                            product.getPrice(),
                            product.getStockCount());
                    System.out.println("----------------------------------------------------------------------");
                }
                c = c.getNext();
            }

            // Print a separator after the cart items
            System.out.println("\n------------------------------------------------"
                    + "----------------------------------------------------------"
                    + "-----------------------------------------------------------"
                    + "----------------------------");
        }
    }
}
