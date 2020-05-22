package me.aartikov.flipper_database_bug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flipper_database_bug.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.words.observe(this, Observer { words ->
            words.let { adapter.setWords(it) }
        })

        fab.setOnClickListener {
            wordViewModel.addWord()
        }

        clear.setOnClickListener {
            wordViewModel.clear()
        }
    }
}
