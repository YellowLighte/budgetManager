
package budgetmanager;

//import java.awt.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    VBox leftMenu;
    VBox purchaseMenu;
    GridPane gridLanding;
    GridPane gridAddBalance;
    GridPane gridAddPurchase;
    GridPane gridShowPurchase;
    GridPane gridBalance;
    GridPane gridSave;
    GridPane gridLoad;
    Button btnUpdateBal;
    
    public static void main(String[] args) {
        launch(args);
    }

    public BudgetManager() {
        this.gridInset = new Insets(10, 10, 10, 10);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        //Test: create Budget_Backend object
        Budget_Backend backend = new Budget_Backend();
        
        //Setting up generic look of window
        window = primaryStage;
        window.setTitle("Budget Manager - Alpha");
        
        populateGrids();       
        populateSideMenu();
        populateAddPurchaseMenu();

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
        Label lblAddPurchase = new Label("Will implement add purchase crap here");
        
        gridAddPurchase.getChildren().addAll(lblAddPurchase); 
        
        //Show purchases
        gridShowPurchase = new GridPane();
        gridShowPurchase.setPadding(gridInset);
        Label lblShowPurchase = new Label("Will implement show purchase crap here");
        gridShowPurchase.getChildren().add(lblShowPurchase);
        
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
        Label lblSave = new Label("Will implement save crap here");
        gridSave.getChildren().add(lblSave);
        
        //Load
        gridLoad = new GridPane();
        gridLoad.setPadding(gridInset);
        Label lblLoad = new Label("Will implement load crap here");
        gridLoad.getChildren().add(lblLoad);

    }
    
    public void populateSideMenu() {
        //Side menu
        leftMenu = new VBox(10);
        Button btnAddBalance = new Button("Add Balance");
        btnAddBalance.setOnAction(e -> 
                {
                    borderPane.setCenter(gridAddBalance);
                    borderPane.setRight(null); 
                    TextInputDialog txtID = new TextInputDialog(); 
                    txtID.showAndWait();
                    backend.addIncome(Double.parseDouble(txtID.getEditor().getText()));
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
    
    
    
    
    
}

