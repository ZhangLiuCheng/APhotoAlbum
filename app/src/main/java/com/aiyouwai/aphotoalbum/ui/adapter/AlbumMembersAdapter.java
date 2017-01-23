package com.aiyouwai.aphotoalbum.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aiyouwai.aphotoalbum.R;
import com.aiyouwai.aphotoalbum.model.entity.Member;
import com.aiyouwai.aphotoalbum.model.entity.Photo;
import com.aiyouwai.aphotoalbum.utils.RecyclerItemLisener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AlbumMembersAdapter extends RecyclerView.Adapter<AlbumMembersAdapter.Holder> implements View.OnClickListener {

    private Context context;
    private LayoutInflater inflater;
    private List<Member> data;

    private RecyclerItemLisener<Member> listener;

    public AlbumMembersAdapter(Context context, List<Member> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setListener(RecyclerItemLisener listener) {
        this.listener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_album_member, parent, false);
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
        holder.setData(data.get(position));
    }

    @Override
    public void onClick(View v) {
        if (null != listener) {
            Member ln = (Member) v.getTag();
            listener.onItemClick(v, ln);
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        private ImageView image;

        public Holder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

        public void setData(Member photo) {
//            Picasso.with(context)
//                    .load(photo.image)
//                    .placeholder(R.drawable.default_loading)
//                    .into(this.image);
        }
    }
}
