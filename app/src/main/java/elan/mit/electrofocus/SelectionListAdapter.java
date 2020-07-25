package elan.mit.electrofocus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import elan.mit.electrofocus.R;

import java.util.List;

public class SelectionListAdapter extends RecyclerView.Adapter<SelectionListAdapter.ViewHolder>
{
    Context mContext;
    List<SelectionList> selectionLists;

    public SelectionListAdapter(Context mContext, List<SelectionList> selectionLists) {
        this.mContext = mContext;
        this.selectionLists = selectionLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.selection_list_individual,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String student_1 = selectionLists.get(position).getStudent_1();
        String student_2 = selectionLists.get(position).getStudent_2();
        String student_3 = selectionLists.get(position).getStudent_3();
        String college_name = selectionLists.get(position).getCollege_name();

        holder.Student_1.setText(student_1);
        holder.Student_2.setText(student_2);
        holder.Student_3.setText(student_3);
        holder.College_name.setText(college_name);
        holder.Serial_no.setText((position+1)+".");

    }

    @Override
    public int getItemCount() {
        return selectionLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView Student_1;
        TextView Student_2;
        TextView Student_3;
        TextView College_name;
        TextView Serial_no;

        ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            Student_1 = itemView.findViewById(R.id.student_1);
            Student_2 = itemView.findViewById(R.id.student_2);
            Student_3 = itemView.findViewById(R.id.student_3);
            College_name = itemView.findViewById(R.id.college_name_id);
            Serial_no = itemView.findViewById(R.id.count_id);
        }
    }
}
