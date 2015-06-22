package ch.swissonid.design_lib_sample.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramakrishna.s on 6/22/2015.
 */
public class DatabaseClass extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION;

    static {
        DATABASE_VERSION = 1;
    }

    // Database Name
    private static final String DATABASE_NAME = "Mahabharatham";

    // Contacts table name
    private static final String TABLE_PARVAMS = "parvams";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_PARVAM_NAME = "parvam_name";
    private static final String KEY_PARVAM_CHAPTER = "parvam_chapter";
    private static final String KEY_PARVAM_CHAPTER_ID = "parvam_chapter_id";

    public DatabaseClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_PARVAMS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PARVAM_NAME + " TEXT,"
                + KEY_PARVAM_CHAPTER + " TEXT,"
                + KEY_PARVAM_CHAPTER_ID + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARVAMS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new chapter
    public void addParvams(ParvamObj parvamObj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PARVAM_NAME, parvamObj.getParvamName());
        values.put(KEY_PARVAM_CHAPTER, parvamObj.getParvamChapter());
        values.put(KEY_PARVAM_CHAPTER_ID, parvamObj.getPravamChapterID());
        // Inserting Row
        db.insert(TABLE_PARVAMS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
//    Contact getContact(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
//                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
//                new String[]{String.valueOf(id)}, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        // return contact
//        return contact;
//    }
//
    //Get All Parvams
    public ArrayList<ParvamObj> getAllParvams() {
        ArrayList<ParvamObj> parvamsList = new ArrayList<ParvamObj>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PARVAMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ParvamObj parvam = new ParvamObj();
                parvam.set_id(Integer.parseInt(cursor.getString(0)));
                parvam.setParvamName(cursor.getString(1));
                parvam.setParvamChapter(cursor.getString(2));
                parvam.setPravamChapterID(cursor.getInt(3));
                // Adding contact to list
                parvamsList.add(parvam);
            } while (cursor.moveToNext());
        }

        return parvamsList;
    }

//
//    // Updating single contact
//    public int updateContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_NAME, contact.getName());
//        values.put(KEY_PH_NO, contact.getPhoneNumber());
//
//        // updating row
//        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(contact.getID()) });
//    }
//
//    // Deleting single contact
//    public void deleteContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                new String[]{String.valueOf(contact.getID())});
//        db.close();
//    }
//
//
//    // Getting contacts Count
//    public int getContactsCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }


}
