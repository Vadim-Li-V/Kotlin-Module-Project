import java.util.Scanner

// Главный класс приложения
fun main() {
    val scanner = Scanner(System.`in`)
    val app = NotesApp(scanner)
    app.run()
}

// Класс Архива, содержащего заметки
class Archive(val name: String) {
    private val notes = mutableListOf<Note>()

    fun open(scanner: Scanner) {
        while (true) {
            println("\nАрхив: $name")
            println("0. Создать заметку")
            notes.forEachIndexed { index, note -> println("${index + 1}. ${note.title}") }
            println("${notes.size + 1}. Назад")

            when (val choice = readChoice(scanner, notes.size + 1)) {
                0 -> createNote(scanner)
                in 1..notes.size -> notes[choice - 1].view()
                notes.size + 1 -> return
            }
        }
    }

    private fun createNote(scanner: Scanner) {
        print("Введите заголовок заметки: ")
        val title = scanner.nextLine().trim()
        if (title.isEmpty()) {
            println("Заголовок не может быть пустым.")
            return
        }
        print("Введите текст заметки: ")
        val content = scanner.nextLine().trim()
        if (content.isEmpty()) {
            println("Текст заметки не может быть пустым.")
            return
        }
        notes.add(Note(title, content))
    }

    private fun readChoice(scanner: Scanner, max: Int): Int {
        while (true) {
            print("Выберите пункт: ")
            val input = scanner.nextLine()
            val choice = input.toIntOrNull()
            if (choice in 0..max) return choice!!
            println("Некорректный ввод. Введите число от 0 до $max.")
        }
    }
}

// Класс Заметки
class Note(val title: String, private val content: String) {
    fun view() {
        println("\n$title\n$content\n")
    }
}
