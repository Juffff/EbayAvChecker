import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class WalmartPageParser {
    private String link;
    private StringBuilder sb;
    private String name;
    private String price;
    private String availability;


    public WalmartPageParser(String link, StringBuilder sb) {
        this.link = link;
        this.sb = sb;
    }

    public boolean parse() {
        String htmlString = sb.toString();
        Document document = Jsoup.parse(htmlString);

        this.name = document.getElementsByTag("title").get(0).html().replace(" - Walmart.com","");
        System.out.println("Loading...");
        if (htmlString.contains("Out of stock")) {
            this.price = "Undefined";
            this.availability = "Out of stock";
            return true;
        } else {
            try {
                this.availability = "Availible";
                int basePriceIndex = htmlString.indexOf("\"CURRENT\"");
                int currencyIndex = basePriceIndex + htmlString.substring(basePriceIndex,htmlString.length()-1).indexOf(",\"currencyUnit\"");
                String priceString = htmlString.substring(basePriceIndex,currencyIndex);
                this.price = priceString.replace("\"CURRENT\":{\"priceType\":\"BASE\",\"price\":","").replace(".",",").replace("\"CURRENT\":{\"priceType\":\"ROLLBACK\",\"price\":","");
                return true;
            } catch (StringIndexOutOfBoundsException e) {
                return false;
            }

        }
    }

    public String getName() {
        return this.name;
    }

    public String getPrice() {
        return this.price;
    }

    public String getAvailability() {
        return this.availability;
    }

}
