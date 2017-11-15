package com.hzn.easypulllayoutjava;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 列表顶部刷新View
 * <br/>
 * Created by huzn on 2017/8/25.
 */

public class TopRefreshView extends LinearLayout {

    private ImageView ivIcon;
    private ImageView ivRefreshing;
    private TextView tv;

    private static final long ANIMATION_DURATION = 300;
    private RotateAnimation rotateAnimation;

    public TopRefreshView(Context context) {
        this(context, null);
    }

    public TopRefreshView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopRefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_top_refresh, this, true);
        ivIcon = view.findViewById(R.id.iv_icon);
        tv = view.findViewById(R.id.tv);
        ivRefreshing = view.findViewById(R.id.iv_refreshing);

        rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(ANIMATION_DURATION);
        rotateAnimation.setRepeatCount(Animation.INFINITE);

        idle();
    }

    public void idle() {
        tv.setText(getContext().getString(R.string.pull_to_load_history));
        rotateAnimation.cancel();
        ivRefreshing.setAnimation(null);
        ivRefreshing.setVisibility(GONE);
        ivIcon.setVisibility(VISIBLE);
        ivIcon.animate()
                .setInterpolator(new BounceInterpolator())
                .setDuration(ANIMATION_DURATION)
                .rotation(0)
                .start();
    }

    public void ready() {
        tv.setText(getContext().getString(R.string.listview_footer_hint_ready));
        rotateAnimation.cancel();
        ivRefreshing.setAnimation(null);
        ivRefreshing.setVisibility(GONE);
        ivIcon.setVisibility(VISIBLE);
        ivIcon.animate()
                .setInterpolator(new BounceInterpolator())
                .setDuration(300)
                .rotation(180)
                .start();
    }

    public void triggered() {
        tv.setText(getContext().getString(R.string.listview_header_hint_loading));
        ivIcon.setVisibility(GONE);
        ivRefreshing.setVisibility(VISIBLE);
        ivRefreshing.setAnimation(rotateAnimation);
        rotateAnimation.start();
    }
}
