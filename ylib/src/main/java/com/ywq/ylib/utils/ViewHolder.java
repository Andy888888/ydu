package com.ywq.ylib.utils;

import android.util.SparseArray;
import android.view.View;

/**
 * @author yanwenqiang
 * @Date 15-7-5
 * @description 适配器获取view的 viewholder
 */
public class ViewHolder {

    private ViewHolder() {
    }

    public static <T extends View> T get(View view, int id) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
