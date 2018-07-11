package scrapper;

import model.ScrapeCourse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CourseScrapper {
    String fileName;

    public CourseScrapper(String fileName) {
        this.fileName = fileName;
    }

    public List<ScrapeCourse> Scrape(){
        List<ScrapeCourse> scrapeCourses = new ArrayList<ScrapeCourse>();

        try {
            File htmlFile = new File(fileName);
            Document doc = Jsoup.parse(htmlFile, "UTF-8");

            Element body = doc.body();

            Elements tboxes = body.getElementsByClass("tbox");

            if(tboxes.size() >= 2){
                Elements tables = tboxes.get(1).getElementsByTag("table");
                Elements tbody = tables.get(0).getElementsByTag("tbody");
                Elements tr = tbody.get(0).getElementsByTag("tr");

                for (Element aTr : tr) {
                    String td = aTr.select("td").get(0).html();
                    if(td.contains("Class") || td.contains("Course"))
                        continue;

//                    for(Element aTd : aTr.select("td")){
//                        System.out.println(aTd.html());
//                    }
                    Elements rowData = aTr.getElementsByTag("td");
                    if(rowData.size() == 5){
                        ScrapeCourse scrapeCourse = new ScrapeCourse(
                                rowData.get(0).html(),
                                rowData.get(1).html(),
                                rowData.get(2).html(),
                                rowData.get(3).html(),
                                rowData.get(4).html()
                        );

                        scrapeCourses.add(scrapeCourse);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrapeCourses;
    }
}
