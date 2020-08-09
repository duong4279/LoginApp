package com.example.loginapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.model.Data;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private List<Data> data;
    private Context context;

    public DataAdapter(List<Data> data, Context c) {
        this.data = data;
        this.context = c;
    }
    //ds repos
    @Override
    public int getItemCount() {
        return data.size();
    }

    //tạo ra view để hiển thị lên
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_layout, parent, false);

        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        Data d = data.get(position);
//        Picasso.with(context)
//                .load(d.avatar_url)
//                .into(holder.ivAvatar);
        holder.tvFullName.setText(d.full_name);
        holder.tvUrl.setText(d.html_url);
        holder.tvDes.setText(d.description);
        holder.tvId.setText(String.valueOf(d.id));


    }

    //lưu lại view để dùng lại
    public static class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFullName;
        public TextView tvId;
        public TextView tvUrl;
        public TextView tvDes;


        public DataViewHolder(View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.tv_full_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_idrepo);
            tvUrl = (TextView) itemView.findViewById(R.id.tv_url);
            tvDes = (TextView) itemView.findViewById(R.id.tv_des);
        }
    }
}
