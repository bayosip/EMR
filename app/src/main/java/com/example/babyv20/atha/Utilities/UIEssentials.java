package com.example.babyv20.atha.Utilities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public class UIEssentials {

    private static Handler uiHandler;

    static {
        uiHandler = new Handler(Looper.getMainLooper());
    }


    public static void  message (Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static Handler getHandler(){
        return uiHandler;
    }

    public static void alerDialog (int sqlID, Context context){
        String alertMessage;
        switch (sqlID){
            case 0:
                alertMessage = "Patient information not saved, Try Again!!";
                break;
            default:
                alertMessage = "Patient information successful saved!";
                break;
        }

        new AlertDialog.Builder(context)
                .setTitle("Patient Alert!")
                .setMessage(alertMessage)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })

                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
