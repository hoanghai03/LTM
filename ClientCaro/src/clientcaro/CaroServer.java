package clientcaro;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.RenderingHints;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

class AddClient implements Runnable {

    JTextArea chatTextArea = new JTextArea();
    Socket client = null;

    public AddClient(Socket client, JTextArea chatTextArea) {
        this.client = client;
        this.chatTextArea = chatTextArea;
    }

    public void run() {
        String line;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        while (true) {
            try {
                line = in.readLine();
                chatTextArea.append("Client : " + line + "\n");
            } catch (IOException ex) {
            }
        }
    }
}

public class CaroServer extends javax.swing.JFrame {

    public int port_server1 = 0;
    public int port_server2 = 0;

    /**
     * Creates new form Caro
     */
    public CaroServer(int _portserver1, int _portserver2, String name) {
        initComponents();
        setVisible(true);
        setTitle(name + " (Server)");
        port_server1 = _portserver1; // Port dung tao server de choi game
        port_server2 = _portserver2; // Port dung de chat khi choi game

        myself.start();
        you.start();

        you.suspend();

        class Listen extends Thread {

            public Listen() {
                start();
            }

            @Override
            public void run() {
                listenSocket();
            }
        }

        class ListenGame extends Thread {

            public ListenGame() {
                start();
            }

            @Override
            public void run() {
                listenSocketGamne();
            }
        }

        new Listen();
        new ListenGame();

        createTrayIcon();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardPanel = new javax.swing.JPanel(){
            public void paintComponent(Graphics g){
                this.setOpaque(false);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                //Vẽ bàn cờ
                g.setColor(Color.GRAY);
                for(int r = 0;r<=Size;r++){
                    g.drawLine(X0, Y0+r*Width, X0+Size*Width, Y0+r*Width);
                }
                for(int c = 0;c<=Size;c++){
                    g.drawLine(X0+c*Width, Y0, X0+c*Width, Y0+Size*Width);
                }

                //Vẽ ô có chuột qua
                if(!isPause)
                if(currentColumn<Size&&currentColumn>=0&&currentRow<Size&&currentRow>=0){
                    g.setColor(new Color(0, 0, 0, 80));
                    g2.fillOval(X0+currentColumn*Width+Width/6+1,Y0+ currentRow*Width+Width/6+1, 2*Width/3, 2*Width/3);
                }
                //Vẽ các vị trí đã đánh
                if(checked.size()==0) return;
                for(int p = 0;p<checked.size();p++){
                    if(startUser){
                        if(p%2==0) g2.setColor(Color.BLACK);
                        else g2.setColor(Color.GREEN);
                    }else{
                        if(p%2!=0) g2.setColor(Color.BLACK);
                        else g2.setColor(Color.GREEN);
                    }
                    g2.fillOval(X0+checked.get(p).x*Width+Width/6+1,Y0+ checked.get(p).y*Width+Width/6+1, 2*Width/3, 2*Width/3);
                    //g2.drawString(String.valueOf(p),X0+checked.get(p).x*Width+12,Y0+ checked.get(p).y*Width+20);
                }
                //Đánh dấu ô mới đánh
                g.setColor(Color.RED);
                g.drawRect(checked.get(checked.size()-1).x*Width+X0, checked.get(checked.size()-1).y*Width+Y0, Width, Width);
                super.paintComponent(g);
            }
        }
        ;
        backButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caro Sever");

        boardPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        boardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boardPanelMouseClicked(evt);
            }
        });
        boardPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                boardPanelMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 720, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 563, Short.MAX_VALUE)
        );

        backButton.setText("Xin đi lại");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton)
                .addGap(259, 259, 259))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boardPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMouseClicked
        if (isPause) {
            return;
        }

        Point p = new Point();
        //Kiểm tra vị trí có thuộc bàn cờ không?
        if ((currentColumn < Size && currentColumn >= 0 && currentRow < Size && currentRow >= 0)) {
            p = new Point(currentColumn, currentRow);
            System.out.println("[CaroServer LOG]: Column: " + currentColumn + " | Row: " + currentRow);
        } else {
            System.out.println("[CaroServer LOG]: Vi tri khong thuoc ban co.");
            return;
        }
        if (!checked.contains(p)) {
            checked.add(p);
            System.out.println("[CaroServer LOG]: Da them point vao checked");

            currentPoint = new Point(p);
            System.out.println("[CaroServer LOG]: Da gan current point bang p");

            currentColumn = -1;
            currentRow = -1;
            boardPanel.repaint();
            try {
                System.out.println("[CaroServer LOG]: Current point co toa do: " + p.getX() + " - " + p.getY());
                outGame.writeObject(currentPoint); //truyền thông tin

                System.out.println("[CaroServer LOG]: Da gui point sang doi thu");
            } catch (IOException ex) {
                Logger.getLogger(CaroServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (isWin(true)) {
                JOptionPane.showMessageDialog(this, "Bạn đã thắng");
                checked.removeAllElements();
                startUser = false;
            }
            user = false;//tới quân đỏ
            myself.suspend();
            you.resume();
        }
        isPause = true;
        boardPanel.repaint();

    }//GEN-LAST:event_boardPanelMouseClicked

    /**
     * Vẽ điểm đánh lên bàn cờ
     */
    private void boardPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boardPanelMouseMoved

        int CX = evt.getX();
        int CY = evt.getY();
        if (CY - Y0 < 0) {
            //System.out.println("CX = " + evt.getX() + " ... CY = " + evt.getY() + "\n");
            currentColumn = (CX - X0) / Width;
            currentRow = -1 + (CY - Y0) / Width;
            return;
        }
        if (CX - X0 < 0) {
            //System.out.println("CX = " + evt.getX() + " ... CY = " + evt.getY() + "\n");
            currentColumn = -1 + (CX - X0) / Width;
            currentRow = (CY - Y0) / Width;
            return;
        }
        currentColumn = (CX - X0) / Width;
        currentRow = (CY - Y0) / Width;
        //System.out.println("==== column = " + currentColumn + " ... Row = " + currentRow + "\n");
        Point p = new Point(currentColumn, currentRow);
        if (checked.contains(p)) {
            currentColumn = -1;
            currentRow = -1;

        }
        boardPanel.repaint();
        boardPanel.validate();
    }//GEN-LAST:event_boardPanelMouseMoved

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if (checked.size() == 0) {
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn muốn xin đi lại", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            user = !user;
            checked.remove(checked.size() - 1);
            boardPanel.repaint();
        }
    }//GEN-LAST:event_backButtonActionPerformed
    /**
     * XÁC ĐỊNH ĐIỀU KIỆN THẮNG: Tìm xung quanh quân vừa đánh theo hàng ngang,
     * doc, chéo ngang chéo chính. Nếu đủ 5 quân và không bị chặn 2 đầu thì
     * thắng
     */
    public boolean isWin(boolean user) {
        int n = 6;
        /**
         * Kiểm tra số quân xung quanh quân mới đánh nếu = 4 và không bị chặn 2
         * đầu thì thắng
         */
        int ok = 0;

        //Kiểm tra có bị chặn 2 đầu không
        int soDauBiChan = 0;
        int u; //u=0 nếu là user 1; u=1 nếu là user 2
        //= (user) ? 0 : 1;// u=0 nếu là user 1; u=1 nếu là user 2
        if (startUser) {
            if (user) {
                u = 0;
            } else {
                u = 1;
            }
        } else {
            if (user) {
                u = 1;
            } else {
                u = 0;
            }
        }
        //Kiểm tra hàng ngang
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x + i, currentPoint.y);
            if (!(p.x < Size)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x - i, currentPoint.y);
            if (!(p.x >= 0)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        //Kiểm tra hàng dọc
        ok = 0;
        soDauBiChan = 0;
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x, currentPoint.y + i);
            if (!(p.y < Size)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x, currentPoint.y - i);
            if (!(p.y >= 0)) {
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        //Kiểm tra đường chéo chính
        ok = 0;
        soDauBiChan = 0;
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x + i, currentPoint.y + i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x - i, currentPoint.y - i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        //Kiểm tra đường chéo phụ
        ok = 0;
        soDauBiChan = 0;
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x + i, currentPoint.y - i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            Point p = new Point(currentPoint.x - i, currentPoint.y + i);
            if (!(p.x >= 0 && p.x < Size && p.y >= 0 && p.y < Size)) {//ô kiểm tra ra ngoài bàn cờ
                break;
            }
            if (checked.contains(p) && checked.indexOf(p) % 2 == u) {
                ok++;
            }
            if ((checked.contains(p) && checked.indexOf(p) % 2 != u) || !checked.contains(p)) {
                if (checked.contains(p) && checked.indexOf(p) % 2 != u) {
                    soDauBiChan++;
                }
                //Gặp quân của đối thủ hoặc gặp ô trống
                break;
            }
        }
        if (ok == 4 && soDauBiChan != 2) {
            return true;
        }
        return false;
    }

    public void listenSocket() {

        try {
            server = new ServerSocket(port_server1);
            Socket socket = server.accept();
            OutputStream o = socket.getOutputStream();
            out = new ObjectOutputStream(o);
            InputStream i = socket.getInputStream();
            in = new ObjectInputStream(i);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (true) {
            try {
                Vector s = null;
                try {
                    s = (Vector) in.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CaroServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.toFront();
            } catch (IOException ex) {
            }
        }
    }

    public void listenSocketGamne() {

        try {
            serverGame = new ServerSocket(port_server2);
            System.out.println("[CHU PHONG]: Da Tao Server socket");

            Socket socket = serverGame.accept();
            System.out.println("[CHU PHONG]: Da accept");

            outGame = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("[CHU PHONG]: Da tao output stream");

            inGame = new ObjectInputStream(socket.getInputStream());
            System.out.println("[CHU PHONG]: Da tao input stream");

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        while (true) {
            try {
                Point s = null;
                try {
                    s = (Point) inGame.readObject();
                    checked.add(s);
                    currentPoint = s;
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CaroServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.toFront();
                boardPanel.repaint();
                if (isWin(false)) {//quân đỏ thắng
                    JOptionPane.showMessageDialog(this, "Bạn đã thua");
                    startUser = true;
                    checked.removeAllElements();
                    boardPanel.repaint();
                }
                user = true;//quân đen thắng
                isPause = false;
                you.suspend();
                myself.resume();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void createTrayIcon() {
        //Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        final SystemTray tray = SystemTray.getSystemTray();

        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");

        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
        popup.addSeparator();
        popup.add(displayMenu);
        displayMenu.add(errorItem);
        displayMenu.add(warningItem);
        displayMenu.add(infoItem);
        displayMenu.add(noneItem);
        popup.add(exitItem);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel boardPanel;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
    private int X0 = 25;
    private int Y0 = 25;
    private int Width = 45;
    private int Size = 15;
    private int currentRow = -1;
    private int currentColumn = -1;
    private Point currentPoint = new Point();
    /**
     * true là user 1(màu đen) false là user 2(màu đỏ)
     */
    private boolean user = true;
    /**
     * Vị trí chẵn lưu các điểm đã đánh của user 1. Vị trí lẻ lưu các điểm đã
     * đánh của user 2
     */
    private Vector<Point> checked = new Vector<Point>();
    PlayNow myself;
    PlayNow you;
    ServerSocket server = null;
    Socket client = null;
    ServerSocket serverGame = null;
    Socket clientGame = null;
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    ObjectInputStream inGame = null;
    ObjectOutputStream outGame = null;
    String line;
    boolean isPause = false;
    boolean startUser = true;// quân đen đi trước

}
