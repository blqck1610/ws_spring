var showMoreBtn = document.querySelector('.js-show-more-btn');

var showLessBtn = document.querySelector('.js-show-less-btn');

var moreDetail = document.querySelector('.js-more-details');

showMoreBtn.onclick = function() {
    event.preventDefault()
    
    moreDetail.style.overflow = 'visible';
    moreDetail.style.height = 'auto';
    showMoreBtn.classList.add('hide-modal');
    showLessBtn.classList.remove('hide-modal');
}
showLessBtn.onclick = function() {
    event.preventDefault()
    moreDetail.style.overflow = 'hidden';
    moreDetail.style.height = '400px';
    showMoreBtn.classList.remove('hide-modal');
    showLessBtn.classList.add('hide-modal');
}

//img product

var subImgs = document.querySelectorAll('.sub-img');
var mainImage = document.querySelector('.main-img');
for(var i = 0; i < subImgs.length; i++){
    subImgs[i].addEventListener('click', function(){
        console.log('a');
        mainImage.src = this.src;
    })
}
// rate
var stars = document.querySelectorAll('.js-rate-star');

for(var i = 0; i < 4.4 - 1; i++){  
    stars[i].classList.add('star-active');
}