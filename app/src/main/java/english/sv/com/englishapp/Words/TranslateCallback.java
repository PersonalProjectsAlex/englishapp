package english.sv.com.englishapp.Words;

public interface TranslateCallback {
    void onSuccess(String translatedText);
    void onFailure();
}