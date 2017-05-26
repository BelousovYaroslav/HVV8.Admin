package hvv_admin;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import HVV_Communication.client.HVV_Comm_client;
import hvv_admin.comm.executor.from.HVV_Communication_E2A;
import hvv_admin.comm.poller.GetExecutor;
import hvv_admin.dialogs.HVV_Admin_MainFrame;
import hvv_admin.dialogs.step02.TechProcessStep02_1_Dlg1;
import hvv_admin.dialogs.step02.TechProcessStep02_3_Dlg;
import hvv_admin.dialogs.step05.TechProcessStep05_1_Dlg;
import hvv_admin.dialogs.step05.TechProcessStep05_2_Dlg;
import hvv_admin.dialogs.step05.TechProcessStep05_3_Dlg;
import hvv_admin.dialogs.step06.TechProcessStep06_1A_Dlg;
import hvv_admin.dialogs.step06.TechProcessStep06_2_Dlg;
import hvv_admin.dialogs.step06.TechProcessStep06_3_Dlg;
import hvv_admin.dialogs.step08.TechProcessStep08_1_Dlg_M;
import hvv_admin.dialogs.step10.TechProcessStep10_1_Dlg_M;
import hvv_admin.dialogs.step11.TechProcessStep11_3A_Dlg;
import hvv_admin.dialogs.step11.TechProcessStep11_4_Dlg;
import hvv_admin.dialogs.step11.TechProcessStep11_5_Dlg;
import hvv_admin.dialogs.step11.TechProcessStep11_6_Dlg;
import hvv_admin.dialogs.step12.TechProcessStep12_3_Dlg;
import hvv_admin.dialogs.step13.TechProcessStep13_2_Dlg;
import hvv_admin.pendings.HVV_AdminPendings;
import hvv_admin.planner.HVV_AdminPlanner;
import hvv_admin.report.ReportGenerator;
import hvv_admin.state.HVV_StateKeeper;
import hvv_admin.steps.info.TechProcessStepInfo;
import hvv_resources.HVV_Resources;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import obsolete.TestDialog5;
import java.io.File;
import static java.lang.Thread.sleep;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.UIManager;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 *
 * @author yaroslav
 */
public class HVV_Admin {
    private final String m_strAMSrootEnvVar;
    public String GetAMSRoot() { return m_strAMSrootEnvVar; }
    
    /*
    public String m_strDevice1; public boolean m_bDevice1;
    public String m_strDevice2; public boolean m_bDevice2;
    public String m_strDevice3; public boolean m_bDevice3;
    public String m_strDevice4; public boolean m_bDevice4;
    public String m_strDevice5; public boolean m_bDevice5;
    public String m_strDevice6; public boolean m_bDevice6;
    public String m_strDevice7; public boolean m_bDevice7;
    public String m_strDevice8; public boolean m_bDevice8;
    */
    

    /** серийные номера установленных приборов<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapSerials;
    /** булевские флаги установленнных приборов<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapDevicePresence;
    /** тип геттера установленных приборов<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapDeviceGetter;
    
    /** ВАХ снимаемая в этапе 6.1 при уставке тока 1000мкА<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_1_1000mcA;
    /** ВАХ снимаемая в этапе 6.1 при уставке тока 1100мкА<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_1_1100mcA;
    /** ВАХ снимаемая в этапе 6.1 при уставке тока 1200мкА<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_1_1200mcA;
    
    /** пороги генерации лазерного излучения внутри обрабатываемых резонаторов, определяемый в этапе 6.2<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_2_LasThreshold;
    /** токи погасания лазерного излучения внутри анодного плеча обрабатываемых резонаторов, определяемый в этапе 6.2<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_2_ExtAn;          //порог
    /** токи погасания лазерного излучения внутри штенгельного плеча обрабатываемых резонаторов, определяемый в этапе 6.2<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_2_ExtTu;
    
    /** Промежуточные комментарии по обрабатываемым резонаторам<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_3_Comments;
    /** булевские флаги продолжения обработки резонаторов, определяемые на этапе 6.3<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep6_3_Continue;
    
    /** Данные о проведении обезгаживания (8 этап)
     */
    public HashMap m_mapDegassing;
    
    /** Данные о проведении активации (10 этап)
     */
    public HashMap m_mapActivation;
    
    /** ВАХ снимаемая в этапе 11.3 при уставке тока 1000мкА<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_3_1000mcA;
    /** ВАХ снимаемая в этапе 11.3 при уставке тока 1100мкА<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_3_1100mcA;
    /** ВАХ снимаемая в этапе 11.3 при уставке тока 1200мкА<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_3_1200mcA;
    
    /** пороги генерации лазерного излучения внутри обрабатываемых резонаторов, определяемый в этапе 11.4<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_4_LasThreshold;
    /** токи погасания лазерного излучения внутри анодного плеча обрабатываемых резонаторов, определяемый в этапе 11.4<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_4_ExtAn;
    /** токи погасания лазерного излучения внутри штенгельного плеча обрабатываемых резонаторов, определяемый в этапе 11.4<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_4_ExtTu;
    
    /** Итоговые комментарии по обрабатываемым резонаторам<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_5_Comments;
    /** булевские флаги прохождения выходного контроля резонаторов, определяемые на этапе 11.5<br>ключи: HVV_AdminConstants.DEVICE1, ...
     */
    public HashMap m_mapStep11_5_Continue;
    
    
    public HVV_Admin_MainFrame m_pMainWnd;
    static Logger logger = Logger.getLogger( HVV_Admin.class);
    public static final org.apache.log4j.Level LOG_LEVEL = org.apache.log4j.Level.DEBUG;
    
