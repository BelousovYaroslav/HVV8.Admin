package hvv_admin.dialogs.step11;

import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
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
public class TechProcessStep11_6_Dlg extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger( TechProcessStep11_6_Dlg.class);
    final private HVV_Admin theApp;
    /**
     * Creates new form TechProcessStep2_1_Dlg
     */
    public TechProcessStep11_6_Dlg( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        theApp = app;
        initComponents();
        
        //ОПРЕДЕЛИМ ЕСТЬ ЛИ СОШЕДШИЕ ПРИБОРЫ
        boolean b_11_6_HaveBad = false;
        for( int i=0; i<8; i++) {
            boolean bBad = ( boolean) theApp.m_mapDevicePresence.get(i) &&
                    !( boolean) theApp.m_mapStep11_5_Continue.get(i);
            if( bBad) {
                b_11_6_HaveBad = true;
                break;
            }
        }

        if( b_11_6_HaveBad) {
            btnYes.setText( "<html>Успешно прошедшие приборы загерметизированы и сняты.<br><br>На стенде остались приборы на переборку. Перейти к пункту 12.1</html>");
        }
        else {
            btnYes.setText( "<html>Успешно прошедшие приборы загерметизированы и сняты.<br><br>Сошедших в этом запуске нет. Перейти к пункту 13.4</html>");
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

        jLabel1 = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("11.6 Герметизация годных приборов");
        setMaximumSize(new java.awt.Dimension(800, 170));
        setMinimumSize(new java.awt.Dimension(800, 170));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setText("Загерметизируйте и снимите со стенда успешно прошедшие обработку приборы.");
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 780, 30);

        btnYes.setText("<html>Успешно прошедшие приборы загерметизированы и сняты.<br><br>На стенде остались приборы на переборку. Перейти к пункту 12.1</html>");
        btnYes.setToolTipText("");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });
        getContentPane().add(btnYes);
        btnYes.setBounds(10, 50, 780, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        if( theApp.IsStepMapContainsKey( "206")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "206");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Завершение герметизации годных приборов");
            info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));
                    
            //ОПРЕДЕЛИМ ЕСТЬ ЛИ СОШЕДШИЕ ПРИБОРЫ
            boolean b_11_6_HaveBad = false;
            for( int i=0; i<8; i++) {
                boolean bBad = ( boolean) theApp.m_mapDevicePresence.get(i) &&
                        !( boolean) theApp.m_mapStep11_5_Continue.get(i);
                if( bBad) {
                    b_11_6_HaveBad = true;
                    break;
                }
            }
            
            if( b_11_6_HaveBad) {
                //У НАС ЕСТЬ НЕПРОШЕДШИЕ ПРИБОРЫ - будем их снимать - идём в пункт 12.1
            
                theApp.NextCurrentStep();

                if( theApp.m_pMainWnd.m_pPanel.pnlStep12.chk_12_01_AutoStart.isSelected()) {
                    info = new TechProcessStepInfo( theApp);

                    info.SetStartDateAsCurrent();
                    info.SetStartReportTitle( "Старт программы закрытия геттера");
                    info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
                    info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
                    info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

                    theApp.SaveStepInfo( "221", info, true);
                    
                    theApp.SetCurrentStepInProgress( true);
                    StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_12.1.xml");
                    executor.StartThread();
                    
                }
                else
                    theApp.SetCurrentStepInProgress( false);
            }
            else {
                //У НАС НЕТ СОШЕДШИХ ПРИБОРОВ, снимать их не надо, идём в пункт 13.4
                theApp.SpecialCase_206_244();
                
                //переходим к пункту 13.4 (автомат)
                if( theApp.m_pMainWnd.m_pPanel.pnlStep13.chk_13_04_AutoStart.isSelected()) {
                    info = new TechProcessStepInfo( theApp);
                    
                    info.SetStartDateAsCurrent();
                    info.SetStartReportTitle( "Старт программы откачки смеси с геттера");
                    info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
                    info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
                    info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

                    theApp.SaveStepInfo( "244", info, true);

                    theApp.SetCurrentStepInProgress( true);
                    StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_13.4.xml");
                    executor.StartThread();
                }
                else {
                    theApp.SetCurrentStepInProgress( false);
                }
                
            }
            
            theApp.m_ReportGenerator.Generate();
                
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();
        }
        else {
            logger.fatal( "Мы заканчиваем этап 206, а инфы на него до сих пор нет!");
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
            java.util.logging.Logger.getLogger(TechProcessStep11_6_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep11_6_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep11_6_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep11_6_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                TechProcessStep11_6_Dlg dialog = new TechProcessStep11_6_Dlg( null, new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnYes;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
