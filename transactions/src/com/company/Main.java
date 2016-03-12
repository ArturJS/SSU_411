package com.company;

import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<Integer, Company> companies = new HashMap<Integer, Company>();
        String fileName = "Sources\\information.csv";

        readAllCompanies(companies, fileName);

        readAllTransactions("Sources", companies);

        returnResults("Sources\\result.csv", companies);
    }

    private static void addCompany(HashMap<Integer, Company> companies, String line) {
        String[] companyInfo = line.split(";");
        Integer businessAccount = Integer.valueOf(companyInfo[1]);
        Company company = new Company(companyInfo[0], businessAccount, new BigDecimal(companyInfo[2], new MathContext(2)));
        companies.put(businessAccount, company);
        company.showAllData();
    }

    private static boolean isTransaction(String folderName, String fileName) {
        return fileName.matches("^" + folderName + "\\\\transaction[0-9]+\\.csv$");
    }

    private static void readAllCompanies(HashMap<Integer, Company> companies, String fileName) {
        String line;
        try {
            BufferedReader r = new BufferedReader(new FileReader(fileName));
            r.readLine();
            while ((line = r.readLine()) != null) {
                addCompany(companies, line);
            }
            r.close();
        } catch (IOException e) {
            System.out.println("File '" + fileName + "' not found!");
        }
    }

    private static void readAllTransactions(String folderName, HashMap<Integer, Company> companies) {
        File sourceFolder = new File(folderName);
        String fileName;

        for (File file : sourceFolder.listFiles()) {
            fileName = file.getPath();
            executeTransaction(folderName, fileName, companies);
        }
    }

    private static void executeTransaction(String folderName, String fileName, HashMap<Integer, Company> companies) {
        String line;
        int lineNumber;

        Company sender;
        String senderName;
        int senderAcc;

        Company receiver;
        String receiverName;
        int receiverAcc;

        BigDecimal money;
        String[] operationInfo;
        String operationLine;

        if (!isTransaction(folderName, fileName)) {
            return;
        }

        try {
            BufferedReader r = new BufferedReader(new FileReader(fileName));
            lineNumber = 1;
            r.readLine();
            while ((line = r.readLine()) != null) {
                lineNumber++;
                operationInfo = line.split(";");
                senderName = operationInfo[0];
                senderAcc = Integer.valueOf(operationInfo[1]);
                receiverName = operationInfo[2];
                receiverAcc = Integer.valueOf(operationInfo[3]);
                money = new BigDecimal(operationInfo[4]);
                operationLine = new String(fileName + "' line " + lineNumber + ".");
                System.out.println("====. " + operationLine +
                        " Money transfer done. Sender: '" + senderName + "; SenderAcc: " + senderAcc +
                        "'. Receiver: '" + receiverName + "; ReceiverAcc: " + receiverAcc + "'. Money: " + operationInfo[4]);
                if (!(companies.containsKey(senderAcc) && companies.get(senderAcc).getName().equals(senderName))) {
                    System.out.println("Error! '" + operationLine + " Sender not found!");
                    continue;
                }

                if (!(companies.containsKey(receiverAcc) && companies.get(receiverAcc).getName().equals(receiverName))) {
                    System.out.println("Error! '" + operationLine + " Receiver not found!");
                    continue;
                }

                sender = companies.get(senderAcc);
                receiver = companies.get(receiverAcc);
                if (sender.debitMoney(money)) {
                    receiver.depositMoney(money);
                    System.out.println("Info. " + operationLine +
                            " Money transfer done. Sender: '" + senderName + "; SenderAcc: " + senderAcc +
                            "'. Receiver: '" + receiverName + "; ReceiverAcc: " + receiverAcc + "'. Money: " + operationInfo[4]);
                } else {
                    System.out.println("Error! " + operationLine + " Not enough money for transfer!");
                }
            }
        } catch (IOException e) {
            System.out.println("File '" + fileName + "' is not processed!");
        }
    }

    private static void returnResults(String fileName, HashMap<Integer, Company> companies) {
        DecimalFormat df = new DecimalFormat("#########.##");
        DecimalFormat intf = new DecimalFormat("00000");
        String companyInfo;

        try (BufferedWriter w = new BufferedWriter(new FileWriter(fileName))) {
            w.write("");
            w.append("Имя компании;Рассчётный счёт;Бюджет;\n");
            System.out.println("\nResults: ");
            for (Company company : companies.values()) {
                companyInfo = new String(company.getName() + ";" + intf.format(company.getBusinessAccount()) + ";" + df.format(company.getBudget()) + ";\n");
                System.out.print(companyInfo);
                w.append(companyInfo);
            }
            w.close();
        } catch (IOException e) {
            System.out.println();
        }
    }
}
