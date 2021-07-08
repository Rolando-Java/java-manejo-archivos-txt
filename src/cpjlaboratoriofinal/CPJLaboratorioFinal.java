package cpjlaboratoriofinal;

import mx.com.gm.peliculas.negocio.CatalogoPeliculas;
import mx.com.gm.peliculas.negocio.CatalogoPeliculasImpl;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class CPJLaboratorioFinal {


    private static final String RUTA_ARCHIVO = "C:\\catalogopeliculas\\peliculas.txt";
    private static Scanner teclado = new Scanner(System.in);
    private CatalogoPeliculas catalogoPeliculas;

    public CPJLaboratorioFinal() {
        this.catalogoPeliculas = new CatalogoPeliculasImpl();
    }

    private String generarMenu() {
        return new StringBuilder()
                .append("1.- Iniciar catalogo películas").append("\n")
                .append("2.- Agregar película").append("\n")
                .append("3.- Listar Películas").append("\n")
                .append("4.- Buscar Película").append("\n")
                .append("5.- Eliminar catalogo películas").append("\n")
                .append("0.- Salir").append("\n").toString();
    }

    public static void main(String... args) {

        CPJLaboratorioFinal laboratorioFinal = new CPJLaboratorioFinal();

        try {
            String nombrePelicula;
            int opcion;
            BUCLE:
            do {
                System.out.println(laboratorioFinal.generarMenu());
                System.out.print("Ingrese una opcion: ");
                opcion = Integer.parseInt(teclado.nextLine());

                switch (opcion) {
                    case 1 -> laboratorioFinal.catalogoPeliculas.iniciarArchivo(RUTA_ARCHIVO);
                    case 2 -> {
                        System.out.print("Ingrese la pelicula a agregar: ");
                        nombrePelicula = teclado.nextLine();
                        laboratorioFinal.catalogoPeliculas.agregarPelicula(nombrePelicula, RUTA_ARCHIVO);
                    }
                    case 3 -> laboratorioFinal.catalogoPeliculas.listarPeliculas(RUTA_ARCHIVO);
                    case 4 -> {
                        System.out.print("Ingrese la pelicula a buscar: ");
                        nombrePelicula = teclado.nextLine();
                        laboratorioFinal.catalogoPeliculas.buscarPelicula(RUTA_ARCHIVO, nombrePelicula);
                    }
                    case 5 -> laboratorioFinal.catalogoPeliculas.eliminarArchivo(RUTA_ARCHIVO);
                    case 0 -> {
                        break BUCLE;
                    }
                    default -> System.out.println("Ingrese una opcion correcta por favor!!");

                }
            } while (opcion != 0);

        } catch (NumberFormatException ex) {
            System.out.println("Ingrese un numero por favor!!");
            main(args);
        }
    }
}