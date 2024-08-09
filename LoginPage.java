
package leavemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

    public LoginPage() {

        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 250)); // Set background color

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 20, 100, 25);
        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(190, 20, 150, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 50, 100, 25);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(190, 50, 150, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 100, 100, 30);
        loginButton.setBackground(new Color(135, 206, 250)); // Set button background color
        loginButton.setForeground(Color.WHITE); // Set button text color
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Set button font

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginPage.this,
                            "Invalid input. Please enter both username and password.");
                } else {
                    // Get the backend LeaveManagementSystem instance using the static method
//                    LeaveManagementSystem leaveManagementSystem = LeaveManagementSystem.getInstance();

                    // Perform login authentication using the backend LeaveManagementSystem
                    GUI.leaveManagementSystem.login(username, password);

                    // Check if login is successful (loggedIn flag in the backend is set)
                    if (GUI.leaveManagementSystem.isLoggedIn()) {
                        JOptionPane.showMessageDialog(LoginPage.this, "Login successful! Welcome "+username);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginPage.this, "Invalid credentials. Login failed.");
                    }
                }
            }
        });

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                // Show the LoginPage
//                new LoginPage().setVisible(true);
//            }
//        });
//    }
}

