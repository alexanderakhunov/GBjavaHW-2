import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class exes {
    public static void main(String[] args) throws Exception {
        String[] arrayData = ReadLine("data2.txt");
        System.out.println(PrintLine(arrayData[0]));
        for (int i = 0; i < arrayData.length; i++) {
            saveToFile(PrintLine(arrayData[i]));
        }
    }


    public static void saveToFile(String s) {
        Logger logger = Logger.getAnonymousLogger(); /* Из-за того, что метод, который раскрывает строку, работает в
        цикле for, получается, что логгер тоже подхватывается в этот цикл, и можно ли как-то реализовать, чтобы логгер
        ловил триггер только, когда есть ошибки и не создавался, если он пустой*/
        FileHandler filehandler = null;
        try{
            filehandler = new FileHandler("Log.txt");
        } catch (IOException e){
            e.printStackTrace();
        }
        logger.addHandler(filehandler);
        String path = "ResultFile.txt";
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(s);
            fw.flush();
            fw.append("\n");
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING,"Поломали(");
        }

    }

    public static String PrintLine(String line) {
        String line1 = line.replace("{", "");
        String line2 = line1.replace("}", "");
        String line3 = line2.replaceAll("\"", "");
        String line4 = line3.replace("[", "");
        String line5 = line4.replace("]", "");
        StringBuilder result = new StringBuilder("");
        String[] arrayData = line5.split(",");
        String[] listName = {"Студент ", " получил ", " по предмету "};
        for (int i = 0; i < arrayData.length; i++) {
            String[] arrData = arrayData[i].split(":");
            result.append(listName[i]);
            result.append(arrData[1]);
        }
        return result.toString();
    }

    public static String[] ReadLine(String pathway) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(pathway));
        String str;
        int size = 0;
        while ((str = br.readLine()) != null) {
            size += 1;
        }
        br.close();
        String[] listData = new String[size];
        int i = 0;
        BufferedReader br1 = new BufferedReader(new FileReader(pathway));
        while ((str = br1.readLine()) != null) {
            listData[i] = str;
            i += 1;
        }
        br1.close();
        return listData;
    }
}

