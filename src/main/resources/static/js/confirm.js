const confirmTableBody = document.getElementById("confirm_table_body");
const totalPriceHolder = document.getElementById("total_price");

function getOrderItems() {
	const currentCart = JSON.parse(localStorage.getItem("cart")) || [];
	return currentCart;
}

function deleteCart() {
	localStorage.removeItem("cart");
}

function populateTotalPrice(total) {
	totalPriceHolder.innerHTML = "";
	totalPriceHolder.textContent = `$${total.toFixed(2)}`;
}

function populateConfirmTableBody() {
	confirmTableBody.innerHTML = "";
	let totalPrice = 0;
	let totalItems = 0;
	const items = getOrderItems();
	for (let i = 0; i < items.length; i++) {
		const tr = document.createElement("tr");
		const cur = items[i];
		totalPrice += handleProductDiscount(cur) * cur.quantity;
		totalItems += cur.quantity;
		tr.innerHTML = `<td data-th="Product">
                <div class="row">
                    <div class="col-md-3 text-left">
                        <img src="https://via.placeholder.com/250x250/5fa9f8/ffffff" alt="" class="img-fluid d-none d-md-block rounded mb-2 shadow ">
                    </div>
                    <div class="col-md-9 text-left mt-sm-2">
                        <h4>${cur.name}</h4>
                        <p class="font-weight-light">${cur.description}</p>
                    </div>
                </div>
            </td>
            <td data-th="Price">${cur.discount > 0 ? renderCartDiscount(cur) : "$" + (cur.price * cur.quantity).toFixed(2)}</td>
            <td data-th="Quantity">
                <p>${cur.quantity}</p>
            </td>`;
		confirmTableBody.appendChild(tr);
	}
	populateTotalPrice(totalPrice);
	deleteCart();
}



populateConfirmTableBody();
