package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.TimeEntry;
import cz.cvut.fel.dbs.TimeEntryId;

import java.time.LocalDateTime;
import java.util.List;

public class TimeEntryDaoImpl extends GenericDaoImpl<TimeEntry, TimeEntryId> {

    public TimeEntryDaoImpl() {
        super(TimeEntry.class);
    }

}
