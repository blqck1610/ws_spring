

const form = document.getElementById('register-form');
form.addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission for now
  
    // Get input field values
   
    const name = document.getElementById('username').value;

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const comfPassword = document.getElementById('re-password').value;
    // Add more input fields as needed
  
    // Reset border colors
   
   
    var errorMessages = document.querySelector(".error-message");
    errorMessages.style.display = "none";
  
  
    // Perform validation
    let isValid = true;
  
    if (name === "") {
      isValid = false;
      const nameInput = document.getElementById('username');
      nameInput.classList.add('invalid-input');
      errorMessages.style.display = "block";
      
    }
    if (password === "") {
      isValid = false;
      const passwordInput = document.getElementById('password');
      passwordInput.classList.add('invalid-input');
      
    }
    if(comfPassword != password){
		isValid = false;
      const comfPasswordInput = document.getElementById('re-password');
      comfPasswordInput.classList.add('invalid-input');
   
	}
    
  
    if (!isValidEmail(email)) {
      isValid = false;
      const nameInput = document.getElementById('email');
        nameInput.classList.add("invalid-input");
       
    }
  
  
    // Add more validation as needed
  
    if (isValid) {
      // Form is valid, you can proceed with submission or other actions
      // For example, you can uncomment the line below to submit the form
      // document.getElementById("myForm").submit();
     
     // form.submit();
    }
  });
  
  function isValidEmail(email) {
    // Simple email validation regex
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }
