/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step10;

import hvv_admin.dialogs.step08.*;
import hvv_admin.HVV_Admin;
import org.apache.log4j.Logger;


/**
 *
 * @author yaroslav
 */
public class TechProcessStep10p5MaxWatchdog implements Runnable {
    static Logger logger = Logger.getLogger(TechProcessStep10p5MaxWatchdog.class);
    
    private double m_dblP5max;
    public double GetP5Max() { return m_dblP5max; }
    
    final private HVV_Admin theApp;
    public TechProcessStep10p5MaxWatchdog( HVV_Admin app) {
        theApp = app;
        m_dblP5max = 0.;
    }
    
    public void DropMax() {
        m_dblP5max = 0.;
    }
    
    public void ProcessValue( double val) {
        if( val > m_dblP5max) m_dblP5max = val;
    }

    private boolean m_bContinue;
    
    public void StopThread() {
        m_bContinue = false;
    }
    
    @Override
    public void run() {
        m_bContinue = true;
        while( m_bContinue) {
            ProcessValue( theApp.GetDoubleFromPoller( "005.01"));
            try {
                Thread.sleep( 10);
            } catch( InterruptedException ex) {
                logger.error( "В процессе ожидания потока отслеживания максимального P5, произошла exception", ex);
            }
        }
    }
}
