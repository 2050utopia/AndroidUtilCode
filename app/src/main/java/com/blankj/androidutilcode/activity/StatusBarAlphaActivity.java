package com.blankj.androidutilcode.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.androidutilcode.R;
import com.blankj.androidutilcode.base.BaseBackActivity;
import com.blankj.utilcode.util.BarUtils;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2017/05/27
 *     desc  : Bar工具类Demo
 * </pre>
 */
public class StatusBarAlphaActivity extends BaseBackActivity {

    private int mAlpha;

    private TextView mTvStatusAlpha;
    private SeekBar  sbChangeAlpha;

    public static void start(Context context) {
        Intent starter = new Intent(context, StatusBarAlphaActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {
        mAlpha = 112;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_status_bar_alpha;
    }

    @Override
    public void initView(Bundle savedInstanceState, View view) {
        findViewById(R.id.btn_set_transparent).setOnClickListener(this);
        mTvStatusAlpha = (TextView) findViewById(R.id.tv_status_alpha);
        sbChangeAlpha = (SeekBar) findViewById(R.id.sb_change_alpha);
        sbChangeAlpha.setOnSeekBarChangeListener(translucentListener);
        mTvStatusAlpha.setText(String.valueOf(mAlpha));

        updateStatusBar();
    }

    @Override
    public void doBusiness(Context context) {

    }

    @Override
    public void onWidgetClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set_transparent:
                sbChangeAlpha.setProgress(0);
                break;
        }
    }

    private SeekBar.OnSeekBarChangeListener translucentListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            mAlpha = progress;
            mTvStatusAlpha.setText(String.valueOf(mAlpha));
            updateStatusBar();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateStatusBar() {
        BarUtils.setStatusBarAlpha(StatusBarAlphaActivity.this, mAlpha, mTvStatusAlpha);
    }
}
