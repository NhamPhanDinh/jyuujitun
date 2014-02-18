package jp.ne.jyuujitunohyojyunsiyou.untils;

import android.content.Context;
import android.content.Intent;

/**
 * Class define function use project
 * @author Nhampd
 *
 */
public class ApplicationUntils {
	/**
	 * Go to activity
	 * @param context - Context class
	 * @param cls - class start activiy
	 * @param position - position image
	 */
	public static void gotoActivity(Context context,Class<?> cls, int position) {
		Intent intent = new Intent(
				context, cls);
		intent.putExtra(Constants.POSITION_IMAGE, position);
		context.startActivity(intent);
	}
}
