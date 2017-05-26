/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.state;

import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.steps.info.GettersActivationProgram;
import hvv_admin.steps.info.GettersActivationProgramStep;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class HVV_StateKeeper {
    
    static Logger logger = Logger.getLogger(HVV_StateKeeper.class);
    
    HVV_Admin theApp;
    String m_strStateKeepFileName;
    
    public boolean m_bDropReadState;
    
    public HVV_StateKeeper( HVV_Admin app) {
        theApp = app;
        m_strStateKeepFileName = app.GetAMSRoot() + "/etc/stateKeeper";
        m_bDropReadState = false;
    }
    
    private void SaveStandardPoint( ObjectOutputStream oos, String strStep1) throws IOException {
        
        oos.writeObject( strStep1);
        
        TechProcessStepInfo info = theApp.GetStepInfo( strStep1);
        if( info != null) {
            oos.writeObject( info.GetStartDate());
            oos.writeObject( info.GetStartReportTitle());
            oos.writeObject( info.GetStartP5());
            oos.writeObject( info.GetStartP6());
            oos.writeObject( info.GetStartP7());

            oos.writeObject( info.GetStopDate());
            oos.writeObject( info.GetStopReportTitle());
            oos.writeObject( info.GetStopP5());
            oos.writeObject( info.GetStopP6());
            oos.writeObject( info.GetStopP7());
        }
        else {
            oos.writeObject( null);
            oos.writeObject( null);
            oos.writeObject( null);
            oos.writeObject( null);
            oos.writeObject( null);

            oos.writeObject( null);
            oos.writeObject( null);
            oos.writeObject( null);
            oos.writeObject( null);
            oos.writeObject( null);
        }
    }
    
    private TechProcessStepInfo ReadStandardPoint( ObjectInputStream ois) throws IOException {
        
        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
        
        try {    
            
            
            info.SetStartDate( ( Date) ois.readObject());
            info.SetStartReportTitle( ( String) ois.readObject());
            info.SetStartP5( ( Double) ois.readObject());
            info.SetStartP6( ( Double) ois.readObject());
            info.SetStartP7( ( Double) ois.readObject());
            
            info.SetStopDate( ( Date) ois.readObject());
            info.SetStopReportTitle( ( String) ois.readObject());
            info.SetStopP5( ( Double) ois.readObject());
            info.SetStopP6( ( Double) ois.readObject());
            info.SetStopP7( ( Double) ois.readObject());
            
        } catch (ClassNotFoundException ex) {
            logger.error( "ClassNotFoundException caught, при чтении state-файла", ex);
        }
        
        return info;
    }
    
    public void SaveState() {
        try {
            FileOutputStream fos = new FileOutputStream(  m_strStateKeepFileName);
            ObjectOutputStream oos = new ObjectOutputStream( fos);
            
            //текущее состояние
            oos.writeInt( theApp.GetCurrentStep());
            
            //фаза 1
            if( theApp.GetCurrentStep() >= 1) SaveStandardPoint( oos, "001");
            
            
            //2.1 Занесение информации об установленных приборах
            //2.2 Предварительная откачка
            //2.3 Проверка герметичности установки приборов
            // ? 2.3.1 напуск азота
            //2.4 Основная откачка
            if( theApp.GetCurrentStep() >= 21) SaveStandardPoint( oos, "021");
            if( theApp.GetCurrentStep() > 21) {
                oos.writeObject( theApp.m_mapDevicePresence);
                oos.writeObject( theApp.m_mapSerials);
                oos.writeObject( theApp.m_mapDeviceGetter);
            }
            if( theApp.GetCurrentStep() >= 22) SaveStandardPoint( oos, "022");
            if( theApp.GetCurrentStep() >= 23) SaveStandardPoint( oos, "023");
            if( theApp.GetCurrentStep() >= 24) SaveStandardPoint( oos, "024");
            
            
            //41       3.1 Напуск кислорода в приборы
            //42       3.2 Обработка. 1ый цикл.
            //43       3.3 Откачка кислорода
            //44       3.4 Напуск кислорода в приборы
            //45       3.5 Обработка. 2ой цикл.
            //46       3.6 Откачка кислорода
            if( theApp.GetCurrentStep() >= 41) SaveStandardPoint( oos, "041");
            if( theApp.GetCurrentStep() >= 42) SaveStandardPoint( oos, "042");
            if( theApp.GetCurrentStep() >= 43) SaveStandardPoint( oos, "043");
            if( theApp.GetCurrentStep() >= 44) SaveStandardPoint( oos, "044");
            if( theApp.GetCurrentStep() >= 45) SaveStandardPoint( oos, "045");
            if( theApp.GetCurrentStep() >= 46) SaveStandardPoint( oos, "046");
            
            
            //61       4.1 Напуск кислород-неона в приборы
            //62       4.2 Обработка. 1ый цикл.
            //63       4.3 Откачка газовой смеси
            //64       4.4 Напуск кислород-неона в приборы
            //65       4.5 Обработка. 2ой цикл.
            //66       4.6 Откачка газовой смеси
            //67       4.7 Переход на основную откачку
            if( theApp.GetCurrentStep() >= 61) SaveStandardPoint( oos, "061");
            if( theApp.GetCurrentStep() >= 62) SaveStandardPoint( oos, "062");
            if( theApp.GetCurrentStep() >= 63) SaveStandardPoint( oos, "063");
            if( theApp.GetCurrentStep() >= 64) SaveStandardPoint( oos, "064");
            if( theApp.GetCurrentStep() >= 65) SaveStandardPoint( oos, "065");
            if( theApp.GetCurrentStep() >= 66) SaveStandardPoint( oos, "066");
            if( theApp.GetCurrentStep() >= 67) SaveStandardPoint( oos, "067");
            
            
            //81    *  5.1 Установка печек
            //82    *  5.2 Включение PID-регулирования печек
            //82.1     5.2.1 Открытие геттера
            //83    *  5.3 Снятие печек
            //84       5.4 Заполнение рабочей смесью
            //85       5.5 Выдержка
            if( theApp.GetCurrentStep() >= 81) SaveStandardPoint( oos, "081");
            if( theApp.GetCurrentStep() >= 82) SaveStandardPoint( oos, "082");
            if( theApp.GetCurrentStep() >= 83) {
                SaveStandardPoint( oos, "082.1");
                SaveStandardPoint( oos, "083");
            }
            if( theApp.GetCurrentStep() >= 84) SaveStandardPoint( oos, "084");
            if( theApp.GetCurrentStep() >= 85) SaveStandardPoint( oos, "085");
            
            
            //101   *  6.1 Измерение ВАХ
            //102   *  6.2 Внесение пороговых токов
            //103   *  6.3 Предварительная оценка параметров приборов
            //104      6.4 Откачка рабочей смеси
            if( theApp.GetCurrentStep() >= 101) SaveStandardPoint( oos, "101");
            if( theApp.GetCurrentStep() > 101) {
                oos.writeObject( theApp.m_mapStep6_1_1000mcA);
                oos.writeObject( theApp.m_mapStep6_1_1100mcA);
                oos.writeObject( theApp.m_mapStep6_1_1200mcA);
            }
            if( theApp.GetCurrentStep() >= 102) SaveStandardPoint( oos, "102");
            if( theApp.GetCurrentStep() > 102) {
                oos.writeObject( theApp.m_mapStep6_2_LasThreshold);
                oos.writeObject( theApp.m_mapStep6_2_ExtAn);
                oos.writeObject( theApp.m_mapStep6_2_ExtTu);
            }
            if( theApp.GetCurrentStep() >= 103) SaveStandardPoint( oos, "103");
            if( theApp.GetCurrentStep() > 103) {
                oos.writeObject( theApp.m_mapStep6_3_Comments);
                oos.writeObject( theApp.m_mapStep6_3_Continue);
            }
            if( theApp.GetCurrentStep() >= 104) SaveStandardPoint( oos, "104");
            
            
            //121      7.1 Напуск тренировочной смеси в приборы
            //122      7.2 Выдержка
            //123      7.3 Обработка. 1ый цикл.
            //124      7.4 Откачка тренировочной смеси
            //125      7.5 Напуск тренировочной смеси в приборы
            //126      7.6 Выдержка
            //127      7.7 Обработка. 2ой цикл.
            //128      7.8 Откачка тренировочной смеси
            //129      7.9 Напуск тренировочной смеси в приборы
            //130      7.10 Выдержка
            //131      7.11 Обработка. 3ий цикл.
            //132      7.12 Откачка тренировочной смеси
            //133      7.13 Переход на основную откачку
            if( theApp.GetCurrentStep() >= 121) SaveStandardPoint( oos, "121");
            if( theApp.GetCurrentStep() >= 122) SaveStandardPoint( oos, "122");
            if( theApp.GetCurrentStep() >= 123) SaveStandardPoint( oos, "123");
            if( theApp.GetCurrentStep() >= 124) SaveStandardPoint( oos, "124");
            if( theApp.GetCurrentStep() >= 125) SaveStandardPoint( oos, "125");
            if( theApp.GetCurrentStep() >= 126) SaveStandardPoint( oos, "126");
            if( theApp.GetCurrentStep() >= 127) SaveStandardPoint( oos, "127");
            if( theApp.GetCurrentStep() >= 128) SaveStandardPoint( oos, "128");
            if( theApp.GetCurrentStep() >= 129) SaveStandardPoint( oos, "129");
            if( theApp.GetCurrentStep() >= 130) SaveStandardPoint( oos, "130");
            if( theApp.GetCurrentStep() >= 131) SaveStandardPoint( oos, "131");
            if( theApp.GetCurrentStep() >= 132) SaveStandardPoint( oos, "132");
            if( theApp.GetCurrentStep() >= 133) SaveStandardPoint( oos, "133");
            
            
            //141   *  8.1 Обезгаживание
            //142      8.2 Открытие геттера
            if( theApp.GetCurrentStep() >= 141) {
                SaveStandardPoint( oos, "141");
                
                oos.writeObject( theApp.m_mapDegassing.size());
                Set keySet = theApp.m_mapDegassing.keySet();
                Iterator it = keySet.iterator();
                while( it.hasNext()) {
                    int nKey = ( int) it.next();
                    oos.writeObject( nKey);
                    
                    GettersActivationProgram dev = ( GettersActivationProgram) theApp.m_mapDegassing.get( nKey);
                    oos.writeObject( dev.GetDtStart());
                    oos.writeObject( dev.GetDtFinish());
                    oos.writeObject( dev.GetGetter());
                    oos.writeObject( dev.GetInductor());
                    
                    //oos.writeObject( dev.GetListSteps());
                    oos.writeObject( dev.GetListSteps().size());
                    for ( Object GetListStep : dev.GetListSteps()) {
                        GettersActivationProgramStep step = ( GettersActivationProgramStep) GetListStep;
                        oos.writeObject( step.GetDuration());
                        oos.writeObject( step.GetPower());
                        oos.writeObject( step.GetP5_start());
                        oos.writeObject( step.GetP5_max());
                        oos.writeObject( step.GetP5_last());
                    }

                }
                        
            }
            if( theApp.GetCurrentStep() >= 142) SaveStandardPoint( oos, "142");
            
            
            //161      9.1 Напуск тренировочной смеси в приборы
            //162      9.2 Выдержка
            //163      9.3 Обработка. 1ый цикл.
            //164      9.4 Откачка тренировочной смеси
            //165      9.5 Напуск тренировочной смеси в приборы
            //166      9.6 Выдержка
            //167      9.7 Обработка. 2ой цикл.
            //168      9.8 Откачка тренировочной смеси
            //169      9.9 Переход на основную откачку
            if( theApp.GetCurrentStep() >= 161) SaveStandardPoint( oos, "161");
            if( theApp.GetCurrentStep() >= 162) SaveStandardPoint( oos, "162");
            if( theApp.GetCurrentStep() >= 163) SaveStandardPoint( oos, "163");
            if( theApp.GetCurrentStep() >= 164) SaveStandardPoint( oos, "164");
            if( theApp.GetCurrentStep() >= 165) SaveStandardPoint( oos, "165");
            if( theApp.GetCurrentStep() >= 166) SaveStandardPoint( oos, "166");
            if( theApp.GetCurrentStep() >= 167) SaveStandardPoint( oos, "167");
            if( theApp.GetCurrentStep() >= 168) SaveStandardPoint( oos, "168");
            if( theApp.GetCurrentStep() >= 169) SaveStandardPoint( oos, "169");
            
            
            //181   *  10.1 Активация
            //182      10.2 Открытие геттера
            if( theApp.GetCurrentStep() >= 181) {
                SaveStandardPoint( oos, "181");
                
                //oos.writeObject( theApp.m_mapActivation);
                oos.writeObject( theApp.m_mapActivation.size());
                Set keySet = theApp.m_mapActivation.keySet();
                Iterator it = keySet.iterator();
                while( it.hasNext()) {
                    int nKey = ( int) it.next();
                    oos.writeObject( nKey);
                    
                    GettersActivationProgram dev = ( GettersActivationProgram) theApp.m_mapActivation.get( nKey);
                    oos.writeObject( dev.GetDtStart());
                    oos.writeObject( dev.GetDtFinish());
                    oos.writeObject( dev.GetGetter());
                    oos.writeObject( dev.GetInductor());
                    
                    //oos.writeObject( dev.GetListSteps());
                    oos.writeObject( dev.GetListSteps().size());
                    for ( Object GetListStep : dev.GetListSteps()) {
                        GettersActivationProgramStep step = ( GettersActivationProgramStep) GetListStep;
                        oos.writeObject( step.GetDuration());
                        oos.writeObject( step.GetPower());
                        oos.writeObject( step.GetP5_start());
                        oos.writeObject( step.GetP5_max());
                        oos.writeObject( step.GetP5_last());
                    }

                }
            }
            if( theApp.GetCurrentStep() >= 182) SaveStandardPoint( oos, "182");
            

            //201      11.1 Заполнение рабочей смесью
            //202      11.2 Выдержка
            //203   *  11.3 Измерение ВАХ
            //204   *  11.4 Внесение пороговых токов
            //205   *  11.5 Оценка параметров приборов
            //206   *  11.6 Герметизация годных приборов
            if( theApp.GetCurrentStep() >= 201) SaveStandardPoint( oos, "201");
            if( theApp.GetCurrentStep() >= 202) SaveStandardPoint( oos, "202");
            
            if( theApp.GetCurrentStep() >= 203) SaveStandardPoint( oos, "203");
            if( theApp.GetCurrentStep() > 203) {
                oos.writeObject( theApp.m_mapStep11_3_1000mcA);
                oos.writeObject( theApp.m_mapStep11_3_1100mcA);
                oos.writeObject( theApp.m_mapStep11_3_1200mcA);
            }
            if( theApp.GetCurrentStep() >= 204) SaveStandardPoint( oos, "204");
            if( theApp.GetCurrentStep() > 204) {
                oos.writeObject( theApp.m_mapStep11_4_LasThreshold);
                oos.writeObject( theApp.m_mapStep11_4_ExtAn);
                oos.writeObject( theApp.m_mapStep11_4_ExtTu);
            }
            if( theApp.GetCurrentStep() >= 205) SaveStandardPoint( oos, "205");
            if( theApp.GetCurrentStep() > 205) {
                oos.writeObject( theApp.m_mapStep11_5_Comments);
                oos.writeObject( theApp.m_mapStep11_5_Continue);
            }
            if( theApp.GetCurrentStep() >= 206) SaveStandardPoint( oos, "206");
            
            
            //221      12.1 Закрытие геттера
            //222      12.2 Напуск азота в приборы
            //223   *  12.3 Снятие непрошедших приборов
            if( theApp.GetCurrentStep() >= 221) SaveStandardPoint( oos, "221");
            if( theApp.GetCurrentStep() >= 222) SaveStandardPoint( oos, "222");
            if( theApp.GetCurrentStep() >= 223) SaveStandardPoint( oos, "223");
            
            //241      13.1 Bypass-откачка
            //242   *  13.2 Проверка герметичности (?? да  ?? нет)
            //      ?   13.2.1 Напуск азота
            //243      13.3 Основная откачка
            //244      13.4 Откачка смеси с геттера
            if( theApp.GetCurrentStep() >= 241) SaveStandardPoint( oos, "241");
            if( theApp.GetCurrentStep() >= 242) SaveStandardPoint( oos, "242");
            if( theApp.GetCurrentStep() >= 243) SaveStandardPoint( oos, "243");
            if( theApp.GetCurrentStep() >= 244) SaveStandardPoint( oos, "244");
            
            
            oos.close();
            fos.close();
        } catch( Exception ex) {
            logger.error( "Во время сохранения состояния произошла Exception!", ex);
        }
        
        
    }
    
    public void RestoreState() {
        try {
            FileInputStream fis = new FileInputStream(  m_strStateKeepFileName);
            ObjectInputStream ois = new ObjectInputStream( fis);
            
            //текущее состояние
            int nLastWrittenStep = ois.readInt();
            
            String strStepNumber = "";
            boolean bContinue;
            do {
                if( strStepNumber.equals( "021")) {
                    theApp.m_mapDevicePresence = ( HashMap) ois.readObject();
                    theApp.m_mapSerials = ( HashMap) ois.readObject();
                    theApp.m_mapDeviceGetter = ( HashMap) ois.readObject();
                }
                
                if( strStepNumber.equals( "101")) {
                    theApp.m_mapStep6_1_1000mcA = ( HashMap) ois.readObject();
                    theApp.m_mapStep6_1_1100mcA = ( HashMap) ois.readObject();
                    theApp.m_mapStep6_1_1200mcA = ( HashMap) ois.readObject();
                }
                if( strStepNumber.equals( "102")) {
                    theApp.m_mapStep6_2_LasThreshold = ( HashMap) ois.readObject();
                    theApp.m_mapStep6_2_ExtAn = ( HashMap) ois.readObject();
                    theApp.m_mapStep6_2_ExtTu = ( HashMap) ois.readObject();
                }
                if( strStepNumber.equals( "103")) {
                    theApp.m_mapStep6_3_Comments = ( HashMap) ois.readObject();
                    theApp.m_mapStep6_3_Continue = ( HashMap) ois.readObject();
                }
                
                if( strStepNumber.equals( "141")) {
                    
                    //theApp.m_mapDegassing = ( HashMap) ois.readObject();
                    int nLen = ( int) ois.readObject();
                    for( int i=0; i<nLen; i++) {
                        int nKey = ( int) ois.readObject();
                        
                        GettersActivationProgram dev = new GettersActivationProgram();
                        dev.SetDtStart( ( Date) ois.readObject());
                        dev.SetDtFinish( ( Date) ois.readObject());
                        dev.SetGetter( ( int) ois.readObject());
                        dev.SetInductor( ( int) ois.readObject());
                        
                        LinkedList lstSteps = new LinkedList();
                        int nStepsLen = ( int) ois.readObject();
                        for( int j=0; j<nStepsLen; j++) {
                            GettersActivationProgramStep step = new GettersActivationProgramStep();
                            
                            step.SetDuration( ( int) ois.readObject());
                            step.SetPower( ( int) ois.readObject());
                            step.SetP5_start( ( double) ois.readObject());
                            step.SetP5_max( ( double) ois.readObject());
                            step.SetP5_last( ( double) ois.readObject());
                            
                            lstSteps.add( step);
                        }
                        //dev.SetListSteps( ( LinkedList) ois.readObject());
                        dev.SetListSteps( lstSteps);
                        
                        
                        theApp.m_mapDegassing.put( nKey, dev);
                    }

                }
                
                if( strStepNumber.equals( "181")) {
                    //theApp.m_mapActivation = ( HashMap) ois.readObject();
                    int nLen = ( int) ois.readObject();
                    for( int i=0; i<nLen; i++) {
                        int nKey = ( int) ois.readObject();
                        
                        GettersActivationProgram dev = new GettersActivationProgram();
                        dev.SetDtStart( ( Date) ois.readObject());
                        dev.SetDtFinish( ( Date) ois.readObject());
                        dev.SetGetter( ( int) ois.readObject());
                        dev.SetInductor( ( int) ois.readObject());
                        
                        LinkedList lstSteps = new LinkedList();
                        int nStepsLen = ( int) ois.readObject();
                        for( int j=0; j<nStepsLen; j++) {
                            GettersActivationProgramStep step = new GettersActivationProgramStep();
                            
                            step.SetDuration( ( int) ois.readObject());
                            step.SetPower( ( int) ois.readObject());
                            step.SetP5_start( ( double) ois.readObject());
                            step.SetP5_max( ( double) ois.readObject());
                            step.SetP5_last( ( double) ois.readObject());
                            
                            lstSteps.add( step);
                        }
                        //dev.SetListSteps( ( LinkedList) ois.readObject());
                        dev.SetListSteps( lstSteps);
                        
                        
                        theApp.m_mapActivation.put( nKey, dev);
                    }
                }
                
                if( strStepNumber.equals( "203")) {
                    theApp.m_mapStep11_3_1000mcA = ( HashMap) ois.readObject();
                    theApp.m_mapStep11_3_1100mcA = ( HashMap) ois.readObject();
                    theApp.m_mapStep11_3_1200mcA = ( HashMap) ois.readObject();
                }
                if( strStepNumber.equals( "204")) {
                    theApp.m_mapStep11_4_LasThreshold = ( HashMap) ois.readObject();
                    theApp.m_mapStep11_4_ExtAn = ( HashMap) ois.readObject();
                    theApp.m_mapStep11_4_ExtTu = ( HashMap) ois.readObject();
                }
                if( strStepNumber.equals( "205")) {
                    theApp.m_mapStep11_5_Comments = ( HashMap) ois.readObject();
                    theApp.m_mapStep11_5_Continue = ( HashMap) ois.readObject();
                }
                
                int nAvailable = fis.available();
                if( nAvailable > 0) {
                    strStepNumber = ( String) ois.readObject();
                    theApp.SaveStepInfo( strStepNumber, ReadStandardPoint( ois), false);
                }
                
                nAvailable = fis.available();
                bContinue = nAvailable != 0;
                
            } while( bContinue);
            
            ois.close();
            fis.close();
            
            //Последний этап = strStepNumber
            //Закончен ли он?
            boolean bEnded = !( theApp.GetStepInfo( strStepNumber).GetStopDate() == null);
            
            String strMessage =
                    "<html>Согласно файлу состояния, в предыдущем запуске,последним был этап " +
                    theApp.HumanNameForStep( strStepNumber) +
                    ", и он " + ( bEnded ? "был закончен" : "не был закончен");
            strMessage += ".<br><br>Какой выставить текущий этап?</html>";
            
            
            //обычное сбитие, предлагаем текущий или следущий этапы
            PrevStateRestoreDlgCommon dlg = new PrevStateRestoreDlgCommon( null, true);

            theApp.SetCurrentStep( nLastWrittenStep);
            theApp.NextCurrentStep();
            int nPotentialNextStep = theApp.GetCurrentStep();

            dlg.m_strLabel.setText( strMessage);
            dlg.m_rad1.setText( theApp.HumanNameForStep( String.format("%03d", nLastWrittenStep)));
            dlg.m_rad2.setText( theApp.HumanNameForStep( String.format("%03d", nPotentialNextStep)));

            dlg.setVisible( true);
            if( dlg.m_bDrop == false) {
                if( dlg.m_rad1.isSelected()) {
                    theApp.SetCurrentStep( nLastWrittenStep);

                    if( "141".equals( strStepNumber)  ||    //мы сбились посередине обезгаживания - надо ещё отметить-подтвердить кого мы провели
                        "181".equals( strStepNumber)) {     //мы сбились посередине активации -     надо ещё отметить-подтвердить кого мы провели
                    
                        HashMap mapToModify = null;
                        
                        PrevStateRestoreDlgDegasation dlg2;
                        if( "141".equals( strStepNumber)) {
                            mapToModify = theApp.m_mapDegassing;
                            dlg2 = new PrevStateRestoreDlgDegasation( null, true, true, theApp);
                        }
                        else { 
                            mapToModify = theApp.m_mapActivation;
                            dlg2 = new PrevStateRestoreDlgDegasation( null, true, false, theApp);
                        }
                        
                        dlg2.setVisible( true);
                        
                        //DEVICE1
                        if( dlg2.m_chkDev1.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE1)) {
                               //DEV1 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV1 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE1, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE1)) {
                               //DEV1 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE1);
                           }
                           else {
                               //DEV1 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }
                        
                        //DEVICE2
                        if( dlg2.m_chkDev2.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE2)) {
                               //DEV2 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV2 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE2, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE2)) {
                               //DEV2 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE2);
                           }
                           else {
                               //DEV2 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }
                        
                        //DEVICE3
                        if( dlg2.m_chkDev3.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE3)) {
                               //DEV3 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV3 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE3, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE3)) {
                               //DEV3 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE3);
                           }
                           else {
                               //DEV3 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }
                        
                        //DEVICE4
                        if( dlg2.m_chkDev4.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE4)) {
                               //DEV4 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV4 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE4, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE4)) {
                               //DEV4 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE4);
                           }
                           else {
                               //DEV4 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }
                        
                        //DEVICE5
                        if( dlg2.m_chkDev5.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE5)) {
                               //DEV5 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV5 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE5, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE5)) {
                               //DEV5 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE5);
                           }
                           else {
                               //DEV5 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }
                        
                        //DEVICE6
                        if( dlg2.m_chkDev6.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE6)) {
                               //DEV6 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV6 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE6, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE6)) {
                                //DEV6 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE6);
                           }
                           else {
                               //DEV6 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }
                        
                        //DEVICE7
                        if( dlg2.m_chkDev7.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE7)) {
                               //DEV7 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV7 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE7, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE7)) {
                               //DEV7 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE7);
                           }
                           else {
                               //DEV7 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }
                        
                        //DEVICE8
                        if( dlg2.m_chkDev8.isSelected()) {
                           if( mapToModify.containsKey( HVV_AdminConstants.DEVICE8)) {
                               //DEV8 отмечен что проведён и инфа есть - всё ок
                           }
                           else {
                               //DEV8 отмечен что проведён а инфы нет - создадим
                               GettersActivationProgram st = new GettersActivationProgram();
                               st.SetDtStart( theApp.GetLocalDate());
                               st.SetDtFinish( theApp.GetLocalDate());
                               mapToModify.put( HVV_AdminConstants.DEVICE8, st);
                           }
                        }
                        else {
                            if( mapToModify.containsKey( HVV_AdminConstants.DEVICE8)) {
                               //DEV8 отмечен что не проведён, а инфа есть - удалим
                                mapToModify.remove( HVV_AdminConstants.DEVICE8);
                           }
                           else {
                               //DEV8 отмечен что не проведён, и инфы нет - всё ок
                           }
                        }

                    }
                    
                    
                    
                }
                else {
                    TechProcessStepInfo info = theApp.GetStepInfo( String.format("%03d", nLastWrittenStep));
                    info.SetStopDateAsCurrent();
                    info.SetStopReportTitle( "Этап отмечен как завершенный вручную, после перезапуска адм. модуля");
                    info.SetStopP5( null);
                    info.SetStopP6( null);
                    info.SetStopP7( null);

                    theApp.SetCurrentStep( nPotentialNextStep);
                    
                    info = new TechProcessStepInfo( theApp);
                    info.SetStartDateAsCurrent();
                    
                    String strTitle = null;
                    
                    //некоторые этапы надо отметить как начатые
                    switch( theApp.GetCurrentStep()) {
                        case 21:    strTitle = "Установка резонаторов (восст.)";  break;
                        case 23:    strTitle = "Проверка герметичности установки приборов (восст.)";  break;
                        case 81:    strTitle = "Установка печек (восст.)";  break;
                        case 82:    strTitle = "Старт термообезгаживания (восст.)";  break;
                        case 83:    strTitle = "Снятие печек (восст.)";  break;
                        case 101:   strTitle = "Снятие вольт-маперной характеристики анодов (восст.)";  break;
                        case 102:   strTitle = "Замеры порогов генерации и погасания (восст.)";  break;
                        case 103:   strTitle = "Внесение промежуточных комментариев (восст.)";  break;
                        case 141:   strTitle = "Начало процесса обезгаживания (восст.)";  break;
                        case 181:   strTitle = "Начало процесса активации (восст.)";  break;
                        case 203:   strTitle = "Измерение ВАХ (восст.)";  break;
                        case 204:   strTitle = "Замеры порогов генерации и погасания (восст.)";  break;
                        case 205:   strTitle = "Внесение финальных комментариев (восст.)";  break;
                        case 206:   strTitle = "Начало герметизации годных приборов (восст.)";  break;
                        case 223:   strTitle = "Старт снятия непрошедших приборов (восст.)";  break;
                        case 242:   strTitle = "Проверка герметичности установки заглушек (восст.)";  break;
                    }
                    
                    if( strTitle != null) {
                        info.SetStartReportTitle( strTitle);
                        info.SetStartP5( null);
                        info.SetStartP6( null);
                        info.SetStartP7( null);
                        theApp.SaveStepInfo( String.format( "%03d", theApp.GetCurrentStep()), info, true);
                    }
                }

                switch( theApp.GetCurrentStep()) {
                    case 21:    theApp.ShowDlg2_1();    break;
                    case 23:    theApp.ShowDlg2_3();    break;
                    case 81:    theApp.ShowDlg5_1();    break;
                    case 82:    theApp.ShowDlg5_2();    break;
                    case 83:    theApp.ShowDlg5_3();    break;
                    case 101:   theApp.ShowDlg6_1A();   break;
                    case 102:   theApp.ShowDlg6_2();    break;
                    case 103:   theApp.ShowDlg6_3();    break;
                    case 141:   theApp.ShowDlg8( 2000);     break;
                    case 181:   theApp.ShowDlg10( 2000);    break;
                    case 203:   theApp.ShowDlg11_3A();  break;
                    case 204:   theApp.ShowDlg11_4();   break;
                    case 205:   theApp.ShowDlg11_5();   break;
                    case 206:   theApp.ShowDlg11_6();   break;
                    case 223:   theApp.ShowDlg12_3();   break;
                    case 242:   theApp.ShowDlg13_2();    break;
                }
            }
            else {
                m_bDropReadState = true;
            }
            
        
            
            
        } catch( Exception ex) {
            logger.error( "Во время восстановления состояния произошла Exception!", ex);
        }
        
        
        
    }
    
    public void DropState() {
        File file = new File( m_strStateKeepFileName);
        file.deleteOnExit();
    }

    public boolean CheckStateKeeperFileExistance() {
        
        return ( new File( m_strStateKeepFileName).exists());
    }
}
