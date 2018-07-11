package model;

public class ScrapeLabCourse extends ScrapeCourse {
    public String labTime, labWeekday, labRoom;

    public ScrapeLabCourse(String name, String section, String time, String weekday, String room, String labTime, String labWeekday, String labRoom) {
        super(name, section, time, weekday, room);
        this.labTime = labTime;
        this.labWeekday = labWeekday;
        this.labRoom = labRoom;
    }

    @Override
    public String toString() {
        return super.toString() + "labTime: " + labTime + ", week: " + labWeekday + ", room: " + labRoom;
    }
}
