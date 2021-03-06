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
public class TechProcessStep06_1A_Dlg extends javax.swing.JDialog {

    static Logger logger = Logger.getLogger( TechProcessStep06_1A_Dlg.class);
    final private HVV_Admin theApp;
    /**
     * Creates new form TechProcessStep2_1_Dlg
     */
    public TechProcessStep06_1A_Dlg( HVV_Admin app, java.awt.Frame parent, boolean modal) {
        super( parent, modal);
        theApp = app;
        initComponents();
        
        boolean bPresent;
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1);
        if( !bPresent) {
            edt_1000_Dev1.setText(""); edt_1000_Dev1.setEnabled( false);
            edt_1100_Dev1.setText(""); edt_1100_Dev1.setEnabled( false);
            edt_1200_Dev1.setText(""); edt_1200_Dev1.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2);
        if( !bPresent) {
            edt_1000_Dev2.setText(""); edt_1000_Dev2.setEnabled( false);
            edt_1100_Dev2.setText(""); edt_1100_Dev2.setEnabled( false);
            edt_1200_Dev2.setText(""); edt_1200_Dev2.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3);
        if( !bPresent) {
            edt_1000_Dev3.setText(""); edt_1000_Dev3.setEnabled( false);
            edt_1100_Dev3.setText(""); edt_1100_Dev3.setEnabled( false);
            edt_1200_Dev3.setText(""); edt_1200_Dev3.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4);
        if( !bPresent) {
            edt_1000_Dev4.setText(""); edt_1000_Dev4.setEnabled( false);
            edt_1100_Dev4.setText(""); edt_1100_Dev4.setEnabled( false);
            edt_1200_Dev4.setText(""); edt_1200_Dev4.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5);
        if( !bPresent) {
            edt_1000_Dev5.setText(""); edt_1000_Dev5.setEnabled( false);
            edt_1100_Dev5.setText(""); edt_1100_Dev5.setEnabled( false);
            edt_1200_Dev5.setText(""); edt_1200_Dev5.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6);
        if( !bPresent) {
            edt_1000_Dev6.setText(""); edt_1000_Dev6.setEnabled( false);
            edt_1100_Dev6.setText(""); edt_1100_Dev6.setEnabled( false);
            edt_1200_Dev6.setText(""); edt_1200_Dev6.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7);
        if( !bPresent) {
            edt_1000_Dev7.setText(""); edt_1000_Dev7.setEnabled( false);
            edt_1100_Dev7.setText(""); edt_1100_Dev7.setEnabled( false);
            edt_1200_Dev7.setText(""); edt_1200_Dev7.setEnabled( false);
        }
        
