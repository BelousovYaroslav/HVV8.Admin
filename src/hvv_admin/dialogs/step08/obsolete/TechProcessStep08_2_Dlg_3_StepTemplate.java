/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step08.obsolete;

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
public class TechProcessStep08_2_Dlg_3_StepTemplate extends javax.swing.JPanel {

    private final int m_nDevice;
    private final HVV_Admin theApp;

    //private final LineBorder m_borderGrey;
    //private final LineBorder m_borderRed;
    Color m_niceRed;
    
    /**
     * Creates new form TechProcessStep08_2_Dlg_3_StepTemplate2
     */
    public TechProcessStep08_2_Dlg_3_StepTemplate( HVV_Admin app, int nDevice) {
        theApp = app;
        initComponents();
        m_nDevice = nDevice;
        
        m_niceRed = new Color( 250, 125, 125);
        //m_borderGrey = new LineBorder( new Color( 220, 220, 220), 1, true);
        //m_borderRed =  new LineBorder( new Color( 200,  30,  30), 1, true);
    }

    /*
    public void ColorPressureLabel( Double dbl, JLabel lbl) {
        if( dbl.isNaN()) {
            lbl.setBackground( null);
            return;
        }
        
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
        }
    }
    */
    
    /*
    public void ColorPressureLabel( Double dbl, JLabel lbl) {
        if( dbl.isNaN()) {
            lbl.setBackground( null);
            return;
        }
        
        int nExp = ( int) Math.log10( dbl);
        if( nExp < 0) nExp--;
        
        float dblMant = ( float) ( dbl / Math.pow( 10, nExp));
        if( nExp == -5)
            lbl.setBorder( m_borderRed);
        else
            lbl.setBorder( m_borderGrey);
    }
    */
    
    public void ColorPressureLabel( Double dbl, JLabel lbl) {
        if( dbl.isNaN()) {
            lbl.setBackground( null);
            return;
        }
        
        int nExp = ( int) Math.log10( dbl);
        if( nExp < 0) nExp--;
        
        float dblMant = ( float) ( dbl / Math.pow( 10, nExp));
        if( nExp == -5)
            lbl.setBackground(m_niceRed);
        else
            lbl.setBackground( null);
    }
    
