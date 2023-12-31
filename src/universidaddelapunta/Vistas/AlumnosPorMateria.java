
package universidaddelapunta.Vistas;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import universidaddelapunta.AccesoADatos.InscripcionData;
import universidaddelapunta.Entidades.Materia;
import universidaddelapunta.AccesoADatos.materiaData;
import universidaddelapunta.Entidades.Alumno;

/**
 *
 * @author carri
 */
public class AlumnosPorMateria extends javax.swing.JInternalFrame {
    private Connection con;
    private InscripcionData idata;
    
    private DefaultTableModel modelo= new DefaultTableModel(){
        public boolean isCellEditable(int f, int c){
            return false;
        }
        //
    };
   
   
    public AlumnosPorMateria() {
        initComponents();
        comboBox();
        modificarTabla();
        llenarTabla();
        borrarlista();
        //
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBMateria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBSalir = new javax.swing.JButton();

        setClosable(true);
        setPreferredSize(new java.awt.Dimension(570, 400));
        setRequestFocusEnabled(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Listado de Alumnos por Materia");

        jLabel2.setText("Seleccione una Materia");

        jCBMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBMateriaActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBSalir)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel2)
                                .addGap(34, 34, 34)
                                .addComponent(jCBMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jBSalir)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBMateriaActionPerformed
        
        llenarTabla();
    }//GEN-LAST:event_jCBMateriaActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSalir;
    private javax.swing.JComboBox<String> jCBMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private void modificarTabla(){
        modelo.addColumn("ID");
        modelo.addColumn("Dni");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nombre");
        jTable1.setModel(modelo);
        //
        
    }
    private void comboBox(){
        materiaData mat= new materiaData();
        for (Materia m : mat.listarMaterias()) {
            String i=m.getNombre();
            jCBMateria.addItem(i);
        }
        //
    }
    
    public void borrarlista(){
        int a=modelo.getRowCount()-1;
            for(int i=a;i>=0;i--){
             modelo.removeRow(i);
            }
            //
    }
 
    private void llenarTabla() {
    borrarlista();
    materiaData mat=new materiaData();
    InscripcionData i=new InscripcionData();
    String  materiaSeleccionada=(String) jCBMateria.getSelectedItem();
    ArrayList <Alumno> alumnos=new ArrayList();
             
      for(Materia m: mat.listarMaterias()){
                
           if(m.getNombre().equalsIgnoreCase(materiaSeleccionada)){
             
               for(Alumno a: i.obtenerAlumnosXMateria(m.getIdMateria())){
                
                modelo.addRow(new Object[]{
                  a.getIdAlumno(),a.getDni(),a.getApellido(),a.getNombre()  });
               }
            }
    }
    
   
}
}