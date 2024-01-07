package com.example.todo.tasks

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
<<<<<<< HEAD
import android.util.Log

interface DatabaseHandler {
    fun addNewTasks(TasksName: String?, TasksDuration: String?)
    fun readTasks(): ArrayList<TasksModel>?
    fun readTasksNotSelected(): ArrayList<TasksModel>?
    fun readTasksSelected(): ArrayList<TasksModel>?
=======


interface DatabaseHandler {
    fun addNewTasks(TasksName: String?, TasksDuration: String?)
    fun readTaskss(): ArrayList<TasksModel>?
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
    fun updateTasks(TasksCod: Int, TasksName: String?, TasksDuration: String?, TasksIsSelected: Int)
}

class DBHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION),
    DatabaseHandler {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE $TABLE_NAME ("
                + "$ID_COL INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$NAME_COL TEXT,"
                + "$DURATION_COL TEXT,"
                + "$SELECTED_COL INTEGER)")

        db.execSQL(query)
    }

    override fun addNewTasks(TasksName: String?, TasksDuration: String?) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COL, TasksName)
        values.put(DURATION_COL, TasksDuration)
        values.put(SELECTED_COL, 0)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

<<<<<<< HEAD
    override fun readTasksNotSelected(): ArrayList<TasksModel>? {
        val db = this.readableDatabase
        val selectedColValue = 0
        val query = "SELECT * FROM $TABLE_NAME WHERE selected = ?"
        val cursorTasks: Cursor = db.rawQuery(query, arrayOf(selectedColValue.toString()))
        val TasksModelArrayList: ArrayList<TasksModel> = ArrayList()

        if (cursorTasks.moveToFirst()) {
            do {
                TasksModelArrayList.add(
                    TasksModel(
                        cursorTasks.getInt(0),
                        cursorTasks.getString(1),
                        cursorTasks.getString(2),
                        cursorTasks.getInt(3)
                    )
                )
            } while (cursorTasks.moveToNext())
        }
        cursorTasks.close()
        return TasksModelArrayList
    }
    override fun readTasksSelected(): ArrayList<TasksModel>? {
        val db = this.readableDatabase
        val selectedColValue = 1
        val query = "SELECT * FROM $TABLE_NAME WHERE selected = ?"
        val cursorTasks: Cursor = db.rawQuery(query, arrayOf(selectedColValue.toString()))
        val TasksModelArrayList: ArrayList<TasksModel> = ArrayList()

        if (cursorTasks.moveToFirst()) {
            do {
                TasksModelArrayList.add(
                    TasksModel(
                        cursorTasks.getInt(0),
                        cursorTasks.getString(1),
                        cursorTasks.getString(2),
                        cursorTasks.getInt(3)
                    )
                )
            } while (cursorTasks.moveToNext())
        }
        cursorTasks.close()
        return TasksModelArrayList
    }
    override fun readTasks(): ArrayList<TasksModel>? {
        val db = this.readableDatabase
        val cursorTasks: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val TasksModelArrayList: ArrayList<TasksModel> = ArrayList()

        if (cursorTasks.moveToFirst()) {
            do {
                TasksModelArrayList.add(
                    TasksModel(
                        cursorTasks.getInt(0),
                        cursorTasks.getString(1),
                        cursorTasks.getString(2),
                        cursorTasks.getInt(3)
                    )
                )
            } while (cursorTasks.moveToNext())
        }
        cursorTasks.close()

        return TasksModelArrayList
    }
    override fun updateTasks(
        TasksCod: Int,
        TasksName: String?,
        TasksDuration: String?,
        TasksIsSelected: Int
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        val whereArgs = arrayOf(TasksCod.toString())
        values.put(NAME_COL, TasksName)
        values.put(DURATION_COL, TasksDuration)
        values.put(SELECTED_COL, TasksIsSelected)
        db.update(TABLE_NAME, values, "id = ?", whereArgs)
=======
    override fun readTaskss(): ArrayList<TasksModel>? {
        val db = this.readableDatabase
        val cursorTaskss: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val TasksModelArrayList: ArrayList<TasksModel> = ArrayList()

        if (cursorTaskss.moveToFirst()) {
            do {
                TasksModelArrayList.add(
                    TasksModel(
                        cursorTaskss.getInt(0),
                        cursorTaskss.getString(1),
                        cursorTaskss.getString(2),
                        cursorTaskss.getInt(3)
                    )
                )
            } while (cursorTaskss.moveToNext())
        }
        cursorTaskss.close()
        return TasksModelArrayList
    }

    override fun updateTasks(TasksCod: Int, TasksName: String?, TasksDuration: String?, TasksIsSelected: Int) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME_COL, TasksName)
        values.put(DURATION_COL, TasksDuration)
        values.put(SELECTED_COL, TasksIsSelected)
        db.update(TABLE_NAME, values, "id = ?", arrayOf(TasksCod.toString()))
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
        db.close()
    }

    companion object {
        private const val DB_NAME = "Tasksdb"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "myTaskss"
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val DURATION_COL = "duration"
        private const val SELECTED_COL = "selected"
    }
}
