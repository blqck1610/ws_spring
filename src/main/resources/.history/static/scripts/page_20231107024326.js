
var page = document.querySelector('.page');
function goToPage(numPage) {
    page.value = numPage;
    this.form.submit();
}

