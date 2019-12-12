function checkUsernameExisted() {
    $.ajax({
        type: "POST",
        url: "checkUsernameAction.action",
        dataType: 'json',
        data: "txtUsernameRg=" + $('#txtUsernameRg').val(),
        success: function (data) {
            if(data.isExisted === true) {
                $('#txtUsernameRg-exist').html("Username existed.");
            } else {
                 $('#txtUsernameRg-exist').html("");
            }         
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
function checkLogin() {
    $.ajax({
        type: "POST",
        url: "loginAction.action",
        dataType: 'json',
        data: "txtUsernameLg=" + $('#txtUsernameLg').val() + "&txtPasswordLg=" + $('#txtPasswordLg').val(),
        success: function (data) {
            if(data.role === "failed") {
                $('#login-result').html("Username or password incorrect.");
            } else if(data.role === "admin" || data.role === "user"){
                $('#login-result').html("");
                location.href = "../guest/home.jsp";
            } else {
                window.location = '../guest/error.jsp?txtError=Your role is not supported';
            }
        },
        error: function () {
            alert("Some error occured.");
        }
    });
}
$(function () {
    $('#form-login').validate({
        rules: {
            txtUsernameLg: {
                required: true
            },
            txtPasswordLg: {
                required: true
            }
        },
        messages: {
            txtUsernameLg: {
                required: "Username cannot be epmty.",
                pattern: "Username has max length is 30 characters, includes alphanumeric only."
            },
            txtPasswordLg: {
                required: "Password cannot be empty.",
                pattern: "Password must be 8 - 16 alphanumberic characters, including at least 1 uppercase letter, 1 lowercase letter."
            }
        },
        submitHandler: function() {
            checkLogin();
        }
    });
    
    $('#form-register').validate({
        rules: {
            txtUsernameRg: {
                required: true
            },
            txtPasswordRg: {
                required: true
            },
            txtRepPasswordRg: {
                required: true,
                equalTo: "#txtPasswordRg"
            },
            txtFullnameRg: {
                required: true
            },
            txtPhoneRg: {
                required: true
            },
            txtAddressRg: {
                required: true
            }
        },
        messages: {
            txtUsernameRg: {
                required: "Username cannot be epmty.",
                pattern: "Username has max length is 30 characters, includes alphanumeric only."
            },
            txtPasswordRg: {
                required: "Password cannot be empty.",
                pattern: "Password must be 8-16 alphanumberic characters, including at least 1 uppercase letter, 1 lowercase letter."
            },
            txtRepPasswordRg: {
                required: "Password cannot be empty.",
                pattern: "Password must be 8-16 alphanumberic characters, including at least 1 uppercase letter, 1 lowercase letter.",
                equalTo: "Repeat password doesn't match."
            },
            txtFullnameRg: {
                required: "Fullname cannot be empty.",
                pattern: "Fullname must be 1-40 characters."
            },
            txtPhoneRg: {
                required: "Phone cannot be empty.",
                pattern: "Phone must be 10-16 numberic characters."
            },
            txtAddressRg: {
                required: "Address cannot be empty.",
                pattern: "Address must be 1-70 characters."
            }
        }
    });
});




