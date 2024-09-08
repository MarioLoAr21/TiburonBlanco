/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesblancos;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import static tiburonesblancos.RPagos.getConection;

public class pagosActivos {
    //----------------------------------------------------------------------------------------------------    
static String login="root";
static String password="16070065";
static String url="jdbc:mysql://localhost/tiburonesblancos?characterEncoding=utf8";
PreparedStatement ps;
ResultSet rs;
public static Connection getConection()
{
Connection conn = null;
    try
           {
              Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);               
               System.out.println ("Conexion establecida");   
           }    catch (Exception e)
           {
               System.err.println ("Hubo problema para la conexión"+e);
           }
           
        return conn;
 }

    
private void limpiarCajas()
{
   // txtIdM.setText(null);
   // txtIdP.setText(null);
   // txtCostoM.setText(null);
        
        
 }
//----------------------------------------------------------------------------------------------------


public pagosActivos() {
    
}
public pagosActivos(int c) {
    
}
public Component getTableComponent(JTable JTPagos, Object value, boolean Selected, boolean hasFocus, int row, int col) throws ParseException
{    
    String vencimiento=JTPagos.getValueAt(row,4).toString();
    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    Date fecha = new Date();
        int vdia=sdf.parse(vencimiento).getDate();
        int vmes=sdf.parse(vencimiento).getMonth()+1;
        int vaño=sdf.parse(vencimiento).getYear()+1900;
    
        int dia=fecha.getDate();
        int mes=fecha.getMonth()+1;
        int año=fecha.getYear()+1900;
        
       if(vdia==dia && vmes==mes && vaño==año)
       {
           Connection con=null;
         try{
            con=getConection();
           ps= con.prepareStatement("UPDATE Pagos SET statusago=? WHERE idPagos=?");
           ps.setString(1,"");
           ps.setString(1,"Inactivo");
            }
         catch(Exception e){
             System.out.println(e);
         }   
       }
    
    return null;    
}
}


