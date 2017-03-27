import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class InfoGetter {
    private String link;
    private URL url;
    private URLConnection connection;
    private StringBuilder sb = new StringBuilder();
    private BufferedReader reader;

    public InfoGetter(String link) throws IOException {
        this.link = link;
    }

    public boolean connect() throws IOException, InterruptedException {
        this.url = new URL(link);
        this.connection = url.openConnection();

        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while (reader.ready()) {
                String s = reader.readLine();
                sb.append(s);
            }
            reader.close();
            return true;
        } catch (IOException e){
            System.out.println("Bad URL!");
            e.printStackTrace();
            System.out.println("Continue...");
            return false;
         }



    }

    public String[] getInfo() throws IOException, InterruptedException {

        int tryCount = 20;

        if (link.contains("ebay.com")) {
            EbayPageParser ebayPageParser = new EbayPageParser(link, sb);

            while (!ebayPageParser.parse()) {
                connect();
                tryCount--;
                if (tryCount == 0) {
                    return new String[]{ebayPageParser.getName(), "Undefined", "Something wrong with this listing. Check it by yourself!", "Undefined"};
                }
            }
            return new String[]{ebayPageParser.getName(), ebayPageParser.getPrice(), ebayPageParser.getAvailability(), ebayPageParser.getPurchased()};
        }

        if (link.contains("walmart.com")) {
            WalmartPageParser walmartPageParser = new WalmartPageParser(link, sb);
            while (!walmartPageParser.parse()) {
                connect();
                tryCount--;
                if (tryCount == 0) {
                    return new String[]{walmartPageParser.getName(), "Undefined", "Something wrong with this listing. Check it by yourself!", "Undefined"};
                }

            }
            return new String[]{walmartPageParser.getName(), walmartPageParser.getPrice(), walmartPageParser.getAvailability(), "Undefined"};
        }

        return new String[]{};
    }


}
