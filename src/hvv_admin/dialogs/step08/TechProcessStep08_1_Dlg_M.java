package hvv_admin.dialogs.step08;

import hvv_admin.dialogs.step08.*;
import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.comm.executor.to.StartProgramExecutor;
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
public class TechProcessStep08_1_Dlg_M extends javax.swing.JDialog {

    private final Font m_fontUsual;
    public Font GetUsualFont() { return m_fontUsual; }
    
    private final Font m_fontBold;
    public Font GetBoldFont() { return m_fontBold; }
    
    static Logger logger = Logger.getLogger(TechProcessStep08_1_Dlg_M.class);
    final private HVV_Admin theApp;
    
    TechProcessStep08_1_Dlg m_dlgInd1 = null;
    TechProcessStep08_1_Dlg m_dlgInd2 = null;
    
    public P5PollerRunnable m_p5PollerRunnable;
    public Thread m_p5PollerThread;
    
    TechProcessStep08_1_Dlg_M_HalfPanel m_pnlDevices14;
    TechProcessStep08_1_Dlg_M_HalfPanel m_pnlDevices58;
    
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
    
    class RefresherViewListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if( m_pnlDevices14 != null)
                m_pnlDevices14.setBounds( 0, 0 - scrlBar14.getValue(), 975, 1000 + scrlBar14.getValue());
            
