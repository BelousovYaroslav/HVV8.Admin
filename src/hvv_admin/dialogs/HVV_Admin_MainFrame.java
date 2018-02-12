/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs;

import hvv_admin.steps.info.TechProcessStepInfo;
import hvv_admin.SoundPlayThread;
import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.ToolTipManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class HVV_Admin_MainFrame extends javax.swing.JFrame {

    class EmulationActionListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean bContinue = false;
            boolean bHandStep = false;
            
            String strExecutorProgram = null;
            String strReportStartTitle = null;
            String strReportStopTitle = null;
            
            switch( theApp.GetCurrentStep()) {
                
                //1. Заполнение рабочей камеры азотом
                case 1:
                    bContinue = false; bHandStep = true; theApp.ShowDlg2_1();
                    strReportStopTitle = "Завершение напуска азота";
                    strReportStartTitle = "";
                    strExecutorProgram = "AdminStep_2.1.xml";
                break;
                    
                //2. Установка приборов
                //2.1 Занесение информации об установленных приборах
                //2.2 Предварительная откачка
                //2.3 Проверка герметичности установки приборов
                //2.4 Основная откачка
                case 21:                    
                    //РУЧНОЙ ЭТАП! МЫ СЮДА НЕ ПОПАДАЕМ
                    bContinue = m_pPanel.pnlStep2.chk_02_02_AutoStart.isSelected();
                    strReportStopTitle = "Если это видно, скажите Ярославу код 01";
                    strReportStartTitle = "Если это видно, скажите Ярославу код 02";
                    strExecutorProgram = "AdminStep_2.2.xml";
                break;
                case 22:
                    bContinue = false; bHandStep = true; theApp.ShowDlg2_3();
                    strReportStopTitle = "Завершение предварительной откачки";
                    strReportStartTitle = "";
                    strExecutorProgram = "AdminStep_2.3.xml";
                break;
                case 23:
                    //РУЧНОЙ ЭТАП! МЫ СЮДА НЕ ПОПАДАЕМ
                    bContinue = m_pPanel.pnlStep2.chk_02_04_AutoStart.isSelected();
                    strReportStopTitle = "Если это видно, скажите Ярославу код 03";
                    strReportStartTitle = "Если это видно, скажите Ярославу код 04";
                    strExecutorProgram = "AdminStep_2.4.xml";
                break;
                case 24:
                    bContinue = m_pPanel.pnlStep3.chk_03_01_AutoStart.isSelected();
                    strReportStopTitle = "Завершение основной откачки";
                    strReportStartTitle = "Старт напуска кислорода";
                    strExecutorProgram = "AdminStep_3.1.xml";
                break;

                //3. Обработка в среде кислорода
                //3.1 Напуск кислорода в приборы
                //3.2 Обработка. 1ый цикл
                //3.3 Откачка кислорода
                //3.4 Напуск кислорода в приборы
                //3.5 Обработка. 2ой цикл
                //3.6 Откачка кислорода
                case 41:
                    bContinue = m_pPanel.pnlStep3.chk_03_02_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска кислорода";
                    strReportStartTitle = "Старт 1-го цикла обработки в кислороде";
                    strExecutorProgram = "AdminStep_3.2.xml";
                break;
                    
                case 42:
                    bContinue = m_pPanel.pnlStep3.chk_03_03_AutoStart.isSelected();
                    strReportStopTitle = "Завершение 1-го цикла обработки в кислороде";
                    strReportStartTitle = "Старт откачки кислорода";
                    strExecutorProgram = "AdminStep_3.3.xml";
                break;
                    
                case 43:
                    bContinue = m_pPanel.pnlStep3.chk_03_04_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки кислорода";
                    strReportStartTitle = "Старт напуска кислорода";
                    strExecutorProgram = "AdminStep_3.4.xml";
                break;
                    
                case 44:
                    bContinue = m_pPanel.pnlStep3.chk_03_05_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска кислорода";
                    strReportStartTitle = "Старт 2-го цикла обработки в кислороде";
                    strExecutorProgram = "AdminStep_3.5.xml";
                break;
                    
                case 45:
                    bContinue = m_pPanel.pnlStep3.chk_03_06_AutoStart.isSelected();
                    strReportStopTitle = "Завершение 2-го цикла обработки в кислороде";
                    strReportStartTitle = "Старт откачки кислорода";
                    strExecutorProgram = "AdminStep_3.6.xml";
                break;
                    
                case 46:
                    bContinue = m_pPanel.pnlStep4.chk_04_01_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки кислорода";
                    strReportStartTitle = "Старт напуска кислород-неонной смеси";
                    strExecutorProgram = "AdminStep_4.1.xml";
                break;
                          
                    
                    
                //4. Обработка в среде кислород-неона.
                //4.1 Напуск кислород-неона в приборы
                //4.2 Обработка. 1ый цикл.
                //4.3 Откачка газовой смеси
                //4.4 Напуск кислород-неона в приборы
                //4.5 Обработка. 2ой цикл.
                //4.6 Откачка газовой смеси
                //4.7 Переход на основную откачку
                case 61:
                    bContinue = m_pPanel.pnlStep4.chk_04_02_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска кислород-неонной смеси";
                    strReportStartTitle = "Старт 1-го цикла обработки в кислород-неоне";
                    strExecutorProgram = "AdminStep_4.2.xml";
                break;
                case 62:
                    bContinue = m_pPanel.pnlStep4.chk_04_03_AutoStart.isSelected();
                    strReportStopTitle = "Завершение 1-го цикла обработки в кислород-неоне";
                    strReportStartTitle = "Старт откачки кислород-неона";
                    strExecutorProgram = "AdminStep_4.3.xml";
                break;
                case 63:
                    bContinue = m_pPanel.pnlStep4.chk_04_04_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки кислород-неона";
                    strReportStartTitle = "Старт напуска кислород-неона";
                    strExecutorProgram = "AdminStep_4.4.xml";
                break;
                case 64:
                    bContinue = m_pPanel.pnlStep4.chk_04_05_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска кислород-неонной смеси";
                    strReportStartTitle = "Старт 2-го цикла обработки в кислород-неоне";
                    strExecutorProgram = "AdminStep_4.5.xml";
                break;
                case 65:
                    bContinue = m_pPanel.pnlStep4.chk_04_06_AutoStart.isSelected();
                    strReportStopTitle = "Завершение 2-го цикла обработки в кислород-неоне";
                    strReportStartTitle = "Старт откачки кислород-неона";
                    strExecutorProgram = "AdminStep_4.6.xml";
                break;
                case 66:
                    bContinue = m_pPanel.pnlStep4.chk_04_07_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки кислород-неона";
                    strReportStartTitle = "Старт программы перехода на основную откачку";
                    strExecutorProgram = "AdminStep_4.7.xml";
                break;
                case 67:
                    bContinue = false; bHandStep = true;
                    
                    if( m_pPanel.pnlStep5.chk_05_01_AutoStart.isSelected()) {
                        //этап 5.1 надо пропустить (типа печки уже поставили)
                        
                        //закрываю 4.7
                        if( theApp.IsStepMapContainsKey( "067")) {
                            TechProcessStepInfo info = theApp.GetStepInfo( "067");

                            info.SetStopDateAsCurrent();
                            info.SetStopReportTitle( "Завершение программы перехода на основную откачку");
                            info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
                            info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
                            info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));

                            info.SetExecApproved();
                        
                            //NONEED?
                            //theApp.SaveStepInfo( "067", info);
                        }
                        
                        //начинаю 5.1
                        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
                        
                        info.SetStartDateAsCurrent();
                        info.SetStartReportTitle( "Установка печек (этап пройден автоматически)");
                        info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
                        info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
                        info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));
                       
                        theApp.SaveStepInfo( "081", info, true);
                        
                        //переключаемся на следующий этап (на 5.1, а ниже переключимся на 5.2)
                        theApp.NextCurrentStep();
                        
                        //и ниже же..
                        strReportStopTitle = null;  //у нас нет строки для завершения этапа 5.1
                        strReportStartTitle = "Включение PID-регулирования печек"; //начнём этап 5.2
                        
                        new Timer( 100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Timer t = ( Timer) e.getSource();
                                t.stop();
                                theApp.ShowDlg5_2();
                            }
                        }).start();
                    }
                    else {
                        //ЭТАП 5.1 НАДО подтвердить руками
                        strReportStopTitle = "Завершение программы перехода на основную откачку";
                        strReportStartTitle = "Установка печек (ручное подтверждение)";
                        
                        new Timer( 100, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Timer t = ( Timer) e.getSource();
                                t.stop();
                                theApp.ShowDlg5_1();
                            }
                        }).start();
                                
                    }
                    
                break;
                    
                    
                    
                //80   5.    Термообезгаживание
                //81   5.1 * Установка печек
                //82   5.2 * Включение PID-регулирования печек
                //82.5       Открытие геттера
                //83   5.3 * Снятие печек
                //84   5.4   Заполнение рабочей смесью
                //85   5.5   Выдержка
                case 81:
                    //НЕ МОЖЕМ ПОПАСТЬ СЮДА!!
                    /*
                    if( m_pPanel.pnlStep5.chk_05_02_AutoStart.isSelected()) {
                        //этап 5.2 надо пропустить
                        
                        //1. завершаем этап 5.1
                        if( theApp.GetMapSteps().containsKey( "081")) {
                            TechProcessStepInfo info = ( TechProcessStepInfo) theApp.GetMapSteps().get( "081");
                            info.SetStopDateAsCurrent();
                            info.SetExecApproved();
                            info.SetStopReportTitle( "");
                            info.SetExecApproved();
                            
                            theApp.GetPollerCommunicator().Req_p5p6p7();
                            info.SetStopP5( theApp.GetPollerCommunicator().GetP5());
                            info.SetStopP6( theApp.GetPollerCommunicator().GetP6());
                            info.SetStopP7( theApp.GetPollerCommunicator().GetP7());
                        }
                        
                        //переключаемся на следующий этап (5.2)
                        theApp.NextCurrentStep();
                        
                        
                        //автоматически его стартуем
                        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
                        info.SetStartDateAsCurrent();
                        theApp.GetMapSteps().put( "082", info);
                        
                        //к этому моменту должно получиться как будто мы заканчиваем этап 5.2, и
                        //в зависимости от опции этапа 5.3, стартанем его или просто перейдём к нему 
                        
                        bContinue = m_pPanel.pnlStep5.chk_05_03_AutoStart.isSelected();
                        strReportStopTitle = "Завершение откачки кислород-неона";
                        strReportStartTitle = "Старт программы перехода на основную откачку";
                        strExecutorProgram = "AdminStep_5.1.xml";
                    }
                    else {
                        //этап 5.3 надо провести (запросить подтверждение установки печек)
                        bContinue = false;
                        bHandStep = true;
                        theApp.ShowDlg5_2();
                    }*/
                break;
                    
                case 82:
                    //НЕ МОЖЕМ ПОПАСТЬ СЮДА!!
                    /*
                    bContinue = m_pPanel.pnlStep5.chk_05_03_AutoStart.isSelected();
                    */
                break;
                    
                case 83:
                    //НЕ МОЖЕМ ПОПАСТЬ СЮДА!!
                    /*
                    if( m_pPanel.pnlStep5.chk_05_02_AutoStart.isSelected()) {
                        //этап 5.4 надо пропустить
                        
                        //1. завершаем этап 5.3
                        if( theApp.GetMapSteps().containsKey( "083")) {
                            TechProcessStepInfo info = ( TechProcessStepInfo) theApp.GetMapSteps().get( "083");
                            info.SetStopDateAsCurrent();
                            info.SetStopReportTitle( "Установка печек (этап пройден автоматически)");
                            info.SetExecApproved();
                            
                            theApp.GetPollerCommunicator().Req_p5p6p7();
                            info.SetStopP5( theApp.GetPollerCommunicator().GetP5());
                            info.SetStopP6( theApp.GetPollerCommunicator().GetP6());
                            info.SetStopP7( theApp.GetPollerCommunicator().GetP7());
                        }
                        
                        //переключаемся на следующий этап (5.4)
                        theApp.NextCurrentStep();
                        
                        //автоматически его стартуем
                        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
                        info.SetStartDateAsCurrent();
                        theApp.GetMapSteps().put( "084", info);
                        
                        //к этому моменту должно получиться как будто мы заканчиваем этап 5.4, и
                        //в зависимости от опции этапа 5.5, стартанем его или просто перейдём к нему 
                        bContinue = m_pPanel.pnlStep5.chk_05_05_AutoStart.isSelected();
                        
                    }
                    else {
                        //этап 5.3 надо провести (запросить подтверждение снятия печек)
                        bContinue = false;
                        bHandStep = true;
                        theApp.ShowDlg5_4();
                    }
                    */
                break;
                case 84:
                    bContinue = m_pPanel.pnlStep5.chk_05_05_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска рабочей смеси";
                    strReportStartTitle = "Старт выдержки";
                    strExecutorProgram = "AdminStep_5.5.xml";
                break;
                case 85:
                    bContinue = false; bHandStep = true;                    
                    strReportStopTitle = "Завершение выдержки";
                    strReportStartTitle = "Снятие вольт-амперной-характеристики анодов";
                    theApp.ShowDlg6_1A();
                break;
                    
                    
                    
                //100   6.  Предварительная оценка параметров приборов
                //101 * 6.1 Измерение ВАХ
                //102 * 6.2 Внесение пороговых токов                
                //103 * 6.3 Промежуточная оценка параметров
                //104   6.4 Откачка рабочей смеси
                /*
                //РУЧНЫЕ ЭТАПЫ
                case 101: bContinue = m_pPanel.pnlStep6.chk_06_02_AutoStart.isSelected(); break;
                case 102: bContinue = m_pPanel.pnlStep6.chk_06_03_AutoStart.isSelected(); break;
                case 103: bContinue = m_pPanel.pnlStep6.chk_06_04_AutoStart.isSelected(); break;
                */
                case 104:
                    bContinue = m_pPanel.pnlStep7.chk_07_01_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки рабочей смеси";
                    strReportStartTitle = "Старт напуска тренировочной смеси";
                    strExecutorProgram = "AdminStep_7.1.xml";
                break;
                    
                    
                    
                //7. Тренировка катода
                //7.1 Напуск тренировочной смеси в приборы
                //7.2 Выдержка
                //7.3 Обработка. 1ый цикл.
                //7.4 Откачка тренировочной смеси
                //7.5 Напуск тренировочной смеси в приборы
                //7.6 Выдержка
                //7.7 Обработка. 2ой цикл.
                //7.8 Откачка тренировочной смеси
                //7.9 Напуск тренировочной смеси в приборы
                //7.10 Выдержка
                //7.11 Обработка. 3ий цикл.
                //7.12 Откачка тренировочной смеси
                //7.13 Переход на основную откачку
                case 121:
                    bContinue = m_pPanel.pnlStep7.chk_07_02_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска тренировочной смеси";
                    strReportStartTitle = "Старт выдержки";
                    strExecutorProgram = "AdminStep_7.2.xml";
                break;
                case 122:
                    bContinue = m_pPanel.pnlStep7.chk_07_03_AutoStart.isSelected();
                    strReportStopTitle = "Завершение выдержки";
                    strReportStartTitle = "Старт 1-го цикла тренировки катода";
                    strExecutorProgram = "AdminStep_7.3.xml";
                break;
                case 123:
                    bContinue = m_pPanel.pnlStep7.chk_07_04_AutoStart.isSelected();
                    strReportStopTitle = "Завершение цикла тренировки катода";
                    strReportStartTitle = "Старт откачки тренировочной смеси";
                    strExecutorProgram = "AdminStep_7.4.xml";
                break;
                case 124:
                    bContinue = m_pPanel.pnlStep7.chk_07_05_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки тренировочной смеси";
                    strReportStartTitle = "Старт напуска тренировочной смеси";
                    strExecutorProgram = "AdminStep_7.5.xml";
                break;
                case 125:
                    bContinue = m_pPanel.pnlStep7.chk_07_06_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска тренировочной смеси";
                    strReportStartTitle = "Старт выдержки";
                    strExecutorProgram = "AdminStep_7.6.xml";
                break;
                case 126:
                    bContinue = m_pPanel.pnlStep7.chk_07_07_AutoStart.isSelected();
                    strReportStopTitle = "Завершение выдержки";
                    strReportStartTitle = "Старт 2-го цикла тренировки катода";
                    strExecutorProgram = "AdminStep_7.7.xml";
                break;
                case 127:
                    bContinue = m_pPanel.pnlStep7.chk_07_08_AutoStart.isSelected();
                    strReportStopTitle = "Завершение цикла тренировки катода";
                    strReportStartTitle = "Старт откачки тренировочной смеси";
                    strExecutorProgram = "AdminStep_7.8.xml";
                break;
                case 128:
                    bContinue = m_pPanel.pnlStep7.chk_07_09_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки тренировочной смеси";
                    strReportStartTitle = "Старт напуска тренировочной смеси";
                    strExecutorProgram = "AdminStep_7.9.xml";
                break;
                case 129:
                    bContinue = m_pPanel.pnlStep7.chk_07_10_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска тренировочной смеси";
                    strReportStartTitle = "Старт выдержки";
                    strExecutorProgram = "AdminStep_7.10.xml";
                break;
                case 130:
                    bContinue = m_pPanel.pnlStep7.chk_07_11_AutoStart.isSelected();
                    strReportStopTitle = "Завершение выдержки";
                    strReportStartTitle = "Старт 3-го цикла тренировки катода";
                    strExecutorProgram = "AdminStep_7.11.xml";
                break;
                case 131:
                    bContinue = m_pPanel.pnlStep7.chk_07_12_AutoStart.isSelected();
                    strReportStopTitle = "Завершение цикла тренировки катода";
                    strReportStartTitle = "Старт откачки тренировочной смеси";
                    strExecutorProgram = "AdminStep_7.12.xml";
                break;
                case 132:
                    bContinue = m_pPanel.pnlStep7.chk_07_13_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки рабочей смеси";
                    strReportStartTitle = "Старт программы перехода на основную откачку";
                    strExecutorProgram = "AdminStep_7.13.xml";
                break;
                    
                case 133:
                    bContinue = false; bHandStep = true;                    
                    strReportStopTitle = "Завершение программы перехода на основную откачку";
                    strReportStartTitle = "Начало процесса обезгаживания";
                    theApp.GetSettings().ReadSettingsDbg();
                    theApp.ShowDlg8( 10);
                break;
                    
                    
                    
                //140 8.    Обезгаживание рабочих геттеров
                //141 8.1 * Обезгаживание
                //142 8.2   Открытие геттера
                //case 141: bContinue = m_pPanel.pnlStep8.chk_08_02_AutoStart.isSelected(); break;
                case 142:
                    bContinue = m_pPanel.pnlStep9.chk_09_01_AutoStart.isSelected();
                    strReportStopTitle = "Завершение программы открытия геттера";
                    strReportStartTitle = "Старт напуска тренировочной смеси";
                    strExecutorProgram = "AdminStep_9.1.xml";
                break;
                    
                    
                    
                //160 9. Тренировка в тренировочной смеси
                //161 9.1 Напуск тренировочной смеси в приборы
                //162 9.2 Выдержка
                //163 9.3 Обработка. 1ый цикл.
                //164 9.4 Откачка тренировочной смеси
                //165 9.5 Напуск тренировочной смеси в приборы
                //166 9.6 Выдержка
                //167 9.7 Обработка. 2ой цикл.
                //168 9.8 Откачка тренировочной смеси
                //169 9.9 Переход на основную откачку 
                case 161:
                    bContinue = m_pPanel.pnlStep9.chk_09_02_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска тренировочной смеси";
                    strReportStartTitle = "Старт выдержки";
                    strExecutorProgram = "AdminStep_9.2.xml";
                break;
                case 162:
                    bContinue = m_pPanel.pnlStep9.chk_09_03_AutoStart.isSelected();
                    strReportStopTitle = "Завершение выдержки";
                    strReportStartTitle = "Старт 1-го цикла тренировки";
                    strExecutorProgram = "AdminStep_9.3.xml";
                break;
                case 163:
                    bContinue = m_pPanel.pnlStep9.chk_09_04_AutoStart.isSelected();
                    strReportStopTitle = "Завершение 1-го цикла тренировки";
                    strReportStartTitle = "Старт откачки тренировочной смеси";
                    strExecutorProgram = "AdminStep_9.4.xml";
                break;
                case 164:
                    bContinue = m_pPanel.pnlStep9.chk_09_05_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки тренировочной смеси";
                    strReportStartTitle = "Старт напуска тренировочной смеси";
                    strExecutorProgram = "AdminStep_9.5.xml";
                break;
                case 165:
                    bContinue = m_pPanel.pnlStep9.chk_09_06_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска тренировочной смеси";
                    strReportStartTitle = "Старт выдержки";
                    strExecutorProgram = "AdminStep_9.6.xml";
                break;
                case 166:
                    bContinue = m_pPanel.pnlStep9.chk_09_07_AutoStart.isSelected();
                    strReportStopTitle = "Завершение выдержки";
                    strReportStartTitle = "Старт 2-го цикла тренировки";
                    strExecutorProgram = "AdminStep_9.7.xml";
                break;
                case 167:
                    bContinue = m_pPanel.pnlStep9.chk_09_08_AutoStart.isSelected();
                    strReportStopTitle = "Завершение 2-го цикла тренировки";
                    strReportStartTitle = "Старт откачки тренировочной смеси";
                    strExecutorProgram = "AdminStep_9.8.xml";
                break;
                case 168:
                    bContinue = m_pPanel.pnlStep9.chk_09_09_AutoStart.isSelected();
                    strReportStopTitle = "Завершение откачки тренировочной смеси";
                    strReportStartTitle = "Старт программы перехода на основную откачку";
                    strExecutorProgram = "AdminStep_9.9.xml";
                break;
                case 169:
                    bContinue = false; bHandStep = true;                    
                    strReportStopTitle = "Завершение программы перехода на основную откачку";
                    strReportStartTitle = "Начало процесса активации";
                    theApp.GetSettings().ReadSettingsDbg();
                    theApp.ShowDlg10( 10);
                break;
    
                    
                    
                //180   10.  Активация рабочих геттеров
                //181 * 10.1 Активация
                //182   10.2 Открытие геттера
                //case 181: bContinue = m_pPanel.pnlStep10.chk_10_02_AutoStart.isSelected(); break;
                case 182:
                    bContinue = m_pPanel.pnlStep11.chk_11_01_AutoStart.isSelected();
                    strReportStopTitle = "Завершение программы открытия геттера";
                    strReportStartTitle = "Старт напуска рабочей смеси";
                    strExecutorProgram = "AdminStep_11.1.xml";
                break;
                    
                    
                    
                //200 11. Выходная оценка параметров приборов
                //201 11.1   Заполнение рабочей смесью
                //202 11.2   Выдержка
                //203 11.3 * Измерение ВАХ
                //204 11.4 * Внесение пороговых токов
                //205 11.5 * Оценка параметров приборов
                //206 11.6 * Герметизация годных приборов
                case 201:
                    bContinue = m_pPanel.pnlStep11.chk_11_02_AutoStart.isSelected();
                    strReportStopTitle = "Завершение напуска рабочей смеси";
                    strReportStartTitle = "Старт выдержки";
                    strExecutorProgram = "AdminStep_11.2.xml";
                break;
                case 202: 
                    bContinue = false; bHandStep = true;                    
                    strReportStopTitle = "Завершение выдержки";
                    strReportStartTitle = "Снятие вольт-амперной-характеристики анодов";
                    theApp.ShowDlg11_3A();
                break;
                //case 203: bContinue = m_pPanel.pnlStep11.chk_11_04_AutoStart.isSelected(); break;
                //case 204: bContinue = m_pPanel.pnlStep11.chk_11_05_AutoStart.isSelected(); break;
                //case 205: bContinue = m_pPanel.pnlStep11.chk_11_06_AutoStart.isSelected(); break;
                //case 206: bContinue = m_pPanel.pnlStep12.chk_12_01_AutoStart.isSelected(); break;    
                    
                    
                //12. Снятие непрошедших приборов (опционально)
                //12.1   Закрытие геттера
                //12.2   Напуск азота в приборы
                //12.3 * Снятие непрошедших приборов
                case 221:
                    bContinue = m_pPanel.pnlStep12.chk_12_02_AutoStart.isSelected();
                    strReportStopTitle = "Завершение программы закрытия геттера";
                    strReportStartTitle = "Старт напуска азота";
                    strExecutorProgram = "AdminStep_12.2.xml";
                break;
                case 222:
                    bContinue = false; bHandStep = true;                    
                    strReportStopTitle = "Завершение напуска азота";
                    strReportStartTitle = "Снятие непрошедших приборов";
                    theApp.ShowDlg12_3();
                break;
                //case 223: bContinue = m_pPanel.pnlStep13.chk_13_01_AutoStart.isSelected(); break;
                
                    
                    
                //13. Завершение технологического процесса
                //13.1   Bypass-откачка
                //13.2 * Проверка герметичности (?? да  ?? нет)
                //13.3   Основная откачка
                //13.4   Откачка смеси с геттера
                case 241:
                    bContinue = false; bHandStep = true;                    
                    strReportStopTitle = "Завершение bypass-откачки";
                    strReportStartTitle = "Проверка герметичности";
                    theApp.ShowDlg13_2();
                break;
                //case 242: bContinue = m_pPanel.pnlStep13.chk_13_03_AutoStart.isSelected(); break;
                case 243:
                    bContinue = m_pPanel.pnlStep13.chk_13_04_AutoStart.isSelected();
                    strReportStopTitle = "Завершение программы основной откачки";
                    strReportStartTitle = "Старт программы откачки смеси с геттера";
                    strExecutorProgram = "AdminStep_13.4.xml";
                break;
                case 244:
                    bContinue = false;
                    strReportStopTitle = "Завершение технологического процесса";
                break;
            }

            //DEBUG: тормозим таймер эмуляции
            m_EmuTimer.stop();
                        
            //проигрываем звуковой сигнал о получении сигнала об окончании программы Executor'a
            SoundPlayThread thr = new SoundPlayThread( theApp.GetAMSRoot() + "/res/sound/level.wav");
            //try {
                thr.start();
                //thr.join();
            //} catch (InterruptedException ex) {
            //    //Logger.getLogger(TestDialog5.class.getName()).log(Level.SEVERE, null, ex);
            //}

            String strStep = String.format( "%03d", theApp.GetCurrentStep());
            if( theApp.IsStepMapContainsKey( strStep)) {
                TechProcessStepInfo info = ( TechProcessStepInfo) theApp.GetStepInfo( strStep);
                
                info.SetStopDateAsCurrent();
                info.SetStopReportTitle( strReportStopTitle);
                info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
                info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
                info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));
                
                info.SetExecApproved();
                
                theApp.SaveStepInfo( strStep, info, true);
            }

            //переключаемся на следующий этап
            theApp.NextCurrentStep();
            
            if( bContinue || bHandStep) {
                //если следующий этап авто-запускаемый ИЛИ ручной (типа само запускается)
                strStep = String.format( "%03d", theApp.GetCurrentStep());
                TechProcessStepInfo info = new TechProcessStepInfo( theApp);
                
                info.SetStartDateAsCurrent();
                info.SetStartReportTitle( strReportStartTitle);
                info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
                info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
                info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));
                
                theApp.SaveStepInfo( strStep, info, true);
            }
            
            if( bContinue == true) {
                //если следующий этап авто-запускаемый
                //DEBUG: запускаем таймер эмуляции
                //m_EmuTimer.start();
            }
            
            if( bContinue == true && strExecutorProgram != null) {
                theApp.SetCurrentStepInProgress( true);
                StartProgramExecutor executor = new StartProgramExecutor( theApp, strExecutorProgram);
                executor.StartThread();
            }
            
            theApp.m_ReportGenerator.Generate();
            
            //перерисовываем экран
            m_pPanel.SetStates();
            m_pPanel.Reposition();
        }
        
    }
    
    public Timer m_EmuTimer;
    private final HVV_Admin_MainFrame.EmulationActionListener m_EmuTimerListener;
    
    static Logger logger = Logger.getLogger( HVV_Admin_MainFrame.class);
    
    public TechProcessPanel5 m_pPanel;
    private final HVV_Admin theApp;
            
    private final LedsRefreshState m_ledsRefreshThread;
    
    /**
     * Creates new form HVV_Admin_MainFrame
     */
    public HVV_Admin_MainFrame( HVV_Admin app) {
        initComponents();
        
        theApp = app;
        
        setTitle( "Административный модуль, 2018.02.12 11:00  (С) ФЛАВТ, 2018.");
        
        m_pPanel = new TechProcessPanel5( app);
        m_pPanel.setVisible( true);
        pnlPanel.add( m_pPanel);
        
        m_pPanel.setBounds( 2, 2, 1000, 850);
        
        m_EmuTimerListener = new EmulationActionListener();
        m_EmuTimer = new Timer( 1500, m_EmuTimerListener);
        
        ToolTipManager.sharedInstance().setDismissDelay( 5000);
        
        m_ledsRefreshThread = new LedsRefreshState( app);
        new Timer( 1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                m_ledsRefreshThread.lightLedsStart();
            }
        }).start();

        if(      Logger.getRootLogger().getLevel() == Level.TRACE) btnTogTrace.setSelected( true);
        else if( Logger.getRootLogger().getLevel() == Level.DEBUG) btnTogDebug.setSelected( true);
        else if( Logger.getRootLogger().getLevel() == Level.INFO)  btnTogInfo.setSelected( true);
        else if( Logger.getRootLogger().getLevel() == Level.WARN)  btnTogWarn.setSelected( true);
        else if( Logger.getRootLogger().getLevel() == Level.ERROR) btnTogError.setSelected( true);
        else btnTogFatal.setSelected( true);
        
        //m_ledsRefreshThread.lightLedsStart();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroupLogLevel = new javax.swing.ButtonGroup();
        pnlPanel = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        jScrollBar1 = new javax.swing.JScrollBar();
        m_lblIconExecutorFrom = new javax.swing.JLabel();
        lblTitleExecutor = new javax.swing.JLabel();
        lblTitlePoller = new javax.swing.JLabel();
        m_lblIconPoller = new javax.swing.JLabel();
        btnMaxManual = new javax.swing.JButton();
        btnMaxAuto = new javax.swing.JButton();
        m_lblIconExecutorTo = new javax.swing.JLabel();
        btnTogTrace = new javax.swing.JToggleButton();
        btnTogDebug = new javax.swing.JToggleButton();
        btnTogInfo = new javax.swing.JToggleButton();
        btnTogWarn = new javax.swing.JToggleButton();
        btnTogError = new javax.swing.JToggleButton();
        btnTogFatal = new javax.swing.JToggleButton();
        lblTitleHv = new javax.swing.JLabel();
        m_lblIconHv = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Title");
        setMinimumSize(new java.awt.Dimension(1050, 1000));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        pnlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        pnlPanel.setMaximumSize(new java.awt.Dimension(504, 404));
        pnlPanel.setMinimumSize(new java.awt.Dimension(504, 404));
        pnlPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlPanelMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1002, Short.MAX_VALUE)
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 852, Short.MAX_VALUE)
        );

        getContentPane().add(pnlPanel);
        pnlPanel.setBounds(10, 40, 1004, 854);

        btnExit.setText("Выход");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit);
        btnExit.setBounds(930, 910, 100, 40);

        lblTitle.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        lblTitle.setText("<html><u>Этапы технологичеcкого процесса:</u></html>");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(10, 6, 530, 30);

        jScrollBar1.setMaximum(215);
        jScrollBar1.setPreferredSize(new java.awt.Dimension(20, 304));
        jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar1AdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(jScrollBar1);
        jScrollBar1.setBounds(1020, 40, 20, 860);

        m_lblIconExecutorFrom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_lblIconExecutorFrom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        getContentPane().add(m_lblIconExecutorFrom);
        m_lblIconExecutorFrom.setBounds(10, 940, 20, 20);

        lblTitleExecutor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleExecutor.setText("E");
        lblTitleExecutor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTitleExecutor);
        lblTitleExecutor.setBounds(30, 920, 30, 40);

        lblTitlePoller.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitlePoller.setText("P");
        lblTitlePoller.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTitlePoller);
        lblTitlePoller.setBounds(90, 920, 30, 20);

        m_lblIconPoller.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_lblIconPoller.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        getContentPane().add(m_lblIconPoller);
        m_lblIconPoller.setBounds(70, 920, 20, 20);

        btnMaxManual.setText("Стартовать все этапы вручную");
        btnMaxManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaxManualActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaxManual);
        btnMaxManual.setBounds(620, 910, 290, 40);

        btnMaxAuto.setText("Стартовать все этапы автоматически");
        btnMaxAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaxAutoActionPerformed(evt);
            }
        });
        getContentPane().add(btnMaxAuto);
        btnMaxAuto.setBounds(320, 910, 290, 40);

        m_lblIconExecutorTo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_lblIconExecutorTo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        getContentPane().add(m_lblIconExecutorTo);
        m_lblIconExecutorTo.setBounds(10, 920, 20, 20);

        btnGroupLogLevel.add(btnTogTrace);
        btnTogTrace.setText("T");
        btnTogTrace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTogTraceActionPerformed(evt);
            }
        });
        getContentPane().add(btnTogTrace);
        btnTogTrace.setBounds(140, 920, 30, 20);

        btnGroupLogLevel.add(btnTogDebug);
        btnTogDebug.setText("D");
        btnTogDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTogDebugActionPerformed(evt);
            }
        });
        getContentPane().add(btnTogDebug);
        btnTogDebug.setBounds(170, 920, 30, 20);

        btnGroupLogLevel.add(btnTogInfo);
        btnTogInfo.setText("I");
        btnTogInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTogInfoActionPerformed(evt);
            }
        });
        getContentPane().add(btnTogInfo);
        btnTogInfo.setBounds(200, 920, 30, 20);

        btnGroupLogLevel.add(btnTogWarn);
        btnTogWarn.setText("W");
        btnTogWarn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTogWarnActionPerformed(evt);
            }
        });
        getContentPane().add(btnTogWarn);
        btnTogWarn.setBounds(230, 920, 30, 20);

        btnGroupLogLevel.add(btnTogError);
        btnTogError.setText("E");
        btnTogError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTogErrorActionPerformed(evt);
            }
        });
        getContentPane().add(btnTogError);
        btnTogError.setBounds(260, 920, 30, 20);

        btnGroupLogLevel.add(btnTogFatal);
        btnTogFatal.setText("F");
        btnTogFatal.setToolTipText("");
        btnTogFatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTogFatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnTogFatal);
        btnTogFatal.setBounds(290, 920, 30, 20);

        lblTitleHv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleHv.setText("HV");
        lblTitleHv.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblTitleHv);
        lblTitleHv.setBounds(90, 940, 30, 20);

        m_lblIconHv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m_lblIconHv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(200, 200, 200)));
        getContentPane().add(m_lblIconHv);
        m_lblIconHv.setBounds(70, 940, 20, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if( HVV_Admin.MessageBoxAskYesNo( "Вы уверены что хотите выйти из программы?", "HVV_ADMIN") == JOptionPane.YES_OPTION) {
            theApp.GetCommE2A().stop();

            /* ADMIN -> EXECUTOR */
            theApp.GetCommA2E().stop( true);

            /* ADMIN -> POLLER */
            theApp.GetCommA2P().stop( true);

            /* ADMIN -> HV */
            theApp.GetCommA2H().stop( true);

            /* Pendings watcher*/
            theApp.GetPendings().PendingsWatcherStop();

            m_ledsRefreshThread.lightLedsStop();

            theApp.DropStateKeeper();

            this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void jScrollBar1AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar1AdjustmentValueChanged
        if( m_pPanel != null)
        m_pPanel.setBounds( 2, 2 - jScrollBar1.getValue(), 1000, 850 + jScrollBar1.getValue());
    }//GEN-LAST:event_jScrollBar1AdjustmentValueChanged

    private void btnMaxAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaxAutoActionPerformed
        logger.debug( "In");
        
        //if( theApp.GetCurrentStep() < 22) m_pPanel.pnlStep2.chk_02_02_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 24) m_pPanel.pnlStep2.chk_02_04_AutoStart.setSelected( true);
        
        if( theApp.GetCurrentStep() < 41) m_pPanel.pnlStep3.chk_03_01_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 42) m_pPanel.pnlStep3.chk_03_02_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 43) m_pPanel.pnlStep3.chk_03_03_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 44) m_pPanel.pnlStep3.chk_03_04_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 45) m_pPanel.pnlStep3.chk_03_05_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 46) m_pPanel.pnlStep3.chk_03_06_AutoStart.setSelected( true);
        
        if( theApp.GetCurrentStep() < 61) m_pPanel.pnlStep4.chk_04_01_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 62) m_pPanel.pnlStep4.chk_04_02_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 63) m_pPanel.pnlStep4.chk_04_03_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 64) m_pPanel.pnlStep4.chk_04_04_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 65) m_pPanel.pnlStep4.chk_04_05_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 66) m_pPanel.pnlStep4.chk_04_06_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 67) m_pPanel.pnlStep4.chk_04_07_AutoStart.setSelected( true);
        
        if( theApp.GetCurrentStep() < 81) m_pPanel.pnlStep5.chk_05_01_AutoStart.setSelected( true);
        //if( theApp.GetCurrentStep() < 82) m_pPanel.pnlStep5.chk_05_02_AutoStart.setSelected( true);
        //if( theApp.GetCurrentStep() < 83) m_pPanel.pnlStep5.chk_05_03_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 84) m_pPanel.pnlStep5.chk_05_04_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 85) m_pPanel.pnlStep5.chk_05_05_AutoStart.setSelected( true);
        
        //if( theApp.GetCurrentStep() < 101) m_pPanel.pnlStep6.chk_06_01_AutoStart.setSelected( true);
        //if( theApp.GetCurrentStep() < 102) m_pPanel.pnlStep6.chk_06_02_AutoStart.setSelected( true);
        //if( theApp.GetCurrentStep() < 103) m_pPanel.pnlStep6.chk_06_03_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 104) m_pPanel.pnlStep6.chk_06_04_AutoStart.setSelected( true);
        
        if( theApp.GetCurrentStep() < 121) m_pPanel.pnlStep7.chk_07_01_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 122) m_pPanel.pnlStep7.chk_07_02_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 123) m_pPanel.pnlStep7.chk_07_03_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 124) m_pPanel.pnlStep7.chk_07_04_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 125) m_pPanel.pnlStep7.chk_07_05_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 126) m_pPanel.pnlStep7.chk_07_06_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 127) m_pPanel.pnlStep7.chk_07_07_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 128) m_pPanel.pnlStep7.chk_07_08_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 129) m_pPanel.pnlStep7.chk_07_09_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 130) m_pPanel.pnlStep7.chk_07_10_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 131) m_pPanel.pnlStep7.chk_07_11_AutoStart.setSelected( true);
        //if( theApp.GetCurrentStep() < 132) m_pPanel.pnlStep7.chk_07_12_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 133) m_pPanel.pnlStep7.chk_07_13_AutoStart.setSelected( true);
        
        //if( theApp.GetCurrentStep() < 141) m_pPanel.pnlStep8.chk_08_01_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 142) m_pPanel.pnlStep8.chk_08_02_AutoStart.setSelected( true);
        
        
        if( theApp.GetCurrentStep() < 161) m_pPanel.pnlStep9.chk_09_01_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 162) m_pPanel.pnlStep9.chk_09_02_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 163) m_pPanel.pnlStep9.chk_09_03_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 164) m_pPanel.pnlStep9.chk_09_04_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 165) m_pPanel.pnlStep9.chk_09_05_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 166) m_pPanel.pnlStep9.chk_09_06_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 167) m_pPanel.pnlStep9.chk_09_07_AutoStart.setSelected( true);
        //if( theApp.GetCurrentStep() < 168) m_pPanel.pnlStep9.chk_09_08_AutoStart.setSelected( true);
        if( theApp.GetCurrentStep() < 169) m_pPanel.pnlStep9.chk_09_09_AutoStart.setSelected( true);
        
        //10. Активация рабочих геттеров
        //if( theApp.GetCurrentStep() < 181) m_pPanel.pnlStep10.chk_10_01_AutoStart.setSelected( true);     //10.1 * Активация
        if( theApp.GetCurrentStep() < 182) m_pPanel.pnlStep10.chk_10_02_AutoStart.setSelected( true);       //10.2   Открытие геттера
        
        //11. Выходная оценка параметров приборов
        if( theApp.GetCurrentStep() < 201) m_pPanel.pnlStep11.chk_11_01_AutoStart.setSelected( true);       //11.1   Заполнение рабочей смесью
        if( theApp.GetCurrentStep() < 202) m_pPanel.pnlStep11.chk_11_02_AutoStart.setSelected( true);       //11.2   Выдержка
        //if( theApp.GetCurrentStep() < 203) m_pPanel.pnlStep9.chk_09_03_AutoStart.setSelected( true);      //11.3 * Измерение ВАХ
        //if( theApp.GetCurrentStep() < 204) m_pPanel.pnlStep9.chk_09_04_AutoStart.setSelected( true);      //11.4 * Внесение пороговых токов
        //if( theApp.GetCurrentStep() < 205) m_pPanel.pnlStep9.chk_09_05_AutoStart.setSelected( true);      //11.5 * Герметизация годных приборов
        
        //12. Снятие непрошедших приборов (опционально)
        if( theApp.GetCurrentStep() < 221) m_pPanel.pnlStep12.chk_12_01_AutoStart.setSelected( true);        //12.1   Закрытие геттера
        if( theApp.GetCurrentStep() < 222) m_pPanel.pnlStep12.chk_12_02_AutoStart.setSelected( true);        //12.2   Напуск азота в приборы
        //if( theApp.GetCurrentStep() < 223) m_pPanel.pnlStep12.chk_12_03_AutoStart.setSelected( true);      //12.3 * Снятие непрошедших приборов
        
        //13. Завершение технологического процесса
        //if( theApp.GetCurrentStep() < 241) m_pPanel.pnlStep13.chk_13_01_AutoStart.setSelected( true);        //13.1   Bypass-откачка
        //if( theApp.GetCurrentStep() < 242) m_pPanel.pnlStep13.chk_13_02_AutoStart.setSelected( true);      //13.2 * Проверка герметичности (?? да  ?? нет)
        if( theApp.GetCurrentStep() < 243) m_pPanel.pnlStep13.chk_13_03_AutoStart.setSelected( true);        //13.3   Основная откачка
        if( theApp.GetCurrentStep() < 244) m_pPanel.pnlStep13.chk_13_04_AutoStart.setSelected( true);        //13.4   Откачка смеси с геттера
        
        logger.debug( "Out");
    }//GEN-LAST:event_btnMaxAutoActionPerformed

    private void btnMaxManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaxManualActionPerformed
        //if( theApp.GetCurrentStep() < 22) m_pPanel.pnlStep2.chk_02_02_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 24) m_pPanel.pnlStep2.chk_02_04_AutoStart.setSelected( false);
        
        if( theApp.GetCurrentStep() < 41) m_pPanel.pnlStep3.chk_03_01_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 42) m_pPanel.pnlStep3.chk_03_02_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 43) m_pPanel.pnlStep3.chk_03_03_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 44) m_pPanel.pnlStep3.chk_03_04_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 45) m_pPanel.pnlStep3.chk_03_05_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 46) m_pPanel.pnlStep3.chk_03_06_AutoStart.setSelected( false);
        
        if( theApp.GetCurrentStep() < 61) m_pPanel.pnlStep4.chk_04_01_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 62) m_pPanel.pnlStep4.chk_04_02_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 63) m_pPanel.pnlStep4.chk_04_03_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 64) m_pPanel.pnlStep4.chk_04_04_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 65) m_pPanel.pnlStep4.chk_04_05_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 66) m_pPanel.pnlStep4.chk_04_06_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 67) m_pPanel.pnlStep4.chk_04_07_AutoStart.setSelected( false);
        
        if( theApp.GetCurrentStep() < 81) m_pPanel.pnlStep5.chk_05_01_AutoStart.setSelected( false);
        //if( theApp.GetCurrentStep() < 82) m_pPanel.pnlStep5.chk_05_02_AutoStart.setSelected( false);
        //if( theApp.GetCurrentStep() < 83) m_pPanel.pnlStep5.chk_05_03_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 84) m_pPanel.pnlStep5.chk_05_04_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 85) m_pPanel.pnlStep5.chk_05_05_AutoStart.setSelected( false);
        
        //if( theApp.GetCurrentStep() < 101) m_pPanel.pnlStep6.chk_06_01_AutoStart.setSelected( false);
        //if( theApp.GetCurrentStep() < 102) m_pPanel.pnlStep6.chk_06_02_AutoStart.setSelected( false);
        //if( theApp.GetCurrentStep() < 103) m_pPanel.pnlStep6.chk_06_03_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 104) m_pPanel.pnlStep6.chk_06_04_AutoStart.setSelected( false);
        
        if( theApp.GetCurrentStep() < 121) m_pPanel.pnlStep7.chk_07_01_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 122) m_pPanel.pnlStep7.chk_07_02_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 123) m_pPanel.pnlStep7.chk_07_03_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 124) m_pPanel.pnlStep7.chk_07_04_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 125) m_pPanel.pnlStep7.chk_07_05_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 126) m_pPanel.pnlStep7.chk_07_06_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 127) m_pPanel.pnlStep7.chk_07_07_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 128) m_pPanel.pnlStep7.chk_07_08_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 129) m_pPanel.pnlStep7.chk_07_09_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 130) m_pPanel.pnlStep7.chk_07_10_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 131) m_pPanel.pnlStep7.chk_07_11_AutoStart.setSelected( false);
        //if( theApp.GetCurrentStep() < 132) m_pPanel.pnlStep7.chk_07_12_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 133) m_pPanel.pnlStep7.chk_07_13_AutoStart.setSelected( false);
        
        //if( theApp.GetCurrentStep() < 141) m_pPanel.pnlStep8.chk_08_01_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 142) m_pPanel.pnlStep8.chk_08_02_AutoStart.setSelected( false);
        
        if( theApp.GetCurrentStep() < 161) m_pPanel.pnlStep9.chk_09_01_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 162) m_pPanel.pnlStep9.chk_09_02_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 163) m_pPanel.pnlStep9.chk_09_03_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 164) m_pPanel.pnlStep9.chk_09_04_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 165) m_pPanel.pnlStep9.chk_09_05_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 166) m_pPanel.pnlStep9.chk_09_06_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 167) m_pPanel.pnlStep9.chk_09_07_AutoStart.setSelected( false);
        //if( theApp.GetCurrentStep() < 168) m_pPanel.pnlStep9.chk_09_08_AutoStart.setSelected( false);
        if( theApp.GetCurrentStep() < 169) m_pPanel.pnlStep9.chk_09_09_AutoStart.setSelected( false);
        
        
        //10. Активация рабочих геттеров
        //if( theApp.GetCurrentStep() < 181) m_pPanel.pnlStep10.chk_10_01_AutoStart.setSelected( false);     //10.1 * Активация
        if( theApp.GetCurrentStep() < 182) m_pPanel.pnlStep10.chk_10_02_AutoStart.setSelected( false);       //10.2   Открытие геттера
        
        //11. Выходная оценка параметров приборов
        if( theApp.GetCurrentStep() < 201) m_pPanel.pnlStep11.chk_11_01_AutoStart.setSelected( false);       //11.1   Заполнение рабочей смесью
        if( theApp.GetCurrentStep() < 202) m_pPanel.pnlStep11.chk_11_02_AutoStart.setSelected( false);       //11.2   Выдержка
        //if( theApp.GetCurrentStep() < 203) m_pPanel.pnlStep9.chk_09_03_AutoStart.setSelected( false);      //11.3 * Измерение ВАХ
        //if( theApp.GetCurrentStep() < 204) m_pPanel.pnlStep9.chk_09_04_AutoStart.setSelected( false);      //11.4 * Внесение пороговых токов
        //if( theApp.GetCurrentStep() < 205) m_pPanel.pnlStep9.chk_09_05_AutoStart.setSelected( false);      //11.5 * Герметизация годных приборов
        
        //12. Снятие непрошедших приборов (опционально)
        if( theApp.GetCurrentStep() < 221) m_pPanel.pnlStep12.chk_12_01_AutoStart.setSelected( false);        //12.1   Закрытие геттера
        if( theApp.GetCurrentStep() < 222) m_pPanel.pnlStep12.chk_12_02_AutoStart.setSelected( false);        //12.2   Напуск азота в приборы
        //if( theApp.GetCurrentStep() < 223) m_pPanel.pnlStep12.chk_12_03_AutoStart.setSelected( false);      //12.3 * Снятие непрошедших приборов
        
        //13. Завершение технологического процесса
        //if( theApp.GetCurrentStep() < 241) m_pPanel.pnlStep13.chk_13_01_AutoStart.setSelected( false);        //13.1   Bypass-откачка
        //if( theApp.GetCurrentStep() < 242) m_pPanel.pnlStep13.chk_13_02_AutoStart.setSelected( false);      //13.2 * Проверка герметичности (?? да  ?? нет)
        if( theApp.GetCurrentStep() < 243) m_pPanel.pnlStep13.chk_13_03_AutoStart.setSelected( false);        //13.3   Основная откачка
        if( theApp.GetCurrentStep() < 244) m_pPanel.pnlStep13.chk_13_04_AutoStart.setSelected( false);        //13.4   Откачка смеси с геттера
    }//GEN-LAST:event_btnMaxManualActionPerformed

    private void btnTogTraceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTogTraceActionPerformed
        if( Logger.getRootLogger().getLevel() != Level.TRACE) {
            logger.info( "Switching log level to TRACE");
            Logger.getRootLogger().setLevel( Level.TRACE);
        }
    }//GEN-LAST:event_btnTogTraceActionPerformed

    private void btnTogDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTogDebugActionPerformed
        if( Logger.getRootLogger().getLevel() != Level.DEBUG) {
            logger.info( "Switching log level to DEBUG");
            Logger.getRootLogger().setLevel( Level.DEBUG);
        }
    }//GEN-LAST:event_btnTogDebugActionPerformed

    private void btnTogInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTogInfoActionPerformed
        if( Logger.getRootLogger().getLevel() != Level.INFO) {
            logger.info( "Switching log level to INFO");
            Logger.getRootLogger().setLevel( Level.INFO);
        }
    }//GEN-LAST:event_btnTogInfoActionPerformed

    private void btnTogWarnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTogWarnActionPerformed
        if( Logger.getRootLogger().getLevel() != Level.WARN) {
            logger.info( "Switching log level to WARN");
            Logger.getRootLogger().setLevel( Level.WARN);
        }
    }//GEN-LAST:event_btnTogWarnActionPerformed

    private void btnTogErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTogErrorActionPerformed
        if( Logger.getRootLogger().getLevel() != Level.ERROR) {
            logger.info( "Switching log level to ERROR");
            Logger.getRootLogger().setLevel( Level.ERROR);
        }
    }//GEN-LAST:event_btnTogErrorActionPerformed

    private void btnTogFatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTogFatalActionPerformed
        if( Logger.getRootLogger().getLevel() != Level.FATAL) {
            logger.info( "Switching log level to FATAL");
            Logger.getRootLogger().setLevel( Level.FATAL);
        }
    }//GEN-LAST:event_btnTogFatalActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if( HVV_Admin.MessageBoxAskYesNo( "Вы уверены что хотите выйти из программы?", "HVV_ADMIN") == JOptionPane.YES_OPTION) {
            theApp.GetCommE2A().stop();
        
            /* ADMIN -> EXECUTOR */
            theApp.GetCommA2E().stop( true);

            /* ADMIN -> POLLER */
            theApp.GetCommA2P().stop( true);

            /* ADMIN -> HV */
            theApp.GetCommA2H().stop( true);

            /* Pendings watcher*/
            theApp.GetPendings().PendingsWatcherStop();

            m_ledsRefreshThread.lightLedsStop();

            //ВОТ ТУТ ОТЛИЧИЕ! ПРИ ЗАКРЫТИИ КРЕСТОМ НЕ НАДО БРОСАТЬ STATEKEEPER
            //А ВООБЩЕ НАДО НА ДИАЛОГЕ ВОПРОСА ВЫШЕ СДЕЛАТЬ CHECK-BOX OPTION "DROP?"
            //theApp.DropStateKeeper();

            this.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.ButtonGroup btnGroupLogLevel;
    private javax.swing.JButton btnMaxAuto;
    private javax.swing.JButton btnMaxManual;
    private javax.swing.JToggleButton btnTogDebug;
    private javax.swing.JToggleButton btnTogError;
    private javax.swing.JToggleButton btnTogFatal;
    private javax.swing.JToggleButton btnTogInfo;
    private javax.swing.JToggleButton btnTogTrace;
    private javax.swing.JToggleButton btnTogWarn;
    public javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleExecutor;
    private javax.swing.JLabel lblTitleHv;
    private javax.swing.JLabel lblTitlePoller;
    public javax.swing.JLabel m_lblIconExecutorFrom;
    public javax.swing.JLabel m_lblIconExecutorTo;
    public javax.swing.JLabel m_lblIconHv;
    public javax.swing.JLabel m_lblIconPoller;
    private javax.swing.JPanel pnlPanel;
    // End of variables declaration//GEN-END:variables
}
