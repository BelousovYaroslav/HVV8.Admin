/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hvv_admin;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.apache.log4j.Logger;

/**
 *
 * @author yaroslav
 */
public class SoundPlayThread extends Thread {

    String m_strWavFile;
    static Logger logger = Logger.getLogger( SoundPlayThread.class);            
    
    private final int EXTERNAL_BUFFER_SIZE = 524288; // 128Kb 
    
    public boolean m_bPlayCompleted;
    public SoundPlayThread( String strWavFile) {
        m_strWavFile = strWavFile;
    }

    @Override
    public void run() {
        
        File soundFile = new File( m_strWavFile);
        if( !soundFile.exists()) { 
            logger.error( "Wave file not found: " + m_strWavFile);
            return;
        } 
 
        AudioInputStream audioInputStream = null;
        try { 
            audioInputStream = AudioSystem.getAudioInputStream( soundFile);
        } catch( UnsupportedAudioFileException e1) { 
            logger.error( "UnsupportedAudioFileException caught", e1);
            return;
        } catch( IOException e1) { 
            logger.error( "IOException caught", e1);
            return;
        } 
 
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline;
        DataLine.Info info = new DataLine.Info( SourceDataLine.class, format);
 
        try { 
            auline = ( SourceDataLine) AudioSystem.getLine( info);
            auline.open( format);
        } catch( LineUnavailableException e) { 
            logger.error( "LineUnavailableException caught", e);
            return;
        } catch( Exception e) { 
            logger.error( "Exception caught", e);
            return;
        } 
 
        auline.start();
        int nBytesRead = 0;
        byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
 
        try { 
            while (nBytesRead != -1) { 
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) 
                    auline.write(abData, 0, nBytesRead);
            } 
        } catch (IOException e) { 
            logger.error( "IOException caught", e);
        } finally { 
            auline.drain();
            auline.close();
        }
                
    }
}
