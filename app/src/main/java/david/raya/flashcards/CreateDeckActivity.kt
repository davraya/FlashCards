package david.raya.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateDeckActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_deck)

        var backButton = findViewById<Button>(R.id.back_deck)
        backButton.setOnClickListener {
            val backIntent = Intent(this@CreateDeckActivity, DecksList::class.java)
            startActivity(backIntent)
        }

        var create_deck = findViewById<Button>(R.id.btn_create_deck)
        create_deck.setOnClickListener {
            createBoard();
        }
    }

    private fun createBoard() {
        var input = findViewById<AppCompatEditText>(R.id.et_board_name).text.toString()
        val deck = hashMapOf(
            "deckName" to input,
        )

        val db = Firebase.firestore
        db.collection("decks").add(deck).addOnSuccessListener { documentReference ->
            Log.d("Hello", "Document added")
        }
    }
}