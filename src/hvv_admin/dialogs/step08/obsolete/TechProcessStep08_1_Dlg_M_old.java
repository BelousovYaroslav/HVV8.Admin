package hvv_admin.dialogs.step08.obsolete;

import hvv_admin.dialogs.step08.obsolete.TechProcessStep08_2_Dlg_3_Panel;
import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.dialogs.step08.P5PollerRunnable;
import hvv_admin.dialogs.step08.TechProcessStep08_1_Dlg;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class TechProcessStep08_1_Dlg_M_old extends javax.swing.JDialog {

    private final Font m_fontUsual;
    public Font GetUsualFont() { return m_fontUsual; }
    
    private final Font m_fontBold;
    public Font GetBoldFont() { return m_fontBold; }
    
    static Logger logger = Logger.getLogger(TechProcessStep08_1_Dlg_M_old.class);
    final private HVV_Admin theApp;
    
    TechProcessStep08_1_Dlg m_dlgInd1 = null;
    TechProcessStep08_1_Dlg m_dlgInd2 = null;
    
    public P5PollerRunnable m_p5PollerRunnable;
    public Thread m_p5PollerThread;
    
    TechProcessStep08_2_Dlg_3_Panel m_pnlDevices;
    
    
    //STOPWATCH
    int m_nStopWatchTicks;
    int m_nStopWatchStatus;
    final int STOPWATCH_STOPPED = 0;
    final int STOPWATCH_RUNNING = 1;
    
    class StopWatchRefresh implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if( m_nStopWatchStatus == STOPWATCH_RUNNING)
                m_nStopWatchTicks++;
            
            int nSec = m_nStopWatchTicks % 60;
            int nMin = ( m_nStopWatchTicks / 60) % 60;
            int nHou = ( m_nStopWatchTicks / 60 / 60) % 24;
            
            String strStopWatchValue = String.format( "%02d:%02d:%02d", nHou, nMin, nSec);
            lblStopWatch.setText( strStopWatchValue);
        }
        
    }
    
    Timer m_timerStopWatch;
    //STOPWATCH
    
    //обработчик отображения уже проведённых результатов
    class RefresherViewListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if( m_pnlDevices != null)
                m_pnlDevices.setBounds( 0, 0 - jScrollBar1.getValue(), 930, 1020 + jScrollBar1.getValue());
        }
    }
    
    RefresherViewListener m_lstnRefresh;
    public Timer m_tmrRefresh;
    
    //обработчик отложенного создания окна индукторов
    class DelayedStartOfADlgs implements Runnable {

        private final TechProcessStep08_1_Dlg_M_old m_pParent;
        private final int m_nInductor;
                
        public DelayedStartOfADlgs( TechProcessStep08_1_Dlg_M_old pParent, int nInductor) {
            m_pParent = pParent;
            m_nInductor = nInductor;
        }

        @Override
        public void run() {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
            ///TechProcessStep08_1_Dlg pChild;
            ///if( m_nInductor == HVV_AdminConstants.INDUCTOR1) {
            ///    m_pParent.m_dlgInd1 = new TechProcessStep08_1_Dlg( theApp, null/*theApp.m_pMainWnd*/, false, m_nInductor, m_pParent);
            ///    pChild = m_pParent.m_dlgInd1;
            ///}
            ///else {
            ///    m_pParent.m_dlgInd2 = new TechProcessStep08_1_Dlg( theApp, null/*theApp.m_pMainWnd*/, false, m_nInductor, m_pParent);
            ///    pChild = m_pParent.m_dlgInd2;
            ///}
            
            ///pChild.setVisible( true);
            ///int nX;
            ///if( m_nInductor == HVV_AdminConstants.INDUCTOR1)
            ///    nX = rect.width - pChild.getWidth() * 2;
            ///else
            ///    nX = rect.width - pChild.getWidth();
            ///pChild.setLocation( nX, 0);
            ///pChild.setRadButtonsState( true);
        }
        
    }
    
    /**
     * Creates new form TechProcessStep08_2_Dlg_2
     */
    public TechProcessStep08_1_Dlg_M_old( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        
        m_nStopWatchStatus = STOPWATCH_STOPPED;
        m_nStopWatchTicks = 0;
        
        theApp = app;
        initComponents();
                
        m_fontUsual = new Font( "Cantarell", Font.PLAIN, 15);
        m_fontBold = new Font( "Cantarell", Font.BOLD, 15);
        
        
        /* 
        
        OLD_WAY
        
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
        
        m_dlgInd2 = new TechProcessStep08_1_Dlg( theApp, new javax.swing.JFrame(), false, HVV_AdminConstants.INDUCTOR2, this);
        m_dlgInd2.setVisible( true);
        m_dlgInd2.setLocation( rect.width - m_dlgInd2.getWidth(), 0);
        m_dlgInd2.setRadButtonsState( true);

        
        m_dlgInd1 = new TechProcessStep08_1_Dlg( theApp, new javax.swing.JFrame(), false, HVV_AdminConstants.INDUCTOR1, this);
        m_dlgInd1.setVisible(true);
        m_dlgInd1.setLocation( rect.width - m_dlgInd1.getWidth() * 2, 0);
        m_dlgInd1.setRadButtonsState( true);
        */
        
        
        /*
        //DELAYED WAY
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
        new  Timer( 500, new DelayedStartOfADlgs( this, HVV_AdminConstants.INDUCTOR2)).start();
        new  Timer( 1000, new DelayedStartOfADlgs( this, HVV_AdminConstants.INDUCTOR1)).start();
        */
        
        //DELAYED WAY_2
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
        new  Thread( new DelayedStartOfADlgs( this, HVV_AdminConstants.INDUCTOR2)).start();
        new  Thread( new DelayedStartOfADlgs( this, HVV_AdminConstants.INDUCTOR1)).start();
        
        
        
        
        new Timer( 1500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                Timer t = ( Timer) e.getSource();
                t.stop();
                
                boolean bDone = false;
                logger.debug( "1. ищем возможные пары (1,5), (2,6), (3,7), (4,8)");
                for( int i=0; i<4; i++) {
                    logger.debug( "1. пробуем пару (" + (i+4) + ", " + i + ")");
                    
                    JRadioButton rad_ind1 = ( JRadioButton) m_dlgInd1.lstRads.get(i + 4);
                    JRadioButton rad_ind2 = ( JRadioButton) m_dlgInd2.lstRads.get(i);
                    
                    //logger.fatal( "rad" + (i + 4) + " " + rad_ind1 + " : " + rad_ind1.isEnabled());
                    //logger.fatal( "rad" + i       + " " + rad_ind2 + " : " + rad_ind2.isEnabled());
                    
                    if( rad_ind1.isEnabled() && rad_ind2.isEnabled()) {
                        
                        logger.debug( "1. Пара (" + (i+4) + ", " + i + ") хороша");
                        
                        rad_ind1.setSelected( true);
                        rad_ind2.setSelected( true);
                        
                        
                        m_dlgInd1.btnStartLevel.setEnabled( true);
                        m_dlgInd1.radInd1.setEnabled( true);
                        m_dlgInd1.radInd2.setEnabled( true);
                        
                        m_dlgInd2.btnStartLevel.setEnabled( true);
                        m_dlgInd2.radInd1.setEnabled( true);
                        m_dlgInd2.radInd2.setEnabled( true);
                        

                        int nGetter = ( int) theApp.m_mapDeviceGetter.get( i + 4);
                        if( nGetter == HVV_AdminConstants.GETTER1)
                            m_dlgInd1.lblGetterValue.setText( "1");
                        else
                            m_dlgInd1.lblGetterValue.setText( "2");
                        
                        nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                        if( nGetter == HVV_AdminConstants.GETTER1)
                            m_dlgInd2.lblGetterValue.setText( "1");
                        else
                            m_dlgInd2.lblGetterValue.setText( "2");

                        
                        m_dlgInd1.DefineProgram();
                        m_dlgInd1.LoadProgram();
                        m_dlgInd1.RefreshProgram();
                        
                        m_dlgInd2.DefineProgram();
                        m_dlgInd2.LoadProgram();
                        m_dlgInd2.RefreshProgram();

                        bDone = true;
                        break;
                    }
                }
                
                if( bDone == false) {
                    //доступных парных приборов найти не удалось
                    boolean bInd1 = false;
                    boolean bInd2 = false;
                
                    //выбираем для первого первый доступный из (5-8)
                    for( int i=4; i<8; i++) {
                        logger.debug( "(2.1) Выбираем прибор " + i + ":(5-8) для индуктора1");
                        JRadioButton rad_ind1 = ( JRadioButton) m_dlgInd1.lstRads.get(i);
                        
                        if( rad_ind1.isEnabled()) {

                            rad_ind1.setSelected( true);

                            m_dlgInd1.btnStartLevel.setEnabled( true);
                            m_dlgInd1.radInd1.setEnabled( true);
                            m_dlgInd1.radInd2.setEnabled( true);

                            int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                            if( nGetter == HVV_AdminConstants.GETTER1)
                                m_dlgInd1.lblGetterValue.setText( "1");
                            else
                                m_dlgInd1.lblGetterValue.setText( "2");

                            m_dlgInd1.DefineProgram();
                            m_dlgInd1.LoadProgram();
                            m_dlgInd1.RefreshProgram();

                            bInd1 = true;
                            break;
                        }
                    }
                    
                    //выбираем для второго первый доступный из (1-4)
                    for( int i=0; i<4; i++) {
                        logger.debug( "(2.2) Выбираем прибор " + i + ":(1-4) для индуктора2");
                        JRadioButton rad_ind2 = ( JRadioButton) m_dlgInd2.lstRads.get(i);
                        
                        if( rad_ind2.isEnabled()) {

                            rad_ind2.setSelected( true);

                            m_dlgInd2.btnStartLevel.setEnabled( true);
                            m_dlgInd2.radInd1.setEnabled( true);
                            m_dlgInd2.radInd2.setEnabled( true);

                            int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                            if( nGetter == HVV_AdminConstants.GETTER1)
                                m_dlgInd2.lblGetterValue.setText( "1");
                            else
                                m_dlgInd2.lblGetterValue.setText( "2");

                            m_dlgInd2.DefineProgram();
                            m_dlgInd2.LoadProgram();
                            m_dlgInd2.RefreshProgram();

                            bInd2 = true;
                            break;
                        }
                    }
                 
                    m_dlgInd1.setRadButtonsState( false);
                    m_dlgInd2.setRadButtonsState( false);
                            
                    if( bInd1 == false) {
                        for( int i=0; i<8; i++) {
                            logger.debug( "(3.1) Выбираем прибор " + i + ":(0-7) для индуктора1");
                            JRadioButton rad_ind1 = ( JRadioButton) m_dlgInd1.lstRads.get(i);

                            if( rad_ind1.isEnabled()) {

                                rad_ind1.setSelected( true);

                                m_dlgInd1.btnStartLevel.setEnabled( true);
                                m_dlgInd1.radInd1.setEnabled( true);
                                m_dlgInd1.radInd2.setEnabled( true);

                                int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                                if( nGetter == HVV_AdminConstants.GETTER1)
                                    m_dlgInd1.lblGetterValue.setText( "1");
                                else
                                    m_dlgInd1.lblGetterValue.setText( "2");

                                m_dlgInd1.DefineProgram();
                                m_dlgInd1.LoadProgram();
                                m_dlgInd1.RefreshProgram();

                                break;
                            }
                        }
                    }
                    
                    if( bInd2 == false) {
                        for( int i=0; i<8; i++) {
                            logger.debug( "(3.2) Выбираем прибор " + i + ":(0-7) для индуктора2");
                            JRadioButton rad_ind2 = ( JRadioButton) m_dlgInd2.lstRads.get(i);

                            if( rad_ind2.isEnabled()) {

                                rad_ind2.setSelected( true);

                                m_dlgInd2.btnStartLevel.setEnabled( true);
                                m_dlgInd2.radInd1.setEnabled( true);
                                m_dlgInd2.radInd2.setEnabled( true);

                                int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                                if( nGetter == HVV_AdminConstants.GETTER1)
                                    m_dlgInd2.lblGetterValue.setText( "1");
                                else
                                    m_dlgInd2.lblGetterValue.setText( "2");

                                m_dlgInd2.DefineProgram();
                                m_dlgInd2.LoadProgram();
                                m_dlgInd2.RefreshProgram();

                                break;
                            }
                        }
                    }
                }
                
                
                /*
                //выбираем прибор для индуктора 1
                int i;
                for( i=0; i<8; i++) {
                    JRadioButton rad = ( JRadioButton) m_dlgInd1.lstRads.get(i);
                    if( rad.isEnabled()) {
                        rad.setSelected( true);

                        m_dlgInd1.btnStart.setEnabled( true);
                        m_dlgInd1.radInd1.setEnabled( true);
                        m_dlgInd1.radInd2.setEnabled( true);

                        int nGetter = ( int) theApp.m_mapDeviceGetter.get( i);
                        if( nGetter == HVV_AdminConstants.GETTER1)
                            m_dlgInd1.lblGetterValue.setText( "1");
                        else
                            m_dlgInd1.lblGetterValue.setText( "2");

                        m_dlgInd1.DefineProgram();
                        m_dlgInd1.LoadProgram();
                        m_dlgInd1.RefreshProgram();

                        break;
                    }
                }

                //выбираем прибор для индуктора 2
                for( int j=++i; j<8; j++) {
                    JRadioButton rad = ( JRadioButton) m_dlgInd2.lstRads.get(j);
                    if( rad.isEnabled()) {
                        rad.setSelected( true);

                        m_dlgInd2.btnStart.setEnabled( true);
                        m_dlgInd2.radInd1.setEnabled( true);
                        m_dlgInd2.radInd2.setEnabled( true);

                        int nGetter = ( int) theApp.m_mapDeviceGetter.get( j);
                        if( nGetter == HVV_AdminConstants.GETTER1)
                            m_dlgInd2.lblGetterValue.setText( "1");
                        else
                            m_dlgInd2.lblGetterValue.setText( "2");

                        m_dlgInd2.DefineProgram();
                        m_dlgInd2.LoadProgram();
                        m_dlgInd2.RefreshProgram();

                        break;
                    }
                }
                */

                m_dlgInd1.setRadButtonsState( false);
                m_dlgInd2.setRadButtonsState( false);
            }
        }).start();
        

        btnNext.setEnabled( false);

        m_p5PollerRunnable = new P5PollerRunnable( app);
        m_p5PollerThread = new Thread( m_p5PollerRunnable);
        m_p5PollerThread.start();

        m_pnlDevices = new TechProcessStep08_2_Dlg_3_Panel( app);
        pnlPanel.add( m_pnlDevices);
        m_pnlDevices.setVisible( true);
        m_pnlDevices.RefreshData();

        m_pnlDevices.setBounds( 0, 0, 930, 1020);

        m_lstnRefresh = new RefresherViewListener();
        m_tmrRefresh = new Timer( 100, m_lstnRefresh);
        m_tmrRefresh.start();
        
        m_timerStopWatch = new Timer( 1000, new StopWatchRefresh());
        m_timerStopWatch.start();
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
        btnNext = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        lblStopWatch = new javax.swing.JLabel();
        btnStopWatchReset = new javax.swing.JButton();
        btnStopWatchStart = new javax.swing.JButton();
        btnStopWatchStop = new javax.swing.JButton();
        btnReloadDbgSettings = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("8.1 Обезгаживание");
        setMinimumSize(new java.awt.Dimension(980, 1020));
        setResizable(false);
        addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                formMouseWheelMoved(evt);
            }
        });
        getContentPane().setLayout(null);

        pnlPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        pnlPanel.setLayout(null);
        getContentPane().add(pnlPanel);
        pnlPanel.setBounds(10, 90, 930, 820);

        btnNext.setText("Далее");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext);
        btnNext.setBounds(800, 920, 140, 60);

        jScrollBar1.setMaximum(630);
        jScrollBar1.setUnitIncrement(10);
        jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar1AdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(jScrollBar1);
        jScrollBar1.setBounds(950, 88, 20, 820);

        lblStopWatch.setFont(new java.awt.Font("Cantarell", 0, 64)); // NOI18N
        lblStopWatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStopWatch.setText("00:00:00");
        lblStopWatch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblStopWatch);
        lblStopWatch.setBounds(30, 10, 500, 70);

        btnStopWatchReset.setText("Сброс");
        btnStopWatchReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopWatchResetActionPerformed(evt);
            }
        });
        getContentPane().add(btnStopWatchReset);
        btnStopWatchReset.setBounds(820, 20, 120, 50);

        btnStopWatchStart.setText("Старт");
        btnStopWatchStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopWatchStartActionPerformed(evt);
            }
        });
        getContentPane().add(btnStopWatchStart);
        btnStopWatchStart.setBounds(540, 20, 120, 50);

        btnStopWatchStop.setText("Стоп");
        btnStopWatchStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopWatchStopActionPerformed(evt);
            }
        });
        getContentPane().add(btnStopWatchStop);
        btnStopWatchStop.setBounds(680, 20, 120, 50);

        btnReloadDbgSettings.setFont(new java.awt.Font("Cantarell", 0, 8)); // NOI18N
        btnReloadDbgSettings.setText("R");
        btnReloadDbgSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadDbgSettingsActionPerformed(evt);
            }
        });
        getContentPane().add(btnReloadDbgSettings);
        btnReloadDbgSettings.setBounds(5, 5, 20, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        //ОСТАНОВИТЬ p5Poller
        m_p5PollerRunnable.RequestStop();
        do {
            logger.info( "Waiting for P5 poller thread end...");
            
            try {
                m_p5PollerThread.join( 1000);
            } catch( InterruptedException ex) {
                logger.error( "Попытка присоединиться до завершения к потоку опроса P5 завершилась exception!", ex);
            }
            
        } while( m_p5PollerThread.isAlive());
        
        logger.info( "P5 poller thread ended");
        
        m_tmrRefresh.stop();
        
        //ЗАКРЫВАЕМ ОКНО 1
        if( m_dlgInd1.m_EmuTimer != null)   m_dlgInd1.m_EmuTimer.stop();
        if( m_dlgInd1.m_FlashTimer != null) m_dlgInd1.m_FlashTimer.stop();
        if( m_dlgInd1.m_TimeTimer != null)  m_dlgInd1.m_TimeTimer.stop();
        if( m_dlgInd1.m_DataTimer != null)  m_dlgInd1.m_DataTimer.stop();
        
        ///m_dlgInd1.m_rnblP5Watchdog.StopThread();
        ///do {
        ///    logger.info( "Waiting for P5 max watch thread end dlg1...");
        ///    
        ///    try {
        ///        m_dlgInd1.m_thrP5Watchdog.join( 1000);
        ///    } catch( InterruptedException ex) {
        ///        logger.error( "Попытка присоединиться до завершения к потоку отслеживания max P5 от диалога 1 завершилась exception!", ex);
        ///    }
        ///    
        ///} while( m_p5PollerThread.isAlive());
        ///m_dlgInd1.dispose();
        /// 
        /////ЗАКРЫВАЕМ ОКНО 2
        ///if( m_dlgInd2.m_EmuTimer != null)   m_dlgInd2.m_EmuTimer.stop();
        ///if( m_dlgInd2.m_FlashTimer != null) m_dlgInd2.m_FlashTimer.stop();
        ///if( m_dlgInd2.m_TimeTimer != null)  m_dlgInd2.m_TimeTimer.stop();
        ///if( m_dlgInd2.m_DataTimer != null)  m_dlgInd2.m_DataTimer.stop();
        ///
        ///m_dlgInd2.m_rnblP5Watchdog.StopThread();
        ///do {
        ///    logger.info( "Waiting for P5 max watch thread end dlg2...");
        ///    
        ///    try {
        ///        m_dlgInd2.m_thrP5Watchdog.join( 1000);
        ///  } catch( InterruptedException ex) {
        ///        logger.error( "Попытка присоединиться до завершения к потоку отслеживания max P5 от диалога 2 завершилась exception!", ex);
        ///    }
        ///    
        ///} while( m_p5PollerThread.isAlive());
        ///m_dlgInd2.dispose();
        ///
        ///m_timerStopWatch.stop();
        ///
        /////ЗАКРЫВАЕМ СЕБЯ
        ///dispose();
        ///
        /////отмечаем 8 этап
        ///TechProcessStepInfo info = theApp.GetStepInfo( "141");
        ///        
        ///info.SetStopDateAsCurrent();
        ///info.SetStopReportTitle( "Завершение процесса обезгаживания");
        ///info.SetStopP5( theApp.GetFromPoller( "005.01"));
        ///info.SetStopP6( theApp.GetFromPoller( "006.01"));
        ///info.SetStopP7( theApp.GetFromPoller( "007.01"));
        ///
        ///theApp.NextCurrentStep();
        ///
        ///if( theApp.m_pMainWnd.m_pPanel.pnlStep8.chk_08_02_AutoStart != null &&
        ///        theApp.m_pMainWnd.m_pPanel.pnlStep8.chk_08_02_AutoStart.isSelected()) {
        ///
        ///    info = new TechProcessStepInfo( theApp);
        ///    
        ///    info.SetStartDateAsCurrent();
        ///    info.SetStartReportTitle( "Старт программы открытия геттера");
        ///    info.SetStartP5( theApp.GetFromPoller( "005.01"));
        ///    info.SetStartP6( theApp.GetFromPoller( "006.01"));
        ///    info.SetStartP7( theApp.GetFromPoller( "007.01"));
        ///
        ///    theApp.SaveStepInfo( "142", info, true);
        ///
        ///    //theApp.m_pMainWnd.m_EmuTimer.start();
        ///    theApp.SetCurrentStepInProgress( true);
        ///    StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_" + theApp.HumanNameForStep( "142") + ".xml");
        ///    executor.StartThread();
        ///}
        
        theApp.m_pMainWnd.setVisible( true);
        
        theApp.m_pMainWnd.m_pPanel.SetStates();
        
        theApp.m_ReportGenerator.Generate();
        theApp.m_pMainWnd.m_pPanel.Reposition();
    }//GEN-LAST:event_btnNextActionPerformed

    private void formMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_formMouseWheelMoved
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
    }//GEN-LAST:event_formMouseWheelMoved

    private void jScrollBar1AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar1AdjustmentValueChanged
        if( m_pnlDevices != null)
            m_pnlDevices.setBounds( 0, 0 - jScrollBar1.getValue(), 930, 820); //904); //1020); //1020 + jScrollBar1.getValue());
    }//GEN-LAST:event_jScrollBar1AdjustmentValueChanged

    private void btnStopWatchStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopWatchStartActionPerformed
        m_nStopWatchStatus = STOPWATCH_RUNNING;
    }//GEN-LAST:event_btnStopWatchStartActionPerformed

    private void btnStopWatchStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopWatchStopActionPerformed
        m_nStopWatchStatus = STOPWATCH_STOPPED;
    }//GEN-LAST:event_btnStopWatchStopActionPerformed

    private void btnStopWatchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopWatchResetActionPerformed
        m_nStopWatchTicks = 0;
    }//GEN-LAST:event_btnStopWatchResetActionPerformed

    private void btnReloadDbgSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadDbgSettingsActionPerformed
        logger.warn( "Обновление (перезачитывание) DEBUG-настроек");
        theApp.GetSettings().ReadSettingsDbg();
    }//GEN-LAST:event_btnReloadDbgSettingsActionPerformed

    
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
                if ("GTK+".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M_old.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M_old.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M_old.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M_old.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
                Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
                TechProcessStep08_1_Dlg_M_old dialog = new TechProcessStep08_1_Dlg_M_old( null, new javax.swing.JFrame(), false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
                
                
                ///TechProcessStep08_1_Dlg dialog2 = new TechProcessStep08_1_Dlg( null, new javax.swing.JFrame(), false, HVV_AdminConstants.INDUCTOR2, dialog);
                /*dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });*/
                ///dialog2.setVisible(true);
                ///dialog2.setLocation( rect.width - dialog2.getWidth(), 0);
                
                ///TechProcessStep08_1_Dlg dialog1 = new TechProcessStep08_1_Dlg( null, new javax.swing.JFrame(), false, HVV_AdminConstants.INDUCTOR1, dialog);
                /*dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });*/
                ///dialog1.setVisible(true);
                ///dialog1.setLocation( rect.width - dialog1.getWidth() * 2, 0);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNext;
    private javax.swing.JButton btnReloadDbgSettings;
    private javax.swing.JButton btnStopWatchReset;
    private javax.swing.JButton btnStopWatchStart;
    private javax.swing.JButton btnStopWatchStop;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel lblStopWatch;
    private javax.swing.JPanel pnlPanel;
    // End of variables declaration//GEN-END:variables
}
