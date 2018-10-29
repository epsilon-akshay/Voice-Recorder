package bootcamp.gojek.voicerecorder

import android.media.MediaPlayer
import android.os.Environment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class RecordingAdapter(private val records: ArrayList<String>): RecyclerView.Adapter<RecordingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view  = LayoutInflater.from(p0.context).inflate(R.layout.recording_button, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindData(records[p1])
    }

    class ViewHolder(val buttonView: View) : RecyclerView.ViewHolder(buttonView) {
        fun bindData(recordName: String) {
            val button = buttonView.findViewById<Button>(R.id.recording_button)
            button.text = recordName
            button.setOnClickListener{
                val newRecord = MediaPlayer()
                newRecord.setDataSource( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).absolutePath + "/" + recordName)
                newRecord.prepare()
                newRecord.start()
            }
        }
    }
}
