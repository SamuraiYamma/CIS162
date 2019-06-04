package labs;

import java.util.Scanner;

public class Login {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String fName, lName, login;
        int digits;

        System.out.print("Enter first name: ");
        fName = in.next();
        System.out.println();
        System.out.print("Enter last name: ");
        lName = in.next();
        System.out.println();
        System.out.print("Enter 4-digits: ");
        digits = in.nextInt();
        System.out.println();

        login = lName.substring(0,5) + fName.substring(0,1) + (digits % 100);

        System.out.printf("Your login name: %s\n", login);
    }
}
