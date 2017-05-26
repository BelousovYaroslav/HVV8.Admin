/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.pendings;

import hvv_admin.planner.*;
import hvv_admin.HVV_Admin;
import hvv_admin.dialogs.DlgPendingStart;
import hvv_admin.dialogs.TechProcessPlannerDlg;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class HVV_AdminPendings {
    static Logger logger = Logger.getLogger( HVV_AdminPendings.class);
    
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final HVV_Admin theApp;

    ScheduledFuture <?> m_pPendingWatcherHandle;
    
    final public TreeMap m_mapPendings;
    
    
    public HVV_AdminPendings( HVV_Admin app) {
        theApp = app;
        
        m_mapPendings = new TreeMap();
    }
    
    public void PendingsWatcherStart() {
     
        final Runnable watchDog;
        
        watchDog = new Runnable() {
            public void run() {
                if( m_mapPendings.size() > 0) {
                    if( m_mapPendings.containsKey( theApp.GetCurrentStep())) {
                        
                        Date dtPending = ( Date) m_mapPendings.get( theApp.GetCurrentStep());
                        
                        
                        GregorianCalendar gclndrNow = new GregorianCalendar();
                        gclndrNow.setTime( theApp.GetLocalDate());
                        gclndrNow.set( Calendar.SECOND, 0);
                        gclndrNow.set( Calendar.MILLISECOND, 0);
                        
                        GregorianCalendar gclndrPend = new GregorianCalendar();
                        gclndrPend.setTime( dtPending);
                        gclndrPend.set( Calendar.SECOND, 0);
                        gclndrPend.set( Calendar.MILLISECOND, 0);
                        
                        //System.out.println( "NOW:" + gclndrNow);
                        //System.out.println( "PEND:" + gclndrPend);
                        if( gclndrNow.equals( gclndrPend)) {
                            logger.info( "PEND TIME!");
                            
                            if( !theApp.IsCurrentStepInProgress()) {
                                logger.info( "PEND CLICK!");
                                
                                switch( theApp.GetCurrentStep()) {
                                    case   1:  theApp.m_pMainWnd.m_pPanel.pnlStep1.btn_01_01_Start.doClick(); break;
                                        
                                    case  21:  theApp.m_pMainWnd.m_pPanel.pnlStep2.btn_02_01_Start.doClick(); break;
                                    case  22:  theApp.m_pMainWnd.m_pPanel.pnlStep2.btn_02_02_Start.doClick(); break;
                                    case  23:  theApp.m_pMainWnd.m_pPanel.pnlStep2.btn_02_03_Start.doClick(); break;
                                    case  24:  theApp.m_pMainWnd.m_pPanel.pnlStep2.btn_02_04_Start.doClick(); break;
                                    
                                    case  41:  theApp.m_pMainWnd.m_pPanel.pnlStep3.btn_03_01_Start.doClick(); break;
                                    case  42:  theApp.m_pMainWnd.m_pPanel.pnlStep3.btn_03_02_Start.doClick(); break;
                                    case  43:  theApp.m_pMainWnd.m_pPanel.pnlStep3.btn_03_03_Start.doClick(); break;
                                    case  44:  theApp.m_pMainWnd.m_pPanel.pnlStep3.btn_03_04_Start.doClick(); break;
                                    case  45:  theApp.m_pMainWnd.m_pPanel.pnlStep3.btn_03_05_Start.doClick(); break;
                                    case  46:  theApp.m_pMainWnd.m_pPanel.pnlStep3.btn_03_06_Start.doClick(); break;

                                    case  61:  theApp.m_pMainWnd.m_pPanel.pnlStep4.btn_04_01_Start.doClick(); break;
                                    case  62:  theApp.m_pMainWnd.m_pPanel.pnlStep4.btn_04_02_Start.doClick(); break;
                                    case  63:  theApp.m_pMainWnd.m_pPanel.pnlStep4.btn_04_03_Start.doClick(); break;
                                    case  64:  theApp.m_pMainWnd.m_pPanel.pnlStep4.btn_04_04_Start.doClick(); break;
                                    case  65:  theApp.m_pMainWnd.m_pPanel.pnlStep4.btn_04_05_Start.doClick(); break;
                                    case  66:  theApp.m_pMainWnd.m_pPanel.pnlStep4.btn_04_06_Start.doClick(); break;
                                    case  67:  theApp.m_pMainWnd.m_pPanel.pnlStep4.btn_04_07_Start.doClick(); break;

                                    case  81:  theApp.m_pMainWnd.m_pPanel.pnlStep5.btn_05_01_Start.doClick(); break;
                                    case  82:  theApp.m_pMainWnd.m_pPanel.pnlStep5.btn_05_02_Start.doClick(); break;
                                    case  83:  theApp.m_pMainWnd.m_pPanel.pnlStep5.btn_05_03_Start.doClick(); break;
                                    case  84:  theApp.m_pMainWnd.m_pPanel.pnlStep5.btn_05_04_Start.doClick(); break;
                                    case  85:  theApp.m_pMainWnd.m_pPanel.pnlStep5.btn_05_05_Start.doClick(); break;

                                    case 101:  theApp.m_pMainWnd.m_pPanel.pnlStep6.btn_06_01_Start.doClick(); break;
                                    case 102:  theApp.m_pMainWnd.m_pPanel.pnlStep6.btn_06_02_Start.doClick(); break;
                                    case 103:  theApp.m_pMainWnd.m_pPanel.pnlStep6.btn_06_03_Start.doClick(); break;
                                    case 104:  theApp.m_pMainWnd.m_pPanel.pnlStep6.btn_06_04_Start.doClick(); break;

                                    case 121:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_01_Start.doClick(); break;
                                    case 122:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_02_Start.doClick(); break;
                                    case 123:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_03_Start.doClick(); break;
                                    case 124:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_04_Start.doClick(); break;
                                    case 125:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_05_Start.doClick(); break;
                                    case 126:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_06_Start.doClick(); break;
                                    case 127:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_07_Start.doClick(); break;
                                    case 128:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_08_Start.doClick(); break;
                                    case 129:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_09_Start.doClick(); break;
                                    case 130:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_10_Start.doClick(); break;
                                    case 131:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_11_Start.doClick(); break;
                                    case 132:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_12_Start.doClick(); break;
                                    case 133:  theApp.m_pMainWnd.m_pPanel.pnlStep7.btn_07_13_Start.doClick(); break;

                                    case 141:  theApp.m_pMainWnd.m_pPanel.pnlStep8.btn_08_01_Start.doClick(); break;
                                    case 142:  theApp.m_pMainWnd.m_pPanel.pnlStep8.btn_08_02_Start.doClick(); break;

                                    case 161:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_01_Start.doClick(); break;
                                    case 162:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_02_Start.doClick(); break;
                                    case 163:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_03_Start.doClick(); break;
                                    case 164:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_04_Start.doClick(); break;
                                    case 165:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_05_Start.doClick(); break;
                                    case 166:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_06_Start.doClick(); break;
                                    case 167:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_07_Start.doClick(); break;
                                    case 168:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_08_Start.doClick(); break;
                                    case 169:  theApp.m_pMainWnd.m_pPanel.pnlStep9.btn_09_09_Start.doClick(); break;

                                    case 181:  theApp.m_pMainWnd.m_pPanel.pnlStep10.btn_10_01_Start.doClick(); break;
                                    case 182:  theApp.m_pMainWnd.m_pPanel.pnlStep10.btn_10_02_Start.doClick(); break;

                                    case 201:  theApp.m_pMainWnd.m_pPanel.pnlStep11.btn_11_01_Start.doClick(); break;
                                    case 202:  theApp.m_pMainWnd.m_pPanel.pnlStep11.btn_11_02_Start.doClick(); break;
                                    case 203:  theApp.m_pMainWnd.m_pPanel.pnlStep11.btn_11_03_Start.doClick(); break;
                                    case 204:  theApp.m_pMainWnd.m_pPanel.pnlStep11.btn_11_04_Start.doClick(); break;
                                    case 205:  theApp.m_pMainWnd.m_pPanel.pnlStep11.btn_11_05_Start.doClick(); break;
                                    case 206:  theApp.m_pMainWnd.m_pPanel.pnlStep11.btn_11_06_Start.doClick(); break;

                                    case 221:  theApp.m_pMainWnd.m_pPanel.pnlStep12.btn_12_01_Start.doClick(); break;
                                    case 222:  theApp.m_pMainWnd.m_pPanel.pnlStep12.btn_12_02_Start.doClick(); break;
                                    case 223:  theApp.m_pMainWnd.m_pPanel.pnlStep12.btn_12_03_Start.doClick(); break;

                                    case 241:  theApp.m_pMainWnd.m_pPanel.pnlStep13.btn_13_01_Start.doClick(); break;
                                    case 242:  theApp.m_pMainWnd.m_pPanel.pnlStep13.btn_13_02_Start.doClick(); break;
                                    case 243:  theApp.m_pMainWnd.m_pPanel.pnlStep13.btn_13_03_Start.doClick(); break;
                                    case 244:  theApp.m_pMainWnd.m_pPanel.pnlStep13.btn_13_04_Start.doClick(); break;                                    
                                }
                                
                                theApp.m_pMainWnd.m_pPanel.SetStates();
                            }
                            else {
                                logger.info( "Этап, который нам надо отложенно запустить, уже в процессе исполнения!");
                            }
                        }
                        
                    }
                    else {
                        logger.trace( "Для текущего этапа нет записи в списке отложенных запусков");
                    }
                }
                else {
                    logger.trace( "Empty pendings map.");
                }
            }
        };
     
        m_pPendingWatcherHandle = scheduler.scheduleAtFixedRate( watchDog, 1, 1, SECONDS);
    }
    
    public void PendingsWatcherStop() {
        m_pPendingWatcherHandle.cancel(true);
    }
    
    public void CallPendSettingDialog( int nStep) {
        DlgPendingStart dlg = new DlgPendingStart( theApp, theApp.m_pMainWnd, true, nStep);
        
        Date dt = null;
        if( theApp.GetPendings().m_mapPendings.containsKey( nStep))
            dt = ( Date) m_mapPendings.get( nStep);
        
        if( dt == null)
            dlg.init( false, theApp.GetLocalDate());
        else
            dlg.init( true, dt);
        
        dlg.setLocation( theApp.m_pMainWnd.getX() + ( theApp.m_pMainWnd.getWidth()  - dlg.getWidth())  / 2,
                         theApp.m_pMainWnd.getY() + ( theApp.m_pMainWnd.getHeight() - dlg.getHeight()) / 2);
        
        dlg.setVisible( true);
        
        m_mapPendings.remove( nStep);
        if( dlg.m_bOk == true) {
            if( dlg.chkAutoStart.isSelected()) {
                GregorianCalendar gclndr = new GregorianCalendar( ( int) dlg.spnYear.getValue(),
                                                                  ( int) dlg.spnMon.getValue() - 1,
                                                                  ( int) dlg.spnDay.getValue(),
                                                                  ( int) dlg.spnHou.getValue(),
                                                                  ( int) dlg.spnMin.getValue());
                
                m_mapPendings.put( nStep, gclndr.getTime());
            }
        }
        
        theApp.m_pMainWnd.m_pPanel.SetStates();
    }
}
