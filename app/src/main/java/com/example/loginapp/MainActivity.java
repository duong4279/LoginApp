package com.example.loginapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");

        //Khởi tạo RecyclerView

        final RecyclerView rvRepos = (RecyclerView) findViewById(R.id.rv_repos);
        rvRepos.setLayoutManager(new LinearLayoutManager(this));

        //Khởi tạo OkHttpClient để lấy dữ liệu
        OkHttpClient client = new OkHttpClient();

        //Khởi tạo Moshi adapter để biến đổi json sang model
        Moshi moshi = new Moshi.Builder().build();

        Type reposType = Types.newParameterizedType(List.class, Repo.class);
        final JsonAdapter<List<Repo>> jsonAdapter = moshi.adapter(reposType);

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
                final List<Repo> repos = jsonAdapter.fromJson(json);

                //Cho hiển thị lên RecyclerView
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvRepos.setAdapter(new RepoAdapter(repos, MainActivity.this));
                    }
                });
            }
        });


    }
}

