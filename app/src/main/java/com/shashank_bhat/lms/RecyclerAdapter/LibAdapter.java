package com.shashank_bhat.lms.RecyclerAdapter;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shashank_bhat.lms.R;
import com.shashank_bhat.lms.RecyclerObject.lBooks;

import java.util.ArrayList;


public class LibAdapter extends RecyclerView.Adapter<LibAdapter.ViewHolder> {
    private ArrayList<com.shashank_bhat.lms.RecyclerObject.lBooks> lBooks;

    public LibAdapter(Context context,ArrayList<lBooks> liBooks)
    {
        lBooks = liBooks;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noc,dep_name,dep_id;
        public ViewHolder(@Nullable View itemView){
            super(itemView);

            dep_id = (TextView)itemView.findViewById(R.id.dep_id);
            dep_name = (TextView)itemView.findViewById(R.id.dep_name);
            noc = (TextView)itemView.findViewById(R.id.noc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    @Override
    public LibAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(LibAdapter.ViewHolder viewHolder, int i) {

        viewHolder.dep_id.setTag(lBooks.get(i));
        viewHolder.dep_id.setText(lBooks.get(i).getDep_id());
        viewHolder.dep_name.setText(lBooks.get(i).getDep_name());
        viewHolder.noc.setText(lBooks.get(i).getNoc());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return lBooks.size();
    }
}