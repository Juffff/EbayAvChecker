import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
  /*      System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getClass());*/
        File[] currentFileList = (new File(".")).listFiles();


        for(File f : currentFileList){
            if(f.getName().contains(".xls") || f.getName().contains(".xlsx")){
                System.out.println(f.getName());
                checkAvailability(f);
            }
        }

        new BufferedReader(new InputStreamReader(System.in)).readLine();

    }

    public static void checkAvailability(File f) throws IOException, InterruptedException {
        String path = f.getPath();
        ExcelReader reader = new ExcelReader(path);
        reader.read();
        ArrayList<ItemFromExcel> itemFromExcelArrayList = reader.getItemListFromExcel();

        for(int j = 0; j < itemFromExcelArrayList.size(); j++){
            System.out.println("Link "+(j+1));
            ItemFromExcel itemFromExcel = itemFromExcelArrayList.get(j);
            System.out.println(itemFromExcel.getUrl());
            InfoGetter infoGetter = new InfoGetter(itemFromExcel.getUrl());
            infoGetter.connect();
            String[] resultsArray = infoGetter.getInfo();
            ExcelWriter excelWriter = new ExcelWriter(path);
            excelWriter.writeResults(resultsArray, j+1);
        }
    }
}
