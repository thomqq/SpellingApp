package pl.tq.spelling.service.mp3Provider;

public enum PollyVoiceEnum {
    EN( "Amy"),
    PL( "Ewa");
    private String id;

    PollyVoiceEnum( String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
