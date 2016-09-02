package himanshugoel.com.imagescrolling;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    CircleIndicatorLayout circleIndicatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.imageScroller);
        circleIndicatorLayout = (CircleIndicatorLayout) findViewById(R.id.lineIndicatorLayout);
        circleIndicatorLayout.setUpWithViewPager(viewPager);
        circleIndicatorLayout.setIndicatorColor("#FFC107");
        circleIndicatorLayout.setIndicatorSize(150);

    }
}
