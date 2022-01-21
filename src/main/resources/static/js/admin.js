const tableHolder = document.getElementById("table-wrapper");
const loadSpinner = document.getElementById("load-spinner")
const productTableBody = document.getElementById("product-table-body");
const cuisineTableBody = document.getElementById("cuisine-table-body");
const productFilterForm = document.getElementById("product-filter-form");
const productCuisineSelect = document.getElementById("product-cuisine-select");
const productNameSearch = document.getElementById("product-name-search");
const clearFilterButton = document.getElementById("clear-filters");

let products;
let prodsToWorkWith;

const hideLoadAndDisplayTable = () => {
	loadSpinner.style.display = "none";
	tableHolder.style.display = "block";
}

const hideTableAndDisplayLoad = () => {
	loadSpinner.style.display = "block";
	tableHolder.style.display = "none";
}

const fetchAndDisplayData = async () => {
	const productResponse = await fetch("/api/admin/products")
	const cuisineResponse = await fetch("/api/admin/cuisines")
	const prodRes = await productResponse.json();
	const cuisRes = await cuisineResponse.json();
	products = prodRes.map(p => {
		return {
			id: p.id,
			name: p.name,
			description: p.description,
			price: p.price,
			discount: p.discount,
			cuisine: p.cuisine.name,
			isEnabled: p.isEnabled
		}
	})
	prodsToWorkWith = products;
	populateProductTable(prodsToWorkWith);
	populateCuisineTable(cuisRes);
	populateCuisineSelect(prodsToWorkWith, productCuisineSelect);
	hideLoadAndDisplayTable();
}

const makeUpdateProductButton = (productId) => {
	return `<button class="btn btn-primary btn-sm update-product" id="${productId}">Update</button>`;
};

const makeUpdateCuisineButton = (cuisineId) => {
	return `<button class="btn btn-primary btn-sm update-cuisine" id="${cuisineId}">Update</button>`;
};

const populateProductTable = (prods) => {
	productTableBody.innerHTML = "";
	for (let i = 0; i < prods.length; i++) {
		const cur = prods[i];
		const row = document.createElement("tr");
		row.setAttribute("id", cur.id);
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
<td data-th="Price">${cur.discount > 0 ? renderProductDiscount(cur) : "$" + cur.price.toFixed(2)
			}</td>
<td data-th="Discount">${cur.discount * 100}%</td>
<td data-th="Cuisine">
   ${cur.cuisine}
</td>
<td data-th="Enabled">
   ${cur.isEnabled ? "&#10003;" : "&#10060;"}
</td>
<td class="actions" data-th="">
      ${makeUpdateProductButton(cur.id)}
	<button class="btn btn-danger btn-sm delete-product" data-value="${cur.id}">Delete</button>
</td>`;
		productTableBody.appendChild(row);
	}
};

const populateCuisineTable = (cuisines) => {
	cuisineTableBody.innerHTML = "";
	for (let i = 0; i < cuisines.length; i++) {
		const cur = cuisines[i];
		const row = document.createElement("tr");
		row.setAttribute("id", cur.id);
		row.innerHTML = `
    <td data-th="ID">${cur.id}</td>
    <td data-th="Cuisine">
    <div class="row">
        <div class="col-md-9 text-left mt-sm-2">
            <h4>${cur.name}</h4>
        </div>
    </div>
</td>
<td class="actions" data-th="">
      ${makeUpdateCuisineButton(cur.id)}
	<button class="btn btn-danger btn-sm delete-cuisine" data-value="${cur.id}">Delete</button>
</td>`;
		cuisineTableBody.appendChild(row);
	}

}

const getProductById = (prods, id) => {
	return prods.filter((p) => p.id === parseInt(id))[0];
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

const handleProductDelete = async (id) => {
	hideTableAndDisplayLoad();
	await fetch("api/admin/products/" + id, { method: "DELETE" })
	fetchAndDisplayData();
}

const handleCuisineDelete = async (id) => {
	hideTableAndDisplayLoad();
	await fetch("api/admin/cuisines/" + id, { method: "DELETE" })
	fetchAndDisplayData();
}

productCuisineSelect.addEventListener("change", (e) => {
	e.preventDefault();
	const formData = new FormData(productFilterForm);
	const nameText = formData.get("product-name-filter").trim().toLowerCase();
	const cuisineText = formData.get("product-cuisine-filter");
	prodsToWorkWith = filterProducts(products, [nameText, cuisineText]);
	populateProductTable(prodsToWorkWith);
});

productNameSearch.addEventListener("input", (e) => {
	e.preventDefault();
	const formData = new FormData(productFilterForm);
	const nameText = formData.get("product-name-filter").trim().toLowerCase();
	const cuisineText = formData.get("product-cuisine-filter");
	prodsToWorkWith = filterProducts(products, [nameText, cuisineText]);
	populateProductTable(prodsToWorkWith);
});

clearFilterButton.addEventListener("click", (e) => {
	e.preventDefault();
	prodsToWorkWith = products;
	productFilterForm.reset();
	populateProductTable(prodsToWorkWith);
});

document.addEventListener("click", (e) => {
	const className = e.target.className;
	if (className.includes("update-product")) {
		document.location.href = "admin/update/product?productId=" + e.target.id;
	} else if (className.includes("update-cuisine")) {
		document.location.href = "admin/update/cuisine?cuisineId=" + e.target.id;
	} else if (className.includes("sort-asc")) {
		populateProductTable(sortAscendingByAttribute(prodsToWorkWith, e.target.name));
	} else if (className.includes("sort-desc")) {
		populateProductTable(sortDescendingByAttribute(prodsToWorkWith, e.target.name));
	} else if (className.includes("delete-product")) {
		const yesDelete = confirm("Are you sure you want to delete this Food Box?")
		if (yesDelete) {
			handleProductDelete(e.target.getAttribute("data-value"))
		}
	} else if (className.includes("delete-cuisine")) {
		const yesDelete = confirm("Are you sure you want to delete this Cuisine?\nNOTE THAT ALL ASSOCIATED FOOD BOXES WILL BE DELETED AS WELL")
		if (yesDelete) {
			handleCuisineDelete(e.target.getAttribute("data-value"))
		}
	}
});

fetchAndDisplayData();