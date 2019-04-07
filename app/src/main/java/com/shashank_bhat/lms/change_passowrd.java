package com.shashank_bhat.lms;


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

public class change_passowrd extends AppCompatActivity {

    Button change;
    public Statement stmt;
    EditText currentPassword,newPassword,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passowrd);

        change = (Button)findViewById(R.id.change);
        currentPassword = (EditText)findViewById(R.id.currentPassword);
        newPassword = (EditText)findViewById(R.id.newPassword);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Connection con = ConnectionClass.CON();
                    if (con == null ||currentPassword.getText().toString().matches("")||newPassword.getText().toString().matches("")||confirmPassword.getText().toString().matches("")) {
                        if(con == null) {
                            showToast("Network error");
                        }
                        else if(currentPassword.getText().toString().matches("")||newPassword.getText().toString().matches("")||confirmPassword.getText().toString().matches("")){
                            showToast("Some fields are Empty");
                        }
                    }else if(currentPassword.getText().toString().matches(newPassword.getText().toString())){
                        showToast("Current Password and New Password should not Match!");
                    }
                    else if(!newPassword.getText().toString().matches(confirmPassword.getText().toString())){
                        showToast("New Password and Confirm Password should Match!");
                    }
                    else {
                        stmt = con.createStatement();
                        String query = "update borrower set Password = '"+newPassword.getText().toString()+"' where Br_Id ='"+home_page.br_id+"';";
                        stmt.execute(query);
                        showToast("Password Changed!!");
                        finish();
                        con.close();
                    }
                }
                catch (Exception ex)
                {
                    showToast(ex.toString());
                }
            }
        });
    }
    public void showToast(String message){


        View toastView = getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast));
        TextView textView = (TextView)toastView.findViewById(R.id.toast);
        textView.setText(message);

        Toast toast = new Toast(change_passowrd.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER_HORIZONTAL ,0,450);
        toast.show();
    }
}
