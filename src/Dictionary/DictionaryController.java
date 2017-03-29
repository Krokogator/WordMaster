package Dictionary;

import ImageProcessing.ImageController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Michał(Krokogator) on 21.03.2017.
 *
 * Main dictionary class
 * Operates on trees
 * Access to simple functions/methods like .exist, .add, etc.
 */
public class DictionaryController {
    private Tree tree;

    public DictionaryController(){
        tree = new Tree();
    }

    public Tree load(String path){
        DictionaryLoader loader = new DictionaryLoader();
        try {
            loader.loadToTree(tree, path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //tree.printAll();
        return tree;
    }

    public void runGridSolver() throws IOException {
        long startTime, elapsedTime;
        Tree tree = load("C:\\Users\\micha\\Desktop\\Dictionary\\slowa.txt");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Wpisz 'image' aby sprawdzić obraz lub 16 literowy ciąg znaków");
        String textinput = br.readLine();

        while(!textinput.equals("exit")) {

            startTime = System.currentTimeMillis();


            if(textinput.equals("image")){
                System.out.println("Podaj nazwę pliku bez rozszerzenia:");
                String fileName = br.readLine();
                startTime = System.currentTimeMillis();
                textinput=checkImage(fileName);
            }

            if(textinput.length()==16){
                checkText(textinput);
            }



            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < 1) {
                System.out.println("Search Time: < 1ms" + "\n");
            } else {
                System.out.println("Search Time: " + elapsedTime + "ms" + "\n");
            }

            System.out.println("Wpisz 'image' aby sprawdzić obraz lub 16 literowy ciąg znaków");
            textinput = br.readLine();
        }
    }

    private String checkImage(String fileName) {
        ImageController imageController = new ImageController(fileName);
        return imageController.getString();

    }

    private void checkText(String textinput) throws IOException {
        if(textinput.length()==16) {


            //task start
            char[] chars = textinput.toCharArray();
            Character[] input = new Character[16];
            for (int i = 0; i < 16; i++) {
                input[i] = chars[i];
            }
            Grid grid = new Grid(input);
            grid.findPaths(tree);
            //task end

        }
        else{
            System.out.println("Niewłaściwy ciąg znaków");
        }
    }

    public Tree getTree(){ return tree; }

    public void testPacket(String testword){
        System.out.print("Exists: ");
        if(tree.checkPath(testword)){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }

    public void testValidPacket(String testword){
        System.out.print("Valid: ");
        if(tree.checkWord(testword)){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }


}