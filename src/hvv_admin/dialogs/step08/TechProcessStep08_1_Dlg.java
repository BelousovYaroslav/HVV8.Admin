package hvv_admin.dialogs.step08;

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
public class TechProcessStep08_1_Dlg extends javax.swing.JDialog {

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
            RefreshProgram();
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
            item.SetP5_last( theApp.GetDoubleFromPoller( "005.01"));
            
            m_nPhase++;
            if( m_nPhase == m_lstProgram.size()) {
                //конец программы этапа
                m_pGetActProgram.SetDtFinish( theApp.GetLocalDate());
                m_pGetActProgram.CloneListSteps( m_lstProgram);
                
                //мы никого не обрабатываем
                m_nInProgress = -1;

                //текущая мощность не нужна
                lblCurrentPowerValue.setText( "-");

                //текущий таймер тоже надо сбросить
                lblTimerValue.setText( "-");

                //показываем кого мы отдегазировали
                if( radDev1.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE1, m_pGetActProgram);
                if( radDev2.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE2, m_pGetActProgram);
                if( radDev3.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE3, m_pGetActProgram);
                if( radDev4.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE4, m_pGetActProgram);
                if( radDev5.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE5, m_pGetActProgram);
                if( radDev6.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE6, m_pGetActProgram);
                if( radDev7.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE7, m_pGetActProgram);
                if( radDev8.isSelected()) theApp.m_mapDegassing.put( HVV_AdminConstants.DEVICE8, m_pGetActProgram);
                
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
                            if( !theApp.m_mapDegassing.containsKey( i)) {
                                bAllDvcsDegasated = false;
                                break;
                            }
                        }
                    }
                }
                
                if( bAllDvcsDegasated)
                    m_pParent.btnNext.setEnabled( true);
                
                
                
                //определяем кто мы из двух индукторов
                TechProcessStep08_1_Dlg dlg = null;
                TechProcessStep08_1_Dlg dlg1 = m_pParent.m_dlgInd1;
                TechProcessStep08_1_Dlg dlg2 = m_pParent.m_dlgInd2;
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

                        JRadioButton rad_ind1 = ( JRadioButton) dlg1.lstRads.get(i + 4);
                        JRadioButton rad_ind2 = ( JRadioButton) dlg2.lstRads.get(i);
                        
                        if( rad_ind1.isEnabled() && rad_ind2.isEnabled()) {

                            logger.debug( "1. Пара (" + (i+4) + ", " + i + ") хороша");

                            rad_ind1.setSelected( true);
                            rad_ind2.setSelected( true);


                            dlg1.btnStartLevel.setEnabled( true);
                            dlg1.radInd1.setEnabled( true);
                            dlg1.radInd2.setEnabled( true);

                            dlg2.btnStartLevel.setEnabled( true);
                            dlg2.radInd1.setEnabled( true);
                            dlg2.radInd2.setEnabled( true);


                            int nGetter = ( int) theApp.m_mapDeviceGetter.get( i + 4);
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

                                dlg.btnStartLevel.setEnabled( true);
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

                                    dlg.btnStartLevel.setEnabled( true);
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

                    
                    /*
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
                            if( nGetter == HVV_AdminConstants.GETTER1) dlg.lblGetterValue.setText( "1");
                            if( nGetter == HVV_AdminConstants.GETTER2) dlg.lblGetterValue.setText( "2");
                            
                            if( m_nInductor == HVV_AdminConstants.INDUCTOR1) dlg.radInd1.setSelected( true);
                            if( m_nInductor == HVV_AdminConstants.INDUCTOR2) dlg.radInd2.setSelected( true);

                            break;
                        }
                    }*/

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
            }
            
            btnNextStep.setEnabled( true);
            m_bReadyForNextStep = false;
            
            //Переходим к следующему шагу обезгаживания
            item = ( GettersActivationProgramStep) m_lstProgram.get( m_nPhase);
            
            //покажем какую мощность надо поставить
            lblCurrentPowerValue.setText( "" + item.GetPower());
            lblCurrentPowerValue.setBackground( clrRed);
            
            //запустим оповещение оператора
            m_nFlashCounter = 0;
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
            
            JLabel lb1 = lblDuration_1;
            JLabel lb2 = lblPower_1;
            JLabel lb3 = lblP5_start_1;
            JLabel lb4 = lblP5_max_1;
            JLabel lb5 = lblP5_last_1;
            
            switch( m_nPhase) {
                case  0: lb1 = lblDuration_1;  lb2 = lblPower_1;  lb3 = lblP5_start_1;  lb4 = lblP5_max_1;  lb5 = lblP5_last_1; break;
                case  1: lb1 = lblDuration_2;  lb2 = lblPower_2;  lb3 = lblP5_start_2;  lb4 = lblP5_max_2;  lb5 = lblP5_last_2; break;
                case  2: lb1 = lblDuration_3;  lb2 = lblPower_3;  lb3 = lblP5_start_3;  lb4 = lblP5_max_3;  lb5 = lblP5_last_3; break;
                case  3: lb1 = lblDuration_4;  lb2 = lblPower_4;  lb3 = lblP5_start_4;  lb4 = lblP5_max_4;  lb5 = lblP5_last_4; break;
                case  4: lb1 = lblDuration_5;  lb2 = lblPower_5;  lb3 = lblP5_start_5;  lb4 = lblP5_max_5;  lb5 = lblP5_last_5; break;
                case  5: lb1 = lblDuration_6;  lb2 = lblPower_6;  lb3 = lblP5_start_6;  lb4 = lblP5_max_6;  lb5 = lblP5_last_6; break;
                case  6: lb1 = lblDuration_7;  lb2 = lblPower_7;  lb3 = lblP5_start_7;  lb4 = lblP5_max_7;  lb5 = lblP5_last_7; break;
                case  7: lb1 = lblDuration_8;  lb2 = lblPower_8;  lb3 = lblP5_start_8;  lb4 = lblP5_max_8;  lb5 = lblP5_last_8; break;
                case  8: lb1 = lblDuration_9;  lb2 = lblPower_9;  lb3 = lblP5_start_9;  lb4 = lblP5_max_9;  lb5 = lblP5_last_9; break;
                case  9: lb1 = lblDuration_10; lb2 = lblPower_10; lb3 = lblP5_start_10; lb4 = lblP5_max_10; lb5 = lblP5_last_10; break;
                case 10: lb1 = lblDuration_11; lb2 = lblPower_11; lb3 = lblP5_start_11; lb4 = lblP5_max_11; lb5 = lblP5_last_11; break;
                case 11: lb1 = lblDuration_12; lb2 = lblPower_12; lb3 = lblP5_start_12; lb4 = lblP5_max_12; lb5 = lblP5_last_12; break;
                case 12: lb1 = lblDuration_13; lb2 = lblPower_13; lb3 = lblP5_start_13; lb4 = lblP5_max_13; lb5 = lblP5_last_13; break;
                case 13: lb1 = lblDuration_14; lb2 = lblPower_14; lb3 = lblP5_start_14; lb4 = lblP5_max_14; lb5 = lblP5_last_14; break;
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
                item.SetP5_start( theApp.GetDoubleFromPoller( "005.01"));

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
    
    static Logger logger = Logger.getLogger( TechProcessStep08_1_Dlg.class);
    final private HVV_Admin theApp;
    
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
    private int m_nProgramType;
    
    TechProcessStep08p5MaxWatchdog m_rnblP5Watchdog;
    Thread m_thrP5Watchdog;
    
    TechProcessStep08_1_Dlg_M m_pParent;
    
    /**
     * Creates new form TechProcessStep08_2_Dlg_2
     */
    public TechProcessStep08_1_Dlg( HVV_Admin app, java.awt.Frame parent, boolean modal, int nInductor, TechProcessStep08_1_Dlg_M pParent) {
        super( parent, modal);
        
        m_nInductor = nInductor;
        
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
        
        //таймер отслеживания шага
        m_pEmuListner = new EmulationActionListener( theApp);
        m_EmuTimer = null; //new Timer( 1000, m_pEmuListner);
        
        //таймер моргания контролами показывающими что делать оператору
        m_pFlashListner = new FlashActionListener();
        m_FlashTimer = new Timer( 1000, m_pFlashListner);
        
        //таймер отсчёта времени шага
        m_pTimeListner = new TimeActionListener();
        m_TimeTimer = new Timer( 1000, m_pTimeListner);
        
        //таймер обновления программы
        m_pDataRefreshListener = new DataRefresherListener();
        m_DataTimer = new Timer( 250, m_pDataRefreshListener);
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
        
        lstRads = new LinkedList();
        lstRads.addLast( radDev1);
        lstRads.addLast( radDev2);
        lstRads.addLast( radDev3);
        lstRads.addLast( radDev4);
        lstRads.addLast( radDev5);
        lstRads.addLast( radDev6);
        lstRads.addLast( radDev7);
        lstRads.addLast( radDev8);
        
        radDev1.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE1));
        radDev2.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE2));
        radDev3.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE3));
        radDev4.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE4));
        radDev5.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE5));
        radDev6.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE6));
        radDev7.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE7));
        radDev8.setEnabled( !theApp.m_mapDegassing.containsKey( HVV_AdminConstants.DEVICE8));

        m_nInProgress = -1;
        
        m_bReadyForNextStep = false;
                
        m_rnblP5Watchdog = new TechProcessStep08p5MaxWatchdog( app);
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
    
    public void LoadProgram() {
        m_lstProgram.clear();
        switch( m_nProgramType) {
            case 11:
                m_lstProgram.add(new GettersActivationProgramStep(  3, 10));
                m_lstProgram.add(new GettersActivationProgramStep(  3, 15));
                if( theApp.GetSettings().GetDebugShortenProgItems()== false) {
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 20));
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 25));
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 30));
                    m_lstProgram.add(new GettersActivationProgramStep( 10, 35));
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 30));
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 25));
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 20));
                    m_lstProgram.add(new GettersActivationProgramStep(  3, 15));
                    m_lstProgram.add(new GettersActivationProgramStep(  3, 10));
                }
            break;
                
            case 12:
                m_lstProgram.add(new GettersActivationProgramStep( 2,  2));
                m_lstProgram.add(new GettersActivationProgramStep( 2,  4));
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  6));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 16));
                    m_lstProgram.add(new GettersActivationProgramStep( 5, 17));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  6));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  4));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  2));
                }
            break;
                
            case 21:
                m_lstProgram.add(new GettersActivationProgramStep(  3, 10));
                m_lstProgram.add(new GettersActivationProgramStep(  3, 15));
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 20));
                    m_lstProgram.add(new GettersActivationProgramStep( 10, 25));
                    m_lstProgram.add(new GettersActivationProgramStep(  5, 20));
                    m_lstProgram.add(new GettersActivationProgramStep(  3, 15));
                    m_lstProgram.add(new GettersActivationProgramStep(  3, 10));
                }
            break;
                
            case 22:
                m_lstProgram.add(new GettersActivationProgramStep( 2,  2));
                m_lstProgram.add(new GettersActivationProgramStep( 2,  4));
                if( theApp.GetSettings().GetDebugShortenProgItems() == false) {
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  6));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 16));
                    m_lstProgram.add(new GettersActivationProgramStep( 5, 17));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 14));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 12));
                    m_lstProgram.add(new GettersActivationProgramStep( 2, 10));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  8));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  6));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  4));
                    m_lstProgram.add(new GettersActivationProgramStep( 2,  2));
                }
            break;
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

        btnGrpInductor = new javax.swing.ButtonGroup();
        btnGroupDevice = new javax.swing.ButtonGroup();
        lblDevTittle = new javax.swing.JLabel();
        lblGetterTitle = new javax.swing.JLabel();
        lblGetterValue = new javax.swing.JLabel();
        lblIndTitle = new javax.swing.JLabel();
        radInd1 = new javax.swing.JRadioButton();
        radInd2 = new javax.swing.JRadioButton();
        btnStartLevel = new javax.swing.JButton();
        lblTimeTitle = new javax.swing.JLabel();
        lblTimerValue = new javax.swing.JLabel();
        lblCurrentPowerTitle = new javax.swing.JLabel();
        lblCurrentPowerValue = new javax.swing.JLabel();
        lblDuration_Title = new javax.swing.JLabel();
        lblPower_Title = new javax.swing.JLabel();
        lblP5start_Title = new javax.swing.JLabel();
        lblP5max_Title = new javax.swing.JLabel();
        lblP5last_Title = new javax.swing.JLabel();
        lblDuration_1 = new javax.swing.JLabel();
        lblPower_1 = new javax.swing.JLabel();
        lblP5_start_1 = new javax.swing.JLabel();
        lblP5_max_1 = new javax.swing.JLabel();
        lblP5_last_1 = new javax.swing.JLabel();
        lblDuration_2 = new javax.swing.JLabel();
        lblPower_2 = new javax.swing.JLabel();
        lblP5_start_2 = new javax.swing.JLabel();
        lblP5_max_2 = new javax.swing.JLabel();
        lblP5_last_2 = new javax.swing.JLabel();
        lblDuration_3 = new javax.swing.JLabel();
        lblPower_3 = new javax.swing.JLabel();
        lblP5_start_3 = new javax.swing.JLabel();
        lblP5_max_3 = new javax.swing.JLabel();
        lblP5_last_3 = new javax.swing.JLabel();
        lblDuration_4 = new javax.swing.JLabel();
        lblPower_4 = new javax.swing.JLabel();
        lblP5_start_4 = new javax.swing.JLabel();
        lblP5_max_4 = new javax.swing.JLabel();
        lblP5_last_4 = new javax.swing.JLabel();
        lblDuration_5 = new javax.swing.JLabel();
        lblPower_5 = new javax.swing.JLabel();
        lblP5_start_5 = new javax.swing.JLabel();
        lblP5_max_5 = new javax.swing.JLabel();
        lblP5_last_5 = new javax.swing.JLabel();
        lblDuration_6 = new javax.swing.JLabel();
        lblPower_6 = new javax.swing.JLabel();
        lblP5_start_6 = new javax.swing.JLabel();
        lblP5_max_6 = new javax.swing.JLabel();
        lblP5_last_6 = new javax.swing.JLabel();
        lblDuration_7 = new javax.swing.JLabel();
        lblPower_7 = new javax.swing.JLabel();
        lblP5_start_7 = new javax.swing.JLabel();
        lblP5_max_7 = new javax.swing.JLabel();
        lblP5_last_7 = new javax.swing.JLabel();
        lblDuration_8 = new javax.swing.JLabel();
        lblPower_8 = new javax.swing.JLabel();
        lblP5_start_8 = new javax.swing.JLabel();
        lblP5_max_8 = new javax.swing.JLabel();
        lblP5_last_8 = new javax.swing.JLabel();
        lblDuration_9 = new javax.swing.JLabel();
        lblPower_9 = new javax.swing.JLabel();
        lblP5_start_9 = new javax.swing.JLabel();
        lblP5_max_9 = new javax.swing.JLabel();
        lblP5_last_9 = new javax.swing.JLabel();
        lblDuration_10 = new javax.swing.JLabel();
        lblPower_10 = new javax.swing.JLabel();
        lblP5_start_10 = new javax.swing.JLabel();
        lblP5_max_10 = new javax.swing.JLabel();
        lblP5_last_10 = new javax.swing.JLabel();
        lblDuration_11 = new javax.swing.JLabel();
        lblPower_11 = new javax.swing.JLabel();
        lblP5_start_11 = new javax.swing.JLabel();
        lblP5_max_11 = new javax.swing.JLabel();
        lblP5_last_11 = new javax.swing.JLabel();
        lblDuration_12 = new javax.swing.JLabel();
        lblPower_12 = new javax.swing.JLabel();
        lblP5_start_12 = new javax.swing.JLabel();
        lblP5_max_12 = new javax.swing.JLabel();
        lblP5_last_12 = new javax.swing.JLabel();
        lblDuration_13 = new javax.swing.JLabel();
        lblPower_13 = new javax.swing.JLabel();
        lblP5_start_13 = new javax.swing.JLabel();
        lblP5_max_13 = new javax.swing.JLabel();
        lblP5_last_13 = new javax.swing.JLabel();
        lblDuration_14 = new javax.swing.JLabel();
        lblPower_14 = new javax.swing.JLabel();
        lblP5_start_14 = new javax.swing.JLabel();
        lblP5_max_14 = new javax.swing.JLabel();
        lblP5_last_14 = new javax.swing.JLabel();
        lblDuration_15 = new javax.swing.JLabel();
        lblPower_15 = new javax.swing.JLabel();
        lblP5_start_15 = new javax.swing.JLabel();
        lblP5_max_15 = new javax.swing.JLabel();
        lblP5_last_15 = new javax.swing.JLabel();
        lblDuration_16 = new javax.swing.JLabel();
        lblPower_16 = new javax.swing.JLabel();
        lblP5_start_16 = new javax.swing.JLabel();
        lblP5_max_16 = new javax.swing.JLabel();
        lblP5_last_16 = new javax.swing.JLabel();
        lblDuration_17 = new javax.swing.JLabel();
        lblPower_17 = new javax.swing.JLabel();
        lblP5_start_17 = new javax.swing.JLabel();
        lblP5_max_17 = new javax.swing.JLabel();
        lblP5_last_17 = new javax.swing.JLabel();
        radDev1 = new javax.swing.JRadioButton();
        radDev2 = new javax.swing.JRadioButton();
        radDev3 = new javax.swing.JRadioButton();
        radDev4 = new javax.swing.JRadioButton();
        radDev5 = new javax.swing.JRadioButton();
        radDev6 = new javax.swing.JRadioButton();
        radDev7 = new javax.swing.JRadioButton();
        radDev8 = new javax.swing.JRadioButton();
        lblInductorNumber = new javax.swing.JLabel();
        btnNextStep = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("8.1 Обезгаживание");
        setMaximumSize(new java.awt.Dimension(450, 1020));
        setMinimumSize(new java.awt.Dimension(450, 1020));
        setModalExclusionType(null);
        setModalityType(null);
        setResizable(false);
        getContentPane().setLayout(null);

        lblDevTittle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDevTittle.setText("Прибор:");
        getContentPane().add(lblDevTittle);
        lblDevTittle.setBounds(10, 30, 450, 30);

        lblGetterTitle.setText("Тип геттера:");
        getContentPane().add(lblGetterTitle);
        lblGetterTitle.setBounds(10, 100, 110, 30);

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
        lblGetterValue.setBounds(130, 100, 60, 30);

        lblIndTitle.setText("Тип индуктора:");
        getContentPane().add(lblIndTitle);
        lblIndTitle.setBounds(220, 100, 110, 30);

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
        radInd1.setBounds(340, 100, 40, 30);

        btnGrpInductor.add(radInd2);
        radInd2.setText("2");
        radInd2.setEnabled(false);
        radInd2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radInd2MouseClicked(evt);
            }
        });
        getContentPane().add(radInd2);
        radInd2.setBounds(400, 100, 40, 30);

        btnStartLevel.setText("Запуск");
        btnStartLevel.setEnabled(false);
        btnStartLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartLevelActionPerformed(evt);
            }
        });
        getContentPane().add(btnStartLevel);
        btnStartLevel.setBounds(10, 140, 430, 60);

        lblTimeTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimeTitle.setText("Время:");
        getContentPane().add(lblTimeTitle);
        lblTimeTitle.setBounds(10, 200, 240, 30);

        lblTimerValue.setFont(new java.awt.Font("Cantarell", 0, 80)); // NOI18N
        lblTimerValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimerValue.setText("-");
        lblTimerValue.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblTimerValue);
        lblTimerValue.setBounds(10, 230, 240, 110);

        lblCurrentPowerTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentPowerTitle.setText("Мощность:");
        getContentPane().add(lblCurrentPowerTitle);
        lblCurrentPowerTitle.setBounds(270, 200, 170, 30);

        lblCurrentPowerValue.setFont(new java.awt.Font("Cantarell", 0, 80)); // NOI18N
        lblCurrentPowerValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurrentPowerValue.setText("-");
        lblCurrentPowerValue.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblCurrentPowerValue.setOpaque(true);
        getContentPane().add(lblCurrentPowerValue);
        lblCurrentPowerValue.setBounds(270, 230, 170, 110);

        lblDuration_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title.setText("<html>Длительность</html>");
        lblDuration_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title);
        lblDuration_Title.setBounds(0, 430, 90, 30);

        lblPower_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title.setText("<html>Мощность</html>");
        lblPower_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title);
        lblPower_Title.setBounds(90, 430, 90, 30);

        lblP5start_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title.setText("<html>P<sub>5, начальное</sub></html>:");
        lblP5start_Title.setToolTipText("");
        lblP5start_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title);
        lblP5start_Title.setBounds(180, 430, 90, 30);

        lblP5max_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title.setText("<html>P<sub>5, макс.</sub></html>:");
        lblP5max_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title);
        lblP5max_Title.setBounds(270, 430, 90, 30);

        lblP5last_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title.setText("<html>P<sub>5, конечное</sub></html>:");
        lblP5last_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title);
        lblP5last_Title.setBounds(360, 430, 90, 30);

        lblDuration_1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_1.setText("3'");
        lblDuration_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_1.setOpaque(true);
        getContentPane().add(lblDuration_1);
        lblDuration_1.setBounds(0, 460, 90, 30);

        lblPower_1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_1.setText("10");
        lblPower_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_1.setOpaque(true);
        getContentPane().add(lblPower_1);
        lblPower_1.setBounds(90, 460, 90, 30);

        lblP5_start_1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_1.setText("-");
        lblP5_start_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_1.setOpaque(true);
        getContentPane().add(lblP5_start_1);
        lblP5_start_1.setBounds(180, 460, 90, 30);

        lblP5_max_1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_1.setText("-");
        lblP5_max_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_1.setOpaque(true);
        getContentPane().add(lblP5_max_1);
        lblP5_max_1.setBounds(270, 460, 90, 30);

        lblP5_last_1.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_1.setText("-");
        lblP5_last_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_1.setOpaque(true);
        getContentPane().add(lblP5_last_1);
        lblP5_last_1.setBounds(360, 460, 90, 30);

        lblDuration_2.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_2.setText("3'");
        lblDuration_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_2.setOpaque(true);
        getContentPane().add(lblDuration_2);
        lblDuration_2.setBounds(0, 490, 90, 30);

        lblPower_2.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_2.setText("15");
        lblPower_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_2.setOpaque(true);
        getContentPane().add(lblPower_2);
        lblPower_2.setBounds(90, 490, 90, 30);

        lblP5_start_2.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_2.setText("-");
        lblP5_start_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_2.setOpaque(true);
        getContentPane().add(lblP5_start_2);
        lblP5_start_2.setBounds(180, 490, 90, 30);

        lblP5_max_2.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_2.setText("-");
        lblP5_max_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_2.setOpaque(true);
        getContentPane().add(lblP5_max_2);
        lblP5_max_2.setBounds(270, 490, 90, 30);

        lblP5_last_2.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_2.setText("-");
        lblP5_last_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_2.setOpaque(true);
        getContentPane().add(lblP5_last_2);
        lblP5_last_2.setBounds(360, 490, 90, 30);

        lblDuration_3.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_3.setText("5'");
        lblDuration_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_3.setOpaque(true);
        getContentPane().add(lblDuration_3);
        lblDuration_3.setBounds(0, 520, 90, 30);

        lblPower_3.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_3.setText("20");
        lblPower_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_3.setOpaque(true);
        getContentPane().add(lblPower_3);
        lblPower_3.setBounds(90, 520, 90, 30);

        lblP5_start_3.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_3.setText("-");
        lblP5_start_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_3.setOpaque(true);
        getContentPane().add(lblP5_start_3);
        lblP5_start_3.setBounds(180, 520, 90, 30);

        lblP5_max_3.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_3.setText("-");
        lblP5_max_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_3.setOpaque(true);
        getContentPane().add(lblP5_max_3);
        lblP5_max_3.setBounds(270, 520, 90, 30);

        lblP5_last_3.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_3.setText("-");
        lblP5_last_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_3.setOpaque(true);
        getContentPane().add(lblP5_last_3);
        lblP5_last_3.setBounds(360, 520, 90, 30);

        lblDuration_4.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_4.setText("5'");
        lblDuration_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_4.setOpaque(true);
        getContentPane().add(lblDuration_4);
        lblDuration_4.setBounds(0, 550, 90, 30);

        lblPower_4.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_4.setText("25");
        lblPower_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_4.setOpaque(true);
        getContentPane().add(lblPower_4);
        lblPower_4.setBounds(90, 550, 90, 30);

        lblP5_start_4.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_4.setText("-");
        lblP5_start_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_4.setOpaque(true);
        getContentPane().add(lblP5_start_4);
        lblP5_start_4.setBounds(180, 550, 90, 30);

        lblP5_max_4.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_4.setText("-");
        lblP5_max_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_4.setOpaque(true);
        getContentPane().add(lblP5_max_4);
        lblP5_max_4.setBounds(270, 550, 90, 30);

        lblP5_last_4.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_4.setText("-");
        lblP5_last_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_4.setOpaque(true);
        getContentPane().add(lblP5_last_4);
        lblP5_last_4.setBounds(360, 550, 90, 30);

        lblDuration_5.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_5.setText("5'");
        lblDuration_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_5.setOpaque(true);
        getContentPane().add(lblDuration_5);
        lblDuration_5.setBounds(0, 580, 90, 30);

        lblPower_5.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_5.setText("30");
        lblPower_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_5.setOpaque(true);
        getContentPane().add(lblPower_5);
        lblPower_5.setBounds(90, 580, 90, 30);

        lblP5_start_5.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_5.setText("-");
        lblP5_start_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_5.setOpaque(true);
        getContentPane().add(lblP5_start_5);
        lblP5_start_5.setBounds(180, 580, 90, 30);

        lblP5_max_5.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_5.setText("-");
        lblP5_max_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_5.setOpaque(true);
        getContentPane().add(lblP5_max_5);
        lblP5_max_5.setBounds(270, 580, 90, 30);

        lblP5_last_5.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_5.setText("-");
        lblP5_last_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_5.setOpaque(true);
        getContentPane().add(lblP5_last_5);
        lblP5_last_5.setBounds(360, 580, 90, 30);

        lblDuration_6.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_6.setText("10'");
        lblDuration_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_6.setOpaque(true);
        getContentPane().add(lblDuration_6);
        lblDuration_6.setBounds(0, 610, 90, 30);

        lblPower_6.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_6.setText("35");
        lblPower_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_6.setOpaque(true);
        getContentPane().add(lblPower_6);
        lblPower_6.setBounds(90, 610, 90, 30);

        lblP5_start_6.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_6.setText("-");
        lblP5_start_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_6.setOpaque(true);
        getContentPane().add(lblP5_start_6);
        lblP5_start_6.setBounds(180, 610, 90, 30);

        lblP5_max_6.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_6.setText("-");
        lblP5_max_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_6.setOpaque(true);
        getContentPane().add(lblP5_max_6);
        lblP5_max_6.setBounds(270, 610, 90, 30);

        lblP5_last_6.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_6.setText("-");
        lblP5_last_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_6.setOpaque(true);
        getContentPane().add(lblP5_last_6);
        lblP5_last_6.setBounds(360, 610, 90, 30);

        lblDuration_7.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_7.setText("5'");
        lblDuration_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_7.setOpaque(true);
        getContentPane().add(lblDuration_7);
        lblDuration_7.setBounds(0, 640, 90, 30);

        lblPower_7.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_7.setText("30");
        lblPower_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_7.setOpaque(true);
        getContentPane().add(lblPower_7);
        lblPower_7.setBounds(90, 640, 90, 30);

        lblP5_start_7.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_7.setText("-");
        lblP5_start_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_7.setOpaque(true);
        getContentPane().add(lblP5_start_7);
        lblP5_start_7.setBounds(180, 640, 90, 30);

        lblP5_max_7.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_7.setText("-");
        lblP5_max_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_7.setOpaque(true);
        getContentPane().add(lblP5_max_7);
        lblP5_max_7.setBounds(270, 640, 90, 30);

        lblP5_last_7.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_7.setText("-");
        lblP5_last_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_7.setOpaque(true);
        getContentPane().add(lblP5_last_7);
        lblP5_last_7.setBounds(360, 640, 90, 30);

        lblDuration_8.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_8.setText("5'");
        lblDuration_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_8.setOpaque(true);
        getContentPane().add(lblDuration_8);
        lblDuration_8.setBounds(0, 670, 90, 30);

        lblPower_8.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_8.setText("25");
        lblPower_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_8.setOpaque(true);
        getContentPane().add(lblPower_8);
        lblPower_8.setBounds(90, 670, 90, 30);

        lblP5_start_8.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_8.setText("-");
        lblP5_start_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_8.setOpaque(true);
        getContentPane().add(lblP5_start_8);
        lblP5_start_8.setBounds(180, 670, 90, 30);

        lblP5_max_8.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_8.setText("-");
        lblP5_max_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_8.setOpaque(true);
        getContentPane().add(lblP5_max_8);
        lblP5_max_8.setBounds(270, 670, 90, 30);

        lblP5_last_8.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_8.setText("-");
        lblP5_last_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_8.setOpaque(true);
        getContentPane().add(lblP5_last_8);
        lblP5_last_8.setBounds(360, 670, 90, 30);

        lblDuration_9.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_9.setText("5'");
        lblDuration_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_9.setOpaque(true);
        getContentPane().add(lblDuration_9);
        lblDuration_9.setBounds(0, 700, 90, 30);

        lblPower_9.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_9.setText("20");
        lblPower_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_9.setOpaque(true);
        getContentPane().add(lblPower_9);
        lblPower_9.setBounds(90, 700, 90, 30);

        lblP5_start_9.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_9.setText("-");
        lblP5_start_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_9.setOpaque(true);
        getContentPane().add(lblP5_start_9);
        lblP5_start_9.setBounds(180, 700, 90, 30);

        lblP5_max_9.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_9.setText("-");
        lblP5_max_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_9.setOpaque(true);
        getContentPane().add(lblP5_max_9);
        lblP5_max_9.setBounds(270, 700, 90, 30);

        lblP5_last_9.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_9.setText("-");
        lblP5_last_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_9.setOpaque(true);
        getContentPane().add(lblP5_last_9);
        lblP5_last_9.setBounds(360, 700, 90, 30);

        lblDuration_10.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_10.setText("3'");
        lblDuration_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_10.setOpaque(true);
        getContentPane().add(lblDuration_10);
        lblDuration_10.setBounds(0, 730, 90, 30);

        lblPower_10.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_10.setText("15");
        lblPower_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_10.setOpaque(true);
        getContentPane().add(lblPower_10);
        lblPower_10.setBounds(90, 730, 90, 30);

        lblP5_start_10.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_10.setText("-");
        lblP5_start_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_10.setOpaque(true);
        getContentPane().add(lblP5_start_10);
        lblP5_start_10.setBounds(180, 730, 90, 30);

        lblP5_max_10.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_10.setText("-");
        lblP5_max_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_10.setOpaque(true);
        getContentPane().add(lblP5_max_10);
        lblP5_max_10.setBounds(270, 730, 90, 30);

        lblP5_last_10.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_10.setText("-");
        lblP5_last_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_10.setOpaque(true);
        getContentPane().add(lblP5_last_10);
        lblP5_last_10.setBounds(360, 730, 90, 30);

        lblDuration_11.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_11.setText("3'");
        lblDuration_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_11.setOpaque(true);
        getContentPane().add(lblDuration_11);
        lblDuration_11.setBounds(0, 760, 90, 30);

        lblPower_11.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_11.setText("10");
        lblPower_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_11.setOpaque(true);
        getContentPane().add(lblPower_11);
        lblPower_11.setBounds(90, 760, 90, 30);

        lblP5_start_11.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_11.setText("-");
        lblP5_start_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_11.setOpaque(true);
        getContentPane().add(lblP5_start_11);
        lblP5_start_11.setBounds(180, 760, 90, 30);

        lblP5_max_11.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_11.setText("-");
        lblP5_max_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_11.setOpaque(true);
        getContentPane().add(lblP5_max_11);
        lblP5_max_11.setBounds(270, 760, 90, 30);

        lblP5_last_11.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_11.setText("-");
        lblP5_last_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_11.setOpaque(true);
        getContentPane().add(lblP5_last_11);
        lblP5_last_11.setBounds(360, 760, 90, 30);

        lblDuration_12.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_12.setText("-");
        lblDuration_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_12.setOpaque(true);
        getContentPane().add(lblDuration_12);
        lblDuration_12.setBounds(0, 790, 90, 30);

        lblPower_12.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_12.setText("-");
        lblPower_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_12.setOpaque(true);
        getContentPane().add(lblPower_12);
        lblPower_12.setBounds(90, 790, 90, 30);

        lblP5_start_12.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_12.setText("-");
        lblP5_start_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_12.setOpaque(true);
        getContentPane().add(lblP5_start_12);
        lblP5_start_12.setBounds(180, 790, 90, 30);

        lblP5_max_12.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_12.setText("-");
        lblP5_max_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_12.setOpaque(true);
        getContentPane().add(lblP5_max_12);
        lblP5_max_12.setBounds(270, 790, 90, 30);

        lblP5_last_12.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_12.setText("-");
        lblP5_last_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_12.setOpaque(true);
        getContentPane().add(lblP5_last_12);
        lblP5_last_12.setBounds(360, 790, 90, 30);

        lblDuration_13.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_13.setText("-");
        lblDuration_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_13.setOpaque(true);
        getContentPane().add(lblDuration_13);
        lblDuration_13.setBounds(0, 820, 90, 30);

        lblPower_13.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_13.setText("-");
        lblPower_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_13.setOpaque(true);
        getContentPane().add(lblPower_13);
        lblPower_13.setBounds(90, 820, 90, 30);

        lblP5_start_13.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_13.setText("-");
        lblP5_start_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_13.setOpaque(true);
        getContentPane().add(lblP5_start_13);
        lblP5_start_13.setBounds(180, 820, 90, 30);

        lblP5_max_13.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_13.setText("-");
        lblP5_max_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_13.setOpaque(true);
        getContentPane().add(lblP5_max_13);
        lblP5_max_13.setBounds(270, 820, 90, 30);

        lblP5_last_13.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_13.setText("-");
        lblP5_last_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_13.setOpaque(true);
        getContentPane().add(lblP5_last_13);
        lblP5_last_13.setBounds(360, 820, 90, 30);

        lblDuration_14.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_14.setText("-");
        lblDuration_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_14.setOpaque(true);
        getContentPane().add(lblDuration_14);
        lblDuration_14.setBounds(0, 850, 90, 30);

        lblPower_14.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_14.setText("-");
        lblPower_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_14.setOpaque(true);
        getContentPane().add(lblPower_14);
        lblPower_14.setBounds(90, 850, 90, 30);

        lblP5_start_14.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_14.setText("-");
        lblP5_start_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_14.setOpaque(true);
        getContentPane().add(lblP5_start_14);
        lblP5_start_14.setBounds(180, 850, 90, 30);

        lblP5_max_14.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_14.setText("-");
        lblP5_max_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_14.setOpaque(true);
        getContentPane().add(lblP5_max_14);
        lblP5_max_14.setBounds(270, 850, 90, 30);

        lblP5_last_14.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_14.setText("-");
        lblP5_last_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_14.setOpaque(true);
        getContentPane().add(lblP5_last_14);
        lblP5_last_14.setBounds(360, 850, 90, 30);

        lblDuration_15.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_15.setText("-");
        lblDuration_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_15.setOpaque(true);
        getContentPane().add(lblDuration_15);
        lblDuration_15.setBounds(0, 880, 90, 30);

        lblPower_15.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_15.setText("-");
        lblPower_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_15.setOpaque(true);
        getContentPane().add(lblPower_15);
        lblPower_15.setBounds(90, 880, 90, 30);

        lblP5_start_15.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_15.setText("-");
        lblP5_start_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_15.setOpaque(true);
        getContentPane().add(lblP5_start_15);
        lblP5_start_15.setBounds(180, 880, 90, 30);

        lblP5_max_15.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_15.setText("-");
        lblP5_max_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_15.setOpaque(true);
        getContentPane().add(lblP5_max_15);
        lblP5_max_15.setBounds(270, 880, 90, 30);

        lblP5_last_15.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_15.setText("-");
        lblP5_last_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_15.setOpaque(true);
        getContentPane().add(lblP5_last_15);
        lblP5_last_15.setBounds(360, 880, 90, 30);

        lblDuration_16.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_16.setText("-");
        lblDuration_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_16.setOpaque(true);
        getContentPane().add(lblDuration_16);
        lblDuration_16.setBounds(0, 910, 90, 30);

        lblPower_16.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_16.setText("-");
        lblPower_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_16.setOpaque(true);
        getContentPane().add(lblPower_16);
        lblPower_16.setBounds(90, 910, 90, 30);

        lblP5_start_16.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_16.setText("-");
        lblP5_start_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_16.setOpaque(true);
        getContentPane().add(lblP5_start_16);
        lblP5_start_16.setBounds(180, 910, 90, 30);

        lblP5_max_16.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_16.setText("-");
        lblP5_max_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_16.setOpaque(true);
        getContentPane().add(lblP5_max_16);
        lblP5_max_16.setBounds(270, 910, 90, 30);

        lblP5_last_16.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_16.setText("-");
        lblP5_last_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_16.setOpaque(true);
        getContentPane().add(lblP5_last_16);
        lblP5_last_16.setBounds(360, 910, 90, 30);

        lblDuration_17.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblDuration_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_17.setText("-");
        lblDuration_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_17.setOpaque(true);
        getContentPane().add(lblDuration_17);
        lblDuration_17.setBounds(0, 940, 90, 30);

        lblPower_17.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblPower_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_17.setText("-");
        lblPower_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_17.setOpaque(true);
        getContentPane().add(lblPower_17);
        lblPower_17.setBounds(90, 940, 90, 30);

        lblP5_start_17.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_start_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_17.setText("-");
        lblP5_start_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_17.setOpaque(true);
        getContentPane().add(lblP5_start_17);
        lblP5_start_17.setBounds(180, 940, 90, 30);

        lblP5_max_17.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_max_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_17.setText("-");
        lblP5_max_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_17.setOpaque(true);
        getContentPane().add(lblP5_max_17);
        lblP5_max_17.setBounds(270, 940, 90, 30);

        lblP5_last_17.setFont(new java.awt.Font("Cantarell", 0, 15)); // NOI18N
        lblP5_last_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_17.setText("-");
        lblP5_last_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_17.setOpaque(true);
        getContentPane().add(lblP5_last_17);
        lblP5_last_17.setBounds(360, 940, 90, 30);

        btnGroupDevice.add(radDev1);
        radDev1.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev1.setText("1");
        radDev1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev1ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev1);
        radDev1.setBounds(30, 60, 50, 40);

        btnGroupDevice.add(radDev2);
        radDev2.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev2.setText("2");
        radDev2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev2ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev2);
        radDev2.setBounds(80, 60, 50, 40);

        btnGroupDevice.add(radDev3);
        radDev3.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev3.setText("3");
        radDev3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev3ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev3);
        radDev3.setBounds(130, 60, 50, 40);

        btnGroupDevice.add(radDev4);
        radDev4.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev4.setText("4");
        radDev4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev4ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev4);
        radDev4.setBounds(180, 60, 50, 40);

        btnGroupDevice.add(radDev5);
        radDev5.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev5.setText("5");
        radDev5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev5ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev5);
        radDev5.setBounds(240, 60, 50, 40);

        btnGroupDevice.add(radDev6);
        radDev6.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev6.setText("6");
        radDev6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev6ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev6);
        radDev6.setBounds(290, 60, 50, 40);

        btnGroupDevice.add(radDev7);
        radDev7.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev7.setText("7");
        radDev7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev7ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev7);
        radDev7.setBounds(340, 60, 50, 40);

        btnGroupDevice.add(radDev8);
        radDev8.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        radDev8.setText("8");
        radDev8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radDev8ActionPerformed(evt);
            }
        });
        getContentPane().add(radDev8);
        radDev8.setBounds(390, 60, 50, 40);

        lblInductorNumber.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblInductorNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblInductorNumber.setText("ИНДУКТОР Х");
        getContentPane().add(lblInductorNumber);
        lblInductorNumber.setBounds(0, 0, 470, 30);

        btnNextStep.setText("Далее");
        btnNextStep.setEnabled(false);
        btnNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextStepActionPerformed(evt);
            }
        });
        getContentPane().add(btnNextStep);
        btnNextStep.setBounds(110, 370, 250, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartLevelActionPerformed
        TurnOffRads();

        btnStartLevel.setEnabled( false);
        m_bReadyForNextStep = false;
        
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
        
        m_nPhase = 0;
        
        //Стартуем прооцесс обезгаживания для конкретного девайса
        int nInductorType = -1;
        if( radInd1.isSelected()) nInductorType = HVV_AdminConstants.INDUCTOR_TYPE1;
        if( radInd2.isSelected()) nInductorType = HVV_AdminConstants.INDUCTOR_TYPE2;
        m_pGetActProgram = new GettersActivationProgram( theApp.GetLocalDate(), nInductorType);
        
        if( "1".equals( lblGetterValue.getText()))
            m_pGetActProgram.SetGetter( HVV_AdminConstants.GETTER1);
        else
            m_pGetActProgram.SetGetter(HVV_AdminConstants.GETTER2);
                
        GettersActivationProgramStep item = ( GettersActivationProgramStep) m_lstProgram.get( m_nPhase);
        item.SetP5_start( theApp.GetDoubleFromPoller( "005.01"));
        
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
        
        //запустим таймер шага
        if( theApp.GetSettings().GetDebugShortenProgTimes())
            m_nTimeCounter = item.GetDuration() * 2;
        else
            m_nTimeCounter = item.GetDuration() * 60 + 4;
        lblTimerValue.setText( String.format( "%02d:%02d", m_nTimeCounter / 60, m_nTimeCounter % 60));
        m_TimeTimer.start();
        
        
        
        
        
        /*
            lblDuration_1.setFont( m_fontBold);
        lblPower_1.setFont( m_fontBold);
        lblP5_start_1.setFont( m_fontBold);
        lblP5_max_1.setFont( m_fontBold);
        lblP5_last_1.setFont( m_fontBold);
            */
    }//GEN-LAST:event_btnStartLevelActionPerformed

    private void lblGetterValueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGetterValueMouseClicked
        if( m_nInProgress != -1) return;
        
        if( "1".equals( lblGetterValue.getText()))
            lblGetterValue.setText( "2");
        else
            lblGetterValue.setText( "1");
        
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_lblGetterValueMouseClicked

    private void radInd2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radInd2MouseClicked
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radInd2MouseClicked

    private void radInd1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radInd1MouseClicked
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radInd1MouseClicked

    private void radDev1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev1ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE1);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev1ActionPerformed

    private void radDev2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev2ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE2);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev2ActionPerformed

    private void radDev3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev3ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE3);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev3ActionPerformed

    private void radDev4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev4ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE4);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev4ActionPerformed

    private void radDev5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev5ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE5);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev5ActionPerformed

    private void radDev6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev6ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE6);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev6ActionPerformed

    private void radDev7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev7ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE7);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev7ActionPerformed

    private void radDev8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radDev8ActionPerformed
        m_pParent.m_dlgInd1.setRadButtonsState( false);
        m_pParent.m_dlgInd2.setRadButtonsState( false);
        int nGetterInDevice = ( int) theApp.m_mapDeviceGetter.get( HVV_AdminConstants.DEVICE8);
        if( nGetterInDevice == HVV_AdminConstants.GETTER1)
            lblGetterValue.setText( "1");
        else
            lblGetterValue.setText( "2");
        DefineProgram();
        LoadProgram();
        RefreshProgram();
    }//GEN-LAST:event_radDev8ActionPerformed

    private void btnNextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextStepActionPerformed
        btnNextStep.setEnabled( false);
        m_bReadyForNextStep = true;
        
        /*
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
        */
    }//GEN-LAST:event_btnNextStepActionPerformed

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
                    
                    boolean bAlready = theApp.m_mapDegassing.containsKey(i);
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
    
    public void ColorPressureLabel( Double dbl, JLabel lbl) {
        if( dbl.isNaN()) {
            lbl.setBackground( null);
            return;
        }
        /*
        int nExp = ( int) Math.log10( dbl);
        if( nExp < 0) nExp--;
        
        float dblMant = ( float) ( dbl / Math.pow( 10, nExp));
        if( nExp >= -6 && nExp <= -4) {
            int nRed = -1, nGreen = -1, nBlue = -1;
            switch( nExp) {
                case -4:    //blue gradient
                    nRed = ( int) ( 1.9 * dblMant);
                    nGreen = ( int) ( 1.9 * dblMant);
                    nBlue = 175 + ( int) ( 4.5 * dblMant);
                break;
                case -5:    //green gradinet
                    nRed = ( int) ( 1.9 * dblMant);
                    nGreen = 175 + ( int) ( 4.5 * dblMant);
                    nBlue = ( int) ( 1.9 * dblMant);
                break;
                case -6:    //red gradient
                    nRed = 175 + ( int) ( 4.5 * dblMant);
                    nGreen = ( int) ( 1.9 * dblMant);
                    nBlue = ( int) ( 1.9 * dblMant);
                break;
            }
            
            if( nRed != -1) {
                lbl.setBackground( new Color( nRed, nGreen, nBlue));
            }
            else
                lbl.setBackground( null);
        }*/
    }
    
    public void RefreshProgram() {
        JLabel [] arrDurations = { lblDuration_1,  lblDuration_2,  lblDuration_3,  lblDuration_4,
                                   lblDuration_5,  lblDuration_6,  lblDuration_7,  lblDuration_8,
                                   lblDuration_9,  lblDuration_10, lblDuration_11, lblDuration_12,
                                   lblDuration_13, lblDuration_14, lblDuration_15, lblDuration_16,
                                   lblDuration_17 };
        
        JLabel [] arrPowers = {    lblPower_1,  lblPower_2,  lblPower_3,  lblPower_4,
                                   lblPower_5,  lblPower_6,  lblPower_7,  lblPower_8,
                                   lblPower_9,  lblPower_10, lblPower_11, lblPower_12,
                                   lblPower_13, lblPower_14, lblPower_15, lblPower_16,
                                   lblPower_17 };
        
        JLabel [] arrP5start = {   lblP5_start_1,  lblP5_start_2,  lblP5_start_3,  lblP5_start_4,
                                   lblP5_start_5,  lblP5_start_6,  lblP5_start_7,  lblP5_start_8,
                                   lblP5_start_9,  lblP5_start_10, lblP5_start_11, lblP5_start_12,
                                   lblP5_start_13, lblP5_start_14, lblP5_start_15, lblP5_start_16,
                                   lblP5_start_17 };
        
        JLabel [] arrP5max = {     lblP5_max_1,  lblP5_max_2,  lblP5_max_3,  lblP5_max_4,
                                   lblP5_max_5,  lblP5_max_6,  lblP5_max_7,  lblP5_max_8,
                                   lblP5_max_9,  lblP5_max_10, lblP5_max_11, lblP5_max_12,
                                   lblP5_max_13, lblP5_max_14, lblP5_max_15, lblP5_max_16,
                                   lblP5_max_17 };
        
        JLabel [] arrP5last = {    lblP5_last_1,  lblP5_last_2,  lblP5_last_3,  lblP5_last_4,
                                   lblP5_last_5,  lblP5_last_6,  lblP5_last_7,  lblP5_last_8,
                                   lblP5_last_9,  lblP5_last_10, lblP5_last_11, lblP5_last_12,
                                   lblP5_last_13, lblP5_last_14, lblP5_last_15, lblP5_last_16,
                                   lblP5_last_17 };
        
        
        for( int i=0; i<17; i++) {
            if( i < m_lstProgram.size()) {
                GettersActivationProgramStep step = ( GettersActivationProgramStep) m_lstProgram.get(i);
                
                //DURATION
                arrDurations[i].setFont( m_nPhase == i ? m_fontBold : m_fontUsual);
                arrDurations[i].setText( "" + step.GetDuration());
                
                //POWER
                arrPowers[i].setFont( m_nPhase == i ? m_fontBold : m_fontUsual);
                arrPowers[i].setText( "" + step.GetPower());
                
                //P5_START
                arrP5start[i].setFont( m_nPhase == i ? m_fontBold : m_fontUsual);
                if( step.GetP5_start() != 0.) {
                    arrP5start[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_start(), false) + "</html>");
                    if( !( m_nPhase == i && m_FlashTimer.isRunning() == true))
                        ColorPressureLabel( step.GetP5_start(), arrP5start[i]);
                }
                else {
                    arrP5start[i].setText( "-");
                    if( !( m_nPhase == i && m_FlashTimer.isRunning() == true))
                        ColorPressureLabel( Double.NaN, arrP5start[i]);
                }
                
                //P5_MAX
                if( m_nPhase == i) {
                    
                    if( m_nInProgress != -1) {
                        arrP5max[i].setFont( m_fontBold);
                        
                        if( btnNextStep.isEnabled()) {
                            //мы висим и ждём нажатия "Далее"
                            arrP5max[i].setText( "-");
                            if( m_FlashTimer.isRunning() == true) {
                                if( m_nPhase != i)
                                    ColorPressureLabel( Double.NaN, arrP5max[i]);
                            }
                            else
                                ColorPressureLabel( Double.NaN, arrP5max[i]);
                        }
                        else {
                            //Основной рабочий режим отслеживания P5max
                            arrP5max[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( m_rnblP5Watchdog.GetP5Max(), false) + "</html>");
                            if( m_FlashTimer.isRunning() == true) {
                                if( m_nPhase != i)
                                    ColorPressureLabel( m_rnblP5Watchdog.GetP5Max(), arrP5max[i]);
                            }
                            else
                                ColorPressureLabel( m_rnblP5Watchdog.GetP5Max(), arrP5max[i]);
                        }
                    }
                    else {
                        //этап не запущен
                        arrP5max[i].setFont( m_fontUsual);
                        
                        arrP5max[i].setText( "-");
                        if( m_FlashTimer.isRunning() == true) {
                            if( m_nPhase != i)
                                ColorPressureLabel( Double.NaN, arrP5max[i]);
                        }
                        else
                            ColorPressureLabel( Double.NaN, arrP5max[i]);
                    }
                }
                else {
                    arrP5max[i].setFont(  m_fontUsual);
                
                    if( step.GetP5_max() != 0.) {
                        arrP5max[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_max(), false) + "</html>");
                        if( m_FlashTimer.isRunning() == true) {
                            if( m_nPhase != i)
                                ColorPressureLabel( step.GetP5_max(), arrP5max[i]);
                        }
                        else
                            ColorPressureLabel( step.GetP5_max(), arrP5max[i]);
                    }
                    else {
                        arrP5max[i].setText( "-");
                        if( m_FlashTimer.isRunning() == true) {
                            if( m_nPhase != i)
                                ColorPressureLabel( Double.NaN, arrP5max[i]);
                        }
                        else
                            ColorPressureLabel( Double.NaN, arrP5max[i]);
                    }
                }
                
                
                //P5_LAST
                arrP5last[i].setFont( m_nPhase == i ? m_fontBold : m_fontUsual);
                if( step.GetP5_last() != 0.) {
                    arrP5last[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_last(), false) + "</html>");
                    if( m_FlashTimer.isRunning() == true) {
                        if( m_nPhase != i) 
                            ColorPressureLabel( step.GetP5_last(), arrP5last[i]);
                    }
                    else
                        ColorPressureLabel( step.GetP5_last(), arrP5last[i]);
                }
                else {
                    arrP5last[i].setText( "-");
                    if( m_FlashTimer.isRunning() == true) {
                        if( m_nPhase != i)
                            ColorPressureLabel( Double.NaN, arrP5last[i]);
                    }
                    else
                        ColorPressureLabel( Double.NaN, arrP5last[i]);
                }
            }
            else {
                arrDurations[i].setFont( m_fontUsual);  arrDurations[i].setText( "-");  arrDurations[i].setBackground( null);
                arrPowers[i].setFont( m_fontUsual);     arrPowers[i].setText( "-");     arrPowers[i].setBackground( null);
                arrP5start[i].setFont( m_fontUsual);    arrP5start[i].setText( "-");    arrP5start[i].setBackground( null);
                arrP5max[i].setFont( m_fontUsual);      arrP5max[i].setText( "-");      arrP5max[i].setBackground( null);
                arrP5last[i].setFont( m_fontUsual);     arrP5last[i].setText( "-");     arrP5last[i].setBackground( null);
            }
        }
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroupDevice;
    public javax.swing.ButtonGroup btnGrpInductor;
    public javax.swing.JButton btnNextStep;
    public javax.swing.JButton btnStartLevel;
    private javax.swing.JLabel lblCurrentPowerTitle;
    private javax.swing.JLabel lblCurrentPowerValue;
    private javax.swing.JLabel lblDevTittle;
    private javax.swing.JLabel lblDuration_1;
    private javax.swing.JLabel lblDuration_10;
    private javax.swing.JLabel lblDuration_11;
    private javax.swing.JLabel lblDuration_12;
    private javax.swing.JLabel lblDuration_13;
    private javax.swing.JLabel lblDuration_14;
    private javax.swing.JLabel lblDuration_15;
    private javax.swing.JLabel lblDuration_16;
    private javax.swing.JLabel lblDuration_17;
    private javax.swing.JLabel lblDuration_2;
    private javax.swing.JLabel lblDuration_3;
    private javax.swing.JLabel lblDuration_4;
    private javax.swing.JLabel lblDuration_5;
    private javax.swing.JLabel lblDuration_6;
    private javax.swing.JLabel lblDuration_7;
    private javax.swing.JLabel lblDuration_8;
    private javax.swing.JLabel lblDuration_9;
    private javax.swing.JLabel lblDuration_Title;
    private javax.swing.JLabel lblGetterTitle;
    public javax.swing.JLabel lblGetterValue;
    private javax.swing.JLabel lblIndTitle;
    private javax.swing.JLabel lblInductorNumber;
    private javax.swing.JLabel lblP5_last_1;
    private javax.swing.JLabel lblP5_last_10;
    private javax.swing.JLabel lblP5_last_11;
    private javax.swing.JLabel lblP5_last_12;
    private javax.swing.JLabel lblP5_last_13;
    private javax.swing.JLabel lblP5_last_14;
    private javax.swing.JLabel lblP5_last_15;
    private javax.swing.JLabel lblP5_last_16;
    private javax.swing.JLabel lblP5_last_17;
    private javax.swing.JLabel lblP5_last_2;
    private javax.swing.JLabel lblP5_last_3;
    private javax.swing.JLabel lblP5_last_4;
    private javax.swing.JLabel lblP5_last_5;
    private javax.swing.JLabel lblP5_last_6;
    private javax.swing.JLabel lblP5_last_7;
    private javax.swing.JLabel lblP5_last_8;
    private javax.swing.JLabel lblP5_last_9;
    private javax.swing.JLabel lblP5_max_1;
    private javax.swing.JLabel lblP5_max_10;
    private javax.swing.JLabel lblP5_max_11;
    private javax.swing.JLabel lblP5_max_12;
    private javax.swing.JLabel lblP5_max_13;
    private javax.swing.JLabel lblP5_max_14;
    private javax.swing.JLabel lblP5_max_15;
    private javax.swing.JLabel lblP5_max_16;
    private javax.swing.JLabel lblP5_max_17;
    private javax.swing.JLabel lblP5_max_2;
    private javax.swing.JLabel lblP5_max_3;
    private javax.swing.JLabel lblP5_max_4;
    private javax.swing.JLabel lblP5_max_5;
    private javax.swing.JLabel lblP5_max_6;
    private javax.swing.JLabel lblP5_max_7;
    private javax.swing.JLabel lblP5_max_8;
    private javax.swing.JLabel lblP5_max_9;
    private javax.swing.JLabel lblP5_start_1;
    private javax.swing.JLabel lblP5_start_10;
    private javax.swing.JLabel lblP5_start_11;
    private javax.swing.JLabel lblP5_start_12;
    private javax.swing.JLabel lblP5_start_13;
    private javax.swing.JLabel lblP5_start_14;
    private javax.swing.JLabel lblP5_start_15;
    private javax.swing.JLabel lblP5_start_16;
    private javax.swing.JLabel lblP5_start_17;
    private javax.swing.JLabel lblP5_start_2;
    private javax.swing.JLabel lblP5_start_3;
    private javax.swing.JLabel lblP5_start_4;
    private javax.swing.JLabel lblP5_start_5;
    private javax.swing.JLabel lblP5_start_6;
    private javax.swing.JLabel lblP5_start_7;
    private javax.swing.JLabel lblP5_start_8;
    private javax.swing.JLabel lblP5_start_9;
    private javax.swing.JLabel lblP5last_Title;
    private javax.swing.JLabel lblP5max_Title;
    private javax.swing.JLabel lblP5start_Title;
    private javax.swing.JLabel lblPower_1;
    private javax.swing.JLabel lblPower_10;
    private javax.swing.JLabel lblPower_11;
    private javax.swing.JLabel lblPower_12;
    private javax.swing.JLabel lblPower_13;
    private javax.swing.JLabel lblPower_14;
    private javax.swing.JLabel lblPower_15;
    private javax.swing.JLabel lblPower_16;
    private javax.swing.JLabel lblPower_17;
    private javax.swing.JLabel lblPower_2;
    private javax.swing.JLabel lblPower_3;
    private javax.swing.JLabel lblPower_4;
    private javax.swing.JLabel lblPower_5;
    private javax.swing.JLabel lblPower_6;
    private javax.swing.JLabel lblPower_7;
    private javax.swing.JLabel lblPower_8;
    private javax.swing.JLabel lblPower_9;
    private javax.swing.JLabel lblPower_Title;
    private javax.swing.JLabel lblTimeTitle;
    private javax.swing.JLabel lblTimerValue;
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
    // End of variables declaration//GEN-END:variables
}
