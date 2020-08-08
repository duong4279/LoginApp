package com.example.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.Moshi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    public EditText edtUserName;
    public String userName;
    private JsonReader reader;
    String login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = (EditText) findViewById(R.id.edt_username);
        btnLogin= (Button) findViewById(R.id.btn_enter);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = edtUserName.getText().toString();
                login();
            }
        });


    }

    public void login() {
        // Khởi tạo OkHttpClient để lấy dữ liệu.
        OkHttpClient client = new OkHttpClient();

        // Khởi tạo Moshi adapter để biến đổi json sang model java
        Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<Repo> jsonAdapter = moshi.adapter(Repo.class);

        // Tạo request lên server.
        final okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://api.github.com/users/" + userName)
                .build();

        // Thực thi request.
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("Network", "Error Network");
            }
            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                // Lấy thông tin JSON trả về.
                Log.e("Network", "Successful Network");

                String json = response.body().string();
                System.out.println(json);

//                reader = JsonReader.of((BufferedSource) users);
//                reader.beginObject();
//                while (reader.hasNext()) {
//                    String name = reader.nextName();
//                    if (name.equals("login")) {
//                        login = reader.nextString();
//                    }
//                }


                if(json.contains("message")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Wrong UserName", Toast.LENGTH_SHORT).show();
                        }
                    });
//                    User user = jsonAdapter.fromJson(json);

                } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("url", "https://api.github.com/users/" + edtUserName.getText());
//                    intent.putExtra("name", user.getName());
                    intent.putExtra("user", userName);
                    startActivity(intent);



                }
            }
        });

        }

}
