package edu.bu.projectportal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == Intent.ACTION_BOOT_COMPLETED) {
            Intent startAppIntent = new Intent (context, ProjectsListActivity.class);
            context.startActivity(startAppIntent);
        } else if (intent.getAction() == Intent.ACTION_BATTERY_CHANGED) {
            Toast.makeText(context,"battery changed", Toast.LENGTH_LONG).show();
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            if(status == BatteryManager.BATTERY_STATUS_CHARGING )
                Toast.makeText(context,"battery is charging", Toast.LENGTH_LONG).show();
        }
    }
}
