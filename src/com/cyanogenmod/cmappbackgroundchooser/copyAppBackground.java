package com.cyanogenmod.cmappbackgroundchooser;

import android.os.Bundle;
import android.os.Environment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import java.io.IOException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.util.Log;

import com.cyanogenmod.cmappbackgroundchooser.R;


public class copyAppBackground extends BroadcastReceiver {

    private static final String TAG = "CMAppBackground-Copy";
    public static final String cpyAppBg = "com.cyanogenmod.cmappbackgroundchooser.COPY_APP_BACKGROUND";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v(TAG, "Action: " + intent.getAction());
        if (intent.getAction().equals(cpyAppBg)) {
            String fileName = intent.getStringExtra("fileNames");
            File fromFile = new File(fileName);
            Log.v(TAG, "file: " + fileName);
            if (fromFile.exists()) {
                try {
                    FileInputStream infile = new FileInputStream(fromFile);
                    FileOutputStream outfile = context.openFileOutput("totalapp_background", Context.MODE_WORLD_READABLE);

                    byte[] buf = new byte[100000];
                    int i = 0;
                    while ((i = infile.read(buf)) != -1) {
                        outfile.write(buf, 0, i);
                    }
                    if (infile != null) infile.close();
                    if (outfile != null) outfile.close();
                } catch (Exception e) {
                    Log.e(TAG, "Error: " + e);
                }
                Toast.makeText(context, R.string.move_ui, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
