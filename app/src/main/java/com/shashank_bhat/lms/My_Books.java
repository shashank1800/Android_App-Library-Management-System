package com.shashank_bhat.lms;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank_bhat.lms.RecyclerAdapter.MyAdapter;
import com.shashank_bhat.lms.RecyclerObject.mBooks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class My_Books extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ProgressBar progressBar;

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__books);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        new DoTheFollowingInBack(){
            @Override
            protected void onPostExecute(ArrayList<mBooks> Books){
                progressBar.setVisibility(View.INVISIBLE);
                mAdapter = new MyAdapter(getApplicationContext(),Books);
                mRecyclerView.setAdapter(mAdapter);
            }
        }.execute();

    }

    public void showToast(String message){
        View toastView = getLayoutInflater().inflate(R.layout.custom_toast,(ViewGroup)findViewById(R.id.custom_toast));
        TextView textView = (TextView)toastView.findViewById(R.id.toast);
        textView.setText(message);

        Toast toast = new Toast(My_Books.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.setGravity(Gravity.CENTER_HORIZONTAL ,0,450);
        toast.show();
    }
}

class DoTheFollowingInBack extends AsyncTask<Void, Void, ArrayList<mBooks>> {
    public Statement stmt;

    @Override
    protected ArrayList<mBooks> doInBackground(Void... strings) {

        Connection con = ConnectionClass.CON();
        ArrayList<String> al = new ArrayList<String>();
        Iterator t ;

        ArrayList<mBooks> mBooks = new ArrayList<mBooks>();

        String br_id = MainActivity.sharedPreferences.getString("brid","");
        if(con!=null){

            try {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT Book1, Book2, Book3 FROM Borrower where Br_Id = '"+br_id+"';");

                while (rs.next()) {
                    al.add(rs.getString("Book1"));
                    al.add(rs.getString("Book2"));
                    al.add(rs.getString("Book3"));
                    }
                t = al.iterator();
                while(t.hasNext()){
                    ResultSet rs1 = stmt.executeQuery("SELECT Book_Name,Author_Name,Publisher,Dep_Id from BOOKS WHERE AccNo ='"+t.next()+"';");

                    while (rs1.next()) {
                        String book_name = rs1.getString("Book_Name");
                        String Author_Name = rs1.getString("Author_Name");
                        String Publisher = rs1.getString("Publisher");
                        String Dep_Id = rs1.getString("Dep_Id");
                        mBooks.add(new mBooks(book_name,Author_Name,Publisher,Dep_Id));
                    }
                }
                con.close();
                return mBooks;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            mBooks.add(new mBooks("Book Name","Author_Name","Publisher","Dep_Id"));
            mBooks.add(new mBooks("Book Name","Author_Name","Publisher","Dep_Id"));
            mBooks.add(new mBooks("Book Name","Author_Name","Publisher","Dep_Id"));
            return mBooks;
        }
        return null;
    }

}

