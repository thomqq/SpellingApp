package pl.tq.spelling.service.mp3Provider;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.Gender;
import com.amazonaws.services.polly.model.LanguageCode;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;
import pl.tq.spelling.util.Config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PollyMp3Provider implements Mp3Provider {
    private final AmazonPollyClient polly;
    private Voice voice;
    private final Voice defaultVoice;
    private Config config;

    public PollyMp3Provider(Config config) {
        this.config = config;
        polly = new AmazonPollyClient(new BasicAWSCredentials(config.getValue("polly_access"), config.getValue("polly_key")),
                new ClientConfiguration());
        polly.setRegion(Region.getRegion(Regions.US_EAST_1));
        // Create describe voices request.
        DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();

        // Synchronously ask Amazon Polly to describe available TTS voices.

        defaultVoice = new Voice();
        //DescribeVoicesResult describeVoicesResult = polly.describeVoices(describeVoicesRequest);
        //voice = describeVoicesResult.getVoices().get(0);
        defaultVoice.setLanguageCode(LanguageCode.EnGB);
        defaultVoice.setGender(Gender.Female);
        defaultVoice.setId("Amy");
        defaultVoice.setLanguageName("British English");
        defaultVoice.setName("Amy");
    }

    public void setVoice( PollyVoiceEnum pollyVoiceEnum) {
        this.voice = pollyVoiceEnum.getVoice();
    }

    @Override
    public String getPathToMp3Sentence(String sentence) {
        Voice useVoice = voice == null ? defaultVoice : voice;
        SynthesizeSpeechRequest synthReq =
                new SynthesizeSpeechRequest().withText(sentence).withVoiceId(useVoice.getId())
                        .withOutputFormat(OutputFormat.Mp3);
        SynthesizeSpeechResult synthRes = polly.synthesizeSpeech(synthReq);

        InputStream audioStream = synthRes.getAudioStream();

        try {
            File file = File.createTempFile("aaa", "aa");
            Files.copy(audioStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            audioStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
