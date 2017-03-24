import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

/**
 * Created by micha on 22.03.2017.
 */
public class LoadDict {

    public LoadDict(){}

    public Tree loadToTree(Tree tree, String path) throws IOException {
        long timeStart = System.currentTimeMillis();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                tree.addWord(line);
            }
        }
        summary(System.currentTimeMillis() - timeStart);
        return tree;
    }

    private void summary(long timeElapsed){
        System.out.println("Dictionary Load Successful!");
        System.out.println("Load Time: "+ timeElapsed + "ms" +"\n");
    }
}