    private final HVV_Resources m_Resources;
    public HVV_Resources GetResources() { return m_Resources;}
    
    private final HVV_AdminSettings m_pSettings;
    public HVV_AdminSettings GetSettings() { return m_pSettings; }
    
    private final HVV_AdminPlanner m_pPlanner;
    public HVV_AdminPlanner GetPlanner() { return m_pPlanner; }
    
    private final HVV_AdminPendings m_pPendings;
    public HVV_AdminPendings GetPendings() { return m_pPendings; }
    
    private final HVV_StateKeeper m_pStateKeeper;
    public HVV_StateKeeper GetStateKeeper() { return m_pStateKeeper; }
    public void DropStateKeeper() { m_pStateKeeper.DropState(); }
    
    private final HVV_AdminStepNames m_pStepNames;
    public String GetStepName( int nId) {
        String result = null;
        if( m_pStepNames.m_mapSteps.containsKey( String.format( "%03d", nId))) {
            ItemStepNames st = ( ItemStepNames) m_pStepNames.m_mapSteps.get( String.format( "%03d", nId));
            result = st.GetName();
        }
        return result;
    }
    public String GetStepNameWithNum( int nId) {
        String result = null;
        if( m_pStepNames.m_mapSteps.containsKey( String.format( "%03d", nId))) {
            ItemStepNames st = ( ItemStepNames) m_pStepNames.m_mapSteps.get( String.format( "%03d", nId));
            result = st.GetNum() + " " + st.GetName();
        }
        return result;
    }
            
    private final Font m_fontUsual;
    public Font GetUsualFont() { return m_fontUsual; }
    
    private final Font m_fontBold;
    public Font GetBoldFont() { return m_fontBold; }
    
    
    
    public ReportGenerator m_ReportGenerator;
    public String m_strAdminStartDtm;
    public boolean m_bProcessingEnded;
    
    public boolean m_bSpecial231ExecFinish;
    public boolean m_bSpecial1321ExecFinish;
    
    private boolean m_bCurrentStepInProgress;
    public boolean IsCurrentStepInProgress() { return m_bCurrentStepInProgress; }
    public void SetCurrentStepInProgress( boolean bStatus) { m_bCurrentStepInProgress = bStatus; }
    
    private int m_nCurrentProcessStep;
    public int GetCurrentStep() { return m_nCurrentProcessStep; }
    public void SetCurrentStep( int nNewStep) {
        m_nCurrentProcessStep = nNewStep;
    }
    
