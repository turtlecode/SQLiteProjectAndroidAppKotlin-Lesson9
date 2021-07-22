package com.turtlecode.sqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.turtlecode.sqlite.R
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    val text = ArrayList<String>()
    var string = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //try catch
        try {
            var  database = this.openOrCreateDatabase("Products" ,Context.MODE_PRIVATE, null)
            database.execSQL("CREATE TABLE IF NOT EXISTS Pro(id INTEGER PRIMARY KEY, name VARCHAR, price INT)")
            database.execSQL("DELETE FROM Pro")
            database.execSQL("INSERT INTO Pro (name,price) VALUES ('Tshirt',50)")
            database.execSQL("INSERT INTO Pro (name,price) VALUES ('Shoe',150)")
            database.execSQL("INSERT INTO Pro (name,price) VALUES ('Coat',250)")
            database.execSQL("INSERT INTO Pro (name,price) VALUES ('Jacket',450)")
            //val cursor =veritabani.rawQuery("SELECT * FROM urunler WHERE id = 3",null)
            val cursor = database.rawQuery("SELECT * FROM Pro",null)
            val idColumnIndex = cursor.getColumnIndex("id")
            val nameColumnIndex = cursor.getColumnIndex("name")
            val priceColumnIndex = cursor.getColumnIndex("price")

            while (cursor.moveToNext()) {
                text.add(cursor.getInt(idColumnIndex).toString() + " ")
                text.add(cursor.getString(nameColumnIndex) + " ")
                text.add(cursor.getInt(priceColumnIndex).toString() + "\n")
            }
            cursor.close()
            for (x in 0..text.size) {
                string = string + text.get(x)
            }

        } catch (e:Exception) {
            e.printStackTrace()
        }
        textView.text = string
    }

}