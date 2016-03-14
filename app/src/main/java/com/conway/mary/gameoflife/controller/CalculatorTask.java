package com.conway.mary.gameoflife.controller;

import android.os.AsyncTask;
import android.os.Handler;

import com.conway.mary.gameoflife.MainActivity;

/**
 * Created by mary on 2/28/2016.
 */
public class CalculatorTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... params) {
        final int INTERVAL = 100;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, INTERVAL);

                MainActivity.sGameModel.next();
                publishProgress();
            }
        }, INTERVAL);

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate();
        if (GameAdapter.sGameAdapter != null && MainActivity.sGameModel != null
                && MainActivity.sGameModel.getCells() != null) {
            GameAdapter.sGameAdapter.setDataSet(MainActivity.sGameModel.getCells());
            GameAdapter.sGameAdapter.notifyDataSetChanged();
        }
    }

}
