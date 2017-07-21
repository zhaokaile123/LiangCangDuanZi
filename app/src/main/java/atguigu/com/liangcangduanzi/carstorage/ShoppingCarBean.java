package atguigu.com.liangcangduanzi.carstorage;

/**
 * Created by ASUS on 2017/7/19.
 */

public class ShoppingCarBean {

    private String name ;
    private String id;
    private String price;
    private String discount;
    private String brandname;
    private String imageUrl;

    private boolean isChecked = true; // 默认是被选中的
    private int numb = 1;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public ShoppingCarBean() {};

    public ShoppingCarBean(String name, String id, String price, String discount, String brandname,int number) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.discount = discount;
        this.brandname = brandname;
        this.numb = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBrandname() {
        return brandname;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }
}
