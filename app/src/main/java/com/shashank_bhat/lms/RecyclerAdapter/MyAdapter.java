package com.shashank_bhat.lms.RecyclerAdapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shashank_bhat.lms.R;
import com.shashank_bhat.lms.RecyclerObject.mBooks;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<com.shashank_bhat.lms.RecyclerObject.mBooks> mBooks;

    public MyAdapter(Context context,ArrayList<mBooks> myBooks)
    {
        mBooks = myBooks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book_name,book_author,book_publisher,dep_id;
        public ViewHolder(@Nullable View itemView){
            super(itemView);

            book_name = (TextView)itemView.findViewById(R.id.book_name);
            book_author = (TextView)itemView.findViewById(R.id.book_author);
            book_publisher = (TextView)itemView.findViewById(R.id.book_publisher);
            dep_id = (TextView)itemView.findViewById(R.id.dep_id);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_b_card_layout, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int i) {

        viewHolder.book_name.setTag(mBooks.get(i));
        viewHolder.book_name.setText(mBooks.get(i).getBook_Name());
        viewHolder.book_author.setText(mBooks.get(i).getBook_Author());
        viewHolder.book_publisher.setText(mBooks.get(i).getBook_Publisher());
        viewHolder.dep_id.setText(mBooks.get(i).getDep_Id());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mBooks.size();
    }
}