    public void NextCurrentStep() {
        
        if( m_nCurrentProcessStep == 244) {
            //в случае приход сигнала об окончании исполнения программы для этапа 13.4 (последнего)
            //не нужно делать ничего. этап не должен переключиться. Флаг исполнения должен остаться поднятым.
            //это нужно для того, чтобы виднелась кнопка "Финиш"
            return;
        }
        
        switch( m_nCurrentProcessStep) {
            //1. Подготовка
            case   1: m_nCurrentProcessStep =  21; break;  //1.1 Заполнение рабочей камеры азотом

            //2. Установка приборов
            case  21: m_nCurrentProcessStep =  22; break;  //2.1 Занесение информации об установленных приборах
            case  22: m_nCurrentProcessStep =  23; break;  //2.2 Предварительная откачка
            case  23: m_nCurrentProcessStep =  24; break;  //2.3 Проверка герметичности установки приборов ( ?? да   ?? нет=уход на 1.1)
            case  24: m_nCurrentProcessStep =  41; break;  //2.4 Основная откачка

            //3. Обработка в среде кислорода.
            case  41: m_nCurrentProcessStep =  42; break;  //3.1 Напуск кислорода в приборы
            case  42: m_nCurrentProcessStep =  43; break;  //3.2 Обработка. 1ый цикл.
            case  43: m_nCurrentProcessStep =  44; break;  //3.3 Откачка кислорода
            case  44: m_nCurrentProcessStep =  45; break;  //3.4 Напуск кислорода в приборы
            case  45: m_nCurrentProcessStep =  46; break;  //3.5 Обработка. 2ой цикл.
            case  46: m_nCurrentProcessStep =  61; break;  //3.6 Откачка кислорода

            //4. Обработка в среде кислород-неона.
            case  61: m_nCurrentProcessStep =  62; break;  //4.1 Напуск кислород-неона в приборы
            case  62: m_nCurrentProcessStep =  63; break;  //4.2 Обработка. 1ый цикл.
            case  63: m_nCurrentProcessStep =  64; break;  //4.3 Откачка газовой смеси
            case  64: m_nCurrentProcessStep =  65; break;  //4.4 Напуск кислород-неона в приборы
            case  65: m_nCurrentProcessStep =  66; break;  //4.5 Обработка. 2ой цикл.
            case  66: m_nCurrentProcessStep =  67; break;  //4.6 Откачка газовой смеси
            case  67: m_nCurrentProcessStep =  81; break;  //4.7 Переход на основную откачку
    
            //5. Термообезгаживание
            case  81: m_nCurrentProcessStep =  82; break;  //5.1 Переход на основную откачку (2 и 1)
            case  82: m_nCurrentProcessStep =  83; break;  //5.2 Установка печек
            case  83: m_nCurrentProcessStep =  84; break;  //5.3 Включение PID-регулирования печек
            case  84: m_nCurrentProcessStep =  85; break;  //5.4 Снятие печек
            case  85: m_nCurrentProcessStep = 101; break;  //5.5 Заполнение рабочей смесью

            //6. Предварительная оценка параметров приборов
            case 101: m_nCurrentProcessStep = 102; break;  //6.1 Внесение комментариев
            case 102: m_nCurrentProcessStep = 103; break;  //6.2 Внесение пороговых токов
            case 103: m_nCurrentProcessStep = 104; break;  //6.3 Измерение ВАХ
            case 104: m_nCurrentProcessStep = 121; break;  //6.4 Откачка рабочей смеси

            //7. Тренировка катода
            case 121: m_nCurrentProcessStep = 122; break;  //7.1 Напуск тренировочной смеси в приборы
            case 122: m_nCurrentProcessStep = 123; break;  //7.2 Выдержка
            case 123: m_nCurrentProcessStep = 124; break;  //7.3 Обработка. 1ый цикл
            case 124: m_nCurrentProcessStep = 125; break;  //7.4 Откачка тренировочной смеси
            case 125: m_nCurrentProcessStep = 126; break;  //7.5 Напуск тренировочной смеси в приборы
            case 126: m_nCurrentProcessStep = 127; break;  //7.6 Выдержка
            case 127: m_nCurrentProcessStep = 128; break;  //7.7 Обработка. 2ой цикл
            case 128: m_nCurrentProcessStep = 129; break;  //7.8 Откачка тренировочной смеси
            case 129: m_nCurrentProcessStep = 130; break;  //7.9 Напуск тренировочной смеси
            case 130: m_nCurrentProcessStep = 131; break;  //7.10 Выдержка
            case 131: m_nCurrentProcessStep = 132; break;  //7.11 Обработка. 3ий цикл
            case 132: m_nCurrentProcessStep = 133; break;  //7.12 Откачка тренировочной смеси
            case 133: m_nCurrentProcessStep = 141; break;  //7.13 Переход на основную откачку
                
            //8. Обезгаживание рабочих геттеров
            case 141: m_nCurrentProcessStep = 142; break;  //8.1 Обезгаживание
            case 142: m_nCurrentProcessStep = 161; break;  //8.2 Открытие геттера
    
            //9. Тренировка в тренировочной смеси
            case 161: m_nCurrentProcessStep = 162; break;  //9.1 Напуск тренировочной смеси в приборы
            case 162: m_nCurrentProcessStep = 163; break;  //9.2 Выдержка
            case 163: m_nCurrentProcessStep = 164; break;  //9.3 Обработка. 1ый цикл.
            case 164: m_nCurrentProcessStep = 165; break;  //9.4 Откачка тренировочной смеси
            case 165: m_nCurrentProcessStep = 166; break;  //9.5 Напуск тренировочной смеси в приборы
            case 166: m_nCurrentProcessStep = 167; break;  //9.6 Выдержка
            case 167: m_nCurrentProcessStep = 168; break;  //9.7 Обработка. 2ой цикл.
            case 168: m_nCurrentProcessStep = 169; break;  //9.8 Откачка тренировочной смеси
            case 169: m_nCurrentProcessStep = 181; break;  //9.9 Переход на основную откачку

            //10. Активация рабочих геттеров
            case 181: m_nCurrentProcessStep = 182; break;  //10.1 Активация
            case 182: m_nCurrentProcessStep = 201; break;  //10.2 Открытие геттера
    
            //11. Выходная оценка параметров приборов
            case 201: m_nCurrentProcessStep = 202; break;  //11.1 Заполнение рабочей смесью
            case 202: m_nCurrentProcessStep = 203; break;  //11.2 Выдержка
            case 203: m_nCurrentProcessStep = 204; break;  //11.3 Внесение пороговых токов
            case 204: m_nCurrentProcessStep = 205; break;  //11.4 Измерение ВАХ
            case 205: m_nCurrentProcessStep = 206; break;  //11.5 Оценка параметров приборов
            case 206: m_nCurrentProcessStep = 221; break;  //11.6 Герметизация годных приборов
    
            //12. Снятие непрошедших приборов (опционально)
            case 221: m_nCurrentProcessStep = 222; break;  //12.1 Закрытие геттера
            case 222: m_nCurrentProcessStep = 223; break;  //12.2 Напуск азота в приборы
            case 223: m_nCurrentProcessStep = 241; break;  //12.3 Снятие непрошедших приборов

            //13. Завершение технологического процесса
            case 241: m_nCurrentProcessStep = 242; break;  //13.1 Bypass-откачка
            case 242: m_nCurrentProcessStep = 243; break;  //13.2 Проверка герметичности (?? да  ?? нет)
            case 243: m_nCurrentProcessStep = 244; break;  //13.3 Основная откачка
            //case 244: m_nCurrentProcessStep = 245; break;  //13.4 Откачка смеси с геттера
        }
        m_bCurrentStepInProgress = false;
    }

    public void SpecialCase_23() {
        //переход 23 (2.3 проверка герметичности) на пункт 22 (2.2 предварительная откачка)
        //в случае когда восстановили герметичность
        if( m_nCurrentProcessStep == 23) {
            m_nCurrentProcessStep = 22;
            if( m_mapSteps.containsKey( "023")) m_mapSteps.remove( "023");
            if( m_mapSteps.containsKey( "022")) m_mapSteps.remove( "022");
        }
        m_bCurrentStepInProgress = false;
    }
    
