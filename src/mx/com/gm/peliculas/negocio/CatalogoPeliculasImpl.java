package mx.com.gm.peliculas.negocio;

import mx.com.gm.peliculas.datos.AccesoDatos;
import mx.com.gm.peliculas.datos.AccesoDatosPeliculaImpl;
import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;

public class CatalogoPeliculasImpl implements CatalogoPeliculas {

    private AccesoDatos<Pelicula> datos;

    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosPeliculaImpl();
    }

    @Override
    public void agregarPelicula(String nombrePelicula, String nombreArchivo) {
        try{
            if(datos.existe(nombreArchivo)) datos.escribir(new Pelicula(nombrePelicula),nombreArchivo,Boolean.TRUE);
        } catch(AccesoDatosEx ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void listarPeliculas(String nombreArchivo) {
        try{
            if(datos.existe(nombreArchivo)) datos.listar(nombreArchivo).stream().forEach(System.out::println);
        } catch(AccesoDatosEx ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void buscarPelicula(String nombreArchivo, String buscar) {
        try {
            String pelicula = "";
            if(datos.existe(nombreArchivo)) pelicula = datos.buscar(nombreArchivo, buscar);
            System.out.println("Pelicula: " + pelicula);
        } catch(AccesoDatosEx ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void iniciarArchivo(String nombreArchivo) {
        try{
            datos.crear(nombreArchivo);
        } catch(AccesoDatosEx ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void eliminarArchivo(String nombreArchivo) {
        try{
            datos.borrar(nombreArchivo);
        } catch(AccesoDatosEx ex) {
            System.err.println(ex.getMessage());
        }
    }
}
