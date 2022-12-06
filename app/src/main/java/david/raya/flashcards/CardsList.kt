package david.raya.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CardsList : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cards_list)

        layoutManager = LinearLayoutManager(this)
        var recyclerV = findViewById<RecyclerView>(R.id.recyclerCardView)
        recyclerV.layoutManager = layoutManager

        adapter = RecyclerCardAdapter(this)
        recyclerV.adapter = adapter

        var extra = intent.extras?.getString("deckId")
        val btn: View = findViewById(R.id.create_additional_card)
        btn.setOnClickListener { view ->
            var intent = Intent(this@CardsList, CreateCardActivity::class.java)
            intent.putExtra("deckName", extra)
            startActivity(intent)
        }
    }
}