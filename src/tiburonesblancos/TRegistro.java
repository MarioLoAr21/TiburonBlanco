/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesblancos;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import static tiburonesblancos.Alumnos.getConection;
import static tiburonesblancos.RPagos.getConection;
import static tiburonesblancos.Registro.getConection;

/**
 *
 * @author Giuseppe
 */
public class TRegistro extends javax.swing.JFrame {
//----------------------------------------------------------------------------------------------------    

    static String login = "root";
    static String password = "16070065";
    static String url = "jdbc:mysql://localhost/tiburonesblancos?characterEncoding=latin1";
    PreparedStatement ps;
    ResultSet rs;

    public static Connection getConection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, login, password);
            System.out.println("Conexion establecida");
        } catch (Exception e) {
            System.err.println("Hubo problema para la conexión" + e);
        }

        return conn;
    }

    private void limpiarCajas() {
        jComboBox1.setSelectedIndex(0);
        datCategoriaCbx.setSelectedIndex(0);
        datPaqueteCbx.setSelectedIndex(0);
        //jTextField1.setText(null);
        nombre.setText(null);
        apellido.setText(null);
        datHorarioCbx.removeAllItems();
        datCategoriaCbx.enable(true);
        datPaqueteCbx.enable(true);
        datHorarioCbx.addItem("-");
        ID.setText(null);
        
    }
