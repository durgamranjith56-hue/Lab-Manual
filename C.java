import java.util.ArrayList;
import java.util.List;

// ==========================================
// 1. GENERIC CLASS
// ==========================================
class DataContainer<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public T get(int index) {
        if (index < 0 || index >= items.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return items.get(index);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }
}

// ==========================================
// 2. BOUNDED TYPE PARAMETERS CLASS
// ==========================================
// Restricts T to subclasses of java.lang.Number
class NumberContainer<T extends Number> {
    private final List<T> numbers = new ArrayList<>();

    public void add(T number) {
        numbers.add(number);
    }

    // Calculates the sum using Number's doubleValue() method
    public double calculateSum() {
        double sum = 0.0;
        for (T num : numbers) {
            sum += num.doubleValue();
        }
        return sum;
    }

    public double calculateAverage() {
        if (numbers.isEmpty()) return 0.0;
        return calculateSum() / numbers.size();
    }
}

// ==========================================
// 3. GENERIC UTILITIES & MAIN CLASS
// ==========================================
public class DataContainerDemo {

    // GENERIC METHOD 1: Prints elements of any container
    public static <E> void printContainer(String label, DataContainer<E> container) {
        System.out.print(label + ": [ ");
        for (E element : container.getAll()) {
            System.out.print(element + " ");
        }
        System.out.println("]");
    }

    // GENERIC METHOD 2 WITH BOUNDED TYPE: Finds maximum element in a Comparable list
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("         GENERIC DATA CONTAINER DEMO            ");
        System.out.println("==================================================\n");

        // --- Demo 1: Generic Class with Strings ---
        DataContainer<String> stringContainer = new DataContainer<>();
        stringContainer.add("Java");
        stringContainer.add("Generics");
        stringContainer.add("Type Safety");
        printContainer("String Container", stringContainer);

        // --- Demo 2: Generic Class with Custom Objects ---
        DataContainer<Double> doubleContainer = new DataContainer<>();
        doubleContainer.add(10.5);
        doubleContainer.add(20.75);
        doubleContainer.add(5.25);
        printContainer("Double Container", doubleContainer);

        // --- Demo 3: Bounded Type Class (Numbers Only) ---
        System.out.println("\n--- Bounded Type Parameter Container (Number Subclasses) ---");
        NumberContainer<Integer> intNumberContainer = new NumberContainer<>();
        intNumberContainer.add(10);
        intNumberContainer.add(20);
        intNumberContainer.add(30);

        System.out.println("Integer Sum    : " + intNumberContainer.calculateSum());
        System.out.println("Integer Average: " + intNumberContainer.calculateAverage());

        NumberContainer<Double> doubleNumberContainer = new NumberContainer<>();
        doubleNumberContainer.add(1.5);
        doubleNumberContainer.add(2.5);
        doubleNumberContainer.add(3.5);

        System.out.println("Double Sum     : " + doubleNumberContainer.calculateSum());
        System.out.println("Double Average : " + doubleNumberContainer.calculateAverage());

        // --- Demo 4: Bounded Generic Method ---
        System.out.println("\n--- Bounded Generic Method (findMax) ---");
        List<Integer> intList = List.of(45, 12, 89, 33, 67);
        List<String> stringList = List.of("Apple", "Orange", "Banana", "Pear");

        System.out.println("Max Integer: " + findMax(intList));
        System.out.println("Max String : " + findMax(stringList));
    }
}
