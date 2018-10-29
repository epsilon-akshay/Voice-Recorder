package bootcamp.gojek.voicerecorder

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.os.SystemClock
import android.widget.Chronometer
import kotlinx.android.synthetic.main.activity_recording.*


class RecordingActivity : AppCompatActivity() {
    private lateinit var recorder: MediaRecorder
    private lateinit var outputFile: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recording)
        val play = findViewById<Button>(R.id.play)
        val stop = findViewById<Button>(R.id.stop)
        val record = findViewById<Button>(R.id.record)
        val timeBar = findViewById<Chronometer>(R.id.time)
        stop.isEnabled = false
        play.isEnabled = true

        val REQUEST_EXTERNAL_STORAGE = 1
        val PERMISSIONS_STORAGE = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)


        val permission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.RECORD_AUDIO)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            )
        } else {
            record.setOnClickListener {
                val recorder = initRecorder()
                outputFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/recording${System.currentTimeMillis()}.3gp"
                recorder.setOutputFile(outputFile)
                recorder.prepare()
                recorder.start()
                timeBar.setBase(SystemClock.elapsedRealtime());
                timeBar.start()
                record.isEnabled = false
                stop.isEnabled = true
            }

            stop.setOnClickListener {
                timeBar.stop()
                timeBar.setText("00:00")
                recorder.stop()
                recorder.release()
                record.isEnabled = true
                stop.isEnabled = false
                play.isEnabled = true
            }

            play.setOnClickListener {
                val newRecord = MediaPlayer()
                newRecord.setDataSource(outputFile)
                newRecord.prepare()
                newRecord.start()
            }

            list.setOnClickListener{
                var i = Intent(applicationContext, RecordList::class.java)
                startActivity(i)
            }
        }
    }

    private fun initRecorder(): MediaRecorder {
        recorder = MediaRecorder()
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
        return recorder
    }
}
