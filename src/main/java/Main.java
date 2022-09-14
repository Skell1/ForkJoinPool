import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public volatile static ArrayList<String> List = new ArrayList<>();
    public volatile static Set<String> set = new LinkedHashSet<>();
    public static String rootURL;
    public static void main(String[] args) {
        String url = "https://hh.ru/";

        rootURL = url;
        List.add(url);

        Node root = new Node(url);
        root.getChild();

        new ForkJoinPool().invoke(new SiteMap(root));
        Collections.sort(List);
        for (String line : List){
            int count = StringUtils.countMatches(line, "/");
            for (int i = 0; i < count-3; i++) {
                line = "\t"+line;
            }
            System.out.println(line);
        }
    }
}

