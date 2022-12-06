package david.raya.flashcards

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class RecyclerCardAdapter(var context: Context): RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>() {
    val db = Firebase.firestore
    var questionsArray = mutableListOf<String>()
    var answerssArray = mutableListOf<String>()
    val intent = (context as Activity).intent
    val extras = intent.extras

    init {
        getCardsFromDb()
    }

    fun getCardsFromDb() {
        val deckId = extras?.getString("deckId")

        if (deckId != null) {
            db.collection("decks")
                .document(deckId)
                .collection(deckId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        questionsArray.add(document.data["cardQuestion"] as String)
                        answerssArray.add(document.data["cardAnswer"] as String)
                    }

                    notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents.", exception)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerCardAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent , false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return questionsArray.size
    }

    override fun onBindViewHolder(holder: RecyclerCardAdapter.ViewHolder, position: Int) {
        holder.itemQuestion.text = questionsArray[position]
        holder.itemAnswer.text = answerssArray[position]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemQuestion: TextView
        var itemAnswer: TextView

        init {
            itemQuestion = itemView.findViewById(R.id.card_question)
            itemAnswer = itemView.findViewById(R.id.card_answer)
        }
    }
}
