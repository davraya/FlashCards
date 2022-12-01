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

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    val db = Firebase.firestore
    private var titles = arrayOf("One", "Two", "Three")

    fun getDecksFromDb(): Array<Any> {
        var decksArray = arrayOf<Any>()

        db.collection("decks")
            .get()
            .addOnSuccessListener { documents ->
                System.out.println(documents.javaClass.name)
//                for (document in documents) {
//                    decksArray += document.data
//                }
//
//                for (document in decksArray) {
//                    Log.d("Result", "${document}")
//                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
        return decksArray
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.deck_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
//        var decksArray = this.getDecksFromDb()
        return titles.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        var decksArray = this.getDecksFromDb()

//        for (document in decksArray) {
//            Log.d("Bind", "${document}")
//        }

        holder.itemTitle.text = titles[position]
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.deck_text_name)
        }
    }
}