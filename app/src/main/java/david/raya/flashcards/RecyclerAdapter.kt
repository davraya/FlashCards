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

// Adapters are responsible for creating and managing views for items
class RecyclerAdapter(var context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val db = Firebase.firestore
    var decksArray = mutableListOf<String>()

    // Start by getting all the information from the database.
    init {
        getDecksFromDb()
    }

    // Method that interacts with the database and gets all the info that contains.
    fun getDecksFromDb() {
        // Pulling all documents from the deck collection
        db.collection("decks")
            .get()
            .addOnSuccessListener { documents ->
                // Populating empty mutable list
                for (document in documents) {
                    decksArray.add(document.data["deckName"] as String)
                }

                // Notifies of an activity change about the list we passed
                notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
    }

    // How the ViewGroup is going to behave.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        // Instantiates a layout XML file into its corresponding view
        val v = LayoutInflater.from(parent.context).inflate(R.layout.deck_layout, parent, false)
        return ViewHolder(v)
    }

    // Gets the total of decks that were extracted from the database.
    override fun getItemCount(): Int {
        return decksArray.size
    }

    // How RecyclerView is going to behave.
    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        // Assigning item in list to deck name
        holder.itemTitle.text = decksArray[position]
        // Listening for deck click
        holder.itemView.setOnClickListener {
            // creating an intent and passing context since this is not an activity
            var intent = Intent(context, CardsList::class.java)
            // Passing deckId so it knows what collection to pull the cards from
            intent.putExtra("deckId", decksArray[position])
            context.startActivity(intent)
        }
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            // Grabbing view from the deck layout xml
            itemTitle = itemView.findViewById(R.id.deck_text_name)
        }
    }
}
