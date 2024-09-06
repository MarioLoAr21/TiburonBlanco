package tiburonesblancos;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import static org.apache.poi.hssf.usermodel.HeaderFooter.file;
import static tiburonesblancos.Registro.getConection;
//import sun.misc.BASE64Decoder;
//import sun.misc.BASE64Encoder;


public class RAlumnos extends javax.swing.JFrame {
    
//----------------------------------------------------------------------------------------------------    
static String login="root";
static String password="16070065";
static String url="jdbc:mysql://localhost/tiburonesblancos";
PreparedStatement ps;
ResultSet rs;

private JPanel contentPane;
File fichero=null;
String image_string;
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
    jTextField1.setText(null);
    jTextField10.setText(null);
    jTextField11.setText(null);
    jTextField12.setText(null);
    jTextField13.setText(null);
    jTextField2.setText(null);
    jTextField3.setText(null);
    jTextField4.setText(null);
    jTextField5.setText(null);
    jTextField6.setText(null);
    d7.setText(null);
    d8.setText(null);
    d9.setText(null);
    jTextField8.setText(null);
    jTextField9.setText(null);
    lbfoto.setIcon(null);
    txt_ruta.setText(null);
    image_string=null;
    bActualizar.setVisible(false);
    bRegistrar.setVisible(true);
    

       
    
        
        
 }
