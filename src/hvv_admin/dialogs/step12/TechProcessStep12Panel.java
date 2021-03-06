/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step12;

import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.dialogs.TechProcessStepPanelTemplate;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class TechProcessStep12Panel extends TechProcessStepPanelTemplate {
    public final int MAXIMIZED_HEIGHT = 120;
    static Logger logger = Logger.getLogger(TechProcessStep12Panel.class);
    /**
     * Creates new form TechProcessStep1Panel
     */
    public TechProcessStep12Panel( HVV_Admin app, boolean bCollapsed) {
        initComponents();
        theApp = app;
        m_bCollapsed = bCollapsed;
        m_nPageNumber = 11;
        m_bFirstOfDoubleClickHeaderDateTime = false;
        
        String strTitle = theApp.GetStepName( 220);
        if( strTitle != null) lbl_12_00_Title.setText( "12. " + strTitle);
        strTitle = theApp.GetStepName( 221);
        if( strTitle != null) lbl_12_01_Title.setText( "12.1 " + strTitle);
        strTitle = theApp.GetStepName( 222);
        if( strTitle != null) lbl_12_02_Title.setText( "12.2 " + strTitle);
        strTitle = theApp.GetStepName( 223);
        if( strTitle != null) lbl_12_03_Title.setText( "12.3 " + strTitle);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_12_00_Date_start = new javax.swing.JLabel();
        lbl_12_00_Time_start = new javax.swing.JLabel();
        lbl_12_00_IcoStep = new javax.swing.JLabel();
        lbl_12_00_Title = new javax.swing.JLabel();
        lbl_12_00_Date_stop = new javax.swing.JLabel();
        lbl_12_00_Time_stop = new javax.swing.JLabel();
        lbl_12_01_Date_start = new javax.swing.JLabel();
        lbl_12_01_Time_start = new javax.swing.JLabel();
        lblAnimation_12_01 = new javax.swing.JLabel();
        lbl_12_01_Title = new javax.swing.JLabel();
        chk_12_01_AutoStart = new javax.swing.JCheckBox();
        lbl_12_01_IcoStep = new javax.swing.JLabel();
        btn_12_01_Next = new javax.swing.JButton();
        lbl_12_01_Date_stop = new javax.swing.JLabel();
        lbl_12_01_Time_stop = new javax.swing.JLabel();
        lbl_12_02_Date_start = new javax.swing.JLabel();
        lbl_12_02_Time_start = new javax.swing.JLabel();
        lblAnimation_12_02 = new javax.swing.JLabel();
        lbl_12_02_Title = new javax.swing.JLabel();
        chk_12_02_AutoStart = new javax.swing.JCheckBox();
        lbl_12_02_IcoStep = new javax.swing.JLabel();
        btn_12_02_Next = new javax.swing.JButton();
        lbl_12_02_Date_stop = new javax.swing.JLabel();
        lbl_12_02_Time_stop = new javax.swing.JLabel();
        lbl_12_03_Date_start = new javax.swing.JLabel();
        lbl_12_03_Time_start = new javax.swing.JLabel();
        lblAnimation_12_03 = new javax.swing.JLabel();
        lbl_12_03_Title = new javax.swing.JLabel();
        lbl_12_03_IcoStep = new javax.swing.JLabel();
        btn_12_03_Next = new javax.swing.JButton();
        lbl_12_03_Date_stop = new javax.swing.JLabel();
        lbl_12_03_Time_stop = new javax.swing.JLabel();
        btn_12_01_Start = new javax.swing.JButton();
        btn_12_02_Start = new javax.swing.JButton();
        btn_12_03_Start = new javax.swing.JButton();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(1000, 120));
        setMinimumSize(new java.awt.Dimension(1000, 30));
        setLayout(null);

        lbl_12_00_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_00_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_00_Date_start.setText("-");
        lbl_12_00_Date_start.setBorder(null);
        lbl_12_00_Date_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_12_00_Date_startMouseClicked(evt);
            }
        });
        add(lbl_12_00_Date_start);
        lbl_12_00_Date_start.setBounds(0, 0, 90, 25);

        lbl_12_00_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_00_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_00_Time_start.setText("-");
        lbl_12_00_Time_start.setBorder(null);
        lbl_12_00_Time_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_12_00_Time_startMouseClicked(evt);
            }
        });
        add(lbl_12_00_Time_start);
        lbl_12_00_Time_start.setBounds(90, 0, 60, 25);

        lbl_12_00_IcoStep.setBorder(null);
        lbl_12_00_IcoStep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_12_00_IcoStepMouseClicked(evt);
            }
        });
        add(lbl_12_00_IcoStep);
        lbl_12_00_IcoStep.setBounds(150, 0, 30, 25);

        lbl_12_00_Title.setText("12. Снятие непрошедших приборов (опционально)");
        lbl_12_00_Title.setBorder(null);
        lbl_12_00_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_12_00_TitleMouseClicked(evt);
            }
        });
        add(lbl_12_00_Title);
        lbl_12_00_Title.setBounds(180, 0, 470, 25);

        lbl_12_00_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_00_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_00_Date_stop.setText("-");
        lbl_12_00_Date_stop.setBorder(null);
        add(lbl_12_00_Date_stop);
        lbl_12_00_Date_stop.setBounds(840, 0, 90, 25);

        lbl_12_00_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_00_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_00_Time_stop.setText("-");
        lbl_12_00_Time_stop.setBorder(null);
        add(lbl_12_00_Time_stop);
        lbl_12_00_Time_stop.setBounds(930, 0, 60, 25);

        lbl_12_01_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_01_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_01_Date_start.setText("-");
        lbl_12_01_Date_start.setBorder(null);
        add(lbl_12_01_Date_start);
        lbl_12_01_Date_start.setBounds(0, 30, 90, 25);

        lbl_12_01_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_01_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_01_Time_start.setText("-");
        lbl_12_01_Time_start.setBorder(null);
        add(lbl_12_01_Time_start);
        lbl_12_01_Time_start.setBounds(90, 30, 60, 25);

        lblAnimation_12_01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_12_01);
        lblAnimation_12_01.setBounds(230, 30, 25, 25);

        lbl_12_01_Title.setText("12.1 Закрытие геттера");
        lbl_12_01_Title.setBorder(null);
        lbl_12_01_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_12_01_TitleMouseClicked(evt);
            }
        });
        add(lbl_12_01_Title);
        lbl_12_01_Title.setBounds(260, 30, 440, 25);

        chk_12_01_AutoStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_12_01_AutoStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chk_12_01_AutoStart.setMaximumSize(new java.awt.Dimension(30, 25));
        chk_12_01_AutoStart.setMinimumSize(new java.awt.Dimension(30, 25));
        chk_12_01_AutoStart.setPreferredSize(new java.awt.Dimension(30, 25));
        add(chk_12_01_AutoStart);
        chk_12_01_AutoStart.setBounds(700, 30, 30, 30);

        lbl_12_01_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_01_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_12_01_IcoStep);
        lbl_12_01_IcoStep.setBounds(730, 30, 30, 25);

        btn_12_01_Next.setText("Далее");
        btn_12_01_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_12_01_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_12_01_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_12_01_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_12_01_NextActionPerformed(evt);
            }
        });
        add(btn_12_01_Next);
        btn_12_01_Next.setBounds(770, 30, 60, 30);

        lbl_12_01_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_01_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_01_Date_stop.setText("-");
        lbl_12_01_Date_stop.setBorder(null);
        add(lbl_12_01_Date_stop);
        lbl_12_01_Date_stop.setBounds(840, 30, 90, 25);

        lbl_12_01_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_01_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_01_Time_stop.setText("-");
        lbl_12_01_Time_stop.setBorder(null);
        add(lbl_12_01_Time_stop);
        lbl_12_01_Time_stop.setBounds(930, 30, 60, 25);

        lbl_12_02_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_02_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_02_Date_start.setText("-");
        lbl_12_02_Date_start.setBorder(null);
        add(lbl_12_02_Date_start);
        lbl_12_02_Date_start.setBounds(0, 60, 90, 25);

        lbl_12_02_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_02_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_02_Time_start.setText("-");
        lbl_12_02_Time_start.setBorder(null);
        add(lbl_12_02_Time_start);
        lbl_12_02_Time_start.setBounds(90, 60, 60, 25);

        lblAnimation_12_02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_12_02);
        lblAnimation_12_02.setBounds(230, 60, 25, 25);

        lbl_12_02_Title.setText("12.2 Напуск азота в приборы");
        lbl_12_02_Title.setBorder(null);
        lbl_12_02_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_12_02_TitleMouseClicked(evt);
            }
        });
        add(lbl_12_02_Title);
        lbl_12_02_Title.setBounds(260, 60, 440, 25);

        chk_12_02_AutoStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_12_02_AutoStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chk_12_02_AutoStart.setMaximumSize(new java.awt.Dimension(30, 25));
        chk_12_02_AutoStart.setMinimumSize(new java.awt.Dimension(30, 25));
        chk_12_02_AutoStart.setPreferredSize(new java.awt.Dimension(30, 25));
        add(chk_12_02_AutoStart);
        chk_12_02_AutoStart.setBounds(700, 60, 30, 30);

        lbl_12_02_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_02_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_12_02_IcoStep);
        lbl_12_02_IcoStep.setBounds(730, 60, 30, 25);

        btn_12_02_Next.setText("Далее");
        btn_12_02_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_12_02_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_12_02_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_12_02_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_12_02_NextActionPerformed(evt);
            }
        });
        add(btn_12_02_Next);
        btn_12_02_Next.setBounds(770, 60, 60, 30);

        lbl_12_02_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_02_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_02_Date_stop.setText("-");
        lbl_12_02_Date_stop.setBorder(null);
        add(lbl_12_02_Date_stop);
        lbl_12_02_Date_stop.setBounds(840, 60, 90, 25);

        lbl_12_02_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_02_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_02_Time_stop.setText("-");
        lbl_12_02_Time_stop.setBorder(null);
        add(lbl_12_02_Time_stop);
        lbl_12_02_Time_stop.setBounds(930, 60, 60, 25);

        lbl_12_03_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_03_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_03_Date_start.setText("-");
        lbl_12_03_Date_start.setBorder(null);
        add(lbl_12_03_Date_start);
        lbl_12_03_Date_start.setBounds(0, 90, 90, 25);

        lbl_12_03_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_03_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_03_Time_start.setText("-");
        lbl_12_03_Time_start.setBorder(null);
        add(lbl_12_03_Time_start);
        lbl_12_03_Time_start.setBounds(90, 90, 60, 25);

        lblAnimation_12_03.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_12_03);
        lblAnimation_12_03.setBounds(230, 90, 25, 25);

        lbl_12_03_Title.setText("12.3 Снятие непрошедших приборов");
        lbl_12_03_Title.setBorder(null);
        lbl_12_03_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_12_03_TitleMouseClicked(evt);
            }
        });
        add(lbl_12_03_Title);
        lbl_12_03_Title.setBounds(260, 90, 440, 25);

        lbl_12_03_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_03_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_12_03_IcoStep);
        lbl_12_03_IcoStep.setBounds(730, 90, 30, 25);

        btn_12_03_Next.setText("Далее");
        btn_12_03_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_12_03_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_12_03_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_12_03_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_12_03_NextActionPerformed(evt);
            }
        });
        add(btn_12_03_Next);
        btn_12_03_Next.setBounds(770, 90, 60, 30);

        lbl_12_03_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_03_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_03_Date_stop.setText("-");
        lbl_12_03_Date_stop.setBorder(null);
        add(lbl_12_03_Date_stop);
        lbl_12_03_Date_stop.setBounds(840, 90, 90, 25);

        lbl_12_03_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_12_03_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_12_03_Time_stop.setText("-");
        lbl_12_03_Time_stop.setBorder(null);
        add(lbl_12_03_Time_stop);
        lbl_12_03_Time_stop.setBounds(930, 90, 60, 25);

        btn_12_01_Start.setText("Старт");
        btn_12_01_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_12_01_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_12_01_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_12_01_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_12_01_StartActionPerformed(evt);
            }
        });
        add(btn_12_01_Start);
        btn_12_01_Start.setBounds(160, 30, 60, 30);

        btn_12_02_Start.setText("Старт");
        btn_12_02_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_12_02_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_12_02_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_12_02_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_12_02_StartActionPerformed(evt);
            }
        });
        add(btn_12_02_Start);
        btn_12_02_Start.setBounds(160, 60, 60, 30);

        btn_12_03_Start.setText("Старт");
        btn_12_03_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_12_03_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_12_03_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_12_03_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_12_03_StartActionPerformed(evt);
            }
        });
        add(btn_12_03_Start);
        btn_12_03_Start.setBounds(160, 90, 60, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_12_00_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_12_00_TitleMouseClicked
        CollapseClick( m_nPageNumber);
    }//GEN-LAST:event_lbl_12_00_TitleMouseClicked

    private void lbl_12_00_IcoStepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_12_00_IcoStepMouseClicked
        CollapseClick( m_nPageNumber);
    }//GEN-LAST:event_lbl_12_00_IcoStepMouseClicked

    private void btn_12_01_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_12_01_NextActionPerformed
        DefaultNextButtonProcessing( "221", "Старт программы закрытия геттера", "Завершение программы закрытия геттера",
                                       "222", "Старт напуска азота в приборы", chk_12_02_AutoStart, logger, false);
    }//GEN-LAST:event_btn_12_01_NextActionPerformed

    private void btn_12_02_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_12_02_NextActionPerformed
        if( theApp.IsCurrentStepInProgress() == false) 
            return;
        
        //мы в процессе исполнения подэтапа XXX, переходим к следующему подэтапу XXX+1
        if( theApp.IsStepMapContainsKey( "222")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "222");

            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Завершение напуска азота в приборы");
            info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));

            theApp.NextCurrentStep();

            info = new TechProcessStepInfo( theApp);
            
            info.SetStartDateAsCurrent();
            info.SetStartReportTitle( "Начало снятия непрошедших приборов");
            info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

            theApp.SaveStepInfo( "223", info, true);

            //обновляем экран и отчёт, и только потом закроем экран диалогами обезгаживания
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_ReportGenerator.Generate();
            theApp.m_pMainWnd.m_pPanel.Reposition();

            theApp.SetCurrentStepInProgress( true);

            new Timer( 100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Timer timer = ( Timer) e.getSource();
                    timer.stop();
                    theApp.ShowDlg12_3();
                }
            }).start();

        }
        else {
            logger.fatal( "Мы заканчиваем этап 222, а инфы на него до сих пор нет!");
        }
    }//GEN-LAST:event_btn_12_02_NextActionPerformed

    private void btn_12_03_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_12_03_NextActionPerformed
        //не попадаем сюда!
    }//GEN-LAST:event_btn_12_03_NextActionPerformed

    private void btn_12_01_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_12_01_StartActionPerformed
        DefaultStartButtonProcessing( "221", "Старт программы закрытия геттера", "Завершение программы закрытия геттера",
                                       "222", "Старт напуска азота в приборы", chk_12_02_AutoStart, logger, false);
    }//GEN-LAST:event_btn_12_01_StartActionPerformed

    private void btn_12_02_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_12_02_StartActionPerformed
        if( theApp.IsCurrentStepInProgress() == true) 
            return;
        
        //мы начинаем процесс
        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
        
        info.SetStartDateAsCurrent();
        info.SetStartReportTitle( "Старт напуска азота в приборы");
        info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
        info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
        info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

        theApp.SaveStepInfo( "222", info, true);

        theApp.m_ReportGenerator.Generate();

        theApp.SetCurrentStepInProgress( true);

        //StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_" + theApp.HumanNameForStep( "133") + ".xml");
        StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_12.2.xml");
        executor.StartThread();

        SetState();
    }//GEN-LAST:event_btn_12_02_StartActionPerformed

    private void btn_12_03_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_12_03_StartActionPerformed
        //STUB ручной этап
    }//GEN-LAST:event_btn_12_03_StartActionPerformed

    private void lbl_12_00_Date_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_12_00_Date_startMouseClicked
        ProcessHeaderDateTimeClick( 12);
    }//GEN-LAST:event_lbl_12_00_Date_startMouseClicked

    private void lbl_12_00_Time_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_12_00_Time_startMouseClicked
        ProcessHeaderDateTimeClick( 12);
    }//GEN-LAST:event_lbl_12_00_Time_startMouseClicked

    private void lbl_12_01_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_12_01_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 221);
        }
    }//GEN-LAST:event_lbl_12_01_TitleMouseClicked

    private void lbl_12_02_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_12_02_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 222);
        }
    }//GEN-LAST:event_lbl_12_02_TitleMouseClicked

    private void lbl_12_03_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_12_03_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 223);
        }
    }//GEN-LAST:event_lbl_12_03_TitleMouseClicked

    public void SetState() {
        if( theApp.GetCurrentStep() / 20 == m_nPageNumber)
            m_bCollapsed = false;
        
        //12. Снятие непрошедших приборов (опционально)
        FillHeaderStepDates( "221", lbl_12_00_Date_start, lbl_12_00_Time_start, "223", lbl_12_00_Date_stop, lbl_12_00_Time_stop);
        
        lbl_12_00_IcoStep.setIcon( m_bCollapsed ? theApp.GetResources().getIconTriaRight() : theApp.GetResources().getIconTriaDown());
        
        lbl_12_00_Title.setFont( theApp.GetCurrentStep() / 20 == m_nPageNumber ? theApp.GetBoldFont() : theApp.GetUsualFont());
        lbl_12_00_Title.setEnabled( theApp.GetCurrentStep() / 20 <= m_nPageNumber);
        
        
        if( m_bCollapsed == false) {
            //12.1 Закрытие геттера
            FillAutoExecutedSubStep( 221,
                                    lbl_12_01_Date_start, lbl_12_01_Time_start, lblAnimation_12_01,
                                    btn_12_01_Start, lbl_12_01_Title, chk_12_01_AutoStart, lbl_12_01_IcoStep, btn_12_01_Next,
                                    lbl_12_01_Date_stop, lbl_12_01_Time_stop);
            
            //12.2 Напуск азота в приборы
            FillAutoExecutedSubStep( 222,
                                    lbl_12_02_Date_start, lbl_12_02_Time_start, lblAnimation_12_02,
                                    btn_12_02_Start, lbl_12_02_Title, chk_12_02_AutoStart, lbl_12_02_IcoStep, btn_12_02_Next,
                                    lbl_12_02_Date_stop, lbl_12_02_Time_stop);
            
            //12.3 Снятие непрошедших приборов
            FillAutoExecutedSubStep( 223,
                                    lbl_12_03_Date_start, lbl_12_03_Time_start, lblAnimation_12_03,
                                    btn_12_03_Start, lbl_12_03_Title, theApp.m_pMainWnd.m_pPanel.pnlStep13.chk_13_01_AutoStart, lbl_12_03_IcoStep, btn_12_03_Next,
                                    lbl_12_03_Date_stop, lbl_12_03_Time_stop);
        }
        
        btn_12_03_Start.setVisible( false);
        btn_12_03_Next.setVisible( false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_12_01_Next;
    public javax.swing.JButton btn_12_01_Start;
    private javax.swing.JButton btn_12_02_Next;
    public javax.swing.JButton btn_12_02_Start;
    private javax.swing.JButton btn_12_03_Next;
    public javax.swing.JButton btn_12_03_Start;
    public javax.swing.JCheckBox chk_12_01_AutoStart;
    public javax.swing.JCheckBox chk_12_02_AutoStart;
    public javax.swing.JLabel lblAnimation_12_01;
    public javax.swing.JLabel lblAnimation_12_02;
    public javax.swing.JLabel lblAnimation_12_03;
    public javax.swing.JLabel lbl_12_00_Date_start;
    private javax.swing.JLabel lbl_12_00_Date_stop;
    private javax.swing.JLabel lbl_12_00_IcoStep;
    public javax.swing.JLabel lbl_12_00_Time_start;
    private javax.swing.JLabel lbl_12_00_Time_stop;
    private javax.swing.JLabel lbl_12_00_Title;
    private javax.swing.JLabel lbl_12_01_Date_start;
    private javax.swing.JLabel lbl_12_01_Date_stop;
    public javax.swing.JLabel lbl_12_01_IcoStep;
    private javax.swing.JLabel lbl_12_01_Time_start;
    private javax.swing.JLabel lbl_12_01_Time_stop;
    private javax.swing.JLabel lbl_12_01_Title;
    private javax.swing.JLabel lbl_12_02_Date_start;
    private javax.swing.JLabel lbl_12_02_Date_stop;
    public javax.swing.JLabel lbl_12_02_IcoStep;
    private javax.swing.JLabel lbl_12_02_Time_start;
    private javax.swing.JLabel lbl_12_02_Time_stop;
    private javax.swing.JLabel lbl_12_02_Title;
    private javax.swing.JLabel lbl_12_03_Date_start;
    private javax.swing.JLabel lbl_12_03_Date_stop;
    public javax.swing.JLabel lbl_12_03_IcoStep;
    private javax.swing.JLabel lbl_12_03_Time_start;
    private javax.swing.JLabel lbl_12_03_Time_stop;
    private javax.swing.JLabel lbl_12_03_Title;
    // End of variables declaration//GEN-END:variables
}
