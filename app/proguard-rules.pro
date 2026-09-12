# R8 / ProGuard — keep what reflection needs; let the rest shrink.

-keepattributes SourceFile,LineNumberTable,*Annotation*,Signature,InnerClasses,EnclosingMethod
-renamesourcefileattribute SourceFile

# Crashlytics / stack traces
-keepattributes RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Gson models (fields used via reflection)
-keep class zw.co.nm.moviedb.data.remote.model.** { <fields>; }
-keep class zw.co.nm.moviedb.data.domain.models.** { <fields>; }
-keep class zw.co.nm.moviedb.data.remote.util.RatedValueDeserializer { *; }
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.JsonAdapter <fields>;
}

# Retrofit service interfaces
-keep,allowobfuscation interface zw.co.nm.moviedb.data.remote.service.** { *; }
-keep,allowobfuscation,allowshrinking class retrofit2.** { *; }
-dontwarn retrofit2.**
-dontwarn okhttp3.**
-dontwarn okio.**

# Kotlin
-dontwarn kotlin.**
-keep class kotlin.Metadata { *; }
-keepclassmembers class **$WhenMappings { <fields>; }
-keepclassmembers class **$compat$* { <methods>; }

# Enums used in Gson / when
-keepclassmembers enum * { *; }

# Parcelable
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# View Binding / generated
-keep class zw.co.nm.moviedb.databinding.** { *; }

# Picasso
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.picasso.** { *; }

# Firebase / Crashlytics / Play
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**
-keep class com.google.android.play.core.** { *; }

# ProcessPhoenix
-keep class com.jakewharton.processphoenix.** { *; }

# BlurView / CircleImageView / YouTube player (reflection / custom views)
-keep class eightbitlab.com.blurview.** { *; }
-keep class de.hdodenhof.circleimageview.** { *; }
-keep class com.pierfrancescosoffritti.androidyoutubeplayer.** { *; }
-dontwarn com.pierfrancescosoffritti.androidyoutubeplayer.**

# Keep Activities / Application entry points (safety with full mode)
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepclassmembers class kotlinx.coroutines.** { volatile <fields>; }
