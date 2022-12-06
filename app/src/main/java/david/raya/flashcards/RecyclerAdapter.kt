package david.raya.flashcards

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RecyclerAdapter(var context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val db = Firebase.firestore
    var decksArray = mutableListOf<String>()

    init {
        getDecksFromDb()
    }

    fun getDecksFromDb() {
        db.collection("decks")
            .get()
            .addOnSuccessListener { documents ->
//                System.out.println(documents.javaClass.name)
                for (document in documents) {
                    decksArray.add(document.data["deckName"] as String)
                }

                notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.deck_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return decksArray.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = decksArray[position]
        // Listening for deck click
        holder.itemView.setOnClickListener {
            // creating an intent and passing context since this is not an activity
            var intent = Intent(context, CardsList::class.java)
            intent.putExtra("deckId", decksArray[position])
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.deck_text_name)
        }
    }
}