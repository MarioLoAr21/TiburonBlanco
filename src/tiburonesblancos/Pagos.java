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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static tiburonesblancos.Alumnos.getConection;
import static tiburonesblancos.Menu.getConection;
import static tiburonesblancos.pagosActivos.getConection;

/**
 *
 * @author Giuseppe
 */
public class Pagos extends javax.swing.JFrame {

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
    jTextField1.setText(null);
    jTextField2.setText(null);
    jTextField3.setText(null);
        
        
 }
//----------------------------------------------------------------------------------------------------
public void buscarA()
{
    Connection con=null;
        try{
            con=getConection();
            ps=con.prepareStatement("SELECT idAlumnos FROM Alumnos WHERE nombre=? AND apellidoP=?");//// corregir alumnos en el bd
            ps.setString(1,jTextField2.getText());
            ps.setString(2,jTextField3.getText());
            rs=ps.executeQuery();
            if(rs.next())
            {
                jTextField1.setText(rs.getString("idAlumnos"));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"No se encontro el Alumno");
                limpiarCajas();
            }
        }catch(Exception e)
        {
            System.err.println(e+"Error 5 :D");
        }
}
public void pagosActivos() 
{ 
    Connection con=null;
    /*
    
    TableModel model= jTPagos.getModel();
    //jtxtIdAlumno.setText(model.getValueAt(i,0).toString());
    int filas= jTPagos.getRowCount();
        for(int i=0;i<filas;i++)
        {
        try {
            String id=model.getValueAt(i, 0).toString();
            String venci=model.getValueAt(i, 4).toString();
            
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            
            int vd=sdf.parse(venci).getDate();
            int vmes=sdf.parse(venci).getMonth()+1;
            int vaño=sdf.parse(venci).getYear()+1900;
            
            Date fecha = new Date();
            int dia=fecha.getDate();
            int mes=fecha.getMonth()+1;
            int año=fecha.getYear()+1900;
            System.out.println("mes "+i+": "+fecha.getYear() );
            System.out.println("mes "+i+": "+vmes );
            System.out.println("año "+i+": "+vaño );
            if(  (vmes<=mes && vaño==año) || ( año>vaño))
            {
                
                con=getConection();
                try{
                    String in="Inactivo";
                    ps= con.prepareStatement("UPDATE Pagos SET StatusPago=? WHERE idPagos=?");
                    ps.setString(1,in);
                    ps.setString(2,id);
                    int res=ps.executeUpdate();
            if(res>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"idPago:"+id+" vencido");
                //limpiarCajas();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Error al actualizar status del pago vencido");
                //limpiarCajas();
            }
            con.close();
                }
                catch(Exception e){
                    System.out.println(e+" ESte es el error");
                }
            }
            } catch (ParseException ex) {
            Logger.getLogger(Pagos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
    con=getConection();
     Date fecha = new Date();
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
                JOptionPane.showMessageDialog(rootPane,"Error al actualizar status del pago vencido");
                //limpiarCajas();
            }
            con.close();
                }
                catch(Exception e){
                    System.out.println(e+" ESte es el error");
                }
            
            
    
    
    
    
}
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

int cont;
public void validar()
{
     if(jtxtNombre.getText().equals("")){cont++;}
    
}
    public Pagos() {
        initComponents();
        //rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo,"src/imgs/Actividades_Natacion.jpg");
        jTextField2.setDocument(new SoloMayusculas());
        jTextField3.setDocument(new SoloMayusculas());
        this.setLocationRelativeTo(null);
        Connection con=null;
        try
        {
            DefaultTableModel modelo =new DefaultTableModel(){
            
            @Override
            public boolean isCellEditable(int Filas, int columnas){
                return columnas == -1;
            }
        };
            jTPagos.setModel(modelo);
            
            PreparedStatement ps= null;
            ResultSet rs=null;
            //PreparedStatement ps2= null;
           // ResultSet rs2=null;
            con=getConection();
            String sql="SELECT idPagos, idAlumno, mes, total, vencimiento,folio, nombre, apellidoP FROM Pagos, Alumnos WHERE Pagos.idAlumno=Alumnos.idAlumnos AND StatusPago='Activo'" ;
            
            ps =con.prepareStatement(sql);
            rs = ps.executeQuery();
            //ps2 =con.prepareStatement(id);
            //rs2 = ps.executeQuery();
            
            ResultSetMetaData rsMd= rs.getMetaData();
            int cantidadColumnas =rsMd.getColumnCount();
            modelo.addColumn("idPagos");
            modelo.addColumn("idAlumno");
            modelo.addColumn("mes");
            modelo.addColumn("total");
            modelo.addColumn("vencimiento");
            modelo.addColumn("folio");
            modelo.addColumn("nombre");
            modelo.addColumn("ApellidoP");
            while(rs.next()){
                
                Object[] filas = new Object[cantidadColumnas];
                for(int i=0;i<cantidadColumnas;i++)
                {
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
                
            }
            
        }
        catch(SQLException e){
            System.err.println(e.toString());
        }
       
       
        
       Conexion x = new Conexion();
        x.pagosActivos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
                
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        Connection con=null;
        String campo = jTextField1.getText();
        String where="";
        if(!"".equals(campo)){
            where= "WHERE idPagos='"+campo+"'";
            
        }
        try
        {
            DefaultTableModel modelo =new DefaultTableModel();
            jTPagos.setModel(modelo);
            
            PreparedStatement ps= null;
            ResultSet rs=null;
            con=getConection();
            String sql="SELECT idPagos, idAlumno, mes, total, vencimiento,folio FROM Pagos"+where;
            ps =con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd= rs.getMetaData();
            int cantidadColumnas =rsMd.getColumnCount();
            modelo.addColumn("idPagos");
            modelo.addColumn("idAlumno");
            modelo.addColumn("mes");
            modelo.addColumn("total");
            modelo.addColumn("vencimiento");
            modelo.addColumn("folio");
            while(rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for(int i=0;i<cantidadColumnas;i++)
                {
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }
            
        }
        catch(SQLException e){
            System.err.println(e.toString());
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

        bEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTPagos = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jBCargar = new javax.swing.JButton();
        jtxtIdAlumno = new javax.swing.JTextField();
        jtxtNombre = new javax.swing.JTextField();
        jtxtApellidoP = new javax.swing.JTextField();
        jtxtApellidoM = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jBAgregar = new javax.swing.JButton();
        jtxtvencimiento = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jBAgregar2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(6);
        setMinimumSize(new java.awt.Dimension(1390, 780));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bEliminar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/EliminarPago.png"))); // NOI18N
        bEliminar.setBorder(null);
        bEliminar.setBorderPainted(false);
        bEliminar.setContentAreaFilled(false);
        bEliminar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/EliminarPago.png"))); // NOI18N
        bEliminar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/EliminarPago2.png"))); // NOI18N
        bEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(bEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1530, 720, 110, 100));

        jTPagos.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "idPago", "idAlumno", "mes", "total", "vencimiento", "folio", "nombre", "ApellidoP"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTPagos.setGridColor(new java.awt.Color(102, 102, 102));
        jTPagos.setRowHeight(24);
        jTPagos.setSelectionBackground(new java.awt.Color(0, 0, 255));

        jTPagos.setSelectionForeground(new java.awt.Color(240, 240, 240));
        jTPagos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPagosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTPagos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 1370, 510));

        jTextField1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 110, 30));

        jLabel1.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel1.setText("ID:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, 30));

        jBCargar.setForeground(new java.awt.Color(255, 255, 255));
        jBCargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/recargar.png"))); // NOI18N
        jBCargar.setBorder(null);
        jBCargar.setBorderPainted(false);
        jBCargar.setContentAreaFilled(false);
        jBCargar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/recargar.png"))); // NOI18N
        jBCargar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/recargar2.png"))); // NOI18N
        jBCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCargarActionPerformed(evt);
            }
        });
        getContentPane().add(jBCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 80, 80, 70));

        jtxtIdAlumno.setEditable(false);
        jtxtIdAlumno.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jtxtIdAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxtIdAlumnoActionPerformed(evt);
            }
        });
        getContentPane().add(jtxtIdAlumno, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 680, 100, -1));

        jtxtNombre.setEditable(false);
        jtxtNombre.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jtxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 680, 170, -1));

        jtxtApellidoP.setEditable(false);
        jtxtApellidoP.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jtxtApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 680, 170, -1));

        jtxtApellidoM.setEditable(false);
        jtxtApellidoM.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jtxtApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 680, 190, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ModificarPago.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ModificarPago.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ModificarPago2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 720, 100, 100));

        jLabel3.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel3.setText("Agregar Mensualidad");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 820, -1, 30));

        jBAgregar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/MensualidadPago.png"))); // NOI18N
        jBAgregar.setBorder(null);
        jBAgregar.setBorderPainted(false);
        jBAgregar.setContentAreaFilled(false);
        jBAgregar.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/MensualidadPago.png"))); // NOI18N
        jBAgregar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/MensualidadPago2.png"))); // NOI18N
        jBAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(jBAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 720, 120, 100));

        jtxtvencimiento.setEditable(false);
        jtxtvencimiento.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        getContentPane().add(jtxtvencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(1480, 680, 150, -1));

        jButton5.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jButton5.setText("Buscar ID");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 90, -1, -1));

        jTextField2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 210, -1));

        jLabel8.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel8.setText("ID:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 680, 30, 20));

        jLabel6.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel6.setText("Modificar");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 820, -1, 30));

        jLabel7.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel7.setText("Eliminar");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 820, 50, 20));

        jLabel12.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel12.setText("Fecha de vencimiento:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 680, -1, 20));

        jLabel9.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel9.setText("Nombre:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 680, 60, 20));

        jLabel5.setFont(new java.awt.Font("Century", 0, 12)); // NOI18N
        jLabel5.setText("Agregar otro pago");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 820, -1, 30));

        jLabel10.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel10.setText("Apellido Paterno:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 680, -1, 20));

        jLabel11.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel11.setText("Apellido Materno:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 680, 120, 20));

        jTextField3.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 90, 210, -1));

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
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 60));

        jBAgregar2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jBAgregar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/AgregarPago.png"))); // NOI18N
        jBAgregar2.setBorder(null);
        jBAgregar2.setBorderPainted(false);
        jBAgregar2.setContentAreaFilled(false);
        jBAgregar2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/AgregarPago.png"))); // NOI18N
        jBAgregar2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/AgregarPago2.png"))); // NOI18N
        jBAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAgregar2ActionPerformed(evt);
            }
        });
        getContentPane().add(jBAgregar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 720, 100, 100));

        jLabel2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, -1, 30));

        jLabel4.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        jLabel4.setText("Apellido Paterno:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, -1, 30));

        jLFondo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu/Alumnos_.jpg"))); // NOI18N
        jLFondo.setMaximumSize(new java.awt.Dimension(1390, 780));
        jLFondo.setMinimumSize(new java.awt.Dimension(1390, 780));
        jLFondo.setPreferredSize(new java.awt.Dimension(1390, 780));
        jLFondo.setRequestFocusEnabled(false);
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1920, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarActionPerformed
        // TODO add your handling code here:
        cont=0;
        validar();
        if(cont==0)
        {
        if(getUsuario().equals("1"))
        {
        Connection con=null;
        try{
            con=getConection();
            ps= con.prepareStatement("DELETE FROM Pagos WHERE idPagos=?");

            ps.setString(1,jtxtIdAlumno.getText());

            int res=ps.executeUpdate();
            if(res>0)
            {
                JOptionPane.showMessageDialog(rootPane,"Pago Eleminado");
                limpiarCajas();
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Error al Eleminar");
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
            JOptionPane.showMessageDialog(rootPane,"No Tiene Permisos Para Eleminar");
        }
        }
        else
            {
                JOptionPane.showMessageDialog(rootPane,"Seleccione un Pago");
              
            }
    }//GEN-LAST:event_bEliminarActionPerformed

    private void jTPagosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPagosMouseClicked
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
        int i= jTPagos.getSelectedRow();
        TableModel model= jTPagos.getModel();
        jtxtIdAlumno.setText(model.getValueAt(i,0).toString());
        jtxtNombre.setText(model.getValueAt(i,1).toString());
        jtxtApellidoP.setText(model.getValueAt(i,2).toString());
        jtxtApellidoM.setText(model.getValueAt(i,3).toString());
        jtxtvencimiento.setText(model.getValueAt(i,4).toString());
    }//GEN-LAST:event_jTPagosMouseClicked

    private void jBCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCargarActionPerformed
         // TODO add your handling code here:------------------------------------------------botn cargar #DBDBDB
         Connection con=null;
        String campo = jTextField1.getText();
        String where="";
        if(!"".equals(jTextField2.getText())){
            where= " AND nombre like'"+jTextField2.getText()+"%'";
        }
        if(!"".equals(jTextField3.getText())){
            where= " AND apellidoP like'"+jTextField3.getText()+"%'";
        }
        if(!"".equals(jTextField3.getText()) && !"".equals(jTextField2.getText())){
            where= " AND nombre like'"+jTextField2.getText()+"%'"+" AND apellidoP like'"+jTextField3.getText()+"%'";
        }
        if(!"".equals(campo)){
            where= " AND idAlumno='"+campo+"'";
        }
        try
        {
            DefaultTableModel modelo =new DefaultTableModel(){
            
            @Override
            public boolean isCellEditable(int Filas, int columnas){
                return columnas == -1;
            }
        };
            jTPagos.setModel(modelo);

            PreparedStatement ps= null;
            ResultSet rs=null;
            con=getConection();
            String sql="SELECT idPagos, idAlumno, mes, total, vencimiento,folio, nombre, apellidoP FROM Pagos, Alumnos WHERE Pagos.idAlumno=Alumnos.idAlumnos"+where;
            ps =con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd= rs.getMetaData();
            int cantidadColumnas =rsMd.getColumnCount();
            modelo.addColumn("idPagos");
            modelo.addColumn("idAlumno");
            modelo.addColumn("mes");
            modelo.addColumn("total");
            modelo.addColumn("vencimiento");
            modelo.addColumn("folio");
            modelo.addColumn("nombre");
            modelo.addColumn("apellidoP");
            while(rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for(int i=0;i<cantidadColumnas;i++)
                {
                    filas[i]=rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }

        }
        catch(SQLException e){
            System.err.println(e.toString());
        }
        limpiarCajas();
        
    }//GEN-LAST:event_jBCargarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(jtxtIdAlumno.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Seleccione un Pago");            
        }
        else
        {
        RPagos rp=new RPagos(jtxtIdAlumno.getText());
        this.setVisible(false);
        rp.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jBAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregarActionPerformed
         // TODO add your handling code here:
        RPagos ra=new RPagos();
        this.setVisible(false);
        ra.setVisible(true);
        
        
    }//GEN-LAST:event_jBAgregarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        buscarA();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu m=new Menu();
        m.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jtxtIdAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxtIdAlumnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxtIdAlumnoActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jBAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAgregar2ActionPerformed
         // TODO add your handling code here:
          Rotros_pagos ra=new Rotros_pagos();
        this.setVisible(false);
        ra.setVisible(true);
    }//GEN-LAST:event_jBAgregar2ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

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
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pagos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEliminar;
    private javax.swing.JButton jBAgregar;
    private javax.swing.JButton jBAgregar2;
    private javax.swing.JButton jBCargar;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTPagos;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jtxtApellidoM;
    private javax.swing.JTextField jtxtApellidoP;
    private javax.swing.JTextField jtxtIdAlumno;
    private javax.swing.JTextField jtxtNombre;
    private javax.swing.JTextField jtxtvencimiento;
    // End of variables declaration//GEN-END:variables
}
