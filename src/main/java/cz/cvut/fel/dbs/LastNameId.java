package cz.cvut.fel.dbs;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LastNameId implements Serializable {
    private static final long serialVersionUID = 6815870893176401902L;
    @Column(name = "userdetails_id", nullable = false)
    private Integer userdetailsId;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    public Integer getUserdetailsId() {
        return userdetailsId;
    }

    public void setUserdetailsId(Integer userdetailsId) {
        this.userdetailsId = userdetailsId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LastNameId entity = (LastNameId) o;
        return Objects.equals(this.userdetailsId, entity.userdetailsId) &&
                Objects.equals(this.lastName, entity.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userdetailsId, lastName);
    }

}