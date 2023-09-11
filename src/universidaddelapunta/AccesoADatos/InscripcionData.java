package universidaddelapunta.AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidaddelapunta.Entidades.Alumno;
import universidaddelapunta.Entidades.Inscripcion;
import universidaddelapunta.Entidades.Materia;

public class InscripcionData {
    
    private Connection con=null;
    private materiaData matData;
    private AlumnoData aluData;
    
    public InscripcionData(){
        con=conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc){  
        String sql="insert into inscripcion(nota,id_alumno,id_materia)"
                + "values(?,?,?)";
        try{
        PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setDouble(1, insc.getNota());
        ps.setInt(2, insc.getAlumno().getIdAlumno());
        ps.setInt(3, insc.getMateria().getIdMateria());
        ps.executeUpdate();
        
        ResultSet rs=ps.getGeneratedKeys();
        if(rs.next()){
            insc.setIdInscripcion(rs.getInt(1));
            JOptionPane.showMessageDialog(null, "Inscripcion Guardado");
        }
        ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        String sql="select * from inscripcion";
        ArrayList<Inscripcion> lista=new ArrayList<>();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Inscripcion in=new Inscripcion();
                in.setIdInscripcion(rs.getInt("id_inscripcion"));
                in.setNota(rs.getInt("nota"));                
                in.setAlumno(rs.getString("id_alumno"));
                in.setMateria(rs.("id_materia"));
                lista.add(in);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno "+e.getMessage());
        }
        return lista;       
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumnos(int id){        
        String sql="select * from inscripcion join alumno on(inscripcion.id_alumno=alumno.id_alumno)"
                + "where alumno.id_alumno=?";
        ArrayList<Inscripcion> lista=new ArrayList<>();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                Inscripcion in=new Inscripcion();
                in.setIdInscripcion(rs.getInt("id_inscripcion"));
                in.setNota(rs.getInt("nota"));                
                in.setAlumno(rs.("id_alumno"));
                in.setMateria(rs.("id_materia"));
                lista.add(in);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno "+e.getMessage());
        }
        return lista;       
    }
    
    public List<Materia> obtenerMateriasCursadas(int id){
        List<Materia> materias=new ArrayList<Materia>();
        
        try{
            String sql = "SELECT inscripcion.id_materia,nombre,anio "
                    + "FROM inscripcion JOIN materia ON(inscripcion.id_materia=materia.id_materia)"
                    + "WHERE inscripcion.id_alumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            while (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al obtener inscripciones "+e.getMessage());
        }
        return materias;
    }
    
    public List<Materia> obtenerMateriasNOCursadas(int id){
        List<Materia> materias=new ArrayList<Materia>();
        
        try{
            String sql = "SELECT inscripcion.id_materia,nombre,anio "
                    + "FROM inscripcion JOIN materia ON(inscripcion.id_materia!=materia.id_materia)"
                    + "WHERE inscripcion.id_alumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            while (rs.next()) {
                materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al obtener inscripciones "+e.getMessage());
        }
        return materias;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){        
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia=?";
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó la inscripcion.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }
    }
    
    public void actualizarNota(int idAlumno,int idMateria,double nota){ 
        String sql="update inscripcion set nota=?"
                + "where id_alumno=? AND id_materia=?";
        try{
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setDouble(1, nota);
        ps.setInt(2, idAlumno);
        ps.setInt(3, idMateria);
        int exito= ps.executeUpdate();        
        if(exito==1){
            JOptionPane.showMessageDialog(null, "Alumno modificado");
        }        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        List<Alumno> alu=new ArrayList<Alumno>();
        String sql="SELECT dni,nombre,apellido,fechaNac\n" +
                "FROM `inscripcion` JOIN alumno ON(inscripcion.id_alumno=alumno.id_alumno)\n" +
                "WHERE inscripcion.id_materia=? AND estado=1";
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                Alumno alum=new Alumno();
                alum.setDni(rs.getInt("dni"));
                alum.setNombre(rs.getString("nombre"));
                alum.setApellido(rs.getString("apellido"));
                alum.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alu.add(alum);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }
        return alu;
    }
}
