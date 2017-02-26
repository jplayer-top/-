package com.oblivion.myapplication;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import static com.oblivion.myapplication.ContactsInfo.list;

/**
 * github : https://github.com/oblivion0001/
 * Blog : http://blog.csdn.net/qq_16666847
 * Created by oblivion on 2017/2/24.
 */
public class TongxunuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv);
        if (Build.VERSION.SDK_INT > 22) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        1);
            } else {
                load();

            }
        } else {
            load();
        }


    }

    private void load() {
        try {
            Uri contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor = this.getContentResolver().query(contactUri,
                    new String[]{"display_name", "sort_key", "contact_id", "data1"},
                    null, null, "sort_key");
            String contactName;
            String contactNumber;
            String contactSortKey;
            int contactId;
            while (cursor.moveToNext()) {
                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contactId = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
                contactSortKey = getSortkey(cursor.getString(1));
                ContactsInfo contactsInfo = new ContactsInfo(contactName, contactNumber, contactSortKey, contactId);
                if (contactName != null)
                    list.add(contactsInfo);
            }
            cursor.close();//使用完后一定要将cursor关闭，不然会造成内存泄露等问题

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            for (ContactsInfo contactsInfo : ContactsInfo.list) {
                System.out.println(contactsInfo.name+contactsInfo.number);
            }
        }
    }

    private static String getSortkey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        } else
            return "#";   //获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
    }
}
