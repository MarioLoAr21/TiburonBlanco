/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesblancos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import static tiburonesblancos.Registro.getConection;


public class pruebas {
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
public String getValorCarril1(String c, String p, String h)
{
    System.out.println(c);
    System.out.println(p);
    System.out.println(h);
    String v="";
    Connection con=null;
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT carril1 FROM Carril WHERE paquete=? AND categoria=? AND Horario=? ");
            ps.setString(1,p);
            ps.setString(2,c);
            ps.setString(3,h);
            rs=ps.executeQuery();
            if(rs.next())
            {
                v=(rs.getString("carril1"));                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el dato");
                limpiarCajas();
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error 1 :D");
        }
        return v;
}
public static void main(String args[]) {
    int m=4;
    int mesespagados=6;       
for(int i=0;i<mesespagados;i++)
                    {
                    m=m-1;
                    if(m==1)
                    {                       
                        m=12;
                    }
                    }   
System.out.println("Vencimiento:"+m);
}


}
