/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.iosched.shared.domain.agenda;

import android.text.TextUtils;

import com.google.samples.apps.iosched.model.Block;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_AFTER_HOURS;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_BIM_SESSION;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_HONOUR;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_KEYNOTE;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_MEAL;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_REGISTRATION;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_REMARKS;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_SESSIONS;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_SHOWCASE;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_SPEECH;

public class AgendaJSONParser {

    private static HashMap<String, Integer> typeColorMAP = new HashMap<>();

    static {

        typeColorMAP.put("session", COLOR_SESSIONS);
        typeColorMAP.put("meal", COLOR_MEAL);
        typeColorMAP.put("speech", COLOR_SPEECH);
        typeColorMAP.put("showcase", COLOR_SHOWCASE);
        typeColorMAP.put("remark", COLOR_REMARKS);
        typeColorMAP.put("badge", COLOR_REGISTRATION);
        typeColorMAP.put("after_hours", COLOR_AFTER_HOURS);
        typeColorMAP.put("keynote", COLOR_KEYNOTE);
        typeColorMAP.put("honour", COLOR_HONOUR);
        typeColorMAP.put("bim_session", COLOR_BIM_SESSION);

    }


    public static ArrayList<Block> getAgenda() throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<Block> blocks = new ArrayList<>();
        Block block;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject agenda = jsonArray.getJSONObject(i);
            String title = agenda.optString("title");
            String type = agenda.optString("type");
            boolean isDark = type.equalsIgnoreCase("keynote") || type.contains("session") || type.contains("after_hours")
                    || type.contains("speech") || type.contains("honour");


            String start = agenda.optString("start");
            String end = agenda.optString("end");


            block = new Block(title, type, getOrDefault(typeColorMAP, type),
                    getOrDefault(typeColorMAP, type), isDark, ZonedDateTime.parse(start),
                    ZonedDateTime.parse(end));
            blocks.add(block);
        }

        return blocks;
    }

    private static Integer getOrDefault(HashMap<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return COLOR_SESSIONS;
    }


    private final static String json = "[\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T08:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T10:30:00+05:45\",\n" +
            "        \"title\": \"OPENING CEREMONY\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T11:00:00+05:45\",\n" +
            "        \"title\": \"TEA\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T11:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T13:00:00+05:45\",\n" +
            "        \"title\": \"KEY NOTE SESSION\",\n" +
            "        \"type\": \"keynote\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T13:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"title\": \"LUNCH\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T15:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 1 (Advanced Geospatial Technologies: Urban)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T15:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 2 (Curriculum Development for GIS Education Outreach)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T16:30:00+05:45\",\n" +
            "        \"title\": \"BIM SESSION\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T16:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T17:45:00+05:45\",\n" +
            "        \"title\": \"Closed Session\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T15:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T16:00:00+05:45\",\n" +
            "        \"title\": \"LUNCH\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T16:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T17:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 3 (Remote Sensing and Space Education: RS for Forest Management)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T16:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T17:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 4 (Spatial Data Infrastructure and Internet of Things (IOT))\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T18:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T20:00:00+05:45\",\n" +
            "        \"title\": \"Partyasd\",\n" +
            "        \"type\": \"after_hours\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T08:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"title\": \"Opening\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T11:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 5 (Advanced Geospatial Technologies- Ecology and Geography)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T11:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 6 (Land Management)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T11:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T12:00:00+05:45\",\n" +
            "        \"title\": \"Lunch\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T12:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T13:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 7 (Remote Sensing and Space Education: RS for Natural Disaster)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T12:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T13:30:00+05:45\",\n" +
            "        \"title\": \"TECHINCAL SESSION 8 (Exploring International Collaboration)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T13:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T14:30:00+05:45\",\n" +
            "        \"title\": \"Lunch\",\n" +
            "        \"type\": \"Lunch\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T14:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T15:00:00+05:45\",\n" +
            "        \"title\": \"Professional Networking (Informal)\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T15:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T16:30:00+05:45\",\n" +
            "        \"title\": \"Closing ceremony\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T16:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T17:00:00+05:45\",\n" +
            "        \"title\": \"Hi-Tea\",\n" +
            "        \"type\": \"after_hours\\n\"\n" +
            "    }\n" +
            "]";
}
