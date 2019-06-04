package classwork;

import java.util.Scanner;

public class lab310 {
    public static void main(String[] args) {
            /* Type your code here. */

            //init variables
            int quarters, dimes, nickels, pennies;
            int qValue, dValue, nValue, pValue;
            double totalValue;
            qValue = 25;
            dValue = 10;
            nValue = 5;
            pValue = 1;
            //init inputs
            Scanner in = new Scanner(System.in);

            //get inputs
            System.out.print("Quarters: ");
            quarters = in.nextInt();
            System.out.print("Dimes: ");
            dimes = in.nextInt();
            System.out.print("Nickels: ");
            nickels = in.nextInt();
            System.out.print("Pennies: ");
            pennies = in.nextInt();

            //compute
            nValue = (nValue * nickels);
            qValue = (qValue * quarters);
            dValue = (dValue * dimes);
            pValue = (pValue * pennies);

            totalValue = (qValue + dValue + nValue + pValue) / 100.0;

            //display output
            System.out.print("Dollars: $" + totalValue);
            System.out.println();
        }
}
