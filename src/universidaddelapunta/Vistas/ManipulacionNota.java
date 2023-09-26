/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidaddelapunta.Vistas;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import universidaddelapunta.AccesoADatos.AlumnoData;
import universidaddelapunta.AccesoADatos.InscripcionData;
import universidaddelapunta.AccesoADatos.conexion;
import universidaddelapunta.AccesoADatos.materiaData;
import universidaddelapunta.Entidades.Alumno;
import universidaddelapunta.Entidades.Inscripcion;
import universidaddelapunta.Entidades.Materia;

/**
 *
 * @author carri
 */
public class ManipulacionNota extends javax.swing.JInternalFrame {
private Connection con;
private DefaultTableModel modelo= new DefaultTableModel(){
     public boolean isCellEditable(int fila, int columna){ 
           return false; 

        }
};

private List<Inscripcion> in;
private List<Materia> ma;
private List<Alumno> alu;
private InscripcionData idata;
private AlumnoData adata;
private materiaData mdata;
    /**
     * Creates new form ManipulacionNota
     */
    public ManipulacionNota() {
        initComponents();
        this.con=conexion.getConexion();
        modelo=new DefaultTableModel();
        idata=new InscripcionData();
        adata=new AlumnoData();
        mdata=new materiaData();
        in=idata.obtenerInscripciones();
        ma=mdata.listarMaterias();
        alu=adata.listarAlumno();
        cargar();
        cabecera();
        borrarlista();
    }
    public void cargar(){
        for(Alumno a:alu){
            jCBAlumno.addItem(a);
        }
    }
    public void cabecera(){
        ArrayList<Object> col=new ArrayList();
        col.add("codigo");
        col.add("nombre");
        col.add("nota");
        for(Object o:col){
            modelo.addColumn(o);
        }
        jTable1.setModel(modelo);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCBAlumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jBSalir = new javax.swing.JButton();
        jBguardar = new javax.swing.JButton();

        setClosable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(602, 483));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Carga de notas");

        jLabel2.setText("Seleccione un alumno");

        jCBAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBAlumnoActionPerformed(evt);
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

        jBguardar.setText("guardar");
        jBguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBguardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jCBAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBguardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jBSalir)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jCBAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBguardar)
                    .addComponent(jBSalir))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBAlumnoActionPerformed
        // TODO add your handling code here:
        cargarInscriptas();
    }//GEN-LAST:event_jCBAlumnoActionPerformed

    private void jBguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBguardarActionPerformed
        // TODO add your handling code here:
        int fila = jTable1.getSelectedRow();
        if(fila!=-1){            
            int idma=(Integer)modelo.getValueAt(fila, 0);
            Alumno idal=(Alumno)jCBAlumno.getSelectedItem();
            int alumno=idal.getIdAlumno();
            int nota=(Integer.parseInt(JOptionPane.showInputDialog("Ingrese nota a modificar")));
            idata.actualizarNota(alumno, idma, nota);
            borrarlista();
        }else{
            JOptionPane.showMessageDialog(this, "Usted debe seleccionar un alumno");
        }
    }//GEN-LAST:event_jBguardarActionPerformed

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jBSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBSalir;
    private javax.swing.JButton jBguardar;
    private javax.swing.JComboBox<Alumno> jCBAlumno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
public void borrarlista(){
    int a=modelo.getRowCount()-1;
    for(int i=a;i>=0;i--){
        modelo.removeRow(i);
    }
}
public void cargarInscriptas(){
        borrarlista();
        Alumno a=(Alumno)jCBAlumno.getSelectedItem();
        in=idata.obtenerInscripciones();
        if(a!=null){
        ArrayList<Materia> lista= (ArrayList) idata.obtenerMateriasCursadas(a.getIdAlumno());
        for(Materia mat:lista){
            for(Inscripcion insc:in){
                if(insc.getAlumno().getIdAlumno()==a.getIdAlumno()){
                    modelo.addRow(new Object[]{mat.getIdMateria(),mat.getNombre(),insc.getNota()});
                    }               
                }
            }       
        }else{
            JOptionPane.showMessageDialog(this, "Usted debe seleccionar un alumno");
        }
    }
}
