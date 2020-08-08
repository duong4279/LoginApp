package com.example.loginapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoItemViewHolder> {
    private List<Repo> repos;
    private Context context;

    public RepoAdapter(List<Repo> repos, Context c) {
        this.repos = repos;
        this.context = c;
    }
    //ds repos
    @Override
    public int getItemCount() {
        return repos.size();
    }

    //tạo ra view để hiển thị lên
    @Override
    public RepoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_layout, parent, false);

        return new RepoItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoItemViewHolder holder, int position) {
        Repo repo = repos.get(position);

        holder.tvFullName.setText(repo.full_name);
        holder.tvUrl.setText(repo.html_url);
        holder.tvDes.setText(repo.description);
        holder.tvId.setText(String.valueOf(repo.id));
    }

    //lưu lại view để dùng lại
    public static class RepoItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFullName;
        public TextView tvId;
        public TextView tvUrl;
        public TextView tvDes;


        public RepoItemViewHolder(View itemView) {
            super(itemView);
            tvFullName = (TextView) itemView.findViewById(R.id.tv_full_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_idrepo);
            tvUrl = (TextView) itemView.findViewById(R.id.tv_url);
            tvDes = (TextView) itemView.findViewById(R.id.tv_des);
        }
    }
}
