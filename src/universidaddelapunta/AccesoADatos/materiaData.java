package universidaddelapunta.AccesoADatos;
    import universidaddelapunta.Entidades.Materia;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.swing.JOptionPane;

public class materiaData {
    
    private Connection con = null;
    
public materiaData(){
    
    con = conexion.getConexion();
    
}

    public void guardarMateria(Materia m){
        String sql = "insert into materia (nombre, anio, estado) values ( ?, ?, ?)";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.getEstado());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                m.setIdMateria(rs.getInt("IdMateria"));
            JOptionPane.showMessageDialog(null, "Materia añadida con exito");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
            
        }
    }
    
    public void eliminarAlumno(int id){
        String sql = "UPDATE materia SET estado = 0 WHERE idMateria = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            int fila = ps.executeUpdate();
            
            if(fila == 1){
                JOptionPane.showMessageDialog(null, "Materia eliminada con exito");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla materia" + ex.getMessage());
        }
        
    }

}
