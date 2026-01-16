import java.util.ArrayList;
import java.util.Scanner;

class StudentRecord {
    private String learnerName;
    private int learnerMarks;

    public StudentRecord(String learnerName, int learnerMarks) {
        this.learnerName = learnerName;
        this.learnerMarks = learnerMarks;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public int getLearnerMarks() {
        return learnerMarks;
    }
}

public class StudentGradeTracker {

    private static Scanner inputDevice = new Scanner(System.in);
    private static ArrayList<StudentRecord> recordList = new ArrayList<>();

    public static void main(String[] args) {

        int menuOption;

        do {
            displayMenu();
            menuOption = inputDevice.nextInt();
            inputDevice.nextLine(); // clear buffer

            switch (menuOption) {
                case 1:
                    insertStudentData();
                    break;

                case 2:
                    showSummaryReport();
                    break;

                case 3:
                    System.out.println("Exiting Student Grade Tracker. Thank you!");
                    break;

                default:
                    System.out.println("Invalid option selected. Try again.");
            }
        } while (menuOption != 3);
    }

    private static void displayMenu() {
        System.out.println("\n====== Student Grade Tracker ======");
        System.out.println("1. Add Student Details");
        System.out.println("2. View Summary Report");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void insertStudentData() {
        System.out.print("Enter student name: ");
        String studentNameInput = inputDevice.nextLine();

        System.out.print("Enter marks (0 - 100): ");
        int marksInput = inputDevice.nextInt();

        if (marksInput < 0 || marksInput > 100) {
            System.out.println("Invalid marks! Please enter between 0 and 100.");
            return;
        }

        StudentRecord newRecord = new StudentRecord(studentNameInput, marksInput);
        recordList.add(newRecord);

        System.out.println("Student record added successfully.");
    }

    private static void showSummaryReport() {

        if (recordList.isEmpty()) {
            System.out.println("No student data available.");
            return;
        }

        int totalMarks = 0;
        int maximumMarks = recordList.get(0).getLearnerMarks();
        int minimumMarks = recordList.get(0).getLearnerMarks();

        System.out.println("\n------ Student Summary Report ------");

        for (StudentRecord record : recordList) {
            System.out.println("Name: " + record.getLearnerName()
                    + " | Marks: " + record.getLearnerMarks());

            totalMarks += record.getLearnerMarks();

            if (record.getLearnerMarks() > maximumMarks) {
                maximumMarks = record.getLearnerMarks();
            }

            if (record.getLearnerMarks() < minimumMarks) {
                minimumMarks = record.getLearnerMarks();
            }
        }

        double averageMarks = (double) totalMarks / recordList.size();

        System.out.println("----------------------------------");
        System.out.println("Average Marks : " + averageMarks);
        System.out.println("Highest Marks : " + maximumMarks);
        System.out.println("Lowest Marks  : " + minimumMarks);
    }
}
