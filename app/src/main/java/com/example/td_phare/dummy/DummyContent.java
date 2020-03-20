package com.example.td_phare.dummy;

import android.util.Log;

import com.example.td_phare.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

//    private static final int COUNT = 25;
//
//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
//    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String details;

        public DummyItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

    }


    public static void loadPhareJson() {
        try {
            String str = loadStrJson();
            JSONObject jObjConnection = null;
            jObjConnection = new JSONObject(str);
            JSONObject jsonBix = jObjConnection.getJSONObject("phare");
            JSONArray jsonA = jsonBix.getJSONArray("liste");

            for (int i = 0; i < jsonA.length(); i++) {
                JSONObject msg = (JSONObject) jsonA.get(i);
                addItem(new DummyItem(
                        msg.getString("id"),
                        msg.getString("name"),
                        msg.getString("region")
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static String loadStrJson() {
        String str = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(MainActivity.getContext().getAssets().open("Phare.json")));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            str = new String(sb.toString());
            Log.d("MainActivity", "str");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}