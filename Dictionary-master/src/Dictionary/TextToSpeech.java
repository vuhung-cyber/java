package Dictionary;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    private String text;
    private static final String voice = "kevin16";

    public TextToSpeech(String text) {
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void read() {
        if (text == null)
            throw new NullPointerException();

        VoiceManager manager = VoiceManager.getInstance();
        Voice kevin = manager.getVoice(voice);
        kevin.allocate();
        kevin.speak(text);
        kevin.deallocate();
    }
}
