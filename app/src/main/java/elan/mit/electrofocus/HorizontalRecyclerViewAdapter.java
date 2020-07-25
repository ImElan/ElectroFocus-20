package elan.mit.electrofocus;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import elan.mit.electrofocus.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.ViewHolder>
{

    private ArrayList<String> names;
    private ArrayList<Integer> images;
    private ArrayList<String> designation;

    public HorizontalRecyclerViewAdapter(ArrayList<String> names, ArrayList<Integer> images,ArrayList<String> designation) {

        this.names = names;
        this.images = images;
        this.designation = designation;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_card_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Name.setText(names.get(position));
        holder.Image.setImageResource(images.get(position));
        holder.Designation.setText(designation.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView Image;
        TextView Name;
        TextView Designation;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Image = itemView.findViewById(R.id.image_id);
            Name = itemView.findViewById(R.id.name_id);
            Designation = itemView.findViewById(R.id.designation_id);
        }
    }
}
