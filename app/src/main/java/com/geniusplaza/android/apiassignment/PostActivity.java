package com.geniusplaza.android.apiassignment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geniusplaza.android.apiassignment.Data.Users;
import com.geniusplaza.android.apiassignment.services.WebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    EditText name;
    EditText job;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        name = findViewById(R.id.name);
        job = findViewById(R.id.job);
        submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText()!=null && job.getText()!=null)
                sendUserData(name.getText().toString(),job.getText().toString());
            }
        });

    }

    public void sendUserData(String name, String job){
        final WebService webService = WebService.retrofit.create(WebService.class);
        Call<Users> newUser = webService.insertUser(name,job);
        newUser.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                String msg = response.body().getName();
                Toast.makeText(PostActivity.this, msg,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }
}
