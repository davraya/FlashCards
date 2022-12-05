package david.raya.flashcards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        adapter = RecyclerCardAdapter()
        recyclerV.adapter = adapter
    }
}