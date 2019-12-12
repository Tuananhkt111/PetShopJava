<%@include file="../admin/adminheader.jsp" %>
<div class="page-top-info">
    <div class="container">
        <h4>Accessory Management</h4>
        <div class="site-pagination">
            <a href="../admin/admin.jsp">Admin</a> /
            <a href="#">Accessory</a>
        </div>
    </div>
</div>
<div class="d-flex flex-row mb-5 justify-content-center">
    <div class="col-8 mt-4" id="acc-body">
        <h2 class="text-center mb-4">Accessory</h2>
        <a data-toggle="modal" data-target="#modalInsAccForm"  style="cursor:pointer;" onclick="generateAccID();loadCbAccCategory('accCatIDIns');">Insert new accessory</a>  
        <div class="modal fade" id="modalInsAccForm" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <h3 class="text-center mt-2" style="color: #00cc33">Insert Accessory</h3>
                    <form class="modal-body" id="form-acc-ins">
                        <div class="md-form form-sm mb-3">                        
                            <i class="fa fa-id-card"></i>
                            <label for="accIDIns">AccID</label>
                            <div class="d-flex flex-row align-items-center">
                                <input type="text" name="accIDIns" id="accIDIns" class="form-control form-control-sm mr-2" disabled>
                                <input type="button" class="btn btn-primary" onclick="generateAccID()" value="Generate"/>
                            </div>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="accNameIns">Accessory Name</label>
                            <input type="text" name="accNameIns" id="accNameIns" class="form-control form-control-sm" pattern=".{1,40}">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="accCatIDIns">Accessory Category</label>
                            <select name="accCatIDIns" id="accCatIDIns" class="form-control form-control-sm"></select>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-address-book"></i>
                            <label for="brandIns">Brand</label>
                            <input type="text" name="brandIns" id="brandIns" class="form-control form-control-sm" pattern=".{1,40}"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="desIns">Description</label>
                            <input type="text" name="desIns" id="desIns" class="form-control form-control-sm" pattern=".{1,500}"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-dollar"></i>
                            <label for="priceIns">Price</label>
                            <input type="number" name="priceIns" id="priceIns" class="form-control form-control-sm" min="1" maxlength="13" step="any"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-calendar-o"></i>
                            <label for="startSellingDateIns">Start Selling Date</label>
                            <input type="date" name="startSellingDateIns" id="startSellingDateIns" class="form-control form-control-sm"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-file-picture-o"></i>
                            <label for="imageIns">Image</label>
                            <input type="text" name="imageIns" id="imageIns" class="form-control form-control-sm" pattern=".+\.(jpg)"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="availableQuantityIns">Available Quantity</label>
                            <input type="number" name="availableQuantityIns" id="availableQuantityIns" class="form-control form-control-sm" min="0" maxlength="13" step="1"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-percent"></i>
                            <label for="salePercentIns">Sale Percent</label>
                            <input type="number" name="salePercentIns" id="salePercentIns" class="form-control form-control-sm" min="0" max="1" step="any"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="isDeleteIns">isDeleted</label>
                            <select name="isDeleteIns" id="isDeleteIns" class="form-control form-control-sm">
                                <option value="false" selected>False</option>
                                <option value="true">True</option>
                            </select>
                        </div>
                        <div class="text-center mt-2">
                            <input type="submit" class="btn btn-success" value="Insert"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <form class="d-flex flex-column mt-2 align-items-start " id="form-acc-search">
            <input type="text" name="accSearch" id="accSearch" class="form-control form-control-sm mt-2" placeholder="Enter accessory name">
            <div class="d-flex flex-row mt-2 mb-2">
                <div class="mr-2 d-flex flex-row">
                    <span class="mr-2 mt-2">isDeleted:</span>
                    <select name="isDeleteSearch" id="isDeleteSearch" class="form-control form-control-sm mt-1">
                        <option value="All" selected>All</option>
                        <option value="True">True</option>
                        <option value="False">False</option>
                    </select>
                </div>
                <input type="button" class="btn btn-success" value="Search" onclick="searchAccByLikeName()"/>
            </div>
        </form>
        <div class="modal fade" id="modalUpdtAccForm" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <h3 class="text-center mt-2" style="color: #00cc33">Update Accessory</h3>
                    <form class="modal-body" id="form-acc-updt">
                        <div class="md-form form-sm mb-3">                        
                            <i class="fa fa-id-card"></i>
                            <label for="accIDUpdt">AccID</label>
                            <div class="d-flex flex-row align-items-center">
                                <input type="text" name="accIDUpdt" id="accIDUpdt" class="form-control form-control-sm mr-2" disabled>
                                <input type="button" class="btn btn-primary" onclick="generateAccID()" value="Generate"/>
                            </div>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="accNameUpdt">Accessory Name</label>
                            <input type="text" name="accNameUpdt" id="accNameUpdt" class="form-control form-control-sm" pattern=".{1,40}">
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="accCatIDUpdt">Accessory Category</label>
                            <select name="accCatIDUpdt" id="accCatIDUpdt" class="form-control form-control-sm"></select>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-address-book"></i>
                            <label for="brandUpdt">Brand</label>
                            <input type="text" name="brandUpdt" id="brandUpdt" class="form-control form-control-sm" pattern=".{1,40}"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="desUpdt">Description</label>
                            <input type="text" name="desUpdt" id="desUpdt" class="form-control form-control-sm" pattern=".{1,500}"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-dollar"></i>
                            <label for="priceUpdt">Price</label>
                            <input type="number" name="priceUpdt" id="priceUpdt" class="form-control form-control-sm" min="1" maxlength="13" step="any"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-calendar-o"></i>
                            <label for="startSellingDateUpdt">Start Selling Date</label>
                            <input type="date" name="startSellingDateUpdt" id="startSellingDateUpdt" class="form-control form-control-sm"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-file-picture-o"></i>
                            <label for="imageUpdt">Image</label>
                            <input type="text" name="imageUpdt" id="imageUpdt" class="form-control form-control-sm" pattern=".+\.(jpg)"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="availableQuantityUpdt">Available Quantity</label>
                            <input type="number" name="availableQuantityUpdt" id="availableQuantityUpdt" class="form-control form-control-sm" min="0" maxlength="13" step="1"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-percent"></i>
                            <label for="salePercentUpdt">Sale Percent</label>
                            <input type="number" name="salePercentUpdt" id="salePercentUpdt" class="form-control form-control-sm" min="0" max="1" step="any"/>
                        </div>
                        <div class="md-form form-sm mb-3">
                            <i class="fa fa-pencil"></i>
                            <label for="isDeleteUpdt">isDeleted</label>
                            <select name="isDeleteUpdt" id="isDeleteUpdt" class="form-control form-control-sm">
                                <option value="false">False</option>
                                <option value="true">True</option>
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
<div class="mt-2 col-12 m-auto mb-5">
    <h4 id="accSearch-not-found" style="display: none; color: #0062cc">No accessory found</h4>
    <div style="position: relative; overflow: auto; height: 600px; display: block;">
        <table border="1" class="table table-hover table-striped animated fadeIn" style="display: none;" id="accTable">
            <thead class="thead-dark">
                <tr>
                    <th>AccID</th>
                    <th>AccName</th>
                    <th>AccCatName</th>
                    <th>Brand</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>StartSellingDate</th>
                    <th>Image</th>
                    <th>Available Quantity</th>
                    <th>Sale Percent</th>
                    <th>isDelete</th>
                    <th>Edit</th>
                </tr>
            </thead>
            <tbody id="tbodyaccSearch">               
            </tbody>
        </table>
    </div>
</div>
<br>
<%@include file="../admin/adminfooter.jsp" %>
<script src="../js/accessoryAdmin.js"></script>
</body>
</html>