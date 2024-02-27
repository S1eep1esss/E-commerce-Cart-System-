
public class Product {
	private int id;
    private String name;
    private String des;
    private double price;
    private int stockCount;

    public Product(int id, String name, String des, double price, int stockCount) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.price = price;
        this.stockCount = stockCount;
    }
    
    public int getid() {
        return id;//return id of product
    }

    public void setid(int id) {
        this.id = id;// set id of product
    }

    public String getName() {
        return name;//return name of product
    }

    public void setName(String name) {
        this.name = name; // set name of product
    }

    public String getDescription() {
        return des;//return description of product
    }

    public void setDescription(String des) {
        this.des = des; //set description of product
    }

    public double getPrice() {
        return price;//return price of product
    }

    public void setPrice(double price) {
        this.price = price; // set price of product
    }

    public int getStockCount() {
        return stockCount;//return qantity of product
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount; // set qantity of product
    }

    @Override
    public String toString() {
        return  "Name: " + name + "\n" +
        		"Id: " + id + "\n" +
                "Description: " + des + "\n" +
        		"Price: " + price + "\n" +
                "Stock: " + stockCount + "\n";
            
    }

}
