package datos;

import entidades.Confederacion;
import entidades.SeleccionFutbol;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Jairo F
 */
public class CrudSeleccionTexto extends CrudTexto implements ICrudSeleccion {

    public CrudSeleccionTexto() {
        this("SeleccionesCatar2022.dat");
    }

    public CrudSeleccionTexto(String name) {
        this.manejadorArchivo = new File(name);
    }

    
    @Override
    public void registrarSeleccion(SeleccionFutbol s) throws IOException {
        PrintWriter pw = null;
        try {
            this.modoEscritura = new FileWriter(this.manejadorArchivo, true);
            pw = new PrintWriter(this.modoEscritura);
            pw.printf("%d;%s;%d;%s;%.2f;%s%n",
                    s.getIdSeleccion(),
                    s.getNombre(),
                    s.getRankingFifa(),
                    s.isClasificada(),
                    s.getpRendimiento(),
                    s.getConfederacion().getIdConfederacion());

        } catch (FileNotFoundException fne) {
            throw new IOException("Error al escribir en el archivo");
        } catch (IOException ioe) {
            throw new IOException("Error al abrir archvio en modo escritura");
        } catch (SecurityException se) {
            throw new IOException("No tiene privilegios para abrir el archivo");
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    private SeleccionFutbol cargarSeleccion(String dato[])throws IOException{
        int id = Integer.valueOf(dato[0]);
        String nombre= dato[1];
        int ranking = Integer.valueOf(dato[2]);
        boolean clasificada = Boolean.valueOf(dato[3]);
        double rendimiento = Double.valueOf(dato[4].replace(',', '.'));
        int idConfe = Integer.valueOf(dato[5]);
        CrudConfederacionTexto aConfe = new CrudConfederacionTexto();
        Confederacion confederacion = aConfe.buscarConfederacionPorId(idConfe);
        SeleccionFutbol s = new SeleccionFutbol(id, nombre, ranking, clasificada, rendimiento, confederacion);
        return s;
    }

    @Override
    public ArrayList<SeleccionFutbol> leerSelecciones() throws IOException {
        ArrayList<SeleccionFutbol> lista = null;
        if(!this.manejadorArchivo.exists()){
           lista = new ArrayList();
           return lista;
        }
        try {
            this.modoLectura = new Scanner(this.manejadorArchivo);
            lista = new ArrayList();
            while (this.modoLectura.hasNext()) {
                String dato[] = this.modoLectura.nextLine().split(";");
                SeleccionFutbol s = this.cargarSeleccion(dato);
                lista.add(s);
            }
            return lista;
        } catch (FileNotFoundException fne) {
            throw new IOException("Error al abrir archivo en modo lectura");
        } catch (SecurityException se) {
            throw new IOException("No tiene privilegios para leer el archivo");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

    @Override
    public SeleccionFutbol buscarSeleccionPorId(int id) throws IOException {
        
        SeleccionFutbol buscada = null;
        try {
            this.modoLectura = new Scanner(this.manejadorArchivo);
            while (this.modoLectura.hasNext()) {
                String dato[] = this.modoLectura.nextLine().split(";");
                SeleccionFutbol s = this.cargarSeleccion(dato);
                if(s.getIdSeleccion()==id){
                    buscada = s;
                    break;
                }
            }
            if(buscada == null)
                throw new NoSuchElementException("la seleccion no esta registrada");
            
            return buscada;
            
        } catch (FileNotFoundException fne) {
            throw new IOException("Error al abrir archivo en modo lectura");
        } catch (SecurityException se) {
            throw new IOException("No tiene privilegios para leer el archivo");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

    @Override
    public ArrayList<SeleccionFutbol> buscarSeleccionesByConfederacion(Confederacion c) throws IOException {
        ArrayList<SeleccionFutbol> buscadas = new ArrayList();
        try {
            this.modoLectura = new Scanner(this.manejadorArchivo);
            while (this.modoLectura.hasNext()) {
                String dato[] = this.modoLectura.nextLine().split(";");
                SeleccionFutbol s = this.cargarSeleccion(dato);
                if(s.getConfederacion().getIdConfederacion()==c.getIdConfederacion()){
                    buscadas.add(s);
                }
            }
           
            return buscadas;
            
        } catch (FileNotFoundException fne) {
            throw new IOException("Error al abrir archivo en modo lectura");
        } catch (SecurityException se) {
            throw new IOException("No tiene privilegios para leer el archivo");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

}
