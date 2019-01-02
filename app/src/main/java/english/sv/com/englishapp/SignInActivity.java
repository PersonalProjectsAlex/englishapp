package english.sv.com.englishapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;

import org.json.JSONArray;

import english.sv.com.englishapp.Models.Students.StudentRegistered;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        AndroidNetworking.post("https://englishappdeployed.herokuapp.com/students/createStudent")
                .addBodyParameter("firstName", "Shekhar")
                .addBodyParameter("lastName", "Shekhar")
                .setPriority(Priority.HIGH)
                .build()
                .getAsObject(StudentRegistered.class, new ParsedRequestListener<StudentRegistered>() {
                    @Override
                    public void onResponse(StudentRegistered user) {
                        // do anything with response
                        Log.d("student", "id : " + user.toString());


                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("Error", anError.getErrorBody());
                        Log.e("Error", anError.getResponse().toString());
                    }
                });
    }
}
