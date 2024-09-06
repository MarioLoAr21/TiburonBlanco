package tiburonesblancos;

//import static com.sun.management.jmx.Trace.isSelected;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class ColorearFilas extends DefaultTableCellRenderer {

    private final int columna_patron;
    private Component componente;

    public ColorearFilas(int Colpatron) {
        this.columna_patron = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col) {
        componente= super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);
        Font font = new Font("Courier", Font.BOLD, 16);
        
        String c1,c2,cat;
        c1=table.getValueAt(row,3).toString();
        c2=table.getValueAt(row,4).toString();
        cat=table.getValueAt(row,1).toString();
        int Ic1=Integer.parseInt(c1);
        int Ic2=Integer.parseInt(c2);  
        int sum=Ic1+Ic2;
        
        switch(table.getValueAt(row,1).toString())
        {
            case "Adultos":
                if((c1.equals("5") || c2.equals("5")) )
                {
                componente.setBackground(Color.YELLOW);     
                if(sum==10)
                {
                componente.setBackground(Color.RED);
                }
                }
                else
                {
                componente.setBackground(Color.GREEN);
                }                
                break;
            case "Niños":
                if((c1.equals("8") || c2.equals("8")) )
                {
                componente.setBackground(Color.YELLOW);     
                if(sum==16)
                {
                componente.setBackground(Color.RED);
                }
                }
                else
                {
                componente.setBackground(Color.GREEN);
                }               
                break;
            case "Bebes":
                if((c1.equals("3") || c2.equals("3")) )
                {
                componente.setBackground(Color.YELLOW);     
                if(sum==6)
                {
                componente.setBackground(Color.RED);
                }
                }
                else
                {
                componente.setBackground(Color.GREEN);
                }               
                break;
            default:
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
