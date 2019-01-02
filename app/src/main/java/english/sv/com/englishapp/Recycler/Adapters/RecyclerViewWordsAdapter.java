package english.sv.com.englishapp.Recycler.Adapters;

import android.app.Application;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mapzen.speakerbox.Speakerbox;

import java.util.List;
import java.util.Locale;

import english.sv.com.englishapp.Models.Models.Words;
import english.sv.com.englishapp.R;
import english.sv.com.englishapp.Recycler.Adapters.Holders.WordsRecyclerViewHolder;

import static android.speech.tts.TextToSpeech.ERROR;
import static android.speech.tts.TextToSpeech.SUCCESS;

public class RecyclerViewWordsAdapter extends RecyclerView.Adapter<WordsRecyclerViewHolder> implements
        TextToSpeech.OnInitListener {

    private List<Words> itemList;
    private Context context;

    TextToSpeech tts;

    public RecyclerViewWordsAdapter(Context context, List<Words> itemList) {
        this.itemList = itemList;
        this.context = context;
    }
    @Override
    public WordsRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        WordsRecyclerViewHolder rcv = new WordsRecyclerViewHolder(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(WordsRecyclerViewHolder holder, final int position) {
        holder.word.setText("Word: " + itemList.get(position).getWord());
        holder.meaning.setText("Meaning: " + itemList.get(position).getMeaning());
        holder.sentence.setText("Sentence:" + itemList.get(position).getSentence());

        tts = new TextToSpeech(context, this);

        tts.setPitch(0.6f);
        tts.setSpeechRate(1.0f);

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               speakOut(itemList.get(position).getWord());
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }


    @Override
    public void onInit(int status) {
        switch (status) {

            case SUCCESS:
                tts.setLanguage(new Locale("en"));
                break;
            case ERROR:
                // Something went wrong. You can't set the language
                break;
        }
    }

    private void speakOut(String text) {

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null,"id1");
    }
}