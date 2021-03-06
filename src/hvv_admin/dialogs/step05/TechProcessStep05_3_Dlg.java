package hvv_admin.dialogs.step05;

import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.dialogs.step01.TechProcessStep01Panel;
import hvv_admin.steps.info.TechProcessStepInfo;
import org.apache.log4j.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yaroslav
 */
public class TechProcessStep05_3_Dlg extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger( TechProcessStep05_3_Dlg.class);
    final private HVV_Admin theApp;
    /**
     * Creates new form TechProcessStep2_1_Dlg
     */
    public TechProcessStep05_3_Dlg( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        theApp = app;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrGet1 = new javax.swing.ButtonGroup();
        btnGrGet2 = new javax.swing.ButtonGroup();
        btnGrGet3 = new javax.swing.ButtonGroup();
        btnGrGet4 = new javax.swing.ButtonGroup();
        btnGrGet5 = new javax.swing.ButtonGroup();
        btnGrGet6 = new javax.swing.ButtonGroup();
        btnGrGet7 = new javax.swing.ButtonGroup();
        btnGrGet8 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("5.3 Снятие печек");
        setMinimumSize(new java.awt.Dimension(640, 140));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("Снимите печки с приборов.");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 620, 30);

        btnYes.setText("Приборы готовы к заполнению рабочей смесью. Перейти к пункту 5.4");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });
        getContentPane().add(btnYes);
        btnYes.setBounds(10, 50, 620, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        //т.к. подэтап 5.3 ручной, мы просто переходим к следующему подэтапу (5.4)
        if( theApp.IsStepMapContainsKey( "083")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "083");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "");
            info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));
                
            theApp.NextCurrentStep();
            
            if( theApp.m_pMainWnd.m_pPanel.pnlStep5.chk_05_04_AutoStart.isSelected()) {
                info = new TechProcessStepInfo( theApp);
                
                info.SetStartDateAsCurrent();
                info.SetStartReportTitle( "Старт напуска рабочей смеси");
                info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
                info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
                info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));
                
                theApp.SaveStepInfo( "084", info, true);
                
                
                //theApp.m_pMainWnd.m_EmuTimer.start();
                theApp.SetCurrentStepInProgress( true);
                StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_5.4.xml");
                executor.StartThread();
            }
            
            theApp.m_ReportGenerator.Generate();
            
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();
        }
        else {
            logger.fatal( "Мы заканчиваем этап 083, а инфы на него до сих пор нет!");
        }
        dispose();
    }//GEN-LAST:event_btnYesActionPerformed

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
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep05_3_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep05_3_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep05_3_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep05_3_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TechProcessStep05_3_Dlg dialog = new TechProcessStep05_3_Dlg( null, new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup btnGrGet1;
    private javax.swing.ButtonGroup btnGrGet2;
    private javax.swing.ButtonGroup btnGrGet3;
    private javax.swing.ButtonGroup btnGrGet4;
    private javax.swing.ButtonGroup btnGrGet5;
    private javax.swing.ButtonGroup btnGrGet6;
    private javax.swing.ButtonGroup btnGrGet7;
    private javax.swing.ButtonGroup btnGrGet8;
    private javax.swing.JButton btnYes;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
