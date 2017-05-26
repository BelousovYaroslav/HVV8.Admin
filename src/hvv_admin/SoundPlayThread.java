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
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author yaroslav
 */
public class SoundPlayThread extends Thread implements LineListener {

    String m_strWavFile;
    //static Logger logger = Logger.getLogger( SoundPlayThread.class);            
    
    public boolean m_bPlayCompleted;
    public SoundPlayThread( String strWavFile) {
        m_strWavFile = strWavFile;
    }

    @Override
    public void run() {
        File audioFile = new File( m_strWavFile);
 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream( audioFile);
            AudioFormat format = audioStream.getFormat();
            
            DataLine.Info info = new DataLine.Info( Clip.class, format);
            
            Clip audioClip = (Clip) AudioSystem.getLine( info);
            
            audioClip.addLineListener( this);
            audioClip.open( audioStream);
            audioClip.start();
            
            m_bPlayCompleted = false;
            while( !m_bPlayCompleted) {
                // wait for the playback completes
                try {
                    Thread.sleep( 100);
                } catch ( InterruptedException ex) {
                    //logger.error( "InterruptedException caught!", ex);
                }
            }
             
            audioClip.close();
             
        } catch (UnsupportedAudioFileException ex) {
            //logger.error( "The specified audio file is not supported.", ex);
        } catch (LineUnavailableException ex) {
            //logger.error( "Audio line for playing back is unavailable.", ex);
        } catch (IOException ex) {
            //logger.error( "Error playing the audio file.", ex);
        }
    }

    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            //logger.debug("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            m_bPlayCompleted = true;
            //logger.debug( "Playback completed.");
        }
    }

}
