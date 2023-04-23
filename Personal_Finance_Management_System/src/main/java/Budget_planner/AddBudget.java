package Budget_planner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anass
 */
public class AddBudget extends javax.swing.JFrame {

    /**
     * Creates new form AddBudget
     */
    public AddBudget()  {
        initComponents();
        
        panel_tableExponse.setVisible(false);
        panel_tableIncome.setVisible(false);
        
    }
    
    //method to store budget amount,name,date 
    public boolean saveBudgetData(){
        boolean flag=false;
            String budgetName=txt_name.getText();
            String budgetAmount=txt_amount.getText();
            String budgetDate=txt_date.getText();
            String sql="insert into AllBudget (budgetName,budgetAmount,budgetDate) values (?,?,?)";
        try {
            Connection con=ConnectionDataBase.connect();
            PreparedStatement pre=con.prepareStatement(sql);
            pre.setString(1,budgetName);
            pre.setInt(2,Integer.parseInt(budgetAmount));
            pre.setString(3,budgetDate);
            int numRow=pre.executeUpdate();
            if(numRow>0){
                flag=true;
            }else{
                flag=false;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        }
        return flag;
    }
    
    String missing;
    // method to check validation of budget name,amount and date
    public boolean checkBudgetValidate(){
        boolean flag=false;
        missing ="you forgot to fill : \n";
        String budgetName=txt_name.getText();
        String budgetAmount=txt_amount.getText();
        String budgetDate=txt_date.getText();
        
        if(budgetName.equals("")){
            missing+="name of budget \n";
        }
        if(budgetAmount.equals("")){
            missing+="amount of budget \n";
        }
        if(budgetDate.equals("")){
            missing+="date of budget \n";
        }
        if(!budgetName.equals("") && !budgetAmount.equals("") && !budgetDate.equals("")){
            flag=true;
        }else{
            
            flag=false;
        }
        return flag;
    }
    
    // method to check validation of catogery name and amount
     public boolean checkCatogeryValidate(){
        boolean flag=false;
        missing ="you forgot to fill : \n";
        String catogeryName=txt_name1.getText();
        String catogeryAmount=txt_name2.getText();
        
        
        if(catogeryName.equals("")){
            missing+="name of catogery \n";
        }
        if(catogeryAmount.equals("")){
            missing+="amount of catogery \n";
        }
        
        if(!catogeryName.equals("") && !catogeryAmount.equals("") ){
            flag=true;
        }else{
            
            flag=false;
        }
        return flag;
    }
    
    //method to change lable7 text
    public void changelable7Text(){
        if(combo_in_ex.getSelectedIndex()==0){
            jLabel7.setText("Add Exponence");
        }else if(combo_in_ex.getSelectedIndex()==1){
            jLabel7.setText("Add Income");
        }
    }
    
    int sum_exponse=0,sum_income=0;
    // method to add income or exponse to table
    public void addDateToTable(){
        String name=txt_name1.getText();
        String amount=txt_name2.getText();
        if(combo_in_ex.getSelectedIndex()==0){
            panel_tableExponse.setVisible(true);
            DefaultTableModel model=(DefaultTableModel)tbl_exponse.getModel();
            Object []obj={name,amount};
            model.addRow(obj);
            sum_exponse+=Integer.parseInt(amount);
            txt_name1.setText("");
            txt_name2.setText("");
        }else if(combo_in_ex.getSelectedIndex()==1){
            panel_tableIncome.setVisible(true);
            DefaultTableModel model=(DefaultTableModel)tbl_income.getModel();
            Object []obj={name,amount};
            model.addRow(obj);
            sum_income=Integer.parseInt(amount);
            txt_name1.setText("");
            txt_name2.setText("");
        }
        System.out.println(sum_income+"  "+sum_exponse);
    }
    
    //method to create table for exponse 
    public void createTable_exponse() throws SQLException{
        String name=txt_name.getText();
        name+="exponse";
        String sql="create table IF NOT EXISTS "+name+" (name text ,amount text not null)";
        Connection con=null;
            try {
                con = ConnectionDataBase.connect();
                Statement stmt = con.createStatement();
                stmt.execute(sql);
                System.out.println(name+"exponse table created");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }finally{
                con.close();
            }
    }
    
    //method to create table for income 
    public void createTable_income() throws SQLException{
        String name=txt_name.getText();
        name+="income";
        String sql="create table  if not exists "+name+" (name text ,amount text)";
        Connection con=null;
        Statement stmt=null;
            try {
                con = ConnectionDataBase.connect();
                
                stmt = con.createStatement() ;
                // create a new table
                stmt.execute(sql);
                System.out.println("income table created");
                
            } catch (SQLException ex) {
                    System.out.println("falid to add table");
                ex.printStackTrace();
            }finally{
                con.close();
                stmt.close();
            }
    }
    
    // method to store data of income or exponse table in data base
    public void saveData() throws SQLException{
        String BudgetName=txt_name.getText();
        String namecatogery=txt_name1.getText();
        String amountcatogery=txt_name2.getText();
                
        //exponse
        if(combo_in_ex.getSelectedIndex()==0){
            
            try {
                //call method  to create table
                createTable_exponse();
            } catch (SQLException ex) {
                Logger.getLogger(AddBudget.class.getName()).log(Level.SEVERE, null, ex);
            }
                Connection con=null;
                PreparedStatement pre=null;
                try {
                    con = ConnectionDataBase.connect();
                    pre=con.prepareStatement("insert into "+BudgetName+"exponse values(?,?)");
                    pre.setString(1, namecatogery);
                    pre.setString(2, amountcatogery);
                    pre.execute();
//                txt_in_ex_amount.setText("");
//            txt_in_ex_name.setText("");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex.getMessage());
                }finally{
                    con.close();
                    pre.close();
                }
                
                //income
            
                
            
        //income
        }else if(combo_in_ex.getSelectedIndex()==1){
            String name=txt_name.getText();
            name+="income";
            //call method  to create table
            createTable_income();
            Connection con=null;
            PreparedStatement pre=null;
            try {
                con = ConnectionDataBase.connect();
                pre=con.prepareStatement("insert into "+name+" values(?,?)");
                pre.setString(1, namecatogery);
                pre.setString(2, amountcatogery);
                
                pre.execute();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }finally{
                con.close();
            }
            updateIncome();
        }
    }
    
    //method to get All amount
    public int getAllAmount() throws SQLException{
        int tot_amount=0;
        Connection con=null;
        PreparedStatement pre=null;
        ResultSet r=null;
        try {
            con=ConnectionDataBase.connect();
            pre=con.prepareStatement("select * from Allbudget where budgetname =?");
            pre.setString(1, txt_name.getText());
            r=pre.executeQuery();
            if(r.next()){
                tot_amount=r.getInt("budgetamount");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            con.close();
            pre.close();
            r.close();
        }
        return tot_amount;
            
    }
    
    // method to calculate spending
    public void setSpendingAndRemining() throws SQLException{
        Connection con=null;
        PreparedStatement pre=null;
        try {
            
            
            con=ConnectionDataBase.connect();
            pre=con.prepareStatement("update Allbudget set budgetspend=? ,budgetRemining=? where budgetName=?");
            pre.setInt(1, sum_exponse);
            pre.setInt(2, getAllAmount()-sum_exponse);
            pre.setString(3, txt_name.getText());
            pre.execute();
            System.out.println("updated successful");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally{
            pre.close();
            con.close();
        }
    }
    
    // method to update total amount of budget [total amount+sum_income] 
    public void updateIncome() throws SQLException{
        Connection con=null;
        PreparedStatement pre=null;
        try{
           con=ConnectionDataBase.connect();
           pre=con.prepareStatement("update allBudget set budgetamount =? where budgetname=?");
           pre.setInt(1, sum_income+getAllAmount());
           pre.setString(2, txt_name.getText());
           pre.execute();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            con.close();
            pre.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_addBudget = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_date = new javax.swing.JTextField();
        combo_in_ex = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel_tableIncome = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_income = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        panel_tableExponse = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_exponse = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        txt_amount = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txt_name1 = new javax.swing.JTextField();
        txt_name2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(215, 214, 214));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(25, 167, 206));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Add Budget");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(431, 431, 431)
                .addComponent(jLabel1)
                .addContainerGap(541, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1084, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Catogery");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        btn_addBudget.setBackground(new java.awt.Color(221, 221, 221));
        btn_addBudget.setText("Add Budget");
        btn_addBudget.setBorder(null);
        btn_addBudget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addBudgetActionPerformed(evt);
            }
        });
        jPanel1.add(btn_addBudget, new org.netbeans.lib.awtextra.AbsoluteConstraints(734, 165, 84, 29));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Budget amount");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 138, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Budget Date");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 138, -1, -1));

        txt_date.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(25, 167, 206)));
        jPanel1.add(txt_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 164, 181, 30));

        combo_in_ex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Expense", "Income" }));
        combo_in_ex.setSelectedIndex(-1);
        combo_in_ex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_in_exMouseClicked(evt);
            }
        });
        combo_in_ex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_in_exActionPerformed(evt);
            }
        });
        jPanel1.add(combo_in_ex, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 180, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 351, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Amout");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 351, -1, -1));

        panel_tableIncome.setBackground(new java.awt.Color(215, 214, 214));

        tbl_income.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name", "amount"
            }
        ));
        tbl_income.setRowHeight(40);
        tbl_income.setSelectionBackground(new java.awt.Color(25, 167, 206));
        jScrollPane4.setViewportView(tbl_income);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Incomes");

        javax.swing.GroupLayout panel_tableIncomeLayout = new javax.swing.GroupLayout(panel_tableIncome);
        panel_tableIncome.setLayout(panel_tableIncomeLayout);
        panel_tableIncomeLayout.setHorizontalGroup(
            panel_tableIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tableIncomeLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panel_tableIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panel_tableIncomeLayout.setVerticalGroup(
            panel_tableIncomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tableIncomeLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );

        jPanel1.add(panel_tableIncome, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 420, -1, -1));

        panel_tableExponse.setBackground(new java.awt.Color(215, 214, 214));

        tbl_exponse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "name", "amount"
            }
        ));
        tbl_exponse.setRowHeight(40);
        tbl_exponse.setSelectionBackground(new java.awt.Color(25, 167, 206));
        jScrollPane5.setViewportView(tbl_exponse);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Exponense");

        javax.swing.GroupLayout panel_tableExponseLayout = new javax.swing.GroupLayout(panel_tableExponse);
        panel_tableExponse.setLayout(panel_tableExponseLayout);
        panel_tableExponseLayout.setHorizontalGroup(
            panel_tableExponseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_tableExponseLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panel_tableExponseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        panel_tableExponseLayout.setVerticalGroup(
            panel_tableExponseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_tableExponseLayout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        jPanel1.add(panel_tableExponse, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 470, -1));

        txt_amount.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(25, 167, 206)));
        jPanel1.add(txt_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 181, 30));

        txt_name.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(25, 167, 206)));
        jPanel1.add(txt_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 181, 30));

        jLabel7.setText("Add Income");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 304, -1, -1));

        jPanel3.setBackground(new java.awt.Color(25, 167, 206));
        jPanel3.setPreferredSize(new java.awt.Dimension(956, 5));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 328, -1, -1));

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Budget Name");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 138, -1, -1));

        txt_name1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(25, 167, 206)));
        jPanel1.add(txt_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 181, 30));

        txt_name2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(25, 167, 206)));
        jPanel1.add(txt_name2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 380, 181, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 760));

        setSize(new java.awt.Dimension(1091, 764));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addBudgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addBudgetActionPerformed
        if(checkBudgetValidate()){
            if(saveBudgetData()){
                JOptionPane.showMessageDialog(rootPane, "budget Added Successful");
                txt_name.setEnabled(false);
                txt_date.setEnabled(false);
                txt_amount.setEnabled(false);
                btn_addBudget.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(rootPane, "faild to add budget");
            }
        }else{
            JOptionPane.showMessageDialog(null,missing,"warning",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_addBudgetActionPerformed

    private void combo_in_exActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_in_exActionPerformed
        if(checkBudgetValidate())
            changelable7Text();
        else{
            JOptionPane.showMessageDialog(rootPane, "you must enter budget details first");
        }
    }//GEN-LAST:event_combo_in_exActionPerformed

    private void combo_in_exMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_in_exMouseClicked
        if(checkBudgetValidate())
            changelable7Text();
        else{
            JOptionPane.showMessageDialog(rootPane, "you must enter budget details first");
        }
    }//GEN-LAST:event_combo_in_exMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(combo_in_ex.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(rootPane, "choose catogery first");
            
        }else{
            if(checkCatogeryValidate()){
                try {
                    addDateToTable();
                    saveData();
                    
                    setSpendingAndRemining();
                } catch (SQLException ex) {
                    Logger.getLogger(AddBudget.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                JOptionPane.showMessageDialog(rootPane, missing);
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
            java.util.logging.Logger.getLogger(AddBudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBudget.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                    new AddBudget().setVisible(true);
               
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addBudget;
    private javax.swing.JComboBox<String> combo_in_ex;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel panel_tableExponse;
    private javax.swing.JPanel panel_tableIncome;
    private javax.swing.JTable tbl_exponse;
    private javax.swing.JTable tbl_income;
    private javax.swing.JTextField txt_amount;
    private javax.swing.JTextField txt_date;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_name1;
    private javax.swing.JTextField txt_name2;
    // End of variables declaration//GEN-END:variables
}
