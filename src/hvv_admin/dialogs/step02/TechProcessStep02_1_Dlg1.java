package hvv_admin.dialogs.step02;

import hvv_admin.dialogs.step01.TechProcessStep01Panel;
import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.util.LinkedList;
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
public class TechProcessStep02_1_Dlg1 extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger( TechProcessStep02_1_Dlg1.class);
    final private HVV_Admin theApp;
    /**
     * Creates new form TechProcessStep2_1_Dlg
     */
    public TechProcessStep02_1_Dlg1( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        theApp = app;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrGet1 = new javax.swing.ButtonGroup();
        btnGrGet2 = new javax.swing.ButtonGroup();
        btnGrGet3 = new javax.swing.ButtonGroup();
        btnGrGet4 = new javax.swing.ButtonGroup();
        btnGrGet5 = new javax.swing.ButtonGroup();
        btnGrGet6 = new javax.swing.ButtonGroup();
        btnGrGet7 = new javax.swing.ButtonGroup();
        btnGrGet8 = new javax.swing.ButtonGroup();
        lblPlace1 = new javax.swing.JLabel();
        edtSerial1 = new javax.swing.JTextField();
        lblPlace2 = new javax.swing.JLabel();
        edtSerial2 = new javax.swing.JTextField();
        lblPlace3 = new javax.swing.JLabel();
        edtSerial3 = new javax.swing.JTextField();
        lblPlace4 = new javax.swing.JLabel();
        edtSerial4 = new javax.swing.JTextField();
        lblPlace5 = new javax.swing.JLabel();
        edtSerial5 = new javax.swing.JTextField();
        lblPlace6 = new javax.swing.JLabel();
        edtSerial6 = new javax.swing.JTextField();
        lblPlace7 = new javax.swing.JLabel();
        edtSerial7 = new javax.swing.JTextField();
        lblPlace8 = new javax.swing.JLabel();
        edtSerial8 = new javax.swing.JTextField();
        lblGetters = new javax.swing.JLabel();
        lblSerials = new javax.swing.JLabel();
        radGet11 = new javax.swing.JRadioButton();
        radGet12 = new javax.swing.JRadioButton();
        radGet21 = new javax.swing.JRadioButton();
        radGet22 = new javax.swing.JRadioButton();
        radGet31 = new javax.swing.JRadioButton();
        radGet32 = new javax.swing.JRadioButton();
        radGet41 = new javax.swing.JRadioButton();
        radGet42 = new javax.swing.JRadioButton();
        radGet51 = new javax.swing.JRadioButton();
        radGet52 = new javax.swing.JRadioButton();
        radGet61 = new javax.swing.JRadioButton();
        radGet62 = new javax.swing.JRadioButton();
        radGet71 = new javax.swing.JRadioButton();
        radGet72 = new javax.swing.JRadioButton();
        radGet81 = new javax.swing.JRadioButton();
        radGet82 = new javax.swing.JRadioButton();
        btnContinue = new javax.swing.JButton();
        chkDevicePresence1 = new javax.swing.JCheckBox();
        chkDevicePresence2 = new javax.swing.JCheckBox();
        chkDevicePresence3 = new javax.swing.JCheckBox();
        chkDevicePresence4 = new javax.swing.JCheckBox();
        chkDevicePresence5 = new javax.swing.JCheckBox();
        chkDevicePresence6 = new javax.swing.JCheckBox();
        chkDevicePresence7 = new javax.swing.JCheckBox();
        chkDevicePresence8 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("2.1 Занесение информации об установленных приборах");
        setMinimumSize(new java.awt.Dimension(480, 530));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblPlace1.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace1.setText("1");
        lblPlace1.setEnabled(false);
        getContentPane().add(lblPlace1);
        lblPlace1.setBounds(80, 40, 50, 40);

        edtSerial1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial1.setEnabled(false);
        getContentPane().add(edtSerial1);
        edtSerial1.setBounds(150, 50, 110, 26);

        lblPlace2.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace2.setText("2");
        lblPlace2.setEnabled(false);
        getContentPane().add(lblPlace2);
        lblPlace2.setBounds(80, 90, 50, 40);

        edtSerial2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial2.setEnabled(false);
        getContentPane().add(edtSerial2);
        edtSerial2.setBounds(150, 100, 110, 26);

        lblPlace3.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace3.setText("3");
        lblPlace3.setEnabled(false);
        getContentPane().add(lblPlace3);
        lblPlace3.setBounds(80, 140, 50, 40);

        edtSerial3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial3.setEnabled(false);
        getContentPane().add(edtSerial3);
        edtSerial3.setBounds(150, 150, 110, 26);

        lblPlace4.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace4.setText("4");
        lblPlace4.setEnabled(false);
        getContentPane().add(lblPlace4);
        lblPlace4.setBounds(80, 190, 50, 40);

        edtSerial4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial4.setEnabled(false);
        getContentPane().add(edtSerial4);
        edtSerial4.setBounds(150, 200, 110, 26);

        lblPlace5.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace5.setText("5");
        lblPlace5.setEnabled(false);
        getContentPane().add(lblPlace5);
        lblPlace5.setBounds(80, 240, 50, 40);

        edtSerial5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial5.setEnabled(false);
        getContentPane().add(edtSerial5);
        edtSerial5.setBounds(150, 250, 110, 26);

        lblPlace6.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace6.setText("6");
        lblPlace6.setEnabled(false);
        getContentPane().add(lblPlace6);
        lblPlace6.setBounds(80, 290, 50, 40);

        edtSerial6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial6.setEnabled(false);
        getContentPane().add(edtSerial6);
        edtSerial6.setBounds(150, 300, 110, 26);

        lblPlace7.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace7.setText("7");
        lblPlace7.setEnabled(false);
        getContentPane().add(lblPlace7);
        lblPlace7.setBounds(80, 340, 50, 40);

        edtSerial7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial7.setEnabled(false);
        getContentPane().add(edtSerial7);
        edtSerial7.setBounds(150, 350, 110, 26);

        lblPlace8.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace8.setText("8");
        lblPlace8.setEnabled(false);
        getContentPane().add(lblPlace8);
        lblPlace8.setBounds(80, 390, 50, 40);

        edtSerial8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtSerial8.setEnabled(false);
        getContentPane().add(edtSerial8);
        edtSerial8.setBounds(150, 400, 110, 26);

        lblGetters.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGetters.setText("Тип геттера");
        lblGetters.setBorder(null);
        getContentPane().add(lblGetters);
        lblGetters.setBounds(320, 10, 120, 30);

        lblSerials.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSerials.setText("Серийные номера приборов");
        getContentPane().add(lblSerials);
        lblSerials.setBounds(90, 10, 220, 30);

        btnGrGet1.add(radGet11);
        radGet11.setText("1");
        radGet11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet11.setEnabled(false);
        radGet11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet11);
        radGet11.setBounds(320, 50, 50, 22);

        btnGrGet1.add(radGet12);
        radGet12.setSelected(true);
        radGet12.setText("2");
        radGet12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet12.setEnabled(false);
        radGet12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet12);
        radGet12.setBounds(380, 50, 50, 22);

        btnGrGet2.add(radGet21);
        radGet21.setText("1");
        radGet21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet21.setEnabled(false);
        radGet21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet21);
        radGet21.setBounds(320, 100, 50, 22);

        btnGrGet2.add(radGet22);
        radGet22.setSelected(true);
        radGet22.setText("2");
        radGet22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet22.setEnabled(false);
        radGet22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet22);
        radGet22.setBounds(380, 100, 50, 22);

        btnGrGet3.add(radGet31);
        radGet31.setText("1");
        radGet31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet31.setEnabled(false);
        radGet31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet31);
        radGet31.setBounds(320, 150, 50, 22);

        btnGrGet3.add(radGet32);
        radGet32.setSelected(true);
        radGet32.setText("2");
        radGet32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet32.setEnabled(false);
        radGet32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet32);
        radGet32.setBounds(380, 150, 50, 22);

        btnGrGet4.add(radGet41);
        radGet41.setText("1");
        radGet41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet41.setEnabled(false);
        radGet41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet41);
        radGet41.setBounds(320, 200, 50, 22);

        btnGrGet4.add(radGet42);
        radGet42.setSelected(true);
        radGet42.setText("2");
        radGet42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet42.setEnabled(false);
        radGet42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet42);
        radGet42.setBounds(380, 200, 50, 22);

        btnGrGet5.add(radGet51);
        radGet51.setText("1");
        radGet51.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet51.setEnabled(false);
        radGet51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet51);
        radGet51.setBounds(320, 250, 50, 22);

        btnGrGet5.add(radGet52);
        radGet52.setSelected(true);
        radGet52.setText("2");
        radGet52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet52.setEnabled(false);
        radGet52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet52);
        radGet52.setBounds(380, 250, 50, 22);

        btnGrGet6.add(radGet61);
        radGet61.setText("1");
        radGet61.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet61.setEnabled(false);
        radGet61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet61);
        radGet61.setBounds(320, 300, 50, 22);

        btnGrGet6.add(radGet62);
        radGet62.setSelected(true);
        radGet62.setText("2");
        radGet62.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet62.setEnabled(false);
        radGet62.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet62);
        radGet62.setBounds(380, 300, 50, 22);

        btnGrGet7.add(radGet71);
        radGet71.setText("1");
        radGet71.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet71.setEnabled(false);
        radGet71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet71);
        radGet71.setBounds(320, 350, 50, 22);

        btnGrGet7.add(radGet72);
        radGet72.setSelected(true);
        radGet72.setText("2");
        radGet72.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet72.setEnabled(false);
        radGet72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet72);
        radGet72.setBounds(380, 350, 50, 22);

        btnGrGet8.add(radGet81);
        radGet81.setText("1");
        radGet81.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet81.setEnabled(false);
        radGet81.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet81);
        radGet81.setBounds(320, 400, 50, 22);

        btnGrGet8.add(radGet82);
        radGet82.setSelected(true);
        radGet82.setText("2");
        radGet82.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        radGet82.setEnabled(false);
        radGet82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(radGet82);
        radGet82.setBounds(380, 400, 50, 22);

        btnContinue.setText("Далее");
        btnContinue.setEnabled(false);
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        getContentPane().add(btnContinue);
        btnContinue.setBounds(120, 450, 240, 40);

        chkDevicePresence1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence1ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence1);
        chkDevicePresence1.setBounds(30, 40, 30, 40);

        chkDevicePresence2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence2ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence2);
        chkDevicePresence2.setBounds(30, 90, 30, 40);

        chkDevicePresence3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence3ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence3);
        chkDevicePresence3.setBounds(30, 140, 30, 40);

        chkDevicePresence4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence4ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence4);
        chkDevicePresence4.setBounds(30, 190, 30, 40);

        chkDevicePresence5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence5ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence5);
        chkDevicePresence5.setBounds(30, 240, 30, 40);

        chkDevicePresence6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence6ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence6);
        chkDevicePresence6.setBounds(30, 290, 30, 40);

        chkDevicePresence7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence7ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence7);
        chkDevicePresence7.setBounds(30, 340, 30, 40);

        chkDevicePresence8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkDevicePresence8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        chkDevicePresence8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDevicePresence8ActionPerformed(evt);
            }
        });
        getContentPane().add(chkDevicePresence8);
        chkDevicePresence8.setBounds(30, 390, 30, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        //т.к. этап 2.1 ручной, мы переходим к следующему подэтапу (2.2)
        
        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE1, edtSerial1.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE1, chkDevicePresence1.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE1, radGet11.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);
        
        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE2, edtSerial2.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE2, chkDevicePresence2.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE2, radGet21.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);

        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE3, edtSerial3.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE3, chkDevicePresence3.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE3, radGet31.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);
        
        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE4, edtSerial4.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE4, chkDevicePresence4.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE4, radGet41.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);
        
        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE5, edtSerial5.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE5, chkDevicePresence5.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE5, radGet51.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);
        
        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE6, edtSerial6.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE6, chkDevicePresence6.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE6, radGet61.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);
        
        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE7, edtSerial7.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE7, chkDevicePresence7.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE7, radGet71.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);
        
        theApp.m_mapSerials.put(        HVV_AdminConstants.DEVICE8, edtSerial8.getText());
        theApp.m_mapDevicePresence.put( HVV_AdminConstants.DEVICE8, chkDevicePresence8.isSelected());
        theApp.m_mapDeviceGetter.put(   HVV_AdminConstants.DEVICE8, radGet81.isSelected() ? HVV_AdminConstants.GETTER1 : HVV_AdminConstants.GETTER2);
        
        if( theApp.IsStepMapContainsKey( "021")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "021");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Установка резонаторов");
            info.SetStopP5( theApp.GetFromPoller( "005.01"));
            info.SetStopP6( theApp.GetFromPoller( "006.01"));
            info.SetStopP7( theApp.GetFromPoller( "007.01"));
            
            theApp.NextCurrentStep();

            if( theApp.m_pMainWnd.m_pPanel.pnlStep2.chk_02_02_AutoStart.isSelected()) {
                info = new TechProcessStepInfo( theApp);
                
                info.SetStartDateAsCurrent();
                info.SetStartReportTitle( "Старт предварительной откачки");
                info.SetStartP5( theApp.GetFromPoller( "005.01"));
                info.SetStartP6( theApp.GetFromPoller( "006.01"));
                info.SetStartP7( theApp.GetFromPoller( "007.01"));
                
                theApp.SaveStepInfo( "022", info, true);
                
                //theApp.m_pMainWnd.m_EmuTimer.start();
                theApp.SetCurrentStepInProgress( true);
                StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_2.2.xml");
                executor.StartThread();
            }
            
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();
        }
        else {
            logger.fatal( "Мы заканчиваем этап 021, а инфы на него до сих пор нет!");
        }
        theApp.GetStateKeeper().SaveState();
        theApp.m_ReportGenerator.Generate();
        dispose();
    }//GEN-LAST:event_btnContinueActionPerformed

    private void chkDevicePresence1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence1ActionPerformed
        lblPlace1.setEnabled( chkDevicePresence1.isSelected());
        edtSerial1.setEnabled( chkDevicePresence1.isSelected());
        if( chkDevicePresence1.isSelected()) edtSerial1.requestFocus();
        radGet11.setEnabled( chkDevicePresence1.isSelected());
        radGet12.setEnabled( chkDevicePresence1.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence1ActionPerformed

    private void chkDevicePresence2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence2ActionPerformed
        lblPlace2.setEnabled( chkDevicePresence2.isSelected());
        edtSerial2.setEnabled( chkDevicePresence2.isSelected());
        if( chkDevicePresence2.isSelected()) edtSerial2.requestFocus();
        radGet21.setEnabled( chkDevicePresence2.isSelected());
        radGet22.setEnabled( chkDevicePresence2.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence2ActionPerformed

    private void chkDevicePresence3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence3ActionPerformed
        lblPlace3.setEnabled( chkDevicePresence3.isSelected());
        edtSerial3.setEnabled( chkDevicePresence3.isSelected());
        if( chkDevicePresence3.isSelected()) edtSerial3.requestFocus();
        radGet31.setEnabled( chkDevicePresence3.isSelected());
        radGet32.setEnabled( chkDevicePresence3.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence3ActionPerformed

    private void chkDevicePresence4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence4ActionPerformed
        lblPlace4.setEnabled( chkDevicePresence4.isSelected());
        edtSerial4.setEnabled( chkDevicePresence4.isSelected());
        if( chkDevicePresence4.isSelected()) edtSerial4.requestFocus();
        radGet41.setEnabled( chkDevicePresence4.isSelected());
        radGet42.setEnabled( chkDevicePresence4.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence4ActionPerformed

    private void chkDevicePresence5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence5ActionPerformed
        lblPlace5.setEnabled( chkDevicePresence5.isSelected());
        edtSerial5.setEnabled( chkDevicePresence5.isSelected());
        if( chkDevicePresence5.isSelected()) edtSerial5.requestFocus();
        radGet51.setEnabled( chkDevicePresence5.isSelected());
        radGet52.setEnabled( chkDevicePresence5.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence5ActionPerformed

    private void chkDevicePresence6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence6ActionPerformed
        lblPlace6.setEnabled( chkDevicePresence6.isSelected());
        edtSerial6.setEnabled( chkDevicePresence6.isSelected());
        if( chkDevicePresence6.isSelected()) edtSerial6.requestFocus();
        radGet61.setEnabled( chkDevicePresence6.isSelected());
        radGet62.setEnabled( chkDevicePresence6.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence6ActionPerformed

    private void chkDevicePresence7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence7ActionPerformed
        lblPlace7.setEnabled( chkDevicePresence7.isSelected());
        edtSerial7.setEnabled( chkDevicePresence7.isSelected());
        if( chkDevicePresence7.isSelected()) edtSerial7.requestFocus();
        radGet71.setEnabled( chkDevicePresence7.isSelected());
        radGet72.setEnabled( chkDevicePresence7.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence7ActionPerformed

    private void chkDevicePresence8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDevicePresence8ActionPerformed
        lblPlace8.setEnabled( chkDevicePresence8.isSelected());
        edtSerial8.setEnabled( chkDevicePresence8.isSelected());
        if( chkDevicePresence8.isSelected()) edtSerial8.requestFocus();
        radGet81.setEnabled( chkDevicePresence8.isSelected());
        radGet82.setEnabled( chkDevicePresence8.isSelected());
        btnContinue.setEnabled( chkDevicePresence1.isSelected() |
                                chkDevicePresence2.isSelected() |
                                chkDevicePresence3.isSelected() |
                                chkDevicePresence4.isSelected() |
                                chkDevicePresence5.isSelected() |
                                chkDevicePresence6.isSelected() |
                                chkDevicePresence7.isSelected() |
                                chkDevicePresence8.isSelected());
    }//GEN-LAST:event_chkDevicePresence8ActionPerformed

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
            java.util.logging.Logger.getLogger(TechProcessStep02_1_Dlg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep02_1_Dlg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep02_1_Dlg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep02_1_Dlg1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TechProcessStep02_1_Dlg1 dialog = new TechProcessStep02_1_Dlg1( null, new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnContinue;
    private javax.swing.ButtonGroup btnGrGet1;
    private javax.swing.ButtonGroup btnGrGet2;
    private javax.swing.ButtonGroup btnGrGet3;
    private javax.swing.ButtonGroup btnGrGet4;
    private javax.swing.ButtonGroup btnGrGet5;
    private javax.swing.ButtonGroup btnGrGet6;
    private javax.swing.ButtonGroup btnGrGet7;
    private javax.swing.ButtonGroup btnGrGet8;
    private javax.swing.JCheckBox chkDevicePresence1;
    private javax.swing.JCheckBox chkDevicePresence2;
    private javax.swing.JCheckBox chkDevicePresence3;
    private javax.swing.JCheckBox chkDevicePresence4;
    private javax.swing.JCheckBox chkDevicePresence5;
    private javax.swing.JCheckBox chkDevicePresence6;
    private javax.swing.JCheckBox chkDevicePresence7;
    private javax.swing.JCheckBox chkDevicePresence8;
    private javax.swing.JTextField edtSerial1;
    private javax.swing.JTextField edtSerial2;
    private javax.swing.JTextField edtSerial3;
    private javax.swing.JTextField edtSerial4;
    private javax.swing.JTextField edtSerial5;
    private javax.swing.JTextField edtSerial6;
    private javax.swing.JTextField edtSerial7;
    private javax.swing.JTextField edtSerial8;
    private javax.swing.JLabel lblGetters;
    private javax.swing.JLabel lblPlace1;
    private javax.swing.JLabel lblPlace2;
    private javax.swing.JLabel lblPlace3;
    private javax.swing.JLabel lblPlace4;
    private javax.swing.JLabel lblPlace5;
    private javax.swing.JLabel lblPlace6;
    private javax.swing.JLabel lblPlace7;
    private javax.swing.JLabel lblPlace8;
    private javax.swing.JLabel lblSerials;
    private javax.swing.JRadioButton radGet11;
    private javax.swing.JRadioButton radGet12;
    private javax.swing.JRadioButton radGet21;
    private javax.swing.JRadioButton radGet22;
    private javax.swing.JRadioButton radGet31;
    private javax.swing.JRadioButton radGet32;
    private javax.swing.JRadioButton radGet41;
    private javax.swing.JRadioButton radGet42;
    private javax.swing.JRadioButton radGet51;
    private javax.swing.JRadioButton radGet52;
    private javax.swing.JRadioButton radGet61;
    private javax.swing.JRadioButton radGet62;
    private javax.swing.JRadioButton radGet71;
    private javax.swing.JRadioButton radGet72;
    private javax.swing.JRadioButton radGet81;
    private javax.swing.JRadioButton radGet82;
    // End of variables declaration//GEN-END:variables
}
