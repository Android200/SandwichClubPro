package my_udaciy.com.sandwichclubpro.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import my_udaciy.com.sandwichclubpro.model.SandWich;

public class JsonUtils {

    public static SandWich parseSandwichedJson(String json) throws JSONException {
        JSONObject object = new JSONObject(json);
        JSONObject name = object.getJSONObject("name");
        String mainName = name.getString("mainName");
        List<String> alsoKnownAs = Arrays.asList(name.getJSONArray("alsoKnownAs").toString().replace("[","").replace("]","").replace("\"","").split(","));
        String placeOfOrigin = object.getString("placeOfOrigin");
        String description = object.getString("description");
        String imageUrl = object.getString("image");
        List<String> ingredients = Arrays.asList(object.getJSONArray("ingredients").toString().replace("[","").replace("]","").replace("\"","").split(","));
        return new SandWich(mainName,alsoKnownAs,placeOfOrigin,description,imageUrl,ingredients);
    }
}
