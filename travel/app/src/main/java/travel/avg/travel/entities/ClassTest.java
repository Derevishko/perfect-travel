package travel.avg.travel.entities;

import java.sql.Time;

public class ClassTest {
    private long id;
    private long time;
    private String text;

    public ClassTest() {
    }

    public ClassTest(long id, long time, String text) {
        this.id = id;
        this.time = time;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public long getTime() {
        return time;
    }

    public String getText() {
        return text;
    }
}
