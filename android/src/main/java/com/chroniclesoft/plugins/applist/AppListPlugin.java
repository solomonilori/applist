package com.chroniclesoft.plugins.applist;

import android.content.Context;

import com.getcapacitor.App;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@CapacitorPlugin(name = "Applist")
public class ApplistPlugin extends Plugin {
    private AppLoader mAppLoader;

    @Override
    public void load() {
        // Get the Android context from Capacitor
        Context context = getContext();

        // Create an instance of AppLoader with the context
        mAppLoader = new AppLoader(context);

    }

    @PluginMethod
    public void getInstalledApps(PluginCall call) {
        // Call the getInstalledApps method of the AppLoader instance
        JSONArray appArray =  mAppLoader.getAllLaunchableApps();

        JSObject retVal = new JSObject();
        retVal.put("apps", appArray);

        // Return the app array as a result of the plugin call
        call.resolve(retVal);
    }
}