package jennie.tuner

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    private lateinit var startRecordingButton: Button
    private lateinit var stopRecordingButton: Button
    private lateinit var freq_value: TextView

    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private val samplingRate = 44100 // Default sampling rate for audio

    private fun isMicrophonePermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startRecordingButton = findViewById(R.id.record_button)
        stopRecordingButton = findViewById(R.id.stop_recording_button)
        freq_value = findViewById(R.id.decibel_text_view)


        if (!isMicrophonePermissionGranted()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECORD_AUDIO), RECORD_AUDIO_PERMISSION_CODE
            )
        }

    }

    companion object {
        const val RECORD_AUDIO_PERMISSION_CODE = 1
    }
}