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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static tiburonesblancos.TRegistro.getConection;

/**
 *
 * @author Giuseppe
 */
public class Grupos extends javax.swing.JFrame{

    /** Creates new form Grupos */
    public Grupos() {
        initComponents();
        this.setLocationRelativeTo(null);
        //rsscalelabel.RSScaleLabel.setScaleLabel(jLFondo,"src/imgs/Actividades_Natacion.jpg");
        mostrar();
        
            Conexion x = new Conexion();        
            x.pagosActivos();
            x.noPagados();
            x.Pagados();
            
            
        
        
        
        
    }

       
    public void countCarriles(){
       Conexion x = new Conexion();
       
        int registros = Table.getRowCount();
        for (int i = 0; i < registros; i++)
        //for (int i = 1; i < 2; i++) 
        {
           
            //String id= buscarA(jTRegistro.getValueAt(i, 6).toString(), jTRegistro.getValueAt(i, 7).toString());
            String p= Table.getValueAt(i, 0).toString();
            String c= Table.getValueAt(i, 1).toString();
            String h= Table.getValueAt(i, 2).toString();
            
            x.countcarril(p,c,h);
        }
    }
    private void mostrar(){
	DefaultTableModel modelo;
        modelo = new DefaultTableModel()
        {
            
            @Override
            public boolean isCellEditable(int Filas, int columnas){
                return columnas == -1;
            }
        };
	ResultSet rs=conection.getTabla("select paquete, categoria, horario,carril1,carril2,cupo from carril");
	modelo.setColumnIdentifiers(new Object[]{"Paquete","Categoria","Horario","Carril 1","Carril 2","Cupo"});
	try{
		while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("paquete"),rs.getString("categoria"),rs.getString("horario"),rs.getString("carril1"),rs.getString("carril2"),rs.getString("cupo")});
		}

		Table.setModel(modelo);
		}catch(Exception e){
		System.out.println(e);
		}
        Table.getColumnModel().getColumn(0).setPreferredWidth(250);
        Table.getColumnModel().getColumn(0).setResizable(false);
        Table.getColumnModel().getColumn(1).setPreferredWidth(100);
        Table.getColumnModel().getColumn(1).setResizable(false);
        Table.getColumnModel().getColumn(2).setPreferredWidth(150);
        Table.getColumnModel().getColumn(2).setResizable(false);
        Table.getColumnModel().getColumn(3).setPreferredWidth(100);
        Table.getColumnModel().getColumn(3).setResizable(false);
        Table.getColumnModel().getColumn(4).setPreferredWidth(100);
        Table.getColumnModel().getColumn(4).setResizable(false);
        Table.getColumnModel().getColumn(5).setPreferredWidth(50);
        Table.getColumnModel().getColumn(5).setResizable(false);
       
      
         Table.setRowHeight(40);
	}
    private void mostrar2(String p, String h, String c){

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
            
            String sql = "SELECT idAlumno, nombre, ApellidoP,ApellidoM, carril, Status_Pago FROM Registro , Alumnos  WHERE Registro.idAlumno=Alumnos.idAlumnos AND idPaquete='"+p+"' AND idHorario='"+c+"' AND Categoria='"+h+"'";
            //String sql = "SELECT idRegistro, idPaquete, idHorario, Categoria, carril, idAlumno, Status_Pago FROM Registro";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //ps2 =con.prepareStatement(id);
            //rs2 = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            modelo2.addColumn("ID de Alumno");
            modelo2.addColumn("Nombre");
            modelo2.addColumn("Apellido Paterno");
            modelo2.addColumn("Apellido Materno");
            modelo2.addColumn("Carril");
            modelo2.addColumn("Status de Pago");
          
            
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
        Table2.getColumnModel().getColumn(0).setPreferredWidth(200);
        Table2.getColumnModel().getColumn(0).setResizable(false);
        Table2.getColumnModel().getColumn(1).setPreferredWidth(200);
        Table2.getColumnModel().getColumn(1).setResizable(false);
        Table2.getColumnModel().getColumn(2).setPreferredWidth(200);
        Table2.getColumnModel().getColumn(2).setResizable(false);
        Table2.getColumnModel().getColumn(3).setPreferredWidth(200);
        Table2.getColumnModel().getColumn(3).setResizable(false);
        Table2.getColumnModel().getColumn(4).setPreferredWidth(100);
        Table2.getColumnModel().getColumn(4).setResizable(false);
       
       
      
         Table2.setRowHeight(40);
	}
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table2 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        datPaqueteCbx = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        datCategoriaCbx = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jLFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1920, 1080));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel2.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Agregar");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 830, 60, 20));

        jLabel1.setFont(new java.awt.Font("Century", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Todos los Registros");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 830, -1, 30));

        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agrega.png"))); // NOI18N
        jButton4.setToolTipText("");
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
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 740, 100, 100));

        Table.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Paquete", "Categoria", "Hoario", "Carril 1", "Carril 2", "Cupo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(Table);
        if (Table.getColumnModel().getColumnCount() > 0) {
            Table.getColumnModel().getColumn(0).setPreferredWidth(300);
        }

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 1370, 250));

        Table2.setFont(new java.awt.Font("Century", 0, 18)); // NOI18N
        Table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID alumno", "Nombre", "Apellido Paterno", "Status de pago"
            }
        ));
        Table2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Table2KeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(Table2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 470, 1370, 270));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Registros.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Registros.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Registros2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 740, 100, 100));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setText("Paquete");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 330, 40));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField2.setText("Categoria");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 190, 40));

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField3.setText("Horario");
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 420, 260, 40));

        datPaqueteCbx.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        datPaqueteCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Lunes, Miercoles, Viernes", "Martes, Jueves", "Sabado" }));
        datPaqueteCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datPaqueteCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datPaqueteCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Paquete");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Categoria");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 82, -1, 30));

        datCategoriaCbx.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        datCategoriaCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Niños", "Bebes", "Adultos" }));
        datCategoriaCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datCategoriaCbxActionPerformed(evt);
            }
        });
        getContentPane().add(datCategoriaCbx, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 240, -1));

        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar.png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 80, 70));

        jLFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/Menu/Alumnos_.jpg"))); // NOI18N
        getContentPane().add(jLFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1910, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Menu m=new Menu();
        m.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Registro r=new Registro();
        this.setVisible(false);
        r.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         // TODO add your handling code here:
         this.setVisible(false);
        TRegistro TR=new TRegistro();
        TR.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void datPaqueteCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datPaqueteCbxActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_datPaqueteCbxActionPerformed

    private void datCategoriaCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datCategoriaCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datCategoriaCbxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    DefaultTableModel modelo= new DefaultTableModel()
    ;
    String where="";
    String cat=(String) datCategoriaCbx.getSelectedItem();
    String paq=(String) datPaqueteCbx.getSelectedItem();
    if(!"-".equals(cat))
    {
       where="WHERE categoria= '"+cat+"'"; 
    }
    if(!"-".equals(paq))
    {
       where="WHERE paquete= '"+paq+"'"; 
    }
    if(!"-".equals(cat) && !"-".equals(paq))
    {
       where="WHERE categoria ='"+cat+"'"+" AND paquete='"+paq+"'"; 
    }
	ResultSet rs=conection.getTabla("select paquete, categoria, horario,carril1,carril2,cupo from carril "+where);
	modelo.setColumnIdentifiers(new Object[]{"Paquete","Categoria","Horario","Carril 1","Carril 2","Cupo"});
	try{
		while (rs.next()){
		modelo.addRow(new Object[]{rs.getString("paquete"),rs.getString("categoria"),rs.getString("horario"),rs.getString("carril1"),rs.getString("carril2"),rs.getString("cupo")});
		}

		Table.setModel(modelo);
		}catch(Exception e){
		System.out.println(e);
		}
        datCategoriaCbx.setSelectedIndex(0);
        datPaqueteCbx.setSelectedIndex(0);     
        
        Table.getColumnModel().getColumn(0).setPreferredWidth(250);
        Table.getColumnModel().getColumn(0).setResizable(false);
        Table.getColumnModel().getColumn(1).setPreferredWidth(100);
        Table.getColumnModel().getColumn(1).setResizable(false);
        Table.getColumnModel().getColumn(2).setPreferredWidth(150);
        Table.getColumnModel().getColumn(2).setResizable(false);
        Table.getColumnModel().getColumn(3).setPreferredWidth(100);
        Table.getColumnModel().getColumn(3).setResizable(false);
        Table.getColumnModel().getColumn(4).setPreferredWidth(100);
        Table.getColumnModel().getColumn(4).setResizable(false);
        Table.getColumnModel().getColumn(5).setPreferredWidth(50);
        Table.getColumnModel().getColumn(5).setResizable(false);
       
      
         Table.setRowHeight(40);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TableMouseClicked
        // TODO add your handling code here:
         int i = Table.getSelectedRow();
        TableModel model = Table.getModel();
        jTextField1.setText(model.getValueAt(i, 0).toString());
        jTextField2.setText(model.getValueAt(i, 1).toString());
        jTextField3.setText(model.getValueAt(i, 2).toString());
        
        mostrar2(model.getValueAt(i, 0).toString(),model.getValueAt(i, 1).toString(),model.getValueAt(i, 2).toString());
        Table2.setDefaultRenderer(Object.class, new ColorearFilas2(3));
        
       
       
    }//GEN-LAST:event_TableMouseClicked

    private void Table2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Table2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Table2KeyPressed

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
            java.util.logging.Logger.getLogger(Grupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Grupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Grupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Grupos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Grupos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Table;
    private javax.swing.JTable Table2;
    private javax.swing.JComboBox<String> datCategoriaCbx;
    private javax.swing.JComboBox<String> datPaqueteCbx;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLFondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables

}
