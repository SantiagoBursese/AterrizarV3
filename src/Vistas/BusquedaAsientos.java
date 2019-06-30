package Vistas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.busquedas.Busqueda;
import aterrizarv2.busquedas.exceptionesBusqueda.ParametrosInsuficienteException;
import aterrizarv2.busquedas.ordenamientoBusqueda.OrdenPorPrecioDescendente;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.filtrosBusqueda.FiltroDestino;
import aterrizarv2.filtrosBusqueda.FiltroFecha;
import aterrizarv2.filtrosBusqueda.FiltroOrigen;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.AsientoVueloFullData;
import controladorVistas.ControladorBuscarAsientos;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import viewModel.BuscarAsientoViewModel;

public class BusquedaAsientos extends javax.swing.JFrame {
    private BuscarAsientoViewModel modelo;
    private ControladorBuscarAsientos controlador;
	 
    public BusquedaAsientos(AterrizarV2 pagina,Usuario usuarioLogeado) {
        this.agregarFuncionalidadBotonBuscar(new ObtieneBusquedas());
        this.agregarFuncionalidadBotonComprar(new CompraAsiento());
        this.agregarFuncionalidadBotonReserva(new ReservaAsiento());
        this.actualizador = actualizador;
    }

    public BusquedaAsientos(BuscarAsientoViewModel buscarAsientoViewModel) {
        this.setTitle("Aterrizar.com");
        this.setResizable(false);
        initComponents();
        this.modelo= buscarAsientoViewModel;
        crearController();
        botonCerrar.addActionListener(e -> cerrarVentana());
    }
    
    private void crearController() {
        controlador = new ControladorBuscarAsientos(modelo);
        
    }
    
    
    public void rellenarConDisponibles(List<AsientoVueloFullData> disponibles){
        disponibles.forEach(asiento -> {
            String codigoAsiento = asiento.getAsiento().getCodigo().getCodigo();
            String codigoVuelo = asiento.getAsiento().getCodigo().getNumeroVuelo();
            String numeroAsiento = asiento.getAsiento().getCodigo().getNumeroAsiento();
            String aerolinea = pagina.obtenerAerolineaAsiento(codigoAsiento);
            String precio = Double.toString(asiento.getAsiento().getPrecio().getPrecioAsiento());
            String ubicacion = asiento.getAsiento().getUbicacion().ubicacionFormatoString();
            String clase = asiento.getAsiento().getClase().claseFormatoString();
            busqueda.rellenarTablaConDisponibles(aerolinea, codigoVuelo, numeroAsiento, precio, ubicacion, clase);
        });
    }
    

    public List<AsientoVueloFullData> obtenerAsientosDisponibles(String origen, String destino, String fecha) throws FormatoFechaIncorrectoException, FechaNoValidaException, ParametrosInsuficienteException{
        FiltroOrigen filtroOrigen = new FiltroOrigen(origen);
        FiltroDestino filtroDestino = new FiltroDestino(destino);
        FiltroFecha filtroFecha = new FiltroFecha(new FechaFlexible(fecha));
        Busqueda busquedaRealizar = new Busqueda(filtroOrigen,filtroDestino,filtroFecha);
        return pagina.asientosCumplenParametro(usuarioBusca, busquedaRealizar, new OrdenPorPrecioDescendente());
    }


