public class CinemaApp {
    // Кількість залів, рядів і місць
    private static final int NUM_HALLS = 5;
    private static final int NUM_ROWS = 10;
    private static final int NUM_SEATS_PER_ROW = 20;

    // 3D масив для представлення кінотеатру
    private int[][][] cinemaSeats;

    // Конструктор для ініціалізації масиву
    public CinemaApp() {
        cinemaSeats = new int[NUM_HALLS][NUM_ROWS][NUM_SEATS_PER_ROW];
        // Ініціалізація всіх місць нулями (вільні)
        for (int hall = 0; hall < NUM_HALLS; hall++) {
            for (int row = 0; row < NUM_ROWS; row++) {
                for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
                    cinemaSeats[hall][row][seat] = 0;
                }
            }
        }
    }

    // Метод для бронювання місць
    public void bookSeats(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            if (cinemaSeats[hallNumber][row][seat] == 0) {
                cinemaSeats[hallNumber][row][seat] = 1; // Заброньовано
            } else {
                System.out.println("Місце " + seat + " в ряду " + row + " залу " + hallNumber + " вже заброньоване.");
            }
        }
    }

    // Метод для скасування бронювання місць
    public void cancelBooking(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            if (cinemaSeats[hallNumber][row][seat] == 1) {
                cinemaSeats[hallNumber][row][seat] = 0; // Скасовано бронювання
            } else {
                System.out.println("Місце " + seat + " в ряду " + row + " залу " + hallNumber + " не було заброньоване.");
            }
        }
    }

    // Метод для перевірки доступності заданої кількості послідовних місць
    public boolean checkAvailability(int hallNumber, int numSeats) {
        for (int row = 0; row < NUM_ROWS; row++) {
            int consecutiveSeats = 0;
            for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
                if (cinemaSeats[hallNumber][row][seat] == 0) {
                    consecutiveSeats++;
                    if (consecutiveSeats == numSeats) {
                        return true; // Знайдено достатню кількість послідовних місць
                    }
                } else {
                    consecutiveSeats = 0; // Збірка послідовних місць обірвалась
                }
            }
        }
        return false; // Недостатньо послідовних місць в жодному ряду
    }

    // Метод для друку схеми розміщення місць
    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Схема розміщення місць для залу " + hallNumber + ":");

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int seat = 0; seat < NUM_SEATS_PER_ROW; seat++) {
                if (cinemaSeats[hallNumber][row][seat] == 0) {
                    System.out.print("O "); // Вільне місце
                } else {
                    System.out.print("X "); // Заброньоване місце
                }
            }
            System.out.println(); // Перехід на новий ряд
        }
    }

    public static void main(String[] args) {
        CinemaApp cinema = new CinemaApp();

        // Приклад використання
        cinema.bookSeats(1, 2, new int[]{3, 4, 5});
        cinema.printSeatingArrangement(1);

        cinema.cancelBooking(1, 2, new int[]{4});
        cinema.printSeatingArrangement(1);

        boolean available = cinema.checkAvailability(1, 3);
        System.out.println("Доступність 3 послідовних місць в залі 1: " + available);
    }
}
