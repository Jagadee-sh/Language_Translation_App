// Model for API Request
public class TranslationRequest {
    private String q;
    private String source;
    private String target;

    public TranslationRequest(String q, String source, String target) {
        this.q = q;
        this.source = source;
        this.target = target;
    }

    // Getter and Setter methods
}

// Model for API Response
public class TranslationResponse {
    private String translatedText;

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}
