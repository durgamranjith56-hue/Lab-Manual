import java.sql.*;
import java.util.Scanner;

public class StudentDatabaseManagement {

    // Database connection details
    static final String URL =
            "jdbc:mysql://localhost:3306/studentdb";
    static final String USER = "root";
    static final String PASSWORD = "root";

    // Create database connection
    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL, USER, PASSWORD);
    }

    // CREATE - Add Student
    public static void addStudent() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Student Marks: ");
        double marks = sc.nextDouble();

        String sql =
                "INSERT INTO students " +
                "(id, name, course, marks) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, course);
            ps.setDouble(4, marks);

            ps.executeUpdate();

            System.out.println(
                    "Student added successfully!");

        } catch (SQLException e) {
            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    // READ - Display Students
    public static void viewStudents() {

        String sql = "SELECT * FROM students";

        try (Connection con = getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println(
                    "\n===== STUDENT RECORDS =====");

            while (rs.next()) {

                System.out.println(
                        "ID     : " + rs.getInt("id"));

                System.out.println(
                        "Name   : " + rs.getString("name"));

                System.out.println(
                        "Course : " + rs.getString("course"));

                System.out.println(
                        "Marks  : " + rs.getDouble("marks"));

                System.out.println(
                        "--------------------------");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    // UPDATE - Update Student
    public static void updateStudent() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        System.out.print("Enter New Marks: ");
        double marks = sc.nextDouble();

        String sql =
                "UPDATE students " +
                "SET name = ?, course = ?, marks = ? " +
                "WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, marks);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Student updated successfully!");
            } else {
                System.out.println(
                        "Student ID not found!");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    // DELETE - Delete Student
    public static void deleteStudent() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        String sql =
                "DELETE FROM students WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Student deleted successfully!");
            } else {
                System.out.println(
                        "Student ID not found!");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error: " + e.getMessage());
        }
    }

    // Main method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println(
                    "\n==================================");

            System.out.println(
                    " STUDENT DATABASE MANAGEMENT SYSTEM");

            System.out.println(
                    "==================================");

            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print(
                    "Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    deleteStudent();
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
