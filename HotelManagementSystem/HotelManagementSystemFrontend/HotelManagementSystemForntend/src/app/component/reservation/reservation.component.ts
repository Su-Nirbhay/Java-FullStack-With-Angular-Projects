import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-reservation',
  imports: [],
  templateUrl: './reservation.component.html',
  styleUrl: './reservation.component.css'
})
export class ReservationComponent {
  title="Sunrise Hotel"

  constructor(private router : Router , private http: HttpClient ){
  
  }

  myProfile(){
    this.router.navigate(['/user']);
  }

}
