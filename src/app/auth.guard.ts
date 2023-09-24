import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router) {}

  canActivate(): boolean {
    // Implement your authentication check here
    const isAuthenticated = '';

    if (!isAuthenticated) {
      this.router.navigate(['/login']);
      return false;
    }

    return true; // Return true if authenticated
  }
}
