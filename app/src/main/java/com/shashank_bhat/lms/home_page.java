package com.shashank_bhat.lms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Statement;

public class home_page extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Statement stmt;
    public static String br_id,user_name;

    NavigationView navigationView;
    DrawerLayout drawer;
    Toolbar toolbar;
    TextView username;
    TextView borrower_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        br_id = MainActivity.sharedPreferences.getString("brid","1RNXXCSXXX");
        user_name = MainActivity.sharedPreferences.getString("username","Rob Percivel");


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeaderView= navigationView.getHeaderView(0);
        username =  navHeaderView.findViewById(R.id.username);
        borrower_id =  navHeaderView.findViewById(R.id.borrower_ID);
        username.setText(user_name);
        borrower_id.setText(br_id);

        /*if(MainActivity.sharedPreferences.getString("username","").equals(""))
        {
            try {

                Connection con = ConnectionClass.CON();
                if (con == null ) {
                    showToast("Network error");
                } else {
                    stmt = con.createStatement();
                    String query = "SELECT NAME from BORROWER WHERE Br_Id = '"+brid+"';";
                    ResultSet resultSet = stmt.executeQuery(query);
                    while(resultSet.next()){
                        user = resultSet.getString(1);
                    }

                    MainActivity.editor.putString("username", user);
                    MainActivity.editor.commit();
                    con.close();

                }
            }
            catch (Exception ex)
            {
                showToast("");
            }

        }
        else
            user = MainActivity.sharedPreferences.getString("username","");*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.change_password) {
            Intent intent = new Intent(this, change_passowrd.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.about_us){
            Intent intent = new Intent(this, About_Us.class);
            startActivity(intent);
            return true;

        }else if(id == R.id.logout){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            MainActivity.editor.clear().commit();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.books) {
            Intent intent = new Intent(this, books.class);
            startActivity(intent);

        } else if (id == R.id.my_books) {
            Intent intent = new Intent(this, My_Books.class);
            startActivity(intent);

        } else if (id == R.id.penalty) {


        } else if (id == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,"Hey, download this app!");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.feedback) {
            Intent Email = new Intent(Intent.ACTION_SEND);
            Email.setType("text/email");
            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "shashankbhat1800@gmail.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
            Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
            startActivity(Intent.createChooser(Email, "Send Feedback:"));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showToast(String message){
        View toastView = getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast));
        TextView textView = (TextView)toastView.findViewById(R.id.toast);
        textView.setText(message);

        Toast toast = new Toast(home_page.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER_HORIZONTAL ,0,450);
        toast.show();
    }
}
