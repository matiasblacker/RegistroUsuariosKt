import java.util.regex.Pattern

fun main() {
    val users = mutableListOf<User>()

    println("¿Cuántos usuarios deseas registrar?")
    val count = readLine()?.toIntOrNull() ?: return println("Por favor, ingresa un número válido.")

    for (i in 1..count) {
        println("Registro del usuario $i:")
        val user = getUserData()
        user?.let { users.add(it) }
    }

    // Ordenar usuarios por edad
    val sortedUsers = users.sortedBy { it.age }

    // Imprimir los usuarios ordenados
    println("\nUsuarios registrados ordenados por edad:")
    for (user in sortedUsers) {
        println(user)
    }
}

// Función para obtener los datos del usuario
fun getUserData(): User? {
    val name = getValidName()
    val surname = getValidSurname()
    val age = getValidAge()
    val email = getValidEmail()
    val healthSystem = getValidHealthSystem()

    return User(name, surname, age, email, healthSystem)
}

// Función para validar el nombre
fun getValidName(): String {
    while (true) {
        println("Ingresa el nombre (1-20 caracteres):")
        val name = readLine() ?: continue
        if (name.length in 1..20 && name.all { it.isLetter() }) {
            return name
        }
        println("Nombre inválido. Debe tener entre 1 y 20 caracteres y solo contener letras.")
    }
}

// Función para validar el apellido
fun getValidSurname(): String {
    while (true) {
        println("Ingresa el apellido (solo letras):")
        val surname = readLine() ?: continue
        if (surname.all { it.isLetter() }) {
            return surname
        }
        println("Apellido inválido. Debe contener solo letras.")
    }
}

// Función para validar la edad
fun getValidAge(): Int {
    while (true) {
        println("Ingresa la edad (número válido):")
        val age = readLine()?.toIntOrNull()
        if (age != null && age >= 0) {
            return age
        }
        println("Edad inválida. Debe ser un número positivo.")
    }
}

// Función para validar el correo
fun getValidEmail(): String {
    val emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    while (true) {
        println("Ingresa el correo electrónico (formato válido):")
        val email = readLine() ?: continue
        if (emailPattern.matcher(email).matches()) {
            return email
        }
        println("Correo inválido. Por favor, ingresa un correo válido.")
    }
}

// Función para validar el sistema de salud
fun getValidHealthSystem(): String {
    val validOptions = listOf("Fonasa", "Isapre", "Particular")
    while (true) {
        println("Ingresa el sistema de salud (Fonasa/Isapre/Particular):")
        val healthSystem = readLine() ?: continue
        if (healthSystem in validOptions) {
            return healthSystem
        }
        println("Opción inválida. Debe ser Fonasa, Isapre o Particular.")
    }
}

// Clase que representa un usuario
data class User(val name: String, val surname: String, val age: Int, val email: String, val healthSystem: String) {
    override fun toString(): String {
        return "Nombre: $name $surname, Edad: $age, Correo: $email, Sistema de Salud: $healthSystem"
    }
}
