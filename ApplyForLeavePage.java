
package leavemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ApplyForLeavePage extends JFrame {
    private String username;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ApplyForLeavePage() {
//        this.username = username;

        setTitle("Apply for Leave");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 250)); // Set background color

        JLabel startDateLabel = new JLabel("Enter start date (YYYY-MM-DD):");
        startDateLabel.setBounds(50, 20, 200, 25);
        JTextField startDateField = new JTextField(20);
        startDateField.setBounds(250, 20, 120, 25);

        JLabel endDateLabel = new JLabel("Enter end date (YYYY-MM-DD):");
        endDateLabel.setBounds(50, 50, 200, 25);
        JTextField endDateField = new JTextField(20);
        endDateField.setBounds(250, 50, 120, 25);

        JLabel reasonLabel = new JLabel("Enter reason for leave:");
        reasonLabel.setBounds(50, 80, 200, 25);
        JTextArea reasonTextArea = new JTextArea(4, 20);
        JScrollPane reasonScrollPane = new JScrollPane(reasonTextArea);
        reasonScrollPane.setBounds(250, 80, 120, 80);

        JButton applyButton = new JButton("Apply");
        applyButton.setBounds(100, 180, 100, 30);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 180, 100, 30);

        Font buttonFont = new Font("Tahoma", Font.PLAIN, 14); // Set common font for buttons
        applyButton.setFont(buttonFont);
        cancelButton.setFont(buttonFont);

        applyButton.setBackground(new Color(135, 206, 250)); // Set button background color
        cancelButton.setBackground(new Color(135, 206, 250)); // Set button background color

        applyButton.setForeground(Color.WHITE); // Set button text color
        cancelButton.setForeground(Color.WHITE); // Set button text color

        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                String reason = reasonTextArea.getText();

                if (!startDate.isEmpty() && !endDate.isEmpty() && !reason.isEmpty()) {
                    if (isValidDateFormat(startDate) && isValidDateFormat(endDate)) {

                        // Check if the user is logged in
                        if (GUI.leaveManagementSystem.isLoggedIn()) {
                            // Parse the dates from strings to LocalDate objects
                            LocalDate parsedStartDate = LocalDate.parse(startDate, dateFormatter);
                            LocalDate parsedEndDate = LocalDate.parse(endDate, dateFormatter);

                            // Show confirmation message box
                            int confirmation = JOptionPane.showConfirmDialog(ApplyForLeavePage.this,
                                    "Are you sure you want to apply for leave with the following details?\n"
                                            + "Start Date: " + startDate + "\n"
                                            + "End Date: " + endDate + "\n"
                                            + "Reason: " + reason,
                                    "Confirm Leave Application",
                                    JOptionPane.YES_NO_OPTION);

                            if (confirmation == JOptionPane.YES_OPTION) {
                                // Apply for leave using the LeaveManagementSystem instance
                                GUI.leaveManagementSystem.applyLeave(parsedStartDate, parsedEndDate, reason);
                                JOptionPane.showMessageDialog(ApplyForLeavePage.this,
                                        "Your application for leave has been accepted successfully.");
                                GUI.leaveManagementSystem.writeLeavesToFile("leaves_data.txt");
                                dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(ApplyForLeavePage.this,
                                    "Please log in before applying for leave.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(ApplyForLeavePage.this,
                                "Invalid date format. Please use the format YYYY-MM-DD.");
                    }
                } else {
                    JOptionPane.showMessageDialog(ApplyForLeavePage.this,
                            "Please fill in all the fields.");
                }
            }
        });



        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        panel.add(startDateLabel);
        panel.add(startDateField);
        panel.add(endDateLabel);
        panel.add(endDateField);
        panel.add(reasonLabel);
        panel.add(reasonScrollPane);
        panel.add(applyButton);
        panel.add(cancelButton);

        add(panel);
    }

    private boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

}
