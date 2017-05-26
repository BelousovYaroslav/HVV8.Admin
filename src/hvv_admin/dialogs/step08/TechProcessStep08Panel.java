/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step08;

import hvv_admin.HVV_Admin;
import hvv_admin.dialogs.TechProcessStepPanelTemplate;
import java.awt.event.MouseEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class TechProcessStep08Panel extends TechProcessStepPanelTemplate {
    public final int MAXIMIZED_HEIGHT = 90;
    static Logger logger = Logger.getLogger(TechProcessStep08Panel.class);
    
    /**
     * Creates new form TechProcessStep1Panel
     */
    public TechProcessStep08Panel( HVV_Admin app, boolean bCollapsed) {    
        initComponents();
        theApp = app;
        m_bCollapsed = bCollapsed;
        m_nPageNumber = 7;
        m_bFirstOfDoubleClickHeaderDateTime = false;
        
        String strTitle = theApp.GetStepName( 140);
        if( strTitle != null) lbl_08_00_Title.setText( "8. " + strTitle);
        strTitle = theApp.GetStepName( 141);
        if( strTitle != null) lbl_08_01_Title.setText( "8.1 " + strTitle);
        strTitle = theApp.GetStepName( 142);
        if( strTitle != null) lbl_08_02_Title.setText( "8.2 " + strTitle);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_08_00_Date_start = new javax.swing.JLabel();
        lbl_08_00_Time_start = new javax.swing.JLabel();
        lbl_08_00_IcoStep = new javax.swing.JLabel();
        lbl_08_00_Title = new javax.swing.JLabel();
        lbl_08_00_Date_stop = new javax.swing.JLabel();
        lbl_08_00_Time_stop = new javax.swing.JLabel();
        lbl_08_01_Date_start = new javax.swing.JLabel();
        lbl_08_01_Time_start = new javax.swing.JLabel();
        lblAnimation_08_01 = new javax.swing.JLabel();
        lbl_08_01_Title = new javax.swing.JLabel();
        lbl_08_01_IcoStep = new javax.swing.JLabel();
        btn_08_01_Next = new javax.swing.JButton();
        lbl_08_01_Date_stop = new javax.swing.JLabel();
        lbl_08_01_Time_stop = new javax.swing.JLabel();
        lbl_08_02_Date_start = new javax.swing.JLabel();
        lbl_08_02_Time_start = new javax.swing.JLabel();
        lblAnimation_08_02 = new javax.swing.JLabel();
        lbl_08_02_Title = new javax.swing.JLabel();
        lbl_08_02_IcoStep = new javax.swing.JLabel();
        btn_08_02_Next = new javax.swing.JButton();
        lbl_08_02_Date_stop = new javax.swing.JLabel();
        lbl_08_02_Time_stop = new javax.swing.JLabel();
        chk_08_02_AutoStart = new javax.swing.JCheckBox();
        btn_08_01_Start = new javax.swing.JButton();
        btn_08_02_Start = new javax.swing.JButton();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(1000, 90));
        setMinimumSize(new java.awt.Dimension(1000, 30));
        setName(""); // NOI18N
        setLayout(null);

        lbl_08_00_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_00_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_00_Date_start.setText("-");
        lbl_08_00_Date_start.setBorder(null);
        lbl_08_00_Date_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_08_00_Date_startMouseClicked(evt);
            }
        });
        add(lbl_08_00_Date_start);
        lbl_08_00_Date_start.setBounds(0, 0, 90, 25);

        lbl_08_00_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_00_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_00_Time_start.setText("-");
        lbl_08_00_Time_start.setBorder(null);
        lbl_08_00_Time_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_08_00_Time_startMouseClicked(evt);
            }
        });
        add(lbl_08_00_Time_start);
        lbl_08_00_Time_start.setBounds(90, 0, 60, 25);

        lbl_08_00_IcoStep.setBorder(null);
        lbl_08_00_IcoStep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_08_00_IcoStepMouseClicked(evt);
            }
        });
        add(lbl_08_00_IcoStep);
        lbl_08_00_IcoStep.setBounds(150, 0, 30, 25);

        lbl_08_00_Title.setText("8. Обезгаживание рабочих геттеров");
        lbl_08_00_Title.setBorder(null);
        lbl_08_00_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_08_00_TitleMouseClicked(evt);
            }
        });
        add(lbl_08_00_Title);
        lbl_08_00_Title.setBounds(180, 0, 470, 25);

        lbl_08_00_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_00_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_00_Date_stop.setText("-");
        lbl_08_00_Date_stop.setBorder(null);
        add(lbl_08_00_Date_stop);
        lbl_08_00_Date_stop.setBounds(840, 0, 90, 25);

        lbl_08_00_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_00_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_00_Time_stop.setText("-");
        lbl_08_00_Time_stop.setBorder(null);
        add(lbl_08_00_Time_stop);
        lbl_08_00_Time_stop.setBounds(930, 0, 60, 25);

        lbl_08_01_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_01_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_01_Date_start.setText("-");
        lbl_08_01_Date_start.setBorder(null);
        add(lbl_08_01_Date_start);
        lbl_08_01_Date_start.setBounds(0, 30, 90, 25);

        lbl_08_01_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_01_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_01_Time_start.setText("-");
        lbl_08_01_Time_start.setBorder(null);
        add(lbl_08_01_Time_start);
        lbl_08_01_Time_start.setBounds(90, 30, 60, 25);

        lblAnimation_08_01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_08_01);
        lblAnimation_08_01.setBounds(230, 30, 25, 25);

        lbl_08_01_Title.setText("8.1 Обезгаживание");
        lbl_08_01_Title.setBorder(null);
        lbl_08_01_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_08_01_TitleMouseClicked(evt);
            }
        });
        add(lbl_08_01_Title);
        lbl_08_01_Title.setBounds(260, 30, 440, 25);

        lbl_08_01_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_01_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_08_01_IcoStep);
        lbl_08_01_IcoStep.setBounds(730, 30, 30, 25);

        btn_08_01_Next.setText("Далее");
        btn_08_01_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_08_01_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_08_01_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        add(btn_08_01_Next);
        btn_08_01_Next.setBounds(770, 30, 60, 30);

        lbl_08_01_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_01_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_01_Date_stop.setText("-");
        lbl_08_01_Date_stop.setBorder(null);
        add(lbl_08_01_Date_stop);
        lbl_08_01_Date_stop.setBounds(840, 30, 90, 25);

        lbl_08_01_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_01_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_01_Time_stop.setText("-");
        lbl_08_01_Time_stop.setBorder(null);
        add(lbl_08_01_Time_stop);
        lbl_08_01_Time_stop.setBounds(930, 30, 60, 25);

        lbl_08_02_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_02_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_02_Date_start.setText("-");
        lbl_08_02_Date_start.setBorder(null);
        add(lbl_08_02_Date_start);
        lbl_08_02_Date_start.setBounds(0, 60, 90, 25);

        lbl_08_02_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_02_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_02_Time_start.setText("-");
        lbl_08_02_Time_start.setBorder(null);
        add(lbl_08_02_Time_start);
        lbl_08_02_Time_start.setBounds(90, 60, 60, 25);

        lblAnimation_08_02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_08_02);
        lblAnimation_08_02.setBounds(230, 60, 25, 25);

        lbl_08_02_Title.setText("8.2 Открытие геттера");
        lbl_08_02_Title.setBorder(null);
        lbl_08_02_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_08_02_TitleMouseClicked(evt);
            }
        });
        add(lbl_08_02_Title);
        lbl_08_02_Title.setBounds(260, 60, 440, 25);

        lbl_08_02_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_02_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_08_02_IcoStep);
        lbl_08_02_IcoStep.setBounds(730, 60, 30, 25);

        btn_08_02_Next.setText("Далее");
        btn_08_02_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_08_02_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_08_02_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_08_02_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_08_02_NextActionPerformed(evt);
            }
        });
        add(btn_08_02_Next);
        btn_08_02_Next.setBounds(770, 60, 60, 30);

        lbl_08_02_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_02_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_02_Date_stop.setText("-");
        lbl_08_02_Date_stop.setBorder(null);
        add(lbl_08_02_Date_stop);
        lbl_08_02_Date_stop.setBounds(840, 60, 90, 25);

        lbl_08_02_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_08_02_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_08_02_Time_stop.setText("-");
        lbl_08_02_Time_stop.setBorder(null);
        add(lbl_08_02_Time_stop);
        lbl_08_02_Time_stop.setBounds(930, 60, 60, 25);

        chk_08_02_AutoStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_08_02_AutoStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chk_08_02_AutoStart.setMaximumSize(new java.awt.Dimension(30, 25));
        chk_08_02_AutoStart.setMinimumSize(new java.awt.Dimension(30, 25));
        chk_08_02_AutoStart.setPreferredSize(new java.awt.Dimension(30, 25));
        add(chk_08_02_AutoStart);
        chk_08_02_AutoStart.setBounds(700, 60, 30, 30);

        btn_08_01_Start.setText("Старт");
        btn_08_01_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_08_01_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_08_01_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        add(btn_08_01_Start);
        btn_08_01_Start.setBounds(160, 30, 60, 30);

        btn_08_02_Start.setText("Старт");
        btn_08_02_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_08_02_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_08_02_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_08_02_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_08_02_StartActionPerformed(evt);
            }
        });
        add(btn_08_02_Start);
        btn_08_02_Start.setBounds(160, 60, 60, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_08_00_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_08_00_TitleMouseClicked
        CollapseClick( m_nPageNumber);
    }//GEN-LAST:event_lbl_08_00_TitleMouseClicked

    private void lbl_08_00_IcoStepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_08_00_IcoStepMouseClicked
        CollapseClick( m_nPageNumber);
    }//GEN-LAST:event_lbl_08_00_IcoStepMouseClicked

    private void btn_08_02_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_08_02_NextActionPerformed
        DefaultNextButtonProcessing( "142", "Запуск программы открытия геттера", "Завершение программы открытия геттера",
                                       "161", "Старт напуска тренировочной смеси", theApp.m_pMainWnd.m_pPanel.pnlStep9.chk_09_01_AutoStart, logger, true);
    }//GEN-LAST:event_btn_08_02_NextActionPerformed

    private void btn_08_02_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_08_02_StartActionPerformed
        DefaultStartButtonProcessing( "142", "Запуск программы открытия геттера", "Завершение программы открытия геттера",
                                       "161", "Старт напуска тренировочной смеси", theApp.m_pMainWnd.m_pPanel.pnlStep9.chk_09_01_AutoStart, logger, true);
    }//GEN-LAST:event_btn_08_02_StartActionPerformed

    private void lbl_08_00_Date_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_08_00_Date_startMouseClicked
        ProcessHeaderDateTimeClick( 8);
    }//GEN-LAST:event_lbl_08_00_Date_startMouseClicked

    private void lbl_08_00_Time_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_08_00_Time_startMouseClicked
        ProcessHeaderDateTimeClick( 8);
    }//GEN-LAST:event_lbl_08_00_Time_startMouseClicked

    private void lbl_08_01_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_08_01_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 141);
        }
    }//GEN-LAST:event_lbl_08_01_TitleMouseClicked

    private void lbl_08_02_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_08_02_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 142);
        }
    }//GEN-LAST:event_lbl_08_02_TitleMouseClicked

    public void SetState() {
        if( theApp.GetCurrentStep() / 20 == m_nPageNumber)
            m_bCollapsed = false;
        
        //8. Обезгаживание рабочих геттеров
        FillHeaderStepDates( "141", lbl_08_00_Date_start, lbl_08_00_Time_start, "142", lbl_08_00_Date_stop, lbl_08_00_Time_stop);
        
        lbl_08_00_IcoStep.setIcon( m_bCollapsed ? theApp.GetResources().getIconTriaRight() : theApp.GetResources().getIconTriaDown());
        
        lbl_08_00_Title.setFont( theApp.GetCurrentStep() / 20 == m_nPageNumber ? theApp.GetBoldFont() : theApp.GetUsualFont());
        lbl_08_00_Title.setEnabled( theApp.GetCurrentStep() / 20 <= m_nPageNumber);
        
        if( m_bCollapsed == false) {
            //8.1 Обезгаживание
            FillAutoExecutedSubStep(141,
                                    lbl_08_01_Date_start, lbl_08_01_Time_start, lblAnimation_08_01,
                                    btn_08_01_Start, lbl_08_01_Title, null, lbl_08_01_IcoStep, btn_08_01_Next,
                                    lbl_08_01_Date_stop, lbl_08_01_Time_stop);
            
            //8.2 Открытие геттера
            FillAutoExecutedSubStep(142,
                                    lbl_08_02_Date_start, lbl_08_02_Time_start, lblAnimation_08_02,
                                    btn_08_02_Start, lbl_08_02_Title, chk_08_02_AutoStart, lbl_08_02_IcoStep, btn_08_02_Next,
                                    lbl_08_02_Date_stop, lbl_08_02_Time_stop);
        }
        
        btn_08_01_Start.setVisible( false);
        btn_08_01_Next.setVisible( false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_08_01_Next;
    public javax.swing.JButton btn_08_01_Start;
    private javax.swing.JButton btn_08_02_Next;
    public javax.swing.JButton btn_08_02_Start;
    public javax.swing.JCheckBox chk_08_02_AutoStart;
    public javax.swing.JLabel lblAnimation_08_01;
    public javax.swing.JLabel lblAnimation_08_02;
    public javax.swing.JLabel lbl_08_00_Date_start;
    private javax.swing.JLabel lbl_08_00_Date_stop;
    private javax.swing.JLabel lbl_08_00_IcoStep;
    public javax.swing.JLabel lbl_08_00_Time_start;
    private javax.swing.JLabel lbl_08_00_Time_stop;
    private javax.swing.JLabel lbl_08_00_Title;
    private javax.swing.JLabel lbl_08_01_Date_start;
    private javax.swing.JLabel lbl_08_01_Date_stop;
    public javax.swing.JLabel lbl_08_01_IcoStep;
    private javax.swing.JLabel lbl_08_01_Time_start;
    private javax.swing.JLabel lbl_08_01_Time_stop;
    private javax.swing.JLabel lbl_08_01_Title;
    private javax.swing.JLabel lbl_08_02_Date_start;
    private javax.swing.JLabel lbl_08_02_Date_stop;
    public javax.swing.JLabel lbl_08_02_IcoStep;
    private javax.swing.JLabel lbl_08_02_Time_start;
    private javax.swing.JLabel lbl_08_02_Time_stop;
    private javax.swing.JLabel lbl_08_02_Title;
    // End of variables declaration//GEN-END:variables
}