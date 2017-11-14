package hvv_admin.dialogs.step11;

import hvv_admin.dialogs.step01.TechProcessStep01Panel;
import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
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
public class TechProcessStep11_5_Dlg extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger( TechProcessStep11_5_Dlg.class);
    final private HVV_Admin theApp;
    /**
     * Creates new form TechProcessStep2_1_Dlg
     */
    public TechProcessStep11_5_Dlg( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        theApp = app;
        initComponents();
        
        boolean bTmp1, bTmp2;
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE1);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev1.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev1.setText( "Прибор не был установлен");
            
            txtCommentDev1.setEnabled( false);
            chkDev1.setSelected( true);
            chkDev1.setEnabled( false);
        }
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE2);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev2.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev2.setText( "Прибор не был установлен");
            
            txtCommentDev2.setEnabled( false);
            chkDev2.setSelected( true);
            chkDev2.setEnabled( false);
        }
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE3);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev3.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev3.setText( "Прибор не был установлен");
            txtCommentDev3.setEnabled( false);
            chkDev3.setSelected( true);
            chkDev3.setEnabled( false);
        }
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE4);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev4.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev4.setText( "Прибор не был установлен");
            txtCommentDev4.setEnabled( false);
            chkDev4.setSelected( true);
            chkDev4.setEnabled( false);
        }
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE5);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev5.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev5.setText( "Прибор не был установлен");
            txtCommentDev5.setEnabled( false);
            chkDev5.setSelected( true);
            chkDev5.setEnabled( false);
        }
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE6);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev6.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev6.setText( "Прибор не был установлен");
            txtCommentDev6.setEnabled( false);
            chkDev6.setSelected( true);
            chkDev6.setEnabled( false);
        }
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE7);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev7.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev7.setText( "Прибор не был установлен");
            txtCommentDev7.setEnabled( false);
            chkDev7.setSelected( true);
            chkDev7.setEnabled( false);
        }
        
        bTmp1 = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8);
        bTmp2 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE8);
        if( bTmp2 == false) {
            if( bTmp1)
                txtCommentDev8.setText( "Прибор не прошёл промежуточный контроль на этапе 6.3");
            else
                txtCommentDev8.setText( "Прибор не был установлен");
            txtCommentDev8.setEnabled( false);
            chkDev8.setSelected( true);
            chkDev8.setEnabled( false);
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

        lblTitle = new javax.swing.JLabel();
        lblPlace1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtCommentDev1 = new javax.swing.JTextArea();
        chkDev1 = new javax.swing.JCheckBox();
        lblPlace2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCommentDev2 = new javax.swing.JTextArea();
        chkDev2 = new javax.swing.JCheckBox();
        lblPlace3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtCommentDev3 = new javax.swing.JTextArea();
        chkDev3 = new javax.swing.JCheckBox();
        lblPlace4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtCommentDev4 = new javax.swing.JTextArea();
        chkDev4 = new javax.swing.JCheckBox();
        lblPlace5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtCommentDev5 = new javax.swing.JTextArea();
        chkDev5 = new javax.swing.JCheckBox();
        lblPlace6 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtCommentDev6 = new javax.swing.JTextArea();
        chkDev6 = new javax.swing.JCheckBox();
        lblPlace7 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtCommentDev7 = new javax.swing.JTextArea();
        chkDev7 = new javax.swing.JCheckBox();
        lblPlace8 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtCommentDev8 = new javax.swing.JTextArea();
        chkDev8 = new javax.swing.JCheckBox();
        btnContinue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("11.5 Оценка параметров приборов");
        setMinimumSize(new java.awt.Dimension(1100, 870));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Внесите комментарии о параметрах приборов, и отметьте приборы, которые необходимо снимать для переборки");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(10, 10, 1060, 30);

        lblPlace1.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace1.setText("1");
        lblPlace1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace1);
        lblPlace1.setBounds(10, 60, 80, 80);

        txtCommentDev1.setColumns(20);
        txtCommentDev1.setRows(5);
        txtCommentDev1.setText("Нет замечаний");
        jScrollPane1.setViewportView(txtCommentDev1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(100, 60, 810, 80);

        chkDev1.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev1.setActionCommand("");
        getContentPane().add(chkDev1);
        chkDev1.setBounds(920, 60, 170, 80);

        lblPlace2.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace2.setText("2");
        lblPlace2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace2);
        lblPlace2.setBounds(10, 150, 80, 80);

        txtCommentDev2.setColumns(20);
        txtCommentDev2.setRows(5);
        txtCommentDev2.setText("Нет замечаний");
        jScrollPane2.setViewportView(txtCommentDev2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(100, 150, 810, 80);

        chkDev2.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev2.setActionCommand("");
        getContentPane().add(chkDev2);
        chkDev2.setBounds(920, 150, 170, 80);

        lblPlace3.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace3.setText("3");
        lblPlace3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace3);
        lblPlace3.setBounds(10, 240, 80, 80);

        txtCommentDev3.setColumns(20);
        txtCommentDev3.setRows(5);
        txtCommentDev3.setText("Нет замечаний");
        jScrollPane3.setViewportView(txtCommentDev3);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(100, 240, 810, 80);

        chkDev3.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev3.setActionCommand("");
        getContentPane().add(chkDev3);
        chkDev3.setBounds(920, 240, 170, 80);

        lblPlace4.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace4.setText("4");
        lblPlace4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace4);
        lblPlace4.setBounds(10, 330, 80, 80);

        txtCommentDev4.setColumns(20);
        txtCommentDev4.setRows(5);
        txtCommentDev4.setText("Нет замечаний");
        jScrollPane4.setViewportView(txtCommentDev4);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(100, 330, 810, 80);

        chkDev4.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev4.setActionCommand("");
        getContentPane().add(chkDev4);
        chkDev4.setBounds(920, 330, 170, 80);

        lblPlace5.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace5.setText("5");
        lblPlace5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace5);
        lblPlace5.setBounds(10, 420, 80, 80);

        txtCommentDev5.setColumns(20);
        txtCommentDev5.setRows(5);
        txtCommentDev5.setText("Нет замечаний");
        jScrollPane5.setViewportView(txtCommentDev5);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(100, 420, 810, 80);

        chkDev5.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev5.setActionCommand("");
        getContentPane().add(chkDev5);
        chkDev5.setBounds(920, 420, 170, 80);

        lblPlace6.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace6.setText("6");
        lblPlace6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace6);
        lblPlace6.setBounds(10, 510, 80, 80);

        txtCommentDev6.setColumns(20);
        txtCommentDev6.setRows(5);
        txtCommentDev6.setText("Нет замечаний");
        jScrollPane6.setViewportView(txtCommentDev6);

        getContentPane().add(jScrollPane6);
        jScrollPane6.setBounds(100, 510, 810, 80);

        chkDev6.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev6.setActionCommand("");
        getContentPane().add(chkDev6);
        chkDev6.setBounds(920, 510, 170, 80);

        lblPlace7.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace7.setText("7");
        lblPlace7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace7);
        lblPlace7.setBounds(10, 600, 80, 80);

        txtCommentDev7.setColumns(20);
        txtCommentDev7.setRows(5);
        txtCommentDev7.setText("Нет замечаний");
        jScrollPane7.setViewportView(txtCommentDev7);

        getContentPane().add(jScrollPane7);
        jScrollPane7.setBounds(100, 600, 810, 80);

        chkDev7.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev7.setActionCommand("");
        getContentPane().add(chkDev7);
        chkDev7.setBounds(920, 600, 170, 80);

        lblPlace8.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace8.setText("8");
        lblPlace8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace8);
        lblPlace8.setBounds(10, 690, 80, 80);

        txtCommentDev8.setColumns(20);
        txtCommentDev8.setRows(5);
        txtCommentDev8.setText("Нет замечаний");
        jScrollPane8.setViewportView(txtCommentDev8);

        getContentPane().add(jScrollPane8);
        jScrollPane8.setBounds(100, 690, 810, 80);

        chkDev8.setText("<html>Снять прибор с дальнейшей обработки</html>");
        chkDev8.setActionCommand("");
        getContentPane().add(chkDev8);
        chkDev8.setBounds(920, 690, 170, 80);

        btnContinue.setText("Далее");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        getContentPane().add(btnContinue);
        btnContinue.setBounds(850, 790, 240, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        //т.к. этап 11.5 ручной, мы переходим к следующему подэтапу (11.6)
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE1, txtCommentDev1.getText());
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE2, txtCommentDev2.getText());
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE3, txtCommentDev3.getText());
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE4, txtCommentDev4.getText());
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE5, txtCommentDev5.getText());
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE6, txtCommentDev6.getText());
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE7, txtCommentDev7.getText());
        theApp.m_mapStep11_5_Comments.put( HVV_AdminConstants.DEVICE8, txtCommentDev8.getText());
        
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE1, !chkDev1.isSelected());
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE2, !chkDev2.isSelected());
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE3, !chkDev3.isSelected());
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE4, !chkDev4.isSelected());
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE5, !chkDev5.isSelected());
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE6, !chkDev6.isSelected());
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE7, !chkDev7.isSelected());
        theApp.m_mapStep11_5_Continue.put( HVV_AdminConstants.DEVICE8, !chkDev8.isSelected());
        
        
        if( theApp.IsStepMapContainsKey( "205")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "205");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Внесение комментариев");
            info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));
            
            //ЕСЛИ ВСЕ ПРИБОРЫ НЕ ПРОШЛИ
            boolean b_11_5_AllWentOff = true;
            if( b_11_5_AllWentOff && !chkDev1.isSelected()) b_11_5_AllWentOff = false;
            if( b_11_5_AllWentOff && !chkDev2.isSelected()) b_11_5_AllWentOff = false;
            if( b_11_5_AllWentOff && !chkDev3.isSelected()) b_11_5_AllWentOff = false;
            if( b_11_5_AllWentOff && !chkDev4.isSelected()) b_11_5_AllWentOff = false;
            if( b_11_5_AllWentOff && !chkDev5.isSelected()) b_11_5_AllWentOff = false;
            if( b_11_5_AllWentOff && !chkDev6.isSelected()) b_11_5_AllWentOff = false;
            if( b_11_5_AllWentOff && !chkDev7.isSelected()) b_11_5_AllWentOff = false;
            if( b_11_5_AllWentOff && !chkDev8.isSelected()) b_11_5_AllWentOff = false;
            
            if( b_11_5_AllWentOff) {
                theApp.SpecialCase_205_221();
                
                //переходим к этапу 12.1 закрытие геттера
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
            }
            else {
                theApp.NextCurrentStep();

                info = new TechProcessStepInfo( theApp);

                info.SetStartDateAsCurrent();
                info.SetStartReportTitle( "Начало герметизации годных приборов");
                info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
                info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
                info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

                theApp.SaveStepInfo( "206", info, true);
                theApp.SetCurrentStepInProgress( true);
            }
            
            theApp.m_ReportGenerator.Generate();
                
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();
            
            dispose();
            new Timer( 100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Timer timer = ( Timer) e.getSource();
                    timer.stop();
                    theApp.ShowDlg11_6();
                }
                
            }).start();
            
        }
        else {
            logger.fatal( "Мы заканчиваем этап 205, а инфы на него до сих пор нет!");
        }
        
        dispose();
    }//GEN-LAST:event_btnContinueActionPerformed

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
            java.util.logging.Logger.getLogger(TechProcessStep11_5_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep11_5_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep11_5_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep11_5_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                TechProcessStep11_5_Dlg dialog = new TechProcessStep11_5_Dlg( null, new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnContinue;
    private javax.swing.JCheckBox chkDev1;
    private javax.swing.JCheckBox chkDev2;
    private javax.swing.JCheckBox chkDev3;
    private javax.swing.JCheckBox chkDev4;
    private javax.swing.JCheckBox chkDev5;
    private javax.swing.JCheckBox chkDev6;
    private javax.swing.JCheckBox chkDev7;
    private javax.swing.JCheckBox chkDev8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblPlace1;
    private javax.swing.JLabel lblPlace2;
    private javax.swing.JLabel lblPlace3;
    private javax.swing.JLabel lblPlace4;
    private javax.swing.JLabel lblPlace5;
    private javax.swing.JLabel lblPlace6;
    private javax.swing.JLabel lblPlace7;
    private javax.swing.JLabel lblPlace8;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea txtCommentDev1;
    private javax.swing.JTextArea txtCommentDev2;
    private javax.swing.JTextArea txtCommentDev3;
    private javax.swing.JTextArea txtCommentDev4;
    private javax.swing.JTextArea txtCommentDev5;
    private javax.swing.JTextArea txtCommentDev6;
    private javax.swing.JTextArea txtCommentDev7;
    private javax.swing.JTextArea txtCommentDev8;
    // End of variables declaration//GEN-END:variables
}
