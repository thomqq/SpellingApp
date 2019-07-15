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
    private Config config;

    public PollyMp3Provider(Config config) {
        this.config = config;
        polly = new AmazonPollyClient(new BasicAWSCredentials(config.getValue("polly_access"), config.getValue("polly_key")),
                new ClientConfiguration());
        polly.setRegion(Region.getRegion(Regions.US_EAST_1));
    }

    @Override
    public String getPathToMp3Sentence(Sentence sentence) {
        SynthesizeSpeechRequest synthReq =
                new SynthesizeSpeechRequest().withText(sentence.getText()).withVoiceId(sentence.getPollyVoice().getId())
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
