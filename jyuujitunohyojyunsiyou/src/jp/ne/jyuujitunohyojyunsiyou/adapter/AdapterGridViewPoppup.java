package jp.ne.jyuujitunohyojyunsiyou.adapter;

import java.util.List;

import jp.ne.jyuujitunohyojyunsiyou.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Adapter gridview poppup
 * 
 * @author Nhampd
 * 
 */
public class AdapterGridViewPoppup extends BaseAdapter {

	List<Integer> list_icon;
	LayoutInflater inflater;
	Context mContext;

	public AdapterGridViewPoppup(List<Integer> list_icon,
			LayoutInflater inflater, Context mContext) {
		super();
		this.list_icon = list_icon;
		this.inflater = inflater;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_icon.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int pos, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub

		if (v == null) {
			v = inflater.inflate(R.layout.adapter_gridview_poppup, null);

		}

		ImageButton img_btn = (ImageButton) v.findViewById(R.id.img_btn);
		img_btn.setBackgroundResource(list_icon.get(pos));
		img_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(mContext, "positon= " + pos, Toast.LENGTH_SHORT)
						.show();

			}
		});
		return v;
	}

}
