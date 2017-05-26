/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.planner;

import hvv_admin.HVV_Admin;
import hvv_admin.dialogs.TechProcessPlannerDlg;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class HVV_AdminPlanner {
    final HVV_Admin theApp;
    final public LinkedList m_lstSteps;
    static Logger logger = Logger.getLogger(HVV_AdminPlanner.class);
    
    public HVV_AdminPlanner( HVV_Admin app) {
        theApp = app;
        
        m_lstSteps = new LinkedList();

        //1. Подготовка (0.5 часа)... Но это у нас якорь
        ItemPlanner item = new ItemPlanner( ( long) ( 3600 * 0.5));
        
        Date dtNow = theApp.GetLocalDate();
        dtNow = new Date( dtNow.getTime() + 5 * 60 * 1000);
        item.SetupAsAbsolute( dtNow);
        m_lstSteps.add( item);
        
        //2. Установка приборов (длительность 1 этапа = 20 минут)
        item = new ItemPlanner( ( long) ( 3600 * 20 / 60));
        item.SetupAsRelative( ( long) ( 3600 * 20 / 60));
        m_lstSteps.add( item);

        //3. Обработка в среде кислорода (длительность 2 этапа = 3.5 часа)
        item = new ItemPlanner( ( long) ( 3600 * 3.5));
        item.SetupAsRelative( ( long) ( 3600 * 3.5));
        m_lstSteps.add( item);
        
        //4. Обработка в среде кислород-неона (длительность 3 этапа = 3 часа)
        item = new ItemPlanner( ( long) ( 3600 * 3));
        item.SetupAsRelative( ( long) ( 3600 * 3));
        m_lstSteps.add( item);
        
        //5. Термообезгаживание (длительность 4 этапа = 4 часа)
        item = new ItemPlanner( ( long) ( 3600 * 4));
        item.SetupAsRelative( ( long) ( 3600 * 4));
        m_lstSteps.add( item);
        
        //6. Предварительная оценка параметров приборов (длительность 5 этапа = 28,5 часов)
        item = new ItemPlanner( ( long) ( 3600 * 28.5));
        item.SetupAsRelative( ( long) ( 3600 * 28.5));
        m_lstSteps.add( item);
        
        //7. Тренировка катода (длительность 6 этапа = 1.5 часа)
        item = new ItemPlanner( ( long) ( 3600 * 1.5));
        item.SetupAsRelative( ( long) ( 3600 * 1.5));
        m_lstSteps.add( item);
        
        //8. Обезгаживание рабочих геттеров (длительность 7 этапа = 11 часов)
        item = new ItemPlanner( ( long) ( 3600 * 11));
        item.SetupAsRelative( ( long) ( 3600 * 11));
        m_lstSteps.add( item);
        
        //9. Тренировка в тренировочной смеси (длительность 8 этапа = 2,5 часа)
        item = new ItemPlanner( ( long) ( 3600 * 2.5));
        item.SetupAsRelative( ( long) ( 3600 * 2.5));
        m_lstSteps.add( item);
        
        //10. Активация рабочих геттеров (длительность 9 этапа = 30 часа)
        item = new ItemPlanner( ( long) ( 3600 * 30));
        item.SetupAsRelative( ( long) ( 3600 * 30));
        m_lstSteps.add( item);
        
        //11. Выходная оценка параметров приборов (длительность 10 этапа = 4,5 часов)
        item = new ItemPlanner( ( long) ( 3600 * 4.5));
        item.SetupAsRelative( ( long) ( 3600 * 4.5));
        m_lstSteps.add( item);
        
        //12. Снятие непрошедших приборов (опционально) (длительность 11 этапа = 2.5 часа)
        item = new ItemPlanner( ( long) ( 3600 * 2.5));
        item.SetupAsRelative( ( long) ( 3600 * 2.5));
        m_lstSteps.add( item);
        
        //13. Завершение технологического процесса (длительность 12 этапа = 1 час)
        item = new ItemPlanner( ( long) ( 3600 * 1));
        item.SetupAsRelative( ( long) ( 3600 * 1));
        m_lstSteps.add( item);
        
    }
    
    public void ShowSetup( int nGreatStep) { //, Date dtScreenThisStep, Date dtScreenPrevStep) {
        if( nGreatStep > m_lstSteps.size()) return;
        
        ItemPlanner item;
        Date dtPrev = null, dtCurr = null;
        
        
        //FIRST
        /*
        if( theApp.GetMapSteps().containsKey( "001")) {
            TechProcessStepInfo info = ( TechProcessStepInfo) theApp.GetMapSteps().get( "001");
            if( info.GetStartDate() != null) {
                dtCurr = info.GetStartDate();
            }
            else
                dtCurr = theApp.GetLocalDate();
        }
        else {
            dtCurr = theApp.GetLocalDate();
        }
        */
        item = ( ItemPlanner) theApp.GetPlanner().m_lstSteps.get( 0);
        dtCurr = item.ProcessIncrement( dtCurr);
        
        //RUN
        for( int i=1; i<nGreatStep; i++) {
            if( theApp.GetCurrentStep() >= i * 20) {
                String strStep = String.format( "%03d", i * 20 + 1);
                if( theApp.IsStepMapContainsKey( strStep)) {
                    TechProcessStepInfo info = ( TechProcessStepInfo) theApp.GetStepInfo( strStep);
                    if( info.GetStartDate() != null) {
                        dtPrev = dtCurr;
                        dtCurr = info.GetStartDate();
                    }
                }        
            }
            else {

                item = ( ItemPlanner) theApp.GetPlanner().m_lstSteps.get( i);

                dtPrev = dtCurr;
                dtCurr = item.ProcessIncrement( dtCurr);
            }
        }
        
        
        logger.fatal( "Prev: " + dtPrev);
        logger.fatal( "Curr:" + dtCurr);
        item = ( ItemPlanner) m_lstSteps.get( nGreatStep - 1);
        
        TechProcessPlannerDlg dlg;
        switch( nGreatStep) {
            case  2: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 20); break;
            case  3: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 40); break;
            case  4: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 60); break;
            case  5: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 80); break;
            case  6: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 100); break;
            case  7: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 120); break;
            case  8: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 140); break;
            case  9: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 160); break;
            case 10: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 180); break;
            case 11: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 200); break;
            case 12: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 220); break;
            case 13: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 240); break;
            default: dlg = new TechProcessPlannerDlg( theApp, theApp.m_pMainWnd, true, 0); break;
        }
        
        if( item.GetType() == ItemPlanner.PLANNER_ABSOLUTE_TIME) {
            dlg.radAbsolute.setSelected( true);
            dlg.SetControls( true);
            
            //MAIN
            dlg.m_gdtmDate.setTime( item.m_dtStepAbsoluteDt);
            
            //ADD
            dlg.m_lRelativeMinutes = (dtCurr.getTime() - dtPrev.getTime()) / 1000 / 60;
        }
        else if( item.GetType() == ItemPlanner.PLANNER_RELATIVE_TIME) {
            dlg.radRelative.setSelected( true);
            dlg.SetControls( false);
            
            //MAIN
            dlg.m_lRelativeMinutes = item.m_lStepRelativedDurationSecs / 60;
            
            //ADD
            dlg.m_gdtmDate = new GregorianCalendar();
            dlg.m_gdtmDate.setTime( dtCurr);
        }
        else
            return;
        
        dlg.setVisible( true);
        if( dlg.m_bOk) {        
            if( dlg.radAbsolute.isSelected()) {
                item.SetupAsAbsolute( dlg.m_gdtmDate.getTime());
            }
            else if( dlg.radRelative.isSelected()) {
                item.SetupAsRelative( dlg.m_lRelativeMinutes * 60);
            }
            
            theApp.m_pMainWnd.m_pPanel.SetStates();
            
        }
    }
}
