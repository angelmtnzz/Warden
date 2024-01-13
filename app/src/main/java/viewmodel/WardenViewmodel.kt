package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import clases.Book
import clases.Title
import kotlinx.coroutines.launch
import repository.WardenRepository

class WardenViewModel(private val repository: WardenRepository) : ViewModel() {

    // Añadir mas metodos

    fun addBookAndTitleToDatabase(title: Title, book: Book) {
        viewModelScope.launch {
            repository.addBookToDatabase(title, book)
        }
    }


    class Factory(private val repository: WardenRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WardenViewModel::class.java)) {
                return WardenViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
