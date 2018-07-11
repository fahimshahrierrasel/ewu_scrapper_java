import model.ScrapeCourse;
import scrapper.CourseScrapper;
import sorter.CourseSorter;

import java.util.List;

public class EWUScrapper {
    public static void main(String[] args) {

        CourseScrapper courseScrapper = new CourseScrapper("src/main/java/courselist.html");
        List<ScrapeCourse> scrapeCourses = courseScrapper.Scrape();

        CourseSorter courseSorter = new CourseSorter();
        courseSorter.init(scrapeCourses);
        System.out.println(scrapeCourses.size());
        System.out.println(courseSorter.findLabCourses().size());
        System.out.println(courseSorter.findRegularCourses().size());
        System.out.print(courseSorter.findAllCourses().size());
    }
}
