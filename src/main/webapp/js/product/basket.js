// modal
var modal = document.getElementById("basket");

var btn = document.getElementById("basketBtn");

var span = document.getElementById("closeBtn");

var delModal=document.getElementById("orderDelete");

var btnDel = document.getElementsByClassName("deleteBtn");

var delspan = document.getElementById("orderDeleteCloseBtn");

btn.onclick = function() {
    modal.style.display = "block";
}
if(document.getElementById("createOrder") != undefined){
    document.getElementById("createOrder").onclick = function() {
        modal.style.display = "block";
    }
}

span.onclick = function() {
    modal.style.display = "none";
}

delspan.onclick = function() {
    delModal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

$('#basketBtn, #createOrder').on('click', function () {
    var path = document.getElementById('basketHidden').value;
    var URL= path+"/app/basket";
    $.ajax({
        url: URL,
        dataType : "json",
        contentType: "application/json; charset=utf-8",
        success: function (data, textStatus) {
            $.each(data, function(i, val) {
                var foto=path+"/img/noFoto.png";
                if (val.productFotoUrlList[0] !=undefined){
                    foto="/files/"+val.productFotoUrlList[0];
                }
                var html="<div id=\"orderItem"+val.id+"\" class=\"row\">\n" +
                    "                   <div class=\"col-lg-2 col-md-4 col-sm-6\">\n" +
                    "                       <img class=\"prodFoto\" src=\""+foto+"\">\n" +
                    "                   </div>\n" +
                    "                   <div class=\"col-lg-10 col-md-8 col-sm-6\">\n" +

                    "                       <span class=\"prodName\">"+val.productTitle +"</span>\n" +

                    "                       <span class=\"prodDes\">"+val.productDescription+"</span>\n" +
                    "                       <label class=\"prodSizeLab\">"+"Размер:" +"</label>\n" +
                    "                       <span class=\"prodSize\">"+val.orderItemSize +"</span>\n" +
                    "                       <label class=\"prodCostLab\">"+"Цена:" +"</label>\n" +
                    "                       <span class=\"prodCost\">"+val.productCost+" BYN/шт." +"</span>\n" +
                    "                       <label class=\"prodAmountLab\">"+"Количество:" +"</label>\n" +
                    "                       <span class=\"prodAmount\">"+val.orderItemAmount+" шт." +"</span>\n" +
                    "                       <label class=\"prodNoteLab\">"+"Примечание:" +"</label>\n" +
                    "                       <span class=\"prodNote\">"+val.orderItemNote+"</span>\n" +
                    "                       <label class=\"prodTotCostLab\">"+"Общая стоимость:" +"</label>\n" +
                    "                       <span class=\"totalCost\">"+val.productCost*val.orderItemAmount+" BYN"+"</span>\n" +
                    "                       <a class=\"editBtn\" id=\"editBasketBtn"+val.id+"\" href=\""+path+"/app/basket/edit?id="+val.id+"\">Редактировать</a>\n" +
                    "                       <a class=\"deleteBtn\" href=\"#\" data-id=\""+val.id+"\">Удалить из корзины</a>\n" +
                    "                   </div>\n" +
                    "               </div>";
                $('#basketBody').append(html);
                console.log(val);
            });
            if ($.trim(data)){
                html="<div id=\"createOrder\" class=\"row\">\n" +
                    "                <div class=\"col\">\n" +
                    "                    <a class=\"orderBtn\" href=\""+path+"/app/basket/order\">Оформить заказ</a>\n" +
                    "                </div>\n" +
                    "            </div>";
                $('#basketBody').append(html);
            }
            if(!$("#emptyBask").length && !$('#guestOrderItemList').length && !$.trim(data)) {
                html="            \n" +
                    "<div class=\"row\">\n" +
                    "    <div class=\"col\">\n" +
                    "        <div id=\"emptyBask\" class=\"emptyBasket\" >Корзина пуста</div>\n" +
                    "    </div>\n" +
                    "</div>";
                $('#basketBody').append(html);

            }


            fotoHeight();
        }
    });
    fotoHeight();
});
$(function() {
    $(document).on('click touchstart', '[id^=deleteFromBasketBtn]', function(){

        var id =parseInt($(this).attr('id').match(/\d+/)) ;
        var path = document.getElementById('basketHidden').value;
        var URL= path+"/app/basket/delete?id="+id;
        $.ajax({
            url: URL,
            success: function (data, textStatus) {
                console.log("seccess");

                $('#orderItem'+id).remove();
                if(!$('[id^=orderItem]').first().length){
                    $('#createOrder').remove();
                    $('.orderBtn').remove();
                    html="            \n" +
                        "<div class=\"row\">\n" +
                        "    <div class=\"col\">\n" +
                        "        <div id=\"emptyBask\" class=\"emptyBasket\">Корзина пуста</div>\n" +
                        "    </div>\n" +
                        "</div>";
                    $('#basketBody').append(html);
                }
            }
        });
    });
});
$('#closeBtn').click(function () {

        $('[id^=orderItem]').remove();
        $('#createOrder').remove();
        $('#emptyBask').remove();


});


function fotoHeight(){
    var width=$('.prodFoto').width();

    $('.prodFoto').css('height', width);
}

$(window).resize(function () {
    fotoHeight();
});







var id;


$(function() {
    $(document).on('click touchstart', '.deleteBtn', function(){
        id=this.getAttribute('data-id');
        $('.delBtn').attr('id',"deleteFromBasketBtn"+id);
        delModal.style.display = "block";
    });
});



$('.delBtn').on( 'click', function () {
    delModal.style.display = "none";
    console.log($('[id^=orderItem]'));
    $('#guestOrderItemList').remove();


});