
package tiburonesblancos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static tiburonesblancos.Conexion.getConection;

public class Contarcarril extends Thread {
    @Override
public void run(){
    Connection con=null;
      con=getConection();
          String path="";
          //UPDATE carril set carril1=(select count(*) from registro where carril=1 and idpaquete='"+p+"' and categoria='"+c+"' and idhorario='"+h+"'), carril2=(select count(*) from registro where carril=2 and idpaquete='"+p+"' and categoria='"+c+"' and idhorario='"+h+"') where paquete='"+p+"' and categoria='"+c+"' and horario='"+h+"'
                try{
                    System.out.println(path);
                    ps= con.prepareStatement(path);
                    
                    int res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
                 System.out.println(" carril contado");
                 System.out.println("");
            }
            else
            {
                                //limpiarCajas();
                System.out.println(" no se conto el carril");
            }
            con.close();
                }
                catch(Exception e){
                    System.out.println(e+" carril contado");
                }
}
    String Cadena;
static String login="root";
static String password="16070065";
static String url="jdbc:mysql://localhost/tiburonesblancos";




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


}
