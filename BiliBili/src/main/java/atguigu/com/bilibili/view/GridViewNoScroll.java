package atguigu.com.bilibili.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.GridView;

/**
 * Created by ASUS on 2017/7/26.
 */

//禁止上下滑动的grideView
public class GridViewNoScroll extends GridView {

    public GridViewNoScroll(Context context) {
        super(context);
// TODO Auto-generated constructor stub
    }

    public GridViewNoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
// TODO Auto-generated constructor stub
    }

    public GridViewNoScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
// TODO Auto-generated constructor stub
    }

    //通过重新dispatchTouchEvent方法来禁止滑动
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
// TODO Auto-generated method stub
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            return true;//禁止Gridview进行滑动
        }
        return super.dispatchTouchEvent(ev);
    }
}

