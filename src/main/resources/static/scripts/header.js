

// show hide slide out
var btnSlideOutClose = document.querySelector('.js-btn-slide-out-close');
var overlay = document.querySelector('.js-overlay')
var slideOut = document.querySelector('.js-slide-out');
var loginForm = document.querySelector('.js-login-form');
var signInForm = document.querySelector('.js-sign-in-form');
var signInRequest = document.querySelector('.js-sign-in-request');
var loginRequest = document.querySelector('.js-login-request');
var userForm = document.querySelector('.js-user-form');


var accountTab = document.querySelector('.js-account-tab');
var cartTab = document.querySelector('.js-cart-tab');


var cartIcon = document.querySelector('.js-cart-icon');
var userIcon = document.querySelector('.js-user-icon');




btnSlideOutClose.onclick = function () {
	overlay.classList.add('hide-modal');
};

overlay.addEventListener('click', function () {
	overlay.classList.add('hide-modal');
});
slideOut.addEventListener('click', function (event) {
	event.stopPropagation();
});

userIcon.onclick = function () {
    overlay.classList.remove('hide-modal');
    showModalTab(null, 'account-tab');
    var tabLink = document.getElementById('js-account-tab-link');
    tabLink.className += ' active';
	
}

function openLoginForm(){
	console.log("aasdas");
	overlay.classList.remove('hide-modal');
    showModalTab(null, 'account-tab');
    var tabLink = document.getElementById('js-account-tab-link');
    tabLink.className += ' active';
}
// show signin form


loginRequest.onclick = function(event) {
	signInForm.classList.add('hide-modal');
	loginForm.classList.remove('hide-modal');
	event.preventDefault();
}






cartIcon.onclick = function () {
    overlay.classList.remove('hide-modal');
    showModalTab(null, 'cart-tab');
    var tabLink = document.getElementById('js-cart-tab-link');
    tabLink.className += ' active';
}




/* đéo biết để làm gì
*/
/*function showAccountForm(evt, tabname){
    var accountForms = document.querySelectorAll('.js-forms-account');

    for(var i=0; i<accountTabContent.length; i++){
        accountTabContents[i].classList.add('hide-modal');
    }

    document.getElementById(tabname).classList.remove('hide-modal');
}*/


function showModalTab(evt, tabname){
    
    var tabContents = document.querySelectorAll('.js-tab-contents');
   
    for(var i = 0; i < tabContents.length; i++){
        tabContents[i].classList.add('hide-modal');
    }

    var tabLinks = document.querySelectorAll('.js-tab-links');
    for(var i = 0; i < tabLinks.length; i++){
        tabLinks[i].classList.remove('active');
    }
    
    document.getElementById(tabname).classList.remove('hide-modal');
    
    
    
    if (evt != null) {
        evt.currentTarget.classList.add('active');
    }
}
function showLoginForm(){
	userForm.classList.add('hide-modal');
	loginForm.classList.remove('hide-modal');
}

// userIcon.addEventListener('click', showLoginForm());






