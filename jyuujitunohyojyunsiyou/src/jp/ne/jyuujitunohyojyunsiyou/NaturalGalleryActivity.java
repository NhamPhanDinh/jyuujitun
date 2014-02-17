/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package jp.ne.jyuujitunohyojyunsiyou;

import java.io.File;

import jp.ne.jyuujitunohyojyunsiyou.adapter.HorizontalListView;
import jp.ne.jyuujitunohyojyunsiyou.untils.Constants;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class NaturalGalleryActivity extends Activity {
	protected ImageLoader imageLoader = ImageLoader.getInstance();
	private final static int BUTTON1_PAGE_NUM = 0;
	private final static int BUTTON2_PAGE_NUM = 4;
	private final static int BUTTON3_PAGE_NUM = 9;
	private final static int BUTTON4_PAGE_NUM = 14;
	private final static int BUTTON5_PAGE_NUM = 19;
	String[] imageUrls;

	DisplayImageOptions options;

	Bitmap thumpImage;
	Drawable d;
	ImageLoaderConfiguration config;
	HorizontalListView scrollView;
	int curPos = 0;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.natural01_image_gallery);

//		Bundle bundle = getIntent().getExtras();
//		imageUrls = bundle.getStringArray(Extra.IMAGES);
		imageUrls=Constants.IMAGES;
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
				.threadPriority(Thread.NORM_PRIORITY - 1)
				// default
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				// default
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024))
				.memoryCacheSize(2 * 1024 * 1024)
				.memoryCacheSizePercentage(13)
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
				//.writeDebugLogs()//write log
				.build();

		imageLoader.init(config);
		 

		scrollView = (HorizontalListView) findViewById(R.id.list_view);
		scrollView.setAdapter(mAdapter);

		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);

		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON1_PAGE_NUM, 250 * abs(curPos));
				imageLoader.clearDiscCache();
				imageLoader.clearMemoryCache();
			}
		});

		button2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON2_PAGE_NUM, 250 * abs(curPos
						- BUTTON2_PAGE_NUM) );
				imageLoader.clearDiscCache();
				imageLoader.clearMemoryCache();
			}
		});

		button3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON3_PAGE_NUM, 250 * abs(curPos
						- BUTTON3_PAGE_NUM));
				imageLoader.clearDiscCache();
				imageLoader.clearMemoryCache();
			}
		});

		button4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON4_PAGE_NUM, 250 * abs(curPos
						- BUTTON4_PAGE_NUM));
				imageLoader.clearDiscCache();
				imageLoader.clearMemoryCache();
			}
		});

		button5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				scrollView.scrollTo(width * BUTTON5_PAGE_NUM, 250 * abs(curPos
						- BUTTON5_PAGE_NUM));
				imageLoader.clearDiscCache();
				imageLoader.clearMemoryCache();
			}
		});
	}

