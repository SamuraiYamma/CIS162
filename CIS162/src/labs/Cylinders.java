package labs;

import java.util.Scanner;

public class Cylinders {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        double radius, height, volume, area;


        System.out.print("Enter Radius: ");
        radius = in.nextFloat();

        System.out.print("Enter Height: ");
        height = in.nextFloat();

        volume = Math.PI * Math.pow(radius, 2) * height;
        area = 2 * Math.PI * radius * height;

        System.out.printf("Volume: %.1f cubic inches.\nSurface Area: %.1f square inches.\n",volume, area);
    }
}
