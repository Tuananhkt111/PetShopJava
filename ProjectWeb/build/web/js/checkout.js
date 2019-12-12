function calculateTotalPrice() {
    var total = 0;
    $('.current-price').each(function () {
        var arr = this.innerHTML.substring(1).split(" x ");
        var str = arr[0].replace(",", ".");
        var price = Number.parseFloat(str);
        var quantity = Number.parseInt(arr[1]);
        total += price * quantity;
    });
    total = Math.round(total * 100) / 100;
    $('.total-price').html("$" + total);
}
function errorImage() {
    this.onerror = null;
    this.src = "../img/product/noimage.jpg";
}
$(function () {
    calculateTotalPrice();
    $('#checkout-form').validate({
        rules: {
            deliveryAddress: {
                required: true
            },
            deliveryPhone: {
                required: true
            }
        },
        messages: {
            deliveryAddress: {
                required: "Address cannot be empty",
                pattern: "Address must be 1-70 characters."
            },
            deliveryPhone: {
                required: "Phone cannot be empty.",
                pattern: "Phone must be 10-16 numberic characters."
            }
        }
    });
});