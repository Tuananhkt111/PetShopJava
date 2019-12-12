var accCartList = null;
function createAccCart(accID, accName, salePercent, price, quantity, availableQuantity, image, isDelete) {
    var row = document.createElement("tr");
    var nameCol = document.createElement("td");
    var quantityCol = document.createElement("td");
    var priceCol = document.createElement("td");
    var deleteCol = document.createElement("td");
    //accessory column
    var imgSrc = document.createElement("img");
    var pTitle = document.createElement("div");
    var titleName = document.createElement("h4");
    var divPrice = document.createElement("div");
    var curPrice = document.createElement("p");
    if (image === "") {
        imgSrc.src = "../img/product/noimage.jpg";
    } else {
        imgSrc.src = "../img/product/" + image;
    }
    imgSrc.onerror = function () {
        this.onerror = null;
        this.src = "../img/product/noimage.jpg";
    };
    imgSrc.style = "max-width: none; width: 72px; height: auto";
    pTitle.classList.add("pc-title");
    titleName.innerHTML = accName;
    divPrice.classList.add("d-flex");
    divPrice.classList.add("flex-row");
    var salePc = Number.parseFloat(salePercent);
    var priceValue = Number.parseFloat(price);
    var curPriceValue = Math.round(priceValue * (1 -salePc) * 100) / 100;
    if (salePc > 0) {
        var priceTag = document.createElement("del");
        priceTag.style = "color:gray; padding-right: 5px";
        priceTag.innerHTML = "$" + priceValue;
        curPrice.innerHTML = "$" + curPriceValue;
        divPrice.appendChild(priceTag);
        divPrice.appendChild(curPrice);
    } else {
        curPrice.innerHTML = "$" + priceValue;
        divPrice.appendChild(curPrice);
    }
    pTitle.appendChild(titleName);
    pTitle.appendChild(divPrice);
    if (isDelete === true) {
        var isDelError = document.createElement("h6");
        isDelError.style = "color: red";
        isDelError.classList.add("isDelError");
        isDelError.innerHTML = "This accessory had been deleted.";
        pTitle.appendChild(isDelError);
    }
    nameCol.classList.add("product-col");
    nameCol.appendChild(imgSrc);
    nameCol.appendChild(pTitle);
    //quantity column
    var divQuantity = document.createElement("div");
    var proQty = document.createElement("div");
    var divAvailable = document.createElement("div");
    var inputQuantity = document.createElement("input");
    var availableQuantityValue = Number.parseInt(availableQuantity);
    inputQuantity.type = "number";
    inputQuantity.value = quantity;
    inputQuantity.min = 1;
    inputQuantity.max = availableQuantityValue;
    inputQuantity.classList.add("form-control");
    var quantityValue = Number.parseInt(quantity);
    var priceTotalValue;
    if (isDelete === true) {
        inputQuantity.disabled = true;
    }
    inputQuantity.onchange = function () {
        if (this.value > availableQuantityValue || this.value < 1) {
            this.value = quantityValue;
        } else {
            if (salePc > 0) {
                priceTotalValue = Math.round(curPriceValue * this.value * 100) / 100;
            } else {
                priceTotalValue = Math.round(priceValue * this.value * 100) / 100;
            }
            this.parentNode.parentNode.parentNode.parentNode.childNodes[2].childNodes[0].innerHTML = "$" + priceTotalValue;
            calculateTotalPrice();
            updateAccCart(accID, this.value);
        }
    };
    proQty.appendChild(inputQuantity);
    divAvailable.style = "color: #0064ff";
    divAvailable.classList.add("quantityError");
    if(availableQuantityValue === 0) {
        divAvailable.innerHTML = "Out of Stock";
        divAvailable.style = "color: red";
        inputQuantity.disabled = true;
    } else if (availableQuantityValue < quantityValue) {
        inputQuantity.value = availableQuantityValue;
        divAvailable.innerHTML = "Updated";
        updateAccCart(accID, availableQuantityValue);
    } 
    divQuantity.classList.add("quantity");
    divQuantity.classList.add("d-flex");
    divQuantity.classList.add("flex-column");
    divQuantity.classList.add("align-items-center");
    divQuantity.appendChild(proQty);
    divQuantity.appendChild(divAvailable);
    quantityCol.classList.add("quy-col");
    quantityCol.appendChild(divQuantity);
    //Price Column
    var priceTotal = document.createElement("h4");
    if (salePc > 0) {
        priceTotalValue = Math.round(curPriceValue * quantityValue * 100) / 100;
    } else {
        priceTotalValue = Math.round(priceValue * quantityValue * 100) / 100;
    }
    priceTotal.innerHTML = "$" + priceTotalValue;
    priceTotal.classList.add("current-price");
    priceCol.classList.add("size-col");
    priceCol.appendChild(priceTotal);
    //Delete column
    var deleteLink = document.createElement("a");
    deleteLink.style = "cursor: pointer";
    deleteLink.innerHTML = "Delete";
    deleteLink.onclick = function () {
        removeAccCart(accID);
    };
    deleteCol.classList.add("total-col");
    deleteCol.appendChild(deleteLink);
    row.appendChild(nameCol);
    row.appendChild(quantityCol);
    row.appendChild(priceCol);
    row.appendChild(deleteCol);
    return row;
}
function loadAccCart() {
    $.ajax({
        type: "POST",
        url: "loadAccCartAction.action",
        dataType: 'json',
        success: function (data) {
            accCartList = data.cart;
            if (accCartList !== null) {
                if ($('#cartCount').html() !== '0') {
                    $('#tbodyCart').html("");
                    for (var i = 0, keys = Object.keys(accCartList), ii = keys.length; i < ii; i++) {
                        var row = createAccCart(accCartList[keys[i]].accID,
                                accCartList[keys[i]].accName, accCartList[keys[i]].salePercent,
                                accCartList[keys[i]].price, accCartList[keys[i]].quantity, accCartList[keys[i]].availableQuantity, accCartList[keys[i]].image, accCartList[keys[i]].isDelete);
                        $('#tbodyCart').append(row);
                        calculateTotalPrice();
                    }
                } else {
                    $('#cart-table').html("Your cart is empty");
                }
            } else {
                $('#cart-table').html("Your cart is empty");
            }
        },
        error: function () {
            alert("Some error occured at loadAccCartAction.");
        }
    });
}
function removeAccCart(accID) {
    $.ajax({
        type: "POST",
        url: "removeCartAction.action",
        dataType: 'json',
        data: "accID=" + accID,
        success: function () {
            countCart();
            loadAccCart();
        },
        error: function () {
            alert("Some error occured at removeAccCartAction.");
        }
    });
}
function updateAccCart(accID, quantity) {
    $.ajax({
        type: "POST",
        url: "updateAccCartAction.action",
        dataType: 'json',
        data: "accID=" + accID + "&quantity=" + quantity,
        error: function () {
            alert("Some error occured at updateAccCartAction.");
        }
    });
}
function calculateTotalPrice() {
    var total = 0;
    $('.current-price').each(function () {
        var s = this.innerHTML.substring(1);
        var price = Number.parseFloat(s);
        total += price;
    });
    total = Math.round(total * 100) / 100;
    $('#total-price').html("$" + total);
}
function checkOut() {
    var check = true;
    $('.isDelError').each(function () {
        if (this.innerHTML !== "") {
            check = false;
        }
    });
    $('.quantityError').each(function () {
        if (this.innerHTML === "Out of Stock") {
            check = false;
        }
    });
    if($('#cart-table').html() === "Your cart is empty") {
        check = false;
    }
    if (check) {
        window.location.href = "../user/getDeliveryInfoAction.action?username=" + usernameValue;
    } else {
        alert("Your cart has problem or empty. Please check your cart again.");
    }
}
$(function () {
    loadAccCart();
});