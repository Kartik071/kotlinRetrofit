package com.example.kotlinretrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding!!.recyclerView.layoutManager = LinearLayoutManager(this)
        retroFit()
    }

    private fun retroFit() {

        val service: GetDataService = ClientInstance.getRetrofitInstance().create(
            GetDataService::class.java
        )
        val call: Call<List<RetroUser?>?>? = service.allUser()
        call!!.enqueue(object : Callback<List<RetroUser?>?> {

            override fun onResponse(
                call: Call<List<RetroUser?>?>, response: Response<List<RetroUser?>?>
            ) {
                generateDataList(response.body()!!)
            }

            override fun onFailure(call: Call<List<RetroUser?>?>, t: Throwable) {
                // Toast.makeText(this@MainActivity, "Something Went Wrong", Toast.LENGTH_LONG).show()
                if (t is SocketTimeoutException) {
                    Toast.makeText(this@MainActivity, "Connection Timeout", Toast.LENGTH_LONG)
                        .show()
                } else if (t is IOException) Toast.makeText(
                    this@MainActivity,
                    "Timeout",
                    Toast.LENGTH_SHORT
                ).show()
                else {
                    if (call.isCanceled) Toast.makeText(
                        this@MainActivity, "Call was cancelled forcefully", Toast.LENGTH_SHORT
                    ).show()
                    else Toast.makeText(
                        this@MainActivity,
                        "Network Error :: ${t.localizedMessage} ",
                        Toast.LENGTH_LONG
                    ).show()

                }


            }
        })
    }

    private fun generateDataList(UserList: List<RetroUser?>) {

        val adapter = CustomAdapter(UserList)
        binding!!.recyclerView.adapter = adapter
    }
}
