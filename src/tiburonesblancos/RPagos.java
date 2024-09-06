/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiburonesblancos;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static tiburonesblancos.Alumnos.getConection;
import static tiburonesblancos.RAlumnos.getConection;
import static tiburonesblancos.Registro.getConection;


public class RPagos extends javax.swing.JFrame {
//----------------------------------------------------------------------------------------------------    
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

    
private void limpiarCajas()
{
   d1.setText(null);
   d2.setSelectedIndex(0);
   d2Cbx2.setSelectedIndex(0);
   d3.setSelectedIndex(0);
   d4.setText(null);
   d5.setText("0");
   d6.setText(null);
   d7.setText(null);
   d8.setText(null);
   d9.setText(null);
   d10.setSelectedIndex(0);
   d11.setText(null);
   jCbxDia.setSelectedIndex(0);
   jCbxDia.setVisible(false);
   jBDia.setVisible(false);
   datApellidoP.setText(null);
   datNombre.setText(null);
        
        
 }
//----------------------------------------------------------------------------------------------------
int cont;
public void validar(){
    if(d1.getText().equals("")){cont++;}
    if(d2.getSelectedItem().equals("Seleccione")){cont++;}
    if(d3.getSelectedItem().equals("Seleccione")){cont++;}
    if(d4.getText().equals("")){cont++;}
    if(d6.getText().equals("")){cont++;}
    if(d7.getText().equals("")){cont++;}
    if(d8.getText().equals("")){cont++;}
    if(d9.getText().equals("")){cont++;}
    if(d10.getSelectedItem().equals("Seleccione")){cont++;}
    if(d11.getText().equals("")){cont++;}
    if(d2Cbx2.getSelectedItem().equals("Seleccione")){cont++;}
    //d2Cbx2
    
}
  
    public RPagos() {
        initComponents();
        jCbxDia.setVisible(false);
        jBDia.setVisible(false);
        this.setLocationRelativeTo(null);
 //       rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo,"src/imgs/Actividades_Natacion.jpg");
        bActualizar.setVisible(false);
    }
    String id="0";
    String gid="0";
    
