package hvv_admin.dialogs;

import hvv_admin.dialogs.step12.TechProcessStep12Panel;
import hvv_admin.dialogs.step11.TechProcessStep11Panel;
import hvv_admin.dialogs.step10.TechProcessStep10Panel;
import hvv_admin.dialogs.step09.TechProcessStep09Panel;
import hvv_admin.dialogs.step07.TechProcessStep07Panel;
import hvv_admin.dialogs.step08.TechProcessStep08Panel;
import hvv_admin.dialogs.step06.TechProcessStep06Panel;
import hvv_admin.dialogs.step04.TechProcessStep04Panel;
import hvv_admin.dialogs.step03.TechProcessStep03Panel;
import hvv_admin.dialogs.step02.TechProcessStep02Panel;
import hvv_admin.dialogs.step01.TechProcessStep01Panel;
import hvv_admin.dialogs.step05.TechProcessStep05Panel;
import hvv_admin.HVV_Admin;
import hvv_admin.dialogs.step13.TechProcessStep13Panel;
import hvv_admin.planner.ItemPlanner;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.awt.Color;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class TechProcessPanel5 extends javax.swing.JPanel {

    static Logger logger = Logger.getLogger( TechProcessPanel5.class);

    public TechProcessStep01Panel pnlStep1;
    public TechProcessStep02Panel pnlStep2;
    public TechProcessStep03Panel pnlStep3;
    public TechProcessStep04Panel pnlStep4;
    public TechProcessStep05Panel pnlStep5;
    public TechProcessStep06Panel pnlStep6;
    public TechProcessStep07Panel pnlStep7;
    public TechProcessStep08Panel pnlStep8;
    public TechProcessStep09Panel pnlStep9;
    public TechProcessStep10Panel pnlStep10;
    public TechProcessStep11Panel pnlStep11;
    public TechProcessStep12Panel pnlStep12;
    public TechProcessStep13Panel pnlStep13;
    
    private final HVV_Admin theApp;
    
    /**
     * Creates new form TechProcessPanel
     */
    public TechProcessPanel5( HVV_Admin app) {
        initComponents();
        
        theApp = app;
        
        pnlStep1 = new TechProcessStep01Panel( theApp, false);
        pnlStep2 = new TechProcessStep02Panel( theApp, true);
        pnlStep3 = new TechProcessStep03Panel( theApp, true);
        pnlStep4 = new TechProcessStep04Panel( theApp, true);
        pnlStep5 = new TechProcessStep05Panel( theApp, true);
        pnlStep6 = new TechProcessStep06Panel( theApp, true);
        pnlStep7 = new TechProcessStep07Panel( theApp, true);
        pnlStep8 = new TechProcessStep08Panel( theApp, true);
        pnlStep9 = new TechProcessStep09Panel( theApp, true);
        pnlStep10 = new TechProcessStep10Panel( theApp, true);
        pnlStep11 = new TechProcessStep11Panel( theApp, true);
        pnlStep12 = new TechProcessStep12Panel( theApp, true);
        pnlStep13 = new TechProcessStep13Panel( theApp, true);
        
        this.add( pnlStep1);
        this.add( pnlStep2);
        this.add( pnlStep3);
        this.add( pnlStep4);
        this.add( pnlStep5);
        this.add( pnlStep6);
        this.add( pnlStep7);
        this.add( pnlStep8);
        this.add( pnlStep9);
        this.add( pnlStep10);
        this.add( pnlStep11);
        this.add( pnlStep12);
        this.add( pnlStep13);
        
        
        
    }

    public void SetStates() {
        pnlStep1.SetState();
        pnlStep2.SetState();
        pnlStep3.SetState();
        pnlStep4.SetState();
        pnlStep5.SetState();
        pnlStep6.SetState();
        pnlStep7.SetState();
        pnlStep8.SetState();
        pnlStep9.SetState();
        pnlStep10.SetState();
        pnlStep11.SetState();
        pnlStep12.SetState();
        pnlStep13.SetState();
        
        boolean bPlannerFail = false;
        Date dtRunningDate = theApp.GetLocalDate();
        
        //FIRST STEP
        ItemPlanner item = ( ItemPlanner) theApp.GetPlanner().m_lstSteps.get( 0);
        dtRunningDate = item.ProcessIncrement( dtRunningDate);

        
        if( theApp.GetSettings().GetUsePlanner()) {
            for( int i=1; i<13; i++) {
                
                JLabel lblDtStart;
                JLabel lblTmStart;
                
                switch( i) {
                    case  1: lblDtStart =  pnlStep2.lbl_02_00_Date_start; lblTmStart =  pnlStep2.lbl_02_00_Time_start; break;
                    case  2: lblDtStart =  pnlStep3.lbl_03_00_Date_start; lblTmStart =  pnlStep3.lbl_03_00_Time_start; break;
                    case  3: lblDtStart =  pnlStep4.lbl_04_00_Date_start; lblTmStart =  pnlStep4.lbl_04_00_Time_start; break;
                    case  4: lblDtStart =  pnlStep5.lbl_05_00_Date_start; lblTmStart =  pnlStep5.lbl_05_00_Time_start; break;
                    case  5: lblDtStart =  pnlStep6.lbl_06_00_Date_start; lblTmStart =  pnlStep6.lbl_06_00_Time_start; break;
                    case  6: lblDtStart =  pnlStep7.lbl_07_00_Date_start; lblTmStart =  pnlStep7.lbl_07_00_Time_start; break;
                    case  7: lblDtStart =  pnlStep8.lbl_08_00_Date_start; lblTmStart =  pnlStep8.lbl_08_00_Time_start; break;
                    case  8: lblDtStart =  pnlStep9.lbl_09_00_Date_start; lblTmStart =  pnlStep9.lbl_09_00_Time_start; break;
                    case  9: lblDtStart = pnlStep10.lbl_10_00_Date_start; lblTmStart = pnlStep10.lbl_10_00_Time_start; break;
                    case 10: lblDtStart = pnlStep11.lbl_11_00_Date_start; lblTmStart = pnlStep11.lbl_11_00_Time_start; break;
                    case 11: lblDtStart = pnlStep12.lbl_12_00_Date_start; lblTmStart = pnlStep12.lbl_12_00_Time_start; break;
                    default: lblDtStart = pnlStep13.lbl_13_00_Date_start; lblTmStart = pnlStep13.lbl_13_00_Time_start; break;
                }
                
                if( theApp.GetCurrentStep() >= i * 20) {
                    lblDtStart.setForeground( null);
                    lblTmStart.setForeground( null);
                    
                    String strStep = String.format( "%03d", i * 20 + 1);
                    if( theApp.IsStepMapContainsKey( strStep)) {
                        TechProcessStepInfo info = theApp.GetStepInfo( strStep);
                        if( info.GetStartDate() != null)
                            dtRunningDate = info.GetStartDate();
                    }
                    
                }
                else {
                    
                    
                    //FUCKS HERE!
                    
                    item = ( ItemPlanner) theApp.GetPlanner().m_lstSteps.get( i);
                    
                    if( bPlannerFail != true)
                        if( item.Validation( dtRunningDate) != 0) bPlannerFail = true;
                    
                    dtRunningDate = item.ProcessIncrement( dtRunningDate);
                    
                    if( bPlannerFail) {
                        lblDtStart.setForeground( new Color( 230, 180, 180));
                        lblTmStart.setForeground( new Color( 230, 180, 180));
                        
                        lblDtStart.setText( theApp.strFormatDate( dtRunningDate));
                        lblTmStart.setText( theApp.strFormatTime( dtRunningDate));
                    }
                    else {
                        lblDtStart.setForeground( new Color( 180, 180, 180));
                        lblTmStart.setForeground( new Color( 180, 180, 180));
                        
                        lblDtStart.setText( theApp.strFormatDate( dtRunningDate));
                        lblTmStart.setText( theApp.strFormatTime( dtRunningDate));
                    }
                }
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

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(1000, 510));
        setMinimumSize(new java.awt.Dimension(1000, 510));
        setPreferredSize(new java.awt.Dimension(1000, 510));
        setLayout(null);
    }// </editor-fold>//GEN-END:initComponents

    public void Reposition() {
        int y = 0;
        int nFlowHeight;
        
        for( int i=0; i< 13; i++) {
            
            JPanel pnl = null;
            boolean bCollapsed = true;
            int nMaximizedHeight = 0;
            
            switch( i) {
                case  0: pnl =  pnlStep1; bCollapsed =  pnlStep1.m_bCollapsed; nMaximizedHeight =  pnlStep1.MAXIMIZED_HEIGHT; break;
                case  1: pnl =  pnlStep2; bCollapsed =  pnlStep2.m_bCollapsed; nMaximizedHeight =  pnlStep2.MAXIMIZED_HEIGHT; break;
                case  2: pnl =  pnlStep3; bCollapsed =  pnlStep3.m_bCollapsed; nMaximizedHeight =  pnlStep3.MAXIMIZED_HEIGHT; break;
                case  3: pnl =  pnlStep4; bCollapsed =  pnlStep4.m_bCollapsed; nMaximizedHeight =  pnlStep4.MAXIMIZED_HEIGHT; break;
                case  4: pnl =  pnlStep5; bCollapsed =  pnlStep5.m_bCollapsed; nMaximizedHeight =  pnlStep5.MAXIMIZED_HEIGHT; break;
                case  5: pnl =  pnlStep6; bCollapsed =  pnlStep6.m_bCollapsed; nMaximizedHeight =  pnlStep6.MAXIMIZED_HEIGHT; break;
                case  6: pnl =  pnlStep7; bCollapsed =  pnlStep7.m_bCollapsed; nMaximizedHeight =  pnlStep7.MAXIMIZED_HEIGHT; break;
                case  7: pnl =  pnlStep8; bCollapsed =  pnlStep8.m_bCollapsed; nMaximizedHeight =  pnlStep8.MAXIMIZED_HEIGHT; break;
                case  8: pnl =  pnlStep9; bCollapsed =  pnlStep9.m_bCollapsed; nMaximizedHeight =  pnlStep9.MAXIMIZED_HEIGHT; break;
                case  9: pnl = pnlStep10; bCollapsed = pnlStep10.m_bCollapsed; nMaximizedHeight = pnlStep10.MAXIMIZED_HEIGHT; break;
                case 10: pnl = pnlStep11; bCollapsed = pnlStep11.m_bCollapsed; nMaximizedHeight = pnlStep11.MAXIMIZED_HEIGHT; break;
                case 11: pnl = pnlStep12; bCollapsed = pnlStep12.m_bCollapsed; nMaximizedHeight = pnlStep12.MAXIMIZED_HEIGHT; break;
                case 12: pnl = pnlStep13; bCollapsed = pnlStep13.m_bCollapsed; nMaximizedHeight = pnlStep13.MAXIMIZED_HEIGHT; break;
            }
            
            if( pnl != null) {
                if( bCollapsed == true) nFlowHeight = 30;
                else nFlowHeight = nMaximizedHeight;
            
                pnl.setBounds( 0, 0, 1000, nFlowHeight);
                pnl.setLocation( 0, y);
                
                y += nFlowHeight;
            }
        }
        
        
        if( y <= 810) {
            theApp.m_pMainWnd.jScrollBar1.setEnabled( false);
            theApp.m_pMainWnd.jScrollBar1.setVisible( false);
            theApp.m_pMainWnd.jScrollBar1.setValue( 0);
            //m_pParent.m_pPanel.
                    setBounds( 2, 2, 1000, 850);
        }
        else {
            theApp.m_pMainWnd.jScrollBar1.setEnabled( true);
            theApp.m_pMainWnd.jScrollBar1.setVisible( true);
            theApp.m_pMainWnd.jScrollBar1.setMaximum( y - 810 - 30);
        }
        logger.debug( "Y=" + y);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}