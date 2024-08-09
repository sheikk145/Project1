package leavemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    public AdminPage() {
        setTitle("Admin Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 250)); // Set background color

        JLabel headingLabel = new JLabel("Admin Page");
        headingLabel.setBounds(150, 5, 100, 30);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 18));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(80, 50, 100, 25);
        JTextField usernameField = new JTextField(20);
        usernameField.setBounds(190, 50, 150, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(80, 80, 100, 25);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setBounds(190, 80, 150, 25);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 130, 100, 30);
        loginButton.setBackground(new Color(135, 206, 250)); // Set button background color
        loginButton.setForeground(Color.WHITE); // Set button text color
        loginButton.setFont(new Font("Tahoma", Font.PLAIN, 14)); // Set button font

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("admin")) {
                    JOptionPane.showMessageDialog(AdminPage.this, "Login successful!");
                    new ApproveOrRejectPage().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(AdminPage.this, "Invalid username or password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(headingLabel);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminPage().setVisible(true);
            }
        });
    }
}

