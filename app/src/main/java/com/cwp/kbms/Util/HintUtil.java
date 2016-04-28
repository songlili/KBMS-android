package com.cwp.kbms.Util;

import android.content.Context;
import android.widget.Toast;


/**
 * Created by 曹伟鹏 on 2016/4/26.
 */
public class HintUtil {

    public static void alert(Context context, String string) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).show();
    }
}
