package budgetmanager;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Budget_Backend {
    ArrayList<String> purchases = new ArrayList<>();
    ArrayList<Double> prices = new ArrayList<>();
    ArrayList<Double> incomeDeposits = new ArrayList<>();
    ArrayList<String> foodPurchases = new ArrayList<>();
    ArrayList<String> clothesPurchases = new ArrayList<>();
    ArrayList<String> entertainmentPurchases = new ArrayList<>();
    ArrayList<String> otherPurchases = new ArrayList<>();
    ArrayList<Double> foodPrices = new ArrayList<>();
    ArrayList<Double> clothesPrices = new ArrayList<>();
    ArrayList<Double> entertainmentPrices = new ArrayList<>();
    ArrayList<Double> otherPrices = new ArrayList<>();
    ArrayList<String> purchaseTypes = new ArrayList<>();
    Double purchaseTotal;
    Double grandTotal = 0.0;
    //Double incomeDeposit;
    Double incomeTotal = 0.0;
    Double balance = 0.0;
    File file = new File("Purchases.txt"); 

       
    public void addIncome(double income) {
        this.balance += income;
        System.out.println("Income was added!");
    } 
    
    public void addFoodPurchase() {
        String purchaseName = "";
        Double itemTotal = 0.0;
        System.out.println("Enter food purchase name: ");
        foodPurchases.add(purchaseName);
        purchases.add(purchaseName);
        System.out.println("Enter its price: ");
        foodPrices.add(itemTotal);
        prices.add(itemTotal);
        grandTotal+= itemTotal;
        System.out.println("Purchase was added!"); 
        balance -= itemTotal;
        purchaseTypes.add("Food");
        System.out.println();
    } 
    
    public void addClothesPurchase() {
        String purchaseName = "";
        Double itemTotal = 0.0;
        System.out.println("Enter clothes purchase name: ");
        clothesPurchases.add(purchaseName);
        purchases.add(purchaseName);
        System.out.println("Enter its price: ");
        clothesPrices.add(itemTotal);
        prices.add(itemTotal);
        grandTotal+= itemTotal;
        System.out.println("Purchase was added!"); 
        balance -= itemTotal;
        purchaseTypes.add("Clothes");
        System.out.println();
    }  // Works as intended. Don't touch.
    
    public void addEntertainmentPurchase() {
        String purchaseName = "";
        Double itemTotal = 0.0;
        System.out.println("Enter entertainment purchase name: ");
        entertainmentPurchases.add(purchaseName);
        purchases.add(purchaseName);
        System.out.println("Enter its price: ");
        entertainmentPrices.add(itemTotal);
        prices.add(itemTotal);
        grandTotal+= itemTotal;
        System.out.println("Purchase was added!"); 
        balance -= itemTotal;
        purchaseTypes.add("Entertainment");
        System.out.println();
        //addPurchaseMenu();
    }  // Works as intended. Don't touch.

    public void addOtherPurchase() {
        String purchaseName = "";
        Double itemTotal = 0.0;
        otherPurchases.add(purchaseName);
        purchases.add(purchaseName);
        otherPrices.add(itemTotal);
        prices.add(itemTotal);
        grandTotal+= itemTotal;
        balance -= itemTotal;
        purchaseTypes.add("Other");
    } // Works as intended. Don't touch.
   
    public void showPurchases() {
        for (int i = 0; i < purchases.size(); i++) {
            System.out.print(purchases.get(i) + " ");
            System.out.println("$" + prices.get(i));
        }
        if (prices.isEmpty()) {
            System.out.print("Purchase list is empty");
            System.out.println();
        } 
        else {
            System.out.println("Total sum: $" + grandTotal);
            System.out.println();
        }
    }// Works as intended. Don't touch.
    
    public void showFoodPurchases() {
        double foodPriceTotal = 0;
        System.out.println("Food:");
        for (int i = 0; i < foodPurchases.size(); i++) {
            System.out.print(foodPurchases.get(i) + " ");
            System.out.println("$" + foodPrices.get(i));
            foodPriceTotal += foodPrices.get(i);
        }
        if (foodPrices.isEmpty()) {
            System.out.println("Purchase list is empty!");
            System.out.println();
        } else {
            System.out.println("Total sum: $" + foodPriceTotal);
        }
    }// Works as intended. Don't touch.
    
    public void showClothesPurchases() {
        double clothesPriceTotal = 0;
        System.out.println("Clothes:");
        for (int i = 0; i < clothesPurchases.size(); i++) {
            System.out.print(clothesPurchases.get(i) + " ");
            System.out.println("$" + clothesPrices.get(i));
            clothesPriceTotal += clothesPrices.get(i);
        }
        if (clothesPurchases.isEmpty()) {
            System.out.println("Purchase list is empty!");
            System.out.println();
        } else {
            System.out.println("Total sum: $" + clothesPriceTotal);
        }
    }// Works as intended. Don't touch.
    
    public void showEntertainmentPurchases() {
        double entertainmentPriceTotal = 0;
        System.out.println("Entertainment:");
        for (int i = 0; i < entertainmentPurchases.size(); i++) {
            System.out.print(entertainmentPurchases.get(i) + " ");
            System.out.println("$" + entertainmentPrices.get(i));
            entertainmentPriceTotal += entertainmentPrices.get(i);
        }
        if (entertainmentPurchases.isEmpty()) {
            System.out.println("Purchase list is empty!");
            System.out.println();
        } else {
            System.out.println("Total sum: $" + entertainmentPriceTotal);
        }
    }// Works as intended. Don't touch.
    
    public void showOtherPurchases() {
        double otherPriceTotal = 0;
        System.out.println("Other:");
        for (int i = 0; i < otherPurchases.size(); i++) {
            System.out.print(otherPurchases.get(i) + " ");
            System.out.println("$" + otherPrices.get(i));
            otherPriceTotal += otherPrices.get(i);
        }
        if (otherPurchases.isEmpty()) {
            System.out.println("Purchase list is empty!");
            System.out.println();
        } else {
            System.out.println("Total sum: $" + otherPriceTotal);
        }
    }// Works as intended. Don't touch.
    
    public double getBalance() {
        
        return this.balance;
    } // Works as intended. Don't touch.
       
    public void savePurchases() {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println(balance);
            writer.println(grandTotal);
            for (int i = 0; i < purchases.size(); i++) {
                writer.print(purchaseTypes.get(i) + " ");
                writer.print(purchases.get(i) + " ");
                writer.println(prices.get(i));
                System.out.println("Purchase line was saved!");
            }
        } catch (Exception e) {
            System.out.println("An error has occurred saving purchases.");
            System.out.println(e.getMessage());
        }
    }  // Works as intended. Don't touch.
    
    public void loadPurchases() {
        try (Scanner fileScanner = new Scanner(file)) {
            balance = Double.parseDouble(fileScanner.next());
            grandTotal = Double.parseDouble(fileScanner.next());
            while (fileScanner.hasNext()) {
                purchaseTypes.add(fileScanner.next());
                System.out.println("purchaseTypes added!");
                purchases.add(fileScanner.next());
                System.out.println("purchases added!");
                prices.add(fileScanner.nextDouble());
                System.out.println("prices added!");
            }
        } catch (Exception e) {
            System.out.println("An error has occurred loading purchases.");
            System.out.println(e.getMessage());
        }
    } // Works as intended. Don't touch.
    
    public void populatePTLists(){
        if (purchaseTypes.size() != 0) {
            for (int i = 0; i < purchaseTypes.size(); i++) {
                if (purchaseTypes.get(i).equals("Food")) {
                    foodPurchases.add(purchases.get(i));
                    foodPrices.add(prices.get(i));
                } else if (purchaseTypes.get(i).equals("Clothes")) {
                    clothesPurchases.add(purchases.get(i));
                    clothesPrices.add(prices.get(i));
                } else if (purchaseTypes.get(i).equals("Entertainment")) {
                    entertainmentPurchases.add(purchases.get(i));
                    entertainmentPrices.add(prices.get(i));
                } else if (purchaseTypes.get(i).equals("Other")) {
                    otherPurchases.add(purchases.get(i));
                    otherPrices.add(prices.get(i));
                }
            }
        } else {
            System.out.println("No purchases found.");
        }
    }// Works as intended. Don't touch.
       
}
