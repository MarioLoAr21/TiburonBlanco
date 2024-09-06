/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tiburonesblancos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static tiburonesblancos.TRegistro.getConection;


/**
 *
 * @author ZEPHYRUS
 */
public class Asitencia extends javax.swing.JFrame {

    /**
     * Creates new form Asitencia
     */
    public Asitencia() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
        nombre.setDocument(new SoloMayusculas());
        apellido.setDocument(new SoloMayusculas());
        //rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo, "C:\\Users\\Giuseppe\\Desktop\\Tiburon\\Fondo.jpg");

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
            modelo.addColumn("Id");
            modelo.addColumn("Paquete");
            modelo.addColumn("Horario");
            modelo.addColumn("Categoria");
            modelo.addColumn("Carril");
            modelo.addColumn("Status_Pago");
            
            modelo.addColumn("Nombre");
            modelo.addColumn("Apellido Paterno");
            modelo.addColumn("Id Alumno");
            modelo.addColumn("Telefono");
            
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
         
        //contarRegistros();
        jTRegistro.getColumnModel().getColumn(0).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(0).setResizable(false);
        jTRegistro.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTRegistro.getColumnModel().getColumn(1).setResizable(false);
        jTRegistro.getColumnModel().getColumn(2).setPreferredWidth(150);
        jTRegistro.getColumnModel().getColumn(2).setResizable(false);
        jTRegistro.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTRegistro.getColumnModel().getColumn(3).setResizable(false);
        jTRegistro.getColumnModel().getColumn(4).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(4).setResizable(false);
        jTRegistro.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTRegistro.getColumnModel().getColumn(5).setResizable(false);
        jTRegistro.getColumnModel().getColumn(6).setPreferredWidth(200);
        jTRegistro.getColumnModel().getColumn(6).setResizable(false);
        jTRegistro.getColumnModel().getColumn(7).setPreferredWidth(50);
        jTRegistro.getColumnModel().getColumn(7).setResizable(false);
        jTRegistro.getColumnModel().getColumn(8).setPreferredWidth(1);
        jTRegistro.getColumnModel().getColumn(8).setResizable(false);
        jTRegistro.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTRegistro.getColumnModel().getColumn(9).setResizable(false);
        
        
        
         jTRegistro.setRowHeight(30);

    }

//----------------------------------------------------------------------------------------------------    

    static String login = "root";
    static String password = "16070065";
    static String url = "jdbc:mysql://localhost/tiburonesblancos";
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
        //jComboBox1.setSelectedIndex(0);
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
    /*
    public void contarRegistros() {
        int filas = jTRegistro.getRowCount();
        jtxtAsi.setText(Integer.toString(filas));
    }
    */
