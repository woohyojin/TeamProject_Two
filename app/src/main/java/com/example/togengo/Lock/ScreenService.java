package com.example.togengo.Lock;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenService extends Service {
    private ScreenReceiver mReceiver = null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mReceiver = new ScreenReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver, filter); // 브로드 캐스트 리시버를 등록한다.
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        // 서비스를 강제로 종료했을 때 서비스를 어떤 방법으로 다시 시작시킬지 결정.
        super.onStartCommand(intent, flags, startId);
        if(intent != null){
            if(intent.getAction()==null){
                if(mReceiver==null){
                    mReceiver = new ScreenReceiver();
                    IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
                    registerReceiver(mReceiver, filter);
                }
            }
        }
        return START_REDELIVER_INTENT;
        // 이후 서비스 재생성 가능, 강제로 종료되기 전에 전달된 마지막 인텐트를 다시 전달해주는 기능 포함.
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(mReceiver != null){
            unregisterReceiver(mReceiver); // 리시버 등록 해제
        }
    }
}
