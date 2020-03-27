package com.zz.myadsplayer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zz.myadsplayer.R;
import com.zz.myadsplayer.utils.NetworkUtils;

public class NetworkState extends AppCompatActivity {

    private static final String TAG = "zz";

    Button getNetState;
    TextView text;
    Boolean permitted = false;
    int netStat = NetworkUtils.NETWORK_UNKNOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_state);

        getNetState = findViewById(R.id.net_get);
        text = findViewById(R.id.net_state);

        getNetState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permitted = NetworkUtils.INSTANCE.checkNetworkPermision(NetworkState.this);

                if (permitted) {
                    netStat = NetworkUtils.INSTANCE.getNetworkState(NetworkState.this);
                }

                switch (netStat) {
                    case NetworkUtils.NETWORK_UNKNOWN:
                        text.setText(netStat + " 未知网络");
                        break;
                    case NetworkUtils.NETWORK_WIFI:
                        text.setText(netStat + " wifi网络");
                        break;
                    case NetworkUtils.NETWORK_NONE:
                        text.setText(netStat + " 无网络");
                        break;
                    default:
                        break;
                }
            }
        });


    }
}
