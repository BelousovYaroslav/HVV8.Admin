package hvv_admin.dialogs.step06;

import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JTextField;
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
public class TechProcessStep06_2_Dlg extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger( TechProcessStep06_2_Dlg.class);
    final private HVV_Admin theApp;
    /**
     * Creates new form TechProcessStep2_1_Dlg
     */
    public TechProcessStep06_2_Dlg( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        theApp = app;
        initComponents();
        
        boolean bPresent;
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1);
        if( !bPresent) {
            edt_Gen_Dev1.setText("");  edt_Gen_Dev1.setEnabled( false);
            edt_Ext_DevA1.setText(""); edt_Ext_DevA1.setEnabled( false);
            edt_Ext_DevT1.setText(""); edt_Ext_DevT1.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2);
        if( !bPresent) {
            edt_Gen_Dev2.setText("");  edt_Gen_Dev2.setEnabled( false);
            edt_Ext_DevA2.setText(""); edt_Ext_DevA2.setEnabled( false);
            edt_Ext_DevT2.setText(""); edt_Ext_DevT2.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3);
        if( !bPresent) {
            edt_Gen_Dev3.setText("");  edt_Gen_Dev3.setEnabled( false);
            edt_Ext_DevA3.setText(""); edt_Ext_DevA3.setEnabled( false);
            edt_Ext_DevT3.setText(""); edt_Ext_DevT3.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4);
        if( !bPresent) {
            edt_Gen_Dev4.setText("");  edt_Gen_Dev4.setEnabled( false);
            edt_Ext_DevA4.setText(""); edt_Ext_DevA4.setEnabled( false);
            edt_Ext_DevT4.setText(""); edt_Ext_DevT4.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5);
        if( !bPresent) {
            edt_Gen_Dev5.setText("");  edt_Gen_Dev5.setEnabled( false);
            edt_Ext_DevA5.setText(""); edt_Ext_DevA5.setEnabled( false);
            edt_Ext_DevT5.setText(""); edt_Ext_DevT5.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6);
        if( !bPresent) {
            edt_Gen_Dev6.setText("");  edt_Gen_Dev6.setEnabled( false);
            edt_Ext_DevA6.setText(""); edt_Ext_DevA6.setEnabled( false);
            edt_Ext_DevT6.setText(""); edt_Ext_DevT6.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7);
        if( !bPresent) {
            edt_Gen_Dev7.setText("");  edt_Gen_Dev7.setEnabled( false);
            edt_Ext_DevA7.setText(""); edt_Ext_DevA7.setEnabled( false);
            edt_Ext_DevT7.setText(""); edt_Ext_DevT7.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8);
        if( !bPresent) {
            edt_Gen_Dev8.setText("");  edt_Gen_Dev8.setEnabled( false);
            edt_Ext_DevA8.setText(""); edt_Ext_DevA8.setEnabled( false);
            edt_Ext_DevT8.setText(""); edt_Ext_DevT8.setEnabled( false);
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

        lblTitle = new javax.swing.JLabel();
        lblPlace1 = new javax.swing.JLabel();
        lblPlace2 = new javax.swing.JLabel();
        lblPlace3 = new javax.swing.JLabel();
        lblPlace4 = new javax.swing.JLabel();
        lblPlace5 = new javax.swing.JLabel();
        lblPlace6 = new javax.swing.JLabel();
        lblPlace7 = new javax.swing.JLabel();
        lblPlace8 = new javax.swing.JLabel();
        lblTitleGeneration = new javax.swing.JLabel();
        edt_Gen_Dev1 = new javax.swing.JTextField();
        edt_Gen_Dev2 = new javax.swing.JTextField();
        edt_Gen_Dev3 = new javax.swing.JTextField();
        edt_Gen_Dev4 = new javax.swing.JTextField();
        edt_Gen_Dev5 = new javax.swing.JTextField();
        edt_Gen_Dev6 = new javax.swing.JTextField();
        edt_Gen_Dev7 = new javax.swing.JTextField();
        edt_Gen_Dev8 = new javax.swing.JTextField();
        lblTitleExtinction = new javax.swing.JLabel();
        edt_Ext_DevA1 = new javax.swing.JTextField();
        edt_Ext_DevA2 = new javax.swing.JTextField();
        edt_Ext_DevA3 = new javax.swing.JTextField();
        edt_Ext_DevA4 = new javax.swing.JTextField();
        edt_Ext_DevA5 = new javax.swing.JTextField();
        edt_Ext_DevA6 = new javax.swing.JTextField();
        edt_Ext_DevA7 = new javax.swing.JTextField();
        edt_Ext_DevA8 = new javax.swing.JTextField();
        btnContinue = new javax.swing.JButton();
        lblTitleExtinction1 = new javax.swing.JLabel();
        edt_Ext_DevT1 = new javax.swing.JTextField();
        edt_Ext_DevT2 = new javax.swing.JTextField();
        edt_Ext_DevT3 = new javax.swing.JTextField();
        edt_Ext_DevT4 = new javax.swing.JTextField();
        edt_Ext_DevT5 = new javax.swing.JTextField();
        edt_Ext_DevT6 = new javax.swing.JTextField();
        edt_Ext_DevT7 = new javax.swing.JTextField();
        edt_Ext_DevT8 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("6.2 Внесение пороговых токов");
        setMaximumSize(new java.awt.Dimension(1100, 370));
        setMinimumSize(new java.awt.Dimension(1100, 370));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Замерьте токи, при которых испытуемые приборы прекращают генерацию и гаснут.");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(10, 10, 1060, 30);

        lblPlace1.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace1.setText("1");
        lblPlace1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace1);
        lblPlace1.setBounds(120, 60, 110, 27);

        lblPlace2.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace2.setText("2");
        lblPlace2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace2);
        lblPlace2.setBounds(240, 60, 110, 27);

        lblPlace3.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace3.setText("3");
        lblPlace3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace3);
        lblPlace3.setBounds(360, 60, 110, 27);

        lblPlace4.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace4.setText("4");
        lblPlace4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace4);
        lblPlace4.setBounds(480, 60, 110, 27);

        lblPlace5.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace5.setText("5");
        lblPlace5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace5);
        lblPlace5.setBounds(600, 60, 110, 27);

        lblPlace6.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace6.setText("6");
        lblPlace6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace6);
        lblPlace6.setBounds(720, 60, 110, 27);

        lblPlace7.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace7.setText("7");
        lblPlace7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace7);
        lblPlace7.setBounds(840, 60, 110, 27);

        lblPlace8.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace8.setText("8");
        lblPlace8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace8);
        lblPlace8.setBounds(960, 60, 110, 27);

        lblTitleGeneration.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleGeneration.setText("<html>Порог<br>генерации, мА</html>");
        getContentPane().add(lblTitleGeneration);
        lblTitleGeneration.setBounds(10, 100, 110, 40);

        edt_Gen_Dev1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev1.setText("0.000");
        edt_Gen_Dev1.setNextFocusableComponent(edt_Ext_DevA1);
        edt_Gen_Dev1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev1FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev1);
        edt_Gen_Dev1.setBounds(120, 100, 110, 40);

        edt_Gen_Dev2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev2.setText("0.000");
        edt_Gen_Dev2.setNextFocusableComponent(edt_Ext_DevA2);
        edt_Gen_Dev2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev2FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev2);
        edt_Gen_Dev2.setBounds(240, 100, 110, 40);

        edt_Gen_Dev3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev3.setText("0.000");
        edt_Gen_Dev3.setNextFocusableComponent(edt_Ext_DevA3);
        edt_Gen_Dev3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev3FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev3);
        edt_Gen_Dev3.setBounds(360, 100, 110, 40);

        edt_Gen_Dev4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev4.setText("0.000");
        edt_Gen_Dev4.setNextFocusableComponent(edt_Ext_DevA4);
        edt_Gen_Dev4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev4FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev4);
        edt_Gen_Dev4.setBounds(480, 100, 110, 40);

        edt_Gen_Dev5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev5.setText("0.000");
        edt_Gen_Dev5.setNextFocusableComponent(edt_Ext_DevA5);
        edt_Gen_Dev5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev5FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev5);
        edt_Gen_Dev5.setBounds(600, 100, 110, 40);

        edt_Gen_Dev6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev6.setText("0.000");
        edt_Gen_Dev6.setNextFocusableComponent(edt_Ext_DevA6);
        edt_Gen_Dev6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev6FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev6);
        edt_Gen_Dev6.setBounds(720, 100, 110, 40);

        edt_Gen_Dev7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev7.setText("0.000");
        edt_Gen_Dev7.setNextFocusableComponent(edt_Ext_DevA7);
        edt_Gen_Dev7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev7FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev7);
        edt_Gen_Dev7.setBounds(840, 100, 110, 40);

        edt_Gen_Dev8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Gen_Dev8.setText("0.000");
        edt_Gen_Dev8.setNextFocusableComponent(edt_Ext_DevA8);
        edt_Gen_Dev8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Gen_Dev8FocusGained(evt);
            }
        });
        getContentPane().add(edt_Gen_Dev8);
        edt_Gen_Dev8.setBounds(960, 100, 110, 40);

        lblTitleExtinction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleExtinction.setText("<html>Порог<br>погасания<br>анодов, мА</html>");
        lblTitleExtinction.setToolTipText("");
        getContentPane().add(lblTitleExtinction);
        lblTitleExtinction.setBounds(10, 150, 110, 60);

        edt_Ext_DevA1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA1.setText("0.000");
        edt_Ext_DevA1.setNextFocusableComponent(edt_Ext_DevT1);
        edt_Ext_DevA1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA1FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA1);
        edt_Ext_DevA1.setBounds(120, 160, 110, 40);

        edt_Ext_DevA2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA2.setText("0.000");
        edt_Ext_DevA2.setNextFocusableComponent(edt_Ext_DevT2);
        edt_Ext_DevA2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA2FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA2);
        edt_Ext_DevA2.setBounds(240, 160, 110, 40);

        edt_Ext_DevA3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA3.setText("0.000");
        edt_Ext_DevA3.setNextFocusableComponent(edt_Ext_DevT3);
        edt_Ext_DevA3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA3FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA3);
        edt_Ext_DevA3.setBounds(360, 160, 110, 40);

        edt_Ext_DevA4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA4.setText("0.000");
        edt_Ext_DevA4.setNextFocusableComponent(edt_Ext_DevT4);
        edt_Ext_DevA4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA4FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA4);
        edt_Ext_DevA4.setBounds(480, 160, 110, 40);

        edt_Ext_DevA5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA5.setText("0.000");
        edt_Ext_DevA5.setNextFocusableComponent(edt_Ext_DevT5);
        edt_Ext_DevA5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA5FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA5);
        edt_Ext_DevA5.setBounds(600, 160, 110, 40);

        edt_Ext_DevA6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA6.setText("0.000");
        edt_Ext_DevA6.setNextFocusableComponent(edt_Ext_DevT6);
        edt_Ext_DevA6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA6FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA6);
        edt_Ext_DevA6.setBounds(720, 160, 110, 40);

        edt_Ext_DevA7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA7.setText("0.000");
        edt_Ext_DevA7.setNextFocusableComponent(edt_Ext_DevT7);
        edt_Ext_DevA7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA7FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA7);
        edt_Ext_DevA7.setBounds(840, 160, 110, 40);

        edt_Ext_DevA8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevA8.setText("0.000");
        edt_Ext_DevA8.setNextFocusableComponent(edt_Ext_DevT8);
        edt_Ext_DevA8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevA8FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevA8);
        edt_Ext_DevA8.setBounds(960, 160, 110, 40);

        btnContinue.setText("Далее");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        getContentPane().add(btnContinue);
        btnContinue.setBounds(830, 280, 240, 40);

        lblTitleExtinction1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitleExtinction1.setText("<html>Порог<br>погасания<br>штенгелей, мА</html>");
        lblTitleExtinction1.setToolTipText("");
        getContentPane().add(lblTitleExtinction1);
        lblTitleExtinction1.setBounds(10, 210, 110, 60);

        edt_Ext_DevT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT1.setText("0.000");
        edt_Ext_DevT1.setNextFocusableComponent(edt_Gen_Dev2);
        edt_Ext_DevT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT1FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT1);
        edt_Ext_DevT1.setBounds(120, 220, 110, 40);

        edt_Ext_DevT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT2.setText("0.000");
        edt_Ext_DevT2.setNextFocusableComponent(edt_Gen_Dev3);
        edt_Ext_DevT2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT2FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT2);
        edt_Ext_DevT2.setBounds(240, 220, 110, 40);

        edt_Ext_DevT3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT3.setText("0.000");
        edt_Ext_DevT3.setNextFocusableComponent(edt_Gen_Dev4);
        edt_Ext_DevT3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT3FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT3);
        edt_Ext_DevT3.setBounds(360, 220, 110, 40);

        edt_Ext_DevT4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT4.setText("0.000");
        edt_Ext_DevT4.setNextFocusableComponent(edt_Gen_Dev5);
        edt_Ext_DevT4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT4FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT4);
        edt_Ext_DevT4.setBounds(480, 220, 110, 40);

        edt_Ext_DevT5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT5.setText("0.000");
        edt_Ext_DevT5.setNextFocusableComponent(edt_Gen_Dev6);
        edt_Ext_DevT5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT5FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT5);
        edt_Ext_DevT5.setBounds(600, 220, 110, 40);

        edt_Ext_DevT6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT6.setText("0.000");
        edt_Ext_DevT6.setNextFocusableComponent(edt_Gen_Dev7);
        edt_Ext_DevT6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT6FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT6);
        edt_Ext_DevT6.setBounds(720, 220, 110, 40);

        edt_Ext_DevT7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT7.setText("0.000");
        edt_Ext_DevT7.setNextFocusableComponent(edt_Gen_Dev8);
        edt_Ext_DevT7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT7FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT7);
        edt_Ext_DevT7.setBounds(840, 220, 110, 40);

        edt_Ext_DevT8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_Ext_DevT8.setText("0.000");
        edt_Ext_DevT8.setNextFocusableComponent(edt_Gen_Dev1);
        edt_Ext_DevT8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_Ext_DevT8FocusGained(evt);
            }
        });
        getContentPane().add(edt_Ext_DevT8);
        edt_Ext_DevT8.setBounds(960, 220, 110, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void SavePoint( HashMap map, int nDevice, JTextField edt, String name) {
        Double dblCurr = new Double( 0.);
        try {
            String strValue =  edt.getText();
            strValue = strValue.replace( ',', '.');
            dblCurr = new Double( strValue);
        } catch( Exception ex) {
            logger.warn( "При преобразовании " + name + " для прибора " + (nDevice) + " произошла Exception");//, ex);
        }
        map.put( nDevice, dblCurr);
    }
    
    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        //т.к. этап 6.2 ручной, мы переходим к следующему ручному подэтапу (6.1)
        
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE1, edt_Gen_Dev1, "ток генерации");
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE2, edt_Gen_Dev2, "ток генерации");
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE3, edt_Gen_Dev3, "ток генерации");
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE4, edt_Gen_Dev4, "ток генерации");
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE5, edt_Gen_Dev5, "ток генерации");
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE6, edt_Gen_Dev6, "ток генерации");
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE7, edt_Gen_Dev7, "ток генерации");
        SavePoint( theApp.m_mapStep6_2_LasThreshold, HVV_AdminConstants.DEVICE8, edt_Gen_Dev8, "ток генерации");
        
        
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE1, edt_Ext_DevA1, "ток погасания анода");
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE2, edt_Ext_DevA2, "ток погасания анода");
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE3, edt_Ext_DevA3, "ток погасания анода");
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE4, edt_Ext_DevA4, "ток погасания анода");
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE5, edt_Ext_DevA5, "ток погасания анода");
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE6, edt_Ext_DevA6, "ток погасания анода");
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE7, edt_Ext_DevA7, "ток погасания анода");
        SavePoint( theApp.m_mapStep6_2_ExtAn, HVV_AdminConstants.DEVICE8, edt_Ext_DevA8, "ток погасания анода");
        
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE1, edt_Ext_DevT1, "ток погасания штенгеля");
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE2, edt_Ext_DevT2, "ток погасания штенгеля");
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE3, edt_Ext_DevT3, "ток погасания штенгеля");
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE4, edt_Ext_DevT4, "ток погасания штенгеля");
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE5, edt_Ext_DevT5, "ток погасания штенгеля");
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE6, edt_Ext_DevT6, "ток погасания штенгеля");
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE7, edt_Ext_DevT7, "ток погасания штенгеля");
        SavePoint( theApp.m_mapStep6_2_ExtTu, HVV_AdminConstants.DEVICE8, edt_Ext_DevT8, "ток погасания штенгеля");
        
        if( theApp.IsStepMapContainsKey( "102")) {
            TechProcessStepInfo info = theApp.GetStepInfo("102");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Замеры порогов генерации и погасания");
            info.SetStopP5( theApp.GetFromPoller( "005.01"));
            info.SetStopP6( theApp.GetFromPoller( "006.01"));
            info.SetStopP7( theApp.GetFromPoller( "007.01"));
            
            theApp.NextCurrentStep();

            info = new TechProcessStepInfo( theApp);
                
            info.SetStartDateAsCurrent();
            info.SetStartReportTitle( "Внесение комментариев");
            info.SetStartP5( theApp.GetFromPoller( "005.01"));
            info.SetStartP6( theApp.GetFromPoller( "006.01"));
            info.SetStartP7( theApp.GetFromPoller( "007.01"));
                
            theApp.SaveStepInfo( "103", info, true);
            theApp.SetCurrentStepInProgress( true);
            
            theApp.m_ReportGenerator.Generate();
                
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();
            
            dispose();
            new Timer( 100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ( ( Timer) e.getSource()).stop();
                    theApp.ShowDlg6_3();
                }
            }).start();
            
        }
        else {
            logger.fatal( "Мы заканчиваем этап 102, а инфы на него до сих пор нет!");
        }
        
        dispose();
    }//GEN-LAST:event_btnContinueActionPerformed

    public void edt_FocusGained( JTextField edt) {
        edt.setSelectionStart( 0);
        edt.setSelectionEnd( edt.getText().length());
    }
    
    private void edt_Gen_Dev1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev1FocusGained
        edt_FocusGained( edt_Gen_Dev1);
    }//GEN-LAST:event_edt_Gen_Dev1FocusGained

    private void edt_Gen_Dev2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev2FocusGained
        edt_FocusGained( edt_Gen_Dev2);
    }//GEN-LAST:event_edt_Gen_Dev2FocusGained

    private void edt_Gen_Dev3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev3FocusGained
        edt_FocusGained( edt_Gen_Dev3);
    }//GEN-LAST:event_edt_Gen_Dev3FocusGained

    private void edt_Gen_Dev4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev4FocusGained
        edt_FocusGained( edt_Gen_Dev4);
    }//GEN-LAST:event_edt_Gen_Dev4FocusGained

    private void edt_Gen_Dev5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev5FocusGained
        edt_FocusGained( edt_Gen_Dev5);
    }//GEN-LAST:event_edt_Gen_Dev5FocusGained

    private void edt_Gen_Dev6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev6FocusGained
        edt_FocusGained( edt_Gen_Dev6);
    }//GEN-LAST:event_edt_Gen_Dev6FocusGained

    private void edt_Gen_Dev7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev7FocusGained
        edt_FocusGained( edt_Gen_Dev7);
    }//GEN-LAST:event_edt_Gen_Dev7FocusGained

    private void edt_Gen_Dev8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Gen_Dev8FocusGained
        edt_FocusGained( edt_Gen_Dev8);
    }//GEN-LAST:event_edt_Gen_Dev8FocusGained

    private void edt_Ext_DevA1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA1FocusGained
        edt_FocusGained( edt_Ext_DevA1);
    }//GEN-LAST:event_edt_Ext_DevA1FocusGained

    private void edt_Ext_DevA2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA2FocusGained
        edt_FocusGained( edt_Ext_DevA2);
    }//GEN-LAST:event_edt_Ext_DevA2FocusGained

    private void edt_Ext_DevA3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA3FocusGained
        edt_FocusGained( edt_Ext_DevA3);
    }//GEN-LAST:event_edt_Ext_DevA3FocusGained

    private void edt_Ext_DevA4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA4FocusGained
        edt_FocusGained( edt_Ext_DevA4);
    }//GEN-LAST:event_edt_Ext_DevA4FocusGained

    private void edt_Ext_DevA5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA5FocusGained
        edt_FocusGained( edt_Ext_DevA5);
    }//GEN-LAST:event_edt_Ext_DevA5FocusGained

    private void edt_Ext_DevA6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA6FocusGained
        edt_FocusGained( edt_Ext_DevA6);
    }//GEN-LAST:event_edt_Ext_DevA6FocusGained

    private void edt_Ext_DevA7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA7FocusGained
        edt_FocusGained( edt_Ext_DevA7);
    }//GEN-LAST:event_edt_Ext_DevA7FocusGained

    private void edt_Ext_DevA8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevA8FocusGained
        edt_FocusGained( edt_Ext_DevA8);
    }//GEN-LAST:event_edt_Ext_DevA8FocusGained

    private void edt_Ext_DevT1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT1FocusGained
        edt_FocusGained( edt_Ext_DevT1);
    }//GEN-LAST:event_edt_Ext_DevT1FocusGained

    private void edt_Ext_DevT2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT2FocusGained
        edt_FocusGained( edt_Ext_DevT2);
    }//GEN-LAST:event_edt_Ext_DevT2FocusGained

    private void edt_Ext_DevT3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT3FocusGained
        edt_FocusGained( edt_Ext_DevT3);
    }//GEN-LAST:event_edt_Ext_DevT3FocusGained

    private void edt_Ext_DevT4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT4FocusGained
        edt_FocusGained( edt_Ext_DevT4);
    }//GEN-LAST:event_edt_Ext_DevT4FocusGained

    private void edt_Ext_DevT5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT5FocusGained
        edt_FocusGained( edt_Ext_DevT5);
    }//GEN-LAST:event_edt_Ext_DevT5FocusGained

    private void edt_Ext_DevT6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT6FocusGained
        edt_FocusGained( edt_Ext_DevT6);
    }//GEN-LAST:event_edt_Ext_DevT6FocusGained

    private void edt_Ext_DevT7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT7FocusGained
        edt_FocusGained( edt_Ext_DevT7);
    }//GEN-LAST:event_edt_Ext_DevT7FocusGained

    private void edt_Ext_DevT8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_Ext_DevT8FocusGained
        edt_FocusGained( edt_Ext_DevT8);
    }//GEN-LAST:event_edt_Ext_DevT8FocusGained

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
            java.util.logging.Logger.getLogger(TechProcessStep06_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep06_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep06_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep06_2_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                TechProcessStep06_2_Dlg dialog = new TechProcessStep06_2_Dlg( null, new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField edt_Ext_DevA1;
    private javax.swing.JTextField edt_Ext_DevA2;
    private javax.swing.JTextField edt_Ext_DevA3;
    private javax.swing.JTextField edt_Ext_DevA4;
    private javax.swing.JTextField edt_Ext_DevA5;
    private javax.swing.JTextField edt_Ext_DevA6;
    private javax.swing.JTextField edt_Ext_DevA7;
    private javax.swing.JTextField edt_Ext_DevA8;
    private javax.swing.JTextField edt_Ext_DevT1;
    private javax.swing.JTextField edt_Ext_DevT2;
    private javax.swing.JTextField edt_Ext_DevT3;
    private javax.swing.JTextField edt_Ext_DevT4;
    private javax.swing.JTextField edt_Ext_DevT5;
    private javax.swing.JTextField edt_Ext_DevT6;
    private javax.swing.JTextField edt_Ext_DevT7;
    private javax.swing.JTextField edt_Ext_DevT8;
    private javax.swing.JTextField edt_Gen_Dev1;
    private javax.swing.JTextField edt_Gen_Dev2;
    private javax.swing.JTextField edt_Gen_Dev3;
    private javax.swing.JTextField edt_Gen_Dev4;
    private javax.swing.JTextField edt_Gen_Dev5;
    private javax.swing.JTextField edt_Gen_Dev6;
    private javax.swing.JTextField edt_Gen_Dev7;
    private javax.swing.JTextField edt_Gen_Dev8;
    private javax.swing.JLabel lblPlace1;
    private javax.swing.JLabel lblPlace2;
    private javax.swing.JLabel lblPlace3;
    private javax.swing.JLabel lblPlace4;
    private javax.swing.JLabel lblPlace5;
    private javax.swing.JLabel lblPlace6;
    private javax.swing.JLabel lblPlace7;
    private javax.swing.JLabel lblPlace8;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleExtinction;
    private javax.swing.JLabel lblTitleExtinction1;
    private javax.swing.JLabel lblTitleGeneration;
    // End of variables declaration//GEN-END:variables
}