import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends Frame implements ActionListener {

    // Declare components
    TextField num1, num2, result;
    Button add, subtract, multiply, divide, clear;

    // Constructor
    SimpleCalculator() {

        // Create labels
        Label label1 = new Label("Enter First Number:");
        Label label2 = new Label("Enter Second Number:");
        Label label3 = new Label("Result:");

        // Create text fields
        num1 = new TextField();
        num2 = new TextField();
        result = new TextField();

        // Result field should not be editable
        result.setEditable(false);

        // Create buttons
        add = new Button("Add");
        subtract = new Button("Subtract");
        multiply = new Button("Multiply");
        divide = new Button("Divide");
        clear = new Button("Clear");

        // Set layout
        setLayout(new GridLayout(6, 2, 10, 10));

        // Add components to frame
        add(label1);
        add(num1);

        add(label2);
        add(num2);

        add(label3);
        add(result);

        add(add);
        add(subtract);

        add(multiply);
        add(divide);

        add(clear);

        // Register event listeners
        add.addActionListener(this);
        subtract.addActionListener(this);
        multiply.addActionListener(this);
        divide.addActionListener(this);
        clear.addActionListener(this);

        // Frame settings
        setTitle("Simple Calculator");
        setSize(400, 300);
        setVisible(true);

        // Close window event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    // Event handling method
    public void actionPerformed(ActionEvent e) {

        // Clear button
        if (e.getSource() == clear) {
            num1.setText("");
            num2.setText("");
            result.setText("");
            return;
        }

        try {
            // Get numbers from text fields
            double a = Double.parseDouble(num1.getText());
            double b = Double.parseDouble(num2.getText());
            double answer = 0;

            // Perform operations
            if (e.getSource() == add) {
                answer = a + b;
            }
            else if (e.getSource() == subtract) {
                answer = a - b;
            }
            else if (e.getSource() == multiply) {
                answer = a * b;
            }
            else if (e.getSource() == divide) {

                if (b == 0) {
                    result.setText("Cannot divide by zero");
                    return;
                }

                answer = a / b;
            }

            // Display result
            result.setText(String.valueOf(answer));

        } catch (NumberFormatException ex) {
            result.setText("Enter valid numbers");
        }
    }

    // Main method
    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
