package cz.cvut.fel.dbs;

import jakarta.persistence.*;

@Entity
@Table(name = "project_assign")
public class ProjectAssign {
    @EmbeddedId
    private ProjectAssignId id;

    @MapsId("employeeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public ProjectAssignId getId() {
        return id;
    }

    public void setId(ProjectAssignId id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}