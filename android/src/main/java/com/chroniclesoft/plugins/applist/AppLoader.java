package com.chroniclesoft.plugin.applist;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AppLoader {
    private Context mContext;
    private PackageManager mPackageManager;

    public AppLoader(Context context) {
        mContext = context;
        mPackageManager = context.getPackageManager();
    }

    public JSONArray getInstalledApps() {
        JSONArray jsonArray = new JSONArray();
        List<ApplicationInfo> apps = mPackageManager.getInstalledApplications(0);

        for (ApplicationInfo app : apps) {
            JSONObject json = new JSONObject();
            try {
                if ((app.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                    continue;
                }else{
                    String appName = (String) app.loadLabel(mPackageManager);
                    String packageName = app.packageName;
                    Drawable appIcon = app.loadIcon(mPackageManager);
                    String appIconUrl = saveIconToFile(appIcon, packageName);

                    // Get the app category
                    String appCategory = ""; //getCategory(app);

                    json.put("appName", appName);
                    json.put("appId", packageName);
                    json.put("iconUrl", appIconUrl);
                    json.put("category", appCategory);

                    jsonArray.put(json);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonArray;
    }

    public JSONArray getAllLaunchableApps() {
        JSONArray jsonArray = new JSONArray();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resovleInfoList = mPackageManager.queryIntentActivities(mainIntent, 0);

        for (ResolveInfo resolveInfo : resovleInfoList) {

            try {
                JSONObject json = new JSONObject();

                String appName = (String) resolveInfo.loadLabel(mPackageManager);
                String packageName = resolveInfo.activityInfo.packageName;
                Drawable appIcon = resolveInfo.loadIcon(mPackageManager);
                String appIconUrl = saveIconToFile(appIcon, packageName);

                // Get the app category
                String appCategory = ""; //getCategory(app);

                json.put("appName", appName);
                json.put("appId", packageName);
                json.put("iconUrl", appIconUrl);
                json.put("category", appCategory);

                jsonArray.put(json);

            }catch (JSONException ex){
                ex.printStackTrace();
            }
        }
        return jsonArray;
    }


    private String saveIconToFile(Drawable iconDrawable, String packageName) {
        Bitmap iconBitmap = drawableToBitmap(iconDrawable);

        File dir = mContext.getDir("app_loader_icons", Context.MODE_PRIVATE);
        File iconFile = new File(dir, packageName + ".png");

        try {
            FileOutputStream fos = new FileOutputStream(iconFile);
            iconBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iconFile.getAbsolutePath();
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    private String getCategory(ApplicationInfo app) {
        String appCategory = "";
        try {
            String category = mPackageManager.getApplicationInfo(app.packageName, PackageManager.GET_META_DATA).metaData.getString("category");
            if (category != null) {
                appCategory = category;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appCategory;
    }

}
