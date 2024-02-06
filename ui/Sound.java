
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
  private static Clip clip;
  private static Clip easterEgg;
  public static void playSound(int selected) {
    switch(selected) {
      case 1:
        soundPlayer(Model.AUDIO_PATH_TACOBELL_SELECT);
        break;
      case 2:
        soundPlayer(Model.AUDIO_PATH_AMOGUS_SELECT);
        break;
      default:
        break;
    }
  }
  public static void playMusic(int selected) {
    switch(selected) {
      case 1:
        musicPlayer(Model.AUDIO_PATH_TACOBELL);
        break;
      case 2:
        musicPlayer(Model.AUDIO_PATH_AMOGUS);
        break;
      default:
        break;
    }
  }
  public static void playEasterEgg(int selected) {
    switch(selected) {
      case 1:
        easterEggPlayer(Model.AUDIO_PATH_TACOBELL_FUNNY);
        break;
      case 2:
        easterEggPlayer(Model.AUDIO_PATH_AMOGUS_FUNNY);
        break;
      default:
        break;
    }
  }
  private static void soundPlayer(String path) {
    File soundFile = new File(path);
    try {
      AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioIn);
      clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  private static synchronized void musicPlayer(final String url) {
    new Thread(new Runnable() {
      public void run() {
        File soundFile = new File(url);
        try {
          clip = AudioSystem.getClip();
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
          clip.open(audioIn);
          clip.start(); 
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
  private static synchronized void easterEggPlayer(final String url) {
    new Thread(new Runnable() {
      public void run() {
        File soundFile = new File(url);
        try {
          easterEgg = AudioSystem.getClip();
          AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
          easterEgg.open(audioIn);
          easterEgg.start(); 
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
  public static void mute() {
    if(clip!=null) {
      clip.close();
    }
  }
  public static void muteEasterEgg() {
    if(easterEgg!=null) {
      easterEgg.close();
    }
  }
}
