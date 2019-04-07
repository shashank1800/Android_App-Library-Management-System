package com.shashank_bhat.lms;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.shashank_bhat.lms.RecyclerAdapter.LibAdapter;
import com.shashank_bhat.lms.RecyclerObject.lBooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class books extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ProgressBar progressBar;


    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        new DoTheFollowingInBack2(){
            @Override
            protected void onPostExecute(ArrayList<lBooks> lBooks){
                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new LibAdapter(getApplicationContext(),lBooks);
                mRecyclerView.setAdapter(mAdapter);
            }
        }.execute();


    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
class DoTheFollowingInBack2 extends AsyncTask<Void, Void, ArrayList<lBooks>> {
    public Statement stmt,stmt2;


    @Override
    protected ArrayList<lBooks> doInBackground(Void... strings) {

        Connection con = ConnectionClass.CON();
        ArrayList<String> al = new ArrayList<String>();
        Iterator t ;

        ArrayList<lBooks> lBooks= new ArrayList<lBooks>();

        if(con!=null){

            try {
                stmt = con.createStatement();
                stmt2 = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Dep_Id,Dep_Name from DEPARTMENT;");
                while (rs.next()) {
                    int count =0;
                    ResultSet rs1 = stmt2.executeQuery("SELECT AccNo from BOOKS where Dep_Id = '"+(rs.getString("Dep_Id"))+"';");
                    {
                        while (rs1.next()) {
                            count++;
                        }
                    }
                    lBooks.add(new lBooks(rs.getString("Dep_Id"), rs.getString("Dep_Name"), String.valueOf(count)));
                }
                con.close();
                return lBooks;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            lBooks.add(new lBooks("Dep_Id","CSE","125"));
            lBooks.add(new lBooks("Dep_Id","ISE","105"));
            lBooks.add(new lBooks("Dep_Id","ECE","50"));
            return lBooks;
        }
        return null;
    }

}
