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
	 */
	public static void gotoActivity(Context context,Class<?> cls) {
		Intent intent = new Intent(
				context, cls);
		context.startActivity(intent);
		// finish();
	}
}
