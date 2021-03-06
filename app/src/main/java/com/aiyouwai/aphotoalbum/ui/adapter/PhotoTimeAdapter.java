package com.aiyouwai.aphotoalbum.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.model.entity.Photo;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoTimeAdapter extends RecyclerView.Adapter<PhotoTimeAdapter.Holder> implements View.OnClickListener {

    private String[] colors =  {"#FF0000", "#00FF00", "#0000FF"};
    private int[] icons = {R.drawable.c_red, R.drawable.c_green, R.drawable.c_blue};

    private Context context;
    private LayoutInflater inflater;
    private List<Photo> data;

    private RecyclerItemLisener<Photo> listener;

    public PhotoTimeAdapter(Context context, List<Photo> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setListener(RecyclerItemLisener listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_photo_time, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.itemView.setTag(data.get(position));
        holder.itemView.setOnClickListener(this);
        holder.setData(position, data);
    }

    @Override
    public void onClick(View v) {
        if (null != listener) {
            Photo ln = (Photo) v.getTag();
            listener.onItemClick(v, ln);
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        private TextView date;
        private ImageView icon;
        private ImageView image;

        public Holder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

        public void setData(int position, List<Photo> data) {
            Photo photo = data.get(position);
            if (position <= 0) {
                date.setVisibility(View.VISIBLE);
            } else {
                if (photo.date.equals(data.get(position - 1).date)) {
                    date.setVisibility(View.INVISIBLE);
                } else {
                    date.setVisibility(View.VISIBLE);
                }
            }
            date.setTextColor(Color.parseColor(colors[position % 3]));
            date.setText(photo.date);
            icon.setImageResource(icons[position % 3]);
            Picasso.with(context)
                    .load(photo.image)
                    .placeholder(R.drawable.default_loading)
                    .into(this.image);
        }
    }
}
