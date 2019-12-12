function addCart(accID, availableQuantity) {
    if (roleValue === "user") {
        var quantity = $('#inputQuantity').val();
        if (quantity > 0 && quantity < Number.parseInt(availableQuantity)) {
            $.ajax({
                type: "POST",
                url: "addCartDetailAction.action",
                dataType: 'json',
                data: "accID=" + accID + "&quantityValue=" + quantity + "&username=" + usernameValue,
                success: function (data) {
                    if (data.msg === "Available quantity is not enough to buy.") {
                        alert(data.msg);
                        location.reload();
                    } else {
                        alert(data.msg);
                    }
                },
                error: function () {
                    alert("Some error occured.");
                }
            });
        } else {
            alert("Please input valid quantity");
        }
    } else if (roleValue === "admin") {
        alert("Your role ADMIN is not authorized");
    } else {
        $('#modalLRForm').modal('show');
    }
}
function getRelatedAccessory(accCatIDSearch, accID) {
    $.ajax({
        type: "POST",
        url: "getRelatedAccAction.action",
        dataType: 'json',
        data: "accCatIDSearch=" + accCatIDSearch + "&accSearch=" + accID,
        success: function (data) {
            loadRelatedList(data);
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
function loadRelatedList(data) {
    if (roleValue === "user") {
        if (data !== null) {
            $.each(data.list, function () {
                var imageName = "";
                if (this.image !== null) {
                    imageName = this.image;
                }
                var row;
                if (checkFavoriteExisted(this.accID)) {
                    row = createProductItem(imageName, this.price, this.accName, this.salePercent, this.accID, true);
                } else {
                    row = createProductItem(imageName, this.price, this.accName, this.salePercent, this.accID, false);
                }
                var container = document.createElement("div");
                container.classList.add("col-lg-3");
                container.appendChild(row);
                $('#related-acc').append(container);
            });
        } else {
            $('#related-acc').html("No ralated accessories.");
        }
    } else {
        if (data !== null) {
            $.each(data.list, function () {
                var imageName = "";
                if (this.image !== null) {
                    imageName = this.image;
                }
                var row = createProductItem(imageName, this.price, this.accName, this.salePercent, this.accID, false);
                var container = document.createElement("div");
                container.classList.add("col-lg-3");
                container.appendChild(row);
                $('#related-acc').append(container);
            });
        } else {
            $('#related-acc').html("No ralated accessories.");
        }
    }

}

