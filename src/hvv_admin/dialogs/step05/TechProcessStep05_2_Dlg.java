package hvv_admin.dialogs.step05;

import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
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
public class TechProcessStep05_2_Dlg extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger( TechProcessStep05_2_Dlg.class);
    final private HVV_Admin theApp;
    
    public GregorianCalendar m_gdtmDate;
    
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture <?> taskHandle;
    
    boolean m_bGetterOpen;
    
    public void startPeriodicTask() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                
                
                if( m_bGetterOpen == false) {
                    //GregorianCalendar gdtmNow = ( GregorianCalendar) Calendar.getInstance();
                    //gdtmNow.add( Calendar.HOUR, -1);

                    GregorianCalendar gdtmNow = new GregorianCalendar();
                    gdtmNow.setTime( theApp.GetLocalDate());
                    
                    if( gdtmNow.after( m_gdtmDate)) {
                        m_bGetterOpen = true;
                        btnYes.setEnabled( true);

                        TechProcessStepInfo info = new TechProcessStepInfo( theApp);

                        info.SetStartDateAsCurrent();
                        info.SetStartReportTitle( "Открытие геттера");
                        info.SetStartP5( theApp.GetFromPoller( "005.01"));
                        info.SetStartP6( theApp.GetFromPoller( "006.01"));
                        info.SetStartP7( theApp.GetFromPoller( "007.01"));

                        theApp.SaveStepInfo( "082.1", info, true);

                        StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_5.2.1.xml");
                        executor.StartThread();
            
                        theApp.m_ReportGenerator.Generate();
                    }
                }
                else {
                    lblOpenGetterDateValue.setEnabled( false);
                    btnSet.setEnabled( false);
                    return;
                }
                
                
                String strGetterOpenDate;
                        
                switch( m_gdtmDate.get( Calendar.DAY_OF_WEEK)) {
                    case Calendar.SUNDAY:   strGetterOpenDate = "вс"; break;
                    case Calendar.MONDAY:   strGetterOpenDate = "пн"; break;
                    case Calendar.TUESDAY:  strGetterOpenDate = "вт"; break;
                    case Calendar.WEDNESDAY:strGetterOpenDate = "ср"; break;
                    case Calendar.THURSDAY: strGetterOpenDate = "чт"; break;
                    case Calendar.FRIDAY:   strGetterOpenDate = "пт"; break;
                    case Calendar.SATURDAY: strGetterOpenDate = "сб"; break;
                    default: strGetterOpenDate = "-"; break;
                }
                
                strGetterOpenDate += "  " + m_gdtmDate.get( Calendar.DAY_OF_MONTH);
                        
                switch( m_gdtmDate.get( Calendar.MONTH)) {
                    case Calendar.JANUARY:  strGetterOpenDate += " . янв";  break;
                    case Calendar.FEBRUARY: strGetterOpenDate += " . фев";  break;
                    case Calendar.MARCH:    strGetterOpenDate += " . мар";  break;
                    case Calendar.APRIL:    strGetterOpenDate += " . апр";  break;
                    case Calendar.MAY:      strGetterOpenDate += " . май";  break;
                    case Calendar.JUNE:     strGetterOpenDate += " . июн";  break;
                    case Calendar.JULY:     strGetterOpenDate += " . июл";  break;
                    case Calendar.AUGUST:   strGetterOpenDate += " . авг";  break;
                    case Calendar.SEPTEMBER:strGetterOpenDate += " . сен";  break;
                    case Calendar.OCTOBER:  strGetterOpenDate += " . окт";  break;
                    case Calendar.NOVEMBER: strGetterOpenDate += " . ноя";  break;
                    case Calendar.DECEMBER: strGetterOpenDate += " . дек";  break;
                    default:                strGetterOpenDate += " . -";    break;
                }
                
                strGetterOpenDate += " . " + m_gdtmDate.get( Calendar.YEAR);

                strGetterOpenDate += "  " + String.format( "%02d", m_gdtmDate.get( Calendar.HOUR_OF_DAY));
                strGetterOpenDate += ":" + String.format( "%02d", m_gdtmDate.get( Calendar.MINUTE));
                
                lblOpenGetterDateValue.setText( strGetterOpenDate);
            }
            
        };
        
        taskHandle = scheduler.scheduleAtFixedRate( task, 0, 200, TimeUnit.MILLISECONDS);
    }
    
    public void stopPeriodicTask() {
        taskHandle.cancel( true);
    }
    /**
     * Creates new form TechProcessStep2_1_Dlg
     */
    public TechProcessStep05_2_Dlg( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        theApp = app;
        initComponents();
        
        //m_gdtmDate = ( GregorianCalendar) Calendar.getInstance();
        //m_gdtmDate.add( Calendar.HOUR, 23);
        
        m_gdtmDate = new GregorianCalendar();
        m_gdtmDate.setTime( theApp.GetLocalDate());
        m_gdtmDate.add( Calendar.HOUR, 24);
        m_gdtmDate.add( Calendar.MINUTE, 30);
        
        m_bGetterOpen = false;
        
        startPeriodicTask();
        btnYes.setEnabled( false);
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
        lblOpenGetterDateValue = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();
        lblOpenGetterTitle = new javax.swing.JLabel();
        btnSet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("5.2 Термообезгаживание");
        setMaximumSize(new java.awt.Dimension(640, 180));
        setMinimumSize(new java.awt.Dimension(640, 180));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblOpenGetterDateValue.setFont(new java.awt.Font("Cantarell", 1, 20)); // NOI18N
        lblOpenGetterDateValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOpenGetterDateValue.setText("ср  12 . май . 2016  23:23");
        lblOpenGetterDateValue.setToolTipText("");
        getContentPane().add(lblOpenGetterDateValue);
        lblOpenGetterDateValue.setBounds(290, 10, 250, 40);

        jLabel2.setText("Проведите процесс термообезгаживания (включите PID-регуляцию печек)..");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 50, 620, 30);

        btnYes.setText("Термообезгаживание закончено. Далее.");
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnYesActionPerformed(evt);
            }
        });
        getContentPane().add(btnYes);
        btnYes.setBounds(10, 90, 620, 50);

        lblOpenGetterTitle.setText("Запустить программу открытия геттера:");
        lblOpenGetterTitle.setToolTipText("");
        getContentPane().add(lblOpenGetterTitle);
        lblOpenGetterTitle.setBounds(10, 10, 280, 40);

        btnSet.setText("Изменить");
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetActionPerformed(evt);
            }
        });
        getContentPane().add(btnSet);
        btnSet.setBounds(550, 10, 80, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnYesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnYesActionPerformed
        stopPeriodicTask();
        
        //т.к. подэтап 5.2 ручной, мы просто переходим к следующему подэтапу (5.3)
        if( theApp.IsStepMapContainsKey( "082")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "082");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "");
            info.SetStopP5( theApp.GetFromPoller( "005.01"));
            info.SetStopP6( theApp.GetFromPoller( "006.01"));
            info.SetStopP7( theApp.GetFromPoller( "007.01"));
            
            theApp.NextCurrentStep();
            
            info = new TechProcessStepInfo( theApp);

            info.SetStartDateAsCurrent();
            //report string смотри ниже
            info.SetStartP5( theApp.GetFromPoller( "005.01"));
            info.SetStartP6( theApp.GetFromPoller( "006.01"));
            info.SetStartP7( theApp.GetFromPoller( "007.01"));

            theApp.SaveStepInfo( "083", info, true);
                
            if( theApp.m_pMainWnd.m_pPanel.pnlStep5.chk_05_03_AutoStart.isSelected()) {
                //надо пропустить диалог "Снятие печек"
                info.SetStartReportTitle( "Снятие печек (этап пройден автоматически)");
                
                info.SetStopDateAsCurrent();
                info.SetStopReportTitle( "");
                info.SetStopP5( theApp.GetFromPoller( "005.01"));
                info.SetStopP6( theApp.GetFromPoller( "006.01"));
                info.SetStopP7( theApp.GetFromPoller( "007.01"));
                
                //переход к пункту 5.4
                theApp.NextCurrentStep();
                
                //...и если стоит галка - запуск пункта 5.4
                if( theApp.m_pMainWnd.m_pPanel.pnlStep5.chk_05_04_AutoStart.isSelected()) {
                    info = new TechProcessStepInfo( theApp);
                    
                    info.SetStartDateAsCurrent();
                    info.SetStartReportTitle( "Старт напуска рабочей смеси");
                    info.SetStartP5( theApp.GetFromPoller( "005.01"));
                    info.SetStartP6( theApp.GetFromPoller( "006.01"));
                    info.SetStartP7( theApp.GetFromPoller( "007.01"));

                    theApp.SaveStepInfo( "084", info, true);

                    theApp.SetCurrentStepInProgress( true);
                    StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_5.4.xml");
                    executor.StartThread();
                    theApp.SetCurrentStepInProgress( true);
                }
            }
            else {
                //надо вывесить диалог "Снятие печек"
                info.SetStartReportTitle( "Снятие печек (ручное подтверждение)");
                theApp.ShowDlg5_3();
            }
            
            theApp.m_ReportGenerator.Generate();
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();
            
        }
        else {
            logger.fatal( "Мы заканчиваем этап 082, а инфы на него до сих пор нет!");
        }
        dispose();
    }//GEN-LAST:event_btnYesActionPerformed

    private void btnSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetActionPerformed
        TechProcessStep05_2_DlgSet dlgSet = new TechProcessStep05_2_DlgSet( theApp, theApp.m_pMainWnd, true, ( GregorianCalendar) m_gdtmDate.clone());
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    
        //dlgSet.setLocation( ( rect.width - dlgSet.getWidth()) / 2, ( rect.height - dlgSet.getHeight()) / 2);
        dlgSet.setLocation( ( rect.width - dlgSet.getWidth()) / 2, this.getY() - dlgSet.getHeight() - 10);
        dlgSet.setVisible( true);
        m_gdtmDate = ( GregorianCalendar) dlgSet.m_gdtmDate.clone();
    }//GEN-LAST:event_btnSetActionPerformed

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
            java.util.logging.Logger.getLogger(TechProcessStep05_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep05_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep05_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep05_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TechProcessStep05_2_Dlg dialog = new TechProcessStep05_2_Dlg( null, new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSet;
    private javax.swing.JButton btnYes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblOpenGetterDateValue;
    private javax.swing.JLabel lblOpenGetterTitle;
    // End of variables declaration//GEN-END:variables
}
