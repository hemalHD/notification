package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_1;
    Button btn_2;
    Button btn_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_1 = findViewById(R.id.btn1);
        btn_2 = findViewById(R.id.btn2);
        btn_3 = findViewById(R.id.btn3);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "This is notification";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        MainActivity.this
                )
                        .setSmallIcon(R.drawable.ic_baseline_message_24)
                        .setContentTitle("New Notification")
                        .setContentText(str)
                        .setAutoCancel(true);
                Intent intent = new Intent(MainActivity.this,MainActivity.class);

                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",str);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0,builder.build());
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContentProviderActivity.class));
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btn_3.getText().toString().equalsIgnoreCase("START SERVICE"))
                {
                    startService(new Intent(MainActivity.this, MyService.class));
                    btn_3.setText("Stop Service");
                }
                else
                {
                    stopService(new Intent(MainActivity.this, MyService.class));
                    btn_3.setText("Start Service");
                }
            }
        });
    }


}