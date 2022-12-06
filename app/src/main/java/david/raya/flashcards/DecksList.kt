package david.raya.flashcards

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// This class uses the RecyclerView to display the decks.
class DecksList : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decks_list)

        // Class that tells adapters how to arrange those items
        layoutManager = LinearLayoutManager(this)
        var recyclerV = findViewById<RecyclerView>(R.id.recyclerView)

        // Defining adapter
        adapter = RecyclerAdapter(this)

        // Injecting layoutManager and adapter into the element
        recyclerV.layoutManager = layoutManager
        recyclerV.adapter = adapter

        val fab: View = findViewById(R.id.fab_create_board)
        // Takes you to create a card
        fab.setOnClickListener { view ->
            startActivity(Intent(this@DecksList, CreateDeckActivity::class.java))
        }
    }
}
