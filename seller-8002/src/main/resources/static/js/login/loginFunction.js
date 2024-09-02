
function showValidate() {
    $('#validate').show();
    $('#login').hide();
    $('#returnBtn').removeClass('hidden');
    $('#dismissBtn').addClass('hidden');
}

function returnLogin() {
    $('#returnBtn').addClass('hidden');
    $('#dismissBtn').removeClass('hidden');
    $('#validate').hide();
    $('#login').show();
}

