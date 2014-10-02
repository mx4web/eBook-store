function showShoppingCartPopUp(data) {
    if (data.status === 'success') {
        $("#shoppingBagWindow").show('fast');
    }
}

function printpage() {
    window.print();
    return false;
}

function showReviewMessage(data) {
    if (data.status === 'success') {
        $("#reviewmessage").show('fast');
        $("#reviewform\\:newreviewtext").val('');
        $("#newreview [id*='newrating']").val('');
        $("#newrate").rateit('reset');
    }
}

$(function() {
    $("#menuCartForm").mouseover(function() {
        $("#shoppingBagWindow").show('fast');
    });
    $("button#continueShopping").click(function() {
        $("#shoppingBagWindow").hide('fast');
    });
});

