package gradle;

public class Dependencies {

  private static final String  LIFECYLE_VERSION ="2.0.0";
  private static final String SUPPORT_LIBRARAY_VERSION="28.0.0";
  private static final String BUTTER_KNIFE_VERSION="10.1.0";
  private static final String DAGGER_VERSION ="2.20";
  private static final String RETROFIT_VERSION ="2.5.0";
  private static final String RX_JAVA_VERSION ="2.1.2";
  private static final String RX_ANDROID_VERSION ="2.1.1";
  private static final String OKHTTP_VERSION ="3.14.2";
  private static final String AUTO_VALUE_VERSION ="1.6.2";
  private static final String RX_BINDING ="3.0.0-alpha2";


  public static final String SUPPORT_LIBRARAY =  "com.android.support:appcompat-v7:"+SUPPORT_LIBRARAY_VERSION;
  public static final String RECYCER_VIEW =  "com.android.support:recyclerview-v7:"+SUPPORT_LIBRARAY_VERSION;
  public static final String DESIGN_SUPPORT =  "com.android.support:design:"+SUPPORT_LIBRARAY_VERSION;
  public static final String CARD_VIEW =  "com.android.support:cardview-v7:"+SUPPORT_LIBRARAY_VERSION;
  public static final String  SUPPORT_ANNOTATION =  "com.android.support:support-annotations:"+SUPPORT_LIBRARAY_VERSION;

  public static final String BUTTER_KNIFE = "com.jakewharton:butterknife:"+BUTTER_KNIFE_VERSION;
  public static final String BUTTER_KNIFE_ANNOTATION =
      "com.jakewharton:butterknife-compiler:"+BUTTER_KNIFE_VERSION;
  public static final String GSON = "com.google.code.gson:gson:2.8.2";
  public static final String DAGGER = "com.google.dagger:dagger-android:"+ DAGGER_VERSION;
  public static final String DAGGER_SUPPORT =
      "com.google.dagger:dagger-android-support:"+ DAGGER_VERSION;
  public static final String DAGGER_ANNOTATION_PROCESSOR =
      "com.google.dagger:dagger-android-processor:"+ DAGGER_VERSION;
  public static final String DAGGER_COMPILER = "com.google.dagger:dagger-compiler:"+ DAGGER_VERSION;
  public static final String GLIDE = "com.github.bumptech.glide:glide:4.9.0";
  public static final String GLIDE_COMPILER = "com.github.bumptech.glide:compiler:4.9.0";
  public static final String  RX_JAVA_ANDROID= "io.reactivex.rxjava2:rxandroid:"+ RX_ANDROID_VERSION;
  public static final String RX_JAVA=   "io.reactivex.rxjava2:rxjava:"+ RX_JAVA_VERSION;
  public static final String RETORIFT =  "com.squareup.retrofit2:retrofit:"+ RETROFIT_VERSION;
  public static final String RETROFIT_CONVERTER =  "com.squareup.retrofit2:converter-gson:"+ RETROFIT_VERSION;
  public static final String OKHTTP =  "com.squareup.okhttp3:logging-interceptor:"+OKHTTP_VERSION;
  public static final String RETROFIT_RX_JAVA_ADAPTER = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:1.0.0";
  public static final String ANDROID_X_LIFECYCLE =  "androidx.lifecycle:lifecycle-extensions:"+LIFECYLE_VERSION;
  public static final String AUTO_VALUE =  "com.google.auto.value:auto-value-annotations:"+AUTO_VALUE_VERSION;
  public static final String AUTO_VALUE_ANNOTATION= "com.google.auto.value:auto-value:"+AUTO_VALUE_VERSION;
  public static final String AUTO_VALUE_ANNOTATION_PARSEL =    "com.ryanharter.auto.value:auto-value-parcel:0.2.5";
  public static final String RX_BINDING_CORE =  "com.jakewharton.rxbinding3:rxbinding-core:"+RX_BINDING;
  public static final String RX_BINDING_APP_COMPAT ="com.jakewharton.rxbinding3:rxbinding-appcompat:"+RX_BINDING;
  public static final String STREAM = "com.annimon:stream:1.2.1";
  public static final String APP_COMPAT = "androidx.appcompat:appcompat:1.1.0-alpha03";
  public static final String AUTOVALUE_PARCEL_COMPILER =
      "com.ryanharter.auto.value:auto-value-parcel:0.2.5";
  public static final String AUTOVALUE_WITH_COMPILER =
      "com.gabrielittner.auto.value:auto-value-with:1.0.0";
}
