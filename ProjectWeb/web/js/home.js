$(function () {
    var listLatestAccessory = null;
    var listMostFavoriteAccessory = null;
    getLatestCategory();
    getMostFavoriteAccessory();
    function getMostFavoriteAccessory() {
        $.ajax({
            type: "POST",
            url: "load8MostFavoriteAccessoriesAction.action",
            dataType: 'json',
            success: function (data) {
                listMostFavoriteAccessory = data;
                loadMostFavoriteList();
            },
            error: function () {
                alert("Some error occured.");
            }
        });
    }
    function loadMostFavoriteList() {
        if (roleValue === "user") {
            if (listMostFavoriteAccessory !== null) {
                $('#most-favorite-list').html("");
                $.each(listMostFavoriteAccessory.list, function () {
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
                    $('#most-favorite-list').append(container);
                });
            } else {
                $('#most-favorite-list').html("No favorite available.");
            }
        } else {
            if (listMostFavoriteAccessory !== null) {
                $('#most-favorite-list').html("");
                $.each(listMostFavoriteAccessory.list, function () {
                    var imageName = "";
                    if (this.image !== null) {
                        imageName = this.image;
                    }
                    var row = createProductItem(imageName, this.price, this.accName, this.salePercent, this.accID, false);
                    var container = document.createElement("div");
                    container.classList.add("col-lg-3");
                    container.appendChild(row);
                    $('#most-favorite-list').append(container);
                });
            } else {
                $('#most-favorite-list').html("No favorite available.");
            }
        }

    }
    function getLatestCategory() {
        $.ajax({
            type: "POST",
            url: "searchAccessoryAction.action",
            dataType: 'json',
            success: function (data) {
                listLatestAccessory = data;
                loadProductList();
            },
            error: function () {
                alert("Some error occured.");
            }
        });
    }
    function loadProductList() {
        var owl = $('#list-latest');
        owl.trigger('destroy.owl.carousel');
        var content = '';
        if (roleValue === "user") {
            if (listLatestAccessory !== null) {
                $('#list-latest').html("");
                $.each(listLatestAccessory.list, function () {
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
                    content += row.outerHTML;
                });
            } else {
                $('#list-latest').html("No accessory available.");
            }
        } else {
            if (listLatestAccessory !== null) {
                $('#list-latest').html("");
                $.each(listLatestAccessory.list, function () {
                    var imageName = "";
                    if (this.image !== null) {
                        imageName = this.image;
                    }
                    var row = createProductItem(imageName, this.price, this.accName, this.salePercent, this.accID, false);
                    content += row.outerHTML;
                });
            } else {
                $('#list-latest').html("No accessory available.");
            }
        }
        owl.html(content);
        owl.owlCarousel({
            nav: true,
            margin: 10,
            loop: true,
            mouseDraggable: false,
            autoplay: true,
            autoplayTimeout: 3000,
            autoplayHoverPause: false,
            responsive: {
                0: {
                    items: 2
                },
                1000: {
                    items: 3
                }
            }
        });
    }
});