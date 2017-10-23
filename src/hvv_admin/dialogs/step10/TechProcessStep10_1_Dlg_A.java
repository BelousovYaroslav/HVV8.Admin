package hvv_admin.dialogs.step10;//GEN-FIRST:event_radDev8ActionPerformed
//GEN-LAST:event_radDev8ActionPerformed
import hvv_admin.steps.info.GettersActivationProgram;
import hvv_admin.SoundPlayThread;
import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.steps.info.GettersActivationProgramStep;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.Timer;
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
public class TechProcessStep10_1_Dlg_A extends javax.swing.JDialog {

    private final Font m_fontUsual;
    public Font GetUsualFont() { return m_fontUsual; }
    
    private final Font m_fontBold;
    public Font GetBoldFont() { return m_fontBold; }
    
    public final int m_nInductor;
    
    public LinkedList lstRads;
    
    int m_nInProgress = -1;
    
    private boolean m_bReadyForNextStep;
    
    
    class DataRefresherListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if( m_pnlSteps != null)
                m_pnlSteps.RefreshData();
        }
    }
    
    class EmulationActionListener implements ActionListener {
        
        HVV_Admin theApp;
        public EmulationActionListener(HVV_Admin app) {
            theApp = app;
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            //останавливаем таймер шагов
            m_EmuTimer.stop();
            m_TimeTimer.stop();
            
            //отделим случай когда мы закончили пол-программы
            if( ( m_nPhase + 1) == m_lstProgram.size() && m_bTrailingAddded == false) {
                int nBgColor = lblTimerValue.getBackground().getRGB();
                if( lblTimerValue.getBackground() == clrBlack ||
                        nBgColor == 0xFFEDECEB) {
                    
                    lblTimerValue.setBackground( clrRed);
                    //lblTimerValue.setText( "1");
                    lblCurrentPowerValue.setBackground( clrRed);
                }
                else {
                    
                    lblTimerValue.setBackground( null);
                    //lblTimerValue.setText( String.format( "0x%06x", nBgColor));
                    lblCurrentPowerValue.setBackground( null);
                    //lblCurrentPowerValue.setText( String.format( "%d", nBgColor));
                }
                    
                m_EmuTimer = new Timer( 500, m_pEmuListner); //setDelay( 5000 + m_nPhase * 1000); //item.GetDuration() * 2000);
                m_EmuTimer.start();
                    
                return;
            }
            
            //проигрываем звуковой сигнал о получении сигнала об окончании программы Executor'a
            SoundPlayThread thr = new SoundPlayThread( theApp.GetAMSRoot() + "/res/sound/level.wav");
            //try {
                thr.start();
                //thr.join();
            //} catch (InterruptedException ex) {
            //    //Logger.getLogger(TestDialog5.class.getName()).log(Level.SEVERE, null, ex);
            //}

            //Завершаем шаг обезгаживания
            GettersActivationProgramStep item = ( GettersActivationProgramStep) m_lstProgram.get( m_nPhase);
            
            //заполняем максимальный ....
            item.SetP5_max( m_rnblP5Watchdog.GetP5Max());
            m_rnblP5Watchdog.DropMax();
            
            //... и последний текущий P5
            item.SetP5_last( theApp.GetFromPoller( "005.01"));
            
            m_nPhase++;
            if( m_nPhase == m_lstProgram.size()) {
                if( m_bTrailingAddded) {
                    //конец программы этапа для текущего активируемого прибора
                    m_pGetActProgram.SetDtFinish( theApp.GetLocalDate());
                    m_pGetActProgram.CloneListSteps( m_lstProgram);

                    //мы никого не обрабатываем
                    m_nInProgress = -1;

                    //текущая мощность не нужна
                    lblCurrentPowerValue.setText( "-");

                    //текущий таймер тоже надо сбросить
                    lblTimerValue.setText( "-");

                    //показываем кого мы отдегазировали
                    if( radDev1.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE1, m_pGetActProgram);
                    if( radDev2.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE2, m_pGetActProgram);
                    if( radDev3.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE3, m_pGetActProgram);
                    if( radDev4.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE4, m_pGetActProgram);
                    if( radDev5.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE5, m_pGetActProgram);
                    if( radDev6.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE6, m_pGetActProgram);
                    if( radDev7.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE7, m_pGetActProgram);
                    if( radDev8.isSelected()) theApp.m_mapActivation.put( HVV_AdminConstants.DEVICE8, m_pGetActProgram);

                    theApp.GetStateKeeper().SaveState();
                    theApp.m_ReportGenerator.Generate();
                    
                    //КНОПКА "ДАЛЕЕ"
                    boolean bAllDvcsDegasated = true;

                    //ищем хоть один проводимый, но недегазированный прибор
                    for( int i=0; i<8; i++) {
                        boolean bPresent = ( boolean) theApp.m_mapDevicePresence.get( i);
                        if( bPresent) {
                            boolean bKeepProcessing = ( boolean) theApp.m_mapStep6_3_Continue.get( i);
                            if( bKeepProcessing) {
                                if( !theApp.m_mapActivation.containsKey( i)) {
                                    bAllDvcsDegasated = false;
                                    break;
                                }
                            }
                        }
                    }

                    if( bAllDvcsDegasated)
                        m_pParent.btnNext.setEnabled( true);


                    
                    //определяем кто мы из двух индукторов
                    TechProcessStep10_1_Dlg_A dlg = null;
                    TechProcessStep10_1_Dlg_A dlg1 = m_pParent.m_dlgInd1;
                    TechProcessStep10_1_Dlg_A dlg2 = m_pParent.m_dlgInd2;
                    if( m_nInductor == HVV_AdminConstants.INDUCTOR1) dlg = m_pParent.m_dlgInd1;
                    if( m_nInductor == HVV_AdminConstants.INDUCTOR2) dlg = m_pParent.m_dlgInd2;


                    //ЭТАП 1. Ищем пары
                    boolean bDone = false;
                    if( dlg1 != null && dlg2 != null && dlg1.m_nInProgress == -1 && dlg2.m_nInProgress == -1) {

                        //выставляем состояния radio-кнопок всех необработанных устройств
                        dlg1.setRadButtonsState( false);
                        dlg2.setRadButtonsState( false);


                        logger.debug( "1. ищем возможные пары (1,5), (2,6), (3,7), (4,8)");
                        for( int i=0; i<4; i++) {
                            logger.debug( "1. пробуем пару (" + (i+4) + ", " + i + ")");

                            JRadioButton rad_ind1 = ( JRadioButton) dlg1.lstRads.get(i+4);
                            JRadioButton rad_ind2 = ( JRadioButton) dlg2.lstRads.get(i);

                            if( rad_ind1.isEnabled() && rad_ind2.isEnabled()) {

                                logger.debug( "1. Пара (" + (i+4) + ", " + i + ") хороша");

                                rad_ind1.setSelected( true);
                                rad_ind2.setSelected( true);


                                dlg1.btnStart.setEnabled( true);
                                dlg1.radInd1.setEnabled( true);
                                dlg1.radInd2.setEnabled( true);

                                dlg2.btnStart.setEnabled( true);
                                dlg2.radInd1.setEnabled( true);
                                dlg2.radInd2.setEnabled( true);


                                int nGetter = ( int) theApp.m_mapDeviceGetter.get( i+4);
                                if( nGetter == HVV_AdminConstants.GETTER1)
                                    dlg1.lblGetterValue.setText( "1");
                                else
                                    dlg1.lblGetterValue.setText( "2");

                                nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                                if( nGetter == HVV_AdminConstants.GETTER1)
                                    dlg2.lblGetterValue.setText( "1");
                                else
                                    dlg2.lblGetterValue.setText( "2");


                                dlg1.DefineProgram();
                                dlg1.LoadProgram();
                                dlg1.RefreshProgram();

                                dlg2.DefineProgram();
                                dlg2.LoadProgram();
                                dlg2.RefreshProgram();

                                bDone = true;
                                break;
                            }
                        }

                    }

                    if( bDone == false) {
                        //доступных парных приборов найти не удалось

                        logger.debug( "Парных приборов не нашлось");
                        if( dlg != null) {
                            //выставляем состояния radio-кнопок всех необработанных устройств
                            dlg.setRadButtonsState( false);

                            boolean bStep2Success = false;

                            int i1 = 0, i2 = 4;
                            //выбираем для СЕБЯ первый доступный из (??-??) нуууу... своего диапазона
                            if( m_nInductor == HVV_AdminConstants.INDUCTOR1) {
                                logger.debug( "(2.) Выбираем для себя (первого индуктора) первый доступный из (5-8)");
                                i1 = 4; i2 = 8;
                            }
                            if( m_nInductor == HVV_AdminConstants.INDUCTOR2) {
                                logger.debug( "(2.) Выбираем для себя (второго индуктора) первый доступный из (1-4)");
                                i1 = 0; i2 = 4;
                            }

                            for( int i=i1; i<i2; i++) {

                                logger.debug( "(2.) Выбираем-пробуем для себя прибор " + i);

                                JRadioButton rad_ind1 = ( JRadioButton) dlg.lstRads.get(i);

                                if( rad_ind1.isEnabled()) {

                                    rad_ind1.setSelected( true);

                                    dlg.btnStart.setEnabled( true);
                                    dlg.radInd1.setEnabled( true);
                                    dlg.radInd2.setEnabled( true);

                                    int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                                    if( nGetter == HVV_AdminConstants.GETTER1)
                                        dlg.lblGetterValue.setText( "1");
                                    else
                                        dlg.lblGetterValue.setText( "2");

                                    dlg.DefineProgram();
                                    dlg.LoadProgram();
                                    dlg.RefreshProgram();

                                    bStep2Success = true;
                                    break;
                                }
                            }

                            if( bStep2Success == false) {
                                logger.debug( "Приборов в своём диапазоне не нашлось - ищем в полном");


                                if( m_nInductor == HVV_AdminConstants.INDUCTOR1)
                                    logger.debug( "(3.) Выбираем для себя (первого индуктора) первый доступный из (1-8)");
                                if( m_nInductor == HVV_AdminConstants.INDUCTOR2)
                                    logger.debug( "(3.) Выбираем для себя (второго индуктора) первый доступный из (1-8)");

                                for( int i=0; i<8; i++) {
                                    logger.debug( "(3.) Выбираем-пробуем для себя прибор " + i);

                                    JRadioButton rad_ind1 = ( JRadioButton) dlg.lstRads.get(i);

                                    if( rad_ind1.isEnabled()) {

                                        rad_ind1.setSelected( true);

                                        dlg.btnStart.setEnabled( true);
                                        dlg.radInd1.setEnabled( true);
                                        dlg.radInd2.setEnabled( true);

                                        int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                                        if( nGetter == HVV_AdminConstants.GETTER1)
                                            dlg.lblGetterValue.setText( "1");
                                        else
                                            dlg.lblGetterValue.setText( "2");

                                        dlg.DefineProgram();
                                        dlg.LoadProgram();
                                        dlg.RefreshProgram();

                                        break;
                                    }
                                }


                            }

                        }
                    
                    }

                    //заново расставляем доступность radio-кнопок доступных обрабатываемых приборов для обоих диалогов
                    m_pParent.m_dlgInd1.setRadButtonsState( false);
                    m_pParent.m_dlgInd2.setRadButtonsState( false);


                    //перезагрузим программу
                    m_lstProgram = new LinkedList();
                    DefineProgram();
                    LoadProgram();

                    m_pParent.m_pnlDevices14.RefreshData();
                    m_pParent.m_pnlDevices58.RefreshData();
                    m_pParent.m_tmrRefresh.start();

                    return;
                    /*
                    //определяем кто мы из двух индукторов
                    TechProcessStep10_1_Dlg_A dlg = null;
                    if( m_nInductor == HVV_AdminConstants.INDUCTOR1) dlg = m_pParent.m_dlgInd1;
                    if( m_nInductor == HVV_AdminConstants.INDUCTOR2) dlg = m_pParent.m_dlgInd2;

                    if( dlg != null) {
                        //выставляем состояния radio-кнопок всех необработанных устройств
                        dlg.setRadButtonsState( false);

                        //отмечаем первый доступный
                        int i;
                        for( i=0; i<8; i++) {
                            JRadioButton rad = ( JRadioButton) dlg.lstRads.get(i);
                            if( rad.isEnabled()) {
                                rad.setSelected( true);

                                dlg.btnStart.setEnabled( true);
                                dlg.radInd1.setEnabled( true);
                                dlg.radInd2.setEnabled( true);

                                int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                                if( nGetter == HVV_AdminConstants.GETTER1) dlg.lblGetterValue.setText("1"); 
                                if( nGetter == HVV_AdminConstants.GETTER2) dlg.lblGetterValue.setText("2"); 

                                if( m_nInductor == HVV_AdminConstants.INDUCTOR1) radInd1.setSelected( true);
                                if( m_nInductor == HVV_AdminConstants.INDUCTOR2) radInd2.setSelected( true);
                                
                                break;
                            }
                        }

                    }

                    //заново расставляем доступность radio-кнопок доступных обрабатываемых приборов для обоих диалогов
                    m_pParent.m_dlgInd1.setRadButtonsState( false);
                    m_pParent.m_dlgInd2.setRadButtonsState( false);


                    //перезагрузим программу
                    m_lstProgram = new LinkedList();
                    DefineProgram();
                    LoadProgram();
                    m_pnlSteps.PlaceEdtBtns();

                    m_pParent.m_pnlDevices14.RefreshData();
                    m_pParent.m_pnlDevices58.RefreshData();
                    m_pParent.m_tmrRefresh.start();

                    return;
                    */
                }
                else {
                    //мы дошли до конца первой половины, и нам не понятно куда двигаться дальше
                    //ОБРАБОТАНО ВЫШЕ ( В НАЧАЛЕ ФУНКЦИИ)
                    //запустим оповещение оператора
                    m_nFlashCounter = 0;
                    
                    m_FlashTimer.stop();
                    new Timer( 100, new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Timer tm = ( Timer) e.getSource();
                            tm.stop();
                            m_FlashTimer.start();
                        }
                    }).start();
                    
                    return;
                }
            }
            
            if( m_nPhase == m_lstProgram.size() && m_bTrailingAddded == false)
                btnNextStep.setEnabled( false);
            else
                btnNextStep.setEnabled( true);

            m_bReadyForNextStep = false;
                    
            //Переходим к следующему шагу обезгаживания
            item = ( GettersActivationProgramStep) m_lstProgram.get( m_nPhase);
            
            //покажем какую мощность надо поставить
            lblCurrentPowerValue.setText( "" + item.GetPower());
            lblCurrentPowerValue.setBackground( clrRed);
            
            //запустим оповещение оператора
            m_nFlashCounter = 0;
            m_FlashTimer.stop();
            new Timer( 100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Timer tm = ( Timer) e.getSource();
                    tm.stop();
                    m_FlashTimer.start();
                }
            }).start();
            
            lblTimerValue.setText( "--:--");
        }
        
    }
    
    //класс показывающий сколько нам осталось времени в шаге
    class TimeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            m_nTimeCounter--;
            String strValue = String.format( "%02d:%02d", m_nTimeCounter / 60, m_nTimeCounter % 60);
            lblTimerValue.setText( strValue);
            if( m_nTimeCounter == 0)
                m_TimeTimer.stop();
        }
        
    }
    
    class FlashActionListener implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent e) {
            
            //проигрываем звуковой сигнал о получении сигнала об окончании программы Executor'a
            SoundPlayThread thr = new SoundPlayThread( theApp.GetAMSRoot() + "/res/sound/crash.wav");
            //try {
                thr.start();
                //thr.join();
            //} catch (InterruptedException ex) {
            //    //Logger.getLogger(TestDialog5.class.getName()).log(Level.SEVERE, null, ex);
            //}
            
            
            m_nFlashCounter++;
            //System.out.println("" + m_nFlashCounter);
            
            
            TechProcessStep10_1_Dlg_A_StepsPanel a = m_pnlSteps;
            JLabel lb1 = a.lblDuration_1;
            JLabel lb2 = a.lblPower_1;
            JLabel lb3 = a.lblP5_start_1;
            JLabel lb4 = a.lblP5_max_1;
            JLabel lb5 = a.lblP5_last_1;
            
            switch( m_nPhase) {
                case  0:
                    lb1 = a.lblDuration_1; lb2 = a.lblPower_1;   lb3 = a.lblP5_start_1;
                    lb4 = a.lblP5_max_1;   lb5 = a.lblP5_last_1; break;
                case  1:
                    lb1 = a.lblDuration_2; lb2 = a.lblPower_2;   lb3 = a.lblP5_start_2;
                    lb4 = a.lblP5_max_2;   lb5 = a.lblP5_last_2; break;
                case  2:
                    lb1 = a.lblDuration_3; lb2 = a.lblPower_3;   lb3 = a.lblP5_start_3;
                    lb4 = a.lblP5_max_3;   lb5 = a.lblP5_last_3; break;
                case  3:
                    lb1 = a.lblDuration_4; lb2 = a.lblPower_4;   lb3 = a.lblP5_start_4;
                    lb4 = a.lblP5_max_4;   lb5 = a.lblP5_last_4; break;
                case  4:
                    lb1 = a.lblDuration_5; lb2 = a.lblPower_5;   lb3 = a.lblP5_start_5;
                    lb4 = a.lblP5_max_5;   lb5 = a.lblP5_last_5; break;
                case  5:
                    lb1 = a.lblDuration_6; lb2 = a.lblPower_6;   lb3 = a.lblP5_start_6;
                    lb4 = a.lblP5_max_6;   lb5 = a.lblP5_last_6; break;
                case  6:
                    lb1 = a.lblDuration_7; lb2 = a.lblPower_7;   lb3 = a.lblP5_start_7;
                    lb4 = a.lblP5_max_7;   lb5 = a.lblP5_last_7; break;
                case  7:
                    lb1 = a.lblDuration_8; lb2 = a.lblPower_8;   lb3 = a.lblP5_start_8;
                    lb4 = a.lblP5_max_8;   lb5 = a.lblP5_last_8; break;
                case  8:
                    lb1 = a.lblDuration_9; lb2 = a.lblPower_9;   lb3 = a.lblP5_start_9;
                    lb4 = a.lblP5_max_9;   lb5 = a.lblP5_last_9; break;
                case  9:
                    lb1 = a.lblDuration_10; lb2 = a.lblPower_10; lb3 = a.lblP5_start_10;
                    lb4 = a.lblP5_max_10;  lb5 = a.lblP5_last_10; break;
                case 10:
                    lb1 = a.lblDuration_11; lb2 = a.lblPower_11; lb3 = a.lblP5_start_11;
                    lb4 = a.lblP5_max_11;  lb5 = a.lblP5_last_11; break;
                case 11:
                    lb1 = a.lblDuration_12; lb2 = a.lblPower_12; lb3 = a.lblP5_start_12;
                    lb4 = a.lblP5_max_12;  lb5 = a.lblP5_last_12; break;
                case 12:
                    lb1 = a.lblDuration_13; lb2 = a.lblPower_13; lb3 = a.lblP5_start_13;
                    lb4 = a.lblP5_max_13;  lb5 = a.lblP5_last_13; break;
                case 13:
                    lb1 = a.lblDuration_14; lb2 = a.lblPower_14; lb3 = a.lblP5_start_14;
                    lb4 = a.lblP5_max_14;  lb5 = a.lblP5_last_14; break;
                case 14:
                    lb1 = a.lblDuration_15; lb2 = a.lblPower_15; lb3 = a.lblP5_start_15;
                    lb4 = a.lblP5_max_15;  lb5 = a.lblP5_last_15; break;
                case 15:
                    lb1 = a.lblDuration_16; lb2 = a.lblPower_16; lb3 = a.lblP5_start_16;
                    lb4 = a.lblP5_max_16;  lb5 = a.lblP5_last_16; break;
                case 16:
                    lb1 = a.lblDuration_17; lb2 = a.lblPower_17; lb3 = a.lblP5_start_17;
                    lb4 = a.lblP5_max_17;  lb5 = a.lblP5_last_17; break;
                case 17:
                    lb1 = a.lblDuration_18; lb2 = a.lblPower_18; lb3 = a.lblP5_start_18;
                    lb4 = a.lblP5_max_18;  lb5 = a.lblP5_last_18; break;
                case 18:
                    lb1 = a.lblDuration_19; lb2 = a.lblPower_19; lb3 = a.lblP5_start_19;
                    lb4 = a.lblP5_max_19;  lb5 = a.lblP5_last_19; break;
                case 19:
                    lb1 = a.lblDuration_20; lb2 = a.lblPower_20; lb3 = a.lblP5_start_20;
                    lb4 = a.lblP5_max_20;  lb5 = a.lblP5_last_20; break;
                case 20:
                    lb1 = a.lblDuration_21; lb2 = a.lblPower_21; lb3 = a.lblP5_start_21;
                    lb4 = a.lblP5_max_21;  lb5 = a.lblP5_last_21; break;
                case 21:
                    lb1 = a.lblDuration_22; lb2 = a.lblPower_22; lb3 = a.lblP5_start_22;
                    lb4 = a.lblP5_max_22;  lb5 = a.lblP5_last_22; break;
                case 22:
                    lb1 = a.lblDuration_23; lb2 = a.lblPower_23; lb3 = a.lblP5_start_23;
                    lb4 = a.lblP5_max_23;  lb5 = a.lblP5_last_23; break;
                case 23:
                    lb1 = a.lblDuration_24; lb2 = a.lblPower_24; lb3 = a.lblP5_start_24;
                    lb4 = a.lblP5_max_24;  lb5 = a.lblP5_last_24; break;
                case 24:
                    lb1 = a.lblDuration_25; lb2 = a.lblPower_25; lb3 = a.lblP5_start_25;
                    lb4 = a.lblP5_max_25;  lb5 = a.lblP5_last_25; break;
                case 25:
                    lb1 = a.lblDuration_26; lb2 = a.lblPower_26; lb3 = a.lblP5_start_26;
                    lb4 = a.lblP5_max_26;  lb5 = a.lblP5_last_26; break;
                case 26:
                    lb1 = a.lblDuration_27; lb2 = a.lblPower_27; lb3 = a.lblP5_start_27;
                    lb4 = a.lblP5_max_27;  lb5 = a.lblP5_last_27; break;
                case 27:
                    lb1 = a.lblDuration_28; lb2 = a.lblPower_28; lb3 = a.lblP5_start_28;
                    lb4 = a.lblP5_max_28;  lb5 = a.lblP5_last_28; break;
                case 28:
                    lb1 = a.lblDuration_29; lb2 = a.lblPower_29; lb3 = a.lblP5_start_29;
                    lb4 = a.lblP5_max_29;  lb5 = a.lblP5_last_29; break;
                case 29:
                    lb1 = a.lblDuration_30; lb2 = a.lblPower_30; lb3 = a.lblP5_start_30;
                    lb4 = a.lblP5_max_30;  lb5 = a.lblP5_last_30; break;
                case 30:
                    lb1 = a.lblDuration_31; lb2 = a.lblPower_31; lb3 = a.lblP5_start_31;
                    lb4 = a.lblP5_max_31;  lb5 = a.lblP5_last_31; break;
                case 31:
                    lb1 = a.lblDuration_32; lb2 = a.lblPower_32; lb3 = a.lblP5_start_32;
                    lb4 = a.lblP5_max_32;  lb5 = a.lblP5_last_32; break;
                case 32:
                    lb1 = a.lblDuration_33; lb2 = a.lblPower_33; lb3 = a.lblP5_start_33;
                    lb4 = a.lblP5_max_33;  lb5 = a.lblP5_last_33; break;
            }

            Color clrBg = (( m_nFlashCounter % 2) == 1) ? clrRed : null;
            
            
            //lblCurrentPowerValue.setBackground( clrBg);
            lb1.setBackground( clrBg);
            lb2.setBackground( clrBg);
            lb3.setBackground( clrBg);
            lb4.setBackground( clrBg);
            lb5.setBackground( clrBg);
            
            if( ( m_nFlashCounter % 2) == 0 && m_bReadyForNextStep == true) {
                m_FlashTimer.stop();
                m_bReadyForNextStep = false;
                
                lblCurrentPowerValue.setBackground( null);
                m_rnblP5Watchdog.DropMax();

                //Переходим к следующему шагу обезгаживания
                GettersActivationProgramStep item = ( GettersActivationProgramStep) m_lstProgram.get( m_nPhase);
                item.SetP5_start( theApp.GetFromPoller( "005.01"));

                int nTime;
                if( theApp.GetSettings().GetDebugShortenProgTimes())
                    nTime = item.GetDuration() * 2000 + 200;
                else
                    nTime = item.GetDuration() * 60000 + 4000 + 200;
                m_EmuTimer = new Timer( nTime, m_pEmuListner); //setDelay( 5000 + m_nPhase * 1000); //item.GetDuration() * 2000);
                m_EmuTimer.start();

                //запустим обратный таймер шага
                if( theApp.GetSettings().GetDebugShortenProgTimes())
                    m_nTimeCounter = item.GetDuration() * 2;
                else
                    m_nTimeCounter = item.GetDuration() * 60 + 4;

                lblTimerValue.setText( String.format( "%02d:%02d", m_nTimeCounter / 60, m_nTimeCounter % 60));
                lblTimerValue.setBackground( null);
                m_TimeTimer.start();
            }
            
        }
        
        
    }
    
    static Logger logger = Logger.getLogger(TechProcessStep10_1_Dlg_A.class);
    final HVV_Admin theApp;
    
    EmulationActionListener m_pEmuListner;
    public Timer m_EmuTimer;
    int m_nPhase;
    
    int m_nFlashCounter;
    FlashActionListener m_pFlashListner;
    public Timer m_FlashTimer;
    Color clrRed; Color clrBlack;
    
    int m_nTimeCounter;
    TimeActionListener m_pTimeListner;
    public Timer m_TimeTimer;
    
    DataRefresherListener m_pDataRefreshListener;
    public Timer m_DataTimer;
    
    LinkedList m_lstProgram;
    GettersActivationProgram m_pGetActProgram;
    int m_nProgramType;
    
    TechProcessStep10p5MaxWatchdog m_rnblP5Watchdog;
    Thread m_thrP5Watchdog;
    
    TechProcessStep10_1_Dlg_M m_pParent;
    final public TechProcessStep10_1_Dlg_A_StepsPanel m_pnlSteps;
    //final public TechProcessStep10_1_Dlg_A_StepsPanelFake m_pnlSteps;
    
    boolean m_bTrailingAddded;
    
    /**
     * Creates new form TechProcessStep08_2_Dlg_2
     */
    public TechProcessStep10_1_Dlg_A( HVV_Admin app, java.awt.Frame parent, boolean modal, int nInductor, TechProcessStep10_1_Dlg_M pParent) {
        super( parent, modal);
        
        m_nInductor = nInductor;
        
        m_bTrailingAddded = false;
        
        m_pParent = pParent;
        theApp = app;
        initComponents();
        
        if( nInductor == HVV_AdminConstants.INDUCTOR1) {
            setTitle( getTitle() + " - Индуктор 1");
            lblInductorNumber.setText( "ИНДУКТОР 1");
        }
        
        if( nInductor == HVV_AdminConstants.INDUCTOR2) {
            setTitle( getTitle() + " - Индуктор 2");
            lblInductorNumber.setText( "ИНДУКТОР 2");
        }
        
        m_fontUsual = new Font( "Cantarell", Font.PLAIN, 15);
        m_fontBold = new Font( "Cantarell", Font.BOLD, 15);
        
        clrRed = new Color( 220, 0, 0);
        clrBlack = new Color( 0, 0, 0);
        
        m_pnlSteps = new TechProcessStep10_1_Dlg_A_StepsPanel( this, nInductor);
        //m_pnlSteps = new TechProcessStep10_1_Dlg_A_StepsPanelFake( this, nInductor);
        
        m_pnlSteps.setVisible( true);
        m_pnlSteps.setBounds( 0, 0, 400, 990);
        pnlInductorSteps.add( m_pnlSteps);
        
        m_bReadyForNextStep = false;
        
        //таймер отслеживания шага
        m_pEmuListner = new EmulationActionListener( theApp);
        m_EmuTimer = null; //new Timer( 1000, m_pEmuListner);
        
        //таймер моргания контролами показывающими что делать оператору
        m_pFlashListner = new FlashActionListener();
        m_FlashTimer = new Timer( 1000, m_pFlashListner);
                
        //таймер отсчёта времени шага
        m_pTimeListner = new TimeActionListener();
        m_TimeTimer = new Timer( 1000, m_pTimeListner);
        
        //таймер обновления программы активации
        m_pDataRefreshListener = new DataRefresherListener();
        m_DataTimer = new Timer( 500, m_pDataRefreshListener);
        m_DataTimer.start();
        
        m_lstProgram = new LinkedList();
        
        if( m_nInductor == HVV_AdminConstants.INDUCTOR1) {
            //исходим из того, что by default на индукторе1 тип индуктора (носика) тоже 1
            radInd1.setSelected( true);
        }
        if( m_nInductor == HVV_AdminConstants.INDUCTOR2) {
            //исходим из того, что by default на индукторе2 тип индуктора (носика) тоже 2
            radInd2.setSelected( true);
        }
        
        //DefineProgram();  <<--это будет делаться в отложке M-dialog'а
        //LoadProgram();
        
        m_pnlSteps.PlaceEdtBtns();
        
        lstRads = new LinkedList();
        lstRads.addLast( radDev1);
        lstRads.addLast( radDev2);
        lstRads.addLast( radDev3);
        lstRads.addLast( radDev4);
        lstRads.addLast( radDev5);
        lstRads.addLast( radDev6);
        lstRads.addLast( radDev7);
        lstRads.addLast( radDev8);
        
        m_nInProgress = -1;
        
        m_rnblP5Watchdog = new TechProcessStep10p5MaxWatchdog( app);
        m_thrP5Watchdog = new Thread( m_rnblP5Watchdog);
        m_thrP5Watchdog.start();
    }

    public void TurnOffRads() {
        for( Object lstRad : lstRads) {
            JRadioButton rad = ( JRadioButton) lstRad;
            rad.setEnabled( false);
        }
    }
    
    public void DefineProgram() {
        if( "1".equals( lblGetterValue.getText())) {
            if( radInd1.isSelected()) 
                m_nProgramType = 11;
            else
                m_nProgramType = 12;
        }
        else {
            if( radInd1.isSelected()) 
                m_nProgramType = 21;
            else
                m_nProgramType = 22;
        }
    }
    
    public void AddTrailingSteps() {
        //1 1    = Getter1 Inductor1
        //1 2    = Getter1 Inductor2
        //...
        switch( m_nProgramType) {
            case 11:
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 45));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 40));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 35));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 30));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 25));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 20));
                }
                m_lstProgram.add( new GettersActivationProgramStep(  3, 15));
                m_lstProgram.add( new GettersActivationProgramStep(  3, 10));
            break;
                
            case 12:
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 26));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 24));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 22));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 20));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 18));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 16));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  6));
                }
                m_lstProgram.add( new GettersActivationProgramStep( 2,  4));
                m_lstProgram.add( new GettersActivationProgramStep( 2,  2));
            break;
                
            case 21:
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 25));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 20));    
                }
                m_lstProgram.add( new GettersActivationProgramStep(  3, 15));
                m_lstProgram.add( new GettersActivationProgramStep(  3, 10));
            break;
                
            case 22:
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 26));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 24));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 22));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 20));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 18));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 16));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  6));
                }
                m_lstProgram.add( new GettersActivationProgramStep( 2,  4));
                m_lstProgram.add( new GettersActivationProgramStep( 2,  2));
            break;
        }
    }
    
    public void LoadProgram() {
        m_lstProgram.clear();
        
        //1 1    = Getter1 Inductor1
        //1 2    = Getter1 Inductor2
        //...
        switch( m_nProgramType) {
            case 11:
                m_lstProgram.add( new GettersActivationProgramStep(  3, 10));
                m_lstProgram.add( new GettersActivationProgramStep(  3, 15));
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 20));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 25));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 30));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 35));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 40));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 45));
                }
                m_pnlSteps.edtDuration.setText( "5");
                m_pnlSteps.edtPower.setText( "50");
            break;
                
            case 12:
                m_lstProgram.add( new GettersActivationProgramStep( 2,  2));
                m_lstProgram.add( new GettersActivationProgramStep( 2,  4));
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  6));
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 16));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 18));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 20));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 22));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 24));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 26));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 28));
                }
                m_pnlSteps.edtDuration.setText( "5");
                m_pnlSteps.edtPower.setText( "29");
            break;
                
            case 21:
                m_lstProgram.add( new GettersActivationProgramStep(  3, 10));
                m_lstProgram.add( new GettersActivationProgramStep(  3, 15));
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 20));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 25));
                    m_lstProgram.add( new GettersActivationProgramStep(  5, 30));
                }
                m_pnlSteps.edtDuration.setText( "5");
                m_pnlSteps.edtPower.setText( "40");
            break;
                
            case 22:
                m_lstProgram.add( new GettersActivationProgramStep( 2,  2));
                m_lstProgram.add( new GettersActivationProgramStep( 2,  4));
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  6));
                    m_lstProgram.add( new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 16));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 18));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 20));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 22));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 24));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 26));
                    m_lstProgram.add( new GettersActivationProgramStep( 2, 28));
                }
                m_pnlSteps.edtDuration.setText( "5");
                m_pnlSteps.edtPower.setText( "29");
            break;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        btnGrpInductor = new javax.swing.ButtonGroup();
        btnGroupDevice = new javax.swing.ButtonGroup();
        lblDevTittle = new javax.swing.JLabel();
        lblGetterTitle = new javax.swing.JLabel();
        lblGetterValue = new javax.swing.JLabel();
        lblIndTitle = new javax.swing.JLabel();
        radInd1 = new javax.swing.JRadioButton();
        radInd2 = new javax.swing.JRadioButton();
        btnStart = new javax.swing.JButton();
        lblTimeTitle = new javax.swing.JLabel();
        lblTimerValue = new javax.swing.JLabel();
        lblCurrentPowerTitle = new javax.swing.JLabel();
        lblCurrentPowerValue = new javax.swing.JLabel();
        lblDuration_Title = new javax.swing.JLabel();
        lblPower_Title = new javax.swing.JLabel();
        lblP5start_Title = new javax.swing.JLabel();
        lblP5max_Title = new javax.swing.JLabel();
        lblP5last_Title = new javax.swing.JLabel();
        radDev1 = new javax.swing.JRadioButton();
        radDev2 = new javax.swing.JRadioButton();
        radDev3 = new javax.swing.JRadioButton();
        radDev4 = new javax.swing.JRadioButton();
        radDev5 = new javax.swing.JRadioButton();
        radDev6 = new javax.swing.JRadioButton();
        radDev7 = new javax.swing.JRadioButton();
        radDev8 = new javax.swing.JRadioButton();
        pnlInductorSteps = new javax.swing.JPanel();
        scrlBar = new javax.swing.JScrollBar();
        lblInductorNumber = new javax.swing.JLabel();
        btnNextStep = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("10.1 Активация");
        setMaximumSize(new java.awt.Dimension(450, 1025));
        setMinimumSize(new java.awt.Dimension(450, 1025));
        setModalExclusionType(null);
        setModalityType(null);
        setResizable(false);
        getContentPane().setLayout(null);

        lblDevTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDevTittle.setText("Прибор:");
        getContentPane().add(lblDevTittle);
        lblDevTittle.setBounds(0, 40, 450, 30);

        lblGetterTitle.setText("Тип геттера:");
        getContentPane().add(lblGetterTitle);
        lblGetterTitle.setBounds(10, 120, 110, 30);

        lblGetterValue.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblGetterValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGetterValue.setText("1");
        lblGetterValue.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblGetterValue.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGetterValueMouseClicked(evt);
            }
        });
        getContentPane().add(lblGetterValue);
        lblGetterValue.setBounds(130, 120, 60, 30);

        lblIndTitle.setText("Тип индуктора:");
        getContentPane().add(lblIndTitle);
        lblIndTitle.setBounds(230, 120, 110, 30);

        btnGrpInductor.add(radInd1);
        radInd1.setSelected(true);
        radInd1.setText("1");
        radInd1.setEnabled(false);
        radInd1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radInd1MouseClicked(evt);
            }
        });
        getContentPane().add(radInd1);
        radInd1.setBounds(350, 120, 40, 30);

        btnGrpInductor.add(radInd2);
        radInd2.setText("2");
        radInd2.setEnabled(false);
        radInd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radInd2MouseClicked(evt);
            }
        });
        getContentPane().add(radInd2);
        radInd2.setBounds(400, 120, 40, 30);

        btnStart.setText("Запуск");
        btnStart.setEnabled(false);
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        getContentPane().add(btnStart);
        btnStart.setBounds(10, 160, 430, 60);

        lblTimeTitle.setText("Время:");
        getContentPane().add(lblTimeTitle);
        lblTimeTitle.setBounds(10, 220, 430, 30);

        lblTimerValue.setFont(new java.awt.Font("Cantarell", 0, 80)); // NOI18N
        lblTimerValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimerValue.setText("-");
        lblTimerValue.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblTimerValue);
        lblTimerValue.setBounds(10, 250, 430, 90);

        lblCurrentPowerTitle.setText("Мощность:");
        getContentPane().add(lblCurrentPowerTitle);
        lblCurrentPowerTitle.setBounds(10, 390, 430, 30);

        lblCurrentPowerValue.setFont(new java.awt.Font("Cantarell", 0, 80)); // NOI18N
        lblCurrentPowerValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentPowerValue.setText("-");
        lblCurrentPowerValue.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblCurrentPowerValue.setOpaque(true);
        getContentPane().add(lblCurrentPowerValue);
        lblCurrentPowerValue.setBounds(10, 420, 430, 90);

        lblDuration_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title.setText("<html>t</html>");
        lblDuration_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title);
        lblDuration_Title.setBounds(10, 520, 60, 30);

        lblPower_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title.setText("<html>W</html>");
        lblPower_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title);
        lblPower_Title.setBounds(70, 520, 70, 30);

        lblP5start_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title.setText("<html>P<sub>5, начальное</sub></html>:");
        lblP5start_Title.setToolTipText("");
        lblP5start_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title);
        lblP5start_Title.setBounds(140, 520, 90, 30);

        lblP5max_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title.setText("<html>P<sub>5, макс.</sub></html>:");
        lblP5max_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title);
        lblP5max_Title.setBounds(230, 520, 90, 30);

        lblP5last_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title.setText("<html>P<sub>5, конечное</sub></html>:");
        lblP5last_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title);
        lblP5last_Title.setBounds(320, 520, 90, 30);

        btnGroupDevice.add(radDev1);
        radDev1.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev1.setText("1");
        radDev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev1ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev1);
        radDev1.setBounds(5, 70, 45, 40);

        btnGroupDevice.add(radDev2);
        radDev2.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev2.setText("2");
        radDev2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev2ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev2);
        radDev2.setBounds(60, 70, 45, 40);

        btnGroupDevice.add(radDev3);
        radDev3.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev3.setText("3");
        radDev3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev3ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev3);
        radDev3.setBounds(115, 70, 45, 40);

        btnGroupDevice.add(radDev4);
        radDev4.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev4.setText("4");
        radDev4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev4ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev4);
        radDev4.setBounds(170, 70, 45, 40);

        btnGroupDevice.add(radDev5);
        radDev5.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev5.setText("5");
        radDev5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev5ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev5);
        radDev5.setBounds(225, 70, 45, 40);

        btnGroupDevice.add(radDev6);
        radDev6.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev6.setText("6");
        radDev6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev6ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev6);
        radDev6.setBounds(280, 70, 45, 40);

        btnGroupDevice.add(radDev7);
        radDev7.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev7.setText("7");
        radDev7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev7ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev7);
        radDev7.setBounds(335, 70, 45, 40);

        btnGroupDevice.add(radDev8);
        radDev8.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev8.setText("8");
        radDev8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev8ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev8);
        radDev8.setBounds(390, 70, 45, 40);

        pnlInductorSteps.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        pnlInductorSteps.setPreferredSize(new java.awt.Dimension(410, 440));
        pnlInductorSteps.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlInductorStepsMouseWheelMoved(evt);
            }
        });
        pnlInductorSteps.setLayout(null);
        getContentPane().add(pnlInductorSteps);
        pnlInductorSteps.setBounds(10, 550, 400, 430);

        scrlBar.setMaximum(580);
        scrlBar.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrlBarAdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(scrlBar);
        scrlBar.setBounds(420, 550, 20, 430);

        lblInductorNumber.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblInductorNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInductorNumber.setText("ИНДУКТОР Х");
        getContentPane().add(lblInductorNumber);
        lblInductorNumber.setBounds(0, 0, 450, 30);

        btnNextStep.setText("Далее");
        btnNextStep.setEnabled(false);
        btnNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextStepActionPerformed(evt);
            }
        });
        getContentPane().add(btnNextStep);
        btnNextStep.setBounds(100, 350, 250, 40);

        pack();
    }// </editor-fold>                        

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {                                         
        TurnOffRads();

        btnStart.setEnabled( false);
        radInd1.setEnabled( false);
        radInd2.setEnabled( false);
                
        if( radDev1.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE1;
        if( radDev2.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE2;
        if( radDev3.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE3;
        if( radDev4.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE4;
        if( radDev5.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE5;
        if( radDev6.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE6;
        if( radDev7.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE7;
        if( radDev8.isSelected()) m_nInProgress = HVV_AdminConstants.DEVICE8;
        
        m_pnlSteps.PlaceEdtBtns();
        
        m_nPhase = 0;
        
        //Стартуем прооцесс активации для конкретного девайса
        int nInductorType = -1;
        if( radInd1.isSelected()) nInductorType = HVV_AdminConstants.INDUCTOR_TYPE1;
        if( radInd2.isSelected()) nInductorType = HVV_AdminConstants.INDUCTOR_TYPE2;
        m_pGetActProgram = new GettersActivationProgram( theApp.GetLocalDate(), nInductorType);
        
        if( "1".equals( lblGetterValue.getText()))
            m_pGetActProgram.SetGetter( HVV_AdminConstants.GETTER1);
        else
            m_pGetActProgram.SetGetter( HVV_AdminConstants.GETTER2);
        
        
        GettersActivationProgramStep item = ( GettersActivationProgramStep) m_lstProgram.get( m_nPhase);
        item.SetP5_start( theApp.GetFromPoller( "005.01"));
        
        m_rnblP5Watchdog.DropMax();
        
        //покажем какую мощность надо поставить
        lblCurrentPowerValue.setText( "" + item.GetPower());

        int nTime;
        if( theApp.GetSettings().GetDebugShortenProgTimes())
            nTime = item.GetDuration() * 2000 + 200;
        else
            nTime = item.GetDuration() * 60000 + 4000 + 200;
        m_EmuTimer = new Timer( nTime, m_pEmuListner);
        m_EmuTimer.start();
        
        /*
        //запустим оповещение оператора
        m_nFlashCounter = 0;
        m_FlashTimer.start();
        */
        m_bReadyForNextStep = false;
        
        //запустим таймер шага
        if( theApp.GetSettings().GetDebugShortenProgTimes())
            m_nTimeCounter = item.GetDuration() * 2;
        else
            m_nTimeCounter = item.GetDuration() * 60 + 4;
        lblTimerValue.setText( String.format( "%02d:%02d", m_nTimeCounter / 60, m_nTimeCounter % 60));
        m_TimeTimer.start();
        
        m_bTrailingAddded = false;
        
        
        
        /*
            lblDuration_1.setFont( m_fontBold);
        lblPower_1.setFont( m_fontBold);
        lblP5_start_1.setFont( m_fontBold);
        lblP5_max_1.setFont( m_fontBold);
        lblP5_last_1.setFont( m_fontBold);
        */    
    }                                        

    private void lblGetterValueMouseClicked(java.awt.event.MouseEvent evt) {                                            
        if( m_nInProgress != -1) return;
        
        if( "1".equals( lblGetterValue.getText()))
            lblGetterValue.setText( "2");
        else
            lblGetterValue.setText( "1");
        
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                           

    private void radInd2MouseClicked(java.awt.event.MouseEvent evt) {                                     
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                    

    private void radInd1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                    

    private void radDev1ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE1);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void radDev2ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE2);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void radDev3ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE3);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void radDev4ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE4);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void radDev5ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE5);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void radDev6ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE6);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void radDev7ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE7);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void radDev8ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE8);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        //RefreshProgram();
        m_pnlSteps.PlaceEdtBtns();
    }                                       

    private void scrlBarAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {                                               

        if( m_pnlSteps != null)
            m_pnlSteps.setBounds( 0, 0 - scrlBar.getValue(), 400, 440 + scrlBar.getValue());
        
    }                                              

    private void pnlInductorStepsMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {                                                 
        if( scrlBar.isEnabled()) {
            int nMin = scrlBar.getMinimum();
            int nMax = scrlBar.getMaximum();
            int nPos = scrlBar.getValue();
            int nStep1 = scrlBar.getBlockIncrement();
            int nStep2 = scrlBar.getUnitIncrement();

            int nNextPos = nPos + evt.getWheelRotation() * nStep1;

            if( nNextPos < nMin) nNextPos = nMin;
            if( nNextPos > nMax) nNextPos = nMax;
            scrlBar.setValue( nNextPos);
        }
    }                                                

    private void btnNextStepActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        if( m_bTrailingAddded == false && m_nPhase == m_lstProgram.size())
            return;
                
        btnNextStep.setEnabled( false);
        m_bReadyForNextStep = true;
        
        
        
        /*lblCurrentPowerValue.setBackground( null);
        m_rnblP5Watchdog.DropMax();
        
        //Переходим к следующему шагу обезгаживания
        GettersActivationProgramStep item = ( GettersActivationProgramStep) m_lstProgram.get( m_nPhase);
        item.SetP5_start( theApp.GetFromPoller( "005.01"));

        int nTime;
        if( theApp.GetSettings().GetDebugShortenProgTimes())
            nTime = item.GetDuration() * 2000 + 200;
        else
            nTime = item.GetDuration() * 60000 + 4000 + 200;
        m_EmuTimer = new Timer( nTime, m_pEmuListner); //setDelay( 5000 + m_nPhase * 1000); //item.GetDuration() * 2000);
        m_EmuTimer.start();

        //запустим обратный таймер шага
        if( theApp.GetSettings().GetDebugShortenProgTimes())
            m_nTimeCounter = item.GetDuration() * 2;
        else
            m_nTimeCounter = item.GetDuration() * 60 + 4;
        
        lblTimerValue.setText( String.format( "%02d:%02d", m_nTimeCounter / 60, m_nTimeCounter % 60));
        lblTimerValue.setBackground( null);
        m_TimeTimer.start();*/
    }                                           

    public void setRadButtonsState( boolean bFirstTime) {
        if( m_nInProgress != -1) return;
        
        for( int i = 0; i < 8; i++) {
            JRadioButton radBtn = ( JRadioButton) lstRads.get(i);
            
            boolean bEnabled = true;
            if( bEnabled) {
                //pay attention!!! i=[0;7] ==> [DEVICE1;..;DEVICE7]
                if( theApp.m_mapDevicePresence.containsKey(i)) {
                    boolean bPresent = ( boolean) theApp.m_mapDevicePresence.get( i);
                    bEnabled &= bPresent;
                }
            }

            if( bEnabled) {
                //pay attention!!! i=[0;7] ==> [DEVICE1;..;DEVICE7]
                if( theApp.m_mapStep6_3_Continue.containsKey(i)) {
                    boolean bContinue = ( boolean) theApp.m_mapStep6_3_Continue.get( i);
                    bEnabled &= bContinue;
                }
            }
            
            if( bFirstTime == false) {
                //если это вызов выставок кнопок НЕ первого раза - надо посмотреть - а не выбран ли этот прибор на диалоге второго индуктора

                if( m_nInductor == HVV_AdminConstants.INDUCTOR1) {
                    if( m_pParent.m_dlgInd2 != null)
                        if( m_pParent.m_dlgInd2.lstRads != null) {
                            JRadioButton radBtn2 = ( JRadioButton) m_pParent.m_dlgInd2.lstRads.get(i);
                            if( radBtn2.isSelected())
                                bEnabled = false;
                        }
                }
                
                if( m_nInductor == HVV_AdminConstants.INDUCTOR2) {
                    if( m_pParent.m_dlgInd1 != null)
                        if( m_pParent.m_dlgInd1.lstRads != null) {
                            JRadioButton radBtn2 = ( JRadioButton) m_pParent.m_dlgInd1.lstRads.get(i);
                            if( radBtn2.isSelected())
                                bEnabled = false;
                        }
                }
                
                if( bEnabled) {
                    //pay attention!!! i=[0;7] ==> [DEVICE1;..;DEVICE7]
                    
                    boolean bAlready = theApp.m_mapActivation.containsKey(i);
                    bEnabled &= (!bAlready);
                    
                    /*
                    if( theApp.m_mapDegassing.containsKey(i)) {
                        boolean bAlready = ( boolean) theApp.m_mapDegassing.get( i);
                        bEnabled &= (!bAlready);      
                    }
                    */
                }
                
            }
            
            radBtn.setEnabled( bEnabled);
        }
    }
    
    public void RefreshProgram() {
        TechProcessStep10_1_Dlg_A_StepsPanel a = m_pnlSteps;
        
        JLabel [] arrDurations = { a.lblDuration_1,  a.lblDuration_2,  a.lblDuration_3,  a.lblDuration_4,
                                   a.lblDuration_5,  a.lblDuration_6,  a.lblDuration_7,  a.lblDuration_8,
                                   a.lblDuration_9,  a.lblDuration_10, a.lblDuration_11, a.lblDuration_12,
                                   a.lblDuration_13, a.lblDuration_14, a.lblDuration_15, a.lblDuration_16,
                                   a.lblDuration_17, a.lblDuration_18, a.lblDuration_19, a.lblDuration_20,
                                   a.lblDuration_21, a.lblDuration_22, a.lblDuration_23, a.lblDuration_24,
                                   a.lblDuration_25, a.lblDuration_26, a.lblDuration_27, a.lblDuration_28,
                                   a.lblDuration_29, a.lblDuration_30, a.lblDuration_31, a.lblDuration_32,
                                   a.lblDuration_33 };
        
        JLabel [] arrPowers = {    a.lblPower_1,  a.lblPower_2,  a.lblPower_3,  a.lblPower_4,
                                   a.lblPower_5,  a.lblPower_6,  a.lblPower_7,  a.lblPower_8,
                                   a.lblPower_9,  a.lblPower_10, a.lblPower_11, a.lblPower_12,
                                   a.lblPower_13, a.lblPower_14, a.lblPower_15, a.lblPower_16,
                                   a.lblPower_17, a.lblPower_18, a.lblPower_19, a.lblPower_20,
                                   a.lblPower_21, a.lblPower_22, a.lblPower_23, a.lblPower_24,
                                   a.lblPower_25, a.lblPower_26, a.lblPower_27, a.lblPower_28,
                                   a.lblPower_29, a.lblPower_30, a.lblPower_31, a.lblPower_32,
                                   a.lblPower_33 };
        
        JLabel [] arrP5start = {   a.lblP5_start_1,  a.lblP5_start_2,  a.lblP5_start_3,  a.lblP5_start_4,
                                   a.lblP5_start_5,  a.lblP5_start_6,  a.lblP5_start_7,  a.lblP5_start_8,
                                   a.lblP5_start_9,  a.lblP5_start_10, a.lblP5_start_11, a.lblP5_start_12,
                                   a.lblP5_start_13, a.lblP5_start_14, a.lblP5_start_15, a.lblP5_start_16,
                                   a.lblP5_start_17, a.lblP5_start_18, a.lblP5_start_19, a.lblP5_start_20,
                                   a.lblP5_start_21, a.lblP5_start_22, a.lblP5_start_23, a.lblP5_start_24,
                                   a.lblP5_start_25, a.lblP5_start_26, a.lblP5_start_27, a.lblP5_start_28,
                                   a.lblP5_start_29, a.lblP5_start_30, a.lblP5_start_31, a.lblP5_start_32,
                                   a.lblP5_start_33 };
        
        JLabel [] arrP5max = {     a.lblP5_max_1,  a.lblP5_max_2,  a.lblP5_max_3,  a.lblP5_max_4,
                                   a.lblP5_max_5,  a.lblP5_max_6,  a.lblP5_max_7,  a.lblP5_max_8,
                                   a.lblP5_max_9,  a.lblP5_max_10, a.lblP5_max_11, a.lblP5_max_12,
                                   a.lblP5_max_13, a.lblP5_max_14, a.lblP5_max_15, a.lblP5_max_16,
                                   a.lblP5_max_17, a.lblP5_max_18, a.lblP5_max_19, a.lblP5_max_20,
                                   a.lblP5_max_21, a.lblP5_max_22, a.lblP5_max_23, a.lblP5_max_24,
                                   a.lblP5_max_25, a.lblP5_max_26, a.lblP5_max_27, a.lblP5_max_28,
                                   a.lblP5_max_29, a.lblP5_max_30, a.lblP5_max_31, a.lblP5_max_32,
                                   a.lblP5_max_33 };
        
        JLabel [] arrP5last = {    a.lblP5_last_1,  a.lblP5_last_2,  a.lblP5_last_3,  a.lblP5_last_4,
                                   a.lblP5_last_5,  a.lblP5_last_6,  a.lblP5_last_7,  a.lblP5_last_8,
                                   a.lblP5_last_9,  a.lblP5_last_10, a.lblP5_last_11, a.lblP5_last_12,
                                   a.lblP5_last_13, a.lblP5_last_14, a.lblP5_last_15, a.lblP5_last_16,
                                   a.lblP5_last_17, a.lblP5_last_18, a.lblP5_last_19, a.lblP5_last_20,
                                   a.lblP5_last_21, a.lblP5_last_22, a.lblP5_last_23, a.lblP5_last_24,
                                   a.lblP5_last_25, a.lblP5_last_26, a.lblP5_last_27, a.lblP5_last_28,
                                   a.lblP5_last_29, a.lblP5_last_30, a.lblP5_last_31, a.lblP5_last_32,
                                   a.lblP5_last_33 };
        
        
        for( int i=0; i<33; i++) {
            if( i < m_lstProgram.size()) {
                GettersActivationProgramStep step = ( GettersActivationProgramStep) m_lstProgram.get(i);
                
                Font font = m_fontUsual;
                if( m_nInProgress != -1 && m_nPhase == i)
                    font = m_fontBold;
                
                if( arrDurations[i].isShowing()) {
                    //DURATION
                    arrDurations[i].setFont( font);
                    arrDurations[i].setText( "" + step.GetDuration());

                    //POWER
                    arrPowers[i].setFont( font);
                    arrPowers[i].setText( "" + step.GetPower());

                    //P5_START
                    arrP5start[i].setFont( font);
                    if( step.GetP5_start() != 0.)
                        arrP5start[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_start(), false) + "</html>");
                    else
                        arrP5start[i].setText( "-");

                    //P5_MAX
                    if( m_nPhase == i) {

                        if( m_nInProgress != -1) {
                            arrP5max[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( m_rnblP5Watchdog.GetP5Max(), false) + "</html>");
                            arrP5max[i].setFont( m_fontBold);
                        }
                        else {
                            arrP5max[i].setText( "-");
                            arrP5max[i].setFont( m_fontUsual);
                        }
                    }
                    else {
                        arrP5max[i].setFont(  m_fontUsual);

                        if( step.GetP5_max() != 0.)
                            arrP5max[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_max(), false) + "</html>");
                        else
                            arrP5max[i].setText( "-");
                    }

                    //P5_LAST
                    arrP5last[i].setFont( font);
                    if( step.GetP5_last() != 0.)
                        arrP5last[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_last(), false) + "</html>");
                    else
                        arrP5last[i].setText( "-");
                }
            }
            else {
                if( arrDurations[i].isShowing()) {
                    arrDurations[i].setFont( GetUsualFont());
                    arrDurations[i].setText( "-");
                    arrPowers[i].setFont( GetUsualFont());
                    arrPowers[i].setText( "-");
                    arrP5start[i].setFont( GetUsualFont());
                    arrP5start[i].setText( "-");
                    arrP5max[i].setFont( GetUsualFont());
                    arrP5max[i].setText( "-");
                    arrP5last[i].setFont( GetUsualFont());
                    arrP5last[i].setText( "-");
                }
            }
        }
        
        //m_pnlSteps.setBounds( 0, 0 - scrlBar.getValue(), 400, 1000);// + scrlBar.getValue());        
    }
    
    

    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup btnGroupDevice;
    public javax.swing.ButtonGroup btnGrpInductor;
    public javax.swing.JButton btnNextStep;
    public javax.swing.JButton btnStart;
    private javax.swing.JLabel lblCurrentPowerTitle;
    private javax.swing.JLabel lblCurrentPowerValue;
    private javax.swing.JLabel lblDevTittle;
    private javax.swing.JLabel lblDuration_Title;
    private javax.swing.JLabel lblGetterTitle;
    public javax.swing.JLabel lblGetterValue;
    private javax.swing.JLabel lblIndTitle;
    private javax.swing.JLabel lblInductorNumber;
    private javax.swing.JLabel lblP5last_Title;
    private javax.swing.JLabel lblP5max_Title;
    private javax.swing.JLabel lblP5start_Title;
    private javax.swing.JLabel lblPower_Title;
    private javax.swing.JLabel lblTimeTitle;
    private javax.swing.JLabel lblTimerValue;
    private javax.swing.JPanel pnlInductorSteps;
    private javax.swing.JRadioButton radDev1;
    private javax.swing.JRadioButton radDev2;
    private javax.swing.JRadioButton radDev3;
    private javax.swing.JRadioButton radDev4;
    private javax.swing.JRadioButton radDev5;
    private javax.swing.JRadioButton radDev6;
    private javax.swing.JRadioButton radDev7;
    private javax.swing.JRadioButton radDev8;
    public javax.swing.JRadioButton radInd1;
    public javax.swing.JRadioButton radInd2;
    private javax.swing.JScrollBar scrlBar;
    // End of variables declaration                   
}
