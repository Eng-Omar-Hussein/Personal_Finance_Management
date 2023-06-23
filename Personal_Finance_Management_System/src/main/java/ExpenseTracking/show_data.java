/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ExpenseTracking;


import User_Authentication.Cookies;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asmaa
 */
public class show_data extends javax.swing.JFrame {

    static ArrayList<expense_varibles> new_expense=new ArrayList<>();
   
    public show_data() {
        ConnectionToDB.Connection();
        initComponents();
        this.setVisible(true);
       
    }
    public void show_table(){
    try {
        
            Connection c = DriverManager.getConnection("jdbc:sqlite:expense.db");
            PreparedStatement s=c.prepareStatement("select * from expense");
            ResultSet ss=s.executeQuery();
            DefaultTableModel table1=Goal_Setting.GoalDBCreator.ResultSetIntoTableModel(ss);
            
            Object[] row=new Object[6];
            table.setModel(table1);
            
       /*     while(ss.next()){
            expense_varibles obj=new expense_varibles(ss.getInt("id"),ss.getString("name"),
            ss.getString("category"),
            ss.getString("amount"),
            ss.getString("date"),
            ss.getString("description"));
            
            new_expense.add(obj);
            
            }
            for(int i=0;i<new_expense.size();i++){
            row[0]=new_expense.get(i).getId();
            row[1]=new_expense.get(i).getName();
            row[2]=new_expense.get(i).getCategory();
            row[3]=new_expense.get(i).getAmount();
            row[4]=new_expense.get(i).getDate();
            row[5]=new_expense.get(i).getDescriptipon();
            table1.addRow(row);
            }
            ss.close();
  
    }*/
    }
    catch(SQLException sq){
        System.out.println(sq.getMessage());
    }
    }
    public void clear(){
        id.setText("");
        new_name.setText("");
        new_category.setText("");
        new_amount.setText("");
        new_description.setText("");
           SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
                try {
            String selectedDate =  "";
                date = dateFormat.parse(selectedDate);
                } catch (ParseException e) {
                    System.out.println("Date conversion error");
                }
        jDateChooser1.setDate(date);
    
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        new_description = new javax.swing.JTextField();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        new_name = new javax.swing.JTextField();
        new_amount = new javax.swing.JTextField();
        new_category = new javax.swing.JTextField();
        update1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        table.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "name", "category", "amount", "date", "description"
            }
        ));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setUpdateSelectionOnSort(false);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(25, 167, 206));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("name of expense : ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(25, 167, 206));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("amount :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(25, 167, 206));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("category ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(25, 167, 206));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("description : ");

        new_description.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        new_description.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 167, 206), 3, true));

        update.setBackground(new java.awt.Color(25, 167, 206));
        update.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setIcon(new javax.swing.ImageIcon("icons/icons8-search-60.png"));
        update.setText("update");
        update.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setBackground(new java.awt.Color(25, 167, 206));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setIcon(new javax.swing.ImageIcon("icons/icons8-delete-60.png"));
        delete.setText("delete");
        delete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(25, 167, 206));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon("icons/icons8-back-48.png"));
        jButton1.setText("back");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(25, 167, 206));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("id");

        id.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        id.setToolTipText("id ");
        id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 167, 206), 3, true));

        new_name.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        new_name.setToolTipText("");
        new_name.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 167, 206), 3, true));

        new_amount.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        new_amount.setToolTipText("");
        new_amount.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 167, 206), 3, true));

        new_category.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        new_category.setToolTipText("");
        new_category.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 167, 206), 3, true));

        update1.setBackground(new java.awt.Color(25, 167, 206));
        update1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        update1.setForeground(new java.awt.Color(255, 255, 255));
        update1.setIcon(new javax.swing.ImageIcon("icons/icons8-show-60.png"));
        update1.setText("show");
        update1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update1ActionPerformed(evt);
            }
        });

        jDateChooser1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(25, 167, 206), 2, true));
        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(25, 167, 206));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(new_name, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(new_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(new_category, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(56, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(new_description, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                        .addGap(210, 210, 210)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(update, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE))
                .addGap(114, 114, 114))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(197, 197, 197))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(update1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(update)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(new_name, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(new_amount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(new_category, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 34, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(new_description, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
                if(evt.getSource()==update){
                  try {
                Connection c=DriverManager.getConnection("jdbc:sqlite:expense.db");

                System.out.println("connected");
                
            }  
                  catch (SQLException ex) {
            System.out.println(ex.getMessage());
        
                  }
          
             if(table.getSelectedRow()==-1){
             JOptionPane.showMessageDialog(null,  "please choose a row ", "error", JOptionPane.ERROR_MESSAGE);
            
             }
            int b=table.getSelectedRow();
                     
               SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
            String date=ss.format(jDateChooser1.getDate());
            if(Valid_Input.checkEmpty(new_category.getText())||Valid_Input.checkEmpty(new_name.getText())||Valid_Input.checkEmpty(new_amount.getText()))
                JOptionPane.showMessageDialog(null,  "please enter the data ", "error", JOptionPane.ERROR_MESSAGE);
            else if(!Valid_Input.valid_amount(new_amount.getText()))
           JOptionPane.showMessageDialog(null,"amount must not have a letter","error",JOptionPane.WARNING_MESSAGE);
            else  if(!Valid_Input.valid_name(new_name.getText()))
           JOptionPane.showMessageDialog(null,"name cannot have a number","error",JOptionPane.WARNING_MESSAGE);
            else {
                    try { ConnectionToDB.update(Cookies.getID(),
                                  new_name.getText(),
                                  new_category.getText(),
                                  new_amount.getText(),date,
                                  new_description.getText(), (int) table.getValueAt(b, 0),
                                  table.getValueAt(b, 1).toString(),
                                   table.getValueAt(b, 2).toString(),
                                    table.getValueAt(b, 3).toString(),
                                     table.getValueAt(b, 4).toString(),
                                      table.getValueAt(b, 5).toString()
                                  
                                  );

                          
                          JOptionPane.showMessageDialog(null, "data updated successflly", "personal finance mangement", JOptionPane.INFORMATION_MESSAGE);
                          show_table();
                          clear();
                    }
                       catch (SQLException ex) {
                          Logger.getLogger(show_data.class.getName()).log(Level.SEVERE, null, ex);
                      }
            
             }
             
            
                }    
                      
                
              
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
       if(evt.getSource()==delete){
        try {
                Connection c=DriverManager.getConnection("jdbc:sqlite:expense.db");

                System.out.println("connected");
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
           if(table.getSelectedRow()==-1){
             JOptionPane.showMessageDialog(null,  "please choose a row ", "error", JOptionPane.ERROR_MESSAGE);
             
             }
             else{
            int b=table.getSelectedRow();
        try {
          ConnectionToDB.delete((int) table.getValueAt(b, 0),
                  table.getValueAt(b, 1).toString(),
                                   table.getValueAt(b, 2).toString(),
                                    table.getValueAt(b, 3).toString(),
                                     table.getValueAt(b, 4).toString(),
                                      table.getValueAt(b, 5).toString());
          JOptionPane.showMessageDialog(null, "data delete successflly", "personal finance mangement", JOptionPane.INFORMATION_MESSAGE);
                          show_table();
                          clear();
                  }
         catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           }
        
       
       }
    }//GEN-LAST:event_deleteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Expensetracking Obj = new Expensetracking();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
    if(evt.getSource()==table){
        int b;
        b=table.getSelectedRow();
        id.setText(table.getValueAt(b, 0).toString());
                new_name.setText( table.getValueAt(b, 1).toString());
        new_category.setText( table.getValueAt(b, 2).toString());
        new_amount.setText( table.getValueAt(b, 3).toString());

        
                 SimpleDateFormat ss=new SimpleDateFormat("yyyy-MM-dd");
                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
                try {
            String selectedDate =  table.getValueAt(b, 4).toString();
                date = dateFormat.parse(selectedDate);
                } catch (ParseException e) {
                    System.out.println("Date conversion error");
                }
                jDateChooser1.setDate(date) ;
                new_description.setText( table.getValueAt(b, 5).toString());



    }
    }//GEN-LAST:event_tableMouseClicked

    private void update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update1ActionPerformed
        show_table();
    
    }//GEN-LAST:event_update1ActionPerformed

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
            java.util.logging.Logger.getLogger(show_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(show_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(show_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(show_data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new show_data().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField new_amount;
    private javax.swing.JTextField new_category;
    private javax.swing.JTextField new_description;
    private javax.swing.JTextField new_name;
    private javax.swing.JTable table;
    private javax.swing.JButton update;
    private javax.swing.JButton update1;
    // End of variables declaration//GEN-END:variables

    
}
