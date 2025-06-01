import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { HotelDetailsService } from '../../service/HotelDetails/hotel-details.service';

@Component({
  selector: 'app-home-page',
  imports: [CommonModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent {
title = 'Sunrise Hotel';
  contact_nr = "456-456-1234";
  email = "sunrise.hotel@gmail.com";

  
  constructor(private router : Router , private http: HttpClient , private hotelDetails : HotelDetailsService){

  }

  validate: boolean = false;
  ngOnInit() {
    const savedState = sessionStorage.getItem('isLoggedIn');
    this.validate = savedState === 'true';
  }

  validation() {
    this.validate = true;
    sessionStorage.setItem('isLoggedIn', 'true');
  }

  logout() {
    this.validate = false;
    sessionStorage.removeItem('isLoggedIn');
  }
  
  login_popup = false;
  signup_popup = false
  login(){
    this.login_popup=true;
    this.signup_popup=false;
  }
  signup(){
    this.signup_popup=true;
    this.login_popup=false;
  }
  popup(){
    this.login_popup=false;
    this.signup_popup=false;
  }

  
  

  myProfile(){
    this.router.navigate(['/user']);
  }

  ACSingleBed:number = 0;
  ACDoubleBed:number = 0;
  NONACSingleBed:number = 0;
  NONACDoubleBed:number = 0;

  getPrice(){
    this.hotelDetails.getPriceACSingleBed().subscribe(response => {
      this.ACSingleBed=response;
    });
    this.hotelDetails.getPriceACDoubleBed().subscribe(response => {
      this.ACDoubleBed=response;
    });
    this.hotelDetails.getPriceNONACSingleBed().subscribe(response => {
      this.NONACSingleBed=response;
    });
    this.hotelDetails.getPriceNONACDoubleBed().subscribe(response => {
      this.NONACDoubleBed=response;
    });


  }

}

