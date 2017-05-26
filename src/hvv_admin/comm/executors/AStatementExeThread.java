/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.comm.executors;

import hvv_admin.HVV_Admin;

/**
 *
 * @author yaroslav
 */
abstract public class AStatementExeThread implements Runnable {

    protected HVV_Admin theApp;
    
    public AStatementExeThread( HVV_Admin app) {
        theApp = app;
    }
    
    abstract public void processResponse( int nCode);
    abstract public void processTimeOut();
}
