/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.dialogs.step08;

import hvv_admin.HVV_Admin;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class P5PollerRunnable extends HVV_Communication.executors.AStatementExeRunnable {
    static final Logger logger = Logger.getLogger( P5PollerRunnable.class);
    
    private final HVV_Admin theApp;
    
    private boolean m_bSent;
    private boolean m_bGotAnswer;
    private boolean m_bTimeOut;
    private boolean m_bStopRequested;

    private double m_dblP5;
    public double GetP5() {
        return m_dblP5;
    }
    
    public P5PollerRunnable(HVV_Admin app) {
        m_dblP5 = Double.NaN;
        theApp = app;
    }
    
    public void RequestStop() {
        m_bStopRequested = true;
    }
    
    @Override
    public void run() {
        m_bSent = false;
        
        m_bStopRequested = false;
        
        boolean bProcessing = true;
        while( bProcessing) {
            if( m_bSent == false) {
                if( m_bStopRequested == true) {
                    bProcessing = false;
                }
                else {
                    
                    LinkedList lst = new LinkedList();
                
                    lst.addLast( "GET");
                    lst.addLast( "005.01");
                
                    HVV_Communication.CommandItem item = new HVV_Communication.CommandItem( this, lst);
                    theApp.GetCommA2P().GetRxTx().AddCommandToQueue( item);
                
                    m_bSent = true;
                    m_bGotAnswer = false;
                    m_bTimeOut = false;
                }
            }
            
            if( m_bGotAnswer == true) {
                m_bSent = false;
            }
            
            if( m_bTimeOut == true) {
                m_bSent = false;
            }
          
            try {
                Thread.sleep( 10);
            } catch ( InterruptedException ex) {
                logger.warn( "P5Poller sleep pause ends up with exception!", ex);
            }
        }
    }

    @Override
    public void processTimeOut() {
        logger.warn( "processTimeOut() call for P5PollerRunnable");
        m_bTimeOut = true;
    }

    @Override
    public void processResponse( LinkedList lstResponseParcel) {
        int nCode = ( int) lstResponseParcel.get( 0);
        logger.debug( theApp.GetCommA2P().m_strMarker + "processResponse( " + nCode + ") call for GetExecutor.");

        if( nCode == 0) {
            if( lstResponseParcel.size() == 2) {
                logger.trace( "GET-command is processed successfully. got value=" + m_dblP5);
                m_dblP5 = ( double) lstResponseParcel.get( 1);
            }
            else
                logger.error( theApp.GetCommA2P().m_strMarker + "RetCode for GET-command = 0, but response length != 2! STRANGE!");
        }
        else if( nCode == 100) {
            logger.info( theApp.GetCommA2P().m_strMarker + "Server want to exit! Disconnecting!");

            try {
                /*
                m_pCommThread.m_bStopRequested = true;
                */
                LinkedList lstQuitCmd = new LinkedList();
                lstQuitCmd.addLast( "QUIT");
                HVV_Communication.CommandItem quitItem = new HVV_Communication.CommandItem( null, lstQuitCmd);
                theApp.GetCommA2P().GetRxTx().AddCommandToQueue( quitItem);

                theApp.GetCommA2P().m_bServerStopRequested = true;
            } catch( Exception ex) {
                logger.error( theApp.GetCommA2P().m_strMarker + "Exception caught!", ex);
            }
        }
        else {
            logger.warn( theApp.GetCommA2P().m_strMarker + "Got problem answer! Don't processing! (TODO)");
        }
        m_bGotAnswer = true;
    }
    
}
