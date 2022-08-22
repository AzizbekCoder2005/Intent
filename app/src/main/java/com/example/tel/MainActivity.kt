package com.example.tel

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBingding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bingding = ActivityMainBingding.inflate(layoutInflater)
        setContentView(bingding.root)

        initViews()
    }

    private fun initViews() {
        binding.btnSend.setOnClickListener {
            val email = bingding.etEmail.text.toString().trim()
            val title = bingding.etTitle.text.toString().trim()
            val dest = bingding.etDescription.text.toString().trim()
            when {
                email.isEmpty() -> {
                    binding.etEmailLayout.error = "Enter email!"
                }
                title.isEmpty() -> {
                    binding.etTitleLayout.error = "Enter title!"
                }
                dest.isEmpty() -> {
                    binding.edDesLayout.error = "Enter description!"
                }
                else -> {
                    sendEmail(email, title, dest)
                }
            }
        }
    }

    private fun sendEmail(email:String, title: String, dest: String){



        val intent = Intent(Intent.ACTION_SEND).apply {
            data = Uri.parse("mailto:")
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, email)
            putExtra(Intent.EXTRA_SUBJECT, title)
            putExtra(Intent.EXTRA_TEXT, dest)
        }
        true{
            startActivity(Intent.createChooser(intent,"Share"))
        }catch (e:Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }

}