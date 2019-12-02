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

            String title2 = agenda.optString("title_2");
            String start = agenda.optString("start");
            String end = agenda.optString("end");

            if (!TextUtils.isEmpty(title2)) {
                block = new Block(title2, type, getOrDefault(typeColorMAP, type),
                        getOrDefault(typeColorMAP, type), isDark, ZonedDateTime.parse(start),
                        ZonedDateTime.parse(end));
                blocks.add(block);
            }


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
            "  {\n" +
            "    \"title\": \"Registration\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"badge\",\n" +
            "    \"start\": \"2019-12-10T08:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Introduction to the dignitaries\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-10T09:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:05+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"National Anthem\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-10T09:05+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:07+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Welcome Speech\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-10T09:07+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Inauguration by Hon'ble Minister, MOLCPA (Chief Guest)\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-10T09:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:17+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Remarks: Guests\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"remark\",\n" +
            "    \"start\": \"2019-12-10T09:17+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:32+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Remarks: Guests\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"remark\",\n" +
            "    \"start\": \"2019-12-10T09:32+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:40+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Remarks: ISPRS Representative\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"remark\",\n" +
            "    \"start\": \"2019-12-10T09:40+05:45\",\n" +
            "    \"end\": \"2019-12-10T09:50+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Remarks: Vice Chancellor, Kathmandu University\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"remark\",\n" +
            "    \"start\": \"2019-12-10T09:50+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Felicitation of Brand Sponsors\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"honour\",\n" +
            "    \"start\": \"2019-12-10T10:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:05+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Inauguration Speech: Hon'ble Minister (Chief Guest)\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-10T10:05+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:20+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Closing Remarks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-10T10:20+05:45\",\n" +
            "    \"end\": \"2019-12-10T10:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"TEA/COFFEE BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"meal\",\n" +
            "    \"start\": \"2019-12-10T10:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T11:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Dr. Sunil Babu Shrestha\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"keynote\",\n" +
            "    \"start\": \"2019-12-10T11:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T11:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Prof. Dr. J.A. Zevenbergen\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"keynote\",\n" +
            "    \"start\": \"2019-12-10T11:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T12:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Dr. Sultan Kocaman\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"keynote\",\n" +
            "    \"start\": \"2019-12-10T12:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T12:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Prof. Ruizhi CHEN, Wuhan University\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"keynote\",\n" +
            "    \"start\": \"2019-12-10T12:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T13:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"LUNCH BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"meal\",\n" +
            "    \"start\": \"2019-12-10T13:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Technical Session I\",\n" +
            "    \"title_2\": \"Technical Session II\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T14:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T14:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T14:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T14:45+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T15:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T15:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T15:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"BIM SESSION\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"bim_session\",\n" +
            "    \"start\": \"2019-12-10T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"TEA/COFFEE BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"meal\",\n" +
            "    \"start\": \"2019-12-10T15:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Technical Session III\",\n" +
            "    \"title_2\": \"Technical Session IV\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T16:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T16:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T16:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T16:30+05:45\",\n" +
            "    \"end\": \"2019-12-10T16:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T16:45+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T17:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-10T17:15+05:45\",\n" +
            "    \"end\": \"2019-12-10T17:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Closed Session ISPRS TC V/WG7\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-10T16:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T18:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"WELCOME RECEPTION\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"after_hours\",\n" +
            "    \"start\": \"2019-12-10T18:00+05:45\",\n" +
            "    \"end\": \"2019-12-10T20:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"REGISTRATION + BREAKFAST\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"badge\",\n" +
            "    \"start\": \"2019-12-11T08:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Opening Speaker\",\n" +
            "    \"title_2\": \"Professor Dr. Kevin McDougall\",\n" +
            "    \"type\": \"keynote\",\n" +
            "    \"start\": \"2019-12-11T09:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Technical Session V\",\n" +
            "    \"title_2\": \"Technical Session VI\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T09:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T09:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T09:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T09:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T10:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T10:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T10:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T10:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T10:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Poster Session\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"showcase\",\n" +
            "    \"start\": \"2019-12-11T09:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"TEA/COFFEE BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"meal\",\n" +
            "    \"start\": \"2019-12-11T11:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Technical Session VII\",\n" +
            "    \"title_2\": \"Technical Session VIII\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T11:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 1\",\n" +
            "    \"title_2\": \"Presentation 1\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T11:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 2\",\n" +
            "    \"title_2\": \"Presentation 2\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T11:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T11:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 3\",\n" +
            "    \"title_2\": \"Presentation 3\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T11:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presenation 4\",\n" +
            "    \"title_2\": \"Presenation 4\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T12:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 5\",\n" +
            "    \"title_2\": \"Presentation 5\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T12:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:30+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Presentation 6\",\n" +
            "    \"title_2\": \"Presentation 6\",\n" +
            "    \"type\": \"session\",\n" +
            "    \"start\": \"2019-12-11T12:30+05:45\",\n" +
            "    \"end\": \"2019-12-11T12:45+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"LUNCH BREAK\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"meal\",\n" +
            "    \"start\": \"2019-12-11T12:45+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:00+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Introduction to the dignitaries\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"speech\",\n" +
            "    \"start\": \"2019-12-11T14:00+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:05+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Technical Report\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"showcase\",\n" +
            "    \"start\": \"2019-12-11T14:05+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:20+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Resolution\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"showcase\",\n" +
            "    \"start\": \"2019-12-11T14:20+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:40+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Guest Remarks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"remark\",\n" +
            "    \"start\": \"2019-12-11T14:40+05:45\",\n" +
            "    \"end\": \"2019-12-11T14:50+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Vote of Thanks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"remark\",\n" +
            "    \"start\": \"2019-12-11T14:50+05:45\",\n" +
            "    \"end\": \"2019-12-11T15:15+05:45\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"title\": \"Closing Remarks\",\n" +
            "    \"title_2\": \"\",\n" +
            "    \"type\": \"remark\",\n" +
            "    \"start\": \"2019-12-11T15:15+05:45\",\n" +
            "    \"end\": \"2019-12-11T15:30+05:45\"\n" +
            "  }\n" +
            "]";
}
