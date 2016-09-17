import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by androidstudio on 17/09/16.
 */
public class DatabaseAdapter extends SQLiteOpenHelper {

    private static  final String TAG = DatabaseAdapter.class.getSimpleName();
    private static  final String DATABASE_NAME = "user.db";
    private static  final int DATABASE_VERSION = 1;
    private static  final String TABLE_USER = "user";
    private static  final String KEY_ID = "id";
    private static  final String KEY_EMAIL="email";
    private static  final String KEY_PASSWORD="password";
    private static  final String KEY_CREATED_AT = "created_at";

    public DatabaseAdapter(Context _context){
        super(_context,DATABASE_NAME,null,DATABASE_VERSION);

    }
  @Override
 public void onCreate(SQLiteDatabase sqlDB){
     String CREATE_USER_TABLE = "CREATE TABLE" +TABLE_USER + "(" + KEY_ID + "INTEGER PRIMARY KEY,"
             +KEY_EMAIL + "TEXT UNIQUE," + KEY_PASSWORD + "TEXT," + KEY_CREATED_AT +"TEXT" + "(";
      sqlDB.execSQL(CREATE_USER_TABLE);


 }

   @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
       db.execSQL("DROP TABLE IF EXIST"+ TABLE_USER);

       onCreate(db);

   }
    public void registerUser(String email, String password, String created_at){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_CREATED_AT, created_at);
        Long id = db.insert(TABLE_USER, null, values);
        db.close();
        Log.d(TAG, "success"+ id);

    }

    public boolean validateUser(String userName, String password){
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM" + TABLE_USER + "WHERE" + KEY_EMAIL+ "="+ userName;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            user.put("email",cursor.getString(1));
            user.put("password",cursor.getString(2));
            user.put("created_at",cursor.getString(3));
        }
        cursor.close();
        db.close();
        Log.d(TAG, "fetching user from sqlite"+ user.toString());
       // Toast.makeText(DatabaseAdapter.this, "password is incorrect", Toast.LENGTH_SHORT).show();
        if(password.equals(user.get(password))){
            Log.d(TAG, "password was validated");
            return true;

        }
        else{
            Log.d(TAG, "Password mismatch");
            return false;
        }


    }


}
