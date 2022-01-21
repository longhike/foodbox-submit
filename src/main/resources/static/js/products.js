const productTable = document.getElementById("product-table");
const loadSpinner = document.getElementById("load-spinner")
const productTableBody = document.getElementById("product-table-body");
const productFilterForm = document.getElementById("product-filter-form");
const productCuisineSelect = document.getElementById("product-cuisine-select");
const productNameSearch = document.getElementById("product-name-search");
const clearFilterButton = document.getElementById("clear-filters");

let products;
let prodsToWorkWith;

const hideLoadAndDisplayTable = () => {
	loadSpinner.style.display = "none";
	productTable.style.display = "block";
}

const fetchAndDisplayProducts = async () => {
	const response = await fetch("/api/products")
	const res = await response.json();
	console.log(res)
	products = res.map(p => {
		return {
			id: p.id,
			name: p.name,
			description: p.description,
			price: p.price,
			discount: p.discount,
			cuisine: p.cuisine.name
		}
	})
	prodsToWorkWith = products;
	populateTable(prodsToWorkWith);
	populateCuisineSelect(prodsToWorkWith, productCuisineSelect);
	hideLoadAndDisplayTable();
}


const makeCartButton = (productId) => {
	return `<button class="btn btn-primary add-to-cart" id="${productId}">+</button>`;
};

const populateTable = (prods) => {
	productTableBody.innerHTML = "";
	for (let i = 0; i < prods.length; i++) {
		const cur = prods[i];
		const row = document.createElement("tr");
		row.innerHTML = `
    <td data-th="ID">${cur.id}</td>
    <td data-th="Product">
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
<td data-th="Price">${cur.discount > 0 ? renderProductDiscount(cur) : "$" + cur.price.toFixed(2)}</td>
<td data-th="Cuisine">
   ${cur.cuisine}
</td>
<td class="actions" data-th="">
    <div class="text-right">
      ${makeCartButton(cur.id)}
    </div>
</td>`;
		productTableBody.appendChild(row);
	}
};

const getProductById = (prods, id) => {
	return prods.filter((p) => p.id === parseInt(id))[0];
};

const addProductToCart = (prod) => {
	const currentCart = JSON.parse(localStorage.getItem("cart")) || [];
	if (currentCart.length > 0) {
		for (let i = 0; i < currentCart.length; i++) {
			if (parseInt(currentCart[i].id) === parseInt(prod.id)) {
				currentCart[i].quantity = currentCart[i].quantity + 1;
				localStorage.setItem("cart", JSON.stringify(currentCart));
				return;
			}
		}
	}
	prod.quantity = 1;
	currentCart.push(prod);
	localStorage.setItem("cart", JSON.stringify(currentCart));
};

const sortAscendingByAttribute = (prods, attribute) => {
	let sorted = null;
	if (attribute === "price") {
		sorted = prods.sort((a, b) => {
			return handleProductDiscount(a) - handleProductDiscount(b);
		});
	} else {
		sorted = prods.sort((a, b) => {
			if (a[attribute] > b[attribute]) {
				return 1;
			}
			if (a[attribute] < b[attribute]) {
				return -1;
			}
			return 0;
		});
	}
	return sorted;
};

const sortDescendingByAttribute = (prods, attribute) => {
	let sorted = null;
	if (attribute === "price") {
		sorted = prods.sort((a, b) => {
			return handleProductDiscount(b) - handleProductDiscount(a);
		});
	} else {
		sorted = prods.sort((a, b) => {
			if (a[attribute] < b[attribute]) {
				return 1;
			}
			if (a[attribute] > b[attribute]) {
				return -1;
			}
			return 0;
		});
	}
	return sorted;
};

const filterProducts = (prods, texts) => {
	let [nameSearch, cuisineSearch] = texts;
	nameSearch = nameSearch.toLowerCase();
	let searchCuisine = true;
	if (cuisineSearch.includes("filter")) searchCuisine = false;
	return prods.filter((p) => {
		if (searchCuisine) {
			return p.name.toLowerCase().includes(nameSearch) && p.cuisine.includes(cuisineSearch);
		} else {
			return p.name.toLowerCase().includes(nameSearch);
		}
	});
};

productCuisineSelect.addEventListener("change", (e) => {
	e.preventDefault();
	const formData = new FormData(productFilterForm);
	const nameText = formData.get("product-name-filter").trim().toLowerCase();
	const cuisineText = formData.get("product-cuisine-filter");
	prodsToWorkWith = filterProducts(products, [nameText, cuisineText]);
	populateTable(prodsToWorkWith);
});

productNameSearch.addEventListener("input", (e) => {
	e.preventDefault();
	const formData = new FormData(productFilterForm);
	const nameText = formData.get("product-name-filter").trim().toLowerCase();
	const cuisineText = formData.get("product-cuisine-filter");
	prodsToWorkWith = filterProducts(products, [nameText, cuisineText]);
	populateTable(prodsToWorkWith);
});

clearFilterButton.addEventListener("click", (e) => {
	e.preventDefault();
	prodsToWorkWith = products;
	productFilterForm.reset();
	populateTable(prodsToWorkWith);
});

document.addEventListener("click", (e) => {
	const className = e.target.className;
	if (className.includes("add-to-cart")) {
		addProductToCart(getProductById(prodsToWorkWith, e.target.id));
		alert("Added to cart!");
	} else if (className.includes("sort-asc")) {
		populateTable(sortAscendingByAttribute(prodsToWorkWith, e.target.name));
	} else if (className.includes("sort-desc")) {
		populateTable(sortDescendingByAttribute(prodsToWorkWith, e.target.name));
	}
});

fetchAndDisplayProducts();

