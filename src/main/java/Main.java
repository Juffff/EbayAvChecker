import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
  /*      System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        System.out.println(Main.class.getProtectionDomain().getCodeSource().getLocation().getClass());*/
        File[] currentFileList = (new File(".")).listFiles();

        for (File f : currentFileList) {
            if (f.getName().contains(".xls") || f.getName().contains(".xlsx")) {
                System.out.println(f.getName());
                try {
                    checkAvailability(f);
                } catch (MalformedURLException e) {
                    System.out.println("There is a problem with the file, may be breaks. Fix it! (" + f.getName() + ")");
                } catch (Exception e) {
                    System.out.println("Invalid file format: " + f.getName());
                }
            }
        }
        System.out.println("Ready. Pres ENTER to exit.");
        new BufferedReader(new InputStreamReader(System.in)).readLine();

    }

    public static void checkAvailability(File f) throws IOException, InterruptedException {
        String path = f.getPath();
        ExcelReader reader = new ExcelReader(path);
        reader.read();
        ArrayList<ItemFromExcel> itemFromExcelArrayList = reader.getItemListFromExcel();

        for (int j = 0; j < itemFromExcelArrayList.size(); j++) {
            System.out.println("Link " + (j + 1));
            ItemFromExcel itemFromExcel = itemFromExcelArrayList.get(j);
            System.out.println(itemFromExcel.getUrl());
            InfoGetter infoGetter = new InfoGetter(itemFromExcel.getUrl());
            infoGetter.connect();
            String[] resultsArray = infoGetter.getInfo();
            ExcelWriter excelWriter = new ExcelWriter(path);
            excelWriter.writeResults(resultsArray, j + 1);
        }
    }
}