//----------------------------------------------------------------------------------------------------

    public void contarRegistros() {
        int filas = jTRegistro.getRowCount();
        jtxtCR.setText(Integer.toString(filas));
    }

    public String buscarA(String NombreA, String ApellidoA) {
        String i="";
        
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("SELECT idAlumnos FROM Alumnos WHERE nombre=? AND apellidoP=?");//// corregir alumnos en el bd
            ps.setString(1, NombreA);
            ps.setString(2, ApellidoA);
            rs = ps.executeQuery();
            if (rs.next()) {
               i=rs.getString("idAlumnos");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el Alumno");
                limpiarCajas();
            }
        } catch (Exception e) {
            System.err.println(e + "Error 5 :D");
        }
        
        return i;
    }

    public void getStatatus() {
        String r = "";
        String v = "";
        Connection con = null;
        int registros = jTRegistro.getRowCount();
        
        for (int i = 0; i < registros; i++) {
            con = getConection();
            //String id= buscarA(jTRegistro.getValueAt(i, 6).toString(), jTRegistro.getValueAt(i, 7).toString());
            String id= jTRegistro.getValueAt(i, 8).toString();
            System.out.println("id Alumno; "+id);
            try {
                
                ps = con.prepareStatement("SELECT StatusPago FROM Pagos WHERE idAlumno=? AND StatusPago=?");
                ps.setString(1, id);
                ps.setString(2, "Activo");
                rs = ps.executeQuery();
                if (rs.next()) {
                    StatusPagos(id, "Pagado");

                } else {
                    StatusPagos(id, "NoPagado");
                }
            } catch (Exception e) {
                System.err.println(e + "Error  :D");
            }
            con= null;
        }
        
    }

    public void StatusPagos(String id, String stat) {
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("UPDATE Registro SET Status_Pago=? WHERE idAlumno=?");
            ps.setString(1, stat);
            ps.setString(2, id);
            
            int res = ps.executeUpdate();
            if (res>0)
            {
                
                
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e + "Error 6 :D");
        }

    }
    int cont;

    public void validar() {
        if (jtxtPaquete.getText().equals("")) {
            cont++;
        }

    }

    public void updateCarril1() {
        String p, c, h; int res=0;
        Conexion up=new Conexion();
        
        p = jtxtPaquete.getText().toString();
        h = jtxtHorario.getText().toString();
        c = jtxtCategoria.getText().toString();
       
        
        res=up.UpdateCountcarril(p, c, h);
       
            if (res > 0) {
                JOptionPane.showMessageDialog(rootPane, "Carril 1 Actualizado");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Error al Actualizar carril");
                limpiarCajas();
            }
        
    }

    public void updateCarril2() {
         String p, c, h; int res=0;
        Conexion up=new Conexion();
        
        p = jtxtPaquete.getText().toString();
        h = jtxtHorario.getText().toString();
        c = jtxtCategoria.getText().toString();
       
        
        res=up.UpdateCountcarril(p, c, h);
       
            if (res > 0) {
                JOptionPane.showMessageDialog(rootPane, "Carril 2 Actualizado");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Error al Actualizar carril");
                limpiarCajas();
            }
    }

    public String getValorCarril1(String p, String c, String h) {
        String v = "";
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("SELECT carril1 FROM Carril WHERE paquete=? AND categoria=? AND Horario=? ");
            ps.setString(1, p);
            ps.setString(2, c);
            ps.setString(3, h);
            rs = ps.executeQuery();
            if (rs.next()) {
                v = (rs.getString("carril1"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el dato del carril 1");
                limpiarCajas();
            }
        } catch (Exception e) {
            System.err.println(e + "Error 1 :D");
        }
        return v;
    }

    public String getValorCarril2(String p, String c, String h) {
        String v = "";
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("SELECT carril2 FROM Carril WHERE paquete=? AND categoria=? AND Horario=? ");
            ps.setString(1, p);
            ps.setString(2, c);
            ps.setString(3, h);
            rs = ps.executeQuery();
            if (rs.next()) {
                v = (rs.getString("carril2"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el dato de carril 2");
                limpiarCajas();
            }
        } catch (Exception e) {
            System.err.println(e + "Error 2 :D");
        }
        return v;
    }

    public TRegistro() {
        Conexion x = new Conexion();
               
            x.pagosActivos();
            x.noPagados();
            x.Pagados();
        initComponents();
        this.setLocationRelativeTo(null);


        Connection con = null;
          //  ----------------------------------------------------------------------
         
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            jTRegistro.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            //PreparedStatement ps2= null;
            // ResultSet rs2=null;
            con = getConection();
            
            String sql = "SELECT idRegistro, idPaquete, idHorario, Categoria, carril, Status_Pago, nombre, apellidoP, idAlumnos, telefono FROM Registro , Alumnos  WHERE Registro.idAlumno=Alumnos.idAlumnos";
            //String sql = "SELECT idRegistro, idPaquete, idHorario, Categoria, carril, idAlumno, Status_Pago FROM Registro";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //ps2 =con.prepareStatement(id);
            //rs2 = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idRegistro");
            modelo.addColumn("Paquete");
            modelo.addColumn("Horario");
            modelo.addColumn("Categoria");
            modelo.addColumn("Carril");
            modelo.addColumn("Status_Pago");
            
            modelo.addColumn("nombre");
            modelo.addColumn("apellidoP");
            modelo.addColumn("idAlumno");
            modelo.addColumn("telefono");
            
            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        
        //getStatatus();
        
        contarRegistros();
        
        jTRegistro.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(0).setResizable(false);
        jTRegistro.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTRegistro.getColumnModel().getColumn(1).setResizable(false);
        jTRegistro.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTRegistro.getColumnModel().getColumn(2).setResizable(false);
        jTRegistro.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTRegistro.getColumnModel().getColumn(3).setResizable(false);
        jTRegistro.getColumnModel().getColumn(4).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(4).setResizable(false);
        jTRegistro.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTRegistro.getColumnModel().getColumn(5).setResizable(false);
        jTRegistro.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTRegistro.getColumnModel().getColumn(6).setResizable(false);
        jTRegistro.getColumnModel().getColumn(7).setPreferredWidth(100);
        jTRegistro.getColumnModel().getColumn(7).setResizable(false);
        jTRegistro.getColumnModel().getColumn(8).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(8).setResizable(false);
        jTRegistro.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTRegistro.getColumnModel().getColumn(9).setResizable(false);
 
        
         jTRegistro.setRowHeight(30);

    }
    
        @Override
    public Image getIconImage(){
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imgs/icono.png"));
    return retValue;
    }
    
    
public void ValidarCbxCategoria()
{
    /*
    2:00 - 3:00 p.m.
    3:00 - 4:00 p.m.
    4:00 - 5:00 p.m.
    5:00 - 6:00 p.m.
    6:00 - 7:00 p.m.
    10:00 - 11:00 a.m.
    11:00 - 12:00 a.m.
    12:00 - 1:00 p.m.
    .addItem("00:00 - 00:00");
    */
    int c=datCategoriaCbx.getSelectedIndex();
    int p=datPaqueteCbx.getSelectedIndex();
    //-----------------------------------------------------------Lunes, miercoles, jueves
    
    
    if(c==1 && p==1)
    {
    datHorarioCbx.addItem("10:00 - 11:00 a.m.");
    datHorarioCbx.addItem("11:00 - 12:00 p.m.");
    datHorarioCbx.addItem("12:00 - 01:00 p.m.");
    datHorarioCbx.addItem("01:00 - 02:00 p.m.");
    datHorarioCbx.addItem("02:00 - 03:00 p.m.");
    datHorarioCbx.addItem("03:00 - 04:00 p.m.");
    datHorarioCbx.addItem("04:00 - 05:00 p.m.");
    datHorarioCbx.addItem("05:00 - 06:00 p.m.");
    datHorarioCbx.addItem("06:00 - 07:00 p.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    
    }
    if(c==2 && p==1)
    {
    datHorarioCbx.addItem("08:10 - 08:40 a.m.");
    datHorarioCbx.addItem("08:40 - 09:10 a.m.");

    datHorarioCbx.addItem("09:10 - 09:40 a.m.");
    datHorarioCbx.addItem("09:40 - 10:10 a.m.");
    datHorarioCbx.addItem("10:10 - 10:40 a.m.");
    datHorarioCbx.addItem("10:40 - 11:10 a.m.");
    datHorarioCbx.addItem("11:10 - 11:40 a.m.");
    datHorarioCbx.addItem("11:40 - 12:10 p.m.");
    datHorarioCbx.addItem("12:10 - 12:40 p.m.");
    datHorarioCbx.addItem("12:40 - 01:10 p.m.");
    datHorarioCbx.addItem("01:10 - 01:40 p.m.");
    datHorarioCbx.addItem("01:40 - 02:10 p.m.");
    
    
//  horario extra bbs 
    datHorarioCbx.addItem("02:40 - 03:10 p.m.");
    
    
    datHorarioCbx.addItem("03:10 - 03:40 p.m.");
    datHorarioCbx.addItem("03:40 - 04:10 p.m.");
    datHorarioCbx.addItem("04:10 - 04:40 p.m." );
    datHorarioCbx.addItem("04:40 - 05:10 p.m.");
    datHorarioCbx.addItem("05:10 - 05:40 p.m.");
    datHorarioCbx.addItem("05:40 - 06:10 p.m.");
    datHorarioCbx.addItem("06:10 - 06:40 p.m.");
    datHorarioCbx.addItem("06:40 - 07:10 p.m.");
    datHorarioCbx.addItem("07:10 - 07:40 p.m.");
    datHorarioCbx.addItem("07:40 - 08:10 p.m.");
    
    //  horario extra bbs 06 07 2023
    datHorarioCbx.addItem("08:10 - 08:40 p.m.");
    
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    if(c==3 && p==1)
    {
    datHorarioCbx.addItem("06:00 - 07:00 a.m.");
    datHorarioCbx.addItem("07:00 - 08:00 a.m." );
    datHorarioCbx.addItem("08:00 - 09:00 a.m.");
    datHorarioCbx.addItem("09:00 - 10:00 a.m.");
    datHorarioCbx.addItem("07:00 - 08:00 p.m.");
    datHorarioCbx.addItem("08:00 - 09:00 p.m.");
    datHorarioCbx.addItem("09:00 - 10:00 p.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    //-----------------------------------------------------------martes,  jueves
    if(c==1&& p==2)
    {
    datHorarioCbx.addItem("10:00 - 11:00 a.m.");
    datHorarioCbx.addItem("11:00 - 12:00 p.m.");  
    datHorarioCbx.addItem("12:00 - 01:00 p.m.");
    datHorarioCbx.addItem("01:00 - 02:00 p.m.");
    datHorarioCbx.addItem("02:00 - 03:00 p.m.");
    datHorarioCbx.addItem("03:00 - 04:00 p.m.");
    datHorarioCbx.addItem("04:00 - 05:00 p.m.");
    datHorarioCbx.addItem("05:00 - 06:00 p.m.");
    datHorarioCbx.addItem("06:00 - 07:00 p.m.");
    datHorarioCbx.addItem("07:00 - 08:00 p.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    if(c==2 && p==2)
    {
    datHorarioCbx.addItem("08:10 - 08:40 a.m.");
    datHorarioCbx.addItem("08:40 - 09:10 a.m.");

    datHorarioCbx.addItem("09:10 - 09:40 a.m.");
    datHorarioCbx.addItem("09:40 - 10:10 a.m.");
    datHorarioCbx.addItem("10:10 - 10:40 a.m.");
    datHorarioCbx.addItem("10:40 - 11:10 a.m.");
    datHorarioCbx.addItem("11:10 - 11:40 a.m.");
    datHorarioCbx.addItem("11:40 - 12:10 p.m.");
    datHorarioCbx.addItem("12:10 - 12:40 p.m.");
    datHorarioCbx.addItem("12:40 - 01:10 p.m.");
    datHorarioCbx.addItem("01:10 - 01:40 p.m.");
    datHorarioCbx.addItem("01:40 - 02:10 p.m.");
    
    //  horario extra bbs 05 07 2023 
    datHorarioCbx.addItem("02:10 - 02:40 p.m.");
    
    //  horario extra bbs 
    datHorarioCbx.addItem("02:40 - 03:10 p.m.");
    
    datHorarioCbx.addItem("03:10 - 03:40 p.m.");
    datHorarioCbx.addItem("03:40 - 04:10 p.m.");
    datHorarioCbx.addItem("04:10 - 04:40 p.m." );
    datHorarioCbx.addItem("04:40 - 05:10 p.m.");
    datHorarioCbx.addItem("05:10 - 05:40 p.m.");
    datHorarioCbx.addItem("05:40 - 06:10 p.m.");
    datHorarioCbx.addItem("06:10 - 06:40 p.m.");
    datHorarioCbx.addItem("06:40 - 07:10 p.m.");
    datHorarioCbx.addItem("07:10 - 07:40 p.m.");
    datHorarioCbx.addItem("07:40 - 08:10 p.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    
    if(c==3 && p==2)
    {
    datHorarioCbx.addItem("06:00 - 07:00 a.m.");
    datHorarioCbx.addItem("07:00 - 08:00 a.m." );
    datHorarioCbx.addItem("08:00 - 09:00 a.m.");
    datHorarioCbx.addItem("09:00 - 10:00 a.m.");
    datHorarioCbx.addItem("07:00 - 08:00 p.m.");
    datHorarioCbx.addItem("08:00 - 09:00 p.m.");
    datHorarioCbx.addItem("09:00 - 10:00 p.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    //-----------------------------------------------------------Sabado
    if(c==1 && p==3)
    {
    datHorarioCbx.addItem("10:00 - 11:00 a.m.");
    datHorarioCbx.addItem("11:00 - 12:00 p.m." );
    datHorarioCbx.addItem("12:00 - 01:00 p.m.");
    datHorarioCbx.addItem("01:00 - 02:00 p.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    if(c==2 && p==3)
    {
        
    //  horario extra bbs 05 07 2023 
    datHorarioCbx.addItem("08:00 - 08:30 a.m.");
    datHorarioCbx.addItem("08:30 - 09:00 a.m.");
    datHorarioCbx.addItem("09:00 - 09:30 a.m.");
    datHorarioCbx.addItem("09:30 - 10:00 a.m.");
    
    
    datHorarioCbx.addItem("10:00 - 10:30 a.m.");
    datHorarioCbx.addItem("10:30 - 11:00 a.m." );
    datHorarioCbx.addItem("11:00 - 11:30 a.m.");
    datHorarioCbx.addItem("11:30 - 12:00 p.m.");
    datHorarioCbx.addItem("12:00 - 12:30 p.m.");
    datHorarioCbx.addItem("12:30 - 01:00 p.m.");
    datHorarioCbx.addItem("01:00 - 01:30 p.m.");
    datHorarioCbx.addItem("01:30 - 02:00 p.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    if(c==3 && p==3)
    {
    datHorarioCbx.addItem("06:00 - 07:00 a.m.");
    datHorarioCbx.addItem("07:00 - 08:00 a.m." );
    datHorarioCbx.addItem("08:00 - 09:00 a.m.");
    datHorarioCbx.addItem("09:00 - 10:00 a.m.");
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }

}
    public String getUsuario() {
        String v = "";
        Connection con = null;
        try {
            con = getConection();
            ps = con.prepareStatement("SELECT idUsuario FROM Usuarios WHERE permisos=? ");
            ps.setString(1, "1");
            rs = ps.executeQuery();
            if (rs.next()) {
                v = (rs.getString("idUsuario"));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontro el dato");
                limpiarCajas();
            }
        } catch (Exception e) {
            System.err.println(e + "Error  :D");
        }
        return v;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        datHorarioCbx = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTRegistro = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        ID = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jtxtIdRegistro = new javax.swing.JTextField();
        jtxtPaquete = new javax.swing.JTextField();
        jtxtHorario = new javax.swing.JTextField();
        jtxtCategoria = new javax.swing.JTextField();
        bEliminar = new javax.swing.JButton();
        jTxtCarril = new javax.swing.JTextField();
        jTxtStatus = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellido = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jtxtCR = new javax.swing.JTextField();
        datPaqueteCbx = new javax.swing.JComboBox<>();
        datCategoriaCbx = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setExtendedState(6);
        setFocusCycleRoot(false);
        setIconImage(getIconImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        datHorarioCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datHorarioCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        datHorarioCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datHorarioCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datHorarioCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, 170, -1));

        jLabel9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel9.setText("Horario");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, -1, -1));

        jTRegistro = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTRegistro.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Paquete", "Horario", "Categoria", "carril", "Status pago", "nombre", "apellidoP", "idAlumno", "telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTRegistro.setGridColor(new java.awt.Color(102, 102, 102));
        jTRegistro.setRowHeight(24);
        jTRegistro.setSelectionBackground(new java.awt.Color(0, 0, 255));
        jTRegistro.setSelectionForeground(new java.awt.Color(240, 240, 240));
        jTRegistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTRegistroMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTRegistro);
        if (jTRegistro.getColumnModel().getColumnCount() > 0) {
            jTRegistro.getColumnModel().getColumn(0).setResizable(false);
            jTRegistro.getColumnModel().getColumn(1).setResizable(false);
            jTRegistro.getColumnModel().getColumn(2).setResizable(false);
            jTRegistro.getColumnModel().getColumn(3).setResizable(false);
            jTRegistro.getColumnModel().getColumn(4).setResizable(false);
            jTRegistro.getColumnModel().getColumn(5).setResizable(false);
            jTRegistro.getColumnModel().getColumn(6).setResizable(false);
            jTRegistro.getColumnModel().getColumn(7).setResizable(false);
            jTRegistro.getColumnModel().getColumn(8).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 1370, 440));

        jLabel8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel8.setText("ID Alumno:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        jButton7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/asistencia.png"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/asistencia.png"))); // NOI18N
        jButton7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/asistencia2.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 740, 100, 90));

        ID.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IDKeyTyped(evt);
            }
        });
        getContentPane().add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 140, -1));

        jButton4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agrega.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agrega.png"))); // NOI18N
        jButton4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agrega2.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 730, 100, 100));

        jLabel1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel1.setText("Status de Pago:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 90, -1, 30));

        jLabel5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel5.setText("Agregar");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 820, -1, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/recargar.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/recargar.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/recargar2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 80, 110, 90));

        jtxtIdRegistro.setEditable(false);
        jtxtIdRegistro.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jtxtIdRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtIdRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtIdRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 670, 100, 40));

        jtxtPaquete.setEditable(false);
        jtxtPaquete.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jtxtPaquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 670, 260, 40));

        jtxtHorario.setEditable(false);
        jtxtHorario.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jtxtHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtHorario, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 670, 210, 40));

        jtxtCategoria.setEditable(false);
        jtxtCategoria.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jtxtCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 670, 150, 40));

        bEliminar.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/eliminar.png"))); // NOI18N
        bEliminar.setBorder(null);
        bEliminar.setBorderPainted(false);
        bEliminar.setContentAreaFilled(false);
        bEliminar.setFocusPainted(false);
        bEliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/eliminar.png"))); // NOI18N
        bEliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/eliminar2.png"))); // NOI18N
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(bEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 730, 100, 90));

        jTxtCarril.setEditable(false);
        jTxtCarril.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTxtCarril, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 670, 130, 40));

        jTxtStatus.setEditable(false);
        jTxtStatus.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTxtStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 670, 140, 40));

        jButton2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Reporte.png"))); // NOI18N
        jButton2.setToolTipText("");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Reporte.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Reporte2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 730, 100, 100));

        nombre.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        getContentPane().add(nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 170, -1));

        jLabel3.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No. Registros");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 870, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel4.setText("Paquete:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, -1, 30));

        apellido.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoKeyTyped(evt);
            }
        });
        getContentPane().add(apellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 170, -1));

        jButton5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Reporte NP.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Reporte NP.png"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Reporte NP2.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 730, 100, 90));

        jComboBox1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Pagado", "NoPagado" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 90, 140, 30));

        jLabel2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, -1, 30));

        jtxtCR.setEditable(false);
        jtxtCR.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jtxtCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtCRActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtCR, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 870, 130, 30));

        datPaqueteCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datPaqueteCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Lunes, Miercoles, Viernes", "Martes, Jueves", "Sabado" }));
        datPaqueteCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datPaqueteCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datPaqueteCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 130, 220, -1));

        datCategoriaCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datCategoriaCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Niños", "Bebes", "Adultos" }));
        datCategoriaCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datCategoriaCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datCategoriaCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 90, 220, -1));

        jLabel6.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel6.setText("Apellido Paterno:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel7.setText("Categoria:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 90, 30));

        jButton6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton6.setText("Menu");
        jButton6.setBorder(null);
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu2.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        jLabel10.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel10.setText("Eliminar");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 820, -1, 30));

        jLabel11.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel11.setText("Reporte P");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 820, -1, 30));

        jLabel12.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel12.setText("Reporte N/P");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 820, -1, 30));

        jLabel13.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel13.setText("Asistencias");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 820, -1, 30));

        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu/Alumnos_.jpg"))); // NOI18N
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTRegistroMouseClicked
        // TODO add your handling code here:
        /*PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            Connection con=null;
            //Conexion objCon=new Conexion();
            //Connection con = objCon.getConection();
            int Fila =jTAlumnos.getSelectedRow();
            String codigo=jTAlumnos.getValueAt(Fila, 0).toString();

            ps=con.prepareStatement("SELECT idAlumos, nombre, apellidoP, apellidoM FROM Alumos WHERE idAlumos=?");
            ps.setString(1,codigo);
            rs=ps.executeQuery();

            while(rs.next()){
                jtxtIdAlumno.setText(rs.getString("idAlumos"));
                jtxtNombre.setText(rs.getString("nombre"));
                jtxtApellidoP.setText(rs.getString("apellidoP"));
                jtxtApellidoM.setText(rs.getString("ApellidoM"));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }
         */
        int i = jTRegistro.getSelectedRow();
        TableModel model = jTRegistro.getModel();
        jtxtIdRegistro.setText(model.getValueAt(i, 0).toString());
        jtxtPaquete.setText(model.getValueAt(i, 1).toString());
        jtxtHorario.setText(model.getValueAt(i, 2).toString());
        jtxtCategoria.setText(model.getValueAt(i, 3).toString());
        jTxtCarril.setText(model.getValueAt(i, 4).toString());
        jTxtStatus.setText(model.getValueAt(i, 5).toString());
    }//GEN-LAST:event_jTRegistroMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Registro r = new Registro();
        this.setVisible(false);
        r.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
           // TODO add your handling code here:
           Conexion x = new Conexion();        
            x.pagosActivos();
            x.noPagados();
            x.Pagados();
        Connection con = null;
        String nom=nombre.getText();
        String hora=(String) datHorarioCbx.getSelectedItem();
        String ape=apellido.getText();
        String ide=ID.getText();
        //String campo = jTextField1.getText();
        String campo2 = (String) jComboBox1.getSelectedItem();
        String where = "";
        String campo3 = (String) datPaqueteCbx.getSelectedItem();
        String campo4 = (String) datCategoriaCbx.getSelectedItem();
        if (!jComboBox1.getSelectedItem().equals("-") && datCategoriaCbx.getSelectedItem().equals("-") && datPaqueteCbx.getSelectedItem().equals("-") && datHorarioCbx.getSelectedItem().equals("-")) {
            where = " AND Status_Pago='" + campo2 + "' AND " +"Categoria='" + campo4 + "'" + "AND idPaquete='" + campo3 + "'"+" AND idHorario='" + hora + "'";
        }
        if (!"-".equals(campo4)) {
            where = " AND Categoria='" + campo4 + "'";
        }
        if (!"-".equals(campo3)) {
            where = " AND idPaquete='" + campo3 + "'";
        }
        if (!"-".equals(campo3) && !"-".equals(campo4)) {
            where = " AND Categoria='" + campo4 + "'" + "AND idPaquete='" + campo3 + "'";
        }
        
        if (!"-".equals(campo3) && !"-".equals(campo4) && !"-".equals(hora)) {
            where = " AND Categoria='" + campo4 + "'" + "AND idPaquete='" + campo3 + "'"+" AND idHorario='" + hora + "'";
        }
        if (!"".equals(nom)) {
            where = " AND nombre like'" + nom + "%'";
        }
        if (!"".equals(ape)) {
            where = " AND apellidoP like'" + ape + "%'";
        }
        if (!"".equals(nom) && !"".equals(ape)) {
            where = " AND nombre like'" + nom + "%'"+" AND apellidoP like'" + ape + "%'";
        }
        if (!"".equals(ide)) {
            where = " AND idAlumno='" + ide + "'";
        }
        if (jComboBox1.getSelectedItem().equals("Pagado") || jComboBox1.getSelectedItem().equals("NoPagado")) {
            where = " AND Status_Pago='" + campo2 + "'";
        }

        if (!jComboBox1.getSelectedItem().equals("-") && !"".equals(nom)) {
            where = " AND Status_Pago='" + campo2 + "' AND " + "nombre='" + nom + "'";
        }
        if (!jComboBox1.getSelectedItem().equals("-") && !"".equals(nom) && !"".equals(ape)) {
            where = " AND Status_Pago='" + campo2 + "' AND " + "nombre='" + nom + "'"+ " AND apellidoP='" + ape + "'";
        }
        if (!jComboBox1.getSelectedItem().equals("-") && !"".equals(nom) && !"".equals(ape) && !"".equals(campo3)) {
            where = " AND Status_Pago='" + campo2 + "' AND " + "nombre='" + nom + "'"+ " AND apellidoP='" + ape + "'" + " AND idPaquete='" + campo3 + "'";
        }
        if (!jComboBox1.getSelectedItem().equals("-") && !"".equals(nom) && !"".equals(ape) && !"-".equals(campo3) && !"-".equals(campo4)) {
            where = " AND Status_Pago='" + campo2 + "' AND " +"nombre='" + nom + "'"+ " AND apellidoP='" + ape + "'" + " AND idPaquete='" + campo3 + "'" + " AND Categoria='" + campo4 + "'";
        }
        

        try {

            DefaultTableModel modelo = new DefaultTableModel();
            jTRegistro.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            con = getConection();
            String sql = "SELECT idRegistro, idPaquete, idHorario, Categoria, carril, Status_Pago, nombre, apellidoP,idAlumnos, telefono from Registro , Alumnos  WHERE Registro.idAlumno=Alumnos.idAlumnos"+where;
            //String sql = "SELECT idRegistro, idPaquete, idHorario, Categoria, carril, idAlumno, Status_Pago FROM Registro" + where;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            modelo.addColumn("idRegistro");
            modelo.addColumn("Paquete");
            modelo.addColumn("Horario");
            modelo.addColumn("Categoria");
            modelo.addColumn("Carril");
            modelo.addColumn("Status_Pago");
            modelo.addColumn("nombre");
            modelo.addColumn("apellidoP");
            modelo.addColumn("idAlumno");
            modelo.addColumn("telefono");
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
        }
        limpiarCajas();
        contarRegistros();
        //getStatatus();
        jTRegistro.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(0).setResizable(false);
        jTRegistro.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTRegistro.getColumnModel().getColumn(1).setResizable(false);
        jTRegistro.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTRegistro.getColumnModel().getColumn(2).setResizable(false);
        jTRegistro.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTRegistro.getColumnModel().getColumn(3).setResizable(false);
        jTRegistro.getColumnModel().getColumn(4).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(4).setResizable(false);
        jTRegistro.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTRegistro.getColumnModel().getColumn(5).setResizable(false);
        jTRegistro.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTRegistro.getColumnModel().getColumn(6).setResizable(false);
        jTRegistro.getColumnModel().getColumn(7).setPreferredWidth(100);
        jTRegistro.getColumnModel().getColumn(7).setResizable(false);
        jTRegistro.getColumnModel().getColumn(8).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(8).setResizable(false);
        jTRegistro.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTRegistro.getColumnModel().getColumn(9).setResizable(false);
 
        
         jTRegistro.setRowHeight(30);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jtxtIdRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtIdRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIdRegistroActionPerformed

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        // TODO add your handling code here:
        cont = 0;
        validar();
        if (cont == 0) {
            Connection con = null;

            String u = getUsuario();
            System.out.println(u);
            int us = Integer.parseInt(u);
              
            if (us == 1) {
                //int Integer.parseInt(jtxtIdRegistro.getText());
                  String idA = jtxtIdRegistro.getText();      
                  System.out.println(idA);
                if ("".equals(idA)) {
                    JOptionPane.showMessageDialog(rootPane, "Seleccione un Registro");
                } else {
                    try {
                        //String p = jtxtPaquete.getText().toString();
                        //String c = jtxtHorario.getText();
                        //String h = jtxtCategoria.getText();
                        

                        con = getConection();
                        ps = con.prepareStatement("DELETE FROM Registro WHERE idRegistro=?");

                        ps.setString(1, jtxtIdRegistro.getText());

                        int res = ps.executeUpdate();
                        if (res > 0) {
                            String carril = jTxtCarril.getText();
                            int ic = Integer.parseInt(carril);
                            if (ic == 1) {
                                updateCarril1();
                            }
                            if (ic == 2){
                                updateCarril2();
                            }
                            JOptionPane.showMessageDialog(rootPane, "Registro Eleminado");
                            limpiarCajas();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Error al Eleminar");
                            limpiarCajas();
                        }
                        con.close();
                    } catch (Exception e) {
                        System.out.println(e+"sad");
                    }
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "No Tiene Permisos Para Eleminar");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un registro");
        }

    }//GEN-LAST:event_bEliminarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          // TODO add your handling code here:

        Connection con = null;
        con = getConection();
        JasperReport reporte = null;
        String path = "C:\\Program Files\\TiburonBlanco\\registros2.jrxml";
        try {
            //Map parametro=new HashMap();
            //parametro.put("'status'","NoPagado");
            reporte = (JasperReport) JasperCompileManager.compileReport(path);
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, null, con);
            JasperViewer view = new JasperViewer(imprimir, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(TRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jtxtCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtCRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtCRActionPerformed

    private void datPaqueteCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datPaqueteCbxActionPerformed
        // TODO add your handling code here:
        ValidarCbxCategoria();
    }//GEN-LAST:event_datPaqueteCbxActionPerformed

    private void datCategoriaCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datCategoriaCbxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_datCategoriaCbxActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu m = new Menu();
        m.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jtxtHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtHorarioActionPerformed

    private void datHorarioCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datHorarioCbxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_datHorarioCbxActionPerformed

    private void nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreKeyTyped
         // TODO add your handling code here:
//         char c=evt.getKeyChar();
  //      if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_nombreKeyTyped

    private void apellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_apellidoKeyTyped
         // TODO add your handling code here:
//         char c=evt.getKeyChar();
  //      if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_apellidoKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
         // TODO add your handling code here       
        Connection con = null;
        con = getConection();
        JasperReport reporte = null;
        String path = "C:\\Program Files\\TiburonBlanco\\registros.jrxml";
        try {
            //Map parametro=new HashMap();
            //parametro.put("'status'","NoPagado");
            reporte = (JasperReport) JasperCompileManager.compileReport(path);
            JasperPrint imprimir = JasperFillManager.fillReport(reporte, null, con);
            JasperViewer view = new JasperViewer(imprimir, false);
            view.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            view.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(TRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_IDKeyTyped

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        tabla TR=new tabla();
        TR.setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ID;
    private javax.swing.JTextField apellido;
    private javax.swing.JButton bEliminar;
    private javax.swing.JComboBox<String> datCategoriaCbx;
    private javax.swing.JComboBox<String> datHorarioCbx;
    private javax.swing.JComboBox<String> datPaqueteCbx;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTRegistro;
    private javax.swing.JTextField jTxtCarril;
    private javax.swing.JTextField jTxtStatus;
    private javax.swing.JTextField jtxtCR;
    private javax.swing.JTextField jtxtCategoria;
    private javax.swing.JTextField jtxtHorario;
    private javax.swing.JTextField jtxtIdRegistro;
    private javax.swing.JTextField jtxtPaquete;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
