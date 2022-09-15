package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rowsRoom = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsRoom = scanner.nextInt();

        String[][] room = printSeats(rowsRoom, seatsRoom);
        menu(rowsRoom, seatsRoom, room);
    }
    public static void menu (int rowsRoom, int seatsRoom, String[][] room) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                cinemaPrint(room);
                menu(rowsRoom, seatsRoom, room);
                break;
            case 2:
                String[][] buySeats = buyTicket(rowsRoom, seatsRoom, room);
                menu(rowsRoom, seatsRoom, room);
                break;
            case 3:
                statistic(room);
                menu(rowsRoom, seatsRoom, room);
            case 0:
                break;
        }
    }

    public static void statistic(String[][] room) {
        int purchased = purchasedTickets(room);
        float percentage = percentage(room);
        int totalIncome = totalIncome(room);
        int currentIncome = currentIncome(room);
        System.out.println("Number of purchased tickets: " + purchased);
        System.out.println("Percentage: " + String.format("%.2f", percentage) + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);

    }
    public static int purchasedTickets(String[][] room) {
        int count = 0;
        for (String[] strings : room) {
            for (String string : strings) {
                if (string.equals("B ")) {
                    count++;
                }
            }
        }
        return count;
    }
    public static float percentage(String[][] room) {
        int bothTickets = purchasedTickets(room);
        int numberRows = room[0].length - 1;
        int numberSeats = room[1].length - 1;
        int allSeats = numberSeats * numberRows;
        float percentage;
        if (bothTickets == 0) {
            percentage = 0.0f;
        } else {
            percentage = (float) (bothTickets * 100) / allSeats;
        }
        return percentage;
    }
    public static int currentIncome(String[][] room) {
        int currentIncome = 0;
        int numberRows = room[0].length - 1;
        int numberSeats = room[1].length - 1;
        int sizeRoom = numberSeats * numberRows;
        int halfRows = numberRows / 2;

        for (int i = 0; i <= numberRows; i++) {
            for (int j = 0; j <= numberSeats; j++) {
                if (room[i][j].equals("B ")) {
                    if (sizeRoom <= 60) {
                        currentIncome += 10;
                    } else {
                        if (i <= halfRows) {
                            currentIncome += 10;
                        } else {
                            currentIncome += 8;
                        }
                    }
                }
            }
        }
        return currentIncome;
    }
    public static int totalIncome(String[][] room) {
        int numberRows = room[0].length - 1;
        int numberSeats = room[1].length - 1;
        int sizeRoom = numberSeats * numberRows;
        int halfRows = numberRows / 2;
        int totalIncome;

        if (sizeRoom > 60) {
            totalIncome = (halfRows * numberSeats * 10) + ((numberRows - halfRows) * numberSeats * 8);
        } else {
            totalIncome = sizeRoom * 10;
        }
        return totalIncome;
    }
    public static String[][] printSeats(int rowsRoom, int seatsRoom) {
        String[][] seats = new String[rowsRoom + 1][seatsRoom + 1];
        for (int i = 0; i <= rowsRoom; i++) {
            for (int j = 0; j <= seatsRoom; j++) {
                if (i == 0 & j == 0) {
                    seats[0][0] = " ";
                } else {
                    if (i == 0 && j > 0) {
                        seats[i][j] = j + " ";
                    } else {
                        if (j == 0 && i > 0) {
                            seats[i][j] = i + " ";
                        } else {
                            seats[i][j] = "S" + " ";
                        }
                    }
                }
            }
        }
        return seats;
    }
    public static String[][] buyTicket(int rowsRoom, int seatsRoom, String[][] room) {
        Scanner scanner = new Scanner(System.in);
        int rows;
        int seats;
        boolean result;
        int sizeRoom = rowsRoom * seatsRoom;

        do {
            System.out.println("Enter a row number:");
            rows = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seats = scanner.nextInt();
            if (rows > rowsRoom || seats > seatsRoom) {
                System.out.println("Wrong input!");
                result = true;
            } else if (room[rows][seats].equals("B ")) {
                System.out.println("That ticket has already been purchased!");
                result = true;
            } else {
                price(sizeRoom, rowsRoom, rows);
                result = false;
            }
        } while(result);

        room[rows][seats] = "B ";
        return room;
    }
    public static void price(int sizeRoom, int rowsRoom, int rows) {
        if (sizeRoom > 60) {
            float halfRows = (float) rowsRoom / rows;
            if (halfRows >= 2.0) {
                System.out.println("Ticket price: $10");
            } else {
                System.out.println("Ticket price: $8");
            }
        } else {
            System.out.println("Ticket price: $10");
        }
    }
    public static void cinemaPrint(String[][] room) {
        System.out.println("Cinema: ");
        for (String[] strings : room) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
    }
}
