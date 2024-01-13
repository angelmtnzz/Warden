package repository

import android.content.Context
import clases.Book
import clases.Title
import database.WardenDatabase

class WardenRepository(context: Context) {
    private val database: WardenDatabase = WardenDatabase.getDatabase(context)

    suspend fun addBookToDatabase(title: Title, book: Book) {
        // Insertamos el titulo
        database.titleDao().insertTitle(title)

        // Le damos al libro el ID del Titulo
        book.titleId = title.id

        // Insertamos el libro
        database.bookDao().insertBook(book)
    }

    // Añadir dos metodos
}
