package com.example.commueoflove.testActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.commueoflove.Connector.DBConnector;
import com.example.commueoflove.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMySqlActivity extends AppCompatActivity {

    //数据库连接类
    private static Connection con = null;
    private static PreparedStatement stmt = null;
    private static final String TAG = "TestMySqlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_my_sql);
        Button btu = findViewById(R.id.button);
        btu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        MySQLConnections.link();
                        con = DBConnector.getConnection();
                        if (con != null) {
                            Log.i(TAG, "onClick: 连接成功！");
                            String sql = "select * from donation";
                            try {
                                Statement statement = con.createStatement();
                                ResultSet rSet = statement.executeQuery(sql);
                                while (rSet.next()) {
                                    Log.d(TAG, rSet.getString("title"));
                                }
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        } else {
                            Log.i(TAG, "onClick: 连接失败！");
                        }
                        try {
                            con.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }).start();

            }
        });
        
    }
}