    class CompraAsiento implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(this.seSeleccionoFila()){
                try {
                    String codigoVuelo = this.obtenerCodigoVueloFilaSeleccionada();
                    String numeroAsiento = this.obtenerNumeroAsientoFilaSeleccionada();
                    String codigoAsiento= codigoVuelo + "-" + numeroAsiento;
                    Aerolinea aerolinea = pagina.obtenerAerolineaTieneAsiento(codigoAsiento);
                    aerolinea.comprarAsiento(codigoAsiento,usuarioBusca);
                    displayExitoCompra(codigoAsiento);
                    actualizador.actualizarVistas();
                    busqueda.eliminarFilaSeleccionada();
                } catch (AsientoReservadoException | CodigoAsientoException ex) {
                    displayErrorCompra(ex.getMessage());
                }
            }
            else{
                //Aca no va a llegar nunca en realidad, pero bueno, se deja 
                displayErrorCompra("No se selecciono ningun asiento a comprar.");
            }
        }
    }
    class ObtieneBusquedas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                busqueda.cambiarTextoTextField("");
                busqueda.eliminarCeldasTabla();
                String origen = busqueda.obtenerTextoOrigen();
                String destino = busqueda.obtenerTextoDestino();
                String fecha = busqueda.obtenerTextoFecha();
                List<AsientoVueloFullData> disponibles = obtenerAsientosDisponibles(origen,destino,fecha);
                if(disponibles.size() != 0){
                    rellenarConDisponibles(disponibles);
                }
                else{
                    busqueda.cambiarTextoTextField("No se encontro ningun asiento con estos parametros");
                }
            } catch (FormatoFechaIncorrectoException | FechaNoValidaException | ParametrosInsuficienteException ex) {
                busqueda.cambiarTextoTextField("No se cumple con el formato fecha");
            }
        }
    }
    
  

    
    class ReservaAsiento implements ActionListener{
        Sobrereserva sobrereserva;
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(busqueda.seSeleccionoFila()){
                String codigoVuelo = busqueda.obtenerCodigoVueloFilaSeleccionada();
                String numeroAsiento = busqueda.obtenerNumeroAsientoFilaSeleccionada();
                String codigoAsiento= codigoVuelo + "-" + numeroAsiento;
                try {
                    Aerolinea aerolinea = pagina.obtenerAerolineaTieneAsiento(codigoAsiento);
                    aerolinea.reservarAsiento(codigoAsiento, usuarioBusca);
                    displayExitoReserva(codigoAsiento);
                    actualizador.actualizarVistas();
                } catch (CodigoAsientoException ex) {
                    displayErrorReserva(ex.getMessage());
                } catch(AsientoNoDisponibleException ex){
                    displayOpcionSobrereserva(codigoAsiento);
                }
            }
            else{
                //Aca no va a llegar nunca en realidad, pero bueno, se deja 
                displayErrorReserva("No se selecciono ningun asiento a comprar.");
            }
        }
    }
        
    public void agregarFuncionalidadBotonReserva(ActionListener eventoReserva){
        botonReservar.addActionListener(eventoReserva);
    }
    
    public void agregarFuncionalidadBotonBuscar(ActionListener eventoBusqueda){
        botonBuscar.addActionListener(eventoBusqueda);
    }
    
    public void agregarFuncionalidadBotonComprar(ActionListener eventoCompra){
        botonComprar.addActionListener(eventoCompra);
    }
    
    public void cambiarTextoTextField(String texto){
        mostrarErrores.setText(texto);
 
    }
    
    public void eliminarFilaSeleccionada(){
        DefaultTableModel tb = (DefaultTableModel) resultadoBusqueda.getModel();
        tb.removeRow(resultadoBusqueda.getSelectedRow());
    }
    
    public void eliminarCeldasTabla(){
        DefaultTableModel tb = (DefaultTableModel) resultadoBusqueda.getModel();
        int a = resultadoBusqueda.getRowCount()-1;
        for (int i = a; i >= 0; i--) {           
        tb.removeRow(tb.getRowCount()-1);
        } 
    }
    
    public void rellenarTablaConDisponibles(String aerolinea, String vuelo, String nroAsiento, String precio, String ubicacion, String clase){
        DefaultTableModel modelo = (DefaultTableModel) resultadoBusqueda.getModel();
        Object[] filaAgregar = {aerolinea,vuelo,nroAsiento,precio,ubicacion,clase};
        modelo.addRow(filaAgregar);
    }
    
    public boolean seSeleccionoFila(){
        return (resultadoBusqueda.getSelectedRow() != -1);
    }
    
    
    public String obtenerCodigoVueloFilaSeleccionada(){
        return (String)resultadoBusqueda.getValueAt(resultadoBusqueda.getSelectedRow(),1);
    }
    
    public String obtenerNumeroAsientoFilaSeleccionada(){
        return (String)resultadoBusqueda.getValueAt(resultadoBusqueda.getSelectedRow(),2);
    }
    
    public String obtenerTextoOrigen(){
        return this.textoOrigen.getText();
    }
    
    public String obtenerTextoDestino(){
        return this.textoDestino.getText();
    }
    
    public String obtenerTextoFecha(){
        return this.textoFecha.getText();
    }
    
    public void cerrarVentana(){
        this.setVisible(false);
        this.dispose();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mostrarErrores = new javax.swing.JTextField();
        origen = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        destino = new javax.swing.JLabel();
        botonBuscar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        resultadoBusqueda = new javax.swing.JTable();
        botonComprar = new javax.swing.JButton();
        botonReservar = new javax.swing.JButton();
        botonCerrar = new javax.swing.JButton();
        textoOrigen = new javax.swing.JTextField();
        textoDestino = new javax.swing.JTextField();
        textoFecha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mostrarErrores.setEditable(false);
        mostrarErrores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarErroresActionPerformed(evt);
            }
        });

        origen.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        origen.setText("Origen:");

        fecha.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        fecha.setText("Fecha:");

        destino.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        destino.setText("Destino:");

        botonBuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonBuscar.setText("Buscar");

        resultadoBusqueda = new javax.swing.JTable(){
            public boolean isCellEditable(int i, int n){
                return false;
            }
        };
        resultadoBusqueda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aerolinea", "Vuelo", "Asiento", "Precio", "Ubicacion", "Clase"
            }
        ));
        resultadoBusqueda.setFocusable(false);
        resultadoBusqueda.getTableHeader().setResizingAllowed(false);
        resultadoBusqueda.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(resultadoBusqueda);

        botonComprar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonComprar.setText("Comprar");

        botonReservar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonReservar.setText("Reservar");
        botonReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReservarActionPerformed(evt);
            }
        });

        botonCerrar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        botonCerrar.setText("Cerrar");

        textoOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoOrigenActionPerformed(evt);
            }
        });
        textoOrigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoOrigenKeyTyped(evt);
            }
        });

        textoDestino.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoDestinoKeyTyped(evt);
            }
        });

        textoFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textoFechaActionPerformed(evt);
            }
        });
        textoFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textoFechaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botonBuscar)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fecha, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(origen))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(textoOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(textoFecha))
                                .addGap(43, 43, 43)
                                .addComponent(destino)
                                .addGap(18, 18, 18)
                                .addComponent(textoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(mostrarErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonComprar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonReservar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonCerrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(mostrarErrores, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(origen)
                    .addComponent(destino)
                    .addComponent(textoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha)
                    .addComponent(textoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(botonBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonComprar)
                    .addComponent(botonReservar)
                    .addComponent(botonCerrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mostrarErroresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarErroresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mostrarErroresActionPerformed

    private void botonReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReservarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonReservarActionPerformed

    private void textoOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoOrigenActionPerformed

    private void textoFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textoFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textoFechaActionPerformed

    private void textoOrigenKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoOrigenKeyTyped
        int maxCaracteres = 3;
        char c = evt.getKeyChar();
        if(textoOrigen.getText().length() >= maxCaracteres){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if(c<'A' || c>'Z'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_textoOrigenKeyTyped

    private void textoDestinoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoDestinoKeyTyped
        int maxCaracteres = 3;
        char c = evt.getKeyChar();
        if(textoDestino.getText().length() >= maxCaracteres){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
         if(c<'A' || c>'Z'){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_textoDestinoKeyTyped

    private void textoFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFechaKeyTyped
        int maxCaracteres = 10;
        char c = evt.getKeyChar();
        if(textoFecha.getText().length() >= maxCaracteres){
            evt.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }//GEN-LAST:event_textoFechaKeyTyped
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BusquedaAsientos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBuscar;
    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonComprar;
    private javax.swing.JButton botonReservar;
    private javax.swing.JLabel destino;
    private javax.swing.JLabel fecha;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField mostrarErrores;
    private javax.swing.JLabel origen;
    private javax.swing.JTable resultadoBusqueda;
    private javax.swing.JTextField textoDestino;
    private javax.swing.JTextField textoFecha;
    private javax.swing.JTextField textoOrigen;
    // End of variables declaration//GEN-END:variables
    public void display() {
        this.setVisible(true);
        this.setLocation(500, 200);		
    }
}

