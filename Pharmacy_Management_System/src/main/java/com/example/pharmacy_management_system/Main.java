package com.example.pharmacy_management_system;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Date;
import java.util.List;

public class Main extends Application {

    private PharmacyManagementSystem pms;

    @Override
    public void start(Stage primaryStage) {
        pms = new PharmacyManagementSystem();

        primaryStage.setTitle("Pharmacy Management System");

        TabPane tabPane = new TabPane();

        Tab tabAddDrug = new Tab("Add Drug", createAddDrugPane());
        Tab tabRemoveDrug = new Tab("Remove Drug", createRemoveDrugPane());
        Tab tabAddSupplier = new Tab("Add Supplier", createAddSupplierPane());
        Tab tabRemoveSupplier = new Tab("Remove Supplier", createRemoveSupplierPane());
        Tab tabShowDrugs = new Tab("Show All Drugs", createShowDrugsPane());
        Tab tabDisplaySuppliers = new Tab("Display Suppliers", createDisplaySuppliersPane());
        Tab tabAddPurchaseRecord = new Tab("Add Purchase Record", createAddPurchaseRecordPane());
        Tab tabDisplayPurchaseHistory = new Tab("Purchase History", createDisplayPurchaseRecordsPane());

        tabPane.getTabs().addAll(tabAddDrug, tabRemoveDrug, tabAddSupplier, tabRemoveSupplier, tabShowDrugs, tabDisplaySuppliers, tabAddPurchaseRecord, tabDisplayPurchaseHistory);

        // Create the layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(tabPane);

        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TitledPane createAddDrugPane() {
        GridPane grid = createDefaultGridPane();

        Label lblDrugCode = new Label("Drug Code:");
        TextField txtDrugCode = new TextField();
        Label lblDrugName = new Label("Drug Name:");
        TextField txtDrugName = new TextField();
        Button btnAddDrug = new Button("Add Drug");

        grid.add(lblDrugCode, 0, 0);
        grid.add(txtDrugCode, 1, 0);
        grid.add(lblDrugName, 0, 1);
        grid.add(txtDrugName, 1, 1);
        grid.add(btnAddDrug, 1, 2);

        btnAddDrug.setOnAction(e -> {
            String code = txtDrugCode.getText();
            String name = txtDrugName.getText();
            Drugs drug = new Drugs(code, name);
            pms.addDrug(drug);
            txtDrugCode.clear();
            txtDrugName.clear();
        });

        return wrapInTitledPane(grid, "Add Drug");
    }

    private TitledPane createRemoveDrugPane() {
        GridPane grid = createDefaultGridPane();

        Label lblDrugCode = new Label("Drug Code:");
        TextField txtDrugCode = new TextField();
        Button btnRemoveDrug = new Button("Remove Drug");

        grid.add(lblDrugCode, 0, 0);
        grid.add(txtDrugCode, 1, 0);
        grid.add(btnRemoveDrug, 1, 1);

        btnRemoveDrug.setOnAction(e -> {
            String code = txtDrugCode.getText();
            pms.removeDrug(code);
            txtDrugCode.clear();
        });

        return wrapInTitledPane(grid, "Remove Drug");
    }

    private TitledPane createAddSupplierPane() {
        GridPane grid = createDefaultGridPane();

        Label lblDrugCode = new Label("Drug Code:");
        TextField txtDrugCode = new TextField();
        Label lblSupplierId = new Label("Supplier ID:");
        TextField txtSupplierId = new TextField();
        Button btnAddSupplier = new Button("Add Supplier");

        grid.add(lblDrugCode, 0, 0);
        grid.add(txtDrugCode, 1, 0);
        grid.add(lblSupplierId, 0, 1);
        grid.add(txtSupplierId, 1, 1);
        grid.add(btnAddSupplier, 1, 2);

        btnAddSupplier.setOnAction(e -> {
            String drugCode = txtDrugCode.getText();
            String supplierId = txtSupplierId.getText();
            pms.addSupplierToDrug(drugCode, supplierId);
            txtDrugCode.clear();
            txtSupplierId.clear();
        });

        return wrapInTitledPane(grid, "Add Supplier");
    }

    private TitledPane createRemoveSupplierPane() {
        GridPane grid = createDefaultGridPane();

        Label lblDrugCode = new Label("Drug Code:");
        TextField txtDrugCode = new TextField();
        Button btnRemoveSupplier = new Button("Remove Supplier");

        grid.add(lblDrugCode, 0, 0);
        grid.add(txtDrugCode, 1, 0);
        grid.add(btnRemoveSupplier, 1, 1);

        btnRemoveSupplier.setOnAction(e -> {
            String code = txtDrugCode.getText();
            pms.removeSupplierToDrug(code);
            txtDrugCode.clear();
        });

        return wrapInTitledPane(grid, "Remove Supplier");
    }

    private TitledPane createShowDrugsPane() {
        GridPane grid = createDefaultGridPane();

        TextArea txtDrugList = new TextArea();
        txtDrugList.setEditable(false);
        Button btnShowDrugs = new Button("Show All Drugs");

        grid.add(txtDrugList, 0, 0, 2, 1);
        grid.add(btnShowDrugs, 1, 1);

        btnShowDrugs.setOnAction(e -> {
            txtDrugList.clear();
            for (Drugs drug : pms.getAllDrugs()) {
                txtDrugList.appendText(drug.getNationalDrugCode() + " - " + drug.getDrugName() + "\n");
            }
        });

        return wrapInTitledPane(grid, "Show All Drugs");
    }

    private TitledPane createDisplaySuppliersPane() {
        GridPane grid = createDefaultGridPane();

        Label lblDrugCode = new Label("Drug Code:");
        TextField txtDrugCode = new TextField();
        TextArea txtSuppliersList = new TextArea();
        txtSuppliersList.setEditable(false);
        Button btnDisplaySuppliers = new Button("Display Suppliers");

        grid.add(lblDrugCode, 0, 0);
        grid.add(txtDrugCode, 1, 0);
        grid.add(btnDisplaySuppliers, 1, 1);
        grid.add(txtSuppliersList, 0, 2, 2, 1);

        btnDisplaySuppliers.setOnAction(e -> {
            String drugCode = txtDrugCode.getText();
            List<String> suppliers = pms.getSuppliersForDrug(drugCode);
            txtSuppliersList.clear();
            if (suppliers.isEmpty()) {
                txtSuppliersList.appendText("No suppliers found for drug code: " + drugCode);
            } else {
                for (String supplier : suppliers) {
                    txtSuppliersList.appendText("Supplier ID: " + supplier + "\n");
                }
            }
        });

        return wrapInTitledPane(grid, "Display Suppliers");
    }

    private TitledPane createAddPurchaseRecordPane() {
        GridPane grid = createDefaultGridPane();

        Label lblDrugCode = new Label("Drug Code:");
        TextField txtDrugCode = new TextField();
        Label lblBuyer = new Label("Buyer:");
        TextField txtBuyer = new TextField();
        Label lblPurchaseDate = new Label("Purchase Date:");
        DatePicker datePicker = new DatePicker();
        Label lblAmount = new Label("Amount:");
        TextField txtAmount = new TextField();
        Button btnAddPurchaseRecord = new Button("Add Purchase Record");

        grid.add(lblDrugCode, 0, 0);
        grid.add(txtDrugCode, 1, 0);
        grid.add(lblBuyer, 0, 1);
        grid.add(txtBuyer, 1, 1);
        grid.add(lblPurchaseDate, 0, 2);
        grid.add(datePicker, 1, 2);
        grid.add(lblAmount, 0, 3);
        grid.add(txtAmount, 1, 3);
        grid.add(btnAddPurchaseRecord, 1, 4);

        btnAddPurchaseRecord.setOnAction(e -> {
            String drugCode = txtDrugCode.getText();
            String buyer = txtBuyer.getText();
            Date purchaseDate = java.sql.Date.valueOf(datePicker.getValue());
            double amount = Double.parseDouble(txtAmount.getText());
            PurchaseRecord record = new PurchaseRecord(buyer, purchaseDate, amount);
            pms.addPurchaseRecord(drugCode, record);
            txtDrugCode.clear();
            txtBuyer.clear();
            datePicker.setValue(null);
            txtAmount.clear();
        });

        return wrapInTitledPane(grid, "Add Purchase Record");
    }

    private TitledPane createDisplayPurchaseRecordsPane() {
        GridPane grid = createDefaultGridPane();

        Label lblDrugCode = new Label("Drug Code:");
        TextField txtDrugCode = new TextField();
        TextArea txtPurchaseRecordsList = new TextArea();
        txtPurchaseRecordsList.setEditable(false);
        Button btnDisplayPurchaseRecords = new Button("Display Purchase Records");

        grid.add(lblDrugCode, 0, 0);
        grid.add(txtDrugCode, 1, 0);
        grid.add(btnDisplayPurchaseRecords, 1, 1);
        grid.add(txtPurchaseRecordsList, 0, 2, 2, 1);

        btnDisplayPurchaseRecords.setOnAction(e -> {
            String drugCode = txtDrugCode.getText();
            List<PurchaseRecord> purchaseRecords = pms.getPurchaseHistory(drugCode);
            txtPurchaseRecordsList.clear();
            if (purchaseRecords.isEmpty()) {
                txtPurchaseRecordsList.appendText("No purchase records found for drug code: " + drugCode);
            } else {
                for (PurchaseRecord record : purchaseRecords) {
                    txtPurchaseRecordsList.appendText("Buyer: " + record.getBuyer() +
                            ", Date: " + record.getPurchaseDate() +
                            ", Amount: " + record.getAmount() + "\n");
                }
            }
        });

        return wrapInTitledPane(grid, "Purchase History");
    }

    private GridPane createDefaultGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));
        return grid;
    }

    private TitledPane wrapInTitledPane(GridPane grid, String title) {
        TitledPane titledPane = new TitledPane();
        titledPane.setText(title);
        titledPane.setContent(grid);
        titledPane.setCollapsible(false);
        return titledPane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
