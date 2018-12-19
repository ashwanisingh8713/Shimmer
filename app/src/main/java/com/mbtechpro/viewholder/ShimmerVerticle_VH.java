package com.mbtechpro.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.mbtechpro.shimmer.R;

public class ShimmerVerticle_VH extends RecyclerView.ViewHolder {

    ShimmerFrameLayout shimmer_view_container;

    public ShimmerVerticle_VH(View itemView) {
        super(itemView);

        shimmer_view_container = itemView.findViewById(R.id.shimmer_view_container);

        Shimmer.ColorHighlightBuilder builder = new Shimmer.ColorHighlightBuilder();
        builder
                .setBaseColor(itemView.getContext().getResources().getColor(R.color.colorAccent))
                .setHighlightColor(itemView.getContext().getResources().getColor(R.color.colorPrimary))
                .setBaseAlpha(1.0f)
                .setHighlightAlpha(1.0f)
                .setDuration(1500)
                .setTilt(0.5f)
                .setDropoff(0.9f)
                .setRepeatDelay(0)
                .setShape(Shimmer.Shape.RADIAL)
        ;

        shimmer_view_container.setShimmer(builder.build());
    }
}
