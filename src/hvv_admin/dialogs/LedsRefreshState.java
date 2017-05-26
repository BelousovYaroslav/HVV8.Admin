/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs;

import HVV_Communication.client.HVV_Comm_client;
import hvv_admin.HVV_Admin;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import org.apache.log4j.Logger;
import static java.util.concurrent.TimeUnit.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author yaroslav
 */
public class LedsRefreshState {
    static final Logger logger = Logger.getLogger( LedsRefreshState.class);
    
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final HVV_Admin theApp;

    ScheduledFuture <?> lighterHandle;
        
    private final Border m_border;
    
    public LedsRefreshState( HVV_Admin app) {
        theApp = app;
        m_border = LineBorder.createBlackLineBorder();
    }

    public void lightLedsStart() {
     
        final Runnable lighter;
        final JLabel lblIconProgress[] = {
            theApp.m_pMainWnd.m_pPanel.pnlStep1.lblAnimation_01_01,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep2.lblAnimation_02_02,
            theApp.m_pMainWnd.m_pPanel.pnlStep2.lblAnimation_02_04,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_01,
            theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_02,
            theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_03,
            theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_04,
            theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_05,
            theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_06,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_01,
            theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_02,
            theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_03,
            theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_04,
            theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_05,
            theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_06,
            theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_07,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep5.lblAnimation_05_04,
            theApp.m_pMainWnd.m_pPanel.pnlStep5.lblAnimation_05_05,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep6.lblAnimation_06_04,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_01,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_02,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_03,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_04,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_05,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_06,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_07,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_08,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_09,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_10,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_11,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_12,
            theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_13,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep8.lblAnimation_08_02,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_01,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_02,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_03,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_04,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_05,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_06,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_07,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_08,
            theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_09,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep10.lblAnimation_10_02,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep11.lblAnimation_11_01,
            theApp.m_pMainWnd.m_pPanel.pnlStep11.lblAnimation_11_02,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep12.lblAnimation_12_01,
            theApp.m_pMainWnd.m_pPanel.pnlStep12.lblAnimation_12_02,
            
            theApp.m_pMainWnd.m_pPanel.pnlStep13.lblAnimation_13_01,
            theApp.m_pMainWnd.m_pPanel.pnlStep13.lblAnimation_13_03,
            theApp.m_pMainWnd.m_pPanel.pnlStep13.lblAnimation_13_04
        };
        
        lighter = new Runnable() {
            public void run() {
                
                //EXECUTOR.TO
                ImageIcon ic = null;
                String strState = "---";
                switch( theApp.GetCommA2E().GetState()) {
                    case HVV_Comm_client.STATE_DISCONNECTED:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "DIS";
                        break;
                        
                    case HVV_Comm_client.STATE_CONNECTED_OK:
                        ic = theApp.GetResources().getIconLittleBrightGreen();
                        strState = "OK";
                        break;
                        
                    case HVV_Comm_client.STATE_CONNECTED_IDLE:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "IDLE";
                        break;
                        
                    case HVV_Comm_client.STATE_CONNECTED_PROBLEMS:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "PRBL";
                        break;
                }
                
                if( ic != null) {
                    theApp.m_pMainWnd.m_lblIconExecutorTo.setBorder( null);
                    theApp.m_pMainWnd.m_lblIconExecutorTo.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconExecutorTo.setText( "");
                }
                else {
                    theApp.m_pMainWnd.m_lblIconExecutorTo.setBorder( m_border);
                    theApp.m_pMainWnd.m_lblIconExecutorTo.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconExecutorTo.setText( strState);
                }
                
                //EXECUTOR.FROM
                ic = null;
                strState = "---";
                switch( theApp.GetCommE2A().GetState()) {
                    case HVV_Communication.server.HVV_Comm_Server.STATE_DISCONNECTED:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "DIS";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_OK:
                        ic = theApp.GetResources().getIconLittleBrightGreen();
                        strState = "OK";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_IDLE:
                        ic = theApp.GetResources().getIconLittleBrightBlue();
                        strState = "IDLE";
                        break;
                        
                }
                
                if( ic != null) {
                    theApp.m_pMainWnd.m_lblIconExecutorFrom.setBorder( null);
                    theApp.m_pMainWnd.m_lblIconExecutorFrom.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconExecutorFrom.setText( "");
                }
                else {
                    theApp.m_pMainWnd.m_lblIconExecutorFrom.setBorder( m_border);
                    theApp.m_pMainWnd.m_lblIconExecutorFrom.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconExecutorFrom.setText( strState);
                }
                                        
                
                //POLLER
                ic = null;
                strState = "---";
                switch( theApp.GetCommA2P().GetState()) {
                    case HVV_Communication.server.HVV_Comm_Server.STATE_DISCONNECTED:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "DIS";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_OK:
                        ic = theApp.GetResources().getIconLittleBrightGreen();
                        strState = "OK";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_IDLE:
                        ic = theApp.GetResources().getIconLittleBrightBlue();
                        strState = "IDLE";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_PROBLEMS:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "PRBL";
                        break;
                }
                
                if( ic != null) {
                    theApp.m_pMainWnd.m_lblIconPoller.setBorder( null);
                    theApp.m_pMainWnd.m_lblIconPoller.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconPoller.setText( "");
                }
                else {
                    theApp.m_pMainWnd.m_lblIconPoller.setBorder( m_border);
                    theApp.m_pMainWnd.m_lblIconPoller.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconPoller.setText( strState);
                }
                
                
                //HV
                ic = null;
                strState = "---";
                switch( theApp.GetCommA2H().GetState()) {
                    case HVV_Communication.server.HVV_Comm_Server.STATE_DISCONNECTED:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "DIS";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_OK:
                        ic = theApp.GetResources().getIconLittleBrightGreen();
                        strState = "OK";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_IDLE:
                        ic = theApp.GetResources().getIconLittleBrightBlue();
                        strState = "IDLE";
                        break;
                        
                    case HVV_Communication.server.HVV_Comm_Server.STATE_CONNECTED_PROBLEMS:
                        ic = theApp.GetResources().getIconLittleBrightRed();
                        strState = "PRBL";
                        break;
                }
                
                if( ic != null) {
                    theApp.m_pMainWnd.m_lblIconHv.setBorder( null);
                    theApp.m_pMainWnd.m_lblIconHv.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconHv.setText( "");
                }
                else {
                    theApp.m_pMainWnd.m_lblIconHv.setBorder( m_border);
                    theApp.m_pMainWnd.m_lblIconHv.setIcon( ic);
                    theApp.m_pMainWnd.m_lblIconHv.setText( strState);
                }
                
                
                
                
                
                
                JLabel lblActiveIconForCurrentStep = null;
                String strCheckProgramName = null;
                switch( theApp.GetCurrentStep()) {
                    case   1:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep1.lblAnimation_01_01;
                        strCheckProgramName = "AdminStep_1.1.xml";
                    break;
                        
                    case  22:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep2.lblAnimation_02_02;
                        strCheckProgramName = "AdminStep_2.2.xml";
                    break;
                    case  24:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep2.lblAnimation_02_04;
                        strCheckProgramName = "AdminStep_2.4.xml";
                    break;
                        
                    case  41:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_01;
                        strCheckProgramName = "AdminStep_3.1.xml";
                    break;
                    case  42:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_02;
                        strCheckProgramName = "AdminStep_3.2.xml";
                    break;
                    case  43:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_03;
                        strCheckProgramName = "AdminStep_3.3.xml";
                    break;
                    case  44:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_04;
                        strCheckProgramName = "AdminStep_3.4.xml";
                    break;                    
                    case  45:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_05;
                        strCheckProgramName = "AdminStep_3.5.xml";
                    break;
                    case  46:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep3.lblAnimation_03_06;
                        strCheckProgramName = "AdminStep_3.6.xml";
                    break;
                    
                        
                    case  61:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_01;
                        strCheckProgramName = "AdminStep_4.1.xml";
                    break;
                    case  62:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_02;
                        strCheckProgramName = "AdminStep_4.2.xml";
                    break;
                    case  63:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_03;
                        strCheckProgramName = "AdminStep_4.3.xml";
                    break;
                    case  64:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_04;
                        strCheckProgramName = "AdminStep_4.4.xml";
                    break;                    
                    case  65:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_05;
                        strCheckProgramName = "AdminStep_4.5.xml";
                    break;
                    case  66:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_06;
                        strCheckProgramName = "AdminStep_4.6.xml";
                    break;
                    case  67:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep4.lblAnimation_04_07;
                        strCheckProgramName = "AdminStep_4.7.xml";
                    break;

                    case  84:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep5.lblAnimation_05_04;
                        strCheckProgramName = "AdminStep_5.4.xml";
                    break;
                    case  85:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep5.lblAnimation_05_05;
                        strCheckProgramName = "AdminStep_5.5.xml";
                    break;
                        
                    case  104:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep6.lblAnimation_06_04;
                        strCheckProgramName = "AdminStep_6.4.xml";
                    break;
                        
                    case  121:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_01;
                        strCheckProgramName = "AdminStep_7.1.xml";
                    break;                    
                    case  122:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_02;
                        strCheckProgramName = "AdminStep_7.2.xml";
                    break;
                    case  123:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_03;
                        strCheckProgramName = "AdminStep_7.3.xml";
                    break;                    
                    case  124:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_04;
                        strCheckProgramName = "AdminStep_7.4.xml";
                    break;
                    case  125:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_05;
                        strCheckProgramName = "AdminStep_7.5.xml";
                    break;                    
                    case  126:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_06;
                        strCheckProgramName = "AdminStep_7.6.xml";
                    break;
                    case  127:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_07;
                        strCheckProgramName = "AdminStep_7.7.xml";
                    break;                    
                    case  128:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_08;
                        strCheckProgramName = "AdminStep_7.8.xml";
                    break;
                    case  129:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_09;
                        strCheckProgramName = "AdminStep_7.9.xml";
                    break;                    
                    case  130:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_10;
                        strCheckProgramName = "AdminStep_7.10.xml";
                    break;
                    case  131:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_11;
                        strCheckProgramName = "AdminStep_7.11.xml";
                    break;                    
                    case  132:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_12;
                        strCheckProgramName = "AdminStep_7.12.xml";
                    break;
                    case  133:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep7.lblAnimation_07_13;
                        strCheckProgramName = "AdminStep_7.13.xml";
                    break;
                        
                    case  142:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep8.lblAnimation_08_02;
                        strCheckProgramName = "AdminStep_8.2.xml";
                    break;
                        
                    case  161:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_01;
                        strCheckProgramName = "AdminStep_9.1.xml";
                    break;                    
                    case  162:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_02;
                        strCheckProgramName = "AdminStep_9.2.xml";
                    break;
                    case  163:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_03;
                        strCheckProgramName = "AdminStep_9.3.xml";
                    break;                    
                    case  164:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_04;
                        strCheckProgramName = "AdminStep_9.4.xml";
                    break;
                    case  165:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_05;
                        strCheckProgramName = "AdminStep_9.5.xml";
                    break;                    
                    case  166:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_06;
                        strCheckProgramName = "AdminStep_9.6.xml";
                    break;
                    case  167:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_07;
                        strCheckProgramName = "AdminStep_9.7.xml";
                    break;                    
                    case  168:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_08;
                        strCheckProgramName = "AdminStep_9.8.xml";
                    break;
                    case  169:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep9.lblAnimation_09_09;
                        strCheckProgramName = "AdminStep_9.9.xml";
                    break;
                        
                    case  182:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep10.lblAnimation_10_02;
                        strCheckProgramName = "AdminStep_10.2.xml";
                    break;
                        
                    case  201:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep11.lblAnimation_11_01;
                        strCheckProgramName = "AdminStep_11.1.xml";
                    break;
                    case  202:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep11.lblAnimation_11_02;
                        strCheckProgramName = "AdminStep_11.2.xml";
                    break;
                        
                    case  221:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep12.lblAnimation_12_01;
                        strCheckProgramName = "AdminStep_12.1.xml";
                    break;
                    case  222:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep12.lblAnimation_12_02;
                        strCheckProgramName = "AdminStep_12.2.xml";
                    break;
                        
                    case  241:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep13.lblAnimation_13_01;
                        strCheckProgramName = "AdminStep_13.1.xml";
                    break;
                    case  243:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep13.lblAnimation_13_02;
                        strCheckProgramName = "AdminStep_13.3.xml";
                    break;
                    case  244:
                        lblActiveIconForCurrentStep = theApp.m_pMainWnd.m_pPanel.pnlStep13.lblAnimation_13_04;
                        strCheckProgramName = "AdminStep_13.4.xml";
                    break;
                }
                
                for (JLabel lblIconProgres : lblIconProgress) {
                    if( lblIconProgres == lblActiveIconForCurrentStep) {
                        if( theApp.GetCommE2A().GetExecutorState().equals( "RUN")) {
                            if( theApp.GetCommE2A().GetExecutorProgram().equals( strCheckProgramName))
                                lblIconProgres.setIcon( theApp.GetResources().getIconAnimatedProgress25());
                            else
                                lblIconProgres.setIcon( null);
                        }
                        else
                            lblIconProgres.setIcon( null);
                    }
                    else
                        lblIconProgres.setIcon( null);
                }
            }
        };
     
        lighterHandle = scheduler.scheduleAtFixedRate( lighter, 1, 1, SECONDS);
        
        /*
        scheduler.schedule( new Runnable() {
            public void run() { beeperHandle.cancel(true); }
        }, 60 * 60, SECONDS);
        */
    }
    
    public void lightLedsStop() {
        lighterHandle.cancel(true);
    }
 
}
