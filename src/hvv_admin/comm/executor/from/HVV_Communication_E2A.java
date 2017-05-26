/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.comm.executor.from;

import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class HVV_Communication_E2A extends HVV_Communication.server.HVV_Comm_Server {
    final HVV_Admin theApp;
    static Logger logger = Logger.getLogger( HVV_Communication_E2A.class);
    
    private String m_strExecutorState;
    public String GetExecutorState() { return m_strExecutorState; }
    
    private String m_strExecutorProgram;
    public String GetExecutorProgram() { return m_strExecutorProgram; }
    
    public HVV_Communication_E2A( int nServerPort, HVV_Admin app) {
        super( "E2A_SRV: ", nServerPort);
        theApp = app;
        
        m_strExecutorState = "";
        m_strExecutorProgram = "";
    }
    
    @Override
    public void processIncomingCommand( String strReqId, LinkedList lstIncomingParcel) throws Exception {
        String strCmd = "";
        String strObject = "";
        
        int nRetCode = 0;
        
        strCmd = ( String) lstIncomingParcel.get( 0);
        if( strCmd != null) {
            switch( strCmd) {

                case "PING":
                    logger.debug( "INCOMING: [" + strReqId + ";PING;" + "]");
                    if( m_nStopRequested == 1) {
                        nRetCode = 100;
                        m_nStopRequested = 2;
                    }
                    else
                        nRetCode = 0;
                break;

                case "EXECUTOR_STATE":
                    m_strExecutorState = ( String) lstIncomingParcel.get( 1);
                    m_strExecutorProgram = ( String) lstIncomingParcel.get( 2);
                    nRetCode = 0;
                break;
                    
                case "FINISH_STEP":
                    strObject = ( String) lstIncomingParcel.get( 1);
                    if( strObject != null) {

                        logger.debug( "INCOMING: [" + strReqId + ";FINISH_STEP;" + strObject + "]");
                        logger.debug( "CURRENT_STEP: '" + theApp.GetCurrentStep() + "'");

                        boolean bOk = false;
                        switch( strObject) {
                            case  "1.1": if( theApp.GetCurrentStep() ==   1) bOk = true; break;

                            case  "2.2": if( theApp.GetCurrentStep() ==  22) bOk = true; break;
                            case  "2.3.1": theApp.m_bSpecial231ExecFinish = true;        break;
                            case  "2.4": if( theApp.GetCurrentStep() ==  24) bOk = true; break;

                            case  "3.1": if( theApp.GetCurrentStep() ==  41) bOk = true; break;
                            case  "3.2": if( theApp.GetCurrentStep() ==  42) bOk = true; break;
                            case  "3.3": if( theApp.GetCurrentStep() ==  43) bOk = true; break;
                            case  "3.4": if( theApp.GetCurrentStep() ==  44) bOk = true; break;
                            case  "3.5": if( theApp.GetCurrentStep() ==  45) bOk = true; break;
                            case  "3.6": if( theApp.GetCurrentStep() ==  46) bOk = true; break;

                            case  "4.1": if( theApp.GetCurrentStep() ==  61) bOk = true; break;
                            case  "4.2": if( theApp.GetCurrentStep() ==  62) bOk = true; break;
                            case  "4.3": if( theApp.GetCurrentStep() ==  63) bOk = true; break;
                            case  "4.4": if( theApp.GetCurrentStep() ==  64) bOk = true; break;
                            case  "4.5": if( theApp.GetCurrentStep() ==  65) bOk = true; break;
                            case  "4.6": if( theApp.GetCurrentStep() ==  66) bOk = true; break;
                            case  "4.7": if( theApp.GetCurrentStep() ==  67) bOk = true; break;

                            case  "5.4": if( theApp.GetCurrentStep() ==  84) bOk = true; break;
                            case  "5.5": if( theApp.GetCurrentStep() ==  85) bOk = true; break;

                            case  "6.1": if( theApp.GetCurrentStep() == 101) bOk = true; break;
                            case  "6.4": if( theApp.GetCurrentStep() == 104) bOk = true; break;

                            case  "7.1": if( theApp.GetCurrentStep() == 121) bOk = true; break;
                            case  "7.2": if( theApp.GetCurrentStep() == 122) bOk = true; break;
                            case  "7.3": if( theApp.GetCurrentStep() == 123) bOk = true; break;
                            case  "7.4": if( theApp.GetCurrentStep() == 124) bOk = true; break;
                            case  "7.5": if( theApp.GetCurrentStep() == 125) bOk = true; break;
                            case  "7.6": if( theApp.GetCurrentStep() == 126) bOk = true; break;
                            case  "7.7": if( theApp.GetCurrentStep() == 127) bOk = true; break;
                            case  "7.8": if( theApp.GetCurrentStep() == 128) bOk = true; break;
                            case  "7.9": if( theApp.GetCurrentStep() == 129) bOk = true; break;
                            case "7.10": if( theApp.GetCurrentStep() == 130) bOk = true; break;
                            case "7.11": if( theApp.GetCurrentStep() == 131) bOk = true; break;
                            case "7.12": if( theApp.GetCurrentStep() == 132) bOk = true; break;
                            case "7.13": if( theApp.GetCurrentStep() == 133) bOk = true; break;

                            case  "8.1": if( theApp.GetCurrentStep() == 141) bOk = true; break;
                            case  "8.2": if( theApp.GetCurrentStep() == 142) bOk = true; break;
    
                            case  "9.1": if( theApp.GetCurrentStep() == 161) bOk = true; break;
                            case  "9.2": if( theApp.GetCurrentStep() == 162) bOk = true; break;
                            case  "9.3": if( theApp.GetCurrentStep() == 163) bOk = true; break;
                            case  "9.4": if( theApp.GetCurrentStep() == 164) bOk = true; break;
                            case  "9.5": if( theApp.GetCurrentStep() == 165) bOk = true; break;
                            case  "9.6": if( theApp.GetCurrentStep() == 166) bOk = true; break;
                            case  "9.7": if( theApp.GetCurrentStep() == 167) bOk = true; break;
                            case  "9.8": if( theApp.GetCurrentStep() == 168) bOk = true; break;
                            case  "9.9": if( theApp.GetCurrentStep() == 169) bOk = true; break;

                            case "10.2": if( theApp.GetCurrentStep() == 182) bOk = true; break;
                                    
                            case "11.1": if( theApp.GetCurrentStep() == 201) bOk = true; break;
                            case "11.2": if( theApp.GetCurrentStep() == 202) bOk = true; break;

                            case "12.1": if( theApp.GetCurrentStep() == 221) bOk = true; break;
                            case "12.2": if( theApp.GetCurrentStep() == 222) bOk = true; break;
                            
                            case "13.1": if( theApp.GetCurrentStep() == 241) bOk = true; break;
                            case "13.2.1": theApp.m_bSpecial1321ExecFinish = true;       break;
                            case "13.3": if( theApp.GetCurrentStep() == 243) bOk = true; break;
                            case "13.4": if( theApp.GetCurrentStep() == 244) bOk = true; break;
                        }

                        if( bOk) {
                            theApp.m_pMainWnd.m_EmuTimer.start();
                        }
                        else {
                            logger.warn( "От EXECUTOR'а поступил сигнал о завершении этапа " + strObject);
                            logger.warn( "Однако текущий этап='" + theApp.GetCurrentStep() + "'");
                        }

                        nRetCode = 0;


                    }
                    else {
                        logger.error( "INCOMING [" + strReqId + ";FINISH_STEP; !!Step is NULL!!]. RetCode 4");
                        nRetCode = 4;
                    }
                break;

                case "REQ_HV_APPL":
                    int nRetByte = 0;
                    
                    boolean bDevReady;
                    switch( theApp.GetCurrentStep()) {
                        //3. обработка в кислороде
                        case 42:
                        case 45:
                            
                        //4. обработка в кислород-неоне
                        case 62:
                        case 65:
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1);
                            if( bDevReady) nRetByte += 0x01;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2);
                            if( bDevReady) nRetByte += 0x02;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3);
                            if( bDevReady) nRetByte += 0x04;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4);
                            if( bDevReady) nRetByte += 0x08;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5);
                            if( bDevReady) nRetByte += 0x10;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6);
                            if( bDevReady) nRetByte += 0x20;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7);
                            if( bDevReady) nRetByte += 0x40;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8);
                            if( bDevReady) nRetByte += 0x80;
                        break;
                            
                        
                        //7. тренировка катодов
                        case 123:
                        case 127:
                        case 131:
                            
                        //9. Тренировка в тренировочной смеси
                        case 163:
                        case 167:
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE1);
                            if( bDevReady) nRetByte += 0x01;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE2);
                            if( bDevReady) nRetByte += 0x02;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE3);
                            if( bDevReady) nRetByte += 0x04;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE4);
                            if( bDevReady) nRetByte += 0x08;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE5);
                            if( bDevReady) nRetByte += 0x10;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE6);
                            if( bDevReady) nRetByte += 0x20;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE7);
                            if( bDevReady) nRetByte += 0x40;
                            bDevReady = ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8) &
                                        ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE8);
                            if( bDevReady) nRetByte += 0x80;
                        break;
                            
                        
                    }
                    
                    nRetCode = nRetByte;
                break;
                    
                case "QUIT":
                    logger.info( "'QUIT' processing");
                    SetState( STATE_DISCONNECTED);
                    return;

                default:
                    logger.error( "" + strReqId + ": Unknown command '" + strCmd + "'. RetCode 3");
                    nRetCode = 3;
                break;
            }
        }
        else {
            logger.error( "" + strReqId + ": Command is null. RetCode 2");
            nRetCode = 2;
        }


        //RESPOND
        logger.debug( "RESPOND [ ID:" + strReqId + ". nRetCode=" + nRetCode + "]");

        GetObjectOutputStream().writeObject( strReqId);
        GetObjectOutputStream().writeInt( 1);
        GetObjectOutputStream().writeObject( nRetCode);
        GetObjectOutputStream().flush();
    }
    
}