//----------------------------------------------------------------------------------------------------
public String getAlumno(String N,String A,String M)
{
    String v="0";
    Connection con=null;
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT idAlumnos FROM alumnos WHERE nombre=? AND apellidoP=? AND apellidoM=?");
            ps.setString(1,N);
            ps.setString(2,A);
            ps.setString(3,M);
            rs=ps.executeQuery();
            if(rs.next())
            {
                v=(rs.getString("idAlumnos"));                
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
    if(jTextField1.getText().equals("")){cont++;}
    if(jTextField2.getText().equals("")){cont++;}
    if(jTextField3.getText().equals("")){cont++;}
    if(jTextField4.getText().equals("")){cont++;}
    if(d7.getText().equals("")){cont++;}
    if(d8.getText().equals("")){cont++;}
    if(d9.getText().equals("")){cont++;}
    if(jTextField9.getText().equals("")){cont++;}
    if(jTextField10.getText().equals("")){cont++;}
    if(jTextField11.getText().equals("")){cont++;}
    if(jTextField12.getText().equals("")){cont++;}
    
}
  public String getUsuario(String c)
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

    public RAlumnos() {
        initComponents();
        this.setLocationRelativeTo(null);
//        rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo,"src/imgs/Actividades_Natacion.jpg");
        bActualizar.setVisible(false);
    }
    String id="0";
    String gid="0";
     
    public RAlumnos(String id) {
         initComponents();
         this.setLocationRelativeTo(null);
         jTextField1.setDocument(new SoloMayusculas());
         jTextField2.setDocument(new SoloMayusculas());
         jTextField3.setDocument(new SoloMayusculas());
  //       rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo,"src/imgs/Actividades_Natacion.jpg");
         bRegistrar.setVisible(false);
         gid=id;
        if(id != "0")
        {
            Connection con=null;
        try{
            con=getConection();
            ps= con.prepareStatement("Select * FROM Alumnos WHERE idAlumnos=?");
            ps.setString(1,id);
            
            rs=ps.executeQuery();
            if(rs.next())
            {                
                jTextField1.setText(rs.getString("nombre"));
                jTextField2.setText(rs.getString("apellidoP"));
                jTextField3.setText(rs.getString("apellidoM"));
                jTextField4.setText(rs.getString("telefono"));
                jTextField5.setText(rs.getString("telefonoCasa"));
                jTextField6.setText(rs.getString("email"));
                //jTextField7.setText(rs.getString("fechaN"));
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                
                String date=rs.getString("fechaN");
                sdf.parse(date);
                
                System.out.println(sdf.parse(date));
                int año=sdf.parse(date).getYear()+1900;
                int mes=sdf.parse(date).getMonth()+1;
                int dia=sdf.parse(date).getDate();                
                d9.setText(Integer.toString(año));
                d8.setText(Integer.toString(mes));
                d7.setText(Integer.toString(dia));
                jTextField8.setText(rs.getString("tipoSangre"));
                jTextField9.setText(rs.getString("codigoPostal"));
                jTextField10.setText(rs.getString("colonia"));
                jTextField11.setText(rs.getString("calle"));
                jTextField12.setText(rs.getString("numExterior"));
                jTextField13.setText(rs.getString("numInterior"));
                 //ob blob = rs.getBlob("foto");
                 //te[] data = blob.getBytes(1, (int)blob.length());
                  
                 BufferedImage img = null;
                 String imagen_string=rs.getString("foto");
                 if(imagen_string.equals(""))
                 {}
                 else
                 {
                // img = decodeToImage(imagen_string);
                ImageIcon icon = new ImageIcon(img);
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lbfoto.getWidth(), lbfoto.getHeight(), Image.SCALE_DEFAULT));
                lbfoto.setText(null);
                lbfoto.setIcon(icono);
                 }
                 //img = ImageIO.read(new ByteArrayInputStream(data));                 
                 //ageIcon icono = new ImageIcon(img);
                // Image conversion=icono.getImage();
                 //Image tamaño=conversion.getScaledInstance(253, 190, Image.SCALE_SMOOTH);
                // ImageIcon imgF = new ImageIcon(tamaño);
                // lbfoto.setIcon(imgF);
                 //img2=(BufferedImage) img.getScaledInstance(240, 190, WIDTH);
                //rsscalelabel.RSScaleLabel.setScaleLabel(lblimagen, );
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No exisste");
                limpiarCajas();

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        lbfoto = new javax.swing.JLabel();
        jButtonAbrir = new javax.swing.JButton();
        txt_ruta = new javax.swing.JTextField();
        jButtonLimpiar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        bRegistrar = new javax.swing.JButton();
        d7 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        d8 = new javax.swing.JTextField();
        d9 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        bActualizar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButtonAbrir.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jButtonAbrir.setText("Abrir");
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirActionPerformed(evt);
            }
        });

        txt_ruta.setEditable(false);

        jButtonLimpiar.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel15.setText("Elegir Foto:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lbfoto, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txt_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbfoto, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jButtonAbrir)
                    .addComponent(jButtonLimpiar))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 140, 330, 410));

        jTextField1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, 360, -1));

        jTextField2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 180, 360, -1));

        jTextField3.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 220, 360, -1));

        jTextField4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, 220, -1));

        jTextField5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 340, 220, -1));

        jTextField6.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 260, 360, 30));

        jTextField8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 420, 210, -1));

        jTextField9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField9KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, 210, -1));

        jTextField10.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 500, 210, 30));

        jTextField11.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 540, 210, 30));

        jTextField12.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 580, 90, 30));

        jTextField13.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 620, 90, 30));

        jLabel1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel1.setText("Nombre*");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 90, 30));

        jLabel2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel2.setText("Apellido Paterno*");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel3.setText("Apellido Materno*");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 220, -1, 20));

        jLabel4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel4.setText("Telefono (cell)*");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel5.setText("Telefono (casa)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 340, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel6.setText("E-mail");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, -1, 20));

        jLabel7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel7.setText("Fecha Nacimiento*");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, -1, 20));

        jLabel8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel8.setText("Tipo de Sangre");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, -1, 20));

        jLabel9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel9.setText("Codigo postal*");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 460, -1, 20));

        jLabel10.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel10.setText("Colonia*");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, -1, 20));

        jLabel11.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel11.setText("Calle*");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 540, -1, 20));

        jLabel12.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel12.setText("Numero Exterior*");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 580, -1, 20));

        jLabel13.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel13.setText("Numero Interior");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 630, -1, 20));

        jLabel14.setFont(new java.awt.Font("Century", 1, 24)); // NOI18N
        jLabel14.setText("Altas Alumnos");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 90, -1, -1));

        jLabel18.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel18.setText("Dia:");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 390, -1, 20));

        bRegistrar.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        bRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Guardar.png"))); // NOI18N
        bRegistrar.setText("Registrar");
        bRegistrar.setBorder(null);
        bRegistrar.setBorderPainted(false);
        bRegistrar.setContentAreaFilled(false);
        bRegistrar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Guardar.png"))); // NOI18N
        bRegistrar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Guardar2.png"))); // NOI18N
        bRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bRegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(bRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 680, 180, 100));

        d7.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                d7KeyTyped(evt);
            }
        });
        getContentPane().add(d7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 380, 40, -1));

        jLabel19.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel19.setText("Mes:");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 390, -1, 20));

        d8.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                d8KeyTyped(evt);
            }
        });
        getContentPane().add(d8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 380, 50, -1));

        d9.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        d9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                d9KeyTyped(evt);
            }
        });
        getContentPane().add(d9, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 380, 90, 30));

        jLabel20.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel20.setText("Año:");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 390, -1, 20));

        bActualizar.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        bActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Modificacion.png"))); // NOI18N
        bActualizar.setText("Modificar");
        bActualizar.setBorder(null);
        bActualizar.setBorderPainted(false);
        bActualizar.setContentAreaFilled(false);
        bActualizar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Modificacion.png"))); // NOI18N
        bActualizar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Modificacion2.png"))); // NOI18N
        bActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(bActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 680, 160, 100));

        jButton1.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton1.setText("Menu");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu/Alumnos_.jpg"))); // NOI18N
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void bRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bRegistrarActionPerformed
        // TODO astemdd your handling code here:
        Connection con=null;
        cont=0;
        validar();
        if(cont==0)
        {
         String a=getAlumno(jTextField1.getText(), jTextField2.getText(), jTextField3.getText());
         System.out.println("el alumno del ida "+a);
         if("0".equals(a))
         {
        try{
            con=getConection();
            if (!txt_ruta.getText().equals("")) {
           
                // TODO add your handling code here:
                BufferedImage img=null;
                image_string=null;
                img = ImageIO.read(new File(fichero.toString()));
                
               // image_string = encodeToString(img);
                guardar_imagen(image_string, fichero.getName());          

             }
                       
            ps= con.prepareStatement("INSERT INTO Alumnos(idAlumnos, nombre, apellidoP, apellidoM, telefono, telefonoCasa, email, fechaN, tipoSangre, codigoPostal, colonia, calle, numExterior, numInterior, foto) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,null);
            ps.setString(2,jTextField1.getText());
            ps.setString(3,jTextField2.getText());
            ps.setString(4,jTextField3.getText());
            ps.setString(5,jTextField4.getText());
            ps.setString(6,jTextField5.getText());
            ps.setString(7,jTextField6.getText());
            ps.setString(8,d9.getText()+"-"+d8.getText()+"-"+d7.getText());
            ps.setString(9,jTextField8.getText());
            ps.setString(10,jTextField9.getText());
            ps.setString(11,jTextField10.getText());
            ps.setString(12,jTextField11.getText());
            ps.setString(13,jTextField12.getText());
            ps.setString(14,jTextField13.getText());
            ps.setString(15,image_string);
            int res=ps.executeUpdate();
            if(res>0)
            {
                JOptionPane.showMessageDialog(rootPane,"AlumnoResgistrado");
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
            System.out.println(e +"  este error");
        }
        }
         else
         {
             JOptionPane.showMessageDialog(rootPane,"El alumno "+jTextField1.getText()+" "+jTextField2.getText()+" "+jTextField3.getText()+" ya esta registrado");
         }
        }
        else
        {
            JOptionPane.showMessageDialog(rootPane,"Rellene los campos requeridos");
        }
    }//GEN-LAST:event_bRegistrarActionPerformed

    private void bActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bActualizarActionPerformed
        // TODO add your handling code here:
        Connection con=null;
        String url=txt_ruta.getText();
        if("".equals(url))
        {
        try{
            con=getConection();
            
            ps= con.prepareStatement("UPDATE Alumnos SET idAlumnos=?, nombre=?, apellidoP=?, apellidoM=?, telefono=?, telefonoCasa=?, email=?, fechaN=?, tipoSangre=?, codigoPostal=?, colonia=?, calle=?, numExterior=?, numInterior=? WHERE idAlumnos=?");
            ps.setString(1,gid);
            ps.setString(2,jTextField1.getText());
            ps.setString(3,jTextField2.getText());
            ps.setString(4,jTextField3.getText());
            ps.setString(5,jTextField4.getText());
            ps.setString(6,jTextField5.getText());
            ps.setString(7,jTextField6.getText());
            ps.setString(8,d9.getText()+"-"+d8.getText()+"-"+d7.getText());            
            ps.setString(9,jTextField8.getText());
            ps.setString(10,jTextField9.getText());
            ps.setString(11,jTextField10.getText());
            ps.setString(12,jTextField11.getText());
            ps.setString(13,jTextField12.getText());
            ps.setString(14,jTextField13.getText());
            ps.setString(15,gid);
             int res=ps.executeUpdate();
             
             
            if(res>0)
            {
                JOptionPane.showMessageDialog(rootPane,"Alumno Actualizado");
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
            System.out.println(e+"  error act");
        }
        }
        else
        {
            try{
            con=getConection();
            if (!txt_ruta.getText().equals("")) {
           
                // TODO add your handling code here:
                BufferedImage img = ImageIO.read(new File(fichero.toString()));
                
             //   image_string = encodeToString(img);
                guardar_imagen(image_string, fichero.getName());          

             }
            
            ps= con.prepareStatement("UPDATE Alumnos SET idAlumnos=?, nombre=?, apellidoP=?, apellidoM=?, telefono=?, telefonoCasa=?, email=?, fechaN=?, tipoSangre=?, codigoPostal=?, colonia=?, calle=?, numExterior=?, numInterior=?, foto=? WHERE idAlumnos=?");
            ps.setString(1,gid);
            ps.setString(2,jTextField1.getText());
            ps.setString(3,jTextField2.getText());
            ps.setString(4,jTextField3.getText());
            ps.setString(5,jTextField4.getText());
            ps.setString(6,jTextField5.getText());
            ps.setString(7,jTextField6.getText());
            ps.setString(8,d9.getText()+"-"+d8.getText()+"-"+d7.getText());            
            ps.setString(9,jTextField8.getText());
            ps.setString(10,jTextField9.getText());
            ps.setString(11,jTextField10.getText());
            ps.setString(12,jTextField11.getText());
            ps.setString(13,jTextField12.getText());
            ps.setString(14,jTextField13.getText());
            ps.setString(15, image_string);
            ps.setString(16,gid);
            
             int res=ps.executeUpdate();
             
             
            if(res>0)
            {
                JOptionPane.showMessageDialog(rootPane,"Alumno Actualizado");
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
            System.out.println(e+"herror act");
        }
        }
    }//GEN-LAST:event_bActualizarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:this.
        Alumnos a=new Alumnos();
        this.setVisible(false);
        a.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    public void guardar_imagen(String g, String h)
    {
        
    }
    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        // TODO add your handling code here:
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg", "jpg");
        file.setFileFilter(filtro);

        int seleccion = file.showOpenDialog(contentPane);
        //Si el usuario, pincha en aceptar
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            //Seleccionamos el fichero
            fichero = file.getSelectedFile();
            //Ecribe la ruta del fichero seleccionado en el campo de texto
            txt_ruta.setText(fichero.getAbsolutePath());
            ImageIcon icon = new ImageIcon(fichero.toString());
            System.out.println(fichero.getName());
            Icon icono = new ImageIcon(icon.getImage().getScaledInstance(318, 265, Image.SCALE_DEFAULT));
            lbfoto.setText(null);
            lbfoto.setIcon(icono);

        }
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
          // TODO add your handling code here:
        lbfoto.setIcon(null);
        txt_ruta.setText(null);
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
         // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(c<'0'|| c>'9')evt.consume();
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        //char c=evt.getKeyChar();
        //if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
         // TODO add your handling code here:
        // char c=evt.getKeyChar();
        //if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
//        char c=evt.getKeyChar();
  //      if((c<'a'|| c>'z') && (c<'A'|| c>'Z'))evt.consume();
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        // TODO add your handling code here:
        char c=evt.getKeyChar();
        if(c<'0'|| c>'9')evt.consume();
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jTextField9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyTyped
         // TODO add your handling code here:
         char c=evt.getKeyChar();
        if(c<'0'|| c>'9')evt.consume();
    }//GEN-LAST:event_jTextField9KeyTyped

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

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
            java.util.logging.Logger.getLogger(RAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RAlumnos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RAlumnos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bActualizar;
    private javax.swing.JButton bRegistrar;
    private javax.swing.JTextField d7;
    private javax.swing.JTextField d8;
    private javax.swing.JTextField d9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lbfoto;
    private javax.swing.JTextField txt_ruta;
    // End of variables declaration//GEN-END:variables



    
} 
