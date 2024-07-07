package com.example.pharmacy_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PharmacyManagementSystem {

    public void addDrug(Drugs drug) {
        String sql = "INSERT INTO Drugs (nationalDrugCode, drugName) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, drug.getNationalDrugCode());
            stmt.setString(2, drug.getDrugName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeDrug(String code) {
        String sql = "DELETE FROM Drugs WHERE nationalDrugCode = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addPurchaseRecord(String nationalDrugCode, PurchaseRecord record) {
        String sql = "INSERT INTO PurchaseRecord (drug_code, buyer, purchase_date, amount) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nationalDrugCode);
            stmt.setString(2, record.getBuyer());
            stmt.setDate(3, new java.sql.Date(record.getPurchaseDate().getTime()));
            stmt.setDouble(4, record.getAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addSupplierToDrug(String drugCode, String supplierId){
        String sql = "INSERT INTO dsupplierS (drug_code, supplier_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, drugCode);
            stmt.setString(2, supplierId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeSupplierToDrug(String code) {
        String sql = "DELETE FROM dsuppliers WHERE drug_code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Drugs> getAllDrugs() {
        List<Drugs> drugs = new ArrayList<>();
        String sql = "SELECT nationalDrugCode, drugName FROM Drugs";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String code = rs.getString("nationalDrugCode");
                String name = rs.getString("drugName");
                drugs.add(new Drugs(code, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drugs;
    }


    public List<PurchaseRecord> getPurchaseHistory(String drugCode) {
        List<PurchaseRecord> purchaseHistory = new ArrayList<>();
        String sql = "SELECT drug_code, buyer, purchase_date, amount FROM PurchaseRecord WHERE drug_code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, drugCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String drugCodeResult = rs.getString("drug_code");
                String buyer = rs.getString("buyer");
                Date purchaseDate = rs.getDate("purchase_date");
                double amount = rs.getDouble("amount");
                purchaseHistory.add(new PurchaseRecord(buyer, purchaseDate, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return purchaseHistory;
    }


    public List<String> getSuppliersForDrug(String drugCode) {
        List<String> suppliers = new ArrayList<>();
        String sql = "SELECT supplier_id FROM dsuppliers WHERE drug_code = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, drugCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                suppliers.add(rs.getString("supplier_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

}