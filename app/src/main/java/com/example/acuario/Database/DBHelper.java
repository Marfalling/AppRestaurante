package com.example.acuario.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "acuario.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, username TEXT, password TEXT, phone TEXT, address TEXT, email TEXT)");
        db.execSQL("CREATE TABLE reservaciones(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nombre TEXT NOT NULL, fecha TEXT NOT NULL, hora TEXT NOT NULL, numero_personas INTEGER NOT NULL, notas TEXT, id_user INTEGER NOT NULL, FOREIGN KEY(id_user) REFERENCES users(id))");
        db.execSQL("INSERT INTO users(username, password) VALUES('admin', 'admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS reservaciones");
        onCreate(db);
    }

    public long addUser(String firstName, String lastName, String username, String password, String phone, String address, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("email", email);
        return db.insert("users", null, contentValues);
    }

    public Cursor getUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username = ? AND password = ?", new String[]{username, password});
        return cursor;
    }

    public int updateUser(String username, String firstName, String lastName, String password, String phone, String address, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstName", firstName);
        contentValues.put("lastName", lastName);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        contentValues.put("address", address);
        contentValues.put("email", email);
        return db.update("users", contentValues, "username = ?", new String[]{username});
    }

    public long addReservacion(String nombre, String fecha, String hora, int numero_personas, String notas, int id_user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("fecha", fecha);
        contentValues.put("hora", hora);
        contentValues.put("numero_personas", numero_personas);
        contentValues.put("notas", notas);
        contentValues.put("id_user", id_user);
        return db.insert("reservaciones", null, contentValues);
    }

}