    public void RefreshData() {
        JLabel [] arrDurations = { lblDuration_1,  lblDuration_2,  lblDuration_3,  lblDuration_4,
                                   lblDuration_5,  lblDuration_6,  lblDuration_7,  lblDuration_8,
                                   lblDuration_9,  lblDuration_10, lblDuration_11, lblDuration_12,
                                   lblDuration_13, lblDuration_14};
        
        JLabel [] arrPowers = {    lblPower_1,  lblPower_2,  lblPower_3,  lblPower_4,
                                   lblPower_5,  lblPower_6,  lblPower_7,  lblPower_8,
                                   lblPower_9,  lblPower_10, lblPower_11, lblPower_12,
                                   lblPower_13, lblPower_14};
        
        JLabel [] arrP5start = {   lblP5_start_1,  lblP5_start_2,  lblP5_start_3,  lblP5_start_4,
                                   lblP5_start_5,  lblP5_start_6,  lblP5_start_7,  lblP5_start_8,
                                   lblP5_start_9,  lblP5_start_10, lblP5_start_11, lblP5_start_12,
                                   lblP5_start_13, lblP5_start_14};
        
        JLabel [] arrP5max = {     lblP5_max_1,  lblP5_max_2,  lblP5_max_3,  lblP5_max_4,
                                   lblP5_max_5,  lblP5_max_6,  lblP5_max_7,  lblP5_max_8,
                                   lblP5_max_9,  lblP5_max_10, lblP5_max_11, lblP5_max_12,
                                   lblP5_max_13, lblP5_max_14};
        
        JLabel [] arrP5last = {    lblP5_last_1,  lblP5_last_2,  lblP5_last_3,  lblP5_last_4,
                                   lblP5_last_5,  lblP5_last_6,  lblP5_last_7,  lblP5_last_8,
                                   lblP5_last_9,  lblP5_last_10, lblP5_last_11, lblP5_last_12,
                                   lblP5_last_13, lblP5_last_14};
        
        GettersActivationProgram prg;
        LinkedList lstDegassing = null;
        if( theApp.m_mapDegassing.containsKey( m_nDevice)) {
            prg = ( GettersActivationProgram) theApp.m_mapDegassing.get( m_nDevice);
            lstDegassing = prg.GetListSteps();
        }
        
        for( int i=0; i<14; i++) {
            if( lstDegassing != null) {
                if( i  < lstDegassing.size()) {
                    GettersActivationProgramStep step = ( GettersActivationProgramStep) lstDegassing.get( i);

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
                        arrP5start[i].setText( "-");    arrP5start[i].setBackground( null); //arrP5start[i].setBorder( m_borderGrey);
                    }
                    
                    //P5_MAX
                    if( step.GetP5_max() != 0.) {
                        arrP5max[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_max(), false) + "</html>");
                        ColorPressureLabel( step.GetP5_max(), arrP5max[i]);
                    }
                    else {
                        arrP5max[i].setText( "-");      arrP5max[i].setBackground( null);   //arrP5max[i].setBorder( m_borderGrey);
                    }
                    
                    //P5_LAST
                    if( step.GetP5_last() != 0.) {
                        arrP5last[i].setText( "<html>" + theApp.m_ReportGenerator.Gen_NiceDoubleP5( step.GetP5_last(), false) + "</html>");
                        ColorPressureLabel( step.GetP5_last(), arrP5last[i]);
                    }
                    else {
                        arrP5last[i].setText( "-");     arrP5last[i].setBackground( null);   //arrP5last[i].setBorder( m_borderGrey);
                    }
                }
                else {
                    arrDurations[i].setText(    "-");
                    arrPowers[i].setText(       "-");
                    arrP5start[i].setText(      "-");   arrP5start[i].setBackground( null); //arrP5start[i].setBorder( m_borderGrey);
                    arrP5max[i].setText(        "-");   arrP5max[i].setBackground( null);
                    arrP5last[i].setText(       "-");   arrP5last[i].setBackground( null);
                }
            }
            else {
                arrDurations[i].setText(    "-");
                arrPowers[i].setText(       "-");
                arrP5start[i].setText(      "-");   arrP5start[i].setBackground( null);
                arrP5max[i].setText(        "-");   arrP5max[i].setBackground( null);
                arrP5last[i].setText(       "-");   arrP5last[i].setBackground( null);
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

        setMaximumSize(new java.awt.Dimension(930, 150));
        setMinimumSize(new java.awt.Dimension(930, 150));
        setPreferredSize(new java.awt.Dimension(930, 150));
        setLayout(null);

        lblDuration_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblDuration_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_Title.setText("<html>Длительность</html>");
        lblDuration_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        add(lblDuration_Title);
        lblDuration_Title.setBounds(0, 0, 90, 30);

        lblPower_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblPower_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_Title.setText("<html>Мощность</html>");
        lblPower_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        add(lblPower_Title);
        lblPower_Title.setBounds(0, 30, 90, 30);

        lblP5start_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5start_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5start_Title.setText("<html>P<sub>5, начальное</sub></html>:");
        lblP5start_Title.setToolTipText("");
        lblP5start_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        add(lblP5start_Title);
        lblP5start_Title.setBounds(0, 60, 90, 30);

        lblP5max_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5max_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5max_Title.setText("<html>P<sub>5, макс.</sub></html>:");
        lblP5max_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        add(lblP5max_Title);
        lblP5max_Title.setBounds(0, 90, 90, 30);

        lblP5last_Title.setFont(new java.awt.Font("Cantarell", 1, 12)); // NOI18N
        lblP5last_Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5last_Title.setText("<html>P<sub>5, конечное</sub></html>:");
        lblP5last_Title.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        add(lblP5last_Title);
        lblP5last_Title.setBounds(0, 120, 90, 30);

        lblDuration_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_1.setText("-");
        lblDuration_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_1.setOpaque(true);
        add(lblDuration_1);
        lblDuration_1.setBounds(90, 0, 60, 30);

        lblPower_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_1.setText("-");
        lblPower_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_1.setOpaque(true);
        add(lblPower_1);
        lblPower_1.setBounds(90, 30, 60, 30);

        lblP5_start_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_1.setText("-");
        lblP5_start_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_1.setOpaque(true);
        add(lblP5_start_1);
        lblP5_start_1.setBounds(90, 60, 60, 30);

        lblP5_max_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_1.setText("-");
        lblP5_max_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_1.setOpaque(true);
        add(lblP5_max_1);
        lblP5_max_1.setBounds(90, 90, 60, 30);

        lblP5_last_1.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_1.setText("-");
        lblP5_last_1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_1.setOpaque(true);
        add(lblP5_last_1);
        lblP5_last_1.setBounds(90, 120, 60, 30);

        lblDuration_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_2.setText("-");
        lblDuration_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_2.setOpaque(true);
        add(lblDuration_2);
        lblDuration_2.setBounds(150, 0, 60, 30);

        lblPower_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_2.setText("-");
        lblPower_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_2.setOpaque(true);
        add(lblPower_2);
        lblPower_2.setBounds(150, 30, 60, 30);

        lblP5_start_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_2.setText("-");
        lblP5_start_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_2.setOpaque(true);
        add(lblP5_start_2);
        lblP5_start_2.setBounds(150, 60, 60, 30);

        lblP5_max_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_2.setText("-");
        lblP5_max_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_2.setOpaque(true);
        add(lblP5_max_2);
        lblP5_max_2.setBounds(150, 90, 60, 30);

        lblP5_last_2.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_2.setText("-");
        lblP5_last_2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_2.setOpaque(true);
        add(lblP5_last_2);
        lblP5_last_2.setBounds(150, 120, 60, 30);

        lblDuration_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_3.setText("-");
        lblDuration_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_3.setOpaque(true);
        add(lblDuration_3);
        lblDuration_3.setBounds(210, 0, 60, 30);

        lblPower_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_3.setText("-");
        lblPower_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_3.setOpaque(true);
        add(lblPower_3);
        lblPower_3.setBounds(210, 30, 60, 30);

        lblP5_start_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_3.setText("-");
        lblP5_start_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_3.setOpaque(true);
        add(lblP5_start_3);
        lblP5_start_3.setBounds(210, 60, 60, 30);

        lblP5_max_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_3.setText("-");
        lblP5_max_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_3.setOpaque(true);
        add(lblP5_max_3);
        lblP5_max_3.setBounds(210, 90, 60, 30);

        lblP5_last_3.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_3.setText("-");
        lblP5_last_3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_3.setOpaque(true);
        add(lblP5_last_3);
        lblP5_last_3.setBounds(210, 120, 60, 30);

        lblDuration_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_4.setText("-");
        lblDuration_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_4.setOpaque(true);
        add(lblDuration_4);
        lblDuration_4.setBounds(270, 0, 60, 30);

        lblPower_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_4.setText("-");
        lblPower_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_4.setOpaque(true);
        add(lblPower_4);
        lblPower_4.setBounds(270, 30, 60, 30);

        lblP5_start_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_4.setText("-");
        lblP5_start_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_4.setOpaque(true);
        add(lblP5_start_4);
        lblP5_start_4.setBounds(270, 60, 60, 30);

        lblP5_max_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_4.setText("-");
        lblP5_max_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_4.setOpaque(true);
        add(lblP5_max_4);
        lblP5_max_4.setBounds(270, 90, 60, 30);

        lblP5_last_4.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_4.setText("-");
        lblP5_last_4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_4.setOpaque(true);
        add(lblP5_last_4);
        lblP5_last_4.setBounds(270, 120, 60, 30);

        lblDuration_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_5.setText("-");
        lblDuration_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_5.setOpaque(true);
        add(lblDuration_5);
        lblDuration_5.setBounds(330, 0, 60, 30);

        lblPower_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_5.setText("-");
        lblPower_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_5.setOpaque(true);
        add(lblPower_5);
        lblPower_5.setBounds(330, 30, 60, 30);

        lblP5_start_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_5.setText("-");
        lblP5_start_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_5.setOpaque(true);
        add(lblP5_start_5);
        lblP5_start_5.setBounds(330, 60, 60, 30);

        lblP5_max_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_5.setText("-");
        lblP5_max_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_5.setOpaque(true);
        add(lblP5_max_5);
        lblP5_max_5.setBounds(330, 90, 60, 30);

        lblP5_last_5.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_5.setText("-");
        lblP5_last_5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_5.setOpaque(true);
        add(lblP5_last_5);
        lblP5_last_5.setBounds(330, 120, 60, 30);

        lblDuration_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_6.setText("-");
        lblDuration_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_6.setOpaque(true);
        add(lblDuration_6);
        lblDuration_6.setBounds(390, 0, 60, 30);

        lblPower_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_6.setText("-");
        lblPower_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_6.setOpaque(true);
        add(lblPower_6);
        lblPower_6.setBounds(390, 30, 60, 30);

        lblP5_start_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_6.setText("-");
        lblP5_start_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_6.setOpaque(true);
        add(lblP5_start_6);
        lblP5_start_6.setBounds(390, 60, 60, 30);

        lblP5_max_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_6.setText("-");
        lblP5_max_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_6.setOpaque(true);
        add(lblP5_max_6);
        lblP5_max_6.setBounds(390, 90, 60, 30);

        lblP5_last_6.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_6.setText("-");
        lblP5_last_6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_6.setOpaque(true);
        add(lblP5_last_6);
        lblP5_last_6.setBounds(390, 120, 60, 30);

        lblDuration_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_7.setText("-");
        lblDuration_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_7.setOpaque(true);
        add(lblDuration_7);
        lblDuration_7.setBounds(450, 0, 60, 30);

        lblPower_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_7.setText("-");
        lblPower_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_7.setOpaque(true);
        add(lblPower_7);
        lblPower_7.setBounds(450, 30, 60, 30);

        lblP5_start_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_7.setText("-");
        lblP5_start_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_7.setOpaque(true);
        add(lblP5_start_7);
        lblP5_start_7.setBounds(450, 60, 60, 30);

        lblP5_max_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_7.setText("-");
        lblP5_max_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_7.setOpaque(true);
        add(lblP5_max_7);
        lblP5_max_7.setBounds(450, 90, 60, 30);

        lblP5_last_7.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_7.setText("-");
        lblP5_last_7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_7.setOpaque(true);
        add(lblP5_last_7);
        lblP5_last_7.setBounds(450, 120, 60, 30);

        lblDuration_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_8.setText("-");
        lblDuration_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_8.setOpaque(true);
        add(lblDuration_8);
        lblDuration_8.setBounds(510, 0, 60, 30);

        lblPower_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_8.setText("-");
        lblPower_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_8.setOpaque(true);
        add(lblPower_8);
        lblPower_8.setBounds(510, 30, 60, 30);

        lblP5_start_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_8.setText("-");
        lblP5_start_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_8.setOpaque(true);
        add(lblP5_start_8);
        lblP5_start_8.setBounds(510, 60, 60, 30);

        lblP5_max_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_8.setText("-");
        lblP5_max_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_8.setOpaque(true);
        add(lblP5_max_8);
        lblP5_max_8.setBounds(510, 90, 60, 30);

        lblP5_last_8.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_8.setText("-");
        lblP5_last_8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_8.setOpaque(true);
        add(lblP5_last_8);
        lblP5_last_8.setBounds(510, 120, 60, 30);

        lblDuration_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_9.setText("-");
        lblDuration_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_9.setOpaque(true);
        add(lblDuration_9);
        lblDuration_9.setBounds(570, 0, 60, 30);

        lblPower_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_9.setText("-");
        lblPower_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_9.setOpaque(true);
        add(lblPower_9);
        lblPower_9.setBounds(570, 30, 60, 30);

        lblP5_start_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_9.setText("-");
        lblP5_start_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_9.setOpaque(true);
        add(lblP5_start_9);
        lblP5_start_9.setBounds(570, 60, 60, 30);

        lblP5_max_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_9.setText("-");
        lblP5_max_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_9.setOpaque(true);
        add(lblP5_max_9);
        lblP5_max_9.setBounds(570, 90, 60, 30);

        lblP5_last_9.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_9.setText("-");
        lblP5_last_9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_9.setOpaque(true);
        add(lblP5_last_9);
        lblP5_last_9.setBounds(570, 120, 60, 30);

        lblDuration_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_10.setText("-");
        lblDuration_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_10.setOpaque(true);
        add(lblDuration_10);
        lblDuration_10.setBounds(630, 0, 60, 30);

        lblPower_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_10.setText("-");
        lblPower_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_10.setOpaque(true);
        add(lblPower_10);
        lblPower_10.setBounds(630, 30, 60, 30);

        lblP5_start_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_10.setText("-");
        lblP5_start_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_10.setOpaque(true);
        add(lblP5_start_10);
        lblP5_start_10.setBounds(630, 60, 60, 30);

        lblP5_max_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_10.setText("-");
        lblP5_max_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_10.setOpaque(true);
        add(lblP5_max_10);
        lblP5_max_10.setBounds(630, 90, 60, 30);

        lblP5_last_10.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_10.setText("-");
        lblP5_last_10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_10.setOpaque(true);
        add(lblP5_last_10);
        lblP5_last_10.setBounds(630, 120, 60, 30);

        lblDuration_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_11.setText("-");
        lblDuration_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_11.setOpaque(true);
        add(lblDuration_11);
        lblDuration_11.setBounds(690, 0, 60, 30);

        lblPower_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_11.setText("-");
        lblPower_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_11.setOpaque(true);
        add(lblPower_11);
        lblPower_11.setBounds(690, 30, 60, 30);

        lblP5_start_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_11.setText("-");
        lblP5_start_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_11.setOpaque(true);
        add(lblP5_start_11);
        lblP5_start_11.setBounds(690, 60, 60, 30);

        lblP5_max_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_11.setText("-");
        lblP5_max_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_11.setOpaque(true);
        add(lblP5_max_11);
        lblP5_max_11.setBounds(690, 90, 60, 30);

        lblP5_last_11.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_11.setText("-");
        lblP5_last_11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_11.setOpaque(true);
        add(lblP5_last_11);
        lblP5_last_11.setBounds(690, 120, 60, 30);

        lblDuration_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_12.setText("-");
        lblDuration_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_12.setOpaque(true);
        add(lblDuration_12);
        lblDuration_12.setBounds(750, 0, 60, 30);

        lblPower_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_12.setText("-");
        lblPower_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_12.setOpaque(true);
        add(lblPower_12);
        lblPower_12.setBounds(750, 30, 60, 30);

        lblP5_start_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_12.setText("-");
        lblP5_start_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_12.setOpaque(true);
        add(lblP5_start_12);
        lblP5_start_12.setBounds(750, 60, 60, 30);

        lblP5_max_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_12.setText("-");
        lblP5_max_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_12.setOpaque(true);
        add(lblP5_max_12);
        lblP5_max_12.setBounds(750, 90, 60, 30);

        lblP5_last_12.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_12.setText("-");
        lblP5_last_12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_12.setOpaque(true);
        add(lblP5_last_12);
        lblP5_last_12.setBounds(750, 120, 60, 30);

        lblDuration_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_13.setText("-");
        lblDuration_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_13.setOpaque(true);
        add(lblDuration_13);
        lblDuration_13.setBounds(810, 0, 60, 30);

        lblPower_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_13.setText("-");
        lblPower_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_13.setOpaque(true);
        add(lblPower_13);
        lblPower_13.setBounds(810, 30, 60, 30);

        lblP5_start_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_13.setText("-");
        lblP5_start_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_13.setOpaque(true);
        add(lblP5_start_13);
        lblP5_start_13.setBounds(810, 60, 60, 30);

        lblP5_max_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_13.setText("-");
        lblP5_max_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_13.setOpaque(true);
        add(lblP5_max_13);
        lblP5_max_13.setBounds(810, 90, 60, 30);

        lblP5_last_13.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_13.setText("-");
        lblP5_last_13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_13.setOpaque(true);
        add(lblP5_last_13);
        lblP5_last_13.setBounds(810, 120, 60, 30);

        lblDuration_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblDuration_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDuration_14.setText("-");
        lblDuration_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblDuration_14.setOpaque(true);
        add(lblDuration_14);
        lblDuration_14.setBounds(870, 0, 60, 30);

        lblPower_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblPower_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPower_14.setText("-");
        lblPower_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblPower_14.setOpaque(true);
        add(lblPower_14);
        lblPower_14.setBounds(870, 30, 60, 30);

        lblP5_start_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_start_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_start_14.setText("-");
        lblP5_start_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_start_14.setOpaque(true);
        add(lblP5_start_14);
        lblP5_start_14.setBounds(870, 60, 60, 30);

        lblP5_max_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_max_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_max_14.setText("-");
        lblP5_max_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_max_14.setOpaque(true);
        add(lblP5_max_14);
        lblP5_max_14.setBounds(870, 90, 60, 30);

        lblP5_last_14.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lblP5_last_14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblP5_last_14.setText("-");
        lblP5_last_14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(220, 220, 220), 1, true));
        lblP5_last_14.setOpaque(true);
        add(lblP5_last_14);
        lblP5_last_14.setBounds(870, 120, 60, 30);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblDuration_1;
    private javax.swing.JLabel lblDuration_10;
    private javax.swing.JLabel lblDuration_11;
    private javax.swing.JLabel lblDuration_12;
    private javax.swing.JLabel lblDuration_13;
    private javax.swing.JLabel lblDuration_14;
    private javax.swing.JLabel lblDuration_2;
    private javax.swing.JLabel lblDuration_3;
    private javax.swing.JLabel lblDuration_4;
    private javax.swing.JLabel lblDuration_5;
    private javax.swing.JLabel lblDuration_6;
    private javax.swing.JLabel lblDuration_7;
    private javax.swing.JLabel lblDuration_8;
    private javax.swing.JLabel lblDuration_9;
    private javax.swing.JLabel lblDuration_Title;
    private javax.swing.JLabel lblP5_last_1;
    private javax.swing.JLabel lblP5_last_10;
    private javax.swing.JLabel lblP5_last_11;
    private javax.swing.JLabel lblP5_last_12;
    private javax.swing.JLabel lblP5_last_13;
    private javax.swing.JLabel lblP5_last_14;
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
    private javax.swing.JLabel lblPower_2;
    private javax.swing.JLabel lblPower_3;
    private javax.swing.JLabel lblPower_4;
    private javax.swing.JLabel lblPower_5;
    private javax.swing.JLabel lblPower_6;
    private javax.swing.JLabel lblPower_7;
    private javax.swing.JLabel lblPower_8;
    private javax.swing.JLabel lblPower_9;
    private javax.swing.JLabel lblPower_Title;
    // End of variables declaration//GEN-END:variables
}
