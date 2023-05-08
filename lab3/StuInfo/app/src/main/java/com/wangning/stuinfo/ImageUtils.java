package com.wangning.stuinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageUtils {
    /**
     * Base64转Bitmap
     *
     * @param base64String base64数据流
     * @return Bitmap 图片
     */
    public static Bitmap base642Bitmap(String base64String) {
        if (null == base64String) throw new NullPointerException();
//        byte[] decode = Base64.decode(base64String.split(",")[1], Base64.DEFAULT);
        byte[] decode = Base64.decode(base64String, Base64.DEFAULT);
        Bitmap mBitmap = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        return mBitmap;
    }

    /**
     * 将 bitmap 转换为 base64
     *
     * @param bitmap bitmap
     * @return base64
     */
    public static String bitmap2Base64(Context context,Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (bitmap == null) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.default_avatar);
        }
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

        return Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
    }
}
