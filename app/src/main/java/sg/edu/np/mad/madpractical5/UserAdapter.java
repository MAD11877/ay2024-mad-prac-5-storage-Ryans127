package sg.edu.np.mad.madpractical5;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Random;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<User> userList;
    private Context context;

    public UserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_activity_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.textViewName.setText(user.getName());
        holder.textViewDescription.setText(user.getDescription());
        holder.imageViewSmall.setImageResource(R.drawable.ic_launcher_foreground);
        holder.imageViewLarge.setImageResource(R.drawable.ic_launcher_foreground);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(user.getName());
                builder.setPositiveButton("View", (dialog, which) -> {
                    // Generate random integer
                    Random random = new Random();
                    int randomInt = random.nextInt(100);

                    // Start MainActivity with the random integer and user object
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.putExtra("RandomNumber", randomInt);
                    intent.putExtra("User", user);
                    context.startActivity(intent);
                });
                builder.setNegativeButton("Close", (dialog, which) -> {
                    // Dismiss the dialog
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
