package budgetmanager;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Budget_Backend {
    ArrayList<Purchase> purchaseObjectsList = new ArrayList<>();
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
    Double incomeTotal = 0.0;
    Double balance = 0.0;
    File file = new File("Purchases.txt"); 

       
    public void addIncome(double income) {
        this.balance += income;
        System.out.println("Income was added!");
    } 
    
    public void addPurchase(Purchase purchase) {
        purchaseObjectsList.add(purchase);
        this.balance -= purchase.getPrice();
    }
    
    /*
    //TODO: Figure out how to pull purchase lists from this. 
    //It seems like a promising start.
    */
    public ArrayList<Purchase> purchaseListReturn() { 
        return purchaseObjectsList;
    } 
       
    
    //TODO: Get totalSum to display in BudgetManager
    
    public String printPurchases() {
        String str = "";
        for (int i = 0; i < purchaseObjectsList.size(); i++) {
            str += this.purchaseObjectsList.get(i).print() + "\n";
        }
        return str;
    } //This is the one that works to show all purchases. Will have to create new method(s) to display different types. Also find way to show totalSum for the price totals.
            

   public double sumOfPurchases(ArrayList<Purchase> arr) {
       double sum = 0;
       
       for (int i = 0; i < arr.size(); i++) {
           sum += arr.get(i).getPrice();
       }
       
       return sum;
   } 
       
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
    }  // TODO: Get this to work with GUI
    
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
    } // TODO: Get this to work with GUI
    
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
    } //Might be obsolete with new Purchase object
}
