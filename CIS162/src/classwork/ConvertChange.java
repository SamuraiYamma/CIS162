package classwork;

import java.util.Scanner;

public class ConvertChange {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int money;

        int[] moneyValue = {10000, 5000, 2000, 1000, 500, 200, 100, 50, 25, 10, 05, 01};
        String[] moneyNames = {"Hundreds", "Fifties", "Twenties", "Tens", "Fives", "Twos", "Ones", "Half-Dollars", "Quarters", "Dimes", "Nickels", "Pennies"};

        System.out.println("Enter your cash: ");

        money = (int) (in.nextDouble() * 100); //we expect money to come into values with only two decimal places behind

        for (int i = 0; i < moneyValue.length; i++) {
            if(money >= moneyValue[i]){ //if it is divisible at least once
                int bills = money/moneyValue[i];//how many denominations we have of this money
                money = money - (bills * moneyValue[i]);//get remaining money
                System.out.printf("%s: %d", moneyNames[i], bills);
                System.out.println();
            }
        }
    }
}
