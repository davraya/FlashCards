package david.raya.flashcards

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CreateCardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        var create_card = findViewById<Button>(R.id.btn_create_card)
        var finish_card = findViewById<Button>(R.id.btn_finish_card)

        finish_card.setOnClickListener {
            val finishIntent = Intent(this@CreateCardActivity, DecksList::class.java)
            startActivity(finishIntent)
        }

        val extras = intent.extras
        if (extras != null) {
            val deckName = extras.getString("deckName")
            if (deckName != null) {
                create_card.setOnClickListener {
                    createCard(deckName);
                }
            }
        }
    }

    private fun createCard(deckName: String) {
        var inputQuestion = findViewById<AppCompatEditText>(R.id.input_card_question)
        var inputAnswer = findViewById<AppCompatEditText>(R.id.input_card_answer)

        var inputQuestionText = inputQuestion.text.toString()
        var inputAnswerText = inputAnswer.text.toString()

        val card = hashMapOf(
            "cardQuestion" to inputQuestionText,
            "cardAnswer" to inputAnswerText
        )

        val db = Firebase.firestore
        db.collection("decks").document(deckName).collection(deckName).document(inputQuestionText).set(card).addOnSuccessListener { documentReference ->
//            Create toast
            inputQuestion.text?.clear()
            inputAnswer.text?.clear()
        }
    }
}