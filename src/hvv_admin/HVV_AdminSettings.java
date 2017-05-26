/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author yaroslav
 */
public class HVV_AdminSettings {
    static Logger logger = Logger.getLogger(HVV_AdminSettings.class);
    
    //POLLER
    private String m_strPollerPartHost;
    public String GetPollerPartHost() { return m_strPollerPartHost;}
    
    private int m_nPollerPartPort;
    public int GetPollerPartPort() { return m_nPollerPartPort;}
    
    
    //EXECUTOR.TO
    private String m_strExecToPartHost;
    public String GetExecToPartHost() { return m_strExecToPartHost;}
    
    private int m_nExecToPartPort;
    public int GetExecToPartPort() { return m_nExecToPartPort;}
    
    
    //EXECUTOR.FROM
    private String m_strExecFromPartHost;
    public String GetExecFromPartHost() { return m_strExecFromPartHost;}
    
    private int m_nExecFromPartPort;
    public int GetExecFromPartPort() { return m_nExecFromPartPort;}
    
    
    //HV
    private String m_strHvPartHost;
    public String GetHvPartHost() { return m_strHvPartHost;}
    
    private int m_nHvPartPort;
    public int GetHvPartPort() { return m_nHvPartPort;}
    
    
    
    private int m_nSingleInstanceSocketServerPort;
    public int GetSingleInstanceSocketServerPort() { return m_nSingleInstanceSocketServerPort;}
    
    private int m_nTimeZoneShift;
    public int GetTimeZoneShift() { return m_nTimeZoneShift;}
    
    private boolean m_bDebugShortenProgTimes;
    public boolean GetDebugShortenProgTimes() { return m_bDebugShortenProgTimes; }
    
    private boolean m_bDebugShortenProgItems;
    public boolean GetDebugShortenProgItems() { return m_bDebugShortenProgItems; }
    
    private boolean m_bUsePlanner;
    public boolean GetUsePlanner() { return m_bUsePlanner; }
    
    public HVV_AdminSettings( String strAMSRoot) {
        m_strPollerPartHost = "localhost";
        m_nPollerPartPort = 6343;
        
        m_strExecToPartHost = "localhost";
        m_nExecToPartPort = 6345;
        
        m_strExecFromPartHost = "localhost";
        m_nExecFromPartPort = 6346;
        
        m_strHvPartHost = "localhost";
        m_nHvPartPort = 6347;
        
        m_nSingleInstanceSocketServerPort = 10003;
        
        //TIME ZONE SHIFT
        m_nTimeZoneShift = 1;
        
        m_bDebugShortenProgItems = false;
        m_bDebugShortenProgTimes = false;
        
        m_bUsePlanner = false;
        
        ReadSettings();
    }
    
    private boolean ReadSettings() {
        boolean bResOk = true;
        try {
            SAXReader reader = new SAXReader();
            
            String strSettingsFilePathName = System.getenv( "AMS_ROOT") + "/etc/settings.admin.xml";
            URL url = ( new java.io.File( strSettingsFilePathName)).toURI().toURL();
            
            Document document = reader.read( url);
            
            Element root = document.getRootElement();

            // iterate through child elements of root
            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                String value = element.getText();
                
                //logger.debug( "Pairs: [" + name + " : " + value + "]");
                
                if( "poller.host".equals( name)) m_strPollerPartHost = value;
                if( "poller.port".equals( name)) m_nPollerPartPort = Integer.parseInt( value);
                
                if( "executor.to.host".equals( name)) m_strExecToPartHost = value;
                if( "executor.to.port".equals( name)) m_nExecToPartPort = Integer.parseInt( value);
                
                if( "executor.from.host".equals( name)) m_strExecFromPartHost = value;
                if( "executor.from.port".equals( name)) m_nExecFromPartPort = Integer.parseInt( value);
                
                //if( "hv.host".equals( name)) m_strPollerPartHost = value;
                //if( "hv.port".equals( name)) m_nPollerPartPort = Integer.parseInt( value);
                
                if( "timezone".equals( name)) m_nTimeZoneShift = Integer.parseInt( value);
                
                if( "debug.shorten.program.items".equals( name)) {
                    if( "true".equals( value))
                        m_bDebugShortenProgItems = true;
                }
                
                if( "debug.shorten.program.times".equals( name)) {
                    if( "true".equals( value))
                        m_bDebugShortenProgTimes = true;
                }
                
                if( "use.planner".equals( name)) {
                    if( "true".equals( value))
                        m_bUsePlanner = true;
                }
            }
            
        } catch( MalformedURLException ex) {
            logger.error( "MalformedURLException caught while loading settings!", ex);
            bResOk = false;
        } catch( DocumentException ex) {
            logger.error( "DocumentException caught while loading settings!", ex);
            bResOk = false;
        }
        
        return bResOk;
    }
    
    public boolean ReadSettingsDbg() {
        boolean bResOk = true;
        try {
            SAXReader reader = new SAXReader();
            
            String strSettingsFilePathName = System.getenv( "AMS_ROOT") + "/etc/settings.admin.xml";
            URL url = ( new java.io.File( strSettingsFilePathName)).toURI().toURL();
            
            Document document = reader.read( url);
            
            Element root = document.getRootElement();

            // iterate through child elements of root
            for ( Iterator i = root.elementIterator(); i.hasNext(); ) {
                Element element = (Element) i.next();
                String name = element.getName();
                String value = element.getText();
                
                //logger.debug( "Pairs: [" + name + " : " + value + "]");
                
                if( "debug.shorten.program.items".equals( name)) {
                    if( "true".equals( value))
                        m_bDebugShortenProgItems = true;
                    else
                        m_bDebugShortenProgItems = false;
                        
                }
                
                if( "debug.shorten.program.times".equals( name)) {
                    if( "true".equals( value))
                        m_bDebugShortenProgTimes = true;
                    else
                        m_bDebugShortenProgTimes = false;
                }
            }
            
        } catch( MalformedURLException ex) {
            logger.error( "MalformedURLException caught while loading settings!", ex);
            bResOk = false;
        } catch( DocumentException ex) {
            logger.error( "DocumentException caught while loading settings!", ex);
            bResOk = false;
        }
        
        return bResOk;
    }
}
