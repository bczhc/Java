package pers.zhc.u.groovy

import org.jdom2.Element
import org.jdom2.input.SAXBuilder
import pers.zhc.tools.utils.MySQLite3

import java.util.regex.Pattern

class Demo0 {
    static getChildren(Element element, def name) {
        def children = element.children
        def r = new LinkedList<Element>()
        List<Integer> indexes = new LinkedList<>()
        for (i in 0..<children.size()) {
            if (children.get(i).name == name) r.add(children.get(i))
        }
        return r
    }

    static void main(String[] args) {
        def db = MySQLite3.open("./students_list")
        db.exec("DROP TABLE IF EXISTS student")
        db.exec("CREATE TABLE IF NOT EXISTS student (\n    class_code INTEGER,\n    name TEXT NOT NULL,\n    examinee_number INTEGER PRIMARY KEY\n)")
        db.exec("BEGIN TRANSACTION")
        def file = new File("/home/zhc/张家港市乐余高级中学高二学生名单.xml")
        def builder = new SAXBuilder()
        def root = builder.build(file).getRootElement()
        def children = getChildren(root, "Worksheet")
        children.each { def child ->
            def tables = getChildren(child, "Table")
            tables.forEach({ table ->
                def rows = getChildren(table, "Row")
                for (i in 1..<rows.size()) {
                    def row = rows.get(i)
                    def cells = getChildren(row, "Cell")

                    def matcher = Pattern.compile("(?<=高二年级).+(?=班)").matcher(getChildren(cells[2], "Data")[0].getText())
                    matcher.find()
                    int classCode = 200 + (matcher.group(0) as int)

                    def name = getChildren(cells[3], "Data")[0].getText()
                    def examineeNumber = getChildren(cells[5], "Data")[0].getText() as int
                    db.exec(String.format("INSERT INTO student VALUES(%d,'%s',%d)", classCode, name, examineeNumber))
                }
            })
        }
        db.exec("COMMIT")
    }
}