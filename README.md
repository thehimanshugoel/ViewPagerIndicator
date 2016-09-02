# ViewPager Indicators
Indicators that are compatible with ViewPager 

![alt tag](https://s11.postimg.io/msfa5ki1v/View_Pager_Indicator_Image.png)

##Download
Grab via gradle:

```compile 'com.himanshugoel.viewpagerindicator:viewpagerindicator:0.0.1'```

or Maven:
```<dependency>
  <groupId>com.himanshugoel.viewpagerindicator</groupId>
  <artifactId>viewpagerindicator</artifactId>
  <version>0.0.1</version>
  <type>pom</type>
</dependency>
 ```


##How To Use

###Steps

- Step 1 : Add Indicator Layout  to your xml layout

### For Circle Indicator use :
```
         <himanshugoel.com.viewpagerindicator.CircleIndicatorLayout
        android:id="@+id/circle"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:layout_alignParentBottom="true"
        android:gravity="center" />
```

### For Line Indicator use : 
```
         <himanshugoel.com.viewpagerindicator.LineIndicatorLayout
        android:id="@+id/l"
        android:layout_width="match_parent"
        android:layout_height="40dp"
        android:layout_alignParentBottom="true"
        android:gravity="center" />
```



- Step 2 : Call method 
            ```setUpWithViewPager(viewPager)```
            This method will require an object of viewPager
			


#Example

```
public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    TextView txtView;
    CircleIndicatorLayout circleIndicatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        circleIndicatorLayout = (CircleIndicatorLayout) findViewById(R.id.circle);
        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        circleIndicatorLayout.setUpWithViewPager(viewPager);
        circleIndicatorLayout.setIndicatorSize(70);
        circleIndicatorLayout.setIndicatorColor("#FFFFFF");
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
				txtView.setText("ViewPager 2");
                }
                
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private class ViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 3;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.on_boarding_screen, container, false);
            txtView = (TextView) view.findViewById(R.id.textView);
            container.addView(view);
            return view;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((View) object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

```






