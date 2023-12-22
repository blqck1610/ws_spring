
var page = document.querySelector('.page');
var formS = document.getElementById('searchForm');
function goToPage(numPage ) {
    page.value = numPage;
   formS.form.submit();
  
}



