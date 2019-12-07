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

package com.google.samples.apps.iosched.shared.domain.agenda

import com.google.samples.apps.iosched.model.Block
import com.google.samples.apps.iosched.shared.data.agenda.*
import com.google.samples.apps.iosched.shared.data.config.AppConfigDataSource
import com.google.samples.apps.iosched.shared.data.config.RemoteAppConfigDataSource.Companion.AGENDA
import org.json.JSONArray
import org.json.JSONException
import org.threeten.bp.ZonedDateTime
import java.util.*

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

object AgendaParser {
    private val typeColorMAP = HashMap<String, Int?>()
    @Throws(JSONException::class)
    fun getAgenda(appConfigDataSource: AppConfigDataSource): ArrayList<Block> {
        val jsonArray = JSONArray(json)
        val blocks = ArrayList<Block>()
        var block: Block
        for (i in 0 until jsonArray.length()) {
            val agenda = jsonArray.getJSONObject(i)
            val title = agenda.optString("title")
            val type = agenda.optString("type")
            val isDark = (type.equals("keynote", ignoreCase = true) || type.contains("after_hours")
                    || type.contains("speech") || type.contains("honour"))
            val start = agenda.optString("start")
            val end = agenda.optString("end")
            block = Block(title, type, getOrDefault(typeColorMAP, type)!!,
                    getOrDefault(typeColorMAP, type)!!, isDark, ZonedDateTime.parse(start),
                    ZonedDateTime.parse(end))
            blocks.add(block)
        }
        return blocks
    }

    private fun getOrDefault(map: HashMap<String, Int?>, key: String): Int? {
        return if (map.containsKey(key)) {
            map[key]
        } else COLOR_SESSIONS
    }

    private const val json = "[\n" +
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
            "        \"title\": \"Closing Remarks\",\n" +
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
            "        \"start\": \"2019-12-11T09:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T09:45:00+05:45\",\n" +
            "        \"title\": \"Day Two Opening Session\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T09:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T09:10:00+05:45\",\n" +
            "        \"title\": \"Summary Of Day One\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T09:10:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T09:30:00+05:45\",\n" +
            "        \"title\": \"The Future Of Authoritative Geospatial Data In The Big Data World - Trends, Opportunities And Challenges\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T09:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T09:45:00+05:45\",\n" +
            "        \"title\": \"Presentation - Trimble\",\n" +
            "        \"type\": \"speech\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T09:45:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T10:00:00+05:45\",\n" +
            "        \"title\": \"Short Break\",\n" +
            "        \"type\": \"after_hours\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T10:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T11:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 5 (Advanced Geospatial Technologies- Ecology And Geography)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T10:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T11:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 6 (Land Management)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T11:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T12:00:00+05:45\",\n" +
            "        \"title\": \"Lunch\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T12:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T13:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 7 (Remote Sensing And Space Education: Rs For Natural Disaster)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T12:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T13:30:00+05:45\",\n" +
            "        \"title\": \"Techincal Session 8 (Exploring International Collaboration)\",\n" +
            "        \"type\": \"session\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T13:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T14:30:00+05:45\",\n" +
            "        \"title\": \"Lunch\",\n" +
            "        \"type\": \"meal\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T14:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T15:00:00+05:45\",\n" +
            "        \"title\": \"Professional Networking (Informal)\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T15:00:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T16:30:00+05:45\",\n" +
            "        \"title\": \"Closing Ceremony\",\n" +
            "        \"type\": \"badge\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"start\": \"2019-12-11T16:30:00+05:45\",\n" +
            "        \"end\": \"2019-12-11T17:00:00+05:45\",\n" +
            "        \"title\": \"Hi-Tea\",\n" +
            "        \"type\": \"after_hours\"\n" +
            "    }\n" +
            "]"

    init {
        typeColorMAP["session"] = COLOR_SESSIONS
        typeColorMAP["meal"] = COLOR_MEAL
        typeColorMAP["speech"] = COLOR_SPEECH
        typeColorMAP["photo"] = COLOR_PHOTO
        typeColorMAP["badge"] = COLOR_REGISTRATION
        typeColorMAP["after_hours"] = COLOR_AFTER_HOURS
        typeColorMAP["keynote"] = COLOR_KEYNOTE
        typeColorMAP["honour"] = COLOR_HONOUR
    }
}