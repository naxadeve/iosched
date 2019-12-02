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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.threeten.bp.ZonedDateTime;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.samples.apps.iosched.model.Tag.TYPE_SESSIONS;
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

        typeColorMAP.put("Technical Session", COLOR_SESSIONS);
        typeColorMAP.put("Tea", COLOR_MEAL);
        typeColorMAP.put("Speech", COLOR_SPEECH);
        typeColorMAP.put("Showcase", COLOR_SHOWCASE);
        typeColorMAP.put("Remarks", COLOR_REMARKS);
        typeColorMAP.put("Registration", COLOR_REGISTRATION);
        typeColorMAP.put("Party", COLOR_AFTER_HOURS);
        typeColorMAP.put("Lunch", COLOR_MEAL);
        typeColorMAP.put("Keynote", COLOR_KEYNOTE);
        typeColorMAP.put("Key note", COLOR_KEYNOTE);
        typeColorMAP.put("Honour", COLOR_HONOUR);
        typeColorMAP.put("BIM Session", COLOR_BIM_SESSION);

    }


    public static ArrayList<Block> getAgenda() throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<Block> blocks = new ArrayList<>();
        Block block;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject agenda = jsonArray.getJSONObject(i);
            String title = agenda.optString("title");

            agenda.optString("title_2");
            String type = agenda.optString("type");
            String start = agenda.optString("start");
            String end = agenda.optString("end");


            block = new Block(title, TYPE_SESSIONS, getOrDefault(typeColorMAP, type),
                    getOrDefault(typeColorMAP, type), false, ZonedDateTime.parse(start),
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
            "  {\n" +
            "    \"\": 0,\n" +
            "    \"title\": \"Registration\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Registration\",\n" +
            "    \"start\": \"2019-12-10T08:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 1,\n" +
            "    \"title\": \"Introduction to the dignitaries\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-10T09:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:05+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 2,\n" +
            "    \"title\": \"National Anthem\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-10T09:05+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:07+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 3,\n" +
            "    \"title\": \"Welcome Speech\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-10T09:07+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 4,\n" +
            "    \"title\": \"Inauguration by Hon'ble Minister, MOLCPA (Chief Guest)\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-10T09:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:17+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 5,\n" +
            "    \"title\": \"Remarks: Guests\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Remarks\",\n" +
            "    \"start\": \"2019-12-10T09:17+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:32+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 6,\n" +
            "    \"title\": \"Remarks: Guests\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Remarks\",\n" +
            "    \"start\": \"2019-12-10T09:32+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:40+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 7,\n" +
            "    \"title\": \"Remarks: ISPRS Representative\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Remarks\",\n" +
            "    \"start\": \"2019-12-10T09:40+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:50+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 8,\n" +
            "    \"title\": \"Remarks: Vice Chancellor, Kathmandu University\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Remarks\",\n" +
            "    \"start\": \"2019-12-10T09:50+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 9,\n" +
            "    \"title\": \"Felicitation of Brand Sponsors\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Honour\",\n" +
            "    \"start\": \"2019-12-10T10:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:05+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 10,\n" +
            "    \"title\": \"Inauguration Speech: Hon'ble Minister (Chief Guest)\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-10T10:05+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:20+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 11,\n" +
            "    \"title\": \"Closing Remarks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-10T10:20+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 12,\n" +
            "    \"title\": \"TEA/COFFEE BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Tea\",\n" +
            "    \"start\": \"2019-12-10T10:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T11:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 13,\n" +
            "    \"title\": \"Dr. Sunil Babu Shrestha\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Keynote\",\n" +
            "    \"start\": \"2019-12-10T11:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T11:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 14,\n" +
            "    \"title\": \"Prof. Dr. J.A. Zevenbergen\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Keynote\",\n" +
            "    \"start\": \"2019-12-10T11:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T12:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 15,\n" +
            "    \"title\": \"Dr. Sultan Kocaman\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Keynote\",\n" +
            "    \"start\": \"2019-12-10T12:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T12:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 16,\n" +
            "    \"title\": \"Prof. Ruizhi CHEN, Wuhan University\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Keynote\",\n" +
            "    \"start\": \"2019-12-10T12:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T13:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 18,\n" +
            "    \"title\": \"LUNCH BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Lunch\",\n" +
            "    \"start\": \"2019-12-10T13:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 19,\n" +
            "    \"title\": \"Technical Session I\",\n" +
            "    \"title_2\": \"Technical Session II\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 20,\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 21,\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T14:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 22,\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T14:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 23,\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T14:45+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 24,\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T15:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 25,\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T15:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 26,\n" +
            "    \"title\": \"BIM SESSION\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"BIM Session\",\n" +
            "    \"start\": \"2019-12-10T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 27,\n" +
            "    \"title\": \"TEA/COFFEE BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Tea\",\n" +
            "    \"start\": \"2019-12-10T15:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 29,\n" +
            "    \"title\": \"Technical Session III\",\n" +
            "    \"title_2\": \"Technical Session IV\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T16:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 30,\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T16:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 31,\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T16:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 32,\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T16:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 33,\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T16:45+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 34,\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T17:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 35,\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-10T17:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 36,\n" +
            "    \"title\": \"Closed Session ISPRS TC V/WG7\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-10T16:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T18:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 37,\n" +
            "    \"title\": \"WELCOME RECEPTION\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Party\",\n" +
            "    \"start\": \"2019-12-10T18:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T20:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 38,\n" +
            "    \"title\": \"REGISTRATION + BREAKFAST\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Registration\",\n" +
            "    \"start\": \"2019-12-11T08:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 39,\n" +
            "    \"title\": \"Opening Speaker\",\n" +
            "    \"title_2\": \"Professor Dr. Kevin McDougall\",\n" +
            "    \"type\": \"Key note\",\n" +
            "    \"start\": \"2019-12-11T09:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 41,\n" +
            "    \"title\": \"Technical Session V\",\n" +
            "    \"title_2\": \"Technical Session VI\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T09:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 42,\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T09:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 43,\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T09:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 44,\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T10:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 45,\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T10:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 46,\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T10:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 47,\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T10:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 48,\n" +
            "    \"title\": \"Poster Session\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Showcase\",\n" +
            "    \"start\": \"2019-12-11T09:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 49,\n" +
            "    \"title\": \"TEA/COFFEE BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Tea\",\n" +
            "    \"start\": \"2019-12-11T11:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 51,\n" +
            "    \"title\": \"Technical Session VII\",\n" +
            "    \"title_2\": \"Technical Session VIII\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T11:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 52,\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T11:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 53,\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T11:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 54,\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T11:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 55,\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T12:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 56,\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T12:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 57,\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"Technical Session\",\n" +
            "    \"start\": \"2019-12-11T12:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 58,\n" +
            "    \"title\": \"LUNCH BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Lunch\",\n" +
            "    \"start\": \"2019-12-11T12:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 60,\n" +
            "    \"title\": \"Introduction to the dignitaries\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Speech\",\n" +
            "    \"start\": \"2019-12-11T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:05+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 61,\n" +
            "    \"title\": \"Technical Report\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Showcase\",\n" +
            "    \"start\": \"2019-12-11T14:05+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:20+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 62,\n" +
            "    \"title\": \"Resolution\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Showcase\",\n" +
            "    \"start\": \"2019-12-11T14:20+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:40+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 63,\n" +
            "    \"title\": \"Guest Remarks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Remarks\",\n" +
            "    \"start\": \"2019-12-11T14:40+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:50+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 64,\n" +
            "    \"title\": \"Vote of Thanks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Remarks\",\n" +
            "    \"start\": \"2019-12-11T14:50+05:45\",\n" +
            "    \"end\": \"2019-12-11T15:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"\": 65,\n" +
            "    \"title\": \"Closing Remarks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"Remarks\",\n" +
            "    \"start\": \"2019-12-11T15:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T15:30+05:45\"\n" +
            "  }\n" +
            "]";
}
