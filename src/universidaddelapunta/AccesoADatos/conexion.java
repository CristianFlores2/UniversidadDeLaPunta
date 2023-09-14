package universidaddelapunta.AccesoADatos;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexion {
    
    private static final String URL="jdbc:mariadb://localhost/";
    private static final String DB="universidad_de_la_punta";
    private static final String USUARIO="root";
    private static final String PASSWORD="";
    private static Connection connection;
    
    private conexion(){}
    
    public static Connection getConexion(){
        if(connection==null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection=DriverManager.getConnection(URL+DB,USUARIO,PASSWORD);
                JOptionPane.showMessageDialog(null, "Conectado");
                
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar los drivers  " + ex.getMessage());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la bd");
            }
        }
        return connection;
    }
}
