package com.salesdemo.sales;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, "SalesDemo", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String mSqlAlert = "CREATE TABLE IF NOT EXISTS `tblRegister` (`ID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE ,`Name` VARCHAR, `Email` VARCHAR, `Password` VARCHAR, `Phone` VARCHAR,`IsLogin` VARCHAR,`Date` VARCHAR, `Field1` VARCHAR,`Field2` VARCHAR);";
        try {
            db.execSQL(mSqlAlert);
        } catch (Exception e) {
            System.out.println("Error in DatabaseCreate..");
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int Insert_RegisterData(String mName,String email,String password,String phoneno)
    {
        int IsInserted = -1 ;
        SQLiteDatabase db = this.getWritableDatabase();
        try
        {
            ContentValues insertValues = new ContentValues();
            insertValues.put("Name", mName);
            insertValues.put("Email", email);
            insertValues.put("Password", password);
            insertValues.put("Phone", phoneno);
            insertValues.put("IsLogin", "");
            insertValues.put("Date", "");
            insertValues.put("Field1", "");
            insertValues.put("Field2", "");
            IsInserted = (int) db.insert("tblRegister", null, insertValues);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
            db.close();
        }
        return  IsInserted;
    }

    public boolean CheckForUser(String email,String pass)
    {
        Cursor cursor = null;
        boolean IsLogin = false;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String selectQuery = "SELECT * FROM tblRegister where Email = '"+email+"' and Password = '"+pass+"'";
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                IsLogin = true;
            } else{
                IsLogin = false;
            }
        }
        catch (Exception e){
            IsLogin = false;
        }
        finally {
            if(cursor!=null)
                cursor.close();
            db.close();
        }
        return IsLogin;
    }

    public boolean CheckForUserExsist(String email)
    {
        Cursor cursor = null;
        boolean IsLogin = false;
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            String selectQuery = "SELECT * FROM tblRegister where Email = '"+email+"'";
            cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                IsLogin = true;
            } else{
                IsLogin = false;
            }
        }
        catch (Exception e){
            IsLogin = false;
        }
        finally {
            if(cursor!=null)
                cursor.close();
            db.close();
        }
        return IsLogin;
    }
}