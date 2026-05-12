package cz.cvut.fel.dbs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SprintId implements Serializable {
    private static final long serialVersionUID = -2419476283186177224L;
    @Column(name = "sprint_number", nullable = false)
    private Integer sprintNumber;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    public Integer getSprintNumber() {
        return sprintNumber;
    }

    public void setSprintNumber(Integer sprintNumber) {
        this.sprintNumber = sprintNumber;
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
        SprintId entity = (SprintId) o;
        return Objects.equals(this.sprintNumber, entity.sprintNumber) &&
                Objects.equals(this.projectId, entity.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sprintNumber, projectId);
    }

}