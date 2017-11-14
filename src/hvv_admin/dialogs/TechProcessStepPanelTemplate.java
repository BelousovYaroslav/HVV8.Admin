/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs;

import hvv_admin.steps.info.TechProcessStepInfo;
import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.Timer;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public abstract class TechProcessStepPanelTemplate extends javax.swing.JPanel {
    public HVV_Admin theApp;
    public boolean m_bCollapsed;
    public int m_nPageNumber;
    
    public boolean m_bFirstOfDoubleClickHeaderDateTime;
    
    private Timer m_tmDoubleClick;
    
    public void CollapseClick( int nPanel) {
        if( theApp.GetCurrentStep() / 20 == nPanel) return;
        m_bCollapsed ^= true;
        theApp.m_pMainWnd.m_pPanel.SetStates();
        theApp.m_pMainWnd.m_pPanel.Reposition();
    }
    
    public void FillHeaderStepDates( String strStepFirst, JLabel lbDtStart, JLabel lbTmStart, String strStepLast, JLabel lbDtStop, JLabel lbTmStop) {
        boolean bStart = false;
        if( theApp.IsStepMapContainsKey( strStepFirst)) {
            TechProcessStepInfo info = theApp.GetStepInfo( strStepFirst);
            
            if( info.GetStartDate() != null) {
                lbDtStart.setText( theApp.strFormatDate( info.GetStartDate()));
                lbTmStart.setText( theApp.strFormatTime( info.GetStartDate()));
                bStart = true;
            }
        }
        
        if( bStart == false) {
            lbDtStart.setText( "-");
            lbTmStart.setText( "-");
        }
        
        
        boolean bStop = false;
        if( theApp.IsStepMapContainsKey( strStepLast)) {
            TechProcessStepInfo info = theApp.GetStepInfo( strStepLast);
            
            if( info.GetStopDate() != null) {
                lbDtStop.setText( theApp.strFormatDate( info.GetStopDate()));
                lbTmStop.setText( theApp.strFormatTime( info.GetStopDate()));
                bStop = true;
            }
        }
        
        if( bStop == false) {
            lbDtStop.setText( "-");
            lbTmStop.setText( "-");
        }
    }
    
    public void FillStepDates( String strStep, JLabel lbDtStart, JLabel lbTmStart, JLabel lbDtStop, JLabel lbTmStop) {
        boolean bStart = false;
        boolean bStop = false;
        
        if( theApp.IsStepMapContainsKey( strStep)) {
            TechProcessStepInfo info = theApp.GetStepInfo( strStep);
            
            if( info.GetStartDate() != null) {
                lbDtStart.setForeground( null); lbDtStart.setText( theApp.strFormatDate( info.GetStartDate()));
                lbTmStart.setForeground( null); lbTmStart.setText( theApp.strFormatTime( info.GetStartDate()));
                bStart = true;
            }
            
            
            if( info.GetStopDate() != null) {
                lbDtStop.setText( theApp.strFormatDate( info.GetStopDate()));
                lbTmStop.setText( theApp.strFormatTime( info.GetStopDate()));
                bStop = true;
            }
        }
        
        if( bStart == false) {
            int nStep = -1;
            try {
                nStep = Integer.parseInt( strStep);
            }
            catch( NumberFormatException ex) {
            }
            
            if( theApp.GetPendings().m_mapPendings.containsKey( nStep)) {
                Date dtPending = ( Date) theApp.GetPendings().m_mapPendings.get( nStep);
                lbDtStart.setForeground( Color.BLUE); lbDtStart.setText( theApp.strFormatDate( dtPending));
                lbTmStart.setForeground( Color.BLUE); lbTmStart.setText( theApp.strFormatTime( dtPending));
            }
            else
                nStep = -1;
            
            if( nStep == -1) {
                lbDtStart.setForeground( null); lbDtStart.setText( "-");
                lbTmStart.setForeground( null); lbTmStart.setText( "-");
            }
        }
        
        if( bStop == false) {
            lbDtStop.setText( "-");
            lbTmStop.setText( "-");
        }
    }
    
    public void FillAutoExecutedSubStep( int nStepId,
                                        JLabel lblDtStart, JLabel lblTmStart, JLabel lblIconProcess,
                                        JButton btnStart, JLabel lblDtTitle, JCheckBox chkAuto, JLabel lblIco, JButton btnNext,
                                        JLabel lblDtStop, JLabel lblTmStop) {
        
        String strStepId = String.format( "%03d", nStepId);
        FillStepDates( strStepId, lblDtStart, lblTmStart, lblDtStop, lblTmStop);

        /*
        if( theApp.GetCurrentStep() == nStepId && theApp.m_pMainWnd.m_EmuTimer.isRunning())
            lblIconProcess.setIcon( theApp.GetResources().getIconAnimatedProgress25());
        else
            lblIconProcess.setIcon( null);
        */
        
        lblDtTitle.setFont( theApp.GetCurrentStep() == nStepId ? theApp.GetBoldFont() : theApp.GetUsualFont());
        lblDtTitle.setEnabled( theApp.GetCurrentStep() <= nStepId);

        if( chkAuto != null)
            chkAuto.setEnabled( theApp.GetCurrentStep() < nStepId);
            
        if( theApp.IsStepMapContainsKey( strStepId)) {
            TechProcessStepInfo info = theApp.GetStepInfo(strStepId);
            lblIco.setIcon( info.GetExecApproved() ?  theApp.GetResources().getIconLittleBrightGreen() : null);
        }
        else
            lblIco.setIcon( null);

        //АВТО-POSSIBLE ПОДЭТАП
        if( theApp.GetCurrentStep() == nStepId) {
            btnStart.setVisible( !theApp.IsCurrentStepInProgress());
            btnNext.setVisible( theApp.IsCurrentStepInProgress());
        }
        else {
            btnStart.setVisible( false);
            btnNext.setVisible( false);
        }
    }
    
    public abstract void SetState();
    
    public void DefaultStartButtonProcessing( String strCurrentStep, String strCurrentStepStartTitle, String strCurrentStepStopTitle,
                                                String strNextStep, String strNextStepStartTitle,
                                                JCheckBox chkNextStep, Logger logger, boolean bLastSubStep) {
        
        //мы начинаем процесс
        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
        info.SetStartDateAsCurrent();
        info.SetStartReportTitle( strCurrentStepStartTitle);
        info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
        info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
        info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

        theApp.SaveStepInfo( strCurrentStep, info, true);

        theApp.m_ReportGenerator.Generate();

        //theApp.m_pMainWnd.m_EmuTimer.start();
        theApp.SetCurrentStepInProgress( true);
        StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_" + theApp.HumanNameForStep( strCurrentStep) + ".xml");
        executor.StartThread();

        SetState();
    }
    
    public void DefaultNextButtonProcessing( String strCurrentStep, String strCurrentStepStartTitle, String strCurrentStepStopTitle,
                                                String strNextStep, String strNextStepStartTitle,
                                                JCheckBox chkNextStep, Logger logger, boolean bLastSubStep) {
        
        //мы в процессе исполнения подэтапа XXX, переходим к следующему подэтапу XXX+1
        //theApp.m_pMainWnd.m_EmuTimer.stop();
        if( theApp.IsStepMapContainsKey( strCurrentStep)) {
            TechProcessStepInfo info = theApp.GetStepInfo( strCurrentStep);

            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( strCurrentStepStopTitle);
            info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));

            theApp.NextCurrentStep();
            
            if( chkNextStep != null && chkNextStep.isSelected()) {
                info = new TechProcessStepInfo( theApp);
                
                info.SetStartDateAsCurrent();
                info.SetStartReportTitle( strNextStepStartTitle);
                info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
                info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
                info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

                //theApp.m_pMainWnd.m_EmuTimer.start();
                theApp.SetCurrentStepInProgress( true);
                StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_" + theApp.HumanNameForStep( strNextStep) + ".xml");
                executor.StartThread();
            }

            if( bLastSubStep)
                theApp.m_pMainWnd.m_pPanel.SetStates();
            else
                SetState();
            
            theApp.m_ReportGenerator.Generate();
            theApp.SaveStepInfo( strNextStep, info, true);
            
            theApp.m_pMainWnd.m_pPanel.Reposition();
        }
        else {
            logger.fatal( "Мы заканчиваем этап " + strCurrentStep + ", а инфы на него до сих пор нет!");
        }
    }
    
    public void ProcessHeaderDateTimeClick( int nGreatStep) { //, Date dtScreenThisStep, Date dtScreenPrevStep) {
        if( ( m_tmDoubleClick != null && m_tmDoubleClick.isRunning()) || m_bFirstOfDoubleClickHeaderDateTime == true) {
            m_tmDoubleClick.stop();
            m_bFirstOfDoubleClickHeaderDateTime = false;
            theApp.GetPlanner().ShowSetup( nGreatStep);//, dtScreenThisStep, dtScreenPrevStep);
        }
        else {
            m_bFirstOfDoubleClickHeaderDateTime = true;
            m_tmDoubleClick = new Timer( 500, new ActionListener() {
                

                @Override
                public void actionPerformed(ActionEvent e) {
                    m_bFirstOfDoubleClickHeaderDateTime = false;
                }
                
            });
            m_tmDoubleClick.setRepeats( false);
            m_tmDoubleClick.start();
        }
    }
}
