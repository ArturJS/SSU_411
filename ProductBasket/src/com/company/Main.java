package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IBasket basket = new Basket();
        String product, quantity, command;
        Scanner in = new Scanner(System.in);

        do {
            System.out.println("Available commands:");
            System.out.println("\t 1) Add product;");
            System.out.println("\t 2) Remove product;");
            System.out.println("\t 3) Update product quantity;");
            System.out.println("\t 4) Clear basket;");
            System.out.println("\t 5) Get product list;");
            System.out.println("\t 6) Get product quantity;");
            System.out.println("\t q) Quit");
            System.out.print("Enter symbol and hit [Enter]\n->");

            command = in.nextLine();

            switch (command) {
                case "1"://Add
                    System.out.print("Enter product name: ");
                    product = in.nextLine();
                    System.out.print("Enter product quantity: ");
                    quantity = in.nextLine();
                    try {
                        int count = Integer.parseInt(quantity);
                        basket.addProduct(product, count);
                        System.out.println("Product added successfully!");
                    } catch (Exception e) {
                        System.out.println("Error! Product NOT added!");
                    }
                    break;
                case "2"://Remove
                    System.out.print("Enter product name: ");
                    product = in.nextLine();
                    try {
                        basket.removeProduct(product);
                        System.out.println("Product removed successfully!");
                    } catch (Exception e) {
                        System.out.println("Error! Product NOT removed!");
                    }
                    break;
                case "3"://Update
                    System.out.print("Enter product name: ");
                    product = in.nextLine();
                    System.out.print("Enter product quantity: ");
                    quantity = in.nextLine();
                    try {
                        basket.updateProductQuantity(product, Integer.parseInt(quantity));
                        System.out.println("Product updated successfully!");
                    } catch (Exception e) {
                        System.out.println("Error! Product NOT updated!");
                    }
                    break;
                case "4"://Clear
                    basket.clear();
                    System.out.println("Product basket cleared!");
                    break;
                case "5"://Get product list
                    if (basket.getProducts().isEmpty()) {
                        System.out.println("Product basket is empty!");
                    } else {
                        System.out.println("Product list:");
                        for (String productName : basket.getProducts()) {
                            System.out.println("\t=> " + productName);
                        }
                    }
                    break;
                case "6"://Get product quantity
                    System.out.print("Enter product name: ");
                    product = in.nextLine();
                    try {
                        System.out.println("Product quantity = " + basket.getProductQuantity(product));
                    } catch (Exception e) {
                        System.out.println("Error! Product NOT found!");
                    }
                    break;
                case "q":
                    break;
                default:
                    System.out.println("Error! Wrong command!");
                    break;
            }
            System.out.println("=========================\n");
        } while (!command.toLowerCase().equals("q"));
    }
}
