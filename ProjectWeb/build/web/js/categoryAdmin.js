function loadCbPetAccessory(id) {
    $.ajax({
        type: "POST",
        url: "loadAllPetCategoryAction.action",
        async: false,
        dataType: 'json',
        success: function (data) {
            $('#' + id).empty();
            $.each(data.list, function () {
                var opt = document.createElement("option");
                opt.value = this.petCatID;
                opt.innerHTML = this.petType;
                $('#' + id).append(opt);
            });
        },
        error: function () {
            alert("Some error occured.");
        }
    });
};
//Pet category
function generatePetCatID() {
    var id = "PC";
    const possible = "0123456789";
    do {
        for (let i = 0; i < 3; i++) {
            id += possible.charAt(Math.floor(Math.random() * possible.length));
        }
    } while (checkPetCatIDExisted(id));
    $('#petCatIDIns').val(id);
}
function checkPetCatIDExisted(id) {
    $.ajax({
        type: "POST",
        url: "checkPetCatIDExistedAction.action",
        dataType: 'json',
        data: "petCatIDIns=" + id,
        success: function (data) {
            if (data.isExisted === true) {
                return true;
            } else {
                return false;
            }
        },
        error: function () {
            alert("Some error occured at checkPetCatIDExisted.");
        }
    });
}
function insertPetCat() {
    $.ajax({
        type: "POST",
        url: "insertPetCatAction.action",
        dataType: 'json',
        data: "petCatIDIns=" + $('#petCatIDIns').val() + "&petTypeIns=" + $('#petTypeIns').val(),
        success: function (data) {
            alert(data.msg);
            $('#petTypeIns').val('');
            generatePetCatID();
            searchPetCatByLikeType();
        },
        error: function () {
            alert("Insert pet category failed.");
        }
    });
}
function loadPetCat(petCatID, petType) {
    $('#petCatIDUpdt').val(petCatID);
    $('#petTypeUpdt').val(petType);
}
function searchPetCatByLikeType() {
    $.ajax({
        type: "POST",
        url: "findPetCatByLikeTypeAction.action",
        dataType: 'json',
        data: "petCatSearch=" + $('#petCatSearch').val(),
        success: function (data) {
            if (data !== null) {
                if (data.list !== null && data.list.length !== 0) {
                    $('#tbodypetCatSearch').empty();
                    $('#petCatSearch-not-found').css("display", "none");
                    $('#petCatTable').css("display", "table");
                    $.each(data.list, function () {
                        var row = document.createElement("tr");
                        var tdID = document.createElement("td");
                        var tdType = document.createElement("td");
                        var tdEdit = document.createElement("td");
                        var tdDelete = document.createElement("td");
                        var editLink = document.createElement("a");
                        var deleteLink = document.createElement("a");
                        tdID.innerHTML = this.petCatID;
                        tdType.innerHTML = this.petType;
                        editLink.href = "#petCatTable";
                        editLink.setAttribute("data-toggle", "modal");
                        editLink.setAttribute("data-target", "#modalUpdtPetCatForm");
                        editLink.style = "cursor: pointer";
                        editLink.addEventListener("click", function () {
                            loadPetCat(tdID.innerHTML, tdType.innerHTML);
                        });
                        editLink.innerHTML = "Edit";
                        deleteLink.href = "#petCatTable";
                        deleteLink.style = "cursor: pointer";
                        deleteLink.addEventListener("click", function () {
                            deletePetCat(tdID.innerHTML);
                        });
                        deleteLink.innerHTML = "Delete";
                        tdEdit.appendChild(editLink);
                        tdDelete.appendChild(deleteLink);
                        row.appendChild(tdID);
                        row.appendChild(tdType);
                        row.appendChild(tdEdit);
                        row.appendChild(tdDelete);
                        $('#tbodypetCatSearch').append(row);
                    });
                } else {
                    $('#petCatSearch-not-found').css("display", "block");
                    $('#petCatTable').css("display", "none");
                }
            } else {
                $('#petCatSearch-not-found').css("display", "block");
                $('#petCatTable').css("display", "none");
            }
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
function updatePetCat() {
    $.ajax({
        type: "POST",
        url: "updatePetCatAction.action",
        dataType: 'json',
        data: "petCatIDUpdt=" + $('#petCatIDUpdt').val() + "&petTypeUpdt=" + $('#petTypeUpdt').val(),
        success: function (data) {
            alert(data.msg);
            searchPetCatByLikeType();
        },
        error: function () {
            alert("Update pet category failed.");
        }
    });
}
function deletePetCat(id) {
    $.ajax({
        type: "POST",
        url: "deletePetCatAction.action",
        dataType: 'json',
        data: "petCatIDDelete=" + id,
        success: function (data) {
            alert(data.msg);
            searchPetCatByLikeType();
        },
        error: function () {
            alert("Delete pet category failed. It may contains some other categories.");
        }
    });
}
$(function () {
    searchPetCatByLikeType();
    $('#form-pet-cat-search').submit(function (e) {
        e.preventDefault();
    });
    $('#form-pet-cat-ins').validate({
        rules: {
            petTypeIns: {
                required: true
            }
        },
        messages: {
            petTypeIns: {
                required: "Pet type cannot be epmty.",
                pattern: "Pet type must be 1-40 characters."
            }
        },
        submitHandler: function () {
            insertPetCat();
        }
    });
    $('#form-pet-cat-updt').validate({
        rules: {
            petTypeUpdt: {
                required: true
            }
        },
        messages: {
            petTypeUpdt: {
                required: "Pet type cannot be epmty.",
                pattern: "Pet type must be 1-40 characters."
            }
        },
        submitHandler: function () {
            updatePetCat();
        }
    });
});
//End Pet category

//Accessory category
function generateAccCatID() {
    var id = "AC";
    const possible = "0123456789";
    do {
        for (let i = 0; i < 3; i++) {
            id += possible.charAt(Math.floor(Math.random() * possible.length));
        }
    } while (checkAccCatIDExisted(id));
    $('#accCatIDIns').val(id);
}
function checkAccCatIDExisted(id) {
    $.ajax({
        type: "POST",
        url: "checkAccCatIDExistedAction.action",
        dataType: 'json',
        data: "accCatIDIns=" + id,
        success: function (data) {
            if (data.isExisted === true) {
                return true;
            } else {
                return false;
            }
        },
        error: function () {
            alert("Some error occured at checkAccCatIDExisted.");
        }
    });
}
function insertAccCat() {
    $.ajax({
        type: "POST",
        url: "insertAccCatAction.action",
        dataType: 'json',
        data: "accCatIDIns=" + $('#accCatIDIns').val() + "&accCatNameIns=" + $('#accCatNameIns').val() + "&petCatIDInsFr=" + $('#petCatIDInsFr').val(),
        success: function (data) {
            alert(data.msg);
            $('#accCatNameIns').val('');
            generateAccCatID();
            searchAccCatByLikeName();
        },
        error: function () {
            alert("Insert accessory category failed.");
        }
    });
}
function loadAccCat(accCatID, accCatName, petCatID) {
    $('#accCatIDUpdt').val(accCatID);
    $('#accCatNameUpdt').val(accCatName);
    $('#petCatIDUpdtFr').val(petCatID);
}
function searchAccCatByLikeName() {
    $.ajax({
        type: "POST",
        url: "findAccCatByLikeNameAction.action",
        dataType: 'json',
        data: "accCatSearch=" + $('#accCatSearch').val(),
        success: function (data) {
            if (data !== null) {
                if (data.list !== null && data.list.length !== 0) {
                    $('#tbodyaccCatSearch').empty();
                    $('#accCatSearch-not-found').css("display", "none");
                    $('#accCatTable').css("display", "table");
                    $.each(data.list, function () {
                        var row = document.createElement("tr");
                        var tdID = document.createElement("td");
                        var tdCatName = document.createElement("td");
                        var tdPetType = document.createElement("td");
                        var tdEdit = document.createElement("td");
                        var tdDelete = document.createElement("td");
                        var editLink = document.createElement("a");
                        var deleteLink = document.createElement("a");
                        tdID.innerHTML = this.accCatID;
                        tdCatName.innerHTML = this.accCatName;
                        tdPetType.innerHTML = this.petType;
                        var petCatId = this.petCatID;
                        editLink.href = "#accCatTable";
                        editLink.setAttribute("data-toggle", "modal");
                        editLink.setAttribute("data-target", "#modalUpdtAccCatForm");
                        editLink.style = "cursor: pointer";
                        editLink.addEventListener("click", function () {
                            loadCbPetAccessory('petCatIDUpdtFr');
                            loadAccCat(tdID.innerHTML, tdCatName.innerHTML, petCatId);  
                        });
                        editLink.innerHTML = "Edit";
                        deleteLink.href = "#accCatTable";
                        deleteLink.style = "cursor: pointer";
                        deleteLink.addEventListener("click", function () {
                            deleteAccCat(tdID.innerHTML);
                        });
                        deleteLink.innerHTML = "Delete";
                        tdEdit.appendChild(editLink);
                        tdDelete.appendChild(deleteLink);
                        row.appendChild(tdID);
                        row.appendChild(tdCatName);
                        row.appendChild(tdPetType);
                        row.appendChild(tdEdit);
                        row.appendChild(tdDelete);
                        $('#tbodyaccCatSearch').append(row);
                    });
                } else {
                    $('#accCatSearch-not-found').css("display", "block");
                    $('#accCatTable').css("display", "none");
                }
            } else {
                $('#accCatSearch-not-found').css("display", "block");
                $('#accCatTable').css("display", "none");
            }
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
function updateAccCat() {
    $.ajax({
        type: "POST",
        url: "updateAccCatAction.action",
        dataType: 'json',
        data: "accCatIDUpdt=" + $('#accCatIDUpdt').val() + "&accCatNameUpdt=" + $('#accCatNameUpdt').val() + "&petCatIDUpdtFr=" + $('#petCatIDUpdtFr').val(),
        success: function (data) {
            alert(data.msg);
            searchAccCatByLikeName();
        },
        error: function () {
            alert("Update accessory category failed.");
        }
    });
}
function deleteAccCat(id) {
    $.ajax({
        type: "POST",
        url: "deleteAccCatAction.action",
        dataType: 'json',
        data: "accCatIDDelete=" + id,
        success: function (data) {
            alert(data.msg);
            searchAccCatByLikeName();
        },
        error: function () {
            alert("Delete accessory category failed. It may contains some accessories.");
        }
    });
}
$(function () {
    searchAccCatByLikeName();
    $('#form-acc-cat-search').submit(function (e) {
        e.preventDefault();
    });
    $('#form-acc-cat-ins').validate({
        rules: {
            accCatNameIns: {
                required: true
            }
        },
        messages: {
            accCatNameIns: {
                required: "Accessory caterogy name cannot be epmty.",
                pattern: "Accessory category name must be 1-40 characters."
            }
        },
        submitHandler: function () {
            insertAccCat();
        }
    });
    $('#form-acc-cat-updt').validate({
        rules: {
            accCatNameUpdt: {
                required: true
            }
        },
        messages: {
            accCatNameUpdt: {
                required: "Accessory caterogy name cannot be epmty.",
                pattern: "Accessory category name must be 1-40 characters."
            }
        },
        submitHandler: function () {
            updateAccCat();
        }
    });
});
//End Accessory category
