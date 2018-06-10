package de.hottenstein.krimirundgang;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class StopDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_detail);

        StopInfo stopInfo = getIntent().getParcelableExtra("stopInfo");
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CollapsingToolbarLayout layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        layout.setTitle(stopInfo.title);

        TextView mStopContentView = (TextView) findViewById(R.id.stopContentText);
        mStopContentView.setText(stopInfo.content);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
