package com.example.loginapp.view;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.model.Data;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    String url, user;
    TextView username;
    ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.user_name);
        avatar = findViewById(R.id.user_avatar);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");
        username.setText(intent.getExtras().getString("name"));
        Picasso.with(this)
                .load(intent.getExtras().getString("avatar"))
                .into(avatar);

        //Khởi tạo RecyclerView

        final RecyclerView rvRepos = (RecyclerView) findViewById(R.id.rv_repos);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));

        //Khởi tạo OkHttpClient để lấy dữ liệu
        OkHttpClient client = new OkHttpClient();

        //Khởi tạo Moshi adapter để biến đổi json sang model
        Moshi moshi = new Moshi.Builder().build();

        Type reposType = Types.newParameterizedType(List.class, Data.class);
        final JsonAdapter<List<Data>> jsonAdapter = moshi.adapter(reposType);

        //Tạo request lên server
        final Request request = new Request.Builder()
                .url("https://api.github.com/users/"+ userName +"/repos")
                .build();

        //Thực thi request
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("Error", "Network Error");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //Lấy thông tin JSON trả về
                String json = response.body().string();
                final List<Data> data = jsonAdapter.fromJson(json);

                //Cho hiển thị lên RecyclerView
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvRepos.setAdapter(new DataAdapter(data, MainActivity.this));
                    }
                });
            }
        });


    }
}

