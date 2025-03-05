import java.util.Scanner

// Класс, управляющий приложением
class NotesApp(private val scanner: Scanner) {
    private val archives = mutableListOf<Archive>()

    fun run() {
        while (true) {
            println("\nСписок архивов:")
            println("0. Создать архив")
            archives.forEachIndexed { index, archive -> println("${index + 1}. ${archive.name}") }
            println("${archives.size + 1}. Выход")

            when (val choice = readChoice(archives.size + 1)) {
                0 -> createArchive()
                in 1..archives.size -> archives[choice - 1].open(scanner)
                archives.size + 1 -> return
            }
        }
    }

    private fun createArchive() {
        print("Введите название архива: ")
        val name = scanner.nextLine().trim()
        if (name.isEmpty()) {
            println("Название архива не может быть пустым.")
            return
        }
        archives.add(Archive(name))
    }

    private fun readChoice(max: Int): Int {
        while (true) {
            print("Выберите пункт: ")
            val input = scanner.nextLine()
            val choice = input.toIntOrNull()
            if (choice in 0..max) return choice!!
            println("Некорректный ввод. Введите число от 0 до $max.")
        }
    }
}