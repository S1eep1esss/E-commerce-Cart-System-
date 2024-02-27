import java.io.*;
import java.util.Scanner;

public class ProductManagement {
	
	static Scanner z = new Scanner(System.in);
	static Scanner x = new Scanner(System.in);
	
	public static void addProduct(ArrayList<Product> productList, Product product) { 
	    // This method adds a given product to the productList.
	    productList.add(product);
	}

	public static void deleteProduct(ArrayList<Product> productList, int id) { 
	    // This method deletes a product with the specified ID from the productList, if it exists.
	    for (int i = 0; i < productList.size(); i++) {
	        if (productList.get(i).getid() == id) {
	            productList.remove(i);
	            break;
	        }
	    }
	}

    
    public static void ReduceProduct(ArrayList<Product> productList, int id, int q) {
    	// This method decreases the quantity of a product with the specified ID in the productList by the given quantity 'q'.
    	for(int i=0;i < productList.size();i++) {
    		if(productList.get(i).getid() == id) {
    			Product r = productList.get(i);
    			if(q <= productList.get(i).getStockCount()) {
    				r.setStockCount(productList.get(i).getStockCount() - q);
    				if(r.getStockCount() == 0) {
    					deleteProduct(productList, r.getid());
    				}
    			}else {
    				System.out.println("\nQuantity in stock is less than input");
    			}
    		}
    	}
    }
    
    public static void DisplayProduct(ArrayList<Product> productList) {
        // This method displays all the products in the productList in a formatted table.
        System.out.println("---------------------------------------------------------"
        		+ "------------------------------------------------------------------"
        		+ "----------------------------------------------------");
        System.out.printf("||%10s||%25s||%12s||%16s||%100s||%n", "ID", "Product Name",
        		"Price", "Quantity", "Description");
        System.out.println("---------------------------------------------------------"
        		+ "------------------------------------------------------------------"
        		+ "----------------------------------------------------");

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            System.out.printf("||%10d||%25s||%12.2f||%16d||%100s||%n",
                    product.getid(),
                    product.getName(),
                    product.getPrice(),
                    product.getStockCount(),
                    product.getDescription());
            System.out.println("-----------------------------------------------------"
            		+ "--------------------------------------------------------------"
            		+ "------------------------------------------------------------");
        }
    }
    
    public static void AddStock(ArrayList<Product> productList, int id) {
        boolean productExists = false;

        for (int i = 0; i < productList.size(); i++) {
            Product r = productList.get(i);
            if (id == productList.get(i).getid()) { // if stock already has the product, increase quantity
                System.out.println("You already have this product in stock");
                System.out.print("Enter Product Quantity: ");
                int Sq = CheckOut.ReadQuantity(x);
                r.setStockCount(productList.get(i).getStockCount() + Sq);
                productExists = true;
                break; // Exit the loop since we found the product
            }
        }

        if (!productExists) {
            // Enter name of the new product
            System.out.print("Enter Product Name: ");
            String Sname = z.nextLine();
            // Enter description of the new product
            System.out.print("Enter Product Description: ");
            String Sdes = z.nextLine();
            // Enter price of the new product
            System.out.print("Enter Product Price: ");
            int Sp = CheckOut.ReadPrice(x);
            // Enter quantity of the new product
            System.out.print("Enter Product Quantity: ");
            int Sq = CheckOut.ReadQuantity(x);
            // Add the new product
            addProduct(productList, new Product(id, Sname, Sdes, Sp, Sq));
        }
    }
    
    public ArrayList<Product> Product_Item() { 
    	// This method creates and populates a list of products and returns it.
    	
        // Initialize an ArrayList to hold the products.
    	ArrayList<Product> productList = new ArrayList<>();
    	// File name for load stock
    	String FileName = "ProductStock.csv";
    	
    	// Load products'stock data
        LoadProductsStock(productList, FileName); 
        
		return productList;
	}
    
    public static void LoadProductsStock(ArrayList<Product> productList, String file) {
        try (BufferedReader r = new BufferedReader(new FileReader(file))) {
            String line;
            boolean headS = false;

            // Read the CSV file line by line
            while ((line = r.readLine()) != null) {
                // Skip the CSV header line
                if (!headS) {
                	headS = true;
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String des = parts[2].trim();
                    double price = Double.parseDouble(parts[3].trim());
                    int q = Integer.parseInt(parts[4].trim());

                    // Create a new Product object and add it to the productList
                    Product product = new Product(id, name, des, price, q);
                    productList.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void SaveProductsStock(ArrayList<Product> productList, String file) {
        try (FileWriter w = new FileWriter(file)) {
            // Write the CSV header
            w.write("ID,Product Name,Description,Price,Quantity\n");

            // Write each product's information as a CSV line using a for loop
            for (int i = 0; i < productList.size(); i++) {
                Product product = productList.get(i);

                String line = String.format("%d,%s,%s,%.2f,%d\n",
                        product.getid(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getStockCount());

                w.write(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
