package universidaddelapunta.AccesoADatos;
    import universidaddelapunta.Entidades.Materia;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.List;
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
    
    public Materia buscarMateria(int id){
        String sql="select * from materia where id_materia=? and estado=1";
        Materia mat=null;
        try {           
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                
                mat=new Materia();
                mat.setIdMateria(id);
                mat.setNombre(rs.getString("nombre"));
                mat.setAnio(rs.getInt("anio"));
                mat.setEstado(true);
                
            }else{
                JOptionPane.showMessageDialog(null, " No existe esa materia.");
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia");
        }
        return mat;
    }
    
    public void modificarMateria(Materia m){
        String sql="update materia set nombre=?, anio=?"
                + "where id_materia=?";
        try{
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1, m.getNombre());
        ps.setInt(2, m.getAnio());
        ps.setInt(3, m.getIdMateria());
        int exito= ps.executeUpdate();
        
        if(exito==1){
            JOptionPane.showMessageDialog(null, "Materia modificado");
        }
        
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia");
        }
    }
    
    public void eliminarMateria(int id){
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

    public List<Materia> listarMaterias(){
        String sql="select * from materia where estado=1";
        ArrayList<Materia> mat=new ArrayList<>();
        try {           
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
                Materia materia=new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                materia.setEstado(true);
                mat.add(materia);
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia "+e.getMessage());
        }
        return mat;
    }
}
