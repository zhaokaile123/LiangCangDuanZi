package atguigu.com.liangcangduanzi.carstorage;

import android.content.Context;
import android.util.SparseArray;

import java.util.ArrayList;


/**
 * 作者：杨光福 on 2017/6/14 09:23
 * QQ：541433511
 * 微信：yangguangfu520
 * 作用：
 */

public class CartStorage {

    public static CartStorage instance;
    private static Context mContext;
    /**
     * 数据存储在内存中
     */
    private SparseArray<ShoppingCarBean> sparseArray;
    private GoodsDBdao goodsDBdao;

    private CartStorage() {

        if(goodsDBdao != null) {
            goodsDBdao.closeDB();
        }
        goodsDBdao = new GoodsDBdao(mContext);//初始化数据库操作类


        //初始化集合
        sparseArray = new SparseArray<>();
        listTosparseArray();
    }

    private void listTosparseArray() {
        //得到所有数据
        ArrayList<ShoppingCarBean> datas = getAllData();

        if(datas != null && datas.size()>0) {
            for (int i = 0; i < datas.size(); i++) {
                ShoppingCarBean goodsBean = datas.get(i);

                sparseArray.put((int) Long.parseLong(goodsBean.getId()), goodsBean);
            }
        }
    }

    /**
     * 得到所有数据
     *
     * @return
     */
    public ArrayList<ShoppingCarBean> getAllData() {

        ArrayList<ShoppingCarBean> products = (ArrayList<ShoppingCarBean>) goodsDBdao.getAllInfo();
        if(products != null && products.size() > 0) {
            return products;
        }

        return null;
    }

    /**
     * 得到CartStorage
     *
     * @return
     */
    public static CartStorage getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            //加上锁
            synchronized (CartStorage.class) {
                if (instance == null) {
                    instance = new CartStorage();
                }
            }
        }

        return instance;
    }

    /**
     *  添加数据
     *
     * @param bean
     */
    public void addData(ShoppingCarBean bean) {

        ShoppingCarBean temp = sparseArray.get((int) Long.parseLong(bean.getId()));

        //查看内容中是否存在
        if(temp != null){
            //存在  就修改下数量
            temp.setNumb(bean.getNumb()+temp.getNumb());
        }else {
            //不存在 ，就在数据库保存
            temp = bean;
            goodsDBdao.addInfo(temp);
            //至少设置1个
           // temp.setNumb(1);
        }

        //内存中更新
        sparseArray.put((int) Long.parseLong(temp.getId()), temp);

        //同步到本地
        commit();
    }
    /**
     *      删除 数据
     * @param bean
     */
    public void deleteData(ShoppingCarBean bean) {
        //内存中更新
        sparseArray.delete((int) Long.parseLong(bean.getId()));
        //数据库更新
        goodsDBdao.deleteInfo(bean.getId());

    }


    /**
     * 更新数据
     *
     * @param bean
     */
    public void updateData(ShoppingCarBean bean) {
        //内存中更新
        sparseArray.put((int) Long.parseLong(bean.getId()),bean);
        //数据库 修改更新
        goodsDBdao.updateInfo(bean);
    }

    /**
     * 在数据库 保存一份
     */
    private void commit() {
        //把SparseArray 转换成List集合
        ArrayList<ShoppingCarBean> goodsBeens = sparseArrayToList();

        goodsDBdao.saveMoreProduct(goodsBeens); //保存多个

    }

    private ArrayList<ShoppingCarBean> sparseArrayToList() {
        ArrayList<ShoppingCarBean> goodsBeens = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            ShoppingCarBean goodsBean = sparseArray.valueAt(i);
            goodsBeens.add(goodsBean);
        }
        return goodsBeens;
    }

}
