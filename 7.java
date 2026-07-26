import java.io.*;
import java.util.*;

public class StudentMarksFileManagement {

    static final String FILE_NAME = "students.txt";

    // Add student marks
    public static void addStudent() {

        Scanner sc = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            BufferedWriter bw = new BufferedWriter(fw);

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();

            sc.nextLine();

            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = sc.nextDouble();

            // Store data in file
            bw.write(id + "," + name + "," + marks);
            bw.newLine();

            bw.close();

            System.out.println("Student record added successfully!");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Display all student records
    public static void displayStudents() {

        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                System.out.println("No student records found.");
                return;
            }

            BufferedReader br = new BufferedReader(
                    new FileReader(FILE_NAME));

            String line;

            System.out.println("\n--- Student Records ---");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                System.out.println(
                        "ID: " + data[0] +
                        " | Name: " + data[1] +
                        " | Marks: " + data[2]);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Search student by ID
    public static void searchStudent() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID to search: ");
        int searchId = sc.nextInt();

        boolean found = false;

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(FILE_NAME));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);

                if (id == searchId) {

                    System.out.println("\nStudent Found!");
                    System.out.println("ID: " + data[0]);
                    System.out.println("Name: " + data[1]);
                    System.out.println("Marks: " + data[2]);

                    found = true;
                    break;
                }
            }

            br.close();

            if (!found) {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Update student marks
    public static void updateMarks() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int updateId = sc.nextInt();

        System.out.print("Enter New Marks: ");
        double newMarks = sc.nextDouble();

        File inputFile = new File(FILE_NAME);
        File tempFile = new File("temp.txt");

        boolean found = false;

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(inputFile));

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(tempFile));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);

                if (id == updateId) {

                    bw.write(data[0] + "," +
                             data[1] + "," +
                             newMarks);

                    found = true;

                } else {

                    bw.write(line);
                }

                bw.newLine();
            }

            br.close();
            bw.close();

            // Delete old file
            inputFile.delete();

            // Rename temporary file
            tempFile.renameTo(inputFile);

            if (found) {
                System.out.println(
                        "Student marks updated successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== STUDENT MARKS FILE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student Marks");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    displayStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateMarks();
                    break;

                case 5:
                    System.out.println(
                            "Program terminated.");
                    break;

                default:
                    System.out.println(
                            "Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
