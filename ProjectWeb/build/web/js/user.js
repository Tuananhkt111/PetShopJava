function createRowDetails(dto) {
    var row = document.createElement("tr");
    var tdID = document.createElement("td");
    var tdName = document.createElement("td");
    var tdCatName = document.createElement("td");
    var tdBrand = document.createElement("td");
    var tdDes = document.createElement("td");
    var tdPrice = document.createElement("td");
    var tdStartSellingDate = document.createElement("td");
    var tdImage = document.createElement("td");
    var tdQuantity = document.createElement("td");
    var tdSale = document.createElement("td");
    var imgSrc = document.createElement("img");
    tdID.innerHTML = dto.accID;
    tdName.innerHTML = dto.accName;
    tdCatName.innerHTML = dto.accCatName;
    tdBrand.innerHTML = dto.brand;
    tdDes.innerHTML = dto.description;
    var n = parseFloat(dto.price);
    var priceValue = Math.round(n * 100) / 100;
    var m = parseFloat(dto.salePercent);
    var saleValue = Math.round(m * 100) / 100;
    tdPrice.innerHTML = "$" + priceValue;
    tdSale.innerHTML = saleValue;
    var myDate = new Date(dto.startSellingDate);
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var year = myDate.getFullYear();
    var date = day + "/" + month + "/" + year;
    tdStartSellingDate.innerHTML = date;
    if (dto.image === null) {
        imgSrc.src = "../img/product/noimage.jpg";
    } else {
        imgSrc.src = "../img/product/" + dto.image;
    }
    imgSrc.onerror = function () {
        this.onerror = null;
        this.src = '../img/product/noimage.jpg';
    };
    imgSrc.alt = "Image not found";
    imgSrc.style = "max-width: none; width: 175px; height: auto";
    tdImage.appendChild(imgSrc);
    tdQuantity.innerHTML = dto.quantity;
    row.appendChild(tdID);
    row.appendChild(tdName);
    row.appendChild(tdCatName);
    row.appendChild(tdBrand);
    row.appendChild(tdDes);
    row.appendChild(tdPrice);
    row.appendChild(tdStartSellingDate);
    row.appendChild(tdImage);
    row.appendChild(tdSale);
    row.appendChild(tdQuantity);
    return row;
}

