/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin;

/**
 *
 * @author yaroslav
 */
public class ItemStepNames {
    private String m_strNum;
    public String GetNum() { return m_strNum; }
    public void SetType( String strNum) { m_strNum = strNum; }
    
    private String m_strName;
    public String GetName() { return m_strName; }
    public void SetName( String strName) { m_strName = strName; }
    
    public ItemStepNames( String strNum, String strName) {
        m_strNum = strNum;
        m_strName = strName;
    }
}
