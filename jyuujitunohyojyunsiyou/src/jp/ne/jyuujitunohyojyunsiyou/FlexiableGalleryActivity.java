package jp.ne.jyuujitunohyojyunsiyou;

import java.io.File;

import jp.ne.jyuujitunohyojyunsiyou.adapter.HorizontalListView;
import jp.ne.jyuujitunohyojyunsiyou.untils.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class FlexiableGalleryActivity extends Activity {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private final static int BUTTON1_PAGE_NUM = 0;
	private final static int BUTTON2_PAGE_NUM = 4;
	private final static int BUTTON3_PAGE_NUM = 9;
	private final static int BUTTON4_PAGE_NUM = 14;
	String[] imageUrls;

	private boolean isStopScroll = false;
	DisplayImageOptions options;

	Bitmap thumpImage;
	Drawable d;
	ImageLoaderConfiguration config;
	HorizontalListView scrollView;
	int curPos = 0;
	Button btnFlexibleTabOne;
	Button btnFlexibleTabTwo;
	Button btnFlexibleTabThree;
	Button btnFlexibleTabFour;

	LinearLayout lnBackgroundTabbar;

	ImageButton backButton;

	Intent intent;
	int postionIntent = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flexiable07_image_gallery);

		// get intent
		intent = getIntent();
		postionIntent = intent.getIntExtra(Constants.POSITION_IMAGE, 0);

		imageUrls = Constants.IMAGES;
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		final int height = displaymetrics.heightPixels;
		final int width = displaymetrics.widthPixels;

		/*
		 * Init Image Config of Application
		 */

		File cacheDir = StorageUtils.getCacheDirectory(getApplicationContext());

		config = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.threadPoolSize(5)
				// default
				.threadPriority(Thread.MAX_PRIORITY)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new WeakMemoryCache())
				// default
				.discCache(new UnlimitedDiscCache(cacheDir))
				// default
				.discCacheSize(50 * 1024 * 1024)
				.discCacheFileCount(100)
				.discCacheFileNameGenerator(new HashCodeFileNameGenerator())
				// default
				.imageDownloader(
						new BaseImageDownloader(getApplicationContext())) // default
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
				.build();

		imageLoader.init(config);

		scrollView = (HorizontalListView) findViewById(R.id.list_view);
		scrollView.setAdapter(mAdapter);
		// get data xml
		btnFlexibleTabOne = (Button) findViewById(R.id.btnFlexibleOne);
		btnFlexibleTabTwo = (Button) findViewById(R.id.btnFlexibleTwo);
		btnFlexibleTabThree = (Button) findViewById(R.id.btnFlexibleThree);
		btnFlexibleTabFour = (Button) findViewById(R.id.btnFlexibleFour);

		lnBackgroundTabbar = (LinearLayout) findViewById(R.id.lnBackgroundTabbar);

		// show image position
		scrollView.scrollTo(width * postionIntent, 0);
		imageLoader.clearDiscCache();
		imageLoader.clearMemoryCache();
		// end show image

		backButton = (ImageButton) findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		btnFlexibleTabOne.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView
						.scrollTo(width * BUTTON1_PAGE_NUM, 250 * abs(curPos));
				// imageLoader.clearDiscCache();
				// imageLoader.clearMemoryCache();
			}
		});

		btnFlexibleTabTwo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON2_PAGE_NUM, 250 * abs(curPos
						- BUTTON2_PAGE_NUM));
				// imageLoader.clearDiscCache();
				// imageLoader.clearMemoryCache();
			}
		});

		btnFlexibleTabThree.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON3_PAGE_NUM, 250 * abs(curPos
						- BUTTON3_PAGE_NUM));
				// imageLoader.clearDiscCache();
				// imageLoader.clearMemoryCache();
			}
		});

		btnFlexibleTabFour.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON4_PAGE_NUM, 250 * abs(curPos
						- BUTTON4_PAGE_NUM));
				// imageLoader.clearDiscCache();
				// imageLoader.clearMemoryCache();
			}
		});

	}

	/**
	 * BaseAdapter
	 */
	private BaseAdapter mAdapter = new BaseAdapter() {

		@Override
		public int getCount() {
			return imageUrls.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			LayoutInflater inflater = (LayoutInflater) getApplicationContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (convertView == null) {
				convertView = inflater.inflate(
						R.layout.item_natural01_gallery_image, null);
				holder = new ViewHolder();
				holder.Image = (ImageView) convertView.findViewById(R.id.image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();

			}

			options = new DisplayImageOptions.Builder()
					.resetViewBeforeLoading(true)
					.imageScaleType(ImageScaleType.EXACTLY)
					.showImageOnLoading(Constants.IMAGES_THUMPS[position])
					.showImageForEmptyUri(Constants.IMAGES_THUMPS[position])
					.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
					.cacheOnDisc(true).considerExifParams(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();

			if (HorizontalListView.isTooFar) {
				if (HorizontalListView.isReady) {
					imageLoader.displayImage(imageUrls[position], holder.Image,
							options);
					HorizontalListView.isReady = false;
				} else {
					// imageLoader.displayImage("drawable://" +
					// Constants.IMAGES_THUMPS[position], holder.Image,
					// options);
				}
				HorizontalListView.isTooFar = false;
			} else {
				imageLoader.displayImage(imageUrls[position], holder.Image,
						options);
			}

			if (isStopScroll) {
				// imageLoader.destroy();
				// imageLoader = ImageLoader.getInstance();
				// imageLoader.displayImage(imageUrls[position], holder.Image,
				// options);
				Log.d("...........", "..............................Stop");
				isStopScroll = false;
			}

			if (abs(curPos - position) == 2) {
				curPos = (curPos + position) / 2;
			} else {
				curPos = position;
			}

			// Set background color for buttons follow the position
			setColorFollowPos(curPos);
			return convertView;
		}

	};

	static class ViewHolder {
		ImageView Image;

	}

	private int abs(int i) {
		// TODO Auto-generated method stub
		if (i < 0) {
			return -i;
		} else {
			return i;
		}
	}

	/**
	 * Set color follow pos
	 * 
	 * @param pos
	 */
	private void setColorFollowPos(int pos) {
		if (pos < BUTTON2_PAGE_NUM) {
			lnBackgroundTabbar.setBackgroundResource(R.drawable.flexible_syunou_tab);
		} else if (pos >= BUTTON2_PAGE_NUM && pos < BUTTON3_PAGE_NUM) {
			lnBackgroundTabbar.setBackgroundResource(R.drawable.flexible_kitchen_tab);
		} else if (pos >= BUTTON3_PAGE_NUM && pos < BUTTON4_PAGE_NUM) {
			lnBackgroundTabbar.setBackgroundResource(R.drawable.flexible_bath_tab);
		} else if (pos >= BUTTON4_PAGE_NUM ) {
			lnBackgroundTabbar.setBackgroundResource(R.drawable.flexible_window_tab);
		}
	}

}