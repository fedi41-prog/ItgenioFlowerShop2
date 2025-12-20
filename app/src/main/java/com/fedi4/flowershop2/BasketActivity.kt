package com.fedi4.flowershop2

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fedi4.flowershop2.db.BasketEntity

import com.fedi4.flowershop2.db.BasketViewModelFactory
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader

class BasketActivity : AppCompatActivity(){

    var basket: HashMap<Int, Int> = HashMap<Int, Int>()

    var viewModel: BasketViewModel? = null



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.acticity_basket)


        var modelFactory: BasketViewModelFactory = BasketViewModelFactory(application)
        viewModel = ViewModelProvider(this, modelFactory).get(BasketViewModel::class.java)


        val textView = findViewById<TextView>(R.id.textView)


        val buttonBack = findViewById<Button>(R.id.buttonBack)
        buttonBack.setOnClickListener {
            finish()
        }



        viewModel?.getData()?.observe(this) {
            Log.d("MainActivity", "Data changed: $it")
            basket = generateBasket(it)
            textView.text = generate_text_view()

        }
        viewModel?.getData()
    }

    fun generateBasket(data: List<BasketEntity>) : HashMap<Int, Int> {
        var basket: HashMap<Int, Int> = HashMap<Int, Int>()


        for (entry in data) {
            basket.put(entry.product_id, entry.count)
            Log.d("BasketActivity", "added entry to view: $entry")
        }


        return basket
    }

    fun generate_text_view() : String {

        var text = ""

        basket.forEach { (key, value) ->
            var product: Product = Products.getProductByIndex(key) as Product
            text += product.name + " - " + value + "pcs - " + product.price*value + "$\n"
        }

        return text;

    }

    //fun getAllBasketEntries(basketRepository: BasketRepository): List<BasketEntry> {
    //    var entries: List<BasketEntry> = listOf()
    //    lifecycleScope.launch {
    //        entries = basketRepository.getAllBasketEntries()
    //        Log.d("BasketActivity", "all entries: $entries")
    //    }
    //    Log.d("BasketActivity", "returned entries: $entries")
    //    return entries
//
    //}
}