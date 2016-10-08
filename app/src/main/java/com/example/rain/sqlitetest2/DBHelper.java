package com.example.rain.sqlitetest2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 2016/10/8.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VESION = 1;
    //database name
    private static final String DATABASE_NAME = "Role";
    //table name
    private static final String TABLE_NAME = "RoleDetail";
    //colum name
    private static final String KEY_NAME = "name";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_ID = "id";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VESION);
    }

    //Create Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ROLE_DETAIL_TABLE = "CREATE TABLE " + TABLE_NAME
                + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_LEVEL + " TEXT " + ")";

        db.execSQL(CREATE_ROLE_DETAIL_TABLE);
    }

    //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop old table if exist
        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        //create table again
        onCreate(db);
    }

    //insert
    void addRole(Role newRole) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //contentValues.put(KEY_ID, newRole.getId());
        contentValues.put(KEY_NAME, newRole.getName());
        contentValues.put(KEY_LEVEL, newRole.getLevel());

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }

    //update
    public boolean updateRoleDetail(int updId, String updName, int updLevel) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        //contentValues.put(KEY_ID, updId);
        contentValues.put(KEY_NAME, updName);
        contentValues.put(KEY_LEVEL, updLevel);

        return db.update(TABLE_NAME, contentValues, KEY_ID + "=" + updId, null) > 0;
        //no close??
    }

    //delete
    public boolean deleteRole(int delId) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, KEY_ID + "=" + delId, null) > 0;
        //no close?
    }

    //getAllRole
    public List<Role> getAllRole() {

        List<Role> roleList = new ArrayList<Role>();
        //select *
        SQLiteDatabase db = this.getWritableDatabase();
        //no getReadableDatabase()???
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Role role = new Role();
                role.setId(Integer.parseInt(cursor.getString(0)));
                role.setName(cursor.getString(1));
                role.setLevel(Integer.parseInt(cursor.getString(2)));

                roleList.add(role);

            }while(cursor.moveToNext());
        }

        return roleList;
    }




}
