package com.mirea.lavrenov.mireaproject.ui.player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.mirea.lavrenov.mireaproject.R;

public class PlayerService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}


//public class PlayerService extends Service {
//    private Looper serviceLooper;
//    private ServiceHandler serviceHandler;
//
//    // Handler that receives messages from the thread
//    private final class ServiceHandler extends Handler {
//        public ServiceHandler(Looper looper) {
//            super(looper);
//        }
//
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.getData().getString("action")) {
//                case "play":
//
//                    break;
//                case "pause":
//
//                    break;
//                case "stop":
//
//                    break;
//            }
//
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//            stopSelf(msg.arg1);
//        }
//    }
//
////    @Override
////    public void onCreate() {
//////        HandlerThread thread = new HandlerThread("ServiceStartArguments",
//////                Process.THREAD_PRIORITY_BACKGROUND);
//////        thread.start();
////
////        // Get the HandlerThread's Looper and use it for our Handler
////        serviceLooper = thread.getLooper();
////        serviceHandler = new ServiceHandler(serviceLooper);
////    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
//
//        // For each start request, send a message to start a job and deliver the
//        // start ID so we know which request we're stopping when we finish the job
//        Message msg = serviceHandler.obtainMessage();
//        msg.arg1 = startId;
//        serviceHandler.sendMessage(msg);
//
//        // If we get killed, after returning from here, restart
//        return START_STICKY;
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        // We don't provide binding, so return null
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
//    }
//}