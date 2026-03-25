package com.quizapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
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
     * Vérifie si toutes les permissions nécessaires (Caméra, Micro) sont accordées.
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
     * Demande les permissions à l'utilisateur.
     */
    public static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
    }

    /**
     * Logique simple pour détecter si l'utilisateur quitte l'application (Fraude potentielle).
     */
    public static void onAppPaused(Context context) {
        Toast.makeText(context, "Avertissement : Ne quittez pas l'écran du quiz !", Toast.LENGTH_LONG).show();
        // Ici, vous pourriez aussi envoyer un log vers Supabase ou Firebase pour signaler l'incident.
    }
}