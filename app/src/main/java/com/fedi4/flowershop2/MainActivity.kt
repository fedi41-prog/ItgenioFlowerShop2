package com.fedi4.flowershop2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fedi4.flowershop2.db.BasketEntity

import com.fedi4.flowershop2.db.BasketViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    val basket: HashMap<Int, Int> = HashMap<Int, Int>()
    var count: Int = 0
    var currentProduct: Int = 0

    var viewModel: BasketViewModel? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var modelFactory: BasketViewModelFactory = BasketViewModelFactory(application)
        viewModel = ViewModelProvider(this, modelFactory).get(BasketViewModel::class.java)
        viewModel?.getData()?.observe(this) {
            Log.d("MainActivity", "Data changed: $it")

        }



        val countView = findViewById<TextView>(R.id.countView)
        val productNameView = findViewById<TextView>(R.id.productNameView)
        val imageView = findViewById<ImageView>(R.id.imageView)
        val buttonAddToBasket = findViewById<Button>(R.id.button)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonLeft = findViewById<Button>(R.id.buttonLeft)
        val buttonRight = findViewById<Button>(R.id.buttonRight)


        defineProducts()


        imageView.setImageResource(Products.getProductByIndex(currentProduct)!!.image)
        productNameView.text = Products.getProductByIndex(currentProduct)!!.name
        countView.text = count.toString()

        buttonAddToBasket.setOnClickListener {
            var res_count = count
            if (basket.containsKey(currentProduct)) {
                res_count += basket.get(currentProduct)!!
            }
            basket.put(currentProduct, res_count)

            writeBasket()
            val intent: Intent = Intent(this, BasketActivity::class.java)
            Log.d("MainActivity", "Basket: $basket")
            startActivity(intent)
        }
        buttonLeft.setOnClickListener {
            currentProduct --
            if (currentProduct < 0) {
                currentProduct = Products.getLength() - 1
            }
            imageView.setImageResource(Products.getProductByIndex(currentProduct)!!.image)
            productNameView.text = Products.getProductByIndex(currentProduct)!!.name
            countView.text = count.toString()
        }
        buttonRight.setOnClickListener {
            currentProduct ++
            if (currentProduct >= Products.getLength()) {
                currentProduct = 0
            }
            imageView.setImageResource(Products.getProductByIndex(currentProduct)!!.image)
            productNameView.text = Products.getProductByIndex(currentProduct)!!.name
            countView.text = count.toString()
        }
        buttonMinus.setOnClickListener {
            count --
            if (count < 0) {
                count = 0
            }
            countView.text = count.toString()
        }
        buttonPlus.setOnClickListener {
            count ++
            countView.text = count.toString()
        }

    }
    fun defineProducts() {
        Products.addProduct(Product("Goofy", 100.0, "Goofy flower", R.drawable.goofy))
        Products.addProduct(Product("Rose", 200.0, "White rose flower", R.drawable.frosch))
        Products.addProduct(Product("Android", 300.0, "Android flower", R.drawable.ic_launcher_foreground))
    }
    fun writeBasket() {


        basket.forEach { (key, value) ->
            viewModel?.insert(BasketEntity(product_id=key, count=value))
        }
    }

    //fun insertNewBasketEntryInDatabase(basketRepository: BasketRepository, id: Int, product_id: Int, count: Int) {
    //    lifecycleScope.launch {
    //        val newEntity = BasketEntity(product_id=product_id, count=count)
    //        Log.d("MainActivity", "New entry: $newEntity")
    //        basketRepository.insertNewBasketEntry(newEntity)
    //    }
    //}
}