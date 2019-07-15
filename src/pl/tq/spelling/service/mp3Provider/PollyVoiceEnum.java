package pl.tq.spelling.service.mp3Provider;


import com.amazonaws.services.polly.model.Gender;
import com.amazonaws.services.polly.model.LanguageCode;
import com.amazonaws.services.polly.model.Voice;

public enum PollyVoiceEnum {
    EN(LanguageCode.EnGB, Gender.Female, "Amy", "British English", "Amy"),
    PL(LanguageCode.PlPL, Gender.Female, "Ewa", "Polish", "Ewa");
    private Voice voice;

    PollyVoiceEnum(LanguageCode languageCode, Gender gender, String id, String languageName, String name) {
        voice = new Voice();
        voice.setLanguageName(languageName);
        voice.setGender(gender);
        voice.setId(id);
        voice.setLanguageCode(languageCode);
        voice.setName(name);
    }

    public Voice getVoice() {
        return voice;
    }
}
