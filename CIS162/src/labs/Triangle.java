package labs;

import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double side1, side2, side3, s, area;

        System.out.print("Enter Side A: ");
        side1 = in.nextFloat();
        System.out.print("Enter Side B: ");
        side2 = in.nextFloat();
        System.out.print("Enter Side C: ");
        side3 = in.nextFloat();

        s = (side1 + side2 + side3)/2;
        area = Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));

        System.out.printf("THe area of the triangle is: %.3f", area);
    }
}
