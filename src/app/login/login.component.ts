// import { Component } from '@angular/core';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-login',
//   templateUrl: './login.component.html',
//   styleUrls: ['./login.component.scss']
// })
// export class LoginComponent {
//   id: string = '';
//   otp: string = '';
//    generatedOTP: string = ''; // Store the generated OTP here
//   otpMatched: boolean = false; // Flag to track OTP validation
//   constructor(private router: Router) {} // Inject the Router service in the constructor


// // Generate a random OTP and display it in the console
//   generateOTP() {
//     const length = 6; // Length of the OTP
//     const charset = "0123456789"; // Characters to include in the OTP
//     let otp = "";

//     for (let i = 0; i < length; i++) {
//       const randomIndex = Math.floor(Math.random() * charset.length);
//       otp += charset.charAt(randomIndex);
//     }
//  this.generatedOTP = otp;
//     console.log('Generated OTP:', otp); // Display OTP in the console
//   }
//     validateOTP() {
//     this.otpMatched = this.generatedOTP === this.otp;
//   }


//   login() {
    
//     console.log('OTP:', this.otp);
//     this.router.navigate(['/employee']);
//   }
   
// }
//-------------------------------

import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
   styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  username: string = '';
  otp: string = '';
  generatedOTP: string = ''; // Store the generated OTP here


  constructor(private router: Router) {}
  
  generateOTP() {
    const length = 6; // Length of the OTP
    const charset = "0123456789"; // Characters to include in the OTP
    let otp = "";

    for (let i = 0; i < length; i++) {
      const randomIndex = Math.floor(Math.random() * charset.length);
      otp += charset.charAt(randomIndex);
    }
 this.generatedOTP = otp;
    console.log('Generated OTP:', otp); // Display OTP in the console
  }
  onLoginSubmit(): void {
    // Implement authentication logic here
   
// if (this.username === 'user' && this.otp === this.generatedOTP) {
//       // Successful login
//       this.router.navigate(['/dashboard']);
//     } else {
//       // Invalid login, show an error message
//       alert('Invalid credentials. Please try again.');
//     }
    // For simplicity, we'll assume successful login and navigate to the dashboard
    this.router.navigate(['/dashboard']);
  }
}



