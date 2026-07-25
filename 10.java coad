import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Student class
class Student {

    private int id;
    private String name;
    private String course;
    private double marks;

    // Constructor
    public Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }

    public double getMarks() {
        return marks;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Display student details
    public void display() {
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Course       : " + course);
        System.out.println("Marks        : " + marks);
        System.out.println("---------------------------");
    }
}

// Main class
public class StudentRecordManagement {

    // ArrayList to store student records
    static ArrayList<Student> studentList =
            new ArrayList<>();

    // HashMap to search students quickly using ID
    static HashMap<Integer, Student> studentMap =
            new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    // ADD operation
    public static void addStudent() {

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        // Check if student already exists
        if (studentMap.containsKey(id)) {
            System.out.println(
                    "Student ID already exists!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        System.out.print("Enter Marks: ");
        double marks = sc.nextDouble();

        Student student =
                new Student(id, name, course, marks);

        // Add to ArrayList
        studentList.add(student);

        // Add to HashMap
        studentMap.put(id, student);

        System.out.println(
                "Student added successfully!");
    }

    // UPDATE operation
    public static void updateStudent() {

        System.out.print(
                "Enter Student ID to update: ");

        int id = sc.nextInt();

        // Search using HashMap
        Student student = studentMap.get(id);

        if (student == null) {
            System.out.println(
                    "Student not found!");
            return;
        }

        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        System.out.print("Enter New Marks: ");
        double marks = sc.nextDouble();

        // Update student details
        student.setName(name);
        student.setCourse(course);
        student.setMarks(marks);

        System.out.println(
                "Student updated successfully!");
    }

    // SEARCH operation
    public static void searchStudent() {

        System.out.print(
                "Enter Student ID to search: ");

        int id = sc.nextInt();

        // Search using HashMap
        Student student = studentMap.get(id);

        if (student != null) {

            System.out.println(
                    "\nStudent Found:");

            student.display();

        } else {

            System.out.println(
                    "Student not found!");
        }
    }

    // DELETE operation
    public static void deleteStudent() {

        System.out.print(
                "Enter Student ID to delete: ");

        int id = sc.nextInt();

        // Remove from HashMap
        Student student = studentMap.remove(id);

        if (student != null) {

            // Remove from ArrayList
            studentList.remove(student);

            System.out.println(
                    "Student deleted successfully!");

        } else {

            System.out.println(
                    "Student not found!");
        }
    }

    // DISPLAY operation
    public static void displayStudents() {

        if (studentList.isEmpty()) {

            System.out.println(
                    "No student records available!");

            return;
        }

        System.out.println(
                "\n===== ALL STUDENT RECORDS =====");

        // Display using ArrayList
        for (Student student : studentList) {
            student.display();
        }
    }

    // Main method
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println(
                    "\n================================");

            System.out.println(
                    " STUDENT RECORD MANAGEMENT SYSTEM");

            System.out.println(
                    "================================");

            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Display All Students");
            System.out.println("6. Exit");

            System.out.print(
                    "Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    updateStudent();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 5:
                    displayStudents();
                    break;

                case 6:
                    System.out.println(
                            "Program terminated.");
                    break;

                default:
                    System.out.println(
                            "Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}
