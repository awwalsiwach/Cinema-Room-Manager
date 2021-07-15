package cinema;

import java.util.Scanner;
import java.util.Arrays;
public class Cinema {

    public static int price , totalSeats;
    public static int numofsoldtickets = 0;
    public static int income = 0;
    public static int ttlIncome = 0;
    public static float percentage = 0.00f;
    public static int rows,seats_in_row,rowno,seatno;
    public static String[][] seats;
    public static Scanner sn = new Scanner(System.in);

    public static void show()
    {
        System.out.println("Cinema:");



        System.out.println(Arrays.deepToString(seats)
                .replace("], ", "]\n")
                .replace("[", "")
                .replace("]", "")
                .replace(",", ""));

        System.out.println();
        menu();
    }

    public static void buy()
    {

        System.out.println("Enter a row number:");
        rowno = sn.nextInt();
        sn.nextLine();
        System.out.println("Enter a seat number in that row:");
        seatno = sn.nextInt();
        sn.nextLine();
        System.out.println();
        if(rowno>rows || seatno>seats_in_row)
        {
            System.out.println("Wrong input!");
            System.out.println("");
            rowno = 0;
            seatno = 0;
            buy();
        }
        if(seats[rowno][seatno] == "B")
        {
            System.out.println("That ticket has already been purchased!");
            rowno = 0;
            seatno = 0;
            System.out.println();
            buy();
        }

        numofsoldtickets++;
        price = totalSeats <= 60 ? 10 : 8;
        if (rowno <= rows / 2) {
            price = 10;
        }
        income += price;
        System.out.println("Ticket price: $"+price);

        if (rowno != 0 && seatno != 0) {
            seats[rowno][seatno] = "B";
        }

        System.out.println();
        menu();
    }

    public static void menu()
    {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choice = sn.nextInt();
        sn.nextLine();
        switch(choice)
        {
            case 0:
                return;
            case 1:
                System.out.println();
                show();
                break;
            case 2:
                System.out.println();
                buy();
                break;
            case 3:
                System.out.println();
                statistics();
                break;
            default:
                System.out.println();
                menu();
                break;
        }
    }

    public static void statistics()
    {

        if(totalSeats<=60)
            ttlIncome = totalSeats*10;
        else
            ttlIncome = (rows/2 * seats_in_row * 10) + ( (totalSeats-(rows/2 * seats_in_row))*8);

        try{
            percentage = ((float)numofsoldtickets*100f)/(float)totalSeats;
        }
        catch(Exception e){}
        System.out.println("Number of purchased tickets: "+numofsoldtickets);
        System.out.printf("Percentage: ");
        System.out.printf("%.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $"+income);
        System.out.println("Total income: $"+ttlIncome);
        System.out.println();
        menu();
    }

    public static void createCinema()
    {
        System.out.println("Enter the number of rows:");
        rows = sn.nextInt();
        sn.nextLine();
        System.out.println("Enter the number of seats in each row:");
        seats_in_row = sn.nextInt();
        sn.nextLine();

        totalSeats = rows * seats_in_row;
        seats = new String[rows + 1][seats_in_row + 1];

        for (int i = 0; i < rows + 1; i++)
        {
            for (int j = 0; j < seats_in_row + 1; j++)
            {
                if (i == 0 && j == 0)
                    seats[i][j] = " ";
                else if (i == 0)
                    seats[i][j] = String.valueOf(j);
                else if (j == 0)
                    seats[i][j] = String.valueOf(i);
                else
                {
                    seats[i][j] = "S";
                }
            }
        }

        System.out.println();
        menu();
    }

    public static void main(String[] args) {
            createCinema();
    }
}
