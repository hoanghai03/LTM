package clientcaro;

import java.awt.event.ItemEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class Loginsuccess extends javax.swing.JFrame {

    DataInputStream inFromServer;
    DataOutputStream outToServer;
    String _name;
    address add;

    public Loginsuccess(DataInputStream inFromServer, DataOutputStream outToServer, String _name) {
        setVisible(true);
        setLocation(400, 200);
        initComponents();
        this.inFromServer = inFromServer;
        this.outToServer = outToServer;
        this._name = _name;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createServer = new javax.swing.JButton();
        btExit = new javax.swing.JButton();
        comServer = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton_loadServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        createServer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        createServer.setText("Khởi tạo server");
        createServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createServerActionPerformed(evt);
            }
        });

        btExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btExit.setForeground(new java.awt.Color(255, 51, 51));
        btExit.setText("Exit");
        btExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExitActionPerformed(evt);
            }
        });

        comServer.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comServerItemStateChanged(evt);
            }
        });
        comServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comServerActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Chọn đối thủ");

        jButton_loadServer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton_loadServer.setText("Tải danh sách đối thủ");
        jButton_loadServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_loadServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExit))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(createServer))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel4)
                                .addGap(39, 39, 39)
                                .addComponent(comServer, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 162, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton_loadServer)
                .addGap(159, 159, 159))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btExit)
                .addGap(32, 32, 32)
                .addComponent(createServer)
                .addGap(18, 18, 18)
                .addComponent(jButton_loadServer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comServer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * TẠO PHÒNG CHƠI, GỬI DỮ LIỆU PHÒNG TẠO VỀ SERVER
     *
     * @param evt
     */
    private void createServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createServerActionPerformed
        //Thông tin gồm: <tên case>-<tên server>-<IP server>
        System.out.println("clicked to create server");
        String _taoServer = "3-" + _name + "-" + IpAddress.getIpAddress();
        try {
            //Gửi thông tin phòng lên Server
            outToServer.writeBytes(_taoServer + "\n");

            //Đọc dữ liệu Server gửi về 
            String _string = inFromServer.readLine();
            String[] addr;
            int _port1, _port2;
            if (_string.length() < 3) {
                _port1 = 9999;
                _port2 = 9998;
            } else {
                addr = _string.split("-");
                _port1 = Integer.parseInt(addr[1]);
                _port2 = Integer.parseInt(addr[2]);
            }
            CaroServer caroServer = new CaroServer(_port1, _port2, _name);
            setVisible(false);
        } catch (Exception ex) {
            
        }
    }//GEN-LAST:event_createServerActionPerformed

    private void comServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comServerActionPerformed

    }//GEN-LAST:event_comServerActionPerformed

    public static String[] ipHost = new String[100];

    /**
     * LOAD DANH SÁCH PHÒNG ĐANG CHỜ
     *
     * @param evt
     */
    private void jButton_loadServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_loadServerActionPerformed
        String _choigame = "4-aaa";
        try {
            //gui len server
            outToServer.writeBytes(_choigame + "\n");
            //Đọc dữ liệu Server gửi về
            //PORT 1 
            String _strPort1 = inFromServer.readLine();
            String[] _cutPort1 = _strPort1.split("-");
            for (int i = 0; i < _cutPort1.length; i++) {
                add.port1.add(Integer.parseInt(_cutPort1[i]));
            }
            //PORT 2
            String _strPort2 = inFromServer.readLine();
            String[] _cutPort2 = _strPort2.split("-");
            for (int i = 0; i < _cutPort2.length; i++) {
                add.port2.add(Integer.parseInt(_cutPort2[i]));
            }
            // IP chủ phòng
            String _strIp = inFromServer.readLine();
            String[] _cutIp = _strIp.split("-");
            for (int i = 0; i < _cutIp.length; i++) {
                add.ip.add(_cutIp[i]);
                ipHost = _cutIp;
            }
            //Tên chủ phòng
            String _strName = inFromServer.readLine();
            String[] _cutName = _strName.split("-");
            for (int i = 0; i < _cutName.length; i++) {
                add.name.add(_cutName[i]);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Không có đối thủ đang online");
        }

        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("Chọn đối thủ:");
        for (int i = 0; i < add.name.size(); i++) {
            model.addElement(add.name.get(i));
        }
        comServer.setModel(model);
    }//GEN-LAST:event_jButton_loadServerActionPerformed

    private void comServerItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comServerItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            //JOptionPane.showMessageDialog(null, evt.getItem());
            int i = 0;
            for (i = 0; i < add.name.size(); i++) {
                if (evt.getItem() == add.name.get(i)) {
                    break;
                }
            }
            System.err.println("IP: " + ipHost[i]);
            CaroClient caroclient = new CaroClient(ipHost[i], add.port1.get(i), add.port2.get(i), _name);
            //remove server sau khi da duoc chon
            String _choigame = "5-" + evt.getItem();
            try {
                //gui len server
                outToServer.writeBytes(_choigame + "\n");
            } catch (Exception ex) {
              
            }
        }
        setVisible(false);
    }//GEN-LAST:event_comServerItemStateChanged

    private void btExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExitActionPerformed
        System.exit(1);
    }//GEN-LAST:event_btExitActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExit;
    private javax.swing.JComboBox comServer;
    private javax.swing.JButton createServer;
    private javax.swing.JButton jButton_loadServer;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
