package labs;

import java.util.Scanner;

public class Madlib {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String keyboard, mouse, monitor, headphones, chair;

        //prompt for input
        System.out.println("Enter a keyboard: ");
        keyboard = in.nextLine();
        System.out.println("Enter a mouse: ");
        mouse = in.nextLine();
        System.out.println("Enter a monitor: ");
        monitor = in.nextLine();
        System.out.println("Enter a headphones: ");
        headphones = in.nextLine();
        System.out.println("Enter a chair: ");
        chair = in.nextLine();

        System.out.printf("Welcome to the PC Master race, " +
                "with your %s keyboard, \n %s mouse, %s monitor" +
                " %s headphones and %s chair.\n",
                keyboard, mouse, monitor, headphones, chair);
    }
}
