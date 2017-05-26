/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.steps.info;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 * @author yaroslav
 */
public class GettersActivationProgram {
    private Date m_dtStart;
    public Date GetDtStart() { return m_dtStart; }
    public void SetDtStart( Date dt) { m_dtStart = dt; }
    
    private Date m_dtFinish;
    public Date GetDtFinish() { return m_dtFinish; }
    public void SetDtFinish( Date dt) { m_dtFinish = dt; }
    
    private int m_nGetter;
    public int GetGetter() { return m_nGetter; }
    public void SetGetter( int nGetter) { m_nGetter = nGetter; }
    
    private int m_nInductor;
    public int GetInductor() { return m_nInductor; }
    public void SetInductor( int nInductor) { m_nInductor = nInductor; }

    private LinkedList m_lstSteps;
    public LinkedList GetListSteps() { return m_lstSteps; }
    public void SetListSteps( LinkedList lst) { m_lstSteps = lst; }
    public void CloneListSteps( Collection col) { m_lstSteps = new LinkedList( col); }
    
    public GettersActivationProgram() {
        m_dtStart = null;
        m_dtFinish = null;
        m_nInductor = -1;
        m_lstSteps = new LinkedList();
    }
    
    public GettersActivationProgram( Date dtStart, int nInductor) {
        m_dtStart = dtStart;
        m_dtFinish = null;
        m_nInductor = nInductor;
        m_lstSteps = new LinkedList();
    }
}
