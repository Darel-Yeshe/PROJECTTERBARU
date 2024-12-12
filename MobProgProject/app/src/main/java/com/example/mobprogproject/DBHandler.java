package com.example.mobprogproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_database";
    private static final int DB_VERSION = 2;

    private static final String TABLE_NAME = "UserInfo";
    private static final String ID_COL = "id";
    private static final String EMAIL_COL = "email";
    private static final String PASSWORD_COL = "password";
    private static final String NAME_COL = "name";
    private static final String AGE_COL = "age";
    private static final String WEIGHT_COL = "weight";
    private static final String HEIGHT_COL = "height";
    private static final String GENDER_COL = "gender";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EMAIL_COL + " TEXT UNIQUE, "
                + PASSWORD_COL + " TEXT, "
                + NAME_COL + " TEXT, "
                + AGE_COL + " TEXT, "
                + WEIGHT_COL + " TEXT, "
                + HEIGHT_COL + " TEXT, "
                + GENDER_COL + " TEXT)";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + PASSWORD_COL + " TEXT");
        }
    }

    public void addNewUser(String email, String password, String name, String age, String weight, String height, String gender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL_COL, email);
        values.put(PASSWORD_COL, password);
        values.put(NAME_COL, name);
        values.put(AGE_COL, age);
        values.put(WEIGHT_COL, weight);
        values.put(HEIGHT_COL, height);
        values.put(GENDER_COL, gender);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_COL + "=? AND " + PASSWORD_COL + "=?", new String[]{email, password});
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }

    public Cursor getUserProfile(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + EMAIL_COL + "=?", new String[]{email});
    }

    public void updateUserProfile(String email, String userName, String userAge, String userWeight, String userHeight, String userGender) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME_COL, userName);
        values.put(AGE_COL, userAge);
        values.put(WEIGHT_COL, userWeight);
        values.put(HEIGHT_COL, userHeight);
        values.put(GENDER_COL, userGender);

        db.update(TABLE_NAME, values, EMAIL_COL + "=?", new String[]{email});
        db.close();
    }
}