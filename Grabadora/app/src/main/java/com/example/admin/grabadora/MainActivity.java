package com.example.admin.grabadora;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button bGrabar,bDetener,bReproducir;
    private static final String LOG_TAG="Grabadora";
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private static String fichero = Environment.getExternalStorageDirectory().getAbsolutePath()+"/audio.3gp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bGrabar=(Button) findViewById(R.id.bGrabar);
        bDetener=(Button) findViewById(R.id.bDetener);
        bReproducir=(Button) findViewById(R.id.bReproducir);
        bDetener.setEnabled(false);
        bReproducir.setEnabled(false);
    }
    public void grabar(View view){
        bGrabar.setEnabled(false);
        bDetener.setEnabled(true);
        bReproducir.setEnabled(false);
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOutputFile(fichero);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.                                                                                                                         AMR_NB);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "Fallo en grabación");
        }
        mediaRecorder.start();
    }
    public void detenerGrabacion(View view){
        bGrabar.setEnabled(true);
        bDetener.setEnabled(false);
        bReproducir.setEnabled(true);
        mediaRecorder.stop();
        mediaRecorder.release();
    }
    public void reproducir(View view){
        mediaPlayer = new MediaPlayer();
        try{
            mediaPlayer.setDataSource(fichero);
            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (IOException e){
            Log.e(LOG_TAG, "Fallo en reproducción");
        }

    }
}
