package com.conway.mary.gameoflife.controller;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conway.mary.gameoflife.MainActivity;
import com.conway.mary.gameoflife.R;
import com.conway.mary.gameoflife.model.Cell;

/**
 * Created by mary on 2/28/2016.
 */
public class GameAdapter  extends RecyclerView.Adapter<GameAdapter.ViewHolder> {
    Cell[] mCells;
    static GameAdapter sGameAdapter;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    if(MainActivity.sGameModel.isAlive(pos)){
                        MainActivity.sGameModel.makeDead(pos);
                    }
                    else
                        MainActivity.sGameModel.makeAlive(pos);
                    sGameAdapter.setDataSet(MainActivity.sGameModel.getCells());

                    sGameAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public GameAdapter(Cell[] cells) {
        mCells = cells;
        sGameAdapter = this;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GameAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
//         create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if(MainActivity.sGameModel.isAlive(position))
            holder.mView.setBackgroundColor(Color.BLACK);
        else
            holder.mView.setBackgroundColor(Color.WHITE);
        holder.mView.setTag(position);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCells.length;
    }

    public void setDataSet(Cell[] cells){
        mCells = cells;
    }
}
