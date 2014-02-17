package jp.ne.jyuujitunohyojyunsiyou.log;

import android.util.Log;

/**
 * Class process show log function
 * @author Nhampd
 *
 */
public class LogCatProcess {
	/**
	 * Show, hide log function, if equal true show log, else not show log
	 */
	private boolean bCheckShowLog= true;
	/**
	 * Constructor
	 */
	public LogCatProcess(){
		
	}
	/**
	 * Log error
	 * @param tag
	 * @param message
	 */
	public void logE(String tag, String message){
		if (bCheckShowLog) {
			Log.e(tag, message);
		}
	}
	/**
	 * Log debug
	 * @param tag
	 * @param message
	 */
	public void logD(String tag, String message){
		if (bCheckShowLog) {
			Log.d(tag, message);
		}
	}
	/**
	 * Log warning
	 * @param tag
	 * @param message
	 */
	public void logW(String tag, String message){
		if (bCheckShowLog) {
			Log.w(tag, message);
		}
	}
	/**
	 * Log information
	 * @param tag
	 * @param message
	 */
	public void logI(String tag, String message){
		if (bCheckShowLog) {
			Log.i(tag, message);
		}
	}
}
