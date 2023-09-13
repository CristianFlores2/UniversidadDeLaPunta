/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidaddelapunta.AccesoADatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import universidaddelapunta.Entidades.Alumno;

/**
 *
 * @author crist
 */
public class AlumnoData {
    private Connection con=null;
    
    public AlumnoData(){
        con=conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno){
        
        String sql="insert into alumno(dni,apellido,nombre,fechaNac,estado)"
                + "values(?,?,?,?,?)";
        try{
        PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, alumno.getDni());
        ps.setString(2, alumno.getApellido());
        ps.setString(3, alumno.getNombre());
        ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
        ps.setBoolean(5, alumno.isEstado());
        ps.executeUpdate();
        
        ResultSet rs=ps.getGeneratedKeys();
        if(rs.next()){
            alumno.setIdAlumno(rs.getInt(1));
            JOptionPane.showMessageDialog(null, "Alumno Guardado");
        }
        ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    public void modificarAlumno(Alumno alumno){
        String sql="update alumno set dni=?, apellido=?, nombre=?, fechaNac=?"
                + "where id_alumno=?";
        try{
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1, alumno.getDni());
        ps.setString(2, alumno.getApellido());
        ps.setString(3, alumno.getNombre());
        ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
        ps.setInt(5, alumno.getIdAlumno());
        int exito= ps.executeUpdate();
        
        if(exito==1){
            JOptionPane.showMessageDialog(null, "Alumno modificado");
        }
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
    public void eliminarAlumno(int dni) {
        String sql = "UPDATE alumno SET estado = 0 WHERE dni = ? ";
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, " Se eliminó el alumno.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }
    }
    
    public Alumno buscarAlumno(int id) {
        String sql="select dni,apellido,nombre,fechaNac from alumno where id_alumno=? and estado=1";
        Alumno alumno=null;
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                alumno=new Alumno();
                alumno.setIdAlumno(id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
                
            }else{
                JOptionPane.showMessageDialog(null, " No existe ese alumno.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }
        return alumno;
    }
    public Alumno buscarAlumnoPorDNI(int dni) {
        Alumno alumno=null;
        String sql="select id_alumno,dni,apellido,nombre,fechaNac from alumno where dni=? and estado=1";        
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
                
            }else{
                JOptionPane.showMessageDialog(null, " No existe ese alumno.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno");
        }
        return alumno;
    }
    
    public List<Alumno> listarAlumno() {
        String sql="select id_alumno,dni,apellido,nombre,fechaNac from alumno where estado=1";
        ArrayList<Alumno> alumnos=new ArrayList<>();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {               
                Alumno alumno=new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNac").toLocalDate());
                alumno.setEstado(true);
                alumnos.add(alumno);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno "+e.getMessage());
        }
        return alumnos;
    }
}
