package incometracking;
import com.mycompany.personal_finance_management_system.Menu_Page;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class IncomePage extends javax.swing.JFrame {
    public IncomePage() {
        initComponents();
        this.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        L1 = new javax.swing.JLabel();
        L2 = new javax.swing.JLabel();
        TAmount = new javax.swing.JTextField();
        L4 = new javax.swing.JLabel();
        TDescription = new javax.swing.JTextField();
        CBox = new javax.swing.JComboBox<>();
        BSave = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Income Entry");
        setLocation(new java.awt.Point(550, 250));

        L1.setText("Category");

        L2.setText("Amount");

        TAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TAmountActionPerformed(evt);
            }
        });

        L4.setText("Description");

        TDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TDescriptionActionPerformed(evt);
            }
        });

        CBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "choose category", "salary", "rental income", "investment income", "freelance income" }));
        CBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CBoxItemStateChanged(evt);
            }
        });
        CBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBoxActionPerformed(evt);
            }
        });

        BSave.setText("Save");
        BSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSaveActionPerformed(evt);
            }
        });

        jButton2.setText("Display Data");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jToolBar3.setRollover(true);

        jButton3.setText("Back");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(L1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CBox, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(L2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(L4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(BSave)
                                .addGap(44, 44, 44)
                                .addComponent(jButton2))
                            .addComponent(TDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addGap(16, 16, 16))
            .addComponent(jToolBar3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L1)
                    .addComponent(L2)
                    .addComponent(TAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(L4)
                    .addComponent(TDescription))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BSave)
                    .addComponent(jButton2))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBoxActionPerformed

    private void TDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TDescriptionActionPerformed

    private void TAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAmountActionPerformed

    private void BSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSaveActionPerformed
        // TODO add your handling code here:
        if((TAmount.getText().equals(""))||(TDescription.getText().equals(""))){
            JOptionPane.showMessageDialog(this, "Please Enter All Data !","Error!",JOptionPane.OK_OPTION);}
        else if(CBox.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Please Choose a Category From List","Error!",JOptionPane.OK_OPTION);}
        else{
            Date date = new Date();
            try {
                DataBaseIncome.create_table();
                IncomeData.setIncome_Data((String)CBox.getSelectedItem(), Integer.parseInt(TAmount.getText()), TDescription.getText(),String.valueOf(date));
            } catch (SQLException ex) {
                Logger.getLogger(IncomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
            IncomePage Obj = new IncomePage();
            this.dispose();
        }
    }//GEN-LAST:event_BSaveActionPerformed

    private void CBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CBoxItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_CBoxItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ReportingPage a =new ReportingPage();
        a.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         Menu_Page Obj = new Menu_Page();
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(IncomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IncomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IncomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IncomePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IncomePage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BSave;
    private javax.swing.JComboBox<String> CBox;
    private javax.swing.JLabel L1;
    private javax.swing.JLabel L2;
    private javax.swing.JLabel L4;
    private javax.swing.JTextField TAmount;
    private javax.swing.JTextField TDescription;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JToolBar jToolBar3;
    // End of variables declaration//GEN-END:variables
}
