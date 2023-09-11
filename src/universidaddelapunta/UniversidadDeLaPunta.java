/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package universidaddelapunta;

import java.sql.Connection;
import java.time.LocalDate;
import universidaddelapunta.AccesoADatos.AlumnoData;
import universidaddelapunta.AccesoADatos.conexion;
import universidaddelapunta.Entidades.Alumno;

/**
 *
 * @author crist
 */
public class UniversidadDeLaPunta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connection con=conexion.getConexion();
        /*
        //Alumno juan=new Alumno(1,12312312,"Luna","Juan Pedro",LocalDate.of(1980,4,25),true);
        AlumnoData alu=new AlumnoData();
        //alu.guardarAlumno(juan);
        //alu.modificarAlumno(juan);
        //alu.eliminarAlumno(1);
        Alumno enco=alu.buscarAlumno(1);
        if(enco!=null){
        System.out.println("dni "+enco.getDni());
        System.out.println("apellido "+enco.getApellido());
*/
        AlumnoData alu=new AlumnoData();
        for (Alumno alumno:alu.listarAlumno()) {
            System.out.println(alumno.getDni());
            System.out.println(alumno.getApellido());
            System.out.println(alumno.getNombre());
            System.out.println(alumno.getFechaNac());
            }
        }
    }
    
