/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step01;

import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.dialogs.DlgPendingStart;
import hvv_admin.dialogs.TechProcessStepPanelTemplate;
import hvv_admin.planner.ItemPlanner;
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
public class TechProcessStep01Panel extends TechProcessStepPanelTemplate {
    public final int MAXIMIZED_HEIGHT = 60;
    static Logger logger = Logger.getLogger(TechProcessStep01Panel.class);
    boolean m_bIsAlreadyOneClick;
    
    /**
     * Creates new form TechProcessStep1Panel
     */
    public TechProcessStep01Panel( HVV_Admin app, boolean bCollapsed) {
        initComponents();
        theApp = app;
        m_bCollapsed = bCollapsed;
        m_bIsAlreadyOneClick = false;
        
        String strTitle = theApp.GetStepName( 0);
        if( strTitle != null) lbl_01_00_Title.setText( "1. " + strTitle);
        strTitle = theApp.GetStepName( 1);
        if( strTitle != null) lbl_01_01_Title.setText( "1.1 " + strTitle);
    }
    
    public void SetState() {
        
        //1. Заполнение рабочей камеры азотом
        boolean bStart = false;
        boolean bStop = false;
        
        if( theApp.IsStepMapContainsKey( "001")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "001");
            
            if( info.GetStartDate() != null) {
                lbl_01_00_Date_start.setText( theApp.strFormatDate( info.GetStartDate()));
                lbl_01_00_Time_start.setText( theApp.strFormatTime( info.GetStartDate()));
                bStart = true;
            }
            
            
            if( info.GetStopDate() != null) {
                lbl_01_00_Date_stop.setText( theApp.strFormatDate( info.GetStopDate()));
                lbl_01_00_Time_stop.setText( theApp.strFormatTime( info.GetStopDate()));
                bStop = true;
            }
        }
        
        if( bStart == false) {
            lbl_01_00_Date_start.setText( "-");
            lbl_01_00_Time_start.setText( "-");
        }
        
        if( bStop == false) {
            lbl_01_00_Date_stop.setText( "-");
            lbl_01_00_Time_stop.setText( "-");
        }
        
        lbl_01_00_IcoStep.setIcon( m_bCollapsed ? theApp.GetResources().getIconTriaRight() : theApp.GetResources().getIconTriaDown());
        
        lbl_01_00_Title.setFont( theApp.GetCurrentStep() < 20 ? theApp.GetBoldFont() : theApp.GetUsualFont());
        lbl_01_00_Title.setEnabled( theApp.GetCurrentStep() < 20);
        
        if( theApp.GetCurrentStep() < 20)
            m_bCollapsed = false;
        
        if( m_bCollapsed == false) {
            //1.1 Заполнение рабочей камеры азотом
            bStart = false;
            bStop = false;
        
            if( theApp.IsStepMapContainsKey( "001")) {
                TechProcessStepInfo info = theApp.GetStepInfo( "001");

                if( info.GetStartDate() != null) {
                    lbl_01_01_Date_start.setText( theApp.strFormatDate( info.GetStartDate()));
                    lbl_01_01_Time_start.setText( theApp.strFormatTime( info.GetStartDate()));
                    bStart = true;
                }


                if( info.GetStopDate() != null) {
                    lbl_01_01_Date_stop.setText( theApp.strFormatDate( info.GetStopDate()));
                    lbl_01_01_Time_stop.setText( theApp.strFormatTime( info.GetStopDate()));
                    bStop = true;
                }

                if( info.GetExecApproved())
                    lbl_01_01_IcoStep.setIcon( theApp.GetResources().getIconLittleBrightGreen());
            }

            if( bStart == false) {
                lbl_01_01_Date_start.setText( "-");
                lbl_01_01_Time_start.setText( "-");
            }

            if( bStop == false) {
                lbl_01_01_Date_stop.setText( "-");
                lbl_01_01_Time_stop.setText( "-");
            }

            /*
            if( theApp.GetCurrentStep() == 1 && theApp.m_pMainWnd.m_EmuTimer.isRunning())
                lblAnimation_01_01.setIcon( theApp.GetResources().getIconAnimatedProgress25());
            else
                lblAnimation_01_01.setIcon( null);
            */
            
            lbl_01_01_Title.setFont( theApp.GetCurrentStep() < 20 ? theApp.GetBoldFont() : theApp.GetUsualFont());
            lbl_01_01_Title.setEnabled( theApp.GetCurrentStep() < 20);

            //НАЧАЛЬНЫЙ ЭТАП
            if( theApp.GetCurrentStep() == 1) {
                btn_01_01_Start.setVisible( !theApp.IsCurrentStepInProgress());
                btn_01_01_Next.setVisible(  theApp.IsCurrentStepInProgress());
            }
            else {
                btn_01_01_Start.setVisible( false);
                btn_01_01_Next.setVisible( false);
            }
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

        lbl_01_00_Date_start = new javax.swing.JLabel();
        lbl_01_00_Time_start = new javax.swing.JLabel();
        lbl_01_00_IcoStep = new javax.swing.JLabel();
        lbl_01_00_Title = new javax.swing.JLabel();
        lbl_01_00_Date_stop = new javax.swing.JLabel();
        lbl_01_00_Time_stop = new javax.swing.JLabel();
        lbl_01_01_Date_start = new javax.swing.JLabel();
        lbl_01_01_Time_start = new javax.swing.JLabel();
        lblAnimation_01_01 = new javax.swing.JLabel();
        lbl_01_01_Title = new javax.swing.JLabel();
        lbl_01_01_IcoStep = new javax.swing.JLabel();
        btn_01_01_Start = new javax.swing.JButton();
        lbl_01_01_Date_stop = new javax.swing.JLabel();
        lbl_01_01_Time_stop = new javax.swing.JLabel();
        btn_01_01_Next = new javax.swing.JButton();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(1000, 60));
        setMinimumSize(new java.awt.Dimension(1000, 60));
        setLayout(null);

