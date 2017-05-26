/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.planner;

import java.util.Date;

/**
 *
 * @author yaroslav
 */
public class ItemPlanner {
    static final int PLANNER_ABSOLUTE_TIME = 1;
    static final int PLANNER_RELATIVE_TIME = 2;
    
    private int m_nType;
    public int GetType() { return m_nType; }
    public void SetType( int nType) { m_nType = nType; }
    
    Date m_dtStepAbsoluteDt;
    long m_lStepRelativedDurationSecs;
    long m_lStepDefaultDuration;
    
    /**
     * Конструктор
     * @param lStepDefaultDuration
     * Default-длительность этапа (в секундах)
     */
    public ItemPlanner( long lStepDefaultDuration) {
        m_nType = 0;
        m_lStepDefaultDuration = lStepDefaultDuration;
    }
    
    /**
     * Инициализировать объект как запуск этапа в кокретно указанное (абсолютное) время
     * @param dtStart Date-объект описывающий дату-время начала этапа
     */
    public void SetupAsAbsolute( Date dtStart) {
        m_nType = PLANNER_ABSOLUTE_TIME;
        m_dtStepAbsoluteDt = dtStart;
    }
    
    /**
     * Инициализировать объект как запуск этапа относительно предыдущего этапа
     * @param lStepRelativeOffsetInSeconds - кол-во секунд 
     */
    public void SetupAsRelative( long lStepRelativeOffsetInSeconds) {
        m_nType = PLANNER_RELATIVE_TIME;
        m_lStepRelativedDurationSecs = lStepRelativeOffsetInSeconds;
    }
    
    
    /**
     * Проверка валидности планирования текущей plan-unit
     * @param dtFlowRun текущая "бегущая дата"
     * @return 
     * 0 - success<br>
     * 1 - time-unit not valid<br>
     * 2 - plan-time-unit абсолютная, но указанная дата-время наступит раньше чем от текущего running-time пройдёт default-длительность<br>
     * 3 - plan-time-unit относительная, но указанная длительность меньше default-длительности
     */
    public int Validation( Date dtFlowRun) {
        int nRetCode = 0;
        switch( m_nType) {
            case PLANNER_ABSOLUTE_TIME:
                
                long dt1 = dtFlowRun.getTime() / 1000;
                dt1 += m_lStepDefaultDuration;
                long dt2 = m_dtStepAbsoluteDt.getTime() / 1000;
                
                if( dtFlowRun.getTime() / 1000 + m_lStepDefaultDuration > m_dtStepAbsoluteDt.getTime() / 1000) {
                    nRetCode = 2;
                }
            break;
                
            case PLANNER_RELATIVE_TIME:
                if( m_lStepRelativedDurationSecs < m_lStepDefaultDuration) {
                    nRetCode = 3;
                }
            break;
                
            default:
                nRetCode = 1;
        }
        return nRetCode;
    }
    
    public Date ProcessIncrement( Date dtFlowRun) {
        Date dtReturn;
        switch( m_nType) {
            case PLANNER_ABSOLUTE_TIME:
                dtReturn = ( Date) m_dtStepAbsoluteDt.clone();
            break;
                
            case PLANNER_RELATIVE_TIME:
                dtReturn = new Date( dtFlowRun.getTime() + m_lStepRelativedDurationSecs * 1000);
            break;
                
            default:
                dtReturn = new Date( dtFlowRun.getTime() + m_lStepDefaultDuration * 1000);
        }
        
        return dtReturn;
    }
}
