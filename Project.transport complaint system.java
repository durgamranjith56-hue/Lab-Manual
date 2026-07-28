import java.awt.*;
import java.awt.event.*;

public class PublicTransportComplaint extends Frame implements ActionListener {

    Label title, nameLabel, busLabel, routeLabel, categoryLabel;
    Label complaintLabel, statusLabel, idLabel, resultLabel;

    TextField nameField, busField, routeField;
    TextArea complaintArea;

    Choice categoryChoice, statusChoice;

    Button submitButton, updateButton, clearButton;

    int complaintNumber = 1001;

    public PublicTransportComplaint() {

        setTitle("Public Transport Complaint Management System");
        setSize(600, 600);
        setLayout(null);
        setBackground(Color.LIGHT_GRAY);

        title = new Label("PUBLIC TRANSPORT COMPLAINT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(100, 50, 400, 30);
        add(title);

        nameLabel = new Label("Passenger Name:");
        nameLabel.setBounds(50, 110, 120, 25);
        add(nameLabel);

        nameField = new TextField();
        nameField.setBounds(190, 110, 300, 25);
        add(nameField);

        busLabel = new Label("Bus Number:");
        busLabel.setBounds(50, 150, 120, 25);
        add(busLabel);

        busField = new TextField();
        busField.setBounds(190, 150, 300, 25);
        add(busField);

        routeLabel = new Label("Route:");
        routeLabel.setBounds(50, 190, 120, 25);
        add(routeLabel);

        routeField = new TextField();
        routeField.setBounds(190, 190, 300, 25);
        add(routeField);

        categoryLabel = new Label("Complaint Category:");
        categoryLabel.setBounds(50, 230, 120, 25);
        add(categoryLabel);

        categoryChoice = new Choice();
        categoryChoice.add("Overcrowding");
        categoryChoice.add("Bus Delay");
        categoryChoice.add("Poor Cleanliness");
        categoryChoice.add("Driver Behaviour");
        categoryChoice.add("Damaged Seat");
        categoryChoice.add("Other");

        categoryChoice.setBounds(190, 230, 300, 25);
        add(categoryChoice);

        complaintLabel = new Label("Complaint:");
        complaintLabel.setBounds(50, 270, 120, 25);
        add(complaintLabel);

        complaintArea = new TextArea();
        complaintArea.setBounds(190, 270, 300, 80);
        add(complaintArea);

        submitButton = new Button("Submit Complaint");
        submitButton.setBounds(100, 380, 140, 35);
        submitButton.addActionListener(this);
        add(submitButton);

        updateButton = new Button("Update Status");
        updateButton.setBounds(260, 380, 120, 35);
        updateButton.addActionListener(this);
        add(updateButton);

        clearButton = new Button("Clear");
        clearButton.setBounds(400, 380, 80, 35);
        clearButton.addActionListener(this);
        add(clearButton);

        idLabel = new Label("Complaint ID: Not Generated");
        idLabel.setBounds(50, 440, 250, 25);
        add(idLabel);

        statusLabel = new Label("Status:");
        statusLabel.setBounds(50, 475, 60, 25);
        add(statusLabel);

        statusChoice = new Choice();
        statusChoice.add("Pending");
        statusChoice.add("In Progress");
        statusChoice.add("Completed");
        statusChoice.setBounds(120, 475, 200, 25);
        add(statusChoice);

        resultLabel = new Label("");
        resultLabel.setBounds(50, 520, 500, 30);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(resultLabel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {

            if (nameField.getText().isEmpty() ||
                busField.getText().isEmpty() ||
                routeField.getText().isEmpty() ||
                complaintArea.getText().isEmpty()) {

                resultLabel.setText("Please fill all required fields!");

            } else {

                String id = "PT2026" + complaintNumber++;

                idLabel.setText("Complaint ID: " + id);

                statusChoice.select("Pending");

                resultLabel.setText(
                    "Complaint Submitted Successfully - Status: Pending"
                );
            }
        }

        if (e.getSource() == updateButton) {

            String status = statusChoice.getSelectedItem();

            if (status.equals("Completed")) {

                resultLabel.setText(
                    "Work Done Successfully - Complaint Completed!"
                );

            } else if (status.equals("In Progress")) {

                resultLabel.setText(
                    "Complaint is being processed - Status: In Progress"
                );

            } else {

                resultLabel.setText(
                    "Complaint is waiting for action - Status: Pending"
                );
            }
        }

        if (e.getSource() == clearButton) {

            nameField.setText("");
            busField.setText("");
            routeField.setText("");
            complaintArea.setText("");

            categoryChoice.select(0);
            statusChoice.select("Pending");

            idLabel.setText("Complaint ID: Not Generated");
            resultLabel.setText("");
        }
    }

    public static void main(String[] args) {
        new PublicTransportComplaint();
    }
}
