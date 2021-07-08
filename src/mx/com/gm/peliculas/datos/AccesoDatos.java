package mx.com.gm.peliculas.datos;

import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;

import java.util.List;

public interface AccesoDatos<T> {

    boolean existe(String nombreArchivo) throws AccesoDatosEx;

    List<T> listar(String nombreArchivo) throws AccesoDatosEx;

    void escribir(T elemento, String nombreArchivo, boolean anexar) throws AccesoDatosEx;

    String buscar(String nombreArchivo, String buscar) throws AccesoDatosEx;

    void crear(String nombreArchivo) throws AccesoDatosEx;

    void borrar(String nombreArchivo) throws AccesoDatosEx;

}
