package labs;

import java.util.Scanner;

public class OrderPizza {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        final double price = 9.99;
        final double tax = 1.06;

        int pizzas;

        double subCost, totalCost;

        System.out.print("How many pizzas? ");
        pizzas = in.nextInt();

        subCost = pizzas * price;
        totalCost = subCost * tax;

        System.out.printf("Sub Total: $%.2f\nTotal Due: $%.2f\n", subCost, totalCost);
    }
}
