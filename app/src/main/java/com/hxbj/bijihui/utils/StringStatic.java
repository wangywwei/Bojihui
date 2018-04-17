package com.hxbj.bijihui.utils;

import android.os.Environment;

public class StringStatic {
    /** SD卡根目录 */
    public static final String ROOTPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    /**不随程序卸载的文件夹 */
    public static final String DIR_LONG_CACHE = ROOTPATH + "/wulinfeng";

    public static String DIYICI="diyici";
    public static String ISDENGLU="isdenglu";
    public static String FILEPATH="filePath";

    public static String IS_CHECK_VERSION="isCheckVersion";//是否提醒升级

    /** 更新文件 */
    public static final String DIR_UPDATE_APK = DIR_LONG_CACHE+"/update_file";
}
