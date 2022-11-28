package david.raya.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DecksList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decks_list)

        val fab: View = findViewById(R.id.fab_create_board)
        fab.setOnClickListener { view ->
            startActivity(Intent(this@DecksList, CreateDeckActivity::class.java))
        }
    }
}