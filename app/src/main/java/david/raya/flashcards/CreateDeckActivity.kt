package david.raya.flashcards

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// This class allows the user to create a new deck, and it is then stored in the database.
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
            createDeck();
        }
    }

    // The text where the user will enter the title of the deck
    private fun createDeck() {
        var input = findViewById<AppCompatEditText>(R.id.et_board_name).text.toString()
        val deck = hashMapOf(
            "deckName" to input,
        )

        val db = Firebase.firestore
        db.collection("decks").document(input).set(deck).addOnSuccessListener { documentReference ->
            var deckName = input;
            val cardIntent = Intent(this@CreateDeckActivity, CreateCardActivity::class.java)
            cardIntent.putExtra("deckName", deckName)
            startActivity(cardIntent)
        }
    }
}
