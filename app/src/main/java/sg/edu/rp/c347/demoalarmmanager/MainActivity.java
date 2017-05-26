package sg.edu.rp.c347.demoalarmmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSetAlarm;
    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSetAlarm = (Button) findViewById(R.id.btnAlarm);

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                //Create a new PendingIntent and add it to the AlarmManager
                Intent intent = new Intent(MainActivity.this, AlarmReceiverActivity.class);
                int reqCode = 12345;
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                //get AlarmManager instance
                am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);

                //set the alarm
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
            }
        });
    }
}
