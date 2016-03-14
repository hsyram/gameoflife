package com.conway.mary.gameoflife;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.conway.mary.gameoflife.controller.GameAdapter;
import com.conway.mary.gameoflife.model.GameModel;
import com.conway.mary.gameoflife.util.Constant;

public class MainActivity extends AppCompatActivity {
    public static final String TAG_PLAY = "play";
    public static final String TAG_STOP = "pause";
    private RecyclerView mRecyclerView;
    private GameAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button mBtnAction;
    public static GameModel sGameModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnAction = (Button) findViewById(R.id.btn_action);
        mBtnAction.setTag(TAG_PLAY);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new GridLayoutManager(this, Constant.DIMEN);
        mRecyclerView.setLayoutManager(mLayoutManager);
        sGameModel = new GameModel(Constant.DIMEN, Constant.DIMEN);

        // specify an adapter (see also next example)
        mAdapter = new GameAdapter(sGameModel.getCells());
        mRecyclerView.setAdapter(mAdapter);

        mBtnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAction((String) v.getTag());
            }
        });
    }
    private void clickAction(String action){
        if(action.equals(TAG_PLAY)){
            mBtnAction.setTag(TAG_STOP);
            mBtnAction.setText(R.string.stop);
            start();
        }else {
            mBtnAction.setTag(TAG_PLAY);
            mBtnAction.setText(R.string.stop);
        }
    }

    private void start(){
        final int INTERVAL = 100;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mBtnAction.getTag().equals(TAG_PLAY)){
                    sGameModel.reset();
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                handler.postDelayed(this, INTERVAL);
                sGameModel.next();
                mAdapter.setDataSet(sGameModel.getCells());
                mAdapter.notifyDataSetChanged();
            }
        }, INTERVAL);
    }
}
