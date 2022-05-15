package com.example.hrms

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.gyde.mylibrary.screens.GydeHomeActivity
import com.gyde.mylibrary.utils.Util

class MainActivity : AppCompatActivity() {
    private var yellow: CardView? = null
    private var red: CardView? = null
    private var blue: CardView? = null
    private var gyde: CardView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDeepLinkingData()
        setUpButtons()
    }

    private fun getDeepLinkingData() {
        try {
            val uri: Uri? = intent.data
            if (uri != null) {
                val parameters = uri.pathSegments
                val flowIdParam = parameters[parameters.size - 2]
                val withVoiceOverParam = parameters[parameters.size - 1]

                val bundle = Bundle()
                bundle.putString(Util.keyFlowId, flowIdParam)
                bundle.putString(Util.keyVoiceOver, withVoiceOverParam)

                startActivity(
                    Intent(this@MainActivity, GydeHomeActivity::class.java).putExtras(
                        bundle
                    )
                )
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        yellow?.setCardBackgroundColor(resources.getColor(R.color.yellow))
        red?.setCardBackgroundColor(resources.getColor(R.color.red))
        blue?.setCardBackgroundColor(resources.getColor(R.color.blue))
    }

    private fun setUpButtons() {
        yellow = findViewById(R.id.yellow)
        red = findViewById(R.id.red)
        blue = findViewById(R.id.blue)
        gyde = findViewById(R.id.gyde)
        yellow?.setOnClickListener {
            yellow?.setCardBackgroundColor(resources.getColor(R.color.yellowDark))
            startActivity(Intent(this@MainActivity, NewCandidateActivity::class.java))
        }
        red?.setOnClickListener {
            red?.setCardBackgroundColor(resources.getColor(R.color.redDark))
            startActivity(Intent(this@MainActivity, AllApplicationsActivity::class.java))
        }
        blue?.setOnClickListener {
            blue?.setCardBackgroundColor(resources.getColor(R.color.blueDark))
            startActivity(Intent(this@MainActivity, SelectedCandidatesActivity::class.java))
        }
        gyde?.setOnClickListener {
            gyde?.setCardBackgroundColor(resources.getColor(R.color.redDark))
            startActivity(Intent(this@MainActivity, GydeHomeActivity::class.java))
        }
    }
}
