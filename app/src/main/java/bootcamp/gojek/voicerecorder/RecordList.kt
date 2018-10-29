package bootcamp.gojek.voicerecorder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import java.io.File

class RecordList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        val recordingList = findViewById<RecyclerView>(R.id.recordings)
        val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val listFile: Array<File> = dir.listFiles()
        val records = ArrayList<String>()
        for(i in (0..listFile.size - 1)) {
            records.add(listFile[i].absolutePath)
            Log.d("hihihihihiihihihihii",records[i])
        }
        Log.d("hihihiih",records.size.toString())
        recordingList.layoutManager = LinearLayoutManager(this)
        recordingList.adapter = RecordingAdapter(records)
    }
}
