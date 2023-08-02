package jennie.tuner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var startRecordingButton: Button
    private lateinit var stopRecordingButton: Button
    private lateinit var startPlayingButton: Button
    private lateinit var stopPlayingButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startRecordingButton = findViewById(R.id.record_button)
        stopRecordingButton = findViewById(R.id.stop_recording_button)
        startPlayingButton = findViewById(R.id.play_button)
        stopPlayingButton = findViewById(R.id.stop_button)




    }
}