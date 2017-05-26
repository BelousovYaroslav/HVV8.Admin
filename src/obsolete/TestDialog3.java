/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obsolete;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author yaroslav
 */
public class TestDialog3 extends javax.swing.JDialog {

    TechProcessPanel3 m_pPanel;
    
    Icon m_icoRight;
    Icon m_icoDown;
    
    private ImageIcon LoadIconShortCut( String strFilePathName) {
        File f = new File( strFilePathName);
        if(f.exists() && !f.isDirectory()) {
            return new ImageIcon( strFilePathName);
        }
        else {
            //logger.warn( "File not found: " + strFilePathName);
            return null;
        }
    }
    
    /**
     * Creates new form TestDialog
     */
    public TestDialog3(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        String strAMSRoot = "/home/yaroslav/HVV_HOME";
        m_icoRight    = LoadIconShortCut( strAMSRoot + "/res/images/right.gif");
        m_icoDown    = LoadIconShortCut( strAMSRoot + "/res/images/down.gif");
        
        m_pPanel = new TechProcessPanel3( this);
        m_pPanel.setVisible( true);
        pnlPanel.add( m_pPanel);
        
        m_pPanel.setBounds( 2, 2, 500, 400);
        
        jScrollBar1.setMaximum( 1650);
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
        jButton1 = new javax.swing.JButton();
        jScrollBar1 = new javax.swing.JScrollBar();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Административный модуль (С) ФЛАВТ, 2016. v1.0.0.0, 2016.03.03 16:45");
        setMinimumSize(new java.awt.Dimension(550, 550));
        getContentPane().setLayout(null);

        pnlPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlPanel.setMaximumSize(new java.awt.Dimension(504, 404));
        pnlPanel.setMinimumSize(new java.awt.Dimension(504, 404));
        pnlPanel.setPreferredSize(new java.awt.Dimension(504, 404));
        pnlPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                pnlPanelMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout pnlPanelLayout = new javax.swing.GroupLayout(pnlPanel);
        pnlPanel.setLayout(pnlPanelLayout);
        pnlPanelLayout.setHorizontalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlPanelLayout.setVerticalGroup(
            pnlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(pnlPanel);
        pnlPanel.setBounds(10, 40, 504, 404);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(470, 460, 72, 34);

        jScrollBar1.setMaximum(215);
        jScrollBar1.setPreferredSize(new java.awt.Dimension(20, 304));
        jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar1AdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(jScrollBar1);
        jScrollBar1.setBounds(520, 40, 20, 410);

        jLabel1.setText("jLabel1");
        jLabel1.setBorder(null);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 6, 530, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jScrollBar1AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar1AdjustmentValueChanged
        m_pPanel.setBounds( 2, 2 - jScrollBar1.getValue(), 500, 400 + jScrollBar1.getValue());
    }//GEN-LAST:event_jScrollBar1AdjustmentValueChanged

    private void pnlPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_pnlPanelMouseWheelMoved
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
    }//GEN-LAST:event_pnlPanelMouseWheelMoved

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
                System.out.println( info.getName());
                if ("GTK+".equals( info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestDialog3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestDialog3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestDialog3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestDialog3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TestDialog3 dialog = new TestDialog3(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JPanel pnlPanel;
    // End of variables declaration//GEN-END:variables
}
