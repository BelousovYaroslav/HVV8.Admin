/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step10;

import hvv_admin.dialogs.step08.*;
import hvv_admin.HVV_Admin;
import hvv_admin.steps.info.GettersActivationProgram;
import hvv_admin.steps.info.GettersActivationProgramStep;
import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author yaroslav
 */
public class TechProcessStep10_1_Dlg_M_StepsPanel extends javax.swing.JPanel {

    private final int m_nDevice;
    private final HVV_Admin theApp;

    //private final LineBorder m_borderGrey;
    //private final LineBorder m_borderRed;
    private final Color m_niceRed;
    
    /**
     * Creates new form TechProcessStep08_2_Dlg_3_StepTemplate2
     */
    public TechProcessStep10_1_Dlg_M_StepsPanel( HVV_Admin app, int nDevice) {
        theApp = app;
        initComponents();
        m_nDevice = nDevice;
        
        m_niceRed = new Color( 250, 125, 125);
        //m_borderGrey = new LineBorder( new Color( 220, 220, 220), 1, true);
        //m_borderRed =  new LineBorder( new Color( 200,  30,  30), 1, true);
    }

    public void ColorPressureLabel( Double dbl, JLabel lbl) {
        if( dbl.isNaN()) {
            lbl.setBackground( null);
            return;
        }
        
        int nExp = ( int) Math.log10( dbl);
        if( nExp < 0) nExp--;
        
        float dblMant = ( float) ( dbl / Math.pow( 10, nExp));
        if( nExp == -5)
            lbl.setBackground( m_niceRed);      //lbl.setBorder( m_borderRed);
        else
            lbl.setBackground( null);           //lbl.setBorder( m_borderGrey);
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
                                   lblDuration_33 };
        
        JLabel [] arrPowers = {    lblPower_1,  lblPower_2,  lblPower_3,  lblPower_4,
                                   lblPower_5,  lblPower_6,  lblPower_7,  lblPower_8,
                                   lblPower_9,  lblPower_10, lblPower_11, lblPower_12,
                                   lblPower_13, lblPower_14, lblPower_15, lblPower_16,
                                   lblPower_17, lblPower_18, lblPower_19, lblPower_20,
                                   lblPower_21, lblPower_22, lblPower_23, lblPower_24,
                                   lblPower_25, lblPower_26, lblPower_27, lblPower_28,
                                   lblPower_29, lblPower_30, lblPower_31, lblPower_32,
                                   lblPower_33 };
        
        JLabel [] arrP5start = {   lblP5_start_1,  lblP5_start_2,  lblP5_start_3,  lblP5_start_4,
                                   lblP5_start_5,  lblP5_start_6,  lblP5_start_7,  lblP5_start_8,
                                   lblP5_start_9,  lblP5_start_10, lblP5_start_11, lblP5_start_12,
                                   lblP5_start_13, lblP5_start_14, lblP5_start_15, lblP5_start_16,
                                   lblP5_start_17, lblP5_start_18, lblP5_start_19, lblP5_start_20,
                                   lblP5_start_21, lblP5_start_22, lblP5_start_23, lblP5_start_24,
                                   lblP5_start_25, lblP5_start_26, lblP5_start_27, lblP5_start_28,
                                   lblP5_start_29, lblP5_start_30, lblP5_start_31, lblP5_start_32,
                                   lblP5_start_33 };
        
        JLabel [] arrP5max = {     lblP5_max_1,  lblP5_max_2,  lblP5_max_3,  lblP5_max_4,
                                   lblP5_max_5,  lblP5_max_6,  lblP5_max_7,  lblP5_max_8,
                                   lblP5_max_9,  lblP5_max_10, lblP5_max_11, lblP5_max_12,
                                   lblP5_max_13, lblP5_max_14, lblP5_max_15, lblP5_max_16, 
                                   lblP5_max_17, lblP5_max_18, lblP5_max_19, lblP5_max_20,
                                   lblP5_max_21, lblP5_max_22, lblP5_max_23, lblP5_max_24,
                                   lblP5_max_25, lblP5_max_26, lblP5_max_27, lblP5_max_28,
                                   lblP5_max_29, lblP5_max_30, lblP5_max_31, lblP5_max_32,
                                   lblP5_max_33 };
    
        JLabel [] arrP5last = {    lblP5_last_1,  lblP5_last_2,  lblP5_last_3,  lblP5_last_4,
                                   lblP5_last_5,  lblP5_last_6,  lblP5_last_7,  lblP5_last_8,
                                   lblP5_last_9,  lblP5_last_10, lblP5_last_11, lblP5_last_12,
                                   lblP5_last_13, lblP5_last_14, lblP5_last_15, lblP5_last_16, 
                                   lblP5_last_17, lblP5_last_18, lblP5_last_19, lblP5_last_20,
                                   lblP5_last_21, lblP5_last_22, lblP5_last_23, lblP5_last_24,
                                   lblP5_last_25, lblP5_last_26, lblP5_last_27, lblP5_last_28,
                                   lblP5_last_29, lblP5_last_30, lblP5_last_31, lblP5_last_32,
                                   lblP5_last_33 };
        
        GettersActivationProgram prg;
        LinkedList lstActivating = null;
        if( theApp.m_mapActivation.containsKey( m_nDevice)) {
            prg = ( GettersActivationProgram) theApp.m_mapActivation.get( m_nDevice);
            lstActivating = prg.GetListSteps();
        }
        
