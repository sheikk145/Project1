//
//package leavemanagementsystem;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class AddEmployeePage extends JFrame {
//    public AddEmployeePage() {
//        setTitle("Add Employee");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setSize(400, 200);
//        setLocationRelativeTo(null);
//
//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//        panel.setBackground(new Color(230, 230, 250)); // Set background color
//
//        JLabel usernameLabel = new JLabel("Username:");
//        usernameLabel.setBounds(80, 30, 80, 25);
//        JTextField usernameField = new JTextField(20);
//        usernameField.setBounds(170, 30, 150, 25);
//
//        JLabel passwordLabel = new JLabel("Password:");
//        passwordLabel.setBounds(80, 65, 80, 25);
//        JPasswordField passwordField = new JPasswordField(20);
//        passwordField.setBounds(170, 65, 150, 25);
//
//        JButton addButton = new JButton("Add");
//        addButton.setBounds(100, 120, 100, 30);
//        JButton cancelButton = new JButton("Cancel");
//        cancelButton.setBounds(220, 120, 100, 30);
//
//        Font buttonFont = new Font("Tahoma", Font.PLAIN, 14); // Set common font for buttons
//        addButton.setFont(buttonFont);
//        cancelButton.setFont(buttonFont);
//
//        addButton.setBackground(new Color(135, 206, 250)); // Set button background color
//        cancelButton.setBackground(new Color(135, 206, 250)); // Set button background color
//
//        addButton.setForeground(Color.WHITE); // Set button text color
//        cancelButton.setForeground(Color.WHITE); // Set button text color
//
//        // Set button size and center align
//        Dimension buttonSize = new Dimension(100, 30);
//        addButton.setPreferredSize(buttonSize);
//        cancelButton.setPreferredSize(buttonSize);
//
//        addButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Get the entered username and password
//                String username = usernameField.getText();
//                char[] passwordChars = passwordField.getPassword();
//                String password = new String(passwordChars);
//
//                // Implement your logic to add the employee with the given username and password here
//                // For example, you can store them in a database or display them in a message box
//                JOptionPane.showMessageDialog(AddEmployeePage.this,
//                        "Employee added:\nUsername: " + username + "\nPassword: " + password);
//
//                // Clear the input fields after adding the employee
//                usernameField.setText("");
//                passwordField.setText("");
//            }
//        });
//
//        cancelButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Close the Add Employee window when the Cancel button is clicked
//                dispose();
//            }
//        });
//
//        panel.add(usernameLabel);
//        panel.add(usernameField);
//        panel.add(passwordLabel);
//        panel.add(passwordField);
//        panel.add(addButton);
//        panel.add(cancelButton);
//
//        add(panel);
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new AddEmployeePage().setVisible(true);
//            }
//        });
//    }
//}
//
//********************************************************************************************************
package leavemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeePage extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton addButton;
    private JButton resetButton;

    public AddEmployeePage() {
        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 250)); // Set background color

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 30, 80, 25);
        usernameField = new JTextField(20);
        usernameField.setBounds(170, 30, 150, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 65, 80, 25);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(170, 65, 150, 25);

        addButton = new JButton("Add");
        addButton.setBounds(100, 120, 100, 30);
        resetButton = new JButton("Reset");
        resetButton.setBounds(220, 120, 100, 30);

        Font buttonFont = new Font("Tahoma", Font.PLAIN, 14); // Set common font for buttons
        addButton.setFont(buttonFont);
        resetButton.setFont(buttonFont);

        addButton.setBackground(new Color(135, 206, 250)); // Set button background color
        resetButton.setBackground(new Color(135, 206, 250)); // Set button background color

        addButton.setForeground(Color.WHITE); // Set button text color
        resetButton.setForeground(Color.WHITE); // Set button text color

        // Set button size and center align
        Dimension buttonSize = new Dimension(100, 30);
        addButton.setPreferredSize(buttonSize);
        resetButton.setPreferredSize(buttonSize);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered username and password
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);

                // Check if any input fields are empty
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(AddEmployeePage.this,
                            "Please fill in both Username and Password fields.",
                            "Empty Fields", JOptionPane.WARNING_MESSAGE);
                } else {
                    // Ask for confirmation
                    int confirmResult = JOptionPane.showConfirmDialog(AddEmployeePage.this,
                            "Are you sure you want to add this employee?\nUsername: " + username + "\nPassword: " + password,
                            "Confirm Employee Addition", JOptionPane.YES_NO_OPTION);

                    // If user clicks "Yes," show success message
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        // Add the employee using the backend LeaveManagementSystem
                        GUI.leaveManagementSystem.addEmployee(username, password);

                        // Clear the input fields after adding the employee
                        usernameField.setText("");
                        passwordField.setText("");

                        JOptionPane.showMessageDialog(AddEmployeePage.this,
                                "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the input fields when the Reset button is clicked
                usernameField.setText("");
                passwordField.setText("");
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(addButton);
        panel.add(resetButton);

        add(panel);
    }
}

