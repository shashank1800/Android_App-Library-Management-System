package com.shashank_bhat.lms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Statement;

public class signup extends AppCompatActivity {

    public Statement stmt;

    private EditText Br_Id, Name,Phone_no,Password;
    private Button createAccButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Br_Id = (EditText)findViewById(R.id.Br_Id);
        Name = (EditText)findViewById(R.id.Name);
        Phone_no = (EditText)findViewById(R.id.Phone_no);
        Password = (EditText)findViewById(R.id.Password);
        createAccButton = (Button)findViewById(R.id.createAccButton);

        context = this;

        createAccButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                try {
                    String loginStatus="Successful";
                    Connection con = ConnectionClass.CON();
                    if (con == null || Br_Id.getText().toString().equals("")||Name.getText().toString().equals("")||Phone_no.getText().toString().equals("")||Password.getText().toString().equals("")) {
                        if(con == null) {
                            loginStatus = "Network Error";
                            showToast(loginStatus);
                        }
                        else if(Br_Id.getText().toString().equals("")||Name.getText().toString().equals("")||Phone_no.getText().toString().equals("")||Password.getText().toString().equals("")){
                            showToast("Some fields are Empty");
                        }
                    } else {

                        stmt = con.createStatement();
                        String query = "insert into BORROWER values('"+Br_Id.getText().toString()+"','"+Name.getText().toString()+"','"+Password.getText().toString()+"','"+Phone_no.getText().toString()+"',null,null,null);";
                        stmt.execute(query);
                        Toast.makeText(getApplicationContext(),loginStatus,Toast.LENGTH_SHORT).show();
                        createAccButton.setClickable(false);

                        Intent intent = new Intent(context,MainActivity.class);
                        startActivity(intent);
                        finish();

                        con.close();
                    }
                }
                catch (Exception ex)
                {
                    showToast("Account already exists on this Id");
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    Intent intent = new Intent(context,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    public void showToast(String message){
        View toastView = getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast));
        TextView textView = (TextView)toastView.findViewById(R.id.toast);
        textView.setText(message);

        Toast toast = new Toast(signup.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER_HORIZONTAL ,0,450);
        toast.show();
    }
}
