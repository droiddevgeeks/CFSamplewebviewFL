package com.example.cfsamplewebviewfl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import android.webkit.JavascriptInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CfJsBridge {
    private final Context context;

    public CfJsBridge(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public String getAppList(String upiScheme, String mandateScheme) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(upiScheme));
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resInfo = pm.queryIntentActivities(intent, 0);
        JSONArray packageNames = new JSONArray();
        try {
            for (ResolveInfo info : resInfo) {
                JSONObject appInfo = new JSONObject();
                appInfo.put(
                        "appName",
                        pm.getApplicationLabel(info.activityInfo.applicationInfo).toString()
                );
                appInfo.put("appPackage", info.activityInfo.packageName);
                packageNames.put(appInfo);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return packageNames.toString();
    }

    @JavascriptInterface
    public boolean openApp(String upiClientPackage, String upiURL) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(upiURL));
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resInfo = pm.queryIntentActivities(intent, 0);
        boolean foundPackageFlag = false;
        ResolveInfo upiClientResolveInfo = null;
        for (ResolveInfo info : resInfo) {
            if (info.activityInfo.packageName.equals(upiClientPackage)) {
                foundPackageFlag = true;
                upiClientResolveInfo = info;
                break;
            }
        }
        try {
            if (foundPackageFlag) {
                intent.setClassName(
                        upiClientResolveInfo.activityInfo.packageName,
                        upiClientResolveInfo.activityInfo.name
                );
                if (context instanceof MainActivity) {
                    ((MainActivity) context).startActivityForResult(intent, 1000);
                }
            }
        } catch (Exception exception) {
            Log.d("Exception UPI app", exception.getMessage());
        }
        return true;
    }
}