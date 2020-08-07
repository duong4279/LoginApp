package com.example.loginapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItemViewHolder> {
    private List<User> users;
    private Context context;

    public UserAdapter (List<User> users, Context c) {
        this.users = users;
        this.context = c;
    }
    //ds repos
    @Override
    public int getItemCount() {
        return users.size();
    }

    //tạo ra view để hiển thị lên
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_layout, parent, false);

        return new UserItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        User user = users.get(position);

        holder.tvFullName.setText(user.full_name);
        holder.tvUrl.setText(user.html_url);
        holder.tvDes.setText(user.description);
        holder.tvId.setText(String.valueOf(user.id));
        holder.tvLogin.setText(user.login);
    }

    //lưu lại view để dùng lại
    public static class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvFullName;
        public TextView tvId;
        public TextView tvUrl;
        public TextView tvDes;
        public  TextView tvLogin;


        public UserItemViewHolder(View itemView) {
            super(itemView);
            tvLogin = (TextView) itemView.findViewById(R.id.tv_Login);
            tvFullName = (TextView) itemView.findViewById(R.id.tv_full_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_idrepo);
            tvUrl = (TextView) itemView.findViewById(R.id.tv_url);
            tvDes = (TextView) itemView.findViewById(R.id.tv_des);
        }
    }
}
