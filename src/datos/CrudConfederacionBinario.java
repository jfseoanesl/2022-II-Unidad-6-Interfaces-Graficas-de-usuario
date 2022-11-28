package datos;

import entidades.Confederacion;
import entidades.SeleccionFutbol;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Jairo F
 */
public class CrudConfederacionBinario extends CrudBinario implements ICrudConfederacion {
    
    public CrudConfederacionBinario() {
        this("Confederaciones.bin");
    }

    public CrudConfederacionBinario(String name) {
        this.manejadorArchivo = new File(name);
    }

    /**
     * @return the manejadorArchivo
     */
    public File getManejadorArchivo() {
        return manejadorArchivo;
    }

    /**
     * @param manejadorArchivo the manejadorArchivo to set
     */
    public void setManejadorArchivo(File manejadorArchivo) {
        this.manejadorArchivo = manejadorArchivo;
    }

    /**
     * @return the modoLectura
     */
    public FileInputStream getModoLectura() {
        return modoLectura;
    }

    /**
     * @param modoLectura the modoLectura to set
     */
    public void setModoLectura(FileInputStream modoLectura) {
        this.modoLectura = modoLectura;
    }

    /**
     * @return the modoEscritura
     */
    public FileOutputStream getModoEscritura() {
        return modoEscritura;
    }

    /**
     * @param modoEscritura the modoEscritura to set
     */
    public void setModoEscritura(FileOutputStream modoEscritura) {
        this.modoEscritura = modoEscritura;
    }
    
    private void guardar(ArrayList<Confederacion> lista)throws IOException{
        ObjectOutputStream oos = null;
        try {
            this.modoEscritura = new FileOutputStream(this.manejadorArchivo);
            oos = new ObjectOutputStream(this.modoEscritura);
            oos.writeObject(lista);

        } catch (FileNotFoundException fne) {
            throw new IOException("Error al abrir archvio modo escritura");
        } catch (SecurityException se) {
            throw new IOException("No tiene privilegion para abrir archvio en modo escritura");
        } catch (NullPointerException np) {
            throw new IOException("El archvio en modo escritura en null");
        } catch (IOException ioe) {
            throw new IOException("Error al crear objeto de escritura");
        } finally {
            if (oos != null) {
                oos.close();
            }
        }
    }
    
    private ArrayList<Confederacion> leer()throws IOException{
        ObjectInputStream ois = null;
        ArrayList<Confederacion> lista = null;
        
        if(!this.manejadorArchivo.exists()){
            lista = new ArrayList();
            return lista;
        }
        
        try {
            this.modoLectura = new FileInputStream(this.manejadorArchivo);
            lista = new ArrayList();
            ois = new ObjectInputStream(this.modoLectura);
            lista = (ArrayList<Confederacion>) ois.readObject();
            return lista;

        } catch (FileNotFoundException fne) {
            throw new IOException("Error al abrir archvio modo lectura");
        } catch (SecurityException se) {
            throw new IOException("No tiene privilegion para abrir archvio en modo lectura");
        } catch (NullPointerException np) {
            throw new IOException("El archvio en modo lectura es null");
        } catch (IOException ioe) {
            throw new IOException("Error al crear objeto lector");
        } catch (ClassNotFoundException ex) {
            throw new IOException("Error al leer objeto");
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
    }
    
    @Override
    public void registrarConfederacion(Confederacion c) throws IOException {
        ArrayList<Confederacion> lista = this.leer();
        lista.add(c);
        this.guardar(lista);
    }

    @Override
    public ArrayList<Confederacion> leerConfederaciones() throws IOException {
        return this.leer();
    }

    @Override
    public Confederacion buscarConfederacionPorId(int id) throws IOException {
        ArrayList<Confederacion> lista = this.leer();
        Confederacion buscada = null;
        for(Confederacion s: lista){
            if(s.getIdConfederacion()==id){
                buscada = s;
                break;
            }
        }
        
        if(buscada==null)
            throw new NoSuchElementException("Confederacion no registrada");
        
        return buscada;
    }
    
    
    
}
