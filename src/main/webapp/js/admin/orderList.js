/*изменение способа оплаты */
var id;
var сhangeUsModal=document.getElementById("changePaymentMethod");
$('.paymentMethodBtn').on('click', function () {

    id=this.getAttribute('data-id');
    $("input[name='orderIdChangePaymentMethod']").attr('value',id);
    $('.changePaymentMethodOrderId').html(id);
    сhangeUsModal.style.display = "block";
});
$('#changePaymentMethodCloseBtn').on('click', function () {
    сhangeUsModal.style.display = "none";
});
$('.changePaymentMethodBtn').on( 'click', function () {
    сhangeUsModal.style.display = "none";
    var $form = $('#changePaymentMethodForm');
    var paymentMethod=$("#paymentMethod").val();
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize()
    }).done(function() {
        $('[data-tardetId = '+id+']').html(paymentMethod);
        console.log('success');
    }).fail(function() {
        console.log('fail');
    });


});
$('#sel1').click(function () {
    $("#paymentMethod").val("CASH");
    $("#sel1").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel3").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel2').click(function () {
    $("#paymentMethod").val("CARD");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel3").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel3').click(function () {
    $("#paymentMethod").val("CARD_ONLINE");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel3").css("background-color", "rgba(196, 55, 211, 0.5)");
});

/*изменение статуса оплаты */
var сhangePaymentStatusModal=document.getElementById("changePaymentStatus");
$('.paymentStatusBtn').on('click', function () {

    id=this.getAttribute('data-id');
    $("input[name='orderIdChangePaymentStatus']").attr('value',id);
    $('.changePaymentStatusOrderId').html(id);
    сhangePaymentStatusModal.style.display = "block";
});
$('#changePaymentStatusCloseBtn').on('click', function () {
    сhangePaymentStatusModal.style.display = "none";
});
$('.changePaymentStatusBtn').on( 'click', function () {
    сhangePaymentStatusModal.style.display = "none";
    var $form = $('#changePaymentStatusForm');
    var paymentMethod=$("#paymentStatus").val();
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize()
    }).done(function() {
        $('[data-paymentStatusId = '+id+']').html(paymentMethod);
        console.log('success');
    }).fail(function() {
        console.log('fail');
    });


});
$('#zel1').click(function () {
    $("#paymentStatus").val("DONT_PAYED");
    $("#zel1").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#zel2").css("background-color", "rgba(114, 119, 132, 0.7)");

});
$('#zel2').click(function () {
    $("#paymentStatus").val("PAYED");
    $("#zel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#zel2").css("background-color", "rgba(196, 55, 211, 0.5)");

});



/*изменение статуса заказа */
var сhangeOrderStatusModal=document.getElementById("changeOrderStatus");
$('.orderStatusBtn').on('click', function () {

    id=this.getAttribute('data-id');
    $("input[name='orderIdChangeOrderStatus']").attr('value',id);
    $('.changeOrderStatusOrderId').html(id);
    сhangeOrderStatusModal.style.display = "block";
});
$('#changeOrderStatusCloseBtn').on('click', function () {
    сhangeOrderStatusModal.style.display = "none";
});
$('.changeOrderStatusBtn').on( 'click', function () {
    сhangeOrderStatusModal.style.display = "none";
    var $form = $('#changeOrderStatusForm');
    var orderMethod=$("#orderStatus").val();
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize()
    }).done(function() {
        $('[data-orderStatusId = '+id+']').html(orderMethod);
        console.log('success');
    }).fail(function() {
        console.log('fail');
    });


});
$('#xel1').click(function () {
    $("#orderStatus").val("REGISTERED");
    $("#xel1").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#xel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#xel3").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#xel2').click(function () {
    $("#orderStatus").val("CONFIRMED");
    $("#xel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#xel2").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#xel3").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#xel3').click(function () {
    $("#orderStatus").val("COMPLETED");
    $("#xel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#xel2").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#xel3").css("background-color", "rgba(196, 55, 211, 0.5)");
});

/*удалние заказа */
var deleteOrderModal=document.getElementById("deleteOrder");
$('.orderDeleteBtn').on('click', function () {

    id=this.getAttribute('data-id');
    $("input[name='orderIdDeleteOrder']").attr('value',id);
    $('.deleteOrderOrderId').html(id);
    deleteOrderModal.style.display = "block";
});
$('#deleteOrderCloseBtn').on('click', function () {
    deleteOrderModal.style.display = "none";
});
$('.deleteOrderBtn').on( 'click', function () {
    deleteOrderModal.style.display = "none";
    var $form = $('#deleteOrderForm');
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize()
    }).done(function() {
        $('#orderList'+id).remove();
        $('#mn').after("<div  class=\"row\">\n" +
            "        <div class=\"col\">"+
            "<div id=\"mainText\" class=\"row\">\n" +
            "        <div class=\"col\">\n" +
            "            Заказ №"+id+" удален!" +
            "        </div>\n" +
            "    </div>"+"    </div>"+"    </div>");
        console.log('success');
    }).fail(function() {
        console.log('fail');
    });


});