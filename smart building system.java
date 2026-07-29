import java.awt.*;
import java.awt.event.*;

public class SmartCityAWT extends Frame implements ActionListener {

    Label title, lblBuilding;
    TextField txtBuilding;
    TextArea display;

    Button btnAdd, btnView, btnExit;

    String[] buildings = new String[20];
    int count = 0;

    SmartCityAWT() {

        setTitle("Smart City Building System");
        setLayout(null);

        title = new Label("SMART CITY BUILDING SYSTEM");
        title.setBounds(120, 40, 250, 30);

        lblBuilding = new Label("Building Name:");
        lblBuilding.setBounds(50, 90, 100, 30);

        txtBuilding = new TextField();
        txtBuilding.setBounds(170, 90, 150, 30);

        btnAdd = new Button("Add Building");
        btnAdd.setBounds(50, 140, 100, 35);

        btnView = new Button("View Buildings");
        btnView.setBounds(170, 140, 100, 35);

        btnExit = new Button("Exit");
        btnExit.setBounds(290, 140, 80, 35);

        display = new TextArea();
        display.setBounds(50, 200, 320, 180);

        add(title);
        add(lblBuilding);
        add(txtBuilding);
        add(btnAdd);
        add(btnView);
        add(btnExit);
        add(display);

        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnExit.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setSize(450, 450);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {

            if (count < buildings.length) {
                buildings[count] = txtBuilding.getText();
                count++;

                display.append("Building Added Successfully\n");
                txtBuilding.setText("");
            }
            else {
                display.append("No Building Slots Available\n");
            }
        }

        if (e.getSource() == btnView) {

            display.append("\n------ Building List ------\n");

            for (int i = 0; i < count; i++) {
                display.append((i + 1) + ". " + buildings[i] + "\n");
            }
        }

        if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        new SmartCityAWT();
    }
}
