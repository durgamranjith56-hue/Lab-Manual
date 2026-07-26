import java.io.*;
import java.util.Scanner;

public class StudentMarksFileManagement {

    static final String FILE_NAME = "students.txt";

    // Method to add student record
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

            System.out.print("Enter Student Marks: ");
            double marks = sc.nextDouble();

            // Write student details to file
            bw.write(id + "," + name + "," + marks);
            bw.newLine();

            bw.close();

            System.out.println("Student record saved successfully!");

        } catch (IOException e) {
            System.out.println("Error while writing file: " + e.getMessage());
        }
    }

    // Method to retrieve and display student records
    public static void displayStudents() {

        try {
            File file = new File(FILE_NAME);

            if (!file.exists()) {
                System.out.println("No student records found!");
                return;
            }

            BufferedReader br = new BufferedReader(
                    new FileReader(FILE_NAME));

            String line;

            System.out.println("\n===== STUDENT RECORDS =====");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                System.out.println("Student ID   : " + data[0]);
                System.out.println("Student Name : " + data[1]);
                System.out.println("Marks        : " + data[2]);
                System.out.println("---------------------------");
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());
        }
    }

    // Method to update student marks
    public static void updateMarks() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID to update: ");
        int updateId = sc.nextInt();

        System.out.print("Enter New Marks: ");
        double newMarks = sc.nextDouble();

        File oldFile = new File(FILE_NAME);
        File newFile = new File("temp.txt");

        boolean found = false;

        try {

            BufferedReader br = new BufferedReader(
                    new FileReader(oldFile));

            BufferedWriter bw = new BufferedWriter(
                    new FileWriter(newFile));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);

                if (id == updateId) {

                    // Update marks
                    bw.write(data[0] + "," +
                             data[1] + "," +
                             newMarks);

                    found = true;

                } else {

                    // Keep existing record
                    bw.write(line);
                }

                bw.newLine();
            }

            br.close();
            bw.close();

            // Replace old file with updated file
            if (oldFile.delete()) {
                newFile.renameTo(oldFile);
            }

            if (found) {
                System.out.println("Marks updated successfully!");
            } else {
                System.out.println("Student ID not found!");
            }

        } catch (IOException e) {
            System.out.println("Error while updating file: "
                    + e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println(" STUDENT MARKS FILE MANAGEMENT");
            System.out.println("=================================");
            System.out.println("1. Store Student Marks");
            System.out.println("2. Retrieve Student Marks");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Exit");
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
                    updateMarks();
                    break;

                case 4:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}
