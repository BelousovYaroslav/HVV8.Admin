/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.comm.executor.to;

import HVV_Communication.CommandItem;
import HVV_Communication.executors.AStatementExeRunnable;
import hvv_admin.HVV_Admin;
import static java.lang.Thread.sleep;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class StartProgramExecutor {

    public class ProcessorRunnable extends AStatementExeRunnable {

        @Override
        public void processResponse( LinkedList lstResponseParcel) {
            //VERIFY length == 1
            logger.debug( theApp.GetCommA2E().m_strMarker + "processResponse(?) call for StartProgramExecutor.");
            int nCode = ( int) lstResponseParcel.get( 0);
            logger.debug( theApp.GetCommA2E().m_strMarker + "processResponse( " + nCode + ") call for StartProgramExecutor.");

            if( nCode == 100) {
                logger.info( "Server want to exit! Disconnecting!");

                try {
                    /*
                    m_pCommThread.m_bStopRequested = true;
                    */
                    LinkedList lstQuitCmd = new LinkedList();
                    lstQuitCmd.addLast( "QUIT");
                    CommandItem quitItem = new CommandItem( null, lstQuitCmd);
                    theApp.GetCommA2E().GetRxTx().AddCommandToQueue( quitItem);

                    theApp.GetCommA2E().m_bServerStopRequested = true;
                } catch( Exception ex) {
                    logger.error( "Exception caught!", ex);
                }
            }
            m_bGotAnswer = true;
        }

        @Override
        public void processTimeOut() {
            logger.warn( theApp.GetCommA2E().m_strMarker + "processTimeOut() call for StartProgramExecutor. Empty statement!");
            m_bTimeOut = true;
        }

        @Override
        public void run() {
            m_bContinue = true;
            boolean bProcessing = false;

            m_bGotAnswer = false;
            m_bTimeOut = false;

            do {

                if( bProcessing == true) {
                    if( m_bGotAnswer == true) {
                        logger.debug( theApp.GetCommA2E().m_strMarker + "START_PROGRAM; RESPONDED;");
                        bProcessing = false;
                        m_bContinue = false;

                    }

                    if( m_bTimeOut == true) {
                        logger.warn( theApp.GetCommA2E().m_strMarker + "START_PROGRAM; TIMEOUT;");
                        bProcessing = false;
                        
                        m_bContinue = false;  //!!!!!!!
                    }

                }
                else {
                    m_bGotAnswer = false;
                    m_bTimeOut = false;

                    LinkedList lst = new LinkedList();

                    //START_PROGRAM
                    lst.addLast( "START_PROGRAM");
                    lst.addLast( m_strProgram);
                    
                    //ADDING COMMAND TO OUTPUT QUEUE WITH MENTION ABOUT ITSELF AS PROCESSOR
                    CommandItem item = new CommandItem( this, lst);
                    theApp.GetCommA2E().GetRxTx().AddCommandToQueue( item);

                    logger.info( theApp.GetCommA2E().m_strMarker + "START_PROGRAM; QUEUED;");
                    bProcessing = true;  
                }    

                try {
                    sleep( 100);
                } catch (InterruptedException ex) {
                    logger.error( theApp.GetCommA2E().m_strMarker + "InterruptedException caught!", ex);
                }

            } while( m_bContinue);
        }
        
    }
    
    static Logger logger = Logger.getLogger(StartProgramExecutor.class);            
    
    private boolean m_bGotAnswer;
    public boolean IsAnswerReceived() { return m_bGotAnswer; }
    
    private boolean m_bTimeOut;
    public boolean IsTimeOutHappens() { return m_bTimeOut; }

    private boolean m_bContinue;
    public void StopThread() {
        m_bContinue = false;
    }
    
    Thread m_pThread;
    ProcessorRunnable m_pProcessor;
    
    final HVV_Admin theApp;
    final String m_strProgram;
    
    public StartProgramExecutor( HVV_Admin app, String strProgram) {
        theApp = app;
        m_strProgram = strProgram;
    }
    
    public void StartThread() {
        m_pProcessor = new ProcessorRunnable();
        m_pThread = new Thread( new ProcessorRunnable());
        m_pThread.start();
    }
}
