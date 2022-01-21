const totalDueDisplay = document.getElementById("total-due");
const checkOutForm = document.getElementById("checkout-form");
const monthSelect = document.getElementById("month-select");
const yearSelect = document.getElementById("year-select");

const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November"]
const minYear = new Date().getFullYear();

const getTotalForCheckout = () => {
	const items = JSON.parse(localStorage.getItem("cart"));
	let total = 0;
	for (let i = 0; i < items.length; i++) {
		const cur = items[i];
		total += handleProductDiscount(cur) * cur.quantity;
	}
	return total;
}

const populateMonthAndYearSelects = () => {
	for (let i = 0; i < months.length; i++) {
		const opt = document.createElement("option");
		opt.value = months[i];
		opt.textContent = months[i];
		monthSelect.appendChild(opt);
	}
	let year = minYear
	for (let i = 0; i < 15; i++) {
		const opt = document.createElement("option");
		opt.value = year;
		opt.textContent = year;
		yearSelect.appendChild(opt);
		year++;
	}
}

const isValidName = (name) => {
	return name.length > 0 && name.split(" ").length > 1;
}

const isValidCard = (card) => {
	const reg = new RegExp('^[0-9]+$');
	return card.length === 16 && reg.test(card);
}

const isValidCode = (code) => {
	return !isNaN(parseInt(code)) && code.length === 3;
}

checkOutForm.addEventListener("submit", (e) => {
	e.preventDefault();
	const data = new FormData(checkOutForm);

	if (!isValidCode(data.get("code"))) {
		alert("please enter a properly formatted CVV/CVC code");
	}
	else if (!isValidCard(data.get("card"))) {
		alert("please enter a properly formatted credit card");
	}
	else if (!isValidName(data.get("name"))) {
		alert("please enter a properly formatted name")
	}
	else {
		document.location.href = "/confirm";
	}
})

totalDueDisplay.textContent = `$${getTotalForCheckout().toFixed(2)}`;
populateMonthAndYearSelects();
