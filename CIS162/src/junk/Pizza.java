package junk;
import java.util.*;

public class Pizza {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        int pizzas, people; //<--- this is ok, except you never instantiate the variable 'people'

        System.out.print ("People: ");
//        pizzas = (int) Math.ceil(people / 6); //<--- there is no value here for 'people'. the compiler sees 'null/6'
        people = keyboard.nextInt(); //<--- you instantiated people after you tried to use it.
        //pizzas = (people/6) +1;
//        System.out.println("Pizzas: " + pizzas);
//        System.out.println("Cost: $" + pizzas * 14.95);

    }
}