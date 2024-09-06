package tiburonesblancos;

//import static com.sun.management.jmx.Trace.isSelected;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.*;
import static tiburonesblancos.Registro.getConection;

public class TableCellRenderColor extends DefaultTableCellRenderer {


    private final int columna_patron;
    private Component componente;

    public TableCellRenderColor(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jTRegistro, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        componente= super.getTableCellRendererComponent(jTRegistro, value, Selected, hasFocus, row, col);
        
        Font font = new Font("Courier", Font.BOLD, 16);
        
        String idA;
        idA=jTRegistro.getValueAt(row, 5).toString();
        Conexion x = new Conexion();
        System.out.println(x.buscarR(idA));
     
        
        
       
        
        
        return this;
    }


}