        lbl_01_00_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_00_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_00_Date_start.setText("-");
        lbl_01_00_Date_start.setBorder(null);
        add(lbl_01_00_Date_start);
        lbl_01_00_Date_start.setBounds(0, 0, 90, 25);

        lbl_01_00_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_00_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_00_Time_start.setText("-");
        lbl_01_00_Time_start.setBorder(null);
        add(lbl_01_00_Time_start);
        lbl_01_00_Time_start.setBounds(90, 0, 60, 25);

        lbl_01_00_IcoStep.setBorder(null);
        lbl_01_00_IcoStep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_01_00_IcoStepMouseClicked(evt);
            }
        });
        add(lbl_01_00_IcoStep);
        lbl_01_00_IcoStep.setBounds(150, 0, 30, 25);

        lbl_01_00_Title.setText("1. Заполнение рабочей камеры азотом");
        lbl_01_00_Title.setBorder(null);
        lbl_01_00_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_01_00_TitleMouseClicked(evt);
            }
        });
        add(lbl_01_00_Title);
        lbl_01_00_Title.setBounds(180, 0, 470, 25);

        lbl_01_00_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_00_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_00_Date_stop.setText("-");
        lbl_01_00_Date_stop.setBorder(null);
        add(lbl_01_00_Date_stop);
        lbl_01_00_Date_stop.setBounds(840, 0, 90, 25);

        lbl_01_00_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_00_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_00_Time_stop.setText("-");
        lbl_01_00_Time_stop.setBorder(null);
        add(lbl_01_00_Time_stop);
        lbl_01_00_Time_stop.setBounds(930, 0, 60, 25);

        lbl_01_01_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_01_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_01_Date_start.setText("-");
        lbl_01_01_Date_start.setBorder(null);
        lbl_01_01_Date_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_01_01_Date_startMouseClicked(evt);
            }
        });
        add(lbl_01_01_Date_start);
        lbl_01_01_Date_start.setBounds(0, 30, 90, 25);

        lbl_01_01_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_01_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_01_Time_start.setText("-");
        lbl_01_01_Time_start.setBorder(null);
        lbl_01_01_Time_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_01_01_Time_startMouseClicked(evt);
            }
        });
        add(lbl_01_01_Time_start);
        lbl_01_01_Time_start.setBounds(90, 30, 60, 25);

        lblAnimation_01_01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_01_01);
        lblAnimation_01_01.setBounds(225, 30, 30, 30);

        lbl_01_01_Title.setText("1.1 Заполнение рабочей камеры азотом");
        lbl_01_01_Title.setBorder(null);
        lbl_01_01_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_01_01_TitleMouseClicked(evt);
            }
        });
        add(lbl_01_01_Title);
        lbl_01_01_Title.setBounds(260, 30, 440, 25);

        lbl_01_01_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_01_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_01_01_IcoStep);
        lbl_01_01_IcoStep.setBounds(730, 30, 30, 30);

        btn_01_01_Start.setText("Старт");
        btn_01_01_Start.setToolTipText("");
        btn_01_01_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_01_01_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_01_01_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_01_01_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_01_01_StartActionPerformed(evt);
            }
        });
        add(btn_01_01_Start);
        btn_01_01_Start.setBounds(160, 30, 60, 30);

        lbl_01_01_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_01_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_01_Date_stop.setText("-");
        lbl_01_01_Date_stop.setBorder(null);
        add(lbl_01_01_Date_stop);
        lbl_01_01_Date_stop.setBounds(840, 30, 90, 25);

        lbl_01_01_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_01_01_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_01_01_Time_stop.setText("-");
        lbl_01_01_Time_stop.setBorder(null);
        add(lbl_01_01_Time_stop);
        lbl_01_01_Time_stop.setBounds(930, 30, 60, 25);

        btn_01_01_Next.setText("Далее");
        btn_01_01_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_01_01_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_01_01_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_01_01_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_01_01_NextActionPerformed(evt);
            }
        });
        add(btn_01_01_Next);
        btn_01_01_Next.setBounds(770, 30, 60, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_01_01_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_01_01_StartActionPerformed
        if( theApp.IsCurrentStepInProgress()) {
            //мы в процессе исполнения переходим к следующей части
            //НЕ ДОЛЖНЫ ПОПАСТЬ СЮДА
            return;   
        }
        
        //мы начинаем процесс
        TechProcessStepInfo info = new TechProcessStepInfo( theApp);

        info.SetStartDateAsCurrent();
        info.SetStartReportTitle( "Старт напуска азота");
        info.SetStartP5( theApp.GetFromPoller( "005.01"));
        info.SetStartP6( theApp.GetFromPoller( "006.01"));
        info.SetStartP7( theApp.GetFromPoller( "007.01"));

        theApp.SaveStepInfo( "001", info, true);

        theApp.SetCurrentStepInProgress( true);
        StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_1.1.xml");
        executor.StartThread();

        theApp.m_ReportGenerator.Generate();

        
        ItemPlanner item = ( ItemPlanner) theApp.GetPlanner().m_lstSteps.get( 0);
        item.SetupAsAbsolute( theApp.GetLocalDate());
        theApp.m_pMainWnd.m_pPanel.SetStates();
    }//GEN-LAST:event_btn_01_01_StartActionPerformed

    private void lbl_01_00_IcoStepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_01_00_IcoStepMouseClicked
        CollapseClick();
    }//GEN-LAST:event_lbl_01_00_IcoStepMouseClicked

    private void lbl_01_00_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_01_00_TitleMouseClicked
        CollapseClick();
    }//GEN-LAST:event_lbl_01_00_TitleMouseClicked

    private void btn_01_01_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_01_01_NextActionPerformed
        if( theApp.IsCurrentStepInProgress() == false) {
            //Мы ещё не начали этап?
            //это нам в обработку кнопки Старт, а не сюда
            return;
        }
        
        //мы в процессе исполнения переходим к следующей части
        if( theApp.IsStepMapContainsKey( "001")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "001");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Окончание напуска азота");
            info.SetStopP5( theApp.GetFromPoller( "005.01"));
            info.SetStopP6( theApp.GetFromPoller( "006.01"));
            info.SetStopP7( theApp.GetFromPoller( "007.01"));

            theApp.NextCurrentStep();

            info = new TechProcessStepInfo( theApp);

            info.SetStartDateAsCurrent();
            info.SetStartReportTitle( "");
            info.SetStartP5( theApp.GetFromPoller( "005.01"));
            info.SetStartP6( theApp.GetFromPoller( "006.01"));
            info.SetStartP7( theApp.GetFromPoller( "007.01"));

            theApp.SaveStepInfo( "021", info, true);

            theApp.m_ReportGenerator.Generate( );

            m_bCollapsed = true;
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();

            theApp.ShowDlg2_1();
        }
        else {
            logger.fatal( "Мы заканчиваем этап 001, а инфы на него до сих пор нет!");
        }
    }//GEN-LAST:event_btn_01_01_NextActionPerformed

    
    
    private void lbl_01_01_Date_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_01_01_Date_startMouseClicked
        if( m_bIsAlreadyOneClick) {
            System.err.println("double click");
            m_bIsAlreadyOneClick = false;
        }
        else {
            m_bIsAlreadyOneClick = true;
            new Timer( 500, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    m_bIsAlreadyOneClick = false;
                }
            }).start();
        }
    }//GEN-LAST:event_lbl_01_01_Date_startMouseClicked

    private void lbl_01_01_Time_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_01_01_Time_startMouseClicked
        if( m_bIsAlreadyOneClick) {
            System.err.println("double click");
            m_bIsAlreadyOneClick = false;
        }
        else {
            m_bIsAlreadyOneClick = true;
            new Timer( 500, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    m_bIsAlreadyOneClick = false;
                }
            }).start();
        }
    }//GEN-LAST:event_lbl_01_01_Time_startMouseClicked

    private void lbl_01_01_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_01_01_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 1);
        }
    }//GEN-LAST:event_lbl_01_01_TitleMouseClicked

    public void CollapseClick() {
        if( Math.floor( theApp.GetCurrentStep() / 20.) == 0.) return;
        m_bCollapsed ^= true;
        
        theApp.m_pMainWnd.m_pPanel.SetStates();
        theApp.m_pMainWnd.m_pPanel.Reposition();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_01_01_Next;
    public javax.swing.JButton btn_01_01_Start;
    public javax.swing.JLabel lblAnimation_01_01;
    public javax.swing.JLabel lbl_01_00_Date_start;
    public javax.swing.JLabel lbl_01_00_Date_stop;
    public javax.swing.JLabel lbl_01_00_IcoStep;
    public javax.swing.JLabel lbl_01_00_Time_start;
    public javax.swing.JLabel lbl_01_00_Time_stop;
    public javax.swing.JLabel lbl_01_00_Title;
    public javax.swing.JLabel lbl_01_01_Date_start;
    public javax.swing.JLabel lbl_01_01_Date_stop;
    public javax.swing.JLabel lbl_01_01_IcoStep;
    public javax.swing.JLabel lbl_01_01_Time_start;
    public javax.swing.JLabel lbl_01_01_Time_stop;
    public javax.swing.JLabel lbl_01_01_Title;
    // End of variables declaration//GEN-END:variables

}
