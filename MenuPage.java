package leavemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage extends JFrame {
    //    public static LeaveManagementSystem LMS = new LeaveManagementSystem(100,100);
    public MenuPage() {
        setTitle("Menu Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(211, 211, 241)); // Set background color

        JLabel headingLabel = new JLabel("Welcome to Leave Management System");
        headingLabel.setBounds(40, 10, 320, 30);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JButton addEmployeeBtn = new JButton("Add Employee");
        addEmployeeBtn.setBounds(100, 50, 200, 30);
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 90, 200, 30);
        JButton applyForLeaveBtn = new JButton("Apply for Leave");
        applyForLeaveBtn.setBounds(100, 130, 200, 30);
        JButton approveRejectLeaveBtn = new JButton("Approve/Reject Leave"); // Combined button
        approveRejectLeaveBtn.setBounds(100, 170, 200, 30);
        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(100, 210, 200, 30);

        Font buttonFont = new Font("Tahoma", Font.PLAIN, 14); // Set common font for buttons
        addEmployeeBtn.setFont(buttonFont);
        loginBtn.setFont(buttonFont);
        applyForLeaveBtn.setFont(buttonFont);
        approveRejectLeaveBtn.setFont(buttonFont);
        exitBtn.setFont(buttonFont);

        addEmployeeBtn.setBackground(new Color(135, 206, 250)); // Set button background color
        loginBtn.setBackground(new Color(135, 206, 250)); // Set button background color
        applyForLeaveBtn.setBackground(new Color(135, 206, 250)); // Set button background color
        approveRejectLeaveBtn.setBackground(new Color(135, 206, 250)); // Set button background color
        exitBtn.setBackground(new Color(135, 206, 250)); // Set button background color

        addEmployeeBtn.setForeground(Color.WHITE); // Set button text color
        loginBtn.setForeground(Color.WHITE); // Set button text color
        applyForLeaveBtn.setForeground(Color.WHITE); // Set button text color
        approveRejectLeaveBtn.setForeground(Color.WHITE); // Set button text color
        exitBtn.setForeground(Color.WHITE); // Set button text color

        // Set button size and center align
        Dimension buttonSize = new Dimension(200, 30);
        addEmployeeBtn.setPreferredSize(buttonSize);
        loginBtn.setPreferredSize(buttonSize);
        applyForLeaveBtn.setPreferredSize(buttonSize);
        approveRejectLeaveBtn.setPreferredSize(buttonSize);
        exitBtn.setPreferredSize(buttonSize);

        addEmployeeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Add Employee page when the "Add Employee" button is clicked
                new AddEmployeePage().setVisible(true);
            }
        });

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the LoginPage when the "Login" button is clicked
                new LoginPage().setVisible(true);
            }
        });

        applyForLeaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the Apply for Leave page when the "Apply for Leave" button is clicked
                new ApplyForLeavePage().setVisible(true);
            }
        });

        approveRejectLeaveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the ApproveOrRejectPage when the "Approve/Reject Leave" button is clicked
                new AdminPage().setVisible(true);
            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your logic for the Exit button here
                System.exit(0);
            }
        });

        panel.add(headingLabel);
        panel.add(addEmployeeBtn);
        panel.add(loginBtn);
        panel.add(applyForLeaveBtn);
        panel.add(approveRejectLeaveBtn); // Add the combined button to the panel
        panel.add(exitBtn);

        add(panel);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new MenuPage().setVisible(true);
//            }
//        });
//    }
}
