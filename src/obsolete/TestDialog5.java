/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsolete;

import hvv_admin.dialogs.TechProcessPanel5;
import java.awt.Font;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author yaroslav
 */



public class TestDialog5 extends javax.swing.JDialog {
    /*
    class EmulationActionListener implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean bContinue = false;
            int nNextStep = -1;
            JLabel lblIcoExec = null;
                    
            switch( m_nCurrentProcessStep) {
                
                //1. Заполнение рабочей камеры азотом
                case 1: bContinue = false; lblIcoExec = m_pPanel.pnlStep1.lbl_01_01_IcoStep; break;
                    
                //2. Установка приборов
                //2.1 Занесение информации об установленных приборах
                //2.2 Предварительная откачка
                //2.3 Проверка герметичности установки приборов
                //2.4 Основная откачка
                case 21: bContinue = m_pPanel.pnlStep2.chk_02_02_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep2.lbl_02_01_IcoStep; break;
                case 22: bContinue = false;                                              lblIcoExec = m_pPanel.pnlStep2.lbl_02_02_IcoStep; break;
                case 23: bContinue = m_pPanel.pnlStep2.chk_02_04_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep2.lbl_02_03_IcoStep; break;
                case 24: bContinue = m_pPanel.pnlStep3.chk_03_01_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep2.lbl_02_04_IcoStep; break;

                //3. Обработка в среде кислорода
                //3.1 Напуск кислорода в приборы
                //3.2 Обработка. 1ый цикл
                //3.3 Откачка кислорода
                //3.4 Напуск кислорода в приборы
                //3.5 Обработка. 2ой цикл
                //3.6 Откачка кислорода
                case 41: bContinue = m_pPanel.pnlStep3.chk_03_02_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep3.lbl_03_01_IcoStep; break;
                case 42: bContinue = m_pPanel.pnlStep3.chk_03_03_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep3.lbl_03_02_IcoStep; break;
                case 43: bContinue = m_pPanel.pnlStep3.chk_03_04_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep3.lbl_03_03_IcoStep; break;
                case 44: bContinue = m_pPanel.pnlStep3.chk_03_05_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep3.lbl_03_04_IcoStep; break;
                case 45: bContinue = m_pPanel.pnlStep3.chk_03_06_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep3.lbl_03_05_IcoStep; break;
                //case 46: bContinue = m_pPanel.pnlStep3.chk_03_07_AutoStart.isSelected(); lblIcoExec = m_pPanel.pnlStep2.lbl_03_06_IcoStep; break;
                                        
                //4. Обработка в среде кислород-неона.
                //4.1 Напуск кислород-неона в приборы
                //4.2 Обработка. 1ый цикл.
                //4.3 Откачка газовой смеси
                //4.4 Напуск кислород-неона в приборы
                //4.5 Обработка. 2ой цикл.
                //4.6 Откачка газовой смеси
                
                //5. Термообезгаживание
                //5.1 Переход на основную откачку (2 и 1)
                //5.2 Установка печек
                //5.3 Включение PID-регулирования печек
                //5.4 Снятие печек
                //5.5 Заполнение рабочей смесью

                //6. Предварительная оценка параметров приборов
                //6.1 Внесение комментариев
                //6.2 Внесение пороговых токов
                //6.3 Измерение ВАХ
                //6.4 Промежуточная оценка параметров (снятие не годных)

                //7. Тренировка катода
                //7.1 Откачка рабочей смеси
                //7.2 Напуск тренировочной смеси в приборы
                //7.3 Обработка. 1ый цикл.
                //7.4 Откачка тренировочной смеси
                //7.5 Напуск тренировочной смеси в приборы
                //7.6 Обработка. 2ой цикл.
                //7.7 Откачка тренировочной смеси
                //7.8 Напуск тренировочной смеси в приборы
                //7.9 Обработка. 3ий цикл.
                //7.10 Откачка тренировочной смеси

                //8. Обезгаживание рабочих геттеров
                //8.1 Переход на основную откачку
                //8.2 Подготовка
                //8.3 Обезгаживание

                //9. Тренировка в тренировочной смеси
                //9.1  Напуск тренировочной смеси в приборы
                //9.2 Обработка. 1ый цикл.
                //9.3 Откачка тренировочной смеси
                //9.4 Напуск тренировочной смеси в приборы
                //9.5 Обработка. 2ой цикл.
                //9.6 Откачка тренировочной смеси

                //10. Активация рабочих геттеров
                //10.1 Переход на основную откачку
                //10.2 Подготовка
                //10.3 Активация

                //11. Выходная оценка параметров приборов
                //11.1 Заполнение рабочей смесью
                //11.2 Внесение пороговых токов
                //11.3 Измерение ВАХ
                //11.4 Герметизация годных приборов
                    
                //12. Снятие непрошедших приборов (опционально)
                //12.1 Напуск азота в приборы
                //12.2 Снятие непрошедших приборов
                //12.3 Предварительная откачка
                //12.4 Проверкагерметичности (?? да  ?? нет)
                //12.5 Основная откачка
         
                

                    
            }

            //DEBUG: тормозим таймер эмуляции
            m_EmuTimer.stop();
                        
            //проигрываем звуковой сигнал о получении сигнала об окончании программы Executor'a
            String strAMSRoot = "/home/yaroslav/HVV_HOME";
            SoundPlayThread thr = new SoundPlayThread( strAMSRoot + "/res/sound/level.wav");
            try {
                thr.start();
                thr.join();
            } catch (InterruptedException ex) {
                //Logger.getLogger(TestDialog5.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //отображаем иконку что получен сигнал от executor'a
            if( lblIcoExec != null)
                lblIcoExec.setIcon( m_icoExecutor);

            //переключаемся на следующий этап
            m_nCurrentProcessStep = SwitchToNextPhase( m_nCurrentProcessStep);
            

            
            if( bContinue == true) {
                //если следующий этап авто-запускаемый
                //DEBUG: запускаем таймер эмуляции
                m_EmuTimer.start();
            }

            //перерисовываем экран
            m_pPanel.Reposition();
        }
        
    }
    
    public int SwitchToNextPhase( int nCurrentPhase) {
        
        int nNextPhase = -1;
        
        JButton oldPhaseButton = null;
        JLabel oldPhaseTitleLabel1 = null;
        JLabel oldPhaseTitleLabel2 = null;
        JCheckBox oldPhaseCheck = null;
        
        JCheckBox newPhaseCheck = null;
        JButton newPhaseButton = null;
        JLabel newPhaseTitleLabel1 = null;
        JLabel newPhaseTitleLabel2 = null;
        JLabel newPhaseDate1 = null;
        JLabel newPhaseTime1 = null;
        JLabel newPhaseDate2 = null;
        JLabel newPhaseTime2 = null;
        switch( nCurrentPhase) {
            
            //1. Заполнение рабочей камеры азотом
            //1.1 Заполнение рабочей камеры азотом
            case  1:
                nNextPhase = 21;
                oldPhaseButton = m_pPanel.pnlStep1.btn_01_01_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep1.lbl_01_00_Title;
                oldPhaseTitleLabel2 = m_pPanel.pnlStep1.lbl_01_01_Title;
                //oldPhaseCheck = m_pPanel.pnlStep1.chk_01_01_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep2.btn_02_01_Action;
                newPhaseTitleLabel1= m_pPanel.pnlStep2.lbl_02_00_Title;
                newPhaseTitleLabel2= m_pPanel.pnlStep2.lbl_02_01_Title;
                //newPhaseCheck = m_pPanel.pnlStep2.chk_02_01_AutoStart;
                
                newPhaseDate1 = m_pPanel.pnlStep2.lbl_02_00_Date_start;
                newPhaseTime1 = m_pPanel.pnlStep2.lbl_02_00_Time_start;
                newPhaseDate2 = m_pPanel.pnlStep2.lbl_02_01_Date_start;
                newPhaseTime2 = m_pPanel.pnlStep2.lbl_02_01_Time_start;
                
                m_pPanel.pnlStep1.m_bCollapsed = true;
                m_pPanel.pnlStep2.m_bCollapsed = false;
            break;

            //2. Установка приборов
            //2.1 Занесение информации об установленных приборах
            //2.2 Предварительная откачка
            //2.3 Проверка герметичности установки приборов
            //2.4 Основная откачка
            case 21:
                nNextPhase = 22;
                oldPhaseButton = m_pPanel.pnlStep2.btn_02_01_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep2.lbl_02_01_Title;
                //oldPhaseCheck = m_pPanel.pnlStep2.chk_02_01_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep2.btn_02_02_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep2.lbl_02_02_Title;
                newPhaseCheck = m_pPanel.pnlStep2.chk_02_02_AutoStart;
                newPhaseDate1 = m_pPanel.pnlStep2.lbl_02_02_Date_start;
                newPhaseTime1 = m_pPanel.pnlStep2.lbl_02_02_Time_start;
            break;
            case 22:
                nNextPhase = 23;
                oldPhaseButton = m_pPanel.pnlStep2.btn_02_02_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep2.lbl_02_02_Title;
                oldPhaseCheck = m_pPanel.pnlStep2.chk_02_02_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep2.btn_02_03_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep2.lbl_02_03_Title;
                //newPhaseCheck = m_pPanel.pnlStep2.chk_02_03_AutoStart;
                
                newPhaseDate1 = m_pPanel.pnlStep2.lbl_02_03_Date_start;
                newPhaseTime1 = m_pPanel.pnlStep2.lbl_02_03_Time_start;
            break;
            case 23:
                nNextPhase = 24;
                oldPhaseButton = m_pPanel.pnlStep2.btn_02_03_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep2.lbl_02_03_Title;
                //oldPhaseCheck = m_pPanel.pnlStep2.chk_02_03_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep2.btn_02_04_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep2.lbl_02_04_Title;
                newPhaseCheck = m_pPanel.pnlStep2.chk_02_04_AutoStart;
                
                newPhaseDate1 = m_pPanel.pnlStep2.lbl_02_04_Date_start;
                newPhaseTime1 = m_pPanel.pnlStep2.lbl_02_04_Time_start;
            break;
            case 24:
                nNextPhase = 41;
                oldPhaseButton = m_pPanel.pnlStep2.btn_02_04_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep2.lbl_02_00_Title;
                oldPhaseTitleLabel2 = m_pPanel.pnlStep2.lbl_02_04_Title;
                oldPhaseCheck = m_pPanel.pnlStep2.chk_02_04_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep3.btn_03_01_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_00_Title;
                newPhaseTitleLabel2 = m_pPanel.pnlStep3.lbl_03_01_Title;
                newPhaseCheck = m_pPanel.pnlStep3.chk_03_01_AutoStart;
                
                newPhaseDate1 = m_pPanel.pnlStep3.lbl_03_00_Date;
                newPhaseTime1 = m_pPanel.pnlStep3.lbl_03_00_Time;
                newPhaseDate2 = m_pPanel.pnlStep3.lbl_03_01_Date;
                newPhaseTime2 = m_pPanel.pnlStep3.lbl_03_01_Time;
                
                m_pPanel.pnlStep2.m_bCollapsed = true;
                m_pPanel.pnlStep3.m_bCollapsed = false;
            break;

            //3. Обработка в среде кислорода
            //3.1 Напуск кислорода в приборы
            //3.2 Обработка. 1ый цикл
            //3.3 Откачка кислорода
            //3.4 Напуск кислорода в приборы
            //3.5 Обработка. 2ой цикл
            //3.6 Откачка кислорода
            case 41:
                nNextPhase = 42;
                oldPhaseButton = m_pPanel.pnlStep3.btn_03_01_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_01_Title;
                oldPhaseCheck = m_pPanel.pnlStep3.chk_03_01_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep3.btn_03_02_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_02_Title;
                newPhaseCheck = m_pPanel.pnlStep3.chk_03_02_AutoStart;
                newPhaseDate1 = m_pPanel.pnlStep3.lbl_03_02_Date;
                newPhaseTime1 = m_pPanel.pnlStep3.lbl_03_02_Time;
            break;
            case 42:
                nNextPhase = 43;
                oldPhaseButton = m_pPanel.pnlStep3.btn_03_02_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_02_Title;
                oldPhaseCheck = m_pPanel.pnlStep3.chk_03_02_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep3.btn_03_03_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_03_Title;
                newPhaseCheck = m_pPanel.pnlStep3.chk_03_03_AutoStart;
                newPhaseDate1 = m_pPanel.pnlStep3.lbl_03_03_Date;
                newPhaseTime1 = m_pPanel.pnlStep3.lbl_03_03_Time;
            break;
            case 43:
                nNextPhase = 44;
                oldPhaseButton = m_pPanel.pnlStep3.btn_03_03_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_03_Title;
                oldPhaseCheck = m_pPanel.pnlStep3.chk_03_03_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep3.btn_03_04_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_04_Title;
                newPhaseCheck = m_pPanel.pnlStep3.chk_03_04_AutoStart;
                newPhaseDate1 = m_pPanel.pnlStep3.lbl_03_04_Date;
                newPhaseTime1 = m_pPanel.pnlStep3.lbl_03_04_Time;
            break;
            case 44:
                nNextPhase = 45;
                oldPhaseButton = m_pPanel.pnlStep3.btn_03_04_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_00_Title;
                oldPhaseTitleLabel2 = m_pPanel.pnlStep3.lbl_03_04_Title;
                oldPhaseCheck = m_pPanel.pnlStep3.chk_03_04_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep3.btn_03_05_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_05_Title;
                newPhaseCheck = m_pPanel.pnlStep3.chk_03_05_AutoStart;
                newPhaseDate1 = m_pPanel.pnlStep3.lbl_03_05_Date;
                newPhaseTime1 = m_pPanel.pnlStep3.lbl_03_05_Time;
            break;
            case 45:
                nNextPhase = 46;
                oldPhaseButton = m_pPanel.pnlStep3.btn_03_05_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_05_Title;
                oldPhaseCheck = m_pPanel.pnlStep3.chk_03_05_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep3.btn_03_06_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_06_Title;
                newPhaseCheck = m_pPanel.pnlStep3.chk_03_06_AutoStart;
                newPhaseDate1 = m_pPanel.pnlStep3.lbl_03_06_Date;
                newPhaseTime1 = m_pPanel.pnlStep3.lbl_03_06_Time;
            break;
            case 46:
                nNextPhase = 61;
                oldPhaseButton = m_pPanel.pnlStep3.btn_03_06_Action;
                oldPhaseTitleLabel1 = m_pPanel.pnlStep3.lbl_03_00_Title;
                oldPhaseTitleLabel2 = m_pPanel.pnlStep3.lbl_03_06_Title;
                oldPhaseCheck = m_pPanel.pnlStep3.chk_03_06_AutoStart;
                
                newPhaseButton = m_pPanel.pnlStep4.btn_04_01_Action;
                newPhaseTitleLabel1 = m_pPanel.pnlStep4.lbl_04_00_Title;
                newPhaseTitleLabel2 = m_pPanel.pnlStep4.lbl_04_01_Title;
                newPhaseCheck = m_pPanel.pnlStep4.chk_04_01_AutoStart;                
                newPhaseDate1 = m_pPanel.pnlStep4.lbl_04_00_Date;
                newPhaseTime1 = m_pPanel.pnlStep4.lbl_04_00_Time;
                newPhaseDate2 = m_pPanel.pnlStep4.lbl_04_01_Date;
                newPhaseTime2 = m_pPanel.pnlStep4.lbl_04_01_Time;
                
                m_pPanel.pnlStep3.m_bCollapsed = true;
                m_pPanel.pnlStep4.m_bCollapsed = false;
            break;

            //4. Обработка в среде кислород-неона.
            //4.1 Напуск кислород-неона в приборы
            //4.2 Обработка. 1ый цикл.
            //4.3 Откачка газовой смеси
            //4.4 Напуск кислород-неона в приборы
            //4.5 Обработка. 2ой цикл.
            //4.6 Откачка газовой смеси

            //5. Термообезгаживание
            //5.1 Переход на основную откачку (2 и 1)
            //5.2 Установка печек
            //5.3 Включение PID-регулирования печек
            //5.4 Снятие печек
            //5.5 Заполнение рабочей смесью

            //6. Предварительная оценка параметров приборов
            //6.1 Внесение комментариев
            //6.2 Внесение пороговых токов
            //6.3 Измерение ВАХ
            //6.4 Промежуточная оценка параметров (снятие не годных)

            //7. Тренировка катода
            //7.1 Откачка рабочей смеси
            //7.2 Напуск тренировочной смеси в приборы
            //7.3 Обработка. 1ый цикл.
            //7.4 Откачка тренировочной смеси
            //7.5 Напуск тренировочной смеси в приборы
            //7.6 Обработка. 2ой цикл.
            //7.7 Откачка тренировочной смеси
            //7.8 Напуск тренировочной смеси в приборы
            //7.9 Обработка. 3ий цикл.
            //7.10 Откачка тренировочной смеси

            //8. Обезгаживание рабочих геттеров
            //8.1 Переход на основную откачку
            //8.2 Подготовка
            //8.3 Обезгаживание

            //9. Тренировка в тренировочной смеси
            //9.1  Напуск тренировочной смеси в приборы
            //9.2 Обработка. 1ый цикл.
            //9.3 Откачка тренировочной смеси
            //9.4 Напуск тренировочной смеси в приборы
            //9.5 Обработка. 2ой цикл.
            //9.6 Откачка тренировочной смеси

            //10. Активация рабочих геттеров
            //10.1 Переход на основную откачку
            //10.2 Подготовка
            //10.3 Активация

            //11. Выходная оценка параметров приборов
            //11.1 Заполнение рабочей смесью
            //11.2 Внесение пороговых токов
            //11.3 Измерение ВАХ
            //11.4 Герметизация годных приборов

            //12. Снятие непрошедших приборов (опционально)
            //12.1 Напуск азота в приборы
            //12.2 Снятие непрошедших приборов
            //12.3 Предварительная откачка
            //12.4 Проверкагерметичности (?? да  ?? нет)
            //12.5 Основная откачка
         
        }
        
        if( oldPhaseButton != null) oldPhaseButton.setVisible( false);
        if( oldPhaseTitleLabel1 != null) oldPhaseTitleLabel1.setFont( m_fontUsual);
        if( oldPhaseTitleLabel1 != null) oldPhaseTitleLabel1.setEnabled( false);
        if( oldPhaseTitleLabel2 != null) oldPhaseTitleLabel2.setFont( m_fontUsual);
        if( oldPhaseTitleLabel2 != null) oldPhaseTitleLabel2.setEnabled( false);
        if( oldPhaseCheck != null) oldPhaseCheck.setEnabled( false);
        
        if( newPhaseCheck != null) {
            newPhaseCheck.setEnabled( false);
            if( newPhaseCheck.isSelected() == false) {
                newPhaseButton.setText( "С Т А Р Т");
            }
            else {
                if( newPhaseDate1 != null) newPhaseDate1.setText( strGetCurrentDate());
                if( newPhaseTime1 != null) newPhaseTime1.setText( strGetCurrentTime());
                if( newPhaseDate2 != null) newPhaseDate2.setText( strGetCurrentDate());
                if( newPhaseTime2 != null) newPhaseTime2.setText( strGetCurrentTime());
            }
        }
        else {
            if( newPhaseDate1 != null) newPhaseDate1.setText( strGetCurrentDate());
            if( newPhaseTime1 != null) newPhaseTime1.setText( strGetCurrentTime());
            if( newPhaseDate2 != null) newPhaseDate2.setText( strGetCurrentDate());
            if( newPhaseTime2 != null) newPhaseTime2.setText( strGetCurrentTime());
        }
        
        if( newPhaseButton != null) newPhaseButton.setVisible( true);
        if( newPhaseTitleLabel1 != null) newPhaseTitleLabel1.setFont( m_fontBold);
        if( newPhaseTitleLabel2 != null) newPhaseTitleLabel2.setFont( m_fontBold);
        
        return nNextPhase;
    }
    */
    public String strGetCurrentDate() {
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( new Date( System.currentTimeMillis() - 1000 * 60 * 60 * 1));

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
    
    public String strGetCurrentTime() {
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( new Date( System.currentTimeMillis() - 1000 * 60 * 60 * 1));

        String strResult = String.format( "%02d:%02d:%02d",
                clndr.get(Calendar.HOUR_OF_DAY),
                clndr.get(Calendar.MINUTE),
                clndr.get(Calendar.SECOND));
        
        return strResult;
    }
    
    public Timer m_EmuTimer;
    //public EmulationActionListener m_EmuTimerListener;
    
    TechProcessPanel5 m_pPanel;
    
    Icon m_icoRight;
    Icon m_icoDown;
    Icon m_icoExecutor;
    
    int m_nCurrentProcessStep = -1;
    
    public Font m_fontUsual;
    public Font m_fontBold;
    
    private ImageIcon LoadIconShortCut( String strFilePathName) {
        File f = new File( strFilePathName);
        if(f.exists() && !f.isDirectory()) {
            return new ImageIcon( strFilePathName);
        }
        else {
            //logger.warn( "File not found: " + strFilePathName);
            return null;
        }
    }
    
    
    /**
     * Creates new form TestDialog
     */
    public TestDialog5(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        String strAMSRoot = "/home/yaroslav/HVV_HOME";
        m_icoRight      = LoadIconShortCut( strAMSRoot + "/res/images/right.gif");
        m_icoDown       = LoadIconShortCut( strAMSRoot + "/res/images/down.gif");
        m_icoExecutor   = LoadIconShortCut( strAMSRoot + "/res/images/green_little_bright.gif");
        
        //jScrollBar1.setMaximum( 1650);
        
       // m_pPanel = new TechProcessPanel5( this);
        m_pPanel.setVisible( true);
        pnlPanel.add( m_pPanel);
        
        m_pPanel.setBounds( 2, 2, 900, 850);
        
        m_fontUsual = new Font( "Cantarell", Font.PLAIN, 15);
        m_fontBold = new Font( "Cantarell", Font.BOLD, 15);
        
        //m_EmuTimerListener = new EmulationActionListener();
        //m_EmuTimer = new Timer( 5000, m_EmuTimerListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPanel = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Административный модуль, v1.0.0.0, (2016.03.03 16:45)  (С) ФЛАВТ, 2016. ");
        setMinimumSize(new java.awt.Dimension(950, 1000));
        getContentPane().setLayout(null);

        pnlPanel.setBorder(null);
        pnlPanel.setMaximumSize(new java.awt.Dimension(504, 404));
        pnlPanel.setMinimumSize(new java.awt.Dimension(504, 404));
        pnlPanel.setPreferredSize(new java.awt.Dimension(504, 404));
        pnlPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlPanelMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 904, Short.MAX_VALUE)
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 854, Short.MAX_VALUE)
        );

        getContentPane().add(pnlPanel);
        pnlPanel.setBounds(10, 40, 904, 854);

        jButton1.setText("Выход");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(840, 920, 100, 34);

        jScrollBar1.setMaximum(215);
        jScrollBar1.setPreferredSize(new java.awt.Dimension(20, 304));
        jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar1AdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(jScrollBar1);
        jScrollBar1.setBounds(920, 40, 20, 860);

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel1.setText("<html><u>Этапы технологичеcкого процесса:</u></html>");
        jLabel1.setBorder(null);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 6, 530, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jScrollBar1AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar1AdjustmentValueChanged
        if( m_pPanel != null)
            m_pPanel.setBounds( 2, 2 - jScrollBar1.getValue(), 900, 850 + jScrollBar1.getValue());
    }//GEN-LAST:event_jScrollBar1AdjustmentValueChanged

    private void pnlPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlPanelMouseWheelMoved
        if( jScrollBar1.isEnabled()) {
            int nMin = jScrollBar1.getMinimum();
            int nMax = jScrollBar1.getMaximum();
            int nPos = jScrollBar1.getValue();
            int nStep1 = jScrollBar1.getBlockIncrement();
            int nStep2 = jScrollBar1.getUnitIncrement();

            int nNextPos = nPos + evt.getWheelRotation() * nStep1;

            if( nNextPos < nMin) nNextPos = nMin;
            if( nNextPos > nMax) nNextPos = nMax;
            jScrollBar1.setValue( nNextPos);
        }
    }//GEN-LAST:event_pnlPanelMouseWheelMoved

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
                System.out.println( info.getName());
                if ("GTK+".equals( info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestDialog5.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TestDialog5 dialog = new TestDialog5(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JPanel pnlPanel;
    // End of variables declaration//GEN-END:variables
}
