import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Clase InterfazUsuario
class InterfazUsuario {
    public static void mostrarMenuPrincipal() {
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear fleje");
        System.out.println("2. Salir");
    }

    public static void mostrarMenuFlejes() {
        System.out.println("Seleccione el tipo de fleje:");
        System.out.println("1. Cuadrado");
        System.out.println("2. Rectangular");
        System.out.println("3. Triangular");
        System.out.println("4. Personalizado");
    }

    public static int leerOpcion(Scanner scanner) {
        return scanner.nextInt();
    }

    public static double leerUnLado(Scanner scanner) {
        System.out.println("Ingrese la medida del lado:");
        return scanner.nextDouble();
    }

    public static double[] leerDosLados(Scanner scanner) {
        double[] lados = new double[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("Ingrese la medida del lado " + (i + 1) + ":");
            lados[i] = scanner.nextDouble();
        }
        return lados;
    }

    public static double[] leerLados(Scanner scanner, int cantidadLados) {
        double[] lados = new double[cantidadLados];
        for (int i = 0; i < cantidadLados; i++) {
            System.out.println("Ingrese la medida del lado " + (i + 1) + ":");
            lados[i] = scanner.nextDouble();
        }
        return lados;
    }

    public static int leerCantidadFlejes(Scanner scanner) {
        System.out.println("Ingrese la cantidad de flejes realizados:");
        return scanner.nextInt();
    }

    public static String leerVarilla(Scanner scanner) {
        System.out.println("Ingrese el tipo de varilla (3/8 o 1/4):");
        scanner.nextLine(); // Limpiar buffer
        return scanner.nextLine();
    }

    public static void mostrarResultado(double total) {
        System.out.println("El total a pagar por este fleje es: " + String.format("%.2f", total));
    }

    public static void mostrarGanancias(double ganancias) {
        System.out.println("Las ganancias del trabajador (7% del total) por este fleje son: " + String.format("%.2f", ganancias));
    }

    public static void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }

    public static void mostrarTotalesAcumulados(double totalPagar, double gananciasTotales) {
        System.out.println("\nTotal acumulado a pagar por todos los flejes: " + String.format("%.2f", totalPagar));
        System.out.println("Total acumulado de ganancias del trabajador: " + String.format("%.2f", gananciasTotales));
    }

    public static void mostrarAcumuladoDeFleje(double totalAcumulado, double gananciasAcumuladas) {
        System.out.println("Acumulado hasta el momento:");
        System.out.println("Total acumulado a pagar: " + String.format("%.2f", totalAcumulado));
        System.out.println("Ganancias acumuladas del trabajador: " + String.format("%.2f", gananciasAcumuladas));
    }
}

// Clase Fleje
class Fleje {
    private String tipo;
    private double[] lados;
    private double equivalenciaVarilla;
    private int cantidad;
    private double precioPorUnidad;

    public Fleje(String tipo, double[] lados, double equivalenciaVarilla, int cantidad, double precioPorUnidad) {
        this.tipo = tipo;
        this.lados = lados;
        this.equivalenciaVarilla = equivalenciaVarilla;
        this.cantidad = cantidad;
        this.precioPorUnidad = precioPorUnidad;
    }

    public double calcularPago() {
        double sumaLados = 0;
        for (double lado : lados) {
            sumaLados += lado;
        }
        double sumaLadosDecimal = Math.round(sumaLados * 100.0) / 100.0;
        
        double factor = sumaLadosDecimal >= 100 ? 1.0 : sumaLadosDecimal / 100.0;
        return (factor + 6) * (equivalenciaVarilla * cantidad) * precioPorUnidad;
    }
}

// Clase FlejeService
class FlejeService {
    public Fleje crearFleje(String tipo, double[] lados, String varilla, int cantidad) {
        double equivalencia;
        if ("3/8".equals(varilla)) {
            equivalencia = 0.5;
        } else if ("1/4".equals(varilla)) {
            equivalencia = 0.25;
        } else {
            throw new IllegalArgumentException("Tipo de varilla no válido.");
        }

        double precioPorUnidad = 250; // Precio estándar
        return new Fleje(tipo, lados, equivalencia, cantidad, precioPorUnidad);
    }
}

// Clase FlejeRepository
class FlejeRepository {
    private List<Fleje> flejes;

    public FlejeRepository() {
        this.flejes = new ArrayList<>();
    }

    public void guardarFleje(Fleje fleje) {
        flejes.add(fleje);
    }

    public List<Fleje> obtenerFlejes() {
        return new ArrayList<>(flejes);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlejeService flejeService = new FlejeService();
        FlejeRepository flejeRepository = new FlejeRepository();

        double totalAcumulado = 0;
        double gananciasTotales = 0;

        while (true) {
            InterfazUsuario.mostrarMenuPrincipal();
            int opcion = InterfazUsuario.leerOpcion(scanner);

            switch (opcion) {
                case 1:
                    InterfazUsuario.mostrarMenuFlejes();
                    int tipoFleje = InterfazUsuario.leerOpcion(scanner);
                    double[] lados = null;

                    switch (tipoFleje) {
                        case 1:
                            double lado = InterfazUsuario.leerUnLado(scanner);
                            lados = new double[]{lado, lado};
                            break;
                        case 2:
                            lados = InterfazUsuario.leerDosLados(scanner);
                            break;
                        case 3:
                            lados = InterfazUsuario.leerLados(scanner, 3);
                            break;
                        case 4:
                            System.out.println("Ingrese la cantidad de lados:");
                            int cantidadLados = InterfazUsuario.leerOpcion(scanner);
                            lados = InterfazUsuario.leerLados(scanner, cantidadLados);
                            break;
                        default:
                            InterfazUsuario.mostrarError("Opción no válida.");
                            continue;
                    }

                    String varilla = InterfazUsuario.leerVarilla(scanner);
                    int cantidad = InterfazUsuario.leerCantidadFlejes(scanner);

                    try {
                        Fleje fleje = flejeService.crearFleje("Fleje", lados, varilla, cantidad);
                        flejeRepository.guardarFleje(fleje);
                        double total = fleje.calcularPago();
                        double ganancias = total * 0.07;

                        InterfazUsuario.mostrarResultado(total);
                        InterfazUsuario.mostrarGanancias(ganancias);

                        // Acumular los totales
                        totalAcumulado += total;
                        gananciasTotales += ganancias;

                        // Mostrar el acumulado de cada cálculo
                        InterfazUsuario.mostrarAcumuladoDeFleje(totalAcumulado, gananciasTotales);

                    } catch (IllegalArgumentException e) {
                        InterfazUsuario.mostrarError(e.getMessage());
                    }
                    break;

                case 2:
                    // Mostrar los totales acumulados
                    InterfazUsuario.mostrarTotalesAcumulados(totalAcumulado, gananciasTotales);
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    InterfazUsuario.mostrarError("Opción no válida.");
            }
        }
    }
}