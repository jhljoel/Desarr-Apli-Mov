package pe.edu.upeu.calculadora_lp_kotlin

import kotlin.math.*

fun factorial(n: Int): Long {
    if (n < 0) throw IllegalArgumentException("El factorial solo está definido para enteros positivos.")
    return if (n == 0) 1 else n * factorial(n - 1)
}

fun main() {
    print(
        "Bienvenido a la calculadora hecha con el lenguaje de programación 'Kotlin'\n" +
                "Operaciones soportadas: '+', '-', '*', '/', '^', '%', 'sin', 'cos', 'tan', 'sqrt', 'ln', 'log10', 'fact'\n"
    )

    while (true) {
        print("Ingrese la operación (ejemplo: 5 + 3, cos 45, sqrt 16) o 'salir': ")
        val input = readLine() ?: ""

        if (input.lowercase() == "salir") {
            println("Calculadora finalizada.")
            break
        }

        val parts = input.split(" ")

        try {
            val resultado = when {
                parts.size == 3 -> { // Operaciones estándar (suma, resta, etc.)
                    val num1 = parts[0].toDouble()
                    val operador = parts[1]
                    val num2 = parts[2].toDouble()

                    when (operador) {
                        "+" -> num1 + num2
                        "-" -> num1 - num2
                        "*" -> num1 * num2
                        "/" -> {
                            if (num2 == 0.0) throw ArithmeticException("División por cero no permitida")
                            num1 / num2
                        }
                        "^" -> num1.pow(num2) // Exponente
                        "%" -> num1 % num2 // Módulo
                        else -> throw IllegalArgumentException("Operador no válido")
                    }
                }

                parts.size == 2 -> { // Funciones matemáticas
                    val operador = parts[0]
                    val num = parts[1].toDouble()

                    when (operador.lowercase()) {
                        "sin" -> sin(Math.toRadians(num))
                        "cos" -> cos(Math.toRadians(num))
                        "tan" -> tan(Math.toRadians(num))
                        "sqrt" -> {
                            if (num < 0) throw ArithmeticException("No se puede calcular la raíz cuadrada de un número negativo.")
                            sqrt(num)
                        }
                        "ln" -> {
                            if (num <= 0) throw ArithmeticException("El logaritmo natural solo está definido para números positivos.")
                            ln(num)
                        }
                        "log10" -> {
                            if (num <= 0) throw ArithmeticException("El logaritmo base 10 solo está definido para números positivos.")
                            log10(num)
                        }
                        "fact" -> {
                            val entero = num.toInt()
                            if (num != entero.toDouble() || entero < 0) {
                                throw IllegalArgumentException("El factorial solo se calcula para enteros positivos.")
                            }
                            factorial(entero)
                        }
                        else -> throw IllegalArgumentException("Operación no válida")
                    }
                }

                else -> throw IllegalArgumentException("Formato inválido. Use: número operador número (ej. 5 + 3) o función número (ej. sqrt 16, fact 5)")
            }

            println("Resultado: $resultado")
        } catch (e: NumberFormatException) {
            println("Error: Entrada inválida. Ingrese números válidos.")
        } catch (e: ArithmeticException) {
            println("Error: ${e.message}")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    }
}