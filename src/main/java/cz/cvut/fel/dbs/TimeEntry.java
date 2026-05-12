package cz.cvut.fel.dbs;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "time_entry")
public class TimeEntry {
    @EmbeddedId
    private TimeEntryId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "task_number", referencedColumnName = "task_number", nullable = false),
            @JoinColumn(name = "sprint_number", referencedColumnName = "sprint_number", nullable = false),
            @JoinColumn(name = "project_id", referencedColumnName = "project_id", nullable = false)
    })
    private Task task;

    @Column(name = "minutes")
    private Integer minutes;

    @Column(name = "logged_at")
    private Instant loggedAt;

    @Lob
    @Column(name = "note")
    private String note;

    public TimeEntryId getId() {
        return id;
    }

    public void setId(TimeEntryId id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Instant getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(Instant loggedAt) {
        this.loggedAt = loggedAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}