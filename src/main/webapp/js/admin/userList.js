/*удаление пользователя */
var delUsModal=document.getElementById("usDelete");
var userName;
var id;
$('.deleteUserBtn').on('click', function () {
    userName=this.getAttribute('data-name');
    id=this.getAttribute('data-id');
    $("input[name='userName']").attr('value',userName);
    $('.deleteUserName').html(userName);
    delUsModal.style.display = "block";
});
$('#orderDeleteUsCloseBtn').on('click', function () {
    delUsModal.style.display = "none";
});
$('.delUsBtn').on( 'click', function () {
    delUsModal.style.display = "none";
    var $form = $('#delUserForm');
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize()
    }).done(function() {
        $('#userList'+id).remove();
        $('#mainText').after("<div id=\"mainText\" class=\"row\">\n" +
            "        <div class=\"col\">\n" +
            "            Пользователь "+userName+" удален" +
            "        </div>\n" +
            "    </div>");
        console.log('success');
    }).fail(function() {
        console.log('fail');
    });


});
/*изменение Роли */
var сhangeUsModal=document.getElementById("usChangeRole");
$('.editRoleBtn').on('click', function () {
    userName=this.getAttribute('data-name');
    id=this.getAttribute('data-id');
    $("input[name='userNameChangeRole']").attr('value',userName);
    $('.changeRoleUserName').html(userName);
    сhangeUsModal.style.display = "block";
});
$('#orderChangeRoleUsCloseBtn').on('click', function () {
    сhangeUsModal.style.display = "none";
});
$('.changeRoleUsBtn').on( 'click', function () {
    сhangeUsModal.style.display = "none";
    var $form = $('#changeRoleUserForm');
    var role=$("#role").val();
    $.ajax({
        type: $form.attr('method'),
        url: $form.attr('action'),
        data: $form.serialize()
    }).done(function() {
        $('#userList'+id).find('.userRole').html(role);
        console.log('success');
    }).fail(function() {
        console.log('fail');
    });


});
$('#sel1').click(function () {
    $("#role").val("ADMIN");
    $("#sel1").css("background-color", "rgba(196, 55, 211, 0.5)");
    $("#sel2").css("background-color", "rgba(114, 119, 132, 0.7)");
});
$('#sel2').click(function () {
    $("#role").val("CLIENT");
    $("#sel1").css("background-color", "rgba(114, 119, 132, 0.7)");
    $("#sel2").css("background-color", "rgba(196, 55, 211, 0.5)");

});