            if( m_pnlDevices58 != null)
                m_pnlDevices58.setBounds( 0, 0 - scrlBar58.getValue(), 975, 1000 + scrlBar58.getValue());
        }
    }
    
    RefresherViewListener m_lstnRefresh;
    public Timer m_tmrRefresh;
    
    //обработчик отложенного создания окна индукторов
    class DelayedStartOfADlgs implements ActionListener {

        private final TechProcessStep08_1_Dlg_M m_pParent;
        private final int m_nInductor;
                
        public DelayedStartOfADlgs( TechProcessStep08_1_Dlg_M pParent, int nInductor) {
            m_pParent = pParent;
            m_nInductor = nInductor;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            Timer t = ( Timer) e.getSource();
            t.stop();
        
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
            TechProcessStep08_1_Dlg pChild;
            if( m_nInductor == HVV_AdminConstants.INDUCTOR1) {
                m_pParent.m_dlgInd1 = new TechProcessStep08_1_Dlg( theApp, new javax.swing.JFrame(), false, m_nInductor, m_pParent);
                pChild = m_pParent.m_dlgInd1;
            }
            else {
                m_pParent.m_dlgInd2 = new TechProcessStep08_1_Dlg( theApp, new javax.swing.JFrame(), false, m_nInductor, m_pParent);
                pChild = m_pParent.m_dlgInd2;
            }
            
            pChild.setVisible( true);
            int nX;
            if( m_nInductor == HVV_AdminConstants.INDUCTOR1)
                nX = rect.width - pChild.getWidth() * 2;
            else
                nX = rect.width - pChild.getWidth();
            pChild.setLocation( nX, 0);
            pChild.setRadButtonsState( true);
        }
        
    }
    
    /**
     * Creates new form TechProcessStep08_2_Dlg_2
     */
    public TechProcessStep08_1_Dlg_M( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        
        theApp = app;
        initComponents();
                
        m_fontUsual = new Font( "Cantarell", Font.PLAIN, 15);
        m_fontBold = new Font( "Cantarell", Font.BOLD, 15);
        
        /*
        
        OLD WAY
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
        m_dlgInd1 = new TechProcessStep10_1_Dlg_A( theApp, new javax.swing.JFrame(), true, HVV_AdminConstants.INDUCTOR1, this);
        m_dlgInd1.setVisible( true);
        m_dlgInd1.setLocation(rect.width - m_dlgInd1.getWidth() * 2, 0);
        m_dlgInd1.setRadButtonsState( true);
        
        m_dlgInd2 = new TechProcessStep10_1_Dlg_A( theApp, new javax.swing.JFrame(), true, HVV_AdminConstants.INDUCTOR2, this);
        m_dlgInd2.setVisible( true);
        m_dlgInd2.setLocation(rect.width - m_dlgInd2.getWidth(), 0);
        m_dlgInd2.setRadButtonsState( false);
        */
        
        
        //DELAYED WAY
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
        Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
        new  Timer( 500, new DelayedStartOfADlgs( this, HVV_AdminConstants.INDUCTOR2)).start();
        new  Timer( 1000, new DelayedStartOfADlgs( this, HVV_AdminConstants.INDUCTOR1)).start();
        
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
        
        /*
        new Timer( 1500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                Timer t = ( Timer) e.getSource();
                t.stop();
                
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
                        m_dlgInd1.m_pnlSteps.PlaceEdtBtns();

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
                        m_dlgInd2.m_pnlSteps.PlaceEdtBtns();

                        break;
                    }
                }

                m_dlgInd1.setRadButtonsState( false);
                m_dlgInd2.setRadButtonsState( false);
            }
            
        }).start();
        */
        
        
        btnNext.setEnabled( false);
        
        m_p5PollerRunnable = new P5PollerRunnable( app);
        m_p5PollerThread = new Thread( m_p5PollerRunnable);
        m_p5PollerThread.start();
        
        m_pnlDevices14 = new TechProcessStep08_1_Dlg_M_HalfPanel( app, HVV_AdminConstants.DEVICE1);
        pnlPanelTop.add( m_pnlDevices14);
        m_pnlDevices14.setVisible( true);
        m_pnlDevices14.setBounds( 0, 0, 975, 1000);
        m_pnlDevices14.RefreshData();
        
        m_pnlDevices58 = new TechProcessStep08_1_Dlg_M_HalfPanel( app, HVV_AdminConstants.DEVICE5);
        pnlPanelBottom.add( m_pnlDevices58);
        m_pnlDevices58.setVisible( true);
        m_pnlDevices58.setBounds( 0, 0, 975, 1000);
        m_pnlDevices58.RefreshData();
        
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

        pnlPanelTop = new javax.swing.JPanel();
        btnNext = new javax.swing.JButton();
        scrlBar14 = new javax.swing.JScrollBar();
        pnlPanelBottom = new javax.swing.JPanel();
        scrlBar58 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();
        lblDuration_Title = new javax.swing.JLabel();
        lblPower_Title = new javax.swing.JLabel();
        lblP5start_Title = new javax.swing.JLabel();
        lblP5max_Title = new javax.swing.JLabel();
        lblP5last_Title = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDuration_Title1 = new javax.swing.JLabel();
        lblPower_Title1 = new javax.swing.JLabel();
        lblP5start_Title1 = new javax.swing.JLabel();
        lblP5max_Title1 = new javax.swing.JLabel();
        lblP5last_Title1 = new javax.swing.JLabel();
        lblDuration_Title2 = new javax.swing.JLabel();
        lblPower_Title2 = new javax.swing.JLabel();
        lblP5start_Title2 = new javax.swing.JLabel();
        lblP5max_Title2 = new javax.swing.JLabel();
        lblP5last_Title2 = new javax.swing.JLabel();
        lblDuration_Title3 = new javax.swing.JLabel();
        lblPower_Title3 = new javax.swing.JLabel();
        lblP5start_Title3 = new javax.swing.JLabel();
        lblP5max_Title3 = new javax.swing.JLabel();
        lblP5last_Title3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblP5last_Title4 = new javax.swing.JLabel();
        lblP5max_Title4 = new javax.swing.JLabel();
        lblP5start_Title4 = new javax.swing.JLabel();
        lblPower_Title4 = new javax.swing.JLabel();
        lblDuration_Title4 = new javax.swing.JLabel();
        lblP5last_Title5 = new javax.swing.JLabel();
        lblP5max_Title5 = new javax.swing.JLabel();
        lblP5start_Title5 = new javax.swing.JLabel();
        lblPower_Title5 = new javax.swing.JLabel();
        lblDuration_Title5 = new javax.swing.JLabel();
        lblP5last_Title6 = new javax.swing.JLabel();
        lblP5max_Title6 = new javax.swing.JLabel();
        lblP5start_Title6 = new javax.swing.JLabel();
        lblPower_Title6 = new javax.swing.JLabel();
        lblDuration_Title6 = new javax.swing.JLabel();
        lblP5last_Title7 = new javax.swing.JLabel();
        lblP5max_Title7 = new javax.swing.JLabel();
        lblP5start_Title7 = new javax.swing.JLabel();
        lblPower_Title7 = new javax.swing.JLabel();
        lblDuration_Title7 = new javax.swing.JLabel();
        btnReloadDebugSettings = new javax.swing.JButton();
        lblStopWatch = new javax.swing.JLabel();
        btnStopWatchReset = new javax.swing.JButton();
        btnStopWatchStart = new javax.swing.JButton();
        btnStopWatchStop = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("8.1 Обезгаживание");
        setMinimumSize(new java.awt.Dimension(1020, 1025));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlPanelTop.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        pnlPanelTop.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlPanelTopMouseWheelMoved(evt);
            }
        });
        pnlPanelTop.setLayout(null);
        getContentPane().add(pnlPanelTop);
        pnlPanelTop.setBounds(10, 150, 975, 350);

        btnNext.setText("Далее");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        getContentPane().add(btnNext);
        btnNext.setBounds(855, 935, 140, 50);

        scrlBar14.setMaximum(650);
        scrlBar14.setUnitIncrement(10);
        scrlBar14.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrlBar14AdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(scrlBar14);
        scrlBar14.setBounds(990, 150, 20, 350);

        pnlPanelBottom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        pnlPanelBottom.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlPanelBottomMouseWheelMoved(evt);
            }
        });
        pnlPanelBottom.setLayout(null);
        getContentPane().add(pnlPanelBottom);
        pnlPanelBottom.setBounds(10, 580, 975, 350);

        scrlBar58.setMaximum(650);
        scrlBar58.setUnitIncrement(10);
        scrlBar58.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                scrlBar58AdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(scrlBar58);
        scrlBar58.setBounds(990, 580, 20, 350);

        jLabel1.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("<html><u>ПРИБОР 1</u></html>");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 90, 210, 30);

        lblDuration_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title.setText("<html>t</html>");
        lblDuration_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title);
        lblDuration_Title.setBounds(10, 120, 30, 30);

        lblPower_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title.setText("<html>W</html>");
        lblPower_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title);
        lblPower_Title.setBounds(40, 120, 30, 30);

        lblP5start_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title.setToolTipText("");
        lblP5start_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title);
        lblP5start_Title.setBounds(70, 120, 60, 30);

        lblP5max_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title);
        lblP5max_Title.setBounds(130, 120, 60, 30);

        lblP5last_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title);
        lblP5last_Title.setBounds(190, 120, 60, 30);

        jLabel2.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel2.setText("<html><u>ПРИБОР 2</u></html>");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(280, 90, 210, 30);

        jLabel3.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel3.setText("<html><u>ПРИБОР 3</u></html>");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(530, 90, 210, 30);

        jLabel4.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel4.setText("<html><u>ПРИБОР 4</u></html>");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(770, 90, 210, 30);

        lblDuration_Title1.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title1.setText("<html>t</html>");
        lblDuration_Title1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title1);
        lblDuration_Title1.setBounds(255, 120, 30, 30);

        lblPower_Title1.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title1.setText("<html>W</html>");
        lblPower_Title1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title1);
        lblPower_Title1.setBounds(285, 120, 30, 30);

        lblP5start_Title1.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title1.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title1.setToolTipText("");
        lblP5start_Title1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title1);
        lblP5start_Title1.setBounds(315, 120, 60, 30);

        lblP5max_Title1.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title1.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title1);
        lblP5max_Title1.setBounds(375, 120, 60, 30);

        lblP5last_Title1.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title1.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title1);
        lblP5last_Title1.setBounds(435, 120, 60, 30);

        lblDuration_Title2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title2.setText("<html>t</html>");
        lblDuration_Title2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title2);
        lblDuration_Title2.setBounds(500, 120, 30, 30);

        lblPower_Title2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title2.setText("<html>W</html>");
        lblPower_Title2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title2);
        lblPower_Title2.setBounds(530, 120, 30, 30);

        lblP5start_Title2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title2.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title2.setToolTipText("");
        lblP5start_Title2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title2);
        lblP5start_Title2.setBounds(560, 120, 60, 30);

        lblP5max_Title2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title2.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title2);
        lblP5max_Title2.setBounds(620, 120, 60, 30);

        lblP5last_Title2.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title2.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title2);
        lblP5last_Title2.setBounds(680, 120, 60, 30);

        lblDuration_Title3.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title3.setText("<html>t</html>");
        lblDuration_Title3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title3);
        lblDuration_Title3.setBounds(745, 120, 30, 30);

        lblPower_Title3.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title3.setText("<html>W</html>");
        lblPower_Title3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title3);
        lblPower_Title3.setBounds(775, 120, 30, 30);

        lblP5start_Title3.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title3.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title3.setToolTipText("");
        lblP5start_Title3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title3);
        lblP5start_Title3.setBounds(805, 120, 60, 30);

        lblP5max_Title3.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title3.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title3);
        lblP5max_Title3.setBounds(865, 120, 60, 30);

        lblP5last_Title3.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title3.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title3);
        lblP5last_Title3.setBounds(925, 120, 60, 30);

        jLabel5.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel5.setText("<html><u>ПРИБОР 5</u></html>");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 520, 210, 30);

        jLabel6.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel6.setText("<html><u>ПРИБОР 6</u></html>");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(290, 520, 210, 30);

        jLabel7.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel7.setText("<html><u>ПРИБОР 8</u></html>");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(780, 520, 210, 30);

        jLabel8.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel8.setText("<html><u>ПРИБОР 7</u></html>");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(530, 520, 210, 30);

        lblP5last_Title4.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title4.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title4);
        lblP5last_Title4.setBounds(190, 550, 60, 30);

        lblP5max_Title4.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title4.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title4);
        lblP5max_Title4.setBounds(130, 550, 60, 30);

        lblP5start_Title4.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title4.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title4.setToolTipText("");
        lblP5start_Title4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title4);
        lblP5start_Title4.setBounds(70, 550, 60, 30);

        lblPower_Title4.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title4.setText("<html>W</html>");
        lblPower_Title4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title4);
        lblPower_Title4.setBounds(40, 550, 30, 30);

        lblDuration_Title4.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title4.setText("<html>t</html>");
        lblDuration_Title4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title4);
        lblDuration_Title4.setBounds(10, 550, 30, 30);

        lblP5last_Title5.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title5.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title5);
        lblP5last_Title5.setBounds(435, 550, 60, 30);

        lblP5max_Title5.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title5.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title5);
        lblP5max_Title5.setBounds(375, 550, 60, 30);

        lblP5start_Title5.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title5.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title5.setToolTipText("");
        lblP5start_Title5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title5);
        lblP5start_Title5.setBounds(315, 550, 60, 30);

        lblPower_Title5.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title5.setText("<html>W</html>");
        lblPower_Title5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title5);
        lblPower_Title5.setBounds(285, 550, 30, 30);

        lblDuration_Title5.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title5.setText("<html>t</html>");
        lblDuration_Title5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title5);
        lblDuration_Title5.setBounds(255, 550, 30, 30);

        lblP5last_Title6.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title6.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title6);
        lblP5last_Title6.setBounds(680, 550, 60, 30);

        lblP5max_Title6.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title6.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title6);
        lblP5max_Title6.setBounds(620, 550, 60, 30);

        lblP5start_Title6.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title6.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title6.setToolTipText("");
        lblP5start_Title6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title6);
        lblP5start_Title6.setBounds(560, 550, 60, 30);

        lblPower_Title6.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title6.setText("<html>W</html>");
        lblPower_Title6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title6);
        lblPower_Title6.setBounds(530, 550, 30, 30);

        lblDuration_Title6.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title6.setText("<html>t</html>");
        lblDuration_Title6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title6);
        lblDuration_Title6.setBounds(500, 550, 30, 30);

        lblP5last_Title7.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title7.setText("<html>P<sub>5, last</sub></html>:");
        lblP5last_Title7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5last_Title7);
        lblP5last_Title7.setBounds(925, 550, 60, 30);

        lblP5max_Title7.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title7.setText("<html>P<sub>5, max</sub></html>:");
        lblP5max_Title7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5max_Title7);
        lblP5max_Title7.setBounds(865, 550, 60, 30);

        lblP5start_Title7.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title7.setText("<html>P<sub>5, start</sub></html>:");
        lblP5start_Title7.setToolTipText("");
        lblP5start_Title7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblP5start_Title7);
        lblP5start_Title7.setBounds(805, 550, 60, 30);

        lblPower_Title7.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title7.setText("<html>W</html>");
        lblPower_Title7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblPower_Title7);
        lblPower_Title7.setBounds(775, 550, 30, 30);

        lblDuration_Title7.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title7.setText("<html>t</html>");
        lblDuration_Title7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        getContentPane().add(lblDuration_Title7);
        lblDuration_Title7.setBounds(745, 550, 30, 30);

        btnReloadDebugSettings.setFont(new java.awt.Font("Cantarell", 0, 8)); // NOI18N
        btnReloadDebugSettings.setText("R");
        btnReloadDebugSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadDebugSettingsActionPerformed(evt);
            }
        });
        getContentPane().add(btnReloadDebugSettings);
        btnReloadDebugSettings.setBounds(5, 5, 20, 20);

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
        btnStopWatchReset.setBounds(850, 20, 120, 50);

        btnStopWatchStart.setText("Старт");
        btnStopWatchStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopWatchStartActionPerformed(evt);
            }
        });
        getContentPane().add(btnStopWatchStart);
        btnStopWatchStart.setBounds(570, 20, 120, 50);

        btnStopWatchStop.setText("Стоп");
        btnStopWatchStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopWatchStopActionPerformed(evt);
            }
        });
        getContentPane().add(btnStopWatchStop);
        btnStopWatchStop.setBounds(710, 20, 120, 50);

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
        
        m_dlgInd1.m_rnblP5Watchdog.StopThread();
        do {
            logger.info( "Waiting for P5 max watch thread end dlg1...");
            
            try {
                m_dlgInd1.m_thrP5Watchdog.join( 1000);
            } catch( InterruptedException ex) {
                logger.error( "Попытка присоединиться до завершения к потоку отслеживания max P5 от диалога 1 завершилась exception!", ex);
            }
            
        } while( m_dlgInd1.m_thrP5Watchdog.isAlive());
        m_dlgInd1.dispose();
        
        //ЗАКРЫВАЕМ ОКНО 2
        if( m_dlgInd2.m_EmuTimer != null)   m_dlgInd2.m_EmuTimer.stop();
        if( m_dlgInd2.m_FlashTimer != null) m_dlgInd2.m_FlashTimer.stop();
        if( m_dlgInd2.m_TimeTimer != null)  m_dlgInd2.m_TimeTimer.stop();
        if( m_dlgInd2.m_DataTimer != null)  m_dlgInd2.m_DataTimer.stop();
        
        m_dlgInd2.m_rnblP5Watchdog.StopThread();
        do {
            logger.info( "Waiting for P5 max watch thread end dlg2...");
            
            try {
                m_dlgInd2.m_thrP5Watchdog.join( 1000);
            } catch( InterruptedException ex) {
                logger.error( "Попытка присоединиться до завершения к потоку отслеживания max P5 от диалога 2 завершилась exception!", ex);
            }
            
        } while( m_dlgInd2.m_thrP5Watchdog.isAlive());
        m_dlgInd2.dispose();
        
        m_timerStopWatch.stop();
        
        logger.info( "Закрываем себя");
        //ЗАКРЫВАЕМ СЕБЯ
        dispose();
        logger.info( "Закрыли себя");
        
        //отмечаем 8.1 этап
        TechProcessStepInfo info = ( TechProcessStepInfo) theApp.GetStepInfo( "141");
                
        info.SetStopDateAsCurrent();
        info.SetStopReportTitle( "Завершение процесса активации");
        info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
        info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
        info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));

        theApp.NextCurrentStep();
        logger.info( "Перещёлкнулись на 8.2");
        
        if( theApp.m_pMainWnd.m_pPanel.pnlStep8.chk_08_02_AutoStart != null &&
                theApp.m_pMainWnd.m_pPanel.pnlStep8.chk_08_02_AutoStart.isSelected()) {
            info = new TechProcessStepInfo( theApp);
            
            info.SetStartDateAsCurrent();
            info.SetStartReportTitle( "Старт программы открытия геттера");
            info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));

            theApp.SaveStepInfo( "142", info, true);

            //theApp.m_pMainWnd.m_EmuTimer.start();
            theApp.SetCurrentStepInProgress( true);
            StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_" + theApp.HumanNameForStep( "142") + ".xml");
            executor.StartThread();
        }
        
        theApp.m_pMainWnd.m_pPanel.SetStates();
        logger.info( "Перерисовали основное окно");
        
        theApp.m_ReportGenerator.Generate();
        logger.info( "Сгенерили отчёт");
        
        theApp.m_pMainWnd.m_pPanel.Reposition();
        logger.info( "Перегруппировали основное окно");
        
        theApp.m_pMainWnd.setVisible( true);
    }//GEN-LAST:event_btnNextActionPerformed

    private void scrlBar14AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrlBar14AdjustmentValueChanged
        if( m_pnlDevices14 != null)
            m_pnlDevices14.setBounds( 0, 0 - scrlBar14.getValue(), 975, 1000 + scrlBar14.getValue());
    }//GEN-LAST:event_scrlBar14AdjustmentValueChanged

    private void scrlBar58AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_scrlBar58AdjustmentValueChanged
        if( m_pnlDevices58 != null)
            m_pnlDevices58.setBounds( 0, 0 - scrlBar58.getValue(), 975, 1000 + scrlBar58.getValue());
    }//GEN-LAST:event_scrlBar58AdjustmentValueChanged

    private void pnlPanelTopMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlPanelTopMouseWheelMoved
        if( scrlBar14.isEnabled()) {
            int nMin = scrlBar14.getMinimum();
            int nMax = scrlBar14.getMaximum();
            int nPos = scrlBar14.getValue();
            int nStep1 = scrlBar14.getBlockIncrement();
            int nStep2 = scrlBar14.getUnitIncrement();

            int nNextPos = nPos + evt.getWheelRotation() * nStep1;

            if( nNextPos < nMin) nNextPos = nMin;
            if( nNextPos > nMax) nNextPos = nMax;
            scrlBar14.setValue( nNextPos);
        }
    }//GEN-LAST:event_pnlPanelTopMouseWheelMoved

    private void pnlPanelBottomMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlPanelBottomMouseWheelMoved
        if( scrlBar58.isEnabled()) {
            int nMin = scrlBar58.getMinimum();
            int nMax = scrlBar58.getMaximum();
            int nPos = scrlBar58.getValue();
            int nStep1 = scrlBar58.getBlockIncrement();
            int nStep2 = scrlBar58.getUnitIncrement();

            int nNextPos = nPos + evt.getWheelRotation() * nStep1;

            if( nNextPos < nMin) nNextPos = nMin;
            if( nNextPos > nMax) nNextPos = nMax;
            scrlBar58.setValue( nNextPos);
        }
    }//GEN-LAST:event_pnlPanelBottomMouseWheelMoved

    private void btnReloadDebugSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadDebugSettingsActionPerformed
        logger.warn( "Обновление (перезачитывание) DEBUG-настроек");
        theApp.GetSettings().ReadSettingsDbg();
    }//GEN-LAST:event_btnReloadDebugSettingsActionPerformed

    private void btnStopWatchResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopWatchResetActionPerformed
        m_nStopWatchTicks = 0;
    }//GEN-LAST:event_btnStopWatchResetActionPerformed

    private void btnStopWatchStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopWatchStartActionPerformed
        m_nStopWatchStatus = STOPWATCH_RUNNING;
    }//GEN-LAST:event_btnStopWatchStartActionPerformed

    private void btnStopWatchStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopWatchStopActionPerformed
        m_nStopWatchStatus = STOPWATCH_STOPPED;
    }//GEN-LAST:event_btnStopWatchStopActionPerformed

    
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
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep08_1_Dlg_M.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                
                
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
                Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
        
                TechProcessStep08_1_Dlg_M dialog = new TechProcessStep08_1_Dlg_M( null, new javax.swing.JFrame(), false);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
                
                
                TechProcessStep08_1_Dlg dialog2 = new TechProcessStep08_1_Dlg( null, new javax.swing.JFrame(), false, HVV_AdminConstants.INDUCTOR2, dialog);
                /*dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });*/
                dialog2.setVisible(true);
                dialog2.setLocation( rect.width - dialog2.getWidth(), 0);
                
                TechProcessStep08_1_Dlg dialog1 = new TechProcessStep08_1_Dlg( null, new javax.swing.JFrame(), false, HVV_AdminConstants.INDUCTOR1, dialog);
                /*dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });*/
                dialog1.setVisible(true);
                dialog1.setLocation( rect.width - dialog1.getWidth() * 2, 0);
                
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnNext;
    private javax.swing.JButton btnReloadDebugSettings;
    private javax.swing.JButton btnStopWatchReset;
    private javax.swing.JButton btnStopWatchStart;
    private javax.swing.JButton btnStopWatchStop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel lblDuration_Title;
    private javax.swing.JLabel lblDuration_Title1;
    private javax.swing.JLabel lblDuration_Title2;
    private javax.swing.JLabel lblDuration_Title3;
    private javax.swing.JLabel lblDuration_Title4;
    private javax.swing.JLabel lblDuration_Title5;
    private javax.swing.JLabel lblDuration_Title6;
    private javax.swing.JLabel lblDuration_Title7;
    private javax.swing.JLabel lblP5last_Title;
    private javax.swing.JLabel lblP5last_Title1;
    private javax.swing.JLabel lblP5last_Title2;
    private javax.swing.JLabel lblP5last_Title3;
    private javax.swing.JLabel lblP5last_Title4;
    private javax.swing.JLabel lblP5last_Title5;
    private javax.swing.JLabel lblP5last_Title6;
    private javax.swing.JLabel lblP5last_Title7;
    private javax.swing.JLabel lblP5max_Title;
    private javax.swing.JLabel lblP5max_Title1;
    private javax.swing.JLabel lblP5max_Title2;
    private javax.swing.JLabel lblP5max_Title3;
    private javax.swing.JLabel lblP5max_Title4;
    private javax.swing.JLabel lblP5max_Title5;
    private javax.swing.JLabel lblP5max_Title6;
    private javax.swing.JLabel lblP5max_Title7;
    private javax.swing.JLabel lblP5start_Title;
    private javax.swing.JLabel lblP5start_Title1;
    private javax.swing.JLabel lblP5start_Title2;
    private javax.swing.JLabel lblP5start_Title3;
    private javax.swing.JLabel lblP5start_Title4;
    private javax.swing.JLabel lblP5start_Title5;
    private javax.swing.JLabel lblP5start_Title6;
    private javax.swing.JLabel lblP5start_Title7;
    private javax.swing.JLabel lblPower_Title;
    private javax.swing.JLabel lblPower_Title1;
    private javax.swing.JLabel lblPower_Title2;
    private javax.swing.JLabel lblPower_Title3;
    private javax.swing.JLabel lblPower_Title4;
    private javax.swing.JLabel lblPower_Title5;
    private javax.swing.JLabel lblPower_Title6;
    private javax.swing.JLabel lblPower_Title7;
    private javax.swing.JLabel lblStopWatch;
    private javax.swing.JPanel pnlPanelBottom;
    private javax.swing.JPanel pnlPanelTop;
    private javax.swing.JScrollBar scrlBar14;
    private javax.swing.JScrollBar scrlBar58;
    // End of variables declaration//GEN-END:variables
}
