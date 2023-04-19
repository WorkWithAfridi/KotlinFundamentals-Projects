package com.example.kotlinprojectonetodoist

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    lateinit var todoName : EditText
    lateinit var addTodoButton : Button
    lateinit var todoList : ListView

    var myTodoList = ArrayList<String>()

    var fileHelper = FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoName = findViewById(R.id.todoNameET)
        addTodoButton = findViewById(R.id.addTodoBTN)
        todoList = findViewById(R.id.todoList)

        myTodoList = fileHelper.readData(this)

        var arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            myTodoList,
        )

        todoList.adapter = arrayAdapter

        addTodoButton.setOnClickListener {
            if(todoName.text.isNotEmpty()){
                myTodoList.add(todoName.text.toString())
                todoName.setText("")
                fileHelper.writeData(myTodoList, applicationContext)
                arrayAdapter.notifyDataSetChanged()
            }
        }

        todoList.setOnItemClickListener { parent, view, position, id ->
            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete item")
            alert.setMessage("Are you sure you want to delete this item?")
            alert.setCancelable(false)
            alert.setNegativeButton(
                "NO",
                DialogInterface.OnClickListener { dialog, which ->
                dialog.cancel()
            } )
            alert.setPositiveButton(
                "YES",
                DialogInterface.OnClickListener { dialog, which ->
                    myTodoList.removeAt(position)
                    arrayAdapter.notifyDataSetChanged()
                    fileHelper.writeData(myTodoList, applicationContext)
                }
            )
            alert.create().show()
        }
    }
}