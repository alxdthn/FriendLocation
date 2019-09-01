package com.nalexand.friendlocation.network

import android.util.Log
import com.nalexand.friendlocation.MainActivity
import com.nalexand.friendlocation.data.AppDatabase
import com.nalexand.friendlocation.data.Token
import kotlinx.coroutines.delay
import java.util.*

fun needNewToken(token : Token?) : Boolean {
    if (token == null)
        return true
    val expires_in = token.createdAt - (Calendar.getInstance().timeInMillis - 7200000) / 1000
    Log.d("bestTAG", "current time:  ${Calendar.getInstance().timeInMillis}")
    Log.d("bestTAG", "token created: ${token.createdAt}")
    Log.d("bestTAG", "expires in:    $expires_in")
    if (expires_in < 60)
        return true
    return false
}

suspend fun getToken(activity: MainActivity, db : AppDatabase) : Token? {
    var token : Token? = db.make().getToken()

    if (token == null || needNewToken(token)) {
        Log.d("bestTAG", "trying get new token")
        val response = activity.service.getToken(activity.requestBody).body()
        if (response != null) {
            token = Token(1,
                response.access_token,
                response.token_type,
                response.created_at)
            db.make().insertToken(token)
            delay(500)
            Log.d("bestTAG", "new token created!")
        }
    }
    return token
}git add .
        git commit -m "";""