    public void SpecialCase_103() {
        if( m_nCurrentProcessStep == 103) {
            m_nCurrentProcessStep = 221;
        }
    }
    
    public void SpecialCase_242() {
        //переход 242 (13.2 проверка герметичности заглушек) на пункт 241 (13.1 bypass-откачка)
        //в случае когда восстановили герметичность
        if( m_nCurrentProcessStep == 242) {
            if( m_mapSteps.containsKey( "242")) m_mapSteps.remove( "242");
            m_nCurrentProcessStep = 241;
        }
    }
    
    public void SpecialCase_205_221() {
        //переход 205 (11.5 оценка параметров приборов) на пункт 221 (12.1 закрытие геттера)
        //в случае когда нет прошедших приборов
        if( m_nCurrentProcessStep == 205) {
            m_nCurrentProcessStep = 221;
        }
    }
            
    public void SpecialCase_206_244() {
        //переход 206 (11.6 герметизация годных) на пункт 244 (13.4 Откачка смеси с геттера)
        //в случае когда все поставленные приборы прошли
        if( m_nCurrentProcessStep == 206) {
            m_nCurrentProcessStep = 244;
        }
    }
    
            
    public String HumanNameForStep( String strInternalName) {
        String strResult = "[" + strInternalName + "]";
        try {
            int nId = Integer.parseInt( strInternalName);
            strResult = String.format( "%d.%d", ( nId / 20) + 1, nId % 20);
            //int nMinor = nId % 20;
            //int nMajor = nId / 20;
        } catch( NumberFormatException ex) {
        }
        
        /*
        switch( strInternalName) {
            case "001": strResult = "1.1"; break;
                
            case "021": strResult = "2.1"; break;
            case "022": strResult = "2.2"; break;
            case "023": strResult = "2.3"; break;
            case "024": strResult = "2.4"; break;
                
            case "041": strResult = "3.1"; break;
            case "042": strResult = "3.2"; break;
            case "043": strResult = "3.3"; break;
            case "044": strResult = "3.4"; break;
            case "045": strResult = "3.5"; break;
            case "046": strResult = "3.6"; break;
                
            case "061": strResult = "4.1"; break;
            case "062": strResult = "4.2"; break;
            case "063": strResult = "4.3"; break;
            case "064": strResult = "4.4"; break;
            case "065": strResult = "4.5"; break;
            case "066": strResult = "4.6"; break;
            case "067": strResult = "4.7"; break;
                
            case "081": strResult = "5.1"; break;
            case "082": strResult = "5.2"; break;
            case "083": strResult = "5.3"; break;
            case "084": strResult = "5.4"; break;
            case "085": strResult = "5.5"; break;
                
            case "101": strResult = "6.1"; break;
            case "102": strResult = "6.2"; break;
            case "103": strResult = "6.3"; break;
            case "104": strResult = "6.4"; break;
                
            case "121": strResult = "7.1"; break;
            case "122": strResult = "7.2"; break;
            case "123": strResult = "7.3"; break;
            case "124": strResult = "7.4"; break;
            case "125": strResult = "7.5"; break;
            case "126": strResult = "7.6"; break;
            case "127": strResult = "7.7"; break;
            case "128": strResult = "7.8"; break;
            case "129": strResult = "7.9"; break;
            case "130": strResult = "7.10"; break;
            case "131": strResult = "7.11"; break;
            case "132": strResult = "7.12"; break;
            case "133": strResult = "7.13"; break;
                
            case "141": strResult = "8.1"; break;
            case "142": strResult = "8.2"; break;
                
            case "161": strResult = "9.1"; break;
            case "162": strResult = "9.2"; break;
            case "163": strResult = "9.3"; break;
            case "164": strResult = "9.4"; break;
            case "165": strResult = "9.5"; break;
            case "166": strResult = "9.6"; break;
            case "167": strResult = "9.7"; break;
            case "168": strResult = "9.8"; break;
            case "169": strResult = "9.9"; break;
                
            case "181": strResult = "10.1"; break;
            case "182": strResult = "10.2"; break;
                
            case "201": strResult = "11.1"; break;
            case "202": strResult = "11.2"; break;
            case "203": strResult = "11.3"; break;
            case "204": strResult = "11.4"; break;
            case "205": strResult = "11.5"; break;
            case "206": strResult = "11.6"; break;
                
            case "221": strResult = "12.1"; break;
            case "222": strResult = "12.2"; break;
            case "223": strResult = "12.3"; break;
                
            case "241": strResult = "13.1"; break;
            case "242": strResult = "13.2"; break;
            case "243": strResult = "13.3"; break;
            case "244": strResult = "13.4"; break;
        }*/
        return strResult;
    }
    
    private TreeMap m_mapSteps;
    public boolean IsStepMapContainsKey( String strStepName) { return m_mapSteps.containsKey( strStepName);}
    public void SaveStepInfo( String strStepName, TechProcessStepInfo info, boolean bSaveState) {
        m_mapSteps.put( strStepName, info);
        if( bSaveState)
            m_pStateKeeper.SaveState();
    }
    
