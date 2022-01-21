const cartTableBody = document.getElementById("cart_table_body");
const totalPriceHolder = document.getElementById("total_price");
const totalItemsHolder = document.getElementById("total_items");
const checkOutButton = document.getElementById("checkout-button");

function getCartItems() {
	const currentCart = JSON.parse(localStorage.getItem("cart")) || [];
	return currentCart;
}

function disableCheckOutIfCartEmpty() {
	if (getCartItems().length <= 0) {
		checkOutButton.style.display = "none";
	}
	else {
		checkOutButton.style.display = "block";
	}
}

function setCartItems(items) {
	localStorage.setItem("cart", JSON.stringify(items));
}

function populateTotalPrice(total) {
	totalPriceHolder.innerHTML = "";
	totalPriceHolder.textContent = `$${total.toFixed(2)}`;
}

function populateTotalQuantity(total) {
	totalItemsHolder.innerHTML = "";
	totalItemsHolder.textContent = total;
}

function populateCartTableBody() {
	cartTableBody.innerHTML = "";
	let totalPrice = 0;
	let totalItems = 0;
	const items = getCartItems();
	if (items.length > 0) {
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
                <input type="number" class="form-control form-control-lg text-center quantity-input" name="${cur.id
				}" value="${cur.quantity}">
            </td>
            <td class="actions" data-th="">
                <div class="text-right">
                    <button class="btn btn-danger btn-sm mb-2 delete-item" name="${cur.id
				}">
                        Delete
                    </button>
                </div>
            </td>`;
			cartTableBody.appendChild(tr);
		}
	} else {
		const noContent = document.createElement("h3");
		noContent.textContent = "No items in your cart yet.";
		cartTableBody.appendChild(noContent);
	}
	populateTotalPrice(totalPrice);
	populateTotalQuantity(totalItems);
}

function removeItemFromCart(itemId) {
	const items = getCartItems();
	const updatedItems = items.filter((i) => parseInt(i.id) !== parseInt(itemId));
	setCartItems(updatedItems);
	populateCartTableBody();
	disableCheckOutIfCartEmpty();
}

document.addEventListener("input", (e) => {
	if (e.target.className.includes("quantity-input")) {
		const itemId = e.target.name;
		const itemQuantity = e.target.value;
		const items = getCartItems();
		if (itemQuantity <= 0) {
			removeItemFromCart(itemId);
			populateCartTableBody();
			disableCheckOutIfCartEmpty()
			return;
		} else {
			for (let i = 0; i < items.length; i++) {
				const cur = items[i];
				if (parseInt(cur.id) === parseInt(itemId)) {
					cur.quantity = parseInt(itemQuantity);
					setCartItems(items);
					populateCartTableBody();
					disableCheckOutIfCartEmpty()
					return;
				}
			}
		}
	}
});

document.addEventListener("click", (e) => {
	if (e.target.className.includes("delete-item")) {
		removeItemFromCart(e.target.name);
		populateCartTableBody();
		disableCheckOutIfCartEmpty()
	}
})

disableCheckOutIfCartEmpty();

populateCartTableBody();
