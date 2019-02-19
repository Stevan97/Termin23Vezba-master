package com.example.termin23vezba.async;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.example.termin23vezba.tools.ReviewerTools;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        int status = intent.getExtras().getInt("STATUS");
        String string = intent.getExtras().getString("EDITTEXT");

        if(status == ReviewerTools.TYPE_WIFI){
            new SyncTask(getApplicationContext(), string).execute(status);
        }else{
            Toast.makeText(getBaseContext(), "Nema konekcije", Toast.LENGTH_SHORT).show();
        }

        stopSelf();
        return START_NOT_STICKY;
    }
}
