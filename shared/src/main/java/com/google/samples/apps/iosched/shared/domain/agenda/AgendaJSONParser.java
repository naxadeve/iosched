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

import com.google.samples.apps.iosched.model.Block;
import com.google.samples.apps.iosched.shared.data.config.AppConfigDataSource;
import com.google.samples.apps.iosched.shared.data.config.RemoteAppConfigDataSource;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_AFTER_HOURS;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_HONOUR;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_KEYNOTE;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_MEAL;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_REGISTRATION;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_PHOTO;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_SESSIONS;
import static com.google.samples.apps.iosched.shared.data.agenda.AgendaBlocksKt.COLOR_SPEECH;
import static com.google.samples.apps.iosched.shared.data.config.RemoteAppConfigDataSource.AGENDA;

public class AgendaJSONParser {

    private static HashMap<String, Integer> typeColorMAP = new HashMap<>();

    static {

        typeColorMAP.put("session", COLOR_SESSIONS);
        typeColorMAP.put("meal", COLOR_MEAL);
        typeColorMAP.put("speech", COLOR_SPEECH);
        typeColorMAP.put("photo", COLOR_PHOTO);
        typeColorMAP.put("badge", COLOR_REGISTRATION);
        typeColorMAP.put("after_hours", COLOR_AFTER_HOURS);
        typeColorMAP.put("keynote", COLOR_KEYNOTE);
        typeColorMAP.put("honour", COLOR_HONOUR);


    }


    public static ArrayList<Block> getAgenda(@NotNull AppConfigDataSource appConfigDataSource) throws JSONException {

        JSONArray jsonArray = new JSONArray();
        ArrayList<Block> blocks = new ArrayList<>();
        Block block;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject agenda = jsonArray.getJSONObject(i);
            String title = agenda.optString("title");
            String type = agenda.optString("type");
            boolean isDark = type.equalsIgnoreCase("keynote")  || type.contains("after_hours")
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
            "        \"end\": \"2019-12-10T09:00:00+05:45\",\n" +
            "        \"title\": \"Registration+Breakfast+Cultural Show By Dhulikhel Municipality (Dhime Baja+Laakhe Naach)\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:05:00+05:45\",\n" +
            "        \"title\": \"Taking Seats At The Ceremony Hall And Introduction To The Dignitaries\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:05:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:07:00+05:45\",\n" +
            "        \"title\": \"National Anthem\",\n" +
            "        \"type\": \"honour\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:07:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:15:00+05:45\",\n" +
            "        \"title\": \"Welcome Speech\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:15:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:17:00+05:45\",\n" +
            "        \"title\": \"Inauguration: Hon' Padma Kumari Aryal, Minister For Land Management, Cooperatives And Archive(Chief Guest) - Swasti Baachan Alongside\",\n" +
            "        \"type\": \"honour\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:17:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:22:00+05:45\",\n" +
            "        \"title\": \"Welcome Remarks\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:22:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:27:00+05:45\",\n" +
            "        \"title\": \"Remarks: Guest\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:27:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:37:00+05:45\",\n" +
            "        \"title\": \"Remarks: Isprs Representative\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:37:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:47:00+05:45\",\n" +
            "        \"title\": \"Remarks\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:47:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"title\": \"Felicitation Of Sponsors\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"title\": \"Inaugural Speech\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:10:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T10:20:00+05:45\",\n" +
            "        \"title\": \"Closing Remarks: Chair\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:20:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T10:30:00+05:45\",\n" +
            "        \"title\": \"Group Photo\",\n" +
            "        \"type\": \"photo\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T11:00:00+05:45\",\n" +
            "        \"title\": \"Tea\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T11:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T13:00:00+05:45\",\n" +
            "        \"title\": \"Key Note Session\",\n" +
            "        \"type\": \"keynote\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T13:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"title\": \"Lunch\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T15:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 1 (Advanced Geospatial Technologies: Urban)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T15:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 2 (Curriculum Development For Gis Education Outreach)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T14:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T16:30:00+05:45\",\n" +
            "        \"title\": \"Bim Session\",\n" +
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
            "        \"title\": \"Lunch\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T16:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T17:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 3 (Remote Sensing And Space Education: Rs For Forest Management)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T16:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T17:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 4 (Spatial Data Infrastructure And Internet Of Things (Iot))\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T18:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T20:00:00+05:45\",\n" +
            "        \"title\": \"Party\",\n" +
            "        \"type\": \"after_hours\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T08:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:00:00+05:45\",\n" +
            "        \"title\": \"Registration + Breakfast\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:45:00+05:45\",\n" +
            "        \"title\": \"Day Ii Opening Session\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:10:00+05:45\",\n" +
            "        \"title\": \"Summary Of Day I\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:10:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:30:00+05:45\",\n" +
            "        \"title\": \"The Future Of Authoritative Geospatial Data In The Big Data World - Trends, Opportunities And Challenges\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T09:45:00+05:45\",\n" +
            "        \"title\": \"Presentation - Trimble\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T09:45:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"title\": \"Short Break\",\n" +
            "        \"type\": \"after_hours\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T11:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 5 (Advanced Geospatial Technologies- Ecology And Geography)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T10:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T11:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 6 (Land Management)\",\n" +
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
            "        \"title\": \"Techincal Session 7 (Remote Sensing And Space Education: Rs For Natural Disaster)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T12:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T13:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 8 (Exploring International Collaboration)\",\n" +
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
            "        \"title\": \"Closing Ceremony\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-10T16:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-10T17:00:00+05:45\",\n" +
            "        \"title\": \"Hi-Tea\",\n" +
            "        \"type\": \"after_hours\"\n" +
            "    }\n" +
            "]";
}
