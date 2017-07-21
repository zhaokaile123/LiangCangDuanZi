package atguigu.com.liangcangduanzi.carstorage;

/**
 * Created by ASUS on 2017/7/19.
 */

public class GoodsTable {

    public static final String  TABLE_NAME = "goods";

    public static final String  GOODSNAME = "name";
    public static final String  GOODSID = "id";
    public static final String  PRICE = "price";
    public static final String  DISCOUNT = "discount";
    public static final String  BRANDNAME = "brand_name";
    public static final String NUMBER = "number";
    public static final String IMAGE = "image";


    public static final String CREATE_TABLE = "create table "+TABLE_NAME+"("
            +GOODSID+" text primary key, "
            +PRICE +" text, "
            +GOODSNAME + " text, "
            +DISCOUNT +" text, "
            + NUMBER + " integer, "
            + IMAGE +" text, "
            +BRANDNAME +" text)";
}
