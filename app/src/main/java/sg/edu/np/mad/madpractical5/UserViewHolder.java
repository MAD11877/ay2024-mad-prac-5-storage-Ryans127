package sg.edu.np.mad.madpractical5;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView imageViewSmall;
    ImageView imageViewLarge;
    TextView textViewName;
    TextView textViewDescription;

    public UserViewHolder(View itemView) {
        super(itemView);
        imageViewSmall = itemView.findViewById(R.id.imageViewSmall);
        imageViewLarge = itemView.findViewById(R.id.imageViewLarge);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewDescription = itemView.findViewById(R.id.textViewDescription);
    }
}
