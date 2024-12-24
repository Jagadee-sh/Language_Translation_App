package com.example.translationapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Spinner spinnerInputLanguage, spinnerOutputLanguage;
    private Button buttonTranslate;
    private TextView textViewOutput;

    private static final String BASE_URL = "https://libretranslate.com/";
    private TranslationApi translationApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextInput = findViewById(R.id.editTextInput);
        spinnerInputLanguage = findViewById(R.id.spinnerInputLanguage);
        spinnerOutputLanguage = findViewById(R.id.spinnerOutputLanguage);
        buttonTranslate = findViewById(R.id.buttonTranslate);
        textViewOutput = findViewById(R.id.textViewOutput);

        // Initialize Retrofit for API calls
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        translationApi = retrofit.create(TranslationApi.class);

        // Set up translation button click listener
        buttonTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextInput.getText().toString();
                String inputLang = spinnerInputLanguage.getSelectedItem().toString();
                String outputLang = spinnerOutputLanguage.getSelectedItem().toString();

                if (inputText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter some text to translate", Toast.LENGTH_SHORT).show();
                    return;
                }

                translateText(inputText, inputLang, outputLang);
            }
        });
    }

    private void translateText(String inputText, String inputLang, String outputLang) {
        // Create a request object with the input text and selected languages
        TranslationRequest request = new TranslationRequest(inputText, inputLang, outputLang);

        // Make an API call using Retrofit
        translationApi.translate(request).enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, retrofit2.Response<TranslationResponse> response) {
                if (response.isSuccessful()) {
                    // Display the translated text
                    textViewOutput.setText(response.body().getTranslatedText());
                } else {
                    // Handle failure
                    Toast.makeText(MainActivity.this, "Translation failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                // Handle error
                Toast.makeText(MainActivity.this, "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
