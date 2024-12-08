import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Asignacion_7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> resultados = new ArrayList<>();

        System.out.println("Bienvenido a la calculadora de áreas, perímetros y potencia.");

        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println("\nFiguras disponibles:");
                System.out.println("1. Círculo");
                System.out.println("2. Cuadrado");
                System.out.println("3. Triángulo");
                System.out.println("4. Rectángulo");
                System.out.println("5. Pentágono");
                System.out.println("6. Salir");

                System.out.print("\nSeleccione una figura (1-6): ");
                int figura = leerEntero(scanner);

                if (figura == 6) {
                    System.out.println("Saliendo del programa...");
                    break;
                }

                System.out.println("\nOperaciones disponibles:");
                System.out.println("1. Área");
                System.out.println("2. Perímetro");
                System.out.println("3. Potencia");

                System.out.print("\nSeleccione una operación (1-3): ");
                int operacion = leerEntero(scanner);

                double resultado = 0;

                if (operacion == 3) { // Cálculo de potencia
                    System.out.print("Ingrese la base: ");
                    double base = leerDouble(scanner);
                    System.out.print("Ingrese el exponente: ");
                    int exponente = leerEntero(scanner);
                    resultado = calcularPotencia(base, exponente); // Método recursivo
                    System.out.printf("\nLa potencia de %.2f elevado a %d es: %.2f\n", base, exponente, resultado);
                } else {
                    resultado = calcularFigura(figura, operacion, scanner);
                }

                // Almacenar el resultado
                resultados.add(resultado);

            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Intente nuevamente.");
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }

        // Mostrar todos los resultados acumulados
        System.out.println("\nResultados acumulados:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.printf("Operación %d: %.2f\n", i + 1, resultados.get(i));
        }

        scanner.close();
    }

    // Método para calcular figuras (área o perímetro)
    private static double calcularFigura(int figura, int operacion, Scanner scanner) {
        double resultado = 0;
        try {
            switch (figura) {
                case 1: // Círculo
                    System.out.print("Ingrese el radio del círculo: ");
                    double radio = leerDouble(scanner);
                    resultado = operacion == 1 ? Math.PI * radio * radio : 2 * Math.PI * radio;
                    break;
                case 2: // Cuadrado
                    System.out.print("Ingrese el lado del cuadrado: ");
                    double ladoCuadrado = leerDouble(scanner);
                    resultado = operacion == 1 ? ladoCuadrado * ladoCuadrado : 4 * ladoCuadrado;
                    break;
                case 3: // Triángulo
                    System.out.print("Ingrese la base del triángulo: ");
                    double base = leerDouble(scanner);
                    System.out.print("Ingrese la altura del triángulo: ");
                    double altura = leerDouble(scanner);
                    if (operacion == 1) {
                        resultado = (base * altura) / 2;
                    } else {
                        System.out.print("Ingrese los otros dos lados del triángulo (lado 1): ");
                        double lado1 = leerDouble(scanner);
                        System.out.print("Ingrese el lado 2 del triángulo: ");
                        double lado2 = leerDouble(scanner);
                        resultado = base + lado1 + lado2;
                    }
                    break;
                case 4: // Rectángulo
                    System.out.print("Ingrese el largo del rectángulo: ");
                    double largo = leerDouble(scanner);
                    System.out.print("Ingrese el ancho del rectángulo: ");
                    double ancho = leerDouble(scanner);
                    resultado = operacion == 1 ? largo * ancho : 2 * (largo + ancho);
                    break;
                case 5: // Pentágono
                    System.out.print("Ingrese el lado del pentágono: ");
                    double ladoPentagono = leerDouble(scanner);
                    if (operacion == 1) {
                        System.out.print("Ingrese el apotema del pentágono: ");
                        double apotema = leerDouble(scanner);
                        resultado = (5 * ladoPentagono * apotema) / 2;
                    } else {
                        resultado = 5 * ladoPentagono;
                    }
                    break;
                default:
                    System.out.println("Figura no válida.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Entrada inválida. Intente nuevamente.");
            scanner.nextLine();
        }
        return resultado;
    }

    // Método recursivo para calcular la potencia
    private static double calcularPotencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        } else if (exponente < 0) {
            return 1 / calcularPotencia(base, -exponente);
        } else {
            return base * calcularPotencia(base, exponente - 1);
        }
    }
    // Método para leer un entero con validación
    private static int leerEntero(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero válido. Intente nuevamente.");
                scanner.nextLine();
            }
        }
    }

    // Método para leer un double con validación
    private static double leerDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número decimal válido. Intente nuevamente.");
                scanner.nextLine();
            }
        }
    }
}