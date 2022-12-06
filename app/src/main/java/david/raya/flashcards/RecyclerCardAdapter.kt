package david.raya.flashcards

import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerCardAdapter: RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>() {
    val db = Firebase.firestore
    private var cardQuestions  = arrayOf("First Question", "Second Question", "Third Question")
    private var cardAnswers  = arrayOf("First Answer", "Second Answer", "Third Answer")
    var questionsArray = mutableListOf<String>()
    var answerssArray = mutableListOf<String>()

    init {
        getCardsFromDb()
    }

    fun getCardsFromDb() {
        db.collection("decks")
            .document("Math")
            .collection("Math")
            .get()
            .addOnSuccessListener { documents ->
//                System.out.println(documents.javaClass.name)
                for (document in documents) {
                    questionsArray.add(document.data["cardQuestion"] as String)
                    answerssArray.add(document.data["cardAnswer"] as String)
                }
//
                for (document in documents) {
                    Log.d("Result", "${document.data}")
                }

                notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
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

//class RecyclerCardAdapter: RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>() {
//
//
//
//}