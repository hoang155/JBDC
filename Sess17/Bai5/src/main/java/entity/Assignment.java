package entity;
public class Assignment {
    private int employeeId;
    private int projectId;
    private String role;

    public Assignment(int employeeId, int projectId, String role) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.role = role;
    }

    public int getEmployeeId() { return employeeId; }
    public int getProjectId() { return projectId; }
    public String getRole() { return role; }
}