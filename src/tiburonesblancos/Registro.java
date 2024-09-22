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
import javax.swing.JOptionPane;
import static tiburonesblancos.TRegistro.getConection;

/**
 *
 * @author Giuseppe
 */
public class Registro extends javax.swing.JFrame {


//----------------------------------------------------------------------------------------------------    
static String login="root";
static String password="16070065";
static String url="jdbc:mysql://localhost/tiburonesblancos?characterEncoding=latin1";
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
    this.datApellidoM.setText(null);
    this.datNombre.setText(null);
    this.datIdAlumno.setText(null);
    this.datCarrilCbx.setSelectedItem("Seleccione");    
    
        datCategoriaCbx.setSelectedIndex(0);
        datCategoriaCbx.enable(true);
        datPaqueteCbx.enable(true);
        datPaqueteCbx.setSelectedIndex(0);
        datHorarioCbx.removeAllItems();
        datHorarioCbx.addItem("Seleccione");
        datCarta_Seguro.setSelectedIndex(0);
        
 }
//----------------------------------------------------------------------------------------------------
public String getReistro(String id)
{
    String v="0";
    Connection con=null;
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT idRegistro FROM Registro WHERE idAlumno=? ");
            ps.setString(1,id);
            rs=ps.executeQuery();
            if(rs.next())
            {
                v=(rs.getString("idRegistro"));                
            }
            else
            {
                //JOptionPane.showMessageDialog(null,"No se encontro el dato");
                //limpiarCajas();
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error  :D");
        }
        return v;
}
int cont;
public void validar(){
    
    if(datCategoriaCbx.getSelectedItem().equals("Seleccione")){cont++;}
    if(datCarrilCbx.getSelectedItem().equals("Seleccione")){cont++;}
    if(datHorarioCbx.getSelectedItem().equals("Seleccione")){cont++;}
    if(datPaqueteCbx.getSelectedItem().equals("Seleccione")){cont++;}
    if(datIdAlumno.getText().equals("")){cont++;}
    if(datCarta_Seguro.getSelectedItem().equals("Seleccione")){cont++;}
     
    
}
public String getValorCarril1(String p, String c, String h)
{
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
public String getValorCarril2(String p, String c, String h)
{
   String v="";
    Connection con=null;
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT carril2 FROM Carril WHERE paquete=? AND categoria=? AND Horario=? ");
            ps.setString(1,p);
            ps.setString(2,c);
            ps.setString(3,h);
            rs=ps.executeQuery();
            if(rs.next())
            {
                v=(rs.getString("carril2"));                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el dato");
                limpiarCajas();
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error 2 :D");
        }
        return v;
}

    public void updateCarril1() {
        String p, c, h; int res=0;
        Conexion up=new Conexion();
        
                p=datPaqueteCbx.getSelectedItem().toString();
                c=datCategoriaCbx.getSelectedItem().toString();
                h=datHorarioCbx.getSelectedItem().toString();
       
        
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
        
        p=datPaqueteCbx.getSelectedItem().toString();
                c=datCategoriaCbx.getSelectedItem().toString();
                h=datHorarioCbx.getSelectedItem().toString();
       
        
        res=up.UpdateCountcarril(p, c, h);
       
            if (res > 0) {
                JOptionPane.showMessageDialog(rootPane, "Carril 2 Actualizado");
                limpiarCajas();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Error al Actualizar carril");
                limpiarCajas();
            }
    }
    public Registro() {//------------------------------------------------------------------------------------------Constructor
        initComponents();
        this.setLocationRelativeTo(null);
        //rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo,"src/imgs/Actividades_Natacion.jpg");
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
    //categoirias C=?
    //C=1  ------>  niños
    //C=2  ------>  bebes
    //C=3  ------>  adultos
    
    
    //-----------------------------------------------------------Lunes, miercoles, viernes
    
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
    //  horario extra bbs 06 07 2023
    datHorarioCbx.addItem("08:10 - 08:40 p.m.");
    // horario extra bbs 28/09/2023
    datHorarioCbx.addItem("08:40 - 09:10 p.m.");
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

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        datPaqueteCbx = new javax.swing.JComboBox<>();
        datHorarioCbx = new javax.swing.JComboBox<>();
        datCarrilCbx = new javax.swing.JComboBox<>();
        datIdAlumno = new javax.swing.JTextField();
        datNombre = new javax.swing.JTextField();
        datApellidoM = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        bRegistrar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        datCategoriaCbx = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        datCarta_Seguro = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLFondo = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        datPaqueteCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datPaqueteCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Lunes, Miercoles, Viernes", "Martes, Jueves", "Sabado" }));
        datPaqueteCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datPaqueteCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datPaqueteCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 250, 40));

        datHorarioCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datHorarioCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        datHorarioCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datHorarioCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datHorarioCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 270, 250, 40));

        datCarrilCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datCarrilCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Carril 1", "Carril 2" }));
        datCarrilCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datCarrilCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datCarrilCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 330, 130, 40));

        datIdAlumno.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(datIdAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 540, 146, 40));

        datNombre.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datNombreActionPerformed(evt);
            }
        });
        datNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datNombreKeyTyped(evt);
            }
        });
        getContentPane().add(datNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 430, 220, 40));

        datApellidoM.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datApellidoM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datApellidoMKeyTyped(evt);
            }
        });
        getContentPane().add(datApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 430, 190, 40));

        jButton1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Busc.png"))); // NOI18N
        jButton1.setText("Buscar Alumno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 480, -1, 40));

        jLabel1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel1.setText("Paquete*");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 212, -1, 40));

        jLabel6.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel6.setText("Nombre");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 402, -1, 30));

        jLabel7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel7.setText("Apellido Paterno");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 402, -1, 30));

        jLabel2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel2.setText("Horario*");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 270, -1, 40));

        jLabel3.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel3.setText("Carta Responsiva/Seguro*");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 600, -1, 40));

        jLabel4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel4.setText("Carril*");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 330, -1, 40));

        jLabel5.setFont(new java.awt.Font("Century", 0, 24)); // NOI18N
        jLabel5.setText("Registro");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 90, -1, -1));

        bRegistrar.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        bRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agrega.png"))); // NOI18N
        bRegistrar.setBorder(null);
        bRegistrar.setBorderPainted(false);
        bRegistrar.setContentAreaFilled(false);
        bRegistrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agrega.png"))); // NOI18N
        bRegistrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agregar2.png"))); // NOI18N
        bRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(bRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 660, 110, 110));

        jLabel8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel8.setText("Categoria*");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 150, -1, 50));

        jLabel10.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel10.setText("Agregar");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 760, -1, -1));

        datCategoriaCbx.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datCategoriaCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Niños", "Bebes", "Adultos" }));
        datCategoriaCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datCategoriaCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datCategoriaCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 150, 200, 40));

        jButton2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Busc.png"))); // NOI18N
        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 150, 160, 40));

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
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 60));

        datCarta_Seguro.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datCarta_Seguro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Si", "No" }));
        getContentPane().add(datCarta_Seguro, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 600, 150, 40));

        jLabel9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel9.setText("ID del Alumno*");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 542, -1, 40));

        jLFondo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLFondo.setForeground(new java.awt.Color(255, 255, 255));
        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu/Alumnos_.jpg"))); // NOI18N
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void datPaqueteCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datPaqueteCbxActionPerformed
        // TODO add your handling code here:
       ValidarCbxCategoria();
    }//GEN-LAST:event_datPaqueteCbxActionPerformed

    private void datCarrilCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datCarrilCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datCarrilCbxActionPerformed
    int v4=0;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // TODO add your handling code here:
        Connection con=null;
        if(datIdAlumno.getText().equals(""))
        {
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT idAlumnos FROM Alumnos WHERE nombre=? AND apellidop=?");//// corregir alumnos en el bd
            ps.setString(1,datNombre.getText());
            ps.setString(2,datApellidoM.getText());
            rs=ps.executeQuery();
            if(rs.next())
            {
                datIdAlumno.setText(rs.getString("idAlumnos"));                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el Alumno");
                datApellidoM.setText(null);
                datNombre.setText(null);
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error 5 :D");
        }
        }
        else
        {
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT nombre, apellidoP FROM Alumnos WHERE idAlumnos=?");//// corregir alumnos en el bd
            ps.setString(1,datIdAlumno.getText());
            rs=ps.executeQuery();
            if(rs.next())
            {
                datNombre.setText(rs.getString("nombre"));
                datApellidoM.setText(rs.getString("apellidoP"));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el Alumno");
                datApellidoM.setText(null);
                datNombre.setText(null);
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error 5.2 :D");
        }    
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void datNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datNombreActionPerformed

    private void bRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRegistrarActionPerformed
        // TODO astemdd your handling code here:
        
        cont=0;
        validar();
        String v=getReistro(datIdAlumno.getText().toString());
        String Carta=datCarta_Seguro.getSelectedItem().toString();
        if("0".equals(v))
        {    
        if(cont==0)
        {
        String val="";
        String p,c,h;
        int cupo=0;
        int vint=0;
                p=datPaqueteCbx.getSelectedItem().toString();
                c=datCategoriaCbx.getSelectedItem().toString();
                h=datHorarioCbx.getSelectedItem().toString();

            String ps6=datIdAlumno.getText();
            switch(datCarrilCbx.getSelectedIndex())
            {
                case 1:
                    val=getValorCarril1(p,c,h);
                    vint=Integer.parseInt(val);
                    break;
                case 2:
                    val=getValorCarril2(p,c,h);
                    vint=Integer.parseInt(val);
                    break;
            }
            switch(datCategoriaCbx.getSelectedIndex())
            {
                case 1: cupo=8; break;
                case 2: cupo=3; break;
                case 3: cupo=5; break;
            }
        if((vint<cupo) ){
        Connection con=null;
        try{
            con=getConection();
            int v1=datPaqueteCbx.getSelectedIndex();
String ps2="";
            switch(v1)
            {
                case 1:  ps2="Lunes, Miercoles, Viernes"; //datHorarioCbx.setSelectedItem("ssdfadas");
                break;
                case 2:  ps2="Martes, Jueves"; break;
                case 3:  ps2="Sabado"; break;              
            }
int v2=datHorarioCbx.getSelectedIndex();
String ps3=datHorarioCbx.getSelectedItem().toString();
/*String ps3="";
            switch(v2)
            {
                case 1: ps3=("1"); break;
                case 2: ps3=("2"); break;
                case 3: ps3=("3"); break;
                case 4: ps3=("4"); break;
                case 5: ps3=("5"); break;
                case 6: ps3=("6"); break;
                case 7: ps3=("7"); break;
                case 8: ps3=("8"); break;                
            }
*/
int v3=datCategoriaCbx.getSelectedIndex();
String ps4="";
            switch(v3)
            {
                case 1: ps4="Niños"; break;
                case 2: ps4="Bebes"; break;
                case 3: ps4="Adultos"; break;                
            }
     v4=datCarrilCbx.getSelectedIndex();
String ps5;
            if(v4==1)
            {
                ps5="1";
                
            }
            else
            {
                ps5="2";  
                               
            }
            //String ps6=datIdAlumno.getText();
            
            ps= con.prepareStatement("INSERT INTO Registro(idRegistro, idPaquete, idHorario, categoria, carril, idAlumno, carta_Seguro) VALUES(?,?,?,?,?,?,?)");//===================Corregir horario Bd
            ps.setString(1,null);
            ps.setString(2,ps2);
            ps.setString(3,ps3);
            ps.setString(4,ps4);
            ps.setString(5,ps5);            
            ps.setString(6,ps6);
            ps.setString(7,Carta);
            System.out.println(Carta);
            
            int res=ps.executeUpdate();
            if(res>0)
            {
                
                if(v4==1)
                {                    
                    updateCarril1();
                }
                if(v4==2)
                {  
                    updateCarril2();                 
                }
                    JOptionPane.showMessageDialog(rootPane,"Alumno Resgistrado");
                    limpiarCajas();
                    Conexion x = new Conexion();        
                    x.pagosActivos();
                    x.noPagados();
                    x.Pagados();
                }
            
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Error al Resgistrar");
                limpiarCajas();
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e+"Error 6 :D");
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"El Carril "+(datCarrilCbx.getSelectedIndex())+" ya esta lleno en el horario "+datHorarioCbx.getSelectedItem()+" en la categoria de "+datCategoriaCbx.getSelectedItem());
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Rellene los campos Requeridos");
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"El alumno ya esta en el idRegistro:"+v);
        }
        
    }//GEN-LAST:event_bRegistrarActionPerformed

    private void datCategoriaCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datCategoriaCbxActionPerformed
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_datCategoriaCbxActionPerformed

    private void datHorarioCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datHorarioCbxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_datHorarioCbxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        datCategoriaCbx.enable(true);
        datCategoriaCbx.setSelectedIndex(0);
        datPaqueteCbx.enable(true);
        datPaqueteCbx.setSelectedIndex(0);
        datHorarioCbx.removeAllItems();
        datHorarioCbx.addItem("Seleccione");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         // TODO add your handling code here:
         TRegistro tr=new TRegistro();
        this.setVisible(false);
        tr.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void datNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datNombreKeyTyped
         // TODO add your handling code here:
   //      char c=evt.getKeyChar();
     //   if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_datNombreKeyTyped

    private void datApellidoMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datApellidoMKeyTyped
         // TODO add your handling code here:
//         char c=evt.getKeyChar();
  //      if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_datApellidoMKeyTyped

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bRegistrar;
    private javax.swing.JTextField datApellidoM;
    private javax.swing.JComboBox<String> datCarrilCbx;
    private javax.swing.JComboBox<String> datCarta_Seguro;
    private javax.swing.JComboBox<String> datCategoriaCbx;
    private javax.swing.JComboBox<String> datHorarioCbx;
    private javax.swing.JTextField datIdAlumno;
    private javax.swing.JTextField datNombre;
    private javax.swing.JComboBox<String> datPaqueteCbx;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
