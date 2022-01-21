const handleProductDiscount = (prod) => {
  if (prod.discount > 0) {
    return prod.price - (prod.price * prod.discount);
  } else {
    return prod.price;
  }
};

const renderProductDiscount = (prod) => {
  const discount = prod.price - (prod.price * prod.discount);
  return `<strike>$${prod.price.toFixed(2)}</strike> $${discount.toFixed(2)}`
}

const renderCartDiscount = (prod) => {
  const discount = prod.price - (prod.price * prod.discount);
  return `<strike>$${(prod.price * prod.quantity).toFixed(2)}</strike> $${(discount * prod.quantity).toFixed(2)}`
}

const populateCuisineSelect = (prods, selectEl) => {
  const set = new Set(prods.map((p) => p.cuisine));
  set.forEach((n) => {
    const option = document.createElement("option");
    option.value = n;
    option.textContent = n;
    selectEl.appendChild(option);
  });
};