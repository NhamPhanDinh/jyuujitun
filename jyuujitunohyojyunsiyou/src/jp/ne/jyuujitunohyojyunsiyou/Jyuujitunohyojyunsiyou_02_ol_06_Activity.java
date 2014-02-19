package jp.ne.jyuujitunohyojyunsiyou;

import java.util.ArrayList;
import java.util.List;

import jp.ne.jyuujitunohyojyunsiyou.adapter.AdapterGridViewPoppup;
import jp.ne.jyuujitunohyojyunsiyou.untils.ApplicationUntils;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Comfortable class
 * 
 * @author Nhampd
 * 
 */
public class Jyuujitunohyojyunsiyou_02_ol_06_Activity extends Activity
		implements OnClickListener {
	ImageButton btn_back, btn_showtab, btn_bottom, btnComfortableOne,
			btnComfortableTwo, btnComfortableThree, btnComfortableFour;
	TabHost mTabHost;
	LinearLayout layout;

	GridView mGridView;

	TextView mTextView;
	List<Integer> list_icon = new ArrayList<Integer>();

	// layout is open or not
	boolean isOpen = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jyuujitunohyojyunsiyou_02_ol_06);
		ini();
		// get data from XML
		mGridView = (GridView) findViewById(R.id.mGridView);
		mTextView = (TextView) findViewById(R.id.mTextView);
		// fill demo data
		for (int i = 0; i < 20; i++) {
			list_icon.add(R.drawable.item_grid);
		}
		// fill gridview
		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

		AdapterGridViewPoppup adapter = new AdapterGridViewPoppup(list_icon,
				inflater, Jyuujitunohyojyunsiyou_02_ol_06_Activity.this,
				Jyuujitunohyojyunsiyou_02_ol_06_Activity.this);

		mGridView.setAdapter(adapter);
	}

	/**
	 * Create layout
	 */
	public void ini() {
		btn_back = (ImageButton) findViewById(R.id.btn_back);
		btn_back.setOnClickListener(this);
		btnComfortableOne = (ImageButton) findViewById(R.id.btn_comfortable_navigation_one);
		btnComfortableOne.setOnClickListener(this);
		btnComfortableTwo = (ImageButton) findViewById(R.id.btn_comfortable_navigation_two);
		btnComfortableTwo.setOnClickListener(this);
		btnComfortableThree = (ImageButton) findViewById(R.id.btn_comfortable_navigation_three);
		btnComfortableThree.setOnClickListener(this);
		btnComfortableFour = (ImageButton) findViewById(R.id.btn_comfortable_navigation_four);
		btnComfortableFour.setOnClickListener(this);

		btn_showtab = (ImageButton) findViewById(R.id.btn_showtab);
		btn_showtab.setOnClickListener(this);
		layout = (LinearLayout) findViewById(R.id.layout);
	}

	/**
	 * Click button events
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_showtab:
			if (isOpen == false) {
				layout.setVisibility(View.VISIBLE);
				isOpen = true;
			} else {
				layout.setVisibility(View.INVISIBLE);
				isOpen = false;
			}
			break;
		case R.id.btn_back:
			finish();
			break;
		case R.id.btn_comfortable_navigation_one:
			// test move class
			ApplicationUntils.gotoActivity(
					Jyuujitunohyojyunsiyou_02_ol_06_Activity.this,
					ComfortableGalleryActivity.class, 0);
			break;
		case R.id.btn_comfortable_navigation_two:
			// test move class
			ApplicationUntils.gotoActivity(
					Jyuujitunohyojyunsiyou_02_ol_06_Activity.this,
					ComfortableGalleryActivity.class, 4);
			break;
		case R.id.btn_comfortable_navigation_three:
			// test move class
			ApplicationUntils.gotoActivity(
					Jyuujitunohyojyunsiyou_02_ol_06_Activity.this,
					ComfortableGalleryActivity.class, 9);
			break;
		case R.id.btn_comfortable_navigation_four:
			// test move class
			ApplicationUntils.gotoActivity(
					Jyuujitunohyojyunsiyou_02_ol_06_Activity.this,
					ComfortableGalleryActivity.class, 14);
			break;
		}
	}

}