function createRow(dto) {
    var row = document.createElement("tr");
    var tdID = document.createElement("td");
    var tdUsername = document.createElement("td");
    var tdtimeOrder = document.createElement("td");
    var tdtimeFinish = document.createElement("td");
    var tdDeliveryPhone = document.createElement("td");
    var tdDeliveryAddress = document.createElement("td");
    var tdPrice = document.createElement("td");
    var tdView = document.createElement("td");
    var tdStatus = document.createElement("td");
    tdID.innerHTML = dto.accTranID;
    tdUsername.innerHTML = dto.username;
    var myDate = new Date(dto.timeOrder);
    var month = myDate.getMonth() + 1;
    var day = myDate.getDate();
    var year = myDate.getFullYear();
    var hour = myDate.getHours();
    var min = myDate.getMinutes();
    var mili = myDate.getMilliseconds();
    var date = day + "/" + month + "/" + year + " " + hour + ":" + min + ":" + mili;
    tdtimeOrder.innerHTML = date;
    if (dto.timeFinish !== null) {
        var myDate1 = new Date(dto.timeFinish);
        var month1 = myDate1.getMonth() + 1;
        var day1 = myDate1.getDate();
        var year1 = myDate1.getFullYear();
        var hour1 = myDate1.getHours();
        var min1 = myDate1.getMinutes();
        var mili1 = myDate1.getMilliseconds();
        var date1 = day1.toString().padStart(2, 0) + "/"
                + month1.toString().padStart(2, 0) + "/" + year1.toString().padStart(4, 0)
                + " " + hour1.toString().padStart(2, 0) + ":" + min1.toString().padStart(2, 0)
                + ":" + mili1.toString().padStart(2, 0);
        tdtimeFinish.innerHTML = date1;
    } else {
        tdtimeFinish.innerHTML = "Unknown";
    }
    tdDeliveryPhone.innerHTML = dto.deliveryPhone;
    tdDeliveryAddress.innerHTML = dto.deliverAddress;
    var n = parseFloat(dto.totalPrice);
    var priceValue = Math.round(n * 100) / 100;
    tdPrice.innerHTML = "$" + priceValue;
//    View transaction
    var viewLink = document.createElement("a");
    viewLink.setAttribute("data-toggle", "modal");
    viewLink.setAttribute("data-target", "#modalAccTransForm");
    viewLink.style = "cursor: pointer";
    viewLink.addEventListener("click", function () {
        viewDetails(dto.accTranID);
    });
    viewLink.innerHTML = "View details";
    tdView.appendChild(viewLink);
    //Status
    tdStatus.innerHTML = dto.status;
    row.appendChild(tdID);
    row.appendChild(tdUsername);
    row.appendChild(tdtimeOrder);
    row.appendChild(tdtimeFinish);
    row.appendChild(tdDeliveryPhone);
    row.appendChild(tdDeliveryAddress);
    row.appendChild(tdPrice);
    row.appendChild(tdView);
    row.appendChild(tdStatus);
    return row;
}
function searchTran() {
    $.ajax({
        type: "POST",
        url: "findTranByUsernameAction.action",
        dataType: 'json',
        data: "username=" + usernameValue,
        success: function (data) {
            if (data !== null) {
                if (data.list !== null && data.list.length !== 0) {
                    $('#tbodyTranSearch').empty();
                    $('#accTranSearch-not-found').css("display", "none");
                    $('#accTranTable').css("display", "table");
                    $.each(data.list, function () {
                        var row = createRow(this);
                        $('#tbodyTranSearch').append(row);
                    });
                } else {
                    $('#accTranSearch-not-found').css("display", "block");
                    $('#accTranTable').css("display", "none");
                }
            } else {
                $('#accTranSearch-not-found').css("display", "block");
                $('#accTranTable').css("display", "none");
            }
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
function viewDetails(accTranID) {
    $.ajax({
        type: "POST",
        url: "viewDetailAction.action",
        dataType: 'json',
        data: "accTranID=" + accTranID,
        success: function (data) {
            if (data !== null) {
                if (data.translist !== null && data.translist.length !== 0) {
                    $('#tbodyAccTranSearch').empty();
                    $('#acc-not-found').css("display", "none");
                    $('#accTable').css("display", "table");
                    $.each(data.translist, function () {
                        var row = createRowDetails(this);
                        $('#tbodyAccTranSearch').append(row);
                    });
                } else {
                    $('#acc-not-found').css("display", "block");
                    $('#accTable').css("display", "none");
                }
            } else {
                $('#acc-not-found').css("display", "block");
                $('#accTable').css("display", "none");
            }
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
function closeModal() {
    $('#modalAccTransForm').hide();
}
function openCity(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("btn-tab");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the link that opened the tab
    if(cityName === "favoritelist") {
        document.getElementById(cityName).style.display = "flex";
    } else {
        document.getElementById(cityName).style.display = "block";
    }
    evt.currentTarget.className += " active";
}
function loadProfile() {
    $.ajax({
        type: "POST",
        url: "loadProfileAction.action",
        dataType: 'json',
        data: "txtUsernameLg=" + usernameValue,
        success: function (data) {
            $('#txtPasswordUpdt').val(data.dto.password);
            $('#txtFullnameUpd').val(data.dto.fullname);
            $('#txtPhoneUpdt').val(data.dto.phone);
            $('#txtAddressUpdt').val(data.dto.address);
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}



function loadFavoriteList() {
    if (roleValue === "user") {
        if (listAccessoryFavorite !== null) {
            $('#favoritelist').html("");
            $.each(listAccessoryFavorite, function () {
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
                $('#favoritelist').append(container);
            });
        } else {
            $('#favoritelist').html("No favorite available.");
        }
    } else {
        if (listAccessoryFavorite !== null) {
            $('#favoritelist').html("");
            $.each(listAccessoryFavorite, function () {
                var imageName = "";
                if (this.image !== null) {
                    imageName = this.image;
                }
                var row = createProductItem(imageName, this.price, this.accName, this.salePercent, this.accID, false);
                var container = document.createElement("div");
                container.classList.add("col-lg-3");
                container.appendChild(row);
                $('#favoritelist').append(container);
            });
        } else {
            $('#favoritelist').html("No favorite available.");
        }
    }
}

function updateProfile() {
    $.ajax({
        type: "POST",
        url: "updateProfileAction.action",
        dataType: 'json',
        data: "txtUsernameLg=" + usernameValue + "&txtFullnameUpd="
                + $('#txtFullnameUpd').val() + "&txtPhoneUpdt=" + $('#txtPhoneUpdt').val()
                + "&txtAddressUpdt=" + $('#txtAddressUpdt').val(),
        success: function (data) {
            alert(data.msg);
            loadProfile();
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
$(function () {
    loadProfile();
    searchTran();
    loadFavoriteList();
    tabcontent = document.getElementsByClassName("tab-content");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    $('#profile').css("display", "block");
    $('#form-update-profile').validate({
        rules: {
            txtFullnameUpd: {
                required: true
            },
            txtPhoneUpdt: {
                required: true
            },
            txtAddressUpdt: {
                required: true
            }
        },
        messages: {
            txtFullnameUpd: {
                required: "Fullname cannot be empty.",
                pattern: "Fullname must be 1-40 characters."
            },
            txtPhoneUpdt: {
                required: "Phone cannot be empty.",
                pattern: "Phone must be 10-16 numberic characters."
            },
            txtAddressUpdt: {
                required: "Address cannot be empty.",
                pattern: "Address must be 1-70 characters."
            }
        },
        submitHandler: function () {
            updateProfile();
        }
    });
}
);