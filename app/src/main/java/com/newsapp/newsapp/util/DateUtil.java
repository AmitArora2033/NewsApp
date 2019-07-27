package com.newsapp.newsapp.util;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

  public static final String SERVER_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss'Z'";
  public static final String READABLE_DATE = "dd-MM-yyyy";
  private static final String TAG = DateUtil.class.getSimpleName();

  public static long parseDate(String date) throws ParseException {
    SimpleDateFormat dateParser = new SimpleDateFormat(SERVER_DATE_TIME, Locale.ENGLISH);
    try {
      return dateParser.parse(date).getTime();
    } catch (ParseException e) {
      Log.e(TAG, e.toString());
      throw e;
    }
  }

  public static String formaReadableDate(long l) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(READABLE_DATE, Locale.ENGLISH);
    if (l > 0) {
      return simpleDateFormat.format(new Date(l));
    } else {
      return "";
    }
  }
}
