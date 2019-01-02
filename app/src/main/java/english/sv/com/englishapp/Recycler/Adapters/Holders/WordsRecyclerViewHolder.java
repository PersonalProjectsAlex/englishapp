package english.sv.com.englishapp.Recycler.Adapters.Holders;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import english.sv.com.englishapp.R;

public class WordsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView word;
    public TextView meaning;
    public TextView sentence;
    public Button play;

    public WordsRecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        word = (TextView)itemView.findViewById(R.id.word);
        meaning = (TextView)itemView.findViewById(R.id.meaning);
        sentence = (TextView)itemView.findViewById(R.id.sentence);
        play = (Button)itemView.findViewById(R.id.playbutton);
    }

    @Override
    public void onClick(View view) {
    }
}