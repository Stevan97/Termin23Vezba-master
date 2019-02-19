package com.example.termin23vezba.async;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class SyncTask extends AsyncTask<Integer, Void, Integer> {

    Context context;
    private String string;

    public SyncTask(Context context, String string){
        this.context = context;
        this.string = string;
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute(integer);

        Intent intent = new Intent("SYNC_DATA");
        intent.putExtra("RESULT_CODE", integer);
        intent.putExtra("COMMENT", string);
        context.sendBroadcast(intent);

    }

}
