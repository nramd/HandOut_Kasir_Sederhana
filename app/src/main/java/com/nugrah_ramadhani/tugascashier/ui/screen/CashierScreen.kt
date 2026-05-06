package com.nugrah_ramadhani.tugascashier.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nugrah_ramadhani.tugascashier.model.CashierUiState
import com.nugrah_ramadhani.tugascashier.model.Product
import com.nugrah_ramadhani.tugascashier.ui.component.CartItemRow
import com.nugrah_ramadhani.tugascashier.ui.component.ProductCard
import com.nugrah_ramadhani.tugascashier.ui.component.SummarySection

@Composable
fun CashierScreen(
    uiState: CashierUiState,
    onAddProduct: (Product) -> Unit,
    onDecreaseItem: (Int) -> Unit,
    onCheckout: () -> Unit,
    onResetTransaction: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text(
                text = "Kasir Sederhana",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = uiState.message,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Daftar Produk",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.products) { product ->
                    ProductCard(
                        product = product,
                        onAddClick = { onAddProduct(product) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Keranjang",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                uiState.cartItems.forEach { cartItem ->
                    CartItemRow(
                        cartItem = cartItem,
                        onAddClick = { onAddProduct(cartItem.product) },
                        onDecreaseClick = { onDecreaseItem(cartItem.product.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            SummarySection(
                uiState = uiState,
                onCheckoutClick = onCheckout,
                onResetClick = onResetTransaction
            )
        }
    }
}