package my_udaciy.com.sandwichclubpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] sandwichpro = getResources().getStringArray(R.array.Sunan_Sandwich);
        ArrayAdapter<String> adaptersandwich = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, sandwichpro);

        // Simplification: Using a ListView instead of a RecyclerView
        ListView listViewsandwich = findViewById(R.id.sandwiched_list);
        listViewsandwich.setAdapter(adaptersandwich);
        listViewsandwich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) { 
                gotoDetailActivity(position);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
    }

    private void gotoDetailActivity(int position) {
        Intent intentsandwich = new Intent(this, DetailActivity.class);
        intentsandwich.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intentsandwich);
    }
}
