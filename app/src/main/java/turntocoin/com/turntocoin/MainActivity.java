package turntocoin.com.turntocoin;

import android.graphics.Color;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements RotateAnimation.InterpolatedTimeListener {
    private TextView[] mTabViews;
    private TextView[] mTextViews;
    private ImageView imgBack;
    private ImageView ivbottom;
    private boolean enableRefresh;
    private int index=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setAlayText(15);
        setAnim(3);
    }
    private void initView(){
        mTabViews = new TextView[7];
        mTextViews=new TextView[7];
        mTabViews[0] = (TextView) findViewById(R.id.tv_add1);
        mTabViews[1] = (TextView) findViewById(R.id.tv_add2);
        mTabViews[2] = (TextView) findViewById(R.id.tv_add3);
        mTabViews[3] = (TextView) findViewById(R.id.tv_add4);
        mTabViews[4] = (TextView) findViewById(R.id.tv_add5);
        mTabViews[5] = (TextView) findViewById(R.id.tv_add6);
        mTabViews[6] = (TextView) findViewById(R.id.tv_add7);
        mTextViews[0]=(TextView)findViewById(R.id.tv_day1);
        mTextViews[1]=(TextView)findViewById(R.id.tv_day2);
        mTextViews[2]=(TextView)findViewById(R.id.tv_day3);
        mTextViews[3]=(TextView)findViewById(R.id.tv_day4);
        mTextViews[4]=(TextView)findViewById(R.id.tv_day5);
        mTextViews[5]=(TextView)findViewById(R.id.tv_day6);
        mTextViews[6]=(TextView)findViewById(R.id.tv_day7);
        imgBack = (ImageView) findViewById(R.id.img_left);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivbottom=(ImageView)findViewById(R.id.ivbottom);
    }
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        try {
            date = formatter.parse(strTime);
        } catch (java.text.ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    private void setAlayText(int dayNum) {
        int m = dayNum / 5;

        for(int n=0;n<7;n++){
            String spantxtyun = "第"  + (n+1) + "天";
            SpannableString styleyun = new SpannableString (
                    spantxtyun);
            mTabViews[n].setBackgroundResource(R.mipmap.img_wqd);
            mTabViews[n].setTextColor(Color.parseColor("#a9a9a9"));
            mTextViews[n].setTextColor(Color.parseColor("#a9a9a9"));
            styleyun.setSpan(new TextAppearanceSpan(this, R.style.styleSignColor), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextViews[n].setText(styleyun);
        }

        for (int n = 0; n < m; n++) {
            String spantxtyun = "第"  + (n+1) + "天";
            SpannableString styleyun = new SpannableString (
                    spantxtyun);
            mTabViews[n].setBackgroundResource(R.mipmap.img_yqd);
            mTabViews[n].setTextColor(Color.parseColor("#c5500a"));
            mTextViews[n].setTextColor(Color.parseColor("#c5500a"));
            styleyun.setSpan(new TextAppearanceSpan(this, R.style.styleSignRedColor), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextViews[n].setText(styleyun);
        }

    }
    private void setAnim(int postion){
        enableRefresh = true;
        RotateAnimation rotateAnim = null;
        float cX = Util.getScreenWidth(this)/12.0f;
        float cY = Util.getScreenWidth(this)/12.0f;
        rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_DECREASE);
        rotateAnim.setDuration(3000);
        if (rotateAnim != null) {
            rotateAnim.setInterpolatedTimeListener(this);
            rotateAnim.setFillAfter(true);
            mTabViews[postion].startAnimation(rotateAnim);
            index=postion;
        }
    }
    @Override
    public void interpolatedTime(float interpolatedTime) {

        if (enableRefresh && interpolatedTime > 0.5f) {

            String spantxtyun = "第"  + (index+1) + "天";
            SpannableString styleyun = new SpannableString (
                    spantxtyun);
            mTabViews[index].setBackgroundResource(R.mipmap.img_yqd);
            mTabViews[index].setTextColor(Color.parseColor("#c5500a"));
            mTextViews[index].setTextColor(Color.parseColor("#c5500a"));
            styleyun.setSpan(new TextAppearanceSpan(this, R.style.styleSignRedColor), 1, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mTextViews[index].setText(styleyun);
            enableRefresh = false;
        }
    }
}
