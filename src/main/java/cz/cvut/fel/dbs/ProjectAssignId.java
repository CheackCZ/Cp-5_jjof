package cz.cvut.fel.dbs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectAssignId implements Serializable {
    private static final long serialVersionUID = -7987928238395645819L;
    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectAssignId entity = (ProjectAssignId) o;
        return Objects.equals(this.employeeId, entity.employeeId) &&
                Objects.equals(this.projectId, entity.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, projectId);
    }

}