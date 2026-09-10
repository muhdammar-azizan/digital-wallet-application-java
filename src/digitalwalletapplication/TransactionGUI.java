//SECTION 03B
//MUHAMMAD AMMAR BIN AZIZAN CB23037
//MUHAMMAD ALIF AIMAN BIN AZHAR CB23120
//MUHAMMAD HIZBU FARHAN BIN ALIAS CB23022


package digitalwalletapplication;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class TransactionGUI extends javax.swing.JFrame {

    /**
     * Creates new form TransactionGUI
     */
    public TransactionGUI() {
        initComponents();

        String loggedInUserId = Session.getLoggedInUserId();

        if (loggedInUserId != null) {
            try {
                // OOP Requirement: Database manipulation - SEARCH (load current balance on form open)
                PreparedStatement ps = MyConnection.getConnection().prepareStatement(
                    "SELECT `walletba` FROM `register` WHERE `id`=?");
                ps.setString(1, loggedInUserId);
                ResultSet rs = ps.executeQuery();

                String balanceText;
                if (rs.next()) {
                    String currentBalanceStr = rs.getString("walletba");
                    if (currentBalanceStr != null && !currentBalanceStr.trim().isEmpty()) {
                        balanceText = "Current Balance: " + currentBalanceStr;
                    } else {
                        balanceText = "You do not have a wallet yet.";
                    }
                } else {
                    balanceText = "You do not have a wallet yet.";
                }

                javax.swing.JLabel balanceLabel = new javax.swing.JLabel(balanceText);
                balanceLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
                balanceLabel.setBounds(jLabel2.getX(), jLabel6.getY() + jLabel6.getHeight() + 5, 300, 20);
                jPanel1.add(balanceLabel);
            } catch (SQLException ex) {
                Logger.getLogger(TransactionGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        javax.swing.JButton backButton = new javax.swing.JButton("Back");
        int headerHeight = jPanel1.getY();
        int backButtonY = Math.max(2, (headerHeight - 25) / 2);
        backButton.setBounds(10, backButtonY, 70, 25);
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu mn = new Menu();
                mn.setVisible(true);
                mn.pack();
                mn.setLocationRelativeTo(null);
                mn.setDefaultCloseOperation(Menu.EXIT_ON_CLOSE);
                dispose();
            }
        });
        getContentPane().add(backButton);

        jTextField3.setToolTipText("Format: DD/MM/YYYY, e.g. 15/01/2025");

        javax.swing.JButton pickDateButton = new javax.swing.JButton("Pick Date");
        pickDateButton.setBounds(jTextField3.getX() + jTextField3.getWidth() + 8, jTextField3.getY(), 90, jTextField3.getHeight());
        pickDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showDatePicker();
            }
        });
        jPanel1.add(pickDateButton);
    }

    private void showDatePicker() {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        final javax.swing.JDialog dialog = new javax.swing.JDialog(this, "Select Date", true);
        dialog.setLayout(new java.awt.BorderLayout());

        final javax.swing.JLabel monthLabel = new javax.swing.JLabel("", javax.swing.SwingConstants.CENTER);
        javax.swing.JButton prevButton = new javax.swing.JButton("<");
        javax.swing.JButton nextButton = new javax.swing.JButton(">");

        javax.swing.JPanel headerPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        headerPanel.add(prevButton, java.awt.BorderLayout.WEST);
        headerPanel.add(monthLabel, java.awt.BorderLayout.CENTER);
        headerPanel.add(nextButton, java.awt.BorderLayout.EAST);

        final javax.swing.JPanel daysPanel = new javax.swing.JPanel(new java.awt.GridLayout(0, 7, 2, 2));

        final Runnable[] refresh = new Runnable[1];
        refresh[0] = new Runnable() {
            public void run() {
                daysPanel.removeAll();

                String[] dayNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                for (String dn : dayNames) {
                    daysPanel.add(new javax.swing.JLabel(dn, javax.swing.SwingConstants.CENTER));
                }

                java.util.Calendar temp = (java.util.Calendar) calendar.clone();
                temp.set(java.util.Calendar.DAY_OF_MONTH, 1);
                int startDayOfWeek = temp.get(java.util.Calendar.DAY_OF_WEEK);
                int daysInMonth = temp.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

                for (int i = 1; i < startDayOfWeek; i++) {
                    daysPanel.add(new javax.swing.JLabel(""));
                }

                for (int day = 1; day <= daysInMonth; day++) {
                    final int selectedDay = day;
                    javax.swing.JButton dayButton = new javax.swing.JButton(String.valueOf(day));
                    dayButton.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            calendar.set(java.util.Calendar.DAY_OF_MONTH, selectedDay);
                            int year = calendar.get(java.util.Calendar.YEAR);
                            int month = calendar.get(java.util.Calendar.MONTH) + 1;
                            String formatted = String.format("%02d/%02d/%04d", selectedDay, month, year);
                            jTextField3.setText(formatted);
                            dialog.dispose();
                        }
                    });
                    daysPanel.add(dayButton);
                }

                String[] monthNames = {"January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"};
                monthLabel.setText(monthNames[calendar.get(java.util.Calendar.MONTH)] + " " + calendar.get(java.util.Calendar.YEAR));

                daysPanel.revalidate();
                daysPanel.repaint();
            }
        };

        prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendar.add(java.util.Calendar.MONTH, -1);
                refresh[0].run();
            }
        });
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calendar.add(java.util.Calendar.MONTH, 1);
                refresh[0].run();
            }
        });

        refresh[0].run();

        dialog.add(headerPanel, java.awt.BorderLayout.NORTH);
        dialog.add(daysPanel, java.awt.BorderLayout.CENTER);
        dialog.setSize(280, 280);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCompleteTrans = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel2.setText("Enter amount:");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel3.setText("Enter the Date :");

        jLabel4.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel4.setText("Enter the Receiver ID :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton1.setText("Finish");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jLabel6.setText("TRANSACTION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(44, 44, 44)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField4)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jCompleteTrans)
                                            .addGap(0, 0, Short.MAX_VALUE)))))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(50, 50, 50)
                .addComponent(jCompleteTrans)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextField5.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jTextField5.setText("DIGITAL WALLET APPLICATION");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(140, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(142, 142, 142))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String loggedInUserId = Session.getLoggedInUserId();

        if (loggedInUserId == null) {
            JOptionPane.showMessageDialog(null, "Please log in first.");
            return;
        }

        String amountText = jTextField2.getText();
        String date = jTextField3.getText();
        String recid = jTextField4.getText();

        if (amountText == null || amountText.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter an amount.");
            return;
        }

        if (date == null || date.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a date.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException pe) {
            JOptionPane.showMessageDialog(null, "Please enter a valid date in DD/MM/YYYY format (or use Pick Date).");
            return;
        }

        if (recid == null || recid.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a Receiver ID.");
            return;
        }

        double amount;
        try {
            amount = Double.parseDouble(amountText.trim());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount.");
            return;
        }

        if (amount <= 0) {
            JOptionPane.showMessageDialog(null, "Amount must be greater than zero.");
            return;
        }

        PreparedStatement checkPs;
        PreparedStatement updatePs;
        PreparedStatement insertPs;
        ResultSet rs;
        String checkQuery = "SELECT `walletba` FROM `register` WHERE `id`=?"; // OOP Requirement: Database manipulation - SEARCH
        String updateQuery = "UPDATE `register` SET `walletba`=? WHERE `id`=?"; // OOP Requirement: Database manipulation - EDIT/UPDATE
        String insertQuery = "INSERT INTO `transactions` (`transaction_id`, `user_id`, `amount`, `transaction_date`, `recipient_id`) VALUES (?,?,?,?,?)"; // OOP Requirement: Database manipulation - INSERT

        try {
            checkPs = MyConnection.getConnection().prepareStatement(checkQuery);
            checkPs.setString(1, loggedInUserId);
            rs = checkPs.executeQuery();

            double currentBalance = 0.0;
            if (rs.next()) {
                String currentBalanceStr = rs.getString("walletba");
                if (currentBalanceStr != null && !currentBalanceStr.trim().isEmpty()) {
                    try {
                        currentBalance = Double.parseDouble(currentBalanceStr.trim());
                    } catch (NumberFormatException nfe) {
                        currentBalance = 0.0;
                    }
                }
            }

            if (currentBalance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient balance for this transaction.");
                return;
            }

            double newBalance = currentBalance - amount;

            // OOP Requirement: Database manipulation - SEARCH (find highest existing transaction ID)
            PreparedStatement maxIdPs = MyConnection.getConnection().prepareStatement(
                "SELECT MAX(CAST(SUBSTRING(transaction_id, 2) AS UNSIGNED)) AS maxNum FROM transactions WHERE transaction_id LIKE 'T%'");
            ResultSet maxIdRs = maxIdPs.executeQuery();
            int nextNum = 1;
            if (maxIdRs.next()) {
                int maxNum = maxIdRs.getInt("maxNum");
                if (!maxIdRs.wasNull()) {
                    nextNum = maxNum + 1;
                }
            }
            String transactionId = "T" + String.format("%03d", nextNum);

            updatePs = MyConnection.getConnection().prepareStatement(updateQuery);
            updatePs.setString(1, String.valueOf(newBalance));
            updatePs.setString(2, loggedInUserId);
            updatePs.executeUpdate();

            insertPs = MyConnection.getConnection().prepareStatement(insertQuery);
            insertPs.setString(1, transactionId);
            insertPs.setString(2, loggedInUserId);
            insertPs.setDouble(3, amount);
            insertPs.setString(4, date.trim());
            insertPs.setString(5, recid.trim());
            insertPs.executeUpdate();

            JOptionPane.showMessageDialog(null, "Transaction successful! Transaction ID: " + transactionId + ", New balance: " + newBalance);

            Menu mn = new Menu();
            mn.setVisible(true);
            mn.pack();
            mn.setLocationRelativeTo(null);
            mn.setDefaultCloseOperation(Menu.EXIT_ON_CLOSE);
            dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            Logger.getLogger(TransactionGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField4ActionPerformed

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
            java.util.logging.Logger.getLogger(TransactionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jCompleteTrans;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
