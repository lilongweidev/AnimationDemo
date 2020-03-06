package com.llw.animationdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivLogo, ivAlphaLogo, ivTranslateLogo, ivScaleLogo;
    private Button btnShow, btnHide, btnAlphaShow, btnAlphaHide, btnTranslateShow,
            btnTranslateHide, btnScaleShow, btnScaleHide;
    private AlphaAnimation alphaAniShow, alphaAniHide;
    private TranslateAnimation translateAniShow, translateAniHide;
    private Animation bigAnimation, smallAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    //初始化控件
    private void initView() {
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        ivAlphaLogo = (ImageView) findViewById(R.id.iv_alpha_logo);
        ivTranslateLogo = (ImageView) findViewById(R.id.iv_translate_logo);
        ivScaleLogo = (ImageView) findViewById(R.id.iv_scale_logo);
        btnShow = (Button) findViewById(R.id.btn_show);
        btnHide = (Button) findViewById(R.id.btn_hide);
        btnAlphaShow = (Button) findViewById(R.id.btn_alpha_show);
        btnAlphaHide = (Button) findViewById(R.id.btn_alpha_hide);
        btnTranslateShow = (Button) findViewById(R.id.btn_translate_show);
        btnTranslateHide = (Button) findViewById(R.id.btn_translate_hide);
        btnScaleShow = (Button) findViewById(R.id.btn_scale_show);
        btnScaleHide = (Button) findViewById(R.id.btn_scale_hide);


        btnShow.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        btnAlphaShow.setOnClickListener(this);
        btnAlphaHide.setOnClickListener(this);
        btnTranslateShow.setOnClickListener(this);
        btnTranslateHide.setOnClickListener(this);
        btnScaleShow.setOnClickListener(this);
        btnScaleHide.setOnClickListener(this);

        alphaAnimation();

        scaleAnimation();

        translateAnimation();

    }

    //位移动画
    private void translateAnimation() {


        //向上位移显示动画  从自身位置的最下端向上滑动了自身的高度
        translateAniShow = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,//RELATIVE_TO_SELF表示操作自身
                0,//fromXValue表示开始的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示结束的X轴位置
                Animation.RELATIVE_TO_SELF,
                1,//fromXValue表示开始的Y轴位置
                Animation.RELATIVE_TO_SELF,
                0);//fromXValue表示结束的Y轴位置
        translateAniShow.setRepeatMode(Animation.REVERSE);
        translateAniShow.setDuration(1000);

        //向下位移隐藏动画  从自身位置的最上端向下滑动了自身的高度
        translateAniHide = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,//RELATIVE_TO_SELF表示操作自身
                0,//fromXValue表示开始的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示结束的X轴位置
                Animation.RELATIVE_TO_SELF,
                0,//fromXValue表示开始的Y轴位置
                Animation.RELATIVE_TO_SELF,
                1);//fromXValue表示结束的Y轴位置
        translateAniHide.setRepeatMode(Animation.REVERSE);
        translateAniHide.setDuration(1000);
    }

    //缩放动画
    private void scaleAnimation() {
        //放大
        bigAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_big);
        //缩小
        smallAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_small);

    }

    //透明度动画
    private void alphaAnimation() {
        //显示
        alphaAniShow = new AlphaAnimation(0, 1);//百分比透明度，从0%到100%显示
        alphaAniShow.setDuration(1000);//一秒

        //隐藏
        alphaAniHide = new AlphaAnimation(1, 0);
        alphaAniHide.setDuration(1000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show://普通显示
                ivLogo.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_hide://普通隐藏
                ivLogo.setVisibility(View.INVISIBLE);
                break;
            case R.id.btn_alpha_show://透明度显示
                ivAlphaLogo.startAnimation(alphaAniShow);
                ivAlphaLogo.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_alpha_hide://透明度隐藏
                ivAlphaLogo.startAnimation(alphaAniHide);
                //这个地方为什么要做动画的监听呢，因为隐藏和显示不一样，
                //必须在动画结束之后再隐藏你的控件，这样才不会显得很突兀
                alphaAniHide.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ivAlphaLogo.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                break;
            case R.id.btn_scale_show://放大显示
                ivScaleLogo.startAnimation(bigAnimation);
                ivScaleLogo.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_scale_hide://缩小隐藏
                ivScaleLogo.startAnimation(smallAnimation);
                smallAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ivScaleLogo.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
            case R.id.btn_translate_show://向上位移显示
                ivTranslateLogo.startAnimation(translateAniShow);
                ivTranslateLogo.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_translate_hide://向下位移隐藏
                ivTranslateLogo.startAnimation(translateAniHide);
                translateAniHide.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ivTranslateLogo.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                break;
        }
    }
}
