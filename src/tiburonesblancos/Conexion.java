
package tiburonesblancos;

import java.sql.*;
import static tiburonesblancos.Pagos.getConection;
public class Conexion  {

    String Cadena;
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



Conexion (String cad)
{
Cadena=cad;

}
Conexion ()
{


}
public void countcarril(String p, String c, String h)
{
    Connection con=null;
      con=getConection();
          String path="UPDATE carril set carril1=(select count(*) from registro where carril=1 and idpaquete='"+p+"' and categoria='"+c+"' and idhorario='"+h+"'), carril2=(select count(*) from registro where carril=2 and idpaquete='"+p+"' and categoria='"+c+"' and idhorario='"+h+"') where paquete='"+p+"' and categoria='"+c+"' and horario='"+h+"'";
                try{
                    System.out.println(path);
                    ps= con.prepareStatement(path);
                    
                    int res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
                 System.out.println(" carril contado");
                 System.out.println("paquete:"+p+"  categoria:"+c+"    horario:"+h+"");
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
public int UpdateCountcarril(String p, String c, String h)
{
    Connection con=null;
      con=getConection();
      int res=0;
          String path="UPDATE carril set carril1=(select count(*) from registro where carril=1 and idpaquete='"+p+"' and categoria='"+c+"' and idhorario='"+h+"'), carril2=(select count(*) from registro where carril=2 and idpaquete='"+p+"' and categoria='"+c+"' and idhorario='"+h+"') where paquete='"+p+"' and categoria='"+c+"' and horario='"+h+"'";
                try{
                    System.out.println(path);
                    ps= con.prepareStatement(path);
                    
                     res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
                
                 System.out.println(" carril contado");
                 System.out.println("paquete:"+p+"  categoria:"+c+"    horario:"+h+"");
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
                return res;
}

public void noPagados()
{
    Connection con=null;
      con=getConection();
          
                try{
                    
                    ps= con.prepareStatement("UPDATE registro SET Status_Pago='NoPagado' where idAlumno in (select idAlumno from pagos where statuspago='Inactivo')");
                    
                    int res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
            }
            else
            {
                                //limpiarCajas();
            }
            con.close();
                }
                catch(Exception e){
                    System.out.println(e+" ESte es el error");
                }
}
public void Pagados()
{
    Connection con=null;
      con=getConection();
       todosNoPagados();
                try{
                    
                    ps= con.prepareStatement("UPDATE registro SET Status_Pago='Pagado' where idAlumno in (select idAlumno from pagos where statuspago='Activo')");
                    
                    int res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
            }
            else
            {
                                //limpiarCajas();
            }
            con.close();
                }
                catch(Exception e){
                    System.out.println(e+" ESte es el error de los pagados");
                }
}

public void todosNoPagados()
{
    Connection con=null;
      con=getConection();
                try{
                    
                    ps= con.prepareStatement("UPDATE registro SET Status_Pago='NoPagado'");
                    
                    int res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
            }
            else
            {
                                //limpiarCajas();
            }
            con.close();
                }
                catch(Exception e){
                    System.out.println(e+" ESte es el error de los pagados");
                }
}
public void pagosActivos()
{
    Connection con=null;
      con=getConection();
     java.util.Date fecha = new java.util.Date();
            int dia=fecha.getDay();
            int mes=fecha.getMonth()+1;
            int año=fecha.getYear()+1900;
            String fec="";
            fec=año+"-"+mes+"-"+dia;
                try{
                    String in="Inactivo";
                    ps= con.prepareStatement("UPDATE pagos SET StatusPago=? where vencimiento<=?");
                    ps.setString(1,in);
                    ps.setString(2,fec);
                    int res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
            }
            else
            {
                                //limpiarCajas();
            }
            con.close();
                }
                catch(Exception e){
                    System.out.println(e+" ESte es el error");
                }
}
       
public void insert()
{
Connection conn = null;
    try
           {
              Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);
               
               System.out.println ("Conexion establecida");
   
               Statement s = conn.createStatement ();
   
               int count;
              
               count = s.executeUpdate (
             
                       "INSERT INTO usuarios"
               + " VALUES" + Cadena);
                        
               
              s.close ();
              System.out.println (count + " registros insertados");}
                catch (Exception e)
           {
               System.err.println ("Hubo problema para la conexión");
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Conexion de la base de datos terminada");
                   }
                   catch (Exception e) { /* ignorar los errores cercanos */ }
               }
           }
       }
