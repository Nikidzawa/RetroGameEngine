package ru.nikidzawa.snakegame.config;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundAccompaniment {
    private String URL;
    private MediaPlayer mediaPlayer;
    public void setURL (String URL) {this.URL = URL;}

    public void playMusic (String soundName, double customVolume) {
        try {
            File soundFile = new File(URL + soundName);
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(sound);
            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            if (customVolume < 0) {
                customVolume = 0;
            }
            if (customVolume > 1) {
                customVolume = 1;
            }
            float min = volume.getMinimum();
            float max = volume.getMaximum();
            volume.setValue((float) ((max - min) * customVolume + min));
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException("""
                    Ошибка при воспроизведении звука
                    1. Проверьте, что вы ранее установили путь к вашей папке с музыкой с помощью метода setUrl. Оканчиваться Url должен символом \\\s
                    2. Поддерживаемый формат музыки - .wav\s
                    3. Проверьте корректность названия. Оно должно оканчиваться на .wav""", e);
        }
    }
        public void startBackGroundMusic(String name, double volume) {
        Media media = new Media(new File
                (URL + name).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(volume);
        mediaPlayer.play();
        }
        public void stopBackgroundMusic() {
        mediaPlayer.stop();
        }
    }

