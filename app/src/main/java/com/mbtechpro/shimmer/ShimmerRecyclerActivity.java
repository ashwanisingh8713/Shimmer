package com.mbtechpro.shimmer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mbtechpro.adapter.RecyclerShimmerAdapter;


public class ShimmerRecyclerActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shimmer_recycler);

        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerShimmerAdapter adapter = new RecyclerShimmerAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutFrozen(true);


    }
}
