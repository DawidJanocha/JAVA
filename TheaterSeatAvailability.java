public class TheaterSeatAvailability {
    private static final int ROWS = 30;
    private static final int COLUMNS = 12;
    private boolean[][] seats;

    public TheaterSeatAvailability() {

        seats = new boolean[ROWS][COLUMNS];
    }

    public void book(char column, int row) {

        int colIndex = column - 'A';
        int rowIndex = row - 1;


        if (colIndex < 0 || colIndex >= COLUMNS || rowIndex < 0 || rowIndex >= ROWS) {
            System.out.println("Invalid seat reference.");
            return;
        }


        if (seats[rowIndex][colIndex]) {
            System.out.println("Seat " + column + row + " is already booked.");
        } else {
            seats[rowIndex][colIndex] = true;
            System.out.println("Seat " + column + row + " has been booked successfully.");
        }
    }

    public void cancel(char column, int row) {

        int colIndex = column - 'A';
        int rowIndex = row - 1;


        if (colIndex < 0 || colIndex >= COLUMNS || rowIndex < 0 || rowIndex >= ROWS) {
            System.out.println("Invalid seat reference.");
            return;
        }


        if (!seats[rowIndex][colIndex]) {
            System.out.println("Seat " + column + row + " is not booked.");
        } else {
            seats[rowIndex][colIndex] = false;
            System.out.println("Seat " + column + row + " has been canceled successfully.");
        }
    }

    public void displaySeats() {
        System.out.println("Current seating arrangement:");
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                char seatStatus = seats[i][j] ? 'X' : 'O';
                System.out.print(seatStatus + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TheaterSeatAvailability theater = new TheaterSeatAvailability();

        // Δοκιμές κράτησης θέσεων
        theater.book('B', 5);
        theater.book('A', 1);
        theater.book('C', 2);
        theater.book('B', 5);
        theater.cancel('B', 5);
        theater.cancel('B', 5);


        theater.displaySeats();
    }
}
