package elan.mit.electrofocus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import elan.mit.electrofocus.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EventSelectionList extends AppCompatActivity {

    private DatabaseReference mEvents;
    private RecyclerView mSelectionList;
    private SelectionListAdapter selectionListAdapter;
    private List<SelectionList> selectionLists;
    private String EventName;
    private String ToolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_selection_list);

        EventName = getIntent().getStringExtra("event");
        ToolbarTitle = getIntent().getStringExtra("toolbar");



        Toolbar mToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle(ToolbarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        selectionLists = new ArrayList<>();
        mEvents = FirebaseDatabase.getInstance().getReference().child("Events").child(EventName);
        mEvents.keepSynced(true);

        mSelectionList = findViewById(R.id.student_selection_list);
        mSelectionList.setHasFixedSize(true);
        mSelectionList.setLayoutManager(new LinearLayoutManager(this));

        mEvents.child("General").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String title = dataSnapshot.child("SelectionListHeading").getValue().toString();
                TextView TitleText = findViewById(R.id.heading_id);
                TitleText.setText(title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        SetSelectionList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem SearchItem = menu.findItem(R.id.search_id);
        SearchView searchView = (SearchView) SearchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText)
            {
                if(!newText.equals("") && !TextUtils.isEmpty(newText))
                {
                    mEvents.child("SelectionList").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            selectionLists.clear();

                            for(DataSnapshot ds : dataSnapshot.getChildren())
                            {
                                SelectionList SingleSelectionList = ds.getValue(SelectionList.class);

                                if(SingleSelectionList.getCollege_name().toLowerCase().contains(newText.toLowerCase()) ||
                                        SingleSelectionList.getStudent_1().toLowerCase().contains(newText.toLowerCase())||
                                        SingleSelectionList.getStudent_2().toLowerCase().contains(newText.toLowerCase())||
                                        SingleSelectionList.getStudent_3().toLowerCase().contains(newText.toLowerCase()))
                                {
                                    selectionLists.add(SingleSelectionList);
                                }
                                selectionListAdapter = new SelectionListAdapter(EventSelectionList.this,selectionLists);
                                selectionListAdapter.notifyDataSetChanged();
                                mSelectionList.setAdapter(selectionListAdapter);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    SetSelectionList();
                }
                return false;
            }
        });
        return true;
    }

    public void SetSelectionList()
    {
        mEvents.child("SelectionList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                selectionLists.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    SelectionList SingleSelectionList = ds.getValue(SelectionList.class);
                    selectionLists.add(SingleSelectionList);
                    selectionListAdapter = new SelectionListAdapter(EventSelectionList.this,selectionLists);
                    mSelectionList.setAdapter(selectionListAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
