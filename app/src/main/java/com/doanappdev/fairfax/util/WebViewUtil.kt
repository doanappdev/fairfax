package com.doanappdev.fairfax.util

import android.content.Context
import android.content.Intent
import android.net.Uri

class WebViewUtil {
    companion object {
        fun openExternalUrl(context: Context, _url: String) {
            var url = _url
            if (!url.contains("http")) url = "http://$_url"
            val uri = Uri.parse(url)
            val intent = Intent("android.intent.action.VIEW", uri)
            context.startActivity(intent)
        }
    }
}