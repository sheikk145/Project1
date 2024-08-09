package leavemanagementsystem;
import java.time.LocalDate;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class LeaveManagementSystem {
    private Leave[] leaves;
    private int leaveCount;
    private static LeaveManagementSystem instance;
    private Employee[] employees;
    private int employeeCount;
    private boolean loggedIn;
    private String loggedInEmployee;

    public LeaveManagementSystem(int maxLeaves, int maxEmployees) {
        leaves = new Leave[maxLeaves];
        leaveCount = 0;
        employees = new Employee[maxEmployees];
        employeeCount = 0;
        loggedIn = false;
        loggedInEmployee = "";
    }

    public static LeaveManagementSystem getInstance() {
        if (instance == null) {
            instance = new LeaveManagementSystem(100, 100);
        }
        return instance;
    }
    public void writeLeavesToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Leave leave : leaves) {
                if (leave != null) {
                    String leaveInfo = leave.getLeaveId() + "," + leave.getEmployeeName() + ","
                            + leave.getStartDate() + "," + leave.getEndDate() + "," + leave.getLeaveType() + ","
                            + leave.isApproved();
                    writer.write(leaveInfo);
                    writer.newLine();
                }
            }
            System.out.println("Leaves data written to file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public List<Leave> readLeavesFromFile(String filename) {
        List<Leave> leavesList = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            leavesList = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] leaveInfo = line.split(",");
                if (leaveInfo.length == 6) {
                    int leaveId = Integer.parseInt(leaveInfo[0]);
                    String employeeName = leaveInfo[1];
                    LocalDate startDate = LocalDate.parse(leaveInfo[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalDate endDate = LocalDate.parse(leaveInfo[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    String leaveType = leaveInfo[4];
                    boolean approved = Boolean.parseBoolean(leaveInfo[5]);

                    Leave leave = new Leave(leaveId, employeeName, startDate, endDate, leaveType);
                    leave.setApproved(approved);

                    leavesList.add(leave);
                }
            }

            // Convert the ArrayList to an array
//            leaves = leavesList.toArray(new Leave[leavesList.size()]);

            System.out.println("Leaves data read from file: " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        return leavesList;
    }

    public void addEmployee(String username, String password) {
        if (employeeCount < employees.length) {
            employees[employeeCount++] = new Employee(username, password);
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Maximum employees reached. Cannot add more employees.");
        }
    }

    public void login(String username, String password) {
        for (int i = 0; i < employeeCount; i++) {
            Employee employee = employees[i];
            if (employee != null && employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                loggedIn = true;
                loggedInEmployee = username;
                System.out.println("Login successful! Welcome, " + loggedInEmployee + ".");
                return;
            }
        }
        loggedIn = false;
        loggedInEmployee = "";
        System.out.println("Invalid credentials. Login failed.");
    }

    public void applyLeave(LocalDate startDate, LocalDate endDate, String leaveType) {
        if (loggedIn) {
            if (leaveCount < leaves.length) {
                leaves[leaveCount++] = new Leave(generateLeaveId(), loggedInEmployee, startDate, endDate, leaveType);
                System.out.println("Leave request applied for " + loggedInEmployee);
                System.out.println();

            } else {
                System.out.println("Maximum leaves reached. Cannot apply for more leave requests.");
            }
        } else {
            System.out.println("Please log in before applying for leave.");
        }
    }
    public String reasonForLeave(int leaveId){
        Leave leaveToApprove = findLeaveById(leaveId);
        return leaveToApprove.getLeaveType();
    }

    public String getUserName() {
        return loggedInEmployee;
    }

    public boolean approveLeave(int leaveId) {
        boolean flag = false;
        if (loggedIn) {
            Leave leaveToApprove = findLeaveById(leaveId);
            if (leaveToApprove != null && !leaveToApprove.isApproved()) {
                leaveToApprove.setApproved(true);
                System.out.println("Leave request with ID " + leaveId + " is approved.");
                flag = true;
            } else {
                System.out.println("Leave request not found with ID " + leaveId + " or already approved/rejected.");
            }
        } else {
            System.out.println("Please log in as an approver to approve leave requests.");
        }
        return flag;
    }

    public boolean rejectLeave(int leaveId) {
        boolean flag = false;
        if (loggedIn) {
            Leave leaveToReject = findLeaveById(leaveId);
            if (leaveToReject != null && !leaveToReject.isApproved()) {
                leaveToReject.setApproved(false);
                System.out.println("Leave request with ID " + leaveId + " is rejected.");
                flag = true;
            } else {
                System.out.println("Leave request not found with ID " + leaveId + " or already approved/rejected.");
            }
        } else {
            System.out.println("Please log in as an approver to reject leave requests.");
        }
        return flag;
    }

    public Leave findLeaveById(int leaveId) {
        for (Leave leave : leaves) {
            if (leave != null && leave.getLeaveId() == leaveId) {
                return leave;
            }
        }
        return null;
    }

    private int generateLeaveId() {
        return leaveCount + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the maximum number of leaves allowed: ");
        int maxLeaves = scanner.nextInt();
        System.out.print("Enter the maximum number of employees allowed: ");
        int maxEmployees = scanner.nextInt();
        LeaveManagementSystem leaveManagementSystem = new LeaveManagementSystem(maxLeaves, maxEmployees);

        scanner.nextLine(); // Clear the input buffer

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Employee");
            System.out.println("2. Login");
            System.out.println("3. Apply for leave");
            System.out.println("4. Approve leave request");
            System.out.println("5. Reject leave request");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the input buffer

            switch (choice) {
                case 1:
                    System.out.print("Enter employee username: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Enter employee password: ");
                    String newPassword = scanner.nextLine();
                    leaveManagementSystem.addEmployee(newUsername, newPassword);
                    break;
                case 2:
                    System.out.print("Enter your username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String password = scanner.nextLine();
                    leaveManagementSystem.login(username, password);
                    break;
                case 3:
                    if (leaveManagementSystem.loggedIn) {
                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        LocalDate startDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter end date (YYYY-MM-DD): ");
                        LocalDate endDate = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter leave type: ");
                        String leaveType = scanner.nextLine();
                        leaveManagementSystem.applyLeave(startDate, endDate, leaveType);
                    } else {
                        System.out.println("Please log in before applying for leave.");
                    }
                    break;
                case 4:
                    if (leaveManagementSystem.loggedIn) {
                        System.out.print("Enter leave ID to approve: ");
                        int leaveIdToApprove = scanner.nextInt();
                        leaveManagementSystem.approveLeave(leaveIdToApprove);
                    } else {
                        System.out.println("Please log in as an approver to approve leave requests.");
                    }
                    break;
                case 5:
                    if (leaveManagementSystem.loggedIn) {
                        System.out.print("Enter leave ID to reject: ");
                        int leaveIdToReject = scanner.nextInt();
                        leaveManagementSystem.rejectLeave(leaveIdToReject);
                    } else {
                        System.out.println("Please log in as an approver to reject leave requests.");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the Leave Management System...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}

class Leave {
    private int leaveId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private boolean approved;

    public Leave(int leaveId, String employeeName, LocalDate startDate, LocalDate endDate, String leaveType) {
        this.leaveId = leaveId;
        this.employeeName = employeeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.approved = false;
    }
    public int getLeaveId() {
        return leaveId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}



class Employee {
    private String username;
    private String password;

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}