public String buscarN(String N, String A )
{
    Connection conn = null;
    try
           {
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);
               
               System.out.println ("Conexión establecida");
   
               Statement s = conn.createStatement ();
   
               int count;
               ResultSet rs=s.executeQuery("select * from persona where nombre="+N+" and"+ "apellido="+A);

while(rs.next())
{
    Cadena=(rs.getString (2));  
    
}
   s.close ();
   }
    catch (Exception e)
           {
               System.err.println ("Hubo probl"
                       + "ema para la conexión");
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Conexión de la base de datos terminada");
                   }
                   catch (Exception e) { /* ignorar los errores cercanos */ }
               }
           }
       
 
return Cadena;
}
public String buscarPsw(String us)
{
    Connection conn = null;
    try
           {
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);
               
               System.out.println ("Conexión establecida");
   
               Statement s = conn.createStatement ();
   
               int count;
               ResultSet rs=s.executeQuery("select * from Usuarios where usuario='"+us+"'");

while(rs.next())
{
    Cadena=(rs.getString (3));  
    
}
   s.close ();
   }
    catch (Exception e)
           {
               System.err.println ("Hubo probl"
                       + "ema para la conexión : "+e);
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Conexión de la base de datos terminada");
                   }
                   catch (Exception e) { /* ignorar los errores cercanos */ }
               }
           }
       
 
return Cadena;
}
public String buscarR(int iD)
{
    Connection conn = null;
    try
           {
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);
               
               System.out.println ("Conexión establecida");
   
               Statement s = conn.createStatement ();
   
               int count;
               ResultSet rs=s.executeQuery("select * from Alumno where control="+iD);

while(rs.next())
{
        Cadena=(rs.getString (4));  
    
}
   s.close ();
   }
    catch (Exception e)
           {
               System.err.println ("Hubo probl"
                       + "ema para la conexion");
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Conexion de la base de datos terminada");
                   }
                   catch (Exception e) { /* ignorar los errores cercanos */ }
               }
           }
       
 
return Cadena;
}
public String buscarT(int iD)
{
    Connection conn = null;
    try
           {
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);
               
               System.out.println ("Conexion establecida");
   
               Statement s = conn.createStatement ();
   
               int count;
               ResultSet rs=s.executeQuery("select * from cliente where id="+iD);

while(rs.next())
{
    Cadena=(rs.getString (5));  
    
}
   s.close ();
   }
    catch (Exception e)
           {
               System.err.println ("Hubo probl"
                       + "ema para la conexion");
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Conexion de la base de datos terminada");
                   }
                   catch (Exception e) { /* ignorar los errores cercanos */ }
               }
           }
       
 
return Cadena;
}

public String obtenerFecha( )
{
    Connection conn = null;
    try
           {
               Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);
               
               System.out.println ("Conexión establecida");
   
               Statement s = conn.createStatement ();
   
               int count;
               ResultSet rs=s.executeQuery("select curdate()");

while(rs.next())
{
    Cadena=(rs.getString (1));  
    
}
   s.close ();
   }
    catch (Exception e)
           {
               System.err.println ("Hubo probl"
                       + "ema para la conexion");
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Conexion de la base de datos terminada");
                   }
                   catch (Exception e) { /* ignorar los errores cercanos */ }
               }
           }
       
 
return Cadena;
}
public void actualizarContraseña(String Cad, String Cad2)
{
Connection conn = null;
    try
           {
              Class.forName ("com.mysql.jdbc.Driver").newInstance ();
               conn = DriverManager.getConnection (url, login, password);
               
               System.out.println ("Conexion establecida");
   
               Statement s = conn.createStatement ();
   
               int count;
              
               count = s.executeUpdate (
             
                       "UPDATE usuarios SET contraseña='" + Cad2 +"' WHERE usuario='"+Cad+"'");
                        
               
              s.close ();
              System.out.println (count + " contraseña atualizadda");}
                catch (Exception e)
           {
               System.err.println ("Hubo problema para la conexión");
           }
           finally
           {
               if (conn != null)
               {
                   try
                   {
                       conn.close ();
                       System.out.println ("Conexion de la base de datos terminada");
                   }
                   catch (Exception e) { /* ignorar los errores cercanos */ }
               }
           }
       }

    boolean buscarR(String idA) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
