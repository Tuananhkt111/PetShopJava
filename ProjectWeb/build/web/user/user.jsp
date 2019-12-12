<%@include file="../guest/header.jsp" %>
<!-- Page info -->
<div class="page-top-info d-flex flex-row align-items-center">
    <div class="ml-4"><img src="../img/icons8-cloud-100.png"/></div>
    <div>
        <h4 style="color: #007fff"><s:property value="%{#session.USER}"/></h4>
        <div class="site-pagination">
            <a href="../guest/home.jsp">Home</a> /
            <a href="#">User</a>
        </div>
    </div>  
</div>
<div class="d-flex flex-row">
    <div class="d-flex flex-column col-2" style="padding-right: 0; padding-left: 0; background: #f2e9e9">
        <button class="btn-tab active text-center" onclick="openCity(event, 'profile')">
            <img src="../img/profile-icon-png-917.png" width="170px" height="auto"/>
            <span style="color: #007872; font-weight: bold">Profile</span>
        </button>
        <button class="btn-tab text-center" onclick="openCity(event, 'mycart')">
            <img src="../img/shopping-cart-304843_1280.png" width="150px" height="auto"/>
            <span style="color: #ff7f00; font-weight: bold">Transaction</span>
        </button>
        <button class="btn-tab" onclick="openCity(event, 'favoritelist')">Tokyo</button>
    </div>
    <div class="col-10" style="padding-right: 0; padding-left: 0">
        <div id="profile" class="tab-content">
            <h3 class="text-center mt-4" style="color: #007872">Your Profile</h3>
            <form class="modal-body" id="form-update-profile">
                <div  class="md-form form-sm mb-3">
                    <i class="fa fa-user"></i>
                    <label for="txtUsernameRg">Username</label>
                    <input type="text" name="txtUsernameUpdt" id="txtUsernameUpdt" class="form-control form-control-sm" 
                           pattern="(\d|\w){1,30}" disabled value="<s:property value="%{#session.USER}"/>"/>
                </div>
                <div class="md-form form-sm mb-3">
                    <i class="fa fa-user-circle"></i>
                    <label for="txtFullnameRg">Fullname</label>
                    <input type="text" id="txtFullnameUpd" name="txtFullnameUpd" class="form-control form-control-sm"
                           pattern=".{1,40}">
                </div>
                <div class="md-form form-sm mb-3">
                    <i class="fa fa-phone"></i>
                    <label for="txtPhoneRg">Phone</label>
                    <input type="text" id="txtPhoneUpdt" name="txtPhoneUpdt" class="form-control form-control-sm"
                           pattern="[0-9]{10,16}">
                </div>
                <div class="md-form form-sm mb-3">
                    <i class="fa fa-address-book"></i>
                    <label for="txtAddressRg">Address</label>
                    <input type="text" id="txtAddressUpdt" name="txtAddressUpdt" class="form-control form-control-sm"
                           pattern=".{1,70}">
                </div>
                <div class="text-center form-sm mt-2">
                    <button type="submit" class="btn btn-success" style="background: #007872"> Update </button>
                </div>
            </form>
        </div>

        <div id="mycart" class="tab-content">
            <div class="mt-2 col-12 m-auto mb-2">
                <h4 id="accTranSearch-not-found" style="display: none; color: #0062cc">No transactions found</h4>
                <div style="position: relative; overflow: auto; height: 700px; display: block;">
                    <table border="1" class="table table-hover table-striped animated fadeIn" style="display: none;" id="accTranTable">
                        <thead class="thead-dark">
                            <tr>
                                <th>AccTranID</th>
                                <th>Username</th>
                                <th>TimeOrder</th>
                                <th>TimeFinish</th>
                                <th>DeliveryPhone</th>
                                <th>DeliveryAddress</th>
                                <th>TotalPrice</th>
                                <th>View details</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody id="tbodyTranSearch">               
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal fade" id="modalAccTransForm" role="dialog">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <h3 class="text-center mt-2 mb-4" style="color: #00cc33">Transaction Details</h3>
                        <div class="mt-2 col-12 m-auto mb-2">
                            <h4 id="acc-not-found" style="display: none; color: #0062cc">No accessories found</h4>
                            <div style="position: relative; overflow: auto; height: 480px; display: block;">
                                <table border="1" class="table table-hover table-striped animated fadeIn" style="display: none;" id="accTable">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th>AccID</th>
                                            <th>AccName</th>
                                            <th>AccCatID</th>
                                            <th>Brand</th>
                                            <th>Description</th>
                                            <th>Price</th>
                                            <th>StartSellingDate</th>
                                            <th>Image</th>
                                            <th>SalePercent</th>
                                            <th>Quantity</th>
                                        </tr>
                                    </thead>
                                    <tbody id="tbodyAccTranSearch">               
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="favoritelist" style="display: flex; flex-flow: row wrap" class="tab-content">  
        </div>
    </div>
</div>
<!-- Page info end -->
<%@include file="../guest/footer.jsp" %>
<script src="../js/user.js"></script>
</body>
</html>
