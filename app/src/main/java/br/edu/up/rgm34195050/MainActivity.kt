package br.edu.up.rgm34195050

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.inventory.ui.inventory.InventoryListScreen
import com.example.inventory.ui.inventory.ItemEntryScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = AppDatabase.getDatabase(this)
        val repository = ItemRepository(database.itemDao())

        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "list") {
                composable("list") {
                    InventoryListScreen(onAddItemClick = { navController.navigate("add") })
                }
                composable("add") {
                    ItemEntryScreen(onItemAdded = { navController.popBackStack() })
                }
            }
        }
    }
}
