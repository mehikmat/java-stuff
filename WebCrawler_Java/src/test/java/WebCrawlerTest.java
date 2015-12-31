import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hikmat
 */
public class WebCrawlerTest {
    static public void main(String[] args) throws Exception {
        WebCrawler ws = new WebCrawler("http://hikmatdhamee.com.np", 5);
        for (List<String> ls : ws.url_content_lists) {
            String url = ls.get(0);
            String text = ls.get(1);
            System.out.println("\n\n\n--URL:\n" + url + "\n  content:\n" + text);
        }
    }
}