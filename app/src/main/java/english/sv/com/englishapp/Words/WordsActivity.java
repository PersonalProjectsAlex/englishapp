package english.sv.com.englishapp.Words;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.mapzen.speakerbox.Speakerbox;

import java.util.List;

import english.sv.com.englishapp.Models.Models.Words;
import english.sv.com.englishapp.Models.Recycler.WordsContainerModel;
import english.sv.com.englishapp.R;
import english.sv.com.englishapp.Recycler.Adapters.RecyclerViewWordsAdapter;

public class WordsActivity extends AppCompatActivity {
    //Global variables
    List<WordsContainerModel> mDataset;
    String TAG = "response:";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewWordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_recycler_container);

        //Inicializating our networking
        AndroidNetworking.initialize(getApplicationContext());

        //RecyclerView
        recyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        request();
    }

    public void request(){
        AndroidNetworking.get("https://englishappdeployed.herokuapp.com/students/words")
                .setTag(this)
                .setPriority(Priority.LOW)
                .build()
                .getAsObjectList(Words.class, new ParsedRequestListener<List<Words>>() {
                    @Override
                    public void onResponse(List<Words> words) {
                        // do anything with response
                        Log.d(TAG, "userList size : " + words.size());
                        adapter = new RecyclerViewWordsAdapter(getApplicationContext(),words);

                        recyclerView.setAdapter(adapter);
                        for (Words user : words) {
                            Log.d(TAG, "id : " + user.getId());
                            Log.d(TAG, "firstname : " + user.getWord());
//                            Log.d(TAG, "lastname : " + user.lastname);
                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG,anError.toString());
                    }
                });
    }
}
