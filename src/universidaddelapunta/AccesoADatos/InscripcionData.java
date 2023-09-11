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
    }
    
    public List<Inscripcion> obtenerInscripciones(){        
        return null;        
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumnos(int id){        
        return null;        
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
        return null;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno,int idMateria){        
    }
    
    public void actualizarNota(int idAlumno,int idMateria,double nota){        
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        return null;
    }
}
