package com.yajatmalhotra.travelmate

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yajatmalhotra.travelmate.databinding.ActivityPdfBinding

class PdfActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPdfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fileUri = Uri.parse(intent.getStringExtra("docUri").toString())
        showPdfFromUri(fileUri)
    }
    private fun showPdfFromUri(uri: Uri?) {
        binding.pdfView.fromUri(uri)
            .defaultPage(0)
            .spacing(10)
            .load()
    }
}