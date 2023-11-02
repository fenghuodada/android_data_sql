package com.example.android_data_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button revise,delete,inquire,increase;
    DataHelp dataHelp;
    TextView text;
    EditText pwd,user,insertphone,insertname;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件
        user=findViewById(R.id.usernameEdit);
        pwd=findViewById(R.id.pwdEdit);
        text=findViewById(R.id.text);
        revise=findViewById(R.id.revise);
        increase=findViewById(R.id.increase);
        delete=findViewById(R.id.delete);
        inquire=findViewById(R.id.inquire);
        insertphone=findViewById(R.id.textinsertphone);
        insertname=findViewById(R.id.textinsertname);
        increase.setOnClickListener(this);
        delete.setOnClickListener(this);
        inquire.setOnClickListener(this);
        revise.setOnClickListener(this);
        //设置点击事件

        //创建并且实例化数据库帮手对象
        dataHelp=new DataHelp(this);
        //开启数据库帮手写入事务

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        String name,phone;
        SQLiteDatabase db;
        ContentValues values;
        int views = view.getId();
        if (views==R.id.increase) {//添加数据
            //要设置一个对象和两层事务
                //创建两个字符串
                name=user.getText().toString();
                phone=pwd.getText().toString();
                //实例化写入事务
                db=dataHelp.getWritableDatabase();
                //实例化内容值事务
                values=new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                //mysql插入语句,调用插入方法
                db.insert("information",null,values);
                Toast.makeText(this,"插入成功",Toast.LENGTH_SHORT).show();
                //关闭数据库
                db.close();
        }
        if (views==R.id.inquire) {//查询数据
            Cursor cursor;
            //创建数据库对象,因为是查询所以权限是只读的
            db=dataHelp.getReadableDatabase();
            //调用查询函数，创建查询表cursor
            cursor=db.query("information",null,null,null,null,null,null);
            //判断获取的数据是否存在
            if (cursor.getCount() == 0) {
                text.setText("");
                Toast.makeText(this,"没有获取到数据",Toast.LENGTH_SHORT).show();
            }else {
                //把游标移动到文本首行
                cursor.moveToFirst();
                //取出从数据库得到的数据
                text.setText("name:"+cursor.getString(1)+"-----"+"phone"+cursor.getString(2));
            }
            cursor.close();
            db.close();

        }
        if (views==R.id.delete) {//删除数据库
            //创建数据库对象，并且权限是可写的
            db=dataHelp.getWritableDatabase();
            //删除表
            db.delete("information",null,null);
            text.setText("");
            Toast.makeText(this,"删除操作已经完成",Toast.LENGTH_SHORT).show();
            //关闭数据库
            db.close();
        }
        if (views==R.id.revise) {//修改数据库
            //创建一个可读写的数据库帮手对象
            db=dataHelp.getWritableDatabase();
            //创建一个内容值事务,目的就是创建一个笔
            ContentValues contentValues=new ContentValues();
            phone=insertphone.getText().toString();
            //写入值
            contentValues.put("phone",phone);
            //更新表
            db.update("information",contentValues,"name=?",new String[]{insertname.getText().toString()});
            Toast.makeText(this,"修改已经完成",Toast.LENGTH_SHORT).show();
            db.close();

        }

    }
}