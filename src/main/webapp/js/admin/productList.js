function res(){
    var widthFoto = $('.prodFoto').width();
    $('.prodFoto').height(widthFoto);
}
res();
$( window ).resize(function() {
    res();
});

var id;
var name;
$('[id^=deleteProductBtn]').on('click', function f() {
    id=this.getAttribute('data-id');
    name=this.getAttribute('data-name');
    $('.deleteProdName').html(name);
    $("input[name='productId']").attr('value',id);
    $('#prodDelete').css('display', 'block');
});
$('#DeleteProdCloseBtn').on('click', function () {
    $('#prodDelete').css('display', 'none');
});
$('.delProdBtn').on( 'click', function () {
    $('#prodDelete').css('display', 'none');
    var $form = $('#delProdForm');
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize()
    }).done(function() {
        $('#prodItem'+id).remove();
        console.log('success');
    }).fail(function() {
        console.log('fail');
    });


});