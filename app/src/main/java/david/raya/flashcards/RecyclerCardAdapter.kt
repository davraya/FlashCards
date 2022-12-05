package david.raya.flashcards

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerCardAdapter: RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>() {
    private var cardQuestions  = arrayOf("First Question", "Second Question", "Third Question")
    private var cardAnswers  = arrayOf("First Answer", "Second Answer", "Third Answer")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerCardAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent , false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return cardQuestions.size
    }

    override fun onBindViewHolder(holder: RecyclerCardAdapter.ViewHolder, position: Int) {
        holder.itemQuestion.text = cardQuestions[position]
        holder.itemAnswer.text = cardAnswers[position]
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