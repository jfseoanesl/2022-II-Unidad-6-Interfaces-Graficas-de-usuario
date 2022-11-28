package datos;

import entidades.Confederacion;
import entidades.SeleccionFutbol;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Jairo F
 */
public class CrudConfederacionTexto extends CrudTexto implements ICrudConfederacion {
    

    public CrudConfederacionTexto() {
        this("Confederaciones.dat");
    }

    public CrudConfederacionTexto(String name) {
        this.manejadorArchivo = new File(name);
    }

    
    @Override
    public void registrarConfederacion(Confederacion c) throws IOException {
        PrintWriter pw = null;
        try {
            this.modoEscritura = new FileWriter(this.manejadorArchivo, true);
            pw = new PrintWriter(this.modoEscritura);
            pw.printf("%d;%s;%s%n",
                    c.getIdConfederacion(),
                    c.getNombre(),
                    c.getPaises());

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

    private Confederacion cargarConfederacion(String dato[]){
        int id = Integer.valueOf(dato[0]);
        String nombre= dato[1];
        String paises = dato[2];
        Confederacion confederacion = new Confederacion(id, nombre, paises);
        return confederacion;
    }
    
    @Override
    public ArrayList<Confederacion> leerConfederaciones() throws IOException {
       
        ArrayList<Confederacion> lista;
        if(!this.manejadorArchivo.exists()){
            lista = new ArrayList();
            return lista;
        }
           
         
        try {
            this.modoLectura = new Scanner(this.manejadorArchivo);
            lista = new ArrayList();
            while (this.modoLectura.hasNext()) {
                String dato[] = this.modoLectura.nextLine().split(";");
                Confederacion c = this.cargarConfederacion(dato);
                lista.add(c);
            }
            return lista;
        } catch (FileNotFoundException fne) {
            throw new IOException("Error al abrir archivo en elctura");
        } catch (SecurityException se) {
            throw new IOException("No tiene privilegios para leer el archivo");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

    @Override
    public Confederacion buscarConfederacionPorId(int id) throws IOException {
       Confederacion buscada = null;
        try {
            this.modoLectura = new Scanner(this.manejadorArchivo);
            while (this.modoLectura.hasNext()) {
                String dato[] = this.modoLectura.nextLine().split(";");
                Confederacion c = this.cargarConfederacion(dato);
                if(c.getIdConfederacion()==id){
                    buscada = c;
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
    
    
}
