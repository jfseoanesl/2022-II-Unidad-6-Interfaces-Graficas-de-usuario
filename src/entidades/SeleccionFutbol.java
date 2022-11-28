package entidades;

import java.io.Serializable;

/**
 *
 * @author Jairo F
 */
public class SeleccionFutbol implements Serializable {
    private int idSeleccion;
    private String nombre;
    private int rankingFifa;
    private boolean clasificada;
    private double pRendimiento;
    private Confederacion confederacion;

    public SeleccionFutbol() {
    }

    public SeleccionFutbol(int idSeleccion, String nombre, int ranking, boolean clasificada, double pRendimiento, Confederacion confederacion) {
        this.idSeleccion = idSeleccion;
        this.nombre = nombre;
        this.rankingFifa = ranking;
        this.clasificada = clasificada;
        this.pRendimiento = pRendimiento;
        this.confederacion = confederacion;
    }

    /**
     * @return the idSeleccion
     */
    public int getIdSeleccion() {
        return idSeleccion;
    }

    /**
     * @param idSeleccion the idSeleccion to set
     */
    public void setIdSeleccion(int idSeleccion) {
        this.idSeleccion = idSeleccion;
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
     * @return the nJugadoresConvocados
     */
    

    /**
     * @return the clasificada
     */
    public boolean isClasificada() {
        return clasificada;
    }

    /**
     * @param clasificada the clasificada to set
     */
    public void setClasificada(boolean clasificada) {
        this.clasificada = clasificada;
    }

    /**
     * @return the pRendimiento
     */
    public double getpRendimiento() {
        return pRendimiento;
    }

    /**
     * @param pRendimiento the pRendimiento to set
     */
    public void setpRendimiento(double pRendimiento) {
        this.pRendimiento = pRendimiento;
    }

    /**
     * @return the confederacion
     */
    public Confederacion getConfederacion() {
        return confederacion;
    }

    /**
     * @param confederacion the confederacion to set
     */
    public void setConfederacion(Confederacion confederacion) {
        this.confederacion = confederacion;
    }

    @Override
    public String toString() {
        return "SeleccionFutbol{" + "idSeleccion=" + idSeleccion + ", nombre=" + nombre + ", Ranking Fifa=" + rankingFifa + ", clasificada=" + clasificada + ", pRendimiento=" + pRendimiento + ", confederacion=" + confederacion + '}';
    }

    /**
     * @return the rankingFifa
     */
    public int getRankingFifa() {
        return rankingFifa;
    }

    /**
     * @param rankingFifa the rankingFifa to set
     */
    public void setRankingFifa(int rankingFifa) {
        this.rankingFifa = rankingFifa;
    }
    
    
}
