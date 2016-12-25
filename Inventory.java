import java.util.*;
import java.io.*;
import java.util.concurrent.*;
public class Inventory implements InventoryManagementSystem{

  private ConcurrentHashMap<String, Product> products = new ConcurrentHashMap<>();
  private ConcurrentHashMap<String, Warehouse> warehouses = new ConcurrentHashMap<>();

  public Inventory(){
    this.products = new ConcurrentHashMap<String, Product>();
    this.warehouses = new ConcurrentHashMap<String, Warehouse>();
  }

  public Inventory addProduct(Product product){
    products.put(product.toString(), product);
    return this;
  }

  public Inventory addWarehouse(Warehouse warehouse){
    warehouses.put(warehouse.toString(), warehouse);
    return this;
  }

  public ConcurrentHashMap<String, Product> getProducts(){
    return this.products;
  }

  public ConcurrentHashMap<String, Warehouse> getWarehouses(){
    return this.warehouses;
  }

  public PickingResult pickProduct(String productId, int amountToPick){
    Product product = products.get(productId);
    product.subProduct(amountToPick);
    PickingResult result = new PickingResult(product);
    return result;
  }

  public RestockingResult restockProduct(String productId, int amountToRestock){
    Product product = products.get(productId);
    product.addProduct(amountToRestock);
    RestockingResult result = new RestockingResult(product);
    return result;
  }

}

//Product class
class Product{

  private String productName;
  private int level;
  private Warehouse warehouse;

  public Product(String productName, int level, Warehouse warehouse){
    this.productName = productName;
    this.level = level;
    this.warehouse = warehouse;
  }

  //String method for name of product
  public String toString(){
    return this.productName;
  }

  //Method for checking products level
  public int getLevel(){
    return this.level;
  }

  //Method for checking what warehouse the product is in
  public Warehouse getWarehouse(){
    return this.warehouse;
  }

  public int addProduct(int level){
    return this.level += level;
  }

  public int subProduct(int level){
    return this.level -= level;
  }
}

//Warehouse class
class Warehouse{

  private String location;

  public Warehouse(String location){
    this.location = location;
  }

  public String toString(){
    return this.location;
  }
}

//PickingResult class
class PickingResult{

  private Product product;

  public PickingResult(Product product){
    this.product = product;
  }

  public Product getProduct(){
    return this.product;
  }
}

//RestockingResult class
class RestockingResult{

  private Product product;

  public RestockingResult(Product product){
    this.product = product;
  }

  public Product getProduct(){
    return this.product;
  }
}
