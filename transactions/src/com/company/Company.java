package com.company;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class Company {
    private String name;
    private int businessAccount;
    private BigDecimal budget;

    public Company(String name, int businessAccount) {
        this.name = name;
        this.businessAccount = businessAccount;
        this.budget = new BigDecimal(0, new MathContext(2));
    }

    public Company(String name, int businessAccount, BigDecimal budget) {
        this.name = name;
        this.businessAccount = businessAccount;
        this.budget = budget;
    }

    public void depositMoney(BigDecimal value) {
        budget = budget.add(value);
    }

    public boolean debitMoney(BigDecimal value) {
        if (budget.compareTo(value) >= 0) {
            budget = budget.subtract(value);
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public int getBusinessAccount(){
        return businessAccount;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void showAllData() {
        DecimalFormat df = new DecimalFormat("#########.##");
        System.out.println("Name: " + name + "; businessAccount: " + businessAccount + "; budget: " + df.format(budget));
    }
}
