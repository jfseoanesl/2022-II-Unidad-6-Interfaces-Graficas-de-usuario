package vista;

import entidades.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import modelo.*;

/**
 *
 * @author Jairo F
 */
public class GuiConsulta extends JDialog {
    private Container contenedor;
    
    private JPanel panelBusqueda, panelPpal, panelLogo;
    private JScrollPane panelResultado;
    
    private RowSorter ordenadorTabla;
    
    private JLabel lbBusqueda, lbLogo;
    private JComboBox cmbBusqueda;
    private JTable tabla;
    private JButton boton;
    
    private DefaultTableModel modeloTabla;
    private String titulos[] = {"Id", "Nombre", "Ranking", "Rendimiento","Clasificada"};
    private String contenido[][]={null,null};
    
    private GestionConfederacion modeloConfe;
    private GestionSeleccionFutbol modeloSele;

    public GuiConsulta(Frame owner, boolean modal) {
        super(owner, modal);
        this.modeloConfe = new GestionConfederacion();
        this.modeloSele = new GestionSeleccionFutbol();
        this.setTitle("Consulta de Selecciones registradas - Version 1");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.iniciarComponentes();
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void iniciarComponentes(){
        this.contenedor = this.getContentPane();
        
        this.panelPpal = new JPanel(new BorderLayout());
        this.panelPpal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        this.setLayout(new BorderLayout());
        this.iniciarPanelBusqueda();
        this.iniciarPenalResultado();
        this.iniciarPanelLogo();
        this.contenedor.add(this.panelPpal);
        this.cargarConfederaciones();
    }
    
    public void iniciarPanelLogo(){
        this.panelLogo = new JPanel();
        this.panelLogo.setBorder(BorderFactory.createTitledBorder("Logotipo"));
        ImageIcon image = new ImageIcon("./src/imagenes/not-image.jpeg");
        this.lbLogo = new JLabel(image);
        this.panelLogo.add(this.lbLogo);
        this.panelPpal.add(this.panelLogo, BorderLayout.EAST);
        
    }
    
    
    public void iniciarPanelBusqueda(){
        this.panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.panelBusqueda.setBorder(BorderFactory.createTitledBorder("Filtro de busqueda"));
        
        this.lbBusqueda=new JLabel("Confederacion: ");
        this.cmbBusqueda = new JComboBox();
        this.cmbBusqueda.addActionListener(new EventoClickBusqueda());
        
        this.panelBusqueda.add(this.lbBusqueda);
        this.panelBusqueda.add(this.cmbBusqueda);
      
        
        this.panelPpal.add(this.panelBusqueda, BorderLayout.NORTH);
    
    }
    public void cargarConfederaciones() {
        try {
            ArrayList<Confederacion> lista = this.modeloConfe.leerConfederaciones();
            for(Confederacion c: lista){
                this.cmbBusqueda.addItem(c.getNombre());
            }
        } catch (IOException io) {
            JOptionPane.showMessageDialog(this, io, "Error de archivo", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void iniciarPenalResultado(){
        
        this.panelResultado = new JScrollPane();
        this.panelResultado.setBorder(BorderFactory.createTitledBorder("Resultados de busqueda"));
        this.tabla = new JTable();
        this.modeloTabla= new DefaultTableModel(this.contenido, this.titulos);
        this.tabla.setModel(this.modeloTabla);
        
        //permite que la tabla ordene sus valores segun la columna a la que se da click
        this.ordenadorTabla = new TableRowSorter(this.modeloTabla);
        this.tabla.setRowSorter(ordenadorTabla);
        
        this.panelResultado.setViewportView(this.tabla);
        
        this.panelPpal.add(this.panelResultado);
        
    }
    
    public void buscarSelecciones() throws IOException{
        String confe = this.cmbBusqueda.getSelectedItem().toString();
        Confederacion confederacion = this.modeloConfe.buscarConfederacionPorNombre(confe);
        String ruta = "./src/imagenes/"+confederacion.getIdConfederacion()+".png";
        File archivo = new File(ruta);
        ImageIcon image;
        if(archivo.exists()){
            image = new ImageIcon(ruta);
        }
        else{
            image = new ImageIcon("./src/imagenes/not-image.jpeg");
        }        
        this.lbLogo.setIcon(image);
        
        ArrayList<SeleccionFutbol> lista = this.modeloSele.buscarSeleccionesByConfederacion(confederacion);
        String matriz[][]=new String[lista.size()][];
        int filas=0;
        for(SeleccionFutbol s: lista){
            
            String registro[]={String.valueOf(s.getIdSeleccion()), s.getNombre(), ""+s.getRankingFifa(), String.valueOf(s.getpRendimiento()), String.valueOf(s.isClasificada())};
            matriz[filas]=registro;
            filas++;
        }
        this.modeloTabla.setDataVector(matriz, this.titulos);
        
        
    }
    
    class EventoClickBusqueda implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {  
                buscarSelecciones();
            } catch (IOException ex) {
                 JOptionPane.showMessageDialog(null, ex, "Error de archivo", JOptionPane.ERROR_MESSAGE);
            }
        }
    
    }
    
    
    
}
