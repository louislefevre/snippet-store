package com.snippetstore.app.data

object MockData {
    fun createMockData(): List<Snippet> {
        return listOf(
            Snippet("Code 1", "Kotlin", "Title 1", "Subtitle 1"),
            Snippet("Code 2", "Java", "Title 2", "Subtitle 2"),
            Snippet("Code 3", "Go", "Title 3", "Subtitle 3"),
            Snippet("Code 4", "Python", "Title 4", "Subtitle 4")
        )
    }
}
