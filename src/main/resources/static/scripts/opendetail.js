var ordered = document.querySelectorAll('.orderd');
var i;
for (i = 0; i < ordered.length; i++) {
  ordered[i].addEventListener("click", function() {
    
    var dropdownContent = this.nextElementSibling;
    if (dropdownContent.style.display === "block") {
      dropdownContent.style.display = "none";
    } else {
      dropdownContent.style.display = "block";
    }
  });
}