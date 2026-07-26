import java.util.*;
import java.util.stream.Collectors;

// ==========================================
// 1. EMPLOYEE MODEL CLASS
// ==========================================
class Employee {
    private final int id;
    private final String name;
    private final String department;
    private final double salary;
    private final int age;
    private final String gender;

    public Employee(int id, String name, String department, double salary, int age, String gender) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.gender = gender;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }
    public String getGender() { return gender; }

    @Override
    public String toString() {
        return String.format("Employee[ID=%d, Name='%s', Dept='%s', Salary=%.2f, Age=%d, Gender='%s']",
                id, name, department, salary, age, gender);
    }
}

// ==========================================
// 2. MAIN ANALYTICS PROGRAM
// ==========================================
public class EmployeeAnalytics {

    public static void main(String[] args) {
        // Sample Dataset
        List<Employee> employees = Arrays.asList(
            new Employee(101, "Alice", "IT", 85000, 29, "Female"),
            new Employee(102, "Bob", "HR", 55000, 34, "Male"),
            new Employee(103, "Charlie", "IT", 95000, 41, "Male"),
            new Employee(104, "Diana", "Finance", 72000, 31, "Female"),
            new Employee(105, "Ethan", "IT", 60000, 24, "Male"),
            new Employee(106, "Fiona", "HR", 62000, 28, "Female"),
            new Employee(107, "George", "Finance", 90000, 45, "Male"),
            new Employee(108, "Hannah", "Sales", 50000, 26, "Female")
        );

        System.out.println("==================================================");
        System.out.println("          EMPLOYEE DATA ANALYTICS BOARD           ");
        System.out.println("==================================================\n");

        // --------------------------------------------------
        // 1. FILTERING: IT Employees earning > 70,000
        // --------------------------------------------------
        System.out.println("--- 1. IT Employees with Salary > $70,000 ---");
        List<Employee> highEarningIT = employees.stream()
                .filter(e -> e.getDepartment().equalsIgnoreCase("IT"))
                .filter(e -> e.getSalary() > 70000)
                .collect(Collectors.toList());
        highEarningIT.forEach(System::println);

        // --------------------------------------------------
        // 2. SORTING: Sort by Salary (Desc), then Name (Asc)
        // --------------------------------------------------
        System.out.println("\n--- 2. Employees Sorted by Salary (Desc) then Name (Asc) ---");
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()
                        .thenComparing(Employee::getName))
                .collect(Collectors.toList());
        sortedEmployees.forEach(System::println);

        // --------------------------------------------------
        // 3. GROUPING: Group Employees by Department
        // --------------------------------------------------
        System.out.println("\n--- 3. Employees Grouped by Department ---");
        Map<String, List<Employee>> employeesByDept = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        
        employeesByDept.forEach((dept, empList) -> {
            System.out.println("Department: " + dept);
            empList.forEach(e -> System.out.println("   " + e));
        });

        // --------------------------------------------------
        // 4. SUMMARIZING: Average Salary by Department
        // --------------------------------------------------
        System.out.println("\n--- 4. Average Salary by Department ---");
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        avgSalaryByDept.forEach((dept, avgSalary) -> 
            System.out.printf("Department: %-10s | Avg Salary: $%.2f%n", dept, avgSalary)
        );

        // --------------------------------------------------
        // 5. FIND MAXIMUM: Highest Paid Employee
        // --------------------------------------------------
        System.out.println("\n--- 5. Highest Paid Employee ---");
        employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .ifPresent(e -> System.out.println("Highest Paid: " + e));

        // --------------------------------------------------
        // 6. ADVANCED SUMMARY: Overall Salary Statistics
        // --------------------------------------------------
        System.out.println("\n--- 6. Overall Company Salary Statistics ---");
        DoubleSummaryStatistics stats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.printf("Total Employees : %d%n", stats.getCount());
        System.out.printf("Total Payroll   : $%.2f%n", stats.getSum());
        System.out.printf("Min Salary      : $%.2f%n", stats.getMin());
        System.out.printf("Max Salary      : $%.2f%n", stats.getMax());