        bPresent = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8);
        if( !bPresent) {
            edt_1000_Dev8.setText(""); edt_1000_Dev8.setEnabled( false);
            edt_1100_Dev8.setText(""); edt_1100_Dev8.setEnabled( false);
            edt_1200_Dev8.setText(""); edt_1200_Dev8.setEnabled( false);
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
        lblTitle1000mcA = new javax.swing.JLabel();
        lblTitle1100 = new javax.swing.JLabel();
        lblTitle1200mcA = new javax.swing.JLabel();
        edt_1000_Dev1 = new javax.swing.JTextField();
        edt_1100_Dev1 = new javax.swing.JTextField();
        edt_1200_Dev1 = new javax.swing.JTextField();
        edt_1000_Dev2 = new javax.swing.JTextField();
        edt_1100_Dev2 = new javax.swing.JTextField();
        edt_1200_Dev2 = new javax.swing.JTextField();
        edt_1000_Dev3 = new javax.swing.JTextField();
        edt_1100_Dev3 = new javax.swing.JTextField();
        edt_1200_Dev3 = new javax.swing.JTextField();
        edt_1000_Dev4 = new javax.swing.JTextField();
        edt_1100_Dev4 = new javax.swing.JTextField();
        edt_1200_Dev4 = new javax.swing.JTextField();
        edt_1000_Dev5 = new javax.swing.JTextField();
        edt_1100_Dev5 = new javax.swing.JTextField();
        edt_1200_Dev5 = new javax.swing.JTextField();
        edt_1000_Dev6 = new javax.swing.JTextField();
        edt_1100_Dev6 = new javax.swing.JTextField();
        edt_1200_Dev6 = new javax.swing.JTextField();
        edt_1000_Dev7 = new javax.swing.JTextField();
        edt_1100_Dev7 = new javax.swing.JTextField();
        edt_1200_Dev7 = new javax.swing.JTextField();
        edt_1000_Dev8 = new javax.swing.JTextField();
        edt_1100_Dev8 = new javax.swing.JTextField();
        edt_1200_Dev8 = new javax.swing.JTextField();
        btnContinue = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("6.1 Снятие вольт-амперной-характеристики на анодах");
        setMaximumSize(new java.awt.Dimension(1100, 330));
        setMinimumSize(new java.awt.Dimension(1100, 330));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setResizable(false);
        getContentPane().setLayout(null);

        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Снимите Вольт-Амперную Характеристики на анодах обрабатываемых приборов.");
        lblTitle.setToolTipText("");
        getContentPane().add(lblTitle);
        lblTitle.setBounds(10, 10, 1060, 30);

        lblPlace1.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace1.setText("1");
        lblPlace1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace1);
        lblPlace1.setBounds(120, 60, 110, 40);

        lblPlace2.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace2.setText("2");
        lblPlace2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace2);
        lblPlace2.setBounds(240, 60, 110, 40);

        lblPlace3.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace3.setText("3");
        lblPlace3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace3);
        lblPlace3.setBounds(360, 60, 110, 40);

        lblPlace4.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace4.setText("4");
        lblPlace4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace4);
        lblPlace4.setBounds(480, 60, 110, 40);

        lblPlace5.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace5.setText("5");
        lblPlace5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace5);
        lblPlace5.setBounds(600, 60, 110, 40);

        lblPlace6.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace6.setText("6");
        lblPlace6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace6);
        lblPlace6.setBounds(720, 60, 110, 40);

        lblPlace7.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace7.setText("7");
        lblPlace7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace7);
        lblPlace7.setBounds(840, 60, 110, 40);

        lblPlace8.setFont(new java.awt.Font("Cantarell", 1, 24)); // NOI18N
        lblPlace8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlace8.setText("8");
        lblPlace8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        getContentPane().add(lblPlace8);
        lblPlace8.setBounds(960, 60, 110, 40);

        lblTitle1000mcA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1000mcA.setText("1000 мкА");
        getContentPane().add(lblTitle1000mcA);
        lblTitle1000mcA.setBounds(10, 110, 110, 30);

        lblTitle1100.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1100.setText("1100 мкА");
        getContentPane().add(lblTitle1100);
        lblTitle1100.setBounds(10, 150, 110, 30);

        lblTitle1200mcA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle1200mcA.setText("1200 мкА");
        getContentPane().add(lblTitle1200mcA);
        lblTitle1200mcA.setBounds(10, 190, 110, 30);

        edt_1000_Dev1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev1.setText("0");
        edt_1000_Dev1.setNextFocusableComponent(edt_1100_Dev1);
        edt_1000_Dev1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev1FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev1);
        edt_1000_Dev1.setBounds(120, 110, 110, 26);

        edt_1100_Dev1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev1.setText("0");
        edt_1100_Dev1.setNextFocusableComponent(edt_1200_Dev1);
        edt_1100_Dev1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev1FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev1);
        edt_1100_Dev1.setBounds(120, 150, 110, 26);

        edt_1200_Dev1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev1.setText("0");
        edt_1200_Dev1.setNextFocusableComponent(edt_1000_Dev2);
        edt_1200_Dev1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev1FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev1);
        edt_1200_Dev1.setBounds(120, 190, 110, 26);

        edt_1000_Dev2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev2.setText("0");
        edt_1000_Dev2.setNextFocusableComponent(edt_1100_Dev2);
        edt_1000_Dev2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev2FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev2);
        edt_1000_Dev2.setBounds(240, 110, 110, 26);

        edt_1100_Dev2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev2.setText("0");
        edt_1100_Dev2.setNextFocusableComponent(edt_1200_Dev2);
        edt_1100_Dev2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev2FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev2);
        edt_1100_Dev2.setBounds(240, 150, 110, 26);

        edt_1200_Dev2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev2.setText("0");
        edt_1200_Dev2.setNextFocusableComponent(edt_1000_Dev3);
        edt_1200_Dev2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev2FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev2);
        edt_1200_Dev2.setBounds(240, 190, 110, 26);

        edt_1000_Dev3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev3.setText("0");
        edt_1000_Dev3.setNextFocusableComponent(edt_1100_Dev3);
        edt_1000_Dev3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev3FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev3);
        edt_1000_Dev3.setBounds(360, 110, 110, 26);

        edt_1100_Dev3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev3.setText("0");
        edt_1100_Dev3.setNextFocusableComponent(edt_1200_Dev3);
        edt_1100_Dev3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev3FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev3);
        edt_1100_Dev3.setBounds(360, 150, 110, 26);

        edt_1200_Dev3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev3.setText("0");
        edt_1200_Dev3.setNextFocusableComponent(edt_1000_Dev4);
        edt_1200_Dev3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev3FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev3);
        edt_1200_Dev3.setBounds(360, 190, 110, 26);

        edt_1000_Dev4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev4.setText("0");
        edt_1000_Dev4.setNextFocusableComponent(edt_1100_Dev4);
        edt_1000_Dev4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev4FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev4);
        edt_1000_Dev4.setBounds(480, 110, 110, 26);

        edt_1100_Dev4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev4.setText("0");
        edt_1100_Dev4.setNextFocusableComponent(edt_1200_Dev4);
        edt_1100_Dev4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev4FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev4);
        edt_1100_Dev4.setBounds(480, 150, 110, 26);

        edt_1200_Dev4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev4.setText("0");
        edt_1200_Dev4.setNextFocusableComponent(edt_1000_Dev5);
        edt_1200_Dev4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev4FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev4);
        edt_1200_Dev4.setBounds(480, 190, 110, 26);

        edt_1000_Dev5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev5.setText("0");
        edt_1000_Dev5.setNextFocusableComponent(edt_1100_Dev5);
        edt_1000_Dev5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev5FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev5);
        edt_1000_Dev5.setBounds(600, 110, 110, 26);

        edt_1100_Dev5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev5.setText("0");
        edt_1100_Dev5.setNextFocusableComponent(edt_1200_Dev5);
        edt_1100_Dev5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev5FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev5);
        edt_1100_Dev5.setBounds(600, 150, 110, 26);

        edt_1200_Dev5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev5.setText("0");
        edt_1200_Dev5.setNextFocusableComponent(edt_1000_Dev6);
        edt_1200_Dev5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev5FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev5);
        edt_1200_Dev5.setBounds(600, 190, 110, 26);

        edt_1000_Dev6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev6.setText("0");
        edt_1000_Dev6.setNextFocusableComponent(edt_1100_Dev6);
        edt_1000_Dev6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev6FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev6);
        edt_1000_Dev6.setBounds(720, 110, 110, 26);

        edt_1100_Dev6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev6.setText("0");
        edt_1100_Dev6.setNextFocusableComponent(edt_1200_Dev6);
        edt_1100_Dev6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev6FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev6);
        edt_1100_Dev6.setBounds(720, 150, 110, 26);

        edt_1200_Dev6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev6.setText("0");
        edt_1200_Dev6.setNextFocusableComponent(edt_1000_Dev7);
        edt_1200_Dev6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev6FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev6);
        edt_1200_Dev6.setBounds(720, 190, 110, 26);

        edt_1000_Dev7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev7.setText("0");
        edt_1000_Dev7.setNextFocusableComponent(edt_1100_Dev7);
        edt_1000_Dev7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev7FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev7);
        edt_1000_Dev7.setBounds(840, 110, 110, 26);

        edt_1100_Dev7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev7.setText("0");
        edt_1100_Dev7.setNextFocusableComponent(edt_1200_Dev7);
        edt_1100_Dev7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev7FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev7);
        edt_1100_Dev7.setBounds(840, 150, 110, 26);

        edt_1200_Dev7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev7.setText("0");
        edt_1200_Dev7.setNextFocusableComponent(edt_1000_Dev8);
        edt_1200_Dev7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev7FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev7);
        edt_1200_Dev7.setBounds(840, 190, 110, 26);

        edt_1000_Dev8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1000_Dev8.setText("0");
        edt_1000_Dev8.setNextFocusableComponent(edt_1100_Dev8);
        edt_1000_Dev8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1000_Dev8FocusGained(evt);
            }
        });
        getContentPane().add(edt_1000_Dev8);
        edt_1000_Dev8.setBounds(960, 110, 110, 26);

        edt_1100_Dev8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1100_Dev8.setText("0");
        edt_1100_Dev8.setNextFocusableComponent(edt_1200_Dev8);
        edt_1100_Dev8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1100_Dev8FocusGained(evt);
            }
        });
        getContentPane().add(edt_1100_Dev8);
        edt_1100_Dev8.setBounds(960, 150, 110, 26);

        edt_1200_Dev8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edt_1200_Dev8.setText("0");
        edt_1200_Dev8.setNextFocusableComponent(edt_1000_Dev1);
        edt_1200_Dev8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edt_1200_Dev8FocusGained(evt);
            }
        });
        getContentPane().add(edt_1200_Dev8);
        edt_1200_Dev8.setBounds(960, 190, 110, 26);

        btnContinue.setText("Далее");
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });
        getContentPane().add(btnContinue);
        btnContinue.setBounds(830, 250, 240, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void SavePoint( HashMap map, int nDevice, JTextField edt, String strName) {
        int nVolt = 0;
        try {
            nVolt = new Integer( edt.getText());
        } catch( Exception ex) {
            logger.warn( "При преобразовании " + strName +" для прибора " + nDevice + " произошла Exception");//, ex);
        }
        map.put( nDevice, nVolt);
    }
    
    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        //т.к. этап 6.1 ручной, мы переходим к следующему подэтапу (6.2)
        
        //1000 мкА
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE1, edt_1000_Dev1, "напряжение 1000мкА");
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE2, edt_1000_Dev2, "напряжение 1000мкА");
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE3, edt_1000_Dev3, "напряжение 1000мкА");
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE4, edt_1000_Dev4, "напряжение 1000мкА");
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE5, edt_1000_Dev5, "напряжение 1000мкА");
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE6, edt_1000_Dev6, "напряжение 1000мкА");
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE7, edt_1000_Dev7, "напряжение 1000мкА");
        SavePoint( theApp.m_mapStep6_1_1000mcA, HVV_AdminConstants.DEVICE8, edt_1000_Dev8, "напряжение 1000мкА");
        
        //1100 мкА
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE1, edt_1100_Dev1, "напряжение 1100мкА");
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE2, edt_1100_Dev2, "напряжение 1100мкА");
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE3, edt_1100_Dev3, "напряжение 1100мкА");
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE4, edt_1100_Dev4, "напряжение 1100мкА");
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE5, edt_1100_Dev5, "напряжение 1100мкА");
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE6, edt_1100_Dev6, "напряжение 1100мкА");
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE7, edt_1100_Dev7, "напряжение 1100мкА");
        SavePoint( theApp.m_mapStep6_1_1100mcA, HVV_AdminConstants.DEVICE8, edt_1100_Dev8, "напряжение 1100мкА");
        
        //1200 мкА
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE1, edt_1200_Dev1, "напряжение 1200мкА");
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE2, edt_1200_Dev2, "напряжение 1200мкА");
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE3, edt_1200_Dev3, "напряжение 1200мкА");
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE4, edt_1200_Dev4, "напряжение 1200мкА");
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE5, edt_1200_Dev5, "напряжение 1200мкА");
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE6, edt_1200_Dev6, "напряжение 1200мкА");
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE7, edt_1200_Dev7, "напряжение 1200мкА");
        SavePoint( theApp.m_mapStep6_1_1200mcA, HVV_AdminConstants.DEVICE8, edt_1200_Dev8, "напряжение 1200мкА");
        
        if( theApp.IsStepMapContainsKey( "101")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "101");
            
            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Замеры порогов генерации и погасания");
            info.SetStopP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStopP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStopP7( theApp.GetDoubleFromPoller( "007.01"));
                        
            theApp.NextCurrentStep();

            info = new TechProcessStepInfo( theApp);
                
            info.SetStartDateAsCurrent();
            info.SetStartReportTitle( "Замеры порогов генерации и погасания");
            info.SetStartP5( theApp.GetDoubleFromPoller( "005.01"));
            info.SetStartP6( theApp.GetDoubleFromPoller( "006.01"));
            info.SetStartP7( theApp.GetDoubleFromPoller( "007.01"));
                
            theApp.SaveStepInfo( "102", info, true);
            theApp.SetCurrentStepInProgress( true);
            
            theApp.m_ReportGenerator.Generate();
                
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_pMainWnd.m_pPanel.Reposition();
            
            dispose();
            
            new Timer( 100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ( ( Timer) e.getSource()).stop();
                    theApp.ShowDlg6_2();
                }
            }).start();
        }
        else {
            logger.fatal( "Мы заканчиваем этап 101, а инфы на него до сих пор нет!");
        }
    }//GEN-LAST:event_btnContinueActionPerformed

    public void edt_FocusGained( JTextField edt) {
        edt.setSelectionStart( 0);
        edt.setSelectionEnd( edt.getText().length());
    }
    
    private void edt_1000_Dev1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev1FocusGained
        edt_FocusGained( edt_1000_Dev1);
    }//GEN-LAST:event_edt_1000_Dev1FocusGained

    private void edt_1000_Dev2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev2FocusGained
        edt_FocusGained( edt_1000_Dev2);
    }//GEN-LAST:event_edt_1000_Dev2FocusGained

    private void edt_1000_Dev3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev3FocusGained
        edt_FocusGained( edt_1000_Dev3);
    }//GEN-LAST:event_edt_1000_Dev3FocusGained

    private void edt_1000_Dev4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev4FocusGained
        edt_FocusGained( edt_1000_Dev4);
    }//GEN-LAST:event_edt_1000_Dev4FocusGained

    private void edt_1000_Dev5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev5FocusGained
        edt_FocusGained( edt_1000_Dev5);
    }//GEN-LAST:event_edt_1000_Dev5FocusGained

    private void edt_1000_Dev6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev6FocusGained
        edt_FocusGained( edt_1000_Dev6);
    }//GEN-LAST:event_edt_1000_Dev6FocusGained

    private void edt_1000_Dev7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev7FocusGained
        edt_FocusGained( edt_1000_Dev7);
    }//GEN-LAST:event_edt_1000_Dev7FocusGained

    private void edt_1000_Dev8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1000_Dev8FocusGained
        edt_FocusGained( edt_1000_Dev8);
    }//GEN-LAST:event_edt_1000_Dev8FocusGained

    private void edt_1100_Dev1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev1FocusGained
        edt_FocusGained( edt_1100_Dev1);
    }//GEN-LAST:event_edt_1100_Dev1FocusGained

    private void edt_1100_Dev2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev2FocusGained
        edt_FocusGained( edt_1100_Dev2);
    }//GEN-LAST:event_edt_1100_Dev2FocusGained

    private void edt_1100_Dev3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev3FocusGained
        edt_FocusGained( edt_1100_Dev3);
    }//GEN-LAST:event_edt_1100_Dev3FocusGained

    private void edt_1100_Dev4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev4FocusGained
        edt_FocusGained( edt_1100_Dev4);
    }//GEN-LAST:event_edt_1100_Dev4FocusGained

    private void edt_1100_Dev5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev5FocusGained
        edt_FocusGained( edt_1100_Dev5);
    }//GEN-LAST:event_edt_1100_Dev5FocusGained

    private void edt_1100_Dev6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev6FocusGained
        edt_FocusGained( edt_1100_Dev6);
    }//GEN-LAST:event_edt_1100_Dev6FocusGained

    private void edt_1100_Dev7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev7FocusGained
        edt_FocusGained( edt_1100_Dev7);
    }//GEN-LAST:event_edt_1100_Dev7FocusGained

    private void edt_1100_Dev8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1100_Dev8FocusGained
        edt_FocusGained( edt_1100_Dev8);
    }//GEN-LAST:event_edt_1100_Dev8FocusGained

    private void edt_1200_Dev1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev1FocusGained
        edt_FocusGained( edt_1200_Dev1);
    }//GEN-LAST:event_edt_1200_Dev1FocusGained

    private void edt_1200_Dev2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev2FocusGained
        edt_FocusGained( edt_1200_Dev2);
    }//GEN-LAST:event_edt_1200_Dev2FocusGained

    private void edt_1200_Dev3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev3FocusGained
        edt_FocusGained( edt_1200_Dev3);
    }//GEN-LAST:event_edt_1200_Dev3FocusGained

    private void edt_1200_Dev4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev4FocusGained
        edt_FocusGained( edt_1200_Dev4);
    }//GEN-LAST:event_edt_1200_Dev4FocusGained

    private void edt_1200_Dev5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev5FocusGained
        edt_FocusGained( edt_1200_Dev5);
    }//GEN-LAST:event_edt_1200_Dev5FocusGained

    private void edt_1200_Dev6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev6FocusGained
        edt_FocusGained( edt_1200_Dev6);
    }//GEN-LAST:event_edt_1200_Dev6FocusGained

    private void edt_1200_Dev7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev7FocusGained
        edt_FocusGained( edt_1200_Dev7);
    }//GEN-LAST:event_edt_1200_Dev7FocusGained

    private void edt_1200_Dev8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edt_1200_Dev8FocusGained
        edt_FocusGained( edt_1200_Dev8);
    }//GEN-LAST:event_edt_1200_Dev8FocusGained

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
            java.util.logging.Logger.getLogger(TechProcessStep06_1A_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep06_1A_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep06_1A_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TechProcessStep06_1A_Dlg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                TechProcessStep06_1A_Dlg dialog = new TechProcessStep06_1A_Dlg( null, new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField edt_1000_Dev1;
    private javax.swing.JTextField edt_1000_Dev2;
    private javax.swing.JTextField edt_1000_Dev3;
    private javax.swing.JTextField edt_1000_Dev4;
    private javax.swing.JTextField edt_1000_Dev5;
    private javax.swing.JTextField edt_1000_Dev6;
    private javax.swing.JTextField edt_1000_Dev7;
    private javax.swing.JTextField edt_1000_Dev8;
    private javax.swing.JTextField edt_1100_Dev1;
    private javax.swing.JTextField edt_1100_Dev2;
    private javax.swing.JTextField edt_1100_Dev3;
    private javax.swing.JTextField edt_1100_Dev4;
    private javax.swing.JTextField edt_1100_Dev5;
    private javax.swing.JTextField edt_1100_Dev6;
    private javax.swing.JTextField edt_1100_Dev7;
    private javax.swing.JTextField edt_1100_Dev8;
    private javax.swing.JTextField edt_1200_Dev1;
    private javax.swing.JTextField edt_1200_Dev2;
    private javax.swing.JTextField edt_1200_Dev3;
    private javax.swing.JTextField edt_1200_Dev4;
    private javax.swing.JTextField edt_1200_Dev5;
    private javax.swing.JTextField edt_1200_Dev6;
    private javax.swing.JTextField edt_1200_Dev7;
    private javax.swing.JTextField edt_1200_Dev8;
    private javax.swing.JLabel lblPlace1;
    private javax.swing.JLabel lblPlace2;
    private javax.swing.JLabel lblPlace3;
    private javax.swing.JLabel lblPlace4;
    private javax.swing.JLabel lblPlace5;
    private javax.swing.JLabel lblPlace6;
    private javax.swing.JLabel lblPlace7;
    private javax.swing.JLabel lblPlace8;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1000mcA;
    private javax.swing.JLabel lblTitle1100;
    private javax.swing.JLabel lblTitle1200mcA;
    // End of variables declaration//GEN-END:variables
}
