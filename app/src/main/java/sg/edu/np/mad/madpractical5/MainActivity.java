package sg.edu.np.mad.madpractical5;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private User user;
    private DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the database handler
        dbHandler = new DatabaseHandler(this, null, null, 1);

        // Retrieve the random integer and user object from the Intent
        int randomInt = getIntent().getIntExtra("RandomNumber", 0); // Default to 0 if no data found
        user = (User) getIntent().getSerializableExtra("User");

        // Getting references to the UI components
        TextView tvName = findViewById(R.id.tvName);
        TextView tvDescription = findViewById(R.id.tvDescription);
        Button btnFollow = findViewById(R.id.btnFollow);

        btnFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check current text of the button
                if (btnFollow.getText().toString().equalsIgnoreCase("FOLLOW")) {
                    Toast.makeText(MainActivity.this, "Followed", Toast.LENGTH_SHORT).show();
                    btnFollow.setText("UNFOLLOW");
                    user.setFollowed(true);
                } else {
                    Toast.makeText(MainActivity.this, "Unfollowed", Toast.LENGTH_SHORT).show();
                    btnFollow.setText("FOLLOW");
                    user.setFollowed(false);
                }
                // Update user in the database
                dbHandler.updateUser(user);
            }
        });

        // Set the TextViews with the User's name and random integer
        tvName.setText(user.getName() + " " + randomInt); // Display name and random integer
        tvDescription.setText(user.getDescription());
        btnFollow.setText(user.getFollowed() ? "UNFOLLOW" : "FOLLOW");  // Set the initial button text based on followed status
    }
}