    public TechProcessStepInfo GetStepInfo( String strStepName) {
        TechProcessStepInfo ret = null;
        
        if( m_mapSteps.containsKey( strStepName))
            ret = ( TechProcessStepInfo) m_mapSteps.get( strStepName);
        
        return ret;
    }
    
    
    /**
     * Интерфейс соединения ADMIN -> POLLER
     * По этой линии ADMIN получает текущие значения параметров. Например P5, P6, P7
     */
    private final HVV_Comm_client m_comm_A2P;
    public HVV_Comm_client GetCommA2P() { return m_comm_A2P; }
    
    /**
     * Интерфейс соединения ADMIN -> EXECUTOR
     * По этой линии ADMIN указывает EXECUTOR'у какую программу загрузить
     */
    private final HVV_Comm_client m_comm_A2E;
    public HVV_Comm_client GetCommA2E() { return m_comm_A2E; }
    
    /**
     * Интерфейс соединения EXECUTOR -> ADMIN
     * По этой линии EXECUTOR сообщает о:<br>
     * текущей исполняемой программе<br>
     * окончании исполнения программы<br>
     * запрашивает у ADMIN'а какие приборы поджигать в 7 и 9 этапах (требуется исключить непрошедшие промежуточный контроль)
     */
    private final HVV_Communication_E2A m_comm_e2a;
    public HVV_Communication_E2A GetCommE2A() { return m_comm_e2a; }
    
    /**
     * Интерфейс соединения ADMIN -> HV
     * По этой линии ADMIN передаёт команды управления в/в частью поста
     */
    private final HVV_Comm_client m_comm_a2h;
    public HVV_Comm_client GetCommA2H() { return m_comm_a2h; }
    
