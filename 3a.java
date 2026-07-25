package app;

// Interface
interface Shape {
    double calculateArea();
    void display();
}

// Circle class
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void display() {
        System.out.println("Shape: Circle");
        System.out.println("Radius: " + radius);
        System.out.println("Area: " + calculateArea());
    }
}

// Rectangle class
class Rectangle implements Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    @Override
    public void display() {
        System.out.println("Shape: Rectangle");
        System.out.println("Length: " + length);
        System.out.println("Width: " + width);
        System.out.println("Area: " + calculateArea());
    }
}

// Square class
class Square implements Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public void display() {
        System.out.println("Shape: Square");
        System.out.println("Side: " + side);
        System.out.println("Area: " + calculateArea());
    }
}

// Main class
public class Main {
    public static void main(String[] args) {

        // Interface reference demonstrating polymorphism
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(10, 5);
        Shape square = new Square(4);

        System.out.println("===== SHAPE AREA CALCULATOR =====");
        System.out.println();

        circle.display();
        System.out.println();

        rectangle.display();
        System.out.println();

        square.display();
    }
}
