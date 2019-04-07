package com.shashank_bhat.lms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    public Statement stmt,stmt1;
    private EditText brid;
    private EditText password;
    private Button login;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        brid = (EditText)findViewById(R.id.brid);
        password = (EditText)findViewById(R.id.password);
        login = (Button)findViewById(R.id.login);

        sharedPreferences = this.getSharedPreferences("com.example.lms6", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        if(!sharedPreferences.getString("brid","").matches("")){
            Intent intent = new Intent(this,home_page.class);
            intent.putExtra("brid",sharedPreferences.getString("brid",""));
            startActivity(intent);
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*try {
                    String loginStatus = "Login Successful";
                    Connection con = ConnectionClass.CON();
                    if (con == null || brid.getText().toString().equals("")||password.getText().toString().equals("")) {
                        if(con == null) {
                            loginStatus = "Network Error";
                            Toast.makeText(context,loginStatus, Toast.LENGTH_SHORT).show();
                        }
                        else if(brid.getText().toString().equals("")||password.getText().toString().equals("")){
                            showToast("Some fields are Empty");
                        }
                    } else {

                        stmt = con.createStatement();
                        String query = "Select * from BORROWER where Br_Id ='"+brid.getText().toString()+"' AND Password='" +password.getText().toString()+"';";
                        ResultSet reset = stmt.executeQuery(query);
                        int count=0;
                        while (reset.next()){
                            count++;
                        }
                        if(count>0){
                            editor.putString("brid", brid.getText().toString()).commit();
                            Intent intent = new Intent(context,home_page.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            showToast("Incorrect Borrower Id or Password");
                        }
                        con.close();
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),ex.toString(),Toast.LENGTH_SHORT).show();

                }*/

                editor.putString("brid", "1RNXXCSXXX").commit();
                Intent intent = new Intent(context,home_page.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Register(View view) {
        Intent intent = new Intent(this,signup.class);
        startActivity(intent);
    }
    public void showToast(String message){

        View toastView = getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast));
        TextView textView = (TextView)toastView.findViewById(R.id.toast);
        textView.setText(message);

        Toast toast = new Toast(MainActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER_HORIZONTAL ,0,450);
        toast.show();
    }
}