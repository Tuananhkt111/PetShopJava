<%@include file="../admin/adminheader.jsp" %>
<div class="page-top-info">
    <div class="container">
        <h4>Accessory Management</h4>
        <div class="site-pagination">
            <a href="../admin/admin.jsp">Admin</a> /
            <a href="#">Accessory Transaction Management</a>
        </div>
    </div>
</div>
<div class="d-flex flex-row mb-5 justify-content-center">
    <div class="col-8 mt-4" id="acc-trans-body">
        <h2 class="text-center mb-4">Accessory Transaction</h2>
        <form class="d-flex flex-column mt-2 align-items-start " id="form-acc-trans-search">
            <input type="text" name="usernameSearch" id="usernameSearch" class="form-control form-control-sm mt-2" placeholder="Enter username" value="">
            <div class="d-flex flex-row mt-2 mb-2">
                <div class="mr-2 d-flex flex-row">
                    <span class="mr-2 mt-2">Status:</span>
                    <select name="statusSearch" id="statusSearch" class="form-control form-control-sm mt-1">
                        <option value="All" selected>All</option>
                        <option value="Waiting for response">Waiting for response</option>
                        <option value="Waiting for payment">Waiting for payment</option>
                        <option value="Finished">Finished</option>
                        <option value="Canceled">Canceled</option>
                    </select>
                </div>
                <input type="button" class="btn btn-success" value="Search" onclick="searchTran()"/>
            </div>
        </form>
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
</div>
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
<%@include file="../admin/adminfooter.jsp" %>
<script src="../js/accessoryTranAdmin.js"></script>
</body>
</html>
