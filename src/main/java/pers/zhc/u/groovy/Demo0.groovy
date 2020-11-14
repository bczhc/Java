package pers.zhc.u.groovy

import org.json.JSONArray
import org.json.JSONObject
import pers.zhc.tools.utils.MySQLite3
import pers.zhc.u.common.ReadIS

import java.nio.charset.StandardCharsets

class Demo0 {
    static void main(String[] args) {
        def db = MySQLite3.open("./online_class_questions")
        db.exec("BEGIN TRANSACTION ")
        db.exec("DROP TABLE IF EXISTS question")
        db.exec("CREATE TABLE IF NOT EXISTS question (\n    subject_name TEXT NOT NULL,\n    question_owner_name TEXT NOT NULL,\n    question_owner_id INTEGER,\n    question_message TEXT NOT NULL,\n    second_timestamp INTEGER\n)")
        def file = new File("/home/zhc/questions.json")
        def is = new FileInputStream(file)
        def read = ReadIS.readToString(is, StandardCharsets.UTF_8)
        is.close()
        def jsonArray = new JSONArray(read)
        jsonArray.forEach({ JSONObject jsonObject ->
            def subjectName = jsonObject.getString("subjectName")
            def courses = jsonObject.getJSONArray("courses")
            courses.forEach({ JSONObject course ->
                def questions = course.getJSONObject("questions").getJSONArray("questions")
                questions.forEach({ JSONObject question ->
                    def secondTimestamp = question.getInt("secondTimestamp")
                    def questionText = question.getString("question")
                    def questionOwnerId = question.getInt("questionOwnerId")
                    def questionOwner = question.getString("questionOwner")
                    db.exec(String.format("INSERT INTO question VALUES('%s','%s',%d,'%s',%d)"
                            , subjectName, questionOwner, questionOwnerId, questionText.replace("'", "''"), secondTimestamp))
                })
            })
        })
        db.exec("COMMIT ")
    }
}