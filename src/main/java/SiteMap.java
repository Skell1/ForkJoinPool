import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class SiteMap extends RecursiveTask<ArrayList<String>>
{
    private final Node node;
//    public SiteMap(Node node) {
//        this.node = node;
//    }
    public SiteMap(Node node1){
        node=node1;
    }



    @Override
    protected ArrayList<String> compute() {
        List<SiteMap> subTasks = new ArrayList<>();
        for(Node child : node.getChilds()) {
            try {
                child.getChild();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
            SiteMap task = new SiteMap(child);
            task.fork();
            subTasks.add(task);
        }

        for (SiteMap task : subTasks) {
           task.join();
        }
        return null;
    }
}
