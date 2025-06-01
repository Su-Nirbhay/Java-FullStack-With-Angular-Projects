import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-user',
  imports: [],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  title = 'Sunrise Hotel';
  contact_nr = "456-456-1234";
  email = "sunrise.hotel@gmail.com";


  constructor(private router : Router , private http: HttpClient) { }
  
  back(){
    this.router.navigate(['']);
  }


}
