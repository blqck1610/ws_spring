
var page = document.querySelector('.page');
function goToPage(numPage) {
    page.value = numPage;
    console.log(page.value);
    this.form.submit();
}

