package tiburonesblancos;

import tiburonesblancos.*;
//import static com.sun.management.jmx.Trace.isSelected;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilas2 extends DefaultTableCellRenderer {

    private final int columna_patron;
    private Component componente;

    public ColorearFilas2(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        componente= super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        Font font = new Font("Courier", Font.BOLD, 16);
        
      
           
        
        switch(table.getValueAt(row,4).toString())
        {
            case "Pagado":
               componente.setBackground(Color.GREEN);               
                break;
            case "NoPagado":
                componente.setBackground(Color.RED);
                 break;
            
            default:
                componente.setBackground(Color.RED);
                break;                        
            
        }
                 
        
        
        
        
     
        
        
        /*
        switch (table.getValueAt(row,3).toString()) {
            
            case "0":              
                componente.setBackground(Color.RED);
                setFont(font);
                break;
            
                  default:
                break;
        }
        */
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        return this;
    }

}
