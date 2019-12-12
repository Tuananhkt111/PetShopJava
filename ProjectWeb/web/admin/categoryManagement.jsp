<%@include file="../admin/adminheader.jsp" %>
<div class="page-top-info">
    <div class="container">
        <h4>Category Management</h4>
        <div class="site-pagination">
            <a href="../admin/admin.jsp">Admin</a> /
            <a href="#">Category</a>
        </div>
    </div>
</div>
<div class="d-flex flex-row mb-5">
    <div class="col-6 mt-4" id="pet-cat-body">
        <h2 class="text-center mb-4">Pet Category</h2>
        <a data-toggle="modal" data-target="#modalInsPetCatForm"  style="cursor:pointer;" onclick="generatePetCatID()">Insert new pet category</a>  
        <div class="modal fade" id="modalInsPetCatForm" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <h3 class="text-center mt-2" style="color: #00cc33">Insert Pet Category</h3>
                    <form class="modal-body" id="form-pet-cat-ins">
                        <div class="md-form form-sm mb-3">                        
                            <i class="fa fa-id-card"></i>
                            <label for="petCatIDIns">PetCatID</label>
                            <div class="d-flex flex-row align-items-center">
                                <input type="text" name="petCatIDIns" id="petCatIDIns" class="form-control form-control-sm mr-2" disabled>
                                <input type="button" class="btn btn-primary" onclick="generatePetCatID()" value="Generate"/>
                            </div>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="petTypeIns">Pet Type</label>
                            <input type="text" name="petTypeIns" id="petTypeIns" class="form-control form-control-sm" pattern=".{1,40}">
                        </div>
                        <div class="text-center mt-2">
                            <input type="submit" class="btn btn-success" value="Insert"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <form class="d-flex flex-column mt-2 align-items-start " id="form-pet-cat-search">
            <input type="text" name="petCatSearch" id="petCatSearch" class="form-control form-control-sm mt-2" placeholder="Enter pet type">
            <input type="button" class="btn btn-success mt-2 mb-2" value="Search" onclick="searchPetCatByLikeType()"/>
        </form>
        <div class="mt-2">
            <h4 id="petCatSearch-not-found" style="display: none; color: #0062cc">No pet category found</h4>
            <div style="position: relative; overflow: auto; height: 400px; display: block;">
                <table border="1" class="table table-hover table-striped table-dark animated fadeIn" style="display: none;" id="petCatTable">
                    <thead>
                        <tr>
                            <th>PetCatID</th>
                            <th>PetType</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody id="tbodypetCatSearch">               
                    </tbody>
                </table>
            </div>
            <div class="modal fade" id="modalUpdtPetCatForm" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <h3 class="text-center mt-2" style="color: #00cc33">Update Pet Category</h3>
                        <form class="modal-body" id="form-pet-cat-updt">
                            <div class="md-form form-sm mb-3">
                                <i class="fa fa-id-card"></i>
                                <label for="petCatIDUpdt">PetCatID</label>
                                <input type="text" name="petCatIDUpdt" id="petCatIDUpdt" class="form-control form-control-sm mr-2" disabled>
                            </div>
                            <div class="md-form form-sm mb-3">
                                <i class="fa fa-pencil"></i>
                                <label for="petTypeUpdt">Pet Type</label>
                                <input type="text" name="petTypeUpdt" id="petTypeUpdt" class="form-control form-control-sm" pattern=".{1,40}">
                            </div>
                            <div class="text-center mt-2">
                                <input type="submit" class="btn btn-success" value="Update"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-6 mt-4" id="acc-cat-body">
        <h2 class="text-center mb-4">Accessory Category</h2>
        <a data-toggle="modal" data-target="#modalInsAccCatForm"  style="cursor:pointer;" onclick="generateAccCatID(); loadCbPetAccessory('petCatIDInsFr');">Insert new accessory category</a>  
        <div class="modal fade" id="modalInsAccCatForm" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <h3 class="text-center mt-2" style="color: #00cc33">Insert Accessory Category</h3>
                    <form class="modal-body" id="form-acc-cat-ins">
                        <div class="md-form form-sm mb-3">                        
                            <i class="fa fa-id-card"></i>
                            <label for="petCatIDIns">AccCatID</label>
                            <div class="d-flex flex-row align-items-center">
                                <input type="text" name="accCatIDIns" id="accCatIDIns" class="form-control form-control-sm mr-2" disabled>
                                <input type="button" class="btn btn-primary" onclick="generateAccCatID()" value="Generate"/>
                            </div>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="accCatNameIns">Accessory Category Name</label>
                            <input type="text" name="accCatNameIns" id="accCatNameIns" class="form-control form-control-sm" pattern=".{1,40}">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="petCatIDInsFr">Pet Type</label>
                            <select name="petCatIDInsFr" id="petCatIDInsFr" class="form-control form-control-sm">
                            </select>
                        </div>
                        <div class="text-center mt-2">
                            <input type="submit" class="btn btn-success" value="Insert"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <form class="d-flex flex-column mt-2 align-items-start " id="form-acc-cat-search">
            <input type="text" name="accCatSearch" id="accCatSearch" class="form-control form-control-sm mt-2" placeholder="Enter accessory category name">
            <input type="button" class="btn btn-success mt-2 mb-2" value="Search" onclick="searchAccCatByLikeName()"/>
        </form>
        <div class="mt-2">
            <h4 id="accCatSearch-not-found" style="display: none; color: #0062cc">No accessory category found</h4>
            <div style="position: relative; overflow: auto; height: 400px; display: block;">
                <table border="1" class="table table-hover table-striped table-dark animated fadeIn" style="display: none;" id="accCatTable">
                    <thead>
                        <tr>
                            <th>AccCatID</th>
                            <th>AccCatName</th>
                            <th>PetType</th>
                            <th>Edit</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody id="tbodyaccCatSearch">               
                    </tbody>
                </table>
            </div>
            <div class="modal fade" id="modalUpdtAccCatForm" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <h3 class="text-center mt-2" style="color: #00cc33">Update Accessory Category</h3>
                        <form class="modal-body" id="form-acc-cat-updt">
                            <div class="md-form form-sm mb-3">
                                <i class="fa fa-id-card"></i>
                                <label for="accCatIDUpdt">AccCatID</label>
                                <input type="text" name="accCatIDUpdt" id="accCatIDUpdt" class="form-control form-control-sm mr-2" disabled>
                            </div>
                            <div class="md-form form-sm mb-3">
                                <i class="fa fa-pencil"></i>
                                <label for="accCatNameUpdt">Accessory Category Name</label>
                                <input type="text" name="accCatNameUpdt" id="accCatNameUpdt" class="form-control form-control-sm" pattern=".{1,40}">
                            </div>
                            <div class="md-form form-sm mb-3">
                                <i class="fa fa-pencil"></i>
                                <label for="petCatIDUpdtFr">Pet Type</label>
                                <select name="petCatIDUpdtFr" id="petCatIDUpdtFr" class="form-control form-control-sm">
                                </select>
                            </div>
                            <div class="text-center mt-2">
                                <input type="submit" class="btn btn-success" value="Update"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../admin/adminfooter.jsp" %>
<script src="../js/categoryAdmin.js"></script>
</body>
</html>