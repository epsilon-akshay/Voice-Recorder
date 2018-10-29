package bootcamp.gojek.voicerecorder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            val nextActivity = Intent(this, RecordingActivity::class.java)
            startActivity(nextActivity)
        },1000)
    }
}
