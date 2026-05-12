package cz.cvut.fel.dbs;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "last_names")
public class LastName {
    @EmbeddedId
    private LastNameId id;

    public LastNameId getId() {
        return id;
    }

    public void setId(LastNameId id) {
        this.id = id;
    }

    //TODO [Reverse Engineering] generate columns from DB
}