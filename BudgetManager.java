
package budgetmanager;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class BudgetManager extends Application {
    
    Budget_Backend backend = new Budget_Backend();
    Stage window;
    Scene scene0, scene1, scene2, scene3, scene4, scene5;
    BorderPane borderPane;
    private final Insets gridInset;
    ToggleGroup rbGroup;
    ToggleGroup rbShowGroup;
    VBox leftMenu;
    VBox purchaseMenu;
    VBox showPurchaseMenu;
    GridPane gridLanding;
    GridPane gridAddBalance;
    GridPane gridAddPurchase;
    GridPane gridShowPurchase;
    GridPane gridBalance;
    GridPane gridSave;
    GridPane gridLoad;
    Button btnUpdateBal;
    Button btnUpdateTAPurchase;
    TextArea taPurchases;
    Label lblPurchaseSum;
    
    public static void main(String[] args) {
        launch(args);
    }

    public BudgetManager() {
        this.gridInset = new Insets(10, 10, 10, 10);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Budget_Backend backend = new Budget_Backend();
        
        //Setting up generic look of window
        window = primaryStage;
        window.setTitle("Budget Manager - Alpha");
        
        populateGrids();       
        populateSideMenu();
        populateAddPurchaseMenu();
        populateShowPurchaseMenu();

        //Main menu        
        borderPane = new BorderPane();
        borderPane.setLeft(leftMenu);
        borderPane.setCenter(gridLanding);
                
        scene0 = new Scene(borderPane, 500, 350);
        
        
        window.setScene(scene0);
        window.show();
        
    }

    public void populateGrids() {
        //Landing
        gridLanding = new GridPane();
        Label lblLanding = new Label("This is the landing (main) page. Maybe "
                + "eventually will have a chart that shows current balance"
                + " and purchase totals.");
        gridLanding.setPadding(gridInset);
        gridLanding.setVgap(8);
        gridLanding.setBackground(new Background(new BackgroundFill(Color.LIGHTSTEELBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        gridLanding.getChildren().add(lblLanding);
        
        //Add balance
        gridAddBalance = new GridPane();
        gridAddBalance.setPadding(gridInset);
        gridAddBalance.setVgap(8);
        gridAddBalance.setHgap(10);
        Label lblIncome = new Label("Enter income:");
        TextField txtIncome = new TextField();
        Button btnSubmitBalance = new Button("Submit"); 
        btnSubmitBalance.setOnAction(e -> 
                {
                    backend.addIncome(Double.parseDouble(txtIncome.getText()));
                    txtIncome.setText("");
        });
        GridPane.setConstraints(lblIncome, 0, 1);
        GridPane.setConstraints(txtIncome, 1, 1);
        GridPane.setConstraints(btnSubmitBalance, 1, 2);
        gridAddBalance.getChildren().addAll(lblIncome, txtIncome, btnSubmitBalance);
        
                        
        //Add purchase
        gridAddPurchase = new GridPane();
        gridAddPurchase.setPadding(gridInset);
        Label lblAddPurchase = new Label("Purchase name:");
        TextField txtPurchaseName = new TextField();
        Label lblPurchasePrice = new Label("Price:");
        TextField txtPurchasePrice = new TextField();
        Button btnAddPurchase = new Button("Submit");
        btnAddPurchase.setOnAction(e -> 
                {
                    //Creates new Purchase object when button is clicked.
                    double purchasePrice = Double.parseDouble(txtPurchasePrice.getText());
                    Purchase purchase = new Purchase(txtPurchaseName.getText(),rbGroup.getSelectedToggle().toString(), purchasePrice);
                    backend.addPurchase(purchase);
                    System.out.println("Purchase created!");
                    txtPurchaseName.setText("");
                    txtPurchasePrice.setText("");
                                        
        });
        
        GridPane.setConstraints(lblAddPurchase, 0, 1);
        GridPane.setConstraints(txtPurchaseName, 0, 2);
        GridPane.setConstraints(lblPurchasePrice, 0, 3);
        GridPane.setConstraints(txtPurchasePrice, 0, 4);
        GridPane.setConstraints(btnAddPurchase, 0, 5);
        
        gridAddPurchase.getChildren().addAll(lblAddPurchase, txtPurchaseName, lblPurchasePrice, txtPurchasePrice, btnAddPurchase); 
        
        //Show purchases
        gridShowPurchase = new GridPane();
        gridShowPurchase.setPadding(gridInset);
        taPurchases = new TextArea();
        lblPurchaseSum = new Label("Total purchases: $");
        GridPane.setConstraints(taPurchases, 0, 0);
        GridPane.setConstraints(lblPurchaseSum, 0, 1);
        btnUpdateTAPurchase = new Button("Update TA list");
        btnUpdateTAPurchase.setOnAction(e
                -> {
            //Test
            if (!rbShowGroup.getSelectedToggle().toString().isEmpty()) {
                //End test
                if (backend.purchaseObjectsList.isEmpty()) {
                    taPurchases.setText("No purchases found!");
                    lblPurchaseSum.setText("Total purchases: $0.00");
                } else {
                    double purchaseSum = backend.sumOfPurchases(backend.purchaseObjectsList);
                    taPurchases.setText(backend.printPurchases());
                    lblPurchaseSum.setText("Total purchases: $" + purchaseSum);
                    System.out.println();
                }
            }
        });
        gridShowPurchase.getChildren().addAll(taPurchases, lblPurchaseSum);
        
        //Show balance
        gridBalance = new GridPane();
        gridBalance.setPadding(gridInset);
        Label lblBal = new Label("Balance: " + backend.getBalance());
        btnUpdateBal = new Button("Update Balance");
        btnUpdateBal.setOnAction(e -> 
                {
                    double bal = backend.getBalance();
                    lblBal.setText("Balance: " + bal);
                    
        });
        System.out.println(backend.getBalance());
        gridBalance.getChildren().add(lblBal);
        GridPane.setConstraints(lblBal, 0, 0);
        
        //Save
        gridSave = new GridPane();
        gridSave.setPadding(gridInset);
        Label lblSave = new Label("Will implement save methods here");
        gridSave.getChildren().add(lblSave);
        
        //Load
        gridLoad = new GridPane();
        gridLoad.setPadding(gridInset);
        Label lblLoad = new Label("Will implement load methods here");
        gridLoad.getChildren().add(lblLoad);

    }
    
    public void populateSideMenu() {
        //Side menu
        leftMenu = new VBox(10);
        Button btnAddBalance = new Button("Add Balance");
        btnAddBalance.setOnAction(e
                -> {
            borderPane.setRight(null);
            borderPane.setCenter(null);
            TextInputDialog txtID = new TextInputDialog();
            txtID.setHeaderText("Add income:");
            txtID.showAndWait();
            if (!txtID.getEditor().getText().isEmpty()) {
                backend.addIncome(Double.parseDouble(txtID.getEditor().getText()));
            }
        });
        
        Button btnAddPurchase = new Button("Add Purchase");
        btnAddPurchase.setOnAction(e -> 
                {
                    borderPane.setCenter(gridAddPurchase);
                    borderPane.setRight(purchaseMenu);
        });
        
        Button btnShowPurchases = new Button("Purchases");
        btnShowPurchases.setOnAction(e -> 
                {
                    borderPane.setCenter(gridShowPurchase);
                    borderPane.setRight(null);
                    btnUpdateTAPurchase.fire();
                    borderPane.setRight(showPurchaseMenu);
        });
        
        Button btnBalance = new Button("Balance");
        btnBalance.setOnAction(e -> 
                {
                    borderPane.setCenter(gridBalance);
                    borderPane.setRight(null);
                    btnUpdateBal.fire();
        });
        
        Button btnSave = new Button("Save");
        btnSave.setOnAction(e -> 
                {
                    borderPane.setCenter(gridSave);
                    borderPane.setRight(null);
        });
        
        Button btnLoad = new Button("Load");
        btnLoad.setOnAction(e -> 
                {
                    borderPane.setCenter(gridLoad);
                    borderPane.setRight(null);
        });
        
        leftMenu.getChildren().addAll(btnAddBalance, btnAddPurchase, btnShowPurchases, btnBalance, btnSave, btnLoad);
        leftMenu.setPadding(new Insets(10,10,10,10));
    }
    
    public void populateAddPurchaseMenu() {
        //Purchase radio button group
        purchaseMenu = new VBox(8);
        rbGroup = new ToggleGroup();
        Label lblPurchaseType = new Label("Purchase type:");
        RadioButton rbFood = new RadioButton("Food");
        RadioButton rbClothes = new RadioButton("Clothes");
        RadioButton rbEntertainment = new RadioButton("Entertainment");
        RadioButton rbOther = new RadioButton("Other");
        rbFood.setToggleGroup(rbGroup);
        rbClothes.setToggleGroup(rbGroup);
        rbEntertainment.setToggleGroup(rbGroup);
        rbOther.setToggleGroup(rbGroup);
                
        purchaseMenu.setPadding(gridInset);
        purchaseMenu.getChildren().addAll(lblPurchaseType, rbFood, rbClothes, rbEntertainment, rbOther);
    }
    
    public void populateShowPurchaseMenu() {
        showPurchaseMenu = new VBox(8);
        rbShowGroup = new ToggleGroup();
        Label lblPurchaseType = new Label("Purchase type:");
        RadioButton rbsAll = new RadioButton("All");
        RadioButton rbsFood = new RadioButton("Food");
        RadioButton rbsClothes = new RadioButton("Clothes");
        RadioButton rbsEntertainment = new RadioButton("Entertainment");
        RadioButton rbsOther = new RadioButton("Other");
        rbsAll.setToggleGroup(rbShowGroup);
        rbsFood.setToggleGroup(rbShowGroup);
        rbsClothes.setToggleGroup(rbShowGroup);
        rbsEntertainment.setToggleGroup(rbShowGroup);
        rbsOther.setToggleGroup(rbShowGroup);
        rbShowGroup.selectToggle(rbsAll);
        rbShowGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
            public void changed(ObservableValue<? extends Toggle> ob,  
                                                    Toggle o, Toggle n) 
            { 
  
                RadioButton rb = (RadioButton)rbShowGroup.getSelectedToggle(); 
  
                if (rb != null) { 
                    if (rb.equals(rbsAll)) {
                        taPurchases.setText(backend.printPurchases());
                    }
                    else {
                    String s = rb.getText(); 
  
                    taPurchases.setText(backend.printTypePurchases(s));
                    lblPurchaseSum.setText("Total purchases: $" + backend.sumOfPurchasesType(backend.purchaseObjectsList, s));
                    }
                } 
                
            } 
        }); 
        
        showPurchaseMenu.setPadding(gridInset);
        showPurchaseMenu.getChildren().addAll(lblPurchaseType, rbsAll, rbsFood, rbsClothes, rbsEntertainment, rbsOther);
    }
    
    
}

