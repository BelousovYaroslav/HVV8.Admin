/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.steps.info;

import java.util.Date;

/**
 *
 * @author yaroslav
 */
public class GettersActivationProgramStep {
    private int m_nDuration;
    public int GetDuration() { return m_nDuration; }
    public void SetDuration( int nDuration) { m_nDuration = nDuration; }
    
    private int m_nPower;
    public int GetPower() { return m_nPower; }
    public void SetPower( int nPower) { m_nPower = nPower; }
    
    private double m_dblP5_start;
    public double GetP5_start() { return m_dblP5_start; }
    public void SetP5_start( double dblP5_start) { m_dblP5_start = dblP5_start; }
    
    private double m_dblP5_max;
    public double GetP5_max() { return m_dblP5_max; }
    public void SetP5_max( double dblP5_max) { m_dblP5_max = dblP5_max; }
    
    private double m_dblP5_last;
    public double GetP5_last() { return m_dblP5_last; }
    public void SetP5_last( double dblP5_last) { m_dblP5_last = dblP5_last; }
    
    public GettersActivationProgramStep() {
        m_nDuration = 0;
        m_nPower = 0;
        m_dblP5_start = 0.;
        m_dblP5_max = 0.;
        m_dblP5_last = 0.;
    }
    
    public GettersActivationProgramStep( int nDuration, int nPower) {
        m_nDuration = nDuration;
        m_nPower = nPower;
        m_dblP5_start = 0.;
        m_dblP5_max = 0.;
        m_dblP5_last = 0.;
    }
}
