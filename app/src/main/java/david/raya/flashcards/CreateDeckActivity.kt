package david.raya.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.Toolbar

class CreateDeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_deck)

        var backButton = findViewById<Button>(R.id.back_deck)
        backButton.setOnClickListener {
            val backIntent = Intent(this@CreateDeckActivity, DecksList::class.java)
            startActivity(backIntent)
        }
    }
}