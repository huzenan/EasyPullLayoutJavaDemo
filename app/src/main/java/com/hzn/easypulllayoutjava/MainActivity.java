package com.hzn.easypulllayoutjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private EasyPullLayoutJ easyPullLayout;
    private TopRefreshView topRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        lv = (ListView) findViewById(R.id.lv);
        easyPullLayout = (EasyPullLayoutJ) findViewById(R.id.easy_pull_layout);
        topRefreshView = (TopRefreshView) findViewById(R.id.top_refresh_view);

        // ListView
        String[] strs = new String[20];
        for (int i = 0; i < 20; i++)
            strs[i] = "item " + i;
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, strs));

        // EasyPullLayout
        easyPullLayout.addOnPullListenerAdapter(new EasyPullLayoutJ.OnPullListenerAdapter() {
            @Override
            public void onPull(int type, float fraction, boolean changed) {
                if (!changed)
                    return;

                if (type == EasyPullLayoutJ.TYPE_EDGE_TOP) {
                    if (fraction == 1f)
                        topRefreshView.ready();
                    else
                        topRefreshView.idle();
                }
            }

            @Override
            public void onTriggered(int type) {
                if (type == EasyPullLayoutJ.TYPE_EDGE_TOP) {
                    topRefreshView.triggered();
                    doRefresh();
                }
            }
        });

    }

    private void doRefresh() {
        easyPullLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                // load finished
                easyPullLayout.stop();
            }
        }, 2000);
    }
}
