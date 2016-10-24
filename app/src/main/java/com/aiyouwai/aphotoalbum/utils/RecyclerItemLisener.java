package com.aiyouwai.aphotoalbum.utils;

import android.view.View;

public interface RecyclerItemLisener<T> {

    void onItemClick(View view, T item);

}
