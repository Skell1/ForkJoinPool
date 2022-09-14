import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class Node {
    String url;
    ArrayList<Node> childs = new ArrayList<>();

    public Node(String url) {
        this.url = url;
    }

    protected void getChild() {
        try {
            Thread.sleep(150);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Document document = null;
        try {
            System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
            document = Jsoup.connect(this.url).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com").get();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (document != null) {
            Elements a = document.select("a");
            ArrayList<String> arrayList = new ArrayList<>();
            a.forEach(element -> arrayList.add(element.attr("href")));
            for (String line : arrayList) {
                if (line.isEmpty() || line.equals("/")){
                    continue;
                }
                if (!line.substring(line.length() - 1).equals("/")) {
                    line += "/";
                }
                if (url.contains(line)){
                    continue;
                }
                if (line.substring(0,1).equals("/")){

                    line = Main.rootURL+line.substring(1);
                }
                if (line.length()>5 && line.substring(line.length() - 5).equals(".pdf/")){
                    continue;
                }
                if (line.contains(url)) {
                        if (!Main.List.contains(line)) {
                            childs.add(new Node(line));
                            Main.List.add(line);
                        }
                }
            }
        }
    }

    public ArrayList<Node> getChilds() {
        return childs;
    }
}
