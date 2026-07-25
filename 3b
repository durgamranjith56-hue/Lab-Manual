class PiCalculator {

    // Public member - accessible from anywhere
    public double piPublic;

    // Private member - accessible only within this class
    private double piPrivate;

    // Protected member - accessible within the same package
    // and by subclasses
    protected double piProtected;

    // Constructor
    public PiCalculator() {
        piPublic = Math.PI;
        piPrivate = Math.PI;
        piProtected = Math.PI;
    }

    // Public method to access private member
    public double getPrivatePi() {
        return piPrivate;
    }

    // Public method to display all values
    public void displayPiValues() {
        System.out.println("Public Pi Value    : " + piPublic);
        System.out.println("Private Pi Value   : " + piPrivate);
        System.out.println("Protected Pi Value : " + piProtected);
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        // Creating object
        PiCalculator obj = new PiCalculator();

        // Accessing public member
        System.out.println("Accessing Public Member:");
        System.out.println("Pi = " + obj.piPublic);

        System.out.println();

        // Accessing private member through public method
        System.out.println("Accessing Private Member through Method:");
        System.out.println("Pi = " + obj.getPrivatePi());

        System.out.println();

        // Accessing protected member
        System.out.println("Accessing Protected Member:");
        System.out.println("Pi = " + obj.piProtected);

        System.out.println();

        // Display all values
        obj.displayPiValues();
    }
}
