package mx.com.gm.peliculas.datos;

import mx.com.gm.peliculas.domain.Pelicula;
import mx.com.gm.peliculas.excepciones.AccesoDatosEx;
import mx.com.gm.peliculas.excepciones.EscrituraDatosEx;
import mx.com.gm.peliculas.excepciones.LecturaDatosEx;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosPeliculaImpl implements AccesoDatos<Pelicula> {


    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File file = new File(nombreArchivo);
        try {
            return file.exists();
        } catch (SecurityException ex) {
            throw new AccesoDatosEx("Error al acceder al fichero: " + ex.getMessage());
        }
    }

    @Override
    public List<Pelicula> listar(String nombreArchivo) throws LecturaDatosEx {
        File file = new File(nombreArchivo);
        List<Pelicula> peliculasList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String linea = "";
            while ((linea = bufferedReader.readLine()) != null) {
                peliculasList.add(new Pelicula(linea));
            }
        } catch (IOException ex) {
            throw new LecturaDatosEx("Error al leer el contenido del fichero: " + ex.getMessage());
        }
        return peliculasList;
    }

    @Override
    public void escribir(Pelicula elemento, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File file = new File(nombreArchivo);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, anexar))) {
            bufferedWriter.append(elemento.getNombre()).append("\n");
        } catch (IOException ex) {
            throw new EscrituraDatosEx("Error al escribir contenido sobre el fichero: " + ex.getMessage());
        }
    }

    @Override
    public String buscar(String nombreArchivo, String buscar) throws LecturaDatosEx {
        File file = new File(nombreArchivo);
        String valor = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.equalsIgnoreCase(buscar)) {
                    valor = linea;
                    break;
                }
            }
        } catch (IOException ex) {
            throw new LecturaDatosEx("Error al leer el contenido del fichero: " + ex.getMessage());
        }
        return valor;
    }

    @Override
    public void crear(String nombreArchivo) throws AccesoDatosEx {
        File file = new File(nombreArchivo);
        try {
            if (file.createNewFile()) System.out.println("Se ha creado el fichero");
            else System.out.println("No se ha podido crear el fichero, puede que e fichero ya exista");
        } catch (IOException ex) {
            throw new AccesoDatosEx("Error al crear el fichero: " + ex.getMessage());
        } catch (SecurityException ex) {
            throw new AccesoDatosEx("Error al acceder al fichero: " + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreArchivo) throws AccesoDatosEx {
        File file = new File(nombreArchivo);
        try {
            if (file.delete()) System.out.println("Se ha eliminado el archivo");
            else System.out.println("No se ha podido eliminar el fichero, puedo que el fichero no exista");
        } catch (SecurityException ex) {
            throw new AccesoDatosEx("Error al acceder al fichero: " + ex.getMessage());
        }
    }
}
