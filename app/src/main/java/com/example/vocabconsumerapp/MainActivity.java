package com.example.vocabconsumerapp;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private EditText topicIdEditText;
    private RecyclerView recyclerView;
    private VocabularyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topicIdEditText = findViewById(R.id.topicIdEditText);
        Button fetchButton = findViewById(R.id.fetchButton);
        recyclerView = findViewById(R.id.vocabularyRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        checkAndRequestPermission();

        if (ContextCompat.checkSelfPermission(this, "com.example.toeicvocaapp.READ_VOCABULARY")
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{"com.example.toeicvocaapp.READ_VOCABULARY"},
                    PERMISSION_REQUEST_CODE);
        }

        fetchButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, "com.example.toeicvocaapp.READ_VOCABULARY")
                    == PackageManager.PERMISSION_GRANTED) {
                String topicId = topicIdEditText.getText().toString();
                if (!topicId.isEmpty()) {
                    fetchVocabulary(Integer.parseInt(topicId));
                } else {
                    Toast.makeText(this, "Please enter a Topic ID", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, "com.example.toeicvocaapp.READ_VOCABULARY") != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{"com.example.toeicvocaapp.READ_VOCABULARY"}, PERMISSION_REQUEST_CODE);
        }
    }

    private void fetchVocabulary(int topicId) {
        List<VocabularyAdapter.Vocabulary> vocabList = new ArrayList<>();
        Uri uri = VocabularyContract.VocabularyEntry.CONTENT_URI.buildUpon()
                .appendPath(String.valueOf(topicId))
                .build();

        try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String english = cursor.getString(cursor.getColumnIndexOrThrow(VocabularyContract.VocabularyEntry.COLUMN_ENGLISH));
                    String vietnamese = cursor.getString(cursor.getColumnIndexOrThrow(VocabularyContract.VocabularyEntry.COLUMN_VIETNAMESE));
                    vocabList.add(new VocabularyAdapter.Vocabulary(english, vietnamese));
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error fetching vocabulary: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        adapter = new VocabularyAdapter(vocabList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}