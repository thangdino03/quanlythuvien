/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package quanlythuvien.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import quanlythuvien.dao.NguoiDungDAO;
import quanlythuvien.entity.NguoiDung;
import quanlythuvien.utils.Auth;
import quanlythuvien.utils.MsgBox;
import quanlythuvien.utils.XImage;

/**
 *
 * @author Linh
 */
public class NhoMatKhau extends javax.swing.JDialog {

    ThuVienMainJFrame tvfr;
    List<String> listUserName = new ArrayList<>();
    NguoiDungDAO dao = new NguoiDungDAO();
    JButton btnUser;

    public NhoMatKhau(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tvfr = (ThuVienMainJFrame) parent;
        init();
    }

    void init() {
        this.setLocation(399, 101);
        this.setTitle("Đăng nhập");
        this.setIconImage(XImage.getAppIcon());
        btnFormDangNhap.setContentAreaFilled(false);
        btnFormDangKy.setContentAreaFilled(false);
        readLoginInfo();
        addBtnTaiKhoan();
    }

    void addBtnTaiKhoan() {
        // Tạo các button và thêm vào panel
        for (String userName : listUserName) {
            btnUser = new JButton(userName);
            btnUser.setBackground(Color.YELLOW);
            btnUser.setPreferredSize(new Dimension(230, 50));
            btnUser.setFont(new Font("Arial", Font.BOLD, 15));
            btnUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dangNhap();
                }
            });
            pnlBtnTaiKhoan.add(btnUser);
        }
        btnUser = new JButton("XÓA TẤT CẢ");
        btnUser.setBackground(Color.red);
        btnUser.setPreferredSize(new Dimension(230, 50));
        btnUser.setFont(new Font("Arial", Font.BOLD, 15));
        btnUser.setForeground(Color.WHITE);
        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MsgBox.confirm(NhoMatKhau.this, "Bạn có chắc chắn xoá!")) {
                    clearLoginInfo();
                    NhoMatKhau.this.dispose();
                }
            }
        });
        pnlBtnTaiKhoan.add(btnUser);
    }

    void dangNhap() {
        NguoiDung nd = dao.selectById(btnUser.getText());
        if (nd != null) {
            Auth.user = dao.selectById(btnUser.getText());
            this.dispose();
            if (Auth.isManager() || Auth.isLibrarian()) {
                new ThuVienQuanLyJFrame().setVisible(true);
            } else {
                new ThuVienUserJFrame().setVisible(true);
            }
        } else {
            MsgBox.alert(this, "Tài khoản này đã bị xóa!");
        }
    }

    public void readLoginInfo() {
        String filePath = "src\\main\\resources\\Saved_User_Infor\\logininfo.txt";
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            // Đọc từng dòng trong file
            while ((line = reader.readLine()) != null) {
                listUserName.add(line);
            }
            System.out.println(listUserName.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearLoginInfo() {
        String filePath = "src\\main\\resources\\Saved_User_Infor\\logininfo.txt";
        File file = new File(filePath);

        // Kiểm tra xem file đã tồn tại chưa
        if (file.exists()) {
            if (file.delete()) {
                MsgBox.alert(this, "Xóa thành công");
            } else {
                MsgBox.alert(this, "Xóa không thành công");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        scrPnl = new javax.swing.JScrollPane();
        pnlBtnTaiKhoan = new javax.swing.JPanel();
        btnFormDangNhap = new javax.swing.JButton();
        btnFormDangKy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrPnl.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        pnlBtnTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));
        pnlBtnTaiKhoan.setMaximumSize(new java.awt.Dimension(288, 2147483647));
        pnlBtnTaiKhoan.setLayout(new java.awt.GridLayout(0, 1));
        scrPnl.setViewportView(pnlBtnTaiKhoan);

        getContentPane().add(scrPnl, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 140, 290, 200));

        btnFormDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormDangNhapActionPerformed(evt);
            }
        });
        getContentPane().add(btnFormDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 380, 310, 60));

        btnFormDangKy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFormDangKyActionPerformed(evt);
            }
        });
        getContentPane().add(btnFormDangKy, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 460, 310, 50));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quanlythuvien/icon/bgrRememberMe.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFormDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormDangNhapActionPerformed
        this.dispose();
        new DangNhapJDialog(tvfr, true).setVisible(true);
    }//GEN-LAST:event_btnFormDangNhapActionPerformed

    private void btnFormDangKyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFormDangKyActionPerformed
        this.dispose();
        tvfr.openDangKy();
    }//GEN-LAST:event_btnFormDangKyActionPerformed

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
            java.util.logging.Logger.getLogger(NhoMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NhoMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NhoMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NhoMatKhau.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NhoMatKhau dialog = new NhoMatKhau(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFormDangKy;
    private javax.swing.JButton btnFormDangNhap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlBtnTaiKhoan;
    private javax.swing.JScrollPane scrPnl;
    // End of variables declaration//GEN-END:variables
}
