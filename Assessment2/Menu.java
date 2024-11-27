import java.util.*;
import java.io.*;

//  Menu class encapsulates the menu-driven interface logic 
class Menu {
    private List<Student> students = new ArrayList<>();  // List for storing student data

    // Method to display the menu for  interacting with the user
    public void displaystudentMenu() {
        Scanner scanner = new Scanner(System.in);  // Scanner to read user input

        while (true) {
            System.out.println("\n---- Menu ----");
            System.out.println("1. Load Students from the  File");
            System.out.println("2. Display All Students Record");
            System.out.println("3. Filter Students Below Threshold value");
            System.out.println("4. Show Top and Bottom 5 Students from the File");
            System.out.println("5. Exit ");
            System.out.print(" Please Enter your choice: ");

            String select = scanner.nextLine();  // Reading user input

            switch (select) {
                case "1":
                    loadStudents(scanner);  // Load students from the  file
                    break;
                case "2":
                    displayStudents();  // Display all students on screen
                    break;
                case "3":
                    filterBelowThresholdValue(scanner);  // Filter students below threshold value
                    break;
                case "4":
                    showTopBottomStudents();  // Show top and bottom 5 students from file
                    break;
                case "5":
                    System.out.println("Exiting...");  // Exit the program
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to load students from  file
    public void loadStudents(Scanner scanner) {
        System.out.print("Enter the filename (e.g., our filename is students__grades.csv , so please write it below): ");
        String filename = scanner.nextLine();  // Get filename from user

        // Create a FileHandler instance with the file name
        FileHandler fileHandler = new FileHandler(filename);  

        // Load students from the file
        students = fileHandler.readFile();  

        if (students.isEmpty()) {
            System.out.println("No students loaded or file is empty , please upload again.");
        } else {
            System.out.println("Students loaded successfully.");
        }
    }

    // Method to display all students
    private void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students loaded.");
        } else {
            System.out.println("\nList of Students:");
            int serialNo = 1; // Start with 1 for serial numbers 
            for (Student student : students) {
                System.out.printf("%d. %s\n", serialNo, student);  // Print serial number and student details now
                serialNo++;
            }
        }
    }

    // Method to filter and display students below a certain threshold value
    private void filterBelowThresholdValue(Scanner scanner) {
        if (students.isEmpty()) {
            System.out.println("No students loaded.");
            return;
        }

        System.out.print("Enter the threshold: ");
        double threshold = scanner.nextDouble();  // Get the threshold value as input
        scanner.nextLine();  // Consume newline character

        List<Student> filteredStudents = MarkProcess.filterStudents(students, threshold);  // Get filtered students below threshold value

        if (filteredStudents.isEmpty()) {
            System.out.println("No students found below the threshold value.");
        } else {
            System.out.println("Students below the threshold value:");
            int serialNo = 1; // Start with 1 for serial numbers
            for (Student student : filteredStudents) {
                System.out.printf("%d. %s\n", serialNo, student);  // Print serial number and student details 
                serialNo++;
            }
        }
    }

    // Method to display the top and bottom 5 students based on total marks
    private void showTopBottomStudents() {
        if (students.isEmpty()) {
            System.out.println("No students loaded.");
            return;
        }

        List<Student> sortedStudents = MarkProcess.sortStudents(new ArrayList<>(students));  // Sort the students by total marks

        // Display top 5 students with the highest total marks from file
        System.out.println("\nTop 5 Students:");
        int serialNo = 1; // Start with 1 for serial numbers
        for (int j = sortedStudents.size() - 1; j >= Math.max(sortedStudents.size() - 5, 0); j--) {
            System.out.printf("%d. %s\n", serialNo, sortedStudents.get(j));  // Print top students
            serialNo++;
        }

        // Display bottom 5 students with the lowest total marks
        System.out.println("\nBottom 5 Students:");
        serialNo = 1; // Reset serial number for bottom list
        for (int j = 0; j < Math.min(5, sortedStudents.size()); j++) {
            System.out.printf("%d. %s\n", serialNo, sortedStudents.get(j));  // Print bottom students
            serialNo++;
        }
    }
}
