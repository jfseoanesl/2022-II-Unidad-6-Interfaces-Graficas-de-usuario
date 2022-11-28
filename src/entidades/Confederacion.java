package entidades;

import java.io.Serializable;

/**
 *
 * @author Jairo F
 */
public class Confederacion implements Serializable {
    private static int id=0;
    private int idConfederacion;
    private String nombre;
    private String paises;

    public Confederacion() {
    }

    public Confederacion(String nombre, String paises) {
        this.nombre = nombre;
        this.paises = paises;
        id++;
        this.idConfederacion=id;
    }

    public Confederacion(int idConfederacion, String nombre, String paises) {
        this.idConfederacion = idConfederacion;
        this.nombre = nombre;
        this.paises = paises;
    }

    /**
     * @return the idConfederacion
     */
    public  int getIdConfederacion() {
        return idConfederacion;
    }

    /**
     * @param aIdConfederacion the idConfederacion to set
     */
    public void setIdConfederacion(int aIdConfederacion) {
        idConfederacion = aIdConfederacion;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the paises
     */
    public String getPaises() {
        return paises;
    }

    /**
     * @param paises the paises to set
     */
    public void setPaises(String paises) {
        this.paises = paises;
    }

    @Override
    public String toString() {
        return "Confederacion{" + "nombre=" + nombre + ", paises=" + paises + '}';
    }
    
    
}
