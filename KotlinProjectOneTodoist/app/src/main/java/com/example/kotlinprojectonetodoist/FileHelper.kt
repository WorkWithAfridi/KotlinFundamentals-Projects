package com.example.kotlinprojectonetodoist

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {
    var FILENAME = "listinfo.dat"

    fun writeData(
        myTodoList : ArrayList<String>,
        context : Context,
    ){
        val fos : FileOutputStream = context.openFileOutput(
            FILENAME,
            Context.MODE_PRIVATE,
        )
        var oas = ObjectOutputStream(
            fos,
        )
        oas.writeObject(
            myTodoList
        )
        oas.close()
    }

    fun readData(
        context : Context
    ) : ArrayList<String>{
        var myTodolist = ArrayList<String>()
        try{
            val fis : FileInputStream = context.openFileInput(FILENAME)
            val ois = ObjectInputStream(fis)
            myTodolist = ois.readObject() as ArrayList<String>
        }catch (error : FileNotFoundException){
            myTodolist = ArrayList<String>()
        }

        return myTodolist
    }
}