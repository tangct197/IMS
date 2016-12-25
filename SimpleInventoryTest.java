import java.util.*;
//Simple inventory test file to test our implementation of the InventoryManagementSystem interface with
//our Inventory class
public class SimpleInventoryTest{
  public static void main(String[] args){
    Inventory inventory = new Inventory();
    //Testing Inventory class
    //Initializing some warehouses to test
    Warehouse w1 = new Warehouse("Warehouse 1");
    Warehouse w2 = new Warehouse("Warehouse 2");
    inventory.addWarehouse(w1);
    inventory.addWarehouse(w2);
    //Initializing some products to test
    Product p1 = new Product("Rock", 2, w2);
    Product p2 = new Product("Paper", 1, w2);
    Product p3 = new Product("Scissor", 3, w2);
    Product p4 = new Product("Crayon", 0, w1);
    inventory.addProduct(p1);
    inventory.addProduct(p2);
    inventory.addProduct(p3);
    inventory.addProduct(p4);
    //What is in our inventory at the moment
    System.out.println("This is in our product inventory: ");
    System.out.println(inventory.getProducts().values());
    System.out.println("These are our warehouses: ");
    System.out.println(inventory.getWarehouses().values());
    simpleInput(inventory);
  }

  public static void simpleInput(Inventory inventory){
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter 1 if you wish to pick an item.\n"
                      + "Enter 2 if you wish to restock an item.\n"
                      + "Enter 0 if you wish to quit.");
    int n = sc.nextInt();
    if(n == 1){
      System.out.println("Enter the product you want from our inventory: ");
      String product = sc.next();
      System.out.println("Enter how you want from our inventory: ");
      int amount = sc.nextInt();
      PickingResult result = inventory.pickProduct(product, amount);
      Product p = inventory.getProducts().get(product);
      if(amount > p.getLevel()){
        System.out.println("Not enough in stock.\nSelect a new amount: ");
        amount = sc.nextInt();
      }
      System.out.println("We have " + result.getProduct().getLevel() + " left.");
    }
    if(n == 2){
      System.out.println("Enter the product you wish to restock: ");
      String product = sc.next();
      System.out.println("Enter the amount you want to restock: ");
      int amount = sc.nextInt();
      RestockingResult result = inventory.restockProduct(product, amount);
      System.out.println("The amount in stock now is: " + result.getProduct().getLevel());
    }
    if(n == 0){
      System.exit(0);
    }
  }
}
