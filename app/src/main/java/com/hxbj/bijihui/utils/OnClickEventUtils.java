package com.hxbj.bijihui.utils;
/**
 * 功能:快速点击控制工具类
 * 
 * zjn
 *
 * 2016-1-4
 */
public class OnClickEventUtils {
	private static long lastClickTime;

	public synchronized static boolean isFastClick() {
		long time = System.currentTimeMillis();
		if (time - lastClickTime < 200) {
			return true;
		}
		lastClickTime = time;
		return false;
	}
}