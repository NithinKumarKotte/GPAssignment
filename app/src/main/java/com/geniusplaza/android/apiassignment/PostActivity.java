package com.geniusplaza.android.apiassignment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

/*
* Activity to handle POST operation*/
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
                if(!(name.getText().toString().matches("") && job.getText().toString().matches(""))) {
                    System.out.println(name.getText().toString());
                    System.out.println(job.getText().toString());
                    sendUserData(name.getText().toString(), job.getText().toString());
                }else{
                    Toast.makeText(PostActivity.this,"Please enter User Credentials",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /*
    * To perform POST operation
    * */
    public void sendUserData(String name, String job){
        final WebService webService = WebService.retrofit.create(WebService.class);

        //insertUser Method is implemented to perform Call operation
        Call<Users> newUser = webService.insertUser(name,job);
        newUser.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                StringBuilder sBuilder = new StringBuilder();
                sBuilder.append("User Created: "+"\n\n"+
                        "Name: "+ response.body().getName()+"\n"+
                        "Job: "+response.body().getJob()+"\n"+
                        "Id: "+response.body().getId()+"\n"+
                        "createdAt: "+response.body().getCreatedAt());

                //Data represented on Alert Dialog Box

                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(PostActivity.this);
                alertBuilder.setMessage(sBuilder);
                alertBuilder.setCancelable(true);

                alertBuilder.setPositiveButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert = alertBuilder.create();
                alert.show();
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Toast.makeText(PostActivity.this,"Failed to create User",Toast.LENGTH_LONG).show();
            }
        });
    }
}
