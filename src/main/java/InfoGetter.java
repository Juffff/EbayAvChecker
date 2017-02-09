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

    public InfoGetter(String link) throws IOException {
        this.link = link;
    }

    public void connect() throws IOException, InterruptedException {
        this.url = new URL(link);
        this.connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        while (reader.ready()) {
            String s = reader.readLine();
            sb.append(s);
        }

        reader.close();
    }

    public String[] getInfo() throws IOException, InterruptedException {
        EbayPageParser ebayPageParser = new EbayPageParser(link, sb);
        int tryCount = 15;
        while (!ebayPageParser.parse()) {
            connect();
            tryCount--;
            if (tryCount==0){
                return new String[]{ebayPageParser.getName(), "Undefined", "Something wrong with this listing. Check it by yourself!", "Undefined"};
            }

        }
        return new String[]{ebayPageParser.getName(), ebayPageParser.getPrice(), ebayPageParser.getAvailability(), ebayPageParser.getPurchased()};
    }


}
