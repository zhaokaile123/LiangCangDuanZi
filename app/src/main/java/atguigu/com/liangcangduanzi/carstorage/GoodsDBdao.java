package atguigu.com.liangcangduanzi.carstorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import static atguigu.com.liangcangduanzi.carstorage.GoodsTable.TABLE_NAME;

/**
 * Created by ASUS on 2017/7/19.
 */

public class GoodsDBdao {

    private GoodsDB goodsDB;

    public GoodsDBdao(Context context) {
        goodsDB = new GoodsDB(context);
    }

    //  添加 到  數據庫
    public void addInfo(ShoppingCarBean bean){
        //校验
        if (bean == null){
            throw new NullPointerException("不能为空");
        }

        SQLiteDatabase database = goodsDB.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(GoodsTable.PRICE,bean.getPrice());
        contentValues.put(GoodsTable.GOODSID,bean.getId());
        contentValues.put(GoodsTable.NUMBER,bean.getNumb());
        contentValues.put(GoodsTable.DISCOUNT,bean.getDiscount());
        contentValues.put(GoodsTable.BRANDNAME,bean.getBrandname());
        contentValues.put(GoodsTable.GOODSNAME,bean.getName());
        contentValues.put(GoodsTable.IMAGE,bean.getImageUrl());


        database.replace(TABLE_NAME,null,contentValues);
    }

    // 删除联系人信息
    public void deleteInfo(String id){
        if (TextUtils.isEmpty(id)){
            return;
        }
        SQLiteDatabase database = goodsDB.getWritableDatabase();

        database.delete(GoodsTable.TABLE_NAME,
                GoodsTable.GOODSID + "=?",new String[]{id});
    }

    //获取所有的 信息
    public List<ShoppingCarBean> getAllInfo(){

        SQLiteDatabase database = goodsDB.getWritableDatabase();

        String sql = "select * from "+ GoodsTable.TABLE_NAME;

        Cursor cursor = database.rawQuery(sql, null);
        List<ShoppingCarBean> shoppingInfo = new ArrayList<>();

        while (cursor.moveToNext()){
            ShoppingCarBean bean = new ShoppingCarBean();

            bean.setName(cursor.getString(cursor.getColumnIndex(GoodsTable.GOODSNAME)));
            bean.setId(cursor.getString(cursor.getColumnIndex(GoodsTable.GOODSID)));
            bean.setBrandname(cursor.getString(cursor.getColumnIndex(GoodsTable.BRANDNAME)));
            bean.setDiscount(cursor.getString(cursor.getColumnIndex(GoodsTable.DISCOUNT)));
            bean.setPrice(cursor.getString(cursor.getColumnIndex(GoodsTable.PRICE)));
            bean.setNumb(cursor.getInt(cursor.getColumnIndex(GoodsTable.NUMBER)));
            bean.setImageUrl(cursor.getString(cursor.getColumnIndex(GoodsTable.IMAGE)));

            //将单个用户添加到集合中
            shoppingInfo.add(bean);
          }

        cursor.close();//关掉游标

        return shoppingInfo;
    }

    // 更新状态
    public void updateInfo(ShoppingCarBean bean){
        if (bean == null){
            return;
        }

        SQLiteDatabase database = goodsDB.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GoodsTable.NUMBER,bean.getNumb());
        database.update(GoodsTable.TABLE_NAME,contentValues,
                GoodsTable.GOODSID +"=?",new String[]{bean.getId()});
    }


    /**
     * 保存多个产品
     */
    public void saveMoreProduct(List<ShoppingCarBean> userInfos ) {
        if (userInfos == null || userInfos.size() == 0) {
            return;
        }

        for (int i = 0; i < userInfos.size(); i++) {

            addInfo(userInfos.get(i));

        }

    }

    /**
     * 关闭数据库
     */
    public void closeDB() {

        if (goodsDB != null) {

            goodsDB.close();
        }
    }





}
