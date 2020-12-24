package com.tuya.smart.android.demo.device;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuya.smart.android.demo.R;
import com.tuya.smart.android.demo.base.activity.BaseActivity;
import com.tuya.smart.android.demo.base.bean.ColorBean;
import com.tuya.smart.android.demo.base.presenter.LampPresenter;
import com.tuya.smart.android.demo.base.utils.AnimationUtil;
import com.tuya.smart.android.demo.base.view.ILampView;
import com.tuya.smart.android.demo.base.widget.ColorPicker;
import com.tuya.smart.android.demo.base.widget.LampView;
import com.tuya.smart.sdk.centralcontrol.api.bean.LightDataPoint;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LampActivity extends BaseActivity implements ILampView {

    private static final String TAG = "LampActivity";
    protected LampPresenter mLampPresenter;

    @BindView(R.id.picker)
    public LampView mLampView;

    @BindView(R.id.iv_lamp_close)
    public ImageView mLampSwitchButton;

    @BindView(R.id.iv_lamp_light)
    public ImageView mLampCloseLight;

    @BindView(R.id.tv_lamp_operationTip)
    public TextView mLampViewTip;

    @BindView(R.id.tv_lamp_color_mode)
    public TextView mLampModeViewTip;


    @BindView(R.id.fl_lamp_white_operation)
    public View mOperationView;

    @BindView(R.id.scene1)
    public Button mLampSwitchButton1;

    @BindView(R.id.scene2)
    public Button mLampSwitchButton2;

    @BindView(R.id.scene3)
    public Button mLampSwitchButton3;

    @BindView(R.id.scene4)
    public Button mLampSwitchButton4;

    @BindView(R.id.scene5)
    public Button mLampSwitchButton5;

    @BindView(R.id.scene6)
    public Button mLampSwitchButton6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamp);

        initToolbar();
        initMenu();
        initView();
        initPresenter();
    }

    private void initView() {
        ButterKnife.bind(this);
    }

    protected void initPresenter() {
        mLampPresenter = new LampPresenter(this, this, getIntent().getStringExtra(SwitchActivity.INTENT_DEVID));
    }

    protected void initMenu() {
        setDisplayHomeAsUpEnabled();
    }

    @Override
    public void showLampView() {
        if (mLampView.getVisibility() != View.VISIBLE) {
            mLampView.setVisibility(View.VISIBLE);
        }
        mLampSwitchButton.setImageResource(R.drawable.transpant_bg);
        if (mLampCloseLight.getVisibility() != View.GONE) {
            mLampCloseLight.setVisibility(View.GONE);
        }
        if (mLampModeViewTip.getVisibility() != View.GONE) {
            mLampModeViewTip.setVisibility(View.GONE);
        }
        if (mLampViewTip.getVisibility() != View.VISIBLE) {
            mLampViewTip.setVisibility(View.VISIBLE);
        }
        if (mLampSwitchButton1.getVisibility() != View.VISIBLE) {
            mLampSwitchButton1.setVisibility(View.VISIBLE);
            mLampSwitchButton2.setVisibility(View.VISIBLE);
            mLampSwitchButton3.setVisibility(View.VISIBLE);
            mLampSwitchButton4.setVisibility(View.VISIBLE);
            mLampSwitchButton5.setVisibility(View.VISIBLE);
            mLampSwitchButton6.setVisibility(View.VISIBLE);
        }
        mLampViewTip.setText(R.string.lamp_close_tip);
    }

    @Override
    public void hideLampView() {
        if (mLampView.getVisibility() != View.GONE) {
            mLampView.setVisibility(View.GONE);
        }
        mLampSwitchButton.setImageResource(R.drawable.ty_lamp_wick_line);
        mLampCloseLight.setVisibility(View.VISIBLE);
        mLampViewTip.setVisibility(View.VISIBLE);
        mLampViewTip.setText(R.string.lamp_open_tip);
        mLampSwitchButton1.setVisibility(View.GONE);
        mLampSwitchButton2.setVisibility(View.GONE);
        mLampSwitchButton3.setVisibility(View.GONE);
        mLampSwitchButton4.setVisibility(View.GONE);
        mLampSwitchButton5.setVisibility(View.GONE);
        mLampSwitchButton6.setVisibility(View.GONE);
        if (mLampModeViewTip.getVisibility() != View.GONE) {
            mLampModeViewTip.setVisibility(View.GONE);
        }
        mLampPresenter.hideOperation();
        mOperationView.setVisibility(View.GONE);
    }


    @Override
    public int getLampColor() {
        return mLampView.getColor();
    }

    @Override
    public int getLampOriginalColor() {
        return mLampView.getOriginalColor();
    }

    @Override
    public void setLampColor(int color) {
        mLampView.setColor(color);
    }

    @Override
    public void setLampColorWithNoMove(int color) {
        mLampView.setColorWithNoAngle(color, ColorPicker.S_RESPONING);
    }

    @Override
    public void showOperationView() {
        mOperationView.setVisibility(View.VISIBLE);
        AnimationUtil.translateView(mOperationView, Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 0f, 300, false, null);
    }


    @Override
    public void sendLampColor(ColorBean bean) {
        mLampPresenter.syncColorToLamp(bean);
    }

    @OnClick(R.id.scene1)
    public void scene1() {
        mLampPresenter.onClickLampSwitch();
    }

    @OnClick(R.id.scene2)
    public void scene2() {
        mLampPresenter.syncColorToLampWhite() ;
    }

    @OnClick(R.id.scene3)
    public void scene3() {
        mLampPresenter.syncColorWork();
    }

    @OnClick(R.id.scene4)
    public void scene4() { mLampPresenter.syncColorScene(); }

    @OnClick(R.id.scene5)
    public void scene5() { mLampPresenter.syncColorScene5(); }

    @OnClick(R.id.scene6)
    public void scene6() { mLampPresenter.syncColorScene6(); }

    @OnClick(R.id.iv_lamp_close)
    public void onLampClick() {
        mLampPresenter.onClickLampSwitch();
    }

    @OnClick(R.id.ll_lamp_bottom_operation)
    public void onClickArrawUp() {
        AnimationUtil.translateView(mOperationView, Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f, 300, false, new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mOperationView.setVisibility(View.GONE);
                    mLampViewTip.setVisibility(View.VISIBLE);
                    mLampPresenter.hideOperation();

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mLampPresenter.onDestroy();
        mLampSwitchButton1.setVisibility(View.GONE);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
