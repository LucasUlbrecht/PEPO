package com.example.trab1progmov;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

// Service para reprodução de áudio contínua
public class MusicService extends Service implements MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayer;
    private boolean isPrepared = false;
    private int NOTIFICATION_ID = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(this);
        // Configurar outros listeners e preparação do MediaPlayer conforme necessário
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (action != null) {
            switch (action) {
                case Constants.ACTION_PLAY:
                    playAudio();
                    break;
                case Constants.ACTION_PAUSE:
                    pauseAudio();
                    break;
                case Constants.ACTION_STOP:
                    stopAudio();
                    break;
                default:
                    break;
            }
        }
        return START_STICKY;
    }

    private void playAudio() {
        if (!mediaPlayer.isPlaying() && isPrepared) {
            mediaPlayer.start();
            startForeground(NOTIFICATION_ID, buildNotification("Música em reprodução")); // Iniciar o serviço em primeiro plano
        }
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            updateNotification("Música pausada"); // Atualizar a notificação ao pausar a música
        }
    }

    private void stopAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
        isPrepared = false;
        stopForeground(true); // Parar o serviço em primeiro plano
        stopSelf(); // Encerrar o serviço quando a reprodução é interrompida
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        isPrepared = true;
        mediaPlayer.start(); // Iniciar a reprodução após o MediaPlayer ser preparado
        startForeground(NOTIFICATION_ID, buildNotification("Música em reprodução")); // Iniciar o serviço em primeiro plano
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // Método para construir a notificação de reprodução
    private Notification buildNotification(String contentText) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION_MAIN_ACTIVITY);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(this, Constants.CHANNEL_ID)
                .setContentTitle("App de Música")
                .setContentText(contentText)
                .setSmallIcon(R.drawable.baseline_settings_24)
                .setContentIntent(pendingIntent)
                .build();
    }

    // Método para atualizar a notificação (por exemplo, ao pausar a música)
    private void updateNotification(String contentText) {
        Notification notification = buildNotification(contentText);
        startForeground(NOTIFICATION_ID, notification);
    }
}
