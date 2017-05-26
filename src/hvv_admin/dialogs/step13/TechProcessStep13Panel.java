/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step13;

import hvv_admin.HVV_Admin;
import hvv_admin.comm.executor.to.StartProgramExecutor;
import hvv_admin.dialogs.TechProcessStepPanelTemplate;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class TechProcessStep13Panel extends TechProcessStepPanelTemplate {
    public final int MAXIMIZED_HEIGHT = 150;
    static Logger logger = Logger.getLogger(TechProcessStep13Panel.class);
    /**
     * Creates new form TechProcessStep1Panel
     */
    public TechProcessStep13Panel( HVV_Admin app, boolean bCollapsed) {
        initComponents();
        theApp = app;
        m_bCollapsed = bCollapsed;
        m_nPageNumber = 12;
        m_bFirstOfDoubleClickHeaderDateTime = false;
        
        String strTitle = theApp.GetStepName( 240);
        if( strTitle != null) lbl_13_00_Title.setText( "13. " + strTitle);
        strTitle = theApp.GetStepName( 241);
        if( strTitle != null) lbl_13_01_Title.setText( "13.1 " + strTitle);
        strTitle = theApp.GetStepName( 242);
        if( strTitle != null) lbl_13_02_Title.setText( "13.2 " + strTitle);
        strTitle = theApp.GetStepName( 243);
        if( strTitle != null) lbl_13_03_Title.setText( "13.3 " + strTitle);
        strTitle = theApp.GetStepName( 244);
        if( strTitle != null) lbl_13_04_Title.setText( "13.4 " + strTitle);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_13_00_Date_start = new javax.swing.JLabel();
        lbl_13_00_Time_start = new javax.swing.JLabel();
        lbl_13_00_IcoStep = new javax.swing.JLabel();
        lbl_13_00_Title = new javax.swing.JLabel();
        lbl_13_00_Date_stop = new javax.swing.JLabel();
        lbl_13_00_Time_stop = new javax.swing.JLabel();
        lbl_13_01_Date_start = new javax.swing.JLabel();
        lbl_13_01_Time_start = new javax.swing.JLabel();
        lblAnimation_13_01 = new javax.swing.JLabel();
        lbl_13_01_Title = new javax.swing.JLabel();
        chk_13_01_AutoStart = new javax.swing.JCheckBox();
        lbl_13_01_IcoStep = new javax.swing.JLabel();
        btn_13_01_Next = new javax.swing.JButton();
        lbl_13_01_Date_stop = new javax.swing.JLabel();
        lbl_13_01_Time_stop = new javax.swing.JLabel();
        lbl_13_02_Date_start = new javax.swing.JLabel();
        lbl_13_02_Time_start = new javax.swing.JLabel();
        lblAnimation_13_02 = new javax.swing.JLabel();
        lbl_13_02_Title = new javax.swing.JLabel();
        lbl_13_02_IcoStep = new javax.swing.JLabel();
        btn_13_02_Next = new javax.swing.JButton();
        lbl_13_02_Date_stop = new javax.swing.JLabel();
        lbl_13_02_Time_stop = new javax.swing.JLabel();
        lbl_13_03_Date_start = new javax.swing.JLabel();
        lbl_13_03_Time_start = new javax.swing.JLabel();
        lblAnimation_13_03 = new javax.swing.JLabel();
        lbl_13_03_Title = new javax.swing.JLabel();
        chk_13_03_AutoStart = new javax.swing.JCheckBox();
        lbl_13_03_IcoStep = new javax.swing.JLabel();
        btn_13_03_Next = new javax.swing.JButton();
        lbl_13_03_Date_stop = new javax.swing.JLabel();
        lbl_13_03_Time_stop = new javax.swing.JLabel();
        lbl_13_04_Date_start = new javax.swing.JLabel();
        lbl_13_04_Time_start = new javax.swing.JLabel();
        lblAnimation_13_04 = new javax.swing.JLabel();
        lbl_13_04_Title = new javax.swing.JLabel();
        chk_13_04_AutoStart = new javax.swing.JCheckBox();
        lbl_13_04_IcoStep = new javax.swing.JLabel();
        btn_13_04_Next = new javax.swing.JButton();
        lbl_13_04_Date_stop = new javax.swing.JLabel();
        lbl_13_04_Time_stop = new javax.swing.JLabel();
        btn_13_01_Start = new javax.swing.JButton();
        btn_13_02_Start = new javax.swing.JButton();
        btn_13_03_Start = new javax.swing.JButton();
        btn_13_04_Start = new javax.swing.JButton();

        setBorder(null);
        setMaximumSize(new java.awt.Dimension(1000, 150));
        setMinimumSize(new java.awt.Dimension(1000, 30));
        setLayout(null);

        lbl_13_00_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_00_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_00_Date_start.setText("-");
        lbl_13_00_Date_start.setBorder(null);
        lbl_13_00_Date_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_00_Date_startMouseClicked(evt);
            }
        });
        add(lbl_13_00_Date_start);
        lbl_13_00_Date_start.setBounds(0, 0, 90, 25);

        lbl_13_00_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_00_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_00_Time_start.setText("-");
        lbl_13_00_Time_start.setBorder(null);
        lbl_13_00_Time_start.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_00_Time_startMouseClicked(evt);
            }
        });
        add(lbl_13_00_Time_start);
        lbl_13_00_Time_start.setBounds(90, 0, 60, 25);

        lbl_13_00_IcoStep.setBorder(null);
        lbl_13_00_IcoStep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_00_IcoStepMouseClicked(evt);
            }
        });
        add(lbl_13_00_IcoStep);
        lbl_13_00_IcoStep.setBounds(150, 0, 30, 25);

        lbl_13_00_Title.setText("13. Завершение технологического процесса");
        lbl_13_00_Title.setBorder(null);
        lbl_13_00_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_00_TitleMouseClicked(evt);
            }
        });
        add(lbl_13_00_Title);
        lbl_13_00_Title.setBounds(180, 0, 470, 25);

        lbl_13_00_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_00_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_00_Date_stop.setText("-");
        lbl_13_00_Date_stop.setBorder(null);
        add(lbl_13_00_Date_stop);
        lbl_13_00_Date_stop.setBounds(840, 0, 90, 25);

        lbl_13_00_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_00_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_00_Time_stop.setText("-");
        lbl_13_00_Time_stop.setBorder(null);
        add(lbl_13_00_Time_stop);
        lbl_13_00_Time_stop.setBounds(930, 0, 60, 25);

        lbl_13_01_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_01_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_01_Date_start.setText("-");
        lbl_13_01_Date_start.setBorder(null);
        add(lbl_13_01_Date_start);
        lbl_13_01_Date_start.setBounds(0, 30, 90, 25);

        lbl_13_01_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_01_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_01_Time_start.setText("-");
        lbl_13_01_Time_start.setBorder(null);
        add(lbl_13_01_Time_start);
        lbl_13_01_Time_start.setBounds(90, 30, 60, 25);

        lblAnimation_13_01.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_13_01);
        lblAnimation_13_01.setBounds(230, 30, 25, 25);

        lbl_13_01_Title.setText("13.1 Bypass-откачка");
        lbl_13_01_Title.setBorder(null);
        lbl_13_01_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_01_TitleMouseClicked(evt);
            }
        });
        add(lbl_13_01_Title);
        lbl_13_01_Title.setBounds(260, 30, 440, 25);

        chk_13_01_AutoStart.setEnabled(false);
        chk_13_01_AutoStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_13_01_AutoStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chk_13_01_AutoStart.setMaximumSize(new java.awt.Dimension(30, 25));
        chk_13_01_AutoStart.setMinimumSize(new java.awt.Dimension(30, 25));
        chk_13_01_AutoStart.setPreferredSize(new java.awt.Dimension(30, 25));
        add(chk_13_01_AutoStart);
        chk_13_01_AutoStart.setBounds(700, 30, 30, 30);

        lbl_13_01_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_01_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_13_01_IcoStep);
        lbl_13_01_IcoStep.setBounds(730, 30, 30, 25);

        btn_13_01_Next.setText("Далее");
        btn_13_01_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_01_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_01_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_01_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_01_NextActionPerformed(evt);
            }
        });
        add(btn_13_01_Next);
        btn_13_01_Next.setBounds(770, 30, 60, 30);

        lbl_13_01_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_01_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_01_Date_stop.setText("-");
        lbl_13_01_Date_stop.setBorder(null);
        add(lbl_13_01_Date_stop);
        lbl_13_01_Date_stop.setBounds(840, 30, 90, 25);

        lbl_13_01_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_01_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_01_Time_stop.setText("-");
        lbl_13_01_Time_stop.setBorder(null);
        add(lbl_13_01_Time_stop);
        lbl_13_01_Time_stop.setBounds(930, 30, 60, 25);

        lbl_13_02_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_02_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_02_Date_start.setText("-");
        lbl_13_02_Date_start.setBorder(null);
        add(lbl_13_02_Date_start);
        lbl_13_02_Date_start.setBounds(0, 60, 90, 25);

        lbl_13_02_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_02_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_02_Time_start.setText("-");
        lbl_13_02_Time_start.setBorder(null);
        add(lbl_13_02_Time_start);
        lbl_13_02_Time_start.setBounds(90, 60, 60, 25);

        lblAnimation_13_02.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_13_02);
        lblAnimation_13_02.setBounds(230, 60, 25, 25);

        lbl_13_02_Title.setText("13.2 Проверка герметичности");
        lbl_13_02_Title.setBorder(null);
        lbl_13_02_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_02_TitleMouseClicked(evt);
            }
        });
        add(lbl_13_02_Title);
        lbl_13_02_Title.setBounds(260, 60, 440, 25);

        lbl_13_02_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_02_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_13_02_IcoStep);
        lbl_13_02_IcoStep.setBounds(730, 60, 30, 25);

        btn_13_02_Next.setText("Далее");
        btn_13_02_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_02_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_02_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_02_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_02_NextActionPerformed(evt);
            }
        });
        add(btn_13_02_Next);
        btn_13_02_Next.setBounds(770, 60, 60, 30);

        lbl_13_02_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_02_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_02_Date_stop.setText("-");
        lbl_13_02_Date_stop.setBorder(null);
        add(lbl_13_02_Date_stop);
        lbl_13_02_Date_stop.setBounds(840, 60, 90, 25);

        lbl_13_02_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_02_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_02_Time_stop.setText("-");
        lbl_13_02_Time_stop.setBorder(null);
        add(lbl_13_02_Time_stop);
        lbl_13_02_Time_stop.setBounds(930, 60, 60, 25);

        lbl_13_03_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_03_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_03_Date_start.setText("-");
        lbl_13_03_Date_start.setBorder(null);
        add(lbl_13_03_Date_start);
        lbl_13_03_Date_start.setBounds(0, 90, 90, 25);

        lbl_13_03_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_03_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_03_Time_start.setText("-");
        lbl_13_03_Time_start.setBorder(null);
        add(lbl_13_03_Time_start);
        lbl_13_03_Time_start.setBounds(90, 90, 60, 25);

        lblAnimation_13_03.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_13_03);
        lblAnimation_13_03.setBounds(230, 90, 25, 25);

        lbl_13_03_Title.setText("13.3 Основная откачка");
        lbl_13_03_Title.setBorder(null);
        lbl_13_03_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_03_TitleMouseClicked(evt);
            }
        });
        add(lbl_13_03_Title);
        lbl_13_03_Title.setBounds(260, 90, 440, 25);

        chk_13_03_AutoStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_13_03_AutoStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chk_13_03_AutoStart.setMaximumSize(new java.awt.Dimension(30, 25));
        chk_13_03_AutoStart.setMinimumSize(new java.awt.Dimension(30, 25));
        chk_13_03_AutoStart.setPreferredSize(new java.awt.Dimension(30, 25));
        add(chk_13_03_AutoStart);
        chk_13_03_AutoStart.setBounds(700, 90, 30, 30);

        lbl_13_03_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_03_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_13_03_IcoStep);
        lbl_13_03_IcoStep.setBounds(730, 90, 30, 25);

        btn_13_03_Next.setText("Далее");
        btn_13_03_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_03_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_03_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_03_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_03_NextActionPerformed(evt);
            }
        });
        add(btn_13_03_Next);
        btn_13_03_Next.setBounds(770, 90, 60, 30);

        lbl_13_03_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_03_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_03_Date_stop.setText("-");
        lbl_13_03_Date_stop.setBorder(null);
        add(lbl_13_03_Date_stop);
        lbl_13_03_Date_stop.setBounds(840, 90, 90, 25);

        lbl_13_03_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_03_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_03_Time_stop.setText("-");
        lbl_13_03_Time_stop.setBorder(null);
        add(lbl_13_03_Time_stop);
        lbl_13_03_Time_stop.setBounds(930, 90, 60, 25);

        lbl_13_04_Date_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_04_Date_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_04_Date_start.setText("-");
        lbl_13_04_Date_start.setBorder(null);
        add(lbl_13_04_Date_start);
        lbl_13_04_Date_start.setBounds(0, 120, 90, 25);

        lbl_13_04_Time_start.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_04_Time_start.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_04_Time_start.setText("-");
        lbl_13_04_Time_start.setBorder(null);
        add(lbl_13_04_Time_start);
        lbl_13_04_Time_start.setBounds(90, 120, 60, 25);

        lblAnimation_13_04.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lblAnimation_13_04);
        lblAnimation_13_04.setBounds(230, 120, 25, 25);

        lbl_13_04_Title.setText("13.4 Откачка смеси с геттера");
        lbl_13_04_Title.setBorder(null);
        lbl_13_04_Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_13_04_TitleMouseClicked(evt);
            }
        });
        add(lbl_13_04_Title);
        lbl_13_04_Title.setBounds(260, 120, 440, 25);

        chk_13_04_AutoStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chk_13_04_AutoStart.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chk_13_04_AutoStart.setMaximumSize(new java.awt.Dimension(30, 25));
        chk_13_04_AutoStart.setMinimumSize(new java.awt.Dimension(30, 25));
        chk_13_04_AutoStart.setPreferredSize(new java.awt.Dimension(30, 25));
        add(chk_13_04_AutoStart);
        chk_13_04_AutoStart.setBounds(700, 120, 30, 30);

        lbl_13_04_IcoStep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_04_IcoStep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        add(lbl_13_04_IcoStep);
        lbl_13_04_IcoStep.setBounds(730, 120, 30, 25);

        btn_13_04_Next.setText("Финиш");
        btn_13_04_Next.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_04_Next.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_04_Next.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_04_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_04_NextActionPerformed(evt);
            }
        });
        add(btn_13_04_Next);
        btn_13_04_Next.setBounds(770, 120, 60, 30);

        lbl_13_04_Date_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_04_Date_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_04_Date_stop.setText("-");
        lbl_13_04_Date_stop.setBorder(null);
        add(lbl_13_04_Date_stop);
        lbl_13_04_Date_stop.setBounds(840, 120, 90, 25);

        lbl_13_04_Time_stop.setFont(new java.awt.Font("Cantarell", 0, 12)); // NOI18N
        lbl_13_04_Time_stop.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_13_04_Time_stop.setText("-");
        lbl_13_04_Time_stop.setBorder(null);
        add(lbl_13_04_Time_stop);
        lbl_13_04_Time_stop.setBounds(930, 120, 60, 25);

        btn_13_01_Start.setText("Старт");
        btn_13_01_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_01_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_01_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_01_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_01_StartActionPerformed(evt);
            }
        });
        add(btn_13_01_Start);
        btn_13_01_Start.setBounds(160, 30, 60, 30);

        btn_13_02_Start.setText("Старт");
        btn_13_02_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_02_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_02_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_02_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_02_StartActionPerformed(evt);
            }
        });
        add(btn_13_02_Start);
        btn_13_02_Start.setBounds(160, 60, 60, 30);

        btn_13_03_Start.setText("Старт");
        btn_13_03_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_03_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_03_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_03_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_03_StartActionPerformed(evt);
            }
        });
        add(btn_13_03_Start);
        btn_13_03_Start.setBounds(160, 90, 60, 30);

        btn_13_04_Start.setText("Старт");
        btn_13_04_Start.setMaximumSize(new java.awt.Dimension(120, 25));
        btn_13_04_Start.setMinimumSize(new java.awt.Dimension(120, 25));
        btn_13_04_Start.setPreferredSize(new java.awt.Dimension(120, 25));
        btn_13_04_Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_13_04_StartActionPerformed(evt);
            }
        });
        add(btn_13_04_Start);
        btn_13_04_Start.setBounds(160, 120, 60, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_13_00_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_00_TitleMouseClicked
        CollapseClick( m_nPageNumber);
    }//GEN-LAST:event_lbl_13_00_TitleMouseClicked

    private void lbl_13_00_IcoStepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_00_IcoStepMouseClicked
        CollapseClick( m_nPageNumber);
    }//GEN-LAST:event_lbl_13_00_IcoStepMouseClicked

    private void btn_13_01_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_01_NextActionPerformed
        if( theApp.IsCurrentStepInProgress() == false)
            return;
        
        //мы в процессе исполнения подэтапа XXX, переходим к следующему подэтапу XXX+1
        if( theApp.IsStepMapContainsKey( "241")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "241");

            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Завершение программы bypass-откачки");
            info.SetStopP5( theApp.GetFromPoller( "005.01"));
            info.SetStopP6( theApp.GetFromPoller( "006.01"));
            info.SetStopP7( theApp.GetFromPoller( "007.01"));

            theApp.NextCurrentStep();

            info = new TechProcessStepInfo( theApp);
            info.SetStartDateAsCurrent();
            info.SetStartReportTitle( "Старт проверки герметичности");
            info.SetStartP5( theApp.GetFromPoller( "005.01"));
            info.SetStartP6( theApp.GetFromPoller( "006.01"));
            info.SetStartP7( theApp.GetFromPoller( "007.01"));

            theApp.SaveStepInfo( "242", info, true);

            //обновляем экран и отчёт, и только потом закроем экран диалогами обезгаживания
            theApp.m_pMainWnd.m_pPanel.SetStates();
            theApp.m_ReportGenerator.Generate();
            theApp.m_pMainWnd.m_pPanel.Reposition();

            theApp.SetCurrentStepInProgress( true);

            new Timer( 100, new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Timer timer = ( Timer) e.getSource();
                    timer.stop();
                    theApp.ShowDlg13_2();
                }
            }).start();

        }
        else {
            logger.fatal( "Мы заканчиваем этап 241, а инфы на него до сих пор нет!");
        }
    }//GEN-LAST:event_btn_13_01_NextActionPerformed

    private void btn_13_02_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_02_NextActionPerformed
        //НЕ ДОЛЖНЫ ПОПАДАТЬ СЮДА
    }//GEN-LAST:event_btn_13_02_NextActionPerformed

    private void btn_13_03_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_03_NextActionPerformed
        DefaultNextButtonProcessing( "243", "Старт основной откачки", "Завершение основной откачки",
                                       "244", "Старт откачки смеси с геттера", chk_13_03_AutoStart, logger, false);
    }//GEN-LAST:event_btn_13_03_NextActionPerformed

    private void btn_13_04_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_04_NextActionPerformed
        //мы в процессе исполнения подэтапа XXX, переходим к следующему подэтапу XXX+1
        if( theApp.IsStepMapContainsKey( "244")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "244");

            info.SetStopDateAsCurrent();
            info.SetStopReportTitle( "Завершение откачки смеси с геттера");
            info.SetStopP5( theApp.GetFromPoller( "005.01"));
            info.SetStopP6( theApp.GetFromPoller( "006.01"));
            info.SetStopP7( theApp.GetFromPoller( "007.01"));

            //theApp.NextCurrentStep();
            theApp.SetCurrentStep( 245);  //<<-- Да, несуществующий. Но это спрячет кнопку "Финиш"  :)
            
            theApp.m_pMainWnd.m_pPanel.SetStates();

            //КОНЕЦ ТЕХНОЛОГИЧЕСКОГО ПРОЦЕССА

            theApp.m_bProcessingEnded = true;
            theApp.m_ReportGenerator.Generate();
            theApp.m_pMainWnd.m_pPanel.Reposition();
        }
        else {
            logger.fatal( "Мы заканчиваем этап 244, а инфы на него до сих пор нет!");
        }
    }//GEN-LAST:event_btn_13_04_NextActionPerformed

    private void btn_13_01_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_01_StartActionPerformed
        if( theApp.IsCurrentStepInProgress() == true)
            return;

        //мы начинаем процесс
        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
        
        info.SetStartDateAsCurrent();
        info.SetStartReportTitle( "Старт программы bypass-откачки");
        info.SetStartP5( theApp.GetFromPoller( "005.01"));
        info.SetStartP6( theApp.GetFromPoller( "006.01"));
        info.SetStartP7( theApp.GetFromPoller( "007.01"));

        theApp.SaveStepInfo("241", info, true);

        theApp.m_ReportGenerator.Generate();

        theApp.SetCurrentStepInProgress( true);

        //StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_" + theApp.HumanNameForStep( "133") + ".xml");
        StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_13.1.xml");
        executor.StartThread();

        SetState();
    }//GEN-LAST:event_btn_13_01_StartActionPerformed

    private void btn_13_02_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_02_StartActionPerformed
        // STUB ручной этап
    }//GEN-LAST:event_btn_13_02_StartActionPerformed

    private void btn_13_03_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_03_StartActionPerformed
        DefaultStartButtonProcessing( "243", "Старт основной откачки", "Завершение основной откачки",
                                       "244", "Старт откачки смеси с геттера", chk_13_03_AutoStart, logger, false);
    }//GEN-LAST:event_btn_13_03_StartActionPerformed

    private void btn_13_04_StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_13_04_StartActionPerformed
        if( theApp.IsCurrentStepInProgress() == true)
            return;

        //мы начинаем процесс
        TechProcessStepInfo info = new TechProcessStepInfo( theApp);
        
        info.SetStartDateAsCurrent();
        info.SetStartReportTitle( "Старт откачки смеси с геттера");
        info.SetStartP5( theApp.GetFromPoller( "005.01"));
        info.SetStartP6( theApp.GetFromPoller( "006.01"));
        info.SetStartP7( theApp.GetFromPoller( "007.01"));

        theApp.SaveStepInfo( "244", info, true);

        theApp.m_ReportGenerator.Generate();

        //theApp.m_pMainWnd.m_EmuTimer.start();
        theApp.SetCurrentStepInProgress( true);
        StartProgramExecutor executor = new StartProgramExecutor( theApp, "AdminStep_13.4.xml");
        executor.StartThread();

        SetState();
    }//GEN-LAST:event_btn_13_04_StartActionPerformed

    private void lbl_13_00_Date_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_00_Date_startMouseClicked
        ProcessHeaderDateTimeClick( 13);
    }//GEN-LAST:event_lbl_13_00_Date_startMouseClicked

    private void lbl_13_00_Time_startMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_00_Time_startMouseClicked
        ProcessHeaderDateTimeClick( 13);
    }//GEN-LAST:event_lbl_13_00_Time_startMouseClicked

    private void lbl_13_01_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_01_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 241);
        }
    }//GEN-LAST:event_lbl_13_01_TitleMouseClicked

    private void lbl_13_02_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_02_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 242);
        }
    }//GEN-LAST:event_lbl_13_02_TitleMouseClicked

    private void lbl_13_03_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_03_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 243);
        }
    }//GEN-LAST:event_lbl_13_03_TitleMouseClicked

    private void lbl_13_04_TitleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_13_04_TitleMouseClicked
        if( evt.getButton() == MouseEvent.BUTTON3) {
            theApp.GetPendings().CallPendSettingDialog( 244);
        }
    }//GEN-LAST:event_lbl_13_04_TitleMouseClicked

    public void SetState() {
        if( theApp.GetCurrentStep() / 20 == m_nPageNumber)
            m_bCollapsed = false;
        
        if( theApp.IsStepMapContainsKey( "241"))
            FillHeaderStepDates("241", lbl_13_00_Date_start, lbl_13_00_Time_start, "244", lbl_13_00_Date_stop, lbl_13_00_Time_stop);
        else
            FillHeaderStepDates("244", lbl_13_00_Date_start, lbl_13_00_Time_start, "244", lbl_13_00_Date_stop, lbl_13_00_Time_stop);
        
        lbl_13_00_IcoStep.setIcon( m_bCollapsed ? theApp.GetResources().getIconTriaRight() : theApp.GetResources().getIconTriaDown());
        
        lbl_13_00_Title.setFont( theApp.GetCurrentStep() / 20 == m_nPageNumber ? theApp.GetBoldFont() : theApp.GetUsualFont());
        lbl_13_00_Title.setEnabled( theApp.GetCurrentStep() / 20 <= m_nPageNumber);
        
        
        if( m_bCollapsed == false) {
            //13.1 Bypass-откачка 
            FillAutoExecutedSubStep( 241,
                                    lbl_13_01_Date_start, lbl_13_01_Time_start, lblAnimation_13_01,
                                    btn_13_01_Start, lbl_13_01_Title, chk_13_01_AutoStart, lbl_13_01_IcoStep, btn_13_01_Next,
                                    lbl_13_01_Date_stop, lbl_13_01_Time_stop);
            chk_13_01_AutoStart.setEnabled( false);
            
            //13.2 Проверка герметичности
            FillAutoExecutedSubStep( 242,
                                    lbl_13_02_Date_start, lbl_13_02_Time_start, lblAnimation_13_02,
                                    btn_13_02_Start, lbl_13_02_Title, null, lbl_13_02_IcoStep, btn_13_02_Next,
                                    lbl_13_02_Date_stop, lbl_13_02_Time_stop);
            
            //13.3 Основная откачка
            FillAutoExecutedSubStep( 243,
                                    lbl_13_03_Date_start, lbl_13_03_Time_start, lblAnimation_13_03,
                                    btn_13_03_Start, lbl_13_03_Title, chk_13_03_AutoStart, lbl_13_03_IcoStep, btn_13_03_Next,
                                    lbl_13_03_Date_stop, lbl_13_03_Time_stop);
            
            //13.4 Откачка смеси с геттера
            FillAutoExecutedSubStep( 244,
                                    lbl_13_04_Date_start, lbl_13_04_Time_start, lblAnimation_13_04,
                                    btn_13_04_Start, lbl_13_04_Title, chk_13_04_AutoStart, lbl_13_04_IcoStep, btn_13_04_Next,
                                    lbl_13_04_Date_stop, lbl_13_04_Time_stop);
            
            if( theApp.GetCurrentStep() == 244) {
                btn_13_04_Start.setVisible( !theApp.IsCurrentStepInProgress());
                btn_13_04_Next.setVisible( theApp.IsCurrentStepInProgress());
            }
            else {
                btn_13_04_Start.setVisible( false);
                btn_13_04_Next.setVisible( false);
            }
        }
        
        btn_13_02_Start.setVisible( false);
        btn_13_02_Next.setVisible( false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_13_01_Next;
    public javax.swing.JButton btn_13_01_Start;
    private javax.swing.JButton btn_13_02_Next;
    public javax.swing.JButton btn_13_02_Start;
    private javax.swing.JButton btn_13_03_Next;
    public javax.swing.JButton btn_13_03_Start;
    private javax.swing.JButton btn_13_04_Next;
    public javax.swing.JButton btn_13_04_Start;
    public javax.swing.JCheckBox chk_13_01_AutoStart;
    public javax.swing.JCheckBox chk_13_03_AutoStart;
    public javax.swing.JCheckBox chk_13_04_AutoStart;
    public javax.swing.JLabel lblAnimation_13_01;
    public javax.swing.JLabel lblAnimation_13_02;
    public javax.swing.JLabel lblAnimation_13_03;
    public javax.swing.JLabel lblAnimation_13_04;
    public javax.swing.JLabel lbl_13_00_Date_start;
    private javax.swing.JLabel lbl_13_00_Date_stop;
    private javax.swing.JLabel lbl_13_00_IcoStep;
    public javax.swing.JLabel lbl_13_00_Time_start;
    private javax.swing.JLabel lbl_13_00_Time_stop;
    private javax.swing.JLabel lbl_13_00_Title;
    private javax.swing.JLabel lbl_13_01_Date_start;
    private javax.swing.JLabel lbl_13_01_Date_stop;
    public javax.swing.JLabel lbl_13_01_IcoStep;
    private javax.swing.JLabel lbl_13_01_Time_start;
    private javax.swing.JLabel lbl_13_01_Time_stop;
    private javax.swing.JLabel lbl_13_01_Title;
    private javax.swing.JLabel lbl_13_02_Date_start;
    private javax.swing.JLabel lbl_13_02_Date_stop;
    public javax.swing.JLabel lbl_13_02_IcoStep;
    private javax.swing.JLabel lbl_13_02_Time_start;
    private javax.swing.JLabel lbl_13_02_Time_stop;
    private javax.swing.JLabel lbl_13_02_Title;
    private javax.swing.JLabel lbl_13_03_Date_start;
    private javax.swing.JLabel lbl_13_03_Date_stop;
    public javax.swing.JLabel lbl_13_03_IcoStep;
    private javax.swing.JLabel lbl_13_03_Time_start;
    private javax.swing.JLabel lbl_13_03_Time_stop;
    private javax.swing.JLabel lbl_13_03_Title;
    private javax.swing.JLabel lbl_13_04_Date_start;
    private javax.swing.JLabel lbl_13_04_Date_stop;
    public javax.swing.JLabel lbl_13_04_IcoStep;
    private javax.swing.JLabel lbl_13_04_Time_start;
    private javax.swing.JLabel lbl_13_04_Time_stop;
    private javax.swing.JLabel lbl_13_04_Title;
    // End of variables declaration//GEN-END:variables
}
