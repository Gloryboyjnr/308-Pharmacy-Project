package com.example.pharmacy_management_system;

import java.util.*;

public class Drugs {
    private String nationalDrugCode;
    private String drugName;
    private Set<String> suppliers;
    private List<PurchaseRecord> purchaseHistoryList;

    public Drugs(String nationalDrugCode, String drugName){
        this.drugName = drugName;
        this.nationalDrugCode = nationalDrugCode;
        this.purchaseHistoryList = new ArrayList<>();
        this.suppliers = new HashSet<>();
    }

    public String getNationalDrugCode() {
        return nationalDrugCode;
    }

    public String getDrugName() {
        return drugName;
    }

    public List<PurchaseRecord> getPurchaseHistoryList() {
        return purchaseHistoryList;
    }

    public Set<String> getSuppliers() {
        return suppliers;
    }

    public void addPurchaseRecord(PurchaseRecord record) {
        purchaseHistoryList.add(record);
        Collections.sort(purchaseHistoryList, Comparator.comparing(PurchaseRecord::getPurchaseDate));
    }

    public void addSupplier(String supplierId) {
        suppliers.add(supplierId);
    }
}
