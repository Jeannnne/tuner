package jennie.tuner

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
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

    private var audioRecord: AudioRecord? = null
    private val audioSource = MediaRecorder.AudioSource.MIC
    private val channelConfig = AudioFormat.CHANNEL_IN_MONO
    private val audioFormat = AudioFormat.ENCODING_PCM_16BIT

    private val samplingRate = 44100 // Default sampling rate for audio

    private val bufferSize: Int = AudioRecord.getMinBufferSize(
        samplingRate,
        channelConfig,
        audioFormat
    )


    private fun isMicrophonePermissionGranted(): Boolean {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                == PackageManager.PERMISSION_GRANTED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startRecordingButton = findViewById(R.id.record_button)
        stopRecordingButton = findViewById(R.id.stop_recording_button)
        freq_value = findViewById(R.id.decibel_text_view)


        if (!isMicrophonePermissionGranted()) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.RECORD_AUDIO),
                RECORD_AUDIO_PERMISSION_CODE // Add the constant here
            )
        }

        startRecordingButton.setOnClickListener() {
            startRecording()
        }

        stopRecordingButton.setOnClickListener() {
            stopRecording()
        }

    }


    @SuppressLint("MissingPermission")
    private fun startRecording() {
        audioRecord = AudioRecord(
            audioSource,
            samplingRate,
            channelConfig,
            audioFormat,
            bufferSize
        )

        audioRecord?.startRecording()

        //analyzeAudio()
        freq_value.text = "Hello World"
    }

    private fun stopRecording() {
        audioRecord?.stop()
        audioRecord?.release()
        audioRecord = null
    }



    companion object {
        const val RECORD_AUDIO_PERMISSION_CODE = 1
    }
}