package com.example.android_data_sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataHelp extends SQLiteOpenHelper {
    //构造函数
    //context 上下文
    //name  数据库名称
    //factory  游标工厂 默认填 null
    //version  数据库的版本号
    //创建数据库帮手的步骤
    //先创建一个继承SQLiteOpenHelper类作为数据库帮手
    //实现里面的构造方法和两个函数，其中版本号，数据库名称，已经游标可以内定，可以输入；
    //在mainactivity活动中，实例化datahelp对象，以及调用写入功能
    public DataHelp(Context context) {
        super(context, constants.DATA_NAME, null, constants.VERSION_DATA);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE information(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),phone VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
