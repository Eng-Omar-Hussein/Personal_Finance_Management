package Budget_Planner_New;

import User_Authentication.Cookies;
import incometracking.DataBaseIncome;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BudgetIncome extends javax.swing.JFrame {

    //   public static final int id = 13;
    public static final int id = Cookies.getID();
    //public static final int id=13;

    public static int numOfRow;
    HashMap<String, Integer> map = new HashMap<>();

    //constructor
    public BudgetIncome() {
        initComponents();

        try {
            if (DataBaseIncome.Connection()) {
                Connection conn = DriverManager.getConnection("jdbc:sqlite:Income_Data.sqlite");
                PreparedStatement pre = conn.prepareStatement("select * from income where id =" + id);
                ResultSet r = pre.executeQuery();
                ArrayList<String> array = new ArrayList<>();
                while (r.next()) {

                    if (map.containsKey(r.getString("category"))) {
                        int OldAmount = map.get(r.getString("category"));
                        map.put(r.getString("category"), OldAmount + r.getInt("amount"));

                    } else {
                        map.put(r.getString("category"), r.getInt("amount"));

                        array.add(r.getString("category"));

                    }

                }
                for (int i = 0; i < array.size(); i++) {
                    jComboBox2.addItem(array.get(i));

                    System.out.println(array.get(i));
                }

            }

            createTable();
            showFromDB("", "");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        jComboBox2.setSelectedIndex(-1);
        lbl_Actual.setText("");

    }

    // method to add income values to table
    public void addIncomeToTable() {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String catogeryDate = format.format(jDateChooser1.getDate());
        String catogeryName = jComboBox2.getSelectedItem().toString();
        Integer catogeryActual = Integer.parseInt(lbl_Actual.getText());
        Integer catogeryPlanned = Integer.parseInt(txt_Planned.getText());
        //String catogeryDate=txt_Date.getText();
        String catogeryDiscription = txt_Discription.getText();

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object obj[] = new Object[6];
        obj[0] = catogeryName;
        obj[1] = catogeryPlanned;
        obj[2] = catogeryActual;
        obj[3] = catogeryPlanned - catogeryActual;
        obj[4] = catogeryDiscription;
        obj[5] = catogeryDate;
        model.addRow(obj);

    }

    // method to create income table 
    public boolean createTable() throws SQLException {
        boolean flag = false;
        Connection con = null;

        try {
            con = ConnectionDataBase.connect();
            String sql = "CREATE TABLE IF NOT EXISTS income_" + id + "("
                    + "Row INTEGER,"
                    + "name TEXT,"
                    + "planned INTEGER NOT NULL,"
                    + "actual INTEGER ,"
                    + "diff INTEGER,"
                    + "date TEXT,"
                    + "discription TEXT"
                    + ");";
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            con.close();

        }
        return flag;
    }

    // method to add income values to db
    public boolean addIncomeToDB() throws SQLException {
        boolean flag = false;
        Connection con = null;
        PreparedStatement pre = null;
        String catogeryName = jComboBox2.getSelectedItem().toString();
        int catogeryActual = Integer.parseInt(lbl_Actual.getText());
        int catogeryPlanned = Integer.parseInt(txt_Planned.getText());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String catogeryDate = format.format(jDateChooser1.getDate());
        String catogeryDiscription = txt_Discription.getText();

        try {
            if (createTable()) {
                con = ConnectionDataBase.connect();
                pre = con.prepareStatement("INSERT INTO income_" + id + " values(?,?,?,?,?,?,?)");

                pre.setInt(1, getlastNumOfRow() + 1);
                pre.setString(2, catogeryName);
                pre.setInt(3, catogeryPlanned);
                pre.setInt(4, catogeryActual);
                pre.setInt(5, catogeryPlanned - catogeryActual);
                pre.setString(6, catogeryDate);
                pre.setString(7, catogeryDiscription);
                int num = pre.executeUpdate();
                if (num > 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            } else {
                System.out.println("falid to create table 1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pre.close();
            con.close();

        }
        return flag;
    }

    // method to show all values in DB to income table
    public void showFromDB(String s1, String s2) throws SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet r = null;
        try {
            ArrayList<BudgetData> list = new ArrayList<>();
            Object[] object = new Object[6];
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            con = ConnectionDataBase.connect();
            if (s1.equals("") && s2.equals("")) {
                pre = con.prepareStatement("select * from income_" + id);
            } else {
                pre = con.prepareStatement("select * from income_" + id + " where date between ? and ?");
                pre.setString(1, s1);
                pre.setString(2, s2);

            }
            r = pre.executeQuery();
            while (r.next()) {
                list.add(new BudgetData(r.getString("name"), r.getInt("Planned"), r.getInt("actual"), r.getInt("diff"), r.getString("date"), r.getString("discription")));

            }
            for (int i = 0; i < list.size(); i++) {
                object[0] = list.get(i).getName();
                object[1] = list.get(i).getPlanned();
                object[2] = list.get(i).getActual();
                object[3] = list.get(i).getDiff();
                object[4] = list.get(i).getDiscription();
                object[5] = list.get(i).getDate();
                model.addRow(object);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage());
        } finally {
            con.close();
            pre.close();
            r.close();
        }
    }

    // method to upadte data in DB
    public void updateDataInDB() throws SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        String catogeryName = jComboBox2.getSelectedItem().toString();
        int catogeryActual = Integer.parseInt(lbl_Actual.getText());
        int catogeryPlanned = Integer.parseInt(txt_Planned.getText());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        String catogeryDate = format.format(jDateChooser1.getDate());
        String catogeryDiscription = txt_Discription.getText();
        try {
            con = ConnectionDataBase.connect();
            pre = con.prepareStatement("UPDATE income_" + id + " set name =? , planned =?,actual =?, diff=?, date=?, discription =? where row=?");
            pre.setString(1, catogeryName);
            pre.setInt(2, catogeryPlanned);
            pre.setInt(3, catogeryActual);
            pre.setInt(4, catogeryPlanned - catogeryActual);
            pre.setString(5, catogeryDate);
            pre.setString(6, catogeryDiscription);
            pre.setInt(7, getNumOfRow());
            int num = pre.executeUpdate();
            if (num > 0) {
                System.out.println("updated data");
            } else {
                System.out.println("faild to update data");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            con.close();
            pre.close();
        }
    }

    // method to get num of row in dataBase
    public int getNumOfRow() throws SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet r = null;
        int Row = 0;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            con = ConnectionDataBase.connect();
            pre = con.prepareStatement("SELECT * FROM income_" + id + " where name =?");
            pre.setString(1, model.getValueAt(selectedRow, 0).toString());
            r = pre.executeQuery();

            if (r.next()) {
                Row = r.getInt("row");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BudgetIncome.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.close();
            pre.close();
            r.close();
        }
        return Row;
    }

    // method to get last numOfRow in db
    public int getlastNumOfRow() throws SQLException {
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet r = null;
        int maxNumOfRow = 0;
        try {
            con = ConnectionDataBase.connect();
            pre = con.prepareStatement("select * from income_" + id);
            r = pre.executeQuery();
            while (r.next()) {
                maxNumOfRow = Math.max(maxNumOfRow, r.getInt("Row"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            con.close();
            pre.close();
            r.close();
        }
        return maxNumOfRow;
    }

    //method to delete row from db
    public void deleteRow() throws SQLException {
        Connection con = null;
        PreparedStatement pre = null;

        try {
            con = ConnectionDataBase.connect();
            pre = con.prepareStatement("DELETE FROM income_" + id + " WHERE row=?");

            pre.setInt(1, getNumOfRow());
            int num = pre.executeUpdate();
            if (num > 0) {
                System.out.println("Deleted data");
            } else {
                System.out.println("faild to Deleted data");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            con.close();
            pre.close();
        }
    }

    // reset all fields to null text
    public void resetAllFields() {
        jComboBox2.setSelectedIndex(-1);
        txt_Discription.setText("");
        txt_Planned.setText("");
        lbl_Actual.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null);

    }

    public Boolean checkInputData() {
        boolean flag = true;
        String missing = "You foegot to Enter The Following\n";
        if (jComboBox2.getSelectedIndex() == -1) {
            missing += "Category\n";
            flag = false;
        }
        if (txt_Planned.getText().equals("")) {
            missing += "Planned\n";
            flag = false;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = jDateChooser1.getDate();
        if (date == null) {
            missing += "Date\n";
            flag = false;
        }
        if (txt_Discription.getText().equals("")) {
            missing += "Discription\n";
            flag = false;
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, missing);
        }
        System.out.println(flag);
        return flag;
    }

    public boolean checking() {

        Date date1 = jDateChooser2.getDate();
        Date date2 = jDateChooser3.getDate();
        boolean flag = true;
        String missing = "You foegot to Enter \n";
        if (date1 == null || date2 == null) {
            missing += "searching date\n";
            flag = false;
        }
        if (!flag) {
            JOptionPane.showMessageDialog(null, missing);
        }
        return flag;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_Discription = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbl_Actual = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_Planned = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        lbl_add = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_delete = new javax.swing.JLabel();
        lbl_update = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(25, 167, 206));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BUDGET INCOME");

        jLabel13.setBackground(new java.awt.Color(25, 167, 206));
        jLabel13.setIcon(new javax.swing.ImageIcon("icons/icons8_Rewind_48px.png"));
        jLabel13.setOpaque(true);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(324, 324, 324)
                .addComponent(jLabel1)
                .addContainerGap(409, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 950, 70));

        txt_Discription.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(25, 167, 206)));
        jPanel1.add(txt_Discription, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 210, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Catogery Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Actual");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, -1, 30));

        lbl_Actual.setBackground(new java.awt.Color(204, 204, 204));
        lbl_Actual.setOpaque(true);
        jPanel1.add(lbl_Actual, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 60, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Discription");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 70, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Date");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 50, 30));

        txt_Planned.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(25, 167, 206)));
        jPanel1.add(txt_Planned, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 210, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Planned");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 60, 30));

        lbl_add.setBackground(new java.awt.Color(25, 167, 206));
        lbl_add.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_add.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_add.setText("Add");
        lbl_add.setOpaque(true);
        lbl_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_addMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 290, 90, 30));

        jTable1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Catogery", "Planned", "Actual", "Diff", "Discription", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jTable1.setRowHeight(35);
        jTable1.setSelectionBackground(new java.awt.Color(0, 175, 80));
        jTable1.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 910, 240));

        lbl_delete.setBackground(new java.awt.Color(25, 167, 206));
        lbl_delete.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_delete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_delete.setText("DELETE");
        lbl_delete.setOpaque(true);
        lbl_delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_deleteMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 730, 90, 40));

        lbl_update.setBackground(new java.awt.Color(25, 167, 206));
        lbl_update.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lbl_update.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_update.setText("UPDATE");
        lbl_update.setOpaque(true);
        lbl_update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_updateMouseClicked(evt);
            }
        });
        jPanel1.add(lbl_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 730, 90, 40));

        jDateChooser1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(25, 167, 206)));
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 210, 30));

        jDateChooser2.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 200, 30));

        jDateChooser3.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, 200, 30));

        jLabel4.setBackground(new java.awt.Color(25, 167, 206));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search");
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 370, 70, 30));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("X");
        jLabel8.setOpaque(true);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jLabel8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel8KeyReleased(evt);
            }
        });
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 0, 30, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 200, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1000, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_addMouseClicked

        if (checkInputData()) {
            System.out.println(checkInputData());
            try {
                addIncomeToTable();
                System.out.println("here1");
                if (addIncomeToDB()) {
                    System.out.println("added data to db");
                    resetAllFields();
                } else {

                    System.out.println("faild to added data to db");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BudgetIncome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_lbl_addMouseClicked

    private void lbl_deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_deleteMouseClicked
        try {
            deleteRow();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            showFromDB("", "");
            resetAllFields();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lbl_deleteMouseClicked

    private void lbl_updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_updateMouseClicked
        try {
            updateDataInDB();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            showFromDB("", "");
            resetAllFields();
        } catch (SQLException ex) {
            Logger.getLogger(BudgetIncome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lbl_updateMouseClicked
    public int selectedRow;
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        selectedRow = jTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        String name = (String) model.getValueAt(selectedRow, 0);
        String planned = Integer.toString((int) model.getValueAt(selectedRow, 1));
        String actual = Integer.toString((int) model.getValueAt(selectedRow, 2));
        String dicription = (String) model.getValueAt(selectedRow, 4);
        String date = (String) model.getValueAt(selectedRow, 5);

        jComboBox2.setSelectedItem(name);
        lbl_Actual.setText(actual);
        txt_Planned.setText(planned);

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Date d = format.parse(date);
            jDateChooser1.setDate(d);
        } catch (ParseException ex) {
            Logger.getLogger(BudgetIncome.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt_Discription.setText(dicription);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        if (checking()) {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            String date1 = format.format(jDateChooser2.getDate());
            String date2 = format.format(jDateChooser3.getDate());
            try {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                showFromDB(date1, date2);
            } catch (SQLException ex) {
                Logger.getLogger(BudgetIncome.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        resetAllFields();
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            showFromDB("", "");
        } catch (SQLException ex) {
            Logger.getLogger(BudgetIncome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jLabel8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel8KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8KeyReleased

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.dispose();
        new Main_Budget().setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (jComboBox2.getSelectedIndex() == 0) {
            lbl_Actual.setText(map.get(jComboBox2.getItemAt(0)).toString());

        }
        if (jComboBox2.getSelectedIndex() == 1) {
            lbl_Actual.setText(map.get(jComboBox2.getItemAt(1)).toString());

        }
        if (jComboBox2.getSelectedIndex() == 2) {
            lbl_Actual.setText(map.get(jComboBox2.getItemAt(2)).toString());

        }
        if (jComboBox2.getSelectedIndex() == 3) {
            lbl_Actual.setText(map.get(jComboBox2.getItemAt(3)).toString());

        }
        if (jComboBox2.getSelectedIndex() == 4) {
            lbl_Actual.setText(map.get(jComboBox2.getItemAt(4)).toString());

        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(BudgetIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BudgetIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BudgetIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BudgetIncome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BudgetIncome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_Actual;
    private javax.swing.JLabel lbl_add;
    private javax.swing.JLabel lbl_delete;
    private javax.swing.JLabel lbl_update;
    private javax.swing.JTextField txt_Discription;
    private javax.swing.JTextField txt_Planned;
    // End of variables declaration//GEN-END:variables
}
