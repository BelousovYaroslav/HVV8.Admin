/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step10;

import hvv_admin.steps.info.GettersActivationProgram;
import hvv_admin.steps.info.GettersActivationProgramStep;
import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import javax.swing.JLabel;

/**
 *
 * @author yaroslav
 */
public class TechProcessStep10_1_Dlg_A_StepsPanel extends javax.swing.JPanel {

    private final int m_nDevice;
    private final TechProcessStep10_1_Dlg_A m_pParent;
            
    /**
     * Creates new form TechProcessStep08_2_Dlg_3_StepTemplate2
     */
    public TechProcessStep10_1_Dlg_A_StepsPanel( TechProcessStep10_1_Dlg_A pParent, int nDevice) {
        m_pParent = pParent;
        initComponents();
        m_nDevice = nDevice;
    }

    public void RefreshData() {
        JLabel [] arrDurations = { lblDuration_1,  lblDuration_2,  lblDuration_3,  lblDuration_4,
                                   lblDuration_5,  lblDuration_6,  lblDuration_7,  lblDuration_8,
                                   lblDuration_9,  lblDuration_10, lblDuration_11, lblDuration_12,
                                   lblDuration_13, lblDuration_14, lblDuration_15, lblDuration_16,
                                   lblDuration_17, lblDuration_18, lblDuration_19, lblDuration_20,
                                   lblDuration_21, lblDuration_22, lblDuration_23, lblDuration_24,
                                   lblDuration_25, lblDuration_26, lblDuration_27, lblDuration_28,
                                   lblDuration_29, lblDuration_30, lblDuration_31, lblDuration_32,
                                   lblDuration_33};
        
        JLabel [] arrPowers = {    lblPower_1,  lblPower_2,  lblPower_3,  lblPower_4,
                                   lblPower_5,  lblPower_6,  lblPower_7,  lblPower_8,
                                   lblPower_9,  lblPower_10, lblPower_11, lblPower_12,
                                   lblPower_13, lblPower_14, lblPower_15, lblPower_16,
                                   lblPower_17, lblPower_18, lblPower_19, lblPower_20,
                                   lblPower_21, lblPower_22, lblPower_23, lblPower_24,
                                   lblPower_25, lblPower_26, lblPower_27, lblPower_28,
                                   lblPower_29, lblPower_30, lblPower_31, lblPower_32,
                                   lblPower_33};
        
        JLabel [] arrP5start = {   lblP5_start_1,  lblP5_start_2,  lblP5_start_3,  lblP5_start_4,
                                   lblP5_start_5,  lblP5_start_6,  lblP5_start_7,  lblP5_start_8,
                                   lblP5_start_9,  lblP5_start_10, lblP5_start_11, lblP5_start_12,
                                   lblP5_start_13, lblP5_start_14, lblP5_start_15, lblP5_start_16,
                                   lblP5_start_17, lblP5_start_18, lblP5_start_19, lblP5_start_20,
                                   lblP5_start_21, lblP5_start_22, lblP5_start_23, lblP5_start_24,
                                   lblP5_start_25, lblP5_start_26, lblP5_start_27, lblP5_start_28,
                                   lblP5_start_29, lblP5_start_30, lblP5_start_31, lblP5_start_32,
                                   lblP5_start_33};
        
        JLabel [] arrP5max = {     lblP5_max_1,  lblP5_max_2,  lblP5_max_3,  lblP5_max_4,
                                   lblP5_max_5,  lblP5_max_6,  lblP5_max_7,  lblP5_max_8,
                                   lblP5_max_9,  lblP5_max_10, lblP5_max_11, lblP5_max_12,
                                   lblP5_max_13, lblP5_max_14, lblP5_max_15, lblP5_max_16,
                                   lblP5_max_17, lblP5_max_18, lblP5_max_19, lblP5_max_20,
                                   lblP5_max_21, lblP5_max_22, lblP5_max_23, lblP5_max_24,
                                   lblP5_max_25, lblP5_max_26, lblP5_max_27, lblP5_max_28,
                                   lblP5_max_29, lblP5_max_30, lblP5_max_31, lblP5_max_32,
                                   lblP5_max_33};
        
        JLabel [] arrP5last = {    lblP5_last_1,  lblP5_last_2,  lblP5_last_3,  lblP5_last_4,
                                   lblP5_last_5,  lblP5_last_6,  lblP5_last_7,  lblP5_last_8,
                                   lblP5_last_9,  lblP5_last_10, lblP5_last_11, lblP5_last_12,
                                   lblP5_last_13, lblP5_last_14, lblP5_last_15, lblP5_last_16,
                                   lblP5_last_17, lblP5_last_18, lblP5_last_19, lblP5_last_20,
                                   lblP5_last_21, lblP5_last_22, lblP5_last_23, lblP5_last_24,
                                   lblP5_last_25, lblP5_last_26, lblP5_last_27, lblP5_last_28,
                                   lblP5_last_29, lblP5_last_30, lblP5_last_31, lblP5_last_32,
                                   lblP5_last_33};
        
        LinkedList lstActivation = m_pParent.m_lstProgram;
        
        for( int i=0; i<33; i++) {
            if( lstActivation != null) {
                if( i  < lstActivation.size()) {
                    GettersActivationProgramStep step = ( GettersActivationProgramStep) lstActivation.get( i);

                    Font font = m_pParent.GetUsualFont();
                    if( m_pParent.m_nInProgress != -1 && m_pParent.m_nPhase == i)
                        font = m_pParent.GetBoldFont();
                
                    //DURATION
                    arrDurations[i].setFont( font);
                    arrDurations[i].setText( "" + step.GetDuration());

                    //POWER
                    arrPowers[i].setFont( font);
                    arrPowers[i].setText( "" + step.GetPower());

                    //P5_START
                    arrP5start[i].setFont( font);
                    if( step.GetP5_start() != 0.)
                        arrP5start[i].setText( "<html>" + m_pParent.theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_start(), false) + "</html>");
                    else
                        arrP5start[i].setText( "-");
                        

                    //P5_MAX
                    if( m_pParent.m_nPhase == i) {
                        
                        if( m_pParent.m_nInProgress != -1) {  //step.GetP5_max() != 0.) {
                            
                            arrP5max[i].setFont( m_pParent.GetBoldFont());
                            
                            if( m_pParent.btnNextStep.isEnabled()) {
                                //мы на текущем этапе, но мы висим и ждём нажатия "Далее"
                                arrP5max[i].setText( "-");
                            }
                            else {
                                arrP5max[i].setText( "<html>" + m_pParent.theApp.m_ReportGenerator.Gen_NiceDoubleP5( m_pParent.m_rnblP5Watchdog.GetP5Max(), false) + "</html>");
                            }
                        }
                        else {
                            arrP5max[i].setText( "-");
                            arrP5max[i].setFont( m_pParent.GetUsualFont());
                        }
                    }
                    else {
                        arrP5max[i].setFont( m_pParent.GetUsualFont());

                        if( step.GetP5_max() != 0.)
                            arrP5max[i].setText( "<html>" + m_pParent.theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_max(), false) + "</html>");
                        else
                            arrP5max[i].setText( "-");
                    }
                    
                    
                    //P5_LAST
                    arrP5last[i].setFont( font);
                    if( step.GetP5_last() != 0.)
                        arrP5last[i].setText( "<html>" + m_pParent.theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_last(), false) + "</html>");
                    else
                        arrP5last[i].setText( "-");
                }
                else {
                    arrDurations[i].setText(    "-"); arrDurations[i].setFont( m_pParent.GetUsualFont());
                    arrPowers[i].setText(       "-"); arrPowers[i].setFont( m_pParent.GetUsualFont());
                    arrP5start[i].setText(      "-"); arrP5start[i].setFont( m_pParent.GetUsualFont());
                    arrP5max[i].setText(        "-"); arrP5max[i].setFont( m_pParent.GetUsualFont());
                    arrP5last[i].setText(       "-"); arrP5last[i].setFont( m_pParent.GetUsualFont());
                }
            }
            else {
                arrDurations[i].setText(    "-"); arrDurations[i].setFont( m_pParent.GetUsualFont());
                arrPowers[i].setText(       "-"); arrPowers[i].setFont( m_pParent.GetUsualFont());
                arrP5start[i].setText(      "-"); arrP5start[i].setFont( m_pParent.GetUsualFont());
                arrP5max[i].setText(        "-"); arrP5max[i].setFont( m_pParent.GetUsualFont());
                arrP5last[i].setText(       "-"); arrP5last[i].setFont( m_pParent.GetUsualFont());
            }
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

        edtDuration = new javax.swing.JTextField();
        edtPower = new javax.swing.JTextField();
        btnTrailing = new javax.swing.JButton();
        btnAddStep = new javax.swing.JButton();
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
        lblDuration_18 = new javax.swing.JLabel();
        lblPower_18 = new javax.swing.JLabel();
        lblP5_start_18 = new javax.swing.JLabel();
        lblP5_max_18 = new javax.swing.JLabel();
        lblP5_last_18 = new javax.swing.JLabel();
        lblDuration_19 = new javax.swing.JLabel();
        lblPower_19 = new javax.swing.JLabel();
        lblP5_start_19 = new javax.swing.JLabel();
        lblP5_max_19 = new javax.swing.JLabel();
        lblP5_last_19 = new javax.swing.JLabel();
        lblDuration_20 = new javax.swing.JLabel();
        lblPower_20 = new javax.swing.JLabel();
        lblP5_start_20 = new javax.swing.JLabel();
        lblP5_max_20 = new javax.swing.JLabel();
        lblP5_last_20 = new javax.swing.JLabel();
        lblDuration_21 = new javax.swing.JLabel();
        lblPower_21 = new javax.swing.JLabel();
        lblP5_start_21 = new javax.swing.JLabel();
        lblP5_max_21 = new javax.swing.JLabel();
        lblP5_last_21 = new javax.swing.JLabel();
        lblDuration_22 = new javax.swing.JLabel();
        lblPower_22 = new javax.swing.JLabel();
        lblP5_start_22 = new javax.swing.JLabel();
        lblP5_max_22 = new javax.swing.JLabel();
        lblP5_last_22 = new javax.swing.JLabel();
        lblDuration_23 = new javax.swing.JLabel();
        lblPower_23 = new javax.swing.JLabel();
        lblP5_start_23 = new javax.swing.JLabel();
        lblP5_max_23 = new javax.swing.JLabel();
        lblP5_last_23 = new javax.swing.JLabel();
        lblDuration_24 = new javax.swing.JLabel();
        lblPower_24 = new javax.swing.JLabel();
        lblP5_start_24 = new javax.swing.JLabel();
        lblP5_max_24 = new javax.swing.JLabel();
        lblP5_last_24 = new javax.swing.JLabel();
        lblDuration_25 = new javax.swing.JLabel();
        lblPower_25 = new javax.swing.JLabel();
        lblP5_start_25 = new javax.swing.JLabel();
        lblP5_max_25 = new javax.swing.JLabel();
        lblP5_last_25 = new javax.swing.JLabel();
        lblDuration_26 = new javax.swing.JLabel();
        lblPower_26 = new javax.swing.JLabel();
        lblP5_start_26 = new javax.swing.JLabel();
        lblP5_max_26 = new javax.swing.JLabel();
        lblP5_last_26 = new javax.swing.JLabel();
        lblDuration_27 = new javax.swing.JLabel();
        lblPower_27 = new javax.swing.JLabel();
        lblP5_start_27 = new javax.swing.JLabel();
        lblP5_max_27 = new javax.swing.JLabel();
        lblP5_last_27 = new javax.swing.JLabel();
        lblDuration_28 = new javax.swing.JLabel();
        lblPower_28 = new javax.swing.JLabel();
        lblP5_start_28 = new javax.swing.JLabel();
        lblP5_max_28 = new javax.swing.JLabel();
        lblP5_last_28 = new javax.swing.JLabel();
        lblDuration_29 = new javax.swing.JLabel();
        lblPower_29 = new javax.swing.JLabel();
        lblP5_start_29 = new javax.swing.JLabel();
        lblP5_max_29 = new javax.swing.JLabel();
        lblP5_last_29 = new javax.swing.JLabel();
        lblDuration_30 = new javax.swing.JLabel();
        lblPower_30 = new javax.swing.JLabel();
        lblP5_start_30 = new javax.swing.JLabel();
        lblP5_max_30 = new javax.swing.JLabel();
        lblP5_last_30 = new javax.swing.JLabel();
        lblDuration_31 = new javax.swing.JLabel();
        lblPower_31 = new javax.swing.JLabel();
        lblP5_start_31 = new javax.swing.JLabel();
        lblP5_max_31 = new javax.swing.JLabel();
        lblP5_last_31 = new javax.swing.JLabel();
        lblDuration_32 = new javax.swing.JLabel();
        lblPower_32 = new javax.swing.JLabel();
        lblP5_start_32 = new javax.swing.JLabel();
        lblP5_max_32 = new javax.swing.JLabel();
        lblP5_last_32 = new javax.swing.JLabel();
        lblDuration_33 = new javax.swing.JLabel();
        lblPower_33 = new javax.swing.JLabel();
        lblP5_start_33 = new javax.swing.JLabel();
        lblP5_max_33 = new javax.swing.JLabel();
        lblP5_last_33 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(400, 990));
        setMinimumSize(new java.awt.Dimension(400, 990));
        setPreferredSize(new java.awt.Dimension(400, 990));
        setLayout(null);

        edtDuration.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        edtDuration.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtDuration.setText("50");
        add(edtDuration);
        edtDuration.setBounds(0, 420, 60, 30);

        edtPower.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        edtPower.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        edtPower.setText("50");
        add(edtPower);
        edtPower.setBounds(60, 420, 70, 30);

        btnTrailing.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        btnTrailing.setText("Завершить серией спадающих мощностей");
        btnTrailing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrailingActionPerformed(evt);
            }
        });
        add(btnTrailing);
        btnTrailing.setBounds(0, 450, 400, 30);

        btnAddStep.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        btnAddStep.setText("Добавить шаг");
        btnAddStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStepActionPerformed(evt);
            }
        });
        add(btnAddStep);
        btnAddStep.setBounds(130, 420, 270, 30);

        lblDuration_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_1.setText("2'");
        lblDuration_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_1.setOpaque(true);
        add(lblDuration_1);
        lblDuration_1.setBounds(0, 0, 60, 30);

        lblPower_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_1.setText("-");
        lblPower_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_1.setOpaque(true);
        add(lblPower_1);
        lblPower_1.setBounds(60, 0, 70, 30);

        lblP5_start_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_1.setText("-");
        lblP5_start_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_1.setOpaque(true);
        add(lblP5_start_1);
        lblP5_start_1.setBounds(130, 0, 90, 30);

        lblP5_max_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_1.setText("-");
        lblP5_max_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_1.setOpaque(true);
        add(lblP5_max_1);
        lblP5_max_1.setBounds(220, 0, 90, 30);

        lblP5_last_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_1.setText("-");
        lblP5_last_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_1.setOpaque(true);
        add(lblP5_last_1);
        lblP5_last_1.setBounds(310, 0, 90, 30);

        lblDuration_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_2.setText("4'");
        lblDuration_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_2.setOpaque(true);
        add(lblDuration_2);
        lblDuration_2.setBounds(0, 30, 60, 30);

        lblPower_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_2.setText("-");
        lblPower_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_2.setOpaque(true);
        add(lblPower_2);
        lblPower_2.setBounds(60, 30, 70, 30);

        lblP5_start_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_2.setText("-");
        lblP5_start_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_2.setOpaque(true);
        add(lblP5_start_2);
        lblP5_start_2.setBounds(130, 30, 90, 30);

        lblP5_max_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_2.setText("-");
        lblP5_max_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_2.setOpaque(true);
        add(lblP5_max_2);
        lblP5_max_2.setBounds(220, 30, 90, 30);

        lblP5_last_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_2.setText("-");
        lblP5_last_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_2.setOpaque(true);
        add(lblP5_last_2);
        lblP5_last_2.setBounds(310, 30, 90, 30);

        lblDuration_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_3.setText("6'");
        lblDuration_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_3.setOpaque(true);
        add(lblDuration_3);
        lblDuration_3.setBounds(0, 60, 60, 30);

        lblPower_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_3.setText("-");
        lblPower_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_3.setOpaque(true);
        add(lblPower_3);
        lblPower_3.setBounds(60, 60, 70, 30);

        lblP5_start_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_3.setText("-");
        lblP5_start_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_3.setOpaque(true);
        add(lblP5_start_3);
        lblP5_start_3.setBounds(130, 60, 90, 30);

        lblP5_max_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_3.setText("-");
        lblP5_max_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_3.setOpaque(true);
        add(lblP5_max_3);
        lblP5_max_3.setBounds(220, 60, 90, 30);

        lblP5_last_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_3.setText("-");
        lblP5_last_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_3.setOpaque(true);
        add(lblP5_last_3);
        lblP5_last_3.setBounds(310, 60, 90, 30);

        lblDuration_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_4.setText("8'");
        lblDuration_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_4.setOpaque(true);
        add(lblDuration_4);
        lblDuration_4.setBounds(0, 90, 60, 30);

        lblPower_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_4.setText("-");
        lblPower_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_4.setOpaque(true);
        add(lblPower_4);
        lblPower_4.setBounds(60, 90, 70, 30);

        lblP5_start_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_4.setText("-");
        lblP5_start_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_4.setOpaque(true);
        add(lblP5_start_4);
        lblP5_start_4.setBounds(130, 90, 90, 30);

        lblP5_max_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_4.setText("-");
        lblP5_max_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_4.setOpaque(true);
        add(lblP5_max_4);
        lblP5_max_4.setBounds(220, 90, 90, 30);

        lblP5_last_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_4.setText("-");
        lblP5_last_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_4.setOpaque(true);
        add(lblP5_last_4);
        lblP5_last_4.setBounds(310, 90, 90, 30);

        lblDuration_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_5.setText("10'");
        lblDuration_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_5.setOpaque(true);
        add(lblDuration_5);
        lblDuration_5.setBounds(0, 120, 60, 30);

        lblPower_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_5.setText("-");
        lblPower_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_5.setOpaque(true);
        add(lblPower_5);
        lblPower_5.setBounds(60, 120, 70, 30);

        lblP5_start_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_5.setText("-");
        lblP5_start_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_5.setOpaque(true);
        add(lblP5_start_5);
        lblP5_start_5.setBounds(130, 120, 90, 30);

        lblP5_max_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_5.setText("-");
        lblP5_max_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_5.setOpaque(true);
        add(lblP5_max_5);
        lblP5_max_5.setBounds(220, 120, 90, 30);

        lblP5_last_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_5.setText("-");
        lblP5_last_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_5.setOpaque(true);
        add(lblP5_last_5);
        lblP5_last_5.setBounds(310, 120, 90, 30);

        lblDuration_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_6.setText("12'");
        lblDuration_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_6.setOpaque(true);
        add(lblDuration_6);
        lblDuration_6.setBounds(0, 150, 60, 30);

        lblPower_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_6.setText("55");
        lblPower_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_6.setOpaque(true);
        add(lblPower_6);
        lblPower_6.setBounds(60, 150, 70, 30);

        lblP5_start_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_6.setText("<html>3.25 10<sup>-5</sup></html>");
        lblP5_start_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_6.setOpaque(true);
        add(lblP5_start_6);
        lblP5_start_6.setBounds(130, 150, 90, 30);

        lblP5_max_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_6.setText("<html>3.25 10<sup>-5</sup></html>");
        lblP5_max_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_6.setOpaque(true);
        add(lblP5_max_6);
        lblP5_max_6.setBounds(220, 150, 90, 30);

        lblP5_last_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_6.setText("<html>3.25 10<sup>-5</sup></html>");
        lblP5_last_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_6.setOpaque(true);
        add(lblP5_last_6);
        lblP5_last_6.setBounds(310, 150, 90, 30);

        lblDuration_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_7.setText("14'");
        lblDuration_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_7.setOpaque(true);
        add(lblDuration_7);
        lblDuration_7.setBounds(0, 180, 60, 30);

        lblPower_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_7.setText("-");
        lblPower_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_7.setOpaque(true);
        add(lblPower_7);
        lblPower_7.setBounds(60, 180, 70, 30);

        lblP5_start_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_7.setText("-");
        lblP5_start_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_7.setOpaque(true);
        add(lblP5_start_7);
        lblP5_start_7.setBounds(130, 180, 90, 30);

        lblP5_max_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_7.setText("-");
        lblP5_max_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_7.setOpaque(true);
        add(lblP5_max_7);
        lblP5_max_7.setBounds(220, 180, 90, 30);

        lblP5_last_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_7.setText("-");
        lblP5_last_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_7.setOpaque(true);
        add(lblP5_last_7);
        lblP5_last_7.setBounds(310, 180, 90, 30);

        lblDuration_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_8.setText("16'");
        lblDuration_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_8.setOpaque(true);
        add(lblDuration_8);
        lblDuration_8.setBounds(0, 210, 60, 30);

        lblPower_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_8.setText("-");
        lblPower_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_8.setOpaque(true);
        add(lblPower_8);
        lblPower_8.setBounds(60, 210, 70, 30);

        lblP5_start_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_8.setText("-");
        lblP5_start_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_8.setOpaque(true);
        add(lblP5_start_8);
        lblP5_start_8.setBounds(130, 210, 90, 30);

        lblP5_max_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_8.setText("-");
        lblP5_max_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_8.setOpaque(true);
        add(lblP5_max_8);
        lblP5_max_8.setBounds(220, 210, 90, 30);

        lblP5_last_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_8.setText("-");
        lblP5_last_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_8.setOpaque(true);
        add(lblP5_last_8);
        lblP5_last_8.setBounds(310, 210, 90, 30);

        lblDuration_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_9.setText("18'");
        lblDuration_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_9.setOpaque(true);
        add(lblDuration_9);
        lblDuration_9.setBounds(0, 240, 60, 30);

        lblPower_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_9.setText("-");
        lblPower_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_9.setOpaque(true);
        add(lblPower_9);
        lblPower_9.setBounds(60, 240, 70, 30);

        lblP5_start_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_9.setText("-");
        lblP5_start_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_9.setOpaque(true);
        add(lblP5_start_9);
        lblP5_start_9.setBounds(130, 240, 90, 30);

        lblP5_max_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_9.setText("-");
        lblP5_max_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_9.setOpaque(true);
        add(lblP5_max_9);
        lblP5_max_9.setBounds(220, 240, 90, 30);

        lblP5_last_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_9.setText("-");
        lblP5_last_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_9.setOpaque(true);
        add(lblP5_last_9);
        lblP5_last_9.setBounds(310, 240, 90, 30);

        lblDuration_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_10.setText("20'");
        lblDuration_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_10.setOpaque(true);
        add(lblDuration_10);
        lblDuration_10.setBounds(0, 270, 60, 30);

        lblPower_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_10.setText("-");
        lblPower_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_10.setOpaque(true);
        add(lblPower_10);
        lblPower_10.setBounds(60, 270, 70, 30);

        lblP5_start_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_10.setText("-");
        lblP5_start_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_10.setOpaque(true);
        add(lblP5_start_10);
        lblP5_start_10.setBounds(130, 270, 90, 30);

        lblP5_max_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_10.setText("-");
        lblP5_max_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_10.setOpaque(true);
        add(lblP5_max_10);
        lblP5_max_10.setBounds(220, 270, 90, 30);

        lblP5_last_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_10.setText("-");
        lblP5_last_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_10.setOpaque(true);
        add(lblP5_last_10);
        lblP5_last_10.setBounds(310, 270, 90, 30);

        lblDuration_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_11.setText("22'");
        lblDuration_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_11.setOpaque(true);
        add(lblDuration_11);
        lblDuration_11.setBounds(0, 300, 60, 30);

        lblPower_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_11.setText("-");
        lblPower_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_11.setOpaque(true);
        add(lblPower_11);
        lblPower_11.setBounds(60, 300, 70, 30);

        lblP5_start_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_11.setText("-");
        lblP5_start_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_11.setOpaque(true);
        add(lblP5_start_11);
        lblP5_start_11.setBounds(130, 300, 90, 30);

        lblP5_max_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_11.setText("-");
        lblP5_max_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_11.setOpaque(true);
        add(lblP5_max_11);
        lblP5_max_11.setBounds(220, 300, 90, 30);

        lblP5_last_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_11.setText("-");
        lblP5_last_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_11.setOpaque(true);
        add(lblP5_last_11);
        lblP5_last_11.setBounds(310, 300, 90, 30);

        lblDuration_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_12.setText("24'");
        lblDuration_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_12.setOpaque(true);
        add(lblDuration_12);
        lblDuration_12.setBounds(0, 330, 60, 30);

        lblPower_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_12.setText("-");
        lblPower_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_12.setOpaque(true);
        add(lblPower_12);
        lblPower_12.setBounds(60, 330, 70, 30);

        lblP5_start_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_12.setText("-");
        lblP5_start_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_12.setOpaque(true);
        add(lblP5_start_12);
        lblP5_start_12.setBounds(130, 330, 90, 30);

        lblP5_max_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_12.setText("-");
        lblP5_max_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_12.setOpaque(true);
        add(lblP5_max_12);
        lblP5_max_12.setBounds(220, 330, 90, 30);

        lblP5_last_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_12.setText("-");
        lblP5_last_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_12.setOpaque(true);
        add(lblP5_last_12);
        lblP5_last_12.setBounds(310, 330, 90, 30);

        lblDuration_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_13.setText("26'");
        lblDuration_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_13.setOpaque(true);
        add(lblDuration_13);
        lblDuration_13.setBounds(0, 360, 60, 30);

        lblPower_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_13.setText("-");
        lblPower_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_13.setOpaque(true);
        add(lblPower_13);
        lblPower_13.setBounds(60, 360, 70, 30);

        lblP5_start_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_13.setText("-");
        lblP5_start_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_13.setOpaque(true);
        add(lblP5_start_13);
        lblP5_start_13.setBounds(130, 360, 90, 30);

        lblP5_max_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_13.setText("-");
        lblP5_max_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_13.setOpaque(true);
        add(lblP5_max_13);
        lblP5_max_13.setBounds(220, 360, 90, 30);

        lblP5_last_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_13.setText("-");
        lblP5_last_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_13.setOpaque(true);
        add(lblP5_last_13);
        lblP5_last_13.setBounds(310, 360, 90, 30);

        lblDuration_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_14.setText("28'");
        lblDuration_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_14.setOpaque(true);
        add(lblDuration_14);
        lblDuration_14.setBounds(0, 390, 60, 30);

        lblPower_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_14.setText("-");
        lblPower_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_14.setOpaque(true);
        add(lblPower_14);
        lblPower_14.setBounds(60, 390, 70, 30);

        lblP5_start_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_14.setText("-");
        lblP5_start_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_14.setOpaque(true);
        add(lblP5_start_14);
        lblP5_start_14.setBounds(130, 390, 90, 30);

        lblP5_max_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_14.setText("-");
        lblP5_max_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_14.setOpaque(true);
        add(lblP5_max_14);
        lblP5_max_14.setBounds(220, 390, 90, 30);

        lblP5_last_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_14.setText("-");
        lblP5_last_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_14.setOpaque(true);
        add(lblP5_last_14);
        lblP5_last_14.setBounds(310, 390, 90, 30);

        lblDuration_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_15.setText("-");
        lblDuration_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_15.setOpaque(true);
        add(lblDuration_15);
        lblDuration_15.setBounds(0, 420, 60, 30);

        lblPower_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_15.setText("-");
        lblPower_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_15.setOpaque(true);
        add(lblPower_15);
        lblPower_15.setBounds(60, 420, 70, 30);

        lblP5_start_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_15.setText("-");
        lblP5_start_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_15.setOpaque(true);
        add(lblP5_start_15);
        lblP5_start_15.setBounds(130, 420, 90, 30);

        lblP5_max_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_15.setText("-");
        lblP5_max_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_15.setOpaque(true);
        add(lblP5_max_15);
        lblP5_max_15.setBounds(220, 420, 90, 30);

        lblP5_last_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_15.setText("-");
        lblP5_last_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_15.setOpaque(true);
        add(lblP5_last_15);
        lblP5_last_15.setBounds(310, 420, 90, 30);

        lblDuration_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_16.setText("-");
        lblDuration_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_16.setOpaque(true);
        add(lblDuration_16);
        lblDuration_16.setBounds(0, 450, 60, 30);

        lblPower_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_16.setText("-");
        lblPower_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_16.setOpaque(true);
        add(lblPower_16);
        lblPower_16.setBounds(60, 450, 70, 30);

        lblP5_start_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_16.setText("-");
        lblP5_start_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_16.setOpaque(true);
        add(lblP5_start_16);
        lblP5_start_16.setBounds(130, 450, 90, 30);

        lblP5_max_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_16.setText("-");
        lblP5_max_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_16.setOpaque(true);
        add(lblP5_max_16);
        lblP5_max_16.setBounds(220, 450, 90, 30);

        lblP5_last_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_16.setText("-");
        lblP5_last_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_16.setOpaque(true);
        add(lblP5_last_16);
        lblP5_last_16.setBounds(310, 450, 90, 30);

        lblDuration_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_17.setText("-");
        lblDuration_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_17.setOpaque(true);
        add(lblDuration_17);
        lblDuration_17.setBounds(0, 480, 60, 30);

        lblPower_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_17.setText("-");
        lblPower_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_17.setOpaque(true);
        add(lblPower_17);
        lblPower_17.setBounds(60, 480, 70, 30);

        lblP5_start_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_17.setText("-");
        lblP5_start_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_17.setOpaque(true);
        add(lblP5_start_17);
        lblP5_start_17.setBounds(130, 480, 90, 30);

        lblP5_max_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_17.setText("-");
        lblP5_max_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_17.setOpaque(true);
        add(lblP5_max_17);
        lblP5_max_17.setBounds(220, 480, 90, 30);

        lblP5_last_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_17.setText("-");
        lblP5_last_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_17.setOpaque(true);
        add(lblP5_last_17);
        lblP5_last_17.setBounds(310, 480, 90, 30);

        lblDuration_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_18.setText("-");
        lblDuration_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_18.setOpaque(true);
        add(lblDuration_18);
        lblDuration_18.setBounds(0, 510, 60, 30);

        lblPower_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_18.setText("-");
        lblPower_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_18.setOpaque(true);
        add(lblPower_18);
        lblPower_18.setBounds(60, 510, 70, 30);

        lblP5_start_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_18.setText("-");
        lblP5_start_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_18.setOpaque(true);
        add(lblP5_start_18);
        lblP5_start_18.setBounds(130, 510, 90, 30);

        lblP5_max_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_18.setText("-");
        lblP5_max_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_18.setOpaque(true);
        add(lblP5_max_18);
        lblP5_max_18.setBounds(220, 510, 90, 30);

        lblP5_last_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_18.setText("-");
        lblP5_last_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_18.setOpaque(true);
        add(lblP5_last_18);
        lblP5_last_18.setBounds(310, 510, 90, 30);

        lblDuration_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_19.setText("-");
        lblDuration_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_19.setOpaque(true);
        add(lblDuration_19);
        lblDuration_19.setBounds(0, 540, 60, 30);

        lblPower_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_19.setText("-");
        lblPower_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_19.setOpaque(true);
        add(lblPower_19);
        lblPower_19.setBounds(60, 540, 70, 30);

        lblP5_start_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_19.setText("-");
        lblP5_start_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_19.setOpaque(true);
        add(lblP5_start_19);
        lblP5_start_19.setBounds(130, 540, 90, 30);

        lblP5_max_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_19.setText("-");
        lblP5_max_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_19.setOpaque(true);
        add(lblP5_max_19);
        lblP5_max_19.setBounds(220, 540, 90, 30);

        lblP5_last_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_19.setText("-");
        lblP5_last_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_19.setOpaque(true);
        add(lblP5_last_19);
        lblP5_last_19.setBounds(310, 540, 90, 30);

        lblDuration_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_20.setText("-");
        lblDuration_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_20.setOpaque(true);
        add(lblDuration_20);
        lblDuration_20.setBounds(0, 570, 60, 30);

        lblPower_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_20.setText("-");
        lblPower_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_20.setOpaque(true);
        add(lblPower_20);
        lblPower_20.setBounds(60, 570, 70, 30);

        lblP5_start_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_20.setText("-");
        lblP5_start_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_20.setOpaque(true);
        add(lblP5_start_20);
        lblP5_start_20.setBounds(130, 570, 90, 30);

        lblP5_max_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_20.setText("-");
        lblP5_max_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_20.setOpaque(true);
        add(lblP5_max_20);
        lblP5_max_20.setBounds(220, 570, 90, 30);

        lblP5_last_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_20.setText("-");
        lblP5_last_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_20.setOpaque(true);
        add(lblP5_last_20);
        lblP5_last_20.setBounds(310, 570, 90, 30);

        lblDuration_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_21.setText("-");
        lblDuration_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_21.setOpaque(true);
        add(lblDuration_21);
        lblDuration_21.setBounds(0, 600, 60, 30);

        lblPower_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_21.setText("-");
        lblPower_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_21.setOpaque(true);
        add(lblPower_21);
        lblPower_21.setBounds(60, 600, 70, 30);

        lblP5_start_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_21.setText("-");
        lblP5_start_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_21.setOpaque(true);
        add(lblP5_start_21);
        lblP5_start_21.setBounds(130, 600, 90, 30);

        lblP5_max_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_21.setText("-");
        lblP5_max_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_21.setOpaque(true);
        add(lblP5_max_21);
        lblP5_max_21.setBounds(220, 600, 90, 30);

        lblP5_last_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_21.setText("-");
        lblP5_last_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_21.setOpaque(true);
        add(lblP5_last_21);
        lblP5_last_21.setBounds(310, 600, 90, 30);

        lblDuration_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_22.setText("-");
        lblDuration_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_22.setOpaque(true);
        add(lblDuration_22);
        lblDuration_22.setBounds(0, 630, 60, 30);

        lblPower_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_22.setText("-");
        lblPower_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_22.setOpaque(true);
        add(lblPower_22);
        lblPower_22.setBounds(60, 630, 70, 30);

        lblP5_start_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_22.setText("-");
        lblP5_start_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_22.setOpaque(true);
        add(lblP5_start_22);
        lblP5_start_22.setBounds(130, 630, 90, 30);

        lblP5_max_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_22.setText("-");
        lblP5_max_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_22.setOpaque(true);
        add(lblP5_max_22);
        lblP5_max_22.setBounds(220, 630, 90, 30);

        lblP5_last_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_22.setText("-");
        lblP5_last_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_22.setOpaque(true);
        add(lblP5_last_22);
        lblP5_last_22.setBounds(310, 630, 90, 30);

        lblDuration_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_23.setText("-");
        lblDuration_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_23.setOpaque(true);
        add(lblDuration_23);
        lblDuration_23.setBounds(0, 660, 60, 30);

        lblPower_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_23.setText("-");
        lblPower_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_23.setOpaque(true);
        add(lblPower_23);
        lblPower_23.setBounds(60, 660, 70, 30);

        lblP5_start_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_23.setText("-");
        lblP5_start_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_23.setOpaque(true);
        add(lblP5_start_23);
        lblP5_start_23.setBounds(130, 660, 90, 30);

        lblP5_max_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_23.setText("-");
        lblP5_max_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_23.setOpaque(true);
        add(lblP5_max_23);
        lblP5_max_23.setBounds(220, 660, 90, 30);

        lblP5_last_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_23.setText("-");
        lblP5_last_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_23.setOpaque(true);
        add(lblP5_last_23);
        lblP5_last_23.setBounds(310, 660, 90, 30);

        lblDuration_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_24.setText("-");
        lblDuration_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_24.setOpaque(true);
        add(lblDuration_24);
        lblDuration_24.setBounds(0, 690, 60, 30);

        lblPower_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_24.setText("-");
        lblPower_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_24.setOpaque(true);
        add(lblPower_24);
        lblPower_24.setBounds(60, 690, 70, 30);

        lblP5_start_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_24.setText("-");
        lblP5_start_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_24.setOpaque(true);
        add(lblP5_start_24);
        lblP5_start_24.setBounds(130, 690, 90, 30);

        lblP5_max_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_24.setText("-");
        lblP5_max_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_24.setOpaque(true);
        add(lblP5_max_24);
        lblP5_max_24.setBounds(220, 690, 90, 30);

        lblP5_last_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_24.setText("-");
        lblP5_last_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_24.setOpaque(true);
        add(lblP5_last_24);
        lblP5_last_24.setBounds(310, 690, 90, 30);

        lblDuration_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_25.setText("-");
        lblDuration_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_25.setOpaque(true);
        add(lblDuration_25);
        lblDuration_25.setBounds(0, 720, 60, 30);

        lblPower_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_25.setText("-");
        lblPower_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_25.setOpaque(true);
        add(lblPower_25);
        lblPower_25.setBounds(60, 720, 70, 30);

        lblP5_start_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_25.setText("-");
        lblP5_start_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_25.setOpaque(true);
        add(lblP5_start_25);
        lblP5_start_25.setBounds(130, 720, 90, 30);

        lblP5_max_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_25.setText("-");
        lblP5_max_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_25.setOpaque(true);
        add(lblP5_max_25);
        lblP5_max_25.setBounds(220, 720, 90, 30);

        lblP5_last_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_25.setText("-");
        lblP5_last_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_25.setOpaque(true);
        add(lblP5_last_25);
        lblP5_last_25.setBounds(310, 720, 90, 30);

        lblDuration_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_26.setText("-");
        lblDuration_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_26.setOpaque(true);
        add(lblDuration_26);
        lblDuration_26.setBounds(0, 750, 60, 30);

        lblPower_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_26.setText("-");
        lblPower_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_26.setOpaque(true);
        add(lblPower_26);
        lblPower_26.setBounds(60, 750, 70, 30);

        lblP5_start_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_26.setText("-");
        lblP5_start_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_26.setOpaque(true);
        add(lblP5_start_26);
        lblP5_start_26.setBounds(130, 750, 90, 30);

        lblP5_max_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_26.setText("-");
        lblP5_max_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_26.setOpaque(true);
        add(lblP5_max_26);
        lblP5_max_26.setBounds(220, 750, 90, 30);

        lblP5_last_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_26.setText("-");
        lblP5_last_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_26.setOpaque(true);
        add(lblP5_last_26);
        lblP5_last_26.setBounds(310, 750, 90, 30);

        lblDuration_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_27.setText("-");
        lblDuration_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_27.setOpaque(true);
        add(lblDuration_27);
        lblDuration_27.setBounds(0, 780, 60, 30);

        lblPower_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_27.setText("-");
        lblPower_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_27.setOpaque(true);
        add(lblPower_27);
        lblPower_27.setBounds(60, 780, 70, 30);

        lblP5_start_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_27.setText("-");
        lblP5_start_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_27.setOpaque(true);
        add(lblP5_start_27);
        lblP5_start_27.setBounds(130, 780, 90, 30);

        lblP5_max_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_27.setText("-");
        lblP5_max_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_27.setOpaque(true);
        add(lblP5_max_27);
        lblP5_max_27.setBounds(220, 780, 90, 30);

        lblP5_last_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_27.setText("-");
        lblP5_last_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_27.setOpaque(true);
        add(lblP5_last_27);
        lblP5_last_27.setBounds(310, 780, 90, 30);

        lblDuration_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_28.setText("-");
        lblDuration_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_28.setOpaque(true);
        add(lblDuration_28);
        lblDuration_28.setBounds(0, 810, 60, 30);

        lblPower_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_28.setText("-");
        lblPower_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_28.setOpaque(true);
        add(lblPower_28);
        lblPower_28.setBounds(60, 810, 70, 30);

        lblP5_start_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_28.setText("-");
        lblP5_start_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_28.setOpaque(true);
        add(lblP5_start_28);
        lblP5_start_28.setBounds(130, 810, 90, 30);

        lblP5_max_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_28.setText("-");
        lblP5_max_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_28.setOpaque(true);
        add(lblP5_max_28);
        lblP5_max_28.setBounds(220, 810, 90, 30);

        lblP5_last_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_28.setText("-");
        lblP5_last_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_28.setOpaque(true);
        add(lblP5_last_28);
        lblP5_last_28.setBounds(310, 810, 90, 30);

        lblDuration_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_29.setText("-");
        lblDuration_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_29.setOpaque(true);
        add(lblDuration_29);
        lblDuration_29.setBounds(0, 840, 60, 30);

        lblPower_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_29.setText("-");
        lblPower_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_29.setOpaque(true);
        add(lblPower_29);
        lblPower_29.setBounds(60, 840, 70, 30);

        lblP5_start_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_29.setText("-");
        lblP5_start_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_29.setOpaque(true);
        add(lblP5_start_29);
        lblP5_start_29.setBounds(130, 840, 90, 30);

        lblP5_max_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_29.setText("-");
        lblP5_max_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_29.setOpaque(true);
        add(lblP5_max_29);
        lblP5_max_29.setBounds(220, 840, 90, 30);

        lblP5_last_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_29.setText("-");
        lblP5_last_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_29.setOpaque(true);
        add(lblP5_last_29);
        lblP5_last_29.setBounds(310, 840, 90, 30);

        lblDuration_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_30.setText("-");
        lblDuration_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_30.setOpaque(true);
        add(lblDuration_30);
        lblDuration_30.setBounds(0, 870, 60, 30);

        lblPower_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_30.setText("-");
        lblPower_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_30.setOpaque(true);
        add(lblPower_30);
        lblPower_30.setBounds(60, 870, 70, 30);

        lblP5_start_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_30.setText("-");
        lblP5_start_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_30.setOpaque(true);
        add(lblP5_start_30);
        lblP5_start_30.setBounds(130, 870, 90, 30);

        lblP5_max_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_30.setText("-");
        lblP5_max_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_30.setOpaque(true);
        add(lblP5_max_30);
        lblP5_max_30.setBounds(220, 870, 90, 30);

        lblP5_last_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_30.setText("-");
        lblP5_last_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_30.setOpaque(true);
        add(lblP5_last_30);
        lblP5_last_30.setBounds(310, 870, 90, 30);

        lblDuration_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_31.setText("-");
        lblDuration_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_31.setOpaque(true);
        add(lblDuration_31);
        lblDuration_31.setBounds(0, 900, 60, 30);

        lblPower_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_31.setText("-");
        lblPower_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_31.setOpaque(true);
        add(lblPower_31);
        lblPower_31.setBounds(60, 900, 70, 30);

        lblP5_start_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_31.setText("-");
        lblP5_start_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_31.setOpaque(true);
        add(lblP5_start_31);
        lblP5_start_31.setBounds(130, 900, 90, 30);

        lblP5_max_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_31.setText("-");
        lblP5_max_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_31.setOpaque(true);
        add(lblP5_max_31);
        lblP5_max_31.setBounds(220, 900, 90, 30);

        lblP5_last_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_31.setText("-");
        lblP5_last_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_31.setOpaque(true);
        add(lblP5_last_31);
        lblP5_last_31.setBounds(310, 900, 90, 30);

        lblDuration_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_32.setText("-");
        lblDuration_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_32.setOpaque(true);
        add(lblDuration_32);
        lblDuration_32.setBounds(0, 930, 60, 30);

        lblPower_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_32.setText("-");
        lblPower_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_32.setOpaque(true);
        add(lblPower_32);
        lblPower_32.setBounds(60, 930, 70, 30);

        lblP5_start_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_32.setText("-");
        lblP5_start_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_32.setOpaque(true);
        add(lblP5_start_32);
        lblP5_start_32.setBounds(130, 930, 90, 30);

        lblP5_max_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_32.setText("-");
        lblP5_max_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_32.setOpaque(true);
        add(lblP5_max_32);
        lblP5_max_32.setBounds(220, 930, 90, 30);

        lblP5_last_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_32.setText("-");
        lblP5_last_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_32.setOpaque(true);
        add(lblP5_last_32);
        lblP5_last_32.setBounds(310, 930, 90, 30);

        lblDuration_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_33.setText("last");
        lblDuration_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblDuration_33.setOpaque(true);
        add(lblDuration_33);
        lblDuration_33.setBounds(0, 960, 60, 30);

        lblPower_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_33.setText("-");
        lblPower_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblPower_33.setOpaque(true);
        add(lblPower_33);
        lblPower_33.setBounds(60, 960, 70, 30);

        lblP5_start_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_33.setText("-");
        lblP5_start_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblP5_start_33.setOpaque(true);
        add(lblP5_start_33);
        lblP5_start_33.setBounds(130, 960, 90, 30);

        lblP5_max_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_33.setText("-");
        lblP5_max_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblP5_max_33.setOpaque(true);
        add(lblP5_max_33);
        lblP5_max_33.setBounds(220, 960, 90, 30);

        lblP5_last_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_33.setText("-");
        lblP5_last_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblP5_last_33.setOpaque(true);
        add(lblP5_last_33);
        lblP5_last_33.setBounds(310, 960, 90, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrailingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrailingActionPerformed
        m_pParent.AddTrailingSteps();
        
        edtDuration.setVisible( false);
        edtPower.setVisible( false);
        btnAddStep.setVisible( false);
        btnTrailing.setVisible( false);
        
        m_pParent.m_bTrailingAddded = true;
    }//GEN-LAST:event_btnTrailingActionPerformed

    private void btnAddStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStepActionPerformed
        int nDuration = Integer.parseInt( edtDuration.getText());
        int nPower = Integer.parseInt( edtPower.getText());
        
        if( nDuration < 0) {//|| nDuration > maxDuration) {
            edtDuration.setBackground( new Color( 100, 0, 0));
            return;
        }
        else
            edtDuration.setBackground( new Color( 255, 255, 255));
        
        if( nPower < 0) {//|| nDuration > maxDuration) {
            edtPower.setBackground( new Color( 100, 0, 0));
            return;
        }
        else
            edtPower.setBackground( new Color( 255, 255, 255));
        
        m_pParent.m_lstProgram.add(new GettersActivationProgramStep(  nDuration, nPower));
        PlaceEdtBtns();       
    }//GEN-LAST:event_btnAddStepActionPerformed

    public void PlaceEdtBtns() {
        
        edtDuration.setVisible( !(m_pParent.m_nInProgress == -1));
        edtDuration.setLocation(  0, 30 * m_pParent.m_lstProgram.size());
        
        edtPower.setVisible(    !(m_pParent.m_nInProgress == -1));
        edtPower.setLocation(    60, 30 * m_pParent.m_lstProgram.size());
        
        btnAddStep.setVisible(  !(m_pParent.m_nInProgress == -1));
        btnAddStep.setLocation( 130, 30 * m_pParent.m_lstProgram.size());
        
        btnTrailing.setVisible( !(m_pParent.m_nInProgress == -1));
        btnTrailing.setLocation(  0, 30 * ( m_pParent.m_lstProgram.size() + 1));
        
        /*
        switch( m_pParent.m_nProgramType) {
            case 11: btnTrailing.setText( "Завершить серией мощностей 45-40-35 и т.д."); break;
            case 12: btnTrailing.setText( "Завершить серией мощностей 26-24-22 и т.д."); break;
            case 21: btnTrailing.setText( "Завершить серией мощностей 25-20-15 и т.д."); break;
            case 22: btnTrailing.setText( "Завершить серией мощностей 26-24-22 и т.д."); break;
            default: btnTrailing.setText( "Завершить серией мощностей для гет? инд?"); break;
        }
        */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAddStep;
    public javax.swing.JButton btnTrailing;
    public javax.swing.JTextField edtDuration;
    public javax.swing.JTextField edtPower;
    public javax.swing.JLabel lblDuration_1;
    public javax.swing.JLabel lblDuration_10;
    public javax.swing.JLabel lblDuration_11;
    public javax.swing.JLabel lblDuration_12;
    public javax.swing.JLabel lblDuration_13;
    public javax.swing.JLabel lblDuration_14;
    public javax.swing.JLabel lblDuration_15;
    public javax.swing.JLabel lblDuration_16;
    public javax.swing.JLabel lblDuration_17;
    public javax.swing.JLabel lblDuration_18;
    public javax.swing.JLabel lblDuration_19;
    public javax.swing.JLabel lblDuration_2;
    public javax.swing.JLabel lblDuration_20;
    public javax.swing.JLabel lblDuration_21;
    public javax.swing.JLabel lblDuration_22;
    public javax.swing.JLabel lblDuration_23;
    public javax.swing.JLabel lblDuration_24;
    public javax.swing.JLabel lblDuration_25;
    public javax.swing.JLabel lblDuration_26;
    public javax.swing.JLabel lblDuration_27;
    public javax.swing.JLabel lblDuration_28;
    public javax.swing.JLabel lblDuration_29;
    public javax.swing.JLabel lblDuration_3;
    public javax.swing.JLabel lblDuration_30;
    public javax.swing.JLabel lblDuration_31;
    public javax.swing.JLabel lblDuration_32;
    public javax.swing.JLabel lblDuration_33;
    public javax.swing.JLabel lblDuration_4;
    public javax.swing.JLabel lblDuration_5;
    public javax.swing.JLabel lblDuration_6;
    public javax.swing.JLabel lblDuration_7;
    public javax.swing.JLabel lblDuration_8;
    public javax.swing.JLabel lblDuration_9;
    public javax.swing.JLabel lblP5_last_1;
    public javax.swing.JLabel lblP5_last_10;
    public javax.swing.JLabel lblP5_last_11;
    public javax.swing.JLabel lblP5_last_12;
    public javax.swing.JLabel lblP5_last_13;
    public javax.swing.JLabel lblP5_last_14;
    public javax.swing.JLabel lblP5_last_15;
    public javax.swing.JLabel lblP5_last_16;
    public javax.swing.JLabel lblP5_last_17;
    public javax.swing.JLabel lblP5_last_18;
    public javax.swing.JLabel lblP5_last_19;
    public javax.swing.JLabel lblP5_last_2;
    public javax.swing.JLabel lblP5_last_20;
    public javax.swing.JLabel lblP5_last_21;
    public javax.swing.JLabel lblP5_last_22;
    public javax.swing.JLabel lblP5_last_23;
    public javax.swing.JLabel lblP5_last_24;
    public javax.swing.JLabel lblP5_last_25;
    public javax.swing.JLabel lblP5_last_26;
    public javax.swing.JLabel lblP5_last_27;
    public javax.swing.JLabel lblP5_last_28;
    public javax.swing.JLabel lblP5_last_29;
    public javax.swing.JLabel lblP5_last_3;
    public javax.swing.JLabel lblP5_last_30;
    public javax.swing.JLabel lblP5_last_31;
    public javax.swing.JLabel lblP5_last_32;
    public javax.swing.JLabel lblP5_last_33;
    public javax.swing.JLabel lblP5_last_4;
    public javax.swing.JLabel lblP5_last_5;
    public javax.swing.JLabel lblP5_last_6;
    public javax.swing.JLabel lblP5_last_7;
    public javax.swing.JLabel lblP5_last_8;
    public javax.swing.JLabel lblP5_last_9;
    public javax.swing.JLabel lblP5_max_1;
    public javax.swing.JLabel lblP5_max_10;
    public javax.swing.JLabel lblP5_max_11;
    public javax.swing.JLabel lblP5_max_12;
    public javax.swing.JLabel lblP5_max_13;
    public javax.swing.JLabel lblP5_max_14;
    public javax.swing.JLabel lblP5_max_15;
    public javax.swing.JLabel lblP5_max_16;
    public javax.swing.JLabel lblP5_max_17;
    public javax.swing.JLabel lblP5_max_18;
    public javax.swing.JLabel lblP5_max_19;
    public javax.swing.JLabel lblP5_max_2;
    public javax.swing.JLabel lblP5_max_20;
    public javax.swing.JLabel lblP5_max_21;
    public javax.swing.JLabel lblP5_max_22;
    public javax.swing.JLabel lblP5_max_23;
    public javax.swing.JLabel lblP5_max_24;
    public javax.swing.JLabel lblP5_max_25;
    public javax.swing.JLabel lblP5_max_26;
    public javax.swing.JLabel lblP5_max_27;
    public javax.swing.JLabel lblP5_max_28;
    public javax.swing.JLabel lblP5_max_29;
    public javax.swing.JLabel lblP5_max_3;
    public javax.swing.JLabel lblP5_max_30;
    public javax.swing.JLabel lblP5_max_31;
    public javax.swing.JLabel lblP5_max_32;
    public javax.swing.JLabel lblP5_max_33;
    public javax.swing.JLabel lblP5_max_4;
    public javax.swing.JLabel lblP5_max_5;
    public javax.swing.JLabel lblP5_max_6;
    public javax.swing.JLabel lblP5_max_7;
    public javax.swing.JLabel lblP5_max_8;
    public javax.swing.JLabel lblP5_max_9;
    public javax.swing.JLabel lblP5_start_1;
    public javax.swing.JLabel lblP5_start_10;
    public javax.swing.JLabel lblP5_start_11;
    public javax.swing.JLabel lblP5_start_12;
    public javax.swing.JLabel lblP5_start_13;
    public javax.swing.JLabel lblP5_start_14;
    public javax.swing.JLabel lblP5_start_15;
    public javax.swing.JLabel lblP5_start_16;
    public javax.swing.JLabel lblP5_start_17;
    public javax.swing.JLabel lblP5_start_18;
    public javax.swing.JLabel lblP5_start_19;
    public javax.swing.JLabel lblP5_start_2;
    public javax.swing.JLabel lblP5_start_20;
    public javax.swing.JLabel lblP5_start_21;
    public javax.swing.JLabel lblP5_start_22;
    public javax.swing.JLabel lblP5_start_23;
    public javax.swing.JLabel lblP5_start_24;
    public javax.swing.JLabel lblP5_start_25;
    public javax.swing.JLabel lblP5_start_26;
    public javax.swing.JLabel lblP5_start_27;
    public javax.swing.JLabel lblP5_start_28;
    public javax.swing.JLabel lblP5_start_29;
    public javax.swing.JLabel lblP5_start_3;
    public javax.swing.JLabel lblP5_start_30;
    public javax.swing.JLabel lblP5_start_31;
    public javax.swing.JLabel lblP5_start_32;
    public javax.swing.JLabel lblP5_start_33;
    public javax.swing.JLabel lblP5_start_4;
    public javax.swing.JLabel lblP5_start_5;
    public javax.swing.JLabel lblP5_start_6;
    public javax.swing.JLabel lblP5_start_7;
    public javax.swing.JLabel lblP5_start_8;
    public javax.swing.JLabel lblP5_start_9;
    public javax.swing.JLabel lblPower_1;
    public javax.swing.JLabel lblPower_10;
    public javax.swing.JLabel lblPower_11;
    public javax.swing.JLabel lblPower_12;
    public javax.swing.JLabel lblPower_13;
    public javax.swing.JLabel lblPower_14;
    public javax.swing.JLabel lblPower_15;
    public javax.swing.JLabel lblPower_16;
    public javax.swing.JLabel lblPower_17;
    public javax.swing.JLabel lblPower_18;
    public javax.swing.JLabel lblPower_19;
    public javax.swing.JLabel lblPower_2;
    public javax.swing.JLabel lblPower_20;
    public javax.swing.JLabel lblPower_21;
    public javax.swing.JLabel lblPower_22;
    public javax.swing.JLabel lblPower_23;
    public javax.swing.JLabel lblPower_24;
    public javax.swing.JLabel lblPower_25;
    public javax.swing.JLabel lblPower_26;
    public javax.swing.JLabel lblPower_27;
    public javax.swing.JLabel lblPower_28;
    public javax.swing.JLabel lblPower_29;
    public javax.swing.JLabel lblPower_3;
    public javax.swing.JLabel lblPower_30;
    public javax.swing.JLabel lblPower_31;
    public javax.swing.JLabel lblPower_32;
    public javax.swing.JLabel lblPower_33;
    public javax.swing.JLabel lblPower_4;
    public javax.swing.JLabel lblPower_5;
    public javax.swing.JLabel lblPower_6;
    public javax.swing.JLabel lblPower_7;
    public javax.swing.JLabel lblPower_8;
    public javax.swing.JLabel lblPower_9;
    // End of variables declaration//GEN-END:variables
}
