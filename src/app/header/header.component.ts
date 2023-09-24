import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router) { }
navigateToBill(event: Event): void {
    const selectedOption = (event.target as HTMLSelectElement).value;
    switch (selectedOption) {
      case 'unpaid':
        this.router.navigate(['Payment']);
        break;
      case 'paid':
        this.router.navigate(['UnPaid']);
        break;
      case 'all':
        this.router.navigate(['/all']);
        break;
      default:
        // Do nothing or handle the default case as needed
        break;
    }
  }

  ngOnInit(): void {
  }


}
