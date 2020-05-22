package me.aartikov.flipper_database_bug

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flipper_database_bug.R

class WordListAdapter : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private var words = emptyList<Word>()

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val inflater  = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.word_item, parent, false)
        return WordViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.wordItemView.text = words[position].word
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size
}