    public RPagos(String id)
    {       
        initComponents();
        jCbxDia.setVisible(false);
        jBDia.setVisible(false);
        this.setLocationRelativeTo(null);
   //     rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo,"src/imgs/Actividades_Natacion.jpg");
        bRegistrar.setVisible(false);
        jButton4.setVisible(false);
         gid=id;
        if(id != "0")
        {
            Connection con=null;
        try{
            con=getConection();
            ps= con.prepareStatement("Select * FROM Pagos WHERE idPagos=?");
            ps.setString(1,id);
            
            rs=ps.executeQuery();
            if(rs.next())
            {                
                d1.setText(rs.getString("idAlumno"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                d2.setSelectedItem(rs.getString("mes"));
                String vencimiento=rs.getString("vencimiento");
                    sdf.parse(vencimiento);
                    int m=sdf.parse(vencimiento).getMonth()+1;
                    int mesespagados=d2.getSelectedIndex();
                    int a=2019;
                    //int dia=13;
                    
                    for(int i=0;i<mesespagados;i++)
                    {
                    m=m-1;
                    if(m==0)
                    {                       
                        m=12;
                    }
                    }  
                d2Cbx2.setSelectedIndex(m);
                d3.setSelectedItem(rs.getString("categoria"));                
                d4.setText(rs.getString("costo"));
                d5.setText(rs.getString("desccuento"));
                d6.setText(rs.getString("total"));
                //jTextField7.setText(rs.getString("fechaN"));
                
                
                String date=rs.getString("fecha");
                sdf.parse(date);
                
                System.out.println(sdf.parse(date));
                int año=sdf.parse(date).getYear()+1900;
                int mes=sdf.parse(date).getMonth()+1;
                int dia=sdf.parse(date).getDate();                
                d9.setText(Integer.toString(año));
                d8.setText(Integer.toString(mes));
                d7.setText(Integer.toString(dia));
                d10.setSelectedItem(rs.getString("tipoPago"));
                d11.setText(rs.getString("folio"));
               
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No exisste");
                limpiarCajas();

            }
        }
        catch(Exception e)
        {
            System.out.println(e+" Este es el erorro");
        }
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

        jMenuItem1 = new javax.swing.JMenuItem();
        jComboBox1 = new javax.swing.JComboBox<>();
        d5 = new javax.swing.JTextField();
        d6 = new javax.swing.JTextField();
        d7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        d1 = new javax.swing.JTextField();
        d4 = new javax.swing.JTextField();
        d3 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        d8 = new javax.swing.JTextField();
        d9 = new javax.swing.JTextField();
        d2 = new javax.swing.JComboBox<>();
        bRegistrar = new javax.swing.JButton();
        datNombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        datApellidoP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        bActualizar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jBDia = new javax.swing.JButton();
        jCbxDia = new javax.swing.JComboBox<>();
        d2Cbx2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        d10 = new javax.swing.JComboBox<>();
        d11 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLFondo = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        d5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d5.setText("0");
        getContentPane().add(d5, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 500, 140, -1));

        d6.setEditable(false);
        d6.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(d6, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 550, 140, -1));

        d7.setEditable(false);
        d7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                d7KeyTyped(evt);
            }
        });
        getContentPane().add(d7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 600, 40, -1));

        jLabel14.setFont(new java.awt.Font("Century", 1, 36)); // NOI18N
        jLabel14.setText("Gestión de Pagos");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, -1, -1));

        jLabel1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel1.setText("ID del Alumno*");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 250, -1, -1));

        jLabel2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel2.setText("Mes a pagar*");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 340, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel3.setText("Categoria*");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel4.setText("Costo*");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel5.setText("Descuento mensual");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 500, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel6.setText("Total*");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 550, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel7.setText("Tipo depago*");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 292, -1, 30));

        d1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d1ActionPerformed(evt);
            }
        });
        getContentPane().add(d1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 250, 91, -1));

        d4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(d4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 450, 140, -1));

        d3.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Niños", "Bebes", "Adultos" }));
        d3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d3ActionPerformed(evt);
            }
        });
        getContentPane().add(d3, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 410, 140, -1));

        jLabel8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel8.setText("Dia:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 600, -1, -1));

        jLabel9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel9.setText("Mes:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 600, -1, -1));

        jLabel10.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel10.setText("Año:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 600, -1, -1));

        d8.setEditable(false);
        d8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                d8KeyTyped(evt);
            }
        });
        getContentPane().add(d8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 600, 40, -1));

        d9.setEditable(false);
        d9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                d9KeyTyped(evt);
            }
        });
        getContentPane().add(d9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 600, 50, -1));

        d2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        d2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                d2ActionPerformed(evt);
            }
        });
        getContentPane().add(d2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 330, 140, -1));

        bRegistrar.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        bRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/AgregarPago.png"))); // NOI18N
        bRegistrar.setText("Registrar");
        bRegistrar.setBorderPainted(false);
        bRegistrar.setContentAreaFilled(false);
        bRegistrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/AgregarPago.png"))); // NOI18N
        bRegistrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/AgregarPago2.png"))); // NOI18N
        bRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(bRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 700, -1, -1));

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
        getContentPane().add(datNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 170, 200, -1));

        jLabel11.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel11.setText("Nombre");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, -1, -1));

        datApellidoP.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        datApellidoP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datApellidoPActionPerformed(evt);
            }
        });
        datApellidoP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                datApellidoPKeyTyped(evt);
            }
        });
        getContentPane().add(datApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, 200, -1));

        jLabel12.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel12.setText("Apellido Paterno");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 210, -1, -1));

        jButton1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Busc.png"))); // NOI18N
        jButton1.setText("Buscar Alumno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 160, -1, 40));

        jButton2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton2.setText("Calcular");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 450, 110, -1));

        jButton3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton3.setText("Menu");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setDefaultCapable(false);
        jButton3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu2.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 70));

        bActualizar.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        bActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ModificarPago.png"))); // NOI18N
        bActualizar.setText("Actualizar");
        bActualizar.setBorder(null);
        bActualizar.setBorderPainted(false);
        bActualizar.setContentAreaFilled(false);
        bActualizar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ModificarPago.png"))); // NOI18N
        bActualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ModificarPago2.png"))); // NOI18N
        bActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(bActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 700, -1, -1));

        jButton4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton4.setText("Cargar Fecha");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 600, -1, -1));

        jButton5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton5.setText("Modificar Fecha");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 640, -1, -1));

        jBDia.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jBDia.setText("Cargar dia");
        jBDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDiaActionPerformed(evt);
            }
        });
        getContentPane().add(jBDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 640, -1, -1));

        jCbxDia.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jCbxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31\t\t" }));
        getContentPane().add(jCbxDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 640, -1, -1));

        d2Cbx2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d2Cbx2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "1 mes", "2 meses", "3 meses", "4 meses", "5 meses", "6 meses", "7 meses", "8 meses", "9 meses", "10 meses", "11 meses", "12 meses" }));
        getContentPane().add(d2Cbx2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 370, 140, -1));

        jLabel13.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel13.setText("Meses pagados*");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 370, 160, 30));

        d10.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Efectivo", "Tarjeta" }));
        getContentPane().add(d10, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 290, 140, -1));

        d11.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(d11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 290, 150, -1));

        jLabel15.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel15.setText("Fecha*");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 600, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Folio*");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, -1, -1));

        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu/Alumnos_.jpg"))); // NOI18N
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void d1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d1ActionPerformed

    private void d3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d3ActionPerformed

    private void bRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRegistrarActionPerformed
        // TODO astemdd your handling code here:valil
        cont=0;
        validar();
        if(cont==0)
        {
        Connection con=null;
        try{
            con=getConection();
            int v1=d2.getSelectedIndex();
String ps3="";
            switch(v1){
                case 1: ps3="Enero"; break;
                case 2: ps3="Febrero"; break;
                case 3: ps3="Marzo"; break;
                case 4: ps3="Abril"; break;
                case 5: ps3="Mayo"; break;
                case 6: ps3="Junio"; break;
                case 7: ps3="Julio"; break;
                case 8: ps3="Agosto"; break;
                case 9: ps3="Septiembre"; break;
                case 10: ps3="Octubre"; break;
                case 11: ps3="Noviembre"; break;
                case 12: ps3="Diciembre"; break;
            }
            int v2=d3.getSelectedIndex();
	    String ps4="";
            switch(v2){
                case 1: ps4="Niños"; break;
                case 2: ps4="Bebes"; break;
                case 3: ps4="Adultos"; break;                
            }
            String ps9;
                int mes=d2.getSelectedIndex();
                int mesespagados=d2Cbx2.getSelectedIndex();
                int año=Integer.parseInt(d9.getText());
                //int dia=13;
  
                for(int i=0;i<mesespagados;i++)
                {
                    mes=mes+1;
                    if(mes==13)
                    {
                        año=año+1;
                        mes=1;
                    }
                }
                ps9=Integer.toString(año)+"-"+Integer.toString(mes)+"-"+"1";
            ps= con.prepareStatement("INSERT INTO Pagos(idPagos, idAlumno, mes, categoria, costo, desccuento, total, fecha, vencimiento,statusPago,tipoPago, folio,clasificacion) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,null);
            ps.setString(2,d1.getText());
            ps.setString(3,ps3);
            ps.setString(4,ps4);
            ps.setString(5,d4.getText());
            ps.setString(6,d5.getText());
            ps.setString(7,d6.getText());
            ps.setString(8,d9.getText()+"-"+d8.getText()+"-"+d7.getText());
            ps.setString(9,ps9);
            ps.setString(10,"Activo");
            ps.setString(11, (String) d10.getSelectedItem());
            ps.setString(12,d11.getText());
            ps.setString(13,"Mensualidades");
            int res=ps.executeUpdate();
            if(res>0)
            {
                JOptionPane.showMessageDialog(rootPane,"Pago Registrado");
                
                limpiarCajas();
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
            System.out.println(e);
        }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Rellene los campos requeridos");
        }
    }//GEN-LAST:event_bRegistrarActionPerformed

    private void d2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_d2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_d2ActionPerformed

    private void datNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datNombreActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(d2Cbx2.getSelectedIndex()==0)
        {
            JOptionPane.showMessageDialog(null,"Seleccione la cantidad de meses a pagar");
        }
        else
        {
        int val4=Integer.parseInt(d4.getText());
        int val5=Integer.parseInt(d5.getText());
        int val6=d2Cbx2.getSelectedIndex();
        int res= (val4-val5)*val6;        
        d6.setText(Integer.toString(res));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Pagos p=new Pagos();
        p.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActualizarActionPerformed
         // TODO add your handling code here:
         cont=0;
         validar();
         if(cont==0)
         {
        Connection con=null;
         try{
            con=getConection();
            int v1=d2.getSelectedIndex();
            
String ps3="";
            switch(v1){
                case 1: ps3="Enero"; break;
                case 2: ps3="Febrero"; break;
                case 3: ps3="Marzo"; break;
                case 4: ps3="Abril"; break;
                case 5: ps3="Mayo"; break;
                case 6: ps3="Junio"; break;
                case 7: ps3="Julio"; break;
                case 8: ps3="Agosto"; break;
                case 9: ps3="Septiembre"; break;
                case 10: ps3="Octubre"; break;
                case 11: ps3="Noviembre"; break;
                case 12: ps3="Diciembre"; break;
            }
            int v2=d3.getSelectedIndex();
	    String ps4="";
            switch(v2){
                case 1: ps4="Niños"; break;
                case 2: ps4="Bebes"; break;
                case 3: ps4="Adultos"; break;                
            }
            String ps9;
                int mes=d2.getSelectedIndex();
                int mesespagados=d2Cbx2.getSelectedIndex();
                int año=Integer.parseInt(d9.getText());
                //int dia=13;
  
                for(int i=0;i<mesespagados;i++)
                {
                    mes=mes+1;
                    if(mes==13)
                    {
                        año=año+1;
                        mes=1;
                    }
                }
                ps9=Integer.toString(año)+"-"+Integer.toString(mes)+"-"+"1";
                
            ps= con.prepareStatement("UPDATE Pagos SET idPagos=?, idAlumno=?, mes=?, categoria=?, costo=?, desccuento=?, total=?, fecha=?, vencimiento=?,tipoPago=?,folio=? WHERE idPagos=?");
            ps.setString(1,gid);
            ps.setString(2,d1.getText());
            ps.setString(3,ps3);
            ps.setString(4,ps4);
            ps.setString(5,d4.getText());
            ps.setString(6,d5.getText());
            ps.setString(7,d6.getText());
            ps.setString(8,d9.getText()+"-"+d8.getText()+"-"+d7.getText());
            ps.setString(9,ps9);
            ps.setString(10,d10.getSelectedItem().toString());
            ps.setString(11,d11.getText());
            ps.setString(12,gid);
            int res=ps.executeUpdate();
            if(res>0)
            {
                JOptionPane.showMessageDialog(rootPane,"Pago Actualizado");
                limpiarCajas();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Error al Actualizar");
                limpiarCajas();
            }
            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
         }
          else
        {
            JOptionPane.showMessageDialog(rootPane,"Rellene los campos requeridos");
        }
    }//GEN-LAST:event_bActualizarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
       
        Date fecha = new Date();
        int dia=fecha.getDate();
        int mes=fecha.getMonth();
        int año=fecha.getYear();
        d7.setText(Integer.toString(dia));
        d8.setText(Integer.toString(mes+1));
        d9.setText(Integer.toString(año+1900));
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void d7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_d7KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_d7KeyTyped

    private void d8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_d8KeyTyped
        // TODO add your handling code here:
         char c=evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_d8KeyTyped

    private void d9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_d9KeyTyped
        // TODO add your handling code here:
         char c=evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_d9KeyTyped

    private void datApellidoPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datApellidoPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datApellidoPActionPerformed

    private void datNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datNombreKeyTyped
         // TODO add your handling code here:
//         char c=evt.getKeyChar();
  //      if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_datNombreKeyTyped

    private void datApellidoPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datApellidoPKeyTyped
         // TODO add your handling code here
//         char c=evt.getKeyChar();
  //      if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_datApellidoPKeyTyped

    public String getUsuario()
{
    String v="";
    Connection con=null;
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT idUsuario FROM Usuarios WHERE permisos=? ");
            ps.setString(1,"1");
            rs=ps.executeQuery();
            if(rs.next())
            {
                v=(rs.getString("idUsuario"));                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el dato");
                limpiarCajas();
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error  :D");
        }
        return v;
}
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Alumnos A=new Alumnos();
        String us=getUsuario();
        if(us.equals("1"))
        {
            jCbxDia.setVisible(true);
            jBDia.setVisible(true);            
            System.out.println(" ya se puede modificar");
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane, "No tiene permiso de modificar");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jBDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDiaActionPerformed
         // TODO add your handling code here:
         if(!"Seleccione".equals(jCbxDia.getSelectedItem()))
         {
         d7.setText(jCbxDia.getSelectedItem().toString());
         }
         else
         {
             JOptionPane.showMessageDialog(rootPane,"Seleccione un dia");
         }
         
    }//GEN-LAST:event_jBDiaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // TODO add your handling code here:
        Connection con=null;
        if(d1.getText().equals(""))
        {
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT idAlumnos FROM Alumnos WHERE nombre=? AND apellidop=?");//// corregir alumnos en el bd
            ps.setString(1,datNombre.getText());
            ps.setString(2,datApellidoP.getText());
            rs=ps.executeQuery();
            if(rs.next())
            {
                d1.setText(rs.getString("idAlumnos"));                
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el Alumno");
                datApellidoP.setText(null);
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
            ps.setString(1,d1.getText());
            rs=ps.executeQuery();
            if(rs.next())
            {
                datNombre.setText(rs.getString("nombre"));
                datApellidoP.setText(rs.getString("apellidoP"));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el Alumno");
                datApellidoP.setText(null);
                datNombre.setText(null);
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error 5.2 :D");
        }    
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RPagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RPagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bActualizar;
    private javax.swing.JButton bRegistrar;
    private javax.swing.JTextField d1;
    private javax.swing.JComboBox<String> d10;
    private javax.swing.JTextField d11;
    private javax.swing.JComboBox<String> d2;
    private javax.swing.JComboBox<String> d2Cbx2;
    private javax.swing.JComboBox<String> d3;
    private javax.swing.JTextField d4;
    private javax.swing.JTextField d5;
    private javax.swing.JTextField d6;
    private javax.swing.JTextField d7;
    private javax.swing.JTextField d8;
    private javax.swing.JTextField d9;
    private javax.swing.JTextField datApellidoP;
    private javax.swing.JTextField datNombre;
    private javax.swing.JButton jBDia;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jCbxDia;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables
}
