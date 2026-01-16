import java.util.ArrayList;
import java.util.Scanner;

class RoomDetails {
    private int roomNumber;
    private String roomCategory;
    private double roomPrice;
    private boolean bookingStatus;

    public RoomDetails(int roomNumber, String roomCategory, double roomPrice) {
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.roomPrice = roomPrice;
        this.bookingStatus = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public boolean isBooked() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}

class ReservationInfo {
    private String customerName;
    private RoomDetails bookedRoom;

    public ReservationInfo(String customerName, RoomDetails bookedRoom) {
        this.customerName = customerName;
        this.bookedRoom = bookedRoom;
    }

    public String getCustomerName() {
        return customerName;
    }

    public RoomDetails getBookedRoom() {
        return bookedRoom;
    }
}

public class HotelReservationSystem {

    private static Scanner userInput = new Scanner(System.in);
    private static ArrayList<RoomDetails> roomInventory = new ArrayList<>();
    private static ArrayList<ReservationInfo> reservationRecords = new ArrayList<>();

    public static void main(String[] args) {
        initializeRooms();
        int userChoice;

        do {
            showMainMenu();
            userChoice = userInput.nextInt();
            userInput.nextLine();

            switch (userChoice) {
                case 1:
                    displayAvailableRooms();
                    break;
                case 2:
                    bookRoomProcess();
                    break;
                case 3:
                    cancelBookingProcess();
                    break;
                case 4:
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }
        } while (userChoice != 4);
    }

    private static void showMainMenu() {
        System.out.println("\n====== Hotel Reservation System ======");
        System.out.println("1. View Available Rooms");
        System.out.println("2. Book a Room");
        System.out.println("3. Cancel Reservation");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void initializeRooms() {
        roomInventory.add(new RoomDetails(101, "Standard", 1500));
        roomInventory.add(new RoomDetails(102, "Standard", 1500));
        roomInventory.add(new RoomDetails(201, "Deluxe", 2500));
        roomInventory.add(new RoomDetails(202, "Deluxe", 2500));
        roomInventory.add(new RoomDetails(301, "Suite", 4000));
    }

    private static void displayAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        boolean roomFound = false;

        for (RoomDetails room : roomInventory) {
            if (!room.isBooked()) {
                System.out.println("Room No: " + room.getRoomNumber()
                        + " | Category: " + room.getRoomCategory()
                        + " | Price: Rs. " + room.getRoomPrice());
                roomFound = true;
            }
        }

        if (!roomFound) {
            System.out.println("No rooms available at the moment.");
        }
    }

    private static void bookRoomProcess() {
        System.out.print("Enter your name: ");
        String guestName = userInput.nextLine();

        System.out.print("Enter room number to book: ");
        int selectedRoomNumber = userInput.nextInt();

        for (RoomDetails room : roomInventory) {
            if (room.getRoomNumber() == selectedRoomNumber && !room.isBooked()) {
                simulatePayment(room.getRoomPrice());
                room.setBookingStatus(true);

                reservationRecords.add(new ReservationInfo(guestName, room));
                System.out.println("Room booked successfully for " + guestName);
                return;
            }
        }
        System.out.println("Room not available or invalid room number.");
    }

    private static void cancelBookingProcess() {
        System.out.print("Enter your name to cancel booking: ");
        String cancelName = userInput.nextLine();

        for (ReservationInfo reservation : reservationRecords) {
            if (reservation.getCustomerName().equalsIgnoreCase(cancelName)) {
                reservation.getBookedRoom().setBookingStatus(false);
                reservationRecords.remove(reservation);
                System.out.println("Reservation cancelled successfully.");
                return;
            }
        }
        System.out.println("No reservation found under this name.");
    }

    private static void simulatePayment(double amount) {
        System.out.println("Total amount to pay: Rs. " + amount);
        System.out.println("Processing payment...");
        System.out.println("Payment successful!");
    }
}
