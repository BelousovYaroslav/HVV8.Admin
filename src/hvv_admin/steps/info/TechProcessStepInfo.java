/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.steps.info;

import hvv_admin.HVV_Admin;
import hvv_admin.HVV_Admin;
import java.util.Date;

/**
 *
 * @author yaroslav
 */
public class TechProcessStepInfo {
    HVV_Admin theApp;
    
    //START
    private Date m_dtStart;
    public Date GetStartDate() { return m_dtStart;}
    public void SetStartDate( Date dt) { m_dtStart = dt;}
    public void SetStartDateAsCurrent() { m_dtStart = theApp.GetLocalDate();}
    
    private String m_strStartReportTitle;
    public String GetStartReportTitle() { return m_strStartReportTitle;}
    public void SetStartReportTitle( String strStartReportTitle) { m_strStartReportTitle = strStartReportTitle;}
    
    private Double m_dblStartP5;
    public Double GetStartP5() { return m_dblStartP5;}
    public void SetStartP5( Double p5) { m_dblStartP5 = p5;}
    
    private Double m_dblStartP6;
    public Double GetStartP6() { return m_dblStartP6;}
    public void SetStartP6( Double p6) { m_dblStartP6 = p6;}
    
    private Double m_dblStartP7;
    public Double GetStartP7() { return m_dblStartP7;}
    public void SetStartP7( Double p7) { m_dblStartP7 = p7;}
    
    // STOP
    private Date m_dtStop;
    public Date GetStopDate() { return m_dtStop;}
    public void SetStopDate( Date dt) { m_dtStop = dt;}
    public void SetStopDateAsCurrent() { m_dtStop = theApp.GetLocalDate();}
    
    private String m_strStopReportTitle;
    public String GetStopReportTitle() { return m_strStopReportTitle;}
    public void SetStopReportTitle( String strStopReportTitle) { m_strStopReportTitle = strStopReportTitle;}
    
    private Double m_dblStopP5;
    public Double GetStopP5() { return m_dblStopP5;}
    public void SetStopP5( Double p5) { m_dblStopP5 = p5;}
    
    private Double m_dblStopP6;
    public Double GetStopP6() { return m_dblStopP6;}
    public void SetStopP6( Double p6) { m_dblStopP6 = p6;}
    
    private Double m_dblStopP7;
    public Double GetStopP7() { return m_dblStopP7;}
    public void SetStopP7( Double p7) { m_dblStopP7 = p7;}
    
    private boolean m_bExecutorApproved;
    public boolean GetExecApproved() { return m_bExecutorApproved;}
    public void SetExecApproved() { m_bExecutorApproved = true;}
    
    public TechProcessStepInfo( HVV_Admin app) {
        theApp = app;
        
        m_strStartReportTitle = null;
        m_strStopReportTitle = null;
    }
}
