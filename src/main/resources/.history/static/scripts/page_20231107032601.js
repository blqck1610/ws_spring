
var page = document.querySelector('.page');
var formS = document.getElementById('filter-form');
function goToPage(numPage ) {
    page.value = numPage;
   formS.submit();
  
}



