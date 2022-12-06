package david.raya.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Loads the Splash screen from the xml
        setContentView(R.layout.activity_splash_screen)

        // After 2.5 seconds, it redirects you to the DeckList activity
        Handler().postDelayed({
            startActivity(Intent(this, DecksList::class.java))
            finish()
        }, 2500)
    }
}