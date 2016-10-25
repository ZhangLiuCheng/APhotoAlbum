package com.aiyouwai.aphotoalbum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.entity.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoStaggerdAdapter extends RecyclerView.Adapter<PhotoStaggerdAdapter.Holder> {

    private Context context;
    private LayoutInflater inflater;
    private List<Photo> data;

    public PhotoStaggerdAdapter(Context context, List<Photo> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_photo_staggered, parent, false);
        return new Holder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.setData(data.get(position));
    }

    class Holder extends RecyclerView.ViewHolder {

        private ImageView image;

        public Holder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

        public void setData(Photo photo) {
            Picasso.with(context)
                    .load(photo.image)
                    .placeholder(R.drawable.default_loading)
                    .into(this.image);
        }
    }
}
