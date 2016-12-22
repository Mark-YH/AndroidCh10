package andbas.ch10sysbroadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private Button btEnd;
    private TextView tvBattery;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        buildViews();  //user define
        this.registerReceiver(batteryInfoRecv, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
    private void buildViews() {
        tvBattery = (TextView)findViewById(R.id.tvIdbatteryLevel);
    	btEnd = (Button) this.findViewById(R.id.btIdEnd);
    	btEnd.setOnClickListener(btEndListener);
    }
        
    private OnClickListener btEndListener = 
    		new OnClickListener() {
		public void onClick(View v) {
            unregisterReceiver(batteryInfoRecv);
            System.exit(0);
        }
    };
    private BroadcastReceiver batteryInfoRecv = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level",0);
            ImageView ivBatteryIcon = (ImageView)findViewById(R.id.ivIdBatteryIcon);
            ivBatteryIcon.getDrawable().setLevel(level);
            tvBattery.setText("目前電量："+level+"%");
        }
    };
}