    public HVV_Admin() {
        m_strAMSrootEnvVar = System.getenv( "AMS_ROOT");
        
        //SETTINGS
        m_pSettings = new HVV_AdminSettings( m_strAMSrootEnvVar);
        
        //STEP NAMES
        m_pStepNames = new HVV_AdminStepNames( m_strAMSrootEnvVar);
        
        //PLANNER
        m_pPlanner = new HVV_AdminPlanner( this);
        
        //объект отложенных запусков этапа
        m_pPendings = new HVV_AdminPendings( this);
        m_pPendings.PendingsWatcherStart();
        
        /*
        //ПРОВЕРКА ОДНОВРЕМЕННОГО ЗАПУСКА ТОЛЬКО ОДНОЙ КОПИИ ПРОГРАММЫ
        try {
            m_pSingleInstanceSocketServer = new ServerSocket( GetSettings().GetSingleInstanceSocketServerPort());
        }
        catch( Exception ex) {
            MessageBoxError( "Модуль исполнения программ автоматизации уже запущен.\nПоищите на других \"экранах\".", "Модуль исполнения программ автоматизации");
            logger.error( "Не смогли открыть сокет для проверки запуска только одной копии программы! Программа уже запущена?", ex);
            m_pSingleInstanceSocketServer = null;
            m_pResources = null;
            m_hv_communicator = null;
            m_vac_communicator = null;
            m_poller_communicator = null;
            return;
        }
        */
        
        //RESOURCES
        m_Resources = HVV_Resources.getInstance();
        
        m_fontUsual = new Font( "Cantarell", Font.PLAIN, 15);
        m_fontBold = new Font( "Cantarell", Font.BOLD, 15);
        
        
        
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( new Date( System.currentTimeMillis() - 1000 * 60 * 60 * GetSettings().GetTimeZoneShift()));
        
        m_strAdminStartDtm = String.format( "%02d.%02d.%02d.%02d.%02d.%02d",
                clndr.get(Calendar.YEAR),
                clndr.get(Calendar.MONTH) + 1,
                clndr.get(Calendar.DAY_OF_MONTH),
                clndr.get(Calendar.HOUR_OF_DAY),
                clndr.get(Calendar.MINUTE),
                clndr.get(Calendar.SECOND));
        
        
        
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
            java.util.logging.Logger.getLogger( TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger( TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger( TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger( TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        
        
        //2.1
        m_mapSerials        = new HashMap( 8);
        m_mapDevicePresence = new HashMap( 8);
        m_mapDeviceGetter   = new HashMap( 8);

        //6.1
        m_mapStep6_1_1000mcA  = new HashMap( 8);
        m_mapStep6_1_1100mcA  = new HashMap( 8);
        m_mapStep6_1_1200mcA  = new HashMap( 8);

        //6.2
        m_mapStep6_2_LasThreshold = new HashMap( 8);
        m_mapStep6_2_ExtAn    = new HashMap( 8);
        m_mapStep6_2_ExtTu    = new HashMap( 8);

        //6.3
        m_mapStep6_3_Comments = new HashMap( 8);
        m_mapStep6_3_Continue = new HashMap( 8);

        //8.1
        m_mapDegassing      = new HashMap( 8);

        //10.1
        m_mapActivation     = new HashMap( 8);

        //11.3
        m_mapStep11_3_1000mcA = new HashMap( 8);
        m_mapStep11_3_1100mcA = new HashMap( 8);
        m_mapStep11_3_1200mcA = new HashMap( 8);

        //11.4
        m_mapStep11_4_LasThreshold = new HashMap( 8);
        m_mapStep11_4_ExtAn   = new HashMap( 8);
        m_mapStep11_4_ExtTu   = new HashMap( 8);

        //11.5
        m_mapStep11_5_Comments = new HashMap( 8);
        m_mapStep11_5_Continue = new HashMap( 8);
        

        
        //карта выполненныъ этапов
        m_mapSteps = new TreeMap();
        
        
        //объект хранения текущего состояния
        m_pStateKeeper = new HVV_StateKeeper( this);
        if( m_pStateKeeper.CheckStateKeeperFileExistance()) {
            int nResponce = MessageBoxAskYesNo( "Обнаружен файл хранения состояния.\nВосстановить состояние предыдущего запуска?", "HVV_Admin");
            if( nResponce == YES_OPTION) {
                m_pStateKeeper.RestoreState();
                if( m_pStateKeeper.m_bDropReadState == true) {
                    //2.1
                    m_mapSerials        = new HashMap( 8);
                    m_mapDevicePresence = new HashMap( 8);
                    m_mapDeviceGetter   = new HashMap( 8);

                    //6.1
                    m_mapStep6_1_1000mcA  = new HashMap( 8);
                    m_mapStep6_1_1100mcA  = new HashMap( 8);
                    m_mapStep6_1_1200mcA  = new HashMap( 8);

                    //6.2
                    m_mapStep6_2_LasThreshold = new HashMap( 8);
                    m_mapStep6_2_ExtAn    = new HashMap( 8);
                    m_mapStep6_2_ExtTu    = new HashMap( 8);

                    //6.3
                    m_mapStep6_3_Comments = new HashMap( 8);
                    m_mapStep6_3_Continue = new HashMap( 8);

                    //8.1
                    m_mapDegassing      = new HashMap( 8);

                    //10.1
                    m_mapActivation     = new HashMap( 8);

                    //11.3
                    m_mapStep11_3_1000mcA = new HashMap( 8);
                    m_mapStep11_3_1100mcA = new HashMap( 8);
                    m_mapStep11_3_1200mcA = new HashMap( 8);

                    //11.4
                    m_mapStep11_4_LasThreshold = new HashMap( 8);
                    m_mapStep11_4_ExtAn   = new HashMap( 8);
                    m_mapStep11_4_ExtTu   = new HashMap( 8);

                    //11.5
                    m_mapStep11_5_Comments = new HashMap( 8);
                    m_mapStep11_5_Continue = new HashMap( 8);
        
                    m_mapSteps = new TreeMap();
        
                    m_nCurrentProcessStep = 1;
                }
                else {
                    //m_nCurrentProcessStep = 1;        //выставка этапа должна быть сделана раньше
                }
            }
            else {
                //файл состояния был, но мы отказываемся от него
                m_nCurrentProcessStep = 1;
            }
        }
        else {
            //"чистый" запуск
            m_nCurrentProcessStep = 1;    
        }
        
        m_bCurrentStepInProgress = false;
        
        //COMMUNICATOR. ADMIN -- (get data) --> POLLER
        m_comm_A2P = new HVV_Comm_client( "A2P_CLI: ", m_pSettings.GetPollerPartHost(), m_pSettings.GetPollerPartPort());
        m_comm_A2P.start();


        //COMMUNICATOR. ADMIN -- (load program) --> EXECUTOR
        m_comm_A2E = new HVV_Comm_client( "A2E_CLI: ", m_pSettings.GetExecToPartHost(), m_pSettings.GetExecToPartPort());
        m_comm_A2E.start();

        //COMMUNICATION: EXECUTOR -- (my state) --> ADMIN
        m_comm_e2a = new HVV_Communication_E2A( m_pSettings.GetExecFromPartPort(), this);
        m_comm_e2a.start();
        
        //COMMUNICATION: ADMIN -- (control) --> HV
        m_comm_a2h = new HVV_Comm_client( "A2H_CLI: ", m_pSettings.GetHvPartHost(), m_pSettings.GetHvPartPort());
        m_comm_a2h.start();
        
        /*
        logger.fatal( "****************");
        Double dbl = 1.2e-6;
        logger.fatal( "1.2e-6 -->" + m_ReportGenerator.Gen_NiceDoubleP5( dbl, false));
        
        dbl = 1.3e-5;
        logger.fatal( "1.3e-5 -->" + m_ReportGenerator.Gen_NiceDoubleP5( dbl, false));
        
        dbl = 0.9e-4;
        logger.fatal( "0.9e-4 -->" + m_ReportGenerator.Gen_NiceDoubleP5( dbl, false));
        
        dbl = 1.0e-3;
        logger.fatal( "1.0e-3 -->" + m_ReportGenerator.Gen_NiceDoubleP5( dbl, false));
        
        dbl = 1.2e-2;
        logger.fatal( "1.2e-2 -->" + m_ReportGenerator.Gen_NiceDoubleP5( dbl, false));
        logger.fatal( "****************");
        */
        
        
        
        /*
        double dbl = 120000;
        for( int i=0; i<12; i++) {
            dbl /= 10.;
            logger.fatal( String.format( "%f %.2f %e %.2e ", dbl, dbl, dbl, dbl));
        }
        */
        
        m_bProcessingEnded = false;
        m_ReportGenerator = new ReportGenerator( this);
        m_ReportGenerator.Generate();
    }
    
    class InnerRunnable_021 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_021( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep02_1_Dlg1 dlg = new TechProcessStep02_1_Dlg1( theApp, m_pMainWnd, false);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg2_1() {
        new Thread( new InnerRunnable_021( this)).start();
    }
    
    
    
    class InnerRunnable_023 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_023( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep02_3_Dlg dlg = new TechProcessStep02_3_Dlg( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg2_3() {
        new Thread( new InnerRunnable_023( this)).start();
    }
    
    
    
    class InnerRunnable_081 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_081( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep05_1_Dlg dlg = new TechProcessStep05_1_Dlg( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.initOnStart();
            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg5_1() {
        new Thread( new InnerRunnable_081( this)).start();
    }
    
    
    
    class InnerRunnable_082 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_082( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep05_2_Dlg dlg = new TechProcessStep05_2_Dlg( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg5_2() {
        new Thread( new InnerRunnable_082( this)).start();
    }
    
    
    
    class InnerRunnable_083 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_083( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep05_3_Dlg dlg = new TechProcessStep05_3_Dlg( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg5_3() {
        new Thread( new InnerRunnable_083( this)).start();
    }
    
    
    
    class InnerRunnable_101 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_101( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep06_1A_Dlg dlg = new TechProcessStep06_1A_Dlg( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg6_1A() {
        new Thread( new InnerRunnable_101( this)).start();
    }
    
    
    
    class InnerRunnable_102 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_102( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep06_2_Dlg dlg = new TechProcessStep06_2_Dlg( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg6_2() {
        new Thread( new InnerRunnable_102( this)).start();
    }
    
    
    
    class InnerRunnable_103 implements Runnable {
        HVV_Admin theApp;
        
        public InnerRunnable_103( HVV_Admin app) {
            theApp = app;
        }
        
        @Override
        public void run() {
            TechProcessStep06_3_Dlg dlg = new TechProcessStep06_3_Dlg( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
        }
    }
    
    public void ShowDlg6_3() {
        new Thread( new InnerRunnable_103( this)).start();
    }
    
    
    
    class InnerRunnable_141 implements Runnable {
        HVV_Admin theApp;
        int m_nDelay;
        
        public InnerRunnable_141( HVV_Admin app, int nDelay) {
            theApp = app;
            m_nDelay = nDelay;
        }
        
        @Override
        public void run() {
            try {
                sleep( m_nDelay);
            } catch (InterruptedException ex) {
                logger.error( "Interrupted Exception caught!", ex);
            }
            
            m_pMainWnd.setVisible( false);
        
            TechProcessStep08_1_Dlg_M dlg = new TechProcessStep08_1_Dlg_M( theApp, m_pMainWnd, false);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( 0, 0);
        }
    }
    
    public void ShowDlg8( int nDelay) {
        new Thread( new InnerRunnable_141( this, nDelay)).start();
    }
    
    
    
    class InnerRunnable_181 implements Runnable {
        HVV_Admin theApp;
        int m_nDelay;
        
        public InnerRunnable_181( HVV_Admin app, int nDelay) {
            theApp = app;
            m_nDelay = nDelay;
        }
        
        @Override
        public void run() {
            try {
                sleep( m_nDelay);
            } catch (InterruptedException ex) {
                logger.error( "Interrupted Exception caught!", ex);
            }
            
            TechProcessStep10_1_Dlg_M dlg = new TechProcessStep10_1_Dlg_M( theApp, m_pMainWnd, false);
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();

            dlg.setVisible( true);
            //dlg.setLocation( rect.width - dlg.getWidth(), 0);
            dlg.setLocation( 0, 0);
        }
    }
    
    public void ShowDlg10( int nDelay) {
        new Thread( new InnerRunnable_181( this, nDelay)).start();
    }
    
    public void ShowDlg11_3A() {
        TechProcessStep11_3A_Dlg dlg = new TechProcessStep11_3A_Dlg( this, m_pMainWnd, false);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    
        dlg.setVisible( true);
        //dlg.setLocation( rect.width - dlg.getWidth(), 0);
        dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
    }
        
    public void ShowDlg11_4() {
        TechProcessStep11_4_Dlg dlg = new TechProcessStep11_4_Dlg( this, m_pMainWnd, false);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    
        dlg.setVisible( true);
        //dlg.setLocation( rect.width - dlg.getWidth(), 0);
        dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
    }
    
    public void ShowDlg11_5() {
        TechProcessStep11_5_Dlg dlg = new TechProcessStep11_5_Dlg( this, m_pMainWnd, false);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    
        dlg.setVisible( true);
        //dlg.setLocation( rect.width - dlg.getWidth(), 0);
        dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
    }
    
    public void ShowDlg11_6() {
        TechProcessStep11_6_Dlg dlg = new TechProcessStep11_6_Dlg( this, m_pMainWnd, false);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    
        dlg.setVisible( true);
        //dlg.setLocation( rect.width - dlg.getWidth(), 0);
        dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
    }
    
    public void ShowDlg12_3() {
        TechProcessStep12_3_Dlg dlg = new TechProcessStep12_3_Dlg( this, m_pMainWnd, false);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    
        dlg.setVisible( true);
        //dlg.setLocation( rect.width - dlg.getWidth(), 0);
        dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
    }
    
    public void ShowDlg13_2() {
        TechProcessStep13_2_Dlg dlg = new TechProcessStep13_2_Dlg( this, m_pMainWnd, false);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
    
        dlg.setVisible( true);
        //dlg.setLocation( rect.width - dlg.getWidth(), 0);
        dlg.setLocation( ( rect.width - dlg.getWidth()) / 2, ( rect.height - dlg.getHeight()) / 2);
    }
    
    public void start() {
        
        
        m_pMainWnd = new HVV_Admin_MainFrame( this);
        
        m_pMainWnd.m_pPanel.SetStates();
        m_pMainWnd.m_pPanel.Reposition();
        
        java.awt.EventQueue.invokeLater( new Runnable() {
            public void run() {
                m_pMainWnd.setVisible( true);
            }
        });
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String strAMSrootEnvVar = System.getenv( "AMS_ROOT");
        if( strAMSrootEnvVar == null) {
            MessageBoxError( "Не задана переменная окружения AMS_ROOT!", "HVV_Admin");
            return;
        }
        
        String strlog4jPropertiesFile = strAMSrootEnvVar + "/etc/log4j.admin.properties";
        File file = new File( strlog4jPropertiesFile);
        if(!file.exists()) {
            System.out.println("It is not possible to load the given log4j properties file :" + file.getAbsolutePath());
            BasicConfigurator.configure();
        }
        else
            PropertyConfigurator.configure( file.getAbsolutePath());
        
        logger.info( "HVV_Admin::main(): in. Start point!");
        
        
        new HVV_Admin().start();
    }
    
    /**
     * Функция для сообщения пользователю информационного сообщения
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static void MessageBoxInfo( String strMessage, String strTitleBar)
    {
        JOptionPane.showMessageDialog( null, strMessage, strTitleBar, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Функция для сообщения пользователю сообщения об ошибке
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static void MessageBoxError( String strMessage, String strTitleBar)
    {
        JOptionPane.showMessageDialog( null, strMessage, strTitleBar, JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Функция для запроса у пользователя простого ответа Да/Нет
     * @param strMessage сообщение
     * @param strTitleBar заголовок
     */
    public static int MessageBoxAskYesNo( String strMessage, String strTitleBar)
    {
        //UIManager.put( "YES", "Да");
        //UIManager.put( "No", "Нет");
        
        UIManager.put("OptionPane.noButtonText", "Нет");
        UIManager.put("OptionPane.okButtonText", "Согласен");
        UIManager.put("OptionPane.yesButtonText", "Да");
    
        int nDialogResult = JOptionPane.showConfirmDialog( null, strMessage, strTitleBar, JOptionPane.YES_NO_OPTION);
        return nDialogResult;
    }
    
    public String strFormatDate( Date dt) {
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( dt);

        String strResult;
        switch( clndr.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:       strResult = "вс "; break;
            case Calendar.MONDAY:       strResult = "пн "; break;
            case Calendar.TUESDAY:      strResult = "вт "; break;
            case Calendar.WEDNESDAY:    strResult = "ср "; break;
            case Calendar.THURSDAY:     strResult = "чт "; break;
            case Calendar.FRIDAY:       strResult = "пт "; break;
            case Calendar.SATURDAY:     strResult = "сб "; break;
            default: strResult = "";
        }
        
        
        strResult += String.format( "%02d.%02d.%02d",
                clndr.get(Calendar.DAY_OF_MONTH),
                clndr.get(Calendar.MONTH) + 1,
                clndr.get(Calendar.YEAR));
        
        return strResult;
    }
    
    public String strFormatTime( Date dt) {
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( dt);

        String strResult = String.format( "%02d:%02d:%02d",
                clndr.get(Calendar.HOUR_OF_DAY),
                clndr.get(Calendar.MINUTE),
                clndr.get(Calendar.SECOND));
        
        return strResult;
    }
    
    public String strGetCurrentDate() {
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( new Date( System.currentTimeMillis() - 1000 * 60 * 60 * GetSettings().GetTimeZoneShift()));

        String strResult="";
        /*switch( clndr.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:       strResult = "вс "; break;
            case Calendar.MONDAY:       strResult = "пн "; break;
            case Calendar.TUESDAY:      strResult = "вт "; break;
            case Calendar.WEDNESDAY:    strResult = "ср "; break;
            case Calendar.THURSDAY:     strResult = "чт "; break;
            case Calendar.FRIDAY:       strResult = "пт "; break;
            case Calendar.SATURDAY:     strResult = "сб "; break;
            default: strResult = "";
        }*/
        
        strResult += String.format( "%02d.%02d.%02d",
                clndr.get(Calendar.YEAR),
                clndr.get(Calendar.MONTH) + 1,
                clndr.get(Calendar.DAY_OF_MONTH));
        
        return strResult;
    }
    
    public String strGetCurrentTime() {
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( new Date( System.currentTimeMillis() - 1000 * 60 * 60 * GetSettings().GetTimeZoneShift()));

        String strResult = String.format( "%02d:%02d:%02d",
                clndr.get(Calendar.HOUR_OF_DAY),
                clndr.get(Calendar.MINUTE),
                clndr.get(Calendar.SECOND));
        
        return strResult;
    }
    
    public Date GetLocalDate() {
        Date dt = new Date( System.currentTimeMillis() - 1000 * 60 * 60 * GetSettings().GetTimeZoneShift());
        return dt;
    }
    
    public double GetFromPoller( String strParam) {
        double dblResult = Double.NaN;
        GetExecutor executor = new GetExecutor( this, strParam);
        executor.StartThread();
        
        boolean bContinue = true;
        int nCounter = 0;
        
        do {
            if( executor.IsAnswerReceived()) {
                dblResult = executor.GetResult();
                break;
            }
            
            if( executor.IsTimeOutHappens())
                break;
            
            if( ++nCounter == 50)
                break;
            
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                logger.error( "InterruptedException caught: ", ex);
                bContinue = false;
            }
            
        } while( bContinue);
        
        return dblResult;
    }
}
