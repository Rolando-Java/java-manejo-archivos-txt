package mx.com.gm.peliculas.negocio;

public interface CatalogoPeliculas {

    void agregarPelicula(String nombrePelicula, String nombreArchivo);
    void listarPeliculas(String nombreArchivo);
    void buscarPelicula(String nombreArchivo, String buscar);
    void iniciarArchivo(String nombreArchivo);
    void eliminarArchivo(String nombreArchivo);

}
