package com.aiyouwai.aphotoalbum.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.entity.Album;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private List<Album> data;

    private RecyclerItemLisener<Album> listener;

    public AlbumAdapter(Context context, List<Album> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(List<Album> images) {
        data.clear();
        append(images);
    }

    public void append(List<Album> images) {
        int positionStart = data.size();
        int itemCount = images.size();
        data.addAll(images);
        if (positionStart > 0 && itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
        } else {
            notifyDataSetChanged();
        }
    }

    public void setListener(RecyclerItemLisener listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_album, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.itemView.setTag(data.get(position));
        holder.itemView.setOnClickListener(this);
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public void onClick(View v) {
        if (null != listener) {
            Album ln = (Album) v.getTag();
            listener.onItemClick(v, ln);
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        ImageView icon;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.privacy)
        ImageView privacy;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Album album) {
            icon.setImageResource(album.getIcon());
            name.setText(album.getLabel());
            privacy.setVisibility(album.isPrivacy() ? View.VISIBLE : View.GONE);
//            Picasso.with(context)
//                    .load(line.icon)
//                    .into(icon);
//            name.setText(line.country);
        }
    }
}
