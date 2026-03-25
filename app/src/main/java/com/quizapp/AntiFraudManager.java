package com.quizapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AntiFraudManager {

    public static final int REQUEST_CODE_PERMISSIONS = 1001;
    private static final String[] REQUIRED_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO
    };

    /**
     * Empêche les captures d'écran et l'enregistrement d'écran.
     */
    public static void preventScreenshots(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * Vérifie si toutes les permissions nécessaires sont accordées.
     */
    public static boolean hasPermissions(Context context) {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * Demande les permissions Caméra et Micro.
     */
    public static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
    }

    /**
     * Alerte si l'utilisateur tente de quitter l'application.
     */
    public static void detectExit(Context context) {
        Toast.makeText(context, "ALERTE FRAUDE : Tentative de sortie détectée !", Toast.LENGTH_SHORT).show();
    }
}