package com.passbase.dev

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.passbase.passbase_sdk.PassbaseSDK
import com.passbase.passbase_sdk.PassbaseSDKListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPassbase()
    }

    private fun initPassbase() {

        val passbase = PassbaseSDK(this@MainActivity).apply {
            initialize("YOUR_API_KEY")
        }

        passbase.callback(object : PassbaseSDKListener {
            override fun onStart() {
                showMessage("Passbase started")
            }

            override fun onFinish(identityAccessKey: String?) {
                showMessage("Passbase finished with key $identityAccessKey")
            }

            override fun onError(errorCode: String) {
                showMessage("Passbase has error $errorCode")
            }
        })

        findViewById<View>(R.id.main_passbase).apply {
            setOnClickListener {
                passbase.startVerification()
            }
        }

    }

    private fun showMessage(p: String) {

        Toast.makeText(this, p, Toast.LENGTH_SHORT).show()
    }
}


