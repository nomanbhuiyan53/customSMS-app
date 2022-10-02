package com.softbard.easysms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;

public class AlldataView extends AppCompatActivity {
    private ArrayList<UserModel> userModalArrayList;
    private CustomAdapter Adapter;
    private RecyclerView recyclerView;
    private ProgressBar loadingPB;
    AssetManager assetManager;
    // array list
    List<String> id,name,mobile,os,mi,ov,prod;

    Workbook workbook;
    AsyncHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alldata_view);

        recyclerView = findViewById(R.id.recyclerview);
        userModalArrayList = new ArrayList<>();
        //String url = "https://github.com/nomanbhuiyan53/sms/blob/main/cmData.xls?raw=true";
        // initializing our views.
        id = new ArrayList<>();
        name = new ArrayList<>();
        mobile = new ArrayList<>();
        os=new ArrayList<>();
        mi=new ArrayList<>();
        ov=new ArrayList<>();
        prod =new ArrayList<>();
        assetManager = getAssets();
        try {
            InputStream input = assetManager.open("data.xls");
            WorkbookSettings ws = new WorkbookSettings();
            ws.setGCDisabled(true);
            if (input != null){
                try {
                    workbook = workbook.getWorkbook(input);
                    Sheet sheet = workbook.getSheet(0);
                    for (int i= 0 ; i< sheet.getRows(); i++){
                        Cell[] row = sheet.getRow(i);
                        id.add(row[0].getContents());
                        name.add(row[1].getContents());
                        mobile.add(row[2].getContents());
                        prod.add(row[3].getContents());
                        os.add(row[4].getContents());
                        mi.add(row[5].getContents());
                        ov.add(row[6].getContents());

                    }
                    showData();
                    Log.d("TAG", "onSuccess :"+ id);
                }catch (IOException e) {
                    e.printStackTrace();
                }catch (BiffException e){
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showData() {
        Adapter = new CustomAdapter(AlldataView.this,id,name,mobile,prod,os,mi,ov);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Adapter);

    }

}