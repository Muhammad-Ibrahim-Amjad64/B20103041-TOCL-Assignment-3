package com.example.contactsfetcher

import android.Manifest
//import android.content.pm.PackageManager
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
import android.Manifest.permission.READ_CONTACTS
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_contact_list)
        private val contacts = mutableListOf<Contact>()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        val adapter = ContactAdapter(contacts)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 0)
        } else {
            readContacts()
            adapter.notifyDataSetChanged() // Notify the adapter that data has changed
        }
    }


    private fun readContacts() {
        val contentResolver = contentResolver
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, null)

        if (cursor?.count ?: 0 > 0) {
            while (cursor.moveToNext()) {
                val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contacts.add(Contact(contactName, contactNumber))
            }
        }
        cursor?.close()
    }
}
    fun getContactPressed(v: View) {

    }
//
//    private fun getContacts(){
////        if(ContextCompat.checkSelfPermission(context:this,Manifest.permission.READ_CONTACTS) != packageManager.PERMISSION+GRANTED){
////           ActivityCompat.requestPermissions(activity:this,new String[]{Manifest.permission.READ_CONTACTS}, requestCode:0);
////        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 0)
//        }
//
////        ContentResolver contentResolver = getContentResolver();
////        Uri uri =ContactsContract.CommomDataKinds.Phone.content_URI;
////        Cursor cursor = contentResolver.query(uri, null,null,null,null);
//        val contentResolver: ContentResolver = getContentResolver()
//        val uri: Uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
//        val cursor: Cursor? = contentResolver.query(uri, null, null, null, null)
////        Log.i("CONTACT_PROVIDER", "Total no. of contacts : "+(cursor.getCount()));
////        if (cursor.getCount()>0){
////            while(cursor.moveToNext()){
////                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
////                String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
////                Log.i("CONTACT_PROVIDER" , "Contact Name :: "+ contactName + "Phone no."+ contactNumber)
////
////            }
////        }
//
//        Log.i("CONTACT_PROVIDER", "Total no. of contacts : " + cursor?.count)
//        if (cursor?.count ?: 0 > 0) {
//            while (cursor?.moveToNext() == true ) {
//                val displayNameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
//                val phoneNumberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
//                if (displayNameIndex >= 0) {
//                    val contactName = cursor.getString(displayNameIndex)
//                    Log.i("CONTACT_PROVIDER", "Contact Name :: $contactName" )
//                }
//
//                if (phoneNumberIndex >= 0) {
//                    val contactNumber = cursor.getString(phoneNumberIndex)
//                    Log.i("CONTACT_PROVIDER",   " Phone no. $contactNumber" )
//
//                }
//                }
//
//
//                }

//                val contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
//                val contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))

//        }

//    }