//	private void startImagePagerActivity(int position) {
//		Intent intent = new Intent(this, ImagePagerActivity.class);
//		intent.putExtra(Extra.IMAGES, imageUrls);
//		intent.putExtra(Extra.IMAGE_POSITION, position);
//		startActivity(intent);
//	}

	/*
	 * private class ImageGalleryAdapter extends BaseAdapter {
	 * 
	 * @Override public int getCount() { return imageUrls.length; }
	 * 
	 * @Override public Object getItem(int position) { return position; }
	 * 
	 * @Override public long getItemId(int position) { return position; }
	 * 
	 * @Override public View getView(int position, View convertView, ViewGroup
	 * parent) { ImageView imageView = (ImageView) convertView; if (imageView ==
	 * null) { imageView = (ImageView) getLayoutInflater().inflate(
	 * R.layout.item_gallery_image, parent, false); }
	 * 
	 * thumpImage = decodeBitmapFromResource(getResources(),
	 * Integer.parseInt(imageUrls[position].replaceAll("[^0-9]+", "")), 50, 50);
	 * d = new BitmapDrawable(getResources(), thumpImage);
	 * 
	 * options = new DisplayImageOptions.Builder()
	 * .showImageOnLoading(Constants.IMAGES_THUMPS[position])
	 * .showImageForEmptyUri(R.drawable.ic_empty)
	 * .showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
	 * .cacheOnDisc(true).considerExifParams(true)
	 * .bitmapConfig(Bitmap.Config.RGB_565).build();
	 * 
	 * imageLoader.displayImage(imageUrls[position], imageView, options);
	 * 
	 * return imageView; } }
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
				convertView = inflater.inflate(R.layout.item_natural01_gallery_image,
						null);
				holder = new ViewHolder();
				holder.Image = (ImageView) convertView.findViewById(R.id.image);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();

			}
			/*
			 * thumpImage = decodeBitmapFromResource(getResources(),
			 * Integer.parseInt(imageUrls[position].replaceAll("[^0-9]+", "")),
			 * 50, 50); d = new BitmapDrawable(getResources(), thumpImage);
			 */

			// holder.Image.setBackgroundResource(dataObjects[position]);
			options = new DisplayImageOptions.Builder()
					.showImageOnLoading(Constants.IMAGES_THUMPS[position])
					.showImageForEmptyUri(Constants.IMAGES_THUMPS[position])
					.showImageOnFail(R.drawable.ic_error).cacheInMemory(true)
					.cacheOnDisc(true).considerExifParams(true)
					.bitmapConfig(Bitmap.Config.RGB_565).build();

			if (HorizontalListView.isTooFar) {
				HorizontalListView.isTooFar = false;
				if (HorizontalListView.isReady) {
					imageLoader.displayImage(imageUrls[position], holder.Image,
							options);
					Log.d("", "Good Job");
					HorizontalListView.isReady = false;
				} else {
					imageLoader.displayImage("drawable://"
							+ Constants.IMAGES_THUMPS[position], holder.Image,
							options);
				}
			} else {
				imageLoader.displayImage(imageUrls[position], holder.Image,
						options);
			}

			// imageLoader.displayImage(imageUrls[position], holder.Image,
			// options);
			/*
			 * imageLoader.loadImage(imageUrls[position], options, new
			 * ImageLoadingListener() {
			 * 
			 * @Override public void onLoadingStarted(String imageUri, View
			 * view) { // TODO Auto-generated method stub //imageLoader.lo }
			 * 
			 * @Override public void onLoadingFailed(String imageUri, View view,
			 * FailReason failReason) { // TODO Auto-generated method stub
			 * 
			 * }
			 * 
			 * @Override public void onLoadingComplete(String imageUri, View
			 * view, Bitmap loadedImage) { // TODO Auto-generated method stub
			 * 
			 * }
			 * 
			 * @Override public void onLoadingCancelled(String imageUri, View
			 * view) { // TODO Auto-generated method stub
			 * 
			 * } });
			 */
			
			
			if(abs(curPos - position) == 2){
				curPos = (curPos + position) / 2;
			}
			else {
				curPos = position;
			}
			
			Log.d("Pos: ", "" + curPos);
			
			// Set background color for buttons follow the position
			setColorFollowPos(curPos);
						
			return convertView;
		}

	};

	static class ViewHolder {
		ImageView Image;

	}

	public static Bitmap decodeBitmapFromResource(Resources res, int resId,
			int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	private int abs(int i) {
		// TODO Auto-generated method stub
		if (i < 0) {
			return -i;
		} else {
			return i;
		}
	}

	private void resetColorAllBtn() {
		button1.setBackgroundResource(R.drawable.background_white);
		button2.setBackgroundResource(R.drawable.background_white);
		button3.setBackgroundResource(R.drawable.background_white);
		button4.setBackgroundResource(R.drawable.background_white);
		button5.setBackgroundResource(R.drawable.background_white);
	}
	
	private void setColorFollowPos(int pos) {
		if(pos < BUTTON2_PAGE_NUM){
			resetColorAllBtn();
			button1.setBackgroundResource(R.drawable.background_brown);
		}
		else if (pos >= BUTTON2_PAGE_NUM && pos < BUTTON3_PAGE_NUM){
			resetColorAllBtn();
			button2.setBackgroundResource(R.drawable.background_brown);
		}
		else if (pos >= BUTTON3_PAGE_NUM && pos < BUTTON4_PAGE_NUM){
			resetColorAllBtn();
			button3.setBackgroundResource(R.drawable.background_brown);
		}
		else if (pos >= BUTTON4_PAGE_NUM && pos < BUTTON5_PAGE_NUM){
			resetColorAllBtn();
			button4.setBackgroundResource(R.drawable.background_brown);
		}
		else if (pos >= BUTTON5_PAGE_NUM){
			resetColorAllBtn();
			button5.setBackgroundResource(R.drawable.background_brown);
		}
	}
}