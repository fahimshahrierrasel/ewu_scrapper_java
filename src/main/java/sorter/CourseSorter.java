package sorter;

import model.ScrapeCourse;
import model.ScrapeLabCourse;

import java.util.*;

public class CourseSorter {
    private List<ScrapeCourse> scrapeCourses;
    private List<ScrapeCourse> duplicatedLabCourses;
    private List<ScrapeCourse> duplicatedRegularCourses;
    private List<ScrapeCourse> allDuplicateFreeCourses;
    private List<String> allCourseNames;
    private List<String> allLabCourses;

    public void init (List<ScrapeCourse> scrapeCourses) {
        this.scrapeCourses = scrapeCourses;
        findLabAndRegularCourses();
        findDuplicateFreeCourses();
    }

    private void findLabAndRegularCourses() {
        duplicatedLabCourses = new ArrayList<ScrapeCourse>();
        duplicatedRegularCourses = new ArrayList<ScrapeCourse>();

        for(ScrapeCourse course: scrapeCourses) {
            if(course.name.toUpperCase().contains("LAB"))
                duplicatedLabCourses.add(course);
            else
                duplicatedRegularCourses.add(course);
        }

        Set<String> regularCourses = new TreeSet<String>();
        for(ScrapeCourse course: duplicatedRegularCourses) {
            regularCourses.add(course.name);
        }
        allCourseNames = new ArrayList<String>(regularCourses);

        Set<String> labCoursesSet = new TreeSet<String>();
        for(ScrapeCourse course : duplicatedLabCourses) {
            labCoursesSet.add(course.name.toUpperCase());
        }
        allLabCourses = new ArrayList<String>(labCoursesSet);
    }

    private void findDuplicateFreeCourses() {
        allDuplicateFreeCourses = new ArrayList<ScrapeCourse>();
        for(String courseName : allCourseNames) {
            List<ScrapeCourse> sameCourses = new ArrayList<ScrapeCourse>();
            List<ScrapeCourse> sameDuplicateFreeCourse = new ArrayList<ScrapeCourse>();

            for(ScrapeCourse course : duplicatedRegularCourses){
                if(course.name.equals(courseName))
                    sameCourses.add(course);
            }

            for (int i = 0; i < sameCourses.size()-1; i++) {
                ScrapeCourse current = sameCourses.get(i);
                ScrapeCourse next = sameCourses.get(i+1);
                if(current.equals(next)){
                    ScrapeCourse mergeCourse = new ScrapeCourse(
                            current.name,
                            current.section,
                            current.time,
                            current.weekday+next.weekday,
                            current.room + " & " + next.room);
                    sameDuplicateFreeCourse.add(mergeCourse);
                    ++i;
                }else
                    sameDuplicateFreeCourse.add(current);
            }
            allDuplicateFreeCourses.addAll(sameDuplicateFreeCourse);
        }
    }

    public List<ScrapeLabCourse> findLabCourses() {
        List<ScrapeCourse> normalLabCourses = new ArrayList<ScrapeCourse>();
        for(ScrapeCourse course : allDuplicateFreeCourses) {
            String courseName = course.name + "LAB";
            if(allLabCourses.contains(courseName.toUpperCase()))
            {
                normalLabCourses.add(course);
            }
        }

        List<ScrapeLabCourse> scrapeLabCourses = new ArrayList<ScrapeLabCourse>();

        for(ScrapeCourse labCourse : duplicatedLabCourses) {
            for(ScrapeCourse course : normalLabCourses) {
                if((labCourse.name.toUpperCase().equals((course.name + "LAB").toUpperCase()) && labCourse.section.equals(course.section))){
                    scrapeLabCourses.add(new ScrapeLabCourse(
                            course.name,
                            course.section,
                            course.time,
                            course.weekday,
                            course.room,
                            labCourse.time,
                            labCourse.weekday,
                            labCourse.room
                    ));

                }
            }
        }

        return scrapeLabCourses;
    }

    public List<ScrapeCourse> findRegularCourses() {
        findDuplicateFreeCourses();

        List<ScrapeCourse> allRegularCourses = new ArrayList<ScrapeCourse>();

        for(ScrapeCourse course : allDuplicateFreeCourses) {
            String courseName = course.name + "LAB";
            if(!allLabCourses.contains(courseName.toUpperCase()))
            {
                allRegularCourses.add(course);
            }
        }

        return allRegularCourses;
    }

    public List<ScrapeCourse> findAllCourses() {
        return scrapeCourses;
    }
}
