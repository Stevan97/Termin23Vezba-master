package com.example.termin23vezba.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.termin23vezba.R;
import com.example.termin23vezba.async.MyReceiver;
import com.example.termin23vezba.async.MyService;
import com.example.termin23vezba.tools.ReviewerTools;

public class DialogActivity extends Activity {

    MyReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button btn = findViewById(R.id.btnDialogActivity);
        final EditText editText = findViewById(R.id.editText);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int status = ReviewerTools.getConnectivityStatus(getApplicationContext());
                String string = editText.getText().toString();

                Intent intent = new Intent(DialogActivity.this, MyService.class);
                intent.putExtra("STATUS", status);
                intent.putExtra("EDITTEXT", string);

                startService(intent);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpReceiver();
    }

    private void setUpReceiver(){
        receiver = new MyReceiver();

        IntentFilter intentFilterInternet = new IntentFilter();
        intentFilterInternet.addAction("SYNC_DATA");
        registerReceiver(receiver, intentFilterInternet);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
