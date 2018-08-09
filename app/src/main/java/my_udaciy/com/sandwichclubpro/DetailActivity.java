package my_udaciy.com.sandwichclubpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import my_udaciy.com.sandwichclubpro.model.SandWich;
import my_udaciy.com.sandwichclubpro.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {
    private TextView Origin,Ingredients,Description,AlsoKnowAs,MainName;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView ingredientsBg = findViewById(R.id.image_bg);
        new SandWich();
        Intent intentsand = getIntent();
        if (intentsand == null) {
            closeWhenError_404();
        }

        int position = intentsand.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeWhenError_404();
            return;
        }

        String[] sandwiched = getResources().getStringArray(R.array.sandwich_history);
        String json_sand = sandwiched[position];
        SandWich sandwiches = null;

        try {
            sandwiches = JsonUtils.parseSandwichedJson(json_sand);
            //removed logcat
        } catch (JSONException e) {
            e.printStackTrace();
            closeWhenError_404();
        }
        if (sandwiches == null) {
            // Sandwich data unavailable
            closeWhenError_404();
            return;
        }

        InsertUI();
        Origin.setText(sandwiches.getPlaceOfOrigin());

        Description.setText(sandwiches.getDescription());
        Ingredients.setText(sandwiches.getIngredients().toString());
        MainName.setText(sandwiches.getMainName());

        if (!sandwiches.getAlsoKnownAs().isEmpty()){
            for (String alsoKnownAs: sandwiches.getAlsoKnownAs())
                AlsoKnowAs.append(alsoKnownAs+", ");
        }
        if (!sandwiches.getIngredients().isEmpty()){
            for (String ingredient:sandwiches.getIngredients()){
                Ingredients.append(ingredient+" ,");
            }
        }
        Picasso.with(this)
                .load(sandwiches.getImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(ingredientsBg);


        setTitle(sandwiches.getMainName());

    }

    private void closeWhenError_404() {
        finish();
        Toast.makeText(this, R.string.error_message, Toast.LENGTH_SHORT).show();
    }

    private void InsertUI() {
        Origin = findViewById(R.id.origin_sm);
        AlsoKnowAs = findViewById(R.id.also_known_sm);
        Description = findViewById(R.id.description_sm);
        Ingredients = findViewById(R.id.ingredients_sm);
        MainName = findViewById(R.id.main_name_bg);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.left_out);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                overridePendingTransition(R.anim.right_in, R.anim.right_out);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
