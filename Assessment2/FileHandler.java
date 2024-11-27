import java.io.*;
import java.util.*;

// This class handles reading student data from the CSV file that we will upload from our device
class FileHandler {
    private String filename;  // Name of the file to load in program

    // Constructor to initialize the filename 
    public FileHandler(String filename) {
        this.filename = filename;  // Set the filename here 
    }

    // Method to read the file and return a list of Student objects 
    public List<Student> readFile() {
        List<Student> students = new ArrayList<>();
        String filePath = filename;  // File path is just the filename here

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the first line (unit name)
            br.readLine();
            // Skip the second line (headers)
            br.readLine();
            // Read the student data line by line
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;  // Skip empty lines
                }

                // Split the data by commas (CSV format)
                String[] data = line.split(",");
                String name = data[1].trim() + " " + data[0].trim();  // Full name
                String studentId = data[2].trim();  // Student ID
                double[] marks = new double[3];  // Array to store marks of students

                // Read and parse the marks for three assignments
                for (int j = 0; j < marks.length; j++) {
                    try {
                        marks[j] = data[j + 3].isEmpty() ? 0 : Double.parseDouble(data[j + 3].trim());
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        marks[j] = 0;  // Handle missing or invalid marks
                    }
                }

                // Create a new Student object and add it to the list
                students.add(new Student(name, studentId, marks));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());  // Handle file read error here
        }

        return students;  // Return the list of students
    }
}
