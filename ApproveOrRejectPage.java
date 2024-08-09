
package leavemanagementsystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ApproveOrRejectPage extends JFrame {
    public ApproveOrRejectPage() {
        setTitle("Approve or Reject Leave");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(230, 230, 250)); // Set background color

        JLabel leaveIdLabel = new JLabel("Leave ID:");
        leaveIdLabel.setBounds(80, 30, 70, 25);
        JTextField leaveIdField = new JTextField();
        leaveIdField.setBounds(160, 30, 120, 25);

        JButton approveButton = new JButton("Approve");
        approveButton.setBounds(100, 80, 100, 30);
        JButton rejectButton = new JButton("Reject");
        rejectButton.setBounds(220, 80, 100, 30);
        JButton showDataButton = new JButton("$");
        showDataButton.setBounds(290, 30, 30, 25);

        Font buttonFont = new Font("Tahoma", Font.PLAIN, 14); // Set common font for buttons
        approveButton.setFont(buttonFont);
        rejectButton.setFont(buttonFont);
        showDataButton.setFont(buttonFont);

        approveButton.setBackground(new Color(135, 206, 250)); // Set button background color
        rejectButton.setBackground(new Color(135, 206, 250)); // Set button background color
        showDataButton.setBackground(new Color(135, 206, 250)); // Set button background color

        approveButton.setForeground(Color.WHITE); // Set button text color
        rejectButton.setForeground(Color.WHITE); // Set button text color
        showDataButton.setForeground(Color.WHITE); // Set button text color

        // Set button size and center align
        Dimension buttonSize = new Dimension(100, 30);
        approveButton.setPreferredSize(buttonSize);
        rejectButton.setPreferredSize(buttonSize);

        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pop a message box for leave approval
                String leaveId = leaveIdField.getText();
                if (!leaveId.isEmpty()) {

                    int leaveIdInt = Integer.parseInt(leaveId);
                    // Call the backend method to approve the leave with leaveIdInt
                    if(GUI.leaveManagementSystem.approveLeave(leaveIdInt)){
                    JOptionPane.showMessageDialog(ApproveOrRejectPage.this,
                            "Leave request with ID " + leaveIdInt + " has been approved");
                    }
                    else{
                    JOptionPane.showMessageDialog(ApproveOrRejectPage.this,
                            "Entered leaveID not found.");}

                } else {
                    JOptionPane.showMessageDialog(ApproveOrRejectPage.this,
                            "Please enter the Leave ID.");
                }
            }
        });

        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pop a message box for leave rejection
                String leaveId = leaveIdField.getText();
                if (!leaveId.isEmpty()) {
                    int leaveIdInt = Integer.parseInt(leaveId);
                    // Call the backend method to reject the leave with leaveIdInt
                    if(GUI.leaveManagementSystem.rejectLeave(leaveIdInt)){
                    JOptionPane.showMessageDialog(ApproveOrRejectPage.this,
                            "Leave request with ID " + leaveIdInt + " has been rejected");}
                    else{
                        JOptionPane.showMessageDialog(ApproveOrRejectPage.this,
                                "Entered leaveID not found.");}


                } else {
                    JOptionPane.showMessageDialog(ApproveOrRejectPage.this,
                            "Please enter the Leave ID.");
                }

            }
        });

        showDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Read data from leaves_data.txt and open a new window to display it
                List<Leave> leavesList = GUI.leaveManagementSystem.readLeavesFromFile("leaves_data.txt");
                if (!leavesList.isEmpty()) {
                    showDataInNewWindow(leavesList);
                } else {
                    JOptionPane.showMessageDialog(ApproveOrRejectPage.this,
                            "No data found in the file.");
                }
            }
        });

        panel.add(leaveIdLabel);
        panel.add(leaveIdField);
        panel.add(approveButton);
        panel.add(rejectButton);
        panel.add(showDataButton);

        add(panel);
    }

    private void showDataInNewWindow(List<Leave> leavesList) {
        JFrame dataWindow = new JFrame();
        dataWindow.setTitle("Leaves Data");
        dataWindow.setSize(400, 250);
        dataWindow.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(230, 230, 250));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Leave leave : leavesList) {
            listModel.addElement("Leave ID: " + leave.getLeaveId() +
                    ", Employee Name: " + leave.getEmployeeName() +
                    ", Start Date: " + leave.getStartDate() +
                    ", End Date: " + leave.getEndDate() +
                    ", Leave Type: " + leave.getLeaveType() +
                    ", Approved: " + leave.isApproved());
        }

        JList<String> dataList = new JList<>(listModel);
        dataList.setFont(new Font("Tahoma", Font.PLAIN, 14));
        JScrollPane dataScrollPane = new JScrollPane(dataList);
        panel.add(dataScrollPane, BorderLayout.CENTER);

        dataWindow.add(panel);
        dataWindow.setVisible(true);
    }
}