//----------------------------------------------------------------------------------------------------    
  private void mostrar2(String n, String a ){
    //  System.out.println(a+","+n);

        Connection con = null;
	 try {
            DefaultTableModel modelo2 = new DefaultTableModel(){
            
            @Override
            public boolean isCellEditable(int Filas, int columnas){
                return columnas == -1;
            }
        }   ;
            Table2.setModel(modelo2);
           

            PreparedStatement ps = null;
            ResultSet rs = null;
            //PreparedStatement ps2= null;
            // ResultSet rs2=null;
            con = getConection();
             String sql=null;
             
                    
        Date fecha = new Date();
       // int dia=fecha.getDate();
        int mes=fecha.getMonth();
        int año=fecha.getYear();
        
        
        
        int mesSelect= MesCbx.getSelectedIndex();
        String añoSelected=AñoCbx.getSelectedItem().toString();
        
        int añoActual=año+1900;
        int mesActual=mes+1;
        //System.out.println(mesSelect);
        //System.out.println(añoSelected);    
            if(AñoCbx.getSelectedItem()=="-" && MesCbx.getSelectedItem()=="-")
            {
                 sql = "select Nombre, apellidoP, apellidoM, fecha from asistencia WHERE nombre='"+n+"' and apellidoP='"+a+"' and fecha between '"+añoActual+"-"+mesActual+"-1' and '"+añoActual+"-"+mesActual+"-31';";
            }
        
            if(MesCbx.getSelectedItem()!="-")
            {
                 sql = "select Nombre, apellidoP, apellidoM, fecha from asistencia WHERE nombre='"+n+"' and apellidoP='"+a+"' and fecha between '"+añoActual+"-"+mesSelect+"-1' and '"+añoActual+"-"+mesSelect+"-31';";
            }
            if(AñoCbx.getSelectedItem()!="-" && MesCbx.getSelectedItem()=="-")
            {
                 javax.swing.JOptionPane.showMessageDialog(null,"Seleccione un mes");
            }
            if(AñoCbx.getSelectedItem()!="-" && MesCbx.getSelectedItem()!="-" )
            {
                 sql = "select Nombre, apellidoP, apellidoM, fecha from asistencia WHERE nombre='"+n+"' and apellidoP='"+a+"' and fecha between '"+añoSelected+"-"+mesSelect+"-1' and '"+añoSelected+"-"+mesSelect+"-31';";
            }
            
            
            //String sql = " select nombre, apellidoP, apellidoM, fecha from asistencia where nombre='"+n+"' AND apellidop='"+a+"';";
            //String sql = "SELECT idRegistro, idPaquete, idHorario, Categoria, carril, idAlumno, Status_Pago FROM Registro";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //ps2 =con.prepareStatement(id);
            //rs2 = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo2.addColumn("Nombre");
            modelo2.addColumn("Apelido P");
            modelo2.addColumn("Apeliido M");
            modelo2.addColumn("Fecha");
          
          
            
            while (rs.next()) {

                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo2.addRow(filas);
            }

        } catch (SQLException e) {
            System.err.println(e.toString());
            System.out.print("error al mostrar");
        }
        
         Table2.setRowHeight(50);
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
    //categoirias C=?
    //C=1  ------>  niños
    //C=2  ------>  bebes
    //C=3  ------>  adultos
    
    
    //-----------------------------------------------------------Lunes, miercoles, jueves
    
    //categoria niños
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
    //categoria bebes
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
    datCategoriaCbx.enable(false);
    datPaqueteCbx.enable(false);
    }
    //categoria adultos
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Año = new javax.swing.JLabel();
        Mes = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        apellido = new javax.swing.JTextField();
        ID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jtxtAsi = new javax.swing.JTextField();
        jtxtIdRegistro = new javax.swing.JTextField();
        jtxtPaquete = new javax.swing.JTextField();
        jtxtHorario = new javax.swing.JTextField();
        jtxtCategoria = new javax.swing.JTextField();
        jTxtCarril = new javax.swing.JTextField();
        jTxtStatus = new javax.swing.JTextField();
        jTxtNombre = new javax.swing.JTextField();
        jTxtApellido = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AñoCbx = new javax.swing.JComboBox<>();
        MesCbx = new javax.swing.JComboBox<>();
        datCategoriaCbx = new javax.swing.JComboBox<>();
        datPaqueteCbx = new javax.swing.JComboBox<>();
        datHorarioCbx = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTRegistro = new javax.swing.JTable();
        jLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setUndecorated(true);
        getContentPane().setLayout(null);

        jButton3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton3.setText("Menu");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu2.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(0, 0, 110, 60);

        jLabel8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel8.setText("ID Alumno");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(530, 92, 92, 30);

        jLabel6.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel6.setText("Apellido P");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(540, 170, 86, 30);

        jLabel2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(560, 130, 67, 30);

        Año.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        Año.setText("Año");
        getContentPane().add(Año);
        Año.setBounds(1475, 560, 40, 22);

        Mes.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        Mes.setText("Mes");
        getContentPane().add(Mes);
        Mes.setBounds(1286, 558, 70, 30);

        nombre.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nombreKeyTyped(evt);
            }
        });
        getContentPane().add(nombre);
        nombre.setBounds(640, 130, 220, 30);

        apellido.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        apellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                apellidoKeyTyped(evt);
            }
        });
        getContentPane().add(apellido);
        apellido.setBounds(640, 170, 220, 30);

        ID.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        ID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                IDKeyTyped(evt);
            }
        });
        getContentPane().add(ID);
        ID.setBounds(640, 90, 220, 30);

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
        getContentPane().add(jButton1);
        jButton1.setBounds(1540, 90, 100, 90);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("No. Asistencias");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(250, 880, 136, 22);

        jtxtAsi.setEditable(false);
        jtxtAsi.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jtxtAsi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtAsiActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtAsi);
        jtxtAsi.setBounds(390, 876, 14, 30);

        jtxtIdRegistro.setEditable(false);
        jtxtIdRegistro.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jtxtIdRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtIdRegistroActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtIdRegistro);
        jtxtIdRegistro.setBounds(270, 490, 60, 40);

        jtxtPaquete.setEditable(false);
        jtxtPaquete.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jtxtPaquete);
        jtxtPaquete.setBounds(340, 490, 200, 40);

        jtxtHorario.setEditable(false);
        jtxtHorario.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jtxtHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtHorario);
        jtxtHorario.setBounds(550, 490, 230, 40);

        jtxtCategoria.setEditable(false);
        jtxtCategoria.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jtxtCategoria);
        jtxtCategoria.setBounds(790, 490, 140, 40);

        jTxtCarril.setEditable(false);
        jTxtCarril.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jTxtCarril);
        jTxtCarril.setBounds(940, 490, 60, 40);

        jTxtStatus.setEditable(false);
        jTxtStatus.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jTxtStatus);
        jTxtStatus.setBounds(1010, 490, 110, 40);

        jTxtNombre.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jTxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(jTxtNombre);
        jTxtNombre.setBounds(1130, 490, 280, 40);

        jTxtApellido.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jTxtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTxtApellidoActionPerformed(evt);
            }
        });
        getContentPane().add(jTxtApellido);
        jTxtApellido.setBounds(1420, 490, 220, 40);

        jLabel9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel9.setText("Horario");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(920, 170, 65, 22);

        jLabel4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel4.setText("Paquete");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(910, 130, 68, 30);

        jLabel7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel7.setText("Categoria");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(900, 100, 82, 22);

        AñoCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        AñoCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "2020", "2021", "2022", "2023", "2024", "2025" }));
        AñoCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AñoCbxActionPerformed(evt);
            }
        });
        getContentPane().add(AñoCbx);
        AñoCbx.setBounds(1520, 550, 120, 40);

        MesCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        MesCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre" }));
        MesCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MesCbxActionPerformed(evt);
            }
        });
        getContentPane().add(MesCbx);
        MesCbx.setBounds(1330, 550, 125, 40);

        datCategoriaCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datCategoriaCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Niños", "Bebes", "Adultos" }));
        datCategoriaCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datCategoriaCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datCategoriaCbx);
        datCategoriaCbx.setBounds(1000, 90, 250, 32);

        datPaqueteCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datPaqueteCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Lunes, Miercoles, Viernes", "Martes, Jueves", "Sabado" }));
        datPaqueteCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datPaqueteCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datPaqueteCbx);
        datPaqueteCbx.setBounds(1000, 130, 250, 32);

        datHorarioCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datHorarioCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        datHorarioCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datHorarioCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datHorarioCbx);
        datHorarioCbx.setBounds(1000, 170, 250, 32);

        Table2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido Paterno", "Apellido Materno", "Fecha"
            }
        ));
        Table2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(Table2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(270, 600, 1370, 250);

        jTRegistro = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTRegistro.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jTRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Paquete", "Horario", "Categoria", "Carril", "Status pago", "Nombre", "Apellido Paterno", "Id", "Telefono"
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

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(270, 210, 1370, 260);

        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu/Alumnos_.jpg"))); // NOI18N
        getContentPane().add(jLFondo);
        jLFondo.setBounds(0, 0, 1920, 1080);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu m=new Menu();
        m.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void IDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_IDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_IDKeyTyped

    private void datCategoriaCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datCategoriaCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datCategoriaCbxActionPerformed

    private void datPaqueteCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datPaqueteCbxActionPerformed
        // TODO add your handling code here:
        ValidarCbxCategoria();
    }//GEN-LAST:event_datPaqueteCbxActionPerformed

    private void datHorarioCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datHorarioCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datHorarioCbxActionPerformed

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
       // String campo2 = (String) jComboBox1.getSelectedItem();
        String where = "";
        String campo3 = (String) datPaqueteCbx.getSelectedItem();
        String campo4 = (String) datCategoriaCbx.getSelectedItem();
        
        /*
        if (!jComboBox1.getSelectedItem().equals("-") && datCategoriaCbx.getSelectedItem().equals("-") && datPaqueteCbx.getSelectedItem().equals("-") && datHorarioCbx.getSelectedItem().equals("-")) {
            where = " AND Status_Pago='" + campo2 + "' AND " +"Categoria='" + campo4 + "'" + "AND idPaquete='" + campo3 + "'"+" AND idHorario='" + hora + "'";
        }
        */
        
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
        
        /*
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
       */
        
        try {

            DefaultTableModel modelo = new DefaultTableModel();
            jTRegistro.setModel(modelo);

            //PreparedStatement ps = null;
            //ResultSet rs = null;
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
//        contarRegistros();
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
        //getStatatus();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1KeyPressed

    private void jtxtAsiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtAsiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtAsiActionPerformed

    private void jTRegistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTRegistroMouseClicked
  
        int i = jTRegistro.getSelectedRow();
        TableModel model = jTRegistro.getModel();
       
        jtxtIdRegistro.setText(model.getValueAt(i, 0).toString());
        jtxtPaquete.setText(model.getValueAt(i, 1).toString());
        jtxtHorario.setText(model.getValueAt(i, 2).toString());
        jtxtCategoria.setText(model.getValueAt(i, 3).toString());
        jTxtCarril.setText(model.getValueAt(i, 4).toString());
        jTxtStatus.setText(model.getValueAt(i, 5).toString());
        jTxtNombre.setText(model.getValueAt(i, 6).toString());
        jTxtApellido.setText(model.getValueAt(i, 7).toString());
        mostrar2(model.getValueAt(i, 6).toString(),model.getValueAt(i, 7).toString());
        
        jtxtAsi.setText(""+(Table2.getModel().getRowCount()));
     
        
       
    }//GEN-LAST:event_jTRegistroMouseClicked

    private void jtxtIdRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtIdRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIdRegistroActionPerformed

    private void jtxtHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtHorarioActionPerformed

    private void Table2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Table2KeyPressed

    private void jTxtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtApellidoActionPerformed

    private void jTxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTxtNombreActionPerformed

    private void AñoCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AñoCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AñoCbxActionPerformed

    private void MesCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MesCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MesCbxActionPerformed

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
            java.util.logging.Logger.getLogger(Asitencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Asitencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Asitencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Asitencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Asitencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Año;
    private javax.swing.JComboBox<String> AñoCbx;
    private javax.swing.JTextField ID;
    private javax.swing.JLabel Mes;
    private javax.swing.JComboBox<String> MesCbx;
    private javax.swing.JTable Table2;
    private javax.swing.JTextField apellido;
    private javax.swing.JComboBox<String> datCategoriaCbx;
    private javax.swing.JComboBox<String> datHorarioCbx;
    private javax.swing.JComboBox<String> datPaqueteCbx;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTRegistro;
    private javax.swing.JTextField jTxtApellido;
    private javax.swing.JTextField jTxtCarril;
    private javax.swing.JTextField jTxtNombre;
    private javax.swing.JTextField jTxtStatus;
    private javax.swing.JTextField jtxtAsi;
    private javax.swing.JTextField jtxtCategoria;
    private javax.swing.JTextField jtxtHorario;
    private javax.swing.JTextField jtxtIdRegistro;
    private javax.swing.JTextField jtxtPaquete;
    private javax.swing.JTextField nombre;
    // End of variables declaration//GEN-END:variables
}
