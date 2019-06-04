package classwork;

import java.util.Scanner;

public class ConvertTemp {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int userInput;


        System.out.println("[1] Convert to Celsius: ");
        System.out.println("[2] Convert to Fahrenheit: ");

        try{
            userInput = in.nextInt();
        }
        catch (Exception e){
            System.out.println("Error: Improper Input.");
        }

    }
}
