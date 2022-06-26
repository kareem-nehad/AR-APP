package database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converter {

    @TypeConverter
    public static byte[] getStringFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        return b;
    }

    @TypeConverter
    public static Bitmap getBitmapFromString(byte[] arr) {
        return BitmapFactory.decodeByteArray(arr, 0, arr.length);
    }
}
