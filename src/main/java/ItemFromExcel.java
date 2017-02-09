
public class ItemFromExcel {
    private String asign;
    private String name;
    private String url;
    private String ebayPrice;
    private String amazonPrice;

    public ItemFromExcel(String asign, String name, String url, String ebayPrice, String amazonPrice) {
        this.asign = asign;
        this.name = name;
        this.url = url;
        this.ebayPrice = ebayPrice;
        this.amazonPrice = amazonPrice;
    }

    public String getAsign() {
        return asign;
    }

    public void setAsign(String asign) {
        this.asign = asign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEbayPrice() {
        return ebayPrice;
    }

    public void setEbayPrice(String ebayPrice) {
        this.ebayPrice = ebayPrice;
    }

    public String getAmazonPrice() {
        return amazonPrice;
    }

    public void setAmazonPrice(String amazonPrice) {
        this.amazonPrice = amazonPrice;
    }

    @Override
    public String toString() {
        return "ItemFromExcel{" +
                "asign='" + asign + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", ebayPrice='" + ebayPrice + '\'' +
                ", amazonPrice='" + amazonPrice + '\'' +
                '}';
    }
}
