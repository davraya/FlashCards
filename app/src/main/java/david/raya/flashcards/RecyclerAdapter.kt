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

// This class is in charge of making the RecyclerView possible for the app.
class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val db = Firebase.firestore
    private var titles = arrayOf("One", "Two", "Three")
    var decksArray = mutableListOf<String>()

    // Start by getting all the information from the database.
    init {
        getDecksFromDb()
    }

    // Method that interacts with the database and gets all the info that contains.
    fun getDecksFromDb() {
        db.collection("decks")
            .get()
            .addOnSuccessListener { documents ->
//                System.out.println(documents.javaClass.name)
                for (document in documents) {
                    decksArray.add(document.data["deckName"] as String)
                }
//
                for (document in documents) {
                    Log.d("Result", "${document.data["deckName"]}")
                }

                notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    // How the ViewGroup is going to behave.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.deck_layout, parent, false)
        return ViewHolder(v)
    }

    // Gets the total of decks that were extracted from the database.
    override fun getItemCount(): Int {
        return decksArray.size
    }

    // How RecyclerView is going to behave.
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = decksArray[position]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.deck_text_name)
        }
    }
}
