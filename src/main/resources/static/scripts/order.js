var ordered = document.querySelectorAll('.orderd');
var i;
for (i = 0; i < ordered.length; i++) {
  ordered[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display === "block") {
      dropdownContent.style.display = "none";
    } else {
      dropdownContent.style.display = "block";
    }
  });
}


const stars = document.querySelectorAll('.form-review-rating .review-rating')


stars.forEach((star, index1) =>{
  star.addEventListener('click', ()=>{
    document.querySelector('.rating-input').value = index1;
    
    stars.forEach((star, index2) =>{
      index1 >= index2 ? star.classList.add("selected") : star.classList.remove("selected");
    });
  });
});

var formsReview = document.querySelectorAll('.form-review');
var overlays = document.querySelectorAll('.overlay-review');
var closeBtn = document.querySelector('.close-btn');
var reviewBtns = document.querySelectorAll('.review-btn');

reviewBtns.forEach((reviewBtn)=>{
    reviewBtn.addEventListener('click', function(){
      this.nextElementSibling.classList.remove('hide-modal');
  })
});
formsReview.forEach((formReview)=>{
  formReview.addEventListener('click', function(event){
    event.stopPropagation();
  });
});

closeBtn.onclick = function(){
  event.preventDefault();
  closeOverlays();
};

function closeOverlays(){
  overlays.forEach((overlay)=>{
      overlay.classList.add('hide-modal');
    
    } );
  }

overlays.forEach((overlay)=>{
  overlay.addEventListener('click', function(){
    overlay.classList.add('hide-modal');
  });
})