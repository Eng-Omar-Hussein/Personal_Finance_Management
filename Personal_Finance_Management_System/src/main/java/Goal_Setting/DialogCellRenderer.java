/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Goal_Setting;
import java.awt.Component;
import java.awt.FontMetrics;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author Mohamed Ahmed
 */
public class DialogCellRenderer {
    //function to alter the Tooltip text of the jTable
    public static void Tooltip_setter (JTable jTable) {
            //get information of jTable columns
            TableColumnModel columnModel = jTable.getColumnModel();
            TableColumn tableColumn = columnModel.getColumn(0);
            //get Table CellRenderer and alter the Tooltip text style
            jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                String text = " ";
                try{
                    text = (String) value;
                } catch (Exception e) {
                    System.out.println("text is null in jTable1");
                }
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                //Class used to provide information about the metrics of the used font
                FontMetrics fm = label.getFontMetrics(label.getFont());
                //Get the width of the text in every jTable cell
                int textWidth = fm.stringWidth(label.getText());
                //Compare between the width of the column and the length of the text in jTable cell
                //if text is longer than the Column's width Tooltip text will be activated 
                if ((textWidth+5) > tableColumn.getWidth())
                    label.setToolTipText("<html><div style='width:200px;font-size:10px;font-weight:bold;'>" + text.replaceAll("\n", "<br/>") + "</div></html>");
                else
                    label.setToolTipText(null);
                return label;
            }
        });
    }
}