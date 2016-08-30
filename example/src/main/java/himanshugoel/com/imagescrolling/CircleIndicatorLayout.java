package himanshugoel.com.imagescrolling;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himanshu on 23-08-2016.
 */
public class CircleIndicatorLayout extends LinearLayout {
    public ViewPager viewPager;
    int pageCount;
    Context context;
    List<TextView> circularViewList;
    GradientDrawable strokeShape;
    int previousIndicator = 0;
    private String hexValue = "#000000";

    public CircleIndicatorLayout(Context context) {
        super(context);
        this.context = context;
    }

    public CircleIndicatorLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

    }

    public CircleIndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CircleIndicatorLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setUpWithImageScroller(ViewPager viewPager) {
        circularViewList = new ArrayList<>();
        this.viewPager = viewPager;
        pageCount = viewPager.getAdapter().getCount();
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(pxToDp(100), pxToDp(100));
        llp.setMargins(0, 0, pxToDp(70), 0);

        for (int i = 0; i < pageCount; i++) {
            TextView textView = new TextView(context);
            addView(textView);
            textView.setLayoutParams(llp);
            circularViewList.add(textView);

            setCircularShapeStroke(textView);

            //fill the first circle with color
            setCircularShapeFill(circularViewList.get(0));
            //set the first circle with grow animation
            growAnimation(0);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageSelected(int position) {

                //setting previous indicator animation to shrink and shape to stroke

                setCircularShapeStroke(circularViewList.get(previousIndicator));
                shrinkAnimation(previousIndicator);


                previousIndicator = position;

                //setting current indicator shape to fill and  animation to grow

                setCircularShapeFill(circularViewList.get(position));
                growAnimation(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }

    private void growAnimation(int position) {
        ScaleAnimation grow = new ScaleAnimation(1, 1.5f, 1, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        grow.setFillAfter(true);
        grow.setDuration(300);
        circularViewList.get(position).startAnimation(grow);

    }

    private void shrinkAnimation(int previousIndicator) {
        ScaleAnimation shrink = new ScaleAnimation(1.5f, 1, 1.5f, 1,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        shrink.setFillAfter(true);
        shrink.setDuration(300);
        circularViewList.get(previousIndicator).startAnimation(shrink);
    }

    private void setCircularShapeStroke(TextView textView) {
        strokeShape = new GradientDrawable();
        strokeShape.setShape(GradientDrawable.OVAL);
        strokeShape.setStroke(3, Color.parseColor(hexValue));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            textView.setBackground(strokeShape);
        } else {
            textView.setBackgroundDrawable(strokeShape);
        }
    }


    private void setCircularShapeFill(TextView textView) {
        GradientDrawable bgShape = (GradientDrawable) textView.getBackground();
        bgShape.setColor(Color.parseColor(hexValue));
    }

    private int pxToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public void setIndicatorColor(String hexValue) {
        this.hexValue = hexValue;
        //if this method is called by the user then set the first circle color to the user defined color
        setCircularShapeFill(circularViewList.get(0));

        for (int i = 0; i < circularViewList.size(); i++) {
            GradientDrawable bgShape = (GradientDrawable) circularViewList.get(i).getBackground();
            bgShape.setStroke(3, Color.parseColor(hexValue));
        }
    }

    public void setIndicatorSize(int size) {
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(pxToDp(size), pxToDp(size));
        llp.setMargins(0, 0, pxToDp(70), 0);
        for (int i = 0; i < circularViewList.size(); i++) {
            circularViewList.get(i).setLayoutParams(llp);
        }
    }
}
