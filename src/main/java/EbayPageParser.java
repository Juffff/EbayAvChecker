import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EbayPageParser {
    private String link;
    private StringBuilder sb;
    private String name;
    private String price;
    private String availability;
    private String purchased;

    public EbayPageParser(String link, StringBuilder sb) {
        this.link = link;
        this.sb = sb;
    }

    public boolean parse() {
        String htmlString = sb.toString();
        Document document = Jsoup.parse(htmlString);
        this.name = link.replace("http://www.ebay.com/itm/", "").split("/")[0].replaceAll("-", " ");
        this.name = name.substring(0, this.name.length() - 1);
        System.out.println("Loading...");
        if (htmlString.contains("This listing has ended") || htmlString.contains("This listing was ended")) {
            this.price = "Undefined";
            this.availability = "This listing has ended";
            this.purchased = "Undefined";
            return true;
        } else {
            try {
                int remainQtyIndex = htmlString.indexOf("\"remainQty\"");
                int totalBidsIndex = htmlString.indexOf("\"totalBids\"");
                String qtyInfoString = htmlString.substring(remainQtyIndex, totalBidsIndex);
                this.availability = qtyInfoString.split(",")[0].split(":")[1];
                this.purchased = qtyInfoString.split(",")[4].split(":")[1];
                int binPriceOnlyIndex = htmlString.indexOf("\"binPriceOnly\"");
                int convertedBinPriceIndex = htmlString.indexOf("\"convertedBinPrice\"");
                String priceInfoString = htmlString.substring(binPriceOnlyIndex, convertedBinPriceIndex);
                this.price = priceInfoString.split(":")[1].replaceAll("\"", "").replace(",", "").replace(".", ",");

            } catch (StringIndexOutOfBoundsException e) {
                return false;
            }
            return true;
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

    public String getPurchased() {
        return this.purchased;
    }
}
