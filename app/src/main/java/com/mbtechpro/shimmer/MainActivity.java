package com.mbtechpro.shimmer;


import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ShimmerFrameLayout shimmerViewContainer;
    ArrayList<Button> presetButtons = new ArrayList<>();
    int currentPreset = -1;
    Toast toast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        shimmerViewContainer = findViewById(R.id.shimmer_view_container);

        presetButtons.add((Button)findViewById(R.id.preset_button0));
        presetButtons.add((Button)findViewById(R.id.preset_button1));
        presetButtons.add((Button)findViewById(R.id.preset_button2));
        presetButtons.add((Button)findViewById(R.id.preset_button3));
        presetButtons.add((Button)findViewById(R.id.preset_button4));
        presetButtons.add((Button)findViewById(R.id.preset_button5));

        for (Button btn :presetButtons){
            btn.setOnClickListener(this);
        }

        selectPreset(0, false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        shimmerViewContainer.startShimmer();
    }

    @Override
    public void onClick(View view) {
        selectPreset(presetButtons.indexOf((Button)view), true);
    }


    private void selectPreset(int preset, boolean showToast) {

        if(currentPreset == preset) {
            return;
        }

        if(currentPreset >= 0) {
            presetButtons.get(currentPreset).setBackgroundResource(R.color.preset_button_background);
        }

        currentPreset = preset;
        presetButtons.get(currentPreset).setBackgroundResource(R.color.preset_button_background_selected);

        if(toast != null) {
            toast.cancel();
        }

        Shimmer.ColorHighlightBuilder shimmerBuilder = new Shimmer.ColorHighlightBuilder();

        Shimmer shimmer = null;

        switch (preset) {
            case 1:
                // Slow and reverse
                toast = Toast.makeText(this, "Slow and reverse", Toast.LENGTH_SHORT);
                shimmer = shimmerBuilder.setDuration(5000L).setRepeatMode(ValueAnimator.REVERSE).build();
                break;
            case 2:
                // Thin, straight and transparent
                toast = Toast.makeText(this, "Thin, straight and transparent", Toast.LENGTH_SHORT);
//                shimmer = shimmerBuilder.setBaseAlpha(0.1f).setDropoff(0.1f).setTilt(0f).build();
                shimmer = shimmerBuilder
                        .setBaseAlpha(1.0f)
                        .setHighlightAlpha(0.5f)
                        .setDropoff(0.9f)
                        .setTilt(1.0f)
                        .setDuration(2000)
                        .setHighlightColor(getResources().getColor(R.color.colorAccent))
                        .setBaseColor(getResources().getColor(R.color.colorPrimary))
                        .setShape(Shimmer.Shape.LINEAR)

//                        .setIntensity(0.15f)
                        .setClipToChildren(true)
                        .build();

                break;

            case 3:
                // Sweep angle 90
                toast = Toast.makeText(this, "Sweep angle 90", Toast.LENGTH_SHORT);
                shimmer = shimmerBuilder.setDirection(Shimmer.Direction.TOP_TO_BOTTOM).setTilt(0f).build();
                break;

            case 4:
                // Spotlight
                toast = Toast.makeText(this, "Spotlight", Toast.LENGTH_SHORT);
                shimmer = shimmerBuilder.setBaseAlpha(0f)
                        .setDuration(2000L)
                        .setDropoff(0.1f)
                        .setIntensity(0.35f)
                        .setShape(Shimmer.Shape.RADIAL).build();
                break;

            case 5:
                // Off
                toast = Toast.makeText(this, "Off", Toast.LENGTH_SHORT);
                shimmer = null;
                break;

                default:
                    toast = Toast.makeText(this, "Default", Toast.LENGTH_SHORT);
                    shimmerBuilder.build();
        }

        shimmerViewContainer.setShimmer(shimmer);

        // Show toast describing the chosen preset, if necessary
        if (showToast && toast != null) {
            toast.show();
        }
    }

}
