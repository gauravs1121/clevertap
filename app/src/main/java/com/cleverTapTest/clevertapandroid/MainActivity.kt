package com.cleverTapTest.clevertapandroid

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clevertap.android.sdk.CleverTapAPI
import java.util.*
import kotlin.collections.HashMap


class MainActivity : AppCompatActivity() {
     private lateinit var btnPush: Button
    private lateinit var btnSaveUser: Button

    private lateinit var etEmail: EditText
    private lateinit var etName: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPush = findViewById(R.id.btn_push_event)
        etEmail = findViewById(R.id.et_email)
        etName = findViewById(R.id.et_name)
        btnSaveUser = findViewById(R.id.save_profile)
        val cleverTapDefaultInstance: CleverTapAPI? = null
        CleverTapAPI.getDefaultInstance(applicationContext)

        btnPush.setOnClickListener {
            val prodViewedAction = HashMap<String, Any>()
            prodViewedAction["Product Name"] = "CleverTap"
            prodViewedAction["Product Image"] = "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg"
            prodViewedAction["Product ID"] = 1

            cleverTapDefaultInstance?.pushEvent("Product viewed", prodViewedAction)
        }


        btnSaveUser.setOnClickListener {
            if (etName.text.toString().isNotEmpty() &&
                ( etEmail.text.toString().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(etEmail.text.toString()).matches())
            ) {
                val profileUpdate = HashMap<String, Any>()
                profileUpdate["Name"] = etName.text.toString() // String
                profileUpdate["Email"] = etEmail.text.toString() // Email address of the user


                cleverTapDefaultInstance?.pushProfile(profileUpdate)
            }
            Toast.makeText(this, getString(R.string.toast_error), Toast.LENGTH_SHORT).show()
        }



    }

// AAAAm-6uonM:APA91bHARugDgrUeHeZZOayyIr7lVE2yR5icKasPNkaAGEerIXWg-b0Sh94-
// Q_De51ly8IKRHC62HQbdV-ZXQUPD2kHCSPXmTZnrUvkjTj978d2R9yBOqvbnjf73tx1bwk06urWFuGcL

// AAAAm-6uonM:APA91bHARugDgrUeHeZZOayyIr7lVE2yR5icKasPNkaAGEerIXWg-b0Sh94-
// Q_De51ly8IKRHC62HQbdV-ZXQUPD2kHCSPXmTZnrUvkjTj978d2R9yBOqvbnjf73tx1bwk06urWFuGcL

}