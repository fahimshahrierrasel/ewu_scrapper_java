package model;

public class ScrapeCourse {
    public String name, section, time, weekday, room;

    public ScrapeCourse(String name, String section, String time, String weekday, String room) {
        this.name = name;
        this.section = section;
        this.time = time;
        this.weekday = weekday;
        this.room = room;
    }

    @Override
    public String toString() {
        return "ScrapeCourse{" +
                "name='" + name + '\'' +
                ", section='" + section + '\'' +
                ", time='" + time + '\'' +
                ", weekday='" + weekday + '\'' +
                ", room='" + room + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScrapeCourse){
            ScrapeCourse course = (ScrapeCourse) obj;
            if(this.name.equals(course.name) && this.section.equals(course.section)){
                return true;
            }
        }
        return false;
    }
}
