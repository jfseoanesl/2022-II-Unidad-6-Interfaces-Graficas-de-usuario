package vista;

import entidades.Confederacion;
import entidades.SeleccionFutbol;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import modelo.GestionConfederacion;
import modelo.GestionSeleccionFutbol;

/**
 *
 * @author Jairo F
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        Confederacion conmebol = new Confederacion("Conmebol", "Sudamerica");
        Confederacion uefa = new Confederacion("Uefa", "Europa");
        Confederacion concacaf = new Confederacion("Concacaf", "Centroamerica, norteamerica y caribe");

        SeleccionFutbol argentina = new SeleccionFutbol(1, "Argentina", 24, true, 0.7, conmebol);
        SeleccionFutbol francia = new SeleccionFutbol(2, "Franciaa", 22, true, 0.8, uefa);
        SeleccionFutbol mexico = new SeleccionFutbol(3, "Mexico", 26, true, 0.5, concacaf);
        SeleccionFutbol colombia = new SeleccionFutbol(4, "Colombia", 22, false, 0.5, conmebol);

        GestionSeleccionFutbol modeloSeleccion = new GestionSeleccionFutbol();
        GestionConfederacion modeloConfederacion = new GestionConfederacion();

        try {
            
            
            // escritura de archivo
            modeloConfederacion.registrarConfederacion(conmebol);
            modeloConfederacion.registrarConfederacion(uefa);
            modeloConfederacion.registrarConfederacion(concacaf);
            
            modeloSeleccion.registrarSeleccion(argentina);
            modeloSeleccion.registrarSeleccion(francia);
            modeloSeleccion.registrarSeleccion(mexico);
            modeloSeleccion.registrarSeleccion(colombia);

            //lectura de archivo
            ArrayList<SeleccionFutbol> lista = modeloSeleccion.leerSelecciones();
            for (SeleccionFutbol s : lista) {
                System.out.println(s);
            }

            //busqueda por id
            try {
                SeleccionFutbol buscada = modeloSeleccion.buscarSeleccionPorId(1);
                System.out.println("");
                System.out.println(buscada);
            } catch (NoSuchElementException nse) {
                System.out.println("");
                System.out.println(nse.getMessage());
            }
            
            //busqueda por confederacion
            System.out.println("");
            lista = modeloSeleccion.buscarSeleccionesByConfederacion(conmebol);
            for (SeleccionFutbol s : lista) {
                System.out.println(s);
            }

        } catch (IOException ioe) {
            System.out.println("Excepcion: " + ioe.getMessage());
        }

    }

}