        for( int i=0; i<33; i++) {
            if( lstActivating != null) {
                if( i  < lstActivating.size()) {
                    GettersActivationProgramStep step = ( GettersActivationProgramStep) lstActivating.get( i);

                    //DURATION
                    arrDurations[i].setText( "" + step.GetDuration());

                    //POWER
                    arrPowers[i].setText( "" + step.GetPower());

                    //P5_START
                    if( step.GetP5_start() != 0.) {
                        arrP5start[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_start(), false) + "</html>");
                        ColorPressureLabel( step.GetP5_start(), arrP5start[i]);
                    }
                    else {
                        arrP5start[i].setText( "-");        arrP5start[i].setBackground( null);      //arrP5start[i].setBorder( m_borderGrey);
                    }
                    
                    //P5_MAX
                    if( step.GetP5_max() != 0.) {
                        arrP5max[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_max(), false) + "</html>");
                        ColorPressureLabel( step.GetP5_max(), arrP5max[i]);
                    }
                    else {
                        arrP5max[i].setText( "-");          arrP5max[i].setBackground( null);      //arrP5max[i].setBorder( m_borderGrey);   
                    }
                    
                    //P5_LAST
                    if( step.GetP5_last() != 0.) {
                        arrP5last[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_last(), false) + "</html>");
                        ColorPressureLabel( step.GetP5_last(), arrP5last[i]);
                    }
                    else {
                        arrP5last[i].setText( "-");         arrP5last[i].setBackground( null);      //arrP5last[i].setBorder( m_borderGrey);   
                    }
                }
                else {
                    arrDurations[i].setText(    "-");
                    arrPowers[i].setText(       "-");
                    arrP5start[i].setText(      "-");       arrP5start[i].setBackground( null);      //arrP5start[i].setBorder( m_borderGrey);
                    arrP5max[i].setText(        "-");       arrP5max[i].setBackground( null);      //arrP5max[i].setBorder( m_borderGrey);
                    arrP5last[i].setText(       "-");       arrP5last[i].setBackground( null);      //arrP5last[i].setBorder( m_borderGrey);
                }
            }
            else {
                arrDurations[i].setText(    "-");
                arrPowers[i].setText(       "-");
                arrP5start[i].setText(      "-");       arrP5start[i].setBackground( null);      //arrP5start[i].setBorder( m_borderGrey);
                arrP5max[i].setText(        "-");       arrP5max[i].setBackground( null);      //arrP5max[i].setBorder( m_borderGrey);
                arrP5last[i].setText(       "-");       arrP5last[i].setBackground( null);      //arrP5last[i].setBorder( m_borderGrey);
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

        setMaximumSize(new java.awt.Dimension(240, 1000));
        setMinimumSize(new java.awt.Dimension(240, 1000));
        setPreferredSize(new java.awt.Dimension(240, 1000));
        setLayout(null);

        lblDuration_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_1.setText("1'");
        lblDuration_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_1.setOpaque(true);
        add(lblDuration_1);
        lblDuration_1.setBounds(0, 0, 30, 30);

        lblPower_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_1.setText("-");
        lblPower_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_1.setOpaque(true);
        add(lblPower_1);
        lblPower_1.setBounds(30, 0, 30, 30);

        lblP5_start_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_1.setText("-");
        lblP5_start_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_1.setOpaque(true);
        add(lblP5_start_1);
        lblP5_start_1.setBounds(60, 0, 60, 30);

        lblP5_max_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_1.setText("-");
        lblP5_max_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_1.setOpaque(true);
        add(lblP5_max_1);
        lblP5_max_1.setBounds(120, 0, 60, 30);

        lblP5_last_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_1.setText("-");
        lblP5_last_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_1.setOpaque(true);
        add(lblP5_last_1);
        lblP5_last_1.setBounds(180, 0, 60, 30);

        lblDuration_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_2.setText("2'");
        lblDuration_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_2.setOpaque(true);
        add(lblDuration_2);
        lblDuration_2.setBounds(0, 30, 30, 30);

        lblPower_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_2.setText("-");
        lblPower_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_2.setOpaque(true);
        add(lblPower_2);
        lblPower_2.setBounds(30, 30, 30, 30);

        lblP5_start_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_2.setText("-");
        lblP5_start_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_2.setOpaque(true);
        add(lblP5_start_2);
        lblP5_start_2.setBounds(60, 30, 60, 30);

        lblP5_max_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_2.setText("-");
        lblP5_max_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_2.setOpaque(true);
        add(lblP5_max_2);
        lblP5_max_2.setBounds(120, 30, 60, 30);

        lblP5_last_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_2.setText("-");
        lblP5_last_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_2.setOpaque(true);
        add(lblP5_last_2);
        lblP5_last_2.setBounds(180, 30, 60, 30);

        lblDuration_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_3.setText("-");
        lblDuration_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_3.setOpaque(true);
        add(lblDuration_3);
        lblDuration_3.setBounds(0, 60, 30, 30);

        lblPower_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_3.setText("-");
        lblPower_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_3.setOpaque(true);
        add(lblPower_3);
        lblPower_3.setBounds(30, 60, 30, 30);

        lblP5_start_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_3.setText("-");
        lblP5_start_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_3.setOpaque(true);
        add(lblP5_start_3);
        lblP5_start_3.setBounds(60, 60, 60, 30);

        lblP5_max_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_3.setText("-");
        lblP5_max_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_3.setOpaque(true);
        add(lblP5_max_3);
        lblP5_max_3.setBounds(120, 60, 60, 30);

        lblP5_last_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_3.setText("-");
        lblP5_last_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_3.setOpaque(true);
        add(lblP5_last_3);
        lblP5_last_3.setBounds(180, 60, 60, 30);

        lblDuration_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_4.setText("-");
        lblDuration_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_4.setOpaque(true);
        add(lblDuration_4);
        lblDuration_4.setBounds(0, 90, 30, 30);

        lblPower_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_4.setText("-");
        lblPower_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_4.setOpaque(true);
        add(lblPower_4);
        lblPower_4.setBounds(30, 90, 30, 30);

        lblP5_start_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_4.setText("-");
        lblP5_start_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_4.setOpaque(true);
        add(lblP5_start_4);
        lblP5_start_4.setBounds(60, 90, 60, 30);

        lblP5_max_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_4.setText("-");
        lblP5_max_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_4.setOpaque(true);
        add(lblP5_max_4);
        lblP5_max_4.setBounds(120, 90, 60, 30);

        lblP5_last_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_4.setText("-");
        lblP5_last_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_4.setOpaque(true);
        add(lblP5_last_4);
        lblP5_last_4.setBounds(180, 90, 60, 30);

        lblDuration_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_5.setText("5'");
        lblDuration_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_5.setOpaque(true);
        add(lblDuration_5);
        lblDuration_5.setBounds(0, 120, 30, 30);

        lblPower_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_5.setText("-");
        lblPower_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_5.setOpaque(true);
        add(lblPower_5);
        lblPower_5.setBounds(30, 120, 30, 30);

        lblP5_start_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_5.setText("-");
        lblP5_start_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_5.setOpaque(true);
        add(lblP5_start_5);
        lblP5_start_5.setBounds(60, 120, 60, 30);

        lblP5_max_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_5.setText("-");
        lblP5_max_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_5.setOpaque(true);
        add(lblP5_max_5);
        lblP5_max_5.setBounds(120, 120, 60, 30);

        lblP5_last_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_5.setText("-");
        lblP5_last_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_5.setOpaque(true);
        add(lblP5_last_5);
        lblP5_last_5.setBounds(180, 120, 60, 30);

        lblDuration_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_6.setText("-");
        lblDuration_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_6.setOpaque(true);
        add(lblDuration_6);
        lblDuration_6.setBounds(0, 150, 30, 30);

        lblPower_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_6.setText("-");
        lblPower_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_6.setOpaque(true);
        add(lblPower_6);
        lblPower_6.setBounds(30, 150, 30, 30);

        lblP5_start_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_6.setText("-");
        lblP5_start_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_6.setOpaque(true);
        add(lblP5_start_6);
        lblP5_start_6.setBounds(60, 150, 60, 30);

        lblP5_max_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_6.setText("-");
        lblP5_max_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_6.setOpaque(true);
        add(lblP5_max_6);
        lblP5_max_6.setBounds(120, 150, 60, 30);

        lblP5_last_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_6.setText("-");
        lblP5_last_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_6.setOpaque(true);
        add(lblP5_last_6);
        lblP5_last_6.setBounds(180, 150, 60, 30);

        lblDuration_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_7.setText("-");
        lblDuration_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_7.setOpaque(true);
        add(lblDuration_7);
        lblDuration_7.setBounds(0, 180, 30, 30);

        lblPower_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_7.setText("-");
        lblPower_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_7.setOpaque(true);
        add(lblPower_7);
        lblPower_7.setBounds(30, 180, 30, 30);

        lblP5_start_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_7.setText("-");
        lblP5_start_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_7.setOpaque(true);
        add(lblP5_start_7);
        lblP5_start_7.setBounds(60, 180, 60, 30);

        lblP5_max_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_7.setText("-");
        lblP5_max_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_7.setOpaque(true);
        add(lblP5_max_7);
        lblP5_max_7.setBounds(120, 180, 60, 30);

        lblP5_last_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_7.setText("-");
        lblP5_last_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_7.setOpaque(true);
        add(lblP5_last_7);
        lblP5_last_7.setBounds(180, 180, 60, 30);

        lblDuration_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_8.setText("-");
        lblDuration_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_8.setOpaque(true);
        add(lblDuration_8);
        lblDuration_8.setBounds(0, 210, 30, 30);

        lblPower_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_8.setText("-");
        lblPower_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_8.setOpaque(true);
        add(lblPower_8);
        lblPower_8.setBounds(30, 210, 30, 30);

        lblP5_start_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_8.setText("-");
        lblP5_start_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_8.setOpaque(true);
        add(lblP5_start_8);
        lblP5_start_8.setBounds(60, 210, 60, 30);

        lblP5_max_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_8.setText("-");
        lblP5_max_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_8.setOpaque(true);
        add(lblP5_max_8);
        lblP5_max_8.setBounds(120, 210, 60, 30);

        lblP5_last_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_8.setText("-");
        lblP5_last_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_8.setOpaque(true);
        add(lblP5_last_8);
        lblP5_last_8.setBounds(180, 210, 60, 30);

        lblDuration_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_9.setText("-");
        lblDuration_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_9.setOpaque(true);
        add(lblDuration_9);
        lblDuration_9.setBounds(0, 240, 30, 30);

        lblPower_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_9.setText("-");
        lblPower_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_9.setOpaque(true);
        add(lblPower_9);
        lblPower_9.setBounds(30, 240, 30, 30);

        lblP5_start_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_9.setText("-");
        lblP5_start_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_9.setOpaque(true);
        add(lblP5_start_9);
        lblP5_start_9.setBounds(60, 240, 60, 30);

        lblP5_max_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_9.setText("-");
        lblP5_max_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_9.setOpaque(true);
        add(lblP5_max_9);
        lblP5_max_9.setBounds(120, 240, 60, 30);

        lblP5_last_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_9.setText("-");
        lblP5_last_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_9.setOpaque(true);
        add(lblP5_last_9);
        lblP5_last_9.setBounds(180, 240, 60, 30);

        lblDuration_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_10.setText("10'");
        lblDuration_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_10.setOpaque(true);
        add(lblDuration_10);
        lblDuration_10.setBounds(0, 270, 30, 30);

        lblPower_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_10.setText("-");
        lblPower_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_10.setOpaque(true);
        add(lblPower_10);
        lblPower_10.setBounds(30, 270, 30, 30);

        lblP5_start_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_10.setText("-");
        lblP5_start_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_10.setOpaque(true);
        add(lblP5_start_10);
        lblP5_start_10.setBounds(60, 270, 60, 30);

        lblP5_max_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_10.setText("-");
        lblP5_max_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_10.setOpaque(true);
        add(lblP5_max_10);
        lblP5_max_10.setBounds(120, 270, 60, 30);

        lblP5_last_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_10.setText("-");
        lblP5_last_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_10.setOpaque(true);
        add(lblP5_last_10);
        lblP5_last_10.setBounds(180, 270, 60, 30);

        lblDuration_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_11.setText("-");
        lblDuration_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_11.setOpaque(true);
        add(lblDuration_11);
        lblDuration_11.setBounds(0, 300, 30, 30);

        lblPower_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_11.setText("-");
        lblPower_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_11.setOpaque(true);
        add(lblPower_11);
        lblPower_11.setBounds(30, 300, 30, 30);

        lblP5_start_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_11.setText("-");
        lblP5_start_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_11.setOpaque(true);
        add(lblP5_start_11);
        lblP5_start_11.setBounds(60, 300, 60, 30);

        lblP5_max_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_11.setText("-");
        lblP5_max_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_11.setOpaque(true);
        add(lblP5_max_11);
        lblP5_max_11.setBounds(120, 300, 60, 30);

        lblP5_last_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_11.setText("-");
        lblP5_last_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_11.setOpaque(true);
        add(lblP5_last_11);
        lblP5_last_11.setBounds(180, 300, 60, 30);

        lblDuration_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_12.setText("-");
        lblDuration_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_12.setOpaque(true);
        add(lblDuration_12);
        lblDuration_12.setBounds(0, 330, 30, 30);

        lblPower_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_12.setText("-");
        lblPower_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_12.setOpaque(true);
        add(lblPower_12);
        lblPower_12.setBounds(30, 330, 30, 30);

        lblP5_start_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_12.setText("-");
        lblP5_start_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_12.setOpaque(true);
        add(lblP5_start_12);
        lblP5_start_12.setBounds(60, 330, 60, 30);

        lblP5_max_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_12.setText("-");
        lblP5_max_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_12.setOpaque(true);
        add(lblP5_max_12);
        lblP5_max_12.setBounds(120, 330, 60, 30);

        lblP5_last_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_12.setText("-");
        lblP5_last_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_12.setOpaque(true);
        add(lblP5_last_12);
        lblP5_last_12.setBounds(180, 330, 60, 30);

        lblDuration_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_13.setText("-");
        lblDuration_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_13.setOpaque(true);
        add(lblDuration_13);
        lblDuration_13.setBounds(0, 360, 30, 30);

        lblPower_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_13.setText("-");
        lblPower_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_13.setOpaque(true);
        add(lblPower_13);
        lblPower_13.setBounds(30, 360, 30, 30);

        lblP5_start_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_13.setText("-");
        lblP5_start_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_13.setOpaque(true);
        add(lblP5_start_13);
        lblP5_start_13.setBounds(60, 360, 60, 30);

        lblP5_max_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_13.setText("-");
        lblP5_max_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_13.setOpaque(true);
        add(lblP5_max_13);
        lblP5_max_13.setBounds(120, 360, 60, 30);

        lblP5_last_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_13.setText("-");
        lblP5_last_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_13.setOpaque(true);
        add(lblP5_last_13);
        lblP5_last_13.setBounds(180, 360, 60, 30);

        lblDuration_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_14.setText("-");
        lblDuration_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_14.setOpaque(true);
        add(lblDuration_14);
        lblDuration_14.setBounds(0, 390, 30, 30);

        lblPower_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_14.setText("-");
        lblPower_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_14.setOpaque(true);
        add(lblPower_14);
        lblPower_14.setBounds(30, 390, 30, 30);

        lblP5_start_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_14.setText("-");
        lblP5_start_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_14.setOpaque(true);
        add(lblP5_start_14);
        lblP5_start_14.setBounds(60, 390, 60, 30);

        lblP5_max_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_14.setText("-");
        lblP5_max_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_14.setOpaque(true);
        add(lblP5_max_14);
        lblP5_max_14.setBounds(120, 390, 60, 30);

        lblP5_last_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_14.setText("-");
        lblP5_last_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_14.setOpaque(true);
        add(lblP5_last_14);
        lblP5_last_14.setBounds(180, 390, 60, 30);

        lblDuration_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_15.setText("15'");
        lblDuration_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_15.setOpaque(true);
        add(lblDuration_15);
        lblDuration_15.setBounds(0, 420, 30, 30);

        lblPower_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_15.setText("-");
        lblPower_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_15.setOpaque(true);
        add(lblPower_15);
        lblPower_15.setBounds(30, 420, 30, 30);

        lblP5_start_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_15.setText("-");
        lblP5_start_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_15.setOpaque(true);
        add(lblP5_start_15);
        lblP5_start_15.setBounds(60, 420, 60, 30);

        lblP5_max_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_15.setText("-");
        lblP5_max_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_15.setOpaque(true);
        add(lblP5_max_15);
        lblP5_max_15.setBounds(120, 420, 60, 30);

        lblP5_last_15.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_15.setText("-");
        lblP5_last_15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_15.setOpaque(true);
        add(lblP5_last_15);
        lblP5_last_15.setBounds(180, 420, 60, 30);

        lblDuration_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_16.setText("-");
        lblDuration_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_16.setOpaque(true);
        add(lblDuration_16);
        lblDuration_16.setBounds(0, 450, 30, 30);

        lblPower_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_16.setText("-");
        lblPower_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_16.setOpaque(true);
        add(lblPower_16);
        lblPower_16.setBounds(30, 450, 30, 30);

        lblP5_start_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_16.setText("-");
        lblP5_start_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_16.setOpaque(true);
        add(lblP5_start_16);
        lblP5_start_16.setBounds(60, 450, 60, 30);

        lblP5_max_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_16.setText("-");
        lblP5_max_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_16.setOpaque(true);
        add(lblP5_max_16);
        lblP5_max_16.setBounds(120, 450, 60, 30);

        lblP5_last_16.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_16.setText("-");
        lblP5_last_16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_16.setOpaque(true);
        add(lblP5_last_16);
        lblP5_last_16.setBounds(180, 450, 60, 30);

        lblDuration_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_17.setText("-");
        lblDuration_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_17.setOpaque(true);
        add(lblDuration_17);
        lblDuration_17.setBounds(0, 480, 30, 30);

        lblPower_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_17.setText("-");
        lblPower_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_17.setOpaque(true);
        add(lblPower_17);
        lblPower_17.setBounds(30, 480, 30, 30);

        lblP5_start_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_17.setText("-");
        lblP5_start_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_17.setOpaque(true);
        add(lblP5_start_17);
        lblP5_start_17.setBounds(60, 480, 60, 30);

        lblP5_max_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_17.setText("-");
        lblP5_max_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_17.setOpaque(true);
        add(lblP5_max_17);
        lblP5_max_17.setBounds(120, 480, 60, 30);

        lblP5_last_17.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_17.setText("-");
        lblP5_last_17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_17.setOpaque(true);
        add(lblP5_last_17);
        lblP5_last_17.setBounds(180, 480, 60, 30);

        lblDuration_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_18.setText("-");
        lblDuration_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_18.setOpaque(true);
        add(lblDuration_18);
        lblDuration_18.setBounds(0, 510, 30, 30);

        lblPower_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_18.setText("-");
        lblPower_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_18.setOpaque(true);
        add(lblPower_18);
        lblPower_18.setBounds(30, 510, 30, 30);

        lblP5_start_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_18.setText("-");
        lblP5_start_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_18.setOpaque(true);
        add(lblP5_start_18);
        lblP5_start_18.setBounds(60, 510, 60, 30);

        lblP5_max_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_18.setText("-");
        lblP5_max_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_18.setOpaque(true);
        add(lblP5_max_18);
        lblP5_max_18.setBounds(120, 510, 60, 30);

        lblP5_last_18.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_18.setText("-");
        lblP5_last_18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_18.setOpaque(true);
        add(lblP5_last_18);
        lblP5_last_18.setBounds(180, 510, 60, 30);

        lblDuration_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_19.setText("-");
        lblDuration_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_19.setOpaque(true);
        add(lblDuration_19);
        lblDuration_19.setBounds(0, 540, 30, 30);

        lblPower_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_19.setText("-");
        lblPower_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_19.setOpaque(true);
        add(lblPower_19);
        lblPower_19.setBounds(30, 540, 30, 30);

        lblP5_start_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_19.setText("-");
        lblP5_start_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_19.setOpaque(true);
        add(lblP5_start_19);
        lblP5_start_19.setBounds(60, 540, 60, 30);

        lblP5_max_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_19.setText("-");
        lblP5_max_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_19.setOpaque(true);
        add(lblP5_max_19);
        lblP5_max_19.setBounds(120, 540, 60, 30);

        lblP5_last_19.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_19.setText("-");
        lblP5_last_19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_19.setOpaque(true);
        add(lblP5_last_19);
        lblP5_last_19.setBounds(180, 540, 60, 30);

        lblDuration_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_20.setText("20'");
        lblDuration_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_20.setOpaque(true);
        add(lblDuration_20);
        lblDuration_20.setBounds(0, 570, 30, 30);

        lblPower_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_20.setText("-");
        lblPower_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_20.setOpaque(true);
        add(lblPower_20);
        lblPower_20.setBounds(30, 570, 30, 30);

        lblP5_start_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_20.setText("-");
        lblP5_start_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_20.setOpaque(true);
        add(lblP5_start_20);
        lblP5_start_20.setBounds(60, 570, 60, 30);

        lblP5_max_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_20.setText("-");
        lblP5_max_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_20.setOpaque(true);
        add(lblP5_max_20);
        lblP5_max_20.setBounds(120, 570, 60, 30);

        lblP5_last_20.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_20.setText("-");
        lblP5_last_20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_20.setOpaque(true);
        add(lblP5_last_20);
        lblP5_last_20.setBounds(180, 570, 60, 30);

        lblDuration_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_21.setText("-");
        lblDuration_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_21.setOpaque(true);
        add(lblDuration_21);
        lblDuration_21.setBounds(0, 600, 30, 30);

        lblPower_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_21.setText("-");
        lblPower_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_21.setOpaque(true);
        add(lblPower_21);
        lblPower_21.setBounds(30, 600, 30, 30);

        lblP5_start_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_21.setText("-");
        lblP5_start_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_21.setOpaque(true);
        add(lblP5_start_21);
        lblP5_start_21.setBounds(60, 600, 60, 30);

        lblP5_max_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_21.setText("-");
        lblP5_max_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_21.setOpaque(true);
        add(lblP5_max_21);
        lblP5_max_21.setBounds(120, 600, 60, 30);

        lblP5_last_21.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_21.setText("-");
        lblP5_last_21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_21.setOpaque(true);
        add(lblP5_last_21);
        lblP5_last_21.setBounds(180, 600, 60, 30);

        lblDuration_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_22.setText("-");
        lblDuration_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_22.setOpaque(true);
        add(lblDuration_22);
        lblDuration_22.setBounds(0, 630, 30, 30);

        lblPower_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_22.setText("-");
        lblPower_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_22.setOpaque(true);
        add(lblPower_22);
        lblPower_22.setBounds(30, 630, 30, 30);

        lblP5_start_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_22.setText("-");
        lblP5_start_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_22.setOpaque(true);
        add(lblP5_start_22);
        lblP5_start_22.setBounds(60, 630, 60, 30);

        lblP5_max_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_22.setText("-");
        lblP5_max_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_22.setOpaque(true);
        add(lblP5_max_22);
        lblP5_max_22.setBounds(120, 630, 60, 30);

        lblP5_last_22.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_22.setText("-");
        lblP5_last_22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_22.setOpaque(true);
        add(lblP5_last_22);
        lblP5_last_22.setBounds(180, 630, 60, 30);

        lblDuration_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_23.setText("-");
        lblDuration_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_23.setOpaque(true);
        add(lblDuration_23);
        lblDuration_23.setBounds(0, 660, 30, 30);

        lblPower_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_23.setText("-");
        lblPower_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_23.setOpaque(true);
        add(lblPower_23);
        lblPower_23.setBounds(30, 660, 30, 30);

        lblP5_start_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_23.setText("-");
        lblP5_start_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_23.setOpaque(true);
        add(lblP5_start_23);
        lblP5_start_23.setBounds(60, 660, 60, 30);

        lblP5_max_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_23.setText("-");
        lblP5_max_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_23.setOpaque(true);
        add(lblP5_max_23);
        lblP5_max_23.setBounds(120, 660, 60, 30);

        lblP5_last_23.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_23.setText("-");
        lblP5_last_23.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_23.setOpaque(true);
        add(lblP5_last_23);
        lblP5_last_23.setBounds(180, 660, 60, 30);

        lblDuration_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_24.setText("-");
        lblDuration_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_24.setOpaque(true);
        add(lblDuration_24);
        lblDuration_24.setBounds(0, 690, 30, 30);

        lblPower_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_24.setText("-");
        lblPower_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_24.setOpaque(true);
        add(lblPower_24);
        lblPower_24.setBounds(30, 690, 30, 30);

        lblP5_start_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_24.setText("-");
        lblP5_start_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_24.setOpaque(true);
        add(lblP5_start_24);
        lblP5_start_24.setBounds(60, 690, 60, 30);

        lblP5_max_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_24.setText("-");
        lblP5_max_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_24.setOpaque(true);
        add(lblP5_max_24);
        lblP5_max_24.setBounds(120, 690, 60, 30);

        lblP5_last_24.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_24.setText("-");
        lblP5_last_24.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_24.setOpaque(true);
        add(lblP5_last_24);
        lblP5_last_24.setBounds(180, 690, 60, 30);

        lblDuration_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_25.setText("25'");
        lblDuration_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_25.setOpaque(true);
        add(lblDuration_25);
        lblDuration_25.setBounds(0, 720, 30, 30);

        lblPower_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_25.setText("-");
        lblPower_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_25.setOpaque(true);
        add(lblPower_25);
        lblPower_25.setBounds(30, 720, 30, 30);

        lblP5_start_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_25.setText("-");
        lblP5_start_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_25.setOpaque(true);
        add(lblP5_start_25);
        lblP5_start_25.setBounds(60, 720, 60, 30);

        lblP5_max_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_25.setText("-");
        lblP5_max_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_25.setOpaque(true);
        add(lblP5_max_25);
        lblP5_max_25.setBounds(120, 720, 60, 30);

        lblP5_last_25.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_25.setText("-");
        lblP5_last_25.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_25.setOpaque(true);
        add(lblP5_last_25);
        lblP5_last_25.setBounds(180, 720, 60, 30);

        lblDuration_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_26.setText("-");
        lblDuration_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_26.setOpaque(true);
        add(lblDuration_26);
        lblDuration_26.setBounds(0, 750, 30, 30);

        lblPower_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_26.setText("-");
        lblPower_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_26.setOpaque(true);
        add(lblPower_26);
        lblPower_26.setBounds(30, 750, 30, 30);

        lblP5_start_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_26.setText("-");
        lblP5_start_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_26.setOpaque(true);
        add(lblP5_start_26);
        lblP5_start_26.setBounds(60, 750, 60, 30);

        lblP5_max_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_26.setText("-");
        lblP5_max_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_26.setOpaque(true);
        add(lblP5_max_26);
        lblP5_max_26.setBounds(120, 750, 60, 30);

        lblP5_last_26.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_26.setText("-");
        lblP5_last_26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_26.setOpaque(true);
        add(lblP5_last_26);
        lblP5_last_26.setBounds(180, 750, 60, 30);

        lblDuration_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_27.setText("-");
        lblDuration_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_27.setOpaque(true);
        add(lblDuration_27);
        lblDuration_27.setBounds(0, 780, 30, 30);

        lblPower_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_27.setText("-");
        lblPower_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_27.setOpaque(true);
        add(lblPower_27);
        lblPower_27.setBounds(30, 780, 30, 30);

        lblP5_start_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_27.setText("-");
        lblP5_start_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_27.setOpaque(true);
        add(lblP5_start_27);
        lblP5_start_27.setBounds(60, 780, 60, 30);

        lblP5_max_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_27.setText("-");
        lblP5_max_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_27.setOpaque(true);
        add(lblP5_max_27);
        lblP5_max_27.setBounds(120, 780, 60, 30);

        lblP5_last_27.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_27.setText("-");
        lblP5_last_27.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_27.setOpaque(true);
        add(lblP5_last_27);
        lblP5_last_27.setBounds(180, 780, 60, 30);

        lblDuration_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_28.setText("-");
        lblDuration_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_28.setOpaque(true);
        add(lblDuration_28);
        lblDuration_28.setBounds(0, 810, 30, 30);

        lblPower_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_28.setText("-");
        lblPower_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_28.setOpaque(true);
        add(lblPower_28);
        lblPower_28.setBounds(30, 810, 30, 30);

        lblP5_start_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_28.setText("-");
        lblP5_start_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_28.setOpaque(true);
        add(lblP5_start_28);
        lblP5_start_28.setBounds(60, 810, 60, 30);

        lblP5_max_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_28.setText("-");
        lblP5_max_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_28.setOpaque(true);
        add(lblP5_max_28);
        lblP5_max_28.setBounds(120, 810, 60, 30);

        lblP5_last_28.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_28.setText("-");
        lblP5_last_28.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_28.setOpaque(true);
        add(lblP5_last_28);
        lblP5_last_28.setBounds(180, 810, 60, 30);

        lblDuration_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_29.setText("-");
        lblDuration_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_29.setOpaque(true);
        add(lblDuration_29);
        lblDuration_29.setBounds(0, 840, 30, 30);

        lblPower_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_29.setText("-");
        lblPower_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_29.setOpaque(true);
        add(lblPower_29);
        lblPower_29.setBounds(30, 840, 30, 30);

        lblP5_start_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_29.setText("-");
        lblP5_start_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_29.setOpaque(true);
        add(lblP5_start_29);
        lblP5_start_29.setBounds(60, 840, 60, 30);

        lblP5_max_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_29.setText("-");
        lblP5_max_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_29.setOpaque(true);
        add(lblP5_max_29);
        lblP5_max_29.setBounds(120, 840, 60, 30);

        lblP5_last_29.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_29.setText("-");
        lblP5_last_29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_29.setOpaque(true);
        add(lblP5_last_29);
        lblP5_last_29.setBounds(180, 840, 60, 30);

        lblDuration_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_30.setText("30'");
        lblDuration_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_30.setOpaque(true);
        add(lblDuration_30);
        lblDuration_30.setBounds(0, 870, 30, 30);

        lblPower_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_30.setText("-");
        lblPower_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_30.setOpaque(true);
        add(lblPower_30);
        lblPower_30.setBounds(30, 870, 30, 30);

        lblP5_start_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_30.setText("-");
        lblP5_start_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_30.setOpaque(true);
        add(lblP5_start_30);
        lblP5_start_30.setBounds(60, 870, 60, 30);

        lblP5_max_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_30.setText("-");
        lblP5_max_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_30.setOpaque(true);
        add(lblP5_max_30);
        lblP5_max_30.setBounds(120, 870, 60, 30);

        lblP5_last_30.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_30.setText("-");
        lblP5_last_30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_30.setOpaque(true);
        add(lblP5_last_30);
        lblP5_last_30.setBounds(180, 870, 60, 30);

        lblDuration_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_31.setText("-");
        lblDuration_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_31.setOpaque(true);
        add(lblDuration_31);
        lblDuration_31.setBounds(0, 900, 30, 30);

        lblPower_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_31.setText("-");
        lblPower_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_31.setOpaque(true);
        add(lblPower_31);
        lblPower_31.setBounds(30, 900, 30, 30);

        lblP5_start_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_31.setText("-");
        lblP5_start_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_31.setOpaque(true);
        add(lblP5_start_31);
        lblP5_start_31.setBounds(60, 900, 60, 30);

        lblP5_max_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_31.setText("-");
        lblP5_max_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_31.setOpaque(true);
        add(lblP5_max_31);
        lblP5_max_31.setBounds(120, 900, 60, 30);

        lblP5_last_31.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_31.setText("-");
        lblP5_last_31.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_31.setOpaque(true);
        add(lblP5_last_31);
        lblP5_last_31.setBounds(180, 900, 60, 30);

        lblDuration_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_32.setText("-");
        lblDuration_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_32.setOpaque(true);
        add(lblDuration_32);
        lblDuration_32.setBounds(0, 930, 30, 30);

        lblPower_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_32.setText("-");
        lblPower_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_32.setOpaque(true);
        add(lblPower_32);
        lblPower_32.setBounds(30, 930, 30, 30);

        lblP5_start_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_32.setText("-");
        lblP5_start_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_32.setOpaque(true);
        add(lblP5_start_32);
        lblP5_start_32.setBounds(60, 930, 60, 30);

        lblP5_max_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_32.setText("-");
        lblP5_max_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_32.setOpaque(true);
        add(lblP5_max_32);
        lblP5_max_32.setBounds(120, 930, 60, 30);

        lblP5_last_32.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_32.setText("-");
        lblP5_last_32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_32.setOpaque(true);
        add(lblP5_last_32);
        lblP5_last_32.setBounds(180, 930, 60, 30);

        lblDuration_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_33.setText("33'");
        lblDuration_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblDuration_33.setOpaque(true);
        add(lblDuration_33);
        lblDuration_33.setBounds(0, 960, 30, 30);

        lblPower_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_33.setText("-");
        lblPower_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblPower_33.setOpaque(true);
        add(lblPower_33);
        lblPower_33.setBounds(30, 960, 30, 30);

        lblP5_start_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_33.setText("-");
        lblP5_start_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblP5_start_33.setOpaque(true);
        add(lblP5_start_33);
        lblP5_start_33.setBounds(60, 960, 60, 30);

        lblP5_max_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_33.setText("-");
        lblP5_max_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblP5_max_33.setOpaque(true);
        add(lblP5_max_33);
        lblP5_max_33.setBounds(120, 960, 60, 30);

        lblP5_last_33.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_33.setText("-");
        lblP5_last_33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(120, 120, 120), 1, true));
        lblP5_last_33.setOpaque(true);
        add(lblP5_last_33);
        lblP5_last_33.setBounds(180, 960, 60, 30);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDuration_1;
    private javax.swing.JLabel lblDuration_10;
    private javax.swing.JLabel lblDuration_11;
    private javax.swing.JLabel lblDuration_12;
    private javax.swing.JLabel lblDuration_13;
    private javax.swing.JLabel lblDuration_14;
    private javax.swing.JLabel lblDuration_15;
    private javax.swing.JLabel lblDuration_16;
    private javax.swing.JLabel lblDuration_17;
    private javax.swing.JLabel lblDuration_18;
    private javax.swing.JLabel lblDuration_19;
    private javax.swing.JLabel lblDuration_2;
    private javax.swing.JLabel lblDuration_20;
    private javax.swing.JLabel lblDuration_21;
    private javax.swing.JLabel lblDuration_22;
    private javax.swing.JLabel lblDuration_23;
    private javax.swing.JLabel lblDuration_24;
    private javax.swing.JLabel lblDuration_25;
    private javax.swing.JLabel lblDuration_26;
    private javax.swing.JLabel lblDuration_27;
    private javax.swing.JLabel lblDuration_28;
    private javax.swing.JLabel lblDuration_29;
    private javax.swing.JLabel lblDuration_3;
    private javax.swing.JLabel lblDuration_30;
    private javax.swing.JLabel lblDuration_31;
    private javax.swing.JLabel lblDuration_32;
    private javax.swing.JLabel lblDuration_33;
    private javax.swing.JLabel lblDuration_4;
    private javax.swing.JLabel lblDuration_5;
    private javax.swing.JLabel lblDuration_6;
    private javax.swing.JLabel lblDuration_7;
    private javax.swing.JLabel lblDuration_8;
    private javax.swing.JLabel lblDuration_9;
    private javax.swing.JLabel lblP5_last_1;
    private javax.swing.JLabel lblP5_last_10;
    private javax.swing.JLabel lblP5_last_11;
    private javax.swing.JLabel lblP5_last_12;
    private javax.swing.JLabel lblP5_last_13;
    private javax.swing.JLabel lblP5_last_14;
    private javax.swing.JLabel lblP5_last_15;
    private javax.swing.JLabel lblP5_last_16;
    private javax.swing.JLabel lblP5_last_17;
    private javax.swing.JLabel lblP5_last_18;
    private javax.swing.JLabel lblP5_last_19;
    private javax.swing.JLabel lblP5_last_2;
    private javax.swing.JLabel lblP5_last_20;
    private javax.swing.JLabel lblP5_last_21;
    private javax.swing.JLabel lblP5_last_22;
    private javax.swing.JLabel lblP5_last_23;
    private javax.swing.JLabel lblP5_last_24;
    private javax.swing.JLabel lblP5_last_25;
    private javax.swing.JLabel lblP5_last_26;
    private javax.swing.JLabel lblP5_last_27;
    private javax.swing.JLabel lblP5_last_28;
    private javax.swing.JLabel lblP5_last_29;
    private javax.swing.JLabel lblP5_last_3;
    private javax.swing.JLabel lblP5_last_30;
    private javax.swing.JLabel lblP5_last_31;
    private javax.swing.JLabel lblP5_last_32;
    private javax.swing.JLabel lblP5_last_33;
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
    private javax.swing.JLabel lblP5_max_18;
    private javax.swing.JLabel lblP5_max_19;
    private javax.swing.JLabel lblP5_max_2;
    private javax.swing.JLabel lblP5_max_20;
    private javax.swing.JLabel lblP5_max_21;
    private javax.swing.JLabel lblP5_max_22;
    private javax.swing.JLabel lblP5_max_23;
    private javax.swing.JLabel lblP5_max_24;
    private javax.swing.JLabel lblP5_max_25;
    private javax.swing.JLabel lblP5_max_26;
    private javax.swing.JLabel lblP5_max_27;
    private javax.swing.JLabel lblP5_max_28;
    private javax.swing.JLabel lblP5_max_29;
    private javax.swing.JLabel lblP5_max_3;
    private javax.swing.JLabel lblP5_max_30;
    private javax.swing.JLabel lblP5_max_31;
    private javax.swing.JLabel lblP5_max_32;
    private javax.swing.JLabel lblP5_max_33;
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
    private javax.swing.JLabel lblP5_start_18;
    private javax.swing.JLabel lblP5_start_19;
    private javax.swing.JLabel lblP5_start_2;
    private javax.swing.JLabel lblP5_start_20;
    private javax.swing.JLabel lblP5_start_21;
    private javax.swing.JLabel lblP5_start_22;
    private javax.swing.JLabel lblP5_start_23;
    private javax.swing.JLabel lblP5_start_24;
    private javax.swing.JLabel lblP5_start_25;
    private javax.swing.JLabel lblP5_start_26;
    private javax.swing.JLabel lblP5_start_27;
    private javax.swing.JLabel lblP5_start_28;
    private javax.swing.JLabel lblP5_start_29;
    private javax.swing.JLabel lblP5_start_3;
    private javax.swing.JLabel lblP5_start_30;
    private javax.swing.JLabel lblP5_start_31;
    private javax.swing.JLabel lblP5_start_32;
    private javax.swing.JLabel lblP5_start_33;
    private javax.swing.JLabel lblP5_start_4;
    private javax.swing.JLabel lblP5_start_5;
    private javax.swing.JLabel lblP5_start_6;
    private javax.swing.JLabel lblP5_start_7;
    private javax.swing.JLabel lblP5_start_8;
    private javax.swing.JLabel lblP5_start_9;
    private javax.swing.JLabel lblPower_1;
    private javax.swing.JLabel lblPower_10;
    private javax.swing.JLabel lblPower_11;
    private javax.swing.JLabel lblPower_12;
    private javax.swing.JLabel lblPower_13;
    private javax.swing.JLabel lblPower_14;
    private javax.swing.JLabel lblPower_15;
    private javax.swing.JLabel lblPower_16;
    private javax.swing.JLabel lblPower_17;
    private javax.swing.JLabel lblPower_18;
    private javax.swing.JLabel lblPower_19;
    private javax.swing.JLabel lblPower_2;
    private javax.swing.JLabel lblPower_20;
    private javax.swing.JLabel lblPower_21;
    private javax.swing.JLabel lblPower_22;
    private javax.swing.JLabel lblPower_23;
    private javax.swing.JLabel lblPower_24;
    private javax.swing.JLabel lblPower_25;
    private javax.swing.JLabel lblPower_26;
    private javax.swing.JLabel lblPower_27;
    private javax.swing.JLabel lblPower_28;
    private javax.swing.JLabel lblPower_29;
    private javax.swing.JLabel lblPower_3;
    private javax.swing.JLabel lblPower_30;
    private javax.swing.JLabel lblPower_31;
    private javax.swing.JLabel lblPower_32;
    private javax.swing.JLabel lblPower_33;
    private javax.swing.JLabel lblPower_4;
    private javax.swing.JLabel lblPower_5;
    private javax.swing.JLabel lblPower_6;
    private javax.swing.JLabel lblPower_7;
    private javax.swing.JLabel lblPower_8;
    private javax.swing.JLabel lblPower_9;
    // End of variables declaration//GEN-END:variables
}
