package com.lgkk.base_project.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PermissionUtils {

    public static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };


    private PermissionUtils() {
    }

    public static boolean checkPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (!checkPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermission(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isDeviceInfoGranted(Context context) {
        return checkPermissions(context, PERMISSIONS_STORAGE);
    }


//    public static boolean isDeviceInfoGranted(Context context) {
//        return checkPermission(context, Manifest.permission.READ_PHONE_STATE);
//    }

    public static void requestPermissions(Object obj, int permissionId, String... permissions) {
        if (obj instanceof Fragment) { // --> can't check
//            FragmentCompat.requestPermissions((Fragment) obj, permissions, permissionId);
        } else if (obj instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) obj, permissions, permissionId);
        }
    }

    public static void requestRxPermission(RxPermissions permissions, Activity activity) {

        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
                .subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {

                } else {
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
