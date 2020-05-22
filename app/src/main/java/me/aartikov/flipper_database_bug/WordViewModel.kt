package me.aartikov.flipper_database_bug

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: WordDao = (application as App).database.wordDao()

    val words: LiveData<List<Word>> = dao.getWords()

    fun addWord() {
        val wordCount = words.value?.size ?: 0

        viewModelScope.launch(Dispatchers.Default) {
            val word = Word("Word $wordCount")
            dao.insert(word)
        }
    }

    fun clear() {
        viewModelScope.launch(Dispatchers.Default) {
            dao.deleteAll()
        }
    }
}