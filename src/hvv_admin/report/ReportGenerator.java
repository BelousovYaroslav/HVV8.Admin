/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin.report;

import hvv_admin.HVV_Admin;
import hvv_admin.HVV_AdminConstants;
import hvv_admin.steps.info.GettersActivationProgram;
import hvv_admin.steps.info.GettersActivationProgramStep;
import hvv_admin.steps.info.TechProcessStepInfo;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.max;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class ReportGenerator {
    
    HVV_Admin theApp;
    boolean m_bSpecialCase63_AllWentOff;
    int m_nDecisionOnStep11_5;
    static Logger logger = Logger.getLogger( ReportGenerator.class);
    
    public ReportGenerator( HVV_Admin app) {
        theApp = app;
    }
    
    public String Gen_NiceDate( Date dt) {
        String strResult;
                
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( dt);

        strResult = String.format( "%02d.%02d.%02d",
                clndr.get(Calendar.DAY_OF_MONTH),
                clndr.get(Calendar.MONTH) + 1,
                clndr.get(Calendar.YEAR));
        
        return strResult;
    }
    
    public String Gen_NiceTime( Date tm) {
        String strResult;
                
        GregorianCalendar clndr = new GregorianCalendar();
        clndr.setTime( tm);

        strResult = String.format( "%02d:%02d:%02d",
                clndr.get(Calendar.HOUR_OF_DAY),
                clndr.get(Calendar.MINUTE),
                clndr.get(Calendar.SECOND));
        
        return strResult;
    }
    
    public String Gen_NiceDoubleGen( Double dbl, boolean bPoint) {
        String strResult;
    
        if( dbl.isNaN()) {
            return "NaN";
        }
        
        int nExp = ( int) Math.log10( dbl);
        float dblMant = ( float) ( dbl / Math.pow( 10, nExp));
        if( nExp > -2 && nExp < 2)
            strResult = String.format( "%.02f", dbl);
        else
            if( bPoint)
                strResult = String.format( "%.02f‧10<sup>%d</sup>", dblMant, nExp);
            else
                strResult = String.format( "%.02f 10<sup>%d</sup>", dblMant, nExp);
        
        strResult = strResult.replace( ",", ".");
        return strResult;
    }
    
    public String Gen_NiceDoubleP5( Double dbl, boolean bPoint) {
        String strResult;
    
        if( dbl == null) {
            return "NULL";
        }
        
        if( dbl.isNaN()) {
            return "NaN";
        }
        
        int nExp = ( int) Math.log10( dbl);
        if( nExp < 0) nExp--;
        
        float dblMant = ( float) ( dbl / Math.pow( 10, nExp));
        if( nExp > -2 && nExp < 2)
            strResult = String.format( "%.02f", dbl);
        else {
            if( bPoint)
                if( dblMant != 10.)
                    strResult = String.format( "%.02f‧10<sup>%d</sup>", dblMant, nExp);
                else
                    strResult = String.format( "%.01f‧10<sup>%d</sup>", dblMant, nExp);
            else
                if( dblMant != 10.)
                    strResult = String.format( "%.02f 10<sup>%d</sup>", dblMant, nExp);
                else
                    strResult = String.format( "%.01f 10<sup>%d</sup>", dblMant, nExp);
        }
        
        strResult = strResult.replace( ",", ".");
        
        return strResult;
    }
    
    public String Gen_NiceDoubleP6P7( Double dbl, boolean bPoint) {
        String strResult;
    
        if( dbl == null) {
            return "NULL";
        }
        
        if( dbl.isNaN()) {
            return "NaN";
        }
        
        strResult = String.format( "%.01f", dbl);
        strResult = strResult.replace( ",", ".");
        return strResult;
    }
    
    public void Gen_Header( FileWriter writer) throws IOException {
        writer.write( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n");
        writer.write( "<html>\n");
        writer.write( "<title>Отчёт о процессе технологической обработки</title>\n");
        writer.write(  "<body>\n");
        
        Date dtStart = theApp.GetLocalDate();
        if( theApp.IsStepMapContainsKey( "001")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "001");
            if( info.GetStartDate() != null)
                dtStart = info.GetStartDate();
        }
        
        Date dtFinish = theApp.GetLocalDate();
        if( theApp.IsStepMapContainsKey( "244")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "244");
            if( info.GetStopDate() != null)
                dtFinish = info.GetStopDate();
        }
        
        writer.write( "<H1>Отчёт о процессе технологической обработки<br>" + 
                Gen_NiceDate( dtStart) + " - " +
                Gen_NiceDate( dtFinish) + "</H1>\n");
    }
    
    public void Gen_TableHeader( FileWriter writer) throws IOException {
        writer.write( "<table>\n");
        writer.write( " <tr>\n");
        writer.write( "  <th width=\"120\" align=\"left\">Дата</th>\n");
        writer.write( "  <th width=\"100\" align=\"left\">Время</th>\n");
        writer.write( "  <th width=\"500\" align=\"left\">Операция</th>\n");
        writer.write( "  <th width=\"120\" align=\"left\">P<sub>5</sub>, Па</th>\n");
        writer.write( "  <th width=\"120\" align=\"left\">P<sub>6</sub>, Па</th>\n");
        writer.write( "  <th width=\"120\" align=\"left\">P<sub>7</sub>, Па</th>\n");
        writer.write( " </tr>\n");
    }
    
    public void Gen_TableLine( FileWriter writer, String strItemId) throws IOException {
        if( theApp.IsStepMapContainsKey( strItemId)) {
            TechProcessStepInfo info = theApp.GetStepInfo( strItemId);
        
            if( info.GetStartReportTitle() != null && !info.GetStartReportTitle().isEmpty()) {
                writer.write( " <tr height=\"30\">\n");
                writer.write( "  <td>" + Gen_NiceDate( info.GetStartDate()) + "</td>\n");
                writer.write( "  <td>" + Gen_NiceTime( info.GetStartDate()) + "</td>\n");
                writer.write( "  <td>" + info.GetStartReportTitle() + "</td>\n");
                writer.write( "  <td>" + Gen_NiceDoubleP5(info.GetStartP5(), true) + "</td>\n");
                writer.write( "  <td>" + Gen_NiceDoubleP6P7(info.GetStartP6(), true) + "</td>\n");
                writer.write( "  <td>" + Gen_NiceDoubleP6P7( info.GetStartP7(), true) + "</td>\n");
                writer.write( " </tr>\n");
            }
            else
                logger.warn( "Нет стартового заголовка по этапу " + strItemId);
            
            if( info.GetStopReportTitle() != null && !info.GetStopReportTitle().isEmpty()) {
                writer.write( " <tr height=\"30\">\n");
                writer.write( "  <td>" + Gen_NiceDate( info.GetStopDate()) + "</td>\n");
                writer.write( "  <td>" + Gen_NiceTime( info.GetStopDate()) + "</td>\n");
                writer.write( "  <td>" + info.GetStopReportTitle() + "</td>\n");
                writer.write( "  <td>" + Gen_NiceDoubleP5( info.GetStopP5(), true) + "</td>\n");
                writer.write( "  <td>" + Gen_NiceDoubleP6P7(info.GetStopP6(), true) + "</td>\n");
                writer.write( "  <td>" + Gen_NiceDoubleP6P7(info.GetStopP7(), true) + "</td>\n");
                writer.write( " </tr>\n");
            }
            else
                logger.warn( "Нет финишного заголовка по этапу " + strItemId);
            
        }
        else {
            logger.warn( "Нет информации по этапу " + strItemId);
            /*
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>нет информации об этапе " + strItemId + "</td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            */
        }
    }
    
    public void Gen_Ch01( FileWriter writer) throws IOException {
        writer.write( "<H3>1. Подготовка</H3>\n");

        //<table>
        Gen_TableHeader( writer);
        Gen_TableLine( writer, "001");

        writer.write( "</table>\n<br>\n\n<!-- ** STEP2 ** -->\n");
    }
    
    public void Gen_Ch02( FileWriter writer) throws IOException {
        boolean bContinue = true;
        writer.write( "<H3>2. Установка приборов</H3>\n");
        
        //2.1 Занесение информации об установленных приборах
        //<table>
        Gen_TableHeader( writer);
        Gen_TableLine( writer, "021");
        
        bContinue &= ( theApp.GetCurrentStep() > 21);
        if( bContinue) {
            writer.write( "</table>\n\n<br>\n<table>\n <tr>\n");
            writer.write( "  <th width=\"220\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"150\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">1</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">2</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">3</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">4</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">5</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">6</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">7</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">8</th>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr height=\"30\">\n  <td></td>\n");
            writer.write( "  <td>Номера резонаторов</td>\n");
            
            String strDevNum;
            
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE1);
            writer.write( "  <td align=\"center\">" +  ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE2);
            writer.write( "  <td align=\"center\">" + ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE3);
            writer.write( "  <td align=\"center\">" + ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE4);
            writer.write( "  <td align=\"center\">" + ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE5);
            writer.write( "  <td align=\"center\">" + ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE6);
            writer.write( "  <td align=\"center\">" + ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE7);
            writer.write( "  <td align=\"center\">" + ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            strDevNum = ( String) theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE8);
            writer.write( "  <td align=\"center\">" + ( strDevNum.isEmpty() ? "-" : strDevNum) + "</td>\n");
            
            writer.write( " </tr>\n</table>\n\n<br>\n");
        }
        
        
        //2.2 Предварительная откачка
        bContinue &= ( theApp.GetCurrentStep() > 22);
        if( bContinue) {
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n");
        
            Gen_TableLine( writer, "022");
        }
        
        //2.3 Проверка герметичности установки приборов
        bContinue &= ( theApp.GetCurrentStep() > 23);
        if( bContinue) {
            Gen_TableLine( writer, "023");
        }
        
        //2.4 Основная откачка
        bContinue &= ( theApp.GetCurrentStep() >= 24);
        if( bContinue) {
            Gen_TableLine( writer, "024");
        }
        
        writer.write( " </table>\n<br>\n\n<!-- ** STEP3 ** -->\n");
    }
    
    public void Gen_Ch03( FileWriter writer) throws IOException {
        writer.write( "<P style=\"page-break-before: always\">\n");
        writer.write( "<H3>3. Обработка в среде кислорода</H3>\n");
        
        if( theApp.GetCurrentStep() >= 41) {
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"><u>1-ый цикл</u></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "041");
        }
        
        if( theApp.GetCurrentStep() >= 42) {
            Gen_TableLine( writer, "042");
        }
        
        if( theApp.GetCurrentStep() >= 43) {
            Gen_TableLine( writer, "043");
        }
        
        
        if( theApp.GetCurrentStep() >= 44) {
            writer.write( " <tr height=\"10\">\n</tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>2-ой цикл</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "044");
        }
        
        if( theApp.GetCurrentStep() >= 45) {
            Gen_TableLine( writer, "045");
        }
        if( theApp.GetCurrentStep() >= 46) {
            Gen_TableLine( writer, "046");
        }
        
        
        
        writer.write( "</table>\n\n<br>\n<!-- ** STEP4 ** -->\n");
    }
    
    public void Gen_Ch04( FileWriter writer) throws IOException {
        writer.write( "<P style=\"page-break-before: always\">\n");
        writer.write( "<H3>4. Обработка в среде кислород-неона</H3>\n");
        
        if( theApp.GetCurrentStep() >= 61) {
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"><u>1-ый цикл</u></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "061");
        }
        
        if( theApp.GetCurrentStep() >= 62) {
            Gen_TableLine( writer, "062");
        }
        
        if( theApp.GetCurrentStep() >= 63) {
            Gen_TableLine( writer, "063");
        }
        
        
        if( theApp.GetCurrentStep() >= 64) {
            writer.write( " <tr height=\"10\">\n </tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>2-ой цикл</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "064");
        }
        
        if( theApp.GetCurrentStep() >= 65) {
            Gen_TableLine( writer, "065");
        }
        if( theApp.GetCurrentStep() >= 66) {
            Gen_TableLine( writer, "066");
        }
        
        
        if( theApp.GetCurrentStep() >= 67) {
            writer.write( " <tr height=\"10\">\n</tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>Завершение этапа</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "067");
        }
        
        writer.write( "</table>\n\n<br>\n<!-- ** STEP5 ** -->\n");
    }
    
    public void Gen_Ch05( FileWriter writer) throws IOException {
        boolean bContinue = true;
        writer.write( "<H3>5. Термообезгаживание</H3>\n");
        Gen_TableHeader( writer);
        
        bContinue &= ( theApp.GetCurrentStep() >= 81);
        if( bContinue) {
            //5.1 Установка печек (только START часть)
            Gen_TableLine( writer, "081");
        }
        
        
        bContinue &= ( theApp.GetCurrentStep() >= 82);
        if( bContinue) {
            //5.2 Включение PID-регулирования печек (только START часть)
            Gen_TableLine( writer, "082");
        }
        
        //SPECIAL CASE - открытие геттера
        bContinue &= ( theApp.GetCurrentStep() >= 83);
        if( bContinue) {
            //5.2.1 Открытие геттера (только START часть)
            Gen_TableLine( writer, "082.1");
        }
        
        bContinue &= ( theApp.GetCurrentStep() >= 83);
        if( bContinue) {
            //5.3 Снятие печек (только START часть)
            Gen_TableLine( writer, "083");
        }
        
        
        bContinue &= ( theApp.GetCurrentStep() >= 84);
        if( bContinue) {
            //5.4 Заполнение рабочей смесью
            Gen_TableLine( writer, "084");
        }
        
        
        bContinue &= ( theApp.GetCurrentStep() >= 85);
        if( bContinue) {
            //5.5 Выдержка
            Gen_TableLine( writer, "085");
        }
        
        
        writer.write( " </table>\n<br>\n\n<!-- ** STEP6 ** -->\n");
    }
    
    public void Gen_Ch06( FileWriter writer) throws IOException {
        boolean bContinue = true;
        
        LinkedList lst = new LinkedList();
        lst.addLast( HVV_AdminConstants.DEVICE1); lst.addLast( HVV_AdminConstants.DEVICE2);
        lst.addLast( HVV_AdminConstants.DEVICE3); lst.addLast( HVV_AdminConstants.DEVICE4);
        lst.addLast( HVV_AdminConstants.DEVICE5); lst.addLast( HVV_AdminConstants.DEVICE6);
        lst.addLast( HVV_AdminConstants.DEVICE7); lst.addLast( HVV_AdminConstants.DEVICE8);
        
        writer.write( "<P style=\"page-break-before: always\">\n<H3>6. Предварительная оценка параметров приборов</H3>\n");
        
        //<table>
        Gen_TableHeader( writer);
        
        //6.1 Снятие вольт-амперной-характеристики анодов
        bContinue &= ( theApp.GetCurrentStep() >= 101);
        if( bContinue) {
            Gen_TableLine( writer, "101");
        
        
            writer.write( "</table>\n\n<br>\n");

            //TABLE WITH DATA
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"220\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"150\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">1</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">2</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">3</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">4</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">5</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">6</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">7</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">8</th>\n");

            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>1000 мкА</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1000mcA.get( HVV_AdminConstants.DEVICE8) + "</td>\n");

            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>1100 мкА</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1100mcA.get( HVV_AdminConstants.DEVICE8) + "</td>\n");

            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>1200 мкА</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep6_1_1200mcA.get( HVV_AdminConstants.DEVICE8) + "</td>\n");

            writer.write( " </tr>\n</table>\n<br>\n<br>\n");
        }
        
        //6.2 Замеры порогов генерации и погасания
        //<table>
        bContinue &= ( theApp.GetCurrentStep() >= 102);
        if( bContinue) {
            writer.write( "<table>\n");
            writer.write( " <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n");

            if( theApp.IsStepMapContainsKey( "102")) {
                TechProcessStepInfo info = theApp.GetStepInfo( "102");

                if( info.GetStartReportTitle() != null) {
                    writer.write( " <tr height=\"30\">\n");
                    writer.write( "  <td>" + Gen_NiceDate( info.GetStartDate()) + "</td>\n");
                    writer.write( "  <td>" + Gen_NiceTime( info.GetStartDate()) + "</td>\n");
                    writer.write( "  <td>" + info.GetStartReportTitle() + "</td>\n");
                    writer.write( "  <td></td>\n");
                    writer.write( "  <td></td>\n");
                    writer.write( "  <td></td>\n");
                    writer.write( " </tr>\n");
                }
                else
                    logger.warn( "Нет стартового заголовка по этапу 102");
            }
            else {
                logger.warn( "Нет информации по этапу 102");
            }
            
            writer.write( "</table>\n");
            

            //TABLE WITH DATA
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"220\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"150\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">1</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">2</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">3</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">4</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">5</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">6</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">7</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">8</th>\n");

            //LASING THRESHOLD
            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>Порог генерации</td>\n");

            for( Object lst1 : lst) {
                Double dblGen   = ( Double) theApp.m_mapStep6_2_LasThreshold.get( lst1);
                Double dblExtAn = ( Double) theApp.m_mapStep6_2_ExtAn.get( lst1);

                if( dblGen != null) {
                    if( dblGen != 0.)
                        writer.write( "  <td align=\"center\">" + String.format( "%.2f", dblGen)+ "</td>\n");
                    else if( dblExtAn != 0.)
                        writer.write( "  <td align=\"center\">&lt; I<sub>погас.</sub></td>\n");
                    else
                        writer.write( "  <td align=\"center\">---</td>\n");
                }
                else {
                    writer.write( "  <td align=\"center\">---</td>\n");
                }
            }

            //LASING EXTINCTION ANODE
            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>Порог погасания анодов</td>\n");

            for( Object lst1 : lst) {
                Double dblExtAn = ( Double) theApp.m_mapStep6_2_ExtAn.get( lst1);

                if( dblExtAn != null) {
                    if( dblExtAn != 0.)
                        writer.write( "  <td align=\"center\">" + String.format( "%.2f", dblExtAn)+ "</td>\n");
                    else
                        writer.write( "  <td align=\"center\">---</td>\n");
                }
                else 
                    writer.write( "  <td align=\"center\">---</td>\n");
            }


            //LASING EXTINCTION TUBULATION
            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>Порог погасания штенгелей</td>\n");

            for( Object lst1 : lst) {
                Double dblExtTu = ( Double) theApp.m_mapStep6_2_ExtTu.get( lst1);

                if( dblExtTu != null) {
                    if( dblExtTu != 0.)
                        writer.write( "  <td align=\"center\">" + String.format( "%.2f", dblExtTu)+ "</td>\n");
                    else
                        writer.write( "  <td align=\"center\">---</td>\n");
                }
                else
                    writer.write( "  <td align=\"center\">---</td>\n");
            }

            writer.write( " </tr>\n</table>\n<br>\n<br>\n");
        }
        
        
        bContinue &= ( theApp.GetCurrentStep() > 103);
        if( bContinue) {
            writer.write( "<P style=\"page-break-before: always\">\n");
            writer.write( "<h3>Промежуточные комментарии</h3>\n");
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"200\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"800\" align=\"left\"></th>\n");
            writer.write( " </tr>\n <tr height=\"30\">\n");

            writer.write( "  <td>" + Gen_NiceDate(
                                        ( ( TechProcessStepInfo) theApp.GetStepInfo( "103")).GetStartDate())  + "</td>\n");
            writer.write( "  <td>" + Gen_NiceTime(
                                        ( ( TechProcessStepInfo) theApp.GetStepInfo("103")).GetStartDate())  + "</td>\n");

            writer.write( "  <td><b>Резонатор</b></td>\n");
            writer.write( "  <td><b>Комментарий</b></td>\n </tr>\n</table>\n<br>\n<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"140\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"160\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"800\" align=\"left\"></th>\n");

            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE1
            boolean bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE1);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 1</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            String strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE1);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE2
            bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE2);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 2</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE2);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE3            
            bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE3);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 3</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE3);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE4
            bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE4);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 4</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE4);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE5
            bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE5);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 5</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE5);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE6
            bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE6);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 6</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE6);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE7
            bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE7);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 7</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE7);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE8
            bCont63 = ( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE8);
            if( bCont63 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8) == true) {
                //прибор был установлен
                if( bCont63)
                    //прибор был установлен и прошёл в п.6.3
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл в п.6.3
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            
            writer.write( "  <td>Прибор 8</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE8) + "</td>\n");
            strComment = ( String) theApp.m_mapStep6_3_Comments.get( HVV_AdminConstants.DEVICE8);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            writer.write( " </tr>\n</table>\n");
        }
        
        //РАССМОТРИМ ОСОБЫЙ СЛУЧАЙ = ВСЕ ПРИБОРЫ СОШЛИ
        m_bSpecialCase63_AllWentOff = false;
        if( theApp.GetCurrentStep() >= 104) {
            m_bSpecialCase63_AllWentOff = true;
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE1);
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE2);
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE3);
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE4);
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE5);
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE6);
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE7);
            if( m_bSpecialCase63_AllWentOff)
                m_bSpecialCase63_AllWentOff = !( boolean) theApp.m_mapStep6_3_Continue.get( HVV_AdminConstants.DEVICE8);

            if( m_bSpecialCase63_AllWentOff) {
                //ТУТ НАДО СГЕНЕРИТЬ SPECIAL END ОТЧЁТА
                //writer.write( "\n<br>\n(TODO) ТУТ НАДО СГЕНЕРИТЬ SPECIAL END ОТЧЁТА\n<br>\n");
                return;
            }
        }
        
        if( theApp.GetCurrentStep() >= 104) {
            writer.write( "<br>\n");
            writer.write( "<table>\n");
            writer.write( " <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"><u>Завершение этапа</u></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\">Дата</th>\n");
            writer.write( "  <th width=\"100\" align=\"left\">Время</th>\n");
            writer.write( "  <th width=\"500\" align=\"left\">Операция</th>\n");
            writer.write( "  <th width=\"120\" align=\"left\">P<sub>5</sub>, Па</th>\n");
            writer.write( "  <th width=\"120\" align=\"left\">P<sub>6</sub>, Па</th>\n");
            writer.write( "  <th width=\"120\" align=\"left\">P<sub>7</sub>, Па</th>\n");
            writer.write( " </tr>\n");
                
            Gen_TableLine( writer, "104");
        }
        
        writer.write( "</table>\n<br>\n\n<!-- ** STEP7 ** -->\n");
        
    }
    
    public void Gen_Ch07( FileWriter writer) throws IOException {
        writer.write( "<P style=\"page-break-before: always\">\n<H3>7. Тренировка катода</H3>\n");
        
        //1ый ЦИКЛ
        if( theApp.GetCurrentStep() > 121) {
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"><u>1-ый цикл</u></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "121");
        }
        
        if( theApp.GetCurrentStep() > 122) {
            Gen_TableLine( writer, "122");
        }
        
        if( theApp.GetCurrentStep() > 123) {
            Gen_TableLine( writer, "123");
        }
        
        if( theApp.GetCurrentStep() > 124) {
            Gen_TableLine( writer, "124");
        }
        
        //2ой ЦИКЛ 
        if( theApp.GetCurrentStep() > 125) {
            writer.write( " <tr height=\"10\">\n </tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>2-ой цикл</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "125");
        }
        
        if( theApp.GetCurrentStep() > 126) {
            Gen_TableLine( writer, "126");
        }
        
        if( theApp.GetCurrentStep() > 127) {
            Gen_TableLine( writer, "127");
        }
        
        if( theApp.GetCurrentStep() > 128) {
            Gen_TableLine( writer, "128");
        }
        
        
        
        //3ий ЦИКЛ 
        if( theApp.GetCurrentStep() > 129) {
            writer.write( " <tr height=\"10\">\n </tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>3-ий цикл</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "129");
        }
        
        if( theApp.GetCurrentStep() > 130) {
            Gen_TableLine( writer, "130");
        }
        
        if( theApp.GetCurrentStep() > 131) {
            Gen_TableLine( writer, "131");
        }
        
        if( theApp.GetCurrentStep() > 132) {
            Gen_TableLine( writer, "132");
        }
        
        
        //ЗАВЕРШЕНИЕ ЭТАПА
        if( theApp.GetCurrentStep() > 133) {
            writer.write( " <tr height=\"10\">\n </tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>Завершение этапа</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "133");
        }
        
        writer.write( "</table>\n\n<br>\n<!-- ** STEP8 ** -->\n");
    }
    
    public void Gen_Ch08( FileWriter writer) throws IOException {
        writer.write( "<P style=\"page-break-before: always\">\n<H3>8. Обезгаживание рабочих геттеров</H3>\n");
        
        //<table>
        Gen_TableHeader( writer);
        
        //дата-время начала дегазации
        if( theApp.IsStepMapContainsKey( "141")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "141");
        
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td>" + Gen_NiceDate( info.GetStartDate()) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceTime( info.GetStartDate()) + "</td>\n");
            writer.write( "  <td>" + info.GetStartReportTitle() + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP5( info.GetStartP5(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7( info.GetStartP6(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7( info.GetStartP7(), true) + "</td>\n");
            writer.write( " </tr>\n");
        }
        writer.write( "</table>\n<br>\n");
        
        //информация по приборам
        for( int i=0; i<4; i++) {
            
            if( i == 2)
                writer.write( "\n<P style=\"page-break-before: always\">\n");
            
            
            int nDev1 = i;
            GettersActivationProgram stepDev1 = null;
            if( theApp.m_mapDegassing.containsKey( nDev1))
                stepDev1 = ( GettersActivationProgram) theApp.m_mapDegassing.get( nDev1);
            
            int nDev2 = 4 + i;
            GettersActivationProgram stepDev2 = null;
            if( theApp.m_mapDegassing.containsKey( nDev2))
                stepDev2 = ( GettersActivationProgram) theApp.m_mapDegassing.get( nDev2);
            
            
            writer.write( "<table border=\"0\" cellspacing=\"1\">\n");
            
            //ЗАГОЛОВОК. 1 ЛИНИЯ
            writer.write( " <tr>\n");
            
            //заголовок левой колонки
            writer.write( "  <th width=\"394\">РМ" + ( i+1));
            if( stepDev1 != null) {
                if( stepDev1.GetDtStart() != null) {
                    if( stepDev1.GetDtFinish() != null) {
                    
                        writer.write( "   " + Gen_NiceTime( stepDev1.GetDtStart()) + " - " +
                                    Gen_NiceTime( stepDev1.GetDtFinish()) + "</th>\n");
                    }
                    else {
                        writer.write( "   (обезгаживание не проводилось)");
                    }
                }
                else {
                    writer.write( "   (обезгаживание не проводилось)");
                }
            }
            else
                writer.write( "   (обезгаживание не проводилось)");
            
            writer.write( "</th>\n");
            
            
            
            //разделитель колонок
            writer.write( "  <th width=\"180\"></th>\n");
            
            
            
            //заголовок правой колонки
            writer.write( "  <th width=\"394\">РМ" + ( i+5));
            if( stepDev2 != null) {
                if( stepDev2.GetDtStart() != null) {
                    if( stepDev2.GetDtFinish() != null) {
                        writer.write( "   " + Gen_NiceTime( stepDev2.GetDtStart()) + " - " +
                                    Gen_NiceTime( stepDev2.GetDtFinish()) + "</th>\n");
                    }
                    else {
                        writer.write( "   (обезгаживание не проводилось)");
                    }
                }
                else {
                    writer.write( "   (обезгаживание не проводилось)");
                }
            }
            else
                writer.write( "   (обезгаживание не проводилось)");
            
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            
            
            
            //ЗАГОЛОВОК. 2 ЛИНИЯ
            //левая колонка
            if( stepDev1 != null) {
                writer.write( "  <td align=\"center\">");
                if( stepDev1.GetGetter() == HVV_AdminConstants.GETTER1)
                    writer.write( "(геттер 1");
                else
                    writer.write( "(геттер 2");
                
                if( stepDev1.GetInductor() == HVV_AdminConstants.INDUCTOR_TYPE1)
                    writer.write( ", индуктор 1)</td>\n");
                else
                    writer.write( ", индуктор 2)</td>\n");
            }
            else
                writer.write( "  <td align=\"center\"> - </td>\n");
            
            //разделитель
            writer.write( "  <td></td>\n");
            
            //правая колонка
            if( stepDev2 != null) {
                writer.write( "  <td align=\"center\">");
                if( stepDev2.GetGetter() == HVV_AdminConstants.GETTER1)
                    writer.write( "(геттер 1");
                else
                    writer.write( "(геттер 2");
                
                if( stepDev2.GetInductor() == HVV_AdminConstants.INDUCTOR_TYPE1)
                    writer.write( ", индуктор 1)</td>\n");
                else
                    writer.write( ", индуктор 2)</td>\n");
            }
            else
                writer.write( "  <td align=\"center\"> - </td>\n");
            
            writer.write( " </tr>\n");
            writer.write( "</table>\n");
            
            
            
            //ЗАГОЛОВОК. 3 ЛИНИЯ - названия столбцов
            //левая колонка
            writer.write( "<table border=\"0\" cellspacing=\"1\">\n");
            writer.write( " <tr>\n");
            writer.write( "  <th width=\"50\">t</th>\n");
            writer.write( "  <th width=\"50\">W</th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 start</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 max</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 last</sub></th>\n");

            //разделитель
            writer.write( "  <th width=\"200\"></th>\n");

            //правая колонка
            writer.write( "  <th width=\"50\">t</th>\n");
            writer.write( "  <th width=\"50\">W</th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 start</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 max</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 last</sub></th>\n");

            
            writer.write( " </tr>\n");      //закрываем TR линии подписей столбцов
            writer.write( " <tr height=\"5\"></tr>\n"); //отступ
            
            
            //список данных левого столбца
            LinkedList lstL = null;
            if( stepDev1 != null)
                lstL = stepDev1.GetListSteps();
            
            Iterator itL = null;
            if( lstL != null)
                itL = lstL.iterator();
            
            
            //список данных правого столбца
            LinkedList lstR = null;
            if( stepDev2 != null)
                lstR = stepDev2.GetListSteps();
            
            Iterator itR = null;
            if( lstR != null)
                itR = lstR.iterator();
            
            
            //и прогоняем все данные
            for( int j=0; j<17; j++) {
                
                GettersActivationProgramStep step = null;
                
                if( ( j % 2) == 0)
                    writer.write( " <tr align=\"center\" height=\"25\" bgcolor=\"#E0E0E0\">\n");
                else
                    writer.write( " <tr align=\"center\" height=\"25\">\n");
                
                //левая колонка
                if( itL != null && itL.hasNext()) {
                    step = ( GettersActivationProgramStep) itL.next();
                    
                    String strBoldingOpen = "";
                    String strBoldingClose = "";
                    int nExp = ( int) Math.log10( step.GetP5_max());
                    if( nExp < 0) nExp--;
                    //logger.debug( "REPORT.STEP8.P5_MAX_EXP=" + nExp);
                    if( nExp == -5) {
                        strBoldingOpen = "<b><u>";
                        strBoldingClose = "</u></b>";
                    }
                        
        
                    writer.write( "  <td>" + strBoldingOpen + step.GetDuration() + strBoldingClose + "'</td>");
                    writer.write( " <td>" + strBoldingOpen + step.GetPower() + strBoldingClose + "%</td>");
                    
                    
                    
                    //P5_START
                    double dblP5 = step.GetP5_start();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5, true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_MAX
                    dblP5 = step.GetP5_max();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,   true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_STOP
                    dblP5 = step.GetP5_last();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,  true) + strBoldingClose + "</td>\n");
                    else
                        writer.write( " <td>-</td>");
                    
                }
                else {
                    writer.write( "  <td></td> <td></td> <td></td> <td></td> <td></td>\n");
                }
                step = null;
                
                //разделитель левой и правой колонки
                writer.write( "  <td></td>\n");
        
                
                //правая колонка
                if( itR != null && itR.hasNext()) {
                    step = ( GettersActivationProgramStep) itR.next();
                    
                    String strBoldingOpen = "";
                    String strBoldingClose = "";
                    
                    int nExp = ( int) Math.log10( step.GetP5_max());
                    if( nExp < 0) nExp--;
                    
                    if( nExp == -5) {
                        strBoldingOpen = "<b><u>";
                        strBoldingClose = "</u></b>";
                    }
                    
                    writer.write( "  <td>" + strBoldingOpen + step.GetDuration() + strBoldingClose + "'</td>");
                    writer.write( " <td>" + strBoldingOpen + step.GetPower() + strBoldingClose + "%</td>");
                    
                    
                    //P5_START
                    double dblP5 = step.GetP5_start();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5, true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_MAX
                    dblP5 = step.GetP5_max();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,   true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_STOP
                    dblP5 = step.GetP5_last();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,  true) + strBoldingClose + "</td>\n");
                    else
                        writer.write( " <td>-</td>");
                }
                else {
                    writer.write( "  <td></td> <td></td> <td></td> <td></td> <td></td>\n");
                }
                
                writer.write( " </tr>\n");
            }
            writer.write( "</table>\n\n");
            
        }
        
        if( theApp.GetCurrentStep() > 141) {
            //дата-время завершения дегазации
            Gen_TableHeader( writer);
        
            TechProcessStepInfo info = theApp.GetStepInfo( "141");
        
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td>" + Gen_NiceDate( info.GetStopDate()) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceTime( info.GetStopDate()) + "</td>\n");
            writer.write( "  <td>" + info.GetStopReportTitle() + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP5( info.GetStopP5(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7(info.GetStopP6(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7(info.GetStopP7(), true) + "</td>\n");
            writer.write( " </tr>\n");
        }
        
        //8.2 Открытие геттера
        if( theApp.GetCurrentStep() > 142) {
            Gen_TableLine( writer, "142");
        }
        writer.write( "</table>\n\n<br>\n<!-- ** STEP9 ** -->\n");
    }
    
    public void Gen_Ch09( FileWriter writer) throws IOException {
        writer.write( "<P style=\"page-break-before: always\">\n<H3>9. Тренировка в тренировочной смеси</H3>\n");
        
        
        //1ый ЦИКЛ
        if( theApp.GetCurrentStep() > 161) {
            writer.write( "<table>\n");
        
            writer.write( " <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"><u>1-ый цикл</u></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n");

            writer.write( " <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\">Дата</th>\n");
            writer.write( "  <th width=\"100\" align=\"left\">Время</th>\n");
            writer.write( "  <th width=\"500\" align=\"left\">Операция</th>\n");
            writer.write( "  <th width=\"120\" align=\"left\">P<sub>5</sub>, Па</th>\n");
            writer.write( "  <th width=\"120\" align=\"left\">P<sub>6</sub>, Па</th>\n");
            writer.write( "  <th width=\"120\" align=\"left\">P<sub>7</sub>, Па</th>\n");
            writer.write( " </tr>\n");
        
            Gen_TableLine( writer, "161");
        }
        
        if( theApp.GetCurrentStep() > 162) {
            Gen_TableLine( writer, "162");
        }
        
        if( theApp.GetCurrentStep() > 163) {
            Gen_TableLine( writer, "163");
        }
        
        if( theApp.GetCurrentStep() > 164) {
            Gen_TableLine( writer, "164");
        }
        
        
        //2ой ЦИКЛ 
        if( theApp.GetCurrentStep() > 165) {
            writer.write( " <tr height=\"10\">\n </tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>2-ой цикл</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "165");
        }
        
        if( theApp.GetCurrentStep() > 166) {
            Gen_TableLine( writer, "166");
        }
        
        if( theApp.GetCurrentStep() > 167) {
            Gen_TableLine( writer, "167");
        }
        
        if( theApp.GetCurrentStep() > 168) {
            Gen_TableLine( writer, "168");
        }
        
        
        //ЗАВЕРШЕНИЕ ЭТАПА
        if( theApp.GetCurrentStep() > 169) {
            writer.write( " <tr height=\"10\">\n</tr>\n");
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td><b><u>Завершение этапа</u></b></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td></td>\n");
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            writer.write( "  <td><b>Дата</b></td>\n");
            writer.write( "  <td><b>Время</b></td>\n");
            writer.write( "  <td><b>Операция</b></td>\n");
            writer.write( "  <td><b>P<sub>5</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>6</sub>, Па</b></td>\n");
            writer.write( "  <td><b>P<sub>7</sub>, Па</b></td>\n");
            writer.write( " </tr>\n");
              
            Gen_TableLine( writer, "169");
        }
        
        writer.write( "</table>\n\n<br>\n<!-- ** STEP10 ** -->\n");
        
        
    }
    
    public void Gen_Ch10( FileWriter writer) throws IOException {
        writer.write( "<P style=\"page-break-before: always\">\n<H3>10. Активация рабочих геттеров</H3>\n");
        
        //<table>
        Gen_TableHeader( writer);
        
        
        //дата-время начала дегазации
        if( theApp.IsStepMapContainsKey( "181")) {
            TechProcessStepInfo info = theApp.GetStepInfo( "181");
        
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td>" + Gen_NiceDate( info.GetStartDate()) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceTime( info.GetStartDate()) + "</td>\n");
            writer.write( "  <td>" + info.GetStartReportTitle() + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP5( info.GetStartP5(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7(info.GetStartP6(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7(info.GetStartP7(), true) + "</td>\n");
            writer.write( " </tr>\n");
        }
        writer.write( "</table>\n<br>\n");
        
        //информация по приборам
        for( int i=0; i<4; i++) {
            int nDev1 = i;
            
            if( i > 0)
                writer.write( "\n<P style=\"page-break-before: always\">\n");
            
            GettersActivationProgram stepDev1 = null;
            if( theApp.m_mapActivation.containsKey( nDev1))
                stepDev1 = ( GettersActivationProgram) theApp.m_mapActivation.get( nDev1);
            
            int nDev2 = 4 + i;
            GettersActivationProgram stepDev2 = null;
            if( theApp.m_mapActivation.containsKey( nDev2))
                stepDev2 = ( GettersActivationProgram) theApp.m_mapActivation.get( nDev2);
            
            
            writer.write( "<table border=\"0\" cellspacing=\"1\">\n");
            
            //ЗАГОЛОВОК. 1 ЛИНИЯ
            writer.write( " <tr>\n");
            
            //заголовок левой колонки
            writer.write( "  <th width=\"394\">РМ" + ( i+1));
            if( stepDev1 != null) {
                if( stepDev1.GetDtStart() != null) {
                    if( stepDev1.GetDtFinish() != null) {
                    
                        writer.write( "   " + Gen_NiceTime( stepDev1.GetDtStart()) + " - " +
                                    Gen_NiceTime( stepDev1.GetDtFinish()) + "</th>\n");
                    }
                    else {
                        writer.write( "   (активация не проводилась)");
                    }
                }
                else {
                    writer.write( "   (активация не проводилась)");
                }
            }
            else
                writer.write( "   (активация не проводилась)");
            
            writer.write( "</th>\n");
            
            
            
            //разделитель колонок
            writer.write( "  <th width=\"180\"></th>\n");
            
            
            
            //заголовок правой колонки
            writer.write( "  <th width=\"394\">РМ" + ( i+5));
            if( stepDev2 != null) {
                if( stepDev2.GetDtStart() != null) {
                    if( stepDev2.GetDtFinish() != null) {
                        writer.write( "   " + Gen_NiceTime( stepDev2.GetDtStart()) + " - " +
                                    Gen_NiceTime( stepDev2.GetDtFinish()) + "</th>\n");
                    }
                    else {
                        writer.write( "   (активация не проводилась)");
                    }
                }
                else {
                    writer.write( "   (активация не проводилась)");
                }
            }
            else
                writer.write( "   (активация не проводилась)");
            
            writer.write( "  </th>\n");
            
                
            writer.write( " </tr>\n");
            writer.write( " <tr>\n");
            
            
            
            //ЗАГОЛОВОК. 2 ЛИНИЯ
            //левая колонка
            if( stepDev1 != null) {
                writer.write( "  <td align=\"center\">");
                if( stepDev1.GetGetter() == HVV_AdminConstants.GETTER1)
                    writer.write( "(геттер 1");
                else
                    writer.write( "(геттер 2");
                
                if( stepDev1.GetInductor() == HVV_AdminConstants.INDUCTOR_TYPE1)
                    writer.write( ", индуктор 1)</td>\n");
                else
                    writer.write( ", индуктор 2)</td>\n");
            }
            else
                writer.write( "  <td align=\"center\"> - </td>\n");
            
            //разделитель
            writer.write( "  <td></td>\n");
            
            //правая колонка
            if( stepDev2 != null) {
                writer.write( "  <td align=\"center\">");
                if( stepDev2.GetGetter() == HVV_AdminConstants.GETTER1)
                    writer.write( "(геттер 1");
                else
                    writer.write( "(геттер 2");
                
                if( stepDev2.GetInductor() == HVV_AdminConstants.INDUCTOR_TYPE1)
                    writer.write( ", индуктор 1)</td>\n");
                else
                    writer.write( ", индуктор 2)</td>\n");
            }
            else
                writer.write( "  <td align=\"center\"> - </td>\n");
            
            writer.write( " </tr>\n");
            writer.write( "</table>\n");
            
            
            
            //ЗАГОЛОВОК. 3 ЛИНИЯ - названия столбцов
            //левая колонка
            writer.write( "<table border=\"0\" cellspacing=\"1\">\n");
            writer.write( " <tr>\n");
            writer.write( "  <th width=\"50\">t</th>\n");
            writer.write( "  <th width=\"50\">W</th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 start</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 max</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 last</sub></th>\n");

            //разделитель
            writer.write( "  <th width=\"200\"></th>\n");

            //правая колонка
            writer.write( "  <th width=\"50\">t</th>\n");
            writer.write( "  <th width=\"50\">W</th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 start</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 max</sub></th>\n");
            writer.write( "  <th width=\"90\">P<sub>5 last</sub></th>\n");

            
            writer.write( " </tr>\n");      //закрываем TR линии подписей столбцов
            writer.write( " <tr height=\"5\"></tr>\n"); //отступ
            
            
            //список данных левого столбца
            LinkedList lstL = null; int nLListLen = 0;
            if( stepDev1 != null) {
                lstL = stepDev1.GetListSteps();
                nLListLen = lstL.size();
            }
            Iterator itL = null;
            if( lstL != null)
                itL = lstL.iterator();
            
            
            //список данных правого столбца
            LinkedList lstR = null; int nRListLen = 0;
            if( stepDev2 != null) {
                lstR = stepDev2.GetListSteps();
                nRListLen = lstR.size();
            }
            Iterator itR = null;
            if( lstR != null)
                itR = lstR.iterator();
            
            int nCells = max( 14, nLListLen);
            nCells = max( nCells, nRListLen);
            
            //и прогоняем все данные
            for( int j=0; j<nCells; j++) {
                
                GettersActivationProgramStep step = null;
                
                if( ( j % 2) == 0)
                    writer.write( " <tr align=\"center\" height=\"25\" bgcolor=\"#E0E0E0\">\n");
                else
                    writer.write( " <tr align=\"center\" height=\"25\">\n");
                
                //левая колонка
                if( itL != null && itL.hasNext()) {
                    step = ( GettersActivationProgramStep) itL.next();
                       
                    String strBoldingOpen = "";
                    String strBoldingClose = "";
                    
                    int nExp = ( int) Math.log10( step.GetP5_max());
                    if( nExp < 0) nExp--;
                    
                    if( nExp == -5) {
                        strBoldingOpen = "<b><u>";
                        strBoldingClose = "</u></b>";
                    }
                    
                    writer.write( "  <td>" + strBoldingOpen + step.GetDuration() + strBoldingClose + "'</td>");
                    writer.write( " <td>" + strBoldingOpen + step.GetPower() + strBoldingClose + "%</td>");
                    
                    //P5_START
                    double dblP5 = step.GetP5_start();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5, true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_MAX
                    dblP5 = step.GetP5_max();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,   true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_STOP
                    dblP5 = step.GetP5_last();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,  true) + strBoldingClose + "</td>\n");
                    else
                        writer.write( " <td>-</td>");
                }
                else {
                    writer.write( "  <td></td> <td></td> <td></td> <td></td> <td></td>\n");
                }
                step = null;
                
                //разделитель левой и правой колонки
                writer.write( "  <td></td>\n");
        
                
                //правая колонка
                if( itR != null && itR.hasNext()) {
                    step = ( GettersActivationProgramStep) itR.next();
                    
                    String strBoldingOpen = "";
                    String strBoldingClose = "";
                    
                    int nExp = ( int) Math.log10( step.GetP5_max());
                    if( nExp < 0) nExp--;
                    
                    if( nExp == -5) {
                        strBoldingOpen = "<b><u>";
                        strBoldingClose = "</u></b>";
                    }
                    
                    writer.write( "  <td>" + strBoldingOpen + step.GetDuration() + strBoldingClose + "'</td>");
                    writer.write( " <td>" + strBoldingOpen + step.GetPower() + strBoldingClose + "%</td>");
                    
                    //P5_START
                    double dblP5 = step.GetP5_start();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5, true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_MAX
                    dblP5 = step.GetP5_max();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,   true) + strBoldingClose + "</td>");
                    else
                        writer.write( " <td>-</td>");
                    
                    
                    //P5_STOP
                    dblP5 = step.GetP5_last();
                    if( dblP5 != 0)
                        writer.write( " <td>" + strBoldingOpen + Gen_NiceDoubleP5( dblP5,  true) + strBoldingClose + "</td>\n");
                    else
                        writer.write( " <td>-</td>");
                }
                else {
                    writer.write( "  <td></td> <td></td> <td></td> <td></td> <td></td>\n");
                }
                
                writer.write( " </tr>\n");
            }
            
            writer.write( "</table>\n\n");
        }
        
        if( theApp.GetCurrentStep() > 181) {
            //дата-время завершения дегазации
            Gen_TableHeader( writer);
        
            TechProcessStepInfo info = theApp.GetStepInfo( "181");
        
            writer.write( " <tr height=\"30\">\n");
            writer.write( "  <td>" + Gen_NiceDate( info.GetStopDate()) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceTime( info.GetStopDate()) + "</td>\n");
            writer.write( "  <td>" + info.GetStopReportTitle() + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP5( info.GetStopP5(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7( info.GetStopP6(), true) + "</td>\n");
            writer.write( "  <td>" + Gen_NiceDoubleP6P7( info.GetStopP7(), true) + "</td>\n");
            writer.write( " </tr>\n");
        }
        
        //10.2 Открытие геттера
        if( theApp.GetCurrentStep() > 182) {
            Gen_TableLine( writer, "182");
        }
        writer.write( "</table>\n\n<br>\n<!-- ** STEP11 ** -->\n");
    }
    
    public void Gen_Ch11( FileWriter writer) throws IOException {
        boolean bContinue = true;
        
        LinkedList lst = new LinkedList();
        lst.addLast( HVV_AdminConstants.DEVICE1); lst.addLast( HVV_AdminConstants.DEVICE2);
        lst.addLast( HVV_AdminConstants.DEVICE3); lst.addLast( HVV_AdminConstants.DEVICE4);
        lst.addLast( HVV_AdminConstants.DEVICE5); lst.addLast( HVV_AdminConstants.DEVICE6);
        lst.addLast( HVV_AdminConstants.DEVICE7); lst.addLast( HVV_AdminConstants.DEVICE8);
        
        writer.write( "<P style=\"page-break-before: always\">\n<H3>11. Выходная оценка параметров резонаторов</H3>\n");
        
        //<table>
        Gen_TableHeader( writer);
        
        //11.1 Заполнение рабочей смесью
        if( theApp.GetCurrentStep() >= 201) {
            Gen_TableLine( writer, "201");
        }
        
        //11.2 Выдержка
        if( theApp.GetCurrentStep() >= 202) {
            Gen_TableLine( writer, "202");    
        }
        
        //11.3 Снятие вольт-амперной-характеристики анодов
        bContinue &= ( theApp.GetCurrentStep() > 203);
        if( bContinue) {
            Gen_TableLine( writer, "203");
        
            writer.write( "</table>\n\n<br>\n");

            //TABLE WITH DATA
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"220\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"150\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">1</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">2</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">3</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">4</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">5</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">6</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">7</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">8</th>\n");

            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>1000 мкА</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1000mcA.get( HVV_AdminConstants.DEVICE8) + "</td>\n");

            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>1100 мкА</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1100mcA.get( HVV_AdminConstants.DEVICE8) + "</td>\n");

            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>1200 мкА</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            writer.write( "  <td align=\"center\">" + theApp.m_mapStep11_3_1200mcA.get( HVV_AdminConstants.DEVICE8) + "</td>\n");

            writer.write( " </tr>\n</table>\n<br>\n<br>\n");
        }
        
        //11.4 Замеры порогов генерации и погасания
        //<table>
        bContinue &= ( theApp.GetCurrentStep() > 204);
        if( bContinue) {
            writer.write( "<table>\n");
            writer.write( " <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"500\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( " </tr>\n");

            bContinue &= ( theApp.GetCurrentStep() >= 204);
            if( bContinue) {
                
                if( theApp.IsStepMapContainsKey( "204")) {
                    TechProcessStepInfo info = theApp.GetStepInfo( "204");

                    if( info.GetStartReportTitle() != null) {
                        writer.write( " <tr height=\"30\">\n");
                        writer.write( "  <td>" + Gen_NiceDate( info.GetStartDate()) + "</td>\n");
                        writer.write( "  <td>" + Gen_NiceTime( info.GetStartDate()) + "</td>\n");
                        writer.write( "  <td>" + info.GetStartReportTitle() + "</td>\n");
                        writer.write( "  <td></td>\n");
                        writer.write( "  <td></td>\n");
                        writer.write( "  <td></td>\n");
                        writer.write( " </tr>\n");
                    }
                    else
                        logger.warn( "Нет стартового заголовка по этапу 204");
                }
                else {
                    logger.warn( "Нет информации по этапу 204");
                }
            }

            writer.write( "</table>\n");
            
            
            //TABLE WITH DATA
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"220\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"150\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">1</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">2</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">3</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">4</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">5</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">6</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">7</th>\n");
            writer.write( "  <th width=\"80\" align=\"center\">8</th>\n");

            //LASING THRESHOLD
            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>Порог генерации</td>\n");

            for( Object lst1 : lst) {
                Double dblGen   = ( Double) theApp.m_mapStep11_4_LasThreshold.get( lst1);
                Double dblExtAn = ( Double) theApp.m_mapStep11_4_ExtAn.get( lst1);

                if( dblGen != null) {
                    if( dblGen != 0.)
                        writer.write( "  <td align=\"center\">" + String.format( "%.2f", dblGen)+ "</td>\n");
                    else if( dblExtAn != 0.)
                        writer.write( "  <td align=\"center\">&lt; I<sub>погас.</sub></td>\n");
                    else
                        writer.write( "  <td align=\"center\">---</td>\n");
                }
                else {
                    writer.write( "  <td align=\"center\">---</td>\n");
                }
            }

            //LASING EXTINCTION ANODE
            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>Порог погасания анодов</td>\n");

            for( Object lst1 : lst) {
                Double dblExtAn = ( Double) theApp.m_mapStep11_4_ExtAn.get( lst1);

                if( dblExtAn != null)
                    writer.write( "  <td align=\"center\">" + String.format( "%.3f", dblExtAn)+ "</td>\n");
                else
                    writer.write( "  <td align=\"center\">---</td>\n");
            }


            //LASING EXTINCTION TUBULATION
            writer.write( " </tr>\n <tr height=\"30\">\n");
            writer.write( "  <td></td>\n");
            writer.write( "  <td>Порог погасания штенгелей</td>\n");

            for( Object lst1 : lst) {
                Double dblExtTu = ( Double) theApp.m_mapStep11_4_ExtTu.get( lst1);

                if( dblExtTu != null)
                    writer.write( "  <td align=\"center\">" + String.format( "%.3f", dblExtTu)+ "</td>\n");
                else
                    writer.write( "  <td align=\"center\">---</td>\n");
            }

            writer.write( " </tr>\n</table>\n<br>\n<br>\n");
        }
        
        
        
        
        //11.5 Оценка параметров приборов
        if( theApp.GetCurrentStep() > 205) {
            //Gen_TableLine( writer, "205"); <-- //не надо
        
        
            boolean bHaveBad  = false;
            boolean bHaveGood = false;
            for( int i=0; i<8; i++) {
                boolean bDeviceGood = ( boolean) theApp.m_mapStep11_5_Continue.get( i);
                if( bDeviceGood)
                    bHaveGood = true;
                else
                    bHaveBad = true;
            }

            if( bHaveGood)
                if( bHaveBad) m_nDecisionOnStep11_5 =  1;       //У нас есть хорошие, у нас есть плохие
                else          m_nDecisionOnStep11_5 =  0;       //У нас только хорошие
            else
                if( bHaveBad) m_nDecisionOnStep11_5 =  2;       //У нас только плохие
                else          m_nDecisionOnStep11_5 = -1;       //У нас нет ни хороших, ни плохих (приборы украли в процессе?)

            logger.debug( "11.5 m_nDecisionOnStep11_5 =" + m_nDecisionOnStep11_5);

                    
            writer.write( "<P style=\"page-break-before: always\">\n");
            writer.write( "<h3>Промежуточные комментарии</h3>\n");
            writer.write( "<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"100\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"200\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"800\" align=\"left\"></th>\n");
            writer.write( " </tr>\n <tr height=\"30\">\n");

            writer.write( "  <td>" + Gen_NiceDate(
                                        ( ( TechProcessStepInfo) theApp.GetStepInfo( "205")).GetStartDate())  + "</td>\n");
            writer.write( "  <td>" + Gen_NiceTime(
                                        ( ( TechProcessStepInfo) theApp.GetStepInfo( "205")).GetStartDate())  + "</td>\n");

            writer.write( "  <td><b>Резонатор</b></td>\n");
            writer.write( "  <td><b>Комментарий</b></td>\n </tr>\n</table>\n<br>\n<table>\n <tr>\n");
            writer.write( "  <th width=\"120\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"140\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"160\" align=\"left\"></th>\n");
            writer.write( "  <th width=\"800\" align=\"left\"></th>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE1
            boolean bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE1);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE1) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 1</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE1) + "</td>\n");
            String strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE1);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE2
            bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE2);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE2) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 2</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE2) + "</td>\n");
            strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE2);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE3
            bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE3);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE3) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 3</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE3) + "</td>\n");
            strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE3);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE4
            bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE4);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE4) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 4</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE4) + "</td>\n");
            strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE4);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE5
            bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE5);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE5) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 5</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE5) + "</td>\n");
            strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE5);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE6
            bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE6);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE6) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 6</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE6) + "</td>\n");
            strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE6);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE7
            bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE7);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE7) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 7</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE7) + "</td>\n");
            strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE7);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            //===== ===== ===== ===== ===== ===== ===== ===== ===== =====
            //DEVICE8
            bCont115 = ( boolean) theApp.m_mapStep11_5_Continue.get( HVV_AdminConstants.DEVICE8);
            if( bCont115 == true)
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#60F060\">\n");
            else
                writer.write( " </tr>\n <tr height=\"40\"  bgcolor=\"#F06060\">\n");
            if( ( boolean) theApp.m_mapDevicePresence.get( HVV_AdminConstants.DEVICE8) == true) {
                //прибор был установлен
                if( bCont115)
                    //прибор был установлен и прошёл
                    writer.write( "  <td></td>\n");
                else
                    //прибор был установлен и НЕпрошёл (неважно в каком пункте)
                    writer.write( "  <td>СНЯТ</td>\n");
            }
            else {
                //прибор не был установлен
                writer.write( "  <td>Н/У</td>\n");
            }
            writer.write( "  <td>Прибор 8</td>\n");
            writer.write( "  <td>" + theApp.m_mapSerials.get( HVV_AdminConstants.DEVICE8) + "</td>\n");
            strComment = ( String) theApp.m_mapStep11_5_Comments.get( HVV_AdminConstants.DEVICE8);
            if( strComment.isEmpty())
                writer.write( "  <td><i> - </i></td>\n");
            else
                writer.write( "  <td><i>" + strComment + "</i></td>\n");

            
            writer.write( " </tr>\n</table>\n");


            if( m_nDecisionOnStep11_5 != 2) { //т.е. у нас есть годные приборы которые надо герметизировать
                //11.6 Герметизация годных приборов
                if( theApp.GetCurrentStep() >= 206) {
                    //<table>
                    Gen_TableHeader( writer);
        
                    Gen_TableLine( writer, "206");
                }
            }
        }
        writer.write( "</table>\n<br>\n\n<!-- ** STEP12 ** -->\n");
    }
    
    public void Gen_Ch12( FileWriter writer) throws IOException {
        if( m_bSpecialCase63_AllWentOff == true || m_nDecisionOnStep11_5 != 0) {
            writer.write( "<P style=\"page-break-before: always\">\n<H3>12. Снятие сошедших приборов</H3>\n");

            //<table>
            Gen_TableHeader( writer);

            //12.1 Закрытие геттера
            if( theApp.GetCurrentStep() > 221)
                Gen_TableLine( writer, "221");

            //12.2 Напуск азота в приборы
            if( theApp.GetCurrentStep() > 222)
                Gen_TableLine( writer, "222");

            //12.3 Снятие непрошедших приборов
            if( theApp.GetCurrentStep() > 223)
                Gen_TableLine( writer, "223");

            writer.write( "</table>\n\n<br>\n");
        }
    }
    
    public void Gen_Ch13( FileWriter writer) throws IOException {
        writer.write( "<H3>13. Завершение технологического процесса</H3>\n");
        
        //<table>
        Gen_TableHeader( writer);
        
        if( m_nDecisionOnStep11_5 != 0) {
            //13.1 Bypass-откачка
            if( theApp.GetCurrentStep() > 241)
                Gen_TableLine( writer, "241");

            //13.2 Проверка герметичности
            if( theApp.GetCurrentStep() > 242)
                Gen_TableLine( writer, "242");

            //13.3 Основная откачка 
            if( theApp.GetCurrentStep() > 243)
                Gen_TableLine( writer, "243");
        }
        
        //13.4 Откачка смеси с геттера
        if( theApp.GetCurrentStep() > 244)
            Gen_TableLine( writer, "244");
        
        writer.write( "</table>\n");
    }
    
    public void Gen_Footer( FileWriter writer) throws IOException {
        writer.write( "</body>\n</html>");
    }
    
    public void Generate() {
        String strReportFileNameLoc = theApp.m_strAdminStartDtm + ".current.html";        
        String strSerial;
        boolean bPresent;
        if( theApp.m_bProcessingEnded) {
            strReportFileNameLoc = Gen_NiceDate( ( ( TechProcessStepInfo) theApp.GetStepInfo( "001")).GetStartDate()) + "--";
            
            for( int i=0; i<8; i++) {
                bPresent = ( boolean) theApp.m_mapDevicePresence.get( i);
                if( bPresent) {
                    strSerial = ( String) theApp.m_mapSerials.get( i);
                    if( strSerial != null)
                        strReportFileNameLoc += strSerial +"--";
                }
            }
            
            strReportFileNameLoc = strReportFileNameLoc.substring( 0, strReportFileNameLoc.length()-2);
            strReportFileNameLoc += ".html";
        }
        
        try {
            FileWriter writer = new FileWriter(  theApp.GetAMSRoot() + "/reports/" + strReportFileNameLoc);
            //FileOutputStream streamOutput = new FileOutputStream( theApp.GetAMSRoot() + "\\" + strReportFileNameLoc, true);
            
            Gen_Header( writer);
            
                                               Gen_Ch01( writer);
            if( theApp.GetCurrentStep() > 20)  Gen_Ch02( writer);
            if( theApp.GetCurrentStep() > 40)  Gen_Ch03( writer);
            if( theApp.GetCurrentStep() > 60)  Gen_Ch04( writer);
            if( theApp.GetCurrentStep() > 80)  Gen_Ch05( writer);
            if( theApp.GetCurrentStep() > 100) Gen_Ch06( writer);
            if( !m_bSpecialCase63_AllWentOff && theApp.GetCurrentStep() > 120) Gen_Ch07( writer);
            if( !m_bSpecialCase63_AllWentOff && theApp.GetCurrentStep() > 140) Gen_Ch08( writer);
            if( !m_bSpecialCase63_AllWentOff && theApp.GetCurrentStep() > 160) Gen_Ch09( writer);
            if( !m_bSpecialCase63_AllWentOff && theApp.GetCurrentStep() > 180) Gen_Ch10( writer);
            if( !m_bSpecialCase63_AllWentOff && theApp.GetCurrentStep() > 200) Gen_Ch11( writer);
            if( theApp.GetCurrentStep() > 220) Gen_Ch12( writer);
            if( theApp.GetCurrentStep() > 240) Gen_Ch13( writer);
            
            Gen_Footer( writer);
            
        
            writer.close();
            //streamOutput.close();
        } catch (IOException ex) {
            logger.error( "В процессе генерации отчёта произошла Exception", ex);
            //Logger.getLogger(ReportGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
