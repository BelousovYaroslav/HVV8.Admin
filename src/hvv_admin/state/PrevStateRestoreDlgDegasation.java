/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.state;

import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import java.util.HashMap;

/**
 *
 * @author yaroslav
 */
public class PrevStateRestoreDlgDegasation extends javax.swing.JDialog {

    public boolean m_bDrop;
    /**
     * Creates new form PrevStateRestoreDlgCommon
     */
    public PrevStateRestoreDlgDegasation(java.awt.Frame parent, boolean modal, boolean bDegasation, HVV_Admin app) {
        super(parent, modal);
        initComponents();
        m_bDrop = false;

        HashMap mapToRestore = null;
        if( bDegasation) {
            mapToRestore = app.m_mapDegassing;
        }
        else {
            mapToRestore = app.m_mapActivation;
        }
        
        if( mapToRestore != null) {
            boolean bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE1);
                if( bFlag)
                    m_chkDev1.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE1));
                else
                    m_chkDev1.setEnabled( false);
            }
            else
                m_chkDev1.setEnabled( false);
            
            bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE2);
                if( bFlag)
                    m_chkDev2.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE2));
                else
                    m_chkDev2.setEnabled( false);
            }
            else
                m_chkDev2.setEnabled( false);
            
            bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE3);
                if( bFlag)
                    m_chkDev3.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE3));
                else
                    m_chkDev3.setEnabled( false);
            }
            else
                m_chkDev3.setEnabled( false);
            
            bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE4);
                if( bFlag)
                    m_chkDev4.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE4));
                else
                    m_chkDev4.setEnabled( false);
            }
            else
                m_chkDev4.setEnabled( false);
            
            bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE5);
                if( bFlag)
                    m_chkDev5.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE5));
                else
                    m_chkDev5.setEnabled( false);
            }
            else
                m_chkDev5.setEnabled( false);
            
            bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE6);
                if( bFlag)
                    m_chkDev6.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE6));
                else
                    m_chkDev6.setEnabled( false);
            }
            else
                m_chkDev6.setEnabled( false);
            
            bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE7);
                if( bFlag)
                    m_chkDev7.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE7));
                else
                    m_chkDev7.setEnabled( false);
            }
            else
                m_chkDev7.setEnabled( false);
            
            bFlag = ( boolean) app.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8);
            if( bFlag) {
                bFlag = ( boolean) app.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE8);
                if( bFlag)
                    m_chkDev8.setSelected( mapToRestore.containsKey( HVV_AdminConstants.DEVICE8));
                else
                    m_chkDev8.setEnabled( false);
            }
            else
                m_chkDev8.setEnabled( false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        btnRestore = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        m_chkDev1 = new javax.swing.JCheckBox();
        m_chkDev2 = new javax.swing.JCheckBox();
        m_chkDev3 = new javax.swing.JCheckBox();
        m_chkDev4 = new javax.swing.JCheckBox();
        m_chkDev5 = new javax.swing.JCheckBox();
        m_chkDev6 = new javax.swing.JCheckBox();
        m_chkDev7 = new javax.swing.JCheckBox();
        m_chkDev8 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 250));
        setResizable(false);
        getContentPane().setLayout(null);

        btnRestore.setText("Восстановить");
        btnRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestoreActionPerformed(evt);
            }
        });
        getContentPane().add(btnRestore);
        btnRestore.setBounds(250, 160, 130, 40);

        jLabel1.setText("Отметьте уже обработанные приборы:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 370, 50);

        m_chkDev1.setText("1");
        getContentPane().add(m_chkDev1);
        m_chkDev1.setBounds(10, 80, 40, 22);

        m_chkDev2.setText("2");
        getContentPane().add(m_chkDev2);
        m_chkDev2.setBounds(60, 80, 40, 22);

        m_chkDev3.setText("3");
        getContentPane().add(m_chkDev3);
        m_chkDev3.setBounds(110, 80, 40, 22);

        m_chkDev4.setText("4");
        getContentPane().add(m_chkDev4);
        m_chkDev4.setBounds(160, 80, 40, 22);

        m_chkDev5.setText("5");
        getContentPane().add(m_chkDev5);
        m_chkDev5.setBounds(210, 80, 40, 22);

        m_chkDev6.setText("6");
        getContentPane().add(m_chkDev6);
        m_chkDev6.setBounds(260, 80, 40, 22);

        m_chkDev7.setText("7");
        getContentPane().add(m_chkDev7);
        m_chkDev7.setBounds(310, 80, 40, 22);

        m_chkDev8.setText("8");
        getContentPane().add(m_chkDev8);
        m_chkDev8.setBounds(350, 80, 40, 22);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestoreActionPerformed
        dispose();
    }//GEN-LAST:event_btnRestoreActionPerformed

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
            java.util.logging.Logger.getLogger(PrevStateRestoreDlgDegasation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrevStateRestoreDlgDegasation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrevStateRestoreDlgDegasation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrevStateRestoreDlgDegasation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PrevStateRestoreDlgDegasation dialog = new PrevStateRestoreDlgDegasation(new javax.swing.JFrame(), true, true, null);
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
    private javax.swing.JButton btnRestore;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JCheckBox m_chkDev1;
    public javax.swing.JCheckBox m_chkDev2;
    public javax.swing.JCheckBox m_chkDev3;
    public javax.swing.JCheckBox m_chkDev4;
    public javax.swing.JCheckBox m_chkDev5;
    public javax.swing.JCheckBox m_chkDev6;
    public javax.swing.JCheckBox m_chkDev7;
    public javax.swing.JCheckBox m_chkDev8;
    // End of variables declaration//GEN-END:variables
}
