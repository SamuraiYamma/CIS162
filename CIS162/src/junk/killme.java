package junk; /**
 * @auther: Ethan Carter
 *
 * I'm bored af.
 */

import java.util.Scanner;

public class killme {
    public static void main(String[] args) {
        System.out.println("How many times would you like to die?");

        double temp = Math.sqrt(9.0);

        Scanner scanner = new Scanner(System.in);
        int amountOfDeaths = scanner.nextInt();

        for (int i = 0; i < amountOfDeaths; i++){
            if(i%3 == 0){
                System.out.println("You died by fire");
            }
            else if(i == 19){
                System.out.println("You died due to the DM's wrath");
            }
            else if(i % 17 == 0){
                System.out.println("You were strangled to death by a Lamia");
            }
            //Add more else if statements to vary up the way you can die.
//            else if()
            else{
                System.out.println("You died.");
            }
        }
    }
}
