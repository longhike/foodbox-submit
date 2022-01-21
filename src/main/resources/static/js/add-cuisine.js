const addForm = document.getElementById("add-form");
const errorHolder = document.getElementById("error")
const errorMessage = document.getElementById("error-message")

const showError = (message) => {
	errorMessage.textContent = "";
	errorMessage.textContent = message;
	errorHolder.style.display = "block";
}

const hideError = () => {
	errorHolder.style.display = "none";
}


addForm.addEventListener("submit", async (e) => {
	e.preventDefault();
	hideError();
	const data = new FormData(addForm);
	const updateObj = {
		name: data.get("name"),
	}
	try {
		const objString = JSON.stringify(updateObj);
		const response = await fetch("/api/admin/cuisines", {
			method: "POST",
			"headers": {
				"Content-Type": "application/json",
				"Accept": "*/*"
			},
			body: objString
		})
		const res = JSON.parse(await response.text());

		switch (response.status) {
			case 200:
			case 201:
				document.location.href = "/admin";
				break;
			case 400:
				throw new Error(res.message);
			case 403:
				throw new Error("You're not allowed to do this")
			case 500:
				throw new Error(res.message)
			default:
				throw new Error(res.message);
		}

	}
	catch (e) {
		showError(e.message)
	}

})