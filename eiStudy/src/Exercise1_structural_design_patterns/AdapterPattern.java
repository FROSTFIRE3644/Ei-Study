package Exercise1_structural_design_patterns;
//MediaPlayer Interface (Target Interface)
interface MediaPlayer {
 void play(String audioType, String fileName);
}

//AdvancedMediaPlayer Interface (Adaptee)
interface AdvancedMediaPlayer {
 void playMp3(String fileName);
 void playWav(String fileName);
}

//Concrete class for MP3 player (Adaptee)
class Mp3Player implements AdvancedMediaPlayer {
 @Override
 public void playMp3(String fileName) {
     System.out.println("Playing MP3 file: " + fileName);
 }

 @Override
 public void playWav(String fileName) {
     // Do nothing
 }
}

//Concrete class for WAV player (Adaptee)
class WavPlayer implements AdvancedMediaPlayer {
 @Override
 public void playMp3(String fileName) {
     // Do nothing
 }

 @Override
 public void playWav(String fileName) {
     System.out.println("Playing WAV file: " + fileName);
 }
}

//Adapter Class
class MediaAdapter implements MediaPlayer {
 AdvancedMediaPlayer advancedMediaPlayer;

 public MediaAdapter(String audioType) {
     if (audioType.equalsIgnoreCase("mp3")) {
         advancedMediaPlayer = new Mp3Player();
     } else if (audioType.equalsIgnoreCase("wav")) {
         advancedMediaPlayer = new WavPlayer();
     }
 }

 @Override
 public void play(String audioType, String fileName) {
     if (audioType.equalsIgnoreCase("mp3")) {
         advancedMediaPlayer.playMp3(fileName);
     } else if (audioType.equalsIgnoreCase("wav")) {
         advancedMediaPlayer.playWav(fileName);
     }
 }
}

//Concrete class AudioPlayer (Client)
class AudioPlayer implements MediaPlayer {
 MediaAdapter mediaAdapter;

 @Override
 public void play(String audioType, String fileName) {
     if (audioType.equalsIgnoreCase("mp3") || audioType.equalsIgnoreCase("wav")) {
         mediaAdapter = new MediaAdapter(audioType);
         mediaAdapter.play(audioType, fileName);
     } else {
         System.out.println("Invalid audio format: " + audioType + ". Supported formats are MP3 and WAV.");
     }
 }
}

//Main Class to run Adapter Pattern Example
public class AdapterPattern {
 public static void main(String[] args) {
     AudioPlayer audioPlayer = new AudioPlayer();

     audioPlayer.play("mp3", "song1.mp3");  // Output: Playing MP3 file: song1.mp3
     audioPlayer.play("wav", "song2.wav");  // Output: Playing WAV file: song2.wav
     audioPlayer.play("mp4", "video.mp4");  // Output: Invalid audio format: mp4. Supported formats are MP3 and WAV.
 }
}
