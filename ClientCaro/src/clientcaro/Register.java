package clientcaro;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Register extends javax.swing.JFrame {

    DataInputStream inFromServer;
    DataOutputStream outToServer;

    public Register(DataInputStream inFromServer, DataOutputStream outToServer) {
        setVisible(true);
        initComponents();
        this.inFromServer = inFromServer;
        this.outToServer = outToServer;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        t_hovaten = new javax.swing.JTextField();
        t_username = new javax.swing.JTextField();
        t_password = new javax.swing.JTextField();
        b_xacnhan = new javax.swing.JButton();
        b_exit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng ký");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Đăng ký");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Họ tên");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tài khoản");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Mật khẩu");

        t_hovaten.setName("t_hovaten"); // NOI18N

        b_xacnhan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        b_xacnhan.setText("Đăng ký");
        b_xacnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_xacnhanActionPerformed(evt);
            }
        });

        b_exit.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        b_exit.setForeground(new java.awt.Color(255, 0, 51));
        b_exit.setText("Exit");
        b_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                        .addComponent(b_exit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(1, 1, 1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(t_password, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(t_username, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(t_hovaten))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(b_xacnhan)
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(b_exit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(t_hovaten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(t_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(t_password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(b_xacnhan)
                .addGap(112, 112, 112))
        );

        t_hovaten.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b_xacnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_xacnhanActionPerformed
        try {
            if (t_username.getText().length() == 0 || t_password.getText().length() == 0 || t_hovaten.getText().length() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Không được để trống");
            } else {
                String resSend = "2-" + t_username.getText() + "-" + t_password.getText() + "-" + t_hovaten.getText();
                try {
                    outToServer.writeBytes(resSend + "\n");
                } catch (IOException ex) {
                    System.out.println("Không gửi đăng ký lên server đc");
                }
                JOptionPane.showMessageDialog(rootPane, "Register in successfully");
                new Loginsuccess(inFromServer, outToServer, resSend);
                setVisible(false);
//                String string;
//                try {
//                    System.out.println("tới đây");
//                    string = inFromServer.readUTF();
//                    System.out.println("String=" + string);
//                    if (string.equals("1")) {
//                        //a pop-up box
//                        JOptionPane.showMessageDialog(null, "You have register in successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
//                        System.exit(0);
//                    } else {
//
//                        JOptionPane.showMessageDialog(null, "Register failed!", "Failed!!",
//                                JOptionPane.ERROR_MESSAGE);
//                    }
//                } catch (IOException ex) {
//                    System.out.println("Get respon từ server bị lỗi");
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_b_xacnhanActionPerformed

    private void b_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_exitActionPerformed
        setVisible(false);
        new LoginCaro(inFromServer, outToServer);
    }//GEN-LAST:event_b_exitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_exit;
    private javax.swing.JButton b_xacnhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField t_hovaten;
    private javax.swing.JTextField t_password;
    private javax.swing.JTextField t_username;
    // End of variables declaration//GEN-END:variables
}
