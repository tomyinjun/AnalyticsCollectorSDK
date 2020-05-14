package com.cmcc.vr.mid.probe;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    VideoQualityInfoInterface videoQualityInfoInterface;
    ServiceConnection conn = new ServiceConnection() {
        //绑定服务时调用
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //服务
            videoQualityInfoInterface = VideoQualityInfoInterface.Stub.asInterface(iBinder);
        }

        //断开服务调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            videoQualityInfoInterface = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定服务
        bindService();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 此处调用自己的动作，用于测试自己的代码，关联app首页邮件按钮事件
                Log.d("event:", (videoQualityInfoInterface == null)+"");
                try {
                    videoQualityInfoInterface.playerEventCall(0, "");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent();
        //显示Intent启动 绑定服务
        intent.setComponent(new ComponentName("com.cmcc.vr.mid.probe.core", "com.cmcc.vr.mid.probe.core.DetectorService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
